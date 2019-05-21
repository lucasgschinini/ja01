package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.CobroCreditoDtoComparator;
import ar.com.telecom.shiva.base.comparador.CobroDebitoDtoComparator;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoCredito;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorSOA;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.mapeos.CobroCreditoMapeadorShiva;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineBusquedaYPaginadoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CreditoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.DebitosControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoCreditoShiva;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaCalipsoCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaCalipsoDebito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaMicCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaMicDebito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaShivaCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.salida.ShivaConsultaCreditoSalida;

public class CobroOnlinePaginadoServicioImpl extends Servicio implements ICobroOnlineBusquedaYPaginadoServicio {
	
	@Autowired
	private IClienteCalipsoServicio clienteCalipsoServicio;
	
	@Autowired
	private IMicJmsSyncServicio clienteMicServicio;
	
	@Autowired
	private ICobroOnlineServicio onlineServicio; 
	
	private MapeadorSOA mapeadorDeudas; 
	
	private MapeadorSOA mapeadorCreditos;	

	@Autowired CobroCreditoMapeadorShiva cobroCreditoMapeadorShiva;
	
	private final String COMPENSACION = "9";
	
	/**
	 * Metodo que busca debitos
	 */
	public DebitoJsonResponse buscarDebitos(ConfCobroDebitoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion {
		filtro.getUsuarioLogeado().setDebPaginado(new DebitosControlPaginacion(filtro));
		return this.paginarDebitos(filtro.getUsuarioLogeado().getDebPaginado(), filtro.getIdsClientes(), filtro.getIdsDoc(), ConstantesCobro.PAGINADO_BUSCAR);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Metodo que pagina debitos (llama a calipso/mic)
	 */
	public DebitoJsonResponse paginarDebitos(DebitosControlPaginacion debPaginado, String idsClientes, 
			String idsDoc, String accion) throws NegocioExcepcion, ShivaExcepcion {
		
		//Mensajes de error
		String errorMensajeCalipso = null;
		String errorMensajeMic = null;
		String informacionCalipso = null;
		String informacionMic = null;
		
		// Calipso
		EntradaCalipsoDeudaWS calipsoEntrada = null;
		SalidaCalipsoDeudaWS calipsoSalida = null;
		
		// MIC
		MicConsultaDeudaEntrada micEntrada = null;
		MicConsultaDeudaSalida micSalida = null;
		
		if(ConstantesCobro.PAGINADO_BUSCAR.equalsIgnoreCase(accion)){
			debPaginado.getFiltro().setIdsClientes(idsClientes);
			debPaginado.getFiltro().setIdsDoc(idsDoc);
		}
		
		calipsoEntrada = debPaginado.obtenerEntradaCalipso();
		micEntrada = debPaginado.obtenerEntradaMic();
		
		List<CobroDebitoDto> debitos = new ArrayList<CobroDebitoDto>();
		List<CobroDebitoDto> debsCalipso;
		List<CobroDebitoDto> debsMic;
		
		
		InfoPaginaCalipsoDebito infoPagCalipso = new InfoPaginaCalipsoDebito();
		InfoPaginaMicDebito infoPagMic = new InfoPaginaMicDebito();
		
		debPaginado.setPuntoDeInicio(accion, calipsoEntrada, micEntrada);
		
		//Busco Todos o solo el calipso
		if (permitirBusquedaPorCalipso(debPaginado.getFiltro())) {
			if (calipsoEntrada.getInformacionParaPaginado() != null) {
				try {
					calipsoSalida = clienteCalipsoServicio.consultaDebito(calipsoEntrada);
					if ("ERR".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())) {
						String mensaje = calipsoSalida.getResultadoProcesamiento().getDescripcionError();
						Traza.advertencia(getClass(), "Error de Servicio: Consulta de Deuda CALIPSO: " + mensaje);
						errorMensajeCalipso = "Se ha producido un error de servicio CALIPSO";
					}
					if ("NOK".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())
							&& "7017".equals(calipsoSalida.getResultadoProcesamiento().getCodigoError())) 
					{
						informacionCalipso = Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busquedaDebito.informar.debito.cobrado");
					}
					
				} catch (NegocioExcepcion e) {
					if (e instanceof WebServiceExcepcion) {
						errorMensajeCalipso = e.getMensajeAuxiliar();
					} else {
						throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
					}
				}
			} else {
				calipsoSalida = new SalidaCalipsoDeudaWS(debPaginado.getTotalCalipso());
			} 
			
			if (!Validaciones.isObjectNull(calipsoSalida)) {
				int totalCalipso = calipsoSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
				debPaginado.setTotalCalipso(totalCalipso);
				if (totalCalipso > 0) {
					calipsoSalida = cargarIdCobroCalipso(debPaginado, calipsoSalida);
					debsCalipso = (List<CobroDebitoDto>) mapeadorDeudas.map(calipsoSalida.getListaDebitos());
					debitos.addAll(debsCalipso);
				}
			}
		} else {
			debPaginado.setTotalCalipso(0);
		}
		
		//Busco Todos o solo el Mic
		if (permitirBusquedaPorMic(debPaginado.getFiltro())) {
			if (micEntrada.getInformacionPaginado() != null) {
				try {
					micSalida = consultarDeudaYRevisarResultado(micEntrada);
					informacionMic = micSalida.getInformacionAMostrar();
				} catch (NegocioExcepcion e) {
					if (e instanceof JmsExcepcion) {
						errorMensajeMic = e.getMensajeAuxiliar();
					} else {
						throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
					}
				}
			} else {
				micSalida = new MicConsultaDeudaSalida(debPaginado.getTotalMic());
			}
			if (!Validaciones.isObjectNull(micSalida)) {
				int totalMic = micSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
				debPaginado.setTotalMic(totalMic);
				if (totalMic > 0) {
					micSalida = cargarIdCobroMic(debPaginado, micSalida);
					debsMic = (List<CobroDebitoDto>) mapeadorDeudas.map(micSalida.getListaDebitos());
					debitos.addAll(debsMic);
				}
			}
		} else {
			debPaginado.setTotalMic(0);
		}
		
		debPaginado.setTotalRegistros(debPaginado.getTotalMic() + debPaginado.getTotalCalipso());
		
		while (debPaginado.getPaginaActual() != 1 && debitos.size() == 0) {
			debPaginado.setPuntoDeInicio(ConstantesCobro.PAGINADO_ANTERIOR, calipsoEntrada, micEntrada);
			
			//Busco Todos o solo el calipso
			if (permitirBusquedaPorCalipso(debPaginado.getFiltro())) {
				if (calipsoEntrada.getInformacionParaPaginado() != null) {
					try {
						calipsoSalida = clienteCalipsoServicio.consultaDebito(calipsoEntrada);
						if ("ERR".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())) {
							String mensaje = calipsoSalida.getResultadoProcesamiento().getDescripcionError();
							Traza.advertencia(getClass(), "Error de Servicio: Consulta de Deuda CALIPSO: " + mensaje);
							errorMensajeCalipso = "Se ha producido un error de servicio CALIPSO";
						}
						if ("NOK".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())
								&& "7017".equals(calipsoSalida.getResultadoProcesamiento().getCodigoError())) 
						{
							informacionCalipso = Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busquedaDebito.informar.debito.cobrado");
						}
						
					} catch(NegocioExcepcion e) {
						if (e instanceof WebServiceExcepcion) {
							errorMensajeCalipso = e.getMensajeAuxiliar();
						} else {
							throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
						}
					}
				} else {
					calipsoSalida = new SalidaCalipsoDeudaWS(debPaginado.getTotalCalipso());
				}
				
				if (!Validaciones.isObjectNull(calipsoSalida)) {
					int totalCalipso = calipsoSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
					debPaginado.setTotalCalipso(totalCalipso);
					if (totalCalipso > 0) {
						calipsoSalida = cargarIdCobroCalipso(debPaginado, calipsoSalida);
						debsCalipso = (List<CobroDebitoDto>) mapeadorDeudas.map(calipsoSalida.getListaDebitos());
						debitos.addAll(debsCalipso);
					}
				}
			} else {
				debPaginado.setTotalCalipso(0);
			}
			
