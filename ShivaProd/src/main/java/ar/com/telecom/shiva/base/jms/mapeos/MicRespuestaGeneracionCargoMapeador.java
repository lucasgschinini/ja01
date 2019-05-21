package ar.com.telecom.shiva.base.jms.mapeos;

import java.math.BigDecimal;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleInteresesGenerados;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicRespuestaGeneracionCargoMapeador 
	extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRecepcionMapeador para serializar");
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
		
		JmsUtilidad.verificarLongitudTotalMensaje(longitudFijaCabeceraDeserializable, 
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable(), 
				mensajeRecibido);
		
		//Sacar la cabecera del mensaje
		String msg = mensajeRecibido;
		if (longitudFijaCabeceraDeserializable > 0) {
			msg = mensajeRecibido.substring(longitudFijaCabeceraDeserializable, msg.length());
		}
		
		MicRespuestaGeneracionCargoSalida datosRecibidos = new MicRespuestaGeneracionCargoSalida();
		
		List<Campo> camposEntrada = getDefaultFormatoMensajeJMS().getCamposDeserializableOrdenada();
		int posInicial = 0;
		int posFinal = 0;
		
	    for (Campo campo: camposEntrada){
	    	String nombreCampo = campo.getNombre();
	    	int longitud = campo.getLongitud();
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    		if ("idOperacionTransaccionRetorno".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	if (camposSeteables) {
	    		    		String idOperacionTransaccion= msg.substring(posInicial, posFinal);
	    		    		datosRecibidos.setIdOperacionTransaccionRetorno(idOperacionTransaccion.trim());
	    		    	}
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "1020 - Error en el campo idOperacionTransaccionRetorno";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    		
	    		if ("idOperacionTransaccion".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	String idOperacionTransaccion= msg.substring(posInicial, posFinal);
	    		    	datosRecibidos.setIdOperacionTransaccion(idOperacionTransaccion.trim());
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "1000 - Error en el campo idOperacionTransaccion";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    	} 
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    		if ("tipoInvocacion".equalsIgnoreCase(nombreCampo)) {

	    			try {
	    				posFinal = posInicial + longitud;
	    		    	Long tipoInvocacion=Long.valueOf(msg.substring(posInicial, posFinal));
		    			datosRecibidos.setTipoInvocacion(TipoInvocacionEnum.getEnumById(tipoInvocacion));
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "1001 - Error en el campo tipoInvocacion";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    	}
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
	    		
	    		if ("retorno".equalsIgnoreCase(nombreCampo)) {
					JmsCopiaTecnica retorno = new JmsCopiaTecnica();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
    						if ("codRetorno".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String codRetorno=msg.substring(posInicial, posFinal);
    			    		    		retorno.setCodRetorno(codRetorno.trim());
    			    		    	}	
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1022 - Error en el campo codRetorno";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlCode".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String sqlCode=msg.substring(posInicial, posFinal);
    			    		    		retorno.setSqlCode(sqlCode.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1023 - Error en el campo sqlCode";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlErrml".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String sqlErrml=msg.substring(posInicial, posFinal);
    			    		    		retorno.setSqlErrml(sqlErrml.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1024 - Error en el campo sqlErrml";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("sqlErrmc".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String sqlErrmc=msg.substring(posInicial, posFinal);
    			    		    		retorno.setSqlErrmc(sqlErrmc.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1025 - Error en el campo sqlErrmc";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("ultRegProc".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String ultRegProc=msg.substring(posInicial, posFinal);
    			    		    		retorno.setUltRegProc(ultRegProc.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1026 - Error en el campo ultRegProc";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
    						if ("msgLength".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					Long msgLength=Long.valueOf(msg.substring(posInicial, posFinal));
    			    		    		retorno.setMsgLength(msgLength.toString());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1027 - Error en el campo msgLength";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("cantRegistros".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					Long cantRegistros=Long.valueOf(msg.substring(posInicial, posFinal));
    			    		    		retorno.setCantRegistros(cantRegistros.toString());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1028 - Error en el campo cantRegistros";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    				} // Fin - for
    				
    				datosRecibidos.setRetorno(retorno);
				} // fin retorno
	    		
	    		
	    		
	    		//Resultado Llamada Servicio
	    		if ("resultadoLLamadaServicio".equalsIgnoreCase(nombreCampo)) {
					MicResultado resultadoLLamadaServicio = new MicResultado();
	    			
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
	    		
						if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    		    	if (camposSeteables) {
			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
			    		    		resultadoLLamadaServicio.setResultadoInvocacion(resultadoInvocacion.trim());
			    		    	}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1002 - Error en el campo resultadoLLamadaServicio.resultadoInvocacion";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}	
						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    		    	if (camposSeteables) {
			    		    		String codigoError=msg.substring(posInicial, posFinal);
			    		    		resultadoLLamadaServicio.setCodigoError(codigoError);
			    		    	}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1003 - Error en el campo resultadoLLamadaServicio.codigoError";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    		    	if (camposSeteables) {
			    		    		String descripcionError=msg.substring(posInicial, posFinal);
			    		    		resultadoLLamadaServicio.setDescripcionError(descripcionError);
			    		    	}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1004 - Error en el campo resultadoLLamadaServicio.descripcionError";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
					} // fin for resultadoLLamadaServicio
					
					datosRecibidos.setResultadoLLamadaServicio(resultadoLLamadaServicio);
	    		} // fin resultadoLLamadaServicio
	    		
	    		
	    		if ("detalleInteresesGenerados".equalsIgnoreCase(nombreCampo)) {
	    			MicDetalleInteresesGenerados detalleInteresesGenerados = new MicDetalleInteresesGenerados();
	    			
	    			for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
	    					
						if ("interesesGeneradosNoRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    				if (camposSeteables) {
			    					String strInteresesGeneradosNoRegulados = msg.substring(posInicial, posFinal);
			    					BigDecimal interesesGeneradosNoRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesGeneradosNoRegulados), 2);
			    					detalleInteresesGenerados.setInteresesGeneradosNoRegulados(interesesGeneradosNoRegulados);
			    				}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1005 - Error en el campo interesesGeneradosNoRegulados";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
						if ("interesesGeneradosRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    				if (camposSeteables) {
			    					String strInteresesGeneradosRegulados = msg.substring(posInicial, posFinal);
			    					BigDecimal interesesGeneradosRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesGeneradosRegulados), 2);
			    					detalleInteresesGenerados.setInteresesGeneradosRegulados(interesesGeneradosRegulados);
			    				}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1006 - Error en el campo interesesGeneradosRegulados";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
						if ("interesesBonificadosNoRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    				if (camposSeteables) {
			    					String strInteresesBonificadosNoRegulados = msg.substring(posInicial, posFinal);
			    					BigDecimal interesesBonificadosNoRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesBonificadosNoRegulados), 2);
			    					detalleInteresesGenerados.setInteresesBonificadosNoRegulados(interesesBonificadosNoRegulados);
			    				}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1007 - Error en el campo interesesBonificadosNoRegulados";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
						if ("interesesBonificadosRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
							try {
			    				posFinal = posInicial + longitudAgrupador;
			    				if (camposSeteables) {
			    					String strInteresesBonificadosRegulados = msg.substring(posInicial, posFinal);
			    					BigDecimal interesesBonificadosRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesBonificadosRegulados), 2);
			    					detalleInteresesGenerados.setInteresesBonificadosRegulados(interesesBonificadosRegulados);
			    				}
				    			posInicial += longitudAgrupador;
			    		    } catch(Exception e){
			    				String desc = "1008 - Error en el campo interesesBonificadosRegulados";
			    				Traza.error(getClass(), desc, e);
			    				throw new NegocioExcepcion(desc,e);
			    			}
						}
			    	} // fin For
	    			
	    			datosRecibidos.setDetalleInteresesGenerados(detalleInteresesGenerados);
				} // fin detalleInteresesGenerados
	    		
    		} // fin if agrupador
	    	
	    } // fin for general
	    
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
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.servicio");
			String msg = msj.substring(0, longitudFijaCabeceraDeserializable);
			if (servicio.equalsIgnoreCase(msg.trim())
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
