package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamParametro;
import ar.com.telecom.shiva.persistencia.repository.ParametroRepositorio;

public class ParametroDaoImpl extends Dao implements IParametroDao  {
	
	@Autowired
	ParametroRepositorio parametroRepositorio;
	
	@SuppressWarnings("unchecked")
	public String getValorTexto(String clave) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamParametro where clave=?");
			qp.addParametro(clave);
			
			List<ShvParamParametro> list = (List<ShvParamParametro>) buscarUsandoQueryConParametros(qp);
			
			if (!list.isEmpty()) {
				return list.get(0).getValorTexto();
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Long getValorNumerico(String clave) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamParametro where clave=?");
			qp.addParametro(clave);
			
			List<ShvParamParametro> list = (List<ShvParamParametro>) buscarUsandoQueryConParametros(qp);
			
			if (!list.isEmpty()) {
				return list.get(0).getValorNumerico();
			} else {
				return null;
			}	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void setValorTexto(String clave, String valorTexto) throws PersistenciaExcepcion {
		try{
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamParametro where clave=?");
			qp.addParametro(clave);
			
			List<ShvParamParametro> list = (List<ShvParamParametro>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(list)){
				ShvParamParametro parametro = list.get(0);
				parametro.setValorTexto(valorTexto);
				parametroRepositorio.save(parametro);
				parametroRepositorio.flush();
			}
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
}
