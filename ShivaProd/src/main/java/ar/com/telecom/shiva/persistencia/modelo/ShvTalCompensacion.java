package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;


@Entity
@Table(name = "SHV_TAL_COMPENSACION")
public class ShvTalCompensacion extends Modelo  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_COMPENSACION")
	@SequenceGenerator(name="SEQ_SHV_TAL_COMPENSACION", sequenceName="SEQ_SHV_TAL_COMPENSACION", allocationSize = 1)
	@Column(name="ID_COMPENSACION")
	private Integer idCompensacion;
	
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="ID_RECIBO_PREIMPRESO", referencedColumnName="ID_RECIBO_PREIMPRESO")
	private ShvTalReciboPreImpreso reciboPreImpreso;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	@Column(name="IMPORTE")
	private BigDecimal  importe;
	
	
	/**
	 * Importe formateado
	 */
	public String getImporteFormateado() {
		return Validaciones.isObjectNull(this.importe) ? "-" : Utilidad.formatCurrency(this.importe, 2);
	}
	
	
	public Integer getIdCompensacion() {
		return idCompensacion;
	}
	public void setIdCompensacion(Integer idCompensacion) {
		this.idCompensacion = idCompensacion;
	}

	public ShvTalReciboPreImpreso getReciboPreImpreso() {
		return reciboPreImpreso;
	}
	public void setReciboPreImpreso(ShvTalReciboPreImpreso reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	
}
