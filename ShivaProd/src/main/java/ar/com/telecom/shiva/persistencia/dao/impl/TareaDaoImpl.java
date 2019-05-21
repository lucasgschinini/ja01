package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvWfTareaSimplificado;
import ar.com.telecom.shiva.persistencia.repository.TareaRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.TareaFiltro;

public class TareaDaoImpl extends Dao implements ITareaDao {

	@Autowired
	TareaRepositorio tareaRepositorio;
	
	@Override
	public ShvWfTarea insertarTarea(ShvWfTarea tarea)
			throws PersistenciaExcepcion {
		
		try {
			ShvWfTarea tareaBD = tareaRepositorio.save(tarea);
			tareaRepositorio.flush();
			return tareaBD;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvWfTarea actualizarTarea(ShvWfTarea tarea)
			throws PersistenciaExcepcion {
		
		try {
			ShvWfTarea tareaBD = tareaRepositorio.save(tarea);
			tareaRepositorio.flush();
			return tareaBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public void eliminarTarea(ShvWfTarea tarea) throws PersistenciaExcepcion {
		try {
			tareaRepositorio.delete(tarea);
			tareaRepositorio.flush();
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvWfTarea buscarTarea(Long id) throws PersistenciaExcepcion {
		try {
			return tareaRepositorio.findOne(id);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvWfTarea buscarTareaPendiente(TipoTareaEnum tipoTarea, Integer idWorkflow)	throws PersistenciaExcepcion {
		return buscarTareaPendiente(tipoTarea,null,null,idWorkflow);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShvWfTarea buscarTareaPendiente(TipoTareaEnum tipoTarea, SociedadEnum sociedad, SistemaEnum sistema, Integer idWorkflow)	throws PersistenciaExcepcion {
		
		try {
			TareaFiltro filtro = new TareaFiltro();
			filtro.setEstadoTarea(TipoTareaEstadoEnum.PENDIENTE);
			filtro.setIdWorkflow(idWorkflow);
			filtro.setTipoTarea(tipoTarea);
			filtro.setSociedad(sociedad);
			filtro.setSistema(sistema);
			
			QueryParametrosUtil qp = generarConsulta(filtro);
			List<ShvWfTarea> lista = (List<ShvWfTarea>) buscarUsandoQueryConParametros(qp);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvWfTarea> buscarTareasPendientes(Integer idWorkflow)
			throws PersistenciaExcepcion {

		try {
			TareaFiltro filtro = new TareaFiltro();
			filtro.setEstadoTarea(TipoTareaEstadoEnum.PENDIENTE);
			filtro.setIdWorkflow(idWorkflow);

			QueryParametrosUtil qp = generarConsulta(filtro);
			List<ShvWfTarea> lista = (List<ShvWfTarea>) buscarUsandoQueryConParametros(qp);

			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvWfTarea> listarTareas(TareaFiltro filtro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = generarConsulta(filtro);
			List<ShvWfTarea> list = (List<ShvWfTarea>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**
	 * Retorna las tareas de error en cobro y aplicacion manual rechazada.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvWfTareaSimplificado> listarTareasParaEstadoFinalDeCobro(TareaFiltro filtro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil();
			
			String query = "select t from ShvWfTareaSimplificado as t where t.idWorkflow=? and t.tipoTarea in (?,?)";
			
			qp.addParametro(new Integer(filtro.getIdWorkflow()));
			qp.addParametro(TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL);
			qp.addParametro(TipoTareaEnum.COB_REV_COB_ERR);
			qp.setSql(query);
			
			List<ShvWfTareaSimplificado> list = (List<ShvWfTareaSimplificado>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
			
	/**
	 * Metodo Privado generico 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil generarConsulta(TareaFiltro filtro) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		String str = "";
		
		/*   ID Worflow   */
		if (!Validaciones.isObjectNull(filtro.getIdWorkflow()) && !Validaciones.isNullOrEmpty(filtro.getIdWorkflow().toString())) {
			str += " where t.idWorkflow=? ";
			qp.addParametro(new Integer(filtro.getIdWorkflow()));
		}
		
		if (!Validaciones.isObjectNull(filtro.getTipoTarea())) {
			str += obtenerOperadorBusqueda(str) + " t.tipoTarea=? ";
			qp.addParametro(filtro.getTipoTarea());
		}
		
		if (!Validaciones.isObjectNull(filtro.getSociedad())) {
			str += obtenerOperadorBusqueda(str) + " t.sociedad=? ";
			qp.addParametro(filtro.getSociedad());
		}
		
		if (!Validaciones.isObjectNull(filtro.getSistema())) {
			str += obtenerOperadorBusqueda(str) + " t.sistema=? ";
			qp.addParametro(filtro.getSistema());
		}
		
		if (!Validaciones.isObjectNull(filtro.getEstadoTarea())) {
			str += obtenerOperadorBusqueda(str) + " t.estado=? ";
			qp.addParametro(filtro.getEstadoTarea());
		}
		
		if (!Validaciones.isObjectNull(filtro.getUsuarioLogeado())) {
			str += obtenerOperadorBusqueda(str) + " (t.usuarioCreacion=? ";
			qp.addParametro(filtro.getUsuarioLogeado().getUsuario());
			
			str += " or  upper(t.perfilAsignacion) in ( ";
			
			String perfiles = "";
			for (String perfil: filtro.getUsuarioLogeado().getPerfiles()) {
				perfiles += "?,";
				qp.addParametro(perfil);
			}
			
			str += perfiles.substring(0, perfiles.length()-1) + " ))";
		}
		
		String query = "select t from ShvWfTarea as t " + str;
		qp.setSql(query);
		
		return qp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ShvWfTarea buscarTareaPendienteConfManual(TipoTareaEnum tipoTarea, SociedadEnum sociedad, SistemaEnum sistema, Integer idWorkflow)	throws PersistenciaExcepcion {
		
		try {
			TareaFiltro filtro = new TareaFiltro();
			filtro.setEstadoTarea(TipoTareaEstadoEnum.PENDIENTE);
			filtro.setIdWorkflow(idWorkflow);
			filtro.setTipoTarea(tipoTarea);
			filtro.setSociedad(sociedad);
			filtro.setSistema(sistema);
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvWfTarea where (sociedad<>? or sistema<>?) and idWorkflow =? and tipoTarea =? and estado =?");
			qp.addParametro(filtro.getSociedad());
			qp.addParametro(filtro.getSistema());
			qp.addParametro(filtro.getIdWorkflow());
			qp.addParametro(filtro.getTipoTarea());
			qp.addParametro(filtro.getEstadoTarea());

			List<ShvWfTarea> lista = (List<ShvWfTarea>) buscarUsandoQueryConParametros(qp);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**Busca las tareas finalizadas del tipo aplicacion manual para obtener las observaciones
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvWfTarea> buscarTareaAPLManualParaObservaciones(Integer idWorkflow)	throws PersistenciaExcepcion {
		
		try {
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvWfTarea where idWorkflow =? and tipoTarea =? and estado =?");
			qp.addParametro(idWorkflow);
			qp.addParametro(TipoTareaEnum.COB_CONF_APLIC_MANUAL);
			qp.addParametro(TipoTareaEstadoEnum.FINALIZADA);

			List<ShvWfTarea> lista = (List<ShvWfTarea>) buscarUsandoQueryConParametros(qp);
			
			return lista;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * 
	 */
	
	public TareaRepositorio getTareaRepositorio() {
		return tareaRepositorio;
	}

	public void setTareaRepositorio(TareaRepositorio tareaRepositorio) {
		this.tareaRepositorio = tareaRepositorio;
	}

	
}
