package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "SHV_MAS_OPER_MASIVA_ADJUNTO")
public class ShvMasOperacionMasivaAdjunto extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	private ShvMasOperacionMasivaAdjuntoPk operacionMasivaAdjuntoPk;

	/**
	 * @return the operacionMasivaAdjuntoPk
	 */
	public ShvMasOperacionMasivaAdjuntoPk getOperacionMasivaAdjuntoPk() {
		return operacionMasivaAdjuntoPk;
	}

	/**
	 * @param operacionMasivaAdjuntoPk the operacionMasivaAdjuntoPk to set
	 */
	public void setOperacionMasivaAdjuntoPk(
			ShvMasOperacionMasivaAdjuntoPk operacionMasivaAdjuntoPk) {
		this.operacionMasivaAdjuntoPk = operacionMasivaAdjuntoPk;
	}
}
