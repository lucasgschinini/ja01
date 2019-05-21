package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MotivoCobroUsoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_MOTIVO_COBRO")
public class ShvParamMotivoCobro extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_MOTIVO_COBRO")
	private Integer idMotivoCobro;
	
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column (name="USO")
	private MotivoCobroUsoEnum uso;

	/**
	 * @return the idMotivoCobro
	 */
	public Integer getIdMotivoCobro() {
		return idMotivoCobro;
	}

	/**
	 * @param idMotivoCobro the idMotivoCobro to set
	 */
	public void setIdMotivoCobro(Integer idMotivo) {
		this.idMotivoCobro = idMotivo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the uso
	 */
	public MotivoCobroUsoEnum getUso() {
		return uso;
	}

	/**
	 * @param uso the uso to set
	 */
	public void setUso(MotivoCobroUsoEnum uso) {
		this.uso = uso;
	}

	
}