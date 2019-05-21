package ar.com.telecom.shiva.base.ws.servidor.servicios.validacion;



import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.DocumentoSap;

public interface IRecepcionNovedadesDocumentosSAPValidacionServicio {
	
	void validacionTipoNovedad (String tipoNovedad) throws ValidacionExcepcion;
	
	void validacionUsuario (String usuario) throws ValidacionExcepcion;
		
	void validacionFechaCreacionContradocumento(Long fechaCreacion) throws ValidacionExcepcion;
		
	void validacionDocumentoSap (DocumentoSap documentoOriginal, boolean esOriginal) throws ValidacionExcepcion;
	
}
