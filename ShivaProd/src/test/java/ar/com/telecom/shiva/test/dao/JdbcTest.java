package ar.com.telecom.shiva.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IInterfazExcepcionMorosidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroCalendarioServicio;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParametroCalendarioDao;
import ar.com.telecom.shiva.persistencia.repository.TestRepository;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = "desa_local_ldap")
//@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml" })
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcTest extends SoporteContextoSpringTest{
	
	@Autowired TestRepository testRepository;
	@Autowired Dao soporteDao;
	@Autowired IParametroCalendarioDao ParametroCalendarioDao;
	@Autowired IParametroCalendarioServicio parametroCalendarioServicio;
	@Autowired IInterfazExcepcionMorosidadServicio interfazExcepcionMorosidadServicio; 
//	@Test
//	public void testEjecutarSQL() throws PersistenciaExcepcion {
//		List<Map<String, Object>> list;
//		String query = "select * from test_table";
//		QueryParametrosUtil qp = new QueryParametrosUtil(query);
//		list = soporteDao.buscarUsandoSQL(qp);
//		Iterator<Map<String, Object>> it = list.iterator();
//	    while(it.hasNext())
//	    {
//	    	Object o = it.next();
//	        System.out.println(o.toString());
//	    }
//	}
//	@Test
//	public void testEjecutarSQL() throws PersistenciaExcepcion, NegocioExcepcion {
////		List<ShvParamCalendario> shvParamCalendarios = ParametroCalendarioDao.buscaParamtroCalendarioApartirDe(new Date());
////	
////		for (ShvParamCalendario shvParamCalendario : shvParamCalendarios) {
////			System.out.println(">>-----> " + shvParamCalendario.getFecha());
////		}
//		Date now = new Date();
//		Set<ShvParamCalendarioWrapper> shvParamCalendarios = parametroCalendarioServicio.buscaParamtroCalendarioApartirDe(now);
//		
//		
//		Calendar nowCalendar = Calendar.getInstance();
//		nowCalendar.setTime(now);
//		Calendar calendar = Calendar.getInstance();
//		
//		Date date = parametroCalendarioServicio.calcularFechaHasta(now, false, 0, shvParamCalendarios);
//		System.out.println("1---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//		
//		date = parametroCalendarioServicio.calcularFechaHasta(now, true, 0, shvParamCalendarios);
//		System.out.println("2---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//		
//		date = parametroCalendarioServicio.calcularFechaHasta(now, false, 1, shvParamCalendarios);
//		System.out.println("3---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//		
//		date = parametroCalendarioServicio.calcularFechaHasta(now, true, 1, shvParamCalendarios);
//		System.out.println("4---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//	
//	
//		date = parametroCalendarioServicio.calcularFechaHasta(now, false, 8, shvParamCalendarios);
//		System.out.println("5---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//		
//		date = parametroCalendarioServicio.calcularFechaHasta(now, true, 8, shvParamCalendarios);
//		System.out.println("6---now {" + Utilidad.formatDateAAAAMMDD_HHmmss(now)+"} -------"+ Utilidad.formatDateAAAAMMDD_HHmmss(date));
//
//	
//	}
	
	@Test
	public void testGenerarArchivoMorosidadRunner() throws PersistenciaExcepcion, NegocioExcepcion {
//		List<ShvParamCalendario> shvParamCalendarios = ParametroCalendarioDao.buscaParamtroCalendarioApartirDe(new Date());
//	
//		for (ShvParamCalendario shvParamCalendario : shvParamCalendarios) {
//			System.out.println(">>-----> " + shvParamCalendario.getFecha());
//		}
		interfazExcepcionMorosidadServicio.generarArchivoExcepcionMorosidad("20/07/2016");
	//System.out.println(Utilidad.formatDecimales(new BigDecimal(60.51), 4));
	}
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testEjecutarHibernate() throws PersistenciaExcepcion {
//		StringBuilder str = new StringBuilder("from ShvSopExcepcionMorosidad excepcionMorosidad WHERE ");
//		str.append("excepcionMorosidad.fechaModificacionUltimoEstado BETWEEN ? AND ?");
//		//str.append("excepcionMorosidad.idCobro = 2389");
//
//		QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());	
//		try {
//			qp.addParametro(Utilidad.deserializeAndFormatDate("11/02/2016 00:00:00", Utilidad.DATE_TIME_FULL_FORMAT));
//			qp.addParametro(Utilidad.deserializeAndFormatDate("11/02/2016 23:59:59", Utilidad.DATE_TIME_FULL_FORMAT));
//			System.out.println(qp.getSql());
//			List<ShvSopExcepcionMorosidad> list = soporteDao.buscarUsandoQueryConParametros(qp);
//			
//			Iterator<ShvSopExcepcionMorosidad> it = list.iterator();
//		    while(it.hasNext())
//	        {
//		    	ShvSopExcepcionMorosidad o = it.next();
//	            System.out.println(o.getIdCobro() + "-");
//	        }
//		} catch (ParseException e) {
//			throw new PersistenciaExcepcion("Error en formato de fecha", e);
//		}
//		SimpleDateFormat dateFormatAll = new SimpleDateFormat("YYYYddMM HHmmss");
//	       System.out.println(dateFormatAll.format(new Date()));
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testEjecutarHibernatePaginado() throws PersistenciaExcepcion {
//		List<TestTableModel> list;
//		QueryParametrosUtil qp = new QueryParametrosUtil("from TestTableModel");
//		list = soporteDao.buscarUsandoQueryConParametros(qp, 0, 3);
//		
//		Iterator<TestTableModel> it = list.iterator();
//	    while(it.hasNext())
//	    {
//	       	TestTableModel o = it.next();
//	        System.out.println(o.getId() + "-" + o.getDescription());
//	    }
//	}
//	
//	@Test
//	public void testBuscarUsandoRepository() {
//		List<TestTableModel> list = testRepository.buscarTodos();
//		Iterator<TestTableModel> it = list.iterator();
//	    while(it.hasNext())
//	    {
//	      	TestTableModel o = it.next();
//	        System.out.println(o.getId() + "-" + o.getDescription());
//	    } 
//	}
//	
//	@Test
//	public void testBuscarUnRegistro() {
//		//Usando un simple repository
//		TestTableModel o = testRepository.findOne(8);
//		System.out.println(o.getId() + "-" + o.getDescription());
//	    
//		//Usando un repository personalizado
//		TestTableModel o1 = testRepository.buscarId(8);
//		System.out.println(o1.getId() + "-" + o1.getDescription());
//		
//		//Usando un repository con findBy[campo]
//		TestTableModel o2 = testRepository.findByDescription("a");
//		System.out.println(o2.getId() + "-" + o2.getDescription());
//	}
//	
//	@Test
//	public void testGuardar() throws PersistenciaExcepcion {
//		//Usando Transaccion Hibernate/JPA 
//		//TestTableModel o = new TestTableModel();
//		//o.setDescription("prueba");
//		//soporteDao.insertar(o);
//		
//		//Usuando JPA Repository 
//		TestTableModel o2 = new TestTableModel();
//		o2.setDescription("prueba2");
//		testRepository.save(o2);
//		
//		List<TestTableModel> list = testRepository.buscarTodos();
//		Iterator<TestTableModel> it = list.iterator();
//	    while(it.hasNext())
//	    {
//	      	TestTableModel o1 = it.next();
//	        System.out.println(o1.getId() + "-" + o1.getDescription());
//	    } 
//	}
//	
//	@Test
//	public void testModificar() throws PersistenciaExcepcion {
//		//Usando Transaccion Hibernate/JPA 
//		//TestTableModel o1 = testRepository.buscarId(8);
//		//o1.setDescription("prueba");
//		//soporteDao.actualizar(o1);
//		
//		//Usuando JPA Repository (Funciona)
//		TestTableModel o2 = testRepository.buscarId(9);
//		o2.setDescription("prueba3");
//		testRepository.save(o2);
//		
//		List<TestTableModel> list = testRepository.buscarTodos();
//		Iterator<TestTableModel> it = list.iterator();
//	    while(it.hasNext())
//	    {
//	      	TestTableModel o = it.next();
//	        System.out.println(o.getId() + "-" + o.getDescription());
//	    } 
//	}
//	
//	@Test
//	public void testXEstadisticas() {
//		Statistics stats = soporteDao.getEstadisticas();
//		
//		System.out.println("Estadisticas: " + stats);
//		System.out.println("Number of connection requests: " + stats.getConnectCount());
//		System.out.println("The number of sessions your code has opened:" + stats.getSessionOpenCount());
//		System.out.println("The number of sessions your code has closed:" + stats.getSessionCloseCount());
//		System.out.println("The number of completed transactions (failed and successful): " + stats.getTransactionCount());
//		System.out.println("The number of transactions completed without failure: " + stats.getSuccessfulTransactionCount());
//		System.out.println("All of the queries that have executed: " + stats.getQueries());
//		System.out.println("Total number of queries executed: " + stats.getQueryExecutionCount());
//	}
	
}
