package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoSalidaCobranzasWs;

@SuppressWarnings("serial")
public class EntradaCalipsoConfirmacionWS extends EntradaWS {
	
	protected MensajeServicioEnum tipoApropiacion;
	
	protected BigInteger idOperacion;
	protected String usuarioCobrador;
	protected List<DetalleFactura> listaFacturasAConfirmar = new ArrayList<DetalleFactura>();
	protected List<DetalleCargoSalidaCobranzasWs> listaCargosAConfirmar = new ArrayList<DetalleCargoSalidaCobranzasWs>();
	protected List<DetalleCTAoNotaCredito> listaCtaONotaCreditoAConfirmar = new ArrayList<DetalleCTAoNotaCredito>();
	
	public BigInteger getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(BigInteger idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}
	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}
	public List<DetalleFactura> getListaFacturasAConfirmar() {
		return listaFacturasAConfirmar;
	}
	public void setListaFacturasAConfirmar(
			List<DetalleFactura> listaFacturasAConfirmar) {
		this.listaFacturasAConfirmar = listaFacturasAConfirmar;
	}
	public List<DetalleCTAoNotaCredito> getListaCtaONotaCreditoAConfirmar() {
		return listaCtaONotaCreditoAConfirmar;
	}
	public void setListaCtaONotaCreditoAConfirmar(
			List<DetalleCTAoNotaCredito> listaCtaONotaCreditoAConfirmar) {
		this.listaCtaONotaCreditoAConfirmar = listaCtaONotaCreditoAConfirmar;
	}
	public MensajeServicioEnum getTipoApropiacion() {
		return tipoApropiacion;
	}
	public void setTipoApropiacion(MensajeServicioEnum tipoApropiacion) {
		this.tipoApropiacion = tipoApropiacion;
	}
	public List<DetalleCargoSalidaCobranzasWs> getListaCargosAConfirmar() {
		return listaCargosAConfirmar;
	}
	public void setListaCargosAConfirmar(List<DetalleCargoSalidaCobranzasWs> listaCargosAConfirmar) {
		this.listaCargosAConfirmar = listaCargosAConfirmar;
	}
	
	
	
}
