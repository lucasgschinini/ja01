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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.AcuseReciboEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoContactoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificadoNotificacion;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_LGJ_NOTIFICACION")
public class ShvLgjNotificacion extends Modelo {
	private static final long serialVersionUID = 20170802L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_LGJ_NOTIFICACION")
	@SequenceGenerator(name = "SEQ_SHV_LGJ_NOTIFICACION", sequenceName = "SEQ_SHV_LGJ_NOTIFICACION", allocationSize = 1)
	@Column(name="ID_NOTIFICACION", updatable = false)
	private Long idNotificacion;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_LEGAJO_CHEQUE_RECHAZADO", referencedColumnName="ID_LEGAJO_CHEQUE_RECHAZADO") 
	private ShvLgjLegajoChequeRechazadoSimplificadoNotificacion legajoChequeRechazado;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_NOTIFICACION")
	private TipoNotificacionEnum tipoNotificacion;

	@Column(name="DATOS_RECEPTOR")
	private String datosReceptor;

	@Column(name="FECHA_CONTACTO")
	private Date fechaContacto;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_CONTACTO")
	private TipoContactoEnum tipoContacto;

	@Column(name="DATOS_CONTACTO")
	private String datosContacto;

	@Enumerated(EnumType.STRING)
	@Column(name="ACUSE_RECIBO")
	private AcuseReciboEnum acuseRecibo;

	@Column(name="FECHA_RECEPCION")
	private Date fechaRecepcion;

	@Column(name="FECHA_ALTA")
	private Date fechaCreacion;

	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;

	@Column(name="OBSERVACIONES")
	private String observaciones;

	@Column(name="USUARIO_DESC")
	private String usuarioDesc;
	
	@Column(name="USUARIO")
	private String usuarioAlta;

	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoNotificacionEnum estado;

	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name="ID_NOTIFICACION_CARTA", referencedColumnName="ID_NOTIFICACION_CARTA")
	private ShvLgjNotificacionCarta carta;

	public ShvLgjNotificacion() {}


	/**
	 * @return the idNotificacion
	 */
	public Long getIdNotificacion() {
		return idNotificacion;
	}

	/**
	 * @return the tipoNotificacion
	 */
	public TipoNotificacionEnum getTipoNotificacion() {
		return tipoNotificacion;
	}


	/**
	 * @param tipoNotificacion the tipoNotificacion to set
	 */
	public void setTipoNotificacion(TipoNotificacionEnum tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}


	/**
	 * @return the datosReceptor
	 */
	public String getDatosReceptor() {
		return datosReceptor;
	}


	/**
	 * @param datosReceptor the datosReceptor to set
	 */
	public void setDatosReceptor(String datosReceptor) {
		this.datosReceptor = datosReceptor;
	}


	/**
	 * @return the fechaContacto
	 */
	public Date getFechaContacto() {
		return fechaContacto;
	}


	/**
	 * @param fechaContacto the fechaContacto to set
	 */
	public void setFechaContacto(Date fechaContacto) {
		this.fechaContacto = fechaContacto;
	}


	/**
	 * @return the tipoContacto
	 */
	public TipoContactoEnum getTipoContacto() {
		return tipoContacto;
	}


	/**
	 * @param tipoContacto the tipoContacto to set
	 */
	public void setTipoContacto(TipoContactoEnum tipoContacto) {
		this.tipoContacto = tipoContacto;
	}


	/**
	 * @return the datosContacto
	 */
	public String getDatosContacto() {
		return datosContacto;
	}


	/**
	 * @param datosContacto the datosContacto to set
	 */
	public void setDatosContacto(String datosContacto) {
		this.datosContacto = datosContacto;
	}


	/**
	 * @return the acuseRecibo
	 */
	public AcuseReciboEnum getAcuseRecibo() {
		return acuseRecibo;
	}


	/**
	 * @param acuseRecibo the acuseRecibo to set
	 */
	public void setAcuseRecibo(AcuseReciboEnum acuseRecibo) {
		this.acuseRecibo = acuseRecibo;
	}


	/**
	 * @return the fechaRecepcion
	 */
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}


	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}


	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}


	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	/**
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}


	/**
	 * @param usuarioAlta the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}


	/**
	 * @return the estado
	 */
	public EstadoNotificacionEnum getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoNotificacionEnum estado) {
		this.estado = estado;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @param idNotificacion the idNotificacion to set
	 */
	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}


	/**
	 * @return the legajoChequeRechazado
	 */
	public ShvLgjLegajoChequeRechazadoSimplificadoNotificacion getLegajoChequeRechazado() {
		return legajoChequeRechazado;
	}


	/**
	 * @param legajoChequeRechazado the legajoChequeRechazado to set
	 */
	public void setLegajoChequeRechazado(
			ShvLgjLegajoChequeRechazadoSimplificadoNotificacion legajoChequeRechazado) {
		this.legajoChequeRechazado = legajoChequeRechazado;
	}


	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}


	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


	/**
	 * @return the carta
	 */
	public ShvLgjNotificacionCarta getCarta() {
		return carta;
	}


	/**
	 * @param carta the carta to set
	 */
	public void setCarta(ShvLgjNotificacionCarta carta) {
		this.carta = carta;
	}


	/**
	 * @return the usuarioDesc
	 */
	public String getUsuarioDesc() {
		return usuarioDesc;
	}


	/**
	 * @param usuarioDesc the usuarioDesc to set
	 */
	public void setUsuarioDesc(String usuarioDesc) {
		this.usuarioDesc = usuarioDesc;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShvLgjNotificacion [idNotificacion=" + idNotificacion
				+ ", legajoChequeRechazado=" + legajoChequeRechazado
				+ ", tipoNotificacion=" + tipoNotificacion + ", datosReceptor="
				+ datosReceptor + ", fechaContacto=" + fechaContacto
				+ ", tipoContacto=" + tipoContacto + ", datosContacto="
				+ datosContacto + ", acuseRecibo=" + acuseRecibo
				+ ", fechaRecepcion=" + fechaRecepcion + ", fechaCreacion="
				+ fechaCreacion + ", fechaModificacion=" + fechaModificacion
				+ ", observaciones=" + observaciones + ", usuarioDesc="
				+ usuarioDesc + ", usuarioAlta=" + usuarioAlta + ", estado="
				+ estado + ", carta=" + carta + "]";
	}
	
	
}
