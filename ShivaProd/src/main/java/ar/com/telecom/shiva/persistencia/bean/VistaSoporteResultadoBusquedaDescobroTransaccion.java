package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

public class VistaSoporteResultadoBusquedaDescobroTransaccion extends VistaSoporteResultadoBusqueda {

	
	private String numeroTransaccionFormateado;
	private EstadoTransaccionEnum estadoTransaccion;
	private SistemaEnum sistemaOrigenDocumento;
	private OrigenDocumentoEnum origenDocumento;
	private TipoComprobanteEnum	tipoComprobante;
	private String subTipoDocumento;
	private String descripcionSubTipoDocumento;
	private String numeroDocumento;
	private String numeroReferenciaMic;
	private Date fechaVencimiento;
	private Date fechaSegundoVencimiento;
	private MonedaEnum moneda;
	private Date fechaCobro;
	private BigDecimal importe;
	private BigDecimal tipoDeCambioFechaCobro;
	private BigDecimal tipoDeCambioFechaEmision;
	private BigDecimal importeAplicadoFechaEmisionMonedaOrigen;
	private SistemaEnum sistemaMedioPago;
	private SociedadEnum sociedad;
	private TipoMedioPagoEnum tipoMedioPago;
	private TipoRetencionEnum subTipoMedioPago;
	private TipoRemanenteEnum subTipoMedioPagoRemanente;
	private String referenciaMedioPago;
	private Date fechaMedioPago;
	private MonedaEnum monedaMedioPago;
	private BigDecimal importeMedioPago;
	private BigDecimal tipoDeCambioFechaCobroMedioPago;
	private BigDecimal tipoDeCambioFechaEmisionMedioPago;
	private BigDecimal importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	private BigDecimal cobradorInteresesTrasladados;
	private TratamientoInteresesEnum queHacerConIntereses;
	private BigDecimal intereses;
	private SiNoEnum cobrarSegundoVencimiento;
	private SiNoEnum trasladarIntereses;
	private Integer porcentajeABonificar;
	private BigDecimal importeABonificar;
	private SistemaEnum sistemaAcuerdo;
	private Long acuerdoDestinoCargos;
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoDestinoCargos;
	private Long idClienteLegadoAcuerdoTrasladoCargo;
	private EstadoCargoGeneradoEnum estadoCargoGenerado;
	private SistemaEnum sistemaAcuerdoContracargo;
	private Long acuerdoContracargo;
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo;
	private Long idClienteAcuerdoContracargo;
	private String mensajeTransaccion;
	private String mensajeDebito;
	private String mensajeCredito;

	private TipoMensajeEstadoEnum tipoMensajeTransaccion;
	private TipoMensajeEstadoEnum tipoMensajeDebito;
	private TipoMensajeEstadoEnum tipoMensajeCredito;

	private EstadoFacturaMedioPagoEnum estadoDebito;
	private EstadoFacturaMedioPagoEnum estadoCredito;
	private Long idDescobro;
	private Long idOperacion;
	private Long idTransaccion;
	private Long numeroTransaccion;
	private Long numeroTransaccionOriginal;
	private String grupo;
	private Long idFactura;
	private Long idMedioPago;
	private Long idTratamientoDiferencia;
	
	private Long idTransaccionPadre;
	private Long numeroTransaccionPadre;
	private Long numeroTransaccionPadreFicticio;
	private String numeroTransaccionFicticioFormateado;
	private Long numeroTransaccionFicticio;
	
	/**
	 * @return the numeroTransaccionFicticioFormateado
	 */
	public String getNumeroTransaccionFicticioFormateado() {
		return numeroTransaccionFicticioFormateado;
	}

	/**
	 * @param numeroTransaccionFicticioFormateado the numeroTransaccionFicticioFormateado to set
	 */
	public void setNumeroTransaccionFicticioFormateado(String numeroTransaccionFicticioFormateado) {
		this.numeroTransaccionFicticioFormateado = numeroTransaccionFicticioFormateado;
	}

