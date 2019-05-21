package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaBean;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class CobroOnlineOtrosDebitoMapeador extends Mapeador implements ICobroOnlineOtrosDebitoMapeador {

	@Autowired
	ICobroOnlineServicio	cobroOnlineServicio;
	@Autowired
	IDocumentoAdjuntoDao documentoAdjuntoDao;
	

	public DTO map(Modelo vo) throws NegocioExcepcion {

		ShvCobEdOtrosDebito modelo = (ShvCobEdOtrosDebito) vo;
		CobroOtrosDebitoDto dto = new CobroOtrosDebitoDto();

		dto.setIdOtrosDebito(modelo.getPk().getIdOtrosDebito());
		dto.setSistema(modelo.getSistema().getDescripcion());
		dto.setSistemaEnum(modelo.getSistema());
		// VER
		dto.setTipoDebito(!Validaciones.isObjectNull(modelo.getTipoComprobante()) ? modelo.getTipoComprobante().getDescripcion() : null);
		dto.setTipoDebitoEnum(modelo.getTipoComprobante());
		dto.setCliente(modelo.getIdClienteLegado());
		dto.setSociedad(modelo.getSociedad().getDescripcion());
		dto.setSociedadEnum(modelo.getSociedad());
		
		if(!Validaciones.isObjectNull(modelo.getReferencia())){
			dto.setReferenciaPago(modelo.getReferencia().toString());
		}
		
		dto.setMoneda(modelo.getMoneda() != null ? modelo.getMoneda().getSigno2() : "");
		dto.setMonedaEnum(modelo.getMoneda());
		dto.setIdAdjunto(modelo.getIdAdjunto());
		
		if (!Validaciones.isObjectNull(modelo.getIdAdjunto())){
			try {
				dto.setNombreAdjunto(documentoAdjuntoDao.buscarDocumentoAdjunto(modelo.getIdAdjunto()).getNombreArchivo());
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
		}
		
		dto.setSinDiferenciaDeCambio(Validaciones.isObjectNull(modelo.getCheckSinDiferenciaCambio()) ? null : modelo.getCheckSinDiferenciaCambio().getDescripcionCorta());
		dto.setFechaVencimiento(!Validaciones.isObjectNull(modelo.getFechaVencimiento()) ? Utilidad.formatDatePicker(modelo.getFechaVencimiento()) : null);
		dto.setMonedaImporteCobrar(modelo.getMonedaImporteACobrar());

		dto.setIdOtrosDebitoPantalla(modelo.getIdOtrosDebitosReferencia());

		BigDecimal tipoCambio = modelo.getTipoCambio();

		if (!Validaciones.isObjectNull(tipoCambio)) {
			dto.setTipoCambio(Utilidad.formatDecimales(tipoCambio, 7));
		}
		if (!Validaciones.isObjectNull(modelo.getImporteCobroarMonedaOrigen())) {
			dto.setImporteMonedaOrigen(Utilidad.formatCurrency(modelo.getImporteCobroarMonedaOrigen(), false, false));
		}
		
		dto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), false, false));
		dto.setImporteString(Utilidad.formatCurrency(modelo.getImporte(), false, true));
		
		if (!Validaciones.isObjectNull(modelo.getSucursalComprobante()) && !Validaciones.isObjectNull(modelo.getNumeroComprobante())) {
			StringBuffer str = new StringBuffer();

			if (Validaciones.isObjectNull(modelo.getClaseComprobante())) {
				str.append(modelo.getSucursalComprobante()).append("-").append(modelo.getNumeroComprobante());
			} else {
				str.append(modelo.getClaseComprobante().name());
				if (!SociedadEnum.PERSONAL.equals(modelo.getSociedad())) {
					str.append("-");
				} else if (SistemaEnum.SAP.equals(modelo.getSistema())) {
					str.append("-");
				}
				str.append(modelo.getSucursalComprobante()).append("-").append(modelo.getNumeroComprobante());
			}
			dto.setNumeroDocumento(str.toString());
			dto.setSucursalComprobante(modelo.getSucursalComprobante());
			dto.setNumeroComprobante(modelo.getNumeroComprobante());
		}
		dto.setOrigen(modelo.getOrigen());
		if (OrigenDebitoEnum.IMPORTACION.equals(dto.getOrigen())) {
			dto.setArchivoImportacion(modelo.getArchivoImportacion());
		}
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.telecom.shiva.base.mapeadores.Mapeador#map(ar.com.telecom.shiva
	 * .base.dto.DTO, ar.com.telecom.shiva.persistencia.modelo.Modelo)
	 */
	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroOtrosDebitoDto otrosDebitoDto = (CobroOtrosDebitoDto) dto;
		ShvCobEdOtrosDebito otrosDebitoModelo = (ShvCobEdOtrosDebito) (vo != null ? vo : new ShvCobEdOtrosDebito());

		try {
			otrosDebitoModelo.setIdClienteLegado(otrosDebitoDto.getCliente());
			otrosDebitoModelo.setSistema(otrosDebitoDto.getSistemaEnum());
			otrosDebitoModelo.setSociedad(otrosDebitoDto.getSociedadEnum());
			otrosDebitoModelo.setTipoComprobante(otrosDebitoDto.getTipoDebitoEnum());
			
			if(!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getReferenciaPago())){
				otrosDebitoModelo.setReferencia(new BigInteger (otrosDebitoDto.getReferenciaPago()));
			}
			
			otrosDebitoModelo.setMoneda(otrosDebitoDto.getMonedaEnum());
			otrosDebitoModelo.setMonedaImporteACobrar(otrosDebitoDto.getMonedaImporteCobrar());
			if (!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getTipoCambio())) {
				otrosDebitoModelo.setTipoCambio(Utilidad.stringToBigDecimal(otrosDebitoDto.getTipoCambio()));
			}
			if (!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getImporteMonedaOrigen())) {
				otrosDebitoModelo.setImporteCobroarMonedaOrigen(Utilidad.stringToBigDecimal(otrosDebitoDto.getImporteMonedaOrigen()));
			}
			otrosDebitoModelo.setIdAdjunto(otrosDebitoDto.getIdAdjunto());
			otrosDebitoModelo.setCheckSinDiferenciaCambio(Validaciones.isNullEmptyOrDash(otrosDebitoDto.getSinDiferenciaDeCambio()) ? null : SiNoEnum.getEnumByDescripcionCorta(otrosDebitoDto.getSinDiferenciaDeCambio()));
			otrosDebitoModelo.setImporte(Utilidad.stringToBigDecimal(otrosDebitoDto.getImporte()));
			otrosDebitoModelo.setIdOtrosDebitosReferencia(!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getIdOtrosDebitoPantalla()) ? otrosDebitoDto.getIdOtrosDebitoPantalla() : null);
			if (!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getFechaVencimiento())) {
				otrosDebitoModelo.setFechaVencimiento(Utilidad.parseDatePickerString(otrosDebitoDto.getFechaVencimiento()));
			}
			if (!Validaciones.isNullEmptyOrDash(otrosDebitoDto.getNumeroDocumento())) {
				String[] split = otrosDebitoDto.getNumeroDocumento().trim().split("-");

				if (split.length == 2) {
					if (split[0].length() == 5) {
						otrosDebitoModelo.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(split[0].substring(0, 1)));
						otrosDebitoModelo.setSucursalComprobante(split[0].substring(1, 5));
						otrosDebitoModelo.setNumeroComprobante(split[1]);
					} else {
						otrosDebitoModelo.setSucursalComprobante(split[0]);
						otrosDebitoModelo.setNumeroComprobante(split[1]);
					}
				} else if (split.length == 3) {
					otrosDebitoModelo.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(split[0]));
					otrosDebitoModelo.setSucursalComprobante(split[1]);
					otrosDebitoModelo.setNumeroComprobante(split[2]);
				}
			}
			otrosDebitoModelo.setOrigen(otrosDebitoDto.getOrigen());
			if (OrigenDebitoEnum.IMPORTACION.equals(otrosDebitoDto.getOrigen())) {
				otrosDebitoModelo.setArchivoImportacion(otrosDebitoDto.getArchivoImportacion());
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return otrosDebitoModelo;
	}

	public DTO map(String compos[], DTO vo, List<ShvParamConfCampo> listarCamposImportacion, MonedaEnum modeCobro, String nombreArchivo, List<ConfReglaBean> listComprobantes) throws NegocioExcepcion {
		CobroOtrosDebitoDto dto;
		if (vo == null) {
			dto = new CobroOtrosDebitoDto();
		} else {
			dto = (CobroOtrosDebitoDto) vo;
		}
		String valorCamor;
		for (ShvParamConfCampo shvParamConfCampo: listarCamposImportacion) {
			valorCamor = compos[shvParamConfCampo.getOrdenCampos().intValue() - 1];
	
			if ("sociedad".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				dto.setSociedadEnum(SociedadEnum.getEnumByApocope(valorCamor));
				dto.setSociedad(dto.getSociedadEnum().getDescripcion());
			} else if ("sistema".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				dto.setSistemaEnum(SistemaEnum.getEnumByDescripcionCorta(valorCamor));
				dto.setSistema(dto.getSistemaEnum().getDescripcion());
			} else if ("tipoComprobante".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				
				dto.setTipoDebitoEnum(TipoComprobanteEnum.getEnumByValor(valorCamor));
				dto.setTipoDebito(dto.getTipoDebitoEnum().getDescripcion());
			} else if ("moneda".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				dto.setMonedaEnum(MonedaEnum.getEnumBySigno2(valorCamor));
				dto.setMoneda(dto.getMonedaEnum().getSigno2());
			} else if ("cliente".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				if (!Validaciones.isNullEmptyOrDash(valorCamor)) {
					dto.setCliente(Long.parseLong(valorCamor));
				}
			} else if ("referenciaPago".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				if (!Validaciones.isNullEmptyOrDash(valorCamor)){
					dto.setReferenciaPago(valorCamor);
				}
				//dto.setReferenciaPago(valorCamor);
			} else if ("importe".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				dto.setImporte(valorCamor);
			} else if ("fechaVencimiento".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				if (!Validaciones.isNullEmptyOrDash(valorCamor)) {
					dto.setFechaVencimiento(valorCamor);
				}
			} else if ("numeroDocumento".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				dto.setNumeroDocumento(valorCamor);
			} else if ("tipoCambio".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				if (!Validaciones.isNullEmptyOrDash(valorCamor)) {
					dto.setTipoCambio(valorCamor);
				}
			} else if ("sinDiferenciaDeCambio".equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				if (!Validaciones.isNullEmptyOrDash(valorCamor)) {
					dto.setSinDiferenciaDeCambio(valorCamor);
				}
			}
		}
		if(dto.getTipoDebitoEnum().equals(TipoComprobanteEnum.DE_)){
			for(ConfReglaBean confReglaBean : listComprobantes){
				if(dto.getSociedadEnum().equals(confReglaBean.getSociedad()) && dto.getMonedaEnum().equals(confReglaBean.getMoneda())&& dto.getSistemaEnum().equals(confReglaBean.getSistema())){
					dto.setTipoDebitoEnum(confReglaBean.getTipoComprobante());
					dto.setTipoDebito(dto.getTipoDebitoEnum().getDescripcion());
					break;
				}
			}
		}
		dto.setMonedaImporteCobrar(modeCobro);
		if (!dto.getMonedaEnum().equals(modeCobro)) {
			if (!Validaciones.isNullEmptyOrDash(dto.getTipoCambio())) {
				double tipoCambio = Utilidad.stringToBigDecimal(dto.getTipoCambio()).doubleValue();
				double importe = Utilidad.stringToBigDecimal(dto.getImporte()).doubleValue();
				Double importeMonedaOrigenDouble = (importe / tipoCambio) * 100;
				importeMonedaOrigenDouble = new Double(importeMonedaOrigenDouble.intValue());
				
				BigDecimal importeMonedaOrigen = new BigDecimal(importeMonedaOrigenDouble / 100);
				dto.setImporteMonedaOrigen(
					Utilidad.formatCurrency(importeMonedaOrigen, false, false)
				);
			}
		}
		
		dto.setOrigen(OrigenDebitoEnum.IMPORTACION);
		dto.setArchivoImportacion(nombreArchivo);
		dto.setIdOtrosDebitoPantalla(dto.generarIdOtrosDebitoPantalla());
		return dto;
	}
	
}
