package ar.com.telecom.shiva.test.modulos;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.ImputacionDescobrosBatchRunner;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

/**
 * Se depreco para correr la imputacion desde la clase ImputacionDescobrosBatchRunner
 * @author u573005
 *
 */
@Deprecated
public class DescobroBatchImputacionTest extends SoporteContextoSpringTest {
	
	@Autowired IDescobroImputacionServicio descobroImputacionServicio;
	@Autowired IDescobroServicio descobroServicio;
	
	@Test
	public void ejecutarBacthDescobros() {
		
		ImputacionDescobrosBatchRunner.main(null);
	}
	
	//@Test
	public void imputarDescobro() {
		
		try {
				ShvCobDescobro descobro = descobroServicio.buscarDescobroPorIdOperacion(new Long(100026));
				
				//Imputo el cobro con el descobro pendiente/En proceso
				descobro = descobroImputacionServicio.imputarDescobro(descobro.getIdDescobro(), 0);
				
				//Por el manejo de la transaccion de la BD, se quedo fuera del metodo imputarCobro
				if (Estado.DES_DESCOBRADO.equals(descobro.getWorkflow().getEstado()) ||
						Estado.DES_EN_ERROR.equals(descobro.getWorkflow().getEstado()) || 
						Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(descobro.getWorkflow().getEstado()) ) {

					try {
						//SHV - Se le deberá dar aviso al usuario de la finalización 
						//del proceso batch de reversión de cobro
						descobroImputacionServicio.enviarMailyGenerarTarea(descobro);
					} catch (Exception e){
						Traza.error(ImputacionDescobrosBatchRunner.class, 
								"No se ha podido generar la tarea y enviar el mail. operacion id: " 
										+ descobro.getOperacion().getIdOperacion(), e);
					}	
				}//Fin - Imputo el descobro
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
