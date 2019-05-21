/**
 * 
 */
package ar.com.telecom.shiva.base.comparador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionRuntimeException;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.semaforo.SemaforoGestionabilidad;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;

/**
 * @author Pablo M. Ibarrola
 * 
 */
public class ComparatorOrdenSimulacionShvCobFactura implements Comparator<ShvCobFactura> {

	/**
	 * El orden de aplicación de los débitos será por fecha ascendente. En
	 * primer lugar se toman los débitos de MIC, Calipso, Negocio.Net y Huawei
	 * en el siguiente orden:
	 * 
	 * Si el documento es una factura o nota de débito MIC por fecha de puesta
	 * al cobro o fecha de último pago parcial si es que tiene. Si el documento
	 * es un DUC MIC por fecha de emisión. Si el documento es de Calipso por
	 * fecha de vencimiento. Si el documento es una factura o nota de débito de
	 * Negocio.Net o Huawei por fecha de vencimiento.
	 * 
	 * A igual fecha se ordena por sistema y número legal del débito ascendente.
	 * 
	 * Luego de estos tipos de documentos se deberán tomar los siguientes:
	 * 
	 * Cuentas corrientes y conjunto de débitos Negocio.Net o Huawei Otros
	 * Débitos de SAP Telecom Otros Débitos de SAP Personal Otros Débitos de
	 * Fibercorp por fecha de vencimiento en el caso de contar con ella, y luego
	 * la cuenta corriente y el conjunto de débitos donde no se cuenta con dicha
	 * fecha Otros Débitos de Nextel por fecha de vencimiento en el caso de
	 * contar con ella, y luego la cuenta corriente y el conjunto de débitos
	 * donde no se cuenta con dicha fecha
	 * 
	 * A igual fecha se ordena por sociedad, sistema y número legal del débito
	 * ascendente.
	 * 
	 * Se agrega un SimulacionRuntimeException ya que al no poder agregarle un throws al metodo, para que el catch de SimulacionCobroException lo agarre.
	 */

	@Override
	public int compare(ShvCobFactura o1, ShvCobFactura o2) {
		int retornoComparacion = 0;
		try {
			
			if (!Validaciones.isObjectNull(o1.getUsoPorTipoComprobante())) {
				
				if ( !Validaciones.isObjectNull(o2.getUsoPorTipoComprobante())) {
					
					if (o1.getUsoPorTipoComprobante().getGeneraIntereses().getEnum() && o2.getUsoPorTipoComprobante().getGeneraIntereses().getEnum()) {
						
						if (o1.getUsoPorTipoComprobante().getOrdenImputacion().equals(o2.getUsoPorTipoComprobante().getOrdenImputacion())) {
							retornoComparacion = ordenarPorClave(o1, o2);		
						} else {
							retornoComparacion = o1.getUsoPorTipoComprobante().getOrdenImputacion().compareTo(o2.getUsoPorTipoComprobante().getOrdenImputacion());
						}
					} else if (o1.getUsoPorTipoComprobante().getGeneraIntereses().getEnum() || o2.getUsoPorTipoComprobante().getGeneraIntereses().getEnum()) {
						
						if (o1.getUsoPorTipoComprobante().getGeneraIntereses().getEnum()) {
							retornoComparacion = -1;
						} else {
							retornoComparacion = 1;
						}
					} else {
						if (o1.getUsoPorTipoComprobante().getOrdenImputacion().equals(o2.getUsoPorTipoComprobante().getOrdenImputacion())) {

							retornoComparacion = ordenarPorClave(o1, o2);
						} else {
							retornoComparacion = o1.getUsoPorTipoComprobante().getOrdenImputacion().compareTo(o2.getUsoPorTipoComprobante().getOrdenImputacion());
						}
					}
				} else {
					String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion.simulacion"), o2.getSociedad().name(), o2.getSistemaOrigen().name(), o2.getTipoComprobante().getDescripcion());
					Traza.error(getClass(), mensajeError);
					throw new SimulacionCobroExcepcion("", null, mensajeError);
				}
			} else {
				String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion.simulacion"), o1.getSociedad().name(), o1.getSistemaOrigen().name(), o1.getTipoComprobante().getDescripcion());
				Traza.error(getClass(), mensajeError);
				throw new SimulacionCobroExcepcion("", null, mensajeError);
			}
		} catch (SimulacionCobroExcepcion e) {

			throw new SimulacionRuntimeException(e.getMensajeAuxiliar(), e.getCause());

		} catch (IllegalArgumentException | NegocioExcepcion e1) {
			e1.printStackTrace();
		}

		return retornoComparacion;
	}

