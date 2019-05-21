package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;

/**
 * @author u578936 M.A.Uehara
 *
 */

@Entity
@Table(name = "SHV_LGJ_NOTIFICACION_CARTA")
public class ShvLgjNotificacionCarta extends Modelo {
	private static final long serialVersionUID = 20170802L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_NOTIFICACION_CARTA")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_NOTIFICACION_CARTA", sequenceName = "SEQ_SHV_LGJ_NOTIFICACION_CARTA", allocationSize = 1)
	@Column(name="ID_NOTIFICACION_CARTA")
	private Long idNotificacionCarta;
	// Datos del destinatario
	@Column(name="CALLE")
	private String calle;

	@Column(name="NUMERO")
	private String numero;

	@Column(name="PISO")
	private String piso;

	@Column(name="DEPARTAMENTO")
	private String departamento;
	
	@Column(name="CODIGO_POSTAL")
	private String codigoPostal;

	@Column(name="LOCALIDAD")
	private String localidad;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
	private ShvParamProvincia provincia;
	
	// Datos de la carta
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="ANALISTA_FIRMANTE")
	private String analistaFirmante;

	@Column(name="NRO_LINEA")
	private String nroLinea;
	
	@Column(name="FECHA_REEMBOLSO")
	private Date fechaReembolso;

	@Column(name="NOMBRE_DESTINATARIO")
	private String nombreDestinatario;

	public ShvLgjNotificacionCarta() {}

	/**
	 * @return the idNotificacionCarta
	 */
	public Long getIdNotificacionCarta() {
		return idNotificacionCarta;
	}

	/**
	 * @param idNotificacionCarta the idNotificacionCarta to set
	 */
	public void setIdNotificacionCarta(Long idNotificacionCarta) {
		this.idNotificacionCarta = idNotificacionCarta;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * @param piso the piso to set
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return the provincia
	 */
	public ShvParamProvincia getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(ShvParamProvincia provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the analistaFirmante
	 */
	public String getAnalistaFirmante() {
		return analistaFirmante;
	}

	/**
	 * @param analistaFirmante the analistaFirmante to set
	 */
	public void setAnalistaFirmante(String analistaFirmante) {
		this.analistaFirmante = analistaFirmante;
	}



	/**
	 * @return the nroLinea
	 */
	public String getNroLinea() {
		return nroLinea;
	}

	/**
	 * @param nroLinea the nroLinea to set
	 */
	public void setNroLinea(String nroLinea) {
		this.nroLinea = nroLinea;
	}

	public Date getFechaReembolso() {
		return fechaReembolso;
	}

	public void setFechaReembolso(Date fechaReembolso) {
		this.fechaReembolso = fechaReembolso;
	}

	/**
	 * @return the nombreDestinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * @param nombreDestinatario the nombreDestinatario to set
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	
}
