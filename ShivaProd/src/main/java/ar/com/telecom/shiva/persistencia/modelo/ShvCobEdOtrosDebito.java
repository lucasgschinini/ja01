package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

@Entity
@Table(name = "SHV_COB_ED_OTROS_DEBITO")
public class ShvCobEdOtrosDebito extends Modelo {
	private static final long serialVersionUID = 20180517L;

	@Id
	private ShvCobEdOtrosDebitoPk pk;

	@Enumerated(EnumType.STRING)
	@Column(name = "SOCIEDAD", nullable=false)
	private SociedadEnum sociedad;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SISTEMA_ORIGEN", nullable=false)
	private SistemaEnum sistema;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_COMPROBANTE", nullable=true)
	private TipoComprobanteEnum tipoComprobante;

	@Column(name = "REFERENCIA")
	private BigInteger referencia;

	@Column(name = "ID_CLIENTE_LEGADO")
	private Long idClienteLegado;
	
	/**
	 * Datos para nro legajo clase - Suc - numcomp 
	 */
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CLASE_COMPROBANTE", nullable=true)
	private TipoClaseComprobanteEnum claseComprobante;
	
	@Column(name = "SUCURSAL_COMPROBANTE")
	private String sucursalComprobante;
	
	@Column(name = "NUMERO_COMPROBANTE")
	private String numeroComprobante;
	
	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MONEDA")
	private MonedaEnum moneda;
	
	@Column(name = "TIPO_CAMBIO")
	private BigDecimal tipoCambio;
	
	@Column(name = "IMPORTE")
	private BigDecimal importe;

	@Column(name = "IMPORTE_COBRAR_MONEDA_ORIGEN")
	private BigDecimal importeCobroarMonedaOrigen;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SIN_DIFERENCIA_CAMBIO", nullable=true)
	private SiNoEnum checkSinDiferenciaCambio;
	
	@Column(name = "ID_ADJUNTO")
	private Long idAdjunto;
	
	@Column(name = "ID_OTROS_DEBITOS_REFERENCIA")
	private String idOtrosDebitosReferencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE_A_COBRAR")
	private MonedaEnum monedaImporteACobrar;

	@Enumerated(EnumType.STRING)
	@Column(name = "ORIGEN")
	private OrigenDebitoEnum origen;

	@Column(name = "ARCHIVO_IMPORTACION")
	private String archivoImportacion;
	
	/**
	 * @return the pk
	 */
	public ShvCobEdOtrosDebitoPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(ShvCobEdOtrosDebitoPk pk) {
		this.pk = pk;
	}

	/**
	 * @return sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}
	/**
	 * @param sociedad
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return the referencia
	 */
	public BigInteger getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(BigInteger referencia) {
		this.referencia = referencia;
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
	 * @return the claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	/**
	 * @param claseComprobante the claseComprobante to set
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	/**
	 * @return the sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}

	/**
	 * @param sucursalComprobante the sucursalComprobante to set
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}

	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
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
	 * @return the checkSinDiferenciaCambio
	 */
	public SiNoEnum getCheckSinDiferenciaCambio() {
		return checkSinDiferenciaCambio;
	}

	/**
	 * @param checkSinDiferenciaCambio the checkSinDiferenciaCambio to set
	 */
	public void setCheckSinDiferenciaCambio(SiNoEnum checkSinDiferenciaCambio) {
		this.checkSinDiferenciaCambio = checkSinDiferenciaCambio;
	}

	/**
	 * @return the idAdjunto
	 */
	public Long getIdAdjunto() {
		return idAdjunto;
	}

	/**
	 * @param idAdjunto the idAdjunto to set
	 */
	public void setIdAdjunto(Long idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the tipoComprobante
	 */
	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getIdOtrosDebitosReferencia() {
		return idOtrosDebitosReferencia;
	}

	public void setIdOtrosDebitosReferencia(String idOtrosDebitosReferencia) {
		this.idOtrosDebitosReferencia = idOtrosDebitosReferencia;
	}

	/**
	 * @return the monedaImporteACobrar
	 */
	public MonedaEnum getMonedaImporteACobrar() {
		return monedaImporteACobrar;
	}

	/**
	 * @param monedaImporteACobrar the monedaImporteACobrar to set
	 */
	public void setMonedaImporteACobrar(MonedaEnum monedaImporteACobrar) {
		this.monedaImporteACobrar = monedaImporteACobrar;
	}

	public BigDecimal getImporteCobroarMonedaOrigen() {
		return importeCobroarMonedaOrigen;
	}

	public void setImporteCobroarMonedaOrigen(BigDecimal importeCobroarMonedaOrigen) {
		this.importeCobroarMonedaOrigen = importeCobroarMonedaOrigen;
	}

	/**
	 * @return the origen
	 */
	public OrigenDebitoEnum getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(OrigenDebitoEnum origen) {
		this.origen = origen;
	}

	/**
	 * @return the archivoImportacion
	 */
	public String getArchivoImportacion() {
		return archivoImportacion;
	}

	/**
	 * @param archivoImportacion the archivoImportacion to set
	 */
	public void setArchivoImportacion(String archivoImportacion) {
		this.archivoImportacion = archivoImportacion;
	}
	
}