package ar.com.telecom.shiva.persistencia.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionShvCobTransaccionInvertido;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_COBRO")
public class ShvCobCobro extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO")	
	private Long idCobro;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_OPERACION", referencedColumnName="ID_OPERACION") 	
	private ShvCobOperacion operacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_OPERACION")
	private MonedaEnum  monedaOperacion ;

	@Transient
	private ShvCobDescobro descobro;
	
	@Column(name="FECHA_ULT_PROCESAMIENTO") 	
	private Date fechaUltProcesamiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CONTIENE_APLICACION_MANUAL")
	private SiNoEnum contieneAplicacionManual;
	
	public Long getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}
	
	public ShvCobOperacion getOperacion() {
		return operacion;
	}
	public void setOperacion(ShvCobOperacion operacion) {
		this.operacion = operacion;
	}
	
	public ShvCobDescobro getDescobro() {
		return descobro;
	}
	public void setDescobro(ShvCobDescobro descobro) {
		this.descobro = descobro;
	}
	
	/**
	 * Retorna una lista ordenada de las transacciones, por id de transaccion.
	 * Omite las que estan en estado DIFERENCIA_DE_CAMBIO_SIM.
	 * @param cobro
	 * @return
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadas() {
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			if(!EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())
					&& !EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento())){
				listaAux.add(transaccion);
			}
		}
		return listaAux;
	}
	
	/**
	 * Retorna la transaccion que coincida con el idTransaccion de la lista de transacciones propias del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobTransaccion getTransaccionPorIdTransaccion(Integer idTransaccion){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			if(transaccion.getIdTransaccion().equals(idTransaccion)){
				return transaccion;
			}
		}
		return null;
	}
	
	/**
	 * Retorna la transaccion que coincida con el numeroTransaccion de la lista de transacciones propias del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobTransaccion getTransaccionPorNumeroTransaccion(Integer numeroTransaccion){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			if(transaccion.getNumeroTransaccion().equals(numeroTransaccion)){
				return transaccion;
			}
		}
		return null;
	}
	
	/**
	 * Retorna el medio de pago que coincida con el idMedioPago de los medios de pago propios del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobMedioPago getMedioPagoPorIdMedioPago(Integer idMedioPago){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			for(ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()){
				if(medioPago.getIdMedioPago().equals(idMedioPago)){
					return medioPago;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadasInversaSinDiferenciaCambio() {
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			if(!Utilidad.esDiferenciaCambio(transaccion)){
				listaAux.add(transaccion);
			}				
		}		
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	public List<ShvCobTransaccion> getTransaccionesDC(Integer numeroTransaccion) {
		
		List<ShvCobTransaccion> lista = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){

			if(!Validaciones.isObjectNull(transaccion.getNumeroTransaccionPadre())
					&& numeroTransaccion.equals(transaccion.getNumeroTransaccionPadre())
					// Levantamos registros en estado diferencia de cambio de simulacion e imputacion, así podemos probar SCARD y otros
					// temas con el cobro simulado. Lo correcto sería que solo levante diferencias de cambio de imputacion (EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO)
					// El otro estado no debiera ser necesario nunca, para el flujo normal del negocio.
					&& (EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento()) 
							|| EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento()))){
				
				lista.add(transaccion);
			}
		}
		
		return lista;
	}
	
	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}
	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
	
	public Date getFechaUltProcesamiento() {
		return fechaUltProcesamiento;
	}
	public void setFechaUltProcesamiento(Date fechaUltProcesamiento) {
		this.fechaUltProcesamiento = fechaUltProcesamiento;
	}
	/**
	 * @return the contieneAplicacionManual
	 */
	public SiNoEnum getContieneAplicacionManual() {
		return contieneAplicacionManual;
	}
	/**
	 * @param contieneAplicacionManual the contieneAplicacionManual to set
	 */
	public void setContieneAplicacionManual(SiNoEnum contieneAplicacionManual) {
		this.contieneAplicacionManual = contieneAplicacionManual;
	}
}
