package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCobroHiloEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCantidadHilosEnCurso;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IProcesamientoHilosCobrosDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;
import ar.com.telecom.shiva.persistencia.repository.ProcesamientoHilosCobrosRepositorio;

public class ProcesamientoHilosCobrosDaoImpl extends Dao implements IProcesamientoHilosCobrosDao{

	@Autowired
	ProcesamientoHilosCobrosRepositorio procesamientoHilosCobrosRepositorio;
	
	public List<ResultadoBusquedaCantidadHilosEnCurso> obtenerCantidadHilosEnCurso(EstadoProcesamientoHilosEnum estado, TipoImputacionEnum tipoImputacion) throws PersistenciaExcepcion {
		
		StringBuffer query = new StringBuffer();
		
		query.append("select count(*) as cantidad_Hilos_En_Curso, tipo_hilo_por_cant_trans  from shv_cob_proc_hilos_cobros ");
		query.append("where estado =? and proceso_batch=? GROUP BY tipo_hilo_por_cant_trans ORDER BY tipo_hilo_por_cant_trans DESC ");
		
		QueryParametrosUtil parametros = new QueryParametrosUtil(query.toString());
		parametros.addCampoAlQuery(estado, Types.VARCHAR);
		parametros.addCampoAlQuery(tipoImputacion, Types.VARCHAR);
		
		List<Map<String, Object>> listaResultadoQuery;
		List<ResultadoBusquedaCantidadHilosEnCurso> listaResultado = new ArrayList<ResultadoBusquedaCantidadHilosEnCurso>();
		
		try {
		
			listaResultadoQuery = buscarUsandoSQL(parametros);
			
			for (Map<String, Object> archivo : listaResultadoQuery) {
				
				ResultadoBusquedaCantidadHilosEnCurso resultado = new ResultadoBusquedaCantidadHilosEnCurso();
				
				if (!Validaciones.isObjectNull(archivo.get("cantidad_Hilos_En_Curso"))){
					resultado.setCantidadHilosEnCurso(Integer.valueOf(archivo.get("cantidad_Hilos_En_Curso").toString()));
				}
				
				if (!Validaciones.isObjectNull(archivo.get("tipo_hilo_por_cant_trans"))){
					resultado.setTipoHilo(TipoCobroHiloEnum.getEnumByName((archivo.get("tipo_hilo_por_cant_trans").toString())));
				}
				
				listaResultado.add(resultado);
			}
			
	
		} catch (PersistenciaExcepcion e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
			
		return listaResultado;
		
	}

	
	public ShvCobProcHilosCobros insertarActualizarHilo(ShvCobProcHilosCobros hilo) throws PersistenciaExcepcion {
		try{
			ShvCobProcHilosCobros hiloDB = procesamientoHilosCobrosRepositorio.save(hilo);
			procesamientoHilosCobrosRepositorio.flush();
			return hiloDB;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}
	}

}
