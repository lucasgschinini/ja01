package ar.com.telecom.shiva.negocio.executor.callable;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Documento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionDocumentoMicEnDeimos;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionDeimosRto;

public class ApropiacionDeimosCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private ApropiacionDeimosRto datosEntrada;
	
	public ApropiacionDeimosCallable(String idThread, ApropiacionDeimosRto datosEntrada) {
		if (idThread == null) {
            throw new NullPointerException("idThread cannot be null");
        }
		this.idThread = idThread;
		this.datosEntrada = datosEntrada;
	}
	
	public List<ResultadoSimulacion> call() throws Exception {
		final String orgName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.idThread);
        Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo {0}.", this.idThread));
		
        List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		try {
			IClienteDeimosServicio clienteDeimosServicio = 
					(IClienteDeimosServicio) Configuracion.ctx.getBean("clienteDeimosServicio");
		
			SalidaDeimosApropiacionWS respuesta = clienteDeimosServicio.apropiarDocumento(datosEntrada.getEntradaDeimosApropiacionWS());
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				for (Resultado respuestaApropiacion : respuesta.getListaResultados()) {

					ResultadoSimulacion respuestaSimulacion = null;
					
					if (SistemaEnum.MIC.equals(respuestaApropiacion.getDocumento().getSistema())) {
						respuestaSimulacion = new ResultadoSimulacionApropiacionDocumentoMicEnDeimos();
						((ResultadoSimulacionApropiacionDocumentoMicEnDeimos)respuestaSimulacion).setNumeroReferenciaMic(respuestaApropiacion.getDocumento().getIdDocumentoMic().getNumeroReferenciaMic());

					} else {
						respuestaSimulacion = new ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos();
						((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setTipoComprobante(respuestaApropiacion.getDocumento().getIdDocumentoCalipso().getTipoComprobante());
						((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setClaseComprobante(respuestaApropiacion.getDocumento().getIdDocumentoCalipso().getClaseComprobante());
						((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setSucursalComprobante(respuestaApropiacion.getDocumento().getIdDocumentoCalipso().getSucursalComprobante());
						((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setNumeroComprobante(respuestaApropiacion.getDocumento().getIdDocumentoCalipso().getNumeroComprobante());
					}
					
					respuestaSimulacion.setIdOperacionShiva(respuesta.getIdSecuencia());

					RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
									TipoResultadoSimulacionEnum.getEnumByCodigoDeimos(respuestaApropiacion.getResultadoApropiacionDocumento().getResultadoApropiacion()),
									respuestaApropiacion.getResultadoApropiacionDocumento().getCodigoError(),
									respuestaApropiacion.getResultadoApropiacionDocumento().getDescripcionError());
					
					respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
					listaResultadoSimulacion.add(respuestaSimulacion);
				}
			} else {
				generarResultadoSimulacionConExcepcionParaApropiacion(
						listaResultadoSimulacion, datosEntrada, "Se ha producido un error en el servicio de Deimos");
			}
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			generarResultadoSimulacionConExcepcionParaApropiacion(
					listaResultadoSimulacion, datosEntrada, e.getMessage());
			
		} finally {
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.idThread));
			
			Thread.currentThread().setName(orgName);
		}
		return listaResultadoSimulacion;
	}
	
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param factura
	 * @param listaMediosPagos
	 * @param e
	 */
	private void generarResultadoSimulacionConExcepcionParaApropiacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, ApropiacionDeimosRto datosEntrada, String mensajeError) {
		
		for (Documento documento : datosEntrada.getEntradaDeimosApropiacionWS().getTransaccion().getListaDocumentos()) {

			ResultadoSimulacion respuestaSimulacion = null;
			
			if (SistemaEnum.MIC.equals(documento.getSistema())) {
				respuestaSimulacion = new ResultadoSimulacionApropiacionDocumentoMicEnDeimos();
				((ResultadoSimulacionApropiacionDocumentoMicEnDeimos)respuestaSimulacion).setNumeroReferenciaMic(documento.getIdDocumentoMic().getNumeroReferenciaMic());

			} else {
				respuestaSimulacion = new ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos();
				((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setTipoComprobante(documento.getIdDocumentoCalipso().getTipoComprobante());
				((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setClaseComprobante(documento.getIdDocumentoCalipso().getClaseComprobante());
				((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setSucursalComprobante(documento.getIdDocumentoCalipso().getSucursalComprobante());
				((ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos)respuestaSimulacion).setNumeroComprobante(documento.getIdDocumentoCalipso().getNumeroComprobante());
			}
			
			respuestaSimulacion.setIdOperacionShiva(this.idThread);
			
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					mensajeError);
			
			respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
			listaResultadoSimulacion.add(respuestaSimulacion);
		}
	}
}
