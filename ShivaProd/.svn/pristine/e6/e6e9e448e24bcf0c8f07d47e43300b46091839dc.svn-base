package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.com.telecom.shiva.presentacion.bean.paginacion.InformacionPaginadoCreditoShiva;

/**
 * @author fabio.giaquinta.ruiz
 *
 */
public class VistaSoporteBusquedaValoresFiltro extends Filtro {
	private static final long serialVersionUID = -2579427009550076872L;

	private String idClienteLegado;
	private String idEstado;
	private String idValor;

	private String nroBoletaFiltro;
	private String referenciaDelValorFiltro;	
	private String referenciaDelValorFiltroLike;
	

	private String idCobroShivaFiltro;
	private List<Long> idValorDeBusqueraPorFiltroCobro = new ArrayList<Long>(); 
	
	private String analistaFiltro;

	private List<String> listaBoletas = new ArrayList<String>();
	private List<String> listaTipoValor = new ArrayList<String>();

	private boolean cobroOnlineCreditosShiva = false;
	private Set<String> idClientesLegado = new HashSet<String>();
	private Set<String> idEstados = new HashSet<String>();
	
	private InformacionPaginadoCreditoShiva informacionPaginadoCredito = new InformacionPaginadoCreditoShiva();
	
	private List<Long> idValores = new ArrayList<Long>(); 
	
	
	
	// Cheques rechazados
	private String idBanco;
	// Si no se seleciona un banco por id se debe buscar por todos los id que corresponde a la descripcion
	private String idBancos = new String();
	protected String moneda;
	protected String fechaVencimiento; // Solo para cheque Diferido
	protected String sistema;
	protected String sqlOrder;

	private String numeroDocumentoContable;

	private String acuerdo;
	
	public VistaSoporteBusquedaValoresFiltro() {
	}
	
	public VistaSoporteBusquedaValoresFiltro(String idClienteLegado, String idEstado) {
		this.idClienteLegado = idClienteLegado;
		this.idEstado = idEstado;
	}
	
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	/**
	 * @return the idClienteLegado
	 */
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	
	public String getIdValor() {
		return idValor;
	}
	
	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}
	public String getNroBoletaFiltro() {
		return nroBoletaFiltro;
	}
	public void setNroBoletaFiltro(String nroBoletaFiltro) {
		this.nroBoletaFiltro = nroBoletaFiltro;
	}
	public String getReferenciaDelValorFiltro() {
		return referenciaDelValorFiltro;
	}
	public void setReferenciaDelValorFiltro(String referenciaDelValorFiltro) {
		this.referenciaDelValorFiltro = referenciaDelValorFiltro;
	}
	public String getIdCobroShivaFiltro() {
		return idCobroShivaFiltro;
	}
	public void setIdCobroShivaFiltro(String idCobroShivaFiltro) {
		this.idCobroShivaFiltro = idCobroShivaFiltro;
	}
	public String getAnalistaFiltro() {
		return analistaFiltro;
	}
	public void setAnalistaFiltro(String analistaFiltro) {
		this.analistaFiltro = analistaFiltro;
	}
	public List<String> getListaBoletas() {
		return listaBoletas;
	}
	public void setListaBoletas(List<String> listaBoletas) {
		this.listaBoletas = listaBoletas;
	}	
	
	public List<String> getListaTipoValor() {
		return listaTipoValor;
	}

	public void setListaTipoValor(List<String> listaTipoValor) {
		this.listaTipoValor = listaTipoValor;
	}

	public Set<String> getIdClientesLegado() {
		return idClientesLegado;
	}

	public void setIdClientesLegado(Set<String> idClientesLegado) {
		this.idClientesLegado = idClientesLegado;
	}

	public Set<String> getIdEstados() {
		return idEstados;
	}

	public void setIdEstados(Set<String> idEstados) {
		this.idEstados = idEstados;
	}

	public InformacionPaginadoCreditoShiva getInformacionPaginadoCredito() {
		return informacionPaginadoCredito;
	}

	public void setInformacionPaginadoCredito(
			InformacionPaginadoCreditoShiva informacionPaginadoCredito) {
		this.informacionPaginadoCredito = informacionPaginadoCredito;
	}

	public boolean isCobroOnlineCreditosShiva() {
		return cobroOnlineCreditosShiva;
	}

	public void setCobroOnlineCreditosShiva(boolean cobroOnlineCreditosShiva) {
		this.cobroOnlineCreditosShiva = cobroOnlineCreditosShiva;
	}

	/**
	 * @return the idValorDeBusqueraPorCobro
	 */
	public List<Long> getIdValorDeBusqueraPorFiltroCobro() {
		return idValorDeBusqueraPorFiltroCobro;
	}

	/**
	 * @param idValorDeBusqueraPorCobro the idValorDeBusqueraPorCobro to set
	 */
	public void setIdValorDeBusqueraPorFiltroCobro(List<Long> idValorDeBusqueraPorCobro) {
		this.idValorDeBusqueraPorFiltroCobro = idValorDeBusqueraPorCobro;
	}

	/**
	 * @return the referenciaDelValorFiltroLike
	 */
	public String getReferenciaDelValorFiltroLike() {
		return referenciaDelValorFiltroLike;
	}

	/**
	 * @param referenciaDelValorFiltroLike the referenciaDelValorFiltroLike to set
	 */
	public void setReferenciaDelValorFiltroLike(String referenciaDelValorFiltroLike) {
		this.referenciaDelValorFiltroLike = referenciaDelValorFiltroLike;
	}
	/**
	 * @return the idBanco
	 */
	public String getIdBanco() {
		return idBanco;
	}

	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	/**
	 * Filtro de idBancos Origen con Id separados por '-'
	 * @return the idBancos
	 */
	public String getIdBancos() {
		return idBancos;
	}

	/**
	 * @param idBancos the idBancos to set
	 */
	public void setIdBancos(String idBancos) {
		this.idBancos = idBancos;
	}

	/**
	 * @return the sqlOrder
	 */
	public String getSqlOrder() {
		return sqlOrder;
	}

	/**
	 * @param sqlOrder the sqlOrder to set
	 */
	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	/**
	 * @return the numeroDocumentoContable
	 */
	public String getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}
	
	/**
	 * @param numeroDocumentoContable the numeroDocumentoContable to set
	 */
	public void setNumeroDocumentoContable(String numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}

	/**
	 * @return the idValores
	 */
	public List<Long> getIdValores() {
		return idValores;
	}

	/**
	 * @param idValores the idValores to set
	 */
	public void setIdValores(List<Long> idValores) {
		this.idValores = idValores;
	}

	/**
	 * @return the acuerdo
	 */
	public String getAcuerdo() {
		return acuerdo;
	}

	/**
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
}