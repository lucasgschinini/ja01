package ar.com.telecom.shiva.base.jms.datos.salida;

import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroCargoGenerado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroFactura;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroMedioPago;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroOperacionPosteriorRelacionada;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;

@SuppressWarnings("serial")
public class MicRespuestaDescobroSalida 
	extends JMS {
	
	private JmsCopiaTecnica retorno = new JmsCopiaTecnica();
	private String idOperacionTransaccionRetorno;
	private String idOperacionTransaccion;
	private Integer idTransaccion;
	
	private TipoInvocacionEnum tipoInvocacion;
	private MicResultado resultadoLlamadaServicio;
	private MicDetalleDescobroFactura detalleFactura;
	private List<MicDetalleDescobroMedioPago> listaDetalleMedioPago;
	private MicDetalleDescobroCargoGenerado detalleCargoGenerado;
	private List<MicDetalleDescobroOperacionPosteriorRelacionada> listaDetalleOperacionesPosterioresRelacionadas;
	
  	public String toString() {
		String str = retorno.toString()+ ",";
		str += "[idOperacionTransaccionRetorno:"+String.valueOf(idOperacionTransaccionRetorno)+"],";		
		str += "[idOperacionTransaccion:"+String.valueOf(idOperacionTransaccion)+"],";
		str += "[tipoInvocacion:"+tipoInvocacion.getValor()+"],";
		str += "[resultadoLlamadaServicio:"+resultadoLlamadaServicio.toString()+"],";
		str += "[detalleFactura:"+detalleFactura.toString()+"],";
		str += "[listaDetalleMedioPago:"+listaDetalleMedioPago.toString()+"],";
		str += "[detalleCargoGenerado:"+detalleCargoGenerado.toString()+"],";
		str += "[listaDetalleOperacionesPosterioresRelacionadas:"+listaDetalleOperacionesPosterioresRelacionadas.toString()+"],";
		return str;
	}

  	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}

	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public MicResultado getResultadoLlamadaServicio() {
		return resultadoLlamadaServicio;
	}

	public void setResultadoLlamadaServicio(MicResultado resultadoLlamadaServicio) {
		this.resultadoLlamadaServicio = resultadoLlamadaServicio;
	}

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public JmsCopiaTecnica getRetorno() {
		return retorno;
	}

	public void setRetorno(JmsCopiaTecnica retorno) {
		this.retorno = retorno;
	}

	public String getIdOperacionTransaccionRetorno() {
		return idOperacionTransaccionRetorno;
	}

	public void setIdOperacionTransaccionRetorno(
			String idOperacionTransaccionRetorno) {
		this.idOperacionTransaccionRetorno = idOperacionTransaccionRetorno;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public MicDetalleDescobroFactura getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(MicDetalleDescobroFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public List<MicDetalleDescobroMedioPago> getListaDetalleMedioPago() {
		return listaDetalleMedioPago;
	}

	public void setListaDetalleMedioPago(
			List<MicDetalleDescobroMedioPago> listaDetalleMedioPago) {
		this.listaDetalleMedioPago = listaDetalleMedioPago;
	}

	public MicDetalleDescobroCargoGenerado getDetalleCargoGenerado() {
		return detalleCargoGenerado;
	}

	public void setDetalleCargoGenerado(
			MicDetalleDescobroCargoGenerado detalleCargoGenerado) {
		this.detalleCargoGenerado = detalleCargoGenerado;
	}

	public List<MicDetalleDescobroOperacionPosteriorRelacionada> getListaDetalleOperacionesPosterioresRelacionadas() {
		return listaDetalleOperacionesPosterioresRelacionadas;
	}

	public void setListaDetalleOperacionesPosterioresRelacionadas(
			List<MicDetalleDescobroOperacionPosteriorRelacionada> listaDetalleOperacionesPosterioresRelacionadas) {
		this.listaDetalleOperacionesPosterioresRelacionadas = listaDetalleOperacionesPosterioresRelacionadas;
	}
}
