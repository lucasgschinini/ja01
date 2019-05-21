package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author u572487 Guido.Settecerze
 */
@Embeddable
public class ShvCobDescobroAdjuntoPk implements Serializable{
	private static final long serialVersionUID = -1L;

	@Column(name="ID_DESCOBRO")	
	private Long idDescobro;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ADJUNTO", referencedColumnName="ID_ADJUNTO") 
	private ShvDocDocumentoAdjunto documentoAdjunto;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name="MOTIVO_ADJUNTO")	
//	private MotivoAdjuntoEnum motivoAdjunto;

	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public Long getIdDescobro() {
		return idDescobro;
	}

	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

//	public MotivoAdjuntoEnum getMotivoAdjunto() {
//		return motivoAdjunto;
//	}
//
//	public void setMotivoAdjunto(MotivoAdjuntoEnum motivoAdjunto) {
//		this.motivoAdjunto = motivoAdjunto;
//	}
}
