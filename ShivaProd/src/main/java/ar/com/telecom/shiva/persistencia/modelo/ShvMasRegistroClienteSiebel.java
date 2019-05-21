package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;

@Entity
@Table(name = "SHV_MAS_REGISTRO_CLI_SIEBEL")
public class ShvMasRegistroClienteSiebel {

	@Id
	private Long idRegistro;
	
	@MapsId 
	@OneToOne
	@JoinColumn(name = "ID_REGISTRO")
	private ShvMasRegistro registro;
	
	@Column(name="CLIENTE_DUENO_DEBITO")
	private Long clienteDuenoDebito;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_DEBITO")
	private String razonSocialClienteDebito;
	
	@Column(name="CUIT_DEBITO")
	private String cuitDebito;
	
	@Column(name="NRO_HOLDING_DEBITO")
	private Long nroHoldingDebito;
	
	@Column(name="DESC_HOLDING_DEBITO")
	private String descripcionHoldingDebito;
	
	@Column(name="NRO_AGENCIA_NEGOCIO_DEBITO")
	private Long nroAgenciaNegocioDebito;
	
	@Column(name="DESC_AGENCIA_NEGOCIO_DEBITO")
	private String descripcionAgenciaNegocioDebito;
	
	@Column(name="NRO_CLIENTE_PERFIL_DEBITO")
	private Long nroClientePerfilDebito;
	
	@Column(name="SEG_AGENCIA_NEGOCIO_DEBITO")
	private String segmentoAgenciaNegocioDebito;

	@Column(name="CODIGO_PROVINCIA_DEBITO")
	private String codigoProvinciaDebito;
	
	@Enumerated(EnumType.STRING)
	@Column(name="RES_CONSULTA_SIEBEL_DEBITO")
	private TipoResultadoEnum resultadoConsultaSiebelDebito;
	
	@Column(name="CODIGO_ERROR_SIEBEL_DEBITO")
	private String codigoErrorSiebelDebito;
	
	@Column(name="DESC_ERROR_SIEBEL_DEBITO")
	private String descripcionErrorSiebelDebito;
	
	
	@Column(name="CLIENTE_DUENO_CREDITO")
	private Long clienteDuenoCredito;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_CREDITO")
	private String razonSocialClienteCredito;
	
	@Column(name="CUIT_CREDITO")
	private String cuitCredito;
	
	@Column(name="NRO_HOLDING_CREDITO")
	private Long nroHoldingCredito;
	
	@Column(name="DESC_HOLDING_CREDITO")
	private String descripcionHoldingCredito;
	
	@Column(name="NRO_AGENCIA_NEGOCIO_CREDITO")
	private Long nroAgenciaNegocioCredito;
	
	@Column(name="DESC_AGENCIA_NEGOCIO_CREDITO")
	private String descripcionAgenciaNegocioCredito;
	
	@Column(name="NRO_CLIENTE_PERFIL_CREDITO")
	private Long nroClientePerfilCredito;
	
	@Column(name="SEG_AGENCIA_NEGOCIO_CREDITO")
	private String segmentoAgenciaNegocioCredito;

	@Column(name="CODIGO_PROVINCIA_CREDITO")
	private String codigoProvinciaCredito;
	
	@Enumerated(EnumType.STRING)
	@Column(name="RES_CONSULTA_SIEBEL_CREDITO")
	private TipoResultadoEnum resultadoConsultaSiebelCredito;
	
	@Column(name="CODIGO_ERROR_SIEBEL_CREDITO")
	private String codigoErrorSiebelCredito;
	
	@Column(name="DESC_ERROR_SIEBEL_CREDITO")
	private String descripcionErrorSiebelCredito;
	
	
	@Column(name="CLIENTE_DUENO_ACUERDO")
	private Long clienteDuenoAcuerdo;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_ACUERDO")
	private String razonSocialClienteAcuerdo;
	
	@Column(name="CUIT_ACUERDO")
	private String cuitAcuerdo;
	
	@Column(name="NRO_HOLDING_ACUERDO")
	private Long nroHoldingAcuerdo;
	
	@Column(name="DESC_HOLDING_ACUERDO")
	private String descripcionHoldingAcuerdo;
	
