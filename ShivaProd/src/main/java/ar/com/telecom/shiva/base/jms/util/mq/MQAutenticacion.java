package ar.com.telecom.shiva.base.jms.util.mq;

/**
 * Clase para usar el usuario y contraseña para conectar al Server MQSeries
 */
public class MQAutenticacion {

	private String user;
	private String pass;

	/**
	 * 
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
