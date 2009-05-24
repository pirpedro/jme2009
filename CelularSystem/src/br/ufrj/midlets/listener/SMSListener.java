package br.ufrj.midlets.listener;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.PushRegistry;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;

import br.ufrj.midlets.alarm.TriggerAlarm;

public class SMSListener extends MIDlet implements MessageListener{

	private final String CONNECTION_TYPE = "sms";
	private final String SMS_PORT = "5000";
	private final String RECORD_STORE = "CarSystem";
	private final String ALARM_MIDLET = TriggerAlarm.class.getName();
	private final int ALARM_DELAY = 1000*10;
	
	private Thread thread;
	private MessageConnection conexaoSMS;
	private TextMessage msg;
	private RecordStore arquivo;
	
	//private TriggerAlarm alarm;
		
	public SMSListener() {
		
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		fecharArquivo();
		thread = null;
		if (conexaoSMS != null){
			try {
				conexaoSMS.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

	protected void pauseApp() {
		thread = null;

	}

	protected void startApp() throws MIDletStateChangeException {
		
		if (conexaoSMS == null){
			try {
				conexaoSMS = (MessageConnection) Connector.open(CONNECTION_TYPE + "://:" + SMS_PORT);
				conexaoSMS.setMessageListener(this);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}

	public void notifyIncomingMessage(MessageConnection arg0) {
		
		abrirArquivo();
		if (thread == null){
			thread = new Thread(new ReceiveMessage());
			thread.start();
			
			
			
		}
		
	}
	
	//abre o RecordStore
	public void abrirArquivo(){
		try {
			arquivo = RecordStore.openRecordStore(RECORD_STORE, true, RecordStore.AUTHMODE_PRIVATE, true);
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}
	
	//fecha o RecordStore
	public void fecharArquivo(){
		try {
			arquivo.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	class ReceiveMessage implements Runnable{
		
		
		private String mensagem;

		public void run() {
			
			try {
				msg = (TextMessage) conexaoSMS.receive();
				
				if (msg != null){
					if (msg instanceof TextMessage){
						mensagem = ((TextMessage) msg).getPayloadText();
						
						if (mensagem.equals("Cancelar")){
							cancelarRMS();
						}else{
							
							try { 
								
								PushRegistry.registerAlarm(ALARM_MIDLET, new Date().getTime()+ ALARM_DELAY);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}							
							ativarRMS();
								
								
							
						
						}
						
					}
					
				}
				
				thread = null;
				
				notifyDestroyed();
			
			} catch (InterruptedIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//cancela o alarme (valor: 0)
		public void cancelarRMS(){
			
			try {
				byte[] dado = "0".getBytes();
				arquivo.setRecord(1,dado , 0, dado.length);
				dado = mensagem.getBytes();
				arquivo.setRecord(2,dado , 0, dado.length);
			} catch (RecordStoreNotOpenException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		//cria ou atualiza o recordStore
		//Primeiro ID: acionar o alarme (valor:1)
		//Segundo ID: Mensagem que será mostrada
		public void ativarRMS(){
			
			try {
				//cria os 2 registros no RecordStore
				if (arquivo.getNumRecords() == 0){
					byte[] dado = "1".getBytes();
					arquivo.addRecord(dado, 0, dado.length);
					dado = mensagem.getBytes();
					arquivo.addRecord(dado, 0, dado.length);
												
				}
				//atualiza os 2 registros no RecordStore
				else{
					byte[] dado = "1".getBytes();
					arquivo.setRecord(1,dado , 0, dado.length);
					dado = mensagem.getBytes();
					arquivo.setRecord(2,dado , 0, dado.length);
					
				}
			} catch (RecordStoreNotOpenException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
	}

}
