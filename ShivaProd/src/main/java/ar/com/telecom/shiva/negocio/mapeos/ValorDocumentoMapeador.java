package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;

public class ValorDocumentoMapeador extends Mapeador {

	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ComprobanteDto comprobanteDto = (ComprobanteDto) dto;

		ShvDocDocumentoAdjunto documentoModelo = (ShvDocDocumentoAdjunto) (vo != null ? vo
				: new ShvDocDocumentoAdjunto());
		try {
			
			documentoModelo.setNombreArchivo(comprobanteDto.getNombreArchivo());
			documentoModelo.setDescripcion(comprobanteDto.getDescripcionArchivo());
			documentoModelo.setArchivoAdjunto(comprobanteDto.getDocumento());
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return documentoModelo;
	}

}
