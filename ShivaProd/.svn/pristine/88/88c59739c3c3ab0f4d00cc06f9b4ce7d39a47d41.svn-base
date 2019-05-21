package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SHV_LGJ_ENVIO_REVERSIONES_ICE")
public class ShvLgjEnvioReversionesIce extends Modelo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_REVERSION_ICE")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_REVERSION_ICE", sequenceName = "SEQ_SHV_LGJ_REVERSION_ICE", allocationSize = 1)
	@Column(name="ID_ENVIO_REVERSION_ICE", updatable = false)
	private Long idEnvioReversionIce;
	
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	@Column(name="FECHA_ENVIO")
	private Date fechaEnvio;

	@Column(name = "CANT_REGISTROS_PROCESADOS")
	private Long cantidadRegistrosProcesados;

	public ShvLgjEnvioReversionesIce(){
	}


	public Long getIdEnvioReversionIce() {
		return idEnvioReversionIce;
	}


	public void setIdEnvioReversionIce(Long idEnvioReversionIce) {
		this.idEnvioReversionIce = idEnvioReversionIce;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public Date getFechaEnvio() {
		return fechaEnvio;
	}


	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}


	public Long getCantidadRegistrosProcesados() {
		return cantidadRegistrosProcesados;
	}


	public void setCantidadRegistrosProcesados(Long cantidadRegistrosProcesados) {
		this.cantidadRegistrosProcesados = cantidadRegistrosProcesados;
	}

}