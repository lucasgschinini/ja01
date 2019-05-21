package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;


public class ResultadoSimulacionDescobroOperacionRelacionada extends ResultadoSimulacion {

	private SistemaEnum sistema;
	private Long idOperacion;
	private Integer nroTransaccion;
	private Long idClienteLegado;
	private BigDecimal importeCobrado;
	private Date fechaCobranza;
	private Long idOperacionPadre;
	private Integer nroTransaccionPadre;
	
	public SistemaEnum getSistema() {
		return sistema;
	}
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	public BigDecimal getImporteCobrado() {
		return importeCobrado;
	}
	public void setImporteCobrado(BigDecimal importeCobrado) {
		this.importeCobrado = importeCobrado;
	}
	public Date getFechaCobranza() {
		return fechaCobranza;
	}
	public void setFechaCobranza(Date fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Integer getNroTransaccion() {
		return nroTransaccion;
	}
	public void setNroTransaccion(Integer nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}
	public Long getIdOperacionPadre() {
		return idOperacionPadre;
	}
	public void setIdOperacionPadre(Long idOperacionPadre) {
		this.idOperacionPadre = idOperacionPadre;
	}
	public Integer getNroTransaccionPadre() {
		return nroTransaccionPadre;
	}
	public void setNroTransaccionPadre(Integer nroTransaccionPadre) {
		this.nroTransaccionPadre = nroTransaccionPadre;
	}
	
}