package ar.com.telecom.shiva.negocio.dto;

import java.util.Date;

import ar.com.telecom.shiva.base.utils.Utilidad;

/**
 * @author u529234
 *
 */
public class DepositoDto extends RegistroAVCDto {

	private static final long serialVersionUID = 1L;
	
	// Deposito
	private Long idRecInstrumento;
	private Long rend;
	private Date fechaPago;
	private Long nroCliente;
	private String formaPago;
	private String estadoAcreditacion;
	private Date fechaAcreditacion;
	private Long nroBoleta;
	private Long sucursalDeposito;
	private String nombreSucursal;
	private String grupoAcreedor;
	private String nombreCliente;
	private String codigoRechazo;
	
	// Cheque
	private Long codigoBanco;
	private Long sucursal;
	private Long codigoPostal;
	private Long numeroCheque;
	private Long cuentaEmisora;
	private Date fechaVencimiento; //Fh Vto CPD

	private String observacion; 

	private String bancoOrigenFormateado;
	private String fechaVencimientoFormateado;
	
	public DepositoDto( ) {}
	
	public DepositoDto(String acuerdo) {
		setAcuerdo(acuerdo);
	}
	
	public Long getIdRecInstrumento() {
		return idRecInstrumento;
	}

	public void setIdRecInstrumento(Long idRecInstrumento) {
		this.idRecInstrumento = idRecInstrumento;
	}

	public Long getRend() {
		return rend;
	}

	public void setRend(Long rend) {
		this.rend = rend;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Long getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(Long nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getEstadoAcreditacion() {
		return estadoAcreditacion;
	}

	public void setEstadoAcreditacion(String estadoAcreditacion) {
		this.estadoAcreditacion = estadoAcreditacion;
	}
	
	public Date getFechaAcreditacion() {
		return fechaAcreditacion;
	}

	public void setFechaAcreditacion(Date fechaAcreditacion) {
		this.fechaAcreditacion = fechaAcreditacion;
	}

	public Long getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(Long nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public Long getSucursalDeposito() {
		return sucursalDeposito;
	}

	public void setSucursalDeposito(Long sucursalDeposito) {
		this.sucursalDeposito = sucursalDeposito;
	}
	
	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getGrupoAcreedor() {
		return grupoAcreedor;
	}

	public void setGrupoAcreedor(String grupoAcreedor) {
		this.grupoAcreedor = grupoAcreedor;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCodigoRechazo() {
		return codigoRechazo;
	}

	public void setCodigoRechazo(String codigoRechazo) {
		this.codigoRechazo = codigoRechazo;
	}

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public Long getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Long codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Long getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Long getCuentaEmisora() {
		return cuentaEmisora;
	}

	public void setCuentaEmisora(Long cuentaEmisora) {
		this.cuentaEmisora = cuentaEmisora;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
 
	public String getFechaVencimientoFormateada(){
		return Utilidad.formatDate(this.fechaVencimiento);
	}

	public String getFechaVencimientoFormateado() {
		return fechaVencimientoFormateado;
	}

	public void setFechaVencimientoFormateado(String fechaVencimientoFormateado) {
		this.fechaVencimientoFormateado = fechaVencimientoFormateado;
	}

	public String getBancoOrigenFormateado() {
		return bancoOrigenFormateado;
	}

	public void setBancoOrigenFormateado(String bancoOrigenFormateado) {
		this.bancoOrigenFormateado = bancoOrigenFormateado;
	}


}
