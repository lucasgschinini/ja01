package ar.com.telecom.shiva.base.utils;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import ar.com.telecom.shiva.base.utils.logs.Traza;

public class UtilPropertiesHolder extends PropertyPlaceholderConfigurer {
	
	private static final String LDAP_PASSWORD = "ldap.password.generico.shiva"; 
	private static final String MQ_MIC_PASSWORD = "mq.mic.auth.pass";
	private static final String BATCH_PASSWORD = "batch.password";
	private static final String SAP_S4_PASSWORD = "ws.sap.auth.s4.password";
	private static final String SAP_R3_PASSWORD = "ws.sap.auth.r3.password";
	private static final String ICE_PASSWORD = "ws.ice.auth.password";

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
		String value = props.getProperty(placeholder);
		
		if (LDAP_PASSWORD.equalsIgnoreCase(placeholder)
				|| MQ_MIC_PASSWORD.equalsIgnoreCase(placeholder)
				|| SAP_S4_PASSWORD.equalsIgnoreCase(placeholder)
				|| SAP_R3_PASSWORD.equalsIgnoreCase(placeholder)
				|| ICE_PASSWORD.equalsIgnoreCase(placeholder)
				|| BATCH_PASSWORD.equalsIgnoreCase(placeholder)) {
			value = desencriptar(value);
		}
		
		return value;
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String desencriptar(String value) {
		String valorDesencriptado = "";
		try {
			valorDesencriptado = Encriptador.decrypt(value);
		} catch (Exception e) {
			System.err.println("Error al desencriptar: " + Utilidad.getStackTrace(e));
			Traza.error(getClass(), "Error al desencriptar:", e);
		} 
		return valorDesencriptado;
	}
	
}
