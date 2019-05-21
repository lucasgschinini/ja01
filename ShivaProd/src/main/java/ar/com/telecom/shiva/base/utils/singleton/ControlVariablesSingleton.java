package ar.com.telecom.shiva.base.utils.singleton;

import ar.com.telecom.shiva.base.aspectos.util.Invocacion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.utils.LRUCache;


public class ControlVariablesSingleton {
	
	private static boolean tracearSQL = false;
	private static ReintentoExcepcion reintentoExcepcion = null;
	private static LRUCache<String, Invocacion> passIntentosLogin = new LRUCache<String, Invocacion>(Constantes.TAMANIO_CACHE);
	private static LRUCache<String, Invocacion> usuarioIntentosLogin = new LRUCache<String, Invocacion>(Constantes.TAMANIO_CACHE);
	
	//----------------- Singleton ------------------
	private static ControlVariablesSingleton SINGLETON = null;
	
	// Constructor privado
	private ControlVariablesSingleton() {
		tracearSQL = false;
	}
	
    // creador sincronizado para protegerse de posibles problemas  
	// multi-hilo para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (SINGLETON == null) { 
            SINGLETON = new ControlVariablesSingleton();
        }
    }
    public static ControlVariablesSingleton getInstance() {
        if (SINGLETON == null) createInstance();
        return SINGLETON;
    }
    //----------------- fin - Singleton ------------------
    
    /**
     * Permitir tracear SQL
     */
    public static void permitirTraceoSQL() {
    	tracearSQL = true;
	}
    
    /**
     * Quitar traceo a nivel Debug
     */
    public static void quitarTraceoSQL() {
    	tracearSQL = false;
	}

    /**
     * Verificar traceo SQL
     * @return
     */
	public static boolean isTracearSQL() {
		return tracearSQL;
	}

	public static ReintentoExcepcion getReintentoExcepcion() {
		return reintentoExcepcion;
	}

	public static void setReintentoExcepcion(ReintentoExcepcion reintentoExcepcion) {
		ControlVariablesSingleton.reintentoExcepcion = reintentoExcepcion;
	}

	public static LRUCache<String, Invocacion> getPassIntentosLogin() {
		return passIntentosLogin;
	}
	public static LRUCache<String, Invocacion> getUsuarioIntentosLogin() {
		return usuarioIntentosLogin;
	}
	

}


