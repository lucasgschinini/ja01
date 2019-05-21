package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.repository.RegistroOperacionMasivaRepositorio;

@SuppressWarnings("unchecked")
public class RegistroOperacionMasivaDaoImpl extends Dao implements IRegistroOperacionMasivaDao{

	@Autowired RegistroOperacionMasivaRepositorio registroOperacionMasivaRepositorio;
	@Autowired IGenericoDao genericoDao;

	
	/**
	 * Inserta el registro Operacion Masiva
	 * @param ShvMasRegistro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public ShvMasRegistro crear(ShvMasRegistro registro) throws PersistenciaExcepcion {
		try{
			ShvMasRegistro reg = registroOperacionMasivaRepositorio.save(registro);
			registroOperacionMasivaRepositorio.flush();
			return reg;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Actualiza el registro de Operacion Masviva en la base (SHV_MAS_REGISTRO).
	 */
	@Override
	public ShvMasRegistro modificar(ShvMasRegistro registroModelo) throws PersistenciaExcepcion {
		try {
//			ShvMasRegistro regAVCBD = registroOperacionMasivaRepositorio.save(registroModelo);
//			registroOperacionMasivaRepositorio.flush();
//			return regAVCBD;
			return this.crear(registroModelo);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna la lista de Registros de Operaciones Masivas en estado 'Pendiente'
	 */
	@Override
	public List<ShvMasRegistro> listarRegistrosOperacionesMasivasPendientes() throws PersistenciaExcepcion {
		List<ShvMasRegistro> lista;	
		try {
			String query = "select regMas from ShvMasRegistro as regMas "
					+ " where regMas.estado =  '" + EstadoRegistroOperacionMasivaEnum.PENDIENTE_DATOS_SIEBEL.name() + "'";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			
			lista = (List<ShvMasRegistro>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna la lista de Registros de Operaciones Masivas en estado 'Procesado'.
	 */
	@Override
	public List<ShvMasRegistro> listarRegistrosOperacionesMasivasProcesados() throws PersistenciaExcepcion {
		List<ShvMasRegistro> lista;	
		try {
			String query = "select regMas from ShvMasRegistro as regMas "
					+ " where regMas.estado = '" + EstadoRegistroOperacionMasivaEnum.PROCESADO.name() + "'";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			
			lista = (List<ShvMasRegistro>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna el registro Operacion Masiva modelo correspondiente al id de registro que 
	 * recibe por parametro.
	 */
	@Override
	public ShvMasRegistro buscarRegistroOperacionMasiva(String id) throws PersistenciaExcepcion {
		return registroOperacionMasivaRepositorio.findOne(Long.valueOf(id));
	}
	
	@Override
	public ShvMasRegistro buscarRegistroOperacionMasivaPorIdOperacionMasiva(String idOperacionMasiva) throws PersistenciaExcepcion {
		
		List<ShvMasRegistro> registro;	
		try {
			String query = "select regMasiva from ShvMasRegistro as regMasiva "
					+ " join regMasiva.operacionMasiva as opMasiva "
					+ " where opMasiva.idOperacionMasiva = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(Long.valueOf(idOperacionMasiva),Types.NUMERIC);
			
			registro = (List<ShvMasRegistro>) buscarUsandoQueryConParametros(qp);

			if(Validaciones.isCollectionNotEmpty(registro)) {
				return registro.get(0);
			}else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public ShvMasRegistro buscarRegistroOperacionMasivaPorIdCobro(String idCobro)throws PersistenciaExcepcion {
		List<ShvMasRegistro> registro;	
		try {
			String query = "select regMasiva from ShvMasRegistro as regMasiva "
					+ " join regMasiva.cobro as cob "
					+ " where cob.idCobro = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(Integer.valueOf(idCobro),Types.NUMERIC);
			
			registro = (List<ShvMasRegistro>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(registro)) {
				return registro.get(0);
			}else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**
	 * @param estadoReg
	 * @param estadoOperacionMasiva
	 * @return
	 * @throws PersistenceException
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public List<ShvMasRegistro> buscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva(
		EstadoRegistroOperacionMasivaEnum estadoReg, List<Estado> estadosOperacionMasiva) throws PersistenciaExcepcion{
		
		List<ShvMasRegistro> registros;
		try {
			StringBuffer strFiltroEstadoOperacionMasiva = new StringBuffer("(");
			
			for (Estado estado : estadosOperacionMasiva) {
				if (strFiltroEstadoOperacionMasiva.length() > 1) {
					strFiltroEstadoOperacionMasiva.append(", ?");
				} else {
					strFiltroEstadoOperacionMasiva.append("?");
				}
			}
			
			StringBuffer str = new StringBuffer();
			str.append("Select regMasiva ");
			str.append("from ShvMasRegistro as regMasiva left join regMasiva.operacionMasiva ");
			str.append("where regMasiva.operacionMasiva in ");
			str.append("(select mas from ShvMasOperacionMasiva as mas left join mas.workFlow ");
			str.append("left join mas.workFlow.shvWfWorkflowEstado workflowEstado where workflowEstado.estado in ");
			str.append(strFiltroEstadoOperacionMasiva.toString());
			str.append(")) and regMasiva.estado in (?) ");
			str.append("order by regMasiva.idRegistro desc");
			
			QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());
			for (Estado estado : estadosOperacionMasiva) {
				qp.addParametro(estado);
			}
			qp.addParametro(estadoReg);
			
			registros = (List<ShvMasRegistro>) buscarUsandoQueryConParametros(qp);
			return registros;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public List<ShvMasRegistro> buscarRegistrosByIds(List<Long> ids) {
		return (List<ShvMasRegistro>) registroOperacionMasivaRepositorio.findAll(ids);
	}
	@Override
	public String test() {
		return "test";
	}
	/**
	 * Obtengo un id de operaciones shiva que se encuentre en un registro que solo se encuentre en un estado "Error mic"
	 *
	 */
	@Override
	public List<Long> obtenerIdOperacionShivaReutilizable() throws PersistenciaExcepcion {
		try {
		List<Long> ids = new ArrayList<Long>();
			StringBuffer str = new StringBuffer();
			str.append("SELECT DISTINCT regMasiva.idOperacion FROM ShvMasRegistro AS regMasiva ");
			str.append("WHERE regMasiva.estado IN (?, ?, ?, ?) AND regMasiva.idOperacion is not null AND ");
			str.append("regMasiva.idOperacion NOT IN ");
			str.append("(SELECT regMasiva1.idOperacion FROM ShvMasRegistro AS regMasiva1 ");
			str.append("WHERE regMasiva1.estado NOT IN (?, ?, ?, ?) AND regMasiva1.idOperacion is not null )");

			QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_MIC);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_SIEBEL);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR);
			
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_MIC);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_SIEBEL);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			qp.addParametro(EstadoRegistroOperacionMasivaEnum.ERROR);
			
			ids = (List<Long>) buscarUsandoQueryConParametros(qp);
			return ids;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}	
	}
	@Override
	public List<ShvMasRegistro> modificar(List<ShvMasRegistro> shvMasRegistro) throws PersistenciaExcepcion {
		try{
			List<ShvMasRegistro> list = registroOperacionMasivaRepositorio.save(shvMasRegistro);
			registroOperacionMasivaRepositorio.flush();
			return list;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvMasRegistro buscarRegistroByEstadoAndIdOperacion(EstadoRegistroOperacionMasivaEnum estado, Long idOperacion) throws PersistenciaExcepcion {
		ShvMasRegistro registro = null;
		try {
			registro = registroOperacionMasivaRepositorio.buscarRegistroByEstadoAndIdOperacion(estado, idOperacion);	
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		return registro;
	}
	@Override
	public List<ShvMasRegistro> buscarRegistroByEstadoAndIdOperacionMasiva(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva) throws PersistenciaExcepcion {
		List<ShvMasRegistro> registro = null;
		try {
			registro = registroOperacionMasivaRepositorio.buscarRegistroByEstadoAndIdOperacionMasiva(estado, idOperacionMasiva);	
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		return registro;
	}	
	
	@Override
	public List<ShvMasRegistro> buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva) throws PersistenciaExcepcion {
		List<ShvMasRegistro> registro = null;
		try {
			registro = registroOperacionMasivaRepositorio.buscarRegistrosXOperacionMasivaDistintosAEstado(estado, idOperacionMasiva);	
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		return registro;
	}	
}
