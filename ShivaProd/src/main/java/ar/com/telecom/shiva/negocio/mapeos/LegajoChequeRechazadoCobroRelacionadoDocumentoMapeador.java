package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleDocumento;
import ar.com.telecom.shiva.presentacion.bean.dto.VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;

public class LegajoChequeRechazadoCobroRelacionadoDocumentoMapeador extends Mapeador {


	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	
	public DTO map(Bean be) throws NegocioExcepcion {
		VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto dto = new VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto();
		VistaSoporteResultadoBusquedaLegajoDetalleDocumento bean = (VistaSoporteResultadoBusquedaLegajoDetalleDocumento) be;

		if (!Validaciones.isNullEmptyOrDash(bean.getTipoComprobante())) {
			dto.setTipoDocumento(bean.getTipoComprobante());
		}

		if (!Validaciones.isObjectNull(bean.getMonedaDocumento())) {
			dto.setMonedaDocumento(bean.getMonedaDocumento().getSigno2());
		}
		
		if (!Validaciones.isObjectNull(bean.getNumeroReferenciaMic())) {
			dto.setNumeroReferencia(bean.getNumeroReferenciaMic().toString());
		}

		if (!Validaciones.isObjectNull(bean.getNumeroLegal())) {
			dto.setNumeroLegal(bean.getNumeroLegal());
		}

		if (!Validaciones.isObjectNull(bean.getSistemaImputacion())) {
			dto.setSistemaOrigenEnum(bean.getSistemaImputacion());
			dto.setSistemaOrigenDescripcion(bean.getSistemaImputacion().getDescripcion());
		}

		
		if (!Validaciones.isObjectNull(bean.getImporteAplicadoDelCheque())) {
			dto.setImporteAplicadoDelCheque(Utilidad.formatCurrency(bean.getImporteAplicadoDelCheque(), true, true, 2));
		}

		if (!Validaciones.isObjectNull(bean.getSaldoDocumentoLuegoAplicarCheque())) {
			dto.setSaldoDocumentoLuegoAplicarCheque(Utilidad.formatCurrency(bean.getSaldoDocumentoLuegoAplicarCheque(), true, true, 2));
		}

		if (!Validaciones.isObjectNull(bean.getSaldoActualDocumento())) {
			dto.setSaldoActualDocumento(Utilidad.formatCurrency(bean.getSaldoActualDocumento(), true, true, 2));
		}

		if (!Validaciones.isObjectNull(bean.getImporteTotalDocumento())) {
			dto.setImporteTotalDocumento(Utilidad.formatCurrency(bean.getImporteTotalDocumento(), true, true, 2));
		}

		if (!Validaciones.isObjectNull(bean.getFechaConsultaSaldo())) {
			dto.setFechaConsultaSaldo(Utilidad.formatDateAndTime(bean.getFechaConsultaSaldo()));
		}
		
		if (!Validaciones.isObjectNull(bean.getIdClienteLegado())) {
			dto.setClienteLegado(String.valueOf(bean.getIdClienteLegado()));
		}
		
		return dto;
	}
	
	public Modelo map(Bean be, Modelo mo) throws NegocioExcepcion {
		ShvLgjChequeRechazadoDetalleDocumento shvLgjChequeRechazadoDetalleDocumento = null;
		VistaSoporteResultadoBusquedaLegajoDetalleDocumento vistaSoporteResultadoBusquedaLegajoDetalleDocumento = (VistaSoporteResultadoBusquedaLegajoDetalleDocumento) be;

		if (Validaciones.isObjectNull(mo)) {
			shvLgjChequeRechazadoDetalleDocumento = new ShvLgjChequeRechazadoDetalleDocumento();
		} else {
			shvLgjChequeRechazadoDetalleDocumento = (ShvLgjChequeRechazadoDetalleDocumento) mo;
		}
		

		//shvLgjChequeRechazadoDetalleDocumento.setNumeroDocumentoLegal(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getNumeroLegal());

		shvLgjChequeRechazadoDetalleDocumento.setNumeroLegal(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getNumeroLegal());

		// Solo para comprobantes tipo 
		shvLgjChequeRechazadoDetalleDocumento.setTipoComprobante(
			TipoComprobanteEnum.getEnumByDescripcion(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getTipoComprobante())
		);

		shvLgjChequeRechazadoDetalleDocumento.setIdClienteLegado(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getIdClienteLegado());

		

		if (!Validaciones.isObjectNull(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getNumeroReferenciaMic())) {
			shvLgjChequeRechazadoDetalleDocumento.setNumeroReferenciaMic(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getNumeroReferenciaMic().toString());
		}

		if (SistemaEnum.ICE.equals(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getSistemaOrigen())) {
			shvLgjChequeRechazadoDetalleDocumento.setMonedaDocumento(MonedaEnum.PES);
		} else {
			shvLgjChequeRechazadoDetalleDocumento.setMonedaDocumento(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getMonedaDocumento());
		}
		shvLgjChequeRechazadoDetalleDocumento.setSistemaOrigen(vistaSoporteResultadoBusquedaLegajoDetalleDocumento.getSistemaImputacion());

		return shvLgjChequeRechazadoDetalleDocumento;
	}
}
