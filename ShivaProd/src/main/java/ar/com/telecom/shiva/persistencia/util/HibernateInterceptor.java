package ar.com.telecom.shiva.persistencia.util;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;

/**
 * Autoayuda
 * Ver url: http://learningviacode.blogspot.in/2012/06/intercepting-events-in-hibernate.html
 */
public class HibernateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	@Override
	public String onPrepareStatement(String sql) {
		Traza.transaccionBBDD(getClass(), sql);
		return sql;
	}

	/**
	 * 
	 */
	public boolean onSave(Object entity,Serializable id, Object[] state,String[] propertyNames,Type[] types) throws CallbackException {
		if (ControlVariablesSingleton.isTracearSQL()) {
			final int length = state.length;
		    for (int i = 0; i < length; i++) {
		    	if (entity != null && propertyNames[i]!= null && types[i] != null) {
		    		Traza.transaccionBBDD(getClass(), "onSave: [" + Utilidad.getClassName(entity.getClass()) + " - "+ 
		    					propertyNames[i] +" - " + types[i].getName() + "] : " + state[i]);
		    	}
		    }
		}
	    return false;
	}
	
	/**
	 * 
	 */
	public boolean onFlushDirty(Object entity,Serializable id, Object[] currentState,Object[] previousState,
		String[] propertyNames,Type[] types) throws CallbackException {
		if (ControlVariablesSingleton.isTracearSQL()) {
			final int length = currentState.length;
		    for (int i = 0; i < length; i++) {
		    	if (entity != null && propertyNames[i]!= null && types[i] != null) {
			    	Traza.transaccionBBDD(getClass(), "onFlushDirty: [" + Utilidad.getClassName(entity.getClass()) + " - "+ 
			    			propertyNames[i] +" - " + types[i].getName() + "] : previous state : " + previousState[i] + " , current state : " + currentState[i]);
		    	}
		    }
		}
		return false;
		
	}
	
	/**
	 * Called before an object is deleted. It is not recommended that the interceptor modify the state.
	 */
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (ControlVariablesSingleton.isTracearSQL()) {
			final int length = state.length;
		    for (int i = 0; i < length; i++) {
		    	if (entity != null && propertyNames[i]!= null && types[i] != null) {
		    		Traza.transaccionBBDD(getClass(), "onDelete: [" + Utilidad.getClassName(entity.getClass()) + " - "+ propertyNames[i] +" - " + types[i].getName() + "] : " + state[i]);
		    	}
		    }
		}
	}
	
	/**
	 * 
	 */
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (ControlVariablesSingleton.isTracearSQL()) {
			/**
			final int length = state.length;
			for (int i = 0; i < length; i++) {
				if (entity != null && propertyNames[i]!= null && types[i] != null) {
					Traza.transaccionBBDD(getClass(), "onLoad: [" + Utilidad.getClassName(entity.getClass()) + " - "+ 
	    					propertyNames[i] +"- " + types[i].getName() + "] : " + state[i]);
		    	}
		    }
		    */
		}
	    return false;
	}
}
