package ar.com.telecom.shiva.base.ws.servidor;

import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPEntrada;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPSalida;
import ar.com.telecom.shiva.base.ws.servidor.servicios.IRecepcionNovedadesDocumentosSAPWSServicio;


@WebService(endpointInterface="ar.com.telecom.shiva.base.ws.servidor.IRecepcionNovedadesDocumentosSAPWS", 
serviceName="RecepcionNovedadesDocumentosSAPWS",
portName="RecepcionNovedadesDocumentosSAPWSPort")
public class RecepcionNovedadesDocumentosSAPWSImpl implements IRecepcionNovedadesDocumentosSAPWS{


	@Autowired
	IRecepcionNovedadesDocumentosSAPWSServicio recepcionNovedadesDocumentosSAPWSServicio;
	
	@PostConstruct
	private void init() {
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	public RecepcionNovedadesDocumentosSAPSalida recepcionNovedades(RecepcionNovedadesDocumentosSAPEntrada entrada) throws PersistenciaExcepcion, ParseException, NegocioExcepcion {

		RecepcionNovedadesDocumentosSAPSalida salida;
		
		salida = recepcionNovedadesDocumentosSAPWSServicio.consultarRecepcionNovedades(entrada);
		
		//Libero la memoria
		System.gc();
		
		return salida;
	}
	
	public IRecepcionNovedadesDocumentosSAPWSServicio getRecepcionNovedadesDocumentosSAPWSServicio() {
		return recepcionNovedadesDocumentosSAPWSServicio;
	}

	public void setRecepcionNovedadesDocumentosSAPWSServicio(
			IRecepcionNovedadesDocumentosSAPWSServicio recepcionNovedadesDocumentosSAPWSServicio) {
		this.recepcionNovedadesDocumentosSAPWSServicio = recepcionNovedadesDocumentosSAPWSServicio;
	}


}
