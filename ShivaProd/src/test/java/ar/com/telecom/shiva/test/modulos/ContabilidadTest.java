package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ContabilidadTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroDao cobroDao;
	@Autowired ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;

	@Autowired IDescobroDao descobroDao;
	@Autowired IDescobroServicio descobroServicio;
	
	/**
	 * @throws WorkflowExcepcion
	 */
//	@Test
	public void generarContabilidadCobro() {
		
		try {
			System.out.println("----------------------Inicio Contabilidad Test-----------------------------");

			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(new Long(8755946));
			System.out.println("ID_OPERACION COBRO=" + cobro.getOperacion().getIdOperacion());
			
			cobroBatchSoporteImputacionServicio.informarAContabilidadScard(cobro);

			System.out.println("----------------------Fin Contabilidad Test--------------------------------");
			
		} catch (PersistenciaExcepcion e) {
			System.out.println(e.getMessage());
		} catch (NegocioExcepcion e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void generarContabilidadDesCobro() {
		
		try {
			System.out.println("----------------------Inicio Contabilidad Test-----------------------------");

			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdOperacion(new Long("8355564"));
			System.out.println("ID_OPERACION DESCOBRO=" + descobro.getOperacion().getIdOperacion());
			
			descobroServicio.informarAContabilidadYScard(descobro);

			System.out.println("----------------------Fin Contabilidad Test--------------------------------");
			
		} catch (PersistenciaExcepcion e) {
			System.out.println(e.getMessage());
		} catch (NegocioExcepcion e) {
			System.out.println(e.getMessage());
		}
	}

}
