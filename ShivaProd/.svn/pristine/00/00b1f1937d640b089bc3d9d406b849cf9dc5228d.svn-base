package ar.com.telecom.shiva.negocio.executor.callable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSapServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionVerificarPartidasSap;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.VerificarPartidasSapRto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapResultado;

public class VerificarPartidasSapCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private VerificarPartidasSapRto datosEntrada;
	
	/**
	 * 
	 * @param idThread
	 * @param datosEntrada
	 */
	public VerificarPartidasSapCallable(String idThread, VerificarPartidasSapRto datosEntrada) {

		if (idThread == null) {
            throw new NullPointerException("idThread cannot be null");
        }
		this.idThread = idThread + "." + this.getClass().getSimpleName();
		this.datosEntrada = datosEntrada;
	}
	
	/**
	 * 
	 */
	public List<ResultadoSimulacion> call() throws Exception {
		final String orgName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.idThread);
        Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo {0}.", this.idThread));
        
        List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		try {
			IClienteSapServicio clienteSapServicio = (IClienteSapServicio) Configuracion.ctx.getBean("clienteSapServicio");
			
			SalidaSapVerificacionPartidasWS respuesta = clienteSapServicio.verificarPartidas(datosEntrada.getEntradaSapVerificacionPartidas());
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				ResultadoSimulacionVerificarPartidasSap respuestaSimulacion = new ResultadoSimulacionVerificarPartidasSap();
				
				// Seteo la lista de errores, en caso de existir
				
				for (ResultadoInvocacion resultado : respuesta.getListaverificaciones()) {
					ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
					documentoCapResultado.setTipo(TipoResultadoEnum.getEnumByDescripcionSap(resultado.getResultadoInvocacion()));
					documentoCapResultado.setCodigo(resultado.getCodigoError());
					documentoCapResultado.setDescripcion(resultado.getDescripcionError());
					documentoCapResultado.setFecha(new Date());
					
					respuestaSimulacion.getListaDocumentoCapResultado().add(documentoCapResultado);
				}
				
				// También seteo un resultado invocación que va a representar a los docCapResultado a nivel de medio de pago
				if (!respuesta.getListaverificaciones().isEmpty()) {
					RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
							TipoResultadoSimulacionEnum.NOK,
							null,
							Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.verificarPartidasSap.detalle.verObservacionesDocumentosCap"));
		
					respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
				}
				
				// En caso de que la respuesta del servicio haya sido errónea, también lo dejo como un resultado de documentos CAP
				// para poder ver en el campo "observaciones documentos CAP"
				
				if (!TipoResultadoSimulacionEnum.OK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoSap(respuesta.getResultadoInvocacion().getResultadoInvocacion()))) {
					ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
					documentoCapResultado.setTipo(TipoResultadoEnum.getEnumByDescripcionSap(respuesta.getResultadoInvocacion().getResultadoInvocacion()));
					documentoCapResultado.setCodigo(respuesta.getResultadoInvocacion().getCodigoError());
					documentoCapResultado.setDescripcion(respuesta.getResultadoInvocacion().getDescripcionError());
					documentoCapResultado.setFecha(new Date());
					
					respuestaSimulacion.getListaDocumentoCapResultado().add(documentoCapResultado);
				}

				// Seteo el resultado de la invocacion, para poder evaluar.
				
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.getEnumByCodigoSap(respuesta.getResultadoInvocacion().getResultadoInvocacion()),
											respuesta.getResultadoInvocacion().getCodigoError(),
											respuesta.getResultadoInvocacion().getDescripcionError());
						
				respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
				
				
				listaResultadoSimulacion.add(respuestaSimulacion);
				
				
			} else {

				listaResultadoSimulacion.addAll(
						generarRespuestaResultadoSimulacionSolicitudGeneracionCompensacinSapAnteError(null));
			}
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			listaResultadoSimulacion.addAll(
					generarRespuestaResultadoSimulacionSolicitudGeneracionCompensacinSapAnteError(e));
			
		} finally {
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.idThread));
			
			Thread.currentThread().setName(orgName);
		}
		return listaResultadoSimulacion;
		
	}

	/**
	 * 
	 * @param factura
	 * @param listaMediosPagoAEnviar
	 * @param e
	 * @return
	 */
	private List<ResultadoSimulacion> generarRespuestaResultadoSimulacionSolicitudGeneracionCompensacinSapAnteError(Throwable e) {

		String mensajeError = Validaciones.isObjectNull(e) ? "WS Verificacion Partidas SAP: Falla de conexión" : e.getMessage();
		
		if (Validaciones.isObjectNull(mensajeError)) {
			if (!Validaciones.isObjectNull(e) && !Validaciones.isObjectNull(e)) {
				e.printStackTrace();
				mensajeError = "WS Verificacion Partidas SAP: Fallo interno post recepcion de respuesta (" + e + ")";
			}
		}

		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		ResultadoSimulacionVerificarPartidasSap 
								resultadoSimulacionSolicitudGeneracionCompensacionSap = new ResultadoSimulacionVerificarPartidasSap();
		
		ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
		documentoCapResultado.setTipo(TipoResultadoEnum.ERROR_SERVICIO);
		documentoCapResultado.setCodigo(null);
		documentoCapResultado.setDescripcion(mensajeError);
		documentoCapResultado.setFecha(new Date());
		
		resultadoSimulacionSolicitudGeneracionCompensacionSap.getListaDocumentoCapResultado().add(documentoCapResultado);
		
		RespuestaInvocacion resultadoInvocacion = 
									new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.ERROR_SERVICIO,
											null,
											mensajeError);

		resultadoSimulacionSolicitudGeneracionCompensacionSap.getListaRespuestasInvocacion().add(resultadoInvocacion);
		listaResultadoSimulacion.add(resultadoSimulacionSolicitudGeneracionCompensacionSap);
		
		return listaResultadoSimulacion;
	}
}
