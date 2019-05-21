package ar.com.telecom.shiva.persistencia.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "SHV_SEG_PERFIL_APLICATIVO")
public class ShvSegPerfilAplicativo extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_PERFIL_APLICATIVO")
	private String idPerfilAplicativo;

	@Column(name="DESCRIPCION")
	private String descripcion;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH}, mappedBy="perfilAplicativo")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvSegPerfilAplicativoAcciones> acciones = new HashSet<ShvSegPerfilAplicativoAcciones>(0);
	
	
	public String getIdPerfilAplicativo() {
		return idPerfilAplicativo;
	}

	public void setIdPerfilAplicativo(String idPerfilAplicativo) {
		this.idPerfilAplicativo = idPerfilAplicativo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ShvSegPerfilAplicativoAcciones> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<ShvSegPerfilAplicativoAcciones> acciones) {
		this.acciones = acciones;
	}
}
