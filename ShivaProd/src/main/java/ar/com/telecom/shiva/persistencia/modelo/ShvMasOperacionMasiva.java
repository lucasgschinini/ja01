package ar.com.telecom.shiva.persistencia.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_MAS_OPER_MASIVA")
public class ShvMasOperacionMasiva extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_MAS_OPERACION_MASIVA")
	@SequenceGenerator(name = "SEQ_SHV_MAS_OPERACION_MASIVA", sequenceName = "SEQ_SHV_MAS_OPERACION_MASIVA", allocationSize = 1)
	@Column(name = "ID_OPERACION_MASIVA", nullable = false)
	private Long idOperacionMasiva;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workFlow;

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER, mappedBy = "operacionMasiva")
	private List<ShvMasRegistro> registros = new ArrayList<ShvMasRegistro>(0);
	
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
	
	@Column(name="OBSERVACION")
	private String observacion;

	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}

	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}

	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	

	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public ShvParamMotivoCobro getMotivo() {
		return motivo;
	}

	public void setMotivo(ShvParamMotivoCobro motivo) {
		this.motivo = motivo;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	
	public TipoArchivoOperacionMasivaEnum getTipoOperacion(){
		if (Validaciones.isCollectionNotEmpty(registros)){
			ShvMasRegistro registro = registros.iterator().next();
			if(registro instanceof ShvMasRegistroAplicarDeuda){
				return TipoArchivoOperacionMasivaEnum.DEUDA;
			}
			if(registro instanceof ShvMasRegistroDesistimiento){
				return TipoArchivoOperacionMasivaEnum.DSIST;
			}
//			if(registro instanceof ShvMasRegistroCuotaConv){
//				return TipoArchivoOperacionMasivaEnum.CONVENIO;
//			}
			if(registro instanceof ShvMasRegistroReintegro){
				return TipoArchivoOperacionMasivaEnum.REINT;
			}
			if(registro instanceof ShvMasRegistroGanancias){
				return TipoArchivoOperacionMasivaEnum.GNCIA;
			}
		}
		return null;
	}

	public TipoArchivoOperacionMasivaEnum getTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}

	public void setTipoOperacionMasiva(
			TipoArchivoOperacionMasivaEnum tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the registros
	 */
	public List<ShvMasRegistro> getRegistros() {
		return registros;
	}

	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(List<ShvMasRegistro> registros) {
		this.registros = registros;
	}
}
