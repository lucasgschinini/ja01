package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the SHV_COB_ED_COBRO_ADJUNTO database table.
 * 
 */
@Entity
@Table(name="SHV_COB_ED_ADJUNTO")
public class ShvCobEdCobroAdjunto extends Modelo  {
	private static final long serialVersionUID = 1L;
	
	@Id
    private ShvCobEdCobroAdjuntoPk pk;

	public ShvCobEdCobroAdjuntoPk getPk() {
		return pk;
	}

	public void setPk(ShvCobEdCobroAdjuntoPk pk) {
		this.pk = pk;
	}	
}