package ar.com.telecom.shiva.persistencia.modelo;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Embeddable
public class ShvLgjAdjuntoPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ID_LEGAJO")	
	private Long idLegajo;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ADJUNTO", referencedColumnName="ID_ADJUNTO") 
	private ShvDocDocumentoAdjunto documentoAdjunto;

	/**
	 * @return the idLegajo
	 */
	public Long getIdLegajo() {
		return idLegajo;
	}

	/**
	 * @param idLegajo the idLegajo to set
	 */
	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}

	/**
	 * @return the documentoAdjunto
	 */
	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	/**
	 * @param documentoAdjunto the documentoAdjunto to set
	 */
	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}
	

}