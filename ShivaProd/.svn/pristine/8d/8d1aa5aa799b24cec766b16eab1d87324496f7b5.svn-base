package ar.com.telecom.shiva.base.ws.servidor;

import java.text.ParseException;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPEntrada;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPSalida;

@WebService
@HandlerChain(file = "RecepcionNovedadesDocumentosSAPWSHandler.xml")
public interface IRecepcionNovedadesDocumentosSAPWS {

	@WebMethod
	@WebResult(name = "respuestaRecepcionNovedadesDocumentosSAP")
    public RecepcionNovedadesDocumentosSAPSalida recepcionNovedades (@WebParam(name="novedad") RecepcionNovedadesDocumentosSAPEntrada entrada) throws ValidacionExcepcion, PersistenciaExcepcion, ParseException, NegocioExcepcion;
}
