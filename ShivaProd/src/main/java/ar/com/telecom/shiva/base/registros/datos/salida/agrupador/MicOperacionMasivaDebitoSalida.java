package ar.com.telecom.shiva.base.registros.datos.salida.agrupador;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDebitoSalida extends REG {
	
	//Valores posibles: 'T' para parcial cancelatorio o total, 
	//					'P' para parcial. Solo para  tipo de invocación 'Aplicar Deuda'. 
	//                   Para "Desistimiento" el valor siempre será "T"
	private TipoPagoEnum tipoOperacion;
	
	private Long clienteDuenoDebito;
	
	//"F", de momento unico valor posible
	private TipoDocumentoEnum tipoDocumento;

	private Long numeroReferenciaFactura;
	
	private SiNoEnum destransferirTerceros;
	
	private SiNoEnum deudaMigrada;
	
	private Long clienteDuenoAcuerdo;

	private Long acuerdoFacturacionDestino;

	private TratamientoInteresesEnum accionSobreIntereses;
	
	private BigDecimal importeBonificacionIntereses;

	private Long porcentajeBonificacionIntereses;

	public TipoPagoEnum getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoPagoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Long getClienteDuenoDebito() {
		return clienteDuenoDebito;
	}

	public void setClienteDuenoDebito(Long clienteDuenoDebito) {
		this.clienteDuenoDebito = clienteDuenoDebito;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumeroReferenciaFactura() {
		return numeroReferenciaFactura;
	}

	public void setNumeroReferenciaFactura(Long numeroReferenciaFactura) {
		this.numeroReferenciaFactura = numeroReferenciaFactura;
	}

	public SiNoEnum getDestransferirTerceros() {
		return destransferirTerceros;
	}

	public void setDestransferirTerceros(SiNoEnum destransferirTerceros) {
		this.destransferirTerceros = destransferirTerceros;
	}

	public SiNoEnum getDeudaMigrada() {
		return deudaMigrada;
	}

	public void setDeudaMigrada(SiNoEnum deudaMigrada) {
		this.deudaMigrada = deudaMigrada;
	}

	public Long getClienteDuenoAcuerdo() {
		return clienteDuenoAcuerdo;
	}

	public void setClienteDuenoAcuerdo(Long clienteDuenoAcuerdo) {
		this.clienteDuenoAcuerdo = clienteDuenoAcuerdo;
	}

	public Long getAcuerdoFacturacionDestino() {
		return acuerdoFacturacionDestino;
	}

	public void setAcuerdoFacturacionDestino(Long acuerdoFacturacionDestino) {
		this.acuerdoFacturacionDestino = acuerdoFacturacionDestino;
	}

	public TratamientoInteresesEnum getAccionSobreIntereses() {
		return accionSobreIntereses;
	}

	public void setAccionSobreIntereses(
			TratamientoInteresesEnum accionSobreIntereses) {
		this.accionSobreIntereses = accionSobreIntereses;
	}

	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}

	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}

	/**
	 * @return the porcentajeBonificacionIntereses
	 */
	public Long getPorcentajeBonificacionIntereses() {
		return porcentajeBonificacionIntereses;
	}

	/**
	 * @param porcentajeBonificacionIntereses the porcentajeBonificacionIntereses to set
	 */
	public void setPorcentajeBonificacionIntereses(
			Long porcentajeBonificacionIntereses) {
		this.porcentajeBonificacionIntereses = porcentajeBonificacionIntereses;
	}
}
