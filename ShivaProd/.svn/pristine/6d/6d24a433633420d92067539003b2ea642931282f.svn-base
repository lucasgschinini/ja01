package ar.com.telecom.shiva.base.jms.mapeos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicRespuestaRecepcionEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaRecepcionSalida;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * Unicamente en las comunicaciones Asincronicas
 * @author u562201
 *
 */
public class MicRespuestaRecepcionMapeador extends MapeadorJMS {

	@Override	
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicRespuestaRecepcionEntrada datosAEnviar = (MicRespuestaRecepcionEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		listaCamposAEnviar.add(new CampoAEnviarJMS("idOperacionTransaccion", null, null, String.valueOf(datosAEnviar.getIdOperacionTransaccion())));
		
		Integer contador = Integer.valueOf(1);
		listaCamposAEnviar.add(new CampoAEnviarJMS("resultado", "resultadoInvocacion", contador, 
				datosAEnviar.getResultado().getResultadoInvocacion()));
		
		String servicio = JmsUtilidad.buscarServicio(datosAEnviar.getServicio());
		String mensaje = JmsUtilidad.generarMensaje(
				getDefaultFormatoMensajeJMS(), listaCamposAEnviar, servicio);
		
		JmsUtilidad.verificarLongitudTotalMensaje(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraSerializable(), 
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeSerializable(), mensaje);
	    
		return mensaje;
	}

	@Override
	public boolean verificarLongitudMsjRecibida(String msj, boolean esSincronico)
			throws NegocioExcepcion {
		
		int longitudFijaCabeceraDeserializable = 0;
		if (esSincronico) {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync();
		} else {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableAsync();
		}
		int total = longitudFijaCabeceraDeserializable 
				+ getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable();
		
		if (total != msj.length()) {
	    	return false;
	    } else {
	    	return true;
	    } 
	}
	
	
	@Override
	public JMS deserializar(String mensajeRecibido, boolean camposSeteables, boolean esSincronico) throws NegocioExcepcion {
		int longitudFijaCabeceraDeserializable = 0;
		if (esSincronico) {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync();
		} else {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableAsync();
		}
		
		JmsUtilidad.verificarLongitudTotalMensaje(
				longitudFijaCabeceraDeserializable, 
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable(), 
				mensajeRecibido);
		
		
		//Sacar la cabecera del mensaje
		String msg = mensajeRecibido;
		if (longitudFijaCabeceraDeserializable > 0) {
			msg = mensajeRecibido.substring(longitudFijaCabeceraDeserializable, msg.length());
		}
		
		MicRespuestaRecepcionSalida datosRecibidos = 
				new MicRespuestaRecepcionSalida();
		
		List<Campo> camposEntrada = getDefaultFormatoMensajeJMS().getCamposDeserializableOrdenada();
		int posInicial = 0;
		int posFinal = 0;
	    for (Campo campo: camposEntrada){
	    	String nombreCampo = campo.getNombre();
	    	int longitud = campo.getLongitud();
	    	
	    	if (TipoDatoEnum.ALFANUMERICO.equals(campo.getTipoDato())) {
	    		if ("idOperacionTransaccion".equalsIgnoreCase(nombreCampo)) {

	    			try {
	    				posFinal = posInicial + longitud;
	    		    	String idOperacionTransaccion= msg.substring(posInicial, posFinal);
		    			datosRecibidos.setIdOperacionTransaccion(idOperacionTransaccion.trim());
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "1001 - Error en el campo idOperacionTransaccion";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    	} 
	    	
	    	if (TipoDatoEnum.NUMERICO.equals(campo.getTipoDato())) {
	    	}
	    	
			if (TipoDatoEnum.AGRUPADOR.equals(campo.getTipoDato())) {
				
				if ("retorno".equalsIgnoreCase(nombreCampo)) {
					JmsCopiaTecnica retorno = new JmsCopiaTecnica();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (TipoDatoEnum.ALFANUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("codRetorno".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	String codRetorno=msg.substring(posInicial, posFinal);
    				    			retorno.setCodRetorno(codRetorno.trim());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1002 - Error en el campo codRetorno";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlCode".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	String sqlCode=msg.substring(posInicial, posFinal);
    				    			retorno.setSqlCode(sqlCode.trim());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1003 - Error en el campo sqlCode";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlErrml".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	String sqlErrml=msg.substring(posInicial, posFinal);
    				    			retorno.setSqlErrml(sqlErrml.trim());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1004 - Error en el campo sqlErrml";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlErrmc".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	String sqlErrmc=msg.substring(posInicial, posFinal);
    				    			retorno.setSqlErrmc(sqlErrmc.trim());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1005 - Error en el campo sqlErrmc";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("ultRegProc".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	String ultRegProc=msg.substring(posInicial, posFinal);
    				    			retorno.setUltRegProc(ultRegProc.trim());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1006 - Error en el campo ultRegProc";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    					if (TipoDatoEnum.NUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("msgLength".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	Long msgLength=Long.valueOf(msg.substring(posInicial, posFinal));
    				    			retorno.setMsgLength(msgLength.toString());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1007 - Error en el campo msgLength";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("cantRegistros".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	Long cantRegistros=Long.valueOf(msg.substring(posInicial, posFinal));
    				    			retorno.setCantRegistros(cantRegistros.toString());
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1008 - Error en el campo cantRegistros";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    				} // Fin - for
    				
    				datosRecibidos.setRetorno(retorno);
				}
    		}
		}
		return datosRecibidos;
	}
	
	public boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion {
		int longitudFijaCabeceraDeserializable = 0;
		if (esSincronico) {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync();
		} else {
			longitudFijaCabeceraDeserializable =
					getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableAsync();
		}
		
		//Si quiero validar el servicio en la cabecera, lo hago
		if (longitudFijaCabeceraDeserializable > 0) {
			String servicioADC = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio");
			String servicioCargo = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.servicio");
			String servicioReversion = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.servicio");
			
			String msg = msj.substring(0, longitudFijaCabeceraDeserializable);
			if (servicioADC.equalsIgnoreCase(msg.trim()) 
					|| servicioCargo.equalsIgnoreCase(msg.trim())
					|| servicioReversion.equalsIgnoreCase(msg.trim())
					|| ((Constantes.ES_ENTORNO_DESA || Constantes.ES_ENTORNO_INTE || Constantes.ES_ENTORNO_PAU) 
							&& "NOAPLICA".equalsIgnoreCase(msg.trim()))) {
				return true;
			} else {
				return false;
			}
		}
		
		return true;
	}
}
