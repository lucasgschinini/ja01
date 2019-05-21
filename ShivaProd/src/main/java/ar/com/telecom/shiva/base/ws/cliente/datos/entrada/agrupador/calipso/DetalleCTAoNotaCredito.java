package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso;

import java.math.BigDecimal;
import java.math.BigInteger;

import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;

public class DetalleCTAoNotaCredito {
	
	//Para la confirmacion / Desapropiacion / respuesta de la apropiacion
	protected BigInteger idCobranza;

	//Para la apropiacion 
	protected BigDecimal importe;
	protected BigDecimal montoAcumuladoSimulacion;
	protected IdDocumento idDocumento;
	protected DetalleCTAoNotaCreditoRespuesta detalleCtaNtaCre;
//	Sprint 5
	protected BigInteger idDocumentoCuentasCobranza;
	protected Resultado resultadoApropiacion;
	
	//Dolares
	protected BigDecimal tipoCambioFechaEmision;
	protected BigDecimal tipoCambioFechaCobro;
	protected BigDecimal importeAplicadoFechaEmisionPesos;
	protected BigDecimal importeAplicadoMonedaOrigen;
		
	public BigInteger getIdCobranza() {
		return idCobranza;
	}

	public void setIdCobranza(BigInteger idCobranza) {
		this.idCobranza = idCobranza;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public IdDocumento getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}

	public DetalleCTAoNotaCreditoRespuesta getDetalleCtaNtaCre() {
		return detalleCtaNtaCre;
	}

	public void setDetalleCtaNtaCre(DetalleCTAoNotaCreditoRespuesta detalleCtaNtaCre) {
		this.detalleCtaNtaCre = detalleCtaNtaCre;
	}

	public BigInteger getIdDocumentoCuentasCobranza() {
		return idDocumentoCuentasCobranza;
	}

	public void setIdDocumentoCuentasCobranza(BigInteger idDocumentoCuentasCobranza) {
		this.idDocumentoCuentasCobranza = idDocumentoCuentasCobranza;
	}

	public Resultado getResultadoApropiacion() {
		return resultadoApropiacion;
	}

	public void setResultadoApropiacion(Resultado resultadoApropiacion) {
		this.resultadoApropiacion = resultadoApropiacion;
	}

	public BigDecimal getMontoAcumuladoSimulacion() {
		return montoAcumuladoSimulacion;
	}

	public void setMontoAcumuladoSimulacion(BigDecimal montoAcumuladoSimulacion) {
		this.montoAcumuladoSimulacion = montoAcumuladoSimulacion;
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
	
}
