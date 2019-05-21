package ar.com.telecom.shiva.test.modulos;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ConciliacionTest extends SoporteContextoSpringTest {
	
	@Test
	public void validarCuit(){
//		String cuit = "PAGO PROVEEDORES INTERBANKING EXTE-20007 00D.N.VIAL 5500 604-FDO 30546675676 03 8227060";
		String cuit = "04-FDO 30546675676 03 8227060";
		char[] entrada = cuit.toCharArray();
		
		System.out.println(Utilidad.cuitFormateado(entrada));
	}
	
	
//	@Autowired 
//	private IConciliacionServicio conciliacionServicio;
	@Autowired 
	private IMotorProcesamientoConciliacionServicio motorProcesamientoConciliacionServicio;
	
//	@BeforeClass
//	@SuppressWarnings("resource")
//    public static void setUpClass() throws Exception {
//		
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
//        DataSource testDataSource = (DataSource) context.getBean("testDataSource");
//        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
//        builder.bind("java:jboss/datasources/ShivaDS", testDataSource);
//        builder.activate();
//        
//        System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
//
//    }
	
//	@Test
//	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
//	public void test_levantarArchivoRegistroAVC() throws PersistenciaExcepcion{
//		System.out.println("Levantando archivos de Registros AVC ------");
//
//		try {
//			conciliacionServicio.procesarArchivosRegistrosAVC();
//		} catch (NegocioExcepcion e) {
//			throw new PersistenciaExcepcion(e);
//		}
//		
//		System.out.println("FIN - Levantando archivos de Registros AVC ------");
//	}
	
//	@Test
	public void test_procesoConciliacion() throws PersistenciaExcepcion{
		System.out.println("Proceso de Conciliacion INICIO ------");
		IRegistroAVCServicio registroServicio = (IRegistroAVCServicio) Configuracion.getBeanBatch("registroServicio");
		
		try {
			// Busco los Registros AVC en estado Pendiente de conciliar, excepto los de tipo Transferencia.
			List<RegistroAVCDto> listaRAVC = registroServicio.listarRegistrosDepositoPendientesConciliar();
			
				// Recorro los RAVC
			for (RegistroAVCDto registroAvc : listaRAVC) {
//				motorProcesamientoConciliacionServicio.ejecutarProcesoConciliacion(registroAvc);
			}
		} catch (NegocioExcepcion e) {
			throw new PersistenciaExcepcion(e);
		}
		
		System.out.println("Proceso de Conciliacion FIN ------");
	}

	public IMotorProcesamientoConciliacionServicio getMotorProcesamientoConciliacionServicio() {
		return motorProcesamientoConciliacionServicio;
	}

	public void setMotorProcesamientoConciliacionServicio(
			IMotorProcesamientoConciliacionServicio motorProcesamientoConciliacionServicio) {
		this.motorProcesamientoConciliacionServicio = motorProcesamientoConciliacionServicio;
	}
	

	
	
	

//	public IConciliacionServicio getConciliacionServicio() {
//		return conciliacionServicio;
//	}
//
//	public void setConciliacionServicio(IConciliacionServicio conciliacionServicio) {
//		this.conciliacionServicio = conciliacionServicio;
//	}

}
