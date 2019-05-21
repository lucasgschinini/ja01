package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_REL_REGISTRO_AVC_BOLETA")
public class ShvRelRegistroAvcBoleta extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_REL_REGISTRO_AVC_BOLETA")
//	@SequenceGenerator(name="SEQ_SHV_REL_REGISTRO_AVC_BOLETA", sequenceName="SEQ_SHV_REL_REGISTRO_AVC_BOLETA", allocationSize = 1)
	//TODO la secuencia no esta creada en la base
	@Column(name="ID_REL_REGISTRO_AVC_BOLETA")
	private Long idRegistroAvcBoleta;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_BOLETA") 
	private ShvBolBoleta boleta;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_REGISTRO_AVC") 
	private ShvAvcRegistroAvc registroAvc;

	public Long getIdRegistroAvcBoleta() {
		return idRegistroAvcBoleta;
	}

	public void setIdRegistroAvcBoleta(Long idRegistroAvcBoleta) {
		this.idRegistroAvcBoleta = idRegistroAvcBoleta;
	}

	public ShvBolBoleta getBoleta() {
		return boleta;
	}

	public void setBoleta(ShvBolBoleta boleta) {
		this.boleta = boleta;
	}

	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}

	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}
}
