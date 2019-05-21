package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_MNU_MENU")
public class ShvMnuMenu extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_MENU")
	private Integer idMenu;

	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="URL_ACCESO")
	private String urlAcceso;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MENU_ID_MENU")
	private ShvMnuMenu menuPadre;

	//@OneToMany(mappedBy="menuIdMenu", cascade = CascadeType.DETACH)
	//private Set<ShvMnuMenu> listaMenu = new HashSet<ShvMnuMenu>();

	@Column(name="ORDEN")
	private Integer orden;
	
	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlAcceso() {
		return urlAcceso;
	}

	public void setUrlAcceso(String urlAcceso) {
		this.urlAcceso = urlAcceso;
	}

	public ShvMnuMenu getMenuPadre() {
		return menuPadre;
	}

	public void setMenuPadre(ShvMnuMenu menuPadre) {
		this.menuPadre = menuPadre;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
