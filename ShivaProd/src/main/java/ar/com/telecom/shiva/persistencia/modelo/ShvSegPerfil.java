package ar.com.telecom.shiva.persistencia.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_SEG_PERFIL")
public class ShvSegPerfil extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_PERFIL")
	private Integer idPerfil;

	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
	@JoinColumn(name = "ID_EMPRESA")
	private ShvParamEmpresa empresa;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
	@JoinColumn(name = "ID_SEGMENTO")
	private ShvParamSegmento segmento;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
	@JoinColumn(name = "ID_PERFIL_APLICATIVO", nullable=false)
	private ShvSegPerfilAplicativo perfilAplicativo;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH}, mappedBy="perfil")
	private Set<ShvMnuMenuPerfil> menuPerfil = new HashSet<ShvMnuMenuPerfil>();
	
	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	public ShvSegPerfilAplicativo getPerfilAplicativo() {
		return perfilAplicativo;
	}

	public void setPerfilAplicativo(ShvSegPerfilAplicativo perfilAplicativo) {
		this.perfilAplicativo = perfilAplicativo;
	}

	public Set<ShvMnuMenuPerfil> getMenuPerfil() {
		return menuPerfil;
	}

	public void setMenuPerfil(Set<ShvMnuMenuPerfil> menuPerfil) {
		this.menuPerfil = menuPerfil;
	}
}
