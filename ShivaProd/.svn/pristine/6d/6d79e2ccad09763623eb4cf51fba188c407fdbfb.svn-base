package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSimplificado;
import ar.com.telecom.shiva.persistencia.repository.BoletaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.BoletaRepositorioSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public class BoletaDaoImpl extends Dao implements IBoletaDao {

	@Autowired
	BoletaRepositorio boletaRepositorio;
	@Autowired
	BoletaRepositorioSimplificado boletaRepositorioSimplificado;
	
	public ShvBolBoleta insertarBoleta(ShvBolBoleta boleta) throws PersistenciaExcepcion {
		try{
			ShvBolBoleta boletaBD = boletaRepositorio.save(boleta);
			boletaRepositorio.flush();
			return boletaBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public ShvBolBoleta buscarBoleta(Long id) throws PersistenciaExcepcion {
		try {
			return boletaRepositorio.findOne(id);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public List<ShvBolBoleta> listarTodasBoletas() throws PersistenciaExcepcion {
		try {
			return boletaRepositorio.findAll();
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ShvBolBoleta actualizarBoleta(ShvBolBoleta boleta) throws PersistenciaExcepcion {
		try {
			ShvBolBoleta boletaBD = boletaRepositorio.save(boleta);
			boletaRepositorio.flush();
			return boletaBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public ShvBolBoletaSimplificado actualizarBoleta(ShvBolBoletaSimplificado boleta) throws PersistenciaExcepcion {
		try {
			ShvBolBoletaSimplificado boletaBD = boletaRepositorioSimplificado.save(boleta);
			boletaRepositorioSimplificado.flush();
			return boletaBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public Long proximoValorNumeroBoleta() throws PersistenciaExcepcion {
		try {
			String query = "select seq_shv_bol_nro_bol.nextval from dual";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			List<Map<String, Object>> lista =  buscarUsandoSQL(qp); 
			return Long.valueOf(lista.get(0).get("NEXTVAL").toString());
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * buscar todas aquellas boletas con valor en estado “Boleta Pendiente de Conciliar” o “Boleta Rechazada”  
	 * con más de dos meses de antigüedad desde su fecha de creación, 
	 * y que no posean un Recibo asociado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvBolBoleta> buscarBoletasPendientesYRechazadasParaDepurar(BoletaSinValorFiltro filtro)
			throws PersistenciaExcepcion {
		List<ShvBolBoleta> lista= new ArrayList<ShvBolBoleta>();
		try {
			String fechaHasta = filtro.getFechaHasta()+ " 23:59:59";
			
			// Boletas sin valor
			String queryBoletaSinValor = "select b from ShvBolBoletaSinValor as bsv "
					+ "join bsv.boleta as b join b.workFlow.shvWfWorkflowEstado as we "
					+ " where b.fechaAlta<=to_timestamp( ? , ? )"
					+ " and we.estado= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(queryBoletaSinValor);
			qp.addParametro(new String(fechaHasta));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(Estado.BOL_PENDIENTE);
			lista = (List<ShvBolBoleta>) buscarUsandoQueryConParametros(qp);
			
			// Boletas con valor
			String queryBoletaDepositoEfectivo = "select b from ShvValBoletaDepositoEfectivo as vbe "
					 + " join vbe.boleta as b join b.workFlow.shvWfWorkflowEstado as weEfe "
					 + " join vbe.valor.workFlow.shvWfWorkflowEstado as weValor "
					 + " where vbe.reciboPreImpreso is null "
					 + " and b.fechaAlta<=to_timestamp( ? , ? )"
					 + " and weEfe.estado= ? and weValor.estado in ( ? , ? )";
			qp = new QueryParametrosUtil(queryBoletaDepositoEfectivo);
			qp.addParametro(new String(fechaHasta));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(Estado.BOL_PENDIENTE);
			qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			qp.addParametro(Estado.VAL_BOLETA_RECHAZADA);
			lista.addAll((List<ShvBolBoleta>) buscarUsandoQueryConParametros(qp));
			
			String queryBoletaDepositoCheque =  "select bc from  ShvValBoletaDepositoCheque as c "
					+ " join c.boleta as bc join bc.workFlow.shvWfWorkflowEstado as weCH "
					+ " join c.valor.workFlow.shvWfWorkflowEstado as weValor "
					+ " where c.reciboPreImpreso is null "
					+ " and bc.fechaAlta<=to_timestamp( ? , ? )"
					+ " and weCH.estado= ? and weValor.estado in ( ? , ? )";
			qp = new QueryParametrosUtil(queryBoletaDepositoCheque);
			qp.addParametro(new String(fechaHasta));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(Estado.BOL_PENDIENTE);
			qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			qp.addParametro(Estado.VAL_BOLETA_RECHAZADA);
			lista.addAll((List<ShvBolBoleta>) buscarUsandoQueryConParametros(qp));
			
			String queryBoletaDepositoChequePagoDiferido = " select bcd from  ShvValBoletaDepositoChequePagoDiferido as d "
					+ " join d.boleta as bcd join bcd.workFlow.shvWfWorkflowEstado as weCHPD "
					+ " join d.valor.workFlow.shvWfWorkflowEstado as weValor "
					+ " where d.reciboPreImpreso is null "
					+ " and bcd.fechaAlta<=to_timestamp( ? , ? )"
					+ " and weCHPD.estado= ? and weValor.estado in ( ? , ? )";
			qp = new QueryParametrosUtil(queryBoletaDepositoChequePagoDiferido);
			qp.addParametro(new String(fechaHasta));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			qp.addParametro(Estado.BOL_PENDIENTE);
			qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			qp.addParametro(Estado.VAL_BOLETA_RECHAZADA);
			lista.addAll((List<ShvBolBoleta>) buscarUsandoQueryConParametros(qp));
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		return lista;
	}
	
	/**
	 * Getters & Setters
	 */
	public BoletaRepositorio getBoletaRepositorio() {
		return boletaRepositorio;
	}

	public void setBoletaRepositorio(BoletaRepositorio boletaRepositorio) {
		this.boletaRepositorio = boletaRepositorio;
	}

	@Override
	public ShvBolBoletaSimplificado buscarBoletaSimplificado(Long idBoleta)
			throws PersistenciaExcepcion {
		try {
				return boletaRepositorioSimplificado.findOne(idBoleta);
				
			} catch (Throwable e) {
				throw new PersistenciaExcepcion(e);
			}
		}

	}

