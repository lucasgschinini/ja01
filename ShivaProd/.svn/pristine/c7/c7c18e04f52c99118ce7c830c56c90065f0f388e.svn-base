package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAVCDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;
import ar.com.telecom.shiva.persistencia.repository.RegistroAVCAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.RegistroAVCRepositorio;
import ar.com.telecom.shiva.persistencia.repository.RegistroAVCValorRepositorio;

public class RegistroAVCDaoImpl extends Dao implements IRegistroAVCDao {

	@Autowired RegistroAVCRepositorio registroAVCRepositorio;
	@Autowired RegistroAVCAdjuntoRepositorio registroAVCAdjuntoRepositorio;
	@Autowired RegistroAVCValorRepositorio registroAVCValorRepositorio;
	
	/**
	 * Inserta el registro AVC
	 * @param RegistroAVC
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvAvcRegistroAvc crear(ShvAvcRegistroAvc registroAVC) throws PersistenciaExcepcion {
		try{
			ShvAvcRegistroAvc regAVCBD = registroAVCRepositorio.save(registroAVC);
			registroAVCRepositorio.flush();
			return regAVCBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna la lista de Registros AVC Deposito en estado 'Pendientes de conciliar'.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvAvcRegistroAvc> listarRegistrosDepositoPendientesConciliar() throws PersistenciaExcepcion {
		try {
			
			
			String query = "select ravc from ShvAvcRegistroAvc as ravc "
					+ "join ravc.workFlow as w  "
					+ "join ravc.deposito as dep  "
					+ "join w.shvWfWorkflowEstado as we "
					+ "where we.estado=? and ravc.acuerdo.idAcuerdo in (28,29,33,34) "
					+ "and we.fechaModificacion>to_timestamp(?,'dd/mm/yyyy hh24:mi:ss')"
					+ "order by dep.nroBoleta desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.AVC_PENDIENTE);
			/**
			 * U562163 Mejora del motor
			 */
			//Obtengo la fecha del primer dia del mes anterior
			GregorianCalendar fechaPrimerDiaMesAnterior = new GregorianCalendar(); 
			fechaPrimerDiaMesAnterior.add(GregorianCalendar.MONTH, -1);
			fechaPrimerDiaMesAnterior.set(fechaPrimerDiaMesAnterior.get(GregorianCalendar.YEAR), fechaPrimerDiaMesAnterior.get(GregorianCalendar.MONTH), 1);
			qp.addParametro(Utilidad.formatDatePicker(fechaPrimerDiaMesAnterior.getTime())+" 00:00:00");

			
			List<ShvAvcRegistroAvc> list = (List<ShvAvcRegistroAvc>) buscarUsandoQueryConParametros(qp);
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna la lista de Registros AVC Interdeposito y Transferencia en estado 'Pendientes de conciliar'.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvAvcRegistroAvc> listarRegistrosInterdepositoYTransferenciaPendientesConciliar() throws PersistenciaExcepcion {
		try {
			String query = "select ravc from ShvAvcRegistroAvc as ravc "
					+ "join ravc.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as we "
					+ "where we.estado=?  and ravc.acuerdo.idAcuerdo not in (28, 29, 33, 34) "
					+ " order by ravc.tipoValor.idTipoValor ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.AVC_PENDIENTE);
			List<ShvAvcRegistroAvc> list = (List<ShvAvcRegistroAvc>) buscarUsandoQueryConParametros(qp);
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna el registro AVC modelo correspondiente al id de registro que 
	 * recibe por parametro.
	 */
	public ShvAvcRegistroAvc buscarRegistroAVC(String id) throws PersistenciaExcepcion {
		return registroAVCRepositorio.findOne(Long.valueOf(id));
	}
	
	@SuppressWarnings("unchecked")
	public ShvAvcRegistroAvcValor buscarRegistroAvcValorPorIdValor(String idValor) throws PersistenciaExcepcion {
		String query = "from ShvAvcRegistroAvcValor as regValor " + 
					" where regValor.shvAvcRegistroAvcValorPK.valor.idValor=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(new Long(idValor));
		List<ShvAvcRegistroAvcValor> registro = (List<ShvAvcRegistroAvcValor>) buscarUsandoQueryConParametros(qp);
		
		if (Validaciones.isCollectionNotEmpty(registro)) {
			return registro.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public ShvAvcRegistroAvcValor buscarRegistroAvcValorPorIdRegistroAvc(String idRegistroAvc) throws PersistenciaExcepcion {
		String query = "from ShvAvcRegistroAvcValor as regValor" + 
				" where regValor.shvAvcRegistroAvcValorPK.registroAvc.idRegistroAvc=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(new Long(idRegistroAvc));
	
		List<ShvAvcRegistroAvcValor> registro = (List<ShvAvcRegistroAvcValor>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(registro)) {
			return registro.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Actualiza el registro AVC en la base.
	 */
	public ShvAvcRegistroAvc modificar(ShvAvcRegistroAvc registroModelo) throws PersistenciaExcepcion {
		try {
			ShvAvcRegistroAvc regAVCBD = registroAVCRepositorio.save(registroModelo);
			registroAVCRepositorio.flush();
			return regAVCBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Retorna la lista de Registros AVC en estado 'Pendientes de conciliar' o 
	 * 'Pendiente de Confirmar Alta de Valor' o 'Alta de Valor Rechazada'.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvAvcRegistroAvc> listarRegistrosAVCSinConciliar() throws PersistenciaExcepcion {
		try {
			String query = "select ravc from ShvAvcRegistroAvc as ravc "
					+ "join ravc.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as we "
					+ "where we.estado in (?,?,?)";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.AVC_PENDIENTE);
			qp.addParametro(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR);
			qp.addParametro(Estado.AVC_ALTA_VALOR_RECHAZADA);
			List<ShvAvcRegistroAvc> list = (List<ShvAvcRegistroAvc>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Graba en ShvAvcRegistroAdjunto
	 */
	public ShvAvcRegistroAdjunto insertarDocumentoAjunto(ShvAvcRegistroAdjunto registroAdjunto)
			throws PersistenciaExcepcion {
		return registroAVCAdjuntoRepositorio.save(registroAdjunto);
	}

	@SuppressWarnings("unchecked")
	public List<ShvAvcRegistroAdjunto> buscarDocumentosAdjuntosPorIdTransferencia(String idRegistro) throws PersistenciaExcepcion {
		try {
			String query = "select rj from ShvAvcRegistroAdjunto as rj "
					+ " where rj.shvAvcRegistroAdjuntoPk.registroAvc.idRegistroAvc=? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(idRegistro));
			
			List<ShvAvcRegistroAdjunto> list = (List<ShvAvcRegistroAdjunto>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Inserta en la tabla ShvAvcRegistroAvcValor
	 * @param registroAVCValor
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvAvcRegistroAvcValor crearShvAvcRegistroAvcValor(ShvAvcRegistroAvcValor registroAVCValor) throws PersistenciaExcepcion {
		try{
			
			ShvAvcRegistroAvcValor regAVCBD = registroAVCValorRepositorio.save(registroAVCValor);
			registroAVCValorRepositorio.flush();
			return regAVCBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
}
