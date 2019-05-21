package ar.com.telecom.shiva.base.ldap.elementos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;

public class RolLdap {

	private String descripcion;
    private List<UsuarioLdap> miembros = new ArrayList<UsuarioLdap>();
    
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<UsuarioLdap> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<UsuarioLdap> miembros) {
		this.miembros = miembros;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean esAnalistaTA() {
		return descripcion.toUpperCase().contains(Constantes.ANALISTA.toUpperCase() + "_" + Constantes.EMPRESA_TELECOM_ARGENTINA);
	}
}