			//Busco Todos o solo el Mic
			if (permitirBusquedaPorMic(debPaginado.getFiltro())) {
				if (micEntrada.getInformacionPaginado() != null) {
					try {
						micSalida = consultarDeudaYRevisarResultado(micEntrada);
						informacionMic = micSalida.getInformacionAMostrar();
					} catch(NegocioExcepcion e) {
						if (e instanceof JmsExcepcion) {
							errorMensajeMic = e.getMensajeAuxiliar();
						} else {
							throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
						}
					}
				} else {
					micSalida = new MicConsultaDeudaSalida(debPaginado.getTotalMic());
				}
				if (!Validaciones.isObjectNull(micSalida)) {
					int totalMic = micSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
					debPaginado.setTotalMic(totalMic);
					if (totalMic > 0) {
						micSalida = cargarIdCobroMic(debPaginado, micSalida);
						debsMic = (List<CobroDebitoDto>) mapeadorDeudas.map(micSalida.getListaDebitos());
						debitos.addAll(debsMic);
					}
				}
			} else {
				debPaginado.setTotalMic(0);
			}
			
			debPaginado.setTotalRegistros(debPaginado.getTotalMic() + debPaginado.getTotalCalipso());
		}
		
		//Seteo si hay alguna información en general
		if (debPaginado.getTotalCalipso() > 0) {
			infoPagCalipso.setHayInformacionEnGeneral(true);
		}
		if (debPaginado.getTotalMic() > 0) {
			infoPagMic.setHayInformacionEnGeneral(true);
		}
		
		//Ordeno los debitos
		Collections.sort(debitos, new CobroDebitoDtoComparator());
		debPaginado.actualizarPagina();
		
		List<CobroDebitoDto> debs = new ArrayList<CobroDebitoDto>();
		for (int i = 0; i <= (debitos.size() - 1) && i <= (ConstantesCobro.CANTIDAD_POR_PAGINA_DEB - 1); i++) {
			CobroDebitoDto dto = debitos.get(i);
			
			//Seteo si hay alguna información en pagina actual
			if (dto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
				infoPagCalipso.setHayInformacionEnEstaPagina(true);
			}
			if (dto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
				infoPagMic.setHayInformacionEnEstaPagina(true);
			}
			
			debs.add(dto);
		}
		
		CobroDebitoDto debDto = null;
		
		InformacionParaPaginadoDebito ultDebCalipso = null;
		MicInformacionPaginadoDebito ultDebMic = null;
		
		InformacionParaPaginadoDebito primerDebCalipso = null;
		MicInformacionPaginadoDebito primerDebMic = null;
		 
		if (debs.size() > 0) {
			for (int i = (debs.size() - 1); i >= 0 
					&& (ultDebCalipso == null || ultDebMic == null 
						|| ultDebMic.getUltimoNumeroDUC() == null || ultDebMic.getUltimoNumeroReferenciaMIC() == null); i--) 
			{
				debDto = debs.get(i);
				if (debDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
					//
					// Logica ult. debito MIC
					//
					if (ultDebMic == null) {
						ultDebMic = new MicInformacionPaginadoDebito();
						ultDebMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB);
					}
					try {
						if (ultDebMic.getUltimoNumeroDUC() == null && debDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.DUC) == 0) {
							ultDebMic.setUltimoNumeroDUC(debDto.getNroDoc());
							ultDebMic.setUltimaFechaDUC(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
							ultDebMic.setUltimoClienteDUC(new BigInteger(debDto.getCliente()));
							ultDebMic.setUltimaCuentaDUC(new BigInteger(debDto.getCuenta()));
						} else if (ultDebMic.getUltimoNumeroReferenciaMIC() == null && debDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.FAC) == 0) {
							ultDebMic.setUltimoNumeroReferenciaMIC(new BigInteger(debDto.getNumeroReferenciaMic()));
							ultDebMic.setUltimaFechaFactura(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
							ultDebMic.setUltimoClienteFactura(new BigInteger(debDto.getCliente()));
							ultDebMic.setUltimaCuentaFactura(new Integer(debDto.getCuenta()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (ultDebCalipso == null &&  debDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
					//
					// Logica ult. debito CALIPSO
					//
					try {
						ultDebCalipso = new InformacionParaPaginadoDebito();
						ultDebCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB)));
						if (!Validaciones.isObjectNull(debDto.getIdDocCuentaCobranza())) {
							ultDebCalipso.setUltimoIdDocCtasCob(debDto.getIdDocCuentaCobranza().toBigInteger());
						}
						ultDebCalipso.setUltimoClienteDocumento(new BigInteger(debDto.getCliente()));
						ultDebCalipso.setUltimaFechaDocumento(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				}
			}
			
			if (ultDebMic != null) {
				if (ultDebMic.getUltimoNumeroDUC() == null) {
					int pagina = debPaginado.getPaginaActual();
					pagina--;
					while (ultDebMic.getUltimoNumeroDUC() == null && pagina > 0) {
						MicInformacionPaginadoDebito info = debPaginado.getInfoPaginaMic().get(pagina).getFin();
						ultDebMic.setUltimoNumeroDUC(info.getUltimoNumeroDUC());
						ultDebMic.setUltimaFechaDUC(info.getUltimaFechaDUC());
						ultDebMic.setUltimoClienteDUC(info.getUltimoClienteDUC());
						ultDebMic.setUltimaCuentaDUC(info.getUltimaCuentaDUC());
						pagina--;
					}
				}
				
				if (ultDebMic.getUltimoNumeroReferenciaMIC() == null) {
					int pagina = debPaginado.getPaginaActual();
					pagina--;
					while (ultDebMic.getUltimoNumeroReferenciaMIC() == null && pagina > 0) {
						MicInformacionPaginadoDebito info = debPaginado.getInfoPaginaMic().get(pagina).getFin();
						ultDebMic.setUltimoNumeroReferenciaMIC(info.getUltimoNumeroReferenciaMIC());
						ultDebMic.setUltimaFechaFactura(info.getUltimaFechaFactura());
						ultDebMic.setUltimoClienteFactura(info.getUltimoClienteFactura());
						ultDebMic.setUltimaCuentaFactura(info.getUltimaCuentaFactura());
						pagina--;
					}
				}
			}
			
			if (ultDebCalipso != null) {
				int pagina = debPaginado.getPaginaActual();
				pagina--;
				while (ultDebCalipso == null && pagina > 0) {
					ultDebCalipso = debPaginado.getInfoPaginaCalipso().get(pagina).getFin();
					pagina--;
				}
			}
			
			for (int i = 0; i <= (debs.size() - 1) && (primerDebCalipso == null || primerDebMic == null); i++) {
				debDto = debs.get(i);
				if (primerDebMic == null && debDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
					//Logica ult. debito MIC
					primerDebMic = new MicInformacionPaginadoDebito();
					primerDebMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB);
					try {
						if (debDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.DUC) == 0) {
							primerDebMic.setUltimoNumeroDUC(debDto.getNroDoc());
							primerDebMic.setUltimaFechaDUC(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
							primerDebMic.setUltimoClienteDUC(new BigInteger(debDto.getCliente()));
							primerDebMic.setUltimaCuentaDUC(new BigInteger(debDto.getCuenta()));
						} else if (debDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.FAC) == 0) {
							primerDebMic.setUltimoNumeroReferenciaMIC(new BigInteger(debDto.getNumeroReferenciaMic()));
							primerDebMic.setUltimaFechaFactura(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
							primerDebMic.setUltimoClienteFactura(new BigInteger(debDto.getCliente()));
							primerDebMic.setUltimaCuentaFactura(new Integer(debDto.getCuenta()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (primerDebCalipso == null && debDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
					//Logica primer debito CALIPSO
					primerDebCalipso = new InformacionParaPaginadoDebito();
					if (!Validaciones.isObjectNull(debDto.getIdDocCuentaCobranza())) {
						primerDebCalipso.setUltimoIdDocCtasCob(debDto.getIdDocCuentaCobranza().toBigInteger());
					}
					primerDebCalipso.setUltimoClienteDocumento(new BigInteger(debDto.getCliente()));
					try {
						primerDebCalipso.setUltimaFechaDocumento(Utilidad.parseDatePickerString(debDto.getFechaVenc()));
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
					primerDebCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_DEB)));
				}
			}
		}
		
		//Guardo los paginados en la pagina actual
		
		if (primerDebCalipso == null && ultDebCalipso == null) {
			InfoPaginaCalipsoDebito infoPagAnterior = debPaginado.getInfoPaginaCalipso().get(debPaginado.getPaginaAnterior());
			if (infoPagAnterior !=null && infoPagAnterior.getInicio()!=null && infoPagAnterior.getFin()!=null) {
				primerDebCalipso = infoPagAnterior.getInicio();
				ultDebCalipso = infoPagAnterior.getFin();
			}
			if (ConstantesCobro.PAGINADO_BUSCAR.equals(accion) || debPaginado.getPaginaActual()==1) {
				if (infoPagCalipso.isHayInformacionEnGeneral() && !infoPagCalipso.isHayInformacionEnEstaPagina()) {
					primerDebCalipso = debPaginado.reiniciarInformacionPaginadoCalipso();
					ultDebCalipso = debPaginado.reiniciarInformacionPaginadoCalipso();
				}
			}
		}
		infoPagCalipso.setInicio(primerDebCalipso);
		infoPagCalipso.setFin(ultDebCalipso);
		debPaginado.getInfoPaginaCalipso().put(debPaginado.getPaginaActual(), infoPagCalipso);
		
		
		if (primerDebMic == null && ultDebMic == null) {
			InfoPaginaMicDebito infoPagAnterior = debPaginado.getInfoPaginaMic().get(debPaginado.getPaginaAnterior());
			if (infoPagAnterior !=null && infoPagAnterior.getInicio()!=null && infoPagAnterior.getFin()!=null) {
				primerDebMic = infoPagAnterior.getInicio();
				ultDebMic = infoPagAnterior.getFin();
			}
			if (ConstantesCobro.PAGINADO_BUSCAR.equals(accion) || debPaginado.getPaginaActual()==1) {
				if (infoPagMic.isHayInformacionEnGeneral() && !infoPagMic.isHayInformacionEnEstaPagina()) {
					primerDebMic = debPaginado.reiniciarInformacionPaginadoMIC();
					ultDebMic = debPaginado.reiniciarInformacionPaginadoMIC();
				}
			}
		}
		infoPagMic.setInicio(primerDebMic);
		infoPagMic.setFin(ultDebMic);
		debPaginado.getInfoPaginaMic().put(debPaginado.getPaginaActual(), infoPagMic);
		
		//
		if (debs.size() > 0) {
			if (debPaginado.getFiltro().getIdsDoc() != null && !debPaginado.getFiltro().getIdsDoc().trim().isEmpty()) {
				String[] idDebs = debPaginado.getFiltro().getIdsDoc().trim().split(",");
				idDebs = Utilidad.ordenarCrec(idDebs);
				for (CobroDebitoDto dto : debs) {
					dto.setSeleccionado(Utilidad.busBin(idDebs, dto.getIdDebitoPantalla()) > 0);
				}
			}
			for (CobroDebitoDto dto : debs) {
				dto.setMonedaImporteCobrar(debPaginado.getFiltro().getMonedaImporteACobrar());
				dto.setSemaforo(onlineServicio.determinarGestionabilidadDeDeuda(dto));
				if(SistemaEnum.CALIPSO.equals(dto.getSistemaOrigen()) 
						&& !Validaciones.isNullEmptyOrDash(debPaginado.getFiltro().getFechaTipoCambio())
						&& COMPENSACION.equals(debPaginado.getFiltro().getMotivo())){
					//TODO
					dto.setFechaTipoCambio(debPaginado.getFiltro().getFechaTipoCambio());
				}
				//onlineServicio.obtenerSaldoMaximoDebito(dto);
				onlineServicio.obtenerImporteAUtilizarDebito(dto, debPaginado.getFiltro().getMotivo());
			}
		}
		
		DebitoJsonResponse resultado = new DebitoJsonResponse();
		resultado.setControlPaginacion(debPaginado.clone());
		resultado.getControlPaginacion().setCantRegistrosMostrados(debs.size());
		resultado.setResultado(((ArrayList<CobroDebitoDto>) Utilidad.guionesNull(debs)));
		
		resultado.setErrorMensaje(this.generarMensajeAMostrar(errorMensajeMic, errorMensajeCalipso, null));
		resultado.setInformacionMensaje(this.generarMensajeAMostrar(informacionMic, informacionCalipso, null));
		
		return resultado;
	}
	
	/**
	 * Carga el id del cobro actual al debito mic
	 * @param debPaginado
	 * @param calipsoSalida
	 */
	private MicConsultaDeudaSalida cargarIdCobroMic(DebitosControlPaginacion debPaginado, MicConsultaDeudaSalida micSalida) {

		for (MicDebito micDebito : micSalida.getListaDebitos()){
			micDebito.setIdCobro(debPaginado.getFiltro().getIdCobro());
		}
		return micSalida;
	}

	/**
	 * Carga el id del cobro actual al debito calipso
	 * @param debPaginado
	 * @param calipsoSalida
	 */
	private SalidaCalipsoDeudaWS cargarIdCobroCalipso(DebitosControlPaginacion debPaginado,SalidaCalipsoDeudaWS calipsoSalida){
		
		for (CalipsoDebito clpDebito : calipsoSalida.getListaDebitos()){
			clpDebito.setIdCobro(debPaginado.getFiltro().getIdCobro());
		}
		return calipsoSalida;
	}
	
	/**
	 * Carga el id del cobro actual al credito mic
	 * @param credPaginado
	 * @param micSalida
	 */
	private MicConsultaCreditoSalida cargarIdCobroMicCredito(CreditosControlPaginacion credPaginado, MicConsultaCreditoSalida micSalida) {

		for (MicCredito micCredito : micSalida.getListaCreditos()){
			micCredito.setIdCobro(credPaginado.getFiltro().getIdCobro());
		}
		return micSalida;
	}

	/**
	 * Carga el id del cobro actual al credito calipso
	 * @param credPaginado
	 * @param calipsoSalida
	 */
	private SalidaCalipsoCreditoWS cargarIdCobroCalipsoCredito(CreditosControlPaginacion credPaginado,SalidaCalipsoCreditoWS calipsoSalida){
		
		for (CalipsoCredito clpCredito : calipsoSalida.getListaCreditos()){
			clpCredito.setIdCobro(credPaginado.getFiltro().getIdCobro());
		}
		return calipsoSalida;
	}
	
	/**
	 * Carga el id del cobro actual al credito Shiva
	 * @param credPaginado
	 * @param shivaSalida
	 */
	private ShivaConsultaCreditoSalida cargarIdCobroShivaCredito(CreditosControlPaginacion credPaginado,ShivaConsultaCreditoSalida shivaSalida){
		
		for (VistaSoporteResultadoBusquedaValor shivaCred : shivaSalida.getListaCreditos()) {
			shivaCred.setIdCobro(credPaginado.getFiltro().getIdCobro());
		}
		
		return shivaSalida;
	}
	
	private boolean verificarSiTodosLosClientesSonMayoresA10(String ids) {
		if (!Validaciones.isNullEmptyOrDash(ids)) {
			String idVector[] = ids.split(",");
			boolean todosClienteUsuario = true;
			for (String id : idVector) {
				if (id.trim().length() <= 10) {
					todosClienteUsuario = false;
					break;
				}
			}
			if (!todosClienteUsuario) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifico si puedo realizar busqueda al calipso
	 * @return
	 */
	private boolean permitirBusquedaPorCalipso(ConfCobroDebitoFiltro filtro) {
		if (this.verificarSiTodosLosClientesSonMayoresA10(filtro.getIdsClientes())) {
			return false;
		}

		if (Validaciones.isNullOrEmpty(filtro.getSistema())) {
			if (!Validaciones.isNullOrEmpty(filtro.getRefMIC())) {
				return false;
			}
			if (!Validaciones.isNullOrEmpty(filtro.getNroDoc())) {
				if (Validaciones.validarNroDocumentoMIC_2_debitos(filtro.getNroDoc())) {
					return false;
				}
				if (Validaciones.validarNroDocumentoDuc(filtro.getNroDoc())) {
					return false;
				}
			}
		}
		if ((!Validaciones.isNullOrEmpty(filtro.getSistema()) 
				&&  SistemaEnum.MIC.getDescripcionCorta().equals(filtro.getSistema()))) {
			return false;
		}
		if ((!Validaciones.isNullOrEmpty(filtro.getSistema()) 
				&&  SistemaEnum.CALIPSO.getDescripcionCorta().equals(filtro.getSistema()))) {
			if (!Validaciones.isNullOrEmpty(filtro.getRefMIC())) {
				return false;
			}
		}
		if (!Validaciones.isNullOrEmpty(filtro.getTipoDoc()) 
				&&  TipoComprobanteEnum.DUC.name().equalsIgnoreCase(filtro.getTipoDoc())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifico si puedo realizar busqueda al Mic
	 * @return
	 */
	private boolean permitirBusquedaPorMic(ConfCobroDebitoFiltro filtro) {
		if (this.verificarSiTodosLosClientesSonMayoresA10(filtro.getIdsClientes())) {
			return false;
		}
		if (Validaciones.isNullOrEmpty(filtro.getSistema())) {
			if (!Validaciones.isNullOrEmpty(filtro.getMoneda()) && MonedaEnum.DOL.getSigla().equalsIgnoreCase(filtro.getMoneda())) {
				return false;
			}
			return true;
		}
		if (!Validaciones.isNullOrEmpty(filtro.getSistema()) && SistemaEnum.MIC.getDescripcionCorta().equals(filtro.getSistema())) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Consulta la deuda pero restringe algunos resultados puntuales
	 * @param micEntrada
	 * @return
	 * @throws JmsExcepcion
	 */
	private MicConsultaDeudaSalida consultarDeudaYRevisarResultado(MicConsultaDeudaEntrada micEntrada) throws JmsExcepcion {
		MicConsultaDeudaSalida micSalida = clienteMicServicio.consultarDeuda(micEntrada);
		
		if (Validaciones.isCollectionNotEmpty(micSalida.getListaDebitos())
				&& micSalida.getListaDebitos().size() == 1 ) {
			MicDebito unicoDebito = micSalida.getListaDebitos().get(0);
			
			String codigoEstadoFactura = unicoDebito.getCodigoEstadoFactura();
			String codigoEstadoDuc = unicoDebito.getCodigoEstadoDuc();
			if (EstadoOrigenEnum.COBRADA_EN_BANCO.codigo().equalsIgnoreCase(codigoEstadoFactura)
					|| EstadoOrigenEnum.COBRADA_OF_TECO.codigo().equalsIgnoreCase(codigoEstadoFactura)
					|| EstadoOrigenEnum.COBRADA_POR_DA.codigo().equalsIgnoreCase(codigoEstadoFactura)
					|| EstadoDUCEnum.COBRADO.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRADO_Y_APLIC_REF.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRADO_Y_ENV_FACT.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRADO_Y_MOV_RTE_I.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRADO_Y_TRANSFER.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRADO_Y_UTILIZADO.codigo().equalsIgnoreCase(codigoEstadoDuc)
					|| EstadoDUCEnum.COBRO_REVERTIDO.codigo().equalsIgnoreCase(codigoEstadoDuc) //Defecto 276
				) {
			
				micSalida.getListaDebitos().remove(0);
				micSalida.getControlPaginado().setCantidadRegistrosRetornados(new Long ("0"));
				micSalida.getControlPaginado().setCantidadRegistrosTotales(new Long ("0"));
				micSalida.setInformacionAMostrar(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busquedaDebito.informar.debito.cobrado"));
			}
		}
		return micSalida;	
	}
	
	/****************************************************************************************************************
	 *Creditos
	 */
	
	/**
	 * 
	 */
	public CreditoJsonResponse buscarCreditos(ConfCobroCreditoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion {
		filtro.getUsuarioLogeado().setCredPaginado(new CreditosControlPaginacion(filtro));
		return this.paginarCreditos(filtro.getUsuarioLogeado().getCredPaginado(), filtro.getIdsClientes(), filtro.getIdsDoc(), ConstantesCobro.PAGINADO_BUSCAR);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public CreditoJsonResponse paginarCreditos(CreditosControlPaginacion credPaginado, 
			String idsClientes, String idsDoc, String accion) throws NegocioExcepcion, ShivaExcepcion {
		
		//Mensajes de error
		String errorMensajeCalipso = null;
		String errorMensajeMic = null;
		String errorMensajeShiva = null;
		
		// Calipso
		EntradaCalipsoCreditoWS calipsoEntrada;
		SalidaCalipsoCreditoWS calipsoSalida = null;
				
		MicConsultaCreditoEntrada micEntrada;
		MicConsultaCreditoSalida micSalida = null;
		
		VistaSoporteBusquedaValoresFiltro shivaEntrada;
		ShivaConsultaCreditoSalida shivaSalida = null;
		
		if(ConstantesCobro.PAGINADO_BUSCAR.equalsIgnoreCase(accion)){
			credPaginado.getFiltro().setIdsClientes(idsClientes);
			credPaginado.getFiltro().setIdsDoc(idsDoc);
		}
		
		calipsoEntrada = credPaginado.obtenerEntradaCalipso();
		micEntrada = credPaginado.obtenerEntradaMic();
		shivaEntrada = credPaginado.obtenerEntradaShiva();
		
		credPaginado.setPuntoDeInicio(accion, calipsoEntrada, micEntrada, shivaEntrada);
		
		List<CobroCreditoDto> creditos = new ArrayList<CobroCreditoDto>();
		List<CobroCreditoDto> credsCalipso;
		List<CobroCreditoDto> credsMic;
		List<CobroCreditoDto> credsShiva;
		
		
		InfoPaginaCalipsoCredito infoPagCalipso = new InfoPaginaCalipsoCredito();
		InfoPaginaMicCredito infoPagMic = new InfoPaginaMicCredito();
		InfoPaginaShivaCredito infoPagShiva = new InfoPaginaShivaCredito();
		
		//Calipso
		if (permitirBusquedaPorCalipso(credPaginado.getFiltro())) {
			if (calipsoEntrada.getInformacionParaPaginado() != null) {
				try {
					calipsoSalida = clienteCalipsoServicio.consultaCredito(calipsoEntrada);
					
					if ("ERR".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())) {
						String mensaje = calipsoSalida.getResultadoProcesamiento().getDescripcionError();
						Traza.advertencia(getClass(), "Error de Servicio: Consulta de Credito CALIPSO: " + mensaje);
						errorMensajeCalipso = "Se ha producido un error de servicio CALIPSO";
					}
					
				} catch (NegocioExcepcion e) {
					if (e instanceof WebServiceExcepcion) {
						errorMensajeCalipso = e.getMensajeAuxiliar();
					} else {
						throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
					}
				}
			} else {
				calipsoSalida = new SalidaCalipsoCreditoWS(credPaginado.getTotalCalipso());
			}
			if (!Validaciones.isObjectNull(calipsoSalida)) {
				if (!Validaciones.isObjectNull(calipsoSalida.getControlPaginado().getCantidadRegistrosTotales())) {
					calipsoSalida.getControlPaginado().getCantidadRegistrosTotales();
					int totalCalipso = calipsoSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
					credPaginado.setTotalCalipso(totalCalipso);
					if (totalCalipso > 0) {
						calipsoSalida = cargarIdCobroCalipsoCredito(credPaginado, calipsoSalida);
						credsCalipso = (List<CobroCreditoDto>) mapeadorCreditos.map(calipsoSalida.getListaCreditos());
						creditos.addAll(credsCalipso);
					}
				}
			}
		} else {
			credPaginado.setTotalCalipso(0);
		}
		
		//MIC
		if (permitirBusquedaPorMic(credPaginado.getFiltro())) {
			if (micEntrada.getInformacionPaginado() != null) {
				try {
					micSalida = clienteMicServicio.consultarCredito(micEntrada);
				} catch(NegocioExcepcion e) {
					if (e instanceof JmsExcepcion) {
						errorMensajeMic = e.getMensajeAuxiliar();
					} else {
						throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
					}
				}
			} else {
				micSalida = new MicConsultaCreditoSalida(credPaginado.getTotalMic());
			}
			if (!Validaciones.isObjectNull(micSalida)) {
				int totalMic = micSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
				credPaginado.setTotalMic(totalMic);
				if (totalMic > 0) {
					micSalida = cargarIdCobroMicCredito(credPaginado, micSalida);
					credsMic = (List<CobroCreditoDto>) mapeadorCreditos.map(micSalida.getListaCreditos());
					creditos.addAll(credsMic);
				}
			}
		} else {
			credPaginado.setTotalMic(0);
		}
		
		//Shiva
		if (permitirBusquedaPorShiva(credPaginado.getFiltro())) {
			if (shivaEntrada.getInformacionPaginadoCredito() != null) {
				try{
					shivaSalida = onlineServicio.buscarCreditosShiva(shivaEntrada);
				}catch(NegocioExcepcion e){
					if(e instanceof JmsExcepcion){
						errorMensajeShiva = e.getMensajeAuxiliar();
					}else{
						throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
					}
				}
			} else {
				shivaSalida = new ShivaConsultaCreditoSalida(credPaginado.getTotalShiva());
			}
			if(!Validaciones.isObjectNull(shivaSalida)){
				int totalShiva = shivaSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();				
				credPaginado.setTotalShiva(totalShiva);
				
				if (totalShiva > 0) {
					shivaSalida = cargarIdCobroShivaCredito(credPaginado, shivaSalida);
					credsShiva = (List<CobroCreditoDto>) cobroCreditoMapeadorShiva.map(shivaSalida.getListaCreditos());
					creditos.addAll(credsShiva);
				}
			}
		} else {
			credPaginado.setTotalShiva(0);
		}
		
		
		//total General
		credPaginado.setTotalRegistros(credPaginado.getTotalMic() + credPaginado.getTotalCalipso() + credPaginado.getTotalShiva());
		
		while (credPaginado.getPaginaActual() != 1 && creditos.size() == 0) {
			credPaginado.setPuntoDeInicio(ConstantesCobro.PAGINADO_ANTERIOR, calipsoEntrada, micEntrada, shivaEntrada);

			//Calipso
			if (permitirBusquedaPorCalipso(credPaginado.getFiltro())) {
			
				if (calipsoEntrada.getInformacionParaPaginado() != null) {
					try {
						calipsoSalida = clienteCalipsoServicio.consultaCredito(calipsoEntrada);
						if ("ERR".equals(calipsoSalida.getResultadoProcesamiento().getResultadoImputacion())) {
							String mensaje = calipsoSalida.getResultadoProcesamiento().getDescripcionError();
							Traza.advertencia(getClass(), "Error de Servicio: Consulta de Credito CALIPSO: " + mensaje);
							errorMensajeCalipso = "Se ha producido un error de servicio CALIPSO";
						}
						
					} catch (NegocioExcepcion e) {
						if (e instanceof WebServiceExcepcion) {
							errorMensajeCalipso = e.getMensajeAuxiliar();
						} else {
							throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
						}
					}
				} else {
					calipsoSalida = new SalidaCalipsoCreditoWS(credPaginado.getTotalCalipso());
				}
				if(!Validaciones.isObjectNull(calipsoSalida)){
					int totalCalipso = calipsoSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
					credPaginado.setTotalCalipso(totalCalipso);
					if (totalCalipso > 0) {
						credsCalipso = (List<CobroCreditoDto>) mapeadorCreditos.map(calipsoSalida.getListaCreditos());
						creditos.addAll(credsCalipso);
					}
				}
			} else {
				credPaginado.setTotalCalipso(0);
			}

			//Mic
			if (permitirBusquedaPorMic(credPaginado.getFiltro())) {
				
				if (micEntrada.getInformacionPaginado() != null) {
					try{
						micSalida = clienteMicServicio.consultarCredito(micEntrada);
					}catch(NegocioExcepcion e){
						if(e instanceof JmsExcepcion){
							errorMensajeMic = e.getMensajeAuxiliar();
						}else{
							throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
						}
					}
				} else {
					micSalida = new MicConsultaCreditoSalida(credPaginado.getTotalMic());
				}
				if(!Validaciones.isObjectNull(micSalida)){
					int totalMic = micSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();
					credPaginado.setTotalMic(totalMic);
					if (totalMic > 0) {
						credsMic = (List<CobroCreditoDto>) mapeadorCreditos.map(micSalida.getListaCreditos());
						creditos.addAll(credsMic);
					}
				}
			} else {
				credPaginado.setTotalMic(0);
			}
			
			//SHIVA
			if (permitirBusquedaPorShiva(credPaginado.getFiltro())) {
				if (shivaEntrada.getInformacionPaginadoCredito() != null) {
					try{
						shivaSalida = onlineServicio.buscarCreditosShiva(shivaEntrada);
					}catch(NegocioExcepcion e){
						if(e instanceof JmsExcepcion){
							errorMensajeShiva = e.getMensajeAuxiliar();
						}else{
							throw new NegocioExcepcion(e.getMessage(), e, e.getMensajeAuxiliar());
						}
					}
				} else {
					shivaSalida = new ShivaConsultaCreditoSalida(credPaginado.getTotalShiva());
				}
				if(!Validaciones.isObjectNull(shivaSalida)){
					int totalShiva = shivaSalida.getControlPaginado().getCantidadRegistrosTotales().intValue();				
					credPaginado.setTotalShiva(totalShiva);
					
					if (totalShiva > 0) {				
						credsShiva = (List<CobroCreditoDto>) cobroCreditoMapeadorShiva.map(shivaSalida.getListaCreditos());
						creditos.addAll(credsShiva);					
					}
				}
			} else {
				credPaginado.setTotalShiva(0);
			}
			
			//Total General
			credPaginado.setTotalRegistros(credPaginado.getTotalMic() + credPaginado.getTotalCalipso() + credPaginado.getTotalShiva());
		}
		
		//Seteo si hay alguna información en general
		if (credPaginado.getTotalCalipso() > 0) {
			infoPagCalipso.setHayInformacionEnGeneral(true);
		}
		if (credPaginado.getTotalMic() > 0) {
			infoPagMic.setHayInformacionEnGeneral(true);
		}
		if (credPaginado.getTotalShiva() > 0) {
			infoPagShiva.setHayInformacionEnGeneral(true);
		}		
		
		//Ordenamiento de Creditos
		Collections.sort(creditos, new CobroCreditoDtoComparator());
		credPaginado.actualizarPagina();
		
		//Preparo una lista de 100 registros
		List<CobroCreditoDto> creds = new ArrayList<CobroCreditoDto>();
		int paginaActual = credPaginado.getPaginaActual();
		boolean puedeCargarEnArregloCreditos = false;
		int contadorCredito = 0;
		for (int i = 0; i <= (creditos.size() - 1); i++) {
			//Credito
			CobroCreditoDto creDto = creditos.get(i);
			
			//Se aplica solo para la logica de paginados SHIVA
			if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.SHIVA.getDescripcionCorta()) == 0) {
				Long regIdValor = creDto.getIdValor();
				
				if (!Validaciones.isObjectNull(shivaEntrada.getInformacionPaginadoCredito())) {
					Long ultimoIdValor = shivaEntrada.getInformacionPaginadoCredito().getUltimoIdValor();
					if (!Validaciones.isObjectNull(ultimoIdValor) && !Validaciones.isObjectNull(regIdValor)) {
						//Por encontrar el ultimo valor, puedo empezar a cargar los registros en adelante
						if (ultimoIdValor.longValue() == regIdValor.longValue()) {
							puedeCargarEnArregloCreditos = true;
							
							//Ahi avanzo al siguiente registro
							if (paginaActual != 1) {
								i++;
								if (i <= (creditos.size() - 1)) {
									creDto = creditos.get(i);
								}
							}
						}
					} else {
						//Por ser la primera pagina, inicialmente puedo empesar a cargar los registros
						puedeCargarEnArregloCreditos = true;
					}
				}
			} else {
				//Ahi validar la carga, por ser de mic o de calipso
				puedeCargarEnArregloCreditos = true;
			}
			
			// Agrego en la lista de 100 registros
			if (puedeCargarEnArregloCreditos) {
				if (contadorCredito <= (ConstantesCobro.CANTIDAD_POR_PAGINA_CRED - 1)) {
					
					//Seteo si hay alguna información en pagina actual
					if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
						infoPagCalipso.setHayInformacionEnEstaPagina(true);
					}
					if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
						infoPagMic.setHayInformacionEnEstaPagina(true);
					}
					if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.SHIVA.getDescripcionCorta()) == 0) {
						infoPagShiva.setHayInformacionEnEstaPagina(true);
					}
					
					creds.add(creDto);
					contadorCredito++;
				} else {
					break;
				}
			}
		}
		
		CobroCreditoDto creDto = null;
		
		InformacionParaPaginadoCredito ultCreCalipso = null;
		MicInformacionPaginadoCredito ultCreMic = null;
		InformacionPaginadoCreditoShiva ultCreShiva = null;
		
		InformacionParaPaginadoCredito primerCreCalipso = null;
		MicInformacionPaginadoCredito primerCreMic = null;
		InformacionPaginadoCreditoShiva primerCreShiva = null;
		
		if (creds.size() > 0) {
			for (int i = (creds.size() - 1); i >= 0 
					&& (ultCreShiva == null || ultCreCalipso == null || ultCreMic == null 
					|| ultCreMic.getUltimoClienteNotaCredito() == null || ultCreMic.getUltimoCodigoClienteRemanente() == null
					|| ultCreCalipso.getUltimoIdDocCuentasCobranzaCTA() == null || ultCreCalipso.getUltimoIdDocCuentasCobranzaNC() == null
					|| ultCreShiva.getUltimoIdValor() == null); i--) 
			{
				creDto = creds.get(i);
				
				if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
					//
					// Logica ult. credito MIC
					//
					if (ultCreMic == null) {
						ultCreMic = new MicInformacionPaginadoCredito();
						ultCreMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
					}
					try {
						if (ultCreMic.getUltimoCodigoClienteRemanente() == null && creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.REM) == 0) {
							ultCreMic.setUltimoCodigoClienteRemanente(new BigInteger(creDto.getIdClienteLegado()));
							ultCreMic.setUltimaCuentaRemanente(new Integer(creDto.getCuenta()));
							ultCreMic.setUltimoCodigoTipoRemanente(new Integer(creDto.getSubtipo()));
							ultCreMic.setUltimaFechaRemanente(Utilidad.parseDatePickerString(creDto.getFechaAltaCredito()));
						} else if (ultCreMic.getUltimoClienteNotaCredito() == null && creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CRE) == 0) {
							ultCreMic.setUltimoNumeroReferenciaMicNC(new BigInteger(creDto.getNumeroReferenciaMic().toString()));
							ultCreMic.setUltimaFechaNotaCredito(Utilidad.parseDatePickerString(creDto.getFechaVenc()));
							ultCreMic.setUltimoClienteNotaCredito(new BigInteger(creDto.getIdClienteLegado()));
							ultCreMic.setUltimaCuentaNotaCredito(new Integer(creDto.getCuenta()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
					//
					// Logica ult. credito CALIPSO
					//
					if (ultCreCalipso == null) {
						ultCreCalipso = new InformacionParaPaginadoCredito();
						ultCreCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
					}
					try {
						if (ultCreCalipso.getUltimoIdDocCuentasCobranzaCTA() == null && creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CTA) == 0) {
							//ultCreCalipso.setUltimaFechaCTA(Utilidad.parseDatePickerString(creDto.getFechaAltaCredito()));
							ultCreCalipso.setUltimaFechaCTA(Utilidad.parseDatePickerString(creDto.getFechaEmision()));
							ultCreCalipso.setUltimoClienteCTA(new BigInteger(creDto.getIdClienteLegado()));
							ultCreCalipso.setUltimoIdDocCuentasCobranzaCTA(new BigInteger(creDto.getIdDocumentoCuentaCobranza().toString()));
						} else if (ultCreCalipso.getUltimoIdDocCuentasCobranzaNC() == null && creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CRE) == 0) {
							ultCreCalipso.setUltimaFechaNC(Utilidad.parseDatePickerString(creDto.getFechaVenc()));
							ultCreCalipso.setUltimoClienteNC(new BigInteger(creDto.getIdClienteLegado()));
							ultCreCalipso.setUltimoIdDocCuentasCobranzaNC(new BigInteger(creDto.getIdDocumentoCuentaCobranza().toString()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (ultCreShiva == null && creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.SHIVA.getDescripcionCorta()) == 0) {
					//
					// Logica ult. credito SHIVA
					//
					if (ultCreShiva == null) {
						ultCreShiva = new InformacionPaginadoCreditoShiva();
						ultCreShiva.setCantidadRegistrosARetornar(new Integer(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
					}
					if (ultCreShiva.getUltimoIdValor() == null) {
						ultCreShiva.setUltimoIdValor(creDto.getIdValor());
					} 
				}
			}
			
			if (ultCreMic != null) {
				if (ultCreMic.getUltimoCodigoClienteRemanente() == null) {
					int pagina = credPaginado.getPaginaActual();
					pagina--;
					while (ultCreMic.getUltimoCodigoClienteRemanente() == null && pagina > 0) {
						MicInformacionPaginadoCredito info = credPaginado.getInfoPaginaMic().get(pagina).getFin();
						ultCreMic.setUltimoCodigoClienteRemanente(info.getUltimoCodigoClienteRemanente());
						ultCreMic.setUltimaCuentaRemanente(info.getUltimaCuentaRemanente()!=null?new Integer(info.getUltimaCuentaRemanente()):0);
						ultCreMic.setUltimoCodigoTipoRemanente(info.getUltimoCodigoTipoRemanente());
						ultCreMic.setUltimaFechaRemanente(info.getUltimaFechaRemanente());
						pagina--;
					}
				}
				
				if (ultCreMic.getUltimoClienteNotaCredito() == null) {
					int pagina = credPaginado.getPaginaActual();
					pagina--;
					while (ultCreMic.getUltimoClienteNotaCredito() == null && pagina > 0) {
						MicInformacionPaginadoCredito info = credPaginado.getInfoPaginaMic().get(pagina).getFin();
						ultCreMic.setUltimoNumeroReferenciaMicNC(info.getUltimoNumeroReferenciaMicNC());
						ultCreMic.setUltimaFechaNotaCredito(info.getUltimaFechaNotaCredito());
						ultCreMic.setUltimoClienteNotaCredito(info.getUltimoClienteNotaCredito());
						ultCreMic.setUltimaCuentaNotaCredito(info.getUltimaCuentaNotaCredito());
						pagina--;
					}
				}
			}
			
			if (ultCreCalipso != null) {
				if (ultCreCalipso.getUltimoIdDocCuentasCobranzaCTA() == null) {
					int pagina = credPaginado.getPaginaActual();
					pagina--;
					while (ultCreCalipso.getUltimoIdDocCuentasCobranzaCTA() == null && pagina > 0) {
						InformacionParaPaginadoCredito info = credPaginado.getInfoPaginaCalipso().get(pagina).getFin();
						ultCreCalipso.setUltimaFechaCTA(info.getUltimaFechaCTA());
						ultCreCalipso.setUltimoClienteCTA(info.getUltimoClienteCTA());
						ultCreCalipso.setUltimoIdDocCuentasCobranzaCTA(info.getUltimoIdDocCuentasCobranzaCTA());
						pagina--;
					}
				}
				
				if (ultCreCalipso.getUltimoIdDocCuentasCobranzaNC() == null) {
					int pagina = credPaginado.getPaginaActual();
					pagina--;
					while (ultCreCalipso.getUltimoIdDocCuentasCobranzaNC() == null && pagina > 0) {
						InformacionParaPaginadoCredito info = credPaginado.getInfoPaginaCalipso().get(pagina).getFin();
						ultCreCalipso.setUltimaFechaNC(info.getUltimaFechaNC());
						ultCreCalipso.setUltimoClienteNC(info.getUltimoClienteNC());
						ultCreCalipso.setUltimoIdDocCuentasCobranzaNC(info.getUltimoIdDocCuentasCobranzaNC());
						pagina--;
					}
				}
			}
			
			if (ultCreShiva != null) {
				if (ultCreShiva.getUltimoIdValor() == null) {
					int pagina = credPaginado.getPaginaActual();
					pagina--;
					while (ultCreShiva.getUltimoIdValor() == null && pagina > 0) {
						InformacionPaginadoCreditoShiva info = credPaginado.getInfoPaginaShiva().get(pagina).getFin();
						ultCreShiva.setUltimoIdValor(info.getUltimoIdValor());
						pagina--;
					}
				}
			}
			
			for (int i = 0; i <= (creds.size() - 1) && (primerCreCalipso == null || primerCreMic == null || primerCreShiva == null); i++) {
				creDto = creds.get(i);
				if (primerCreMic == null && creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.MIC.getDescripcionCorta()) == 0) {
					//Logica primer credito MIC
					primerCreMic = new MicInformacionPaginadoCredito();
					primerCreMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
					try {
						if (creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.REM) == 0) {
							primerCreMic.setUltimoCodigoClienteRemanente(new BigInteger(creDto.getIdClienteLegado()));
							primerCreMic.setUltimaCuentaRemanente(new Integer(creDto.getCuenta()));
							primerCreMic.setUltimoCodigoTipoRemanente(new Integer(creDto.getSubtipo()));
							primerCreMic.setUltimaFechaRemanente(Utilidad.parseDatePickerString(creDto.getFechaVenc()));
						} else if (creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CRE) == 0) {
							if (creDto.getNumeroReferenciaMic() != null) {
								primerCreMic.setUltimoNumeroReferenciaMicNC(new BigInteger(creDto.getNumeroReferenciaMic().toString()));
							}
							primerCreMic.setUltimaFechaNotaCredito(Utilidad.parseDatePickerString(creDto.getFechaVenc()));
							primerCreMic.setUltimoClienteNotaCredito(new BigInteger(creDto.getIdClienteLegado()));
							primerCreMic.setUltimaCuentaNotaCredito(new Integer(creDto.getCuenta()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (primerCreCalipso == null &&  creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.CALIPSO.getDescripcionCorta()) == 0) {
					//Logica primer credito CALIPSO
					primerCreCalipso = new InformacionParaPaginadoCredito();
					primerCreCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
					try {
						if (creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CTA) == 0) {
							//primerCreCalipso.setUltimaFechaCTA(Utilidad.parseDatePickerString(creDto.getFechaAltaCredito()));
							primerCreCalipso.setUltimaFechaCTA(Utilidad.parseDatePickerString(creDto.getFechaEmision()));
							primerCreCalipso.setUltimoClienteCTA(new BigInteger(creDto.getIdClienteLegado()));
							primerCreCalipso.setUltimoIdDocCuentasCobranzaCTA(new BigInteger(creDto.getIdDocumentoCuentaCobranza().toString()));
						} else if (creDto.getTipoComprobanteEnum().compareTo(TipoComprobanteEnum.CRE) == 0) {
							primerCreCalipso.setUltimaFechaCTA(Utilidad.parseDatePickerString(creDto.getFechaVenc()));
							primerCreCalipso.setUltimoClienteNC(new BigInteger(creDto.getIdClienteLegado()));
							primerCreCalipso.setUltimoIdDocCuentasCobranzaNC(new BigInteger(creDto.getIdDocumentoCuentaCobranza().toString()));
						}
					} catch (ParseException e) {
						Traza.error(getClass(),e.getMessage());
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				} else if (primerCreShiva == null &&  creDto.getSistemaOrigen().getDescripcionCorta().compareTo(SistemaEnum.SHIVA.getDescripcionCorta()) == 0) {
					//Logica primer credito SHIVA
					primerCreShiva = new InformacionPaginadoCreditoShiva();
					primerCreShiva.setCantidadRegistrosARetornar(new Integer(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
					
					primerCreShiva.setUltimoIdValor(creDto.getIdValor());			
				}
			}
		}
		
		//Guardo los paginados en la pagina actual
		//
		if (primerCreCalipso == null && ultCreCalipso == null) {
			InfoPaginaCalipsoCredito infoPagAnterior = credPaginado.getInfoPaginaCalipso().get(credPaginado.getPaginaAnterior());
			if (infoPagAnterior !=null && infoPagAnterior.getInicio()!=null && infoPagAnterior.getFin()!=null) {
				primerCreCalipso = infoPagAnterior.getInicio();
				ultCreCalipso = infoPagAnterior.getFin();
			}
			if (ConstantesCobro.PAGINADO_BUSCAR.equals(accion) || credPaginado.getPaginaActual()==1) {
				if (infoPagCalipso.isHayInformacionEnGeneral() && !infoPagCalipso.isHayInformacionEnEstaPagina()) {
					primerCreCalipso = credPaginado.reiniciarInformacionPaginadoCalipso();
					ultCreCalipso = credPaginado.reiniciarInformacionPaginadoCalipso();
				}
			}
		}
		infoPagCalipso.setInicio(primerCreCalipso);
		infoPagCalipso.setFin(ultCreCalipso);
		credPaginado.getInfoPaginaCalipso().put(credPaginado.getPaginaActual(), infoPagCalipso);
		
		//
		if (primerCreMic == null && ultCreMic == null) {
			InfoPaginaMicCredito infoPagAnterior = credPaginado.getInfoPaginaMic().get(credPaginado.getPaginaAnterior());
			if (infoPagAnterior !=null && infoPagAnterior.getInicio()!=null && infoPagAnterior.getFin()!=null) {
				primerCreMic = infoPagAnterior.getInicio();
				ultCreMic = infoPagAnterior.getFin();
			}
			if (ConstantesCobro.PAGINADO_BUSCAR.equals(accion) || credPaginado.getPaginaActual()==1) {
				if (infoPagMic.isHayInformacionEnGeneral() && !infoPagMic.isHayInformacionEnEstaPagina()) {
					primerCreMic = credPaginado.reiniciarInformacionPaginadoMIC();
					ultCreMic = credPaginado.reiniciarInformacionPaginadoMIC();
				}
			}
		}
		infoPagMic.setInicio(primerCreMic);
		infoPagMic.setFin(ultCreMic);
		credPaginado.getInfoPaginaMic().put(credPaginado.getPaginaActual(), infoPagMic);
		
		//
		if (primerCreShiva == null && ultCreShiva == null) {
			InfoPaginaShivaCredito infoPagAnterior = credPaginado.getInfoPaginaShiva().get(credPaginado.getPaginaAnterior());
			if (infoPagAnterior !=null && infoPagAnterior.getInicio()!=null && infoPagAnterior.getFin()!=null) {
				primerCreShiva = infoPagAnterior.getInicio();
				ultCreShiva = infoPagAnterior.getFin();
			}
			if (ConstantesCobro.PAGINADO_BUSCAR.equals(accion) || credPaginado.getPaginaActual()==1) {
				if (infoPagShiva.isHayInformacionEnGeneral() && !infoPagShiva.isHayInformacionEnEstaPagina()) {
					primerCreShiva = credPaginado.reiniciarInformacionPaginadoSHIVA();
					ultCreShiva = credPaginado.reiniciarInformacionPaginadoSHIVA();
				}
			}
		}
		infoPagShiva.setInicio(primerCreShiva);
		infoPagShiva.setFin(ultCreShiva);
		credPaginado.getInfoPaginaShiva().put(credPaginado.getPaginaActual(), infoPagShiva);
		
		//
		if (creds.size() > 0) {
			if (credPaginado.getFiltro().getIdsDoc() != null && !credPaginado.getFiltro().getIdsDoc().trim().isEmpty()) {
				String[] idCreds = credPaginado.getFiltro().getIdsDoc().trim().split(",");
				idCreds = Utilidad.ordenarCrec(idCreds);
				for (CobroCreditoDto dto : creds) {
					dto.setSeleccionado(Utilidad.busBin(idCreds, dto.getIdCreditoPantalla()) > 0);
				}
			}
			for (CobroCreditoDto dto : creds) {
				dto.setMonedaImporteUtilizar(credPaginado.getFiltro().getMonedaImporteACobrar());
				dto.setSemaforo(onlineServicio.determinarGestionabilidadDeCredito(dto));
				onlineServicio.obtenerImporteAUtilizarCredito(dto);
			}
		}
		
		CreditoJsonResponse resultado = new CreditoJsonResponse();
		resultado.setControlPaginacion(credPaginado.clone());
		resultado.getControlPaginacion().setCantRegistrosMostrados(creds.size());
		resultado.setResultado(((ArrayList<CobroCreditoDto>) Utilidad.guionesNull(creds)));
		
		resultado.setErrorMensaje(this.generarMensajeAMostrar(errorMensajeMic, errorMensajeCalipso, errorMensajeShiva));
		return resultado;
	}

	/**
	 * Verifico si puedo realizar busqueda al calipso
	 * @param sistema
	 * @param tipoMedioPagoCobro
	 * @return
	 */
	private boolean permitirBusquedaPorCalipso(ConfCobroCreditoFiltro filtro) {
		if (this.verificarSiTodosLosClientesSonMayoresA10(filtro.getIdsClientes())) {
			return false;
		}
		if (
			Validaciones.isNullOrEmpty(filtro.getSistema()) ||
			(
				!Validaciones.isNullOrEmpty(filtro.getSistema()) &&
				SistemaEnum.CALIPSO.getDescripcionCorta().equals(filtro.getSistema())
			)
		) {
			
			if (!Validaciones.isNullOrEmpty(filtro.getTipoMedioPago()) 
					&&  !TipoCreditoEnum.PAGOACUENTA.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())
					&&  !TipoCreditoEnum.NOTACREDITO.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Verifico si puedo realizar busqueda al Mic
	 * @param sistema
	 * @param tipoMedioPagoCobro
	 * @return
	 */
	private boolean permitirBusquedaPorMic(ConfCobroCreditoFiltro filtro) {
		if (this.verificarSiTodosLosClientesSonMayoresA10(filtro.getIdsClientes())) {
			return false;
		}
		if (Validaciones.isNullOrEmpty(filtro.getSistema()) 
				|| (!Validaciones.isNullOrEmpty(filtro.getSistema()) 
						&&  SistemaEnum.MIC.getDescripcionCorta().equals(filtro.getSistema()))) 
		{
			if (!Validaciones.isNullOrEmpty(filtro.getTipoMedioPago()) 
					&&  !TipoCreditoEnum.NOTACREDITO.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())
					&&  !TipoCreditoEnum.REMANENTE.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())) {
				return false;
			}
			if (!Validaciones.isNullOrEmpty(filtro.getMoneda()) 
					&&  MonedaEnum.DOL.getSigla().equalsIgnoreCase(filtro.getMoneda())) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Verifico si puedo realizar busqueda al shiva
	 * @param sistema
	 * @param tipoMedioPagoCobro
	 * @return
	 */
	private boolean permitirBusquedaPorShiva(ConfCobroCreditoFiltro filtro) {
		
		if (Validaciones.isNullOrEmpty(filtro.getSistema()) 
				|| (!Validaciones.isNullOrEmpty(filtro.getSistema()) 
						&&  SistemaEnum.SHIVA.getDescripcionCorta().equals(filtro.getSistema()))) 
		{
			if (!Validaciones.isNullOrEmpty(filtro.getTipoMedioPago()) 
					&&  (TipoCreditoEnum.PAGOACUENTA.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())
							||  TipoCreditoEnum.NOTACREDITO.getValor().equalsIgnoreCase(filtro.getTipoMedioPago())
							||  TipoCreditoEnum.REMANENTE.getValor().equalsIgnoreCase(filtro.getTipoMedioPago()))) {
				return false;
			}
			if (!Validaciones.isNullOrEmpty(filtro.getMoneda()) 
					&&  MonedaEnum.DOL.getSigla().equalsIgnoreCase(filtro.getMoneda())) {
				return false;
			}
			if (!Validaciones.isNullOrEmpty(filtro.getAcuerdoFac())) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Preparo los mensajes a mostrar (pueden ser mensajes de error o informativo)
	 * @param mensajeMic
	 * @param mensajeCalipso
	 * @param mensajeShiva
	 * @return
	 */
	private String generarMensajeAMostrar(String mensajeMic, String mensajeCalipso, String mensajeShiva) {
		String mensaje = null;
		if (!Validaciones.isNullOrEmpty(mensajeCalipso)) {
			mensaje = mensajeCalipso;
		}
		if (!Validaciones.isNullOrEmpty(mensajeMic)) {
			mensaje = mensajeMic;
		}
		if (!Validaciones.isNullOrEmpty(mensajeShiva)) {
			mensaje = mensajeShiva;
		}
		if (!Validaciones.isNullOrEmpty(mensajeCalipso) && !Validaciones.isNullOrEmpty(mensajeMic)) {
			mensaje = mensajeCalipso + "<br>" + mensajeMic;
		}
		if (!Validaciones.isNullOrEmpty(mensajeCalipso) && !Validaciones.isNullOrEmpty(mensajeShiva)) {
			mensaje = mensajeCalipso + "<br>" + mensajeShiva;
		}
		if (!Validaciones.isNullOrEmpty(mensajeMic) && !Validaciones.isNullOrEmpty(mensajeShiva)) {
			mensaje = mensajeMic + "<br>" + mensajeShiva;
		}
		if (!Validaciones.isNullOrEmpty(mensajeCalipso) && !Validaciones.isNullOrEmpty(mensajeMic)
			&& !Validaciones.isNullOrEmpty(mensajeShiva)) {
			mensaje = mensajeCalipso + "<br>" + mensajeMic + "<br>" +mensajeShiva;
		}
		return mensaje;
	}
	
	/******************************************************************************************
	 * Getters & Setters
	 ******************************************************************************************/
	public IClienteCalipsoServicio getClienteCalipsoServicio() {
		return clienteCalipsoServicio;
	}

	public void setClienteCalipsoServicio(
			IClienteCalipsoServicio clienteCalipsoServicio) {
		this.clienteCalipsoServicio = clienteCalipsoServicio;
	}

	public IMicJmsSyncServicio getClienteMicServicio() {
		return clienteMicServicio;
	}

	public void setClienteMicServicio(IMicJmsSyncServicio clienteMicServicio) {
		this.clienteMicServicio = clienteMicServicio;
	}

	public MapeadorSOA getMapeadorDeudas() {
		return mapeadorDeudas;
	}

	public void setMapeadorDeudas(MapeadorSOA mapeadorDeudas) {
		this.mapeadorDeudas = mapeadorDeudas;
	}

	public MapeadorSOA getMapeadorCreditos() {
		return mapeadorCreditos;
	}

	public void setMapeadorCreditos(MapeadorSOA mapeadorCreditos) {
		this.mapeadorCreditos = mapeadorCreditos;
	}

	public ICobroOnlineServicio getOnlineServicio() {
		return onlineServicio;
	}

	public void setOnlineServicio(ICobroOnlineServicio onlineServicio) {
		this.onlineServicio = onlineServicio;
	}

	public CobroCreditoMapeadorShiva getCobroCreditoMapeadorShiva() {
		return cobroCreditoMapeadorShiva;
	}

	public void setCobroCreditoMapeadorShiva(
			CobroCreditoMapeadorShiva cobroCreditoMapeadorShiva) {
		this.cobroCreditoMapeadorShiva = cobroCreditoMapeadorShiva;
	}
	
}