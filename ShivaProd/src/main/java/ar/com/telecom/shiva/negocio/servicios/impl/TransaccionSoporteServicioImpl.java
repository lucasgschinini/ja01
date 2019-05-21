package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionSoporteServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;


/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class TransaccionSoporteServicioImpl extends Servicio implements ITransaccionSoporteServicio {

	@Override
	public Long obtenerClienteLegadoTransaccion(ShvCobTransaccion transaccion) {
	
		Long idClienteLegado = obtenerClienteLegadoTransaccionDebito(transaccion);
		
		if (Validaciones.isObjectNull(idClienteLegado)) {
			idClienteLegado = obtenerClienteLegadoTransaccionReintegro(transaccion);
			
//			if (Validaciones.isObjectNull(idClienteLegado)) {
//				idClienteLegado = obtenerClienteLegadoTransaccionConjuntoDeDebitos(transaccion);
//			}
		}
		
		return idClienteLegado;
	}
	
	@Override
	public Long obtenerClienteLegadoTransaccionDebito(ShvCobTransaccion transaccion) {
		
		Long idClienteLegado = null;
		
		if (!Validaciones.isObjectNull(transaccion.getFacturaTransaccion())) {
			
			if (TipoComprobanteEnum.CDD.name().equals(transaccion.getFacturaTransaccion().getTipoComprobante().getIdTipoComprobante())) {
				
				ShvCobMedioPago medioPago = (ShvCobMedioPago) transaccion.getMediosPago().iterator().next();
				
				if (medioPago instanceof ShvCobMedioPagoUsuario) {
					
//					// Ordena la lista de clientes, para siempre devolver el mismo.
//					Iterator<ShvCobMedioPagoCliente> iterator = ((ShvCobMedioPagoUsuario) medioPago).getListaMedioPagoClientes().iterator();
//					List<String> clientesOrdenados = new ArrayList<String>();
//			
//					while (iterator.hasNext()) {
//						clientesOrdenados.add(iterator.next().getIdClienteLegado());
//					}
//					Collections.sort(clientesOrdenados);
//					
//					idClienteLegado = Long.valueOf(clientesOrdenados.get(0));
					idClienteLegado = Long.valueOf(((ShvCobMedioPagoUsuario)medioPago).obtenerPrimerIdClienteLegado());
					
				} else {
					idClienteLegado = transaccion.getMediosPago().iterator().next().getIdClienteLegado();
				}
				
			} else {
				idClienteLegado = transaccion.getFacturaTransaccion().getIdClienteLegado();	
			}
		}
		
		return idClienteLegado;
	}
	
	@Override
	public Long obtenerClienteLegadoTransaccionReintegro(ShvCobTransaccion transaccion) {
		
		Long idClienteLegado = null;
		
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
			
			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia().getIdClienteLegadoAcuerdoTrasladoCargo())) {
				idClienteLegado = transaccion.getTratamientoDiferencia().getIdClienteLegadoAcuerdoTrasladoCargo();
			} else {
				idClienteLegado = transaccion.getMediosPago().iterator().next().getIdClienteLegado();
			}
		} else {
			idClienteLegado = obtenerClienteLegadoTransaccionConjuntoDeDebitos(transaccion);
		}
		
		return idClienteLegado;
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	private Long obtenerClienteLegadoTransaccionConjuntoDeDebitos(ShvCobTransaccion transaccion) {
		
		Long idClienteLegado = null;
		
		if (!Validaciones.isObjectNull(transaccion.getFacturaTransaccion())) {
			if (TipoComprobanteEnum.CDD.name().equals(transaccion.getFacturaTransaccion().getTipoComprobante().getIdTipoComprobante())) {
				
				ShvCobMedioPago medioPago = (ShvCobMedioPago) transaccion.getMediosPago().iterator().next();
				
				if (medioPago instanceof ShvCobMedioPagoUsuario) {
					
					// Ordena la lista de clientes, para siempre devolver el mismo.
					Iterator<ShvCobMedioPagoCliente> iterator = ((ShvCobMedioPagoUsuario) medioPago).getListaMedioPagoClientes().iterator();
					List<String> clientesOrdenados = new ArrayList<String>();
			
					while (iterator.hasNext()) {
						clientesOrdenados.add(iterator.next().getIdClienteLegado());
					}
					Collections.sort(clientesOrdenados);
					
					idClienteLegado = Long.valueOf(clientesOrdenados.get(0));
					
				} else {
					idClienteLegado = transaccion.getMediosPago().iterator().next().getIdClienteLegado();
				}
			}
		}
		
		return idClienteLegado;
	}
}
