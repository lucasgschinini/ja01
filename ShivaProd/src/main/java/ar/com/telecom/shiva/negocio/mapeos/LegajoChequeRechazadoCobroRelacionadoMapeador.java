package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturacionIceEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoCobroRelacionadoDto;

public class LegajoChequeRechazadoCobroRelacionadoMapeador extends Mapeador {


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
		LegajoChequeRechazadoCobroRelacionadoDto dto = new LegajoChequeRechazadoCobroRelacionadoDto();
		VistaSoporteResultadoBusquedaLegajoCobrosRelacionado bean = (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado) be;
		
		TipoFacturacionIceEnum tipoFacturacionIce = TipoFacturacionIceEnum.getEnumByCodigo(bean.getSistema());
		if(!Validaciones.isObjectNull(tipoFacturacionIce)){
			dto.setSistema(Utilidad.capitalizarCadena(tipoFacturacionIce.sistema()));
			TipoComprobanteEnum tipoComprobante = TipoComprobanteEnum.getEnumByName(bean.getTipoDocumento());
			if (Validaciones.isObjectNull(tipoComprobante)) {
				dto.setTipoDocumentoDescripcion(bean.getTipoDocumento());
			} else {
				dto.setTipoDocumentoDescripcion(tipoComprobante.descripcion());
			}
		} else {
			if (!Validaciones.isNullEmptyOrDash(bean.getSistema())) {
				dto.setSistema(Utilidad.capitalizarCadena(bean.getSistema()));
			}
			if (!Validaciones.isObjectNull(bean.getTipoDocumento())) {
				dto.setTipoDocumentoDescripcion(TipoComprobanteEnum.getEnumByName(bean.getTipoDocumento()).descripcion());
			}
		}
		if (!Validaciones.isNullEmptyOrDash(dto.getTipoDocumentoDescripcion())) {
			dto.setTipoDocumentoDescripcion(Utilidad.capitalizarCadena(dto.getTipoDocumentoDescripcion()));
		}
		
		dto.setTipoDocumento(bean.getTipoDocumento());
		dto.setIdOperacion(bean.getIdOperacion());
		dto.setNumeroLegal(bean.getNumeroLegal());
		dto.setNumeroReferencia(bean.getNumeroReferencia());
		dto.setConvenioFinanciacion(bean.getConvenioFinanciacion());
		dto.setCuotaFinanciacion(bean.getCuotaFinanciacion());
		dto.setClienteLegado(bean.getClienteLegado());
		
		//Importes
		if(!Validaciones.isObjectNull(bean.getImporteTotalDocumento())){
			dto.setImporteTotalDocumento(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalDocumento()), true, true, 2));
		}		
		if(!Validaciones.isObjectNull(bean.getImporteTotalCheque())){
			dto.setImporteTotalCheque(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalCheque()), true, true, 2));
		}		
		if(!Validaciones.isObjectNull(bean.getImporteTotalEfectivo())){
			dto.setImporteTotalEfectivo(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalEfectivo()), true, true, 2));
		}		
		if(!Validaciones.isObjectNull(bean.getImporteTotalRetenciones())){
			dto.setImporteTotalRetenciones(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalRetenciones()), true, true, 2));
		}		
		if(!Validaciones.isObjectNull(bean.getImporteTotalBonos())){
			dto.setImporteTotalBonos(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalBonos()), true, true, 2));
		}		
		if(!Validaciones.isObjectNull(bean.getImporteTotalOtrasMonedas())){
			dto.setImporteTotalOtrasMonedas(Utilidad.formatCurrency(new BigDecimal(bean.getImporteTotalOtrasMonedas()), true, true, 2));
		}
		
		if (!Validaciones.isObjectNull(bean.getFechaImputacion())) {
			dto.setFechaImputacion(Utilidad.formatDatePicker(bean.getFechaImputacion()));
		}
		dto.setIdDescobro(bean.getIdDescobro());
		dto.setIdOperacionDescobro(bean.getIdOperacionDescobro());
		dto.setAnalista(bean.getAnalista());
		dto.setCopropietario(bean.getCopropietario());
		dto.setAnalistaTeamComercial(bean.getAnalistaTeamComercial());
		dto.setIdAnalista(bean.getIdAnalista());
		dto.setIdCopropietario(bean.getIdCopropietario());
		dto.setIdAnalistaTeamComercial(bean.getIdAnalistaTeamComercial());
		if(!Validaciones.isObjectNull(bean.getIdChequeRechazadoCobro())){
			dto.setIdChequeRechazadoCobro(new Long(bean.getIdChequeRechazadoCobro()));
		}

		dto.setSistemaOrigen(bean.getSistemaOrigen());
		if (!Validaciones.isObjectNull(dto.getSistemaOrigen())) {
			if (SistemaEnum.SHIVA.equals(bean.getSistemaOrigen())) {
				if (!Validaciones.isObjectNull(bean.getEstadoReversionShiva())) {
					dto.setEstadoShiva(Estado.getEnumByName(bean.getEstadoReversionShiva()));
					dto.setEstadoShivaDescripcion(dto.getEstadoShiva().descripcion());
				}
			} else if (SistemaEnum.ICE.equals(bean.getSistemaOrigen())) {
				if (!Validaciones.isObjectNull(bean.getEstadoReversionIce())) {
					dto.setEstadoIce(EstadoChequeRechazadoDetalleCobroEnum.getEnumByName(bean.getEstadoReversionIce()));
					dto.setEstadoIceDescripcion(dto.getEstadoIce().getDescripcion());
				}
			}
		}
		if (!Validaciones.isObjectNull(bean.getFechaReversion())) {
			dto.setFechaReversion(Utilidad.formatDateAndTimeFull(bean.getFechaReversion()));
		}
		dto.setNombreArchivoConReversion(bean.getNombreArchivoReversion());
		return dto;
	}
}
