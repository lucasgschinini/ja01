package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoSalidaCobranzasWs;

@SuppressWarnings("serial")
public class EntradaCalipsoDesapropiacionWS extends EntradaWS {
	protected MensajeServicioEnum tipoApropiacion;
	
	protected BigInteger idOperacion;
	protected String usuarioCobrador;
	protected List<DetalleFactura> listaFacturasADesapropiar = new ArrayList<DetalleFactura>();
	protected List<DetalleCargoSalidaCobranzasWs> listaCargosADesapropiar = new ArrayList<DetalleCargoSalidaCobranzasWs>();
	protected List<DetalleCTAoNotaCredito> listaCtaONotaCreditoADesapropiar = new ArrayList<DetalleCTAoNotaCredito>();
	
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
	
	public List<DetalleFactura> getListaFacturasADesapropiar() {
		return listaFacturasADesapropiar;
	}
	public void setListaFacturasADesapropiar(
			List<DetalleFactura> listaFacturasADesapropiar) {
		this.listaFacturasADesapropiar = listaFacturasADesapropiar;
	}
	
	public List<DetalleCTAoNotaCredito> getListaCtaONotaCreditoADesapropiar() {
		return listaCtaONotaCreditoADesapropiar;
	}
	public void setListaCtaONotaCreditoADesapropiar(
			List<DetalleCTAoNotaCredito> listaCtaONotaCreditoADesapropiar) {
		this.listaCtaONotaCreditoADesapropiar = listaCtaONotaCreditoADesapropiar;
	}
	public MensajeServicioEnum getTipoApropiacion() {
		return tipoApropiacion;
	}
	public void setTipoApropiacion(MensajeServicioEnum tipoApropiacion) {
		this.tipoApropiacion = tipoApropiacion;
	}
	public List<DetalleCargoSalidaCobranzasWs> getListaCargosADesapropiar() {
		return listaCargosADesapropiar;
	}
	public void setListaCargosADesapropiar(
			List<DetalleCargoSalidaCobranzasWs> listaCargosADesapropiar) {
		this.listaCargosADesapropiar = listaCargosADesapropiar;
	}
}
