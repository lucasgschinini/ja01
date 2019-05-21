/**
 * 
 */
package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class ProcesarArchivoAvcTest extends SoporteContextoSpringTest {

	@Test
	public void procesarArchivoAvc() {
		try {
			IConciliacionServicio conciliacionServicio = (IConciliacionServicio) Configuracion.getBeanBatch("conciliacionServicio");
			conciliacionServicio.procesarArchivosRegistrosAVC();
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
