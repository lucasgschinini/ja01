package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;

@Entity
@Table(name = "SHV_COB_APLIC_MANUAL_BATCH_DET")
public class ShvCobAplicacionManualBatchDetalle extends Modelo {
	 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_APLIC_MANUAL_BATCH")
    @SequenceGenerator(name="SEQ_SHV_COB_APLIC_MANUAL_BATCH", sequenceName="SEQ_SHV_COB_APLIC_MANUAL_BATCH",allocationSize=1)
	@Column(name="ID_COB_APLIC_MANUAL_BATCH_DET")	
	private Long idCobAplicManualBatchDetalle;
	
	@Column(name="ID_TRATAMIENTO_DIFERENCIA")	
	private BigDecimal idTratamientoDiferencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO_TAREA")
	private MensajeEstadoEnum estadoTarea;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ARCHIVO", referencedColumnName="ID_ARCHIVO")
	private ShvArcArchivo archivo;
	
	@Column(name="TIPO_OPERACION")	
	private String tipoOperacion;
	
	@Column(name="CUIT")
	private String cuit;
	
	@Column(name="POSEE_ADJUNTO")
	private String poseeAdjunto;
	
	@Column(name="MONEDA")
	private String moneda;
	
	@Column(name="MONTO_IMPUTAR_MONEDA_ORIGEN")	
	private BigDecimal montoImputarMonedaOrigen;
	
	@Column(name="TIPO_CAMBIO")	
	private BigDecimal tipoCambio;
	
	@Column(name="MONTO_IMPUTAR_PESOS")	
	private BigDecimal montoImputarPesos;

	@Column(name="ID_TRANSACCION")
	private Long idTransaccion;

	@Column(name="ID_TRANSACCION_COBRO")
	private String idTransaccionCobro;

	@Column(name="FECHA_VALOR_MEDIO_PAGO")
	private Date fechaValorMedioPago;
	
	@Column(name="TIPO_MEDIO_PAGO")
	private String tipoMedioPago;
	
	@Column(name="REFERENCIA_VALOR")
	private String referenciaValor;
	
	@Column(name="OPERACIONES_EXTERNAS")
	private String operacionesExternas;
	
	@Column(name="ID_TRANSACCION_DESCOBRO")
	private String idTransaccionDescobro;
	
	@Column(name="SISTEMA")
	@Enumerated(EnumType.STRING)
	private SistemaEnum sistema;
	
	@Column(name="ID_COBRO")
	private Long idCobro;

	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name="ID_DESCOBRO")
	private Long idDescobro;

	@Column(name="ID_OPERACION_DESCOBRO")
	private Long idOperacionDescobro;
	
	public Long getIdCobAplicManualBatchDetalle() {
		return idCobAplicManualBatchDetalle;
	}

	public void setIdCobAplicManualBatchDetalle(Long idCobAplicManualBatchDetalle) {
		this.idCobAplicManualBatchDetalle = idCobAplicManualBatchDetalle;
	}

	public BigDecimal getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	public void setIdTratamientoDiferencia(BigDecimal idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}

	public MensajeEstadoEnum getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(MensajeEstadoEnum estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public ShvArcArchivo getArchivo() {
		return archivo;
	}

	public void setArchivo(ShvArcArchivo archivo) {
		this.archivo = archivo;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}



	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getMontoImputarMonedaOrigen() {
		return montoImputarMonedaOrigen;
	}

	public void setMontoImputarMonedaOrigen(BigDecimal montoImputarMonedaOrigen) {
		this.montoImputarMonedaOrigen = montoImputarMonedaOrigen;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public BigDecimal getMontoImputarPesos() {
		return montoImputarPesos;
	}

	public void setMontoImputarPesos(BigDecimal montoImputarPesos) {
		this.montoImputarPesos = montoImputarPesos;
	}

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getIdTransaccionCobro() {
		return idTransaccionCobro;
	}

	public void setIdTransaccionCobro(String idTransaccionCobro) {
		this.idTransaccionCobro = idTransaccionCobro;
	}

	public Date getFechaValorMedioPago() {
		return fechaValorMedioPago;
	}

	public void setFechaValorMedioPago(Date fechaValorMedioPago) {
		this.fechaValorMedioPago = fechaValorMedioPago;
	}

	public String getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public String getReferenciaValor() {
		return referenciaValor;
	}

	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}

	public String getOperacionesExternas() {
		return operacionesExternas;
	}

	public void setOperacionesExternas(String operacionesExternas) {
		this.operacionesExternas = operacionesExternas;
	}

	public String getIdTransaccionDescobro() {
		return idTransaccionDescobro;
	}

	public void setIdTransaccionDescobro(String idTransaccionDescobro) {
		this.idTransaccionDescobro = idTransaccionDescobro;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the poseeAdjunto
	 */
	public String getPoseeAdjunto() {
		return poseeAdjunto;
	}

	/**
	 * @param poseeAdjunto the poseeAdjunto to set
	 */
	public void setPoseeAdjunto(String poseeAdjunto) {
		this.poseeAdjunto = poseeAdjunto;
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
	 * @return the idOperacionDescobro
	 */
	public Long getIdOperacionDescobro() {
		return idOperacionDescobro;
	}

	/**
	 * @param idOperacionDescobro the idOperacionDescobro to set
	 */
	public void setIdOperacionDescobro(Long idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}

}
