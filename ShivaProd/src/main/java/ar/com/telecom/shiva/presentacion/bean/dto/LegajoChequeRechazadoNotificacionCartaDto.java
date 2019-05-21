package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
/**
 * @author u578936 M.A.Uehara
 *
 */
public class LegajoChequeRechazadoNotificacionCartaDto extends DTO {
	private static final long serialVersionUID = 20170802L;

	private String calle;
	private String numero;
	private String piso;
	private String departamento;
	private String codigoPostal;
	private String localidad;
	private String provincia;
	private String fecha;
	private String analistaFirmante;
	private String numLineaServicio;
	private String fechaReembolso;
	private String nombreDestinatario;


	public LegajoChequeRechazadoNotificacionCartaDto() {}


	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}


	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}


	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}


	/**
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}


	/**
	 * @param piso the piso to set
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}


	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}


	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}


	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}


	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}


	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the analistaFirmante
	 */
	public String getAnalistaFirmante() {
		return analistaFirmante;
	}


	/**
	 * @param analistaFirmante the analistaFirmante to set
	 */
	public void setAnalistaFirmante(String analistaFirmante) {
		this.analistaFirmante = analistaFirmante;
	}


	public String getNumLineaServicio() {
		return numLineaServicio;
	}


	public void setNumLineaServicio(String numLineaServicio) {
		this.numLineaServicio = numLineaServicio;
	}


	public String getFechaReembolso() {
		return fechaReembolso;
	}


	public void setFechaReembolso(String fechaReembolso) {
		this.fechaReembolso = fechaReembolso;
	}


	/**
	 * @return the nombreDestinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}


	/**
	 * @param nombreDestinatario the nombreDestinatario to set
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	
}
