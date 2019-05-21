package ar.com.telecom.shiva.persistencia.modelo;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_BOL_BOLETA_SIN_VALOR")
public class ShvBolBoletaSinValor extends Modelo {

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="ID_BOLETA")
	@GeneratedValue(generator = "SEQ_SHV_BOL_BOLETA")  
	@GenericGenerator(name = "SEQ_SHV_BOL_BOLETA", strategy="foreign", parameters=@Parameter(name="property", value="boleta")) 
	private Long idBoleta;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvBolBoleta boleta;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Formula("ID_CLIENTE_LEGADO||' - '||RAZON_SOCIAL||';'||ID_CLIENTE_SIEBEL")
	private String codigoClienteSiebelFormula;
	
	@Column(name="ID_CLIENTE_SIEBEL")
	private Long idClienteSiebel;
	
	@Column(name="ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name="ID_ANALISTA")
	private String idAnalista;
	
	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;
	
	@Column(name="IMPORTE")
	private BigDecimal importe;
	
	@Column(name="OBSERVACIONES")
	private String observaciones;
	
	@Column(name="OPERACION_ASOCIADA")
	private Integer operacionAsociada;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamAcuerdo acuerdo;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamMotivo motivo;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ORIGEN")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamOrigen origen;

	public Long getIdBoleta() {
		super.setId(idBoleta);
		return idBoleta;
	}

	public void setIdBoleta(Long idBoleta) {
		this.idBoleta = idBoleta;
	}

	public ShvBolBoleta getBoleta() {
		return boleta;
	}

	public void setBoleta(ShvBolBoleta boleta) {
		this.boleta = boleta;
	}

	public Long getIdClienteSiebel() {
		return idClienteSiebel;
	}

	public void setIdClienteSiebel(Long idClienteSiebel) {
		this.idClienteSiebel = idClienteSiebel;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getOperacionAsociada() {
		return operacionAsociada;
	}

	public void setOperacionAsociada(Integer operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
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

	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	public ShvParamMotivo getMotivo() {
		return motivo;
	}

	public void setMotivo(ShvParamMotivo motivo) {
		this.motivo = motivo;
	}

	public ShvParamOrigen getOrigen() {
		return origen;
	}

	public void setOrigen(ShvParamOrigen origen) {
		this.origen = origen;
	}

	public String getCodigoClienteSiebelFormula() {
		return codigoClienteSiebelFormula;
	}

	public void setCodigoClienteSiebelFormula(String codigoClienteSiebelFormula) {
		this.codigoClienteSiebelFormula = codigoClienteSiebelFormula;
	}
}
