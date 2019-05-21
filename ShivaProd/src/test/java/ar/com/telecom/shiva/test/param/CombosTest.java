package ar.com.telecom.shiva.test.param;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "desa_local_ldap")
@ContextConfiguration(locations = { "classpath*:/context/spring-jdbc.xml", "classpath*:/context/spring-security.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CombosTest {
	
	@Autowired IParametroDao dao;
	private IGenericoDao genericoDao;
	
	@Autowired ShvParamEmpresa empresa;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListarEmpresas() throws PersistenciaExcepcion {
	
		try {
			Collection<ShvParamEmpresa> list = (Collection<ShvParamEmpresa>) genericoDao.listar(ShvParamEmpresa.class);
			Iterator<ShvParamEmpresa> it = list.iterator();
		    while(it.hasNext())
		    {
		    	ShvParamEmpresa o = (ShvParamEmpresa) it.next();
		        System.out.println(o.getIdEmpresa() + " - "+ o.getDescripcion());
		    }
		    
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Test
	public void testListarEstados(){
		List<Estado> lista = Estado.listarEstados("tal");
		for(Estado e: lista){
			System.out.println(e.name());
			System.out.println(e.descripcion());
		}
	}
	
	public IParametroDao getDao() {
		return dao;
	}
	public void setDao(IParametroDao dao) {
		this.dao = dao;
	}
}