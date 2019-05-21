package ar.com.telecom.shiva.base.ws.servidor.servicios.validacion.impl;



import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.RecepcionNovedadesDocumentosSAPEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.DocumentoSap;
import ar.com.telecom.shiva.base.ws.servidor.servicios.validacion.IRecepcionNovedadesDocumentosSAPValidacionServicio;

public class RecepcionNovedadesDocumentosSAPValidacionServicioImpl implements IRecepcionNovedadesDocumentosSAPValidacionServicio{

	@Override
	public void validacionTipoNovedad(String tipoNovedad)
			throws ValidacionExcepcion {
		
		if(Validaciones.isNullEmptyOrDash(tipoNovedad)) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),"Tipo Novedad"),"Tipo Novedad");
		}
		
		if(!RecepcionNovedadesDocumentosSAPEnum.ANULACION_DOC_K$.name().equals(tipoNovedad)) {
			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),"Tipo Novedad",tipoNovedad),"Tipo Novedad");
		}
		
	}

	@Override
	public void validacionUsuario(String usuario) throws ValidacionExcepcion {
		
		if(Validaciones.isNullEmptyOrDash(usuario)) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),"Usuario"),"Usuario");
		}
	}
	
	public void validacionFechaCreacionContradocumento(Long fechaCreacion) throws ValidacionExcepcion {

		if(Validaciones.isObjectNull(fechaCreacion)) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),"Fecha Creacion Contradocumento"),"Fecha Creacion Contradocumento");
		}

		if(!Validaciones.validarFecha(Utilidad.cambiarFormatoFechaDDMMAAAA(fechaCreacion.toString()))){

			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),"Fecha Creacion Contradocumento",fechaCreacion.toString()),"Fecha Creacion Contradocumento");

		}
		//Compara que la fecha enviada sea menor a la fecha actual
		if(!Validaciones.validarFechaDesdeHasta(
				Utilidad.cambiarFormatoFechaDDMMAAAA(fechaCreacion.toString()),
				Utilidad.cambiarFormatoFechaDDMMAAAA(Utilidad.formatDateAAAAMMDD(Utilidad.obtenerFechaActual())))) {
			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),"Fecha Creacion Contradocumento",fechaCreacion.toString()),"Fecha Creacion Contradocumento");
		}
	}

	@Override
	public void validacionDocumentoSap(DocumentoSap documentoSap, boolean esOriginal)
			throws ValidacionExcepcion {
		
		if (Validaciones.isObjectNull(documentoSap.getIdDocumento())) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.id."+(esOriginal? "documento" : "contradocumento"))),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.id."+(esOriginal? "documento" : "contradocumento")));
		}
		
		if(!Validaciones.isNumeric(documentoSap.getIdDocumento())) {
			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.id."+(esOriginal? "documento" : "contradocumento")),documentoSap.getIdDocumento()),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.id."+(esOriginal? "documento" : "contradocumento")));
		}
		
		if (Validaciones.isObjectNull(documentoSap.getIdSociedad())) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.sociedad."+(esOriginal? "documento" : "contradocumento"))),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.sociedad."+(esOriginal? "documento" : "contradocumento")));
		} else {
			if (Constantes.EMPTY_STRING.equals(documentoSap.getIdSociedad().trim())) {
				throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),
						Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.sociedad."+(esOriginal? "documento" : "contradocumento"))),
						Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.sociedad."+(esOriginal? "documento" : "contradocumento")));
			}
		}
		
//		if(!Validaciones.isNumeric(documentoOriginal.getIdSociedad())) {
//			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
//					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),"ID Sociedad Original",documentoOriginal.getIdSociedad()),"ID Sociedad Original");
//		}
		
		if (Validaciones.isObjectNull(documentoSap.getAnioFiscal())) {
			throw new ValidacionExcepcion("2001", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Obligatorio"),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.anio."+(esOriginal? "documento" : "contradocumento"))),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.anio."+(esOriginal? "documento" : "contradocumento")));
		}
		
		if (documentoSap.getAnioFiscal() <= Constantes.CERO) {
			throw new ValidacionExcepcion("2011", Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.validarCampo.Formato"),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.anio."+(esOriginal? "documento" : "contradocumento")),documentoSap.getAnioFiscal().toString()),
					Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.anio."+(esOriginal? "documento" : "contradocumento")));
		}
	}
}
