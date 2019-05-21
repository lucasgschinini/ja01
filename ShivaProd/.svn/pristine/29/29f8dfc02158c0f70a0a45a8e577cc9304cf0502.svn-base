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
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Proveedor;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSapServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionConsultarProveedorSap;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ConsultarProveedoresSapRto;
import ar.com.telecom.shiva.negocio.servicios.IProveedorCapServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapResultado;

public class ConsultarProveedorSapCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private ConsultarProveedoresSapRto datosEntrada;

	/**
	 * 
	 * @param idThread
	 * @param datosEntrada
	 */
	public ConsultarProveedorSapCallable(String idThread, ConsultarProveedoresSapRto datosEntrada) {

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

			SalidaSapConsultaProveedoresWS respuesta = clienteSapServicio.consultarProveedoresParaSimulacion(
							datosEntrada.getEntradaSapConsultaProveedoresWS(), datosEntrada.getIdOperacion());
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				ResultadoSimulacionConsultarProveedorSap respuestaSimulacion = new ResultadoSimulacionConsultarProveedorSap();
								
				// Seteo la lista de errores, en caso de existir
				
				ResultadoInvocacion resultado = respuesta.getResultadoInvocacion();
				if (TipoResultadoSimulacionEnum.OK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoSap(resultado.getResultadoInvocacion()))) {
					Proveedor proveedor = respuesta.getListaProveedores().get(0);
					
					IProveedorCapServicio proveedorCapServicio = (IProveedorCapServicio) Configuracion.ctx.getBean("proveedorCapServicio");
					
					if (proveedorCapServicio.esProveedorInhabilitado(proveedor.getIdBloqueo())) {
						ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
						documentoCapResultado.setTipo(TipoResultadoEnum.ERROR);
						documentoCapResultado.setCodigo("");
						documentoCapResultado.setDescripcion("El proveedor ( " +proveedor.getIdProveedor() +" ) se encuentra inhabilitado.");
						documentoCapResultado.setFecha(new Date());
						respuestaSimulacion.getListaDocumentoCapResultado().add(documentoCapResultado);

						// También seteo un resultado invocación que va a representar a los docCapResultado a nivel de medio de pago
						RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.NOK,
								null,
								Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.verificarPartidasSap.detalle.verObservacionesDocumentosCap"));
						
						respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
					}
				}
								
				
				// En caso de que la respuesta del servicio haya sido errónea, también lo dejo como un resultado de documentos CAP
				// para poder ver en el campo "observaciones documentos CAP"
				
				if (!TipoResultadoSimulacionEnum.OK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoSap(resultado.getResultadoInvocacion()))) {
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
						generarRespuestaResultadoSimulacionConsultarProveedorAnteError(null));
			}
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			listaResultadoSimulacion.addAll(
					generarRespuestaResultadoSimulacionConsultarProveedorAnteError(e));
			
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
	private List<ResultadoSimulacion> generarRespuestaResultadoSimulacionConsultarProveedorAnteError(Throwable e) {

		String mensajeError = Validaciones.isObjectNull(e) ? "WS Consultar Proveedores SAP: Falla de conexión" : e.getMessage();
		
		if (Validaciones.isObjectNull(mensajeError)) {
			if (!Validaciones.isObjectNull(e) && !Validaciones.isObjectNull(e)) {
				e.printStackTrace();
				mensajeError = "WS Consultar Proveedores SAP: Fallo interno post recepcion de respuesta (" + e + ")";
			}
		}
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		ResultadoSimulacionConsultarProveedorSap 
								resultadoSimulacionConsultarProveedorSap = new ResultadoSimulacionConsultarProveedorSap();
		
		ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
		documentoCapResultado.setTipo(TipoResultadoEnum.ERROR_SERVICIO);
		documentoCapResultado.setCodigo(null);
		documentoCapResultado.setDescripcion(mensajeError);
		documentoCapResultado.setFecha(new Date());
		
		resultadoSimulacionConsultarProveedorSap.getListaDocumentoCapResultado().add(documentoCapResultado);
		
		RespuestaInvocacion resultadoInvocacion = 
									new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.ERROR_SERVICIO,
											null,
											mensajeError);

		resultadoSimulacionConsultarProveedorSap.getListaRespuestasInvocacion().add(resultadoInvocacion);
		listaResultadoSimulacion.add(resultadoSimulacionConsultarProveedorSap);
		
		return listaResultadoSimulacion;
	}
}
