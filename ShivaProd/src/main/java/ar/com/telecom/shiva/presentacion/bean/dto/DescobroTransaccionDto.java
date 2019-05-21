package ar.com.telecom.shiva.presentacion.bean.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DescobroTransaccionDto extends DTO {

	private static final long serialVersionUID = 1L;

	private String numeroTransaccionFormateado;		
	private String estadoTransaccion;		
	private String sistemaDoc;		
	private String tipoDocumento;		
	private String subtipoDocumento;	
	private String subtipoDocumentoDesc;	
	private String nroDoc;		
	private String numeroReferenciaMic;	
	private String fechaVenc;
	private String fecha2doVenc;
	private SiNoEnum cobrarAl2doVenc;
	private String moneda;	
	private String fechaCobro;		
	private String importe;		
	private String tipoDeCambioFechaCobro;
	private String tipoDeCambioFechaEmision;		
	private String importeAplicadoFechaEmisionMonedaOrigen;		
	private String sistemaMedioPago;		
	private String tipoMedioPago;		
	private String subtipoMedioPago;
	private String subtipoMedioPagoDesc;		
	private String referenciaMedioPago;	
	private String monedaMedioPago;
	private String fechaMedioPago;
	private String importeMedioPago;
	private String tipoDeCambioFechaCobroMedioPago;
	private String tipoDeCambioFechaEmisionMedioPago;		
	private String importeAplicadoFechaEmisionMonedaOrigenMedioPago;	
	private String cobradorInteresesTrasladados;
	private String intereses;  
	private boolean trasladarIntereses;  //check buttom
	private String trasladarInteresesFormateado;
	private String porcABonificar;
	private String importeABonificar;
	private String sistemaAcuerdo;
	private String acuerdoDestinoCargos;
	private String estadoAcuerdoDestinoCargos;
	private String clienteAcuerdoDestinoCargos;
	private String estadoCargoProximaFactura;
	private String sistemaAcuerdoFactDestinoContracargos;
	private String sistemaAcuerdoFactDestinoContracargosDescripcion;
	private String acuerdoFactDestinoContracargos;
	private String estadoAcuerdoDestinoContracargos;
	private String clienteAcuerdoDestinoContracargos;
	private String mensajeTransaccion;
	private String tipoMensajeTransaccion;
	private String mensajeDebito;
	private String mensajeCredito;
	private String numeroTransaccion;
	private String numeroTransaccionOriginal;
	private String tipoMensajeDebito;
	private String tipoMensajeCredito;
	private String colorLetraRegistro;
	private boolean soloLecturaPantalla;
	private boolean habilitarBtnSimular = true;
	
	private boolean micAcuerdoExistente;
	
	private String idFactura;
	private String idMedioPago;
	private String idTratamientoDiferencia;
	private Long idCobro;
	private String apocopeGrupo;
	private String numeroGrupo;
	private String numeroTransaccionFicticioFormateado;	
	private String numeroTransaccionFicticio;
	
	/**
	 * @return the idFactura
	 */
	public String getIdFactura() {
		return idFactura;
	}
	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * @return the idMedioPago
	 */
	public String getIdMedioPago() {
		return idMedioPago;
	}
	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(String idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	/**
	 * @return the idTratamientoDiferencia
	 */
	public String getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}
	/**
	 * @param idTratamientoDiferencia the idTratamientoDiferencia to set
	 */
	public void setIdTratamientoDiferencia(String idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}
	/**
	 * @return the numeroTransaccionFormateado
	 */
	public String getNumeroTransaccionFormateado() {
		return numeroTransaccionFormateado;
	}
	/**
	 * @param numeroTransaccionFormateado the numeroTransaccionFormateado to set
	 */
	public void setNumeroTransaccionFormateado(String numeroTransaccionFormateado) {
		this.numeroTransaccionFormateado = numeroTransaccionFormateado;
	}
	/**
	 * @return the estadoTransaccion
	 */
	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}
	/**
	 * @param estadoTransaccion the estadoTransaccion to set
	 */
	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}
	/**
	 * @return the sistemaDoc
	 */
	public String getSistemaDoc() {
		return sistemaDoc;
	}
	/**
	 * @param sistemaDoc the sistemaDoc to set
	 */
	public void setSistemaDoc(String sistemaDoc) {
		this.sistemaDoc = sistemaDoc;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * @return the subtipoDocumentoDesc
	 */
	public String getSubtipoDocumentoDesc() {
		return subtipoDocumentoDesc;
	}
	/**
	 * @param subtipoDocumentoDesc the subtipoDocumentoDesc to set
	 */
	public void setSubtipoDocumentoDesc(String subtipoDocumentoDesc) {
		this.subtipoDocumentoDesc = subtipoDocumentoDesc;
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
	 * @return the fechaCobro
	 */
	public String getFechaCobro() {
		return fechaCobro;
	}
	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
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
	/**
	 * @return the tipoDeCambioFechaCobro
	 */
	public String getTipoDeCambioFechaCobro() {
		return tipoDeCambioFechaCobro;
	}
	/**
	 * @param tipoDeCambioFechaCobro the tipoDeCambioFechaCobro to set
	 */
	public void setTipoDeCambioFechaCobro(String tipoDeCambioFechaCobro) {
		this.tipoDeCambioFechaCobro = tipoDeCambioFechaCobro;
	}
	/**
	 * @return the tipoDeCambioFechaEmision
	 */
	public String getTipoDeCambioFechaEmision() {
		return tipoDeCambioFechaEmision;
	}
	/**
	 * @param tipoDeCambioFechaEmision the tipoDeCambioFechaEmision to set
	 */
	public void setTipoDeCambioFechaEmision(String tipoDeCambioFechaEmision) {
		this.tipoDeCambioFechaEmision = tipoDeCambioFechaEmision;
	}
	/**
	 * @return the importeAplicadoFechaEmisionMonedaOrigen
	 */
	public String getImporteAplicadoFechaEmisionMonedaOrigen() {
		return importeAplicadoFechaEmisionMonedaOrigen;
	}
	/**
	 * @param importeAplicadoFechaEmisionMonedaOrigen the importeAplicadoFechaEmisionMonedaOrigen to set
	 */
	public void setImporteAplicadoFechaEmisionMonedaOrigen(
			String importeAplicadoFechaEmisionMonedaOrigen) {
		this.importeAplicadoFechaEmisionMonedaOrigen = importeAplicadoFechaEmisionMonedaOrigen;
	}
	/**
	 * @return the sistemaMedioPago
	 */
	public String getSistemaMedioPago() {
		return sistemaMedioPago;
	}
	/**
	 * @param sistemaMedioPago the sistemaMedioPago to set
	 */
	public void setSistemaMedioPago(String sistemaMedioPago) {
		this.sistemaMedioPago = sistemaMedioPago;
	}
	/**
	 * @return the tipoMedioPago
	 */
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}
	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	/**
	 * @return the subtipoMedioPago
	 */
	public String getSubtipoMedioPago() {
		return subtipoMedioPago;
	}
	/**
	 * @param subtipoMedioPago the subtipoMedioPago to set
	 */
	public void setSubtipoMedioPago(String subtipoMedioPago) {
		this.subtipoMedioPago = subtipoMedioPago;
	}
	/**
	 * @return the subtipoMedioPagoDesc
	 */
	public String getSubtipoMedioPagoDesc() {
		return subtipoMedioPagoDesc;
	}
	/**
	 * @param subtipoMedioPagoDesc the subtipoMedioPagoDesc to set
	 */
	public void setSubtipoMedioPagoDesc(String subtipoMedioPagoDesc) {
		this.subtipoMedioPagoDesc = subtipoMedioPagoDesc;
	}
	/**
	 * @return the referenciaMedioPago
	 */
	public String getReferenciaMedioPago() {
		return referenciaMedioPago;
	}
	/**
	 * @param referenciaMedioPago the referenciaMedioPago to set
	 */
	public void setReferenciaMedioPago(String referenciaMedioPago) {
		this.referenciaMedioPago = referenciaMedioPago;
	}
	/**
	 * @return the fechaMedioPago
	 */
	public String getFechaMedioPago() {
		return fechaMedioPago;
	}
	/**
	 * @param fechaMedioPago the fechaMedioPago to set
	 */
	public void setFechaMedioPago(String fechaMedioPago) {
		this.fechaMedioPago = fechaMedioPago;
	}
	/**
	 * @return the importeMedioPago
	 */
	public String getImporteMedioPago() {
		return importeMedioPago;
	}
	/**
	 * @param importeMedioPago the importeMedioPago to set
	 */
	public void setImporteMedioPago(String importeMedioPago) {
		this.importeMedioPago = importeMedioPago;
	}
	/**
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}
	/**
	 * @param intereses the intereses to set
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	/**
	 * @return the trasladarIntereses
	 */
	public boolean isTrasladarIntereses() {
		return trasladarIntereses;
	}
	/**
	 * @param trasladarIntereses the trasladarIntereses to set
	 */
	public void setTrasladarIntereses(boolean trasladarIntereses) {
		this.trasladarIntereses = trasladarIntereses;
	}
	/**
	 * @return the trasladarInteresesFormateado
	 */
	public String getTrasladarInteresesFormateado() {
		return trasladarInteresesFormateado;
	}
	/**
	 * @param trasladarInteresesFormateado the trasladarInteresesFormateado to set
	 */
	public void setTrasladarInteresesFormateado(String trasladarInteresesFormateado) {
		this.trasladarInteresesFormateado = trasladarInteresesFormateado;
	}
	/**
	 * @return the porcABonificar
	 */
	public String getPorcABonificar() {
		return porcABonificar;
	}
	/**
	 * @param porcABonificar the porcABonificar to set
	 */
	public void setPorcABonificar(String porcABonificar) {
		this.porcABonificar = porcABonificar;
	}
	/**
	 * @return the importeABonificar
	 */
	public String getImporteABonificar() {
		return importeABonificar;
	}
	/**
	 * @param importeABonificar the importeABonificar to set
	 */
	public void setImporteABonificar(String importeABonificar) {
		this.importeABonificar = importeABonificar;
	}
	/**
	 * @return the acuerdoDestinoCargos
	 */
	public String getAcuerdoDestinoCargos() {
		return acuerdoDestinoCargos;
	}
	/**
	 * @param acuerdoDestinoCargos the acuerdoDestinoCargos to set
	 */
	public void setAcuerdoDestinoCargos(String acuerdoDestinoCargos) {
		this.acuerdoDestinoCargos = acuerdoDestinoCargos;
	}
	/**
	 * @return the estadoAcuerdoDestinoCargos
	 */
	public String getEstadoAcuerdoDestinoCargos() {
		return estadoAcuerdoDestinoCargos;
	}
	/**
	 * @param estadoAcuerdoDestinoCargos the estadoAcuerdoDestinoCargos to set
	 */
	public void setEstadoAcuerdoDestinoCargos(String estadoAcuerdoDestinoCargos) {
		this.estadoAcuerdoDestinoCargos = estadoAcuerdoDestinoCargos;
	}
	/**
	 * @return the sistemaAcuerdoFactDestinoContracargosDescripcion
	 */
	public String getSistemaAcuerdoFactDestinoContracargosDescripcion() {
		return sistemaAcuerdoFactDestinoContracargosDescripcion;
	}
	/**
	 * @param sistemaAcuerdoFactDestinoContracargosDescripcion the sistemaAcuerdoFactDestinoContracargosDescripcion to set
	 */
	public void setSistemaAcuerdoFactDestinoContracargosDescripcion(
			String sistemaAcuerdoFactDestinoContracargosDescripcion) {
		this.sistemaAcuerdoFactDestinoContracargosDescripcion = sistemaAcuerdoFactDestinoContracargosDescripcion;
	}
	/**
	 * @return the acuerdoFactDestinoContracargos
	 */
	public String getAcuerdoFactDestinoContracargos() {
		return acuerdoFactDestinoContracargos;
	}
	/**
	 * @param acuerdoFactDestinoContracargos the acuerdoFactDestinoContracargos to set
	 */
	public void setAcuerdoFactDestinoContracargos(
			String acuerdoFactDestinoContracargos) {
		this.acuerdoFactDestinoContracargos = acuerdoFactDestinoContracargos;
	}
	/**
	 * @return the mensajeTransaccion
	 */
	public String getMensajeTransaccion() {
		return mensajeTransaccion;
	}
	/**
	 * @param mensajeTransaccion the mensajeTransaccion to set
	 */
	public void setMensajeTransaccion(String mensajeTransaccion) {
		this.mensajeTransaccion = mensajeTransaccion;
	}
	/**
	 * @return the mensajeDebito
	 */
	public String getMensajeDebito() {
		return mensajeDebito;
	}
	/**
	 * @param mensajeDebito the mensajeDebito to set
	 */
	public void setMensajeDebito(String mensajeDebito) {
		this.mensajeDebito = mensajeDebito;
	}
	/**
	 * @return the mensajeCredito
	 */
	public String getMensajeCredito() {
		return mensajeCredito;
	}
	/**
	 * @param mensajeCredito the mensajeCredito to set
	 */
	public void setMensajeCredito(String mensajeCredito) {
		this.mensajeCredito = mensajeCredito;
	}
	/**
	 * @return the numeroTransaccion
	 */
	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}
	/**
	 * @param numeroTransaccion the numeroTransaccion to set
	 */
	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	public String getNumeroTransaccionOriginal() {
		return numeroTransaccionOriginal;
	}
	public void setNumeroTransaccionOriginal(String numeroTransaccionOriginal) {
		this.numeroTransaccionOriginal = numeroTransaccionOriginal;
	}
	/**
	 * @return the tipoMensajeTransaccion
	 */
	public String getTipoMensajeTransaccion() {
		return tipoMensajeTransaccion;
	}
	/**
	 * @param tipoMensajeTransaccion the tipoMensajeTransaccion to set
	 */
	public void setTipoMensajeTransaccion(String tipoMensajeTransaccion) {
		this.tipoMensajeTransaccion = tipoMensajeTransaccion;
	}
	/**
	 * @return the tipoMensajeDebito
	 */
	public String getTipoMensajeDebito() {
		return tipoMensajeDebito;
	}
	/**
	 * @param tipoMensajeDebito the tipoMensajeDebito to set
	 */
	public void setTipoMensajeDebito(String tipoMensajeDebito) {
		this.tipoMensajeDebito = tipoMensajeDebito;
	}
	/**
	 * @return the tipoMensajeCredito
	 */
	public String getTipoMensajeCredito() {
		return tipoMensajeCredito;
	}
	/**
	 * @param tipoMensajeCredito the tipoMensajeCredito to set
	 */
	public void setTipoMensajeCredito(String tipoMensajeCredito) {
		this.tipoMensajeCredito = tipoMensajeCredito;
	}
	/**
	 * @return the colorLetraRegistro
	 */
	public String getColorLetraRegistro() {
		return colorLetraRegistro;
	}
	/**
	 * @param colorLetraRegistro the colorLetraRegistro to set
	 */
	public void setColorLetraRegistro(String colorLetraRegistro) {
		this.colorLetraRegistro = colorLetraRegistro;
	}
	
	public String getColorFondoRegistro() {
		Integer operacion = 0;
		if(!Validaciones.isEmptyString(this.numeroTransaccion)){
			operacion = new Integer(numeroTransaccion);
		}
		
		if(operacion % 2 == 0){
			return "0";
		} else {
			return "1";
		}
	}
	/**
	 * @return the fecha2doVenc
	 */
	public String getFecha2doVenc() {
		return fecha2doVenc;
	}
	/**
	 * @param fecha2doVenc the fecha2doVenc to set
	 */
	public void setFecha2doVenc(String fecha2doVenc) {
		this.fecha2doVenc = fecha2doVenc;
	}
	/**
	 * @return the cobrarAl2doVenc
	 */
	public SiNoEnum getCobrarAl2doVenc() {
		return cobrarAl2doVenc;
	}
	/**
	 * @param cobrarAl2doVenc the cobrarAl2doVenc to set
	 */
	public void setCobrarAl2doVenc(SiNoEnum cobrarAl2doVenc) {
		this.cobrarAl2doVenc = cobrarAl2doVenc;
	}
	/**
	 * @return the soloLecturaPantalla
	 */
	public boolean isSoloLecturaPantalla() {
		return soloLecturaPantalla;
	}
	/**
	 * @param soloLecturaPantalla the soloLecturaPantalla to set
	 */
	public void setSoloLecturaPantalla(boolean soloLecturaPantalla) {
		this.soloLecturaPantalla = soloLecturaPantalla;
	}
	/**
	 * @return the sistemaAcuerdo
	 */
	public String getSistemaAcuerdo() {
		return sistemaAcuerdo;
	}
	/**
	 * @param sistemaAcuerdo the sistemaAcuerdo to set
	 */
	public void setSistemaAcuerdo(String sistemaAcuerdo) {
		this.sistemaAcuerdo = sistemaAcuerdo;
	}
	/**
	 * @return the estadoAcuerdoDestinoContracargos
	 */
	public String getEstadoAcuerdoDestinoContracargos() {
		return estadoAcuerdoDestinoContracargos;
	}
	/**
	 * @param estadoAcuerdoDestinoContracargos the estadoAcuerdoDestinoContracargos to set
	 */
	public void setEstadoAcuerdoDestinoContracargos(
			String estadoAcuerdoDestinoContracargos) {
		this.estadoAcuerdoDestinoContracargos = estadoAcuerdoDestinoContracargos;
	}
	/**
	 * @return the estadoCargoProximaFactura
	 */
	public String getEstadoCargoProximaFactura() {
		return estadoCargoProximaFactura;
	}
	/**
	 * @param estadoCargoProximaFactura the estadoCargoProximaFactura to set
	 */
	public void setEstadoCargoProximaFactura(String estadoCargoProximaFactura) {
		this.estadoCargoProximaFactura = estadoCargoProximaFactura;
	}
	/**
	 * @return the clienteAcuerdoDestinoCargos
	 */
	public String getClienteAcuerdoDestinoCargos() {
		return clienteAcuerdoDestinoCargos;
	}
	/**
	 * @param clienteAcuerdoDestinoCargos the clienteAcuerdoDestinoCargos to set
	 */
	public void setClienteAcuerdoDestinoCargos(
			String clienteAcuerdoDestinoCargos) {
		this.clienteAcuerdoDestinoCargos = clienteAcuerdoDestinoCargos;
	}
	/**
	 * @return the clienteAcuerdoDestinoContracargos
	 */
	public String getClienteAcuerdoDestinoContracargos() {
		return clienteAcuerdoDestinoContracargos;
	}
	/**
	 * @param clienteAcuerdoDestinoContracargos the clienteAcuerdoDestinoContracargos to set
	 */
	public void setClienteAcuerdoDestinoContracargos(
			String clienteAcuerdoDestinoContracargos) {
		this.clienteAcuerdoDestinoContracargos = clienteAcuerdoDestinoContracargos;
	}
	/**
	 * @return the micAcuerdoExistente
	 */
	public boolean isMicAcuerdoExistente() {
		return micAcuerdoExistente;
	}
	/**
	 * @param micAcuerdoExistente the micAcuerdoExistente to set
	 */
	public void setMicAcuerdoExistente(boolean micAcuerdoExistente) {
		this.micAcuerdoExistente = micAcuerdoExistente;
	}
	/**
	 * @return the sistemaAcuerdoFactDestinoContracargos
	 */
	public String getSistemaAcuerdoFactDestinoContracargos() {
		return sistemaAcuerdoFactDestinoContracargos;
	}
	/**
	 * @param sistemaAcuerdoFactDestinoContracargos the sistemaAcuerdoFactDestinoContracargos to set
	 */
	public void setSistemaAcuerdoFactDestinoContracargos(
			String sistemaAcuerdoFactDestinoContracargos) {
		this.sistemaAcuerdoFactDestinoContracargos = sistemaAcuerdoFactDestinoContracargos;
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
	public boolean isHabilitarBtnSimular() {
		return habilitarBtnSimular;
	}
	public void setHabilitarBtnSimular(boolean habilitarBtnSimular) {
		this.habilitarBtnSimular = habilitarBtnSimular;
	}
	/**
	 * @return the cobradorInteresesTrasladados
	 */
	public String getCobradorInteresesTrasladados() {
		return cobradorInteresesTrasladados;
	}
	/**
	 * @param cobradorInteresesTrasladados the cobradorInteresesTrasladados to set
	 */
	public void setCobradorInteresesTrasladados(
			String cobradorInteresesTrasladados) {
		this.cobradorInteresesTrasladados = cobradorInteresesTrasladados;
	}
	/**
	 * @return the monedaMedioPago
	 */
	public String getMonedaMedioPago() {
		return monedaMedioPago;
	}
	/**
	 * @param monedaMedioPago the monedaMedioPago to set
	 */
	public void setMonedaMedioPago(String monedaMedioPago) {
		this.monedaMedioPago = monedaMedioPago;
	}
	/**
	 * @return the tipoDeCambioFechaCobroMedioPago
	 */
	public String getTipoDeCambioFechaCobroMedioPago() {
		return tipoDeCambioFechaCobroMedioPago;
	}
	/**
	 * @param tipoDeCambioFechaCobroMedioPago the tipoDeCambioFechaCobroMedioPago to set
	 */
	public void setTipoDeCambioFechaCobroMedioPago(
			String tipoDeCambioFechaCobroMedioPago) {
		this.tipoDeCambioFechaCobroMedioPago = tipoDeCambioFechaCobroMedioPago;
	}
	/**
	 * @return the tipoDeCambioFechaEmisionMedioPago
	 */
	public String getTipoDeCambioFechaEmisionMedioPago() {
		return tipoDeCambioFechaEmisionMedioPago;
	}
	/**
	 * @param tipoDeCambioFechaEmisionMedioPago the tipoDeCambioFechaEmisionMedioPago to set
	 */
	public void setTipoDeCambioFechaEmisionMedioPago(
			String tipoDeCambioFechaEmisionMedioPago) {
		this.tipoDeCambioFechaEmisionMedioPago = tipoDeCambioFechaEmisionMedioPago;
	}
	/**
	 * @return the importeAplicadoFechaEmisionMonedaOrigenMedioPago
	 */
	public String getImporteAplicadoFechaEmisionMonedaOrigenMedioPago() {
		return importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	}
	/**
	 * @param importeAplicadoFechaEmisionMonedaOrigenMedioPago the importeAplicadoFechaEmisionMonedaOrigenMedioPago to set
	 */
	public void setImporteAplicadoFechaEmisionMonedaOrigenMedioPago(
			String importeAplicadoFechaEmisionMonedaOrigenMedioPago) {
		this.importeAplicadoFechaEmisionMonedaOrigenMedioPago = importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	}
	
	/**
	 * @return the apocopeGrupo
	 */
	public String getApocopeGrupo() {
		return apocopeGrupo;
	}
	/**
	 * @param apocopeGrupo the apocopeGrupo to set
	 */
	public void setApocopeGrupo(String apocopeGrupo) {
		this.apocopeGrupo = apocopeGrupo;
	}
	/**
	 * @return the numeroGrupo
	 */
	public String getNumeroGrupo() {
		return numeroGrupo;
	}
	/**
	 * @param numeroGrupo the numeroGrupo to set
	 */
	public void setNumeroGrupo(String numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}
	/**
	 * @return the numeroTransaccionFicticioFormateado
	 */
	public String getNumeroTransaccionFicticioFormateado() {
		return numeroTransaccionFicticioFormateado;
	}
	/**
	 * @param numeroTransaccionFicticioFormateado the numeroTransaccionFicticioFormateado to set
	 */
	public void setNumeroTransaccionFicticioFormateado(String numeroTransaccionFicticioFormateado) {
		this.numeroTransaccionFicticioFormateado = numeroTransaccionFicticioFormateado;
	}
	/**
	 * @return the numeroTransaccionFicticio
	 */
	public String getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}
	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(String numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}
}
