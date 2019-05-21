/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobFactura;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

import com.google.common.collect.Iterables;

/**
 * @author u564030
 *
 */
public class ComponentesCobroBatch {

	private List<ShvCobFactura> 		listaFacturas 			= new ArrayList<ShvCobFactura>();
	private List<ShvCobMedioPago>		listaMediosDePago		= new ArrayList<ShvCobMedioPago>();
	private ShvCobTratamientoDiferencia tratamientoDiferencia 	= null;
	
	private Long idCobro				= null;
	private Long idOperacion			= null;
	private String usuarioCreacion		= null;
	
	private String idEmpresa			= null;
	private String idSegmento			= null;
	
	private ShvWfWorkflow workflow 		= null;
	private ShvCobOperacion operacion 	= null;
	
	private MonedaEnum monedaOperacion	= null;
	
	
	public ShvCobFactura obtenerSiguenteFactura() {
		return getListaFacturas().iterator().next();
	}

	public ShvCobMedioPago obtenerSiguienteMedioPago() {
		return getListaMediosDePago().iterator().next();
	}
	
	public ShvCobMedioPago obtenerSiguienteMedioPagoParaReintegro() {
		
		ShvCobMedioPago medioPagoRetorno = null;
		
		for(ShvCobMedioPago medioPago : getListaMediosDePago()){
			if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoReintegro())) {
				medioPagoRetorno = medioPago;
				break;
			}
		}
		
		return medioPagoRetorno;
	}

	/**
	 * Retorna el primer medio de pago del Cobro que permita Aplicación Manual.
	 * @return
	 */
	public ShvCobMedioPago obtenerSiguienteMedioPagoParaAplicacionManual() {
		
		ShvCobMedioPago medioPagoRetorno = null;
		
		for(ShvCobMedioPago medioPago :getListaMediosDePago()){
			if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoAplicacionManual())) {
				medioPagoRetorno = medioPago;
				break;
			}
		}
		
		return medioPagoRetorno;
	}
	
	/**
	 * 
	 * @return
	 */
	public ShvCobFactura obtenerUltimaFacturaPorFechaVencimiento() {
		
		Collections.sort(listaFacturas, new ComparatorOrdenSimulacionShvCobFactura());
		ShvCobFactura ultimaFactura = Iterables.getLast(listaFacturas);

		return ultimaFactura;
	}
	
	
	/**
	 * @return the listaFacturas
	 */
	public List<ShvCobFactura> getListaFacturas() {
		return listaFacturas;
	}
	/**
	 * @param listaFacturas the listaFacturas to set
	 */
	public void setListaFacturas(List<ShvCobFactura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	/**
	 * @return the listaMediosDePago
	 */
	public List<ShvCobMedioPago> getListaMediosDePago() {
		return listaMediosDePago;
	}
	/**
	 * @param listaMediosDePago the listaMediosDePago to set
	 */
	public void setListaMediosDePago(List<ShvCobMedioPago> listaMediosPago) {
		this.listaMediosDePago = listaMediosPago;
	}
	/**
	 * @return the tratamientoDiferencia
	 */
	public ShvCobTratamientoDiferencia getTratamientoDiferencia() {
		return tratamientoDiferencia;
	}
	/**
	 * @param tratamientoDiferencia the tratamientoDiferencia to set
	 */
	public void setTratamientoDiferencia(ShvCobTratamientoDiferencia reintegro) {
		this.tratamientoDiferencia = reintegro;
	}

	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @return the workflow
	 */
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow the workflow to set
	 */
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the operacion
	 */
	public ShvCobOperacion getOperacion() {
	
		if (Validaciones.isObjectNull(operacion)) {
			operacion = new ShvCobOperacion();
			operacion.setIdOperacion(idOperacion);
			operacion.setMonedaOperacion(getMonedaOperacion());
			operacion.setTipoOperacion(TipoOperacionEnum.COBRO);
		}
		
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(ShvCobOperacion operacion) {
		this.operacion = operacion;
	}

	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}

	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idSegmento
	 */
	public String getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}
	
}
