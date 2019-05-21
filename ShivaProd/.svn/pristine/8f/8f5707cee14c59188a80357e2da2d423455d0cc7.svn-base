package ar.com.telecom.shiva.presentacion.bean.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;

/**
 * 
 * @author U587496 Guido.Settecerze
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CobroOtrosDebitoDto extends DTO  { //implements IDatosComunesEntrada
	
	private static final long serialVersionUID = 20180514L;
	
	private Long idOtrosDebito;
	private String idOtrosDebitoPantalla;
	private Long idCobro;
	private SociedadEnum sociedadEnum;
	private String sociedad;
	private SistemaEnum sistemaEnum;
	private String sistema;
	private TipoComprobanteEnum tipoDebitoEnum;
	private String tipoDebito;
	private String referenciaPago;
	private Long cliente;//OK
	private String numeroDocumento;		//OK
	private String fechaVencimiento;	//OK
	private MonedaEnum monedaEnum;
	private String moneda;
	private String tipoCambio;			//Ok
	private String importe;				//OK
	private String importeMonedaOrigen;	//Ok
	private String erroresImporte;
	private String sinDiferenciaDeCambio;
	private String nombreAdjunto;
	private Long idAdjunto;
	
	// mariana.d.budani
	
	private TipoClaseComprobanteEnum claseComprobante;
	private String  sucursalComprobante;
	private String  numeroComprobante;
	
	private String importeString;
	
	private boolean mantenerComprobantesAdjuntos = false;

	private MonedaEnum monedaImporteCobrar;
	private OrigenDebitoEnum origen;
	private String archivoImportacion;
	private byte[] adjunto;

	/**
	 * @return the adjunto
	 */
	public byte[] getAdjunto() {
		return adjunto;
	}
	/**
	 * @param adjunto the adjunto to set
	 */
	public void setAdjunto(byte[] file) {
		this.adjunto = file;
	}
	/**
	 * @return the idOtrosDebito
	 */
	public Long getIdOtrosDebito() {
		return idOtrosDebito;
	}
	/**
	 * @return the importeString
	 */
	public String getImporteString() {
		return importeString;
	}
	/**
	 * @param importeString the importeString to set
	 */
	public void setImporteString(String importeString) {
		this.importeString = importeString;
	}
	/**
	 * @param idOtrosDebito the idOtrosDebito to set
	 */
	public void setIdOtrosDebito(Long idOtrosDebito) {
		this.idOtrosDebito = idOtrosDebito;
	}
	/**
	 * @return the idOtrosDebitoPantalla
	 */
	public String getIdOtrosDebitoPantalla() {
		return idOtrosDebitoPantalla;
	}
	/**
	 * @param idOtrosDebitoPantalla the idOtrosDebitoPantalla to set
	 */
	public void setIdOtrosDebitoPantalla(String idOtrosDebitoPantalla) {
		this.idOtrosDebitoPantalla = idOtrosDebitoPantalla;
	}
	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}
	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}
	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}
	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}
	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	/**
	 * @return the tipoDebito
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}
	/**
	 * @param tipoDebito the tipoDebito to set
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	/**
	 * @return the referencia
	 */
	public String getReferenciaPago() {
		return referenciaPago;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferenciaPago(String referencia) {
		this.referenciaPago = referencia;
	}
	/**
	 * @return the cliente
	 */
	public Long getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the claseComprobante
	 */
	
	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public MonedaEnum getMonedaEnum() {
		return monedaEnum;
	}
	public void setMonedaEnum(MonedaEnum monedaEnum) {
		this.monedaEnum = monedaEnum;
	}
	/**
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}
	public void setImporteMonedaOrigen(String importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}
	public String getErroresImporte() {
		return erroresImporte;
	}
	public void setErroresImporte(String erroresImporte) {
		this.erroresImporte = erroresImporte;
	}
	/**
	 * @return the sinDiferenciaDeCambio
	 */
	public String getSinDiferenciaDeCambio() {
		return sinDiferenciaDeCambio;
	}
	/**
	 * @param sinDiferenciaDeCambio the sinDiferenciaDeCambio to set
	 */
	public void setSinDiferenciaDeCambio(String sinDiferenciaDeCambio) {
		this.sinDiferenciaDeCambio = sinDiferenciaDeCambio;
	}
	public String getNombreAdjunto() {
		return nombreAdjunto;
	}
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
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
	 * @return the nroLegal
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param nroLegal the nroLegal to set
	 */
	public void setNumeroDocumento(String nroLegal) {
		this.numeroDocumento = nroLegal;
	}
	/**
	 * @return the sociedadEnum
	 */
	public SociedadEnum getSociedadEnum() {
		return sociedadEnum;
	}
	/**
	 * @param sociedadEnum the sociedadEnum to set
	 */
	public void setSociedadEnum(SociedadEnum sociedadEnum) {
		this.sociedadEnum = sociedadEnum;
	}
	/**
	 * @return the sistemaEnum
	 */
	public SistemaEnum getSistemaEnum() {
		return sistemaEnum;
	}
	/**
	 * @param sistemaEnum the sistemaEnum to set
	 */
	public void setSistemaEnum(SistemaEnum sistemaEnum) {
		this.sistemaEnum = sistemaEnum;
	}
	
	/**
	 * @return the tipoDebitoEnum
	 */
	public TipoComprobanteEnum getTipoDebitoEnum() {
		return tipoDebitoEnum;
	}
	/**
	 * @param tipoDebitoEnum the tipoDebitoEnum to set
	 */
	public void setTipoDebitoEnum(TipoComprobanteEnum tipoDebitoEnum) {
		this.tipoDebitoEnum = tipoDebitoEnum;
	}
	/**
	 * @return claseComprobante
	 */
	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}
	/**
	 * @param claseComprobante
	 */
	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	/**
	 * @return sucursalComprobante
	 */
	public String getSucursalComprobante() {
		return sucursalComprobante;
	}
	/**
	 * @param sucursalComprobante
	 */
	public void setSucursalComprobante(String sucursalComprobante) {
		this.sucursalComprobante = sucursalComprobante;
	}
	/**
	 * @return nroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	/**
	 * @param numeroComprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public boolean isMantenerComprobantesAdjuntos() {
		return mantenerComprobantesAdjuntos;
	}
	public void setMantenerComprobantesAdjuntos(boolean mantenerComprobantesAdjuntos) {
		this.mantenerComprobantesAdjuntos = mantenerComprobantesAdjuntos;
	}
	/**
	 * @return the monedaImporteCobrar
	 */
	public MonedaEnum getMonedaImporteCobrar() {
		return monedaImporteCobrar;
	}
	/**
	 * @param monedaImporteCobrar the monedaImporteCobrar to set
	 */
	public void setMonedaImporteCobrar(MonedaEnum monedaImporteCobrar) {
		this.monedaImporteCobrar = monedaImporteCobrar;
	}

	public String generarIdOtrosDebitoPantalla() {
		StringBuffer idReferencia = new StringBuffer();
		idReferencia.append(this.sociedadEnum.name()).append("_").append(this.sistemaEnum.name()).append("_");

		if (TipoComprobanteEnum.C_C.equals(this.tipoDebitoEnum)) {
			if (!SistemaEnum.SAP.equals(this.sistemaEnum)) {
				idReferencia.append(this.referenciaPago);
			} else {
				idReferencia.append(this.cliente);
			}
		} else if (TipoComprobanteEnum.CDD.equals(this.tipoDebitoEnum)) {
			idReferencia.append(this.tipoDebito);
		} else {
			idReferencia.append(this.numeroDocumento);
		}
		return idReferencia.toString();
	}
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada#getIdDocumentoCalipso()
	 */
	
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada#getIdPantalla()
	 */
	//@Override
	public String getIdPantalla() {
		return this.getIdOtrosDebitoPantalla();
	}
	
	//@Override
	public String getClaseString() {
		return this.getClass().getSimpleName();
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