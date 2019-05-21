package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistro;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroDesistimiento;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroGanancias;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroReintegro;
import ar.com.telecom.shiva.base.utils.Utilidad;

@SuppressWarnings("serial")
public class MicOperacionMasivaRegistroEntrada extends REG {
	private String errorSerializar = Utilidad.EMPTY_STRING;
	// Parametros generales del registro de salida
	private MicOperacionMasivaParametrosEntrada parametrosGenerales = new MicOperacionMasivaParametrosEntrada();
	// Parametros generales del registro de salida
	private MicOperacionMasivaDatosCobranzasGralesEntrada datosCobranzaGenerales = new MicOperacionMasivaDatosCobranzasGralesEntrada();
	// Datos de cobranza apropiacion de deuda 
	private MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada datosCobranzaApropiacionDeuda = new MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada();
	//Datos de cobranza generacion de cargo
	private MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada datosCobranzasGeneracionCargoEntrada = new MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada();
	
	private MicOperacionMasivaDatosClienteImputadoEntrada datosClienteImputado = new MicOperacionMasivaDatosClienteImputadoEntrada();
	// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	private MicOperacionMasivaDatosDebitoImputadoGralesEntrada datosDebitoImputadoDatosGenerales  = new MicOperacionMasivaDatosDebitoImputadoGralesEntrada();
	
	private MicOperacionMasivaDatosDebitoImputadoTagetikEntrada datosDebitoImputadoTegetik = new MicOperacionMasivaDatosDebitoImputadoTagetikEntrada();
	
	private MicOperacionMasivaDatosDebitoImputadoDacotaEntrada datosDebitoImputadoDacota = new MicOperacionMasivaDatosDebitoImputadoDacotaEntrada();
	
	private MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada datosDebitoImputadoSaldoTerceros = new MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada();
	
	private MicOperacionMasivaDatosCreditoAplicadoClienteEntrada datosCreditoAplicadoCliente = new MicOperacionMasivaDatosCreditoAplicadoClienteEntrada();
	
	private MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada datosCreditoAplicadoMedioPago = new MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada();
	// vDatos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	private MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada datosCreditoAplicadoNotaCredito = new MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada();
	private MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada datosCreditoAplicadoRemanente = new MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada();
	private MicOperacionMasivaDatosCreditoAplicadoGralesEntrada datosCreditoAplicadoDatosGenerales = new MicOperacionMasivaDatosCreditoAplicadoGralesEntrada();
	private MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada datosCreditoAplicadoTagetik = new MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada();
	private MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada datosCreditoAplicadoDacota = new MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada();
	private MicOperacionMasivaDatosAcuerdoGralesEntrada datosAcuerdoGenerales = new MicOperacionMasivaDatosAcuerdoGralesEntrada();
	private MicOperacionMasivaDatosAcuerdoClienteEntrada datosAcuerdosCliente = new MicOperacionMasivaDatosAcuerdoClienteEntrada();
	private MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaDebito = new MicOperacionMasivaDatosRespuestaGeneralesEntrada(); 
	private MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaCredito = new MicOperacionMasivaDatosRespuestaGeneralesEntrada(); 
	private MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaReintegro = new MicOperacionMasivaDatosRespuestaGeneralesEntrada();
	
