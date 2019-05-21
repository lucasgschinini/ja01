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
@Table(name = "SHV_AVC_REG_AVC_EFECTIVO")
public class ShvAvcRegistroAvcEfectivo extends Modelo{
	
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID_REGISTRO_AVC")
	@GeneratedValue(generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@GenericGenerator(name = "SEQ_SHV_AVC_REGISTRO_AVC", strategy="foreign", parameters=@Parameter(name="property", value="deposito")) 
	private Long idRegistroAvc;
	
	@OneToOne  
	@PrimaryKeyJoinColumn  
	private ShvAvcRegistroAvcDeposito deposito;

	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}

	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}

	public ShvAvcRegistroAvcDeposito getDeposito() {
		return deposito;
	}

	public void setDeposito(ShvAvcRegistroAvcDeposito deposito) {
		this.deposito = deposito;
	}

	
}
