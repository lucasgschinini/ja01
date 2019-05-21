package ar.com.telecom.shiva.base.comparador;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdOtrosMedioPagoWrapper;

/**
 * 
 * @author u578936 Max Uehara
 */
public class ComparatorShvCobEdOtrosMedioPagoWrapper implements Comparator<ShvCobEdOtrosMedioPagoWrapper>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * Compara Otros medios de pago segun. importe -> fecha -> tipo -> clientes (en string) -> numero de acta -> checkMedioEnProceso
	 */
	public int compare(ShvCobEdOtrosMedioPagoWrapper o1, ShvCobEdOtrosMedioPagoWrapper o2) {
		int comparacion = 0;
		int comparacionImporte = o1.getMedio().getImporte().compareTo(o2.getMedio().getImporte());
		if (comparacionImporte == 0) {
			if (o1.getMedio().getFecha().equals(o2.getMedio().getFecha())) {
				int comparacionTipo = o1.getTipo().compareTo(o2.getTipo());
				if (comparacionTipo == 0) {
					int comparacionIdLegados = o1.getClientes().compareTo(o2.getClientes());
					if (comparacionIdLegados == 0) {
						String strO1 = o1.getMedio().getNroActa();
						String strO2 = o2.getMedio().getNroActa();
						if (StringUtils.equals(strO1, strO2)) {
							SiNoEnum o1SiNo = o1.getMedio().getCheckMedioPagoEnProceso();
							SiNoEnum o2SiNo = o2.getMedio().getCheckMedioPagoEnProceso();
							if (o1SiNo == null && o2SiNo == null) {
								comparacion = 0;
							} else if (o1SiNo == null) {
								comparacion = -1;
							} else if (o2SiNo == null) {
								comparacion = 1;
							} else {
								comparacion = o1SiNo.getDescripcion().compareTo(o2SiNo.getDescripcion());
							}
						} else {
							if (Validaciones.isNullOrEmpty(strO1)) {
								comparacion = -1;
							} else if (Validaciones.isNullOrEmpty(strO2)) {
								comparacion = 1;
							} else {
								int comparacionNroActa = strO1.compareTo(strO2);
								// no va haber comparacionNroActa == 0. este caso ya se estudio em StringEquals
								if (comparacionNroActa < 0) {
									comparacion = -1;
								} else {
									comparacion = 1;
								}
							}
						}
					} else if (comparacionIdLegados < 0) {
						comparacion = -1;
					} else {
						comparacion = 1;
					}
				} else if (comparacionTipo < 0) {
					comparacion = -1;
				} else {
					comparacion = 1;
				}
			} else if (o1.getMedio().getFecha().before(o2.getMedio().getFecha())) {
				comparacion = -1;
			} else {
				comparacion = 1;
			}
		} else if (comparacionImporte < 0) {
			comparacion = -1;
		} else {
			comparacion = 1;
		}
		return comparacion;
	}
}