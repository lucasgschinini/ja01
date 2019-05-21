package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_MAS_OPER_MASIVA")
public class ShvMasOperacionMasivaSimplificadoWorkFlow extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_OPERACION_MASIVA", nullable = false)
	private Long idOperacionMasiva;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workFlow;

	@Column(name="USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;

	@Column(name="CANT_REGISTROS_PROCESADOS_OK")
	private Long cantRegistrosProcesados;

	@Column(name="CANT_REGISTROS_ERROR")
	private Long cantRegistrosError;

	@Column(name="CANT_REGISTROS")
	private Long cantRegistros;

	@Column(name="CANT_REGISTROS_EN_PROCESO")
	private Long cantRegistrosEnProceso;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamMotivoCobro motivo;
	
	@Column (name = "ID_ANALISTA")
	private String idAnalista;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_OPERACION")
	private TipoArchivoOperacionMasivaEnum tipoOperacionMasiva;

	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	public ShvMasOperacionMasivaSimplificadoWorkFlow () {}
			
	public ShvMasOperacionMasivaSimplificadoWorkFlow (
		Long idOperacionMasiva,
		Long cantRegistrosProcesados,
		Long cantRegistrosError,
		Long cantRegistros,
		Long cantRegistrosEnProceso
	) {
		this.idOperacionMasiva = idOperacionMasiva;
		this.cantRegistrosProcesados = cantRegistrosProcesados;
		this.cantRegistrosError = cantRegistrosError;
		this.cantRegistros = cantRegistros;
		this.cantRegistrosEnProceso = cantRegistrosEnProceso;
	}
	/**
	 * @return the idOperacionMasiva
	 */
	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}
	/**
	 * @param idOperacionMasiva the idOperacionMasiva to set
	 */
	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}
	/**
	 * @return the cantRegistrosProcesados
	 */
	public Long getCantRegistrosProcesados() {
		return cantRegistrosProcesados;
	}
	/**
	 * @param cantRegistrosProcesados the cantRegistrosProcesados to set
	 */
	public void setCantRegistrosProcesados(Long cantRegistrosProcesados) {
		this.cantRegistrosProcesados = cantRegistrosProcesados;
	}
	/**
	 * @return the cantRegistrosError
	 */
	public Long getCantRegistrosError() {
		return cantRegistrosError;
	}
	/**
	 * @param cantRegistrosError the cantRegistrosError to set
	 */
	public void setCantRegistrosError(Long cantRegistrosError) {
		this.cantRegistrosError = cantRegistrosError;
	}
	/**
	 * @return the cantRegistros
	 */
	public Long getCantRegistros() {
		return cantRegistros;
	}
	/**
	 * @param cantRegistros the cantRegistros to set
	 */
	public void setCantRegistros(Long cantRegistros) {
		this.cantRegistros = cantRegistros;
	}
	/**
	 * @return the cantRegistrosEnProceso
	 */
	public Long getCantRegistrosEnProceso() {
		return cantRegistrosEnProceso;
	}
	/**
	 * @param cantRegistrosEnProceso the cantRegistrosEnProceso to set
	 */
	public void setCantRegistrosEnProceso(Long cantRegistrosEnProceso) {
		this.cantRegistrosEnProceso = cantRegistrosEnProceso;
	}

	/**
	 * @return the workFlow
	 */
	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	/**
	 * @param workFlow the workFlow to set
	 */
	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
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
	 * @return the empresa
	 */
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}

	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	/**
	 * @return the motivo
	 */
	public ShvParamMotivoCobro getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(ShvParamMotivoCobro motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}

	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	/**
	 * @return the tipoOperacionMasiva
	 */
	public TipoArchivoOperacionMasivaEnum getTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}

	/**
	 * @param tipoOperacionMasiva the tipoOperacionMasiva to set
	 */
	public void setTipoOperacionMasiva(
			TipoArchivoOperacionMasivaEnum tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
