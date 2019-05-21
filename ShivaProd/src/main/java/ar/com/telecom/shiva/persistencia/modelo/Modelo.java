package ar.com.telecom.shiva.persistencia.modelo;

import java.io.Serializable;

public class Modelo extends Object implements Serializable {

	private static final long serialVersionUID = 7681097413607571180L;
	
	private Serializable id;
	
	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}
	
	/**
	 * Para los traceos
	 */
	public String toString() {
		return this.getClass().getName() + " ID=["+getId()+"]";
	}
	
	/**
	 * Para comparar
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (!(obj instanceof Modelo)) {
			return false;
		}
		
		if (((Modelo) obj).getId() == null 
				|| getId() == null) {
			return false;
		}
		
		return ((Modelo) obj).getId().equals(this.getId());
	}
	
	/**
	 * Para el Hashtable
	 */
	public int hashCode() {
		if (getId() == null) {
			return super.hashCode();
		} else {
			return getId().hashCode();
		}
	}
}
