package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHV_WF_TAREA_VALOR")
public class ShvWfTareaValor extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_TAREA")
	@GeneratedValue(generator = "SEQ_SHV_WF_TAREA")
	@GenericGenerator(name = "SEQ_SHV_WF_TAREA", strategy = "foreign", parameters = @Parameter(name="property", value="tarea"))
	private Long idTarea;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvWfTarea tarea;
	
	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;

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

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

}
