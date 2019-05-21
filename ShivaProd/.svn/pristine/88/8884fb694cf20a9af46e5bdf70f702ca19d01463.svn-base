package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
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
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.ResultadoApropiacionDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Proveedor;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSapServicio;
import ar.com.telecom.shiva.negocio.batch.bean.SubdiarioBatch;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesamientoHilosCobrosServicio;
import ar.com.telecom.shiva.negocio.servicios.IProveedorCapServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCobrosPendientes;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.impl.TareaDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapResultado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TareaFiltro;

/*Procedimiento del Imputar Cobros
 * 
 El imputarBatchRunner busca todos los IdOperacion pendientes o en proceso
	por cada uno llama al imputarCobro(IdOperacion) en el servicio
	While(true){
		- el servicio entra en un while donde tira la query para que le traiga la transaccion pendiente
		- si el numero de la transaccion es 1 entonces busco el workflow y lo avanzo y lo guardo (sin traerme el cobro)
		- procesa la transaccion
		- verifica en que estado quedo la transaccion
			- Pendiente o En proceso de apropiacion ejecuto break
			- Error ejecuto break
			- Apropiada, ejecuto la query para que traiga la siguiente transaccion pendiente
				- Si la query trae una transaccion vuelvo a procesarla
				- Si NO trae una transaccion asumo que termine de procesar el cobro y hago un break del while
	}
	
	******************************************************************
	* Esta parte no se hace y se deja el metodo verificarEstadoCobro
	******************************************************************
	mediante flags deduzco si sali porque: 
		- tengo que esperar a MIC, no hago nada
		- hugo error y tengo que desapropiar
			- pregunto si ya no envie la desapropiacion
				- Si ya la envie no hago nada
				- Si NO la envie
					- en este caso levanto solo los datos importantes para armar el mensaje y lo envio
						- si desapropio todo bien recien aca busco todo el cobro para setearle los estados
					
		- termine de procesar todas las transacciones y debo enviar la confirmacion
			- pregunto si ya no envie la confirmacion
				- Si ya la envie no hago nada
				- Si NO la envie
					- en este caso levanto solo los datos importantes para armar el mensaje y lo envio
						- si confirmo todo bien recien aca busco todo el cobro para setearle los estados
*/

