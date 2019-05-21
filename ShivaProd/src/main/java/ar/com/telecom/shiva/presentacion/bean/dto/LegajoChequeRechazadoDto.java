package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.negocio.workflow.definicion.EdicionTipoEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LegajoChequeRechazadoDto extends GestionDto {

	private static final long serialVersionUID = 1L;

	private Long idLegajoChequeRechazado;
	private Estado estado;
	private String estadoDescripcion;
	
	private String  idAnalista;
	private String	analista;
	private String  idCopropietario;
	private String  idAnalistaTeamComercial;
	private String  fechaNotificacion;
	private int  	motivoLegajo;
	private String	motivoLegajoDescripcion;
	private String  observaciones;
	private String	observacionesAnteriores;
	private String  ubicacion;
	private String  ubicacionDesc;
	private String  fechaRechazo;
	private String  idBancoOrigen;
	private String	bancoOrigenDescripcion;
	private String  bancoDeposito;
	private String  numeroCheque;
	private String  fechaVencimiento;
	private String  moneda;
	private String  mondeDesc;
	private String  importe;
	private String  idAcuerdo;
	private String  idbancoDeposito;
	private String 	bancoDepositoDescripcion;
	private String  cuentaDeposito;
	private String	numeroCuentaDeposito;
	private String  fechaDeposito;
	private String  idCliente;
	private String  descripcionCliente;
	private String  cuitCliente;
	
	private String  tipoCheque;
	private String  tipoChequeDescripcion;
	
	private String  sistemaOrigen;

	private String  analistaTeamComercial;
	private String  copropietario;
	
	private String  empresa;
	private String  segmento;

	private ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeRechazado = null;
	
	private EdicionTipoEnum edicionTipo;

	// Atributos de Control
	private boolean validarAnulacionInmediataDelValor = false;
	private int validarAnulacionInmediataDelValorCount = 0; 
	
	//para modificar las observaciones en la edicion de un legajo
	private Integer idWorkflow;
	
	private String idSolapaDesde;
	private String idSolapaHasta;
	
	private List<LegajoChequeRechazadoCobroRelacionadoDto> listaCobrosRelacionados = new ArrayList<LegajoChequeRechazadoCobroRelacionadoDto>();
	private List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaDetalleDocumentos = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>();
	private List<LegajoChequeRechazadoNotificacionDto> listaNotificaciones = new ArrayList<LegajoChequeRechazadoNotificacionDto>();
	
	private List<ComprobanteDto> listaComprobantes;
	
	private String fechaEmision;

	// Se usa para la verificar si el Cobro esta revertido
	private Long idOperacion;
	
	// Totales Solo para exportacion
	private String montoARevertir;
	private String montoAReenvolsar;
	
	
	

	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public String getIdCopropietario() {
		return idCopropietario;
	}
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}
	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}
	public String getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public int getMotivoLegajo() {
		return motivoLegajo;
	}
	public void setMotivoLegajo(int motivoLegajo) {
		this.motivoLegajo = motivoLegajo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getFechaRechazo() {
		return fechaRechazo;
	}
	public void setFechaRechazo(String fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getIdAcuerdo() {
		return idAcuerdo;
	}
	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	public String getIdbancoDeposito() {
		return idbancoDeposito;
	}
	public void setIdbancoDeposito(String idbancoDeposito) {
		this.idbancoDeposito = idbancoDeposito;
	}
	public String getCuentaDeposito() {
		return cuentaDeposito;
	}
	public void setCuentaDeposito(String cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}
	public String getFechaDeposito() {
		return fechaDeposito;
	}
	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getDescripcionCliente() {
		return descripcionCliente;
	}
	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}
	public String getTipoCheque() {
		return tipoCheque;
	}
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}
	/**
	 * @return the chequeRechazado
	 */
	public ConsultaSoporteResultadoBusquedaChequeRechazadoDto getChequeRechazado() {
		return chequeRechazado;
	}
	/**
	 * @param chequeRechazado the chequeRechazado to set
	 */
	public void setChequeRechazado(
			ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeRechazado) {
		this.chequeRechazado = chequeRechazado;
	}
	/**
	 * @return the validarAnulacionInmediataDelValor
	 */
	public boolean getValidarAnulacionInmediataDelValor() {
		return validarAnulacionInmediataDelValor;
	}
	/**
	 * @param validarAnulacionInmediataDelValor the validarAnulacionInmediataDelValor to set
	 */
	public void setValidarAnulacionInmediataDelValor(
			boolean validarAnulacionInmediataDelValor) {
		this.validarAnulacionInmediataDelValor = validarAnulacionInmediataDelValor;
	}
	public String getSistemaOrigen() {
		return sistemaOrigen;
	}
	public void setSistemaOrigen(String sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	/**
	 * @return the idLegajoChequeRechazado
	 */
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}
	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	/**
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}
	/**
	 * @param estadoDescripcion the estadoDescripcion to set
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}
	/**
	 * @return the validarAnulacionInmediataDelValorCount
	 */
	public int getValidarAnulacionInmediataDelValorCount() {
		return validarAnulacionInmediataDelValorCount;
	}
	/**
	 * @param validarAnulacionInmediataDelValorCount the validarAnulacionInmediataDelValorCount to set
	 */
	public void setValidarAnulacionInmediataDelValorCount(
			int validarAnulacionInmediataDelValorCount) {
		this.validarAnulacionInmediataDelValorCount = validarAnulacionInmediataDelValorCount;
	}
	/**
	 * @return the bancoDeposito
	 */
	public String getBancoDeposito() {
		return bancoDeposito;
	}
	/**
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	/**
	 * @return the analistaTeamComercial
	 */
	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}
	/**
	 * @param analistaTeamComercial the analistaTeamComercial to set
	 */
	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}
	/**
	 * @return the sopropietario
	 */
	public String getCopropietario() {
		return copropietario;
	}
	/**
	 * @param sopropietario the sopropietario to set
	 */
	public void setCopropietario(String sopropietario) {
		this.copropietario = sopropietario;
	}
	/**
	 * @return the ubicacionDesc
	 */
	public String getUbicacionDesc() {
		return ubicacionDesc;
	}
	/**
	 * @param ubicacionDesc the ubicacionDesc to set
	 */
	public void setUbicacionDesc(String ubicacionDesc) {
		this.ubicacionDesc = ubicacionDesc;
	}
	/**
	 * @return the mondeDesc
	 */
	public String getMondeDesc() {
		return mondeDesc;
	}
	/**
	 * @param mondeDesc the mondeDesc to set
	 */
	public void setMondeDesc(String mondeDesc) {
		this.mondeDesc = mondeDesc;
	}
	public String getIdBancoOrigen() {
		return idBancoOrigen;
	}
	public void setIdBancoOrigen(String idBancoOrigen) {
		this.idBancoOrigen = idBancoOrigen;
	}
	public String getBancoOrigenDescripcion() {
		return bancoOrigenDescripcion;
	}
	public void setBancoOrigenDescripcion(String bancoOrigenDescripcion) {
		this.bancoOrigenDescripcion = bancoOrigenDescripcion;
	}
	public String getBancoDepositoDescripcion() {
		return bancoDepositoDescripcion;
	}
	public void setBancoDepositoDescripcion(String bancoDepositoDescripcion) {
		this.bancoDepositoDescripcion = bancoDepositoDescripcion;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getMotivoLegajoDescripcion() {
		return motivoLegajoDescripcion;
	}
	public void setMotivoLegajoDescripcion(String motivoLegajoDescripcion) {
		this.motivoLegajoDescripcion = motivoLegajoDescripcion;
	}
	public String getTipoChequeDescripcion() {
		return tipoChequeDescripcion;
	}
	public void setTipoChequeDescripcion(String tipoChequeDescripcion) {
		this.tipoChequeDescripcion = tipoChequeDescripcion;
	}
	/**
	 * @return the edicionTipo
	 */
	public EdicionTipoEnum getEdicionTipo() {
		return edicionTipo;
	}
	/**
	 * @param edicionTipo the edicionTipo to set
	 */
	public void setEdicionTipo(EdicionTipoEnum edicionTipo) {
		this.edicionTipo = edicionTipo;
	}
	/**
	 * @return the observacionesAnteriores
	 */
	public String getObservacionesAnteriores() {
		return observacionesAnteriores;
	}
	/**
	 * @param observacionesAnteriores the observacionesAnteriores to set
	 */
	public void setObservacionesAnteriores(String observacionesAnteriores) {
		this.observacionesAnteriores = observacionesAnteriores;
	}
	public String getNumeroCuentaDeposito() {
		return numeroCuentaDeposito;
	}
	public void setNumeroCuentaDeposito(String numeroCuentaDeposito) {
		this.numeroCuentaDeposito = numeroCuentaDeposito;
	}
	/**
	 * @return the cuitCliente
	 */
	public String getCuitCliente() {
		return cuitCliente;
	}
	/**
	 * @param cuitCliente the cuitCliente to set
	 */
	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}
	public Integer getIdWorkflow() {
		return idWorkflow;
	}
	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
	public String getIdSolapaDesde() {
		return idSolapaDesde;
	}
	public void setIdSolapaDesde(String idSolapaDesde) {
		this.idSolapaDesde = idSolapaDesde;
	}
	public String getIdSolapaHasta() {
		return idSolapaHasta;
	}
	public void setIdSolapaHasta(String idSolapaHasta) {
		this.idSolapaHasta = idSolapaHasta;
	}
	public List<LegajoChequeRechazadoCobroRelacionadoDto> getListaCobrosRelacionados() {
		return listaCobrosRelacionados;
	}
	public void setListaCobrosRelacionados(
			List<LegajoChequeRechazadoCobroRelacionadoDto> listaCobrosRelacionados) {
		this.listaCobrosRelacionados = listaCobrosRelacionados;
	}
	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}
	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	/**
	 * @return the listaComprobantes
	 */
	public List<ComprobanteDto> getListaComprobantes() {
		return listaComprobantes;
	}
	/**
	 * @param listaComprobantes the listaComprobantes to set
	 */
	public void setListaComprobantes(List<ComprobanteDto> listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}
	/**
	 * @return the listaDetalleDocumentos
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> getListaDetalleDocumentos() {
		return listaDetalleDocumentos;
	}
	/**
	 * @param listaDetalleDocumentos the listaDetalleDocumentos to set
	 */
	public void setListaDetalleDocumentos(
			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaDetalleDocumentos) {
		this.listaDetalleDocumentos = listaDetalleDocumentos;
	}
	/**
	 * @return the listaNotificaciones
	 */
	public List<LegajoChequeRechazadoNotificacionDto> getListaNotificaciones() {
		return listaNotificaciones;
	}
	/**
	 * @param listaNotificaciones the listaNotificaciones to set
	 */
	public void setListaNotificaciones(
			List<LegajoChequeRechazadoNotificacionDto> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the montoARevertir
	 */
	public String getMontoARevertir() {
		return montoARevertir;
	}
	/**
	 * @param montoARevertir the montoARevertir to set
	 */
	public void setMontoARevertir(String montoARevertir) {
		this.montoARevertir = montoARevertir;
	}
	/**
	 * @return the montoAReenvolsar
	 */
	public String getMontoAReenvolsar() {
		return montoAReenvolsar;
	}
	/**
	 * @param montoAReenvolsar the montoAReenvolsar to set
	 */
	public void setMontoAReenvolsar(String montoAReenvolsar) {
		this.montoAReenvolsar = montoAReenvolsar;
	}
	
}
