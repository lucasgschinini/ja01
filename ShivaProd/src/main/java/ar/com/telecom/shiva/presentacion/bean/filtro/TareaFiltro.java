package ar.com.telecom.shiva.presentacion.bean.filtro;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;


public class TareaFiltro extends Filtro {

	private static final long serialVersionUID = 1L;
	
	public TareaFiltro(){}
	
	private Integer idWorkflow;
	
	private TipoTareaEnum tipoTarea;
	private TipoTareaEstadoEnum estadoTarea;
	private SistemaEnum sistema;
	private SociedadEnum sociedad;
	public SistemaEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	public SociedadEnum getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	private String usuarioCreacion;
	private String perfilAsignacion;
	

	public Integer getIdWorkflow() {
		return idWorkflow;
	}

	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getPerfilAsignacion() {
		return perfilAsignacion;
	}

	public void setPerfilAsignacion(String perfilAsignacion) {
		this.perfilAsignacion = perfilAsignacion;
	}

	public TipoTareaEstadoEnum getEstadoTarea() {
		return estadoTarea;
	}

	public void setEstadoTarea(TipoTareaEstadoEnum estadoTarea) {
		this.estadoTarea = estadoTarea;
	}

	public TipoTareaEnum getTipoTarea() {
		return tipoTarea;
	}

	public void setTipoTarea(TipoTareaEnum tipoTarea) {
		this.tipoTarea = tipoTarea;
	}
	
	
	

}
