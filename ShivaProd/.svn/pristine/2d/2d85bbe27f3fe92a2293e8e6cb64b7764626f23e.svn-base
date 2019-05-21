package ar.com.telecom.shiva.base.ws.servidor.servicios;

import java.math.BigDecimal;
import java.text.ParseException;

import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPEntrada;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPSalida;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;


public interface IRecepcionNovedadesDocumentosSAPWSServicio {
	
	RecepcionNovedadesDocumentosSAPSalida consultarRecepcionNovedades(
			RecepcionNovedadesDocumentosSAPEntrada entrada) throws ValidacionExcepcion, PersistenciaExcepcion, ParseException, NegocioExcepcion;
	
	public void validarDatosEntradaNovedadesSap(RecepcionNovedadesDocumentosSAPEntrada entrada) throws ValidacionExcepcion ;
	
	public void actualizarDocumentoCap (ShvCobMedioPagoDocumentoCap documentoCap, RecepcionNovedadesDocumentosSAPEntrada entrada) throws ParseException, PersistenciaExcepcion;
	
	public void enviarMailyGenerarTarea(Long idCobro, String idDocumentoCap, String idContradocumentoCap, String usuarioCreacion) throws PersistenciaExcepcion, NegocioExcepcion;
		
	public void crearTareaPendiente(ShvCobCobro cobro, String idDocumentoCap, String idContradocumentoCap, String usuarioCreacion, String analista, BigDecimal importe,
			TipoTareaEnum tipoTarea, String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail) throws NegocioExcepcion;
	
	public void enviarMail(String analista, String conCopia, String asunto, String cuerpo, Long idCobro) throws NegocioExcepcion;
	
}
