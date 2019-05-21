package ar.com.telecom.shiva.presentacion.bean.dto;



import java.util.List;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;


public class TalonarioDto extends GestionDto {

	private static final long serialVersionUID = 1L;

	private String idTalonario;
	private String empresa;
	private String segmento;
	private String nroSerie;

	private String rangoDesde;
	private String rangoHasta;
	
	private Boolean errorNingunaModificacion;
	private String descripcionNingunaModificacion;
	
	private String idEstado;
	private String estado;
	private String idUsuarioGestor;
	private String usuarioGestor;
	
	private String usuarioAlta;
	private String nombreUsuarioAlta;
	private String mailUsuarioAlta;
	
	private String fechaAsignacion;
	private String usuarioAsignacion;
	private String nombreUsuarioAsignacion;
	private String mailUsuarioAsignacion;
	
	private String fechaRendicion;
	private String usuarioRendicion;
	private String nombreUsuarioRendicion;
	private String mailUsuarioRendicion;
	
	private String fechaAprobacionRendicion;
	private String usuarioAprobacionRendicion;
	private String nombreUsuarioAprobacionRendicion;
	private String mailUsuarioAprobacionRendicion;
	
	private String observaciones;
	private Boolean errorRechazo;
	
	private boolean errorRendicion;
	private String errorRendicionMensaje;
	
	private List<ReciboPreImpresoDto> listaRecibos;
	
	public TalonarioDto() {
	}
	
	public TalonarioDto(Integer id) {
		super.setId(id);
	}

	public boolean getEsAsignadoSupervisor() {
		return (Estado.TAL_ASIGNADO_SUPERVISOR.descripcion().equalsIgnoreCase(estado));
	}
	public boolean getEsDerivAdminTalonario() {
		return (Estado.TAL_DERIVADO_ADMINISTRADOR.descripcion().equalsIgnoreCase(estado));
	}
	
	public boolean getEsAsignado(){
		return (Estado.TAL_ASIGNADO_GESTOR.descripcion().equalsIgnoreCase(estado));
	}
	
	public boolean getEsAsignadoGestorORendicionRechazada() {
		return (Estado.TAL_RENDICION_RECHAZADA.descripcion().equalsIgnoreCase(estado) 
				|| Estado.TAL_ASIGNADO_GESTOR.descripcion().equalsIgnoreCase(estado));
	}
	
	
	/*************************************************
	 *              GETTERS & SETTERS                *
	 ************************************************/
	
	public String getUsuarioGestor() {
		return usuarioGestor;
	}
	
	public void setUsuarioGestor(String gestorNombre) {
		this.usuarioGestor = gestorNombre;
	}

	public Boolean getErrorRechazo() {
		return errorRechazo;
	}
	
	public void setErrorRechazo(Boolean errorRechazo) {
		this.errorRechazo = errorRechazo;
	}
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdTalonario() {
		return idTalonario;
	}

	public void setIdTalonario(String idTalonario) {
		this.idTalonario = idTalonario;
	}


	public String getRangoDesde() {
		return rangoDesde;
	}

	public void setRangoDesde(String rangoDesde) {
		this.rangoDesde = rangoDesde;
	}

	public String getRangoHasta() {
		return rangoHasta;
	}

	public void setRangoHasta(String rangoHasta) {
		this.rangoHasta = rangoHasta;
	}


	public String getIdUsuarioGestor() {
		return idUsuarioGestor;
	}

