package ar.com.telecom.shiva.base.comparador;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;

public class CobroCreditoDtoComparator implements Comparator<CobroCreditoDto> {

	public int compare(CobroCreditoDto dto01, CobroCreditoDto dto02) {
		try {
			Date fecha01 = Utilidad.parseDatePickerString(dto01.getOrderByFecha());
			Date fecha02 = Utilidad.parseDatePickerString(dto02.getOrderByFecha());
			 
			if (fecha01==null && fecha02!=null) {
				return 1;
			} else { 
				if (fecha01!=null && fecha02==null) {
					return -1;
				} 
			}
			
			if ((fecha01!=null && fecha02!=null) && (fecha01.getTime() > fecha02.getTime())) {
				return 1;
			} else { 
				if ((fecha01!=null && fecha02!=null) && (fecha01.getTime() < fecha02.getTime())) {
					return -1;
				} else {
					Long idClienteLegado01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByIdClienteLegado())?new Long(0):new Long(dto01.getOrderByIdClienteLegado());
					Long idClienteLegado02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByIdClienteLegado())?new Long(0):new Long(dto02.getOrderByIdClienteLegado());
					if (idClienteLegado01.compareTo(idClienteLegado02) > 0) {
						return 1;
					} else {
						if (idClienteLegado01.compareTo(idClienteLegado02) < 0) {
							return -1;
						} else {
							if (SistemaEnum.MIC.equals(dto01.getSistemaOrigen()) 
									&& SistemaEnum.MIC.equals(dto02.getSistemaOrigen())
									&& TipoCreditoEnum.NOTACREDITO.name().equals(dto01.getTipoCreditoEnum()) 
									&& TipoCreditoEnum.NOTACREDITO.name().equals(dto02.getTipoCreditoEnum())) 
							{
								Long cod_cuenta01 = Validaciones.isNullEmptyOrDash(dto01.getCuenta()) ? 0l : Long.parseLong(dto01.getCuenta());
								Long cod_cuenta02 = Validaciones.isNullEmptyOrDash(dto01.getCuenta()) ? 0l : Long.parseLong(dto02.getCuenta());
								if (cod_cuenta01.compareTo(cod_cuenta02) > 0) {
									return 1;
								} else if (cod_cuenta01.compareTo(cod_cuenta02) < 0) {
									return -1;
								} else {
									Long numeroIdentificatorio01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto01.getOrderByNumeroIdentificatorio());
									Long numeroIdentificatorio02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto02.getOrderByNumeroIdentificatorio());
									
									return numeroIdentificatorio01.compareTo(numeroIdentificatorio02);
								}
							} else {
								if (SistemaEnum.MIC.equals(dto01.getSistemaOrigen()) 
										&& SistemaEnum.MIC.equals(dto02.getSistemaOrigen())
										&& TipoCreditoEnum.REMANENTE.name().equals(dto01.getTipoCreditoEnum()) 
										&& TipoCreditoEnum.REMANENTE.name().equals(dto02.getTipoCreditoEnum())) 
								{
									Long cuenta01 = Validaciones.isNullEmptyOrDash(dto01.getCuenta())?new Long(dto01.getCuenta()):new Long(0); 
									Long cuenta02 = Validaciones.isNullEmptyOrDash(dto02.getCuenta())?new Long(dto02.getCuenta()):new Long(0);
									
									if (cuenta01.compareTo(cuenta02) > 0) {
										return 1;
									} else {
										if (cuenta01.compareTo(cuenta02) < 0) {
											return -1;
										} else {
											Long subtipo01 = Validaciones.isNullEmptyOrDash(dto01.getSubtipo())?new Long(dto01.getSubtipo()):new Long(0); 
											Long subtipo02 = Validaciones.isNullEmptyOrDash(dto02.getSubtipo())?new Long(dto02.getSubtipo()):new Long(0);
											
											if (subtipo01.compareTo(subtipo02) > 0) {
												return 1;
											} else {
												if (subtipo01.compareTo(subtipo02) < 0) {
													return -1;
												} 
											}
										}
									}
								} else {
									if (
										SistemaEnum.CALIPSO.equals(dto01.getSistemaOrigen()) &&
										SistemaEnum.CALIPSO.equals(dto02.getSistemaOrigen())
									) {
										Long numeroIdentificatorio01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto01.getOrderByNumeroIdentificatorio());
										Long numeroIdentificatorio02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto02.getOrderByNumeroIdentificatorio());

										return numeroIdentificatorio01.compareTo(numeroIdentificatorio02);
									} else {
										if (dto01.getOrderByNumeroIdentificatorio().compareTo(dto02.getOrderByNumeroIdentificatorio()) > 0) {
											return 1;
										} else {
											if (dto01.getOrderByNumeroIdentificatorio().compareTo(dto02.getOrderByNumeroIdentificatorio()) < 0) {
												return -1;
											} 
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			Traza.error(getClass(), "Error al parsear", e);
		} 

		return 0;
	} 

}
