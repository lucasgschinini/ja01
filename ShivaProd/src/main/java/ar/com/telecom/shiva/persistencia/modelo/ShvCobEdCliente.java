package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

@Entity
@Table(name = "SHV_COB_ED_CLIENTE")
public class ShvCobEdCliente extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	private ShvCobEdClientePk pk;
	
	@Column(name="ID_CLIENTE_LEGADO")
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

	@Column(name="RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "ORIGEN")
	@Enumerated(EnumType.STRING)
	private ClienteOrigenEnum origen;
	
	@Column(name="CUIT")
	private String cuit;

	@Column(name="ID_HOLDING")
	private Long idHolding;

	@Column(name="DESCRIPCION_HOLDING")
	private String descripcionHolding;

	@Column(name="ID_AGENCIA_NEGOCIO")
	private Long idAgenciaNegocio;

	@Column(name="DESCRIPCION_AGENCIA_NEGOCIO")
	private String descripcionAgenciaNegocio;

	@Column(name="SEGMENTO_AGENCIA_NEGOCIO")
	private String segmentoAgenciaNegocio;
	
	@Column(name="ID_CLIENTE_PERFIL")
	private Long idClientePerfil;

	@Column(name="ID_PROVINCIA")
	private String idProvincia;
	
	public ShvCobEdClientePk getPk() {
		return pk;
	}

	public void setPk(ShvCobEdClientePk pk) {
		this.pk = pk;
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
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the idHolding
	 */
	public Long getIdHolding() {
		return idHolding;
	}

	/**
	 * @param idHolding the idHolding to set
	 */
	public void setIdHolding(Long idHolding) {
		this.idHolding = idHolding;
	}

	/**
	 * @return the descripcionHolding
	 */
	public String getDescripcionHolding() {
		return descripcionHolding;
	}

	/**
	 * @param descripcionHolding the descripcionHolding to set
	 */
	public void setDescripcionHolding(String descripcionHolding) {
		this.descripcionHolding = descripcionHolding;
	}

	/**
	 * @return the idAgenciaNegocio
	 */
	public Long getIdAgenciaNegocio() {
		return idAgenciaNegocio;
	}

	/**
	 * @param idAgenciaNegocio the idAgenciaNegocio to set
	 */
	public void setIdAgenciaNegocio(Long idAgenciaNegocio) {
		this.idAgenciaNegocio = idAgenciaNegocio;
	}

	/**
	 * @return the descripcionAgenciaNegocio
	 */
	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}

	/**
	 * @param descripcionAgenciaNegocio the descripcionAgenciaNegocio to set
	 */
	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}

	/**
	 * @return the segmentoAgenciaNegocio
	 */
	public String getSegmentoAgenciaNegocio() {
		return segmentoAgenciaNegocio;
	}

	/**
	 * @param segmentoAgenciaNegocio the segmentoAgenciaNegocio to set
	 */
	public void setSegmentoAgenciaNegocio(String segmentoAgenciaNegocio) {
		this.segmentoAgenciaNegocio = segmentoAgenciaNegocio;
	}

	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the permiteUsoTA
	 */
	public SiNoEnum getPermiteUsoTA() {
		return permiteUsoTA;
	}

	/**
	 * @param permiteUsoTA the permiteUsoTA to set
	 */
	public void setPermiteUsoTA(SiNoEnum permiteUsoTA) {
		this.permiteUsoTA = permiteUsoTA;
	}

	/**
	 * @return the permiteUsoTP
	 */
	public SiNoEnum getPermiteUsoTP() {
		return permiteUsoTP;
	}

	/**
	 * @param permiteUsoTP the permiteUsoTP to set
	 */
	public void setPermiteUsoTP(SiNoEnum permiteUsoTP) {
		this.permiteUsoTP = permiteUsoTP;
	}

	/**
	 * @return the permiteUsoCV
	 */
	public SiNoEnum getPermiteUsoCV() {
		return permiteUsoCV;
	}

	/**
	 * @param permiteUsoCV the permiteUsoCV to set
	 */
	public void setPermiteUsoCV(SiNoEnum permiteUsoCV) {
		this.permiteUsoCV = permiteUsoCV;
	}

	/**
	 * @return the permiteUsoFT
	 */
	public SiNoEnum getPermiteUsoFT() {
		return permiteUsoFT;
	}

	/**
	 * @param permiteUsoFT the permiteUsoFT to set
	 */
	public void setPermiteUsoFT(SiNoEnum permiteUsoFT) {
		this.permiteUsoFT = permiteUsoFT;
	}

	/**
	 * @return the permiteUsoNX
	 */
	public SiNoEnum getPermiteUsoNX() {
		return permiteUsoNX;
	}

	/**
	 * @param permiteUsoNX the permiteUsoNX to set
	 */
	public void setPermiteUsoNX(SiNoEnum permiteUsoNX) {
		this.permiteUsoNX = permiteUsoNX;
	}

	/**
	 * @return the origen
	 */
	public ClienteOrigenEnum getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(ClienteOrigenEnum origen) {
		this.origen = origen;
	}

	
}
