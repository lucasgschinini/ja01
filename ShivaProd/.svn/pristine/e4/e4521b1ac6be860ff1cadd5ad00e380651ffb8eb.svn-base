package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;

public class CobroOnlineDocumentoAdjuntoMapeador extends Mapeador implements ICobroOnlineDocumentoAdjuntoMapeador {
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		
		ComprobanteDto comprobante = (ComprobanteDto) dto;
		
		ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
		docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
		docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
		docAdjunto.setFechaCreacion(new Date());
		docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
		docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
		docAdjunto.setIdValor(new ArrayList<ShvValValor>());
		
		//docAdjunto = documentoAdjuntoDao.crear(docAdjunto);
				
		ShvCobEdCobroAdjuntoPk cobroAdjuntoPK = new ShvCobEdCobroAdjuntoPk();
		cobroAdjuntoPK.setDocumentoAdjunto(docAdjunto);
//		cobroAdjuntoPK.setIdCobro(idCobro);
		cobroAdjuntoPK.setMotivoAdjunto(MotivoAdjuntoEnum.getEnumByName(comprobante.getMotivoAdjunto()));

		ShvCobEdCobroAdjunto cobroAdjunto = new ShvCobEdCobroAdjunto();
		cobroAdjunto.setPk(cobroAdjuntoPK);
		
//		cobroAdjunto = cobroOnlineDao.insertarDocumentoAjunto(cobroAdjunto);
		
		comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());		

		return cobroAdjunto;
	}
}