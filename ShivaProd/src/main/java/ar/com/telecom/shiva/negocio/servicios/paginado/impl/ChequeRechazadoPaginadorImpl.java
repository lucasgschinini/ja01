package ar.com.telecom.shiva.negocio.servicios.paginado.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorConsultaSoporteResultadoBusquedaChequeRechazado;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.ClienteCheque;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.IceCheques;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteIceServicio;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConsultaSoporteResultadoBusquedaChequeRechazado;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConsultaSoporteBusquedaChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ChequeRechazadoControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoCreditoShiva;
import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoGenerico;
import ar.com.telecom.shiva.presentacion.bean.paginacion.PaginadorAccion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaIceCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaShivaCredito;

public class ChequeRechazadoPaginadorImpl extends PaginadorServicioImpl {

	@Autowired 
	private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired
	private IClienteIceServicio clienteIceServicio;
	
	public ChequeRechazadoPaginadorImpl() {}
	@Override
	public List<Bean> buscar(ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro)chequeRechazadoControlPaginacion.getFiltro() ;
		// Seteo los filtro por como van estar ordenadoas en los servicos
		chequeRechazadoFiltro.setCobroOnlineCreditosShiva(false);
		chequeRechazadoFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()));
		chequeRechazadoFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
		
		if (Validaciones.isNullEmptyOrDash(chequeRechazadoFiltro.getFechaVencimiento())) {
			chequeRechazadoFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()));
			chequeRechazadoFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
		}
		
		chequeRechazadoFiltro.setSqlOrder(" ORDER By fecha_deposito DESC, ID_BANCO_ORIGEN, CAST( REFERENCIA_VALOR as NUMBER) ");
		
		chequeRechazadoFiltro.getIdEstados().add(Estado.VAL_DISPONIBLE.name());
		chequeRechazadoFiltro.getIdEstados().add(Estado.VAL_USADO.name());
		
		return this.paginado(chequeRechazadoControlPaginacion,PaginadorAccion.BUSCAR, validacionesAuxiliar);
	}
	@Override
	public List<Bean> paginado(ControlPaginacion controlPaginacion, PaginadorAccion accion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		List<Bean> chequeRechzados = new ArrayList<Bean>();
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = null;
		chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) controlPaginacion;
		
		// Punto de inicio
		chequeRechazadoControlPaginacion.actualizarPuntoDeInicio(accion);
		// Consultas
		chequeRechzados.addAll(this.listar(controlPaginacion, validacionesAuxiliar));
		// Mergeo y ordenamiento
		Collections.sort(chequeRechzados, new ComparatorConsultaSoporteResultadoBusquedaChequeRechazado());
		
		
		chequeRechazadoControlPaginacion.actualizarPagina();
		if (chequeRechzados.size() >= Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO) {
			chequeRechzados = new ArrayList<Bean>(chequeRechzados.subList(0, Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO));
		}
		// Nuevos punto de control
		this.actualizarOffset(controlPaginacion, chequeRechzados);
		controlPaginacion.setCantRegistrosMostrados(chequeRechzados.size());
		return chequeRechzados;
	}
	@Override
	protected ControlPaginacion actualizarOffset(ControlPaginacion controlPaginacion, List<Bean> lista) {
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) controlPaginacion;
		
		if (chequeRechazadoControlPaginacion.getPaginaActual() <= chequeRechazadoControlPaginacion.getTotalPaginas()) {
			InformacionPaginadoGenerico<Long> ultCreIce = null;
			InformacionPaginadoCreditoShiva ultCreShiva = null;
	
			InfoPaginaIceCredito infoPagIce = new InfoPaginaIceCredito();
			InfoPaginaShivaCredito infoPagShiva = new InfoPaginaShivaCredito();
	
			ListIterator<Bean> it = lista.listIterator(lista.size());
			ConsultaSoporteResultadoBusquedaChequeRechazado c = null;
			
	
			while(it.hasPrevious()) {
				c = (ConsultaSoporteResultadoBusquedaChequeRechazado) it.previous();
	
				switch (c.getSistemaOrigen()) {
				case SHIVA:
					infoPagShiva.setHayInformacionEnEstaPagina(true);
					if (Validaciones.isObjectNull(ultCreShiva)) {
						ultCreShiva = new InformacionPaginadoCreditoShiva();
						ultCreShiva.setCantidadRegistrosARetornar(new Integer(Integer.toString(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO)));
						ultCreShiva.setUltimoIdValor(c.getIdInternoValor());
						infoPagShiva.setFin(ultCreShiva);
					}
					break;
				case ICE:
					infoPagIce.setHayInformacionEnEstaPagina(true);
					if (Validaciones.isObjectNull(ultCreIce)) {
						ultCreIce = new InformacionPaginadoGenerico<Long>();
						ultCreIce.setCantidadRegistrosARetornar(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO);
						ultCreIce.setUltimoIdValor(c.getIdInternoIce());
						infoPagIce.setFin(ultCreIce);
					}
					break;
				default:
					break;
				}
			}
			if (chequeRechazadoControlPaginacion.getPaginaActual() > 1) {
				if (Validaciones.isObjectNull(ultCreShiva)) {
					int pagina = controlPaginacion.getPaginaActual();
					ultCreShiva = new InformacionPaginadoCreditoShiva();
					ultCreShiva = chequeRechazadoControlPaginacion.getChequeShivaInfoPagina().get(pagina - 1 - 1).getFin();
					infoPagShiva.setFin(ultCreShiva);
				}
				if (Validaciones.isObjectNull(ultCreIce)) {
					int pagina = controlPaginacion.getPaginaActual();
					ultCreIce = new InformacionPaginadoGenerico<Long>();
					ultCreIce = chequeRechazadoControlPaginacion.getChequeIceinfoPagina().get(pagina - 1 - 1).getFin();
					infoPagIce.setFin(ultCreIce);
				}
			}
			
			chequeRechazadoControlPaginacion.getChequeShivaInfoPagina().add(chequeRechazadoControlPaginacion.getPaginaActual() - 1, infoPagShiva);
			chequeRechazadoControlPaginacion.getChequeIceinfoPagina().add(chequeRechazadoControlPaginacion.getPaginaActual() - 1, infoPagIce);
		}
		return chequeRechazadoControlPaginacion;
	}
	@Override
	protected List<Bean> listar(ControlPaginacion controlPaginacion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro = null;
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) controlPaginacion;
		List<Bean> lista = new ArrayList<Bean>();
		NegocioExcepcion exShiva = null;
		NegocioExcepcion exHice = null;

		chequeRechazadoFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) chequeRechazadoControlPaginacion.getFiltro();
		
		try {
			if (Validaciones.isNullEmptyOrDash(chequeRechazadoFiltro.getSistema()) || SistemaEnum.SHIVA.getDescripcionCorta().equals(chequeRechazadoFiltro.getSistema())) {
				List<ConsultaSoporteResultadoBusquedaChequeRechazado> listaShiva = this.listarChequesShiva(chequeRechazadoControlPaginacion, chequeRechazadoFiltro);
				lista.addAll(listaShiva);
			}	
		} catch (NegocioExcepcion e) {
			exShiva = e;
		}

		
		try {
			if (
				(
					Validaciones.isNullEmptyOrDash(chequeRechazadoFiltro.getSistema()) ||
					SistemaEnum.ICE.getDescripcionCorta().equals(chequeRechazadoFiltro.getSistema())
				) &&
				!(
					!Validaciones.isNullEmptyOrDash(chequeRechazadoFiltro.obtenerClienteSiebelFiltro().getCriterio()) &&
					chequeRechazadoFiltro.getIdsLegadosParaIce().isEmpty()
				)
			) {
				List<ConsultaSoporteResultadoBusquedaChequeRechazado> listaICe = this.listarChequesIce(chequeRechazadoControlPaginacion, chequeRechazadoFiltro);
				lista.addAll(listaICe);
			}
		} catch (NegocioExcepcion e) {
			exHice = e;
		}

		if (!Validaciones.isObjectNull(exShiva) || !Validaciones.isObjectNull(exHice)) {
			if (!Validaciones.isObjectNull(exShiva) && !Validaciones.isNullEmptyOrDash(exShiva.getMensajeAuxiliar())) {
				validacionesAuxiliar.getResultadoValidaciones().append(exShiva.getMensajeAuxiliar());
			}
			if (!Validaciones.isObjectNull(exHice) && !Validaciones.isNullEmptyOrDash(exHice.getMensajeAuxiliar())) {
				if (validacionesAuxiliar.getResultadoValidaciones().length() > 0) {
					validacionesAuxiliar.getResultadoValidaciones().append(" - ");
				}
				validacionesAuxiliar.getResultadoValidaciones().append(exHice.getMensajeAuxiliar());
			}
			
			
		}
		return lista;
	}
	private List<ConsultaSoporteResultadoBusquedaChequeRechazado> listarChequesShiva(ControlPaginacion controlPaginacion, Filtro filtro) throws NegocioExcepcion {
		VistaSoporteBusquedaValoresFiltro vistaSoporteBusquedaValoresFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) filtro;
		List<ConsultaSoporteResultadoBusquedaChequeRechazado> lista = new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazado>();
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) controlPaginacion;
		
		if (
			Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getSistema()) ||
			SistemaEnum.SHIVA.getDescripcionCorta().equals(vistaSoporteBusquedaValoresFiltro.getSistema())
		) {
			try {
				List<VistaSoporteResultadoBusquedaValor> listaCheques = vistaSoporteServicio.buscarValores(vistaSoporteBusquedaValoresFiltro);
				//controlPaginacion.setTotalRegistros(listaCheques.size());
				chequeRechazadoControlPaginacion.setCantidadChequeShiva(listaCheques.size());
				// Se tendria que ordenar por Fecha 
				boolean idValorEncontrado = false;
				for (VistaSoporteResultadoBusquedaValor vistaSoporteResultadoBusquedaValor: listaCheques) {
					if (
						!idValorEncontrado &&
						!Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioShiva()) &&
						!Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioShiva().getUltimoIdValor())
					) {
						if (chequeRechazadoControlPaginacion.getInicioShiva().getUltimoIdValor().equals(vistaSoporteResultadoBusquedaValor.getIdValor())) {
							idValorEncontrado = true;
						}
					} else {
						idValorEncontrado = true;
						ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = new ConsultaSoporteResultadoBusquedaChequeRechazado();
						chequeRechazado.setSistemaOrigen(SistemaEnum.SHIVA);
						chequeRechazado.setTipoCheque(vistaSoporteResultadoBusquedaValor.getTipoValor());
						chequeRechazado.setIdTipoCheque(vistaSoporteResultadoBusquedaValor.getIdTipoValor());
						chequeRechazado.setCodBancoOrigen(vistaSoporteResultadoBusquedaValor.getIdBancoOrigen());
						chequeRechazado.setDescripcionBancoOrigen(vistaSoporteResultadoBusquedaValor.getDescripcionBancoOrigen());
						chequeRechazado.setNroCheque(vistaSoporteResultadoBusquedaValor.getReferenciaValor());
						chequeRechazado.setFechaDeposito(vistaSoporteResultadoBusquedaValor.getFechaDeposito());
						chequeRechazado.setFechaRecepcion(vistaSoporteResultadoBusquedaValor.getFechaIngresoRecibo() );
						chequeRechazado.setFechaVenc(vistaSoporteResultadoBusquedaValor.getFechaVencimiento());
						chequeRechazado.setMoneda(MonedaEnum.PES);
						chequeRechazado.setImporte(vistaSoporteResultadoBusquedaValor.getImporte());
						chequeRechazado.setAcuerdo(vistaSoporteResultadoBusquedaValor.getIdAcuerdo());
						chequeRechazado.setBancoDeposito(vistaSoporteResultadoBusquedaValor.getBancoDeposito());
						chequeRechazado.setCuentaDeposito(vistaSoporteResultadoBusquedaValor.getCuentaDeposito());
						chequeRechazado.setEstado(vistaSoporteResultadoBusquedaValor.getEstadoValor());
						chequeRechazado.setEmpresa(vistaSoporteResultadoBusquedaValor.getEmpresa());
						chequeRechazado.setSegmento(vistaSoporteResultadoBusquedaValor.getSegmento());
						chequeRechazado.setAnalista(vistaSoporteResultadoBusquedaValor.getIdAnalista());
						chequeRechazado.setCopropietario(vistaSoporteResultadoBusquedaValor.getIdCopropietario());
						chequeRechazado.setAnalistaTeamComercial(vistaSoporteResultadoBusquedaValor.getIdAnalistaTeamComercial());
						chequeRechazado.setIdInternoValor(vistaSoporteResultadoBusquedaValor.getIdValor());
						
						chequeRechazado.setSaldoDisponible(vistaSoporteResultadoBusquedaValor.getSaldoDisponible());

						// Clientes
						if (!Validaciones.isNullEmptyOrDash(vistaSoporteResultadoBusquedaValor.getIdClienteLegado())) {
							ClienteBean clienteSiebel = new ClienteBean();
							clienteSiebel.setIdClienteLegado(Long.parseLong(vistaSoporteResultadoBusquedaValor.getIdClienteLegado()));
							clienteSiebel.setRazonSocial(vistaSoporteResultadoBusquedaValor.getRazonSocialClienteAgrupador());
							chequeRechazado.getClienteCheques().add(clienteSiebel);
						}
						lista.add(chequeRechazado);
					}
				}
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e, "Error de Servicio: Consulta de cheques en SHIVA");
			}
		}
		//chequeRechazadoControlPaginacion.setCantidadChequeShiva(lista.size());
		return lista;
	}

	private List<ConsultaSoporteResultadoBusquedaChequeRechazado> listarChequesIce(ControlPaginacion controlPaginacion, Filtro filtro) throws NegocioExcepcion {
		ConsultaSoporteBusquedaChequeRechazadoFiltro vistaSoporteBusquedaValoresFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) filtro;
		List<ConsultaSoporteResultadoBusquedaChequeRechazado> lista = new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazado>();
		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = (ChequeRechazadoControlPaginacion) controlPaginacion;
		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();

		if (
			(
				Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getSistema()) ||
				SistemaEnum.ICE.getDescripcionCorta().equals(vistaSoporteBusquedaValoresFiltro.getSistema())
			) && (
				Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getFechaVencimiento())
			)
		) {
			if (
				!Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioIce()) &&
				!Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioIce().getUltimoId())
			) {
				entrada.setUltimoRegistroProcesado(chequeRechazadoControlPaginacion.getInicioIce().getUltimoId());
			}
			try {
				if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getIdBancos())) {
					entrada.getBancos().addAll(
						Arrays.asList(
							vistaSoporteBusquedaValoresFiltro.getIdBancos().split("-") 
					));
				}
				if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getReferenciaDelValorFiltroLike())) {
					entrada.setNroCheque(vistaSoporteBusquedaValoresFiltro.getReferenciaDelValorFiltroLike());
				}
				try {
					if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getFechaDesde())) {
						entrada.setFechaCobroDesde(Utilidad.parseDatePickerString(vistaSoporteBusquedaValoresFiltro.getFechaDesde()));
					}
					if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getFechaHasta())) {
						entrada.setFechaCobroHasta(Utilidad.parseDatePickerString(vistaSoporteBusquedaValoresFiltro.getFechaHasta()));
					}
					if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getImporteHasta())) {
						entrada.setImporteHasta(Utilidad.stringToBigDecimal(vistaSoporteBusquedaValoresFiltro.getImporteHasta()));
					}
				} catch (ParseException e) {
					throw new NegocioExcepcion("Error de conversion de datos ", e);
				}
				if (!Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getImporteDesde())) {
					entrada.setImporteDesde(Utilidad.stringToBigDecimal(vistaSoporteBusquedaValoresFiltro.getImporteDesde()));
				}
				
				if (!vistaSoporteBusquedaValoresFiltro.getIdsLegadosParaIce().isEmpty()) {
					for (String nroCliente : vistaSoporteBusquedaValoresFiltro.getIdsLegadosParaIce()) {
						entrada.getClientes().add(new Long(nroCliente));
					}
				}
				/*************************Filtro*/
