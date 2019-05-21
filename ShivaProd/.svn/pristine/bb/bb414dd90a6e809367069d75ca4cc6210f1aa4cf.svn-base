package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumentoMic;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Documento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.InfoAdicionalMedPagNoComisionables;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Transaccion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.InformacionGeneralApropiacion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.ResultadoApropiacionDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.ApropiacionDeimos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.ConsultaEstadoDeudaFiltroDeimos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.DesapropiacionDeimos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoCalipso;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoMIC;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.ListaDocumentos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.RespuestaApropiacionDeimos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.RespuestaDesapropiacionDeimos;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.ResultadoConsultaEstadoDeuda;
import ar.com.telecom.shiva.presentacion.wsdl.deimos.ShivaWebService;

public class DeimosWS {
	
	@Autowired
	ShivaWebService deimosWebService;
	
	/**
	 * Objetivo: Permite conocer la deuda de un cliente en el cobrador Deimos
	 * Proveedor: Deimos
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public SalidaDeimosConsultaEstadoDocumentoWS consultarEstadoDocumento(
			EntradaDeimosConsultaEstadoDeudaWS entrada)
			throws NegocioExcepcion {
		try {
			
			ConsultaEstadoDeudaFiltroDeimos filtro = new ConsultaEstadoDeudaFiltroDeimos();
			if(!Validaciones.isObjectNull(entrada)){
				if(!Validaciones.isObjectNull(entrada.getEmpresa())){
					filtro.setEmpresa(entrada.getEmpresa().name());
				}				
				if(!Validaciones.isObjectNull(entrada.getSistema())){
					filtro.setSistema(entrada.getSistema().getDescripcionCorta());
				}				
				if (SistemaEnum.MIC.equals(entrada.getSistema())) {
					IdDocumentoMIC idDocumento = new IdDocumentoMIC();
					if(!Validaciones.isObjectNull(entrada.getIdNumeroReferenciaMic())){
						idDocumento.setNumeroReferenciaMIC(Utilidad.rellenarCerosIzquierda(
								entrada.getIdNumeroReferenciaMic().toString(), 15));
					}				
					filtro.setIdDocumentoMIC(idDocumento);
				}
				if (SistemaEnum.CALIPSO.equals(entrada.getSistema())) {
					IdDocumentoCalipso idDocumento = new IdDocumentoCalipso();
					idDocumento.setTipoComprobante(
							entrada.getIdDocumentoCalipso().getTipoComprobante().siglaAux());
					idDocumento.setClaseComprobante(
							entrada.getIdDocumentoCalipso().getClaseComprobante().name());
					idDocumento.setSucursalComprobante(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											entrada.getIdDocumentoCalipso().getSucursalComprobante()), 4));
					idDocumento.setNumeroComprobante(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											entrada.getIdDocumentoCalipso().getNumeroComprobante()), 8));
					filtro.setIdDocumentoCalipso(idDocumento);
				}
			}
			
			ResultadoConsultaEstadoDeuda respuesta = 
	        		deimosWebService.consultaEstadoDocumento(filtro);
	
	        if (respuesta != null) {
	        	SalidaDeimosConsultaEstadoDocumentoWS datos = new SalidaDeimosConsultaEstadoDocumentoWS();
				
	        	if (respuesta.getIdDocumentoMIC()!=null) {
					datos.setIdNumeroReferenciaMic(Long.valueOf(respuesta.getIdDocumentoMIC().getNumeroReferenciaMIC()));
				}
				if (SistemaEnum.CALIPSO.equals(entrada.getSistema())) {
					IdDocumento idDocumento = new IdDocumento();
					idDocumento.setTipoComprobante(
						TipoComprobanteEnum.getEnumBySiglaAux(	
							respuesta.getIdDocumentoCalipso().getTipoComprobante()));
					idDocumento.setClaseComprobante(
						TipoClaseComprobanteEnum.getEnumByName(	
							respuesta.getIdDocumentoCalipso().getClaseComprobante()));
					idDocumento.setSucursalComprobante(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											respuesta.getIdDocumentoCalipso().getSucursalComprobante()), 4));
					idDocumento.setNumeroComprobante(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											respuesta.getIdDocumentoCalipso().getNumeroComprobante()), 8));
					datos.setIdDocumentoCalipso(idDocumento);
				}
	        	
				InformacionGeneralApropiacion info = new InformacionGeneralApropiacion();
				info.setApropiada(SiNoEnum.getEnumByDescripcionCorta(
						respuesta.getInformacionGeneralApropiacion().getApropiada()));
				info.setCodigoEstadoTramite(respuesta.getInformacionGeneralApropiacion().getCodigoEstadoTramite());
				info.setDescripcionEstadoTramite(respuesta.getInformacionGeneralApropiacion().getDescripcionEstadoTramite());
				info.setConvenio(new Long(respuesta.getInformacionGeneralApropiacion().getConvenio()));
				info.setCantidadCuotas(new Integer(respuesta.getInformacionGeneralApropiacion().getCantidadCuotas()));
				info.setCantidadCuotasPagas(new Integer(respuesta.getInformacionGeneralApropiacion().getCantidadCuotasPagas()));
				datos.setInformacionGeneralApropiacion(info);
				
				ResultadoProcesamiento resultado = new ResultadoProcesamiento();
				resultado.setResultadoImputacion(respuesta.getResultadoProcesamiento().getResultadoImputacion());
				resultado.setCodigoError(respuesta.getResultadoProcesamiento().getCodigoError());
				resultado.setDescripcionError(respuesta.getResultadoProcesamiento().getDescripcionError());
				datos.setResultadoProcesamiento(resultado);
				
				return datos;
			} throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + " : Se ha producido un error en el webservice");
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			
			if (!Validaciones.isObjectNull(e.getCause()) && !Validaciones.isObjectNull(e.getCause().getCause())) {
				Traza.error(DeimosWS.class, "");
				Traza.error(DeimosWS.class, "Causa raíz de la excepcion: " + e.getCause().getCause());
				Traza.error(DeimosWS.class, "Error al acceder al servicio de Deimos: " + e.getCause());
				Traza.error(DeimosWS.class, "");
			}
			
			throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_CONSULTA_ESTADO_DEUDA + ": Falla de conexion: " + e);
		}
	}
	
	/**
	 * Recibe la interface IDatosComunesEntradaDeimos y realiza la consulta a deimos
	 * @param datosDeimos
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosConsultaEstadoDocumentoWS consultarDeimos(IDatosComunesEntrada datosDeimos, EmpresaEnum empresa) throws NegocioExcepcion {
		
		EntradaDeimosConsultaEstadoDeudaWS 			entradaDeimos = new EntradaDeimosConsultaEstadoDeudaWS();
		SalidaDeimosConsultaEstadoDocumentoWS 		respuestaDeimos = null;
		
		entradaDeimos.setIdDocumentoCalipso(datosDeimos.getIdDocumentoCalipso());
		entradaDeimos.setSistema(datosDeimos.getSistemaOrigen());
		entradaDeimos.setEmpresa(empresa);
		if (Validaciones.isNumeric(datosDeimos.getNumeroReferenciaMic())){
			entradaDeimos.setIdNumeroReferenciaMic(Long.valueOf(datosDeimos.getNumeroReferenciaMic()));
		}
		
		respuestaDeimos = this.consultarEstadoDocumento(entradaDeimos);
		
		return respuestaDeimos;
			
	}
	
	/**
	 * @author u573005
	 * Sprint 5
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosApropiacionWS apropiarDocumento(EntradaDeimosApropiacionWS entrada) throws NegocioExcepcion {
		try {
//			Creo el objeto del webservice
			ApropiacionDeimos entradaWS = new ApropiacionDeimos();
			
			String idOperacionShiva = Utilidad.rellenarCerosIzquierda(entrada.getIdOperacionShiva().toString(), 7);
			entradaWS.setIdOperacionShiva(idOperacionShiva);
			entradaWS.setUsuarioCobrador(entrada.getUsuarioCobrador());
			Transaccion transaccion = entrada.getTransaccion();
			
			if(!Validaciones.isObjectNull(transaccion)){
//				Creo el objeto del webservice
				ar.com.telecom.shiva.presentacion.wsdl.deimos.Transaccion transaccionWS = new ar.com.telecom.shiva.presentacion.wsdl.deimos.Transaccion();
				
				String idSecuencia = Utilidad.rellenarCerosIzquierda(transaccion.getIdSecuencia().toString(), 5);
				transaccionWS.setIdSecuencia(idSecuencia);
				
//				Creo el objeto del webservice
				ar.com.telecom.shiva.presentacion.wsdl.deimos.ListaDocumentos listaDocumentosWS = new ListaDocumentos();
				
				if (transaccion.getListaDocumentos()!=null) {
					for(Documento documento : transaccion.getListaDocumentos()){
						if(!Validaciones.isObjectNull(documento)){
							// Creo el objeto del webservice
							ar.com.telecom.shiva.presentacion.wsdl.deimos.Documento documentoWS = new ar.com.telecom.shiva.presentacion.wsdl.deimos.Documento();
							
							documentoWS.setEmpresa(!Validaciones.isObjectNull(documento.getEmpresa())?documento.getEmpresa().name():null);
							documentoWS.setSistema(!Validaciones.isObjectNull(documento.getSistema())?documento.getSistema().getDescripcionCorta():null);
							documentoWS.setImporte(!Validaciones.isObjectNull(
									documento.getImporte())?
											new BigDecimal(documento.getImporte().toString().replace(",", ".")):new BigDecimal("0"));
							InfoAdicionalMedPagNoComisionables infoAdicional = documento.getInfoAdicionalMedPagNoComisionables();
							
							if(!Validaciones.isObjectNull(infoAdicional)){
								// Creo el objeto del webservice
								ar.com.telecom.shiva.presentacion.wsdl.deimos.InfoAdicionalMedPagNoComisionables infoAdicionalWS = new ar.com.telecom.shiva.presentacion.wsdl.deimos.InfoAdicionalMedPagNoComisionables();
								
								infoAdicionalWS.setImporte(!Validaciones.isObjectNull(
										infoAdicional.getImporte())?
												new BigDecimal(infoAdicional.getImporte().toString().replace(",", ".")):new BigDecimal("0"));
								infoAdicionalWS.setCodigoTipoMedioPago(infoAdicional.getCodigoTipoMedioPago());
								documentoWS.setInfoAdicionalMedPagNoComisionables(infoAdicionalWS);
							}
							
							IdDocumentoMic idDocumentoMic = documento.getIdDocumentoMic();
							
							if(!Validaciones.isObjectNull(idDocumentoMic)){
								// Creo el objeto del webservice
								ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoMIC idDocumentoMicWS = new ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoMIC();
								
								idDocumentoMicWS.setNumeroReferenciaMIC(idDocumentoMic.getNumeroReferenciaMic());
								documentoWS.setIdDocumentoMIC(idDocumentoMicWS);
							}
							
							IdDocumento idDocumentoCalipso = documento.getIdDocumentoCalipso();
							
							if(!Validaciones.isObjectNull(idDocumentoCalipso)){
								// Creo el objeto del webservice
								ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoCalipso idDocumentoCalipsoWS = new ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoCalipso();
								
								idDocumentoCalipsoWS.setTipoComprobante(!Validaciones.isObjectNull(idDocumentoCalipso.getTipoComprobante())?idDocumentoCalipso.getTipoComprobante().siglaAux():null);
								idDocumentoCalipsoWS.setClaseComprobante(!Validaciones.isObjectNull(idDocumentoCalipso.getClaseComprobante())?idDocumentoCalipso.getClaseComprobante().name():null);
								idDocumentoCalipsoWS.setSucursalComprobante(
										Utilidad.rellenarCerosIzquierda(idDocumentoCalipso.getSucursalComprobante(), 4)
										);
								idDocumentoCalipsoWS.setNumeroComprobante(
										Utilidad.rellenarCerosIzquierda(idDocumentoCalipso.getNumeroComprobante(), 8)
										);
								
								documentoWS.setIdDocumentoCalipso(idDocumentoCalipsoWS);
							}
							
							listaDocumentosWS.getDocumento().add(documentoWS);
						}
					}
				}
				
				transaccionWS.setListaDocumentos(listaDocumentosWS);
				entradaWS.setTransaccion(transaccionWS);
			}
			
			entradaWS.setModoOperacion(!Validaciones.isObjectNull(entrada.getModoOperacion())?entrada.getModoOperacion().getDescripcionCorta():null);

			RespuestaApropiacionDeimos respuesta = deimosWebService.apropiacionDocumento(entradaWS);

			if (!Validaciones.isObjectNull(respuesta)) {
				SalidaDeimosApropiacionWS datos = new SalidaDeimosApropiacionWS();
				datos.setIdSecuencia(respuesta.getIdSecuencia());
				
//				Creo objeto wbeservice
				ar.com.telecom.shiva.presentacion.wsdl.deimos.ListaResultados listaResultadosWS = respuesta.getListaResultados();
				
				if(!Validaciones.isObjectNull(listaResultadosWS)){
					List<Resultado> listaResultados = new ArrayList<Resultado>();
					
//					Recorro objeto wbeservice
					for(ar.com.telecom.shiva.presentacion.wsdl.deimos.Resultado resultadoWS : listaResultadosWS.getResultado()){
						Resultado resultado = new Resultado();
						
						ar.com.telecom.shiva.presentacion.wsdl.deimos.Documento documentoWS = resultadoWS.getDocumento();
						
						if(!Validaciones.isObjectNull(documentoWS)){
							ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.DocumentoSalida documento = new ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.DocumentoSalida();
							documento.setEmpresa(EmpresaEnum.getEnumByName(documentoWS.getEmpresa()));
							documento.setSistema(SistemaEnum.getEnumByDescripcionCorta(documentoWS.getSistema()));
							
//							Recorro objeto wbeservice
							ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoMIC idDocumentoMicWS = documentoWS.getIdDocumentoMIC();
							
							if(!Validaciones.isObjectNull(idDocumentoMicWS)){
								IdDocumentoMic idDocumentoMic = new IdDocumentoMic();
								idDocumentoMic.setNumeroReferenciaMic(idDocumentoMicWS.getNumeroReferenciaMIC());
								documento.setIdDocumentoMic(idDocumentoMic);
							}
							
//							Recorro objeto wbeservice
							ar.com.telecom.shiva.presentacion.wsdl.deimos.IdDocumentoCalipso idDocumentoCalipsoWS = documentoWS.getIdDocumentoCalipso();
							
							if(!Validaciones.isObjectNull(idDocumentoCalipsoWS)){
								IdDocumento idDocumentoCalipso = new IdDocumento();
								idDocumentoCalipso.setTipoComprobante(TipoComprobanteEnum.getEnumBySiglaAux(idDocumentoCalipsoWS.getTipoComprobante()));
								idDocumentoCalipso.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(idDocumentoCalipsoWS.getClaseComprobante()));
								idDocumentoCalipso.setSucursalComprobante(idDocumentoCalipsoWS.getSucursalComprobante());
								idDocumentoCalipso.setNumeroComprobante(idDocumentoCalipsoWS.getNumeroComprobante());
								documento.setIdDocumentoCalipso(idDocumentoCalipso);
							}
							
							resultado.setDocumento(documento);
						}
						
//						Recorro objeto wbeservice
						ar.com.telecom.shiva.presentacion.wsdl.deimos.ResultadoApropiacionDocumento resultadoApropiacionDocumentoWS = resultadoWS.getResultadoApropiacionDocumento();
						
						if(!Validaciones.isObjectNull(resultadoApropiacionDocumentoWS)){
							ResultadoApropiacionDocumento resultadoApropiacionDocumento = new ResultadoApropiacionDocumento();
							resultadoApropiacionDocumento.setResultadoApropiacion(resultadoApropiacionDocumentoWS.getResultadoApropiacion());
							resultadoApropiacionDocumento.setCodigoError(resultadoApropiacionDocumentoWS.getCodigoError());
							resultadoApropiacionDocumento.setDescripcionError(resultadoApropiacionDocumentoWS.getDescripcionError());
							resultado.setResultadoApropiacionDocumento(resultadoApropiacionDocumento);
						}						
						
						listaResultados.add(resultado);
					}
					
					datos.setListaResultados(listaResultados);					
				}

//				Recorro objeto wbeservice
				ar.com.telecom.shiva.presentacion.wsdl.deimos.ResultadoProcesamiento resultadoProcesamientoWS = respuesta.getResultadoProcesamiento();
				
				if(!Validaciones.isObjectNull(resultadoProcesamientoWS)){
					ResultadoProcesamiento resultadoProcesamiento = new ResultadoProcesamiento();
					resultadoProcesamiento.setResultadoImputacion(resultadoProcesamientoWS.getResultadoImputacion());
					resultadoProcesamiento.setCodigoError(resultadoProcesamientoWS.getCodigoError());
					resultadoProcesamiento.setDescripcionError(resultadoProcesamientoWS.getDescripcionError());
					datos.setResultadoProcesamiento(resultadoProcesamiento);
				}
				
				return datos;
			}
			
			throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_APROPIACION + " : Se ha producido un error en el webservice");
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS " + ConstantesCobro.MENSAJE_APROPIACION + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			
			if (!Validaciones.isObjectNull(e.getCause()) && !Validaciones.isObjectNull(e.getCause().getCause())) {
				Traza.error(DeimosWS.class, "");
				Traza.error(DeimosWS.class, "Causa raíz de la excepcion: " + e.getCause().getCause());
				Traza.error(DeimosWS.class, "Error al acceder al servicio de Deimos: " + e.getCause());
				Traza.error(DeimosWS.class, "");
			}
			
			throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_APROPIACION + ": Falla de conexion: " + e);
		}
		
	}
	
	/**
	 * @author u573005
	 * Sprint 5
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaDeimosDesapropiacionWS desapropiarDocumento(EntradaDeimosDesapropiacionWS entrada) throws NegocioExcepcion {
		try {
//			Creo objeto webservice
			DesapropiacionDeimos entradaWS = new DesapropiacionDeimos();
			
			String idOperacionShiva = Utilidad.rellenarCerosIzquierda(entrada.getIdOperacionShiva().toString(), 7);
			entradaWS.setIdOperacionShiva(idOperacionShiva);
			entradaWS.setUsuarioCobrador(entrada.getUsuarioCobrador());
			
			RespuestaDesapropiacionDeimos respuesta = deimosWebService.desapropiacionDocumento(entradaWS);

			if (!Validaciones.isObjectNull(respuesta)) {
				SalidaDeimosDesapropiacionWS datos = new SalidaDeimosDesapropiacionWS();
				
				datos.setIdOperacionShiva(!Validaciones.isNullOrEmpty(respuesta.getIdOperacionShiva())?new Integer(respuesta.getIdOperacionShiva()):null);
				
//				Recorro objeto webservice
				ar.com.telecom.shiva.presentacion.wsdl.deimos.ResultadoProcesamiento resultadoProcesamientoWS = respuesta.getResultadoProcesamiento();
				
				if(!Validaciones.isObjectNull(resultadoProcesamientoWS)){
					ResultadoProcesamiento resultadoProcesamiento = new ResultadoProcesamiento();
					resultadoProcesamiento.setResultadoImputacion(resultadoProcesamientoWS.getDescripcionError());
					resultadoProcesamiento.setCodigoError(resultadoProcesamientoWS.getCodigoError());
					resultadoProcesamiento.setDescripcionError(resultadoProcesamientoWS.getDescripcionError());
					datos.setResultadoProcesamiento(resultadoProcesamiento);
				}
				
				return datos;
			}
			
			throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_DESAPROPIACION + " : Se ha producido un error en el webservice");
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS " + ConstantesCobro.MENSAJE_DESAPROPIACION + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			
			if (!Validaciones.isObjectNull(e.getCause()) && !Validaciones.isObjectNull(e.getCause().getCause())) {
				Traza.error(DeimosWS.class, "");
				Traza.error(DeimosWS.class, "Causa raíz de la excepcion: " + e.getCause().getCause());
				Traza.error(DeimosWS.class, "Error al acceder al servicio de Deimos: " + e.getCause());
				Traza.error(DeimosWS.class, "");
			}
			
			throw new WebServiceExcepcion("WS " + ConstantesCobro.MENSAJE_DESAPROPIACION + ": Falla de conexion: " + e);
		}
		
	}
	
}