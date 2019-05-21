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
@Table(name = "SHV_BOL_BOLETA_CON_VALOR")
public class ShvBolBoletaConValor extends Modelo {

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "ID_BOLETA")
	@GeneratedValue(generator = "SEQ_SHV_BOL_BOLETA")
	@GenericGenerator(name = "SEQ_SHV_BOL_BOLETA", strategy = "foreign", parameters = @Parameter(name = "property", value = "boleta"))
	private Long idBoleta;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvBolBoleta boleta;

	public Long getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(Long idBoleta) {
		this.idBoleta = idBoleta;
	}

	public ShvBolBoleta getBoleta() {
		return boleta;
	}

	public void setBoleta(ShvBolBoleta boleta) {
		this.boleta = boleta;
	}

}
