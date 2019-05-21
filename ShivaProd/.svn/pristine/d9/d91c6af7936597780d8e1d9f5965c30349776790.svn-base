package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;


public class ComprobanteDto extends DTO {

	private static final long serialVersionUID = 1L;
	
	private Long idComprobante;
	private String nombreArchivo;
	private String descripcionArchivo;
	private byte[] documento;
	private Long ordenTabla;// se usa para saber el orden que tienen en la tabla del jsp en donde se use
	private String usuarioCreacion;
	private Long idPantallaComprobante;
	private String motivoAdjunto;
	
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getDescripcionArchivo() {
		return descripcionArchivo;
	}
	public void setDescripcionArchivo(String descripcionArchivo) {
		this.descripcionArchivo = descripcionArchivo;
	}
	public byte[] getDocumento() {
		return documento;
	}
	public void setDocumento(byte[] file) {
		this.documento = file;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		ComprobanteDto objComprobanteDto = (ComprobanteDto) obj;
		
		if (this.getIdComprobante() != null && objComprobanteDto.getIdComprobante() != null) {
			return this.getIdComprobante().equals(objComprobanteDto.getIdComprobante());
		}
		if (this.getId() != null && objComprobanteDto.getId() != null) {
			return this.getId().equals(objComprobanteDto.getId());
		}
		
		return false;
	}
	public Long getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(Long idComprobante) {
		this.idComprobante = idComprobante;
	}
	public Long getOrdenTabla() {
		return ordenTabla;
	}
	public void setOrdenTabla(Long ordenTabla) {
		this.ordenTabla = ordenTabla;
	}
	public Long getIdPantallaComprobante() {
		return idPantallaComprobante;
	}
	public void setIdPantallaComprobante(Long idPantallaComprobante) {
		this.idPantallaComprobante = idPantallaComprobante;
	}
	public String getMotivoAdjunto() {
		return motivoAdjunto;
	}
	public void setMotivoAdjunto(String motivoAdjunto) {
		this.motivoAdjunto = motivoAdjunto;
	}
	
	
	
}