package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;


/**
 * @author u572487 Guido.Settecerze
 * The persistent class for the SHV_COB_DESCOBRO_ADJUNTO database table.
 * 
 */
@Entity
@Table(name="SHV_COB_DESCOBRO_ADJUNTO")
public class ShvCobDescobroAdjunto extends Modelo  {
	private static final long serialVersionUID = 1L;
	
	@Id
    private ShvCobDescobroAdjuntoPk pk;

	@Enumerated(EnumType.STRING)
	@Column(name="MOTIVO_ADJUNTO")	
	private MotivoAdjuntoEnum motivoAdjunto;
	
	public ShvCobDescobroAdjuntoPk getPk() {
		return pk;
	}

	public void setPk(ShvCobDescobroAdjuntoPk pk) {
		this.pk = pk;
	}

	public MotivoAdjuntoEnum getMotivoAdjunto() {
		return motivoAdjunto;
	}

	public void setMotivoAdjunto(MotivoAdjuntoEnum motivoAdjunto) {
		this.motivoAdjunto = motivoAdjunto;
	}	
}