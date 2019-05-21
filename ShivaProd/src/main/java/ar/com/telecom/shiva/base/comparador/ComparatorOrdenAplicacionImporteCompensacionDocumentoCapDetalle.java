/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapDetalle;

/**
 * @author u564030
 * 
 */
public class ComparatorOrdenAplicacionImporteCompensacionDocumentoCapDetalle implements Comparator<ShvCobMedioPagoDocumentoCapDetalle> {
	
	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 *  	- El importe mas negativo hacia el importe mas positivo (menor a mayor, teniendo en cuenta que las NC son positivas, para que queden al final)
	 *  	- Si los importes son iguales, se compara por fecha de emision.
	 *  
	 *  	Al momento de evaluar el importe negativo, se toma el importe en pesos a emision o a fecha shiva, de acuerdo
	 *      al valor del check sin diferencia de cambio.
	 */
	@Override
	public int compare(ShvCobMedioPagoDocumentoCapDetalle o1, ShvCobMedioPagoDocumentoCapDetalle o2) {
	
		int retornoComparacion = 0;
		
		if (o1.getImporteRenglonMonedaOrigenAFechaEmision().compareTo(o2.getImporteRenglonMonedaOrigenAFechaEmision()) == 0) {
			retornoComparacion = o1.getFechaEmision().compareTo(o2.getFechaEmision());
		} else {
			retornoComparacion = o1.getImporteRenglonPesosEvaluandoCheckSinDiferenciaCambio().compareTo(o2.getImporteRenglonPesosEvaluandoCheckSinDiferenciaCambio());
		}

		return retornoComparacion;
	}
}
