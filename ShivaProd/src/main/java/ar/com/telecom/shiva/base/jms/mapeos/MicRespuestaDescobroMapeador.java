package ar.com.telecom.shiva.base.jms.mapeos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroCargoGenerado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroFactura;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroMedioPago;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroOperacionPosteriorRelacionada;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicRespuestaDescobroMapeador extends MapeadorJMS {

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
		
		MicRespuestaDescobroSalida datosRecibidos = new MicRespuestaDescobroSalida();
		
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
    			    				String desc = "1101 - Error en el campo codRetorno";
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
    			    				String desc = "1102 - Error en el campo sqlCode";
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
    			    				String desc = "1103 - Error en el campo sqlErrml";
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
    			    				String desc = "1104 - Error en el campo sqlErrmc";
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
    			    				String desc = "1105 - Error en el campo ultRegProc";
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
    			    				String desc = "1106 - Error en el campo msgLength";
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
    			    				String desc = "1107 - Error en el campo cantRegistros";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    				} // Fin - for
    				
    				datosRecibidos.setRetorno(retorno);
				}//Fin retorno 
	    		
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
    				
    				datosRecibidos.setResultadoLlamadaServicio(resultadoLLamadaServicio);
    				
				} //Fin resultadoLLamadaServicio
				//
				
				if ("detalleFactura".equalsIgnoreCase(nombreCampo)) {
					MicDetalleDescobroFactura detalleDescobroFactura = new MicDetalleDescobroFactura();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (TipoDatoEnum.AGRUPADOR.equals(campoAgrupador.getTipoDato())) {
    						if ("resultadoDescobroFactura".equalsIgnoreCase(nombreCampoAgrupador)) {
    								
								MicResultado resultadoDescobroFactura = new MicResultado();
								for (Campo campoAgrupador2: campoAgrupador.getAgrupadorOrdenada()){
    		    					String nombreCampoAgrupador2 = campoAgrupador2.getNombre();
    		    					int longitudAgrupador2 = campoAgrupador2.getLongitud();
    		    					
        							if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroFactura.setResultadoInvocacion(resultadoInvocacion);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1007 - Error en el campo resultadoDescobroFactura.resultadoInvocacion";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
            						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String codigoError=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroFactura.setCodigoError(codigoError);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1008 - Error en el campo resultadoDescobroFactura.codigoError";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
            						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String descripcionError=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroFactura.setDescripcionError(descripcionError);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1009 - Error en el campo resultadoDescobroFactura.descripcionError";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
    							
    							}// fin for contador
        						
    							detalleDescobroFactura.setResultadoDescobroFactura(resultadoDescobroFactura);
    						}
    			    	}
    					
    					
    					if (TipoDatoEnum.ALFANUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("codigoEstadoCargoGenerado".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCodigoEstadoCargoGenerado = msg.substring(posInicial, posFinal);
    			    					detalleDescobroFactura.setCodigoEstadoCargoGenerado(strCodigoEstadoCargoGenerado);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1010 - Error en el campo codigoEstadoCargoGenerado";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("detalleEstadoCargoGenerado".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strDetalleEstadoCargoGenerado = msg.substring(posInicial, posFinal);
    			    					detalleDescobroFactura.setDetalleEstadoCargoGenerado(strDetalleEstadoCargoGenerado);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1011 - Error en el campo detalleEstadoCargoGenerado";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("codigoEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCodigoEstadoAcuerdoFacturacion = msg.substring(posInicial, posFinal);
    			    					detalleDescobroFactura.setCodigoEstadoAcuerdoFacturacion(strCodigoEstadoAcuerdoFacturacion);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1012 - Error en el campo codigoEstadoAcuerdoFacturacion";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("descripcionEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strDescripcionEstadoAcuerdoFacturacion = msg.substring(posInicial, posFinal);
    			    					detalleDescobroFactura.setDescripcionEstadoAcuerdoFacturacion(strDescripcionEstadoAcuerdoFacturacion);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1013 - Error en el campo descripcionEstadoAcuerdoFacturacion";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    					}
    					
    					if (TipoDatoEnum.NUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("interesesRealesNoReguladosTrasladados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCobradorInteresesTrasladadosNoRegulados = msg.substring(posInicial, posFinal);
    			    					BigDecimal cobradorInteresesTrasladadosNoRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strCobradorInteresesTrasladadosNoRegulados), 2);
    			    					detalleDescobroFactura.setInteresesRealesNoReguladosTrasladados(cobradorInteresesTrasladadosNoRegulados);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = " - Error en el campo interesesRealesNoReguladosTrasladados Factura";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("interesesRealesReguladosTrasladados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCobradorInteresesTrasladadosRegulados = msg.substring(posInicial, posFinal);
    			    					BigDecimal cobradorInteresesTrasladadosRegulados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strCobradorInteresesTrasladadosRegulados), 2);
    			    					detalleDescobroFactura.setInteresesRealesReguladosTrasladados(cobradorInteresesTrasladadosRegulados);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = " - Error en el campo interesesRealesReguladosTrasladados Factura";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    					}
						
					} // Fin - for campos de detalleFactura	
    					
    				datosRecibidos.setDetalleFactura(detalleDescobroFactura);
				} //Fin detalleFactura
				
				if ("listaDetalleMedioPago".equalsIgnoreCase(nombreCampo)) {
					List<MicDetalleDescobroMedioPago> listaDetalleMedioPago = new ArrayList<MicDetalleDescobroMedioPago>();
					
					for (int contador=0; campo.getLongitud() > contador ; contador++) {
						
						MicDetalleDescobroMedioPago detalleMedioPago = new MicDetalleDescobroMedioPago();
						
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
	    			    				String desc = "1014 - Error en el campo tipoMedioPago";
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
	    			    				String desc = "1015 - Error en el campo cuentaRemanente";
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
	    			    				String desc = "1016 - Error en el campo tipoRemanente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroReferenciaMic".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strNumeroReferenciaMic = msg.substring(posInicial, posFinal);
	    			    					Long numeroReferenciaMic = !Validaciones.isNullOrEmpty(strNumeroReferenciaMic)?new Long(strNumeroReferenciaMic):null;
	    			    					detalleMedioPago.setNumeroReferenciaMic(numeroReferenciaMic);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1017 - Error en el campo numeroReferenciaMic";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    					}
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
        						if ("resultadoDescobroMedioPago".equalsIgnoreCase(nombreCampoAgrupador)) {
        							
        							MicResultado resultadoDescobroMedioPago = new MicResultado();
        							for (Campo campoAgrupador2: campoAgrupador.getAgrupadorOrdenada()){
        		    					String nombreCampoAgrupador2 = campoAgrupador2.getNombre();
        		    					int longitudAgrupador2 = campoAgrupador2.getLongitud();
        		    					
	        							if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
	            			    		    		resultadoDescobroMedioPago.setResultadoInvocacion(resultadoInvocacion);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1018 - Error en el campo detalleMedioPago.resultadoInvocacion";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String codigoError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoDescobroMedioPago.setCodigoError(codigoError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1019 - Error en el campo detalleMedioPago.codigoError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador2)) {
	            							try {
	            			    				posFinal = posInicial + longitudAgrupador2;
	            			    		    	if (camposSeteables) {
	            			    		    		String descripcionError=msg.substring(posInicial, posFinal);
	            			    		    		resultadoDescobroMedioPago.setDescripcionError(descripcionError);
	            			    		    	}
	            				    			posInicial += longitudAgrupador2;
	            			    		    } catch(Exception e){
	            			    				String desc = "1020 - Error en el campo detalleMedioPago.descripcionError";
	            			    				Traza.error(getClass(), desc, e);
	            			    				throw new NegocioExcepcion(desc,e);
	            			    			}
	            						}
	            						
        							} // fin - for campos del resultadoDescobroMedioPago
        							
        							detalleMedioPago.setResultadoDescobroMedioPago(resultadoDescobroMedioPago);
        						}
	    			    	}
	    				} // Fin - for campos del detalleMedioPago
					
						listaDetalleMedioPago.add(detalleMedioPago);
					} // Fin - For Longitud (50)
					
    				datosRecibidos.setListaDetalleMedioPago(listaDetalleMedioPago);
				} //Fin listaDetalleMedioPago

				
				if ("detalleCargoGenerado".equalsIgnoreCase(nombreCampo)) {
					MicDetalleDescobroCargoGenerado detalleCargoGenerado = new MicDetalleDescobroCargoGenerado();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (TipoDatoEnum.ALFANUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("codigoEstadoCargoGenerado".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCodigoEstadoCargoGenerado = msg.substring(posInicial, posFinal);
    			    					detalleCargoGenerado.setCodigoEstadoCargoGenerado(strCodigoEstadoCargoGenerado);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1021 - Error en el campo codigoEstadoCargoGenerado";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("detalleEstadoCargoGenerado".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strDetalleEstadoCargoGenerado = msg.substring(posInicial, posFinal);
    			    					detalleCargoGenerado.setDetalleEstadoCargoGenerado(strDetalleEstadoCargoGenerado);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1022 - Error en el campo detalleEstadoCargoGenerado";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("codigoEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strCodigoEstadoAcuerdoFacturacion = msg.substring(posInicial, posFinal);
    			    					detalleCargoGenerado.setCodigoEstadoAcuerdoFacturacion(strCodigoEstadoAcuerdoFacturacion);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1023 - Error en el campo codigoEstadoAcuerdoFacturacion";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    						if ("descripcionEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strDescripcionEstadoAcuerdoFacturacion = msg.substring(posInicial, posFinal);
    			    					detalleCargoGenerado.setDescripcionEstadoAcuerdoFacturacion(strDescripcionEstadoAcuerdoFacturacion);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1024 - Error en el campo descripcionEstadoAcuerdoFacturacion";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						
    					}
    					
    					if (TipoDatoEnum.NUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("interesesRealesNoReguladosTrasladados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strInteresesRealesNoReguladosTrasladados = msg.substring(posInicial, posFinal);
    			    					BigDecimal interesesRealesNoReguladosTrasladados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesRealesNoReguladosTrasladados), 2);
    			    					detalleCargoGenerado.setInteresesRealesNoReguladosTrasladados(interesesRealesNoReguladosTrasladados);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1025 - Error en el campo interesesRealesNoReguladosTrasladados Cargo";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    					}
    					
    					if (TipoDatoEnum.NUMERICO.equals(campoAgrupador.getTipoDato())) {
    						if ("interesesRealesReguladosTrasladados".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String strInteresesRealesReguladosTrasladados = msg.substring(posInicial, posFinal);
    			    					BigDecimal interesesRealesReguladosTrasladados = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(strInteresesRealesReguladosTrasladados), 2);
    			    					detalleCargoGenerado.setInteresesRealesReguladosTrasladados(interesesRealesReguladosTrasladados);
    			    				}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "1026 - Error en el campo interesesRealesReguladosTrasladados Cargo";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    					}
    					
    					if (TipoDatoEnum.AGRUPADOR.equals(campoAgrupador.getTipoDato())) {
    						if ("resultadoDescobroCargoGenerado".equalsIgnoreCase(nombreCampoAgrupador)) {
    								
								MicResultado resultadoDescobroCargoGenerado = new MicResultado();
								
								for (Campo campoAgrupador2: campoAgrupador.getAgrupadorOrdenada()){
    		    					String nombreCampoAgrupador2 = campoAgrupador2.getNombre();
    		    					int longitudAgrupador2 = campoAgrupador2.getLongitud();
    		    					
        							if ("resultadoInvocacion".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String resultadoInvocacion=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroCargoGenerado.setResultadoInvocacion(resultadoInvocacion);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1027 - Error en el campo resultadoDescobroCargoGenerado.resultadoInvocacion";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
            						if ("codigoError".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String codigoError=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroCargoGenerado.setCodigoError(codigoError);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1028 - Error en el campo resultadoDescobroCargoGenerado.codigoError";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
            						if ("descripcionError".equalsIgnoreCase(nombreCampoAgrupador2)) {
            							try {
            			    				posFinal = posInicial + longitudAgrupador2;
            			    		    	if (camposSeteables) {
            			    		    		String descripcionError=msg.substring(posInicial, posFinal);
            			    		    		resultadoDescobroCargoGenerado.setDescripcionError(descripcionError);
            			    		    	}
            				    			posInicial += longitudAgrupador2;
            			    		    } catch(Exception e){
            			    				String desc = "1029 - Error en el campo resultadoDescobroCargoGenerado.descripcionError";
            			    				Traza.error(getClass(), desc, e);
            			    				throw new NegocioExcepcion(desc,e);
            			    			}
            						}
    							} // fin - for resultadoDescobroCargoGenerado
        						
    							detalleCargoGenerado.setResultadoDescobroCargoGenerado(resultadoDescobroCargoGenerado);
    						}
    			    	}
    					
						
					} // Fin - for campos de detalleCargoGenerado	
    					
    				datosRecibidos.setDetalleCargoGenerado(detalleCargoGenerado);
				} //Fin detalleCargoGenerado
				
				
				if ("listaDetalleOperacionesPosterioresRelacionadas".equalsIgnoreCase(nombreCampo)) {
					List<MicDetalleDescobroOperacionPosteriorRelacionada> listaDetalleOperacionesPosterioresRelacionadas = new ArrayList<MicDetalleDescobroOperacionPosteriorRelacionada>();
					
					for (int contador=0; campo.getLongitud() > contador ; contador++) {
						
						MicDetalleDescobroOperacionPosteriorRelacionada detalleOperacionesPosterioresRelacionadas = new MicDetalleDescobroOperacionPosteriorRelacionada();
						
						for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
							String nombreCampoAgrupador = campoAgrupador.getNombre();
	    					int longitudAgrupador = campoAgrupador.getLongitud();
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    						if ("sistema".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strSistema = msg.substring(posInicial, posFinal).trim();
	    			    					SistemaEnum sistema = SistemaEnum.getEnumByName(strSistema);
	    			    					detalleOperacionesPosterioresRelacionadas.setSistema(sistema);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1030 - Error en el campo sistema";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    						if ("tipoDocumento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strTipoDocumento = msg.substring(posInicial, posFinal);
	    			    					TipoComprobanteEnum tipoDocumento = TipoComprobanteEnum.getEnumByName(strTipoDocumento);
	    			    					detalleOperacionesPosterioresRelacionadas.setTipoDocumento(tipoDocumento);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1031 - Error en el campo tipoDocumento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    					}
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    						
	    						if ("idOperacion".equalsIgnoreCase(nombreCampoAgrupador)) {
	    			    			try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String strIdOperacion = msg.substring(posInicial, posFinal);
    			    					Long idOperacion = !Validaciones.isNullOrEmpty(strIdOperacion)? new Long(strIdOperacion):null;
	    			    				detalleOperacionesPosterioresRelacionadas.setIdOperacion(idOperacion);
	    			    				posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1032 - Error en el campo idOperacion";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    			    		}
	    						
	    						if ("idSecuencia".equalsIgnoreCase(nombreCampoAgrupador)) {
	    			    			try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String strIdSecuencia = msg.substring(posInicial, posFinal);
    			    					Integer idSecuencia = !Validaciones.isNullOrEmpty(strIdSecuencia)? new Integer(strIdSecuencia):null;
	    			    				detalleOperacionesPosterioresRelacionadas.setIdSecuencia(idSecuencia);
	    			    				posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1033 - Error en el campo idSecuencia";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    			    		}
	    						
	    						if ("numeroReferenciaMic".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strNumeroReferenciaMic = msg.substring(posInicial, posFinal);
	    			    					Long numeroReferenciaMic = !Validaciones.isNullOrEmpty(strNumeroReferenciaMic)? new Long(strNumeroReferenciaMic):null;
	    			    					detalleOperacionesPosterioresRelacionadas.setNumeroReferenciaMic(numeroReferenciaMic);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1034 - Error en el campo numeroReferenciaMic";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    						if ("codigoCliente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strCodigoCliente = msg.substring(posInicial, posFinal);
	    			    					BigInteger codigoCliente = !Validaciones.isNullOrEmpty(strCodigoCliente)? new BigInteger(strCodigoCliente):null;
	    			    					detalleOperacionesPosterioresRelacionadas.setCodigoCliente(codigoCliente);
	    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1035 - Error en el campo codigoCliente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    						if ("importeCobrado".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strImporteCobrado = msg.substring(posInicial, posFinal);
	    			    					BigDecimal importeCobrado = !Validaciones.isNullOrEmpty(strImporteCobrado)? new BigDecimal(strImporteCobrado):null;
	    			    					detalleOperacionesPosterioresRelacionadas.setImporteCobrado(importeCobrado);    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1036 - Error en el campo importeCobrado";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    						if ("fechaCobranza".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				if (camposSeteables) {
	    			    					String strFechaCobranza = msg.substring(posInicial, posFinal);
	    			    					strFechaCobranza = strFechaCobranza.substring(0, strFechaCobranza.length()-3);
	    			    					Date fechaCobranza = !Validaciones.isNullOrEmpty(strFechaCobranza)? Utilidad.parseDateFullFormatMillisecondsGuiones(strFechaCobranza):null;
	    			    					detalleOperacionesPosterioresRelacionadas.setFechaCobranza(fechaCobranza);    			    				}
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "1037 - Error en el campo fechaCobranza";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    					}
	    					
	    					
	    				} // Fin - for campos del detalleOperacionesPosterioresRelacionadas
					
						listaDetalleOperacionesPosterioresRelacionadas.add(detalleOperacionesPosterioresRelacionadas);
					} // Fin - For Longitud (51)
					
    				datosRecibidos.setListaDetalleOperacionesPosterioresRelacionadas(listaDetalleOperacionesPosterioresRelacionadas);
				} //Fin listaDetalleOperacionesPosterioresRelacionadas
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
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.servicio");
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
