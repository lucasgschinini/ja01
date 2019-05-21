package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.persistencia.repository.ReciboPreImpresoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.TalonarioRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;

public class TalonarioDaoImpl extends Dao implements ITalonarioDao {
	
	@Autowired TalonarioRepositorio talonarioRepositorio;
	
	@Autowired ReciboPreImpresoRepositorio reciboPreImpresoRepositorio;
	
	
	public Boolean verificacionRango (String desde, String hasta, String nroSerie)  throws PersistenciaExcepcion{
		
		String hsql = "SELECT count(rt) from ShvTalTalonario rt where "
				+  " rt.nroSerie = ? AND ( "
				+ "((? <= rt.rangoDesde) and (? >= rt.rangoDesde)) "
				+ "OR ((? >= rt.rangoDesde) and (? <= rt.rangoHasta)) "
				+ "OR ((? >= rt.rangoHasta) and (? <= rt.rangoHasta)))";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(hsql);
		qp.addParametro(Integer.valueOf(nroSerie));
		qp.addParametro(Integer.valueOf(desde));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(desde));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(desde));
		
		return verificacionRango(qp);
	}
	public Boolean verificacionRango (String desde, String hasta, String idTalonario, String nroSerie)  throws PersistenciaExcepcion{
		
		String hsql = "SELECT count(rt) from ShvTalTalonario rt where rt.idTalonario <> ? AND rt.nroSerie = ? AND"
				+ "(((? <= rt.rangoDesde) and (? >= rt.rangoDesde)) "
				+ "OR ((? >= rt.rangoDesde) and (? <= rt.rangoHasta)) "
				+ "OR ((? >= rt.rangoHasta) and (? <= rt.rangoHasta)))";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(hsql);
		qp.addParametro(new Integer(idTalonario));
		qp.addParametro(Integer.valueOf(nroSerie));
		qp.addParametro(Integer.valueOf(desde));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(desde));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(hasta));
		qp.addParametro(Integer.valueOf(desde));
		
		return verificacionRango(qp);
	}
	
	@SuppressWarnings("unchecked")
	private Boolean verificacionRango(QueryParametrosUtil qp) throws PersistenciaExcepcion{
		Boolean resp = false;
		try {
			List<Long> resultado = (List<Long>) buscarUsandoQueryConParametros(qp);
			if (resultado.get(0)== 0){
				resp = true;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ShvTalTalonario> listarTalonarios(TalonarioFiltro talonarioFiltro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = generarConsulta(talonarioFiltro);
			List<ShvTalTalonario> list = (List<ShvTalTalonario>) buscarUsandoQueryConParametros(qp); 
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvTalReciboPreImpreso> listarRecibos(String idTalonario) throws PersistenciaExcepcion {
		try {
			String query = "from ShvTalReciboPreImpreso where talonario.idTalonario=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Integer(idTalonario));
			
			List<ShvTalReciboPreImpreso> list = (List<ShvTalReciboPreImpreso>) buscarUsandoQueryConParametros(qp); 
			return list;			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public ShvTalTalonario buscarTalonario(Integer id)	throws PersistenciaExcepcion {
		try {
			return talonarioRepositorio.findOne(id);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		
	}


	public ShvTalTalonario insertarTalonario(ShvTalTalonario talonario) throws PersistenciaExcepcion {
		try{
			ShvTalTalonario talonarioBD = talonarioRepositorio.save(talonario);
			talonarioRepositorio.flush();
			return talonarioBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	public TalonarioRepositorio getTalonarioRepositorio() {
		return talonarioRepositorio;
	}

	public void setTalonarioRepositorio(TalonarioRepositorio talonarioRepositorio) {
		this.talonarioRepositorio = talonarioRepositorio;
	}

	/**
	 * Metodo Privado generico 
	 * @param filtro
	 * @return
	 */
	private QueryParametrosUtil generarConsulta(TalonarioFiltro filtro) throws PersistenciaExcepcion {
		QueryParametrosUtil qp = new QueryParametrosUtil();
		
		String str = "";
		
		/*  Id Talonario   */
		if(!Validaciones.isNullOrEmpty(filtro.getIdTalonarioBandeja())){
			str += obtenerOperadorBusqueda(str) + "tal.idTalonario=? ";
			qp.addParametro(new Integer(filtro.getIdTalonarioBandeja()));
		}
		
		/*   EMPRESA   */
		if(!Validaciones.isNullOrEmpty(filtro.getEmpresa())){
			str += obtenerOperadorBusqueda(str)+"tal.empresa.idEmpresa=? ";
			qp.addParametro(filtro.getEmpresa());
		}
		
		/*   SEGMENTO   */
		if(!Validaciones.isNullOrEmpty(filtro.getSegmento())){
			if(!filtro.getSegmento().equals(Constantes.TODOS_LOS_SEGMENTOS_ID)){
				str += obtenerOperadorBusqueda(str)+"tal.segmento.idSegmento=? ";
				qp.addParametro(filtro.getSegmento());
			}
		}
		
		/* NRO SERIE */
		if(!Validaciones.isNullOrEmpty(filtro.getNroSerie())){
			str += obtenerOperadorBusqueda(str)+"tal.nroSerie=? ";
			qp.addParametro(new Integer(filtro.getNroSerie()));
		}
		
		
		/*   RANGO   */
		if(!Validaciones.isNullOrEmpty(filtro.getRangoDesde())){
			str += obtenerOperadorBusqueda(str)+ "('" + filtro.getRangoDesde().replace(".", "").replace(",", ".") + "' <= tal.rangoHasta)";
		}
		
		if(!Validaciones.isNullOrEmpty(filtro.getRangoHasta())){
			str += obtenerOperadorBusqueda(str)+ "('" + filtro.getRangoHasta().replace(".", "").replace(",", ".") + "' >= tal.rangoDesde)";
		}
		
		/*   ESTADO   */
		if(!Validaciones.isNullOrEmpty(filtro.getIdEstado())){
			str += obtenerOperadorBusqueda(str)+ "we.estado=? ";
			qp.addParametro(Estado.getEnumByName(filtro.getIdEstado()));
		}
		
		String query = "select tal from ShvTalTalonario as tal "
				+ "join tal.workflow as w  "
				+ "join w.shvWfWorkflowEstado as we " + str;  
		qp.setSql(query);
		
		return qp;
	}

	@Override
	public ShvTalTalonario actualizarTalonario(ShvTalTalonario talonario)
			throws PersistenciaExcepcion {
		try {
			ShvTalTalonario talonarioBD = talonarioRepositorio.save(talonario);
			talonarioRepositorio.flush();
			return talonarioBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
		
}
