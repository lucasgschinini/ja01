package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;

/**
 * 
 * 
1	Cliente dueño del crédito			Numérico	10	
2	Cuenta			Numérico	13	
3	Tipo Remanente			Alfanumérico	3	"RT1: no actualizable/transferible RT2: no actualizable/no transferible Vacío: en caso que el cobro se haga con una nota de crédito"
4	Número Referencia NC			Numérico	15	"Número de referencia de la nota de crédito Vacío: en caso que el cobro se haga con remanente"
5	Importe			Numérico	10	Expresado en centavos
6	Crédito migrado			Alfanumérico	1	"S N Vacío: en caso que el cobro se haga con remanente"

Se deberá validar la consistencia del registro en sí mismo:

•	Tipo Remanente y Número Referencia NC son excluyentes.
•	Si se informa Número de Referencia NC, se debe informar la marca de crédito migrado.

*
*/
public class RegistroOperacionMasivaGananciasDto extends RegistroOperacionMasivaDto {

	private static final long serialVersionUID = 1L;
	
	private Long clienteDuenoCredito;
	private Long cuentaOrigen;
	private TipoOrigenEnum tipoRemanente;
	private Long numeroReferenciaNC;
	private BigDecimal importe;
	private SiNoEnum creditoMigrado;

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

	public Long getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Long cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Long getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}

	public void setNumeroReferenciaNC(Long numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}

	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}

	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}

	public TipoOrigenEnum getTipoRemanente() {
		return tipoRemanente;
	}

	public void setTipoRemanente(TipoOrigenEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	
}
