package ar.com.telecom.shiva.base.jms.datos.entrada;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;

@SuppressWarnings("serial")
public class MicDescobroEntrada 
	extends JMS {
	
	//Para guardar los mensajes
	private MensajeServicioEnum tipoMensaje;
	private Long idTransaccion;
	
	//Propiedades del Mensaje
	private TipoInvocacionEnum tipoInvocacion;
	private SiNoEnum modoEjecucion;
	private Long idOperacion;
	private Long idOperacionDescobroMensajeria;
	private Integer idSecuencia;
	private String usuarioCobrador;
	private SiNoEnum facturarContracargoFactura;
	private String acuerdoFacturacionContracargoFactura;
	private SiNoEnum facturarContracargoCargo;
	private String acuerdoFacturacionContracargoCargo;
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	
	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public SiNoEnum getModoEjecucion() {
		return modoEjecucion;
	}

	public void setModoEjecucion(SiNoEnum modoEjecucion) {
		this.modoEjecucion = modoEjecucion;
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

	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}

	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}

	public MensajeServicioEnum getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(MensajeServicioEnum tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public SiNoEnum getFacturarContracargoFactura() {
		return facturarContracargoFactura;
	}

	public void setFacturarContracargoFactura(SiNoEnum facturarContracargoFactura) {
		this.facturarContracargoFactura = facturarContracargoFactura;
	}

	public String getAcuerdoFacturacionContracargoFactura() {
		return acuerdoFacturacionContracargoFactura;
	}

	public void setAcuerdoFacturacionContracargoFactura(
			String acuerdoFacturacionContracargoFactura) {
		this.acuerdoFacturacionContracargoFactura = acuerdoFacturacionContracargoFactura;
	}

	public SiNoEnum getFacturarContracargoCargo() {
		return facturarContracargoCargo;
	}

	public void setFacturarContracargoCargo(SiNoEnum facturarContracargoCargo) {
		this.facturarContracargoCargo = facturarContracargoCargo;
	}

	public String getAcuerdoFacturacionContracargoCargo() {
		return acuerdoFacturacionContracargoCargo;
	}

	public void setAcuerdoFacturacionContracargoCargo(
			String acuerdoFacturacionContracargoCargo) {
		this.acuerdoFacturacionContracargoCargo = acuerdoFacturacionContracargoCargo;
	}

	public Long getIdOperacionDescobroMensajeria() {
		return idOperacionDescobroMensajeria;
	}

	public void setIdOperacionDescobroMensajeria(Long idOperacionDescobroMensajeria) {
		this.idOperacionDescobroMensajeria = idOperacionDescobroMensajeria;
	}
}
