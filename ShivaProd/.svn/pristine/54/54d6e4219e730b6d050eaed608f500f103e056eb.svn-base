package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.ResultadoApropiacionDocumento;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.batch.bean.SubdiarioBatch;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionManualServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesamientoHilosCobrosServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaEstadoGrupos;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

/**
 * 
 * @author Lucas Schinini
 *
 */
@Service
public class CobroBatchImputacionManualServicioImpl implements ICobroImputacionManualServicio {

	@Autowired IWorkflowCobros workflowCobros;
	@Autowired IValorServicio valorServicio;
	@Autowired ICobroDao cobroDao;
	@Autowired IGenericoDao genericoDao;
	@Autowired IContabilidadServicio contabilidadServicio;
	@Autowired IScardServicio scardServicio;
	@Autowired ITareaServicio tareaServicio;
	@Autowired ILdapServicio ldapServicio;
	@Autowired ICobroOnlineDao cobroOnlineDao;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	@Autowired private IClienteCalipsoServicio clienteCalipsoServicio;
	@Autowired ITransaccionCobroServicio transaccionCobroServicio;
	@Autowired private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired private ITipoMedioPagoDao tipoMedioPagoDao;
	@Autowired IOrganismoDao organismoDao;
	@Autowired private IMensajeriaTransaccionDao mensajeriaTransaccionDao;
	@Autowired IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio;
	@Autowired private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	@Autowired private ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;
	@Autowired private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	@Autowired private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired private ICobroBatchServicio cobroBatchServicio;
	@Autowired private ICobroImputacionServicio cobroImputacionServicio;
	
	/***************************************************************************
	 * BATCH IMPUTACION MANUAL COBRO
	 *************************************************************************/
	/**
	 * Método que recibe un cobro y se comunica con Calipso y MIC. 
	 * 
	 * @throws NegocioExcepcion 
	 */
//	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void imputarCobroManual(ImputacionCobroRto datosEntrada) throws NegocioExcepcion {
		ControlVariablesSingleton.setReintentoExcepcion(null);
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		ShvCobTransaccionSinOperacion transaccion = null;
		
		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			Boolean ocurrioError = false;
			Boolean cobroPendienteMic = false;
			int cantTransaccionesProcesadasConsecutivas = 0;
			Long idOperacion = datosEntrada.getCobroAProcesar().getIdOperacion();
			Long grupoActual = null;
			SistemaEnum sistemaActual = null;
			SociedadEnum sociedadActual = null;
			
