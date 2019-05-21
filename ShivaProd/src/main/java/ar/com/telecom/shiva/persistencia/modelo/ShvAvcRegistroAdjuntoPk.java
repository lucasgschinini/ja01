package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvAvcRegistroAdjuntoPk implements Serializable{

 
	private static final long serialVersionUID = -1L;

	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_REGISTRO_AVC", referencedColumnName="ID_REGISTRO_AVC") 
	private ShvAvcRegistroAvc registroAvc;

	//U562201 - Sprint 4
	@OneToOne(cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ADJUNTO", referencedColumnName="ID_ADJUNTO") 
	private ShvDocDocumentoAdjunto documentoAdjunto;


	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}

	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
	}

	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}


}
