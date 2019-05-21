package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaAdjuntoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaAdjunto;
import ar.com.telecom.shiva.persistencia.repository.OperacionMasivaAdjuntoRepositorio;

public class OperacionMasivaAdjuntoDaoImpl extends Dao implements
		IOperacionMasivaAdjuntoDao {

	@Autowired
	private OperacionMasivaAdjuntoRepositorio repositorioOperacionMasivaAdjunto;
	
	@Override
	public ShvMasOperacionMasivaAdjunto crear(ShvMasOperacionMasivaAdjunto operacionMasivaAdjunto) throws PersistenciaExcepcion {
		
		ShvMasOperacionMasivaAdjunto adjunto = (ShvMasOperacionMasivaAdjunto) repositorioOperacionMasivaAdjunto.save(operacionMasivaAdjunto);
		repositorioOperacionMasivaAdjunto.flush();
		return adjunto;
	}

	@Override
	public List<ShvDocDocumentoAdjunto> buscarComprobantesxIdOperacionMasiva(
			Long idOperacionMasiva) throws PersistenciaExcepcion {
		
		try {
			List<ShvDocDocumentoAdjunto> list = new ArrayList<ShvDocDocumentoAdjunto>();
			
			List<ShvMasOperacionMasivaAdjunto> lista = 
					repositorioOperacionMasivaAdjunto.buscarComprobantesxIdOperacionMasiva(idOperacionMasiva);
			
			if(Validaciones.isCollectionNotEmpty(lista)){
				for (ShvMasOperacionMasivaAdjunto operacionMasivaAdjunto : lista) {
					list.add(operacionMasivaAdjunto.getOperacionMasivaAdjuntoPk().getArchivoAdjunto());
				}
			}
			return list;
	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}		
	}
	
	public OperacionMasivaAdjuntoRepositorio getRepositorioOperacionMasivaAdjunto() {
		return repositorioOperacionMasivaAdjunto;
	}

	public void setRepositorioOperacionMasivaAdjunto(
			OperacionMasivaAdjuntoRepositorio repositorioOperacionMasivaAdjunto) {
		this.repositorioOperacionMasivaAdjunto = repositorioOperacionMasivaAdjunto;
	}

	

}
