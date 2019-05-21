package ar.com.telecom.shiva.persistencia.modelo.param;

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

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_CLIENTE")
public class ShvParamCliente extends Modelo {

	private static final long serialVersionUID = -8129833431256124450L;
	@Id
	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_TA")
	private SiNoEnum permiteUsoTA;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_TP")
	private SiNoEnum permiteUsoTP;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_CV")
	private SiNoEnum permiteUsoCV;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_FT")
	private SiNoEnum permiteUsoFT;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMITE_USO_NX")
	private SiNoEnum permiteUsoNX;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA")
	@NotFound(action = NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SEGMENTO")
	@NotFound(action = NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column(name = "ORIGEN")
	@Enumerated(EnumType.STRING)
	private ClienteOrigenEnum origen;

	@Column(name = "CUENTA_CONTABLE")
	private String cuentaContable;
	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	@Column(name = "CUIT")
	private String cuit;
	
	@Column(name = "HOLDING")
	private Long holding;
	
	@Column(name = "DESCRIPCION_HOLDING")
	private String descripcionHolding;
	
	@Column(name = "NUMERO_HOLDING")
	private Long numHolding;

	@Column(name = "ID_AGENCIA_NEGOCIO")
	private Long idAgenciaNegocio;

	@Column(name = "DESCRIPCION_AGENCIA_NEGOCIO")
	private String descripcionAgenciaNegocio;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
	private ShvParamProvincia provincia;
	
	@Column(name = "ID_UNIDAD_NEGOCIO")
	private String idUnidadNegocio;

	@Column(name = "DESCRIPCION_UNIDAD_NEGOCIO")
	private String descripcionUnidadNegocio;

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/


	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ClienteOrigenEnum getOrigen() {
		return origen;
	}

	public void setOrigen(ClienteOrigenEnum origen) {
		this.origen = origen;
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Long getHolding() {
		return holding;
	}

	public void setHolding(Long idHolding) {
		this.holding = idHolding;
	}

	public Long getIdAgenciaNegocio() {
		return idAgenciaNegocio;
	}

	public void setIdAgenciaNegocio(Long idAgenciaNegocio) {
		this.idAgenciaNegocio = idAgenciaNegocio;
	}

	public String getDescripcionHolding() {
		return descripcionHolding;
	}

	public void setDescripcionHolding(String descripcionHolding) {
		this.descripcionHolding = descripcionHolding;
	}

	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}

	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	public ShvParamProvincia getProvincia() {
		return provincia;
	}

	public void setProvincia(ShvParamProvincia provincia) {
		this.provincia = provincia;
	}

	public Long getNumHolding() {
		return numHolding;
	}

	public void setNumHolding(Long numHolding) {
		this.numHolding = numHolding;
	}

	public String getIdUnidadNegocio() {
		return idUnidadNegocio;
	}

	public void setIdUnidadNegocio(String idUnidadNegocio) {
		this.idUnidadNegocio = idUnidadNegocio;
	}

	public String getDescripcionUnidadNegocio() {
		return descripcionUnidadNegocio;
	}

	public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
		this.descripcionUnidadNegocio = descripcionUnidadNegocio;
	}

	public SiNoEnum getPermiteUsoTA() {
		return permiteUsoTA;
	}

	public void setPermiteUsoTA(SiNoEnum permiteUsoTA) {
		this.permiteUsoTA = permiteUsoTA;
	}

	public SiNoEnum getPermiteUsoTP() {
		return permiteUsoTP;
	}

	public void setPermiteUsoTP(SiNoEnum permiteUsoTP) {
		this.permiteUsoTP = permiteUsoTP;
	}

	public SiNoEnum getPermiteUsoCV() {
		return permiteUsoCV;
	}

	public void setPermiteUsoCV(SiNoEnum permiteUsoCV) {
		this.permiteUsoCV = permiteUsoCV;
	}

	public SiNoEnum getPermiteUsoFT() {
		return permiteUsoFT;
	}

	public void setPermiteUsoFT(SiNoEnum permiteUsoFT) {
		this.permiteUsoFT = permiteUsoFT;
	}

	public SiNoEnum getPermiteUsoNX() {
		return permiteUsoNX;
	}

	public void setPermiteUsoNX(SiNoEnum permiteUsoNX) {
		this.permiteUsoNX = permiteUsoNX;
	}
	
	
}
