package br.ufrj.midlets.listener;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.io.Connector;
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
	
	private Thread thread;
	private MessageConnection conexaoSMS;
	private TextMessage msg;
	private RecordStore arquivo;
	
	private TriggerAlarm alarm;
		
	public SMSListener() {
		
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		try {
			arquivo.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
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
		
		try {
			arquivo = RecordStore.openRecordStore("BD", true,RecordStore.AUTHMODE_PRIVATE,true);
			
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		
		
		if (thread == null){
			thread = new Thread(new ReceiveMessage());
			thread.start();
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
							ativarRMS();
							alarm = new TriggerAlarm(mensagem);
						}
						
					}
					
				}
			
			
			
			
			} catch (InterruptedIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void cancelarRMS(){
			
		}
		
		public void ativarRMS(){
			
		}
		
	}

}
