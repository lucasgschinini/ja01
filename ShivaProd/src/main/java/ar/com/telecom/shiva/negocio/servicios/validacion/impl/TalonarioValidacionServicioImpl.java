package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ITalonarioValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;

public class TalonarioValidacionServicioImpl implements ITalonarioValidacionServicio {

	@Autowired ITalonarioServicio talonarioServicio;

	
	public void validacionIdTalonario(String idTalonario) throws ValidacionExcepcion{
		if (Validaciones.isNullOrEmpty(idTalonario)){
			throw new ValidacionExcepcion("","recibo.codigoTalonario.obligatorio");
		}else{
			if (!Validaciones.isNullOrNumeric(idTalonario)){
				throw new ValidacionExcepcion("","recibo.codigoTalonario.numerico");
			}
		}
	}
	
	public void validacionEmpresa(String empresa) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(empresa)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionSegmento(String segmento) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(segmento)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}
	
	public void validacionNroSerie(String nroSerie, Boolean busqueda) throws ValidacionExcepcion {
		if (!busqueda && Validaciones.isNullOrEmpty(nroSerie)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}else{
			if (!Validaciones.isNullOrNumericNatural(nroSerie)){
				throw new ValidacionExcepcion("","error.numerico");
			}
		}
	}

	public void validacionRangoDesde(String rangoDesde, Boolean busqueda) throws ValidacionExcepcion{
		if (!busqueda && Validaciones.isNullOrEmpty(rangoDesde)){
			throw new ValidacionExcepcion("","error.obligatorio");
		}else{
			if (!Validaciones.isNullOrNumericNatural(rangoDesde)){
				throw new ValidacionExcepcion("","error.numerico");
			}
		}
	}
	
	public void validacionRangoHasta(String rangoHasta, Boolean busqueda) throws ValidacionExcepcion {
		if (!busqueda && Validaciones.isNullOrEmpty(rangoHasta)){
			throw new ValidacionExcepcion("","error.obligatorio");
		}else{
			if (!Validaciones.isNullOrNumericNatural(rangoHasta)){
				throw new ValidacionExcepcion("","error.numerico");
			}
		}
	}

	public void validacionRangoDesdeHasta(String rangoDesde, String rangoHasta, String nroSerie, Boolean busqueda) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(rangoDesde) && !Validaciones.isNullOrEmpty(rangoHasta)){
			if ((Long.valueOf(rangoDesde)>Long.valueOf(rangoHasta)&& busqueda) || (Long.valueOf(rangoDesde)>=Long.valueOf(rangoHasta)&& !busqueda)){
				if (busqueda){
					throw new ValidacionExcepcion("rangoDesde","error.desdeHasta");
				} else {
					throw new ValidacionExcepcion("rangoDesde","error.desdeMenorHasta");
				}
			}else{
					if (!busqueda && !talonarioServicio.existeTalonarioRango(rangoDesde, rangoHasta, nroSerie)){
						throw new ValidacionExcepcion("rangoDesde","error.rangoInvalido");
					}
			}
		}
	}
	
	public void validacionModificacionRango (TalonarioDto talonarioDto) throws NegocioExcepcion {
		if (Long.valueOf(talonarioDto.getRangoDesde())>=Long.valueOf(talonarioDto.getRangoHasta())){
			throw new ValidacionExcepcion("rangoDesde","error.desdeMenorHasta");
		} else {
			if (!talonarioServicio.existeTalonarioRango(talonarioDto.getRangoDesde(), talonarioDto.getRangoHasta(),talonarioDto.getIdTalonario(),talonarioDto.getNroSerie())){
				throw new ValidacionExcepcion("rangoDesde","error.rangoInvalido");
			}
		}
	}
	
	public void validacionObservaciones(String observaciones) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			if (!Validaciones.esFormatoTexto(observaciones)) {
				throw new ValidacionExcepcion("","error.formatoTexto");
			}
		}else{
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}
	
	public void validacionModificacion(TalonarioDto talonarioDto) throws ValidacionExcepcion {
		try {
			if(Validaciones.isNullOrEmpty(talonarioServicio.getDatosModificados(talonarioDto))){
				throw new ValidacionExcepcion("", Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
			}
		} catch (NegocioExcepcion e) {
			throw new ValidacionExcepcion("", Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
		}
	}
	
	public void validacionRendicion(TalonarioDto talonarioDto) throws NegocioExcepcion {
		//Verificar que cada uno de los recibos pertenecientes al talonario
		//se encuentre asociado al menos a un valor o pesea una compensacion cargada){
		//if (!talonarioServicio.){
			//Obtener una lista de recibos invalidos y pasar a la variable listaDeRecibos por ejemplo "2,3,4"
			//String listaDeRecibos = "2,3,4"; 
			//String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("recibo.rendirRalonario.error");
			//throw new NegocioExcepcion(mensajeError.replace("{0}", listaDeRecibos));
		//}
	}
	
	/****************************************************************************************************
	 * Getters & Setters
	 ****************************************************************************************************/
	public ITalonarioServicio getTalonarioServicio() {
		return talonarioServicio;
	}

	public void settalonarioservicio(ITalonarioServicio talonarioServicio) {
		this.talonarioServicio = talonarioServicio;
	}


	
	
}
