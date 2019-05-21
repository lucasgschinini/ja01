package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_BOL_BOLETA_SIN_VALOR")
public class ShvBolBoletaSinValorSimple extends Modelo {
	private static final long serialVersionUID = 20171228L;

	@Id
	@Column(name="ID_BOLETA")
	private Long idBoleta;

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

	public ShvBolBoletaSinValorSimple() {}

	/**
	 * @return the idBoleta
	 */
	public Long getIdBoleta() {
		return idBoleta;
	}

	/**
	 * @param idBoleta the idBoleta to set
	 */
	public void setIdBoleta(Long idBoleta) {
		this.idBoleta = idBoleta;
	}

	/**
	 * @return the codigoClienteSiebelFormula
	 */
	public String getCodigoClienteSiebelFormula() {
		return codigoClienteSiebelFormula;
	}

	/**
	 * @param codigoClienteSiebelFormula the codigoClienteSiebelFormula to set
	 */
	public void setCodigoClienteSiebelFormula(String codigoClienteSiebelFormula) {
		this.codigoClienteSiebelFormula = codigoClienteSiebelFormula;
	}

	/**
	 * @return the idClienteSiebel
	 */
	public Long getIdClienteSiebel() {
		return idClienteSiebel;
	}

	/**
	 * @param idClienteSiebel the idClienteSiebel to set
	 */
	public void setIdClienteSiebel(Long idClienteSiebel) {
		this.idClienteSiebel = idClienteSiebel;
	}

	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
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
	 * @return the operacionAsociada
	 */
	public Integer getOperacionAsociada() {
		return operacionAsociada;
	}

	/**
	 * @param operacionAsociada the operacionAsociada to set
	 */
	public void setOperacionAsociada(Integer operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}

	
}
