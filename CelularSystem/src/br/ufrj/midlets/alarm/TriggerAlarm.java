package br.ufrj.midlets.alarm;

import java.util.Date;

import javax.microedition.io.PushRegistry;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class TriggerAlarm extends MIDlet {

	private final String RECORD_STORE = "CelularSystem";
	private final String MSG_DEFAULT = "Celular Roubado!";
	private final int TEMPO_VIBRACAO = 1000*60*1;
	private final int TEMPO_LUZ = 1000*60*1;
	private final long PROXIMO_ALARM = 1000*60*4;
	private final long SLEEP = 1000*60*1;
	
	private Display display;
	private Alert alarm;
	private RecordStore arquivo;
	private Thread thread;
	
	public TriggerAlarm() {
		abrirArquivo();
		checaNovoRegistro();
		display = Display.getDisplay(this);
		alarm = new Alert(MSG_DEFAULT);
		alarm.setTimeout(Alert.FOREVER);
				
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		fecharArquivo();
		thread = null;

	}

	protected void pauseApp() {
		

	}

	protected void startApp() throws MIDletStateChangeException {
		display.setCurrent(alarm);
		if (thread == null){
			thread = new Thread(new DisparaAlarme());
		}
		
		thread.start();

	}
	
	public void abrirArquivo(){
		try {
			arquivo = RecordStore.openRecordStore(RECORD_STORE, false);
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void fecharArquivo(){
		try {
			arquivo.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}
	
	//verifica se o midlet deve se registrar novamente
	public void checaNovoRegistro(){
		try {
			
			if (arquivo.getNumRecords() != 0){
				if (new String(arquivo.getRecord(1)).equals("1")){
					PushRegistry.registerAlarm(this.getClass().getName(), new Date().getTime() + PROXIMO_ALARM);
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class DisparaAlarme implements Runnable{

		public void run() {
			try {
				if (arquivo.getNumRecords()!=0){
					if (new String(arquivo.getRecord(1)).equals("1")){
					
						alarm.setString(new String(arquivo.getRecord(2)));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			display.flashBacklight(TEMPO_LUZ);
			display.vibrate(TEMPO_VIBRACAO);
			try {
				Thread.sleep(SLEEP);
				notifyDestroyed();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
		
}
