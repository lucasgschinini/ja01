package ar.com.telecom.shiva.base.jms.util;

import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.FormatoMensajeJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class JmsUtilidad {

	/**
	 * Verifico que si la longitud del mensaje es correcta de acuerdo a lo configurado
	 * @param formatoMensaje
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	public static void verificarLongitudTotalMensaje(int longitudCabecera, int longitudCuerpo, String mensaje) 
			throws NegocioExcepcion
	{
		if ((longitudCabecera+longitudCuerpo) != mensaje.length()) {
	    	String mensajeError = "Su longitud fija es incorrecto - Mensaje Length:" + mensaje.length();
	    	throw new JmsExcepcion(mensajeError, null, mensajeError);
	    }
	}
	
	/**
	 * Genero un mensaje a enviar de acuerdo al formato configurado y los valores ingresados
	 * @param formatoMensaje
	 * @param listaCamposAEnviar
	 * @return
	 */
	public static String generarMensaje(FormatoMensajeJMS formatoMensaje, 
			List<CampoAEnviarJMS> listaCamposAEnviar, String datosCabecera) 
	{
	
		String mensaje = "";
		
		// Si la longitud de la cabecera es mayor que cero,
		// puede incorporar en el cuerpo (actua como un flag)
		if (formatoMensaje.getLontitudFijaCabeceraSerializable() > 0) {
			if (!Validaciones.isNullOrEmpty(datosCabecera)) {
				mensaje += Utilidad.rellenarEspaciosDerecha(datosCabecera, 
						formatoMensaje.getLontitudFijaCabeceraSerializable());	 
			}
		}
		
		List<Campo> camposSalida = formatoMensaje.getCamposSerializableOrdenada();
	    for (Campo campo: camposSalida){
	    	String nombreCampo = campo.getNombre();
	    	int longitud = campo.getLongitud();
	    	
	    	String valorCampo = "";
	    	if (campo.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    		
	    		for (CampoAEnviarJMS campoAEnviar: listaCamposAEnviar){
					String nombre= campoAEnviar.getNombreCampo();
					if (nombre.equalsIgnoreCase(nombreCampo)) {
						valorCampo = campoAEnviar.getValorCampo();
						break;
					}
	    		}
	    		
	    		mensaje += Utilidad.rellenarEspaciosDerecha(valorCampo, longitud).substring(0, longitud);  
	    	} 
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    		for (CampoAEnviarJMS campoAEnviar: listaCamposAEnviar){
					String nombre= campoAEnviar.getNombreCampo();
					if (nombre.equalsIgnoreCase(nombreCampo)) {
						valorCampo = campoAEnviar.getValorCampo();
						break;
					}
	    		}
	    		
	    		mensaje += Utilidad.rellenarCerosIzquierda(valorCampo, longitud).substring(0, longitud);
	    	}
	    	
			if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
    			for (int i=1; i <= longitud; i++) {
    				Integer contador = Integer.valueOf(i);
    				
    				for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					String valorCampoAgrupador = "";
    					
    					for (CampoAEnviarJMS campoAEnviar: listaCamposAEnviar){
    						String nombre= campoAEnviar.getNombreCampo();
    						Integer posicion = campoAEnviar.getPosicion();
    						
    						if (nombre.equalsIgnoreCase(nombreCampo) 
    								&& nombreCampoAgrupador.equalsIgnoreCase(campoAEnviar.getNombreCampoAgrupador())
    								&& posicion != null
    								&& contador.compareTo(posicion) == 0) {

    							valorCampoAgrupador = campoAEnviar.getValorCampo();
    							break;
    						}
    					}
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    		    		mensaje += Utilidad.rellenarEspaciosDerecha(valorCampoAgrupador, longitudAgrupador).substring(0, longitudAgrupador);  
	    		    	} 
	    		    	if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    		    		mensaje += Utilidad.rellenarCerosIzquierda(valorCampoAgrupador, longitudAgrupador).substring(0, longitudAgrupador);
	    		    	}
	    			}
    			}
    		}
		}
	    
	    return mensaje;
	}
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static String buscarServicio(MensajeServicioEnum servicio) throws NegocioExcepcion {
		
		if (MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_APROP_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio");
		}  
		if (MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_INTERES.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.servicio");
		}
		if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.servicio");
		} 
		if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.descobro.servicio");
		}
		
		throw new NegocioExcepcion("buscarServicio: Servicio incorrecto");
	}
	
	
	
	/**
	 * 
	 * @param servicio
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static String buscarContrato(MensajeServicioEnum servicio) throws NegocioExcepcion {
		//Contrato 1
		if (MensajeServicioEnum.MIC_APROP_DEUDA.equals(servicio)
				|| MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_APROP_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_CONFIRMACION.equals(servicio)
				|| MensajeServicioEnum.MIC_DESAPROPIACION.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.contrato.1");
		}
		if (MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.contrato.1");
		}
		if (MensajeServicioEnum.MIC_DESCOBRO.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.contrato.1");
		}
		if (MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.descobro.contrato.1");
		}
		
		
		//Contrato 2
		if (MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_APROP_MP.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.contrato.2");
		}  
		if (MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_INTERES.equals(servicio)) 
		{
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.contrato.2");
		}
		if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(servicio)) {
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.contrato.2");
		}
		if (MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(servicio)
				|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(servicio)) {
			return Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.descobro.contrato.2");
		}
		
		throw new NegocioExcepcion("buscarContrato: Se debe tener un contrato configurado de acuerdo al servicio");
	}
}
