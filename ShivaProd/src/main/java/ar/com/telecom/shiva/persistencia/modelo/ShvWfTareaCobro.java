package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;

@Entity
@Table(name = "SHV_WF_TAREA_COBRO")
public class ShvWfTareaCobro extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_TAREA")
	@GeneratedValue(generator = "SEQ_SHV_WF_TAREA")
	@GenericGenerator(name = "SEQ_SHV_WF_TAREA", strategy = "foreign", parameters = @Parameter(name="property", value="tarea"))
	private Long idTarea;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvWfTarea tarea;
	
	@Column(name = "ID_COBRO", nullable = false) 
	private Long idCobro;
	
	@Column(name = "IMPORTE", nullable = false) 
	private BigDecimal importe;
	
	@Column(name = "ID_CLIENTE_LEGADO", nullable = false) 
	private Long idClienteLegado;
	
	@Column(name = "RAZON_SOCIAL_CLIENTE", nullable = false) 
	private String razonSocial;
	
	@Column(name = "ID_OPERACION", nullable = false) 
	private Long idOperacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE")
	private MonedaEnum  monedaImporte;
	

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public ShvWfTarea getTarea() {
		return tarea;
	}

	public void setTarea(ShvWfTarea tarea) {
		this.tarea = tarea;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}

}
