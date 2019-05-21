package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenShvParamRespWfTarea;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParamRespWfTareaServicio;
import ar.com.telecom.shiva.persistencia.dao.IParamRespWfTareaDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamRespWfTarea;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;

public class ParamRespWfTareaServicioImpl extends Servicio implements IParamRespWfTareaServicio{
	@Autowired
	private IParamRespWfTareaDao paramRespWfTareaDao;
	
//	Busca el responsable de la tarea en la parametrica ShvParamRespWfTarea segun la empresa, sociedad, segmento y sistema
	@Override
	public TipoPerfilEnum buscarPerfil(PerfilFiltro filtro) throws NegocioExcepcion{
		
		TipoPerfilEnum perfil = null;
		List<ShvParamRespWfTarea> listaRespWfTarea = new ArrayList<ShvParamRespWfTarea>();
		try{
				listaRespWfTarea = paramRespWfTareaDao.consultarPerfiles(filtro);
			if (!listaRespWfTarea.isEmpty()){
				Collections.sort(listaRespWfTarea,
						new ComparatorOrdenShvParamRespWfTarea());
				for(ShvParamRespWfTarea respuestaWfTarea : listaRespWfTarea){
					if (Validaciones.isObjectNull(perfil)){
					perfil = buscarRespWfTarea(respuestaWfTarea,  filtro);
					}
				}
			} else {
				String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.perfil.tipo.tarea"),filtro.getTipoTarea());
				Traza.error(getClass(), mensajeError);
				throw new NegocioExcepcion("", null, mensajeError);
			}
		}catch (PersistenciaExcepcion ex) {
			throw new NegocioExcepcion(ex.getMessage(), ex);
		}
		return perfil;
		
	}

// En la parametrica ShvParamRespWfTarea, el * es un comodin el cual por ejemplo si esta en la columna empresa aplicara a todas las empresas.
	public TipoPerfilEnum buscarRespWfTarea(ShvParamRespWfTarea respuestaWfTarea, PerfilFiltro filtro){
		TipoPerfilEnum perfil = null;
		if (filtro.getEmpresa().equals(respuestaWfTarea.getIdEmpresa()) || respuestaWfTarea.getIdEmpresa().equals(Constantes.ASTERISK)){
			if (filtro.getSegmento().equals(respuestaWfTarea.getIdSegmento()) || respuestaWfTarea.getIdSegmento().equals(Constantes.ASTERISK)){
				if (filtro.getSociedad().equals(respuestaWfTarea.getIdSociedad()) || respuestaWfTarea.getIdSociedad().equals(Constantes.ASTERISK)){
					if (filtro.getSistema().equals(respuestaWfTarea.getSistema()) || respuestaWfTarea.getSistema().equals(Constantes.ASTERISK) ){
						if (filtro.getTipoTarea().equals(respuestaWfTarea.getTipoTarea())){
							perfil = respuestaWfTarea.getPerfil();
						}
					}
				}
			}
		}
		return perfil;	
	}
	
	/**
	 * @return the paramRespWfTareaDao
	 */
	public IParamRespWfTareaDao getParamRespWfTareaDao() {
		return paramRespWfTareaDao;
	}

	/**
	 * @param paramRespWfTareaDao the paramRespWfTareaDao to set
	 */
	public void setParamRespWfTareaDao(IParamRespWfTareaDao paramRespWfTareaDao) {
		this.paramRespWfTareaDao = paramRespWfTareaDao;
	}

}
