package ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recepcionNovedadesDocumentosSAPEntrada", propOrder = {
		"tipoNovedad", "usuario", "fechaCreacionContradocumento", "documentoOriginal",
		"contraDocumentoCancelatorio"})
@XmlRootElement
public class RecepcionNovedadesDocumentosSAPEntrada extends Object implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "tipoNovedad", required = true)
	private String tipoNovedad;
	
	@XmlElement(name = "usuario", required = true)
	private String usuario;
	
	@XmlElement(name = "fechaCreacionContradocumento", required = true)
	private Long fechaCreacionContradocumento;
	
	@XmlElement(name = "documentoOriginal", required = true)
	private DocumentoSap documentoOriginal;
	
	@XmlElement(name = "contraDocumentoCancelatorio", required = true)
	private DocumentoSap contraDocumentoCancelatorio;
	
	
	public String getTipoNovedad() {
		return tipoNovedad;
	}

	public void setTipoNovedad(String tipoNovedad) {
		this.tipoNovedad = tipoNovedad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getFechaCreacionContradocumento() {
		return fechaCreacionContradocumento;
	}

	public void setFechaCreacionContradocumento(Long fechaCreacionContradocumento) {
		this.fechaCreacionContradocumento = fechaCreacionContradocumento;
	}

	public DocumentoSap getDocumentoOriginal() {
		return documentoOriginal;
	}

	public void setDocumentoOriginal(DocumentoSap documentoOriginal) {
		this.documentoOriginal = documentoOriginal;
	}

	public DocumentoSap getContraDocumentoCancelatorio() {
		return contraDocumentoCancelatorio;
	}

	public void setContraDocumentoCancelatorio(
			DocumentoSap contraDocumentoCancelatorio) {
		this.contraDocumentoCancelatorio = contraDocumentoCancelatorio;
	}

}
