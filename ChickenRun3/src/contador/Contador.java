package contador;

import java.util.Timer;
import java.util.TimerTask;

public class Contador extends Timer {
	
	private int tempoTrascorrido=0;
	
	public Contador(){
		
		scheduleAtFixedRate(new TimerTask(){
			public void run(){
				tempoTrascorrido++;
			}
		}, 0, 1000);
	}
	public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
		
		super.scheduleAtFixedRate(task, delay, period);
	}
	
	public int getTempoTranscorrido(){
		return tempoTrascorrido;
	}
	
	public void restart(){
		this.tempoTrascorrido = 0;
	}
	
}
