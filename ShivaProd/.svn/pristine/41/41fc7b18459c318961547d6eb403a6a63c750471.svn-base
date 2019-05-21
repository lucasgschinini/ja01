package ar.com.telecom.shiva.presentacion.bean.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoShivaEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CobroCreditoDto extends DocumentoGestionableDTO implements IDatosComunesEntrada {

	private static final long serialVersionUID = 1L;

	private String idCredito;
	private String idCreditoPantalla;
	private String idClienteLegado;
	private String cuenta;
	
	private String tipoCredito;			//MIC-tipoCredito/CLP-tipoComprobante/SHV-tipoValor
	private String tipoCreditoEnum;		//MIC-tipoCredito/CLP-tipoComprobante/SHV-tipoValor
	private String tipoComprobante;     //MIC-(CRE o REM) depende del tipoCredito/CLP-tipoComprobante
	private TipoComprobanteEnum tipoComprobanteEnum; //Para los semaforos -- MIC-(CRE o REM) depende del tipoCredito/CLP-tipoComprobante
	private TipoShivaEnum tipoComprobanteEnumShiva;  //Para los semaforos -- Origen: SHIVA 
	private String idTipoMedioPago;
	//
	
	private String subtipoDocumentoDesc;	//Este campo es la descripcion del subtipoDocumento y se debe mostrar en el title de esa columna
	private String nroDoc;
	private String fechaValor;
	private String saldoMonOrigen;//saldoMonedaOrigen
	private String moneda;
	private String impMonOrigen;//importeMonedaOrigen
	private String impPesificado;//importePesificado
	private String saldoPesificado;
	private String fechaAltaCredito;//fechaAlta
	private String fechaIngresoRecibo;//fechaIngresoRecibo
	private String fechaDeposito;//fechaDeposito
	private String fechaTrans;//fechaTransferencia
	private String fechaEmision;
	private String fechaVenc;//fechaVencimiento
	private String fechaUltimoMov;//fechaUltimoMovimiento
	private String tipoCambio;//tipoDeCambio
	private String provincia;
	private String cuit;
	private String estadoOrigen; //si es mic descEstadoFactura y si es calipso es estadoMorosidad , si es shiva estadovalor
	private String descripcionErrorValidacion;
	private String importeAUtilizar;//importeAUtilizar
	private Long   idValor;
	
	// MarcaReclamo
	private MarcaReclamoEnum marcaReclamo;
	private String marcaReclamoDescripcion; //Muestra Si o No en la pantalla
	
	//MarcaMigradosDeimos
	private SiNoEnum marcaMigradoDeimos;
	@SuppressWarnings("unused")
	private String marcaMigradoDeimosDesc;
	
	/**************************** WS *******************************/
	private TipoClaseComprobanteEnum claseComprobante;
	private String  sucursalComprobante;
	private String  numeroComprobante;
	
	private Integer cicloFacturacion;
	private String codigoTarifa;
	private String fechaVencimientoMora;
	private String holding;
	private Long idDocumentoCuentaCobranza;
	private String indicadorPeticionCorte;
	
	private String marketingCustomerGroup;
	private Long numeroAcuerdo;
	private String razonSocialCliente;
	private String subtipo;
	private String tipoCliente;
	private String unidadOperativa;
	
	private String origen;
	
	private boolean seleccionado;
	/************DEIMOS***************/
	private String estadoDeimos;			//estadoTramite (Descripcion)
	private EstadoTramiteDeimosEnum dmosEstadoTramite; // Estado en DEIMOs
	private SiNoEnum dmosApropiacionEnDeimos;
	private Long dmosCantidadDeCuotas;
	private Long dmosCantidadDeCuotasPagas;
	private Long dmosNroConvenio;
	

	/************ RESULTADOS DE VALIDACION ***************/
	private EstadoDebitoImportadoEnum resultadoValidacionEstado;
	private String resultadoValidacionCodigoError;
	private String resultadoValidacionDescripcionError;
	
	private String referenciaValor;//Nro. Documento
	private String descripcionTipoRetencion;//Subtipo Documento
		
	// SubTipo
	private TipoFacturaEnum tipoFactura; //Origen: MIC y Tipo: CRE esta definida mas arriba
	private Long codigoTipoRemanente;
	private TipoRemanenteEnum tipoFacturaMicRem; // (CodigoTipoRemanente) Origen: MIC y Tipo REM
	private TipoCreditoEnum tipoFacturaShiva; // Origen: SHIVA
	
	// Estado Origen: mic o calipso o shiva
	private EstadoOrigenEnum estadoOrigenEnum;
	
	// Motivo
	private String motivoDescripcion;
	private MotivoShivaEnum motivo; // Origen: SHIVA y Tipo: *
	
	// Moneda
	private MonedaEnum monedaEnum;
	
	// Origen
	private SistemaEnum sistemaOrigen;
	
	// Marca migrada en origen
	private SiNoEnum marcaMigradaOrigenEnum;
	
	// El documento esta incluido en otra operacion Shiva en edicion
	private SiNoEnum marcaPagoCompensacionEnProcesoShiva;
	@SuppressWarnings("unused")
	private String marcaPagoCompensacionEnProcesoShivaDesc;
	private String opeAsocAnalista;			//en este campo va a ir la lista de operaciones y analistas que 
											//tiene tomado el debito en forma parcial	
	private boolean sinDifDeCambio;		//checkSinDiferenciaCambio
	@SuppressWarnings("unused")
	private String sinDifDeCambiodesc;		//checkSinDiferenciaCambio
	private SiNoEnum documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;

	private SiNoEnum marcaReversionDeCobroProcesoPendiente;
	private SiNoEnum marcaReversionDeCobroEdicion;

	private MarcaCyQEnum marcaCyq;
	
	private String numeroReferenciaMic;
	
	private String sistemaDescripcion;
	
	//Para ordenar creditos
	private String orderByFecha;
	private String orderByIdClienteLegado;
	private String orderByNumeroIdentificatorio;
	
	private String monedaImporteUtilizar;
	
	
	//FECHA_BUSQUEDA
	//	Interdepósito --> FECHA_DEPOSITO
	//	Transferencia -->FECHA_TRANSFERENCIA
	//	Cheque --> FECHA_DEPOSITO
	//	Cheque Diferido --> FECHA_VENCIMIENTO
	//	Depósito Efectivo --> FECHA_DEPOSITO
	//	Retención --> FECHA_EMISION
	public String getFechaValorShiva(TipoCreditoEnum tipoCredito) {
		if (tipoCredito!=null) {
			if (TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE.equals(tipoCredito)
					|| TipoCreditoEnum.CHEQUE.equals(tipoCredito)
					|| TipoCreditoEnum.INTERDEPOSITO.equals(tipoCredito)
					|| TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO.equals(tipoCredito)
					|| TipoCreditoEnum.EFECTIVO.equals(tipoCredito)) {
				return this.fechaDeposito;
			} 
			if (TipoCreditoEnum.TRANSFERENCIA.equals(tipoCredito)) {
				return this.fechaTrans;
			}
			if (TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF.equals(tipoCredito)
					|| TipoCreditoEnum.CHEQUEDIF.equals(tipoCredito)) {
				return this.fechaVenc;
			}
			if (TipoCreditoEnum.RETENCION.equals(tipoCredito)) {
				return this.fechaEmision;
			}
		}
		return null;
	}
	
	
	/**
	 * Importe máximo por defecto
	 */
	private String importeMaxDefault = "none";
	
	/**
	 * @return the idCredito
	 */
	public String getIdCredito() {
		return idCredito;
	}
	/**
	 * @param idCredito the idCredito to set
	 */
	public void setIdCredito(String idCredito) {
		this.idCredito = idCredito;
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
	 * @return the fechaValor
	 */
	public String getFechaValor() {
		return fechaValor;
	}
	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(String fechaValor) {
		this.fechaValor = fechaValor;
	}
	/**
	 * @return the saldoMonOrigen
	 */
	public String getSaldoMonOrigen() {
		return saldoMonOrigen;
	}
	/**
	 * @param saldoMonOrigen the saldoMonOrigen to set
	 */
	public void setSaldoMonOrigen(String saldoMonOrigen) {
		this.saldoMonOrigen = saldoMonOrigen;
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
	 * @return the impMonOrigen
	 */
	public String getImpMonOrigen() {
		return impMonOrigen;
	}
	/**
	 * @param impMonOrigen the impMonOrigen to set
	 */
	public void setImpMonOrigen(String impMonOrigen) {
		this.impMonOrigen = impMonOrigen;
	}
	/**
	 * @return the impPesificado
	 */
	public String getImpPesificado() {
		return impPesificado;
	}
	/**
	 * @param impPesificado the impPesificado to set
	 */
	public void setImpPesificado(String impPesificado) {
		this.impPesificado = impPesificado;
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
	 * @return the fechaAltaCredito
	 */
	public String getFechaAltaCredito() {
		return fechaAltaCredito;
	}
	/**
	 * @param fechaAltaCredito the fechaAltaCredito to set
	 */
	public void setFechaAltaCredito(String fechaAltaCredito) {
		this.fechaAltaCredito = fechaAltaCredito;
	}
	/**
	 * @return the fechaIngresoRecibo
	 */
	public String getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}
	/**
	 * @param fechaIngresoRecibo the fechaIngresoRecibo to set
	 */
	public void setFechaIngresoRecibo(String fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}
	/**
	 * @return the fechaDeposito
	 */
	public String getFechaDeposito() {
		return fechaDeposito;
	}
	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	/**
	 * @return the fechaTrans
	 */
	public String getFechaTrans() {
		return fechaTrans;
	}
	/**
	 * @param fechaTrans the fechaTrans to set
	 */
	public void setFechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
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
	 * @return the fechaUltimoMov
	 */
	public String getFechaUltimoMov() {
		return fechaUltimoMov;
	}
	/**
	 * @param fechaUltimoMov the fechaUltimoMov to set
	 */
	public void setFechaUltimoMov(String fechaUltimoMov) {
		this.fechaUltimoMov = fechaUltimoMov;
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
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
	 * @return the estadoOrigen
	 */
	public String getEstadoOrigen() {
		return estadoOrigen;
	}
	/**
	 * @param estadoValor the estadoValor to set
	 */
	public void setEstadoOrigen(String estadoOrigen) {
		this.estadoOrigen = estadoOrigen;
	}
	/**
	 * @return the importeAUtilizar
	 */
	public String getImporteAUtilizar() {
		return importeAUtilizar;
	}
	/**
	 * @param importeAUtilizar the importeAUtilizar to set
	 */
	public void setImporteAUtilizar(String importeAUtilizar) {
		this.importeAUtilizar = importeAUtilizar;
	}
	/**
	 * @return the idValor
	 */
	public Long getIdValor() {
		return idValor;
	}
	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	/**
	 * @return the tipoComprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	/**
	 * @param tipoComprobante the tipoComprobante to set
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
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
	 * @return the idDocumentoCuentaCobranza
	 */
	public Long getIdDocumentoCuentaCobranza() {
		return idDocumentoCuentaCobranza;
	}
	/**
	 * @param idDocumentoCuentaCobranza the idDocumentoCuentaCobranza to set
	 */
	public void setIdDocumentoCuentaCobranza(Long idDocumentoCuentaCobranza) {
		this.idDocumentoCuentaCobranza = idDocumentoCuentaCobranza;
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
	 * @return the marcaMigradoDeimos
	 */
	public SiNoEnum getMarcaMigradoDeimos() {
		return marcaMigradoDeimos;
	}
	/**
	 * @param marcaMigradoDeimos the marcaMigradoDeimos to set
	 */
	public void setMarcaMigradoDeimos(SiNoEnum marcaMigradoDeimos) {
		this.marcaMigradoDeimos = marcaMigradoDeimos;
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
	 * @return the numeroAcuerdo
	 */
	public Long getNumeroAcuerdo() {
		return numeroAcuerdo;
	}
	/**
	 * @param numeroAcuerdo the numeroAcuerdo to set
	 */
	public void setNumeroAcuerdo(Long numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
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
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}
	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
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
	 * @return the tipoCredito
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}
	/**
	 * @param tipoCredito the tipoCredito to set
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
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
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
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
	 * @return the tipoFacturaMicRem
	 */
	public TipoRemanenteEnum getTipoFacturaMicRem() {
		return tipoFacturaMicRem;
	}
	/**
	 * @param tipoFacturaMicRem the tipoFacturaMicRem to set
	 */
	public void setTipoFacturaMicRem(
			TipoRemanenteEnum tipoFacturaMicRem) {
		this.tipoFacturaMicRem = tipoFacturaMicRem;
	}
	/**
	 * @return the tipoFacturaShiva
	 */
	public TipoCreditoEnum getTipoFacturaShiva() {
		return tipoFacturaShiva;
	}
	/**
	 * @param tipoFacturaShiva the tipoFacturaShiva to set
	 */
	public void setTipoFacturaShiva(TipoCreditoEnum tipoFacturaShiva) {
		this.tipoFacturaShiva = tipoFacturaShiva;
	}
	
	/**
	 * @return the motivo
	 */
	public MotivoShivaEnum getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(MotivoShivaEnum motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the monedaEnum
	 */
	public MonedaEnum getMonedaEnum() {
		return monedaEnum;
	}
	/**
	 * @param monedaEnum the monedaEnum to set
	 */
	public void setMonedaEnum(MonedaEnum monedaEnum) {
		this.monedaEnum = monedaEnum;
	}
	
	public MonedaEnum getMonedaImporteUtilizarEnum() {
		MonedaEnum monedaOperacion = MonedaEnum.getEnumByName(monedaImporteUtilizar);
		if(!Validaciones.isObjectNull(monedaOperacion)){
			return monedaOperacion;
		} else {
			return MonedaEnum.getEnumBySigno2(monedaImporteUtilizar);
		}
	}
	public MonedaEnum getMonedaImporteEnum() {
		return this.getMonedaImporteUtilizarEnum();
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
	 * @return the tipoComprobanteEnumShiva
	 */
	public TipoShivaEnum getTipoComprobanteEnumShiva() {
		return tipoComprobanteEnumShiva;
	}
	/**
	 * @param tipoComprobanteEnumShiva the tipoComprobanteEnumShiva to set
	 */
	public void setTipoComprobanteEnumShiva(TipoShivaEnum tipoComprobanteEnumShiva) {
		this.tipoComprobanteEnumShiva = tipoComprobanteEnumShiva;
	}
	
	public String getSistemaDescripcion() {
		return sistemaDescripcion;
	}
	public void setSistemaDescripcion(String sistemaDescripcion) {
		this.sistemaDescripcion = sistemaDescripcion;
	}
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	public String getReferenciaValor() {
		return referenciaValor;
	}
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}
	public String getDescripcionTipoRetencion() {
		return descripcionTipoRetencion;
	}
	public void setDescripcionTipoRetencion(String descripcionTipoRetencion) {
		this.descripcionTipoRetencion = descripcionTipoRetencion;
	}
	
	public String getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}
	public void setNumeroReferenciaMic(String numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
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
	public String getIdPantalla() {
		return this.getIdCreditoPantalla();
	}
	public String getDescripcionErrorValidacion() {
		return descripcionErrorValidacion;
	}
	public void setDescripcionErrorValidacion(String descripcionErrorValidacion) {
		this.descripcionErrorValidacion = descripcionErrorValidacion;
	}
	@Override
	public String getClaseString() {
		return this.getClass().getSimpleName();
	}
	public String getIdCreditoPantalla() {
		return idCreditoPantalla;
	}
	public void setIdCreditoPantalla(String idCreditoPantalla) {
		this.idCreditoPantalla = idCreditoPantalla;
	}
	public String getEstadoDeimos() {
		return estadoDeimos;
	}
	public void setEstadoDeimos(String estadoDeimos) {
		this.estadoDeimos = estadoDeimos;
	}
	public String getSubtipoDocumentoDesc() {
		return subtipoDocumentoDesc;
	}
	public void setSubtipoDocumentoDesc(String subtipoDocumentoDesc) {
		this.subtipoDocumentoDesc = subtipoDocumentoDesc;
	}
	public String getOpeAsocAnalista() {
		return opeAsocAnalista;
	}
	public void setOpeAsocAnalista(String opeAsocAnalista) {
		this.opeAsocAnalista = opeAsocAnalista;
	}
	public boolean isSinDifDeCambio() {
		return sinDifDeCambio;
	}
	public void setSinDifDeCambio(boolean sinDifDeCambio) {
		this.sinDifDeCambio = sinDifDeCambio;
	}
	public Long getCodigoTipoRemanente() {
		return codigoTipoRemanente;
	}
	public void setCodigoTipoRemanente(Long codigoTipoRemanente) {
		this.codigoTipoRemanente = codigoTipoRemanente;
	}
	public EstadoOrigenEnum getEstadoOrigenEnum() {
		return estadoOrigenEnum;
	}
	public void setEstadoOrigenEnum(EstadoOrigenEnum estadoOrigenEnum) {
		this.estadoOrigenEnum = estadoOrigenEnum;
	}
	public String getImporteMaxDefault() {
		return importeMaxDefault;
	}
	public void setImporteMaxDefault(String importeMaxDefault) {
		this.importeMaxDefault = importeMaxDefault;
	}
	public String getTipoCreditoEnum() {
		return tipoCreditoEnum;
	}
	public void setTipoCreditoEnum(String tipoCreditoEnum) {
		this.tipoCreditoEnum = tipoCreditoEnum;
	}
	public String getMotivoDescripcion() {
		return motivoDescripcion;
	}
	public void setMotivoDescripcion(String motivoDescripcion) {
		this.motivoDescripcion = motivoDescripcion;
	}
	public SiNoEnum getDmosApropiacionEnDeimos() {
		return dmosApropiacionEnDeimos;
	}
	public void setDmosApropiacionEnDeimos(SiNoEnum dmosApropiacionEnDeimos) {
		this.dmosApropiacionEnDeimos = dmosApropiacionEnDeimos;
	}
	public Long getDmosCantidadDeCuotas() {
		return dmosCantidadDeCuotas;
	}
	public void setDmosCantidadDeCuotas(Long dmosCantidadDeCuotas) {
		this.dmosCantidadDeCuotas = dmosCantidadDeCuotas;
	}
	public Long getDmosNroConvenio() {
		return dmosNroConvenio;
	}
	public void setDmosNroConvenio(Long dmosNroConvenio) {
		this.dmosNroConvenio = dmosNroConvenio;
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
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}
	public void setIdTipoMedioPago(String idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}
	public String getMarcaMigradoDeimosDesc() {
		String desc = "";
		if(!Validaciones.isObjectNull(marcaMigradoDeimos)) {
			desc = marcaMigradoDeimos.getDescripcion();
		}
		return desc;
	}
	public void setMarcaMigradoDeimosDesc(String marcaMigradoDeimosDesc) {
		this.marcaMigradoDeimosDesc = marcaMigradoDeimosDesc;
	}
	public String getMarcaPagoCompensacionEnProcesoShivaDesc() {
		return marcaPagoCompensacionEnProcesoShiva.getDescripcion();
	}
	public String getSinDifDeCambiodesc() {
		String desc = "";
		if(isSinDifDeCambio()){
			desc = "SI";
		}else{
			desc = "NO";
		}
		return desc;
	}
	public void setSinDifDeCambiodesc(String sinDifDeCambiodesc) {
		this.sinDifDeCambiodesc = sinDifDeCambiodesc;
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
	public String getMonedaImporteUtilizar() {
		return monedaImporteUtilizar;
	}
	public void setMonedaImporteUtilizar(String monedaImporteUtilizar) {
		this.monedaImporteUtilizar = monedaImporteUtilizar;
	}
}