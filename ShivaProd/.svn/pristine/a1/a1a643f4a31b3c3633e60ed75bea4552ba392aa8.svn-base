package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "SHV_MAS_REGISTRO")
public class ShvMasRegistroSimplificada extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REGISTRO")
	private Long idRegistro;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_OPERACION_MASIVA", referencedColumnName="ID_OPERACION_MASIVA") 	
	private ShvMasOperacionMasiva operacionMasiva;
	
	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	@PrimaryKeyJoinColumn(name="ID_REGISTRO", referencedColumnName="ID_REGISTRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvMasRegistroClienteSiebel clientesSiebel;
		
	@Column(name="ID_COBRO")
	private Long idCobro;
	
	@Enumerated (EnumType.STRING)
	@Column(name="ESTADO") 			
	private EstadoRegistroOperacionMasivaEnum estado;
	
	@Column(name="FECHA_CREACION") 			
	private Date fechaCreacion;
	
	@Column(name="USUARIO_CREACION") 			
	private String usuarioCreacion;

	@Column(name="USUARIO_MODIFICACION") 			
	private String usuarioModificacion;
	
	@Column(name="FECHA_MODIFICACION") 			
	private Date fechaModificacion;

	@Column(name="IMPORTE") 			
	private  BigDecimal importe;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name="FECHA_SALIDA") 			
	private Date fechaSalida;
	
	@Column(name="NOMBRE_ARCHIVO_SALIDA") 			
	private String nombreArchivoSalida;

	@Column(name="FECHA_ENTRADA") 			
	private Date fechaEntrada;
	
	@Column(name="NOMBRE_ARCHIVO_ENTRADA") 			
	private String nombreArchivoEntrada;
	
	@Column(name="NOMBRE_ARCHIVO_ORIGINAL") 			
	private String nombreArchivoOriginal;
	
	@Enumerated (EnumType.STRING)
	@Column(name="RESULTADO_CONSULTA_DEBITO")
	private TipoResultadoEnum resultadoConsultaDebito;
	
	@Column(name="CODIGO_ERROR_DEBITO")
	private TipoResultadoEnum codigoErrorDebito;
	
	@Column(name="DESCRIPCION_ERROR_DEBITO")
	private TipoResultadoEnum descripcionErrorDebito;
	
	@Enumerated (EnumType.STRING)
	@Column(name="RESULTADO_CONSULTA_CREDITO")
	private TipoResultadoEnum resultadoConsultaCredito;
	
	@Column(name="CODIGO_ERROR_CREDITO")
	private TipoResultadoEnum codigoErrorCredito;
	
	@Column(name="DESCRIPCION_ERROR_CREDITO")
	private TipoResultadoEnum descripcionErrorCredito;
	
	@Enumerated (EnumType.STRING)
	@Column(name="RESULTADO_CONSULTA_REINTEGRO")
	private TipoResultadoEnum resultadoConsultaReintegro;
	
	@Column(name="CODIGO_ERROR_REINTEGRO")
	private TipoResultadoEnum codigoErrorReintegro;
	
	@Column(name="DESCRIPCION_ERROR_REINTEGRO")
	private TipoResultadoEnum descripcionErrorReintegro;
	
	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public ShvMasOperacionMasiva getOperacionMasiva() {
		return operacionMasiva;
	}

	public void setOperacionMasiva(ShvMasOperacionMasiva operacionMasiva) {
		this.operacionMasiva = operacionMasiva;
	}

	public EstadoRegistroOperacionMasivaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoRegistroOperacionMasivaEnum estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public ShvMasRegistroClienteSiebel getClientesSiebel() {
		return clientesSiebel;
	}

	public void setClientesSiebel(ShvMasRegistroClienteSiebel clientesSiebel) {
		this.clientesSiebel = clientesSiebel;
	}

	/**
	 * @return the nombreArchivoOriginal
	 */
	public String getNombreArchivoOriginal() {
		return nombreArchivoOriginal;
	}

	/**
	 * @param nombreArchivoOriginal the nombreArchivoOriginal to set
	 */
	public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
		this.nombreArchivoOriginal = nombreArchivoOriginal;
	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the fechaSalida
	 */
	public Date getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * @param fechaSalida the fechaSalida to set
	 */
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
	 * @return the nombreArchivoSalida
	 */
	public String getNombreArchivoSalida() {
		return nombreArchivoSalida;
	}

	/**
	 * @param nombreArchivoSalida the nombreArchivoSalida to set
	 */
	public void setNombreArchivoSalida(String nombreArchivoSalida) {
		this.nombreArchivoSalida = nombreArchivoSalida;
	}

	/**
	 * @return the fechaEntrada
	 */
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	/**
	 * @param fechaEntrada the fechaEntrada to set
	 */
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	/**
	 * @return the nombreArchivoEntrada
	 */
	public String getNombreArchivoEntrada() {
		return nombreArchivoEntrada;
	}

	/**
	 * @param nombreArchivoEntrada the nombreArchivoEntrada to set
	 */
	public void setNombreArchivoEntrada(String nombreArchivoEntrada) {
		this.nombreArchivoEntrada = nombreArchivoEntrada;
	}

	public TipoResultadoEnum getResultadoConsultaDebito() {
		return resultadoConsultaDebito;
	}

	public void setResultadoConsultaDebito(TipoResultadoEnum resultadoConsultaDebito) {
		this.resultadoConsultaDebito = resultadoConsultaDebito;
	}

	public TipoResultadoEnum getCodigoErrorDebito() {
		return codigoErrorDebito;
	}

	public void setCodigoErrorDebito(TipoResultadoEnum codigoErrorDebito) {
		this.codigoErrorDebito = codigoErrorDebito;
	}

	public TipoResultadoEnum getDescripcionErrorDebito() {
		return descripcionErrorDebito;
	}

	public void setDescripcionErrorDebito(TipoResultadoEnum descripcionErrorDebito) {
		this.descripcionErrorDebito = descripcionErrorDebito;
	}

	public TipoResultadoEnum getResultadoConsultaCredito() {
		return resultadoConsultaCredito;
	}

	public void setResultadoConsultaCredito(
			TipoResultadoEnum resultadoConsultaCredito) {
		this.resultadoConsultaCredito = resultadoConsultaCredito;
	}

	public TipoResultadoEnum getCodigoErrorCredito() {
		return codigoErrorCredito;
	}

	public void setCodigoErrorCredito(TipoResultadoEnum codigoErrorCredito) {
		this.codigoErrorCredito = codigoErrorCredito;
	}

	public TipoResultadoEnum getDescripcionErrorCredito() {
		return descripcionErrorCredito;
	}

	public void setDescripcionErrorCredito(TipoResultadoEnum descripcionErrorCredito) {
		this.descripcionErrorCredito = descripcionErrorCredito;
	}

	public TipoResultadoEnum getResultadoConsultaReintegro() {
		return resultadoConsultaReintegro;
	}

	public void setResultadoConsultaReintegro(
			TipoResultadoEnum resultadoConsultaReintegro) {
		this.resultadoConsultaReintegro = resultadoConsultaReintegro;
	}

	public TipoResultadoEnum getCodigoErrorReintegro() {
		return codigoErrorReintegro;
	}

	public void setCodigoErrorReintegro(TipoResultadoEnum codigoErrorReintegro) {
		this.codigoErrorReintegro = codigoErrorReintegro;
	}

	public TipoResultadoEnum getDescripcionErrorReintegro() {
		return descripcionErrorReintegro;
	}

	public void setDescripcionErrorReintegro(
			TipoResultadoEnum descripcionErrorReintegro) {
		this.descripcionErrorReintegro = descripcionErrorReintegro;
	}

}
