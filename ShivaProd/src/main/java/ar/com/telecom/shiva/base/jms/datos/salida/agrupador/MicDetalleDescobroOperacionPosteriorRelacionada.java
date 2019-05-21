package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;


public class MicDetalleDescobroOperacionPosteriorRelacionada {

	private SistemaEnum sistema;
	private Long idOperacion;
	private Integer idSecuencia;
	private TipoComprobanteEnum tipoDocumento;
	private Long numeroReferenciaMic;
	private BigInteger codigoCliente;
	private BigDecimal importeCobrado;
	private Date fechaCobranza;
	
	public String toString() {
		String str = "[sistema:" + sistema.name() + "]," 
				+ "[idOperacion:" + String.valueOf(idOperacion) + "],"
				+ "[idSecuencia:" + String.valueOf(idSecuencia) + "],"
				+ "[tipoDocumento:" + tipoDocumento.name() + "]," 
				+ "[numeroReferenciaMic:" + numeroReferenciaMic + "],"
				+ "[codigoCliente:" + String.valueOf(codigoCliente) + "],"
				+ "[importeCobrado:" + String.valueOf(importeCobrado) + "],"
				+ "[fechaCobranza:" + String.valueOf(fechaCobranza) + "]";
		return str;
	}

	public SistemaEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdSecuencia() {
		return idSecuencia;
	}

	public void setIdSecuencia(Integer idSecuencia) {
		this.idSecuencia = idSecuencia;
	}

	public TipoComprobanteEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoComprobanteEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}

	public BigInteger getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(BigInteger codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	
}