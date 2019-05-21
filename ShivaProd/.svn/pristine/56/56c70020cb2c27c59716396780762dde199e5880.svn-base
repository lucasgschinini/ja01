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
@Table(name = "SHV_REL_REGISTRO_AVC_VALOR")
public class ShvRelRegistroAvcValor extends Modelo{

private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_REL_REGISTRO_AVC_VALOR")
//	@SequenceGenerator(name="SEQ_SHV_REL_REGISTRO_AVC_VALOR", sequenceName="SEQ_SHV_REL_REGISTRO_AVC_VALOR", allocationSize = 1)
	//TODO la secuencia no esta creada en la base
	@Column(name="ID_REL_REGISTRO_AVC_VALOR")
	private Long idRegistroAvcValor;
	

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_VALOR") 
	private ShvValValor valor;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_REGISTRO_AVC") 
	private ShvAvcRegistroAvc registroAvc;

	public Long getIdRegistroAvcValor() {
		return idRegistroAvcValor;
	}

	public void setIdRegistroAvcValor(Long idRegistroAvcValor) {
		this.idRegistroAvcValor = idRegistroAvcValor;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}

	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}
}
