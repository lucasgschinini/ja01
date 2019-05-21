package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;

public interface IContabilidadServicio {

	/**
	 * 
	 * @param contabilidadDto
	 * @throws NegocioExcepcion
	 */
	void contabilizar(ContabilidadDto contabilidadDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param fechaHasta
	 * @return
	 * @throws NegocioExcepcion
	 */
	String procesarArchivoSAP(String fechaHasta) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @throws NegocioExcepcion
	 */
	void anularContabilidad(Long idValor) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param detalle
	 * @throws NegocioExcepcion
	 */
	void evitarAcreditacion(ShvCntContabilidadDetalle detalle) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idValor
	 * @param solo30
	 * @throws NegocioExcepcion
	 */
	void desactivarDetalle(Long idValor, boolean solo30) throws NegocioExcepcion;
}
