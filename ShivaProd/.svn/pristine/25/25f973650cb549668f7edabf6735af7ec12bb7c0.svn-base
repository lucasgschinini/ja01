package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCotizacionSapEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionRegistroCotizacionSapExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.RegistroCotizacionSap;
import ar.com.telecom.shiva.negocio.dto.CotizacionDto;
import ar.com.telecom.shiva.negocio.servicios.ICotizacionValidacionServicio;

public class CotizacionValidacionServicioImpl implements ICotizacionValidacionServicio{

	@Override
	public CotizacionDto validarRegistroCotizacionesSap(RegistroCotizacionSap registroCotizacionesSap) throws ValidacionRegistroCotizacionSapExcepcion {
		String[] contenidoRegistro = null;
		List<String> errorLista = new ArrayList<String>();
		int elementosLlenos = 0;
		boolean esTipoCotizacionValida = false;
		Pattern patronFecha = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[\\.](0[1-9]|1[012])[\\.](19|[2-9]\\d)\\d\\d");
		boolean esFechaCorrecta  = false;
		Date fechaValidezDesde = null;
		BigDecimal tipoDeCambio = null;
		
		
		if (!Validaciones.isNullEmptyOrDash(registroCotizacionesSap.getContenidoRegistro())){
			contenidoRegistro = (registroCotizacionesSap.getContenidoRegistro()).split(";");
		}
		
		
		for (String elemento : contenidoRegistro) {
			if( !Validaciones.isNullOrEmpty(elemento) ){
				elementosLlenos++;
			}
		}
		
		if( (elementosLlenos != 5) || (contenidoRegistro.length != 5) ){
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.cantidadElementos"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								(elementosLlenos == 5 ? contenidoRegistro.length + "" : elementosLlenos + "")
								));
		}
		
		if( (elementosLlenos == 5) && (contenidoRegistro.length == 5) ){			
			for (TipoCotizacionSapEnum tipoCotizacion : TipoCotizacionSapEnum.values()) {
				if(tipoCotizacion.name().equals(contenidoRegistro[0])){
					esTipoCotizacionValida = true;
				}
			}
			
			if(!esTipoCotizacionValida){
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.tipoCotizacion"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								contenidoRegistro[0]
								));
			}
		
			if (  !(MonedaEnum.PES.getSigla().equals(contenidoRegistro[1]) || 
			    	MonedaEnum.DOL.getSigla().equals(contenidoRegistro[1]) ||
			    	MonedaEnum.EUR.getSigla().equals(contenidoRegistro[1]))) {
				errorLista.add(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.monedaProcedencia"),
						registroCotizacionesSap.getNumeroRegistro().toString(), 
						contenidoRegistro[1]
						));
			}
			
			if (  !(MonedaEnum.PES.getSigla().equals(contenidoRegistro[2]) || 
					MonedaEnum.DOL.getSigla().equals(contenidoRegistro[2]) ||
					MonedaEnum.EUR.getSigla().equals(contenidoRegistro[2]))) {
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.monedaDestino"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								contenidoRegistro[2]
								));
			}
			
			if(!(Validaciones.isObjectNull(contenidoRegistro[1]) || Validaciones.isObjectNull(contenidoRegistro[2]))){
				if(contenidoRegistro[1].equals(contenidoRegistro[2])){
				errorLista.add(						
						Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.monedas"),
						registroCotizacionesSap.getNumeroRegistro().toString(), 
						contenidoRegistro[1],
						contenidoRegistro[2]
						));
				}
			}
				
			esFechaCorrecta = patronFecha.matcher(contenidoRegistro[3]).matches();
			if(!esFechaCorrecta){
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.fechaFormato"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								contenidoRegistro[3]
								)); 
			}

			if(esFechaCorrecta){
				try {
					fechaValidezDesde = Utilidad.parseDateFormatPoint(contenidoRegistro[3]);
				} catch (ParseException e) {
					errorLista.add(
							Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.fechaValor"),
									registroCotizacionesSap.getNumeroRegistro().toString(), 
									contenidoRegistro[3]
									)); 
				}	
			}
		
			try {
				tipoDeCambio = new BigDecimal(contenidoRegistro[4].replaceAll(",", "."));
			} catch (NumberFormatException e) {
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.tipoCambio"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								contenidoRegistro[4]
								));
			}
			
			if(BigDecimal.ZERO.equals(tipoDeCambio)){
				errorLista.add(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.cotizacion.validacion.error.tipoCambio"),
								registroCotizacionesSap.getNumeroRegistro().toString(), 
								contenidoRegistro[4]
								));
			}
		}
		
		if(!errorLista.isEmpty()){
			throw new ValidacionRegistroCotizacionSapExcepcion(errorLista);
		}
		
		CotizacionDto cotizacionDto = new CotizacionDto();
		
		cotizacionDto.setTipoCotizacion(TipoCotizacionSapEnum.getEnumByName(contenidoRegistro[0]));
		cotizacionDto.setMonedaProcedencia(MonedaEnum.getEnumBySigla(contenidoRegistro[1]));
		cotizacionDto.setMonedaDestino(MonedaEnum.getEnumBySigla(contenidoRegistro[2]));
		cotizacionDto.setFechaValidezDesde(fechaValidezDesde);
		cotizacionDto.setTipoDeCambio(tipoDeCambio);
		
		cotizacionDto.setNroRegistro(registroCotizacionesSap.getNumeroRegistro());
		
		return cotizacionDto;
	}

}
