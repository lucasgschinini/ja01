package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the SHV_LGJ_ADJUNTO database table.
 * 
 */
@Entity
@Table(name = "SHV_LGJ_ADJUNTO")
public class ShvLgjAdjunto extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	private ShvLgjAdjuntoPk pk;

	/**
	 * @return the pk
	 */
	public ShvLgjAdjuntoPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(ShvLgjAdjuntoPk pk) {
		this.pk = pk;
	}

}