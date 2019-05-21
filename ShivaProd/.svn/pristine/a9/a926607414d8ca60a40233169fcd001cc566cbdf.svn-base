package ar.com.telecom.shiva.presentacion.bean.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CobroTransaccionDto extends DTO  {
	
	private static final long serialVersionUID = 20180918L;

	private String numeroTransaccionFormateado;
	private String numeroTransaccionFicticioFormateado;
	private String numeroTransaccionFicticio;
	private String estadoTransaccion;
	private String sistemaDoc;
	private String tipoDocumento;
	private String subtipoDocumento;	
	private String subtipoDocumentoDesc;	
	private String nroDoc;		
	private String numeroReferencia;	
	private String fechaVenc;
	private String fecha2doVenc;
	private SiNoEnum cobrarAl2doVenc;
	private Boolean noHabilitadoTrasladarIntereses;
	private String moneda;	
	private String fechaCobro;		
	private String importe;		
	private String tipoDeCambioFechaCobro;
	private String tipoDeCambioFechaEmision;		
	private String importeAplicadoFechaEmisionMonedaOrigen;		
	private String sistemaMedioPago;		
	private String tipoMedioPago;
	private String tipoMedioPagoNombre;
	private String subtipoMedioPago;
	private String subtipoMedioPagoDesc;		
	private String referenciaMedioPago;		
	private String fechaMedioPago;
	private String monedaMedioPago;
	private String importeMedioPago;
	private String tipoDeCambioFechaCobroMedioPago;
	private String tipoDeCambioFechaEmisionMedioPago;		
	private String importeAplicadoFechaEmisionMonedaOrigenMedioPago;
	private String intereses;
	private String cobradorInteresesTrasladados;
	private boolean trasladarIntereses;
	private boolean generaInteresesParamUso;
	private String trasladarInteresesFormateado;
	private String porcABonificar;
	private String importeABonificar;
	private String acuerdoDestinoCargos;
	private String acuerdoDestinoCargosOriginal;
	private String estadoAcuerdoDestinoCargos;
	private String clienteAcuerdoDestinoCargos;
	private String mensajeTransaccion;
	private String mensajeDebito;
	private String mensajeCredito;
	private String tipoMensajeTransaccion;
	private String tipoMensajeDebito;
	private String tipoMensajeCredito;
	private String numeroTransaccion;
	private String numeroTransaccionOriginal;
	//	si es informativo de calipso seteo "1", sino "0"
	private String colorLetraRegistro;
	private boolean soloLecturaPantalla;
	
	private String idFactura;
	private String idMedioPago;
	private String idTratamientoDiferencia;
	private Boolean esTrasladarInteresesObligatorio = false;
	
	//se utiliza en DescobroTransacciones, para la primer grilla cuando no se genero todavia el id_descobro
	private String sistemaAcuerdo;	
	private String estadoCargoProximaFactura;
	private String sistemaAcuerdoFactDestinoContracargos;
	private String acuerdoFactDestinoContracargos;
	private String estadoAcuerdoDestinoContracargos;
	private String apocopeGrupo;
	private String numeroGrupo;
	private Boolean fechaCobroMenorIgualFechaVto;
	private SiNoEnum mostrarAsteriscos = SiNoEnum.NO;
	
	public String getNumeroTransaccionFormateado() {
		return numeroTransaccionFormateado;
	}
	public void setNumeroTransaccionFormateado(String numeroTransaccionFormateado) {
		this.numeroTransaccionFormateado = numeroTransaccionFormateado;
	}
	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}
	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}
	public String getSistemaDoc() {
		return sistemaDoc;
	}
	public void setSistemaDoc(String sistemaDoc) {
		this.sistemaDoc = sistemaDoc;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getSubtipoDocumento() {
		return subtipoDocumento;
	}
	public void setSubtipoDocumento(String subtipoDocumento) {
		this.subtipoDocumento = subtipoDocumento;
	}
	public String getSubtipoDocumentoDesc() {
		return subtipoDocumentoDesc;
	}
	public void setSubtipoDocumentoDesc(String subtipoDocumentoDesc) {
		this.subtipoDocumentoDesc = subtipoDocumentoDesc;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferenciaMic) {
		this.numeroReferencia = numeroReferenciaMic;
	}
	public String getFechaVenc() {
		return fechaVenc;
	}
	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFechaCobro() {
		return fechaCobro;
	}
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	public String getImporte() {
		return importe;
	}
	/**
	 *  setear con signo de moneda
	 * @param importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getTipoDeCambioFechaCobro() {
		return tipoDeCambioFechaCobro;
	}
	/**
	 * se setea con 7 decimales sin signo
	 * @param tipoDeCambioFechaCobro
	 */
	public void setTipoDeCambioFechaCobro(String tipoDeCambioFechaCobro) {
		this.tipoDeCambioFechaCobro = tipoDeCambioFechaCobro;
	}
	public String getTipoDeCambioFechaEmision() {
		return tipoDeCambioFechaEmision;
	}
	/**
	 * se setea con 7 decimales sin signo
	 * @param tipoDeCambioFechaEmision
	 */
	public void setTipoDeCambioFechaEmision(String tipoDeCambioFechaEmision) {
		this.tipoDeCambioFechaEmision = tipoDeCambioFechaEmision;
	}
	public String getImporteAplicadoFechaEmisionMonedaOrigen() {
		return importeAplicadoFechaEmisionMonedaOrigen;
	}
	/**
	 *  setear con signo de moneda
	 * @param importe
	 */
	public void setImporteAplicadoFechaEmisionMonedaOrigen(
			String importeAplicadoFechaEmisionMonedaOrigen) {
		this.importeAplicadoFechaEmisionMonedaOrigen = importeAplicadoFechaEmisionMonedaOrigen;
	}
	public String getSistemaMedioPago() {
		return sistemaMedioPago;
	}
	public void setSistemaMedioPago(String sistemaMedioPago) {
		this.sistemaMedioPago = sistemaMedioPago;
	}
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	public String getSubtipoMedioPago() {
		return subtipoMedioPago;
	}
	public void setSubtipoMedioPago(String subtipoMedioPago) {
		this.subtipoMedioPago = subtipoMedioPago;
	}
	public String getReferenciaMedioPago() {
		return referenciaMedioPago;
	}
	public void setReferenciaMedioPago(String referenciaMedioPago) {
		this.referenciaMedioPago = referenciaMedioPago;
	}
	public String getFechaMedioPago() {
		return fechaMedioPago;
	}
	public void setFechaMedioPago(String fechaMedioPago) {
		this.fechaMedioPago = fechaMedioPago;
	}
	public String getIntereses() {
		return intereses;
	}
	/**
	 *  setear con signo de moneda
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	public boolean getTrasladarIntereses() {
		return trasladarIntereses;
	}
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
	public void setTrasladarInteresesFormateado(
			String trasladarInteresesFormateado) {
		this.trasladarInteresesFormateado = trasladarInteresesFormateado;
	}
	public String getPorcABonificar() {
		return porcABonificar;
	}
	public void setPorcABonificar(String porcABonificar) {
		this.porcABonificar = porcABonificar;
	}
	public String getImporteABonificar() {
		return importeABonificar;
	}
	/**
	 *  setear sin signo de moneda
	 */
	public void setImporteABonificar(String importeABonificar) {
		this.importeABonificar = importeABonificar;
	}
	public String getAcuerdoDestinoCargos() {
		return acuerdoDestinoCargos;
	}
	public void setAcuerdoDestinoCargos(String acuerdoDestinoCargos) {
		this.acuerdoDestinoCargos = acuerdoDestinoCargos;
	}
	public String getAcuerdoDestinoCargosOriginal() {
		return acuerdoDestinoCargosOriginal;
	}
	public void setAcuerdoDestinoCargosOriginal(String acuerdoDestinoCargosOriginal) {
		this.acuerdoDestinoCargosOriginal = acuerdoDestinoCargosOriginal;
	}
	public String getEstadoAcuerdoDestinoCargos() {
		return estadoAcuerdoDestinoCargos;
	}
	public void setEstadoAcuerdoDestinoCargos(String estadoAcuerdoDestinoCargos) {
		this.estadoAcuerdoDestinoCargos = estadoAcuerdoDestinoCargos;
	}
	public String getMensajeTransaccion() {
		return mensajeTransaccion;
	}
	public void setMensajeTransaccion(String mensajeTransaccion) {
		this.mensajeTransaccion = mensajeTransaccion;
	}
	public String getMensajeDebito() {
		return mensajeDebito;
	}
	public void setMensajeDebito(String mensajeDebito) {
		this.mensajeDebito = mensajeDebito;
	}
	public String getMensajeCredito() {
		return mensajeCredito;
	}
	public void setMensajeCredito(String mensajeCredito) {
		this.mensajeCredito = mensajeCredito;
	}
	public String getSubtipoMedioPagoDesc() {
		return subtipoMedioPagoDesc;
	}
	public void setSubtipoMedioPagoDesc(String subtipoMedioPagoDesc) {
		this.subtipoMedioPagoDesc = subtipoMedioPagoDesc;
	}
	public String getImporteMedioPago() {
		return importeMedioPago;
	}
	/**
	 *  setear con signo de moneda
	 */
	public void setImporteMedioPago(String importeMedioPago) {
		this.importeMedioPago = importeMedioPago;
	}
	public String getColorLetraRegistro() {
		return colorLetraRegistro;
	}
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
	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}
	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	public String getNumeroTransaccionOriginal() {
		return numeroTransaccionOriginal;
	}
	public void setNumeroTransaccionOriginal(String numeroTransaccionOriginal) {
		this.numeroTransaccionOriginal = numeroTransaccionOriginal;
	}
	public boolean getSoloLecturaPantalla() {
		return soloLecturaPantalla;
	}
	public void setSoloLecturaPantalla(boolean soloLecturaPantalla) {
		this.soloLecturaPantalla = soloLecturaPantalla;
	}
	public String getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	public String getIdMedioPago() {
		return idMedioPago;
	}
	public void setIdMedioPago(String idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	public String getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}
	public void setIdTratamientoDiferencia(String idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}
	public String getTipoMensajeTransaccion() {
		return tipoMensajeTransaccion;
	}
	public void setTipoMensajeTransaccion(String tipoMensajeTransaccion) {
		this.tipoMensajeTransaccion = tipoMensajeTransaccion;
	}
	public String getTipoMensajeDebito() {
		return tipoMensajeDebito;
	}
	public void setTipoMensajeDebito(String tipoMensajeDebito) {
		this.tipoMensajeDebito = tipoMensajeDebito;
	}
	public String getTipoMensajeCredito() {
		return tipoMensajeCredito;
	}
	public void setTipoMensajeCredito(String tipoMensajeCredito) {
		this.tipoMensajeCredito = tipoMensajeCredito;
	}
	public String getClienteAcuerdoDestinoCargos() {
		return clienteAcuerdoDestinoCargos;
	}
	public void setClienteAcuerdoDestinoCargos(String clienteAcuerdoDestinoCargos) {
		this.clienteAcuerdoDestinoCargos = clienteAcuerdoDestinoCargos;
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
	public SiNoEnum isCobrarAl2doVenc() {
		return cobrarAl2doVenc;
	}
	/**
	 * @param cobrarAl2doVenc the cobrarAl2doVenc to set
	 */
	public void setCobrarAl2doVenc(SiNoEnum cobrarAl2doVenc) {
		this.cobrarAl2doVenc = cobrarAl2doVenc;
	}
	/**
	 * @return the noHabilitadoTrasladarIntereses
	 */
	public Boolean getNoHabilitadoTrasladarIntereses() {
		return noHabilitadoTrasladarIntereses;
	}
	/**
	 * @param noHabilitadoTrasladarIntereses the habilitadoTrasladarIntereses to set
	 */
	public void setNoHabilitadoTrasladarIntereses(Boolean  noHabilitadoTrasladarIntereses) {
		this.noHabilitadoTrasladarIntereses = noHabilitadoTrasladarIntereses;
	}
	/**
	 * @return the fechaCobroMenorIgualFechaVto
	 */
	public Boolean isFechaCobroMenorIgualFechaVto() {
		return fechaCobroMenorIgualFechaVto;
	}
	/**
	 * @param fechaCobroMenorIgualFechaVto the fechaCobroMenorIgualFechaVto to set
	 */
	public void setFechaCobroMenorIgualFechaVto(Boolean fechaCobroMenorIgualFechaVto) {
		this.fechaCobroMenorIgualFechaVto = fechaCobroMenorIgualFechaVto;
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
	 * @return the tipoMedioPagoNombre
	 * Name del tipoMedioPagoEnum es el valor que viene de la Vista en el 
	 * campo TipoMedioPago
	 */
	public String getTipoMedioPagoNombre() {
		return tipoMedioPagoNombre;
	}
	/**
	 * @param tipoMedioPagoNombre the tipoMedioPagoNombre to set
	 */
	public void setTipoMedioPagoNombre(String tipoMedioPagoNombre) {
		this.tipoMedioPagoNombre = tipoMedioPagoNombre;
	}
	/**
	 * @return the esTrasladarInteresesObligatorio
	 */
	public Boolean getEsTrasladarInteresesObligatorio() {
		return esTrasladarInteresesObligatorio;
	}
	/**
	 * @param esTrasladarInteresesObligatorio the esTrasladarInteresesObligatorio to set
	 */
	public void setEsTrasladarInteresesObligatorio(
			Boolean esTrasladarInteresesObligatorio) {
		this.esTrasladarInteresesObligatorio = esTrasladarInteresesObligatorio;
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
	 * @return the mostrarAsteriscos
	 */
	public SiNoEnum getMostrarAsteriscos() {
		return mostrarAsteriscos;
	}
	/**
	 * @param mostrarAsteriscos the mostrarAsteriscos to set
	 */
	public void setMostrarAsteriscos(SiNoEnum mostrarAsteriscos) {
		this.mostrarAsteriscos = mostrarAsteriscos;
	}
	/**
	 * @return the generaInteresesParamUso
	 */
	public boolean isGeneraInteresesParamUso() {
		return generaInteresesParamUso;
	}
	/**
	 * @param generaInteresesParamUso the generaInteresesParamUso to set
	 */
	public void setGeneraInteresesParamUso(boolean generaInteresesParamUso) {
		this.generaInteresesParamUso = generaInteresesParamUso;
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
	 * @return the numeroTransaccionFiciticio
	 */
	public String getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}
	/**
	 * @param numeroTransaccionFiciticio the numeroTransaccionFiciticio to set
	 */
	public void setNumeroTransaccionFicticio(String numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}
}