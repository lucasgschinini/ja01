package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

public class VistaSoporteLegajoChequeRechazadoFiltro extends Filtro{

	private static final long serialVersionUID = 1L;
	
	protected String selectCliente;
	protected String textCliente;
	
	private String idClienteLegado;
	private String estado;
	private Long idLegajo;
	private String numeroCheque;
	private String analista;
	private String fechaVencimiento;
	private String codigoBanco;
	private String descripcionBanco;

	private Set<String> idClientesLegado = new HashSet<String>();
	private Set<String> codigosBancos = new HashSet<String>();
	private Set<Estado> estados = new HashSet<Estado>();
	private List<Estado> notInEstados = new ArrayList<Estado>();
	private Set<Long> idValores = new HashSet<Long>();
	
	private String fechaAltaLegajoDesde;
	private String fechaAltaLegajoHasta;
	private String fechaAltaChequeDesde;
	private String fechaAltaChequeHasta;
	
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Long getIdLegajo() {
		return idLegajo;
	}
	
	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}
	
	public String getNumeroCheque() {
		return numeroCheque;
	}
	
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	
	public String getAnalista() {
		return analista;
	}
	
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	/**
	 * @return the selectCliente
	 */
	public String getSelectCliente() {
		return selectCliente;
	}

	/**
	 * @param selectCliente the selectCliente to set
	 */
	public void setSelectCliente(String selectCliente) {
		this.selectCliente = selectCliente;
	}

	/**
	 * @return the textCliente
	 */
	public String getTextCliente() {
		return textCliente;
	}

	/**
	 * @param textCliente the textCliente to set
	 */
	public void setTextCliente(String textCliente) {
		this.textCliente = textCliente;
	}

	public ClienteFiltro obtenerClienteSiebelFiltro() {
		ClienteFiltro clienteSiebelFiltro = new ClienteFiltro();
		clienteSiebelFiltro.setCriterio(this.getSelectCliente());
		clienteSiebelFiltro.setBusqueda(this.getTextCliente());
		return clienteSiebelFiltro;
	}

	/**
	 * @return the idClientesLegado
	 */
	public Set<String> getIdClientesLegado() {
		return idClientesLegado;
	}

	/**
	 * @param idClientesLegado the idClientesLegado to set
	 */
	public void setIdClientesLegado(Set<String> idClientesLegado) {
		this.idClientesLegado = idClientesLegado;
	}
	
	public String getFechaAltaLegajoDesde() {
		return fechaAltaLegajoDesde;
	}

	public void setFechaAltaLegajoDesde(String fechaAltaLegajoDesde) {
		this.fechaAltaLegajoDesde = fechaAltaLegajoDesde;
	}

	public String getFechaAltaLegajoHasta() {
		return fechaAltaLegajoHasta;
	}

	public void setFechaAltaLegajoHasta(String fechaAltaLegajoHasta) {
		this.fechaAltaLegajoHasta = fechaAltaLegajoHasta;
	}

	public String getFechaAltaChequeDesde() {
		return fechaAltaChequeDesde;
	}

	public void setFechaAltaChequeDesde(String fechaAltaChequeDesde) {
		this.fechaAltaChequeDesde = fechaAltaChequeDesde;
	}

	public String getFechaAltaChequeHasta() {
		return fechaAltaChequeHasta;
	}

	public void setFechaAltaChequeHasta(String fechaAltaChequeHasta) {
		this.fechaAltaChequeHasta = fechaAltaChequeHasta;
	}

	public Set<String> getCodigosBancos() {
		return codigosBancos;
	}

	public void setCodigosBancos(Set<String> codigosBancos) {
		this.codigosBancos = codigosBancos;
	}

	/**
	 * @return the estados
	 */
	public Set<Estado> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(Set<Estado> estados) {
		this.estados = estados;
	}

	/**
	 * @return the notInEstados
	 */
	public List<Estado> getNotInEstados() {
		return notInEstados;
	}

	/**
	 * @param notInEstados the notInEstados to set
	 */
	public void setNotInEstados(List<Estado> notInEstados) {
		this.notInEstados = notInEstados;
	}

	/**
	 * @return the idValores
	 */
	public Set<Long> getIdValores() {
		return idValores;
	}

	/**
	 * @param idValores the idValores to set
	 */
	public void setIdValores(Set<Long> idValores) {
		this.idValores = idValores;
	}
}
