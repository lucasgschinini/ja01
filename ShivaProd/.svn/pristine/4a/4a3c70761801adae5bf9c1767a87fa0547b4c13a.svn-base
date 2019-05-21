package ar.com.telecom.shiva.persistencia.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ShvMasOperacionMasivaAdjuntoPk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn (name = "ID_OPERACION_MASIVA")
	private ShvMasOperacionMasiva operacionMasiva;
	
	@OneToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn (name = "ID_ADJUNTO")
	private ShvDocDocumentoAdjunto archivoAdjunto;

	public ShvMasOperacionMasiva getOperacionMasiva() {
		return operacionMasiva;
	}

	public void setOperacionMasiva(ShvMasOperacionMasiva operacionMasiva) {
		this.operacionMasiva = operacionMasiva;
	}

	public ShvDocDocumentoAdjunto getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(ShvDocDocumentoAdjunto archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
}
