package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoCliente;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;


public class ContabilidadDto extends DTO {

	private static final long serialVersionUID = 1L;
	
	private String descripcionTipoOrigenContable; 
	private Date fechaProceso; 
	private ShvParamTipoMedioPago idTipoMedioPago;
	private ShvParamTipoCliente idTipoCliente; 
	private String idJurisdiccion;
	private Long codigoClienteLegado; 
	private ShvParamAcuerdo idAcuerdo;
	private String idAnalista;
	private BigDecimal importe;
	private String moneda;
	private Date fechaValor;
	private Date fechaVencimiento;
	private String transaccion;
	private Long numeroBoleta;
	private String numeroComprobante;
	private String idOrganismo;
	private String tipoDeCambio;
	private String cuit;
	private Long numeroDocumentoContable;
	private String idCaja;
	private String estado;
	private String idBanco;
	private Long idValor;
	private Long idTransaccion;
	private Long IdCobro;
	private String estadoInactividad;
	
	public String getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}
	public String getDescripcionTipoOrigenContable() {
		return descripcionTipoOrigenContable;
	}
	public void setDescripcionTipoOrigenContable(String idTipoOrigenContable) {
		this.descripcionTipoOrigenContable = idTipoOrigenContable;
	}
	public Date getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public ShvParamTipoMedioPago getIdTipoMedioPago() {
		return idTipoMedioPago;
	}
	public void setIdTipoMedioPago(ShvParamTipoMedioPago idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}
	public ShvParamTipoCliente getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(ShvParamTipoCliente idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public String getIdJurisdiccion() {
		return idJurisdiccion;
	}
	public void setIdJurisdiccion(String idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}
	public Long getCodigoClienteLegado() {
		return codigoClienteLegado;
	}
	public void setCodigoClienteLegado(Long codigoClienteLegado) {
		this.codigoClienteLegado = codigoClienteLegado;
	}
	public String getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public Long getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public String getIdOrganismo() {
		return idOrganismo;
	}
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
	public String getTipoDeCambio() {
		return tipoDeCambio;
	}
	public void setTipoDeCambio(String tipoDeCambio) {
		this.tipoDeCambio = tipoDeCambio;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Long getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}
	public void setNumeroDocumentoContable(Long numeroDocumenoContable) {
		this.numeroDocumentoContable = numeroDocumenoContable;
	}
	public String getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ShvParamAcuerdo getIdAcuerdo() {
		return idAcuerdo;
	}
	public void setIdAcuerdo(ShvParamAcuerdo idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	public Long getIdValor() {
		return idValor;
	}
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	public Long getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public Long getIdCobro() {
		return IdCobro;
	}
	public void setIdCobro(Long idCobro) {
		IdCobro = idCobro;
	}
	public String getEstadoInactividad() {
		return estadoInactividad;
	}
	public void setEstadoInactividad(String estadoInactividad) {
		this.estadoInactividad = estadoInactividad;
	}

	
	
}