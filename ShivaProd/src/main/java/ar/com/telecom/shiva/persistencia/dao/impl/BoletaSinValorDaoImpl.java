package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaSinValorDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaConValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSinValorSimple;
import ar.com.telecom.shiva.persistencia.repository.BoletaSinValorRepositorio;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public class BoletaSinValorDaoImpl extends Dao implements IBoletaSinValorDao{

	@Autowired BoletaSinValorRepositorio boletaSinValorRepositorio;
	
	@Autowired
	protected ICombosServicio combosServicio;
	
	public ShvBolBoletaSinValor buscarBoletaSinValor(Long numeroBoleta) throws PersistenciaExcepcion {
		try {
			ShvBolBoletaSinValor boletaSinValor = boletaSinValorRepositorio.findOne(numeroBoleta);  
			return boletaSinValor;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvBolBoletaSinValor> buscarBoletasSinValor(BoletaSinValorFiltro boletaFiltro) throws PersistenciaExcepcion {
		try {
			
			String query = "select bsv from ShvBolBoletaSinValor as bsv "
					+ "join bsv.boleta as b "
					+ "join b.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as we ";
			
			QueryParametrosUtil qp = generarConsulta(query, boletaFiltro);
			List<ShvBolBoletaSinValor> list = (List<ShvBolBoletaSinValor>) buscarUsandoQueryConParametros(qp); 
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvBolBoletaSinValor> buscarBoletasSinValor(BoletaSinValorFiltro boletaFiltro, UsuarioSesion userSesion) throws PersistenciaExcepcion {
		try {
			
			String query = "select bsv from ShvBolBoletaSinValor as bsv "
					+ "join bsv.boleta as b "
					+ "join b.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as we ";
			
			QueryParametrosUtil qp = generarConsulta(query, boletaFiltro);
			List<ShvBolBoletaSinValor> list = (List<ShvBolBoletaSinValor>) buscarUsandoQueryConParametros(qp); 
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvBolBoletaConValor> buscarBoletasConValor(BoletaSinValorFiltro boletaFiltro) throws PersistenciaExcepcion {
		try {
			String query = "select bsv from ShvBolBoletaConValor as bsv "
					+ "join bsv.boleta as b "
					+ "join b.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as we ";
			
			QueryParametrosUtil qp = generarConsulta(query, boletaFiltro);
			List<ShvBolBoletaConValor> list = (List<ShvBolBoletaConValor>) buscarUsandoQueryConParametros(qp); 
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvBolBoletaSinValor buscarBoletaSinValorPorNumeroBoleta( String numeroBoleta) throws PersistenciaExcepcion {
		try {
			String query = "from ShvBolBoleta where numeroBoleta=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(numeroBoleta));
			
			List<ShvBolBoleta> boletas = (List<ShvBolBoleta>)buscarUsandoQueryConParametros(qp); 
			if(Validaciones.isCollectionNotEmpty(boletas)){
				return boletas.get(0).getBoletaSinValor();
			}else{
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public List<ShvBolBoletaSinValor> listarTodasBoletasSinValor() throws PersistenciaExcepcion {
		try {
			return boletaSinValorRepositorio.findAll();
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvBolBoletaSinValorSimple> listarCodigosClienteLegado(String usuarioLogueado) throws PersistenciaExcepcion {
		try {
			String query = "from ShvBolBoletaSinValorSimple where upper(idAnalista)=? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(usuarioLogueado.toUpperCase());
			
			List<ShvBolBoletaSinValorSimple> lista = (List<ShvBolBoletaSinValorSimple>) buscarUsandoQueryConParametros(qp, 0, 100);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Metodo Privado generico 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil generarConsulta(String query, BoletaSinValorFiltro filtro) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		String str = "";
		
		/*   ID BOLETA   */
		if (!Validaciones.isNullOrEmpty(filtro.getIdBoleta())) {
			str += " where b.idBoleta=?";
			qp.addParametro(new Long(filtro.getIdBoleta()));
		}
		
		/*   EMPRESA   */
		if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
			str += obtenerOperadorBusqueda(str) + " bsv.empresa.idEmpresa=?";
			qp.addParametro(filtro.getEmpresa());
		}
		
		/*   SEGMENTO   */
		if (!Validaciones.isNullOrEmpty(filtro.getSegmento())) {
			if (!filtro.getSegmento().equals(Constantes.TODOS_LOS_SEGMENTOS_ID)) {
				str += obtenerOperadorBusqueda(str) + " bsv.segmento.idSegmento=?";
				qp.addParametro(filtro.getSegmento());
			}
		}
		
		/*   FECHA ALTA   */
		if (!Validaciones.isNullOrEmpty(filtro.getFechaDesde())) {
			str += obtenerOperadorBusqueda(str) + " b.fechaAlta>=to_timestamp('" + filtro.getFechaDesde() 
												+ " 00:00:00','" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT + "')";
		}

		if (!Validaciones.isNullOrEmpty(filtro.getFechaHasta())) {
			str += obtenerOperadorBusqueda(str) + " b.fechaAlta<=to_timestamp('" + filtro.getFechaHasta() 
												+ " 23:59:59','" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT+"')";
		}
		
		/*   IMPORTE   */
		if (!Validaciones.isNullOrEmpty(filtro.getImporteDesde())) {
			str += obtenerOperadorBusqueda(str) + " bsv.importe>= ?";
			qp.addParametro(new BigDecimal(filtro.getImporteDesde().replace(".", "").replace(",", ".")));
		}
		if (!Validaciones.isNullOrEmpty(filtro.getImporteHasta())) {
			str += obtenerOperadorBusqueda(str) + " bsv.importe<= ?";
			qp.addParametro(new BigDecimal(filtro.getImporteHasta().replace(".", "").replace(",", ".")));
		}
		
		/*   COPROPIETARIO Y USUARIO MODIFICACION   */
		if (!Constantes.TODOS_LOS_SEGMENTOS_ID.equals(filtro.getSegmento())) {
			if (!Validaciones.isObjectNull(filtro.getUsuarioLogeado()) && !filtro.getUsuarioLogeado().esSupervisor() && !filtro.getUsuarioLogeado().esAdminValor() 
					&& !filtro.getUsuarioLogeado().esPerfilConsulta()) {
				str += obtenerOperadorBusqueda(str) + " (upper(bsv.idCopropietario)=? or  upper(bsv.idAnalista)=?)";
				qp.addParametro(filtro.getUsuarioLogeado().getUsuario().toUpperCase());
				qp.addParametro(filtro.getUsuarioLogeado().getUsuario().toUpperCase());
			}
		}
		
		qp.setSql(query + str);
		return qp;
	}
	
	/**
	 * Retorna la lista de Boletas Sin Valor en estado 'Pendientes de conciliar'
	 */
	@SuppressWarnings("unchecked")
	public List<ShvBolBoleta> listarBoletasSinValorPendientesConciliar() throws PersistenciaExcepcion {
		try {
			String query = "select b from ShvBolBoleta as b, ShvBolBoletaSinValor as bsv "
					+ "join b.workFlow as w "
					+ "join w.shvWfWorkflowEstado as we "
					+ "where we.estado=? and bsv.idBoleta=b.idBoleta "
					+ "order by b.numeroBoleta desc";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.BOL_PENDIENTE);
			
			List<ShvBolBoleta> boletas = (List<ShvBolBoleta>) buscarUsandoQueryConParametros(qp);
			return boletas;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna la lista de Boletas Con Valor en estado 'Pendientes de conciliar', 
	 * filtrando por tipo de valor.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvValValor> listarBoletasConValorPendientesConciliar(TipoValorEnum tipoValor) throws PersistenciaExcepcion {
		try {
			String query = "";
			QueryParametrosUtil qp;
			
			List<ShvValValor> listaValores = new ArrayList<ShvValValor>();
			switch (tipoValor) {
			    case BOLETA_DEPOSITO_CHEQUE:
			    	query = "select v from ShvValBoletaDepositoCheque vch "
			    			+ "join vch.valor as v "
			    			+ "join vch.boleta as b "
			    			+ "join b.workFlow.shvWfWorkflowEstado as weBoleta "
							+ "join v.workFlow.shvWfWorkflowEstado as weValor "
							+ "where weBoleta.estado=? "
							+ "and weValor.estado=? "
							+ " order by vch.boleta.numeroBoleta desc";
			    	
			    	qp = new QueryParametrosUtil(query);
					qp.addParametro( Estado.BOL_PENDIENTE);
					qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			    	listaValores = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			    	break;
			    	
			    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
			    	query = "select v from ShvValBoletaDepositoChequePagoDiferido vchpd "
			    			+ "join vchpd.valor as v "
			    			+ "join vchpd.boleta as b "
			    			+ "join b.workFlow.shvWfWorkflowEstado as weBoleta "
							+ "join v.workFlow.shvWfWorkflowEstado as weValor "
							+ "where weBoleta.estado=? "
							+ "and weValor.estado=? "
							+ " order by vchpd.boleta.numeroBoleta desc";
			    	
			    	qp = new QueryParametrosUtil(query);
					qp.addParametro( Estado.BOL_PENDIENTE);
					qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			    	listaValores = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			    	break;
			    	
			    case BOLETA_DEPOSITO_EFECTIVO:
			    	query = "select v from ShvValBoletaDepositoEfectivo ve "
			    			+ "join ve.valor as v "
			    			+ "join ve.boleta as b "
			    			+ "join b.workFlow.shvWfWorkflowEstado as weBoleta "
							+ "join v.workFlow.shvWfWorkflowEstado as weValor "
							+ "where weBoleta.estado=? "
							+ "and weValor.estado=? "
							+ " order by ve.boleta.numeroBoleta desc";
			    	
			    	qp = new QueryParametrosUtil(query);
					qp.addParametro( Estado.BOL_PENDIENTE);
					qp.addParametro(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION);
			    	listaValores = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			    	break;
			    default:
			}
			
			return listaValores;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Getters & Setters
	 */
	public BoletaSinValorRepositorio getBoletaSinValorRepositorio() {
		return boletaSinValorRepositorio;
	}

	public void setBoletaSinValorRepositorio(
			BoletaSinValorRepositorio boletaSinValorRepositorio) {
		this.boletaSinValorRepositorio = boletaSinValorRepositorio;
	}

	
}
