package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaAcciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReportePerfilesAuditoriaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfil;
import ar.com.telecom.shiva.persistencia.modelo.ShvSegPerfilAplicativo;
import ar.com.telecom.shiva.persistencia.repository.ReportePerfilesAuditoriaRepositorio;

public class ReportePerfilesAuditoriaDaoImpl extends Dao implements IReportePerfilesAuditoriaDao {

	@Autowired
	ReportePerfilesAuditoriaRepositorio reportePerfilesAuditoriaRepositorio;

	/**
	 * Retorna las acciones disponibles en shiva
	 */
	@Override
	public List<ResultadoBusquedaAcciones> listarAcciones() {

		QueryParametrosUtil qp = new QueryParametrosUtil();
		List<ResultadoBusquedaAcciones> listaResultado = new ArrayList<ResultadoBusquedaAcciones>();
		try {
			String query = "SELECT DISTINCT ID_ACCION AS ID_ACCION, DESCRIPCION_ACCION AS DESCRIPCION_ACCION FROM SHV_SEG_ACCION";

			qp.setSql(query);
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

			if (Validaciones.isCollectionNotEmpty(listaResultadoQuery)) {

				for (Map<String, Object> registro : listaResultadoQuery) {

					ResultadoBusquedaAcciones resultado = new ResultadoBusquedaAcciones();

					if (!Validaciones.isObjectNull(registro.get("ID_ACCION"))) {

						resultado.setIdAccion(Long.valueOf(registro.get("ID_ACCION").toString()));

					}

					if (!Validaciones.isObjectNull(registro.get("DESCRIPCION_ACCION"))) {

						resultado.setDescripcionAccion(registro.get("DESCRIPCION_ACCION").toString());

					}

					listaResultado.add(resultado);

				}
			}

		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaResultado;
	}

	@Override
	public ShvArcArchivo insertarArcArchivo(ShvArcArchivo archivo) throws PersistenciaExcepcion {
		try {
			ShvArcArchivo modeloNuevo = reportePerfilesAuditoriaRepositorio.save(archivo);
			reportePerfilesAuditoriaRepositorio.flush();
			return modeloNuevo;

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvSegPerfil> buscarSegPerfil() {

		QueryParametrosUtil qp = new QueryParametrosUtil("from ShvSegPerfil");
		List<ShvSegPerfil> listaPerfil = new ArrayList<ShvSegPerfil>();
		try {
			listaPerfil = (List<ShvSegPerfil>) buscarUsandoQueryConParametros(qp);

		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPerfil;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvSegPerfilAplicativo> buscarSegPerfilAplicativo() {
		QueryParametrosUtil qp = new QueryParametrosUtil("from ShvSegPerfilAplicativo");
		List<ShvSegPerfilAplicativo> listaPerfil = new ArrayList<ShvSegPerfilAplicativo>();
		try {
			listaPerfil = (List<ShvSegPerfilAplicativo>) buscarUsandoQueryConParametros(qp);

		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPerfil;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShvSegPerfil buscarPerfil(String perfil) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvSegPerfil where nombre=?");
			qp.addParametro(perfil);
			List<ShvSegPerfil> list = (List<ShvSegPerfil>) buscarUsandoQueryConParametros(qp);

			return (list.size() > 0) ? list.get(0) : null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ResultadoBusquedaAcciones> buscarAccionesPorPerfil(TipoPerfilEnum tipoPerfil, String empresa, String segmento) {
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		List<ResultadoBusquedaAcciones> listaResultado = new ArrayList<ResultadoBusquedaAcciones>();
		try {
			String query = "select distinct accs.id_perfil_aplicativo as id_perfil_aplicativo ,accs.id_accion as id_accion, perfil.id_perfil as id_perfil from shv_seg_accion accs join shv_seg_perfil_aplicativo aplicativo on aplicativo.id_perfil_aplicativo = accs.id_perfil_aplicativo inner join shv_seg_perfil perfil on perfil.id_perfil_aplicativo = aplicativo.id_perfil_aplicativo  where aplicativo.descripcion = ?";

			parametros.addCampoAlQuery(tipoPerfil.descripcion(), Types.VARCHAR);

			/*      */
			if (!Validaciones.isNullOrEmpty(empresa)) {
				query += " and perfil.id_empresa = ? ";
				parametros.addCampoAlQuery(empresa, Types.VARCHAR);
			}
			if (!Validaciones.isNullOrEmpty(segmento)) {
				query += "and perfil.id_segmento = ?";
				parametros.addCampoAlQuery(segmento, Types.VARCHAR);
			}
			parametros.setSql(query);

			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

			if (Validaciones.isCollectionNotEmpty(listaResultadoQuery)) {

				for (Map<String, Object> registro : listaResultadoQuery) {

					ResultadoBusquedaAcciones resultado = new ResultadoBusquedaAcciones();

					if (!Validaciones.isObjectNull(registro.get("ID_PERFIL_APLICATIVO"))) {

						resultado.setIdPerfilAplicativo(registro.get("ID_PERFIL_APLICATIVO").toString());

					}

					if (!Validaciones.isObjectNull(registro.get("ID_ACCION"))) {

						resultado.setIdAccion(Long.valueOf(registro.get("ID_ACCION").toString()));

					}

					if (!Validaciones.isObjectNull(registro.get("ID_PERFIl"))) {

						resultado.setIdPerfil(registro.get("ID_PERFIl").toString());

					}

					listaResultado.add(resultado);

				}
			}
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaResultado;
	}

	@Override
	public String buscarValor(String valor) {
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		String resultado = new String();
		try {
			String query = "select perfil_tu_id as perfil_tu_id from (select decode (ssp.id_segmento, null, decode(ssp.id_perfil_aplicativo,8,'SHIVA_PROD_'||upper(sspa.descripcion)||'_'||ssp.id_empresa, 'SHIVA_PROD_'||upper(sspa.descripcion)), 'SHIVA_PROD_'||upper(sspa.descripcion)||'_'||ssp.id_empresa||'_'||ssp.id_segmento) ";
			query += "perfil_tu_id from shv_seg_perfil ssp,shv_seg_perfil_aplicativo sspa"; 
			query += " where ssp.id_perfil_aplicativo = sspa.id_perfil_aplicativo)";
			query += " where perfil_tu_id = ? ";

			parametros.addCampoAlQuery(valor.toUpperCase(), Types.VARCHAR);

			parametros.setSql(query);

			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);

			if (Validaciones.isCollectionNotEmpty(listaResultadoQuery)) {

				for (Map<String, Object> registro : listaResultadoQuery) {

					if (!Validaciones.isObjectNull(registro.get("PERFIL_TU_ID"))) {

					 resultado = (registro.get("PERFIL_TU_ID").toString());

					}

				}
			}
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
