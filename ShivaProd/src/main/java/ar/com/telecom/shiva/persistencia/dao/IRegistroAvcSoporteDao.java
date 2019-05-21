/**
 * 
 */
package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.batch.springbatch.model.AvcRegistroAvc;

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
public interface IRegistroAvcSoporteDao {

	/**
	 * 
	 * @param registroAvc
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	AvcRegistroAvc crear(AvcRegistroAvc registroAvc) throws PersistenciaExcepcion;
}