			while(true){
				
				//IF DEL HORARIO 20HS
				try {
					if (datosEntrada.hayQueEjecutarValidacionesDeCorte()) {
						if (!Utilidad.verificarRangoHorarioBatchCobros(parametroServicio)) {
							break;
						}
						
						//Si procesé menos transacciones consecutivas que el limite, sigo, sino corto el proceso
						if (cantTransaccionesProcesadasConsecutivas >= datosEntrada.getCantidadTransaccionesAProcesarPorHilo()){
							break;
						}
					}
				
					/**
					 * Si el cobro quedó en ERROR_EN_DESAPROPIACION, y se vuelve a enviar la desapropiación, tenemos que buscar las transacciones en
					 * este estado EN_PROCESO_DESAPROPIACION_POR_ERROR_DESAPROPIACION.
					 * 
					 */
					
					transaccion = cobroDao.buscarTransaccionAProcesarPorErrorDesapropiacionPorIdOperacion(idOperacion);
					
					if(Validaciones.isObjectNull(transaccion)){
						//Busco la transaccion que tengo que procesar
						transaccion = cobroDao.buscarTransaccionAProcesarPorIdOperacionImpManual(idOperacion);
						
					}
					
					//Si NO trae una transaccion asumo que termine de procesar el cobro y hago un break del while
					if(Validaciones.isObjectNull(transaccion)){
						
						if (!Validaciones.isObjectNull(grupoActual)){
							
							generarTareaDeAplicacionManualEnCasoDeErrorAnterior(idOperacion,datosEntrada.getCobroAProcesar().getIdCobro(),grupoActual);
							generarTareaYAvanzarTransacciones(idOperacion,grupoActual,sociedadActual,sistemaActual,datosEntrada.getCobroAProcesar().getIdCobro());
							//Avanzo el cobro a pendiente confirmacion manual
							cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL, usuarioBatchCobroImputacion);
						} else{
							
							boolean tareaGenerada = generarTareaDeAplicacionManualEnCasoDeErrorAnterior(idOperacion,datosEntrada.getCobroAProcesar().getIdCobro(),grupoActual);
							
							if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(datosEntrada.getCobroAProcesar().getEstadoCobro())){
								
								if (tareaGenerada){
									//Como se generó la tarea, lo avanzo a pendiente confirmacion manual
									cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL, usuarioBatchCobroImputacion);
									
								} else {
									//No tengo nada para seguir apropiando, avanzo el cobro a en proceso
									cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PROCESO, usuarioBatchCobroImputacion);
								}

							} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(datosEntrada.getCobroAProcesar().getEstadoCobro())){
								
								//No tengo nada para seguir apropiando, avanzo el cobro al estado correspondiente
								cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL, usuarioBatchCobroImputacion);
							}
						}
						
						break;
					}
					
					transaccion = evaluarTransaccion(transaccion);
					
					//Me guardo el grupo de la transaccion, viene ordenado de menor a mayor. Para saber cuándo finalizo de apropiar el grupo XX completo.
					if (!Validaciones.isObjectNull(transaccion.getGrupo())){
						
						if (Validaciones.isObjectNull(grupoActual)){
							
							//Solamente seteo el grupo
							grupoActual = transaccion.getGrupo();
							sistemaActual = transaccion.getSistema();
							sociedadActual = transaccion.getSociedad();
						
						} else if (!grupoActual.equals(transaccion.getGrupo())){
							
							//genera la tarea, avisando que el grupo XX se terminó de apropiar
							generarTareaYAvanzarTransacciones(idOperacion,grupoActual,sociedadActual,sistemaActual,datosEntrada.getCobroAProcesar().getIdCobro());
							
							
							if(cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)){
								//Avanzo el cobro a pendiente confirmacion manual y en proceso aplicacion externa
								cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL, usuarioBatchCobroImputacion);
								break;
							}
							//Avanzo el cobro a pendiente confirmacion manual y en proceso aplicacion externa
							cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA, usuarioBatchCobroImputacion);
							
							//Seteo el nuevo grupo y genero la tarea de finalización de apropiacion del grupo anterior
							grupoActual = transaccion.getGrupo();
							sistemaActual = transaccion.getSistema();
							sociedadActual = transaccion.getSociedad();
							
						}
					}
					
					
					
					if (EstadoTransaccionEnum.PENDIENTE.equals(transaccion.getEstadoProcesamiento())
							|| EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
						
						cantTransaccionesProcesadasConsecutivas++;
						
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, true);
						
						// Actualizo el estado a "EN PROCESO" si el estado actual es "PENDIENTE".
						if(EstadoTransaccionEnum.PENDIENTE.equals(transaccion.getEstadoProcesamiento())){
							transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.EN_PROCESO_APROPIACION);
						}
					
						ShvCobFacturaSinOperacion factura = transaccion.getFactura(); 
						
						//Lista de medios de pago a enviar
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoMIC = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.MIC);
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoCalipso = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.CALIPSO);
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoShiva = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.SHIVA);
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoUsuario = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.USUARIO);
						
						EstadoFacturaMedioPagoEnum estadoMedioPagoMIC = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoMIC(transaccion);
						EstadoFacturaMedioPagoEnum estadoMedioPagoCalipso = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoCalipso(transaccion);
						EstadoFacturaMedioPagoEnum estadoMedioPagoShiva = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoShiva(transaccion);

						Utilidad.tracearTiempo(getClass(), "Tiempo en comenzar alguna apropiacion", fechaHoraInicioNanoTime);
						
						if(!Validaciones.isObjectNull(factura)){
							if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
								
								factura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
								factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
								
								for (ShvCobMedioPagoSinOperacion mp : listaMediosPagoUsuario) {
									
									if (mp instanceof ShvCobMedioPagoUsuarioSinOperacion
											&& !TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())
											&& !TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())
											&& !TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())){
										
										mp.setEstado(factura.getEstado());
									}
								}
							}
						}
						
						if (!haySaldoACobrar(transaccion)){
							
							//Si hay medio de pago shiva y están en estado pendiente apropia en shiva
							if (Validaciones.isCollectionNotEmpty(listaMediosPagoShiva)){
								if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoShiva)) {
									cobroBatchSoporteImputacionServicio.apropiacionShiva(listaMediosPagoShiva,transaccion, usuarioBatchCobroImputacion);
									transaccion = guardarTransaccionSinOperacion(transaccion);
									//traza despues de guardar la transaccion
									cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
								}
							}
								
							Date fechaCobranza = null;
							MonedaEnum monedaOperacion = null;
							
							if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
								fechaCobranza = transaccion.getTratamientoDiferencia().getFechaValor();
								monedaOperacion = transaccion.getTratamientoDiferencia().getMoneda();
							}
							
							if(!Validaciones.isObjectNull(factura)){
								monedaOperacion = factura.getMonedaImporteCobrar();
								fechaCobranza = factura.getFechaValor();
							}	
							
							if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(cobroBatchSoporteImputacionServicio.getEstadoMedioPagoShiva(transaccion))
									|| Validaciones.isObjectNull(estadoMedioPagoShiva)){
									
								if (Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)){
									if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoCalipso)) {
										cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(listaMediosPagoCalipso, null, transaccion, fechaCobranza, monedaOperacion);
									}
								}
									
								if (Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
									if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
										cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, factura,transaccion);
										cobroPendienteMic = true;
										break;
									}
									
								}
							}
						}
						
						/**LOGICA DE DESAPROPIACION PARCIAL POR GRUPO**/
					} else if (EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA.equals(transaccion.getEstadoProcesamiento())){
						if (!cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)){
							cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(transaccion.getIdOperacion(),datosEntrada.getCobroAProcesar().getIdCobro(),grupoActual,usuarioBatchCobroImputacion,true);
							break;
							
						} else {
							//No tengo nada para seguir apropiando, avanzo el cobro a en proceso
							cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PROCESO, usuarioBatchCobroImputacion);
							break;
						}
					} else if (EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION_POR_ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
					
						if (!cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)){
							cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(transaccion.getIdOperacion(),datosEntrada.getCobroAProcesar().getIdCobro(),grupoActual,usuarioBatchCobroImputacion,false);
							break;
							
						} else {
							//No tengo nada para seguir apropiando, avanzo el cobro a en proceso
							cobroBatchSoporteImputacionServicio.cambiarEstadoCobroImputacionManual(idOperacion, Estado.COB_PROCESO, usuarioBatchCobroImputacion);
						}
					
					}
					
					//hago esto porque sino pierdo los cambios que hice en la transaccion
					transaccion = cobroBatchSoporteImputacionServicio.verificarEstadoTransaccion(transaccion, usuarioBatchCobroImputacion);
					
					//traza antes de guardar la transaccion
					cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
					
					transaccion = guardarTransaccionSinOperacion(transaccion);
					
					//traza despues de guardar la transaccion
					cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
					
					// Si la transaccion esta en uno de los estados de error no hago mas nada
					if (EstadoTransaccionEnum.listarEstadosError().contains(transaccion.getEstadoProcesamiento())) {
						ocurrioError=true;
						break;
					}
					
					if(!EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())
							&& !EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento())
							&& !EstadoTransaccionEnum.ERROR_APL_MANUAL_RECHAZADA.equals(transaccion.getEstadoProcesamiento())){
						break;
					}
					
				} catch (ReintentoExcepcion e) {
					cobroImputacionServicio.setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, true,transaccion);
				}
				
			} /*Fin FOR transaccion*/
			
			if (cobroPendienteMic){
				
				transaccion = cobroBatchSoporteImputacionServicio.verificarEstadoTransaccion(transaccion, usuarioBatchCobroImputacion);
				
				transaccion = guardarTransaccionSinOperacion(transaccion);
				
				cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
			}

			// Si ocurrio un error debo enviar la desapropiacion
			if(ocurrioError){
				/**LOGICA DE DESAPROPIACION PARCIAL POR GRUPO**/
				cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(idOperacion,datosEntrada.getCobroAProcesar().getIdCobro(), grupoActual, usuarioBatchCobroImputacion,false);
			}
		
			// Asumo que si ambos flags estan en false al salir del while debo esperar la respuesta de MIC.
			
			Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
		

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ReintentoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			transaccion = null;
		}
	}
	
	
	/**
	 * Este método se encarga de levantar las transacciones apropiadas, generar tarea y enviar el mail de cobro con aplicacion manual pendiente de confirmar, por si 
	 * hubo un error anteriormente.
	 * @param idOperacion
	 * @param idCobro
	 * @throws NegocioExcepcion
	 */
	private boolean generarTareaDeAplicacionManualEnCasoDeErrorAnterior(Long idOperacion, Long idCobro,Long grupoActual) throws NegocioExcepcion {
		
		boolean tareaGenerada = false;

		try {
			List<ResultadoBusquedaEstadoGrupos> listaEstadoGrupos = new ArrayList<ResultadoBusquedaEstadoGrupos>();
			listaEstadoGrupos = cobroDao.buscarEstadoGruposConAplicacionManual(idOperacion,grupoActual);
			Long grupo = null;
			
			if (Validaciones.isCollectionNotEmpty(listaEstadoGrupos)){
				
				for (ResultadoBusquedaEstadoGrupos resultado : listaEstadoGrupos) {

					if (EstadoTransaccionEnum.listarEstadosError().contains(resultado.getEstadoTransaccion())
							|| EstadoTransaccionEnum.APROPIADA.equals(resultado.getEstadoTransaccion())
							|| EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(resultado.getEstadoTransaccion())	
							){
					
						if(Validaciones.isObjectNull(grupo)){
							grupo = resultado.getGrupo();
							generarTareaYAvanzarTransacciones(idOperacion, grupo, resultado.getSociedad(),resultado.getSistema(), idCobro);
							tareaGenerada = true;
						} else{
							if (!grupo.equals(resultado.getGrupo())){
								grupo = resultado.getGrupo();
								generarTareaYAvanzarTransacciones(idOperacion,grupo, resultado.getSociedad(),resultado.getSistema(), idCobro);
								tareaGenerada = true;
							}
						}
					} 
					
				}
			}
				
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return tareaGenerada;
		
	}

	/**
	 * Método que evalua la transaccion actual en estado PENDIENTE_FINALIZACION_TRANSACCION. busca la transaccion de la cual depende por el numero transaccion.
	 * Si esa transaccion está en un estado diferente a PENDIENTE O ERROR DESAPROPIACION, la transaccion actual se avanza al estado PENDIENTE
	 * @param transaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobTransaccionSinOperacion evaluarTransaccion(ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion {
	
		try {
			
			if(EstadoTransaccionEnum.PENDIENTE_FINALIZACION_TRANSACCION.equals(transaccion.getEstadoProcesamiento())){
				
				cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
				
				ShvCobTransaccionSimple transaccionDependiente;
				
				//Busco la transaccion simple por idOperacion y numero
				transaccionDependiente = cobroDao.buscarTransaccionSimplePorIdOperacionYNumeroTransaccion(transaccion.getIdOperacion(), transaccion.getNumeroTransaccionDependencia());
				if (!Validaciones.isObjectNull(transaccionDependiente)){
					
					Traza.auditoria(this.getClass(), "Transaccion dependiente encontrada:" + transaccionDependiente.getOperacionTransaccionFormateado()
							+ ". En estado: [" + transaccionDependiente.getEstadoProcesamiento() + "]" );
					
					if(!EstadoTransaccionEnum.PENDIENTE.equals(transaccionDependiente.getEstadoProcesamiento())
							&& !EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccionDependiente.getEstadoProcesamiento())
							&& !EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL.equals(transaccionDependiente.getEstadoProcesamiento())){
						
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
						
						Traza.auditoria(this.getClass(), "Se avanza la transaccion "+transaccion.getOperacionTransaccionFormateado() +"al estado ["
						+ transaccion.getEstadoProcesamiento() + "]" );
						
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
					} //else {
						//throw new NegocioExcepcion("Estado incorrecto de la transaccion dependiente");
					//}
				} else {
					throw new NegocioExcepcion("No se encontró la transaccion dependiente");
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return transaccion;
	}

/**
 * 
 * @param idOperacion
 * @param grupo
 * @param sociedad
 * @param sistema
 * @param idCobro
 * @throws NegocioExcepcion
 */
	@Transactional(readOnly = false, rollbackFor = {NegocioExcepcion.class, PersistenciaExcepcion.class}, propagation=Propagation.REQUIRED)
	private void generarTareaYAvanzarTransacciones(Long idOperacion, Long grupo, SociedadEnum sociedad, SistemaEnum sistema, Long idCobro) throws NegocioExcepcion {

		List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		try {
			Traza.advertencia(this.getClass(), "--------------------Inicio metodo generacion de tarea y avance de transacciones-------------");
			Traza.advertencia(this.getClass(), "--------------------Inicio de busqueda de transacciones por id operacion y grupo------------");
			
			listaTransaccionesPorGrupo = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYGrupo(idOperacion, grupo);
			
			Traza.advertencia(this.getClass(), "--------------------Fin de busqueda de transacciones por id operacion y grupo------------");
			
			EstadoTransaccionEnum estadoDelGrupo = listaTransaccionesPorGrupo.get(0).getEstadoProcesamiento();
			
			Traza.advertencia(this.getClass(), "--------------------Inicio del calculo del importe parcial por grupo------------");
			BigDecimal importeParcial = cobroBatchSoporteImputacionServicio.calcularImporteParcialDelGrupo(listaTransaccionesPorGrupo);
			Traza.advertencia(this.getClass(), "--------------------Fin del calculo del importe parcial por grupo------------");
			
			Traza.advertencia(this.getClass(), "--------------------Inicio de generacion de mail y tarea de imputacion manual------------");
			
			cobroBatchSoporteImputacionServicio.enviarMailyGenerarTareaImputacionManual(idOperacion,grupo,estadoDelGrupo,sociedad,sistema,idCobro,importeParcial);
			
			Traza.advertencia(this.getClass(), "--------------------Fin de generacion de mail y tarea de imputacion manual------------");
			
			Traza.advertencia(this.getClass(), "--------------------Inicio de avance de transacciones a PENDIENTE DE CONFIRMACION APLICACION MANUAL------------");
			
			cobroBatchSoporteImputacionServicio.avanzarTransacciones(listaTransaccionesPorGrupo,EstadoTransaccionEnum.PENDIENTE_CONFIRMAR_APL_MANUAL);

			Traza.advertencia(this.getClass(), "--------------------Fin de avance de transacciones a PENDIENTE DE CONFIRMACION APLICACION MANUAL------------");
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	private boolean haySaldoACobrar(ShvCobTransaccionSinOperacion transaccion){
		for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()) {
			if (TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
				return true;
			}
		}
		return false;
	}

	

	
	/**********************************************************************************************
	 * Para las transacciones sin la clase operacion
	 **********************************************************************************************
	 */
	
	/**
	 * Retorna el resultado de la apropiación del documento, true si se apropió correctamente, false si hay algún error
	 * @param resultado
	 * @return
	 */
	
	public boolean estadoApropiacionDocumentoDeimos (ResultadoApropiacionDocumento resultado){
		
		if (OkNokEnum.OK.getDescripcion().equals(resultado.getResultadoApropiacion())
				|| (TipoResultadoEnum.ERROR.equals(resultado.getResultadoApropiacion())
						&& Constantes.DEIMOS_COD_TRANSACCION_YA_PROCESADA.equals(resultado.getCodigoError()))){
			return true;
		} else {
			return false;
		}
	}
	
	
	/***************************************************************************
	 * Batch Proceso Especial de normalización de operaciones
	 * ************************************************************************/
	
	/**
	 * Reenvia la confirmacion del cobro perteneciente a la operacion que recibe por parametro,
	 * a los cobradores 
	 * @param idOperacion
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion  
	 */
	public void reenviarConfirmacionCobros(Long idOperacion) throws NegocioExcepcion {
		try{
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			List<ShvCobCobro> listaCobrosErrorConfirmacion = cobroDao.listarCobrosErrorConfirmacion(idOperacion);
			
			if(Validaciones.isCollectionNotEmpty(listaCobrosErrorConfirmacion)){
				ShvCobCobro cobro = listaCobrosErrorConfirmacion.get(0);
				cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), true, 1);
				revivirCobro(cobro);

				try {
					cobroBatchSoporteImputacionServicio.verificarEstadoCobro(cobro, usuarioBatchCobroImputacion, true);
				} catch (ReintentoExcepcion e) {
					cobroImputacionServicio.setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, false,null);
				}
				
				cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), false, 1);
				
			}else{ 
				String mensaje = "No se ha encontrado el cobro con el id de operacion " + idOperacion + " en estado Error en confirmacion.";
				System.out.println(mensaje);
				Traza.advertencia(getClass(), mensaje);
			}
			
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		} 
	}

	
	/**
	 * Este metodo modifica el estado de las transacciones, tratamientos, facturas y medios de pago.
	 * Ademas borramos la mensajeria.
	 * @param cobro
	 */
	private void revivirCobro(ShvCobCobro cobro) throws NegocioExcepcion{
		try{
			//Actualizacion de estados
			for(ShvCobTransaccion transac : cobro.getTransaccionesOrdenadas()){
				if(EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(transac.getEstadoProcesamiento())){
					transac.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
					
					for(ShvCobMedioPago med : transac.getMediosPago()){
						if(EstadoFacturaMedioPagoEnum.ERROR.equals(med.getEstado()) && !SistemaEnum.SHIVA.equals(med.getSistemaOrigen())){
							med.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
						}
					}
					if(!Validaciones.isObjectNull(transac.getFactura())){
						if(EstadoFacturaMedioPagoEnum.ERROR.equals(transac.getFactura().getEstado())){
							transac.getFactura().setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
						}
					} else if(!Validaciones.isObjectNull(transac.getTratamientoDiferencia())){
						if(EstadoFacturaMedioPagoEnum.ERROR.equals(transac.getTratamientoDiferencia().getEstado())){
							transac.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
						}
					}
				}
			}
			
			//Busco la mensajeria para borrarla
			List<ShvCobTransaccionMensajeriaDetalle> mensajesConfirmacion = cobroDao.buscarConfirmacionFallida(cobro.getOperacion().getIdOperacion());
			
			//Eliminacion de mensajeria de confirmacion del cobro
			for (ShvCobTransaccionMensajeriaDetalle mensaje : mensajesConfirmacion) {
				mensajeriaTransaccionServicio.borrarMensajesPorIdTransaccionMensajeria(mensaje);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	

	public ShvCobCobroSimple modificar(ShvCobCobroSimple cobroModelo) throws NegocioExcepcion{
		try {
			return cobroDao.modificar(cobroModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	public ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion {
		try {
			return cobroDao.buscarCobroPorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public ShvCobCobroSimple buscarCobroSimplePorIdOperacion(Long idOperacion) throws NegocioExcepcion {
		try {
			return cobroDao.buscarCobroSimplePorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public ShvCobCobroSimpleSinWorkflow buscarCobroSimpleSinWorkflowPorIdOperacion(Long idOperacion) throws NegocioExcepcion {
		try {
			return cobroDao.buscarCobroSimpleSinWorkflowPorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public List<ShvCobFactura> buscarFacturaParaReporteRetencion(List<String> idRetenciones) throws NegocioExcepcion {
		try {
			return cobroDao.buscarFacturaParaReporteRetencion(idRetenciones);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public List<SubdiarioBatch> listarCobrosParaSubdiario(GregorianCalendar fechaUltimoDiaMes) throws NegocioExcepcion {
		try {
			List<Map<String, Object>> listaResultado= cobroDao.buscarCobrosParaSubdiario(fechaUltimoDiaMes);
			List<SubdiarioBatch> listaSubdiario = new ArrayList<SubdiarioBatch>();
			for (Map<String, Object> map : listaResultado) {
				SubdiarioBatch subdiario= new SubdiarioBatch(map);
				listaSubdiario.add(subdiario);
			}
			return listaSubdiario;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public List<ResultadoBusquedaCobrosPendientes> listarCobrosPendientesImputacionManual(TipoImputacionEnum tipoImputacion) throws NegocioExcepcion {
		try {
			List<ResultadoBusquedaCobrosPendientes> lista = cobroDao.listarCobrosImputacionManualPendientes(tipoImputacion);
			return lista;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	

	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public ShvCobCobro buscarCobro(Long idCobro) throws NegocioExcepcion {
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			return cobro;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	/**
	 * Devuelve el medio de pago Shiva asociado al id valor
	 */
	public ShvCobMedioPago getMedioPagoShivaPorIdValor(ShvCobCobro cobro, Long idValor){
		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
			for(ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()){
				if(medioPago instanceof ShvCobMedioPagoShiva){
					ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva) medioPago);
					if(!Validaciones.isObjectNull(medioPagoShiva.getIdValor())) {
						if(medioPagoShiva.getIdValor().equals(idValor)){
							return medioPago;
						}
					}
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public Long crear(DTO dto) throws NegocioExcepcion, ValidacionExcepcion {
		return null;
	}

	
	
    
    
    /**
	 * Por cada elemento de la lista "idCobrosADesapropiar" se cambia los estados del cobro y de sus transacciones
	 * para que se pueda enviar a desapropiar. 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void solicitarEnviarDesapropiar(List<String> idCobrosADesapropiar, String usuarioId) throws NegocioExcepcion {
		for (String id : idCobrosADesapropiar) {
			ShvCobCobro modeloCobro = this.buscarCobro(Long.valueOf(id));
			this.setearDesapropiarEnProceso(modeloCobro);
			// Finalizo la tarea reliacionada al cobro que se quiere volver a Desapropiar
			tareaServicio.finalizarTareaCorrecto(
				TipoTareaEnum.COB_REV_COB_DES,
				modeloCobro.getWorkflow().getIdWorkflow(),
				new Date(),
				usuarioId,
				null
			);
		}
	}
	/**
	 * Cambio los estados del cobro y sus transacciones para que se pueda volver a Desapropiar en 
	 * caso que este el cobro en COB_ERROR_DESAPROPIAR
	 * @param modeloCobro
	 * @throws NegocioExcepcion
	 */
	private void setearDesapropiarEnProceso(ShvCobCobro modeloCobro) throws NegocioExcepcion {
		
		if (Estado.COB_ERROR_DESAPROPIACION.equals(modeloCobro.getWorkflow().getEstado())) {
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			// Si un cobro esta en COB_ERROR_DESAPROPIACION para que se vuelva procesar la DESAPROPIACION
			// se deve cambiar el estado del cobro a COB_PROCESO
			cobroBatchServicio.cambiarEstadoCobro(modeloCobro,Estado.COB_PROCESO,usuarioBatchCobroImputacion);
			List<ShvCobTransaccion> listaTransaccionesOrdenadas = modeloCobro.getTransaccionesOrdenadas();

			for (ShvCobTransaccion transaccion : listaTransaccionesOrdenadas) {
				
				if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())) {
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
					this.cambiarEstadoTransaccionesADesapropiar(
						transaccion,
						false,
						false,
						false
					);
				} else if (EstadoTransaccionEnum.listarEstadosError().contains(transaccion.getEstadoProcesamiento())) {
					switch (transaccion.getEstadoProcesamiento()) {
					case ERROR_DEUDA:
						this.cambiarEstadoTransaccionesADesapropiar(
							transaccion,
							true,
							false,
							false
						);
						break;
					case ERROR_MEDIO_PAGO:
						this.cambiarEstadoTransaccionesADesapropiar(
							transaccion,
							false,
							true,
							false
						);
						break;
					case ERROR_MEDIO_PAGO_DEUDA:
						this.cambiarEstadoTransaccionesADesapropiar(
							transaccion,
							true,
							true,
							false
						);
						break;
					case ERROR_TRATAMIENTO:
						this.cambiarEstadoTransaccionesADesapropiar(
							transaccion,
							false,
							false,
							true
						);
						break;
					default:
						break;
					}
					break;
				}
			} /*Fin FOR transaccion*/
			
			try {
				modeloCobro = cobroDao.modificar(modeloCobro);
				// Borro la mensajeria
				mensajeriaTransaccionDao.borrarMensajesPorIdOperacionServicios(
					modeloCobro.getOperacion().getIdOperacion(),
					MensajeServicioEnum.MIC_DESAPROPIACION,
					MensajeServicioEnum.CLP_DESAPROPIACION,
					MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION
				);
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
		}
		
		
	}
	/**
	 * Cambia de estado a la transaccion y sus componentes
	 * @param transaccion
	 * @param ignorarDeuda
	 * @param ignorarMedioPago
	 * @param ignorarTratamiento
	 */
	private void cambiarEstadoTransaccionesADesapropiar(ShvCobTransaccion transaccion, boolean ignorarDeuda, boolean ignorarMedioPago, boolean ignorarTratamiento) {
		
		if (!ignorarDeuda) {
			// FACTURAS
			ShvCobFactura factura = transaccion.getFactura();
			if (!Validaciones.isObjectNull(factura) && EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
				factura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
			}
		}
		if (!ignorarMedioPago) {
			// MEDIO DE PAGO
			if (!Validaciones.isObjectNull(transaccion.getMediosPago())) {
				for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
					if (EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())) {
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
					}
				}
			}
		}
		if (!ignorarTratamiento) {
			// TRATAMIENTO DIFERENCIA
			ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();
			if (!Validaciones.isObjectNull(tratamiento)) {
				if (EstadoFacturaMedioPagoEnum.ERROR.equals(tratamiento.getEstado())) {
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
				}
			}
		}
	}
	
	
	
	/**
	 * 
	 */
	public boolean contieneTransaccionesMic(ShvCobCobro cobro) throws NegocioExcepcion {
		for(ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(cobroBatchSoporteImputacionServicio.existenMediosPagoMic(transaccion) 
					|| (!Validaciones.isObjectNull(transaccion.getFactura()) && (transaccion.getFactura() instanceof ShvCobFacturaMic))
					|| (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia()) && (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())))) {
				return true;
			}
		}
		return false;
	}
	
	
	/***************************************************************************
	 * LO NUEVO
	 * ************************************************************************/
	
	/**
	 * Permite modificar el estado del workflow (Pendiente a Proceso) 
	 * @param idOperacion
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private void pasarWorkflowPendienteAProceso(ShvCobTransaccionSinOperacion transaccion, String usuario) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = cobroDao.buscarWorkflowPorIdOperacion(transaccion.getIdOperacion());
			if(Estado.COB_PENDIENTE.equals(workflow.getEstado())){
				workflowCobros.iniciarCobro(workflow, " " ,usuario);
			}
			Traza.auditoria(getClass(), "Se inicia la imputacion de la operación (" +transaccion.getOperacionTransaccionFormateado()+ ")");
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			workflow = null;
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private ShvCobTransaccionSinOperacion guardarTransaccionSinOperacion(ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion {
		try {
			return cobroDao.guardarTransaccionSinOperacion(transaccion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public ShvCobCobroSimpleSinWorkflow modificar(ShvCobCobroSimpleSinWorkflow cobroModelo) throws NegocioExcepcion {
		try {
			return cobroDao.modificar(cobroModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 */
	public Integer hayHilosCobrosVivos(TipoImputacionEnum tipoImputacionEnum) throws NegocioExcepcion{
		
		Integer resultado=0;
		try {
			resultado = cobroDao.hayHilosCobrosVivos(tipoImputacionEnum);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return resultado;
	}
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Integer obtenerEstadoDelHilo(Long idOperacion) throws NegocioExcepcion{
		
		Integer resultado=0;
		try {
			resultado = cobroDao.obtenerEstadoDelHilo(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return resultado;
	}

//	
//	/**
//	 * 
//	 * @param transaccion
//	 */
//	private void apropiarSaldoACobrar(ShvCobTransaccion transaccion) {
//		
//		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
//			if(TipoMedioPagoEnum.SALDO_A_COBRAR.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())){
//				medioPago.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
//				medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * @param transaccion
//	 */
//	private void apropiarSaldoAPagar(ShvCobTransaccion transaccion) {
//		
//		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
//			if (TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
//				transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
//				transaccion.getTratamientoDiferencia().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @param listaCapResultado
//	 * @param resultado
//	 * @param documentoCap
//	 * @return
//	 */
//	private List<ShvCobMedioPagoDocumentoCapResultado> agregarDocumentoCapResultadoError(List<ShvCobMedioPagoDocumentoCapResultado> listaCapResultado, 
//			ResultadoInvocacion resultado, ShvCobMedioPagoDocumentoCap documentoCap) {
//		
//		ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
//		
//		if (Validaciones.isObjectNull(resultado)){
//			documentoCapResultado.setTipo(TipoResultadoEnum.ERROR_SERVICIO);
//			documentoCapResultado.setDescripcion(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.formato.imputacion.sap"));
//		} else {
//			documentoCapResultado.setTipo(TipoResultadoEnum.getEnumByDescripcionSap(resultado.getResultadoInvocacion()));
//			documentoCapResultado.setCodigo(resultado.getCodigoError());
//			documentoCapResultado.setDescripcion(resultado.getDescripcionError());
//		}
//		
//		documentoCapResultado.setFecha(new Date());
//		documentoCapResultado.setDocumentoCap(documentoCap);
//		listaCapResultado.add(documentoCapResultado);
//		
//		return listaCapResultado;
//	}
//
//	/**
//	 * 
//	 * @param documentoCap
//	 * @param idOperacion
//	 * @return
//	 * @throws NegocioExcepcion
//	 * @throws PersistenciaExcepcion 
//	 */
//	private EntradaSapRegistrarCompensacionWS prepararImputacionPartidasSap(ShvCobMedioPagoDocumentoCap documentoCap, Long idOperacion) throws NegocioExcepcion, PersistenciaExcepcion {
//		
//		EntradaSapRegistrarCompensacionWS entrada = new EntradaSapRegistrarCompensacionWS();
//		
//		ShvCobEdCobroSimplificado cobroSimplificado = cobroOnlineDao.buscarCobroSimplificado(idOperacion);
//		String textoPosicion = parametroServicio.getValorTexto(Constantes.PARAM_COMPENSACION_RAV + cobroSimplificado.getIdSegmento());
//		UsuarioLdapDto usuarioAprob = ldapServicio.buscarUsuarioPorUidEnMemoria(cobroSimplificado.getUsuarioAprobadorSupervisorOperacEspeciales());
//		
//		
//		entrada.setIdOperacionShiva(idOperacion);
//		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
//		
//		for (ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalle : documentoCap.getDetalle()) {
//			
//			Partida partida = new Partida();
//			
//			partida.setIdSociedad(documentoCapDetalle.getIdSociedad());
//			partida.setIdProveedor(documentoCapDetalle.getIdProveedor());
//			partida.setEjercicioFiscalDocSAP(documentoCapDetalle.getEjercicioFiscalDocSAP().toString());
//			partida.setNumeroDocSAP(documentoCapDetalle.getNumeroDocSAP());
//			partida.setPosicionDocSAP(documentoCapDetalle.getPosicionDocSAP().toString());
//			partida.setMonedaDocProveedor(documentoCapDetalle.getMonedaDocProveedor().getSigla());
//			partida.setClaseDocSAP(documentoCapDetalle.getTipoDocumento().name());
//			partida.setMesFiscalDocSAP(documentoCapDetalle.getMesFiscalDocSap().toString());
//			partida.setIndicador(documentoCapDetalle.getIndicador());
//			partida.setImporteAplicadoMonedaDolar(documentoCapDetalle.getImporteAplicadoMonedaDolar());
//			partida.setImporteAplicadoMonedaPesos(documentoCapDetalle.getImporteAplicadoMonedaPesos());
//			partida.setMonedaCompensacion(documentoCap.getMoneda().getSigla());
//			partida.setBloqueoPago(documentoCapDetalle.getBloqueoPago().getCodigo());
//			partida.setNumeroDocSAPVinculado(documentoCapDetalle.getNumeroDocSAPVinculado());
//			
//			if (!Validaciones.isObjectNull(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado())) {
//				partida.setEjercicioFiscalDocSAPVinculado(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado().toString());
//			}
//
//			partida.setPosicionDocSAPVinculado(documentoCapDetalle.getPosicionDocSAPVinculado().toString());
//			partida.setClaveRef2(documentoCapDetalle.getClaveRef2());
//			
//			partida.setImporteRenglonMonedaOrigenAFechaEmision(documentoCapDetalle.getImporteRenglonMonedaOrigenAFechaEmision());
//			partida.setTextoPosicion(textoPosicion+" - "+usuarioAprob.getNombreCompleto());
//			partida.setFechaEmision(documentoCapDetalle.getFechaEmision());
//			
//			
//					
//			entrada.getListaPartidas().add(partida);
//		}
//
//		try {
//			entrada.setPdfFlie(cobroBatchServicio.generarPdfResumenCompensacion(documentoCap.getIdCobro()).getByteArray());
//		} catch (NegocioExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//		
//		return entrada;
//	}
//
//	/**
//	 * 
//	 * @param listaMediosPagoCompensacion
//	 * @return
//	 */
//	private ShvCobMedioPagoDocumentoCap obtenerDocumentoCap(List<ShvCobMedioPago> listaMediosPagoCompensacion) {
//		
//		ShvCobMedioPagoDocumentoCap documentoCap = null;
//		
//		for (ShvCobMedioPago medioPago : listaMediosPagoCompensacion){
//			
//			documentoCap = new ShvCobMedioPagoDocumentoCap();
//			
//			if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
//				documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto)medioPago).getDocumentoCap();
//				
//			} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor){
//				documentoCap = ((ShvCobMedioPagoCompensacionProveedor)medioPago).getDocumentoCap();
//			}
//			
//			break;
//			
//		}
//		
//		return documentoCap;
//	}
		
	public List<ShvCobMedioPago> listarMediosPagoCompensacion(ShvCobTransaccion transaccion, List<ShvCobMedioPago> listaMp) throws NegocioExcepcion {
		
		for (ShvCobMedioPago mp : transaccion.getMediosPago()) {
			if(mp instanceof ShvCobMedioPagoCompensacionProveedor
				|| mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
				listaMp.add(mp);
			}
		}
		return listaMp;
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	/**
	 * @return the workflowCobros
	 */
	public IWorkflowCobros getWorkflowCobros() {
		return workflowCobros;
	}

	/**
	 * @param workflowCobros the workflowCobros to set
	 */
	public void setWorkflowCobros(IWorkflowCobros workflowCobros) {
		this.workflowCobros = workflowCobros;
	}

	/**
	 * @return the cobroDao
	 */
	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	/**
	 * @param cobroDao the cobroDao to set
	 */
	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}

	/**
	 * @return the valorServicio
	 */
	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	/**
	 * @param valorServicio the valorServicio to set
	 */
	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	/**
	 * 
	 * @return
	 */
	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}
	
	/**
	 * 
	 * @param parametroServicio
	 */
	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	/**
	 * @return the genericoDao
	 */
	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	/**
	 * @param genericoDao the genericoDao to set
	 */
	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	/**
	 * @return the contabilidadServicio
	 */
	public IContabilidadServicio getContabilidadServicio() {
		return contabilidadServicio;
	}

	/**
	 * @param contabilidadServicio the contabilidadServicio to set
	 */
	public void setContabilidadServicio(IContabilidadServicio contabilidadServicio) {
		this.contabilidadServicio = contabilidadServicio;
	}

	/**
	 * 
	 * @return
	 */
	public IClienteCalipsoServicio getClienteCalipsoServicio() {
		return clienteCalipsoServicio;
	}
	
	/**
	 * 
	 * @param clienteCalipsoServicio
	 */
	public void setClienteCalipsoServicio(
			IClienteCalipsoServicio clienteCalipsoServicio) {
		this.clienteCalipsoServicio = clienteCalipsoServicio;
	}
	
	/**
	 * 
	 * @return
	 */
	public IMensajeriaTransaccionServicio getMensajeriaTransaccionServicio() {
		return mensajeriaTransaccionServicio;
	}

	/**
	 * 
	 * @param mensajeriaTransaccionServicio
	 */
	public void setMensajeriaTransaccionServicio(
			IMensajeriaTransaccionServicio mensajeriaTransaccionServicio) {
		this.mensajeriaTransaccionServicio = mensajeriaTransaccionServicio;
	}
	
	/**
	 * 
	 * @return
	 */
	public ITransaccionCobroServicio getTransaccionCobroServicio() {
		return transaccionCobroServicio;
	}

	/**
	 * 
	 * @param transaccionCobroServicio
	 */
	public void setTransaccionCobroServicio(
			ITransaccionCobroServicio transaccionCobroServicio) {
		this.transaccionCobroServicio = transaccionCobroServicio;
	}
	
	/**
	 * 
	 * @return
	 */
	public IScardServicio getScardServicio() {
		return scardServicio;
	}

	/**
	 * 
	 * @param scardServicio
	 */
	public void setScardServicio(IScardServicio scardServicio) {
		this.scardServicio = scardServicio;
	}

	/**
	 * 
	 * @return
	 */
	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}
	
	/**
	 * 
	 * @param vistaSoporteServicio
	 */
	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}

	/**
	 * 
	 * @return
	 */
	public ITipoMedioPagoDao getTipoMedioPagoDao() {
		return tipoMedioPagoDao;
	}

	/**
	 * 
	 * @param tipoMedioPagoDao
	 */
	public void setTipoMedioPagoDao(ITipoMedioPagoDao tipoMedioPagoDao) {
		this.tipoMedioPagoDao = tipoMedioPagoDao;
	}

	/**
	 * 
	 * @return
	 */
	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	/**
	 * 
	 * @param organismoDao
	 */
	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}


	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}


	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}


	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		
	}


	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		
	}


	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}


	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
	}
}