	private String nombreArchivoOperacionMasivaSalidaMic;
	private Date fechaArchivoOperacionMasivaSalidaMic;
	
	
	public MicMasRegistro getRegistro() throws NegocioExcepcion{
		MicMasRegistro registroSalida = null;
		switch (this.getParametrosGenerales().getTipoInvocacion()) {
		case DEUDA:
			registroSalida = new MicMasRegistroAplicarDeuda(this);
			break;
		case DSIST:
			registroSalida = new MicMasRegistroDesistimiento(this);
			break;
		case GNCIA:
			registroSalida = new MicMasRegistroGanancias(this);
			break;
		case REINT:
			registroSalida = new MicMasRegistroReintegro(this);
			break;
		default:
			throw new NegocioExcepcion("No existe el tipo de invocacion");
		}
		return registroSalida;
	}
	/**
	 * @return the parametrosGenerales
	 */
	public MicOperacionMasivaParametrosEntrada getParametrosGenerales() {
		return parametrosGenerales;
	}
	/**
	 * @param parametrosGenerales the parametrosGenerales to set
	 */
	public void setParametrosGenerales(
			MicOperacionMasivaParametrosEntrada parametrosGenerales) {
		this.parametrosGenerales = parametrosGenerales;
	}
	/**
	 * @return the datosCobranzaGenerales
	 */
	public MicOperacionMasivaDatosCobranzasGralesEntrada getDatosCobranzaGenerales() {
		return datosCobranzaGenerales;
	}
	/**
	 * @param datosCobranzaGenerales the datosCobranzaGenerales to set
	 */
	public void setDatosCobranzaGenerales(
			MicOperacionMasivaDatosCobranzasGralesEntrada datosCobranzaGenerales) {
		this.datosCobranzaGenerales = datosCobranzaGenerales;
	}
	/**
	 * @return the datosCobranzaApropiacionDeuda
	 */
	public MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada getDatosCobranzaApropiacionDeuda() {
		return datosCobranzaApropiacionDeuda;
	}
	/**
	 * @param datosCobranzaApropiacionDeuda the datosCobranzaApropiacionDeuda to set
	 */
	public void setDatosCobranzaApropiacionDeuda(
			MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada datosCobranzaApropiacionDeuda) {
		this.datosCobranzaApropiacionDeuda = datosCobranzaApropiacionDeuda;
	}
	/**
	 * @return the datosCobranzasGeneracionCargoEntrada
	 */
	public MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada getDatosCobranzasGeneracionCargoEntrada() {
		return datosCobranzasGeneracionCargoEntrada;
	}
	/**
	 * @param datosCobranzasGeneracionCargoEntrada the datosCobranzasGeneracionCargoEntrada to set
	 */
	public void setDatosCobranzasGeneracionCargoEntrada(
			MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada datosCobranzasGeneracionCargoEntrada) {
		this.datosCobranzasGeneracionCargoEntrada = datosCobranzasGeneracionCargoEntrada;
	}
	/**
	 * @return the datosClienteImputado
	 */
	public MicOperacionMasivaDatosClienteImputadoEntrada getDatosClienteImputado() {
		return datosClienteImputado;
	}
	/**
	 * @param datosClienteImputado the datosClienteImputado to set
	 */
	public void setDatosClienteImputado(
			MicOperacionMasivaDatosClienteImputadoEntrada datosClienteImputado) {
		this.datosClienteImputado = datosClienteImputado;
	}
	/**
	 * @return the datosDebitoImputadoDatosGenerales
	 */
	public MicOperacionMasivaDatosDebitoImputadoGralesEntrada getDatosDebitoImputadoDatosGenerales() {
		return datosDebitoImputadoDatosGenerales;
	}
	/**
	 * @param datosDebitoImputadoDatosGenerales the datosDebitoImputadoDatosGenerales to set
	 */
	public void setDatosDebitoImputadoDatosGenerales(
			MicOperacionMasivaDatosDebitoImputadoGralesEntrada datosDebitoImputadoDatosGenerales) {
		this.datosDebitoImputadoDatosGenerales = datosDebitoImputadoDatosGenerales;
	}
	/**
	 * @return the datosDebitoImputadoTegetik
	 */
	public MicOperacionMasivaDatosDebitoImputadoTagetikEntrada getDatosDebitoImputadoTegetik() {
		return datosDebitoImputadoTegetik;
	}
	/**
	 * @param datosDebitoImputadoTegetik the datosDebitoImputadoTegetik to set
	 */
	public void setDatosDebitoImputadoTegetik(
			MicOperacionMasivaDatosDebitoImputadoTagetikEntrada datosDebitoImputadoTegetik) {
		this.datosDebitoImputadoTegetik = datosDebitoImputadoTegetik;
	}
	/**
	 * @return the datosDebitoImputadoDacota
	 */
	public MicOperacionMasivaDatosDebitoImputadoDacotaEntrada getDatosDebitoImputadoDacota() {
		return datosDebitoImputadoDacota;
	}
	/**
	 * @param datosDebitoImputadoDacota the datosDebitoImputadoDacota to set
	 */
	public void setDatosDebitoImputadoDacota(
			MicOperacionMasivaDatosDebitoImputadoDacotaEntrada datosDebitoImputadoDacota) {
		this.datosDebitoImputadoDacota = datosDebitoImputadoDacota;
	}
	/**
	 * @return the datosDebitoImputadoSaldoTerceros
	 */
	public MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada getDatosDebitoImputadoSaldoTerceros() {
		return datosDebitoImputadoSaldoTerceros;
	}
	/**
	 * @param datosDebitoImputadoSaldoTerceros the datosDebitoImputadoSaldoTerceros to set
	 */
	public void setDatosDebitoImputadoSaldoTerceros(
			MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada datosDebitoImputadoSaldoTerceros) {
		this.datosDebitoImputadoSaldoTerceros = datosDebitoImputadoSaldoTerceros;
	}
	/**
	 * @return the datosCreditoAplicadoCliente
	 */
	public MicOperacionMasivaDatosCreditoAplicadoClienteEntrada getDatosCreditoAplicadoCliente() {
		return datosCreditoAplicadoCliente;
	}
	/**
	 * @param datosCreditoAplicadoCliente the datosCreditoAplicadoCliente to set
	 */
	public void setDatosCreditoAplicadoCliente(
			MicOperacionMasivaDatosCreditoAplicadoClienteEntrada datosCreditoAplicadoCliente) {
		this.datosCreditoAplicadoCliente = datosCreditoAplicadoCliente;
	}
	/**
	 * @return the datosCreditoAplicadoMedioPago
	 */
	public MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada getDatosCreditoAplicadoMedioPago() {
		return datosCreditoAplicadoMedioPago;
	}
	/**
	 * @param datosCreditoAplicadoMedioPago the datosCreditoAplicadoMedioPago to set
	 */
	public void setDatosCreditoAplicadoMedioPago(
			MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada datosCreditoAplicadoMedioPago) {
		this.datosCreditoAplicadoMedioPago = datosCreditoAplicadoMedioPago;
	}
	/**
	 * @return the datosCreditoAplicadoNotaCredito
	 */
	public MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada getDatosCreditoAplicadoNotaCredito() {
		return datosCreditoAplicadoNotaCredito;
	}
	/**
	 * @param datosCreditoAplicadoNotaCredito the datosCreditoAplicadoNotaCredito to set
	 */
	public void setDatosCreditoAplicadoNotaCredito(
			MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada datosCreditoAplicadoNotaCredito) {
		this.datosCreditoAplicadoNotaCredito = datosCreditoAplicadoNotaCredito;
	}
	/**
	 * @return the datosCreditoAplicadoRemanente
	 */
	public MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada getDatosCreditoAplicadoRemanente() {
		return datosCreditoAplicadoRemanente;
	}
	/**
	 * @param datosCreditoAplicadoRemanente the datosCreditoAplicadoRemanente to set
	 */
	public void setDatosCreditoAplicadoRemanente(
			MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada datosCreditoAplicadoRemanente) {
		this.datosCreditoAplicadoRemanente = datosCreditoAplicadoRemanente;
	}
	/**
	 * @return the datosCreditoAplicadoDatosGenerales
	 */
	public MicOperacionMasivaDatosCreditoAplicadoGralesEntrada getDatosCreditoAplicadoDatosGenerales() {
		return datosCreditoAplicadoDatosGenerales;
	}
	/**
	 * @param datosCreditoAplicadoDatosGenerales the datosCreditoAplicadoDatosGenerales to set
	 */
	public void setDatosCreditoAplicadoDatosGenerales(
			MicOperacionMasivaDatosCreditoAplicadoGralesEntrada datosCreditoAplicadoDatosGenerales) {
		this.datosCreditoAplicadoDatosGenerales = datosCreditoAplicadoDatosGenerales;
	}
	/**
	 * @return the datosCreditoAplicadoTagetik
	 */
	public MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada getDatosCreditoAplicadoTagetik() {
		return datosCreditoAplicadoTagetik;
	}
	/**
	 * @param datosCreditoAplicadoTagetik the datosCreditoAplicadoTagetik to set
	 */
	public void setDatosCreditoAplicadoTagetik(
			MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada datosCreditoAplicadoTagetik) {
		this.datosCreditoAplicadoTagetik = datosCreditoAplicadoTagetik;
	}
	/**
	 * @return the datosCreditoAplicadoDacota
	 */
	public MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada getDatosCreditoAplicadoDacota() {
		return datosCreditoAplicadoDacota;
	}
	/**
	 * @param datosCreditoAplicadoDacota the datosCreditoAplicadoDacota to set
	 */
	public void setDatosCreditoAplicadoDacota(
			MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada datosCreditoAplicadoDacota) {
		this.datosCreditoAplicadoDacota = datosCreditoAplicadoDacota;
	}
	/**
	 * @return the datosAcuerdoGenerales
	 */
	public MicOperacionMasivaDatosAcuerdoGralesEntrada getDatosAcuerdoGenerales() {
		return datosAcuerdoGenerales;
	}
	/**
	 * @param datosAcuerdoGenerales the datosAcuerdoGenerales to set
	 */
	public void setDatosAcuerdoGenerales(
			MicOperacionMasivaDatosAcuerdoGralesEntrada datosAcuerdoGenerales) {
		this.datosAcuerdoGenerales = datosAcuerdoGenerales;
	}
	/**
	 * @return the datosAcuerdosCliente
	 */
	public MicOperacionMasivaDatosAcuerdoClienteEntrada getDatosAcuerdosCliente() {
		return datosAcuerdosCliente;
	}
	/**
	 * @param datosAcuerdosCliente the datosAcuerdosCliente to set
	 */
	public void setDatosAcuerdosCliente(
			MicOperacionMasivaDatosAcuerdoClienteEntrada datosAcuerdosCliente) {
		this.datosAcuerdosCliente = datosAcuerdosCliente;
	}
	/**
	 * @return the datosRespuestaDebito
	 */
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaDebito() {
		return datosRespuestaDebito;
	}
	/**
	 * @param datosRespuestaDebito the datosRespuestaDebito to set
	 */
	public void setDatosRespuestaDebito(
			MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaDebito) {
		this.datosRespuestaDebito = datosRespuestaDebito;
	}
	/**
	 * @return the datosRespuestaCredito
	 */
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaCredito() {
		return datosRespuestaCredito;
	}
	/**
	 * @param datosRespuestaCredito the datosRespuestaCredito to set
	 */
	public void setDatosRespuestaCredito(
			MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaCredito) {
		this.datosRespuestaCredito = datosRespuestaCredito;
	}
	/**
	 * @return the datosRespuestaReintegro
	 */
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaReintegro() {
		return datosRespuestaReintegro;
	}
	/**
	 * @param datosRespuestaReintegro the datosRespuestaReintegro to set
	 */
	public void setDatosRespuestaReintegro(
			MicOperacionMasivaDatosRespuestaGeneralesEntrada datosRespuestaReintegro) {
		this.datosRespuestaReintegro = datosRespuestaReintegro;
	}
	/**
	 * @return the errorSerializar
	 */
	public String getErrorSerializar() {
		return errorSerializar;
	}
	/**
	 * @param errorSerializar the errorSerializar to set
	 */
	public void setErrorSerializar(String errorSerializar) {
		this.errorSerializar = errorSerializar;
	}
	/**
	 * @return the nombreArchivoOperacionMasivaSalidaMic
	 */
	public String getNombreArchivoOperacionMasivaSalidaMic() {
		return nombreArchivoOperacionMasivaSalidaMic;
	}
	/**
	 * @param nombreArchivoOperacionMasivaSalidaMic the nombreArchivoOperacionMasivaSalidaMic to set
	 */
	public void setNombreArchivoOperacionMasivaSalidaMic(
			String nombreArchivoOperacionMasivaSalidaMic) {
		this.nombreArchivoOperacionMasivaSalidaMic = nombreArchivoOperacionMasivaSalidaMic;
	}
	/**
	 * @return the fechaArchivoOperacionMasivaSalidaMic
	 */
	public Date getFechaArchivoOperacionMasivaSalidaMic() {
		return fechaArchivoOperacionMasivaSalidaMic;
	}
	/**
	 * @param fechaArchivoOperacionMasivaSalidaMic the fechaArchivoOperacionMasivaSalidaMic to set
	 */
	public void setFechaArchivoOperacionMasivaSalidaMic(
			Date fechaArchivoOperacionMasivaSalidaMic) {
		this.fechaArchivoOperacionMasivaSalidaMic = fechaArchivoOperacionMasivaSalidaMic;
	}
}
