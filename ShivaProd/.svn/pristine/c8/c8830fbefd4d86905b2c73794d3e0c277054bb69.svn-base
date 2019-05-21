package ar.com.telecom.shiva.negocio.executor.callable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoCreditoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoDebitoCalipso;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoCalipsoRto;

public class GeneracionCargoCalipsoCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private GeneracionCargoCalipsoRto datosEntrada;
	
	public GeneracionCargoCalipsoCallable(String idThread, GeneracionCargoCalipsoRto datosEntrada) {
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
				respuestaSimulacion = this.ejecutarSimulacionGeneracionCargosDebitoCalipso(datosEntrada.getEntradaCalipsoGeneracionCargosWs());
			} else {
				respuestaSimulacion = this.ejecutarSimulacionGeneracionCargosCreditoCalipso(datosEntrada.getEntradaCalipsoGeneracionCargosWs());
			}
			
			listaResultadoSimulacion.add(respuestaSimulacion);
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			ResultadoSimulacion respuestaSimulacion = 
					datosEntrada.isTipoDebito()?new ResultadoSimulacionGeneracionCargoDebitoCalipso(): 
						new ResultadoSimulacionGeneracionCargoCreditoCalipso();
			
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
	private ResultadoSimulacionGeneracionCargoCreditoCalipso ejecutarSimulacionGeneracionCargosCreditoCalipso(
			EntradaCalipsoCargosWS entrada) {
		
		IClienteCalipsoServicio clienteCalipsoServicio = 
				(IClienteCalipsoServicio) Configuracion.ctx.getBean("clienteCalipsoServicio");
		ResultadoSimulacionGeneracionCargoCreditoCalipso respuestaSimulacion = new ResultadoSimulacionGeneracionCargoCreditoCalipso();
		
		SalidaCalipsoCargosWS respuesta = null;
		try {
			respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.COBRO);
		} catch (NegocioExcepcion e) {
			
			respuestaSimulacion.setIdOperacionShiva(datosEntrada.getOperacionTransaccionFormateado());

			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
			
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		}
				
		if (!Validaciones.isObjectNull(respuesta)) {
			
			respuestaSimulacion.setIdOperacionShiva(respuesta.getIdOperacionTransaccion());

			// Datos de respuesta
			respuestaSimulacion.setInteresesTrasladados(respuesta.getMontoCalculadoMora());
			respuestaSimulacion.setInteresesBonificados(respuesta.getInteresesBonificados());

			// Verifico en la respuesta si el acuerdo se encuentra inactivo
			if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()))
					&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(respuesta.getResultado().getCodigoError())
					&& (Validaciones.isObjectNull(respuesta.getMontoCalculadoMora()) 
							|| (!Validaciones.isObjectNull(respuesta.getMontoCalculadoMora()) && BigDecimal.ZERO.compareTo(respuesta.getMontoCalculadoMora()) < 0))) {
					
				// Seteo en la respuesta la necesidad de actualizar el acuerdo
				respuestaSimulacion.setRequiereBuscarAcuerdoActivo(true);
			}
				
			// Resultados posibles: OK / NOK / ERR

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()),
								respuesta.getResultado().getCodigoError(),
								respuesta.getResultado().getDescripcionError());
								
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);

		} else {
			return null;
		}
		
		return respuestaSimulacion;
	}
	
	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ResultadoSimulacionGeneracionCargoDebitoCalipso ejecutarSimulacionGeneracionCargosDebitoCalipso(
			EntradaCalipsoCargosWS entrada) {
		
		IClienteCalipsoServicio clienteCalipsoServicio = 
				(IClienteCalipsoServicio) Configuracion.ctx.getBean("clienteCalipsoServicio");
		ResultadoSimulacionGeneracionCargoDebitoCalipso respuestaSimulacion = new ResultadoSimulacionGeneracionCargoDebitoCalipso();
		
		SalidaCalipsoCargosWS respuesta = null;
		try {
			respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.COBRO);
		} catch (NegocioExcepcion e) {
			
			respuestaSimulacion.setIdOperacionShiva(datosEntrada.getOperacionTransaccionFormateado());

			// Resultados posibles: OK / NOK / ERR

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
			
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
		}
				
		if (!Validaciones.isObjectNull(respuesta)) {
			
			respuestaSimulacion.setIdOperacionShiva(respuesta.getIdOperacionTransaccion());

			// Datos de respuesta
			respuestaSimulacion.setInteresesTrasladados(respuesta.getMontoCalculadoMora());
			respuestaSimulacion.setInteresesBonificados(respuesta.getInteresesBonificados());
			
			// Verifico en la respuesta si el acuerdo se encuentra inactivo
			if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()))
					&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(respuesta.getResultado().getCodigoError())
					&& (Validaciones.isObjectNull(respuesta.getMontoCalculadoMora()) 
							|| (!Validaciones.isObjectNull(respuesta.getMontoCalculadoMora()) && BigDecimal.ZERO.compareTo(respuesta.getMontoCalculadoMora()) < 0))) {
				
				// Seteo en la respuesta la necesidad de actualizar el acuerdo
				respuestaSimulacion.setRequiereBuscarAcuerdoActivo(true);
			}
			
			// Resultados posibles: OK / NOK / ERR

			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()),
								respuesta.getResultado().getCodigoError(),
								respuesta.getResultado().getDescripcionError());
								
		 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);

		}
		
		return respuestaSimulacion;
	}
}
