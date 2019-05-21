/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenSimulacionShvCobMedioPago implements Comparator<ShvCobMedioPago> {

	/**
	 * El orden de aplicación de los medios de pago será el siguiente:
	 * 		- Si dentro de la operación existe algún medio de pago de compensación, el mismo debe quedar primero 
	 *        a ser aplicado independientemente de la fecha que tenga. Si existen varios se deberán ordenar por fecha ascendente.
	 * 		- El resto de los medios de pago se ordenan por fecha valor ascendente.
	 * 		- En caso que haya más de un valor con la misma fecha se ordena por prioridad de aplicación.
	 * 		- En caso que haya más de un valor con la misma fecha y misma prioridad de aplicación se ordena por sistema 
	 *        y referencia de medio de pago (*) ascendente.
	 *
	 */
	@Override
	public int compare(ShvCobMedioPago o1, ShvCobMedioPago o2) {
		int retornoComparacion = 0;

		if (TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())) 
		{
			
			// Si ambos medios de pago son compensaciones iguales, comparo por fecha
			if (o1.getTipoMedioPago().getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())) {
				retornoComparacion = o1.getFechaValor().compareTo(o2.getFechaValor());

			} else {

				// Si ambas son compensaciones 
				if ((TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
						|| TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
						|| TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
						|| TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
						|| TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
						|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago()))
						&& (TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
								|| TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
								|| TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
								|| TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
								|| TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago())
								|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(o2.getTipoMedioPago().getIdTipoMedioPago()))) 
				{
					retornoComparacion = o1.getFechaValor().compareTo(o2.getFechaValor());

				} else {
					// Si al menos uno de los medios de pago es compensacion, la misma tiene mas peso
					if (TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(o1.getTipoMedioPago().getIdTipoMedioPago())) 
					{
						retornoComparacion = -1;

					} else {
						retornoComparacion = 1;
					}
				}
			}

		} else {
			
			// No tengo compensaciones
			
			if (o1.getFechaValor().compareTo(o2.getFechaValor()) == 0) {
				if (o1.getTipoMedioPago().getOrdenImputacion().equals(o2.getTipoMedioPago().getOrdenImputacion())) {
					if (o1.getSistemaOrigen().equals(o2.getSistemaOrigen())) {
						retornoComparacion = o1.getReferencia().compareTo(o2.getReferencia());
					} else {
						retornoComparacion = o1.getSistemaOrigen().compareTo(o2.getSistemaOrigen());
					}
				} else {
					retornoComparacion = o1.getTipoMedioPago().getOrdenImputacion().compareTo(o2.getTipoMedioPago().getOrdenImputacion());
				}
			} else {
				retornoComparacion = o1.getFechaValor().compareTo(o2.getFechaValor());
			}
		}
		
		return retornoComparacion;	
	}
}
