package ar.com.telecom.shiva.base.utils.singleton;

import java.util.concurrent.ConcurrentHashMap;

import ar.com.telecom.shiva.base.utils.ControlThread;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class ControlThreadSingleton {
	
	private static ControlThread controlThread = null;
	
	//----------------- Singleton ------------------
	private static ControlThreadSingleton SINGLETON = null;
	
	// Constructor privado
	private ControlThreadSingleton() {
		controlThread = new ControlThread(new ConcurrentHashMap<String, Thread>());
	}
	
    // creador sincronizado para protegerse de posibles problemas  
	// multi-hilo para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (SINGLETON == null) { 
            SINGLETON = new ControlThreadSingleton();
        }
    }
    public static ControlThreadSingleton getInstance() {
        if (SINGLETON == null) createInstance();
        return SINGLETON;
    }
    //----------------- fin - Singleton ------------------
    
    /**
     * Agrego la cantidad de concurrencias 
     * @param concurrencias
     */
    public static void setConcurrencias(int concurrencias) {
    	
    	if (controlThread==null) {
    		controlThread = new ControlThread(new ConcurrentHashMap<String, Thread>());
    	}
		controlThread.setConcurrencias(concurrencias);
	}
    
    /**
     * Agrego el hilo
     * @param hilo
     */
    @SuppressWarnings("static-access")
	public static void agregarHilo(Thread hilo) {
    	controlThread.getThreadsEnEspera().put(hilo.getName(), hilo);
    	
    	Traza.auditoria(ControlThreadSingleton.class, 
			Utilidad.reemplazarMensajes("Se ha agregado un nuevo hilo {0} en la lista de hilos.", hilo.getName()));
    	
    	if (!controlThread.isActivo()) {
    		controlThread.run();
    	}
    }
    
    /**
	 * Se espera hasta que terminen todos los threads 
	 * @param threads
	 * @param countMaxCycle
	 */
	@SuppressWarnings("static-access")
	public static void esperarFinTodosHilos() {
		int countCycle=1;
		int countMaxCycle = 10;
		try {
			while (controlThread.hayAlgunHiloVivo()) {
				if (countCycle < countMaxCycle) {
					countCycle++;
					Thread.sleep(5000);
				} else {
					break;
				}
			}
		} catch (InterruptedException ignore) {}
	}
}


