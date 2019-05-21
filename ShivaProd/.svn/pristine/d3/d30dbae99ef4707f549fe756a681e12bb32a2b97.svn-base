package ar.com.telecom.shiva.base.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class ControlThread implements Runnable {

	private static ConcurrentMap<String, Thread> threadsEnEspera 
				= new ConcurrentHashMap<String, Thread>();
	
	private static Queue<Thread> threads = new LinkedList<Thread>();
	
	private static boolean activo = false;
	
	private long tiempoEspera = Constantes.$1_MIN_MS;
	private int concurrencias = 0;
	
	/**
	 * Constructor
	 * @param threads
	 * @param concurrencias
	 */
	@SuppressWarnings("static-access")
	public ControlThread (ConcurrentHashMap<String, Thread> threads) {
		this.threadsEnEspera = threads;
		this.activo = false;
	}
	
	/**
	 * Arranco la ejecucion de threads
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		
		this.activo = true;
		
		try {
			for (Entry<String, Thread> e: threadsEnEspera.entrySet()) {
				
				String id = (String) e.getKey();
				Thread hilo = (Thread) e.getValue();
				
				//Veo que si estan todos ocupados
				while (estanTodosHilosVivos()) {
					try {
						Thread.sleep(tiempoEspera);
					} catch (InterruptedException ie) {}
				}
				
				threads = monitorThreads(hilo);
				threadsEnEspera.remove(id);
			}
		} catch (Throwable e) {
			Traza.error(getClass(), e.getMessage(), e);
		}
		
		//Al finalizar el runnable
		this.activo = false;
	}
	
	/**
	 * Monitorea los threads
	 * @param hiloTB
	 * @return
	 */
	private Queue<Thread> monitorThreads(Thread hiloTB) {
		try {
			if (threads!=null && !threads.isEmpty()) {
				for (Iterator<Thread> i = threads.iterator(); i.hasNext();) {
					Thread hilo = i.next();
					if (!hilo.isAlive()) {
						i.remove();
					}
				}
			}
			
			hiloTB.start();
			threads.add(hiloTB);
			
		} catch (Throwable e) {
			Traza.error(getClass(), e.getMessage(), e);
		}
		
		return threads;
	}
	
	/**
     * Verifico que todos están vivos
     * Asi podemos limitar el uso de threads de acuerdo a la cantidad de concurrencias
     * @param threads
     * @param concurrencias
     * @return
     */
    public boolean estanTodosHilosVivos() {
    	int contConcurrencias = 0;
    	if (threads!=null && !threads.isEmpty()) {
    		for (Thread hilo : threads) {
    			if (hilo.isAlive()) {
    				contConcurrencias++;
    			}
    		}
    	}
    	if (contConcurrencias >= concurrencias) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * Verifico que si hay algun hilo vivo
     * Asi podemos limitar el uso de threads de acuerdo a la cantidad de concurrencias
     * @param threads
     * @param concurrencias
     * @return
     */
    public static boolean hayAlgunHiloVivo() {
    	if (threads!=null && !threads.isEmpty()) {
    		for (Thread hilo : threads) {
    			if (hilo.isAlive()) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
	
	/**
	 * @return the activo
	 */
	public static boolean isActivo() {
		return activo;
	}

	/**
	 * @return the threads
	 */
	public ConcurrentMap<String, Thread> getThreadsEnEspera() {
		return threadsEnEspera;
	}

	/**
	 * @param concurrencias the concurrencias to set
	 */
	public void setConcurrencias(int concurrencias) {
		this.concurrencias = concurrencias;
	}

}
