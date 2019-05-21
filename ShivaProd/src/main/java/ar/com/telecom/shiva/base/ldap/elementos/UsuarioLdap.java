package ar.com.telecom.shiva.base.ldap.elementos;

import java.util.Collection;


public class UsuarioLdap {
	
	private String tuid;
	private String nombreCompleto;
	private String mail;
	
	private Collection<RolLdap> roles;

	public String getTuid() {
		return tuid;
	}

	public void setTuid(String tuid) {
		this.tuid = tuid;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Collection<RolLdap> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RolLdap> roles) {
		this.roles = roles;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}