package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.AccionesSobreDiferenciaDeCambioEnum;
import ar.com.telecom.shiva.base.enumeradores.AlgoritmoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;

public class DetalleFactura {
	//Para Desapropiacion/confirmacion/Apropiacion(respuesta)
	protected BigInteger idCobranza;
	
	//Para Apropiacion
	protected IdDocumento idDocumento;
	protected BigDecimal montoACancelarEnPesos;
	
	protected TipoPagoEnum tipoOperacion;
	protected String tipoMora;
	protected AlgoritmoMoraEnum algoritmoMora;
	protected BigDecimal importeBonificacionIntereses;
	protected String acuerdoFacturacion;
//	Sprint 5
	protected BigDecimal montoAcumuladoSimulacion;
	protected BigInteger idDocumentoCuentasCobranza;
	
	//Dolares
	protected AccionesSobreDiferenciaDeCambioEnum accionSobreDiferenciaDeCambio;
	
	//Para apropiacion(Respuesta)
	protected BigDecimal montoCalculadoMora;
	protected BigDecimal montoCuenta;
	protected BigDecimal tipoCambioFechaEmision;
	protected BigDecimal tipoCambioFechaCobro;
	protected BigDecimal importeAplicadoFechaEmisionPesos;
	protected BigDecimal importeAplicadoMonedaOrigen;
	protected List<Resultado> listaResultadoApropiacion;
	
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	public BigInteger getIdCobranza() {
		return idCobranza;
	}

	public void setIdCobranza(BigInteger idCobranza) {
		this.idCobranza = idCobranza;
	}

	public IdDocumento getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}

	public TipoPagoEnum getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoPagoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getTipoMora() {
		return tipoMora;
	}

	public void setTipoMora(String tipoMora) {
		this.tipoMora = tipoMora;
	}

	public AlgoritmoMoraEnum getAlgoritmoMora() {
		return algoritmoMora;
	}

	public void setAlgoritmoMora(AlgoritmoMoraEnum algoritmoMora) {
		this.algoritmoMora = algoritmoMora;
	}

	public String getAcuerdoFacturacion() {
		return acuerdoFacturacion;
	}

	public void setAcuerdoFacturacion(String acuerdoFacturacion) {
		this.acuerdoFacturacion = acuerdoFacturacion;
	}

	public BigDecimal getMontoACancelarEnPesos() {
		return montoACancelarEnPesos;
	}

	public void setMontoACancelarEnPesos(BigDecimal montoACancelarEnPesos) {
		this.montoACancelarEnPesos = montoACancelarEnPesos;
	}

	public BigDecimal getMontoCalculadoMora() {
		return montoCalculadoMora;
	}

	public void setMontoCalculadoMora(BigDecimal montoCalculadoMora) {
		this.montoCalculadoMora = montoCalculadoMora;
	}

	public BigDecimal getMontoCuenta() {
		return montoCuenta;
	}

	public void setMontoCuenta(BigDecimal montoCuenta) {
		this.montoCuenta = montoCuenta;
	}
	public BigInteger getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}

	public void setIdDocumentoCuentasCobranza(BigInteger idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}

	public BigDecimal getTipoCambioFechaEmision() {
		return tipoCambioFechaEmision;
	}

	public void setTipoCambioFechaEmision(BigDecimal tipoCambioFechaEmision) {
		this.tipoCambioFechaEmision = tipoCambioFechaEmision;
	}

	public BigDecimal getTipoCambioFechaCobro() {
		return tipoCambioFechaCobro;
	}

	public void setTipoCambioFechaCobro(BigDecimal tipoCambioFechaCobro) {
		this.tipoCambioFechaCobro = tipoCambioFechaCobro;
	}

	public BigDecimal getImporteAplicadoFechaEmisionPesos() {
		return importeAplicadoFechaEmisionPesos;
	}

	public void setImporteAplicadoFechaEmisionPesos(
			BigDecimal importeAplicadoFechaEmisionPesos) {
		this.importeAplicadoFechaEmisionPesos = importeAplicadoFechaEmisionPesos;
	}

	public BigDecimal getImporteAplicadoMonedaOrigen() {
		return importeAplicadoMonedaOrigen;
	}

	public void setImporteAplicadoMonedaOrigen(
			BigDecimal importeAplicadoMonedaOrigen) {
		this.importeAplicadoMonedaOrigen = importeAplicadoMonedaOrigen;
	}

	public List<Resultado> getListaResultadoApropiacion() {
		return listaResultadoApropiacion;
	}

	public void setListaResultadoApropiacion(
			List<Resultado> listaResultadoApropiacion) {
		this.listaResultadoApropiacion = listaResultadoApropiacion;
	}

	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}

	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}

	public BigDecimal getMontoAcumuladoSimulacion() {
		return montoAcumuladoSimulacion;
	}

	public void setMontoAcumuladoSimulacion(BigDecimal montoAcumuladoSimulacion) {
		this.montoAcumuladoSimulacion = montoAcumuladoSimulacion;
	}

	public AccionesSobreDiferenciaDeCambioEnum getAccionSobreDiferenciaDeCambio() {
		return accionSobreDiferenciaDeCambio;
	}

	public void setAccionSobreDiferenciaDeCambio(
			AccionesSobreDiferenciaDeCambioEnum accionSobreDiferenciaDeCambio) {
		this.accionSobreDiferenciaDeCambio = accionSobreDiferenciaDeCambio;
	}
	
}
