package ar.com.telecom.shiva.negocio.dto.cobros;

import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;

@SuppressWarnings("serial")
public class MicTransaccionDescobroRespuestaDto extends DTO {
	
	private String idOperacionTransaccion;
	private Integer idTransaccion;
	
	private TipoInvocacionEnum tipoInvocacion;
	private MicResultadoDto resultadoLLamadaServicio;
	private MicDetalleDescobroFacturaDto detalleFactura;
	private List<MicDetalleDescobroMedioPagoDto> listaDetalleMedioPago;
	private MicDetalleDescobroCargoGeneradoDto detalleCargoGenerado;
	private List<MicDetalleDescobroOperacionPosteriorRelacionadaDto> listaDetalleOperacionesPosterioresRelacionadas;
	
  	public String toString() {
		String str = "[idOperacionTransaccion:"+String.valueOf(idOperacionTransaccion)+"],";
		str += "[tipoInvocacion:"+tipoInvocacion.getValor()+"],";
		str += "[resultadoLLamadaServicio:"+resultadoLLamadaServicio.toString()+"],";
		str += "[detalleFactura:"+detalleFactura.toString()+"],";
		str += "[listaDetalleMedioPago:"+listaDetalleMedioPago.toString()+"],";
		str += "[detalleCargoGenerado:"+detalleCargoGenerado.toString()+"],";
		str += "[listaDetalleOperacionesPosterioresRelacionadas:"+listaDetalleOperacionesPosterioresRelacionadas.toString()+"],";
		return str;
	}

	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}

	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public MicResultadoDto getResultadoLLamadaServicio() {
		return resultadoLLamadaServicio;
	}

	public void setResultadoLLamadaServicio(MicResultadoDto resultadoLLamadaServicio) {
		this.resultadoLLamadaServicio = resultadoLLamadaServicio;
	}

	public MicDetalleDescobroFacturaDto getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(MicDetalleDescobroFacturaDto detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public List<MicDetalleDescobroMedioPagoDto> getListaDetalleMedioPago() {
		return listaDetalleMedioPago;
	}

	public void setListaDetalleMedioPago(
			List<MicDetalleDescobroMedioPagoDto> listaDetalleMedioPago) {
		this.listaDetalleMedioPago = listaDetalleMedioPago;
	}

	public MicDetalleDescobroCargoGeneradoDto getDetalleCargoGenerado() {
		return detalleCargoGenerado;
	}

	public void setDetalleCargoGenerado(
			MicDetalleDescobroCargoGeneradoDto detalleCargoGenerado) {
		this.detalleCargoGenerado = detalleCargoGenerado;
	}

	public List<MicDetalleDescobroOperacionPosteriorRelacionadaDto> getListaDetalleOperacionesPosterioresRelacionadas() {
		return listaDetalleOperacionesPosterioresRelacionadas;
	}

	public void setListaDetalleOperacionesPosterioresRelacionadas(
			List<MicDetalleDescobroOperacionPosteriorRelacionadaDto> listaDetalleOperacionesPosterioresRelacionadas) {
		this.listaDetalleOperacionesPosterioresRelacionadas = listaDetalleOperacionesPosterioresRelacionadas;
	}
}