	@Column(name="NRO_AGENCIA_NEGOCIO_ACUERDO")
	private Long nroAgenciaNegocioAcuerdo;
	
	@Column(name="DESC_AGENCIA_NEGOCIO_ACUERDO")
	private String descripcionAgenciaNegocioAcuerdo;
	
	@Column(name="NRO_CLIENTE_PERFIL_ACUERDO")
	private Long nroClientePerfilAcuerdo;
	
	@Column(name="SEG_AGENCIA_NEGOCIO_ACUERDO")
	private String segmentoAgenciaNegocioAcuerdo;

	@Column(name="CODIGO_PROVINCIA_ACUERDO")
	private String codigoProvinciaAcuerdo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="RES_CONSULTA_SIEBEL_ACUERDO")
	private TipoResultadoEnum resultadoConsultaSiebelAcuerdo;
	
	@Column(name="CODIGO_ERROR_SIEBEL_ACUERDO")
	private String codigoErrorSiebelAcuerdo;
	
	@Column(name="DESC_ERROR_SIEBEL_ACUERDO")
	private String descripcionErrorSiebelAcuerdo;

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public ShvMasRegistro getRegistro() {
		return registro;
	}

	public void setRegistro(ShvMasRegistro registro) {
		this.registro = registro;
	}

	public Long getClienteDuenoDebito() {
		return clienteDuenoDebito;
	}

	public void setClienteDuenoDebito(Long clienteDuenoDebito) {
		this.clienteDuenoDebito = clienteDuenoDebito;
	}

	public String getRazonSocialClienteDebito() {
		return razonSocialClienteDebito;
	}

	public void setRazonSocialClienteDebito(String razonSocialClienteDebito) {
		this.razonSocialClienteDebito = razonSocialClienteDebito;
	}

	public String getCuitDebito() {
		return cuitDebito;
	}

	public void setCuitDebito(String cuitDebito) {
		this.cuitDebito = cuitDebito;
	}

	public Long getNroHoldingDebito() {
		return nroHoldingDebito;
	}

	public void setNroHoldingDebito(Long nroHoldingDebito) {
		this.nroHoldingDebito = nroHoldingDebito;
	}

	public String getDescripcionHoldingDebito() {
		return descripcionHoldingDebito;
	}

	public void setDescripcionHoldingDebito(String descripcionHoldingDebito) {
		this.descripcionHoldingDebito = descripcionHoldingDebito;
	}

	public Long getNroAgenciaNegocioDebito() {
		return nroAgenciaNegocioDebito;
	}

	public void setNroAgenciaNegocioDebito(Long nroAgenciaNegocioDebito) {
		this.nroAgenciaNegocioDebito = nroAgenciaNegocioDebito;
	}

	public String getDescripcionAgenciaNegocioDebito() {
		return descripcionAgenciaNegocioDebito;
	}

	public void setDescripcionAgenciaNegocioDebito(
			String descripcionAgenciaNegocioDebito) {
		this.descripcionAgenciaNegocioDebito = descripcionAgenciaNegocioDebito;
	}

	public Long getNroClientePerfilDebito() {
		return nroClientePerfilDebito;
	}

	public void setNroClientePerfilDebito(Long nroClientePerfilDebito) {
		this.nroClientePerfilDebito = nroClientePerfilDebito;
	}

	public String getSegmentoAgenciaNegocioDebito() {
		return segmentoAgenciaNegocioDebito;
	}

	public void setSegmentoAgenciaNegocioDebito(String segmentoAgenciaNegocioDebito) {
		this.segmentoAgenciaNegocioDebito = segmentoAgenciaNegocioDebito;
	}

	public String getCodigoProvinciaDebito() {
		return codigoProvinciaDebito;
	}

	public void setCodigoProvinciaDebito(String codigoProvinciaDebito) {
		this.codigoProvinciaDebito = codigoProvinciaDebito;
	}

	public TipoResultadoEnum getResultadoConsultaSiebelDebito() {
		return resultadoConsultaSiebelDebito;
	}

	public void setResultadoConsultaSiebelDebito(
			TipoResultadoEnum resultadoConsultaSiebelDebito) {
		this.resultadoConsultaSiebelDebito = resultadoConsultaSiebelDebito;
	}

	public String getCodigoErrorSiebelDebito() {
		return codigoErrorSiebelDebito;
	}

	public void setCodigoErrorSiebelDebito(String codigoErrorSiebelDebito) {
		this.codigoErrorSiebelDebito = codigoErrorSiebelDebito;
	}

	public String getDescripcionErrorSiebelDebito() {
		return descripcionErrorSiebelDebito;
	}

	public void setDescripcionErrorSiebelDebito(String descripcionErrorSiebelDebito) {
		this.descripcionErrorSiebelDebito = descripcionErrorSiebelDebito;
	}

	public Long getClienteDuenoCredito() {
		return clienteDuenoCredito;
	}

	public void setClienteDuenoCredito(Long clienteDuenoCredito) {
		this.clienteDuenoCredito = clienteDuenoCredito;
	}

	public String getRazonSocialClienteCredito() {
		return razonSocialClienteCredito;
	}

	public void setRazonSocialClienteCredito(String razonSocialClienteCredito) {
		this.razonSocialClienteCredito = razonSocialClienteCredito;
	}

	public String getCuitCredito() {
		return cuitCredito;
	}

	public void setCuitCredito(String cuitCredito) {
		this.cuitCredito = cuitCredito;
	}

	public Long getNroHoldingCredito() {
		return nroHoldingCredito;
	}

	public void setNroHoldingCredito(Long nroHoldingCredito) {
		this.nroHoldingCredito = nroHoldingCredito;
	}

	public String getDescripcionHoldingCredito() {
		return descripcionHoldingCredito;
	}

	public void setDescripcionHoldingCredito(String descripcionHoldingCredito) {
		this.descripcionHoldingCredito = descripcionHoldingCredito;
	}

	public Long getNroAgenciaNegocioCredito() {
		return nroAgenciaNegocioCredito;
	}

	public void setNroAgenciaNegocioCredito(Long nroAgenciaNegocioCredito) {
		this.nroAgenciaNegocioCredito = nroAgenciaNegocioCredito;
	}

	public String getDescripcionAgenciaNegocioCredito() {
		return descripcionAgenciaNegocioCredito;
	}

	public void setDescripcionAgenciaNegocioCredito(
			String descripcionAgenciaNegocioCredito) {
		this.descripcionAgenciaNegocioCredito = descripcionAgenciaNegocioCredito;
	}

	public Long getNroClientePerfilCredito() {
		return nroClientePerfilCredito;
	}

	public void setNroClientePerfilCredito(Long nroClientePerfilCredito) {
		this.nroClientePerfilCredito = nroClientePerfilCredito;
	}

	public String getSegmentoAgenciaNegocioCredito() {
		return segmentoAgenciaNegocioCredito;
	}

	public void setSegmentoAgenciaNegocioCredito(
			String segmentoAgenciaNegocioCredito) {
		this.segmentoAgenciaNegocioCredito = segmentoAgenciaNegocioCredito;
	}

	public String getCodigoProvinciaCredito() {
		return codigoProvinciaCredito;
	}

	public void setCodigoProvinciaCredito(String codigoProvinciaCredito) {
		this.codigoProvinciaCredito = codigoProvinciaCredito;
	}

	public TipoResultadoEnum getResultadoConsultaSiebelCredito() {
		return resultadoConsultaSiebelCredito;
	}

	public void setResultadoConsultaSiebelCredito(
			TipoResultadoEnum resultadoConsultaSiebelCredito) {
		this.resultadoConsultaSiebelCredito = resultadoConsultaSiebelCredito;
	}

	public String getCodigoErrorSiebelCredito() {
		return codigoErrorSiebelCredito;
	}

	public void setCodigoErrorSiebelCredito(String codigoErrorSiebelCredito) {
		this.codigoErrorSiebelCredito = codigoErrorSiebelCredito;
	}

	public String getDescripcionErrorSiebelCredito() {
		return descripcionErrorSiebelCredito;
	}

	public void setDescripcionErrorSiebelCredito(
			String descripcionErrorSiebelCredito) {
		this.descripcionErrorSiebelCredito = descripcionErrorSiebelCredito;
	}

	public Long getClienteDuenoAcuerdo() {
		return clienteDuenoAcuerdo;
	}

	public void setClienteDuenoAcuerdo(Long clienteDuenoAcuerdo) {
		this.clienteDuenoAcuerdo = clienteDuenoAcuerdo;
	}

	public String getRazonSocialClienteAcuerdo() {
		return razonSocialClienteAcuerdo;
	}

	public void setRazonSocialClienteAcuerdo(String razonSocialClienteAcuerdo) {
		this.razonSocialClienteAcuerdo = razonSocialClienteAcuerdo;
	}

	public String getCuitAcuerdo() {
		return cuitAcuerdo;
	}

	public void setCuitAcuerdo(String cuitAcuerdo) {
		this.cuitAcuerdo = cuitAcuerdo;
	}

	public Long getNroHoldingAcuerdo() {
		return nroHoldingAcuerdo;
	}

	public void setNroHoldingAcuerdo(Long nroHoldingAcuerdo) {
		this.nroHoldingAcuerdo = nroHoldingAcuerdo;
	}

	public String getDescripcionHoldingAcuerdo() {
		return descripcionHoldingAcuerdo;
	}

	public void setDescripcionHoldingAcuerdo(String descripcionHoldingAcuerdo) {
		this.descripcionHoldingAcuerdo = descripcionHoldingAcuerdo;
	}

	public Long getNroAgenciaNegocioAcuerdo() {
		return nroAgenciaNegocioAcuerdo;
	}

	public void setNroAgenciaNegocioAcuerdo(Long nroAgenciaNegocioAcuerdo) {
		this.nroAgenciaNegocioAcuerdo = nroAgenciaNegocioAcuerdo;
	}

	public String getDescripcionAgenciaNegocioAcuerdo() {
		return descripcionAgenciaNegocioAcuerdo;
	}

	public void setDescripcionAgenciaNegocioAcuerdo(
			String descripcionAgenciaNegocioAcuerdo) {
		this.descripcionAgenciaNegocioAcuerdo = descripcionAgenciaNegocioAcuerdo;
	}

	public Long getNroClientePerfilAcuerdo() {
		return nroClientePerfilAcuerdo;
	}

	public void setNroClientePerfilAcuerdo(Long nroClientePerfilAcuerdo) {
		this.nroClientePerfilAcuerdo = nroClientePerfilAcuerdo;
	}

	public String getSegmentoAgenciaNegocioAcuerdo() {
		return segmentoAgenciaNegocioAcuerdo;
	}

	public void setSegmentoAgenciaNegocioAcuerdo(
			String segmentoAgenciaNegocioAcuerdo) {
		this.segmentoAgenciaNegocioAcuerdo = segmentoAgenciaNegocioAcuerdo;
	}

	public String getCodigoProvinciaAcuerdo() {
		return codigoProvinciaAcuerdo;
	}

	public void setCodigoProvinciaAcuerdo(String codigoProvinciaAcuerdo) {
		this.codigoProvinciaAcuerdo = codigoProvinciaAcuerdo;
	}

	public TipoResultadoEnum getResultadoConsultaSiebelAcuerdo() {
		return resultadoConsultaSiebelAcuerdo;
	}

	public void setResultadoConsultaSiebelAcuerdo(
			TipoResultadoEnum resultadoConsultaSiebelAcuerdo) {
		this.resultadoConsultaSiebelAcuerdo = resultadoConsultaSiebelAcuerdo;
	}

	public String getCodigoErrorSiebelAcuerdo() {
		return codigoErrorSiebelAcuerdo;
	}

	public void setCodigoErrorSiebelAcuerdo(String codigoErrorSiebelAcuerdo) {
		this.codigoErrorSiebelAcuerdo = codigoErrorSiebelAcuerdo;
	}

	public String getDescripcionErrorSiebelAcuerdo() {
		return descripcionErrorSiebelAcuerdo;
	}

	public void setDescripcionErrorSiebelAcuerdo(
			String descripcionErrorSiebelAcuerdo) {
		this.descripcionErrorSiebelAcuerdo = descripcionErrorSiebelAcuerdo;
	}
	
			
		
	
}
