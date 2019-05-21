package ar.com.telecom.shiva.base.jms.datos.entrada;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPago;

@SuppressWarnings("serial")
public class MicApropiacionDesapropiacionConfirmacionEntrada 
	extends JMS {
	
	//Para guardar los mensajes
	private MensajeServicioEnum tipoMensaje;
	private Long idTransaccion;
	
	//Propiedades del Mensaje
	private TipoInvocacionEnum tipoInvocacion;
	private SiNoEnum modoEjecucion;
	private Long idOperacion;
	private Integer idSecuencia;
	private String usuarioCobrador;
	
	private MicDetalleFactura detalleFactura = new MicDetalleFactura();
	private List<MicDetalleMedioPago> listaMediosPago = new ArrayList<MicDetalleMedioPago>();
	
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

	public MicDetalleFactura getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(MicDetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public List<MicDetalleMedioPago> getListaMediosPago() {
		return listaMediosPago;
	}

	public void setListaMediosPago(List<MicDetalleMedioPago> listaMediosPago) {
		this.listaMediosPago = listaMediosPago;
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
}
