/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.batch.springbatch.model.AvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAvcSoporteDao;
import ar.com.telecom.shiva.persistencia.repository.RegistroAvcSoporteRepositorio;

/**
 * @author u564030 Pablo M. Ibarrola
 * 
 * Req 74070 Automatización de acuerdos bancarios.
 * 
 * Se genera un nuevo juego de clases para RegistroAvc, a fin de poder trabajar con modelos actualizados
 * generados utilizando herencia.
 * La idea es ir de a poco usando estos nuevos modelos, y cuando llegue el momento en que los modelos viejos
 * ya no se usan mas, mover estos nuevos al paquete correspondiente.
 * Por otro lado, se apunta a trabajar a nivel de servicio con los modelos directamente, y no recibir mas un DTO.
 * Para ello se crean las clases de soporte para poder trabajar de esta manera y evitar conflictos/confusiones con 
 * las clases y métodos actuales.
 *
 */
public class RegistroAvcSoporteDaoImpl extends Dao implements IRegistroAvcSoporteDao {

	@Autowired RegistroAvcSoporteRepositorio registroAvcSoporteRepositorio;
	
	/**
	 * @see ar.com.telecom.shiva.persistencia.dao.IRegistroAvcSoporteDao#crear(ar.com.telecom.shiva.batch.springbatch.model.AvcRegistroAvc)
	 */
	@Override
	public AvcRegistroAvc crear(AvcRegistroAvc registroAvc) throws PersistenciaExcepcion {

		try{
			registroAvc = registroAvcSoporteRepositorio.save(registroAvc);
			registroAvcSoporteRepositorio.flush();
			return registroAvc;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * @return the registroAvcSoporteRepositorio
	 */
	public RegistroAvcSoporteRepositorio getRegistroAvcSoporteRepositorio() {
		return registroAvcSoporteRepositorio;
	}

	/**
	 * @param registroAvcSoporteRepositorio the registroAvcSoporteRepositorio to set
	 */
	public void setRegistroAvcSoporteRepositorio(RegistroAvcSoporteRepositorio registroAvcSoporteRepositorio) {
		this.registroAvcSoporteRepositorio = registroAvcSoporteRepositorio;
	}
}
