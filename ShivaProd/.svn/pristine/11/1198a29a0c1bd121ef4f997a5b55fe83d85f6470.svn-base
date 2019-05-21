package ar.com.telecom.shiva.base.ws.cliente.servicios.impl.dummy;

import java.util.Random;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.InformacionGeneralApropiacion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;

public class DummyDeimosServicio implements IClienteDeimosServicio {

	@Override
	public SalidaDeimosConsultaEstadoDocumentoWS consultarEstadoDocumento(
			EntradaDeimosConsultaEstadoDeudaWS entradaWS)
			throws NegocioExcepcion {
		SalidaDeimosConsultaEstadoDocumentoWS salida = new SalidaDeimosConsultaEstadoDocumentoWS();
		
		//MIC y CALIPSO ROJO
		//codigo estado tramite CO FAC, DEB, CRED 
		//codigo estado tramite EGCA y cantidad de cuotas pagas mayor a cero
				
		InformacionGeneralApropiacion informacionGeneralApropiacion = new InformacionGeneralApropiacion();
		ResultadoProcesamiento resultado = new ResultadoProcesamiento();
		Random rnd = new Random();
		if(rnd.nextBoolean()){
			informacionGeneralApropiacion.setApropiada(SiNoEnum.NO);
			informacionGeneralApropiacion.setCantidadCuotas(1);
			informacionGeneralApropiacion.setCantidadCuotasPagas(0);
			informacionGeneralApropiacion.setCodigoEstadoTramite("EGSA");
			informacionGeneralApropiacion.setConvenio(new Long(94));
			informacionGeneralApropiacion.setDescripcionEstadoTramite("En Gestion Sin Acuerdo");
			
			resultado.setResultadoImputacion("OK");
			resultado.setCodigoError("");
			resultado.setDescripcionError("");
		}else{
			informacionGeneralApropiacion.setApropiada(SiNoEnum.SI);
			informacionGeneralApropiacion.setCantidadCuotas(1);
			informacionGeneralApropiacion.setCantidadCuotasPagas(1);
			informacionGeneralApropiacion.setCodigoEstadoTramite("EGCA");
			informacionGeneralApropiacion.setConvenio(new Long(94));
			informacionGeneralApropiacion.setDescripcionEstadoTramite("En Gestion Con Acuerdo");
			
			resultado.setResultadoImputacion("OK");
			resultado.setCodigoError("1");
			resultado.setDescripcionError("Prueba");
		}
		
		salida.setInformacionGeneralApropiacion(informacionGeneralApropiacion);
		salida.setResultadoProcesamiento(resultado);		
		return salida;
	}

	@Override
	public SalidaDeimosConsultaEstadoDocumentoWS consultarDeimos(
			IDatosComunesEntrada datosDeimos, EmpresaEnum empresa) throws NegocioExcepcion {
		EntradaDeimosConsultaEstadoDeudaWS 			entradaDeimos = new EntradaDeimosConsultaEstadoDeudaWS();
		SalidaDeimosConsultaEstadoDocumentoWS 		respuestaDeimos = null;
		
		entradaDeimos.setIdDocumentoCalipso(datosDeimos.getIdDocumentoCalipso());
		entradaDeimos.setSistema(datosDeimos.getSistemaOrigen());
		entradaDeimos.setEmpresa(empresa);
		if (Validaciones.isNumeric((datosDeimos.getNumeroReferenciaMic()))){
			entradaDeimos.setIdNumeroReferenciaMic(Long.valueOf(datosDeimos.getNumeroReferenciaMic()));
		}
		try {
			
			respuestaDeimos = this.consultarEstadoDocumento(entradaDeimos);

		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e.getCause(), e.getMensajeAuxiliar());
		}
		
		return respuestaDeimos;
	}

	@Override
	public SalidaDeimosApropiacionWS apropiarDocumento(
			EntradaDeimosApropiacionWS entrada) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalidaDeimosDesapropiacionWS desapropiarDocumento(
			EntradaDeimosDesapropiacionWS entrada) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	
}
