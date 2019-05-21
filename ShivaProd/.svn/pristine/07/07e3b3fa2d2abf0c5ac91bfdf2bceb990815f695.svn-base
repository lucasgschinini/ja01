package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;

@Embeddable
public class ShvCobEdCobroAdjuntoPk implements Serializable{
	private static final long serialVersionUID = -1L;

	@Column(name="ID_COBRO")	
	private Long idCobro;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ADJUNTO", referencedColumnName="ID_ADJUNTO") 
	private ShvDocDocumentoAdjunto documentoAdjunto;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MOTIVO_ADJUNTO")	
	private MotivoAdjuntoEnum motivoAdjunto;
	

	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public MotivoAdjuntoEnum getMotivoAdjunto() {
		return motivoAdjunto;
	}

	public void setMotivoAdjunto(MotivoAdjuntoEnum motivoAdjunto) {
		this.motivoAdjunto = motivoAdjunto;
	}

	


}
