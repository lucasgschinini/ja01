package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_MNU_MENU_PERFIL")
public class ShvMnuMenuPerfil extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_MENU_PERFIL")
	private Integer idMenuPerfil;

	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH})
	@JoinColumn(name = "ID_MENU", nullable=false)
	private ShvMnuMenu menu;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable=false)
	private ShvSegPerfil perfil;

	public Integer getIdMenuPerfil() {
		return idMenuPerfil;
	}

	public void setIdMenuPerfil(Integer idMenuPerfil) {
		this.idMenuPerfil = idMenuPerfil;
	}

	public ShvMnuMenu getMenu() {
		return menu;
	}

	public void setMenu(ShvMnuMenu menu) {
		this.menu = menu;
	}

	public ShvSegPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(ShvSegPerfil perfil) {
		this.perfil = perfil;
	}	
}
