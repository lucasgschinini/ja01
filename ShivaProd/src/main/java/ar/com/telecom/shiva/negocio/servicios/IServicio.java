package ar.com.telecom.shiva.negocio.servicios;

import java.io.Serializable;
import java.util.Collection;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IServicio {

	/**
	 * Retorna el DTO 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	DTO buscar(Integer id) throws NegocioExcepcion;
	
	/**
	 * Retorna un listado de DTO
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion;
	
	/**
	 * Crea un registro DTO
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	Long crear(DTO dto)throws NegocioExcepcion;

	/**
	 * Modifica un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void modificar(DTO dto) throws NegocioExcepcion;
	
	/**
	 * Anula un registro DTO
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	void anular(DTO dto) throws NegocioExcepcion;
	
	/**
	 * Retorna una cadena de datos modificados 
	 * Ahi se implementa la logica que permite 
	 * comparar el DTO nuevo (JSP) con el DTO viejo (BD)
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	String getDatosModificados(DTO dto) throws NegocioExcepcion;
	
	/**
	 * /**
	 * Logica que valida la concurrencia  
	 * @param id
	 * @param timeStamp - fechaUltimaModificacion (pantalla)
	 * @throws NegocioExcepcion
	 */
	void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion;
}
