package ar.com.telecom.shiva.base.mail;

import java.util.List;

public class Mail {

	private String remitente;
	private String remitenteNombre;
	private String destinatarioPara;
	private String destinatarioCC;
	private String destinatarioBCC;
	private String asunto;
	private StringBuffer cuerpo;
	private List<Adjunto> adjuntos;

	private Boolean debug = Boolean.FALSE;
	
	public Mail (String para){
		this.destinatarioPara = para;
	}
	
	public Mail (String para, String asunto, StringBuffer cuerpo) {
		this.destinatarioPara = para;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
	public Mail (String para, String cc,  String asunto, StringBuffer cuerpo) {
		this.destinatarioPara = para;
		this.destinatarioCC = cc;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
	public Mail (String para, String cc, String bcc,  String asunto, StringBuffer cuerpo) {
		this.destinatarioPara = para;
		this.destinatarioCC = cc;
		this.destinatarioBCC = bcc;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
		
	public Mail() {
	}

	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getDestinatarioPara() {
		return destinatarioPara;
	}
	public void setDestinatarioPara(String destinatarioPara) {
		this.destinatarioPara = destinatarioPara;
	}
	public String getDestinatarioCC() {
		return destinatarioCC;
	}
	public void setDestinatarioCC(String destinatarioCC) {
		this.destinatarioCC = destinatarioCC;
	}
	public String getDestinatarioBCC() {
		return destinatarioBCC;
	}
	public void setDestinatarioBCC(String destinatarioBCC) {
		this.destinatarioBCC = destinatarioBCC;
	}
	public String getAsunto() {
		if (asunto == null)
	    	asunto = "(correo enviado sin asunto)";
		
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public StringBuffer getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(StringBuffer cuerpo) {
		if (cuerpo == null)
	    	cuerpo = new StringBuffer("");
		
		this.cuerpo = cuerpo;
	}

	public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public String getRemitenteNombre() {
		return remitenteNombre;
	}

	public void setRemitenteNombre(String remitenteNombre) {
		this.remitenteNombre = remitenteNombre;
	}

	public List<Adjunto> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<Adjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}
	
}
