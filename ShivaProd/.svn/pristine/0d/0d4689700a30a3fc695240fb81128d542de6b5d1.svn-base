package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;

/**
 * Servicio para el manejo de los Archivos de Opraciones Masivas
 * @author u564030
 *
 */
public interface ILegajoChequeRechazadoReversionIceServicio {

	/**
	 * 
	 * @throws NegocioExcepcion
	 */
	public void generarArchivoReversionesIce(String fechaHasta) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param reversionIce
	 * @param listaShvMasRegistro
	 * @return
	 */
	public List<ShvLgjChequeRechazadoDetalleCobro> actualizarEstadoDetalleCobroARevertido(List<ShvLgjChequeRechazadoDetalleCobro> listaShvMasRegistro);
	
}


