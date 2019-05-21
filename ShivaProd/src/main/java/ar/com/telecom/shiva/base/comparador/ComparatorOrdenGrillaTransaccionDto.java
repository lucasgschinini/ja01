/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenGrillaTransaccionDto implements Comparator<CobroTransaccionDto> {

	/**
	 * El orden de comparacion se arma de la siguiente manera:
	 * 		- Se compara el grupo primero y luego el numero de transaccion
	 */
	@Override
	public int compare(CobroTransaccionDto o1, CobroTransaccionDto o2) {
		
		StringBuffer transaccion1 = new StringBuffer();
		
		transaccion1.append(Utilidad.rellenarCerosIzquierda(o1.getNumeroGrupo(), 5)).append(Constantes.SEPARADOR_PIPE);
		transaccion1.append(o1.getNumeroTransaccionFicticioFormateado()).append(Constantes.SEPARADOR_PIPE);
		transaccion1.append(o1.getNumeroTransaccionFormateado()).append(Constantes.SEPARADOR_PIPE);
		transaccion1.append(o1.getNumeroTransaccionOriginal());
		
		StringBuffer transaccion2 = new StringBuffer();
		
		transaccion2.append(Utilidad.rellenarCerosIzquierda(o2.getNumeroGrupo(), 5)).append(Constantes.SEPARADOR_PIPE);
		transaccion2.append(o2.getNumeroTransaccionFicticioFormateado()).append(Constantes.SEPARADOR_PIPE);
		transaccion2.append(o2.getNumeroTransaccionFormateado()).append(Constantes.SEPARADOR_PIPE);
		transaccion2.append(o2.getNumeroTransaccionOriginal());
		
		int comparacion = transaccion1.toString().compareTo(transaccion2.toString());
		
		if (comparacion==0){
			if (!Validaciones.isNullEmptyOrDash(o1.getIdFactura()) || !Validaciones.isNullEmptyOrDash(o1.getIdTratamientoDiferencia())){
				return -1;
			} else {
				return 1;
			}
		}
		
		return comparacion;
		
	}
	

}
