package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaArchivoDto;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;

public class ArchivoOperacionMasivaMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasOperacionMasivaArchivo modelo = (ShvMasOperacionMasivaArchivo) vo;
		OperacionMasivaArchivoDto operacionMasivaArchivoDto = new OperacionMasivaArchivoDto();
		
		try {
			operacionMasivaArchivoDto.setIdOperacionMasivaArchivo(modelo.getIdOperacionMasivaArchivo());
			operacionMasivaArchivoDto.setLogProcesamiento(modelo.getLogProcesamiento());
			operacionMasivaArchivoDto.setNombreArchivo(modelo.getNombreArchivo());
			operacionMasivaArchivoDto.setCantidadRegistros(modelo.getCantidadRegistros());
			operacionMasivaArchivoDto.setImporteTotal(modelo.getImporteTotal());
			operacionMasivaArchivoDto.setFechaDerivacion(modelo.getFechaDerivacion());
			operacionMasivaArchivoDto.setFechaAutorizacion(modelo.getFechaAutorizacion());
			operacionMasivaArchivoDto.setFechaProceso(modelo.getFechaProceso());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return operacionMasivaArchivoDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		OperacionMasivaArchivoDto operacionMasivaArchivoDto = (OperacionMasivaArchivoDto) dto;
		
		ShvMasOperacionMasivaArchivo archivoOperacionMasivaModelo = (ShvMasOperacionMasivaArchivo)
				(vo != null ? vo : new ShvMasOperacionMasivaArchivo());
		try {
			archivoOperacionMasivaModelo.setIdOperacionMasivaArchivo(operacionMasivaArchivoDto.getIdOperacionMasivaArchivo());
			archivoOperacionMasivaModelo.setLogProcesamiento(operacionMasivaArchivoDto.getLogProcesamiento());
			archivoOperacionMasivaModelo.setNombreArchivo(operacionMasivaArchivoDto.getNombreArchivo());
			archivoOperacionMasivaModelo.setCantidadRegistros(operacionMasivaArchivoDto.getCantidadRegistros());
			archivoOperacionMasivaModelo.setImporteTotal(operacionMasivaArchivoDto.getImporteTotal());
			archivoOperacionMasivaModelo.setFechaDerivacion(operacionMasivaArchivoDto.getFechaDerivacion());
			archivoOperacionMasivaModelo.setFechaAutorizacion(operacionMasivaArchivoDto.getFechaAutorizacion());
			archivoOperacionMasivaModelo.setFechaProceso(operacionMasivaArchivoDto.getFechaProceso());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return archivoOperacionMasivaModelo;
	}

}
