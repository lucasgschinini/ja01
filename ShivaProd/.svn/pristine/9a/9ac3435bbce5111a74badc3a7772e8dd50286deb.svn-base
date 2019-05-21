package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITeamComercialDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamTeamComercial;
import ar.com.telecom.shiva.persistencia.repository.TeamComercialRepositorio;

public class TeamComercialDaoImpl extends Dao implements ITeamComercialDao {
	
	@Autowired 
	private TeamComercialRepositorio repositorio;
	
	public void borrarTodos() {
		repositorio.deleteAll();
	}
	
	public void guardarTeamComercial(ShvParamTeamComercial teamComercial) throws PersistenciaExcepcion {
 		try {
 			repositorio.save(teamComercial);
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage());
		} 
	}

	@SuppressWarnings("unchecked")
	public ShvParamTeamComercial buscarTeamComercial(String nroCliente) throws PersistenciaExcepcion {
		try {
			String query = "select sptc from ShvParamTeamComercial as sptc "
							+ "where sptc.nroDeCliente = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(nroCliente,Types.VARCHAR);
			
			List<ShvParamTeamComercial> resultado = (List<ShvParamTeamComercial>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(resultado)){
				return resultado.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listarIdAnalistaTeamComercialPorListaNroCliente(List<String> listaNroCliente) throws PersistenciaExcepcion {
		try {
			String query = "select sptc.userAnalistaCobranzaDatos from ShvParamTeamComercial as sptc "
							+ "where sptc.nroDeCliente in( ? )";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(listaNroCliente);
			
			return (List<String>) buscarUsandoQueryConParametros(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	@Override
	public Long obtenerUltimoId() throws PersistenciaExcepcion {
		try{
			String query = "select MAX(ID_TEAM_COMERCIAL) as ID_TEAM_COMERCIAL "
							+ "from SHV_PARAM_TEAM_COMERCIAL "
							+ "where EMPRESA not like 'TA' or ORIGEN not like 'DELTA'";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			List<Map<String,Object>> lista = buscarUsandoSQL(qp);
			
			if( !Validaciones.isObjectNull( lista.get(0).get("ID_TEAM_COMERCIAL") ) ) {
				return ( (BigDecimal) lista.get(0).get("ID_TEAM_COMERCIAL") ).longValue();
			} else {
				return 0L;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public void borrarPorEmpresaYOrigen(EmpresaEnum empresa, ClienteOrigenEnum origen) throws PersistenciaExcepcion{
		repositorio.borrar(empresa, origen);
	}

	public TeamComercialRepositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(TeamComercialRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	

}