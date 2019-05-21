package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoGestorEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_GESTOR")
public class ShvParamGestor extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
 	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_PARAM_GESTOR")
 	@SequenceGenerator(name="SEQ_SHV_PARAM_GESTOR", sequenceName="SEQ_SHV_PARAM_GESTOR", allocationSize = 1)
	@Column (name="ID_GESTOR")
	private Integer idGestor;
	 
	@Column (name="ID_USUARIO")
	private String idUsuario;

	@Column (name="NOMBRE_Y_APELLIDO")
	private String nombreYApellido;
	
	@Enumerated(EnumType.STRING)
	@Column (name="ESTADO")
	private EstadoGestorEnum estado;

	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public Integer getIdGestor() {
		return idGestor;
	}
	
	public void setIdGestor(Integer idGestor) {
		this.idGestor = idGestor;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNombreYApellido() {
		return nombreYApellido;
	}
	
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}
	
	public EstadoGestorEnum getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoGestorEnum estado) {
		this.estado = estado;
	}
	
}