	protected Object invocar(ShvCobFactura factura, String key) throws NegocioExcepcion {

		Object valueObject = null;
		String nombreGetter = this.armarGetter(key);
		try {
			Method method;
			method = factura.getClass().getMethod(nombreGetter);
			valueObject = method.invoke(factura, (Object[]) null);

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		return valueObject;
	}

	protected String armarGetter(String key) {

		StringBuffer strNombreGetter = new StringBuffer(SemaforoGestionabilidad.GETTER);
		strNombreGetter.append(key);
		return strNombreGetter.toString();
	}

	private int ordenarPorClave(ShvCobFactura o1, ShvCobFactura o2) throws NegocioExcepcion, SimulacionCobroExcepcion {

		if (o1.getUsoPorTipoComprobante().getClave().equals(o2.getUsoPorTipoComprobante().getClave())) {

			int retornoComparacion = 0;
			String[] claveComprobante = o1.getUsoPorTipoComprobante().getClave().split("\\|");
		
			String comparacion = armarComparacion(o1,claveComprobante);
			String comparacion2 = armarComparacion(o2,claveComprobante);

			if (comparacion.compareTo(comparacion2) > 0) {
				retornoComparacion = 1;
			} else {
				retornoComparacion = -1;
			}
			return retornoComparacion;
		} else {
			String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion.simulacion.comparacion"), o1.getSociedad().name(), o1.getSistemaOrigen().name(), o1.getTipoComprobante().getDescripcion(), o2.getSociedad().name(), o2.getSistemaOrigen().name(), o2.getTipoComprobante().getDescripcion());
			Traza.error(getClass(), mensajeError);
			throw new SimulacionCobroExcepcion("", null, mensajeError);
		}

	}
	private String armarComparacion(ShvCobFactura factura, String[] claveComprobante) throws NegocioExcepcion, SimulacionCobroExcepcion {
		
		String comparacion = new String();
		
		for (String clave : claveComprobante) {
			if (clave.equalsIgnoreCase("Fecha")) {
				if (!Validaciones.isObjectNull(factura.getUsoPorTipoComprobante().getFechaClave())) {
					String[] claveFecha = factura.getUsoPorTipoComprobante().getFechaClave().split("\\|");
					Object tipoFecha = null;
					ArrayList<String> fechaClave = new ArrayList<String>();
					int contador = 0;

					for (String fecha : claveFecha) {
						fechaClave.add(fecha);
					}

					while (fechaClave.size() > contador && tipoFecha == null) {
						tipoFecha = invocar(factura, fechaClave.get(contador));
						contador++;
					}
					comparacion += tipoFecha;
				} else {
					String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion.simulacion.fechaClave"), factura.getSociedad().name(), factura.getSistemaOrigen().name(), factura.getTipoComprobante().getDescripcion(), factura.getSociedad().name(), factura.getSistemaOrigen().name(), factura.getTipoComprobante().getDescripcion());
					Traza.error(getClass(), mensajeError);
					throw new SimulacionCobroExcepcion("", null, mensajeError);
				}
			} else if (clave.equalsIgnoreCase("NroLegal")) {
				comparacion += Utilidad.obtenerNumeroLegalFormateado(factura);
			} else {
				comparacion += invocar(factura, clave).toString();
			}
		}
		return comparacion;
		
	}
}