	/**
	 * @return the numeroTransaccionFicticio
	 */
	public Long getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}

	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(Long numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}

	/**
	 * @return the numeroTransaccionFormateado
	 */
	public String getNumeroTransaccionFormateado() {
		return numeroTransaccionFormateado;
	}

	/**
	 * @param numeroTransaccionFormateado the numeroTransaccionFormateado to set
	 */
	public void setNumeroTransaccionFormateado(String numeroTransaccionFormateado) {
		this.numeroTransaccionFormateado = numeroTransaccionFormateado;
	}

	/**
	 * @return the estadoTransaccion
	 */
	public EstadoTransaccionEnum getEstadoTransaccion() {
		return estadoTransaccion;
	}

	/**
	 * @param estadoTransaccion the estadoTransaccion to set
	 */
	public void setEstadoTransaccion(EstadoTransaccionEnum estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	/**
	 * @return the sistemaOrigenDocumento
	 */
	public SistemaEnum getSistemaOrigenDocumento() {
		return sistemaOrigenDocumento;
	}

	/**
	 * @param sistemaOrigenDocumento the sistemaOrigenDocumento to set
	 */
	public void setSistemaOrigenDocumento(SistemaEnum sistemaOrigenDocumento) {
		this.sistemaOrigenDocumento = sistemaOrigenDocumento;
	}

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	/**
	 * @return the subTipoDocumento
	 */
	public String getSubTipoDocumento() {
		return subTipoDocumento;
	}

	/**
	 * @param subTipoDocumento the subTipoDocumento to set
	 */
	public void setSubTipoDocumento(String subTipoDocumento) {
		this.subTipoDocumento = subTipoDocumento;
	}

	/**
	 * @return the descripcionSubTipoDocumento
	 */
	public String getDescripcionSubTipoDocumento() {
		return descripcionSubTipoDocumento;
	}

	/**
	 * @param descripcionSubTipoDocumento the descripcionSubTipoDocumento to set
	 */
	public void setDescripcionSubTipoDocumento(String descripcionSubTipoDocumento) {
		this.descripcionSubTipoDocumento = descripcionSubTipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the fechaCobro
	 */
	public Date getFechaCobro() {
		return fechaCobro;
	}

	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the tipoDeCambioFechaCobro
	 */
	public BigDecimal getTipoDeCambioFechaCobro() {
		return tipoDeCambioFechaCobro;
	}

	/**
	 * @param tipoDeCambioFechaCobro the tipoDeCambioFechaCobro to set
	 */
	public void setTipoDeCambioFechaCobro(BigDecimal tipoDeCambioFechaCobro) {
		this.tipoDeCambioFechaCobro = tipoDeCambioFechaCobro;
	}

	/**
	 * @return the tipoDeCambioFechaEmision
	 */
	public BigDecimal getTipoDeCambioFechaEmision() {
		return tipoDeCambioFechaEmision;
	}

	/**
	 * @param tipoDeCambioFechaEmision the tipoDeCambioFechaEmision to set
	 */
	public void setTipoDeCambioFechaEmision(BigDecimal tipoDeCambioFechaEmision) {
		this.tipoDeCambioFechaEmision = tipoDeCambioFechaEmision;
	}

	/**
	 * @return the importeAplicadoFechaEmisionMonedaOrigen
	 */
	public BigDecimal getImporteAplicadoFechaEmisionMonedaOrigen() {
		return importeAplicadoFechaEmisionMonedaOrigen;
	}

	/**
	 * @param importeAplicadoFechaEmisionMonedaOrigen the importeAplicadoFechaEmisionMonedaOrigen to set
	 */
	public void setImporteAplicadoFechaEmisionMonedaOrigen(
			BigDecimal importeAplicadoFechaEmisionMonedaOrigen) {
		this.importeAplicadoFechaEmisionMonedaOrigen = importeAplicadoFechaEmisionMonedaOrigen;
	}

	/**
	 * @return the sistemaMedioPago
	 */
	public SistemaEnum getSistemaMedioPago() {
		return sistemaMedioPago;
	}

	/**
	 * @param sistemaMedioPago the sistemaMedioPago to set
	 */
	public void setSistemaMedioPago(SistemaEnum sistemaMedioPago) {
		this.sistemaMedioPago = sistemaMedioPago;
	}

	/**
	 * @return the tipoMedioPago
	 */
	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}

	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	/**
	 * @return the subTipoMedioPago
	 */
	public TipoRetencionEnum getSubTipoMedioPago() {
		return subTipoMedioPago;
	}

	/**
	 * @param subTipoMedioPago the subTipoMedioPago to set
	 */
	public void setSubTipoMedioPago(TipoRetencionEnum subTipoMedioPago) {
		this.subTipoMedioPago = subTipoMedioPago;
	}

	/**
	 * @return the referenciaMedioPago
	 */
	public String getReferenciaMedioPago() {
		return referenciaMedioPago;
	}

	/**
	 * @param referenciaMedioPago the referenciaMedioPago to set
	 */
	public void setReferenciaMedioPago(String referenciaMedioPago) {
		this.referenciaMedioPago = referenciaMedioPago;
	}

	/**
	 * @return the fechaMedioPago
	 */
	public Date getFechaMedioPago() {
		return fechaMedioPago;
	}

	/**
	 * @param fechaMedioPago the fechaMedioPago to set
	 */
	public void setFechaMedioPago(Date fechaMedioPago) {
		this.fechaMedioPago = fechaMedioPago;
	}

	/**
	 * @return the importeMedioPago
	 */
	public BigDecimal getImporteMedioPago() {
		return importeMedioPago;
	}

	/**
	 * @param importeMedioPago the importeMedioPago to set
	 */
	public void setImporteMedioPago(BigDecimal importeMedioPago) {
		this.importeMedioPago = importeMedioPago;
	}

	/**
	 * @return the queHacerConIntereses
	 */
	public TratamientoInteresesEnum getQueHacerConIntereses() {
		return queHacerConIntereses;
	}

	/**
	 * @param queHacerConIntereses the queHacerConIntereses to set
	 */
	public void setQueHacerConIntereses(
			TratamientoInteresesEnum queHacerConIntereses) {
		this.queHacerConIntereses = queHacerConIntereses;
	}

	/**
	 * @return the intereses
	 */
	public BigDecimal getIntereses() {
		return intereses;
	}

	/**
	 * @param intereses the intereses to set
	 */
	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}

	/**
	 * @return the cobrarSegundoVencimiento
	 */
	public SiNoEnum getCobrarSegundoVencimiento() {
		return cobrarSegundoVencimiento;
	}

	/**
	 * @param cobrarSegundoVencimiento the cobrarSegundoVencimiento to set
	 */
	public void setCobrarSegundoVencimiento(SiNoEnum cobrarSegundoVencimiento) {
		this.cobrarSegundoVencimiento = cobrarSegundoVencimiento;
	}

	/**
	 * @return the trasladarIntereses
	 */
	public SiNoEnum getTrasladarIntereses() {
		return trasladarIntereses;
	}

	/**
	 * @param trasladarIntereses the trasladarIntereses to set
	 */
	public void setTrasladarIntereses(SiNoEnum trasladarIntereses) {
		this.trasladarIntereses = trasladarIntereses;
	}

	/**
	 * @return the porcentajeABonificar
	 */
	public Integer getPorcentajeABonificar() {
		return porcentajeABonificar;
	}

	/**
	 * @param porcentajeABonificar the porcentajeABonificar to set
	 */
	public void setPorcentajeABonificar(Integer porcentajeABonificar) {
		this.porcentajeABonificar = porcentajeABonificar;
	}

	/**
	 * @return the importeABonificar
	 */
	public BigDecimal getImporteABonificar() {
		return importeABonificar;
	}

	/**
	 * @param importeABonificar the importeABonificar to set
	 */
	public void setImporteABonificar(BigDecimal importeABonificar) {
		this.importeABonificar = importeABonificar;
	}

	/**
	 * @return the acuerdoDestinoCargos
	 */
	public Long getAcuerdoDestinoCargos() {
		return acuerdoDestinoCargos;
	}

	/**
	 * @param acuerdoDestinoCargos the acuerdoDestinoCargos to set
	 */
	public void setAcuerdoDestinoCargos(Long acuerdoDestinoCargos) {
		this.acuerdoDestinoCargos = acuerdoDestinoCargos;
	}

	/**
	 * @return the estadoAcuerdoDestinoCargos
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoDestinoCargos() {
		return estadoAcuerdoDestinoCargos;
	}

	/**
	 * @param estadoAcuerdoDestinoCargos the estadoAcuerdoDestinoCargos to set
	 */
	public void setEstadoAcuerdoDestinoCargos(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoDestinoCargos) {
		this.estadoAcuerdoDestinoCargos = estadoAcuerdoDestinoCargos;
	}

	/**
	 * @return the idClienteLegadoAcuerdoTrasladoCargo
	 */
	public Long getIdClienteLegadoAcuerdoTrasladoCargo() {
		return idClienteLegadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param idClienteLegadoAcuerdoTrasladoCargo the idClienteLegadoAcuerdoTrasladoCargo to set
	 */
	public void setIdClienteLegadoAcuerdoTrasladoCargo(
			Long idClienteLegadoAcuerdoTrasladoCargo) {
		this.idClienteLegadoAcuerdoTrasladoCargo = idClienteLegadoAcuerdoTrasladoCargo;
	}

	/**
	 * @return the mensajeTransaccion
	 */
	public String getMensajeTransaccion() {
		return mensajeTransaccion;
	}

	/**
	 * @param mensajeTransaccion the mensajeTransaccion to set
	 */
	public void setMensajeTransaccion(String mensajeTransaccion) {
		this.mensajeTransaccion = mensajeTransaccion;
	}

	/**
	 * @return the mensajeDebito
	 */
	public String getMensajeDebito() {
		return mensajeDebito;
	}

	/**
	 * @param mensajeDebito the mensajeDebito to set
	 */
	public void setMensajeDebito(String mensajeDebito) {
		this.mensajeDebito = mensajeDebito;
	}

	/**
	 * @return the mensajeCredito
	 */
	public String getMensajeCredito() {
		return mensajeCredito;
	}

	/**
	 * @param mensajeCredito the mensajeCredito to set
	 */
	public void setMensajeCredito(String mensajeCredito) {
		this.mensajeCredito = mensajeCredito;
	}

	/**
	 * @return the tipoMensajeTransaccion
	 */
	public TipoMensajeEstadoEnum getTipoMensajeTransaccion() {
		return tipoMensajeTransaccion;
	}

	/**
	 * @param tipoMensajeTransaccion the tipoMensajeTransaccion to set
	 */
	public void setTipoMensajeTransaccion(
			TipoMensajeEstadoEnum tipoMensajeTransaccion) {
		this.tipoMensajeTransaccion = tipoMensajeTransaccion;
	}

	/**
	 * @return the tipoMensajeDebito
	 */
	public TipoMensajeEstadoEnum getTipoMensajeDebito() {
		return tipoMensajeDebito;
	}

	/**
	 * @param tipoMensajeDebito the tipoMensajeDebito to set
	 */
	public void setTipoMensajeDebito(TipoMensajeEstadoEnum tipoMensajeDebito) {
		this.tipoMensajeDebito = tipoMensajeDebito;
	}

	/**
	 * @return the tipoMensajeCredito
	 */
	public TipoMensajeEstadoEnum getTipoMensajeCredito() {
		return tipoMensajeCredito;
	}

	/**
	 * @param tipoMensajeCredito the tipoMensajeCredito to set
	 */
	public void setTipoMensajeCredito(TipoMensajeEstadoEnum tipoMensajeCredito) {
		this.tipoMensajeCredito = tipoMensajeCredito;
	}

	/**
	 * @return the estadoDebito
	 */
	public EstadoFacturaMedioPagoEnum getEstadoDebito() {
		return estadoDebito;
	}

	/**
	 * @param estadoDebito the estadoDebito to set
	 */
	public void setEstadoDebito(EstadoFacturaMedioPagoEnum estadoDebito) {
		this.estadoDebito = estadoDebito;
	}

	/**
	 * @return the estadoCredito
	 */
	public EstadoFacturaMedioPagoEnum getEstadoCredito() {
		return estadoCredito;
	}

	/**
	 * @param estadoCredito the estadoCredito to set
	 */
	public void setEstadoCredito(EstadoFacturaMedioPagoEnum estadoCredito) {
		this.estadoCredito = estadoCredito;
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
	 * @return the idTransaccion
	 */
	public Long getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * @return the numeroTransaccion
	 */
	public Long getNumeroTransaccion() {
		return numeroTransaccion;
	}

	/**
	 * @param numeroTransaccion the numeroTransaccion to set
	 */
	public void setNumeroTransaccion(Long numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public Long getNumeroTransaccionOriginal() {
		return numeroTransaccionOriginal;
	}

	public void setNumeroTransaccionOriginal(Long numeroTransaccionOriginal) {
		this.numeroTransaccionOriginal = numeroTransaccionOriginal;
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the idMedioPago
	 */
	public Long getIdMedioPago() {
		return idMedioPago;
	}

	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	/**
	 * @return the idTratamientoDiferencia
	 */
	public Long getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	/**
	 * @param idTratamientoDiferencia the idTratamientoDiferencia to set
	 */
	public void setIdTratamientoDiferencia(Long idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}

	/**
	 * @return the idTransaccionPadre
	 */
	public Long getIdTransaccionPadre() {
		return idTransaccionPadre;
	}

	/**
	 * @param idTransaccionPadre the idTransaccionPadre to set
	 */
	public void setIdTransaccionPadre(Long idTransaccionPadre) {
		this.idTransaccionPadre = idTransaccionPadre;
	}

	/**
	 * @return the origenDocumento
	 */
	public OrigenDocumentoEnum getOrigenDocumento() {
		return origenDocumento;
	}

	/**
	 * @param origenDocumento the origenDocumento to set
	 */
	public void setOrigenDocumento(OrigenDocumentoEnum origenDocumento) {
		this.origenDocumento = origenDocumento;
	}

	/**
	 * @return the fechaSegundoVencimiento
	 */
	public Date getFechaSegundoVencimiento() {
		return fechaSegundoVencimiento;
	}

	/**
	 * @param fechaSegundoVencimiento the fechaSegundoVencimiento to set
	 */
	public void setFechaSegundoVencimiento(Date fechaSegundoVencimiento) {
		this.fechaSegundoVencimiento = fechaSegundoVencimiento;
	}

	/**
	 * @return the idDescobro
	 */
	public Long getIdDescobro() {
		return idDescobro;
	}

	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

	/**
	 * @return the sistemaAcuerdoContracargo
	 */
	public SistemaEnum getSistemaAcuerdoContracargo() {
		return sistemaAcuerdoContracargo;
	}

	/**
	 * @param sistemaAcuerdoContracargo the sistemaAcuerdoContracargo to set
	 */
	public void setSistemaAcuerdoContracargo(SistemaEnum sistemaAcuerdoContracargo) {
		this.sistemaAcuerdoContracargo = sistemaAcuerdoContracargo;
	}

	/**
	 * @return the acuerdoContracargo
	 */
	public Long getAcuerdoContracargo() {
		return acuerdoContracargo;
	}

	/**
	 * @param acuerdoContracargo the acuerdoContracargo to set
	 */
	public void setAcuerdoContracargo(Long acuerdoContracargo) {
		this.acuerdoContracargo = acuerdoContracargo;
	}

	/**
	 * @return the estadoAcuerdoContracargo
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoContracargo() {
		return estadoAcuerdoContracargo;
	}

	/**
	 * @param estadoAcuerdoContracargo the estadoAcuerdoContracargo to set
	 */
	public void setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo) {
		this.estadoAcuerdoContracargo = estadoAcuerdoContracargo;
	}

	/**
	 * @return the idClienteAcuerdoContracargo
	 */
	public Long getIdClienteAcuerdoContracargo() {
		return idClienteAcuerdoContracargo;
	}

	/**
	 * @param idClienteAcuerdoContracargo the idClienteAcuerdoContracargo to set
	 */
	public void setIdClienteAcuerdoContracargo(
			Long idClienteAcuerdoContracargo) {
		this.idClienteAcuerdoContracargo = idClienteAcuerdoContracargo;
	}

	/**
	 * @return the estadoCargoGenerado
	 */
	public EstadoCargoGeneradoEnum getEstadoCargoGenerado() {
		return estadoCargoGenerado;
	}

	/**
	 * @param estadoCargoGenerado the estadoCargoGenerado to set
	 */
	public void setEstadoCargoGenerado(EstadoCargoGeneradoEnum estadoCargoGenerado) {
		this.estadoCargoGenerado = estadoCargoGenerado;
	}

	/**
	 * @return the sistemaAcuerdo
	 */
	public SistemaEnum getSistemaAcuerdo() {
		return sistemaAcuerdo;
	}

	/**
	 * @param sistemaAcuerdo the sistemaAcuerdo to set
	 */
	public void setSistemaAcuerdo(SistemaEnum sistemaAcuerdo) {
		this.sistemaAcuerdo = sistemaAcuerdo;
	}

	/**
	 * @return the cobradorInteresesTrasladados
	 */
	public BigDecimal getCobradorInteresesTrasladados() {
		return cobradorInteresesTrasladados;
	}

	/**
	 * @param cobradorInteresesTrasladados the cobradorInteresesTrasladados to set
	 */
	public void setCobradorInteresesTrasladados(
			BigDecimal cobradorInteresesTrasladados) {
		this.cobradorInteresesTrasladados = cobradorInteresesTrasladados;
	}

	public Long getNumeroTransaccionPadre() {
		return numeroTransaccionPadre;
	}

	public void setNumeroTransaccionPadre(Long numeroTransaccionPadre) {
		this.numeroTransaccionPadre = numeroTransaccionPadre;
	}

	/**
	 * @return the subTipoMedioPagoRemanente
	 */
	public TipoRemanenteEnum getSubTipoMedioPagoRemanente() {
		return subTipoMedioPagoRemanente;
	}

	/**
	 * @param subTipoMedioPagoRemanente the subTipoMedioPagoRemanente to set
	 */
	public void setSubTipoMedioPagoRemanente(TipoRemanenteEnum subTipoMedioPagoRemanente) {
		this.subTipoMedioPagoRemanente = subTipoMedioPagoRemanente;
	}

	/**
	 * @return the monedaMedioPago
	 */
	public MonedaEnum getMonedaMedioPago() {
		return monedaMedioPago;
	}

	/**
	 * @param monedaMedioPago the monedaMedioPago to set
	 */
	public void setMonedaMedioPago(MonedaEnum monedaMedioPago) {
		this.monedaMedioPago = monedaMedioPago;
	}

	/**
	 * @return the tipoDeCambioFechaCobroMedioPago
	 */
	public BigDecimal getTipoDeCambioFechaCobroMedioPago() {
		return tipoDeCambioFechaCobroMedioPago;
	}

	/**
	 * @param tipoDeCambioFechaCobroMedioPago the tipoDeCambioFechaCobroMedioPago to set
	 */
	public void setTipoDeCambioFechaCobroMedioPago(
			BigDecimal tipoDeCambioFechaCobroMedioPago) {
		this.tipoDeCambioFechaCobroMedioPago = tipoDeCambioFechaCobroMedioPago;
	}

	/**
	 * @return the tipoDeCambioFechaEmisionMedioPago
	 */
	public BigDecimal getTipoDeCambioFechaEmisionMedioPago() {
		return tipoDeCambioFechaEmisionMedioPago;
	}

	/**
	 * @param tipoDeCambioFechaEmisionMedioPago the tipoDeCambioFechaEmisionMedioPago to set
	 */
	public void setTipoDeCambioFechaEmisionMedioPago(
			BigDecimal tipoDeCambioFechaEmisionMedioPago) {
		this.tipoDeCambioFechaEmisionMedioPago = tipoDeCambioFechaEmisionMedioPago;
	}

	/**
	 * @return the importeAplicadoFechaEmisionMonedaOrigenMedioPago
	 */
	public BigDecimal getImporteAplicadoFechaEmisionMonedaOrigenMedioPago() {
		return importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	}

	/**
	 * @param importeAplicadoFechaEmisionMonedaOrigenMedioPago the importeAplicadoFechaEmisionMonedaOrigenMedioPago to set
	 */
	public void setImporteAplicadoFechaEmisionMonedaOrigenMedioPago(
			BigDecimal importeAplicadoFechaEmisionMonedaOrigenMedioPago) {
		this.importeAplicadoFechaEmisionMonedaOrigenMedioPago = importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	}
	
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the numeroTransaccionPadreFicticio
	 */
	public Long getNumeroTransaccionPadreFicticio() {
		return numeroTransaccionPadreFicticio;
	}

	/**
	 * @param numeroTransaccionPadreFicticio the numeroTransaccionPadreFicticio to set
	 */
	public void setNumeroTransaccionPadreFicticio(Long numeroTransaccionPadreFicticio) {
		this.numeroTransaccionPadreFicticio = numeroTransaccionPadreFicticio;
	}
	
	

}