package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ScardTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroDao cobroDao;
	@Autowired IDescobroDao descobroDao;

	@Autowired IScardServicio scardServicio;
	
	/**
	 * Genero los registros pendientes en la tabla de documentos.
	 * Esto es lo que se ejecuta cuando el cobro o la reversion pasan al estado cobrado/revertido respectivamente.
	 * 
	 * @throws WorkflowExcepcion
	 */
//	@Test
	public void inicializarDocumentosOperacionCobro() {

		try {
			// Cobro de Juan
			// ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755781));
			
			// Cobro de Pablo
			//ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755828));
			// Cobro de Juan L con muchas cosas
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755890));
			// Cobro de Nico con codigos de operacion externa
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755914));
			
			// cobro generado de ejempo 8755941
			
			// Cobro para generar evidencia 8755946
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755946));
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755958));
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8756145));
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8756149));
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8355438));
			
			// Cobro de Paola en DESA1
//			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8355411));
			
			// Genero los registros pendientes en la tabla de documentos.
			// Esto es lo que se ejecuta cuando el cobro o la reversion pasan al estado cobrado/revertido respectivamente.
			scardServicio.inicializarDocumentoOperacionCobro(cobro.getOperacion());
			scardServicio.generarDocumentos("13/09/2018");
			
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error al inicializar documentos para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		} catch (NegocioExcepcion e) {
			System.out.println("Error al inicializar documentos para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		} catch (PersistenciaExcepcion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inicializarDocumentosOperacionDesCobro() {

		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdOperacion(new Long("8355564"));
			
			// Genero los registros pendientes en la tabla de documentos.
			// Esto es lo que se ejecuta cuando el cobro o la reversion pasan al estado cobrado/revertido respectivamente.

			scardServicio.inicializarDocumentoOperacionDescobro(descobro.getOperacion());
			scardServicio.generarDocumentos("26/09/2018");
			
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error al inicializar documentos para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		} catch (NegocioExcepcion e) {
			System.out.println("Error al inicializar documentos para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		} catch (PersistenciaExcepcion e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Genero los documentos a SCARD en base a los registros pendientes
	 * Esto es lo que se ejecuta cuando se dispara el batch de Scard para una fecha dada
     *
	 */
//	@Test
	public void generarDocumentos() {
		
		try {
			// Genero los documentos a SCARD en base a los registros pendientes
			// Esto es lo que se ejecuta cuando se dispara el batch de Scard para una fecha dada
			scardServicio.generarDocumentos("13/09/2018");
			
		} catch (SimulacionCobroExcepcion e) {
			System.out.println("Error al generar registros para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		} catch (NegocioExcepcion e) {
			System.out.println("Error al generar registros para SCARD: " + e.getMensajeAuxiliar());
			e.printStackTrace();
		}
	}
}

