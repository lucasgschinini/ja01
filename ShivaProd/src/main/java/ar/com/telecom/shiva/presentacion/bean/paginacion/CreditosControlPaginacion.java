package ar.com.telecom.shiva.presentacion.bean.paginacion;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoCredito;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoCredito;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaCalipsoCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaMicCredito;
import ar.com.telecom.shiva.presentacion.bean.paginacion.info.InfoPaginaShivaCredito;

public class CreditosControlPaginacion extends ControlPaginacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ConfCobroCreditoFiltro filtro;
	
	private Map<Integer, InfoPaginaCalipsoCredito> infoPaginaCalipso;
	
	private Map<Integer, InfoPaginaMicCredito> infoPaginaMic;
	
	private Map<Integer, InfoPaginaShivaCredito> infoPaginaShiva;
	
	private int totalCalipso = 0;
	
	private int totalMic = 0;
	
	private int totalShiva = 0;
	
	private InformacionParaPaginadoCredito inicioCalipso;
	
	private MicInformacionPaginadoCredito inicioMic;
	
	private InformacionPaginadoCreditoShiva inicioShiva;
	
	
	public CreditosControlPaginacion() {
		super();
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_CRED;
		
		this.infoPaginaCalipso = new HashMap<Integer, InfoPaginaCalipsoCredito>();
		this.infoPaginaMic = new HashMap<Integer, InfoPaginaMicCredito>();
		this.infoPaginaShiva = new HashMap<Integer, InfoPaginaShivaCredito>();
		
		this.inicioCalipso = reiniciarInformacionPaginadoCalipso();
		this.inicioMic = reiniciarInformacionPaginadoMIC();
		this.inicioShiva = reiniciarInformacionPaginadoSHIVA();
		
		this.filtro = new ConfCobroCreditoFiltro();
	}
	
	public CreditosControlPaginacion(ConfCobroCreditoFiltro filtro) {
		super();
		
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_CRED;
		
		this.infoPaginaCalipso = new HashMap<Integer, InfoPaginaCalipsoCredito>();
		this.infoPaginaMic = new HashMap<Integer, InfoPaginaMicCredito>();
		this.infoPaginaShiva = new HashMap<Integer, InfoPaginaShivaCredito>();
		
		this.inicioCalipso = reiniciarInformacionPaginadoCalipso();
		this.inicioMic = reiniciarInformacionPaginadoMIC();
		this.inicioShiva = reiniciarInformacionPaginadoSHIVA();
		
		this.filtro = filtro;
	}
	
	/**
	 * Limpia los paginados
	 * @param filtro
	 */
	public void limpiarPaginado(ConfCobroCreditoFiltro filtro) {
		this.paginaActual = 1;
		this.totalPaginas = 0;
		this.cantPorPagina = ConstantesCobro.CANTIDAD_POR_PAGINA_CRED;
		this.totalRegistros = 0;
		this.cantRegistrosMostrados = 0;
		this.filtro = filtro;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public InformacionParaPaginadoCredito reiniciarInformacionPaginadoCalipso() {
		InformacionParaPaginadoCredito infoPagCalipso = new InformacionParaPaginadoCredito();
		infoPagCalipso.setCantidadDeRegistrosARetornar(new BigInteger(String.valueOf(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED)));
		return infoPagCalipso;
	}
	
	/**
	 * 
	 * @return
	 */
	public MicInformacionPaginadoCredito reiniciarInformacionPaginadoMIC() {
		MicInformacionPaginadoCredito infoPagMic = new MicInformacionPaginadoCredito();
		infoPagMic.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		return infoPagMic;
	}
	
	/**
	 * 
	 * @return
	 */
	public InformacionPaginadoCreditoShiva reiniciarInformacionPaginadoSHIVA() {
		InformacionPaginadoCreditoShiva infoPagShiva = new InformacionPaginadoCreditoShiva();
		infoPagShiva.setCantidadRegistrosARetornar(ConstantesCobro.CANTIDAD_POR_PAGINA_CRED);
		return infoPagShiva;
	}
	
	/**
	 * Para filtrar por calipso
	 * @return
	 */
	public EntradaCalipsoCreditoWS obtenerEntradaCalipso() {
		EntradaCalipsoCreditoWS eEntrada = new EntradaCalipsoCreditoWS();
		try {

			String[] idsClientes = this.filtro.getIdsClientes().trim().split(",");
			Cliente cliente = null;
			for (String idCliente : idsClientes) {
				// En este punto siempre almenos un cliente es de menos o igual 10 caracteres
				if (idCliente.trim().length() <= 10) {
					cliente = new Cliente();
					cliente.setIdClienteLegado(new BigInteger(idCliente));
					eEntrada.getListaClientes().add(cliente);
				}
			}

			if (Validaciones.isNullOrEmpty(this.filtro.getMoneda())) {
				eEntrada.setMoneda(MonedaEnum.TOD);
			} else {
				eEntrada.setMoneda(MonedaEnum.getEnumBySigla(this.filtro.getMoneda()));
			}

			if (!Validaciones.isNullOrEmpty(this.filtro.getTipoMedioPago())
					&& (TipoMedioPagoEnum.CRE.name().equals(this.filtro.getTipoMedioPago())
							|| TipoMedioPagoEnum.CTA.name().equals(this.filtro.getTipoMedioPago()))) {
				eEntrada.setTipoMedioPago(TipoMedioPagoEnum.getEnumByName(this.filtro.getTipoMedioPago()));
			} else {
				eEntrada.setTipoMedioPago(TipoMedioPagoEnum.TODOS);
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getAcuerdoFac())) {
					eEntrada.setAcuerdo(new BigInteger(this.filtro.getAcuerdoFac()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getCredDesde())) {
				eEntrada.setFechaDesde(Utilidad.parseDatePickerString(this.filtro.getCredDesde().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getCredHasta())) {
				eEntrada.setFechaHasta(Utilidad.parseDatePickerString(this.filtro.getCredHasta().trim()));
			}

		} catch (ParseException e) {
			Traza.error(getClass(), "Error al parsear fecha", e);
		}
		
		return eEntrada;
	}
	
	/**
	 * Para filtrar por MIC
	 * @return
	 */
	public MicConsultaCreditoEntrada obtenerEntradaMic() {
		MicConsultaCreditoEntrada eEntrada = new MicConsultaCreditoEntrada();
		try {

			String[] idsClientes = this.filtro.getIdsClientes().trim().split(",");
			for (String idCliente : idsClientes) {
				if (idCliente.trim().length() <= 10) {
					eEntrada.getListaClientes().add(new BigInteger(idCliente));
				}
			}

			if (!Validaciones.isNullOrEmpty(this.filtro.getTipoMedioPago())
					&& (TipoMedioPagoEnum.CRE.name().equals(this.filtro.getTipoMedioPago())
							|| TipoMedioPagoEnum.REM.name().equals(this.filtro.getTipoMedioPago()))) {
				eEntrada.setTipoMedioPago(TipoMedioPagoEnum.getEnumByName(this.filtro.getTipoMedioPago()));
			} else {
				eEntrada.setTipoMedioPago(TipoMedioPagoEnum.TODOS);
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getAcuerdoFac())) {
					eEntrada.setAcuerdo(new BigInteger(this.filtro.getAcuerdoFac()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getCredDesde())) {
				eEntrada.setFechaDesde(Utilidad.parseDatePickerString(this.filtro.getCredDesde().trim()));
			}
			if (!Validaciones.isNullOrEmpty(this.filtro.getCredHasta())) {
				eEntrada.setFechaHasta(Utilidad.parseDatePickerString(this.filtro.getCredHasta().trim()));
			}
			
		} catch (ParseException e) {
			Traza.error(getClass(), "Error al parsear fecha", e);
		}
		return eEntrada;
	}

	/**
	 * Preparo los datos de filtro para buscar valores shiva
	 * @return
	 */
	public VistaSoporteBusquedaValoresFiltro obtenerEntradaShiva() {
		VistaSoporteBusquedaValoresFiltro eEntrada = new VistaSoporteBusquedaValoresFiltro();

		//Clientes legado
		String[] idsClientes = this.filtro.getIdsClientes().trim().split(",");
		for (String idCliente : idsClientes) {
			if (!Validaciones.isNullOrEmpty(idCliente)) {
				String idClienteAux = Long.valueOf(idCliente).toString();
				eEntrada.getIdClientesLegado().add(idClienteAux);
			}
		}
		
		//Nros de valores SHIVA
		if (!Validaciones.isNullOrEmpty(this.filtro.getTipoMedioPago())) {
			String tipoMedioPago = TipoCreditoEnum.getEnumByValor(this.filtro.getTipoMedioPago()).grupoIdTipoValores();
			if(!Validaciones.isNullOrEmpty(tipoMedioPago)){
				List<String> listaTipoValor = new ArrayList<>();
				if(tipoMedioPago.contains(",")){
					String[] listaTipoMedioPago = tipoMedioPago.split(",");
					listaTipoValor.add(listaTipoMedioPago[0]);
					listaTipoValor.add(listaTipoMedioPago[1]);
				} else {
					listaTipoValor.add(tipoMedioPago);
				}			 
				eEntrada.setListaTipoValor(listaTipoValor);
			}
		}
		
		//FECHA_BUSQUEDA en la vista
		//	Interdepósito --> FECHA_DEPOSITO
		//	Transferencia -->FECHA_TRANSFERENCIA
		//	Cheque --> FECHA_DEPOSITO
		//	Cheque Diferido --> FECHA_VENCIMIENTO
		//	Depósito Efectivo --> FECHA_DEPOSITO
		//	Retención --> FECHA_EMISION
		if (!Validaciones.isNullOrEmpty(this.filtro.getCredDesde())) {
			eEntrada.setFechaDesde(this.filtro.getCredDesde().trim());
		}
		if (!Validaciones.isNullOrEmpty(this.filtro.getCredHasta())) {
			eEntrada.setFechaHasta(this.filtro.getCredHasta().trim());
		}
		
		//Estados de Valores
		eEntrada.getIdEstados().add(Estado.VAL_NO_DISPONIBLE.name());
		eEntrada.getIdEstados().add(Estado.VAL_AVISO_PAGO_RECHAZADO.name());
		eEntrada.getIdEstados().add(Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR.name());
		eEntrada.getIdEstados().add(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name());
		eEntrada.getIdEstados().add(Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.name());
		eEntrada.getIdEstados().add(Estado.VAL_BOLETA_RECHAZADA.name());
		eEntrada.getIdEstados().add(Estado.VAL_DISPONIBLE.name());
		eEntrada.getIdEstados().add(Estado.VAL_SUSPENDIDO.name());
		
		return eEntrada;
	}
	
	
	/**
	 * 
	 * @param accion
	 * @param entradaCalipso
	 * @param entradaMic
	 * @param entradaShiva
	 */
	public void setPuntoDeInicio(String accion, EntradaCalipsoCreditoWS entradaCalipso, 
			MicConsultaCreditoEntrada entradaMic, VistaSoporteBusquedaValoresFiltro entradaShiva) 
	{
		entradaCalipso.setInformacionParaPaginado(this.inicioCalipso);
		entradaMic.setInformacionPaginado(this.inicioMic);
		entradaShiva.setInformacionPaginadoCredito(this.inicioShiva);
		
		if (ConstantesCobro.PAGINADO_ANTERIOR.equals(accion)) {
			this.paginaActual--;
			if (this.paginaActual > 1) {
				entradaCalipso.setInformacionParaPaginado(this.infoPaginaCalipso.get(this.paginaActual - 1).getFin());
				entradaMic.setInformacionPaginado(this.infoPaginaMic.get(this.paginaActual - 1).getFin());
				entradaShiva.setInformacionPaginadoCredito(this.infoPaginaShiva.get(this.paginaActual - 1).getFin());
			} 
		} else {
			if (ConstantesCobro.PAGINADO_SIGUIENTE.equals(accion)) {
				entradaCalipso.setInformacionParaPaginado(this.infoPaginaCalipso.get(this.paginaActual).getFin());
				entradaMic.setInformacionPaginado(this.infoPaginaMic.get(this.paginaActual).getFin());
				entradaShiva.setInformacionPaginadoCredito(this.infoPaginaShiva.get(this.paginaActual).getFin());
				if (paginaActual < this.totalPaginas) {
					this.paginaActual++;
				}
			}
		}
		List<Integer> keyList = new ArrayList<Integer>();
		keyList.addAll(this.infoPaginaCalipso.keySet());
		Collections.sort(keyList);
		for (Integer pag : keyList) {
			if (this.paginaActual < pag) {
				this.infoPaginaCalipso.remove(pag);
			}
		}
		keyList = new ArrayList<Integer>();
		keyList.addAll(this.infoPaginaMic.keySet());
		Collections.sort(keyList);
		for (Integer pag : keyList) {
			if (this.paginaActual < pag) {
				this.infoPaginaMic.remove(pag);
			}
		}
		keyList = new ArrayList<Integer>();
		keyList.addAll(this.infoPaginaShiva.keySet());
		Collections.sort(keyList);
		for (Integer pag : keyList) {
			if (this.paginaActual < pag) {
				this.infoPaginaShiva.remove(pag);
			}
		}
		
	}
	
	/**
	 * 
	 */
	public CreditosControlPaginacion clone() {
		CreditosControlPaginacion clone = new CreditosControlPaginacion();
		clone.setFiltro(this.filtro.clone());
		clone.setPaginaActual(this.paginaActual);
		clone.setTotalPaginas(this.totalPaginas);
		clone.setCantPorPagina(this.cantPorPagina);
		clone.setTotalRegistros(this.totalRegistros);
		clone.setCantRegistrosMostrados(this.cantRegistrosMostrados);
		clone.setInhabilitarAnt(this.inhabilitarAnt);
		clone.setInhabilitarSig(this.inhabilitarSig);
		return clone;
	}

	
	/*****************************************************************
	 * Getters & Setters
	 */
	public ConfCobroCreditoFiltro getFiltro() {
		return filtro;
	}

	public void setFiltro(ConfCobroCreditoFiltro filtro) {
		this.filtro = filtro;
	}

	public Map<Integer, InfoPaginaCalipsoCredito> getInfoPaginaCalipso() {
		return infoPaginaCalipso;
	}

	public void setInfoPaginaCalipso(
			Map<Integer, InfoPaginaCalipsoCredito> infoPaginaCalipso) {
		this.infoPaginaCalipso = infoPaginaCalipso;
	}

	public Map<Integer, InfoPaginaMicCredito> getInfoPaginaMic() {
		return infoPaginaMic;
	}

	public void setInfoPaginaMic(Map<Integer, InfoPaginaMicCredito> infoPaginaMic) {
		this.infoPaginaMic = infoPaginaMic;
	}

	public int getTotalCalipso() {
		return totalCalipso;
	}

	public void setTotalCalipso(int totalCalipso) {
		this.totalCalipso = totalCalipso;
	}

	public int getTotalMic() {
		return totalMic;
	}

	public void setTotalMic(int totalMic) {
		this.totalMic = totalMic;
	}

	public InformacionParaPaginadoCredito getInicioCalipso() {
		return inicioCalipso;
	}

	public void setInicioCalipso(InformacionParaPaginadoCredito inicioCalipso) {
		this.inicioCalipso = inicioCalipso;
	}

	public MicInformacionPaginadoCredito getInicioMic() {
		return inicioMic;
	}

	public void setInicioMic(MicInformacionPaginadoCredito inicioMic) {
		this.inicioMic = inicioMic;
	}

	public int getTotalShiva() {
		return totalShiva;
	}

	public void setTotalShiva(int totalShiva) {
		this.totalShiva = totalShiva;
	}

	public Map<Integer, InfoPaginaShivaCredito> getInfoPaginaShiva() {
		return infoPaginaShiva;
	}

	public void setInfoPaginaShiva(
			Map<Integer, InfoPaginaShivaCredito> infoPaginaShiva) {
		this.infoPaginaShiva = infoPaginaShiva;
	}

	public InformacionPaginadoCreditoShiva getInicioShiva() {
		return inicioShiva;
	}

	public void setInicioShiva(InformacionPaginadoCreditoShiva inicioShiva) {
		this.inicioShiva = inicioShiva;
	}	
}