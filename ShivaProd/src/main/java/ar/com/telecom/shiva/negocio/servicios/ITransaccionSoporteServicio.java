/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public interface ITransaccionSoporteServicio {

	/**
	 * Retorna el cliente legado "de la transaccion". Se define como cliente legado "de la transaccion" a
	 * 		Si existe una factura, el cliente legado de la misma (salvo "Conjunto de D�bitos" pues no tiene cliente asociado)
	 * 		Caso contrario
	 * 			Si existe tratamiento de diferencia con acuerdo (reintegro), el cliente del acuerdo
	 * 			Caso contrario, el cliente del medio de pago (siempre ser� uno en caso de reintegro)
	 * 			
	 * 		Caso contrario
	 * 			Si existe factura y es un "Conjunto de D�bitos" el cliente del medio de pago (siempre ser� uno
	 *          para estos casos por definici�n)
	 *
	 * @param transaccion
	 * @return
	 */
	Long obtenerClienteLegadoTransaccion(ShvCobTransaccion transaccion);

	/**
	 * Retorna el cliente legado "de la transaccion" de una cobranza de un debito. 
	 * En este caso se estar� retornando el cliente del debito existente en la transaccion 
	 * 
	 * @param transaccion
	 * @return
	 */
	Long obtenerClienteLegadoTransaccionDebito(ShvCobTransaccion transaccion);

	/**
	 * Retorna el cliente legado "de la transaccion" de un reintegro
	 * Si existe tratamiento de diferencia con acuerdo (reintegro), el cliente del acuerdo
	 * Caso contrario, el cliente del medio de pago (siempre ser� uno en caso de reintegro) 
	 * 
	 * @param transaccion
	 * @return
	 */
	Long obtenerClienteLegadoTransaccionReintegro(ShvCobTransaccion transaccion);
}