//				if (
//					Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioIce()) &&
//					Validaciones.isObjectNull(chequeRechazadoControlPaginacion.getInicioIce().getUltimoId())
//				) {
//					entrada.setUltimoRegistroProcesado(chequeRechazadoControlPaginacion.getInicioIce().getUltimoId());
//				}
				entrada.setCantidadDeRegistrosARetornar(new BigInteger(Integer.toString(Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO)));
				
				SalidaIceConsultaChequesWS salida = clienteIceServicio.consultarChequesIce(entrada);
				if (
					!Validaciones.isObjectNull(salida) &&
					!Validaciones.isObjectNull(salida.getResultadoProcesamiento()) &&
					!TipoResultadoEnum.OK.name().equals(salida.getResultadoProcesamiento().getResultadoImputacion())
				) {
					if(!"0564".equalsIgnoreCase(salida.getResultadoProcesamiento().getCodigoError())) {
						Traza.error(getClass(), salida.getResultadoProcesamiento().getDescripcionError());
						throw new NegocioExcepcion(salida.getResultadoProcesamiento().getDescripcionError(), "Error de Servicio: Consulta de cheques en ICE ");
					}
				} else {
					chequeRechazadoControlPaginacion.setCantidadChequeIce(salida.getCantidadDeRegistrosTotales());
					
					if (!Validaciones.isObjectNull(salida.getListaCheques())) {
						for (IceCheques iceCheques: salida.getListaCheques()) {
							ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = new ConsultaSoporteResultadoBusquedaChequeRechazado();
							chequeRechazado.setSistemaOrigen(SistemaEnum.ICE);
							chequeRechazado.setCodBancoOrigen(iceCheques.getBancoEmisorCheque());
							chequeRechazado.setDescripcionBancoOrigen(iceCheques.getDescripcionBanco());
							chequeRechazado.setNroCheque(iceCheques.getNumeroCheque());
							chequeRechazado.setFechaDeposito(iceCheques.getFechaDeCobro());
							chequeRechazado.setMoneda(iceCheques.getMoneda());
							chequeRechazado.setImporte(iceCheques.getImporteAbsoluto());
							chequeRechazado.setCodBancoDeCobro(iceCheques.getCodigoBancoDeCobro());
							chequeRechazado.setIdInternoIce(Long.parseLong(iceCheques.getIdCheque()));
							//chequeRechazado.setEmpresa(iceCheques.get);
							//chequeRechazado.setAnalistaTeamComercial(iceCheques.getIdAnalistaTeamComercial());
							//chequeRechazado.setIdInternoValor(iceCheques.getIdValor());
							chequeRechazado.setImporteCheque(iceCheques.getImporteCheque());
							
							if (Validaciones.isCollectionNotEmpty(iceCheques.getClientes())) {
								for (ClienteCheque clienteCheque : iceCheques.getClientes()) {
									ClienteBean clienteSiebel = new ClienteBean();
									clienteSiebel.setIdClienteLegado(clienteCheque.getIdClienteLegado());
									clienteSiebel.setRazonSocial(clienteCheque.getRazonSocial());
									chequeRechazado.getClienteCheques().add(clienteSiebel);
								}
							}
							lista.add(chequeRechazado);
						}
					}
				}
			} catch(NegocioExcepcion e) {
				if (e instanceof WebServiceExcepcion) {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e, "Error de Servicio: Consulta de cheques en ICE ");
				} else {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e, "Error de Servicio: Consulta de cheques en ICE ");
				}
			}
		}
		
		return lista;
	}
}
