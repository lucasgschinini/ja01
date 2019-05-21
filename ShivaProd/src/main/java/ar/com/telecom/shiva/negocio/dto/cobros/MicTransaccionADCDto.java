package ar.com.telecom.shiva.negocio.dto.cobros;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MicTransaccionADCDto extends MicTransaccionDto {
	
	private MicDetalleFacturaDto detalleFactura = new MicDetalleFacturaDto();
	private List<MicDetalleMedioPagoDto> listaMediosPago = new ArrayList<MicDetalleMedioPagoDto>();
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/

	/**
	 * @return the detalleFactura
	 */
	public MicDetalleFacturaDto getDetalleFactura() {
		return detalleFactura;
	}

	/**
	 * @param detalleFactura the detalleFactura to set
	 */
	public void setDetalleFactura(MicDetalleFacturaDto detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	/**
	 * @return the listaMediosPago
	 */
	public List<MicDetalleMedioPagoDto> getListaMediosPago() {
		return listaMediosPago;
	}

	/**
	 * @param listaMediosPago the listaMediosPago to set
	 */
	public void setListaMediosPago(List<MicDetalleMedioPagoDto> listaMediosPago) {
		this.listaMediosPago = listaMediosPago;
	}
}
