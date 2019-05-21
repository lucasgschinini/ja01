package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_COB_FACTURA")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvCobFacturaSinOperacion extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_FACTURA_SIN")
    @SequenceGenerator(name="SEQ_SHV_COB_FACTURA_SIN", sequenceName="SEQ_SHV_COB_FACTURA", allocationSize = 1)
	@Column(name="ID_FACTURA")
	private Integer idFactura;
	
	@Transient
	private Integer idFacturaCobro;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_TRANSACCION", referencedColumnName="ID_TRANSACCION") 	
	private ShvCobTransaccionSinOperacion transaccion;
	
	@Column(name="FECHA_VALOR")
	private	Date fechaValor;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoFacturaMedioPagoEnum  estado ;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum tipoComprobante;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CLASE_COMPROBANTE")
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Column(name="SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;
	
	@Column(name="NUMERO_COMPROBANTE")
	private String numeroComprobante;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="IMPORTE_COBRAR")
	private BigDecimal importeCobrar;
	
	@Column(name="IMPORTE_ORIGINAL")
	private BigDecimal importeOriginal;
	
	@Column(name="SALDO_ACTUAL")
	private BigDecimal saldoActual;
	
	@Column(name="CUIT")
	private String cuit;
	
	@Column(name="FECHA_VENCIMIENTO")
	private Date  fechaVencimiento;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;

	@Enumerated(EnumType.STRING)
	@Column(name="SOCIEDAD")
	private SociedadEnum sociedad;
	//
	// Simulacion
	//
	
	@Column(name="MONTO_ACUMULADO_SIMULACION")
	private BigDecimal montoAcumuladoSimulacion;
	
	@Column(name="FECHA_ACUMULADO_SIMULACION")
	private Date fechaAcumuladoSimulacion;
	
	@Column(name="MENSAJE_ESTADO")
	private String mensajeEstado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_MENSAJE_ESTADO")
	private TipoMensajeEstadoEnum tipoMensajeEstado;

	//
	// Tratamiento de intereses y gestion de cargos
	//
	
	@Enumerated(EnumType.STRING)
	@Column(name="CHECK_TRASLADAR_INTERESES")
	private SiNoEnum trasladarIntereses;

	@Column(name="ACUERDO_TRASLADO_CARGO")
	private String acuerdoTrasladoCargo;

	@Column(name="ACUERDO_TRASLADO_CARGO_ORIGNAL")
	private String acuerdoTrasladoCargoOriginal;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_TRASLADO_CARGO", nullable = true)
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo;

	@Column(name="ID_CLIENTE_ACUERDO_TRAS_CARGO")
	private Long idClienteLegadoAcuerdoTrasladoCargo;

	@Column(name="PORCENTAJE_BONIF_INTERESES")
	private Integer porcentajeBonifIntereses;
	
	@Column(name="IMPORTE_BONIF_INTERESES")
	private BigDecimal importeBonificacionIntereses;

	@Enumerated(EnumType.STRING)
	@Column(name="QUE_HACER_CON_INTERESES")
	private TratamientoInteresesEnum queHacerConIntereses;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_PAGO")		
	private TipoPagoEnum  tipoPago;
	
	@Column(name="COBRADOR_INTERESES_GENERADOS")
	private BigDecimal cobradorInteresesGenerados;
	
	@Column(name="COBRADOR_INTERESES_TRASLADADOS")
	private BigDecimal cobradorInteresesTrasladados;

	@Column(name="ID_COBRANZA")
	private Long idCobranza;
	
	@Enumerated(EnumType.STRING)
	@Column(name="GENERADO_POR_COBRO")
	private SiNoEnum generadoPorCobro;
	
	//
	// Deimos
	//
	
	@Enumerated(EnumType.STRING)
	@Column(name="MIGRADO_DEIMOS")
	private SiNoEnum migradoDeimos;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_DEIMOS")
	private EstadoFacturaMedioPagoEnum estadoDeimos;
	
	@Column(name="FECHA_ACTUALIZACION_DEIMOS")
	private Date fechaActualiuzacionDeimos;
	
	//
	// Relación con debitos del online
	//
	@Column(name="ID_COBRO")
	private Long idCobro;
	
	@Column(name="ID_DEBITO_ORIGEN")
	private Long idDebitoOrigen;

	@Transient
	private String textoArchivo;
	
	//
	// Descobros, sprint 7, u573005, fabio.giaquinta.ruiz
	//
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_CARGO_GENERADO", nullable = true)
	private EstadoCargoGeneradoEnum estadoCargoGenerado;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ACUERDO_CONTRACARGO", nullable = true)
	private SistemaEnum sistemaAcuerdoContracargo;
	
	@Column(name="ACUERDO_CONTRACARGO", nullable = true)
	private String  acuerdoContracargo;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_ACUERDO_CONTRACARGO", nullable = true)
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo;

	@Column(name="ID_CLIENTE_ACUERDO_CONTRACARGO", nullable = true)
	private Long idClienteLegadoAcuerdoContracargo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_CARGO")
	private OrigenCargoEnum origenCargo;
	
	@Column(name="LEYENDA_FACTURA_INTERES")
	private String leyendaFacturaInteres;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE_COBRAR")
	private MonedaEnum  monedaImporteCobrar ;
	
	@Override
	public Serializable getId() {
		return this.getIdFactura();
	}
	
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public ShvCobTransaccionSinOperacion getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(ShvCobTransaccionSinOperacion transaccion) {
		this.transaccion = transaccion;
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
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	/**
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}
	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	/**
	 * @return the importeCobrar
	 */
	public BigDecimal getImporteCobrar() {
		return importeCobrar;
	}
	/**
	 * @param importeCobrar the importeCobrar to set
	 */
	public void setImporteCobrar(BigDecimal importeCobrar) {
		this.importeCobrar = importeCobrar;
	}
	/**
	 * @return the importeOriginal
	 */
	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}
	/**
	 * @param importeOriginal the importeOriginal to set
	 */
	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
	}
	/**
	 * @return the saldoActual
	 */
	public BigDecimal getSaldoActual() {
		return saldoActual;
	}
	/**
	 * @param saldoActual the saldoActual to set
	 */
	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}
	/**
	 * @return the acuerdoTrasladoCargo
	 */
	public String getAcuerdoTrasladoCargo() {
		return acuerdoTrasladoCargo;
	}
	/**
	 * @param acuerdoTrasladoCargo the acuerdoTrasladoCargo to set
	 */
	public void setAcuerdoTrasladoCargo(String acuerdoTrasladoCargo) {
		this.acuerdoTrasladoCargo = acuerdoTrasladoCargo;
	}

	/**
	 * @return the acuerdoTrasladoCargoOriginal
	 */
	public String getAcuerdoTrasladoCargoOriginal() {
		return acuerdoTrasladoCargoOriginal;
	}

	/**
	 * @param acuerdoTrasladoCargoOriginal the acuerdoTrasladoCargoOriginal to set
	 */
	public void setAcuerdoTrasladoCargoOriginal(String acuerdoTrasladoCargoOriginal) {
		this.acuerdoTrasladoCargoOriginal = acuerdoTrasladoCargoOriginal;
	}

	/**
	 *  @return the porcentajeBonifIntereses
	 * 
	 */
	public Integer getPorcentajeBonifIntereses() {
		return porcentajeBonifIntereses;
	}
	/**
	 * @param porcentajeBonifIntereses the porcentajeBonifIntereses to set
	 * 
	 */
	public void setPorcentajeBonifIntereses(Integer porcentajeBonifIntereses) {
		this.porcentajeBonifIntereses = porcentajeBonifIntereses;
	}
	
	public EstadoFacturaMedioPagoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoFacturaMedioPagoEnum estado) {
		this.estado = estado;
	}
	public TipoPagoEnum getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(TipoPagoEnum tipoPago) {
		this.tipoPago = tipoPago;
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
	public TratamientoInteresesEnum getQueHacerConIntereses() {
		return queHacerConIntereses;
	}
	public void setQueHacerConIntereses(
			TratamientoInteresesEnum queHacerConIntereses) {
		this.queHacerConIntereses = queHacerConIntereses;
	}
	public BigDecimal getCobradorInteresesGenerados() {
		return cobradorInteresesGenerados;
	}
	public void setCobradorInteresesGenerados(BigDecimal interesesGenerados) {
		this.cobradorInteresesGenerados = interesesGenerados;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Long getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}
	public SiNoEnum getGeneradoPorCobro() {
		return generadoPorCobro;
	}
	public void setGeneradoPorCobro(SiNoEnum generadoPorCobro) {
		this.generadoPorCobro = generadoPorCobro;
	}
	public String getTextoArchivo(){
		return this.textoArchivo;
	}

	public void setTextoArchivo(String textoArchivo) {
		this.textoArchivo = textoArchivo;
	}
	
	public String getTipoCliente() {
		return null;
	}

	public void setTipoCliente(String tipoCliente) {
	}
	/**
	 * @return the importeBonificacionIntereses
	 */
	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}
	/**
	 * @param importeBonificacionIntereses the importeBonificacionIntereses to set
	 */
	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}
	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}
	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	/**
	 * @return the montoAcumuladoSimulacion
	 */
	public BigDecimal getMontoAcumuladoSimulacion() {
		return montoAcumuladoSimulacion;
	}
	/**
	 * @param montoAcumuladoSimulacion the montoAcumuladoSimulacion to set
	 */
	public void setMontoAcumuladoSimulacion(BigDecimal montoAcumuladoSimulacion) {
		this.montoAcumuladoSimulacion = montoAcumuladoSimulacion;
	}
	/**
	 * @return the fechaAcumuladoSimulacion
	 */
	public Date getFechaAcumuladoSimulacion() {
		return fechaAcumuladoSimulacion;
	}
	/**
	 * @param fechaAcumuladoSimulacion the fechaAcumuladoSimulacion to set
	 */
	public void setFechaAcumuladoSimulacion(Date fechaAcumuladoSimulacion) {
		this.fechaAcumuladoSimulacion = fechaAcumuladoSimulacion;
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
	 * @return the migradoDeimos
	 */
	public SiNoEnum getMigradoDeimos() {
		return migradoDeimos;
	}
	/**
	 * @param migradoDeimos the migradoDeimos to set
	 */
	public void setMigradoDeimos(SiNoEnum migradoDeimos) {
		this.migradoDeimos = migradoDeimos;
	}
	/**
	 * @return the estadoDeimos
	 */
	public EstadoFacturaMedioPagoEnum getEstadoDeimos() {
		return estadoDeimos;
	}
	/**
	 * @param estadoDeimos the estadoDeimos to set
	 */
	public void setEstadoDeimos(EstadoFacturaMedioPagoEnum estadoDeimos) {
		this.estadoDeimos = estadoDeimos;
	}
	/**
	 * @return the fechaActualiuzacionDeimos
	 */
	public Date getFechaActualiuzacionDeimos() {
		return fechaActualiuzacionDeimos;
	}
	/**
	 * @param fechaActualiuzacionDeimos the fechaActualiuzacionDeimos to set
	 */
	public void setFechaActualiuzacionDeimos(Date fechaActualiuzacionDeimos) {
		this.fechaActualiuzacionDeimos = fechaActualiuzacionDeimos;
	}
	/**
	 * @return the mensajeEstado
	 */
	public String getMensajeEstado() {
		return mensajeEstado;
	}
	/**
	 * @param mensajeEstado the mensajeEstado to set
	 */
	public void setMensajeEstado(String mensajeEstado) {
		this.mensajeEstado = mensajeEstado;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	/**
	 * @return the idDebitoOrigen
	 */
	public Long getIdDebitoOrigen() {
		return idDebitoOrigen;
	}

	/**
	 * @param idDebitoOrigen the idDebitoOrigen to set
	 */
	public void setIdDebitoOrigen(Long idDebitoOrigen) {
		this.idDebitoOrigen = idDebitoOrigen;
	}

	/**
	 * @return the estadoAcuerdoTrasladoCargo
	 */
	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoTrasladoCargo() {
		return estadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param estadoAcuerdoTrasladoCargo the estadoAcuerdoTrasladoCargo to set
	 */
	public void setEstadoAcuerdoTrasladoCargo(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoTrasladoCargo) {
		this.estadoAcuerdoTrasladoCargo = estadoAcuerdoTrasladoCargo;
	}

	/**
	 * @return the idClienteAcuerdoFacturacion
	 */
	public Long getIdClienteLegadoAcuerdoTrasladoCargo() {
		return idClienteLegadoAcuerdoTrasladoCargo;
	}

	/**
	 * @param idClienteAcuerdoFacturacion the idClienteAcuerdoFacturacion to set
	 */
	public void setIdClienteLegadoAcuerdoTrasladoCargo(Long idClienteAcuerdoFacturacion) {
		this.idClienteLegadoAcuerdoTrasladoCargo = idClienteAcuerdoFacturacion;
	}

	public TipoMensajeEstadoEnum getTipoMensajeEstado() {
		return tipoMensajeEstado;
	}

	public void setTipoMensajeEstado(TipoMensajeEstadoEnum tipoMensajeEstado) {
		this.tipoMensajeEstado = tipoMensajeEstado;
	}

	public EstadoCargoGeneradoEnum getEstadoCargoGenerado() {
		return estadoCargoGenerado;
	}

	public void setEstadoCargoGenerado(EstadoCargoGeneradoEnum estadoCargoGenerado) {
		this.estadoCargoGenerado = estadoCargoGenerado;
	}

	public SistemaEnum getSistemaAcuerdoContracargo() {
		return sistemaAcuerdoContracargo;
	}

	public void setSistemaAcuerdoContracargo(SistemaEnum sistemaContracargo) {
		this.sistemaAcuerdoContracargo = sistemaContracargo;
	}

	public String getAcuerdoContracargo() {
		return acuerdoContracargo;
	}

	public void setAcuerdoContracargo(String acuerdoContracargo) {
		this.acuerdoContracargo = acuerdoContracargo;
	}

	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoContracargo() {
		return estadoAcuerdoContracargo;
	}

	public void setEstadoAcuerdoContracargo(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo) {
		this.estadoAcuerdoContracargo = estadoAcuerdoContracargo;
	}

	public Long getIdClienteLegadoAcuerdoContracargo() {
		return idClienteLegadoAcuerdoContracargo;
	}

	public void setIdClienteLegadoAcuerdoContracargo(
			Long idClienteLegadoAcuerdoContracargo) {
		this.idClienteLegadoAcuerdoContracargo = idClienteLegadoAcuerdoContracargo;
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
	
	public OrigenCargoEnum getOrigenCargo() {
		return origenCargo;
	}

	public void setOrigenCargo(OrigenCargoEnum origenCargo) {
		this.origenCargo = origenCargo;
	}

	public String getLeyendaFacturaInteres() {
		return leyendaFacturaInteres;
	}

	public void setLeyendaFacturaInteres(String leyendaFacturaInteres) {
		this.leyendaFacturaInteres = leyendaFacturaInteres;
	}

	public Integer getIdFacturaCobro() {
		return idFacturaCobro;
	}

	public void setIdFacturaCobro(Integer idFacturaCobro) {
		this.idFacturaCobro = idFacturaCobro;
	}

	public MonedaEnum getMonedaImporteCobrar() {
		return monedaImporteCobrar;
	}

	public void setMonedaImporteCobrar(MonedaEnum monedaImporteCobrar) {
		this.monedaImporteCobrar = monedaImporteCobrar;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}
}