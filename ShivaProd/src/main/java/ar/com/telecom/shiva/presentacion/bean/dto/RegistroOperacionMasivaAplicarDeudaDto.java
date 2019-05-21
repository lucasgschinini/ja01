package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

public class RegistroOperacionMasivaAplicarDeudaDto extends RegistroOperacionMasivaDto {

	private static final long serialVersionUID = 1L;
	
	/** 
	 * Archivo Aplicar Deuda - Indice de campos del registro 
	 * 1	Tipo Operaci�n				Alfanum�rico	1	"T: Saldo total del documento P: Saldo parcial del documento"
	 * 2	Cliente due�o del d�bito	Num�rico	10	
	 * 3	N�mero referencia Factura	Num�rico	15	
	 * 4	Destransferir terceros		Alfanum�rico	1	"S N"
	 * 5	Deuda migrada				Alfanum�rico	1	"S N"
	 * 6	Importe						Num�rico	10	Expresado en centavos
	 * 7	Cliente due�o del cr�dito	Num�rico	10	
	 * 8	Cuenta						Num�rico	13	
	 * 9	Tipo Remanente				Alfanum�rico	3	"RT1: no actualizable/transferible RT2: no actualizable/no transferible Vac�o: en caso que el cobro se haga con una nota de cr�dito"
	 * 10	N�mero Referencia NC		Num�rico	15	"N�mero de referencia de la nota de cr�dito Vac�o: en caso que el cobro se haga con remanente"
	 * 11	Cr�dito migrado				Alfanum�rico	1	"S N Vac�o: en caso que el cobro se haga con remanente" 
	 * 12	Acci�n sobre intereses		Alfanum�rico	2	"BV BM TV TM"
	 * 13	Porcentaje bonificaci�n intereses	Num�rico	3	"Expresado en entero (solo es v�lido para la opci�n de tratamiento de intereses BV) Vac�o: en caso que se decida no llevar a cabo bonificaci�n de intereses o bien la bonificaci�n se expresa en un importe"
	 * 14	Importe bonificaci�n intereses		Num�rico	10	"Expresado en centavos (solo es v�lido para la opci�n de tratamiento de intereses BV) Vac�o: en caso que se decida no llevar a cabo bonificaci�n de intereses o bien la bonificaci�n se expresa en un porcentaje"
	 * 15	Cliente due�o del acuerdo de facturaci�n	Num�rico	10	Vac�o: en caso que se decida no trasferir intereses a pr�xima factura (esto es tanto transferencia total de intereses o bien parcial por bonificaci�n parcial)
	 * 16	Acuerdo de Facturaci�n Destino				Num�rico	10	"Acuerdo de facturaci�n destino Vac�o: en caso que se decida no trasferir intereses a pr�xima factura (esto es tanto transferencia total de intereses o bien parcial por bonificaci�n parcial)"
	 ****/
	
	private String tipoOperacion;
	private Long clienteDuenoDebito;
	private Long numeroReferenciaFactura;
	private SiNoEnum destransferirTerceros;
	private SiNoEnum deudaMigrada;
	private BigDecimal importe;
	private Long clienteDuenoCredito;
	private Long cuenta;
	private TipoOrigenEnum tipoRemanente;
	private String numeroReferenciaNC;
	private SiNoEnum creditoMigrado;
	private TratamientoInteresesEnum accionSobreIntereses;
	private Long porcentajeBonificacionIntereses;
	private BigDecimal importeBonificacionIntereses;
	private Long clienteDuenoAcuerdoFacturacion;
	private Long acuerdoFacturacionDestino;
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Long getClienteDuenoDebito() {
		return clienteDuenoDebito;
	}
	public void setClienteDuenoDebito(Long clienteDuenoDebito) {
		this.clienteDuenoDebito = clienteDuenoDebito;
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
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getClienteDuenoCredito() {
		return clienteDuenoCredito;
	}
	public void setClienteDuenoCredito(Long clienteDuenoCredito) {
		this.clienteDuenoCredito = clienteDuenoCredito;
	}
	public Long getCuenta() {
		return cuenta;
	}
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	public TipoOrigenEnum getTipoRemanente() {
		return tipoRemanente;
	}
	public void setTipoRemanente(TipoOrigenEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	public String getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}
	public void setNumeroReferenciaNC(String numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}
	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}
	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}
	public TratamientoInteresesEnum getAccionSobreIntereses() {
		return accionSobreIntereses;
	}
	public void setAccionSobreIntereses(
			TratamientoInteresesEnum accionSobreIntereses) {
		this.accionSobreIntereses = accionSobreIntereses;
	}
	public Long getPorcentajeBonificacionIntereses() {
		return porcentajeBonificacionIntereses;
	}
	public void setPorcentajeBonificacionIntereses(
			Long porcentajeBonificacionIntereses) {
		this.porcentajeBonificacionIntereses = porcentajeBonificacionIntereses;
	}
	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}
	public void setImporteBonificacionIntereses(BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}
	public Long getClienteDuenoAcuerdoFacturacion() {
		return clienteDuenoAcuerdoFacturacion;
	}
	public void setClienteDuenoAcuerdoFacturacion(
			Long clienteDuenoAcuerdoFacturacion) {
		this.clienteDuenoAcuerdoFacturacion = clienteDuenoAcuerdoFacturacion;
	}
	public Long getAcuerdoFacturacionDestino() {
		return acuerdoFacturacionDestino;
	}
	public void setAcuerdoFacturacionDestino(Long acuerdoFacturacionDestino) {
		this.acuerdoFacturacionDestino = acuerdoFacturacionDestino;
	}
	
}
