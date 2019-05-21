package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;

@Entity
@Table(name = "SHV_VAL_VALOR_RETENCION")
public class ShvValValorRetencion extends Modelo {

	private static final long serialVersionUID = 1L;


	
	@Id
	@Column(name = "ID_VALOR")
	@GeneratedValue(generator = "SEQ_SHV_VAL_VALOR")
	@GenericGenerator(name = "SEQ_SHV_VAL_VALOR", strategy = "foreign", parameters = @Parameter(name = "property", value = "valor"))
	private Long idValor;

	@Column(name = "FECHA_EMISION", nullable = false)
	private Date fechaEmision;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvValValor valor;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_RETENCION_IMPUESTO", referencedColumnName = "ID_TIPO_RETENCION_IMPUESTO")
	private ShvParamTipoRetencionImpuesto paramTipoRetencionImpuesto;

	@Column(name = "NRO_CONSTANCIA_RETENCION", nullable = false)
	private String nroConstanciaRetencion;

	@Column(name = "CUIT")
	private String cuit;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_JURISDICCION", referencedColumnName = "ID_JURISDICCION")
	private ShvParamJurisdiccion paramJurisdiccion;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_COMPROBANTE", referencedColumnName = "ID_TIPO_COMPROBANTE")
	private ShvParamTipoComprobante paramTipoComprobante;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_LETRA_COMPROBANTE", referencedColumnName = "ID_TIPO_LETRA_COMPROBANTE")
	private ShvParamTipoLetraComprobante paramTipoLetraComprobante;

	@Column(name = "NUMERO_COMPROBANTE")
	private String numeroComprobante;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_RECIBO_PREIMPRESO", referencedColumnName = "ID_RECIBO_PREIMPRESO")
	private ShvTalReciboPreImpreso reciboPreImpreso;

	@Column(name = "FECHA_RECIBO")
	private Date fechaRecibo;

	@Column(name = "SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

	public ShvParamTipoRetencionImpuesto getParamTipoRetencionImpuesto() {
		return paramTipoRetencionImpuesto;
	}

	public void setParamTipoRetencionImpuesto(
			ShvParamTipoRetencionImpuesto paramTipoRetencionImpuesto) {
		this.paramTipoRetencionImpuesto = paramTipoRetencionImpuesto;
	}

	public String getNroConstanciaRetencion() {
		return nroConstanciaRetencion;
	}

	public void setNroConstanciaRetencion(String nroConstanciaRetencion) {
		this.nroConstanciaRetencion = nroConstanciaRetencion;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public ShvParamJurisdiccion getParamJurisdiccion() {
		return paramJurisdiccion;
	}

	public void setParamJurisdiccion(ShvParamJurisdiccion paramJurisdiccion) {
		this.paramJurisdiccion = paramJurisdiccion;
	}

	public ShvParamTipoComprobante getParamTipoComprobante() {
		return paramTipoComprobante;
	}

	public void setParamTipoComprobante(
			ShvParamTipoComprobante paramTipoComprobante) {
		this.paramTipoComprobante = paramTipoComprobante;
	}

	public ShvParamTipoLetraComprobante getParamTipoLetraComprobante() {
		return paramTipoLetraComprobante;
	}

	public void setParamTipoLetraComprobante(
			ShvParamTipoLetraComprobante paramTipoLetraComprobante) {
		this.paramTipoLetraComprobante = paramTipoLetraComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	public ShvTalReciboPreImpreso getReciboPreImpreso() {
		return reciboPreImpreso;
	}

	public void setReciboPreImpreso(
			ShvTalReciboPreImpreso reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}

	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

}
