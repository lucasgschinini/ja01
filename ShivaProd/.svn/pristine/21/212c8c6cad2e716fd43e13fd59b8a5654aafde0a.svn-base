package ar.com.telecom.shiva.negocio.mapeos;

import java.text.ParseException;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;

public class CobroOnlineCodigoOperacionExternaMapeador extends Mapeador {
	
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdCodigoOperacionExterna modelo = (ShvCobEdCodigoOperacionExterna) vo;
		
		
		CodigoOperacionExternaDto codDto = new CodigoOperacionExternaDto();
		
		if (Validaciones.isCollectionNotEmpty(modelo.getCobro().getCodigosOperacionesExternas())) {
			
			CobroDto cobro = new CobroDto();
			String signoImporte = Validaciones.isObjectNull(cobro.getMonedaOperacion())? MonedaEnum.PES.getSigno2() : cobro.getMonedaOperacion();
			String idTransaccion = Validaciones.isObjectNull(modelo.getNumeroTransaccionFormateado()) ? null : modelo.getNumeroTransaccionFormateado().substring(modelo.getNumeroTransaccionFormateado().length() - 1);
	
			
			codDto.setIdCobCobroCodOperExt(modelo.getIdCobEdCodigoOperacionExterna());
			codDto.setIdCobro(modelo.getIdCobEdCodigoOperacionExterna().toString());
			codDto.setCodigoOperacionExterna(Validaciones.isObjectNull(modelo.getCodigoOperacionExterna()) ? null : modelo.getCodigoOperacionExterna());
			codDto.setReferencia(Validaciones.isObjectNull(modelo.getReferente()) ? null : modelo.getReferente());
			codDto.setNroTransaccion(Validaciones.isObjectNull(modelo.getNumeroTransaccionFormateado()) ? null : modelo.getNumeroTransaccionFormateado());
			codDto.setImporte(Validaciones.isObjectNull(modelo.getImporte()) ? null : signoImporte + Utilidad.formatCurrency(modelo.getImporte(), false,true).toString());
			codDto.setSistema(Validaciones.isObjectNull(modelo.getSistema()) ? null : modelo.getSistema());
			codDto.setIdTransaccion(Validaciones.isObjectNull(modelo.getIdTransaccion()) ? idTransaccion : modelo.getIdTransaccion().toString());
			codDto.setMonedaImporte(Validaciones.isObjectNull(modelo.getMonedaImporte()) ? null : modelo.getMonedaImporte().toString());
			codDto.setGrupo(Validaciones.isObjectNull(modelo.getGrupo()) ? null : modelo.getGrupo());
			codDto.setResponsableImputacion(Validaciones.isObjectNull(modelo.getNombreApellidoReferenteAprobador()) ? null : modelo.getNombreApellidoReferenteAprobador());
			codDto.setFechaImputacion(Validaciones.isObjectNull(modelo.getFechaAprobacion()) ? null : Utilidad.formatDateGeneral(modelo.getFechaAprobacion(), Utilidad.DATE_TIME_FORMAT));
		}
		
		return codDto;
	}
	
	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CodigoOperacionExternaDto codigoOperacionExternaDto = (CodigoOperacionExternaDto) dto;
		
		ShvCobEdCodigoOperacionExterna modelo = (ShvCobEdCodigoOperacionExterna)
				(vo != null ? vo : new ShvCobEdCodigoOperacionExterna());
		
		if (!Validaciones.isNullOrEmpty(codigoOperacionExternaDto.getCodigoOperacionExterna())) {
			modelo.setCodigoOperacionExterna(codigoOperacionExternaDto.getCodigoOperacionExterna());
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getIdCobCobroCodOperExt())) {
			modelo.setIdCobEdCodigoOperacionExterna(codigoOperacionExternaDto.getIdCobCobroCodOperExt());
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getNroTransaccion())) {
			modelo.setNumeroTransaccionFormateado(codigoOperacionExternaDto.getNroTransaccion());
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getSistema())) {
			modelo.setSistema(codigoOperacionExternaDto.getSistema());
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getReferencia())) {
			modelo.setReferente(codigoOperacionExternaDto.getReferencia());
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getImporte())) {
			modelo.setImporte(Utilidad.stringToBigDecimal(codigoOperacionExternaDto.getImporte()));
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getMonedaImporte())) {
			modelo.setMonedaImporte(MonedaEnum.getEnumByName(codigoOperacionExternaDto.getMonedaImporte()));
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getIdTransaccion())) {
			modelo.setIdTransaccion(Integer.parseInt(codigoOperacionExternaDto.getIdTransaccion()));
		}
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getGrupo())) {
			modelo.setGrupo(codigoOperacionExternaDto.getGrupo());
		}
		
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getResponsableImputacion())) {
			modelo.setNombreApellidoReferenteAprobador(codigoOperacionExternaDto.getResponsableImputacion());
		}
		
		if (!Validaciones.isObjectNull(codigoOperacionExternaDto.getFechaImputacion())) {
			try {
				modelo.setFechaAprobacion(Utilidad.parseDatePickerString(codigoOperacionExternaDto.getFechaImputacion()));
			} catch (ParseException e) {
				e.getMessage();
			}
		}
		
	    return modelo;
	}
}
