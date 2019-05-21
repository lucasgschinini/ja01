package ar.com.telecom.shiva.base.comparador;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;

public class CobroDebitoDtoComparator implements Comparator<CobroDebitoDto> {

	/**
	 * Ordenamiento de los débitos
	 * 
	 * ordena por Fecha vencimiento
	 * ordena por Id cliente legado
	 * 		Si el sistema origen es MIC
	 * 			- Ordena por cuenta
	 * 				- Verifica si es un DUC y ordena por Nro de referencia
	 * 		Si es sistema origen en Calipso
	 * 			- Ordena por nro de documento o vacio
	 * 		Si no
	 * 			- ordena por un numero identificatorio (numero de referencia o idCob)
	 * 
	 */
	public int compare(CobroDebitoDto dto01, CobroDebitoDto dto02) {

		try {
			Date fecha01 = Utilidad.parseDatePickerString(dto01.getOrderByFecha());
			Date fecha02 = Utilidad.parseDatePickerString(dto02.getOrderByFecha());
			
			if (fecha01 == null && fecha02 != null) {
				return 1;
			} else { 
				if (fecha01 != null && fecha02 == null) {
					return -1;
				}
			}
			if ((fecha01 != null && fecha02 != null) && (fecha01.getTime() > fecha02.getTime())) {
				return 1;
			} else { 
				if ((fecha01 != null && fecha02 != null) && (fecha01.getTime() < fecha02.getTime())) {
					return -1;
				} else {
					Long idClienteLegado01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByIdClienteLegado()) ? new Long(0) : new Long(dto01.getOrderByIdClienteLegado());
					Long idClienteLegado02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByIdClienteLegado()) ? new Long(0) : new Long(dto02.getOrderByIdClienteLegado());
					if (idClienteLegado01.compareTo(idClienteLegado02) > 0) {
						return 1;
					} else {
						if (idClienteLegado01.compareTo(idClienteLegado02) < 0) {
							return -1;
						} else {
							if (
								SistemaEnum.MIC.equals(dto01.getSistemaOrigen()) &&
								SistemaEnum.MIC.equals(dto02.getSistemaOrigen())
							) {
								Long cod_cuenta01 = Validaciones.isNullEmptyOrDash(dto01.getCuenta()) ? 0l : Long.parseLong(dto01.getCuenta());
								Long cod_cuenta02 = Validaciones.isNullEmptyOrDash(dto01.getCuenta()) ? 0l : Long.parseLong(dto02.getCuenta());
								if (cod_cuenta01.compareTo(cod_cuenta02) > 0) {
									return 1;
								} else if (cod_cuenta01.compareTo(cod_cuenta02) < 0) {
									return -1;
								} else {
									if (
										!TipoComprobanteEnum.DUC.equals(dto01.getTipoComprobanteEnum()) &&
										!TipoComprobanteEnum.DUC.equals(dto02.getTipoComprobanteEnum())
									) {
										Long numeroIdentificatorio01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto01.getOrderByNumeroIdentificatorio());
										Long numeroIdentificatorio02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto02.getOrderByNumeroIdentificatorio());

										return numeroIdentificatorio01.compareTo(numeroIdentificatorio02);
									} else {
										return dto01.getOrderByNumeroIdentificatorio().compareTo(dto02.getOrderByNumeroIdentificatorio());
									}
								}
							} else if (
								SistemaEnum.CALIPSO.equals(dto01.getSistemaOrigen()) &&
								SistemaEnum.CALIPSO.equals(dto02.getSistemaOrigen())
							) {
								Long numeroIdentificatorio01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto01.getOrderByNumeroIdentificatorio());
								Long numeroIdentificatorio02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByNumeroIdentificatorio())?new Long(0):new Long(dto02.getOrderByNumeroIdentificatorio());

								return numeroIdentificatorio01.compareTo(numeroIdentificatorio02);
							} else {

								Traza.error(this.getClass(), "Se van a comparar débitos por 'numero identificación' con estos datos: ");
								Traza.error(this.getClass(), "Debito 01: Fecha " + fecha01 + " idClienteLegado: " + dto01.getOrderByIdClienteLegado() + " Sistema Origen: " + dto01.getSistemaOrigen() + " Numero identificatorio: " + dto01.getOrderByNumeroIdentificatorio());
								Traza.error(this.getClass(), "Debito 02: Fecha " + fecha02 + " idClienteLegado: " + dto02.getOrderByIdClienteLegado() + " Sistema Origen: " + dto02.getSistemaOrigen() + " Numero identificatorio: " + dto02.getOrderByNumeroIdentificatorio());
								
								Long orderByNumeroIdentificatorio01 = Validaciones.isNullEmptyOrDash(dto01.getOrderByNumeroIdentificatorio()) ? new Long(0) : new Long(dto01.getOrderByNumeroIdentificatorio());
								Long orderByNumeroIdentificatorio02 = Validaciones.isNullEmptyOrDash(dto02.getOrderByNumeroIdentificatorio()) ? new Long(0) : new Long(dto02.getOrderByNumeroIdentificatorio());
								
								int resultado = orderByNumeroIdentificatorio01.compareTo(orderByNumeroIdentificatorio02);

								String resultadoStr = "Son iguales";
								if (resultado > 0) {
									resultadoStr = "Debito 01 es mayor a Debito 02";
								} else if (resultado < 0) {
									resultadoStr = "Debito 01 es menor a Debito 02";
								}
								
								Traza.error(this.getClass(), "Resultado de comparación: " + resultadoStr + " (" + resultado + ")");
								
								if (resultado != 0) {
									return resultado;
								} else {
									if (dto01.getSistemaOrigen().equals(dto02.getSistemaOrigen())) {
										return 0;
									} else {
										if (SistemaEnum.MIC.equals(dto01.getSistemaOrigen())) {
											return 1;
										} else {
											return -1;
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

//}