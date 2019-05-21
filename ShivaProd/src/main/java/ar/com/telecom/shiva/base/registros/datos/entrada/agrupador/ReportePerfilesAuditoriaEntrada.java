package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import ar.com.telecom.shiva.base.dto.REG;

public class ReportePerfilesAuditoriaEntrada extends REG {
	
	private static final long serialVersionUID = 1L;
	private String sistema;
	private String aplicacion;
	private String valor;
	private String perfil;
	private String usuario;
	private String apellido;
	private String nombre;
	private String registroArchivo;
	private int nroRegistro;
	private String descripcionError = "";
	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}
	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	/**
	 * @return the aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}
	/**
	 * @param aplicacion the aplicacion to set
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}
	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the registroArchivo
	 */
	public String getRegistroArchivo() {
		return registroArchivo;
	}
	/**
	 * @param registroArchivo the registroArchivo to set
	 */
	public void setRegistroArchivo(String registroArchivo) {
		this.registroArchivo = registroArchivo;
	}
	/**
	 * @return the nroRegistro
	 */
	public int getNroRegistro() {
		return nroRegistro;
	}
	/**
	 * @param nroRegistro the nroRegistro to set
	 */
	public void setNroRegistro(int nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	/**
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}
	/**
	 * @param descripcionError the descripcionError to set
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	
}
