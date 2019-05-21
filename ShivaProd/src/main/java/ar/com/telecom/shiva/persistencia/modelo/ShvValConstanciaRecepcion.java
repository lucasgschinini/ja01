package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoValorConstRecepEnum;

@Entity
@Table(name = "SHV_VAL_CONSTANCIA_RECEPCION")
public class ShvValConstanciaRecepcion extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_VAL_CONSTANCIA_RECEP")
	@SequenceGenerator(name = "SEQ_SHV_VAL_CONSTANCIA_RECEP", sequenceName = "SEQ_SHV_VAL_CONSTANCIA_RECEP", allocationSize=1)
	@Column(name = "ID_CONSTANCIA_RECEPCION", nullable = false)
	private Integer idConstanciaRecepcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private EstadoValorConstRecepEnum estado;

	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Column(name = "USUARIO_CREACION", nullable = false)
	private String usuaroCreacion;

	@Column(name = "FECHA_ANULACION")
	private Date fechaAnulacion;

	@Column(name = "USUARIO_ANULACION")
	private String usuarioAnulacion;

	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CONSTANCIA_RECEPCION_PADRE", referencedColumnName = "ID_CONSTANCIA_RECEPCION", nullable=true)
	private ShvValConstanciaRecepcion idConstanciaRecepcionPadre;

	public Integer getIdConstanciaRecepcion() {
		return idConstanciaRecepcion;
	}

	public void setIdConstanciaRecepcion(Integer idConstanciaRecepcion) {
		this.idConstanciaRecepcion = idConstanciaRecepcion;
	}

	public EstadoValorConstRecepEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoValorConstRecepEnum estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuaroCreacion() {
		return usuaroCreacion;
	}

	public void setUsuaroCreacion(String usuaroCreacion) {
		this.usuaroCreacion = usuaroCreacion;
	}

	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}

	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
	}

	public ShvValConstanciaRecepcion getIdConstanciaRecepcionPadre() {
		return idConstanciaRecepcionPadre;
	}

	public void setIdConstanciaRecepcionPadre(
			ShvValConstanciaRecepcion idConstanciaRecepcionPadre) {
		this.idConstanciaRecepcionPadre = idConstanciaRecepcionPadre;
	}

}
