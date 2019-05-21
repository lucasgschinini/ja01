package ar.com.telecom.shiva.base.mail;

/**
 * Clase de adjuntos para mail 
 * @author nicolas.i.voget
 *
 */
public class Adjunto {

	private byte[] adjunto;
	private String nombreAdjunto;
	private String extension;

	/**
	 * 
	 * @param adjunto
	 * @param nombreAdjunto
	 * @param extension
	 */
	public Adjunto(byte[] adjunto, String nombreAdjunto, String extension) {
		this.adjunto = adjunto;
		this.nombreAdjunto = nombreAdjunto;
		this.extension = extension;
	}

	public byte[] getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}

	public String getNombreAdjunto() {
		return nombreAdjunto;
	}

	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getNombreArchivoAdjuntar(){
		return this.nombreAdjunto + "." + this.extension;
	}
	
}