	public void setIdUsuarioGestor(String usuarioGestor) {
		this.idUsuarioGestor = usuarioGestor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getErrorNingunaModificacion() {
		return errorNingunaModificacion;
	}

	public void setErrorNingunaModificacion(Boolean errorNingunaModificacion) {
		this.errorNingunaModificacion = errorNingunaModificacion;
	}

	public String getDescripcionNingunaModificacion() {
		return descripcionNingunaModificacion;
	}

	public void setDescripcionNingunaModificacion(
			String descripcionNingunaModificacion) {
		this.descripcionNingunaModificacion = descripcionNingunaModificacion;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}


	public List<ReciboPreImpresoDto> getListaRecibos() {
		return listaRecibos;
	}

	public void setListaRecibos(List<ReciboPreImpresoDto> listaRecibos) {
		this.listaRecibos = listaRecibos;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public String getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}


	public String getFechaRendicion() {
		return fechaRendicion;
	}

	public void setFechaRendicion(String fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
	}

	public String getUsuarioRendicion() {
		return usuarioRendicion;
	}

	public void setUsuarioRendicion(String usuarioRendicion) {
		this.usuarioRendicion = usuarioRendicion;
	}

	public String getFechaAprobacionRendicion() {
		return fechaAprobacionRendicion;
	}

	public void setFechaAprobacionRendicion(String fechaAprobacionRendicion) {
		this.fechaAprobacionRendicion = fechaAprobacionRendicion;
	}

	public String getUsuarioAprobacionRendicion() {
		return usuarioAprobacionRendicion;
	}

	public void setUsuarioAprobacionRendicion(String usuarioAprobacionRendicion) {
		this.usuarioAprobacionRendicion = usuarioAprobacionRendicion;
	}

	public String getNombreUsuarioAlta() {
		return nombreUsuarioAlta;
	}

	public void setNombreUsuarioAlta(String nombreUsuarioAlta) {
		this.nombreUsuarioAlta = nombreUsuarioAlta;
	}

	public String getNombreUsuarioAsignacion() {
		return nombreUsuarioAsignacion;
	}

	public void setNombreUsuarioAsignacion(String nombreUsuarioAsignacion) {
		this.nombreUsuarioAsignacion = nombreUsuarioAsignacion;
	}

	public String getNombreUsuarioRendicion() {
		return nombreUsuarioRendicion;
	}

	public void setNombreUsuarioRendicion(String nombreUsuarioRendicion) {
		this.nombreUsuarioRendicion = nombreUsuarioRendicion;
	}

	public String getNombreUsuarioAprobacionRendicion() {
		return nombreUsuarioAprobacionRendicion;
	}

	public void setNombreUsuarioAprobacionRendicion(
			String nombreUsuarioAprobacionRendicion) {
		this.nombreUsuarioAprobacionRendicion = nombreUsuarioAprobacionRendicion;
	}

	public String getMailUsuarioAlta() {
		return mailUsuarioAlta;
	}

	public void setMailUsuarioAlta(String mailUsuarioAlta) {
		this.mailUsuarioAlta = mailUsuarioAlta;
	}

	public String getMailUsuarioAsignacion() {
		return mailUsuarioAsignacion;
	}

	public void setMailUsuarioAsignacion(String mailUsuarioAsignacion) {
		this.mailUsuarioAsignacion = mailUsuarioAsignacion;
	}

	public String getMailUsuarioRendicion() {
		return mailUsuarioRendicion;
	}

	public void setMailUsuarioRendicion(String mailUsuarioRendicion) {
		this.mailUsuarioRendicion = mailUsuarioRendicion;
	}

	public String getMailUsuarioAprobacionRendicion() {
		return mailUsuarioAprobacionRendicion;
	}

	public void setMailUsuarioAprobacionRendicion(
			String mailUsuarioAprobacionRendicion) {
		this.mailUsuarioAprobacionRendicion = mailUsuarioAprobacionRendicion;
	}

	public boolean isErrorRendicion() {
		return errorRendicion;
	}

	public void setErrorRendicion(boolean errorRendicion) {
		this.errorRendicion = errorRendicion;
	}

	public String getErrorRendicionMensaje() {
		return errorRendicionMensaje;
	}

	public void setErrorRendicionMensaje(String errorRendicionMensaje) {
		this.errorRendicionMensaje = errorRendicionMensaje;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	
}