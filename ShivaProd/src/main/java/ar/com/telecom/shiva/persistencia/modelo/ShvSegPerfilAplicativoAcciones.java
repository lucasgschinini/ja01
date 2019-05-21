package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.AccionesDeUsuarioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_SEG_PERFIL_APLICATIVO_ACCS")
public class ShvSegPerfilAplicativoAcciones extends Modelo {

	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@Column(name="ID_ACCION")
	private Integer idAccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL_APLICATIVO", nullable=false)
	private ShvSegPerfilAplicativo perfilAplicativo;

	@Enumerated(EnumType.STRING)
	@Column(name="DESCRIPCION")
	private AccionesDeUsuarioEnum accion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIENE_PERMISO")
	private SiNoEnum tienePermiso;
	
	public ShvSegPerfilAplicativo getPerfilAplicativo() {
		return perfilAplicativo;
	}

	public void setPerfilAplicativo(ShvSegPerfilAplicativo perfilAplicativo) {
		this.perfilAplicativo = perfilAplicativo;
	}

	public AccionesDeUsuarioEnum getAccion() {
		return accion;
	}

	public void setAccion(AccionesDeUsuarioEnum accion) {
		this.accion = accion;
	}

	public SiNoEnum getTienePermiso() {
		return tienePermiso;
	}

	public void setTienePermiso(SiNoEnum tienePermiso) {
		this.tienePermiso = tienePermiso;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}
	
	
}
