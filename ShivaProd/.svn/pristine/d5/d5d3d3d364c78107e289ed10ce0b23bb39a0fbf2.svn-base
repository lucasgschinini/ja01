package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;

public class RegistroOperacionMasivaAplicarDeudaDto extends RegistroOperacionMasivaDto {

	private static final long serialVersionUID = 1L;
	
	/** 
	 * Archivo Aplicar Deuda - Indice de campos del registro 
	 * 1	Tipo Operación				Alfanumérico	1	"T: Saldo total del documento P: Saldo parcial del documento"
	 * 2	Cliente dueño del débito	Numérico	10	
	 * 3	Número referencia Factura	Numérico	15	
	 * 4	Destransferir terceros		Alfanumérico	1	"S N"
	 * 5	Deuda migrada				Alfanumérico	1	"S N"
	 * 6	Importe						Numérico	10	Expresado en centavos
	 * 7	Cliente dueño del crédito	Numérico	10	
	 * 8	Cuenta						Numérico	13	
	 * 9	Tipo Remanente				Alfanumérico	3	"RT1: no actualizable/transferible RT2: no actualizable/no transferible Vacío: en caso que el cobro se haga con una nota de crédito"
	 * 10	Número Referencia NC		Numérico	15	"Número de referencia de la nota de crédito Vacío: en caso que el cobro se haga con remanente"
	 * 11	Crédito migrado				Alfanumérico	1	"S N Vacío: en caso que el cobro se haga con remanente" 
	 * 12	Acción sobre intereses		Alfanumérico	2	"BV BM TV TM"
	 * 13	Porcentaje bonificación intereses	Numérico	3	"Expresado en entero (solo es válido para la opción de tratamiento de intereses BV) Vacío: en caso que se decida no llevar a cabo bonificación de intereses o bien la bonificación se expresa en un importe"
	 * 14	Importe bonificación intereses		Numérico	10	"Expresado en centavos (solo es válido para la opción de tratamiento de intereses BV) Vacío: en caso que se decida no llevar a cabo bonificación de intereses o bien la bonificación se expresa en un porcentaje"
	 * 15	Cliente dueño del acuerdo de facturación	Numérico	10	Vacío: en caso que se decida no trasferir intereses a próxima factura (esto es tanto transferencia total de intereses o bien parcial por bonificación parcial)
	 * 16	Acuerdo de Facturación Destino				Numérico	10	"Acuerdo de facturación destino Vacío: en caso que se decida no trasferir intereses a próxima factura (esto es tanto transferencia total de intereses o bien parcial por bonificación parcial)"
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
