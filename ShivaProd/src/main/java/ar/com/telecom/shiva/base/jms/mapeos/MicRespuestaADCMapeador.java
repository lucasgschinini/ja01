package ar.com.telecom.shiva.base.jms.mapeos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPagoRespuestaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleCobroFactura;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicRespuestaADCMapeador extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRespuestaRecepcionMapeador para serializar");
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
		
		MicRespuestaADCSalida datosRecibidos = new MicRespuestaADCSalida();
		
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
	    				String desc = "1000 - Error en el campo idOperacionTransaccionRetorno";
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
	    				String desc = "1001 - Error en el campo idOperacionTransaccion";
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
	    				String desc = "1002 - Error en el campo tipoInvocacion";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    		
	    		if ("idCobranza".equalsIgnoreCase(nombreCampo)) {

	    			try {
	    				posFinal = posInicial + longitud;
	    				if (camposSeteables) {
	    					Long idCobranza = Long.valueOf(msg.substring(posInicial, posFinal));
	    					datosRecibidos.setIdCobranza(idCobranza);
	    				}
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "1003 - Error en el campo idCobranza";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    	}
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
	    		//
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
				}//Fin retorno 
	    		//
	    		if ("resultadoLLamadaServicio".equalsIgnoreCase(nombreCampo)) {
					MicResultado resultadoLLamadaServicio = new MicResultado();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
    						if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    		    	if (camposSeteables) {
    			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
    			    		    		resultadoLLamadaServicio.setResultadoInvocacion(resultadoInvocacion);
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1004 - Error en el campo resultadoLLamadaServicio.resultadoInvocacion";
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
    			    				String desc = "1005 - Error en el campo resultadoLLamadaServicio.codigoError";
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
    			    				String desc = "1006 - Error en el campo resultadoLLamadaServicio.descripcionError";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
    			    	}
    				} // Fin - for
    				
    				datosRecibidos.setResultadoLLamadaServicio(resultadoLLamadaServicio);
    				
				} //Fin resultadoLLamadaServicio
				//
				
				if ("detalleCobroFactura".equalsIgnoreCase(nombreCampo)) {
					MicDetalleCobroFactura detalleCobroFactura = new MicDetalleCobroFactura();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (TipoDatoEnum.NUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("interesesGenerados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strInteresesGenerados=msg.substring(posInicial, posFinal);
    			    					BigDecimal interesesGenerados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesGenerados), 2);
    			    					detalleCobroFactura.setInteresesGenerados(interesesGenerados);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1007 - Error en el campo interesesGenerados";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("interesesBonificadosRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
	    			    				String strInteresesBonificadosRegulados=msg.substring(posInicial, posFinal);
	    			    		    	BigDecimal interesesBonificadosRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesBonificadosRegulados), 2);
	    			    		    	detalleCobroFactura.setInteresesBonificadosRegulados(interesesBonificadosRegulados);
    			    				}
    			    		    	posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1008 - Error en el campo interesesBonificadosRegulados";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("interesesBonificadosNoRegulados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strInteresesBonificadosNoRegulados=msg.substring(posInicial, posFinal);
    			    					BigDecimal interesesBonificadosNoRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesBonificadosNoRegulados), 2);
    			    					detalleCobroFactura.setInteresesBonificadosNoRegulados(interesesBonificadosNoRegulados);
    			    				}
    			    		    	posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1009 - Error en el campo interesesBonificadosNoRegulados";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    					}
    					
						if (TipoDatoEnum.AGRUPADOR.equals(campoAgrupador.getTipoDato())) {
    						if ("resultadoApropiacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							List<MicResultado> listaResultadoApropiacion = new ArrayList<MicResultado>();
    							
    							for (int contador=0; campoAgrupador.getLongitud() > contador ; contador++) {
    								
    								MicResultado resultadoApropiacion = new MicResultado();
    								for (Campo campoAgrupador2: campoAgrupador.getAgrupadorOrdenada()){
	    		    					String nombreCampoAgrupador2 = campoAgrupador2.getNombre();
	    		    					int longitudAgrupador2 = campoAgrupador2.getLongitud();
	    		    					
	        							if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setResultadoInvocacion(resultadoInvocacion);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1010 - Error en el campo resultadoApropiacion.resultadoInvocacion";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String codigoError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setCodigoError(codigoError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1011 - Error en el campo resultadoApropiacion.codigoError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String descripcionError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setDescripcionError(descripcionError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1012 - Error en el campo resultadoApropiacion.descripcionError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
        							} // fin - for resultadoApropiacion
            						
    								listaResultadoApropiacion.add(resultadoApropiacion);
    							
    							}// fin for contador
        						
    							detalleCobroFactura.setResultadoApropiacion(listaResultadoApropiacion);
    						}
    			    	}
					} // Fin - for campos de detalleCobroFactura	
    					
    				datosRecibidos.setDetalleCobroFactura(detalleCobroFactura);
				} //Fin detalleCobroFactura
				//
				if ("detalleMedioPago".equalsIgnoreCase(nombreCampo)) {
					List<MicDetalleMedioPagoRespuestaEntrada> listaDetalleMedioPago = new ArrayList<MicDetalleMedioPagoRespuestaEntrada>();
					
					for (int contador=0; campo.getLongitud() > contador ; contador++) {
						
						MicDetalleMedioPagoRespuestaEntrada detalleMedioPago = new MicDetalleMedioPagoRespuestaEntrada();
						
						for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
							String nombreCampoAgrupador = campoAgrupador.getNombre();
	    					int longitudAgrupador = campoAgrupador.getLongitud();
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    						if ("tipoMedioPago".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strTipoMedioPago = msg.substring(posInicial, posFinal);
	    			    					TipoMedioPagoEnum tipoMedioPago = TipoMedioPagoEnum.getEnumByName(strTipoMedioPago);
	    			    					detalleMedioPago.setTipoMedioPago(tipoMedioPago);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1013 - Error en el campo tipoMedioPago";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    					}
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    						if ("cuentaRemanente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
		    			    				String strCuentaRemanente = msg.substring(posInicial, posFinal);
		    			    		    	Long cuentaRemanente = !Validaciones.isNullOrEmpty(strCuentaRemanente)?new Long(strCuentaRemanente):null;
		    			    		    	detalleMedioPago.setCuentaRemanente(cuentaRemanente);
	    			    				}
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1014 - Error en el campo cuentaRemanente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("tipoRemanente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strTipoRemanente =msg.substring(posInicial, posFinal);
	    			    					TipoRemanenteEnum tipoRemanente = TipoRemanenteEnum.getEnumByCodigo(strTipoRemanente);
	    			    					detalleMedioPago.setTipoRemanente(tipoRemanente);
	    			    				}
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1015 - Error en el campo tipoRemanente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroNC".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strNumeroNC = msg.substring(posInicial, posFinal);
	    			    					Long numeroNC = !Validaciones.isNullOrEmpty(strNumeroNC)?new Long(strNumeroNC):null;
	    			    					detalleMedioPago.setNumeroNC(numeroNC);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1016 - Error en el campo numeroNC";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    					}
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
        						if ("resultadoApropiacion".equalsIgnoreCase(nombreCampoAgrupador)) {
        							
        							MicResultado resultadoApropiacion = new MicResultado();
        							for (Campo campoAgrupador2: campoAgrupador.getAgrupadorOrdenada()){
        		    					String nombreCampoAgrupador2 = campoAgrupador2.getNombre();
        		    					int longitudAgrupador2 = campoAgrupador2.getLongitud();
        		    					
	        							if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setResultadoInvocacion(resultadoInvocacion);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1017 - Error en el campo detalleMedioPago.resultadoInvocacion";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String codigoError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setCodigoError(codigoError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1018 - Error en el campo detalleMedioPago.codigoError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String descripcionError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoApropiacion.setDescripcionError(descripcionError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1019 - Error en el campo detalleMedioPago.descripcionError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						
        							} // fin - for campos del resultadoApropiacion
        							
        							detalleMedioPago.setResultadoApropiacion(resultadoApropiacion);
        						}
	    			    	}
	    				} // Fin - for campos del detalleMedioPago
					
						listaDetalleMedioPago.add(detalleMedioPago);
					} // Fin - For Longitud (50)
					
    				datosRecibidos.setListaDetalleMedioPago(listaDetalleMedioPago);
				} //Fin detalleMedioPago

				
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
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio");
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