@Service
public class CobroBatchImputacionServicioImpl extends Servicio implements ICobroImputacionServicio {

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
	@Autowired private ICobroBatchSoporteServicio cobroBatchSoporteServicio;
	@Autowired private ICobroBatchServicio cobroBatchServicio;
	@Autowired private IDocumentoCapDao documentoCapDao;
	@Autowired private IMotivoCobroDao motivoCobroDao;
	@Autowired private IProveedorCapServicio proveedorCapServicio;
	@Autowired private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	@Autowired private ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;
	@Autowired private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	@Autowired private TareaDaoImpl tareaDaoImpl;

	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#buscar(java.lang.Integer)
	 */
	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#listar(ar.com.telecom.shiva.presentacion.bean.filtro.Filtro)
	 */
	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}
	
	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#modificar(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {

	}

	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#anular(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public void anular(DTO dto) throws NegocioExcepcion {

	}

	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#getDatosModificados(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	/** (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#verificarConcurrencia(java.io.Serializable, java.lang.Long)
	 */
	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {

	}

	/***************************************************************************
	 * BATCH IMPUTAR COBRO
	 *************************************************************************/
	/**
	 * Método que recibe un cobro y se comunica con Calipso y MIC. 
	 * 
	 * @throws NegocioExcepcion 
	 */
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void imputarCobro(ImputacionCobroRto datosEntrada) throws NegocioExcepcion {
		ControlVariablesSingleton.setReintentoExcepcion(null);
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		ShvCobTransaccionSinOperacion transaccion = null;

		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			Boolean ocurrioError = false;
			Boolean enviarConfirmacion = false;
			Boolean cobroPendienteMic = false;
			int cantTransaccionesProcesadasConsecutivas = 0;
			Long idOperacion = datosEntrada.getCobroAProcesar().getIdOperacion();
			
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
				
					//Busco la transaccion que tengo que procesar
					transaccion = cobroDao.buscarTransaccionAProcesarPorIdOperacion(idOperacion);
					
					
					
					//Si NO trae una transaccion asumo que termine de procesar el cobro y hago un break del while
					if(Validaciones.isObjectNull(transaccion)){
						
						//Me fijo si el cobro contiene aplicacion manual pendiente de procesar, si contiene avanzo el wf del cobro a EN PROCESO APLICACION MANUAL
						if (SiNoEnum.SI.equals(datosEntrada.getCobroAProcesar().getContieneAplicacionManual())
								&& cobroBatchSoporteImputacionServicio.tieneTransaccionesConAplicacionManualPendienteDeProcesar(idOperacion)){
							//TODO AVANZAR WF A EN PROCESO APLICACION MANUAL
							pasarWorkflowPendienteAProceso(idOperacion, usuarioBatchCobroImputacion);
							pasarWorkflowPendienteAProcesoAplicacionManual(idOperacion, usuarioBatchCobroImputacion);
							break;
						} else {
							
							enviarConfirmacion=true;
							break;
						}
					}
					
					cantTransaccionesProcesadasConsecutivas++;
					
					cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, true);
					
					//Si el numero de la transaccion es 1 entonces busco el workflow y lo avanzo a "COB_PROCESO" y lo guardo (sin traerme el cobro)
					if(transaccion.getNumeroTransaccion()==1){
						pasarWorkflowPendienteAProceso(idOperacion, usuarioBatchCobroImputacion);
					}
					
					//***************************************************************************************************************
					//Comienzo de la parte vieja
					//***************************************************************************************************************
					// Actualizo el estado a "EN PROCESO" si el estado actual es "PENDIENTE".
					if(EstadoTransaccionEnum.PENDIENTE.equals(transaccion.getEstadoProcesamiento())){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.EN_PROCESO_APROPIACION);
					}
				
					// Primera vez o estoy esperando respuesta de MIC.
					if(EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento()) ){
						
						ShvCobFacturaSinOperacion factura = transaccion.getFactura(); 
						
						//Lista de medios de pago a enviar
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoMIC = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.MIC);
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoCalipso = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.CALIPSO);
						List<ShvCobMedioPagoSinOperacion> listaMediosPagoShiva = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.SHIVA);
						
						EstadoFacturaMedioPagoEnum estadoMedioPagoMIC = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoMIC(transaccion);
						EstadoFacturaMedioPagoEnum estadoMedioPagoCalipso = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoCalipso(transaccion);
						EstadoFacturaMedioPagoEnum estadoMedioPagoShiva = cobroBatchSoporteImputacionServicio.getEstadoMedioPagoShiva(transaccion);

						Utilidad.tracearTiempo(getClass(), "Tiempo en comenzar alguna apropiacion", fechaHoraInicioNanoTime);
						
						if (haySaldoACobrar(transaccion)){
							factura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
							factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
						} else {
							
							//Si hay medio de pago shiva y están en estado pendiente apropia en shiva
							if (Validaciones.isCollectionNotEmpty(listaMediosPagoShiva)){
								if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoShiva)) {
									cobroBatchSoporteImputacionServicio.apropiacionShiva(listaMediosPagoShiva,transaccion, usuarioBatchCobroImputacion);
									transaccion = guardarTransaccionSinOperacion(transaccion);
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
								if (Validaciones.isObjectNull(factura)){
									
									if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoCalipso)) {
										cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(listaMediosPagoCalipso, null, transaccion, fechaCobranza, monedaOperacion);
									}
									if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
										cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, null,transaccion);
										cobroPendienteMic = true;
										break;
									}
									
								}else{
									
									// Si la factura es de MIC, primero apropio los MP de Calipso y luego los de MIC.
									if(factura instanceof ShvCobFacturaMicSinOperacion){
										if(cobroBatchSoporteImputacionServicio.existenMediosPagoCalipso(transaccion)){
											if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoCalipso)) {
												
												// Enviar el mensaje a la interfaz calipso.
												if(cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(listaMediosPagoCalipso, null, transaccion, fechaCobranza, monedaOperacion)){
													//Apropio a Mic 
													if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
														if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
															// apropio la factura y los MP y Prox Factura de MIC.
															cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, factura,null);
															cobroPendienteMic = true;
															break;
														}
													}else{
														//solo hay factura de MIC
														if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
															// apropio la factura.
															cobroBatchSoporteImputacionMicServicio.apropiacionMic(null,factura,null);
															cobroPendienteMic = true;
															break;
														}
													}
												}
											} else {
												if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(estadoMedioPagoCalipso)) {
													//Apropio a Mic 
													if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
														if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
															// apropio la factura y los MP y Prox Factura de MIC.
															cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, factura,null);
															cobroPendienteMic = true;
															break;
														}
													}else{
														//solo hay factura de MIC
														if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
															// apropio la factura.
															cobroBatchSoporteImputacionMicServicio.apropiacionMic(null,factura,null);
															cobroPendienteMic = true;
															break;
														}
													}
												}
												
											}
										}else{
											// No hay medios de pago Calipso
											if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
												// La transaccion no contiene MP de Calipso. Apropio a MIC
												if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
													// apropio la factura y los MP y ProxFactura de MIC 
													cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, factura,null);
													cobroPendienteMic = true;
													break;
												}
											}else{
												// No hay medios de pago MIC ni proxima factura. Por lo tanto contiene una factura MIC
												// y Medios de pago Shiva y/o Usuario
												if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
													//Apropio Deuda
													cobroBatchSoporteImputacionMicServicio.apropiacionMic(null, factura,null);
													cobroPendienteMic = true;
													break;
												}
											}
										}
									}else{
										// Si la factura es de CALIPSO, primero apropio los MP de MIC y luego los de CALIPSO.
										if(factura instanceof ShvCobFacturaCalipsoSinOperacion){
											
											factura = calcularSaldoActualMonedaOrigen(factura, transaccion);
											
											if(cobroBatchSoporteImputacionServicio.existenMediosPagoMic(transaccion)){
												// Tengo que apropiar los Medios de pago de MIC. 
												if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoMIC)){
													cobroBatchSoporteImputacionMicServicio.apropiacionMic(listaMediosPagoMIC, factura,null);
													cobroPendienteMic = true;
													break;
												}else{
													if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(estadoMedioPagoMIC)){
														// Si los MP de MIC estan Apropiados.
														// Apropio los MP / prox factura y la factura de Calipso.
														if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)){
															if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoCalipso)){
																cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(listaMediosPagoCalipso, factura, transaccion, fechaCobranza, monedaOperacion);
															}
														}else{
															//Apropio Deuda
															if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
																cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(null, factura, transaccion, fechaCobranza, monedaOperacion);
															}
														}
													}
												}
											}else{
												// si la transaccion no tiene MP de MIC. Apropio los MP / prox factura de Calipso.
												if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)){
													if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(estadoMedioPagoCalipso)){
														cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(listaMediosPagoCalipso, factura, transaccion, fechaCobranza, monedaOperacion);
													}
												}else{
													// No hay medios de pago CALIPSO ni proxima factura. Por lo tanto contiene una factura CALIPSO
													// y Medios de pago Shiva y/o Usuario
													if(EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())){
														//Apropio Deuda
														cobroBatchSoporteImputacionCalipsoServicio.apropiacionCalipso(null, factura, transaccion, factura.getFechaValor(), monedaOperacion);
													}
												}
											}
										}
									}
								}
							}
						}
						
						//***************************************************************************************************************
						//Fin de la parte vieja
						//***************************************************************************************************************
						
						//hago esto porque sino pierdo los cambios que hice en la transaccion
						transaccion = cobroBatchSoporteImputacionServicio.verificarEstadoTransaccion(transaccion, usuarioBatchCobroImputacion);
						
						transaccion = guardarTransaccionSinOperacion(transaccion);
						
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
						
						// Si la transaccion esta en uno de los estados de error no hago mas nada
						if (EstadoTransaccionEnum.listarEstadosError().contains(transaccion.getEstadoProcesamiento())) {
							ocurrioError=true;
							break;
						}
						
						if(!EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())
								&& !EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento())){
							break;
						}
					
					} else if (EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
						cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(idOperacion,datosEntrada.getCobroAProcesar().getIdCobro(), transaccion.getGrupo(), usuarioBatchCobroImputacion, false);
					}
				} catch (ReintentoExcepcion e) {
					setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, true,transaccion);
					break;
				}
				
			} /*Fin WHILE transaccion*/
			
			if (cobroPendienteMic){
				
				transaccion = cobroBatchSoporteImputacionServicio.verificarEstadoTransaccion(transaccion, usuarioBatchCobroImputacion);
				
				transaccion = guardarTransaccionSinOperacion(transaccion);
				
				cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
			}
			try {
				
				ShvCobCobro cobro = null;
			
				if (enviarConfirmacion){
					cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
					try{
						apropiacionCompensacionesSap(cobro, usuarioBatchCobroImputacion);
					} catch (ReintentoExcepcion e) {
						ShvCobTransaccionSinOperacion transacSinOperacion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(e.getIdTransaccion());
						setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, true,transacSinOperacion);
					}
					
					try{
						cobroBatchSoporteImputacionServicio.verificarEstadoCobro(cobro,usuarioBatchCobroImputacion,false);
						cobroBatchSoporteImputacionServicio.calcularImporteParcial(cobro);
					}catch (ReintentoExcepcion e) {
						setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, false,null);
					}
				}
				
				// Si ocurrio un error debo enviar la desapropiacion
				if(ocurrioError){
					
					
					//Me fijo si el cobro contiene aplicacion manual pendiente de procesar y compensaciones sap, 
					//si no contiene compensaciones sap avanzo el wf del cobro a EN PROCESO APLICACION MANUAL
					
					if (SiNoEnum.SI.equals(datosEntrada.getCobroAProcesar().getContieneAplicacionManual())
							&& cobroBatchSoporteImputacionServicio.tieneTransaccionesConAplicacionManualPendienteDeProcesar(idOperacion)
							&& !cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)){

						cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(idOperacion,datosEntrada.getCobroAProcesar().getIdCobro(), transaccion.getGrupo(), usuarioBatchCobroImputacion, false);
						pasarWorkflowPendienteAProceso(idOperacion, usuarioBatchCobroImputacion);
						pasarWorkflowPendienteAProcesoAplicacionManual(idOperacion, usuarioBatchCobroImputacion);
					} else {
						
						//---- Actualizar el workflow de cobros y envio la confirmacion si corresponde
						cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
						cobroBatchSoporteImputacionServicio.verificarEstadoCobro(cobro,usuarioBatchCobroImputacion,false);
					}
				}
			} catch (ReintentoExcepcion e) {
				setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, false,null);
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
	



	private ShvCobFacturaSinOperacion calcularSaldoActualMonedaOrigen(ShvCobFacturaSinOperacion facturaActual, ShvCobTransaccionSinOperacion transaccion) throws PersistenciaExcepcion {
		
		String numDocumentoFacturaClp ="";
		BigDecimal saldoActualFacturaMonedaOrigen = null;
		BigDecimal importeAplicadoMonedaOrigen = null;
		BigDecimal totalSaldoActual = null;
		
		//Si la factura es en Dolares, me guardo el numero de doc, y el saldo de la factura.
		//Por si es un pago parcial, y en la proxima transaccion debo actualizar el saldo de la factura.
		if (MonedaEnum.PES.equals(facturaActual.getMonedaImporteCobrar())
				&& MonedaEnum.DOL.equals(((ShvCobFacturaCalipsoSinOperacion) facturaActual).getMoneda())){
		
			//guardo el numero de documento de la factura actual.
			String numDocumentoFacturaActual = facturaActual.getClaseComprobante() + facturaActual.getSucursalComprobante() + facturaActual.getNumeroComprobante();
			
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(transaccion.getIdOperacion());
			
			//Recorro las transacciones, en busca de 
			for (ShvCobTransaccion trans : cobro.getTransaccionesOrdenadas()) {
				
				ShvCobFactura factura = trans.getFactura();

				if(!Validaciones.isObjectNull(factura)
					&& factura instanceof ShvCobFacturaCalipso){
					
					if (EstadoFacturaMedioPagoEnum.PENDIENTE.equals(factura.getEstado())) {
						break;
					}
					
					numDocumentoFacturaClp = factura.getClaseComprobante() + factura.getSucursalComprobante() + factura.getNumeroComprobante();
					
					saldoActualFacturaMonedaOrigen = ((ShvCobFacturaCalipso) factura).getSaldoActualMonedaOrigen();
					importeAplicadoMonedaOrigen = ((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaOrigen();
					
					//Si los numeros de documento son iguales y son de diferentes transacciones, guardo el saldo.
					if (numDocumentoFacturaClp.equals(numDocumentoFacturaActual) 
							&& transaccion.getNumeroTransaccion().equals(facturaActual.getTransaccion().getNumeroTransaccion()-1)
							&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())){
						
						totalSaldoActual = saldoActualFacturaMonedaOrigen.subtract(importeAplicadoMonedaOrigen);
					}
				}
			}
		}

		if (!Validaciones.isObjectNull(totalSaldoActual)){
			((ShvCobFacturaCalipsoSinOperacion) facturaActual).setSaldoActualMonedaOrigen(totalSaldoActual);
		}
		
		return facturaActual;
	}

	/**
	 * 
	 * @param cobro
	 * @param usuario
	 * @param e
	 * @param errorEnApropiacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	public ShvCobCobro setearEstadoCobroPorCancelacion(Long idOperacion, String usuario, ReintentoExcepcion e, boolean errorEnApropiacion, 
			ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion {
		
		ShvCobCobro cobro = null;
		try{
			cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
			List<ShvCobTransaccion> listaTransaccionesOrdenadas = cobro.getTransaccionesOrdenadas();
			if (errorEnApropiacion) {
				verificarErrorPorCancelacion(transaccion,e);
//				if (TipoInvocacionEnum.$12.equals(e.getTipoInvocacion())
//						|| TipoInvocacionEnum.$13.equals(e.getTipoInvocacion())){
				cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
//				}
				if(SiNoEnum.SI.equals(cobro.getContieneAplicacionManual())
						&& cobroBatchSoporteImputacionServicio.tieneTransaccionesConAplicacionManualPendienteDeProcesar(idOperacion)
						&& !cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)
						){
					Traza.advertencia(this.getClass(), "Se envia la desapropiacion parcial por cancelacion de mensajeria");
					cobroBatchSoporteImputacionServicio.desapropiarTransaccionesPorGrupo(idOperacion, cobro.getIdCobro(), transaccion.getGrupo()
							, usuario, false);
					pasarWorkflowPendienteAProcesoAplicacionManual(idOperacion, usuario);
					
				} else {
					cobroBatchSoporteImputacionServicio.verificarEstadoCobro(cobro, usuario, false);
				}
				//---- Compruebo el estado del cobro, y si esta en un estado final
				//---- verifico si debo revertir algun medio de pago Shiva del cobro
				//realizarReversionMedioPagoValor(cobro);
				
			} else {
				procesarErrorEnConfirmacionODesapropiacionPorCancelacion(listaTransaccionesOrdenadas,e);
				if(TipoInvocacionEnum.$01.equals(e.getTipoInvocacion())
						|| TipoInvocacionEnum.$02.equals(e.getTipoInvocacion())
						|| TipoInvocacionEnum.$03.equals(e.getTipoInvocacion())
						|| TipoInvocacionEnum.$12.equals(e.getTipoInvocacion())
						|| TipoInvocacionEnum.$13.equals(e.getTipoInvocacion())
						){
					if(cobroBatchSoporteImputacionServicio.verificarErrorEnApropiacion(listaTransaccionesOrdenadas)){
						cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_APROPIACION, usuario);
					} else {
						cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_COBRO, usuario);
					}
				} else if(TipoInvocacionEnum.$04.equals(e.getTipoInvocacion())){
					cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_DESAPROPIACION, usuario);
				} else if(TipoInvocacionEnum.$05.equals(e.getTipoInvocacion())){
					cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_CONFIRMACION, usuario);
				}
				cobroBatchSoporteImputacionServicio.realizarReversionMedioPagoValor(cobro);
				
				cobro = cobroDao.modificar(cobro);
				ControlVariablesSingleton.setReintentoExcepcion(e);
				cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), false, 0);
				Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
				
				cobroBatchSoporteImputacionServicio.evaluarEnvioMailYGenerarTarea(cobro,null);
			}
			
			return cobro;
			
		} catch (PersistenciaExcepcion exc) {
			throw new NegocioExcepcion(exc.getMessage(),exc);
		} catch (ReintentoExcepcion e1) {
			List<ShvCobTransaccion> listaTransaccionesOrdenadas = cobro.getTransaccionesOrdenadas();
			procesarErrorEnConfirmacionODesapropiacionPorCancelacion(listaTransaccionesOrdenadas,e1);
			if(TipoInvocacionEnum.$01.equals(e.getTipoInvocacion())
					|| TipoInvocacionEnum.$02.equals(e.getTipoInvocacion())
					|| TipoInvocacionEnum.$03.equals(e.getTipoInvocacion())){
				if(cobroBatchSoporteImputacionServicio.verificarErrorEnApropiacion(listaTransaccionesOrdenadas)){
					cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_APROPIACION, usuario);
				} else {
					cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_COBRO, usuario);
				}
			} else if(TipoInvocacionEnum.$04.equals(e.getTipoInvocacion())){
				cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_DESAPROPIACION, usuario);
			} else if(TipoInvocacionEnum.$05.equals(e.getTipoInvocacion())){
				cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_ERROR_CONFIRMACION, usuario);
			}
			cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), false, 0);
			Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
			
			cobroBatchSoporteImputacionServicio.evaluarEnvioMailYGenerarTarea(cobro,null);
			return cobro;
		}
		
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
	
	/**
	 * Verifica que si la primer transaccion esta en un estado de ERROR y las demas
	 * transacciones estan en estado PENDIENTE retorna true para que se aplique el estado
	 * COB_ERROR_APROPIACION al cobro.
	 * @param listaTransaccionesOrdenada
	 * @return
	 */
	private void procesarErrorEnConfirmacionODesapropiacionPorCancelacion(List<ShvCobTransaccion> listaTransaccionesOrdenada, ReintentoExcepcion e) {
		
		for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
			if(EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())
				|| EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
				|| EstadoTransaccionEnum.listarEstadosError().contains(transaccion.getEstadoProcesamiento())){
				verificarErrorPorCancelacion(transaccion, e);
			}
		}
	}
	
	/**
	 * 
	 * @param listaTransaccionesOrdenada
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	private boolean verificarErrorPorCancelacion(ShvCobTransaccionSinOperacion transaccion, ReintentoExcepcion e) throws PersistenciaExcepcion {
		
		EstadoFacturaMedioPagoEnum estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.APROPIADA;
		EstadoFacturaMedioPagoEnum estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.PENDIENTE;
		EstadoTransaccionEnum estadoTransaccion = null;
		
		switch (e.getTipoInvocacion()){
		//si es apropiación
		case $01:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_DEUDA;
			break;
		case $02:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO;
			break;
		case $03:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA;
			break;
		
		//desapropiacion
		case $04:
			estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.DESAPROPIADA;
			estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION;
			estadoTransaccion = EstadoTransaccionEnum.ERROR_DESAPROPIACION;
			break;
		//confirmacion
		case $05:
			estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.CONFIRMADA;
			estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.APROPIADA;
			estadoTransaccion = EstadoTransaccionEnum.ERROR_CONFIRMACION;
			break;
		case $07:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_TRATAMIENTO;
			break;
		//registracion sap
		case $12:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO;
			//Se setea la transaccion solamente en este caso,
			transaccion.setEstadoProcesamiento(estadoTransaccion);
			transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
			break;
		case $13:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO;
			//Se setea la transaccion solamente en este caso,
			transaccion.setEstadoProcesamiento(estadoTransaccion);
			transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
			break;
		default:
			break;
		}
		
		ShvCobFacturaSinOperacion factura = transaccion.getFactura();
		
		if (!Validaciones.isObjectNull(factura)){
			//factura
			//En apropiación si está APROPIADA. En confirmación si está CONFIRMADA
			if(estadoFacturaTratamientoMp.equals(factura.getEstado())){
				
				return false;
			
				//En apropiación si está PENDIENTE. En confirmación si está APROPIADA
			}else if (estadoFacturaTratamientoMpElse.equals(factura.getEstado())
					&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) && factura instanceof ShvCobFacturaCalipsoSinOperacion)
							|| (SistemaEnum.MIC.equals(e.getCobrador()) && factura instanceof ShvCobFacturaMicSinOperacion))){
				
				factura.setMensajeEstado(e.getMessage());
				factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
				if(EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
					transaccion.setEstadoProcesamiento(estadoTransaccion);
					transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
				}
			}
		} else {
			//tratamiento
			ShvCobTratamientoDiferenciaSinOperacion tratamiento = transaccion.getTratamientoDiferencia();
			
			if (!Validaciones.isObjectNull(tratamiento)){
				if(estadoFacturaTratamientoMp.equals(tratamiento.getEstado())){
					
					return false;
					
				}else if (estadoFacturaTratamientoMpElse.equals(tratamiento.getEstado())
						&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) 
								&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia()))
								|| (SistemaEnum.MIC.equals(e.getCobrador()) 
										&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())))){
					
					tratamiento.setMensajeEstado(e.getMessage());
					tratamiento.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					if(EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
						transaccion.setEstadoProcesamiento(estadoTransaccion);
						transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
					}
				}
			}
		}
		for(ShvCobMedioPagoSinOperacion mp : transaccion.getMediosPago()){
			//TODO: VER SI VA EL IF AL REVES
			if(estadoFacturaTratamientoMp.equals(mp.getEstado())){
				
				return false;
				
			} else if (estadoFacturaTratamientoMpElse.equals(mp.getEstado())
					&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) && mp instanceof ShvCobMedioPagoCalipsoSinOperacion)
							|| (SistemaEnum.MIC.equals(e.getCobrador()) && mp instanceof ShvCobMedioPagoMicSinOperacion))){
				
				mp.setMensajeEstado(e.getMessage());
				mp.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				mp.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
				if(EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
					transaccion.setEstadoProcesamiento(estadoTransaccion);
					transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
				}
			}
		}
			
		if(EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
			transaccion.setEstadoProcesamiento(estadoTransaccion);
			transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
		}
		
		
		return true;
	}
	
	/**
	 * 
	 * @param listaTransaccionesOrdenada
	 * @return
	 */
	private boolean verificarErrorPorCancelacion(ShvCobTransaccion transaccion, ReintentoExcepcion e) {
		
		EstadoFacturaMedioPagoEnum estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.APROPIADA;
		EstadoFacturaMedioPagoEnum estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.PENDIENTE;
		EstadoTransaccionEnum estadoTransaccion = null;
		
		switch (e.getTipoInvocacion()){
		//si es apropiación
		case $01:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_DEUDA;
			break;
		case $02:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO;
			break;
		case $03:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA;
			break;
		
		//desapropiacion
		case $04:
			estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.DESAPROPIADA;
			estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION;
			estadoTransaccion = EstadoTransaccionEnum.ERROR_DESAPROPIACION;
			break;
		//confirmacion
		case $05:
			estadoFacturaTratamientoMp = EstadoFacturaMedioPagoEnum.CONFIRMADA;
			estadoFacturaTratamientoMpElse = EstadoFacturaMedioPagoEnum.APROPIADA;
			estadoTransaccion = EstadoTransaccionEnum.ERROR_CONFIRMACION;
			break;
		case $07:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_TRATAMIENTO;
			break;
		//registracion sap
		case $12:
			estadoTransaccion = EstadoTransaccionEnum.ERROR_MEDIO_PAGO;
			//Se setea la transaccion solamente en este caso,
			transaccion.setEstadoProcesamiento(estadoTransaccion);
			break;
		default:
			break;
		}
		
		ShvCobFactura factura = transaccion.getFactura();
		
		if (!Validaciones.isObjectNull(factura)){
			//factura
			//En apropiación si está APROPIADA. En confirmación si está CONFIRMADA
			if(estadoFacturaTratamientoMp.equals(factura.getEstado())){
				
				return false;
			
				//En apropiación si está PENDIENTE. En confirmación si está APROPIADA
			}else if (estadoFacturaTratamientoMpElse.equals(factura.getEstado())
					&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) && factura instanceof ShvCobFacturaCalipso)
							|| (SistemaEnum.MIC.equals(e.getCobrador()) && factura instanceof ShvCobFacturaMic))){
				
				factura.setMensajeEstado(e.getMessage());
				factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
				if(!EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
					transaccion.setEstadoProcesamiento(estadoTransaccion);
				}
			}
		} else {
			//tratamiento
			ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();
			
			if (!Validaciones.isObjectNull(tratamiento)){
				if(estadoFacturaTratamientoMp.equals(tratamiento.getEstado())){
					
					return false;
					
				}else if (estadoFacturaTratamientoMpElse.equals(tratamiento.getEstado())
						&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) 
								&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia()))
								|| (SistemaEnum.MIC.equals(e.getCobrador()) 
										&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())))){
					
					tratamiento.setMensajeEstado(e.getMessage());
					tratamiento.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					if(!EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
						transaccion.setEstadoProcesamiento(estadoTransaccion);
					}
				}
			}
		}
		for(ShvCobMedioPago mp : transaccion.getMediosPago()){
			
			if(estadoFacturaTratamientoMp.equals(mp.getEstado())){
				
				return false;
				
			} else if (estadoFacturaTratamientoMpElse.equals(mp.getEstado())
					&& ((SistemaEnum.CALIPSO.equals(e.getCobrador()) && mp instanceof ShvCobMedioPagoCalipso)
							|| (SistemaEnum.MIC.equals(e.getCobrador()) && mp instanceof ShvCobMedioPagoMic))){
				
				mp.setMensajeEstado(e.getMessage());
				mp.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				mp.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
				if(!EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
					transaccion.setEstadoProcesamiento(estadoTransaccion);
				}
			}
		}
			
		if(!EstadoTransaccionEnum.listarEstadosError().contains(estadoTransaccion)){
			transaccion.setEstadoProcesamiento(estadoTransaccion);
		}
		return true;
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
					setearEstadoCobroPorCancelacion(idOperacion, usuarioBatchCobroImputacion, e, false,null);
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

