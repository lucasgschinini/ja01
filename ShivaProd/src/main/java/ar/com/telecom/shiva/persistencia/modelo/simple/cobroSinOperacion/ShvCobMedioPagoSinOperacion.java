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

import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPagoUso;

@Entity
@Table(name = "SHV_COB_MED_PAGO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvCobMedioPagoSinOperacion extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_MEDIO_PAGO_SIN")
    @SequenceGenerator(name="SEQ_SHV_COB_MEDIO_PAGO_SIN", sequenceName="SEQ_SHV_COB_MEDIO_PAGO",allocationSize=1)
	@Column(name="ID_MEDIO_PAGO")	
	private Integer idMedioPago;
	
	@Transient
	private Integer idMedioPagoCobro;

	@Column(name="IMPORTE") 			
	private  BigDecimal importe;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO", referencedColumnName="ID_TIPO_MEDIO_PAGO", updatable=false)
	private ShvParamTipoMedioPago tipoMedioPago;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_TRANSACCION", referencedColumnName="ID_TRANSACCION") 	
	private ShvCobTransaccionSinOperacion transaccion;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")		
	private EstadoFacturaMedioPagoEnum estado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA")				
	private MonedaEnum moneda;

	@Column(name="MONTO_ACUMULADO_SIMULACION")
	private BigDecimal montoAcumuladoSimulacion;

	@Enumerated(EnumType.STRING)
	@Column(name="MIGRADO_DEIMOS")
	private SiNoEnum migradoDeimos;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_DEIMOS")
	private EstadoFacturaMedioPagoEnum estadoDeimos;
	
	@Column(name="FECHA_ACTUALIZACION_DEIMOS")
	private Date fechaActualiuzacionDeimos;
	
	@Column(name="MENSAJE_ESTADO")
	private String mensajeEstado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_MENSAJE_ESTADO")
	private TipoMensajeEstadoEnum tipoMensajeEstado;

	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;

	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE")
	private MonedaEnum  monedaImporte ;
	
	//
	// Relación con créditos y otros medios de pago del online
	//
	@Column(name="ID_COBRO")
	private Long idCobro;
	
	@Column(name="ID_CREDITO_ORIGEN")
	private Long idCreditoOrigen;

	
	/**
	 * Retorna los posibles usos del medio de pago para la moneda especifica del medio de pago actual
	 * @return
	 */
	public ShvParamTipoMedioPagoUso getUsoPorMoneda() {
		
		for (ShvParamTipoMedioPagoUso uso : this.tipoMedioPago.getUsos()) {
			if (uso.getMoneda().equals(this.getMoneda())) {
				return uso;
			}
		}
		return null;
	}
	
	/**
	 * Este metodo será sobrecargado por cada medio de pago puntual, y será
	 * utilizado entre otras cosas para ordenar los medios de pago por fecha valor.
	 * 
	 * @return
	 */
	public Date getFechaValor() {
		return  null;
	}
	
	/**
	 * Este metodo será spbrecargado por cada medio de pago puntual, y será
	 * utilizado entre otras cosas para ordenar los medios de pago por referencia
	 * 
	 * @return
	 */
	public String getReferencia() {
		return null;
	}

	
	/**
	 * Este metodo será sobrecargado por cada medio de pago que participe en 
	 * apropiacion de cobradores, y será utilizado para poder sincronizar la respuesta del cobrador 
	 * dentro de la transaccion, luego de una apropiacion
	 * 
	 * @return
	 */
	public String getIdBusquedaRespuestaCobrador() {
		return null;
	}
	/**
	 * @return the idMedioPago
	 */
	public Integer getIdMedioPago() {
		return idMedioPago;
	}

	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(Integer idMedioPago) {
		this.idMedioPago = idMedioPago;
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
	 * @return the tipoMedioPago
	 */
	public ShvParamTipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(ShvParamTipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	/**
	 * @return the transaccion
	 */
	public ShvCobTransaccionSinOperacion getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(ShvCobTransaccionSinOperacion transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * @return the estado
	 */
	public EstadoFacturaMedioPagoEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoFacturaMedioPagoEnum estado) {
		this.estado = estado;
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

	@Override
	public Serializable getId() {
		return this.getIdMedioPago();
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
	 * @return the idCreditoOrigen
	 */
	public Long getIdCreditoOrigen() {
		return idCreditoOrigen;
	}

	/**
	 * @param idCreditoOrigen the idCreditoOrigen to set
	 */
	public void setIdCreditoOrigen(Long idCreditoOrigen) {
		this.idCreditoOrigen = idCreditoOrigen;
	}

	/**
	 * @return the tipoMensajeEstado
	 */
	public TipoMensajeEstadoEnum getTipoMensajeEstado() {
		return tipoMensajeEstado;
	}

	/**
	 * @param tipoMensajeEstado the tipoMensajeEstado to set
	 */
	public void setTipoMensajeEstado(TipoMensajeEstadoEnum tipoMensajeEstado) {
		this.tipoMensajeEstado = tipoMensajeEstado;
	}

	public Integer getIdMedioPagoCobro() {
		return idMedioPagoCobro;
	}

	public void setIdMedioPagoCobro(Integer idMedioPagoCobro) {
		this.idMedioPagoCobro = idMedioPagoCobro;
	}

	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}
	
	
}
