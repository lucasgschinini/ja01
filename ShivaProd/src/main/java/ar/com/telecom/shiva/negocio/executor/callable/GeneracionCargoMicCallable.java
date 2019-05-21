package ar.com.telecom.shiva.negocio.executor.callable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoCreditoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoDebitoMic;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoMicRto;

public class GeneracionCargoMicCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private GeneracionCargoMicRto datosEntrada;
	
	
	public GeneracionCargoMicCallable(String idThread, GeneracionCargoMicRto datosEntrada) {
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
			ResultadoSimulacion respuestaSimulacion = null;
			if (datosEntrada.isTipoDebito()) {
				respuestaSimulacion = this.ejecutarSimulacionGeneracionCargosDebitoMic(datosEntrada.getEntradaMicGeneracionCargosJms());
			} else {
				respuestaSimulacion = this.ejecutarSimulacionGeneracionCargosCreditoMic(datosEntrada.getEntradaMicGeneracionCargosJms());
			}
			
			listaResultadoSimulacion.add(respuestaSimulacion);
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			ResultadoSimulacion respuestaSimulacion = 
					datosEntrada.isTipoDebito()?new ResultadoSimulacionGeneracionCargoDebitoMic(): 
						new ResultadoSimulacionGeneracionCargoCreditoMic();
			
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null, 
					e.getMessage());
			
			respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		 	respuestaSimulacion.setIdOperacionShiva(datosEntrada.getOperacionTransaccionFormateado());
		 	
			listaResultadoSimulacion.add(respuestaSimulacion);
			
		} finally {
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.idThread));
			
			Thread.currentThread().setName(orgName);
		}
		return listaResultadoSimulacion;
		
	}
	
	
	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ResultadoSimulacionGeneracionCargoCreditoMic ejecutarSimulacionGeneracionCargosCreditoMic(
			MicTransaccionGeneracionCargosDto  entrada) {
		
		IMicJmsSyncServicio micJmsSyncServicio = (IMicJmsSyncServicio) Configuracion.ctx.getBean("micJmsSyncServicio");
		ResultadoSimulacionGeneracionCargoCreditoMic respuestaSimulacion = new ResultadoSimulacionGeneracionCargoCreditoMic();
		
		MicRespuestaGeneracionCargoSalida salida = null;
		try {
			salida = micJmsSyncServicio.simularCargoCredito(entrada);
		} catch (JmsExcepcion e) {
			
			respuestaSimulacion.setIdOperacionShiva(datosEntrada.getOperacionTransaccionFormateado());

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
					
			respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		}

		if (!Validaciones.isObjectNull(salida)) {
			
			respuestaSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());

			// Datos de respuesta
			respuestaSimulacion.setInteresesTrasladados(salida.getDetalleInteresesGenerados().getInteresesGeneradosNoRegulados());
			respuestaSimulacion.setInteresesBonificados(salida.getDetalleInteresesGenerados().getInteresesBonificadosNoRegulados());

			MicResultado micResultado = salida.getResultadoLLamadaServicio();
			if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()))
					&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(micResultado.getCodigoError())
					&& (Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) 
							|| (!Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) && BigDecimal.ZERO.compareTo(respuestaSimulacion.getInteresesTrasladados()) < 0))) {
				
				// Seteo en la respuesta la necesidad de actualizar el acuerdo
				respuestaSimulacion.setRequiereBuscarAcuerdoActivo(true);
			}
			// Resultados posibles: OK / ER 
			
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getResultadoLLamadaServicio().getResultadoInvocacion()),
								salida.getResultadoLLamadaServicio().getCodigoError(),
								salida.getResultadoLLamadaServicio().getDescripcionError());
								
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		 	
		} 
		
		return respuestaSimulacion;
	}


	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ResultadoSimulacionGeneracionCargoDebitoMic ejecutarSimulacionGeneracionCargosDebitoMic(
			MicTransaccionGeneracionCargosDto entrada) {
		
		IMicJmsSyncServicio micJmsSyncServicio = (IMicJmsSyncServicio) Configuracion.ctx.getBean("micJmsSyncServicio");
		ResultadoSimulacionGeneracionCargoDebitoMic respuestaSimulacion = new ResultadoSimulacionGeneracionCargoDebitoMic();
		
		MicRespuestaGeneracionCargoSalida salida = null;
		try {
			salida = micJmsSyncServicio.simularCargoCredito(entrada);
		} catch (JmsExcepcion e) {
			respuestaSimulacion.setIdOperacionShiva(datosEntrada.getOperacionTransaccionFormateado());

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
					
			respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		}
		
		if (!Validaciones.isObjectNull(salida)) {
			
			respuestaSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());

			// Datos de respuesta
			respuestaSimulacion.setInteresesTrasladados(salida.getDetalleInteresesGenerados().getInteresesGeneradosRegulados());
			respuestaSimulacion.setInteresesBonificados(salida.getDetalleInteresesGenerados().getInteresesBonificadosRegulados());

			MicResultado micResultado = salida.getResultadoLLamadaServicio();
			if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()))
					&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(micResultado.getCodigoError())
					&& (Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) 
							|| (!Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) && BigDecimal.ZERO.compareTo(respuestaSimulacion.getInteresesTrasladados()) < 0))) {
				
				// Seteo en la respuesta la necesidad de actualizar el acuerdo
				respuestaSimulacion.setRequiereBuscarAcuerdoActivo(true);
			}
			// Resultados posibles: OK / ER 

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getResultadoLLamadaServicio().getResultadoInvocacion()),
								salida.getResultadoLLamadaServicio().getCodigoError(),
								salida.getResultadoLLamadaServicio().getDescripcionError());
								
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		} 
		
		return respuestaSimulacion;
	}
}