//	@Override
//	public List<Map<String,Object>> listarCobrosPendientesImputacion() throws NegocioExcepcion {
//		try {
//			List<Map<String,Object>> lista = cobroDao.listarCobrosPendientes();
//			return lista;
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//	}
	
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
			
			//TODO: EVALUAR ACÁ PARA CAMBIAR EL ESTADO DEL COBRO
			Estado estadoAAvanzar = evaluarEstadoCobroParaDesapropiar(modeloCobro);
			cobroBatchServicio.cambiarEstadoCobro(modeloCobro, estadoAAvanzar, usuarioBatchCobroImputacion);
			List<ShvCobTransaccion> listaTransaccionesOrdenadas = modeloCobro.getTransaccionesOrdenadas();

			for (ShvCobTransaccion transaccion : listaTransaccionesOrdenadas) {
				
				if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())) {
					
					if(transaccion.getGrupo() > 0){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION_POR_ERROR_DESAPROPIACION);
					}else {
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
						
					}
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
	 * Retorna el estado correspondiente para avanzar el cobro, para que se vuelva a procesar la desapropiación
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Estado evaluarEstadoCobroParaDesapropiar(ShvCobCobro cobro) throws NegocioExcepcion {
		
		Estado estado = null;

		try {
			
			List<ShvWfTarea> listaTareas = new ArrayList<ShvWfTarea>();
			TareaFiltro filtro = new TareaFiltro();
			
			if (SiNoEnum.SI.equals(cobro.getContieneAplicacionManual())){
				
				filtro.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
				filtro.setTipoTarea(TipoTareaEnum.COB_CONF_APLIC_MANUAL);
				filtro.setEstadoTarea(TipoTareaEstadoEnum.PENDIENTE);

				listaTareas = tareaDaoImpl.listarTareas(filtro);
				
				if (Validaciones.isCollectionNotEmpty(listaTareas)){
					
					estado = Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA;
				} else {
					
					for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
						
						if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
							
							if (transaccion.getGrupo() > 0){
								estado = Estado.COB_PROCESO_APLICACION_EXTERNA;
							} else {
								estado = Estado.COB_PROCESO;
							}
						}
					}
				}


			} else {
				estado = Estado.COB_PROCESO;
			}
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return estado;
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
	private void pasarWorkflowPendienteAProceso(Long idOperacion, String usuario) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			if(Estado.COB_PENDIENTE.equals(workflow.getEstado())){
				workflowCobros.iniciarCobro(workflow, " " ,usuario);
			}
			Traza.auditoria(getClass(), "Se inicia la imputacion de la operación (" + idOperacion + ")");
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			workflow = null;
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private void pasarWorkflowPendienteAProcesoAplicacionManual(Long idOperacion, String usuario) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			if(Estado.COB_PROCESO.equals(workflow.getEstado())){
				workflowCobros.registrarCobroEnProcesoDeAplicacionExterna(workflow, " " ,usuario);
			}
			Traza.auditoria(getClass(), "Se avanza el cobro(" + idOperacion + ") a En Proceso Aplicacion Externa");
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
	@Override
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

	/**
	 * 
	 * @param listaMediosPagoCompensacion
	 * @param transaccion
	 * @throws NegocioExcepcion
	 */
	private void apropiacionCompensacionesSap(ShvCobCobro cobro, String usuario) throws NegocioExcepcion,ReintentoExcepcion {
		
		Integer idTransaccion = null;
		try {
			
			Long idOperacion = cobro.getOperacion().getIdOperacion();
			List<ShvCobMedioPago> listaMediosPagoCompensacion = new ArrayList<ShvCobMedioPago>();
			List<ShvCobTransaccion> listaTransacciones = new ArrayList<ShvCobTransaccion>();
			SalidaSapConsultaProveedoresWS respuestaProveedor = null;
			for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
				listaTransacciones.add(transaccion);
				if (
					EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento()) ||
					EstadoTransaccionEnum.APL_MANUAL_APROBADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento())
				) {
					//Listo todos los medios de pago compensacion liquidoproducto o proveedor
					listaMediosPagoCompensacion = listarMediosPagoCompensacion(transaccion, listaMediosPagoCompensacion);
				}
			}
			if(Validaciones.isCollectionNotEmpty(listaMediosPagoCompensacion)){
				idTransaccion = listaMediosPagoCompensacion.get(listaMediosPagoCompensacion.size()-1).getTransaccion().getIdTransaccion();
			}
			//Busco el documento cap
			ShvCobMedioPagoDocumentoCap documentoCap = obtenerDocumentoCap(listaMediosPagoCompensacion);
			
			if (!Validaciones.isObjectNull(documentoCap)){
				
				List<ShvCobMedioPagoDocumentoCapResultado> listaCapResultado = new ArrayList<ShvCobMedioPagoDocumentoCapResultado>();
				IClienteSapServicio clienteSapServicio = (IClienteSapServicio) Configuracion.ctx.getBean("clienteSapServicio");
				
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.SAP, TipoInvocacionEnum.$13)){
					Long idCliente = new  Long(0);
					//Tomo el el primer medio de pago de la lista, es para obtener el cliente legado
					Set<ShvCobMedioPagoCliente> setClientesmp =((ShvCobMedioPagoUsuario) listaMediosPagoCompensacion.get(0)).getListaMedioPagoClientes();
					if (!Validaciones.isObjectNull(setClientesmp.iterator().next())){
						if (!Validaciones.isObjectNull(setClientesmp.iterator().next().getIdClienteLegado())){
							idCliente = new Long(setClientesmp.iterator().next().getIdClienteLegado());
						}
					}
					EntradaSapConsultaProveedoresWS entrada = new EntradaSapConsultaProveedoresWS();
					String cuit = cobroDao.obtenerCuit(cobro.getIdCobro(),idCliente);
					if (!Validaciones.isNullOrEmpty(cuit)){
						
						List<String> listaCuit = new ArrayList<String>();
						listaCuit.add(cuit);
						entrada.setListaCuits(listaCuit);
						entrada.setListaCuits(entrada.getListaCuits());
					}
					
					respuestaProveedor = clienteSapServicio.consultarProveedoresParaImputacion(entrada, idOperacion);
					
					if (!Validaciones.isObjectNull(respuestaProveedor)) {
						
						if (!TipoResultadoEnum.OK.equals(TipoResultadoEnum.getEnumByDescripcionSap(respuestaProveedor.getResultadoInvocacion().getResultadoInvocacion()))){
							listaCapResultado = agregarDocumentoCapResultadoError(listaCapResultado,null,documentoCap);
						}
						
						ResultadoInvocacion resultado = respuestaProveedor.getResultadoInvocacion();
						if (TipoResultadoSimulacionEnum.OK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoSap(resultado.getResultadoInvocacion()))) {
							Proveedor proveedor = respuestaProveedor.getListaProveedores().get(0);
							if (this.proveedorCapServicio.esProveedorInhabilitado(proveedor.getIdBloqueo())) {
								
								ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
								documentoCapResultado.setTipo(TipoResultadoEnum.ERROR);
								documentoCapResultado.setCodigo("");
								documentoCapResultado.setDescripcion("El proveedor ( " +proveedor.getIdProveedor() +" ) se encuentra inhabilitado.");
								documentoCapResultado.setFecha(new Date());
								documentoCapResultado.setDocumentoCap(documentoCap);
								listaCapResultado.add(documentoCapResultado);
							}
						}

						// En caso de que la respuesta del servicio haya sido errónea, también lo dejo como un resultado de documentos CAP
						// para poder ver en el campo "observaciones documentos CAP"

						if (!TipoResultadoSimulacionEnum.OK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoSap(resultado.getResultadoInvocacion()))) {
							ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
							documentoCapResultado.setTipo(TipoResultadoEnum.getEnumByDescripcionSap(respuestaProveedor.getResultadoInvocacion().getResultadoInvocacion()));
							documentoCapResultado.setCodigo(respuestaProveedor.getResultadoInvocacion().getCodigoError());
							documentoCapResultado.setDescripcion(respuestaProveedor.getResultadoInvocacion().getDescripcionError());
							documentoCapResultado.setFecha(new Date());
							documentoCapResultado.setDocumentoCap(documentoCap);
							listaCapResultado.add(documentoCapResultado);
						}
						
						if(!Validaciones.isCollectionNotEmpty(listaCapResultado)){
							if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.SAP, TipoInvocacionEnum.$12)){
								
								//Seteo los datos a enviar a SAP
								EntradaSapRegistrarCompensacionWS entradaCompensacion = prepararImputacionPartidasSap(documentoCap, idOperacion);
								
								SalidaSapRegistrarCompensacionWS respuesta = clienteSapServicio.registrarCompensacion(entradaCompensacion);
								
								if (!Validaciones.isObjectNull(respuesta)) {
									
									if (!Validaciones.isObjectNull(respuesta.getListaverificaciones())){
										
										for (ResultadoInvocacion resultado1 : respuesta.getListaverificaciones()) {
											listaCapResultado = agregarDocumentoCapResultadoError(listaCapResultado,resultado1,documentoCap);
										}
										
										if (!TipoResultadoEnum.OK.equals(TipoResultadoEnum.getEnumByDescripcionSap(respuesta.getResultadoInvocacion().getResultadoInvocacion()))){
											listaCapResultado = agregarDocumentoCapResultadoError(listaCapResultado,null,documentoCap);
										}
										
										if (!Validaciones.isObjectNull(respuesta.getIdDocumentoCap().getIdDocumento())
												&& !Validaciones.isNullOrEmpty(respuesta.getIdDocumentoCap().getIdSociedad())
												&& !Validaciones.isObjectNull(respuesta.getIdDocumentoCap().getFiscyear())
												&& !(new Long(0).equals(respuesta.getIdDocumentoCap().getFiscyear()))){
											
											documentoCap.setIdDocumento(respuesta.getIdDocumentoCap().getIdDocumento());
											if (Validaciones.isObjectNull(documentoCap.getIdSociedad())){
												documentoCap.setIdSociedad(respuesta.getIdDocumentoCap().getIdSociedad());
											}
											documentoCap.setAnioFiscal(respuesta.getIdDocumentoCap().getFiscyear());
											documentoCap.setFechaCreacion(Utilidad.obtenerFechaActual());
											documentoCap.setFechaDerivacionCap(Utilidad.obtenerFechaActual());
											documentoCap.setOrden(cobroDao.obtenerOrdenCap(documentoCap.getIdProveedor()) );
											
										} else {
											if (Validaciones.isObjectNull(respuesta.getIdDocumentoCap().getIdDocumento())
													|| Validaciones.isNullOrEmpty(respuesta.getIdDocumentoCap().getIdSociedad())
													|| Validaciones.isObjectNull(respuesta.getIdDocumentoCap().getFiscyear())
													|| new Long(0).equals(respuesta.getIdDocumentoCap().getFiscyear())){
												listaCapResultado = agregarDocumentoCapResultadoError(listaCapResultado,null,documentoCap);
											}
										}
										
										for (ShvCobMedioPago mp : listaMediosPagoCompensacion) {
											
											if(mp instanceof ShvCobMedioPagoCompensacionProveedor){
												ShvCobMedioPagoCompensacionProveedor mpProveedor = (ShvCobMedioPagoCompensacionProveedor)mp;
												mpProveedor.getDocumentoCap().getResultado().addAll(listaCapResultado);
												//Si hay error, seteo el medio de pago y transaccion en ERROR
												if (Validaciones.isCollectionNotEmpty(listaCapResultado)){
													mpProveedor.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
													mpProveedor.setMensajeEstado(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.registrarCompensacionSap.detalle.verObservacionesDocumentosCap"));
													mp.getTransaccion().setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
												} else {
													mpProveedor.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
													mpProveedor.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
												}
											} else if(mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
												
												ShvCobMedioPagoCompensacionLiquidoProducto mpLiquidoProducto = (ShvCobMedioPagoCompensacionLiquidoProducto)mp;
												
												mpLiquidoProducto.getDocumentoCap().getResultado().addAll(listaCapResultado);
												//Si hay error, seteo el medio de pago y transaccion en ERROR
												if (Validaciones.isCollectionNotEmpty(listaCapResultado)){
													mpLiquidoProducto.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
													mpLiquidoProducto.setMensajeEstado(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.registrarCompensacionSap.detalle.verObservacionesDocumentosCap"));
													mp.getTransaccion().setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
												} else {
													mpLiquidoProducto.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
													mpLiquidoProducto.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
													
												}
											}
										}
										
										//Si no tengo errores, recorro las transacciones para ver si tengo saldo a pagar(tratamiento dif) o saldo a cobrar (medio de pago) y 
										//los paso a estado APROPIADA. Tambien cambio de estado la transaccion a APROPIADA
										if (!Validaciones.isCollectionNotEmpty(listaCapResultado)){
											for (ShvCobTransaccion transaccion : listaTransacciones){
												if (transaccion.haySaldoAPagarOSaldoACobrar()){
													apropiarSaldoAPagar(transaccion);
													apropiarSaldoACobrar(transaccion);
												}
												transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
												transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
												
											}
										}
									}
								}
								cobro = cobroDao.modificar(cobro);
							}					
						} else {
							for (ShvCobMedioPago mp : listaMediosPagoCompensacion) {
								
								if(mp instanceof ShvCobMedioPagoCompensacionProveedor){
									ShvCobMedioPagoCompensacionProveedor mpProveedor = (ShvCobMedioPagoCompensacionProveedor)mp;
									mpProveedor.getDocumentoCap().getResultado().addAll(listaCapResultado);
									//Si hay error, seteo el medio de pago y transaccion en ERROR
									if (Validaciones.isCollectionNotEmpty(listaCapResultado)){
										mpProveedor.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
										mpProveedor.setMensajeEstado(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.registrarCompensacionSap.detalle.verObservacionesDocumentosCap"));
										mp.getTransaccion().setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
									}
								} else if(mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
									
									ShvCobMedioPagoCompensacionLiquidoProducto mpLiquidoProducto = (ShvCobMedioPagoCompensacionLiquidoProducto)mp;
									
									mpLiquidoProducto.getDocumentoCap().getResultado().addAll(listaCapResultado);
									//Si hay error, seteo el medio de pago y transaccion en ERROR
									if (Validaciones.isCollectionNotEmpty(listaCapResultado)){
										mpLiquidoProducto.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
										mpLiquidoProducto.setMensajeEstado(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.registrarCompensacionSap.detalle.verObservacionesDocumentosCap"));
										mp.getTransaccion().setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
									}
								}
							}
						}
					}
					
				}
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ReintentoExcepcion e) {
			throw new ReintentoExcepcion(e.getIdOperacion(),idTransaccion, e.getCobrador(), e.getTipoInvocacion(), e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 */
	private void apropiarSaldoACobrar(ShvCobTransaccion transaccion) {
		
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(TipoMedioPagoEnum.SALDO_A_COBRAR.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())){
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
				medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			}
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 */
	private void apropiarSaldoAPagar(ShvCobTransaccion transaccion) {
		
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
			if (TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
				transaccion.getTratamientoDiferencia().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			}
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

	/**
	 * 
	 * @param listaCapResultado
	 * @param resultado
	 * @param documentoCap
	 * @return
	 */
	private List<ShvCobMedioPagoDocumentoCapResultado> agregarDocumentoCapResultadoError(List<ShvCobMedioPagoDocumentoCapResultado> listaCapResultado, 
			ResultadoInvocacion resultado, ShvCobMedioPagoDocumentoCap documentoCap) {
		
		ShvCobMedioPagoDocumentoCapResultado documentoCapResultado = new ShvCobMedioPagoDocumentoCapResultado();
		
		if (Validaciones.isObjectNull(resultado)){
			documentoCapResultado.setTipo(TipoResultadoEnum.ERROR_SERVICIO);
			documentoCapResultado.setDescripcion(Propiedades.MENSAJES_PROPIEDADES.getString("error.ws.formato.imputacion.sap"));
		} else {
			documentoCapResultado.setTipo(TipoResultadoEnum.getEnumByDescripcionSap(resultado.getResultadoInvocacion()));
			documentoCapResultado.setCodigo(resultado.getCodigoError());
			documentoCapResultado.setDescripcion(resultado.getDescripcionError());
		}
		
		documentoCapResultado.setFecha(new Date());
		documentoCapResultado.setDocumentoCap(documentoCap);
		listaCapResultado.add(documentoCapResultado);
		
		return listaCapResultado;
	}

	
	/**
	 * 
	 * @param documentoCap
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 */
	private EntradaSapRegistrarCompensacionWS prepararImputacionPartidasSap(ShvCobMedioPagoDocumentoCap documentoCap, Long idOperacion) throws NegocioExcepcion, PersistenciaExcepcion {
		
		EntradaSapRegistrarCompensacionWS entrada = new EntradaSapRegistrarCompensacionWS();
		
		ShvCobEdCobroSimplificado cobroSimplificado = cobroOnlineDao.buscarCobroSimplificado(idOperacion);
		String textoPosicion = parametroServicio.getValorTexto(Constantes.PARAM_COMPENSACION_RAV + cobroSimplificado.getIdSegmento());
		UsuarioLdapDto usuarioAprob = ldapServicio.buscarUsuarioPorUidEnMemoria(cobroSimplificado.getUsuarioAprobadorSupervisorOperacEspeciales());
		
		
		entrada.setIdOperacionShiva(idOperacion);
		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		for (ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalle : documentoCap.getDetalle()) {
			
			Partida partida = new Partida();
			
			partida.setIdSociedad(documentoCapDetalle.getIdSociedad());
			partida.setIdProveedor(documentoCapDetalle.getIdProveedor());
			partida.setEjercicioFiscalDocSAP(documentoCapDetalle.getEjercicioFiscalDocSAP().toString());
			partida.setNumeroDocSAP(documentoCapDetalle.getNumeroDocSAP());
			partida.setPosicionDocSAP(documentoCapDetalle.getPosicionDocSAP().toString());
			partida.setMonedaDocProveedor(documentoCapDetalle.getMonedaDocProveedor().getSigla());
			partida.setClaseDocSAP(documentoCapDetalle.getTipoDocumento().name());
			partida.setMesFiscalDocSAP(documentoCapDetalle.getMesFiscalDocSap().toString());
			partida.setIndicador(documentoCapDetalle.getIndicador());
			partida.setImporteAplicadoMonedaDolar(documentoCapDetalle.getImporteAplicadoMonedaDolar());
			partida.setImporteAplicadoMonedaPesos(documentoCapDetalle.getImporteAplicadoMonedaPesos());
			partida.setMonedaCompensacion(documentoCap.getMoneda().getSigla());
			partida.setBloqueoPago(documentoCapDetalle.getBloqueoPago().getCodigo());
			partida.setNumeroDocSAPVinculado(documentoCapDetalle.getNumeroDocSAPVinculado());
			
			if (!Validaciones.isObjectNull(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado())) {
				partida.setEjercicioFiscalDocSAPVinculado(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado().toString());
			}

			partida.setPosicionDocSAPVinculado(documentoCapDetalle.getPosicionDocSAPVinculado().toString());
			partida.setClaveRef2(documentoCapDetalle.getClaveRef2());
			
			partida.setImporteRenglonMonedaOrigenAFechaEmision(documentoCapDetalle.getImporteRenglonMonedaOrigenAFechaEmision());
			partida.setTextoPosicion(textoPosicion+" - "+usuarioAprob.getNombreCompleto());
			partida.setFechaEmision(documentoCapDetalle.getFechaEmision());
			
			
					
			entrada.getListaPartidas().add(partida);
		}

		try {
			entrada.setPdfFlie(cobroBatchServicio.generarPdfResumenCompensacion(documentoCap.getIdCobro()).getByteArray());
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return entrada;
	}

	/**
	 * 
	 * @param listaMediosPagoCompensacion
	 * @return
	 */
	private ShvCobMedioPagoDocumentoCap obtenerDocumentoCap(List<ShvCobMedioPago> listaMediosPagoCompensacion) {
		
		ShvCobMedioPagoDocumentoCap documentoCap = null;
		
		for (ShvCobMedioPago medioPago : listaMediosPagoCompensacion){
			
			documentoCap = new ShvCobMedioPagoDocumentoCap();
			
			if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
				documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto)medioPago).getDocumentoCap();
				
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor){
				documentoCap = ((ShvCobMedioPagoCompensacionProveedor)medioPago).getDocumentoCap();
			}
			
			break;
			
		}
		
		return documentoCap;
	}
		
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
}