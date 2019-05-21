package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaArchivoAVC;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;

public class ArchivoAVCMapeador extends Mapeador {

	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcArchivoAvc modelo = (ShvAvcArchivoAvc) vo;
		ArchivoAVCDto archivoDto = new ArchivoAVCDto();
		
		try {
			archivoDto.setId(modelo.getIdArchivosAvc());
			archivoDto.setNroAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
			archivoDto.setFechaProcesamiento(modelo.getFechaProcesamiento());
			archivoDto.setLogProcesamiento(modelo.getLogProcesamiento());
			archivoDto.setNombreArchivo(modelo.getNombreArchivo());
			archivoDto.setUsuarioProcesamiento(modelo.getUsuarioProcesamiento());
			archivoDto.setArchivoAdjunto(modelo.getDocumentoAdjunto().getArchivoAdjunto());
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return archivoDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ArchivoAVCDto archivoAVC = (ArchivoAVCDto) dto;
		
		ShvAvcArchivoAvc archivosAvcModelo = (ShvAvcArchivoAvc)
				(vo != null ? vo : new ShvAvcArchivoAvc());
		try {
			archivosAvcModelo.setAcuerdo(acuerdoDao.buscarAcuerdo(archivoAVC.getNroAcuerdo()));
			archivosAvcModelo.setFechaProcesamiento(archivoAVC.getFechaProcesamiento());
			archivosAvcModelo.setLogProcesamiento(archivoAVC.getLogProcesamiento());
			archivosAvcModelo.setNombreArchivo(archivoAVC.getNombreArchivo());
			archivosAvcModelo.setUsuarioProcesamiento(archivoAVC.getUsuarioProcesamiento());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return archivosAvcModelo;
	}
	
	public DTO map(ResultadoBusquedaArchivoAVC res) throws NegocioExcepcion {
		
		ArchivoAVCDto archivoDto = new ArchivoAVCDto();
		
		try {
			archivoDto.setId(res.getIdArchivosAvc());
			archivoDto.setNroAcuerdo(res.getNroAcuerdo());
			archivoDto.setFechaProcesamiento(res.getFechaProcesamiento());
			archivoDto.setNombreArchivo(res.getNombreArchivo());
			archivoDto.setRegistrosProcesados(res.getRegistrosProcesados());
			archivoDto.setRegistrosConciliados(res.getRegistrosConciliados());
			archivoDto.setRegistrosConciliacionSugerida(res.getRegistrosConciliacionSugerida());
			archivoDto.setRegistrosSinConciliar(res.getRegistrosSinConciliar());
			archivoDto.setUsuarioProcesamiento(res.getUsuarioProcesamiento());
			archivoDto.setRegistrosAnulados(res.getRegistrosAnulados());
			archivoDto.setLogProcesamiento(res.getLogProcesamiento());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return archivoDto;
	}

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

}
