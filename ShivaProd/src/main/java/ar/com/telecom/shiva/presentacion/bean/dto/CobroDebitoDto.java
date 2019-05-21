package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CobroDebitoDto extends DocumentoGestionableDTO implements IDatosComunesEntrada {
	
	private static final long serialVersionUID = 1L;
	
	// Campos que se visualizan en el resultado de búsqueda de débitos
	private String idDebito;
	private String idDebitoPantalla;
	private String cliente;					//cliente - Codigo cliente - idLegagoCliente
	private String cuenta;					//cuenta
	private String tipoDoc;					//tipoComprobante
	private TipoComprobanteEnum tipoComprobanteEnum; //tipoComprobanteEnum
	//
	private String subtipoDocumento;		//
	private String subtipoDocumentoDesc;	//Este campo es la descripcion del subtipoDocumento y se debe mostrar en el title de esa columna
	//
	private String nroDoc;					//Tiene una logica para mostrar
	private String numeroReferenciaMic;		 
	private String numeroReferenciaDuc;		
	private String fechaVenc;				//fechaVencimiento
	private String saldo1erVencMonOrigen;	//saldoPriVencMonedaOrigen
	private String moneda;					//moneda
	private String imp1erVenc;				//importePriVencMonedaOrigen
	private String imp2doVenc;				//importeSegVenc
	private String saldoPesificado;			//saldoPesificado
	private String estadoCptosDe3ros;		//estadoConceptoTerceros
	private String imp3rosTransf;			//importeTercerosTransferidos
	
	private String estadoOrigen;				//si es mic descEstadoFactura/estadoDuc y si es calipso es estadoMorosidad
	private EstadoOrigenEnum estadoOrigenEnum;  //si es mic descEstadoFactura/estadoDuc y si es calipso es estadoMorosidad
	
	// MarcaReclamo
	private String marcaReclamoDescripcion; //Muestra Si o No en la pantalla
	private MarcaReclamoEnum marcaReclamo; 
	
	// Marca migrada en origen
	private String migradoOrigen;			//Muestra si o no en la pantalla
	private SiNoEnum marcaMigradaOrigenEnum; 
	
	private String estadoDeimos;			//estadoTramite (Descripcion)
	private String opeAsocAnalista;			//en este campo va a ir la lista de operaciones y analistas que 
											//tiene tomado el debito en forma parcial
	private String tipoCambio;				//tipoDeCambio
	private String fechaEmision;			//fechaEmision
	private String nroConvenio;				//numearoConvenio
	private String cuota;					//cuota
	private String fechaUltPagoParcial;		//fechaUltimoPagoParcial
	
	// Campos adicionales que se editan al seleccionar un debito
	private boolean cobrarAl2doVenc;		//checkCobrarSegVencimiento
	private boolean destransferir3ros;		//checkDestranferirTerceros
	private String impACobrar;				//importeACobrar
	private boolean sinDifDeCambio;			//checkSinDiferenciaCambio
	private String descripcionErrorValidacion;
	
	private String habilitarEdicionSinDifCambio;
	
	private String  importe1erVencimientoPesificadoFechaCotizacion;
	private String  SaldoPesificadoFechaCotizacion;
	private String  tipoCambioFechaCotizacion;
	private String  fechaTipoCambio;
	
	
	/******************************************************************/
	/***************** Para calculo de saldos maximos *****************/
	/******************************************************************/
	/**
	 * Caso 01: NO cobrar al 2do Vencimiento y NO destransferir a 3ros
	 */
	private String saldoMaxCaso01 = "none";
	
	/**
	 * Caso 02: NO cobrar al 2do Vencimiento y SI destransferir a 3ros
	 */
	private String saldoMaxCaso02 = "none";
	
	/**
	 * Caso 03: SI cobrar al 2do Vencimiento y NO destransferir a 3ros
	 */
	private String saldoMaxCaso03 = "none";
	
	/**
	 * Caso 04: SI cobrar al 2do Vencimiento y SI destransferir a 3ros
	 */
	private String saldoMaxCaso04 = "none";
	
	/**
	 * Caso 05: SI sin diferencia cambio
	 */
	private String saldoMaxCaso05 = "none";
	
	/**
	 * Caso 06: NO sin diferencia cambio
	 */
	private String saldoMaxCaso06 = "none";

	/**
	 * Saldo máximo por defecto
	 */
	private String saldoMaxDefault = "none";
	/**
	 * Saldo máximo por 
	 */
	private String saldoMaxDefaultCalculado = "none";
	
	/******************************************************************/
	/**************************** WS/MQ *******************************/
	/******************************************************************/
	// Campos 
	private String acuerdoFacturacion;									//acuerdo - Calipso o MIC
	private String estadoAcuerdoFacturacionCalipso;						//codEstadoAcuerdoFact - Calipso
	private EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacionMic; 	//codEstadoAcuerdoFact - MIC
	
	private TipoClaseComprobanteEnum claseComprobante;
	private String  sucursalComprobante;
	private String  numeroComprobante;
	private String numeroDocumento;
	private BigDecimal importePriVencPesificado;
	private BigDecimal importePriVencTerceros;
	private BigDecimal importeSegVencTerceros;
	
	private MarcaCyQEnum marcaCyq;
	private String fechaPuestaCobro;
	private BigDecimal idDocCuentaCobranza;
	private String marcaMigradoDeimos;
	private String posibilidadDestransferencia;
	private String marcaApropiarDeimos;
	private OrigenDebitoEnum origen;
	private String estadoMorosidad;

	private BigDecimal saldoTerceroFinanciableNOTransferible;
	private BigDecimal saldoTerceroFinanciableTransferible;
	private BigDecimal saldoTerceroNOFinanciableTransferible;
	
	/************TAGETIK***************/
	private String razonSocialCliente;
	private String tipoCliente;
	private String cuit;
	private Integer cicloFacturacion;
	private String marketingCustomerGroup;
	
	/************DAKOTA***************/
	private String unidadOperativa;
	private String holding;
	private String fechaVencimientoMora;
	private String indicadorPeticionCorte;
	private String codigoTarifa;
	private String subTipoDakota;
	
	/************DEIMOS***************/
	private EstadoTramiteDeimosEnum dmosEstadoTramite; // Estado en DEIMOs
	private SiNoEnum dmosApropiacionEnDeimos;
	private Long dmosCantidadDeCuotas;
	private Long dmosCantidadDeCuotasPagas;
	private Long dmosNroConvenio;
	
	/************ RESULTADOS DE VALIDACION ***************/
	private EstadoDebitoImportadoEnum resultadoValidacionEstado;
	private String resultadoValidacionCodigoError;
	private String resultadoValidacionDescripcionError;
	
	// Origen
	private SistemaEnum sistemaOrigen; 
	
	// SubTipo
	private TipoFacturaEnum tipoFactura;
	private TipoDUCEnum tipoDuc;
	
	// El documento esta incluido en otra operacion Shiva en edicion
	private SiNoEnum marcaPagoCompensacionEnProcesoShiva;
	private SiNoEnum documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	// Marca de reversión de cobro en proceso en SHIVA
	private SiNoEnum marcaReversionDeCobroProcesoPendiente;
	private SiNoEnum marcaReversionDeCobroEdicion;

	private String sistemaDescripcion;
	
	// Solo para mostrar
	private boolean seleccionado;
	
	//Para ordenar debitos
	private String orderByFecha;
	private String orderByIdClienteLegado;
	private String orderByNumeroIdentificatorio;

	private String monedaImporteCobrar;
	private SociedadEnum sociedad;
	
	/**
	 * @return the idDebito
	 */
	public String getIdDebito() {
		return idDebito;
	}

	/**
	 * @param idDebito the idDebito to set
	 */
	public void setIdDebito(String idDebito) {
		this.idDebito = idDebito;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * @param tipoDoc the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return the subtipoDocumento
	 */
	public String getSubtipoDocumento() {
		return subtipoDocumento;
	}

	/**
	 * @param subtipoDocumento the subtipoDocumento to set
	 */
	public void setSubtipoDocumento(String subtipoDocumento) {
		this.subtipoDocumento = subtipoDocumento;
	}

	/**
	 * @return the nroDoc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * @param nroDoc the nroDoc to set
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	/**
	 * @return the numeroReferenciaMic
	 */
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	/**
	 * @param numeroReferenciaMic the numeroReferenciaMic to set
	 */
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	/**
	 * @return the fechaVenc
	 */
	public String getFechaVenc() {
		return fechaVenc;
	}

	/**
	 * @param fechaVenc the fechaVenc to set
	 */
	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	/**
	 * @return the saldo1erVencMonOrigen
	 */
	public String getSaldo1erVencMonOrigen() {
		return saldo1erVencMonOrigen;
	}

	/**
	 * @param saldo1erVencMonOrigen the saldo1erVencMonOrigen to set
	 */
	public void setSaldo1erVencMonOrigen(String saldo1erVencMonOrigen) {
		this.saldo1erVencMonOrigen = saldo1erVencMonOrigen;
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

	/**
	 * @return the imp1erVenc
	 */
	public String getImp1erVenc() {
		return imp1erVenc;
	}

	/**
	 * @param imp1erVenc the imp1erVenc to set
	 */
	public void setImp1erVenc(String imp1erVenc) {
		this.imp1erVenc = imp1erVenc;
	}

	/**
	 * @return the imp2doVenc
	 */
	public String getImp2doVenc() {
		return imp2doVenc;
	}

	/**
	 * @param imp2doVenc the imp2doVenc to set
	 */
	public void setImp2doVenc(String imp2doVenc) {
		this.imp2doVenc = imp2doVenc;
	}

	/**
	 * @return the saldoPesificado
	 */
	public String getSaldoPesificado() {
		return saldoPesificado;
	}

	/**
	 * @param saldoPesificado the saldoPesificado to set
	 */
	public void setSaldoPesificado(String saldoPesificado) {
		this.saldoPesificado = saldoPesificado;
	}

	/**
	 * @return the estadoCptosDe3ros
	 */
	public String getEstadoCptosDe3ros() {
		return estadoCptosDe3ros;
	}

	/**
	 * @param estadoCptosDe3ros the estadoCptosDe3ros to set
	 */
	public void setEstadoCptosDe3ros(String estadoCptosDe3ros) {
		this.estadoCptosDe3ros = estadoCptosDe3ros;
	}

	/**
	 * @return the imp3rosTransf
	 */
	public String getImp3rosTransf() {
		return imp3rosTransf;
	}

	/**
	 * @param imp3rosTransf the imp3rosTransf to set
	 */
	public void setImp3rosTransf(String imp3rosTransf) {
		this.imp3rosTransf = imp3rosTransf;
	}

	/**
	 * @return the estadoOrigen
	 */
	public String getEstadoOrigen() {
		return estadoOrigen;
	}

	/**
	 * @param estadoOrigen the estadoOrigen to set
	 */
	public void setEstadoOrigen(String estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}

	/**
	 * @return the migradoOrigen
	 */
	public String getMigradoOrigen() {
		return migradoOrigen;
	}

	/**
	 * @param migradoOrigen the migradoOrigen to set
	 */
	public void setMigradoOrigen(String migradoOrigen) {
		this.migradoOrigen = migradoOrigen;
	}

	/**
	 * @return the estadoDeimos
	 */
	public String getEstadoDeimos() {
		return estadoDeimos;
	}

	/**
	 * @param estadoDeimos the estadoDeimos to set
	 */
	public void setEstadoDeimos(String estadoDeimos) {
		this.estadoDeimos = estadoDeimos;
	}

	/**
	 * @return the opeAsocAnalista
	 */
	public String getOpeAsocAnalista() {
		return opeAsocAnalista;
	}

	/**
	 * @param opeAsocAnalista the opeAsocAnalista to set
	 */
	public void setOpeAsocAnalista(String opeAsocAnalista) {
		this.opeAsocAnalista = opeAsocAnalista;
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
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the nroConvenio
	 */
	public String getNroConvenio() {
		return nroConvenio;
	}

	/**
	 * @param nroConvenio the nroConvenio to set
	 */
	public void setNroConvenio(String nroConvenio) {
		this.nroConvenio = nroConvenio;
	}

	/**
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * @return the fechaUltPagoParcial
	 */
	public String getFechaUltPagoParcial() {
		return fechaUltPagoParcial;
	}

	/**
	 * @param fechaUltPagoParcial the fechaUltPagoParcial to set
	 */
	public void setFechaUltPagoParcial(String fechaUltPagoParcial) {
		this.fechaUltPagoParcial = fechaUltPagoParcial;
	}

	/**
	 * @return the cobrarAl2doVenc
	 */
	public boolean isCobrarAl2doVenc() {
		return cobrarAl2doVenc;
	}

	/**
	 * @param cobrarAl2doVenc the cobrarAl2doVenc to set
	 */
	public void setCobrarAl2doVenc(boolean cobrarAl2doVenc) {
		this.cobrarAl2doVenc = cobrarAl2doVenc;
	}

	/**
	 * @return the destransferir3ros
	 */
	public boolean isDestransferir3ros() {
		return destransferir3ros;
	}

	/**
	 * @param destransferir3ros the destransferir3ros to set
	 */
	public void setDestransferir3ros(boolean destransferir3ros) {
		this.destransferir3ros = destransferir3ros;
	}

	/**
	 * @return the impACobrar
	 */
	public String getImpACobrar() {
		return impACobrar;
	}

	/**
	 * @param impACobrar the impACobrar to set
	 */
	public void setImpACobrar(String impACobrar) {
		this.impACobrar = impACobrar;
	}

	/**
	 * @return the sinDifDeCambio
	 */
	public boolean isSinDifDeCambio() {
		return sinDifDeCambio;
	}

	/**
	 * @param sinDifDeCambio the sinDifDeCambio to set
	 */
	public void setSinDifDeCambio(boolean sinDifDeCambio) {
		this.sinDifDeCambio = sinDifDeCambio;
	}

	/**
	 * @return the descripcionErrorValidacion
	 */
	public String getDescripcionErrorValidacion() {
		return descripcionErrorValidacion;
	}

	/**
	 * @param descripcionErrorValidacion the descripcionErrorValidacion to set
	 */
	public void setDescripcionErrorValidacion(String descripcionErrorValidacion) {
		this.descripcionErrorValidacion = descripcionErrorValidacion;
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
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the importePriVencPesificado
	 */
	public BigDecimal getImportePriVencPesificado() {
		return importePriVencPesificado;
	}

	/**
	 * @param importePriVencPesificado the importePriVencPesificado to set
	 */
	public void setImportePriVencPesificado(BigDecimal importePriVencPesificado) {
		this.importePriVencPesificado = importePriVencPesificado;
	}

	/**
	 * @return the importePriVencTerceros
	 */
	public BigDecimal getImportePriVencTerceros() {
		return importePriVencTerceros;
	}

	/**
	 * @param importePriVencTerceros the importePriVencTerceros to set
	 */
	public void setImportePriVencTerceros(BigDecimal importePriVencTerceros) {
		this.importePriVencTerceros = importePriVencTerceros;
	}

	/**
	 * @return the importeSegVencTerceros
	 */
	public BigDecimal getImporteSegVencTerceros() {
		return importeSegVencTerceros;
	}

	/**
	 * @param importeSegVencTerceros the importeSegVencTerceros to set
	 */
	public void setImporteSegVencTerceros(BigDecimal importeSegVencTerceros) {
		this.importeSegVencTerceros = importeSegVencTerceros;
	}

	/**
	 * @return the marcaCyq
	 */
	public MarcaCyQEnum getMarcaCyq() {
		return marcaCyq;
	}

	/**
	 * @param marcaCyq the marcaCyq to set
	 */
	public void setMarcaCyq(MarcaCyQEnum marcaCyq) {
		this.marcaCyq = marcaCyq;
	}

	/**
	 * @return the fechaPuestaCobro
	 */
	public String getFechaPuestaCobro() {
		return fechaPuestaCobro;
	}

	/**
	 * @param fechaPuestaCobro the fechaPuestaCobro to set
	 */
	public void setFechaPuestaCobro(String fechaPuestaCobro) {
		this.fechaPuestaCobro = fechaPuestaCobro;
	}

	/**
	 * @return the idDocCuentaCobranza
	 */
	public BigDecimal getIdDocCuentaCobranza() {
		return idDocCuentaCobranza;
	}

	/**
	 * @param idDocCuentaCobranza the idDocCuentaCobranza to set
	 */
	public void setIdDocCuentaCobranza(BigDecimal idDocCuentaCobranza) {
		this.idDocCuentaCobranza = idDocCuentaCobranza;
	}

	/**
	 * @return the marcaMigradoDeimos
	 */
	public String getMarcaMigradoDeimos() {
		return marcaMigradoDeimos;
	}

	/**
	 * @param marcaMigradoDeimos the marcaMigradoDeimos to set
	 */
	public void setMarcaMigradoDeimos(String marcaMigradoDeimos) {
		this.marcaMigradoDeimos = marcaMigradoDeimos;
	}

	/**
	 * @return the posibilidadDestransferencia
	 */
	public String getPosibilidadDestransferencia() {
		return posibilidadDestransferencia;
	}

	/**
	 * @param posibilidadDestransferencia the posibilidadDestransferencia to set
	 */
	public void setPosibilidadDestransferencia(String posibilidadDestransferencia) {
		this.posibilidadDestransferencia = posibilidadDestransferencia;
	}

	/**
	 * @return the marcaApropiarDeimos
	 */
	public String getMarcaApropiarDeimos() {
		return marcaApropiarDeimos;
	}

	/**
	 * @param marcaApropiarDeimos the marcaApropiarDeimos to set
	 */
	public void setMarcaApropiarDeimos(String marcaApropiarDeimos) {
		this.marcaApropiarDeimos = marcaApropiarDeimos;
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
	 * @return the estadoMorosidad
	 */
	public String getEstadoMorosidad() {
		return estadoMorosidad;
	}

	/**
	 * @param estadoMorosidad the estadoMorosidad to set
	 */
	public void setEstadoMorosidad(String estadoMorosidad) {
		this.estadoMorosidad = estadoMorosidad;
	}

	/**
	 * @return the razonSocialCliente
	 */
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}

	/**
	 * @param razonSocialCliente the razonSocialCliente to set
	 */
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
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
	 * @return the cicloFacturacion
	 */
	public Integer getCicloFacturacion() {
		return cicloFacturacion;
	}

	/**
	 * @param cicloFacturacion the cicloFacturacion to set
	 */
	public void setCicloFacturacion(Integer cicloFacturacion) {
		this.cicloFacturacion = cicloFacturacion;
	}

	/**
	 * @return the marketingCustomerGroup
	 */
	public String getMarketingCustomerGroup() {
		return marketingCustomerGroup;
	}

	/**
	 * @param marketingCustomerGroup the marketingCustomerGroup to set
	 */
	public void setMarketingCustomerGroup(String marketingCustomerGroup) {
		this.marketingCustomerGroup = marketingCustomerGroup;
	}

	/**
	 * @return the unidadOperativa
	 */
	public String getUnidadOperativa() {
		return unidadOperativa;
	}

	/**
	 * @param unidadOperativa the unidadOperativa to set
	 */
	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	/**
	 * @return the holding
	 */
	public String getHolding() {
		return holding;
	}

	/**
	 * @param holding the holding to set
	 */
	public void setHolding(String holding) {
		this.holding = holding;
	}

	/**
	 * @return the fechaVencimientoMora
	 */
	public String getFechaVencimientoMora() {
		return fechaVencimientoMora;
	}

	/**
	 * @param fechaVencimientoMora the fechaVencimientoMora to set
	 */
	public void setFechaVencimientoMora(String fechaVencimientoMora) {
		this.fechaVencimientoMora = fechaVencimientoMora;
	}

	/**
	 * @return the indicadorPeticionCorte
	 */
	public String getIndicadorPeticionCorte() {
		return indicadorPeticionCorte;
	}

	/**
	 * @param indicadorPeticionCorte the indicadorPeticionCorte to set
	 */
	public void setIndicadorPeticionCorte(String indicadorPeticionCorte) {
		this.indicadorPeticionCorte = indicadorPeticionCorte;
	}

	/**
	 * @return the codigoTarifa
	 */
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	/**
	 * @param codigoTarifa the codigoTarifa to set
	 */
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	/**
	 * @return the dmosApropiacionEnDeimos
	 */
	public SiNoEnum getDmosApropiacionEnDeimos() {
		return dmosApropiacionEnDeimos;
	}

	/**
	 * @param dmosApropiacionEnDeimos the dmosApropiacionEnDeimos to set
	 */
	public void setDmosApropiacionEnDeimos(SiNoEnum dmosApropiacionEnDeimos) {
		this.dmosApropiacionEnDeimos = dmosApropiacionEnDeimos;
	}

	/**
	 * @return the dmosCantidadDeCuotas
	 */
	public Long getDmosCantidadDeCuotas() {
		return dmosCantidadDeCuotas;
	}

	/**
	 * @param dmosCantidadDeCuotas the dmosCantidadDeCuotas to set
	 */
	public void setDmosCantidadDeCuotas(Long dmosCantidadDeCuotas) {
		this.dmosCantidadDeCuotas = dmosCantidadDeCuotas;
	}

	/**
	 * @return the dmosCantidadDeCuotasPagas
	 */
	public Long getDmosCantidadDeCuotasPagas() {
		return dmosCantidadDeCuotasPagas;
	}

	/**
	 * @param dmosCantidadDeCuotasPagas the dmosCantidadDeCuotasPagas to set
	 */
	public void setDmosCantidadDeCuotasPagas(Long dmosCantidadDeCuotasPagas) {
		this.dmosCantidadDeCuotasPagas = dmosCantidadDeCuotasPagas;
	}

	/**
	 * @return the dmosNroConvenio
	 */
	public Long getDmosNroConvenio() {
		return dmosNroConvenio;
	}

	/**
	 * @param dmosNroConvenio the dmosNroConvenio to set
	 */
	public void setDmosNroConvenio(Long dmosNroConvenio) {
		this.dmosNroConvenio = dmosNroConvenio;
	}

	/**
	 * @return the resultadoValidacionEstado
	 */
	public EstadoDebitoImportadoEnum getResultadoValidacionEstado() {
		return resultadoValidacionEstado;
	}

	/**
	 * @param resultadoValidacionEstado the resultadoValidacionEstado to set
	 */
	public void setResultadoValidacionEstado(EstadoDebitoImportadoEnum resultadoValidacionEstado) {
		this.resultadoValidacionEstado = resultadoValidacionEstado;
	}

	/**
	 * @return the resultadoValidacionCodigoError
	 */
	public String getResultadoValidacionCodigoError() {
		return resultadoValidacionCodigoError;
	}

	/**
	 * @param resultadoValidacionCodigoError the resultadoValidacionCodigoError to set
	 */
	public void setResultadoValidacionCodigoError(
			String resultadoValidacionCodigoError) {
		this.resultadoValidacionCodigoError = resultadoValidacionCodigoError;
	}

	/**
	 * @return the resultadoValidacionDescripcionError
	 */
	public String getResultadoValidacionDescripcionError() {
		return resultadoValidacionDescripcionError;
	}

	/**
	 * @param resultadoValidacionDescripcionError the resultadoValidacionDescripcionError to set
	 */
	public void setResultadoValidacionDescripcionError(
			String resultadoValidacionDescripcionError) {
		this.resultadoValidacionDescripcionError = resultadoValidacionDescripcionError;
	}

	/**
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * @return the tipoComprobanteEnum
	 */
	public TipoComprobanteEnum getTipoComprobanteEnum() {
		return tipoComprobanteEnum;
	}

	/**
	 * @param tipoComprobanteEnum the tipoComprobanteEnum to set
	 */
	public void setTipoComprobanteEnum(TipoComprobanteEnum tipoComprobanteEnum) {
		this.tipoComprobanteEnum = tipoComprobanteEnum;
	}

	/**
	 * @return the tipoFactura
	 */
	public TipoFacturaEnum getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(TipoFacturaEnum tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * @return the marcaReclamo
	 */
	public MarcaReclamoEnum getMarcaReclamo() {
		return marcaReclamo;
	}

	/**
	 * @param marcaReclamo the marcaReclamo to set
	 */
	public void setMarcaReclamo(MarcaReclamoEnum marcaReclamo) {
		this.marcaReclamo = marcaReclamo;
	}

	/**
	 * @return the marcaMigradaOrigenEnum
	 */
	public SiNoEnum getMarcaMigradaOrigenEnum() {
		return marcaMigradaOrigenEnum;
	}

	/**
	 * @param marcaMigradaOrigenEnum the marcaMigradaOrigenEnum to set
	 */
	public void setMarcaMigradaOrigenEnum(
			SiNoEnum marcaMigradaOrigenEnum) {
		this.marcaMigradaOrigenEnum = marcaMigradaOrigenEnum;
	}

	/**
	 * @return the marcaPagoCompensacionEnProcesoShiva
	 */
	public SiNoEnum getMarcaPagoCompensacionEnProcesoShiva() {
		return marcaPagoCompensacionEnProcesoShiva;
	}

	/**
	 * @param marcaPagoCompensacionEnProcesoShiva the marcaPagoCompensacionEnProcesoShiva to set
	 */
	public void setMarcaPagoCompensacionEnProcesoShiva(
			SiNoEnum marcaPagoCompensacionEnProcesoShiva) {
		this.marcaPagoCompensacionEnProcesoShiva = marcaPagoCompensacionEnProcesoShiva;
	}

	/**
	 * @return the documentoIncluidoEnOtraOperacionShivaEnEdicionEnum
	 */
	public SiNoEnum getDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum() {
		return documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	}

	/**
	 * @param documentoIncluidoEnOtraOperacionShivaEnEdicionEnum the documentoIncluidoEnOtraOperacionShivaEnEdicionEnum to set
	 */
	public void setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(
			SiNoEnum documentoIncluidoEnOtraOperacionShivaEnEdicionEnum) {
		this.documentoIncluidoEnOtraOperacionShivaEnEdicionEnum = documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	}

	/**
	 * @return the dmosEstadoTramite
	 */
	public EstadoTramiteDeimosEnum getDmosEstadoTramite() {
		return dmosEstadoTramite;
	}

	/**
	 * @param dmosEstadoTramite the dmosEstadoTramite to set
	 */
	public void setDmosEstadoTramite(EstadoTramiteDeimosEnum dmosEstadoTramite) {
		this.dmosEstadoTramite = dmosEstadoTramite;
	}

	public String getSistemaDescripcion() {
		return sistemaDescripcion;
	}

	public void setSistemaDescripcion(String sistemaDescripcion) {
		this.sistemaDescripcion = sistemaDescripcion;
	}

	
	@Override
	public IdDocumento getIdDocumentoCalipso() {
		
		IdDocumento idDocumentoEntrada = new IdDocumento();
		idDocumentoEntrada.setClaseComprobante(this.getClaseComprobante());
		idDocumentoEntrada.setTipoComprobante(this.getTipoComprobanteEnum());
		idDocumentoEntrada.setSucursalComprobante(this.getSucursalComprobante());
		idDocumentoEntrada.setNumeroComprobante(this.getNumeroComprobante());
		
		return idDocumentoEntrada;
	}

	@Override
	public String getClaseString() {
		return this.getClass().getSimpleName();
	}
	@Override
	public String getIdPantalla() {
		return this.getIdDebitoPantalla();
	}

	public String getSubtipoDocumentoDesc() {
		return subtipoDocumentoDesc;
	}

	public void setSubtipoDocumentoDesc(String subtipoDocumentoDesc) {
		this.subtipoDocumentoDesc = subtipoDocumentoDesc;
	}

	public String getSaldoMaxCaso01() {
		return saldoMaxCaso01;
	}
	
	/**
	 * Caso 01: NO cobrar al 2do Vencimiento y NO destransferir a 3ros
	 */
	public void setSaldoMaxCaso01(String saldoMaxCaso01) {
		this.saldoMaxCaso01 = saldoMaxCaso01;
	}

	public String getSaldoMaxCaso02() {
		return saldoMaxCaso02;
	}

	/**
	 * Caso 02: NO cobrar al 2do Vencimiento y SI destransferir a 3ros
	 */
	public void setSaldoMaxCaso02(String saldoMaxCaso02) {
		this.saldoMaxCaso02 = saldoMaxCaso02;
	}

	public String getSaldoMaxCaso03() {
		return saldoMaxCaso03;
	}

	/**
	 * Caso 03: SI cobrar al 2do Vencimiento y NO destransferir a 3ros
	 */
	public void setSaldoMaxCaso03(String saldoMaxCaso03) {
		this.saldoMaxCaso03 = saldoMaxCaso03;
	}

	public String getSaldoMaxCaso04() {
		return saldoMaxCaso04;
	}

	/**
	 * Caso 04: SI cobrar al 2do Vencimiento y SI destransferir a 3ros
	 */
	public void setSaldoMaxCaso04(String saldoMaxCaso04) {
		this.saldoMaxCaso04 = saldoMaxCaso04;
	}

	public String getSaldoMaxCaso05() {
		return saldoMaxCaso05;
	}

	/**
	 * Caso 05: SI sin diferencia cambio
	 */
	public void setSaldoMaxCaso05(String saldoMaxCaso05) {
		this.saldoMaxCaso05 = saldoMaxCaso05;
	}
	
	public String getSaldoMaxDefault() {
		return saldoMaxDefault;
	}

	/**
	 * Saldo máximo por defecto
	 */
	public void setSaldoMaxDefault(String saldoMaxDefault) {
		this.saldoMaxDefault = saldoMaxDefault;
	}

	public String getIdDebitoPantalla() {
		return idDebitoPantalla;
	}
	
	public void setIdDebitoPantalla(String idDebitoPantalla) {
		this.idDebitoPantalla = idDebitoPantalla;
	}

	public EstadoOrigenEnum getEstadoOrigenEnum() {
		return estadoOrigenEnum;
	}

	public void setEstadoOrigenEnum(EstadoOrigenEnum estadoOrigenEnum) {
		this.estadoOrigenEnum = estadoOrigenEnum;
	}

	public String getMarcaReclamoDescripcion() {
		return marcaReclamoDescripcion;
	}

	public void setMarcaReclamoDescripcion(String marcaReclamoDescripcion) {
		this.marcaReclamoDescripcion = marcaReclamoDescripcion;
	}

	public String getOrderByFecha() {
		return orderByFecha;
	}

	public void setOrderByFecha(String orderByFecha) {
		this.orderByFecha = orderByFecha;
	}

	public String getOrderByIdClienteLegado() {
		return orderByIdClienteLegado;
	}

	public void setOrderByIdClienteLegado(String orderByIdClienteLegado) {
		this.orderByIdClienteLegado = orderByIdClienteLegado;
	}

	public String getOrderByNumeroIdentificatorio() {
		return orderByNumeroIdentificatorio;
	}

	public void setOrderByNumeroIdentificatorio(String orderByNumeroIdentificatorio) {
		this.orderByNumeroIdentificatorio = orderByNumeroIdentificatorio;
	}

	public String getNumeroReferenciaDuc() {
		return numeroReferenciaDuc;
	}

	public void setNumeroReferenciaDuc(String numeroReferenciaDuc) {
		this.numeroReferenciaDuc = numeroReferenciaDuc;
	}

	public TipoDUCEnum getTipoDuc() {
		return tipoDuc;
	}

	public void setTipoDuc(TipoDUCEnum tipoDuc) {
		this.tipoDuc = tipoDuc;
	}

	public String getEstadoAcuerdoFacturacionCalipso() {
		return estadoAcuerdoFacturacionCalipso;
	}

	public void setEstadoAcuerdoFacturacionCalipso(
			String estadoAcuerdoFacturacionCalipso) {
		this.estadoAcuerdoFacturacionCalipso = estadoAcuerdoFacturacionCalipso;
	}

	public EstadoAcuerdoFacturacionEnum getEstadoAcuerdoFacturacionMic() {
		return estadoAcuerdoFacturacionMic;
	}

	public void setEstadoAcuerdoFacturacionMic(
			EstadoAcuerdoFacturacionEnum estadoAcuerdoFacturacionMic) {
		this.estadoAcuerdoFacturacionMic = estadoAcuerdoFacturacionMic;
	}

	public String getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}

	public void setAcuerdoFacturacion(String acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}

	public BigDecimal getSaldoTerceroFinanciableNOTransferible() {
		return saldoTerceroFinanciableNOTransferible;
	}

	public void setSaldoTerceroFinanciableNOTransferible(
			BigDecimal saldoTerceroFinanciableNOTransferible) {
		this.saldoTerceroFinanciableNOTransferible = saldoTerceroFinanciableNOTransferible;
	}

	public BigDecimal getSaldoTerceroFinanciableTransferible() {
		return saldoTerceroFinanciableTransferible;
	}

	public void setSaldoTerceroFinanciableTransferible(
			BigDecimal saldoTerceroFinanciableTransferible) {
		this.saldoTerceroFinanciableTransferible = saldoTerceroFinanciableTransferible;
	}

	public BigDecimal getSaldoTerceroNOFinanciableTransferible() {
		return saldoTerceroNOFinanciableTransferible;
	}

	public void setSaldoTerceroNOFinanciableTransferible(
			BigDecimal saldoTerceroNOFinanciableTransferible) {
		this.saldoTerceroNOFinanciableTransferible = saldoTerceroNOFinanciableTransferible;
	}

	public String getSubTipoDakota() {
		return subTipoDakota;
	}

	public void setSubTipoDakota(String subTipoDakota) {
		this.subTipoDakota = subTipoDakota;
	}

	/**
	 * @return the saldoMaxDefaultCalculado
	 */
	public String getSaldoMaxDefaultCalculado() {
		return saldoMaxDefaultCalculado;
	}

	/**
	 * @param saldoMaxDefaultCalculado the saldoMaxDefaultCalculado to set
	 */
	public void setSaldoMaxDefaultCalculado(String saldoMaxDefaultCalculado) {
		this.saldoMaxDefaultCalculado = saldoMaxDefaultCalculado;
	}

	/**
	 * @return the marcaReversionDeCobroProcesoPendiente
	 */
	public SiNoEnum getMarcaReversionDeCobroProcesoPendiente() {
		return marcaReversionDeCobroProcesoPendiente;
	}

	/**
	 * @param marcaReversionDeCobroProcesoPendiente the marcaReversionDeCobroProcesoPendiente to set
	 */
	public void setMarcaReversionDeCobroProcesoPendiente(
			SiNoEnum marcaReversionDeCobroProcesoPendiente) {
		this.marcaReversionDeCobroProcesoPendiente = marcaReversionDeCobroProcesoPendiente;
	}

	/**
	 * @return the marcaReversionDeCobroEdicion
	 */
	public SiNoEnum getMarcaReversionDeCobroEdicion() {
		return marcaReversionDeCobroEdicion;
	}

	/**
	 * @param marcaReversionDeCobroEdicion the marcaReversionDeCobroEdicion to set
	 */
	public void setMarcaReversionDeCobroEdicion(
			SiNoEnum marcaReversionDeCobroEdicion) {
		this.marcaReversionDeCobroEdicion = marcaReversionDeCobroEdicion;
	}

	public String getMonedaImporteCobrar() {
		return monedaImporteCobrar;
	}

	public void setMonedaImporteCobrar(String monedaImporteCobrar) {
		this.monedaImporteCobrar = monedaImporteCobrar;
	}
	
	public MonedaEnum getMonedaImporteCobrarEnum() {
		MonedaEnum monedaOperacion = MonedaEnum.getEnumByName(monedaImporteCobrar);
		if(!Validaciones.isObjectNull(monedaOperacion)){
			return monedaOperacion;
		} else {
			return MonedaEnum.getEnumBySigno2(monedaImporteCobrar);
		}
	}
	public MonedaEnum getMonedaImporteEnum() {
		return this.getMonedaImporteCobrarEnum();
	}

	/**
	 * @return the habilitarEdicionSinDifCambio
	 */
	public String getHabilitarEdicionSinDifCambio() {
		return habilitarEdicionSinDifCambio;
	}

	/**
	 * @param habilitarEdicionSinDifCambio the habilitarEdicionSinDifCambio to set
	 */
	public void setHabilitarEdicionSinDifCambio(
			String habilitarEdicionSinDifCambio) {
		this.habilitarEdicionSinDifCambio = habilitarEdicionSinDifCambio;
	}

	/**
	 * @return the importe1erVencimientoPesificadoFechaCotizacion
	 */
	public String getImporte1erVencimientoPesificadoFechaCotizacion() {
		return importe1erVencimientoPesificadoFechaCotizacion;
	}

	/**
	 * @param importe1erVencimientoPesificadoFechaCotizacion the importe1erVencimientoPesificadoFechaCotizacion to set
	 */
	public void setImporte1erVencimientoPesificadoFechaCotizacion(
			String importe1erVencimientoPesificadoFechaCotizacion) {
		this.importe1erVencimientoPesificadoFechaCotizacion = importe1erVencimientoPesificadoFechaCotizacion;
	}

	/**
	 * @return the saldoPesificadoFechaCotizacion
	 */
	public String getSaldoPesificadoFechaCotizacion() {
		return SaldoPesificadoFechaCotizacion;
	}

	/**
	 * @param saldoPesificadoFechaCotizacion the saldoPesificadoFechaCotizacion to set
	 */
	public void setSaldoPesificadoFechaCotizacion(
			String saldoPesificadoFechaCotizacion) {
		SaldoPesificadoFechaCotizacion = saldoPesificadoFechaCotizacion;
	}

	/**
	 * @return the tipoCambioFechaCotizacion
	 */
	public String getTipoCambioFechaCotizacion() {
		return tipoCambioFechaCotizacion;
	}

	/**
	 * @param tipoCambioFechaCotizacion the tipoCambioFechaCotizacion to set
	 */
	public void setTipoCambioFechaCotizacion(String tipoCambioFechaCotizacion) {
		this.tipoCambioFechaCotizacion = tipoCambioFechaCotizacion;
	}

	/**
	 * @return the fechaTipoCambio
	 */
	public String getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	/**
	 * @param fechaTipoCambio the fechaTipoCambio to set
	 */
	public void setFechaTipoCambio(String fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	/**
	 * @return the saldoMaxCaso06
	 */
	public String getSaldoMaxCaso06() {
		return saldoMaxCaso06;
	}

	/**
	 * @param saldoMaxCaso06 the saldoMaxCaso06 to set
	 */
	public void setSaldoMaxCaso06(String saldoMaxCaso06) {
		this.saldoMaxCaso06 = saldoMaxCaso06;
	}

	/**
	 * @return the sociedad
	 */
	public SociedadEnum getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

}