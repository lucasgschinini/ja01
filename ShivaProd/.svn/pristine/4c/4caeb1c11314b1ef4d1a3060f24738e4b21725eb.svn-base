/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionReintegroShvCobMedioPago;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobMedioPago;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobTransaccion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.AccionesReglaCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.AccionesSobreDiferenciaDeCambioEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReglaCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionRuntimeException;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionImporteMaximoDebitoProximaFacturaSimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionMonedaDebitoProximaFacturaSimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida.AcuerdoFacturacion;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumentoMic;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Documento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.InfoAdicionalMedPagNoComisionables;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Transaccion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionDocumentoMicEnDeimos;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionFacturaCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionFacturaMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionFacturaUsuario;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoShiva;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoUsuario;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionTratamientoDiferencia;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionConsultarProveedorSap;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoCreditoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoCreditoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoDebitoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionGeneracionCargoDebitoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionVerificarPartidasSap;
import ar.com.telecom.shiva.negocio.executor.CobroSimulacionExecutor;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionCalipsoRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionDeimosRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionMicRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ConsultarProveedoresSapRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoCalipsoRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.GeneracionCargoMicRto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.VerificarPartidasSapRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReglaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionCesionCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIIBB;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIntercompany;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionOtras;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapResultado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoSaldoACobrar;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaCobro;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ReglaCobroFiltro;

/**
 * @author u564030
 *
 */
public class CobroBatchSimulacionServicioImpl extends Servicio implements ICobroBatchSimulacionServicio {

	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private ITipoMedioPagoServicio tipoMedioPagoServicio;
	@Autowired
	private IValorServicio valorServicio;
	@Autowired
	private ICobroOnlineDao cobroOnlineDao;
	@Autowired
	private ICobroDao cobroDao;
	@Autowired
	private ICobroOnlineClienteDao cobroOnlineClienteDao;
	@Autowired
	private ITareaServicio tareaServicio;
	@Autowired
	private MailServicio mailServicio;
	@Autowired
	private ILdapServicio ldapServicio;
	@Autowired
	private ExportacionDetalleCobroImpl exportacionDetalleCobro;
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired
	private IWorkflowService workflowService;
	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;

	@Autowired
	private ICobroBatchServicio cobroBatchServicio;

	@Autowired
	private IClienteCalipsoServicio clienteCalipsoServicio;
	@Autowired
	private IClienteDeimosServicio clienteDeimosServicio;
	@Autowired
	private IMicJmsSyncServicio micJmsSyncServicio;
	@Autowired
	private IMensajeriaTransaccionDao mensajeriaTransaccionDao;

	@Autowired
	private IFacJmsSyncServicio facJmsSyncServicio;

	@Autowired
	private ICobroBatchSoporteServicio cobroBatchSoporteServicio;

	@Autowired
	ICobroOnlineDebitoDao cobroOnlineDebitoDao;
	
	@Autowired
	ITipoComprobanteDao tipoComprobanteDao;
	
	@Autowired
	IReglaCobroServicio reglaCobroServicio;

	private static final String EXTENSION_ARCHIVO_ADJUNTO = "xls";

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobEdCobro simularCobroOnline(Long idCobro) throws SimulacionCobroExcepcion, NegocioExcepcion {

		ShvCobEdCobro edCobro = null;

		//
		// Busco el cobro en la base de datos
		//
		try {
			edCobro = cobroOnlineDao.buscarCobro(idCobro);
			simularCobroOnline(edCobro);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

		return edCobro;
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void simularCobroOnline(ShvCobEdCobro edCobro) throws SimulacionCobroExcepcion, NegocioExcepcion {

		try {

			//
			// Convierto componentes del cobro online en componentes del cobro
			// batch
			//

			ComponentesCobroBatch componentesCobroBatch = cobroBatchSoporteServicio.convertirCobroEnEdicionEnComponentesCobroBatch(edCobro);
			// loguearCobroOriginal(componentesCobroBatch);

			//
			// Valido los componentes del cobro
			//

			validarConsistenciaCobroOnline(componentesCobroBatch);

			//
			// Armo las transacciones
			//
			Set<ShvCobTransaccion> listaTransacciones = armarTransacciones(componentesCobroBatch);
			// loguearTransaccionesArmadas(listaTransacciones);

			//
			// Armo el objeto Cobro
			//

			ShvCobCobro cobro = cobroBatchSoporteServicio.armarCobro(componentesCobroBatch);

			//
			// Seteo de transacciones
			//
			cobro.getOperacion().setTransacciones(listaTransacciones);
			
			//
			// Valido armado del cobro generado
			//

			validarConsistenciaCobroBatch(cobro);

			//
			// Actualizo el tratamiento de intereses en el cobro
			//
			cobroBatchServicio.actualizarTratamientoIntereses(cobro);

			//
			// Verifico y seteo si contiene aplicacion manual
			//
			verificarSiContieneAplicacionManual(cobro);
			
			//
			// Almaceno el cobro en la base de datos
			//
			cobro = cobroDao.insertarCobro(cobro);

			// Verifico dependencia de transaccion
			//
			verificarDependenciaTransaccion(cobro);
			//
			int cantidadTransaccionesMaximoOnlinePermitido = parametroServicio.getValorNumerico(Mensajes.COBROS_VALIDACION_CANTIDAD_TRANSACCIONES_MAXIMO_ONLINE_PERMITIDO).intValue();

			if (cobro.getOperacion().getTransacciones().size() > cantidadTransaccionesMaximoOnlinePermitido) {

				//
				// Seteo la marca de simulación batch en proceso
				//
				workflowService.agregarWorkflowMarca(cobro.getWorkflow(), componentesCobroBatch.getUsuarioCreacion(), MarcaEnum.SIMULACION_BATCH_EN_PROCESO);
			} else {

				simularCobroProcesamientoOnline(cobro, componentesCobroBatch.getUsuarioCreacion());
			}

		} catch(SimulacionRuntimeException e){
			throw new SimulacionCobroExcepcion("", e.getCause(), e.getMessage());
		}catch (SimulacionCobroExcepcion e) {
			throw e;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void simularCobroProcesamientoOnline(ShvCobCobro cobro, String usuarioModificacion) throws SimulacionCobroExcepcion, NegocioExcepcion {

		//
		// Simulo contra los cobradores
		//
		cobro = simularCobroEnCobradores(cobro);

		// Actualizo los calculos de intereses
		cobro = actualizarCalculoIntereses(cobro);

		MarcaEnum marcaResultadoSimulacion = MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO;

		// Determino si la simulacion ha finalizado correctamente
		if (!esSimulacionExitosa(cobro)) {
			marcaResultadoSimulacion = MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_ERROR;
		}
		// Si la simulacion es exitos calculo el checkSum del cobro
		String hash = cobroOnlineServicio.generarCheckSum(cobro.getIdCobro());
		cobroOnlineServicio.persistirCheckSum(cobro.getIdCobro(), hash, usuarioModificacion, Utilidad.obtenerFechaActual());

		//
		// Seteo la marca de resultado de simulacion
		//
		workflowService.agregarWorkflowMarca(cobro.getWorkflow(), usuarioModificacion, marcaResultadoSimulacion);
	}

	public void simularCobroBatch(ShvCobCobro cobro, String usuarioModificacion) throws SimulacionCobroExcepcion, NegocioExcepcion {

		try {
			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Comienza simulacion batch.", String.valueOf(cobro.getOperacion().getIdOperacion())));

			// Simulo el cobro en cobradores
			simularCobroProcesamientoBatch(cobro, usuarioModificacion);

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Levanto una foto actualizada del cobro.", String.valueOf(cobro.getOperacion().getIdOperacion())));

			// Levanto una versión actualizada del cobro, luego de la simulación
			cobro = cobroDao.buscarCobro(cobro.getIdCobro());

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Envio correo y genero tareas.", String.valueOf(cobro.getOperacion().getIdOperacion())));

			// envio mail y genero tarea en base a la ultima foto del cobro
			enviarMailyGenerarTareaSimulacionBatchFinalizada(cobro);

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Finaliza simulacion batch.", String.valueOf(cobro.getOperacion().getIdOperacion())));

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void simularCobroProcesamientoBatch(ShvCobCobro cobro, String usuarioModificacion) throws SimulacionCobroExcepcion, NegocioExcepcion {

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Comienzo a simular en cobradores.", String.valueOf(cobro.getOperacion().getIdOperacion())));
		//
		// Simulo contra los cobradores
		//
		cobro = simularCobroEnCobradores(cobro);

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Termino de simular en cobradores.", String.valueOf(cobro.getOperacion().getIdOperacion())));

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Ejecuto actualizacion de calculo de intereses.", String.valueOf(cobro.getOperacion().getIdOperacion())));

		// Actualizo los calculos de intereses
		cobro = actualizarCalculoIntereses(cobro);

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Actualizo el cobro en la base de datos.", String.valueOf(cobro.getOperacion().getIdOperacion())));

		//
		// Actualizo el cobro en la base
		//
		try {
			cobro = cobroDao.modificar(cobro);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Defino marca y actualizo workflow.", String.valueOf(cobro.getOperacion().getIdOperacion())));

		MarcaEnum marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO;

		// Determino si la simulacion ha finalizado correctamente
		if (!esSimulacionExitosa(cobro)) {
			marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR;
		}
		// Calculo el checkSum del cobro
		String hash = cobroOnlineServicio.generarCheckSum(cobro.getIdCobro());
		cobroOnlineServicio.persistirCheckSum(cobro.getIdCobro(), hash, usuarioModificacion, Utilidad.obtenerFechaActual());

		//
		// Seteo la marca de resultado de simulacion
		//
		workflowService.agregarWorkflowMarca(cobro.getWorkflow(), usuarioModificacion, marcaResultadoSimulacion);
	}

	/**
	 * 
	 * @param cobro
	 * @return
	 */
	private boolean esSimulacionExitosa(ShvCobCobro cobro) {

		boolean simulacionExitosa = true;

		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {

			if (!Validaciones.isObjectNull(transaccion.getFactura())) {
				if (TipoMensajeEstadoEnum.ERR.equals(transaccion.getFactura().getTipoMensajeEstado())) {
					simulacionExitosa = false;
					break;
				}
			}

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
				if (TipoMensajeEstadoEnum.ERR.equals(transaccion.getTratamientoDiferencia().getTipoMensajeEstado())) {
					simulacionExitosa = false;
					break;
				}
			}

			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
				if (TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
					simulacionExitosa = false;
					break;
				}
			}

			if (TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())) {
				simulacionExitosa = false;
				break;
			}

			// Agregamos validacion para tener en cuenta resultado
			// verificar/registra compensacion en SAP
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {

				ShvCobMedioPagoDocumentoCap documentoCap = null;
				if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
					documentoCap = ((ShvCobMedioPagoCompensacionProveedor) medioPago).getDocumentoCap();
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
					documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getDocumentoCap();
				}

				if (!Validaciones.isObjectNull(documentoCap) && documentoCap.getResultado().size() != 0) {
					simulacionExitosa = false;
					break;
				}

			}
		}

		return simulacionExitosa;
	}

	// /**
	// *
	// * @param componentesCobroBatch
	// */
	// private void loguearCobroOriginal(ComponentesCobroBatch
	// componentesCobroBatch) {
	//
	// System.out.println("");
	// System.out.println("Contenido inicial del cobro antes de aplicar reglas... (Id Cobro: "
	// +
	// componentesCobroBatch.getIdCobro() + " | Id Operacion: " +
	// componentesCobroBatch.getIdOperacion() + ")");
	// System.out.println("");
	//
	// System.out.println("Documentos a cobrar");
	//
	// StringBuffer print = new StringBuffer();
	// for (ShvCobFactura factura : componentesCobroBatch.getListaFacturas()) {
	//
	// print = new StringBuffer();
	//
	// print.append("Factura: ");
	// print.append(" | Documento: ");
	// print.append(factura.getTipoComprobante());
	// print.append(" ");
	// print.append(factura.getClaseComprobante());
	// print.append(factura.getSucursalComprobante());
	// print.append(factura.getNumeroComprobante());
	// print.append(" | Fecha vencimiento: ");
	// print.append(Utilidad.formatDate(factura.getFechaVencimiento()));
	// print.append(" | Importe: ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(factura.getImporteCobrar(),
	// true, true), 12));
	//
	// if (factura instanceof ShvCobFacturaMic) {
	// ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;
	// print.append(" | Destransferir 3eros : ");
	// print.append(facturaMic.getDestransferirTerceros());
	// print.append(" | Cobro 2do vencimien : ");
	// print.append(facturaMic.getCobrarSegundoVencimiento());
	// }
	//
	// System.out.println(print.toString());
	// }
	//
	// System.out.println("");
	// System.out.println("Medios de pago a utilizar");
	//
	// for (ShvCobMedioPago medioPago :
	// componentesCobroBatch.getListaMediosDePago()) {
	// print = new StringBuffer();
	//
	// print.append("Medio de pago: ");
	// print.append(medioPago.getTipoMedioPago().getDescripcion());
	// print.append(" | Importe: ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(medioPago.getImporte(),
	// true, true), 12));
	// print.append("| Fecha valor: ");
	// print.append(Utilidad.formatDate(medioPago.getFechaValor()));
	//
	// print.append("| Puede aplicar sobre DUC: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoDUC());
	// print.append("| Puede aplicar sobre Reintegro: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoReintegro());
	// print.append("| Puede aplicar sobre Cobro: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoCobro());
	//
	// System.out.println(print.toString());
	// }
	//
	// System.out.println("");
	// System.out.println("Tratamiento de diferencia");
	// if
	// (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()))
	// {
	// print = new StringBuffer();
	//
	// print.append("Tipo: ");
	// print.append(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia().getDescripcion());
	// print.append("| Importe ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(componentesCobroBatch.getTratamientoDiferencia().getImporte(),
	// true, true), 12));
	// print.append("| Fecha reintegro ");
	// print.append(Utilidad.formatDate(componentesCobroBatch.getTratamientoDiferencia().getFechaTramiteReintegro()));
	// // print.append("| Fecha valor ");
	// //
	// print.append(Utilidad.formatDate(componentesCobroBatch.getTratamientoDiferencia().));
	// print.append("| Numero reintegro ");
	// print.append(componentesCobroBatch.getTratamientoDiferencia().getNumeroTramiteReintegro());
	// print.append("| Sistema ");
	// // print.append(componentesCobroBatch.getTratamientoDiferencia());
	// print.append("| Linea ");
	// // print.append(componentesCobroBatch.getTratamientoDiferencia().get);
	// print.append("| Acuerdo ");
	// // print.append();
	// print.append("| IdCliente Acuerdo ");
	// // print.append();
	//
	// } else {
	// System.out.println("No existe un tratamiento de diferencia");
	// }
	// System.out.println("");
	// }
	//
	// /**
	// *
	// * @param listaTransacciones
	// */
	// private void loguearTransaccionesArmadas(Set<ShvCobTransaccion>
	// listaTransacciones) {
	//
	// System.out.println("");
	// System.out.println("Resultado del armado de transacciones...");
	// System.out.println("");
	//
	// StringBuffer print = new StringBuffer();
	//
	// for (ShvCobTransaccion transaccion : listaTransacciones) {
	//
	// print = new StringBuffer();
	// print.append("Transaccion: ");
	// print.append(transaccion.getNumeroTransaccion());
	// System.out.println(print);
	//
	// ShvCobFactura factura = transaccion.getFactura();
	//
	// if (!Validaciones.isObjectNull(transaccion.getFactura())) {
	// print = new StringBuffer();
	// print.append("ID Factura: ");
	// // print.append(factura.getIdFactura());
	// print.append(" | Documento: ");
	// print.append(factura.getTipoComprobante());
	// print.append(" ");
	// print.append(factura.getClaseComprobante());
	// print.append(factura.getSucursalComprobante());
	// print.append(factura.getNumeroComprobante());
	// print.append(" | Fecha valor: ");
	// print.append(Utilidad.formatDate(factura.getFechaValor()));
	// print.append(" | Importe: ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(factura.getImporteCobrar(),
	// true, true), 12));
	//
	// if (factura instanceof ShvCobFacturaMic) {
	// ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;
	// print.append(" | Destransferir 3eros : ");
	// print.append(facturaMic.getDestransferirTerceros());
	// print.append(" | Cobro 2do vencimien : ");
	// print.append(facturaMic.getCobrarSegundoVencimiento());
	// }
	//
	// System.out.println(print.toString());
	// }
	//
	// if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
	// print = new StringBuffer();
	// print.append("ID Tratamiento diferencia: ");
	// print.append(transaccion.getTratamientoDiferencia().getIdTratamientoDiferencia());
	// print.append(" | Tipo: ");
	// print.append(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia().getDescripcion());
	// print.append(" ");
	// print.append(" | Fecha valor (tramite reintegro): ");
	// print.append(transaccion.getTratamientoDiferencia().getFechaTramiteReintegro());
	// print.append(" | Importe: ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(transaccion.getTratamientoDiferencia().getImporte(),
	// true, true), 12));
	//
	// }
	//
	// System.out.println("Lista de medios de pago");
	// for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
	// print = new StringBuffer();
	//
	// print.append("        Medio de pago: ");
	// print.append(medioPago.getTipoMedioPago().getDescripcion());
	// print.append(" | Importe: ");
	// print.append(Utilidad.rellenarEspaciosIzquierda(Utilidad.formatCurrency(medioPago.getImporte(),
	// true, true), 12));
	// print.append("| Fecha valor: ");
	// print.append(Utilidad.formatDate(medioPago.getFechaValor()));
	//
	// print.append("| Puede aplicar sobre DUC: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoDUC());
	// print.append("| Puede aplicar sobre Reintegro: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoReintegro());
	// print.append("| Puede aplicar sobre Cobro: ");
	// print.append(medioPago.getUsoPorMoneda().getPermiteUsoCobro());
	//
	// System.out.println(print.toString());
	// }
	// System.out.println("");
	// }
	// }

	/**
	 * Despues de finalizar la simulación, todo registro que haya calculado
	 * intereses, se actualiza % de bonificación = 100% Importe a bonificar =
	 * campo cobrador intereses generados Campo que hacer con los intereses Si
	 * es TM cambiar por BM Si es TV cambiar por BV
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private ShvCobCobro actualizarCalculoIntereses(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			ShvCobFactura factura = transaccion.getFactura();
			if (!Validaciones.isObjectNull(factura)) {
				factura.setImporteBonificacionIntereses(factura.getCobradorInteresesGenerados());
				factura.setPorcentajeBonifIntereses(100);
				if (TratamientoInteresesEnum.TM.equals(factura.getQueHacerConIntereses())) {
					factura.setQueHacerConIntereses(TratamientoInteresesEnum.BM);
				}
				if (TratamientoInteresesEnum.TV.equals(factura.getQueHacerConIntereses())) {
					factura.setQueHacerConIntereses(TratamientoInteresesEnum.BV);
				}
			}

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {

				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

					ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
					tratamientoDiferencia.setImporteBonificacionIntereses(tratamientoDiferencia.getCobradorInteresesGenerados());
					tratamientoDiferencia.setPorcentajeBonifIntereses(100);
					if (TratamientoInteresesEnum.TM.equals(tratamientoDiferencia.getQueHacerConIntereses())) {
						tratamientoDiferencia.setQueHacerConIntereses(TratamientoInteresesEnum.BM);
					}
					if (TratamientoInteresesEnum.TV.equals(tratamientoDiferencia.getQueHacerConIntereses())) {
						tratamientoDiferencia.setQueHacerConIntereses(TratamientoInteresesEnum.BV);
					}
				}
			}

			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
					ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;

					debitoProximaFacturaCalipso.setImporteBonificacionIntereses(debitoProximaFacturaCalipso.getCobradorIntereseGenerados());
					debitoProximaFacturaCalipso.setPorcentajeBonifIntereses(100);
					if (TratamientoInteresesEnum.TM.equals(debitoProximaFacturaCalipso.getQueHacerConIntereses())) {
						debitoProximaFacturaCalipso.setQueHacerConIntereses(TratamientoInteresesEnum.BM);
					}
					if (TratamientoInteresesEnum.TV.equals(debitoProximaFacturaCalipso.getQueHacerConIntereses())) {
						debitoProximaFacturaCalipso.setQueHacerConIntereses(TratamientoInteresesEnum.BV);
					}

				} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;

					debitoProximaFacturaMic.setImporteBonificacionIntereses(debitoProximaFacturaMic.getCobradorInteresesGenerados());
					debitoProximaFacturaMic.setPorcentajeBonifIntereses(100);

					if (TratamientoInteresesEnum.TM.equals(debitoProximaFacturaMic.getQueHacerConIntereses())) {
						debitoProximaFacturaMic.setQueHacerConIntereses(TratamientoInteresesEnum.BM);
					}

					if (TratamientoInteresesEnum.TV.equals(debitoProximaFacturaMic.getQueHacerConIntereses())) {
						debitoProximaFacturaMic.setQueHacerConIntereses(TratamientoInteresesEnum.BV);
					}
				}
			}
		}

		return cobro;
	}

	/**
	 * 
	 * @param edCobro
	 * @throws NegocioExcepcion
	 */
	private void validarConsistenciaCobroOnline(ComponentesCobroBatch componentesCobroBatch) throws NegocioExcepcion, SimulacionCobroExcepcion {

		// «VAL» La operación de cobro/reintegro debe netear en si misma
		validarNeteoCobroConfigurado(componentesCobroBatch);

		// «RN» En una operación con saldo a pagar debe haber al menos un débito
		// seleccionado
		validarOperacionConSaldoAPagarDebeExistirAlMenosUnDebito(componentesCobroBatch);

		// «VAL» Validar que existan créditos utilizables con Aplicación Manual
		// suficientes para cubrir el monto de reintegro cuando haya uno.
		validarExistenciaCreditosDeUsoAplicacionManual(componentesCobroBatch);

		// «VAL» Validar que existan créditos reintegrables suficientes para
		// cubrir el monto de reintegro cuando haya uno.
		validarExistenciaCreditosReintegrables(componentesCobroBatch);

		// «RN» Al momento de realizar un reintegro, la fecha valor del medio de
		// pago reintegrable que se utilice debe ser menor o igual a la fecha
		// del día
		validarFechaValorMedioPagoReintegro(componentesCobroBatch);

		// «RN» Una retención se debe aplicar de manera completa dentro de una
		// operación de cobro
		validarMedioPagoRetencionAplicacionCompleta(componentesCobroBatch);

		// «RN» La compensacion incluida dentro de una operación de cobro deben
		// aplicar sobre todos los documentos
		validarAplicacionCompensacion(componentesCobroBatch);

		// «VAL» Validar que existan medios de pago aplicables a DUCs
		// suficientes para cubrir el monto de DUCs cuando haya.
		validarExistenciaMediosPagoSuficientesParaDuc(componentesCobroBatch);

		// «RN» Al momento de realizar la aplicación a un DUC, la fecha valor
		// del medio de pago que se utilice debe ser menor o igual a la fecha
		// del día
		validarFechaValorMedioPagoParaDuc(componentesCobroBatch);

		// «RN» El saldo a pagar asociado a una compensación no puede ser
		// inferior a un porcentaje parametrizable
		// sobre el total de los comprobantes CAP incluidos siempre y cuando
		// sufran retenciones y las mismas
		// no hayan sido efectuadas al momento.
		//
		// Esta regla se origina en el hecho que sobre un pago que se lleve a
		// cabo a un proveedor se deben efectuar
		// retenciones impositivas. Dado que al momento de llevar a cabo la
		// operación la información de impuestos
		// asociada a las facturas seleccionadas no está cargada en el sistema
		// SAP se procede a utilizar un porcentaje estimado.
		// En esta estimación se deben incluir todos los documentos CAP
		// incluidos en la operación.
		validarOperacionConSaldoAPagarSaldoNoInferiorAPorcentajeDeRetencion(componentesCobroBatch);

		validarCompensacionesAutomaticas(componentesCobroBatch);
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «RN» Al momento de
	 * realizar un reintegro, la fecha valor del medio de pago reintegrable que
	 * se utilice debe ser menor o igual a la fecha del día
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarFechaValorMedioPagoReintegro(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()) && ((!TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) && (!TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) && !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) && !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) && !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) && !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()))))) {

			BigDecimal totalImporteCreditosReintegrables = BigDecimal.ZERO;
			Date fechaActual = DateUtils.truncate(new Date(), Calendar.DATE);

			for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {

				if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoReintegro())) {

					Date fechaMedioPago = DateUtils.truncate(medioPago.getFechaValor(), Calendar.DATE);

					if (fechaMedioPago.compareTo(fechaActual) <= 0) {
						totalImporteCreditosReintegrables = totalImporteCreditosReintegrables.add(medioPago.getImporte());
					}
				}
			}

			if (totalImporteCreditosReintegrables.compareTo(componentesCobroBatch.getTratamientoDiferencia().getImporte()) < 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.fechaValorMedioPagoReintegroNoValida"));
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «RN» Una retención se
	 * debe aplicar de manera completa dentro de una operación de cobro
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarMedioPagoRetencionAplicacionCompleta(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion, NegocioExcepcion {

		List<Long> listaIdValores = new ArrayList<Long>();

		for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				listaIdValores.add(((ShvCobMedioPagoShiva) medioPago).getIdValor());
			}
		}

		if (Validaciones.isCollectionNotEmpty(listaIdValores)) {

			// Busco los valores asociados a mis medios de pago
			List<ShvValValorSimplificado> listaValoresSimplificados = valorServicio.listarValoresSimplificados(listaIdValores);

			// Recorro los valores encontrados, verificando para cada uno de
			// ellos si el importe a utilizar coincide con el saldo disponible
			for (ShvValValorSimplificado valorSimplificado : listaValoresSimplificados) {

				// Validamos importes de retenciones
				if (TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor() == valorSimplificado.getIdTipoValor().intValue()) {

					BigDecimal importeValorAUsar = BigDecimal.ZERO;

					// Me vuelvo a buscar los medios de pago que hacen
					// referencia a este valor, para sumar importes a Cobrar
					for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {
						if (medioPago instanceof ShvCobMedioPagoShiva) {

							ShvCobMedioPagoShiva medioPagoShiva = (ShvCobMedioPagoShiva) medioPago;

							if (valorSimplificado.getIdValor().equals(medioPagoShiva.getIdValor())) {
								importeValorAUsar = importeValorAUsar.add(medioPagoShiva.getImporte());
							}
						}
					}

					// Comparo importe total a usar del valor vs saldo
					// disponible, si no son iguales mando error
					if (valorSimplificado.getSaldoDisponible().compareTo(importeValorAUsar) != 0) {
						throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.medioPagoRetencionNoAplicaCompleta"));
					}
				}
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «RN» Las compensaciones
	 * incluidas dentro de una operación de cobro deben aplicar sobre todos los
	 * documentos
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarAplicacionCompensacion(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		// Sumo el total de importe de compensaciones
		BigDecimal totalImporteCompensaciones = BigDecimal.ZERO;
		for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {
			if (medioPago instanceof ShvCobMedioPagoCompensacionOtras || medioPago instanceof ShvCobMedioPagoCompensacionIIBB || medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito || medioPago instanceof ShvCobMedioPagoCompensacionProveedor || medioPago instanceof ShvCobMedioPagoCompensacionIntercompany || medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {

				totalImporteCompensaciones = totalImporteCompensaciones.add(medioPago.getImporte());
			}
		}

		if (totalImporteCompensaciones.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal totalImporteFacturas = BigDecimal.ZERO;

			// Sumo el total de importe de facturas
			for (ShvCobFactura factura : componentesCobroBatch.getListaFacturas()) {
				totalImporteFacturas = totalImporteFacturas.add(factura.getImporteCobrar());
			}

			// Obtengo la ultima factura de la lista de facturas
			ShvCobFactura ultimaFactura = componentesCobroBatch.obtenerUltimaFacturaPorFechaVencimiento();

			// Al total de importe de facturas, le resto el importe de
			// compensaciones, y obtengo la diferencia
			BigDecimal diferenteEntreCompensacionYFactura = totalImporteFacturas.subtract(totalImporteCompensaciones);

			// Veo si la diferencia en totalImporteFacturas es mayor a cero y
			// menor al importe de la ultima factura
			if (diferenteEntreCompensacionYFactura.compareTo(ultimaFactura.getImporteCobrar()) >= 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.medioPagoCompensacionNoAplicaSobreTodosLosDocumentos"));
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «VAL» Validar que
	 * existan medios de pago aplicables a DUCs suficientes para cubrir el monto
	 * de DUCs cuando haya.
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarExistenciaMediosPagoSuficientesParaDuc(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		BigDecimal importeTotalDocumentosDUC = BigDecimal.ZERO;

		for (ShvCobFactura factura : componentesCobroBatch.getListaFacturas()) {
			if (TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante().getIdTipoComprobante())) {
				importeTotalDocumentosDUC = importeTotalDocumentosDUC.add(factura.getImporteCobrar());
			}
		}

		// Si hay documentos de tipo DUC para cobrar
		if (importeTotalDocumentosDUC.compareTo(BigDecimal.ZERO) > 0) {

			// Verifico que existan medios de pago que se puedan aplicar
			BigDecimal totalImporteCreditosDuc = BigDecimal.ZERO;

			for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {

				if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoDUC())) {
					totalImporteCreditosDuc = totalImporteCreditosDuc.add(medioPago.getImporte());
				}
			}

			if (totalImporteCreditosDuc.compareTo(importeTotalDocumentosDUC) < 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noExistenMediosPagoSuficientesParaDuc"));
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «RN» Al momento de
	 * realizar la aplicación a un DUC, la fecha valor del medio de pago que se
	 * utilice debe ser menor o igual a la fecha del día
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarFechaValorMedioPagoParaDuc(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		BigDecimal importeTotalDocumentosDUC = BigDecimal.ZERO;

		for (ShvCobFactura factura : componentesCobroBatch.getListaFacturas()) {
			if (TipoComprobanteEnum.DUC.name().equals(factura.getTipoComprobante().getIdTipoComprobante())) {
				importeTotalDocumentosDUC = importeTotalDocumentosDUC.add(factura.getImporteCobrar());
			}
		}

		// Si hay documentos de tipo DUC para cobrar
		if (importeTotalDocumentosDUC.compareTo(BigDecimal.ZERO) > 0) {

			// Verifico que existan medios de pago que se puedan aplicar
			BigDecimal totalImporteCreditosDUC = BigDecimal.ZERO;
			Date fechaActual = DateUtils.truncate(new Date(), Calendar.DATE);

			for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {

				if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoDUC())) {

					Date fechaMedioPago = DateUtils.truncate(medioPago.getFechaValor(), Calendar.DATE);

					if (fechaMedioPago.compareTo(fechaActual) <= 0) {
						totalImporteCreditosDUC = totalImporteCreditosDUC.add(medioPago.getImporte());
					}
				}
			}

			if (totalImporteCreditosDUC.compareTo(importeTotalDocumentosDUC) < 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.fechaValorMedioPagoParaDucMayorAFechaActual"));
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «VAL» La operación de
	 * cobro/reintegro debe netear en si misma
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarNeteoCobroConfigurado(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		BigDecimal totalDebitos = BigDecimal.ZERO;
		BigDecimal totalCreditos = BigDecimal.ZERO;

		for (ShvCobFactura factura : componentesCobroBatch.getListaFacturas()) {
			totalDebitos = totalDebitos.add(factura.getImporteCobrar());
		}

		for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {
			totalCreditos = totalCreditos.add(medioPago.getImporte());
		}

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia())) {
			totalDebitos = totalDebitos.add(componentesCobroBatch.getTratamientoDiferencia().getImporte());
		}

		if (totalDebitos.compareTo(totalCreditos) != 0) {
			throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobroConfiguradoNoNetea"));
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «VAL» Validar que
	 * existan créditos utilizables con Aplicación Manual suficientes para
	 * cubrir el monto de reintegro cuando haya uno.
	 * 
	 * @author u587496 Guido.Settecerze
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarExistenciaCreditosDeUsoAplicacionManual(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()) 
				&& (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
						|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
						|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
						|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
						|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()))) {

			BigDecimal totalImporteCreditosAplicacionManual = BigDecimal.ZERO;
			for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {

				if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoAplicacionManual())) {
					totalImporteCreditosAplicacionManual = totalImporteCreditosAplicacionManual.add(medioPago.getImporte());
				}
			}

			if (totalImporteCreditosAplicacionManual.compareTo(componentesCobroBatch.getTratamientoDiferencia().getImporte()) < 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noExistenMediosPagoSuficientesParaAplicacionManual"));
			}
		}
	}

	/**
	 * Verifica la aplicabilidad de la regla de negocio «VAL» Validar que
	 * existan créditos reintegrables suficientes para cubrir el monto de
	 * reintegro cuando haya uno.
	 * 
	 * @param componentesCobroBatch
	 * @throws NegocioExcepcion
	 * @throws ValidacionExcepcion
	 */
	private void validarExistenciaCreditosReintegrables(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()) 
				&& !TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
				&& (!TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
					&& !TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia()))) {

			BigDecimal totalImporteCreditosReintegrables = BigDecimal.ZERO;
			for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {

				if (SiNoEnum.SI.equals(medioPago.getUsoPorMoneda().getPermiteUsoReintegro())) {
					totalImporteCreditosReintegrables = totalImporteCreditosReintegrables.add(medioPago.getImporte());
				}
			}

			if (totalImporteCreditosReintegrables.compareTo(componentesCobroBatch.getTratamientoDiferencia().getImporte()) < 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noExistenMediosPagoSuficientesParaReintegro"));
			}
		}
	}

	/**
	 * «RN» En una operación con saldo a pagar debe haber al menos un débito
	 * seleccionado
	 * 
	 * @param componentesCobroBatch
	 * @throws SimulacionCobroExcepcion
	 */
	private void validarOperacionConSaldoAPagarDebeExistirAlMenosUnDebito(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion {

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()) && TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

			if (componentesCobroBatch.getListaFacturas().size() == 0) {
				throw new SimulacionCobroExcepcion("", null, Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.operacionConSaldoAPagarDebeExistirAlMenosUnDebito"));
			}
		}
	}

	/**
	 * «RN» El saldo a pagar asociado a una compensación no puede ser inferior a
	 * un porcentaje parametrizable sobre el total de los comprobantes CAP
	 * incluidos siempre y cuando sufran retenciones.
	 *
	 * Esta regla se origina en el hecho que sobre un pago que se lleve a cabo a
	 * un proveedor se deben efectuar retenciones impositivas. Dado que al
	 * momento de llevar a cabo la operación la información de impuestos
	 * asociada a las facturas seleccionadas no está cargada en el sistema SAP
	 * se procede a utilizar un porcentaje estimado. En esta estimación se deben
	 * incluir todos los documentos CAP incluidos en la operación.
	 * 
	 * • Documentos CAP propensos a sufrir retenciones ante un pago No todos los
	 * documentos CAP son propensos a sufrir retenciones al momento del pago. Se
	 * definen los tipos de comprobantes afectados por este cálculo:
	 *
	 * • DP - Fact Ret/Liq a Pag • K2 - Documento Interno • KB - Factura Proveed
	 * Nac • KC - N Crédito Prov Nac • KD - N Débito Prov Nac • KK - Fact
	 * Compras Menores • KQ - Otr Docum RG 1415 • KR - Otr Docum RG 3419 • KT -
	 * Factura Ticket
	 *
	 * • Porcentaje de retención sobre pagos Inicialmente el porcentaje será
	 * seteado en un 25% (valor estimado según promedio de impuestos
	 * aplicables).
	 * 
	 * @param componentesCobroBatch
	 * @throws SimulacionCobroExcepcion
	 * @throws NegocioExcepcion
	 */
	private void validarOperacionConSaldoAPagarSaldoNoInferiorAPorcentajeDeRetencion(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion, NegocioExcepcion {

		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia()) && TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(componentesCobroBatch.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

			ShvCobMedioPagoDocumentoCap documentoCap = null;

			if (componentesCobroBatch.getListaMediosDePago().get(0) instanceof ShvCobMedioPagoCompensacionProveedor) {
				documentoCap = ((ShvCobMedioPagoCompensacionProveedor) componentesCobroBatch.getListaMediosDePago().get(0)).getDocumentoCap();

			} else if (componentesCobroBatch.getListaMediosDePago().get(0) instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
				documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto) componentesCobroBatch.getListaMediosDePago().get(0)).getDocumentoCap();
			}

			if (!Validaciones.isObjectNull(documentoCap)) {

				List<TipoDocumentoCapEnum> listaDocumentoCapSufrenRetenciones = new ArrayList<TipoDocumentoCapEnum>();
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.K2);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KB);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KC);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KD);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KK);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KQ);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KR);
				listaDocumentoCapSufrenRetenciones.add(TipoDocumentoCapEnum.KT);

				List<String> listaDocumentoCapDetalleProcesados = new ArrayList<String>();

				BigDecimal importeDocumentosCapSufrenRetenciones = BigDecimal.ZERO;

				for (ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalle : documentoCap.getDetalle()) {
					if (listaDocumentoCapSufrenRetenciones.contains(documentoCapDetalle.getTipoDocumento())) {

						if (!listaDocumentoCapDetalleProcesados.contains(documentoCapDetalle.getNumeroDocSAP())) {
							importeDocumentosCapSufrenRetenciones = importeDocumentosCapSufrenRetenciones.add(documentoCapDetalle.getImporteTotalPesosEvaluandoCheckSinDiferenciaCambio());
							// if
							// (SiNoEnum.NO.equals((documentoCapDetalle.getCheckSinDiferenciaCambio())))
							// {
							// importeDocumentosCapSufrenRetenciones =
							// importeDocumentosCapSufrenRetenciones.add(documentoCapDetalle.getImporteTotalDocumentoPesosAFechaShiva());
							// } else {
							// importeDocumentosCapSufrenRetenciones =
							// importeDocumentosCapSufrenRetenciones.add(documentoCapDetalle.getImporteTotalDocumentoPesosAFechaEmision());
							// }
							listaDocumentoCapDetalleProcesados.add(documentoCapDetalle.getNumeroDocSAP());
						}
					}
				}

				importeDocumentosCapSufrenRetenciones = importeDocumentosCapSufrenRetenciones.multiply(new BigDecimal(-1));

				int porcentajeRetencionDocumentosCap = parametroServicio.getValorNumerico(Mensajes.COBROS_SIMULACION_VALIDACION_PORCENTAJE_RETENCION_DOCUMENTOS_CAP).intValue();

				BigDecimal importeAValidarDocumentosCapSufrenRetenciones = (importeDocumentosCapSufrenRetenciones.multiply(new BigDecimal(porcentajeRetencionDocumentosCap))).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);

				if (importeAValidarDocumentosCapSufrenRetenciones.compareTo(componentesCobroBatch.getTratamientoDiferencia().getImporte()) > 0) {
					throw new SimulacionCobroExcepcion("", null, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.operacionConSaldoAPagarSaldoNoInferiorAPorcentajeDeRetencion"), new DecimalFormat("#,##0").format(porcentajeRetencionDocumentosCap), Utilidad.formatCurrency(importeAValidarDocumentosCapSufrenRetenciones, 2)));
				}
			}
		}
	}
	private void validarCompensacionesAutomaticas(ComponentesCobroBatch componentesCobroBatch) throws SimulacionCobroExcepcion{
		
		for (ShvCobMedioPago medioPago : componentesCobroBatch.getListaMediosDePago()) {
			if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor ||  medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
				for(ShvCobFactura factura : componentesCobroBatch.getListaFacturas()){
					if(TipoComprobanteEnum.CDD.name().equals(factura.getTipoComprobante().getIdTipoComprobante()) || TipoComprobanteEnum.C_C.name().equals(factura.getTipoComprobante().getIdTipoComprobante())){
						throw new SimulacionCobroExcepcion("",null,Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.medioPagoCompensacionAutomaticaNoAplicaSobreConjuntoDebitoOCuentaCorriente"));
					}
				}
			}
		}
	}
	private void validarDebitoAProximaFacturaOtrosDebitos(ShvCobCobro cobro) throws SimulacionCobroExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {	
			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
				if (transaccion.getFactura() instanceof ShvCobFacturaUsuario && medioPago instanceof ShvCobMedioPagoDebitoProximaFactura) {
					
						throw new SimulacionCobroExcepcion("",null,Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noSePuedeAplicarTratamientoDiferenciaDebitoAProximaFactura"));
	
					}
				}
			}
		}
	
	/**
	 * 
	 * @throws NegocioExcepcion
	 * @throws SimulacionCobroExcepcion
	 */
	private void validarConsistenciaCobroBatch(ShvCobCobro cobro) throws NegocioExcepcion, SimulacionCobroExcepcion {

		// «VAL» Valida la consistencia del medio de pago debito a próxima
		// factura
		validarConsistenciaDebitoAProxima(cobro);

		// «RN» El medio de pago próxima factura debe aplicar sobre un documento
		// que se esté saldando completamente dentro del cobro
		validarMedioPagoProximaFacturaAplicacionSobreDocumentoQueSaldaCompletamente(cobro);

		//
		validarCobroSegundoVencimientoNoAdmiteFechaCobroAnteriorPrimerVencimiento(cobro);

		// «VAL» Validar que si una transaccion tiene como medio de pago uno
		// configurado como que no calcula intereses,
		// la opción de cobro al 2do vencimiento no esté marcada
		validarConflictoMedioPagoSinCalculoInteresesVsCobroSegundoVencimiento(cobro);

		// «VAL» Validar que si existe una diferencia a ser gestionada como un
		// tratamiento de diferencia,
		// la diferencia solo puede ser en la moneda de la operación
		validarConsistenciaTratamientoDiferenciaNoAplicaEnDiferenciasDondeDifiereMonedaOperacion(cobro);

		// «RN» Para un cobro en pesos, un medio de pago en dolares no puede
		// aplicar sobre un debito en dolares, dentro de la misma transaccion
		validarCobroPesosMedioPagoDolaresNoPuedeAplicarSobreDebitoDolares(cobro);

		// «RN» Para un cobro en pesos, un medio de pago desistimiento o plande
		// pago no puede aplicar sobre un debito en dolares, dentro de la misma
		// transaccion
		validarCobroPesosMedioPagoDesistimientoOPlanPagoNoPuedeAplicarSobreDebitoDolares(cobro);

		// «RN» Para una factura Mic que no admite pagos parciales, la misma no
		// puede quedar asociado a un medio de pago Saldo a Cobrar.
		// La factura Mic no admite pagos parciales cuando
		// - se quiere cobrar al segundo vencimiento
		// - se quiere destransferir terceros
		// - la factura posee conceptor de terceros parcialmente transferidos
		//
		// En tal caso se informa al usuario:
		// "No se puede aplicar saldo a cobrar a una factura con conceptos de 3eros"
		// o
		// "No se puede aplicar saldo a cobrar a una factura con cobro al 2do vto"
		// segun corresponda
		//
		validarSaldoACobrarNoApliqueSobreFacturaMicQueNoAdmitePagosParciales(cobro);		
		
		// A los Otros Débitos no se le puede aplicar el tratamiento de la
		// diferencia débito a próxima factura
		validarDebitoAProximaFacturaOtrosDebitos(cobro);

	}

	/**
	 * «RN» El medio de pago próxima factura debe aplicar sobre un documento que
	 * se esté saldando completamente dentro del cobro
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarMedioPagoProximaFacturaAplicacionSobreDocumentoQueSaldaCompletamente(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic || medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

					if (TipoPagoEnum.PAGO_PARCIAL.equals(transaccion.getFactura().getTipoPago())) {

						StringBuffer mensajeError = new StringBuffer();

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
								mensajeError.append("<br>");
							}
						}

						mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
						mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.medioPagoProximaFacturaNoAplicaSobrePagoParcialCancelatorio"));

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
						} else {
							transaccion.setMensajeEstado(mensajeError.toString());
						}

						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarCobroSegundoVencimientoNoAdmiteFechaCobroAnteriorPrimerVencimiento(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
			if (!transaccion.getShvCobFactura().isEmpty() && transaccion.getFactura() instanceof ShvCobFacturaMic) {
				ShvCobFacturaMic factura = (ShvCobFacturaMic) transaccion.getFactura();

				if (SiNoEnum.SI.equals(factura.getCobrarSegundoVencimiento())) {

					if (factura.getFechaValor().compareTo(factura.getFechaVencimiento()) <= 0) {

						StringBuffer mensajeError = new StringBuffer("");

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
								mensajeError.append("<br>");
							}
						}

						mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
						mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobro2doVencimientoNoAdmiteFechaAnterior1erVencimiento"));

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
						} else {
							transaccion.setMensajeEstado(mensajeError.toString());
						}

						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					}
				}
			}
		}
	}

	/**
	 * «VAL» Validar que si una transaccion tiene como medio de pago uno
	 * configurado como que no calcula intereses, la opción de cobro al 2do
	 * vencimiento no esté marcada
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarConflictoMedioPagoSinCalculoInteresesVsCobroSegundoVencimiento(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
			if (!transaccion.getShvCobFactura().isEmpty() && transaccion.getFactura() instanceof ShvCobFacturaMic) {
				ShvCobFacturaMic factura = (ShvCobFacturaMic) transaccion.getFactura();

				if (SiNoEnum.SI.equals(factura.getCobrarSegundoVencimiento())) {

					for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
						if (SiNoEnum.NO.equals(medioPago.getTipoMedioPago().getGeneraIntereses())) {

							StringBuffer mensajeError = new StringBuffer("");

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
									mensajeError.append("<br>");
								}
							}

							mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
							mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
							mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.transaccionConflictoMedioPagoSinCalculoInteresesVsCobroSegundoVencimiento"));

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
							} else {
								transaccion.setMensajeEstado(mensajeError.toString());
							}

							transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						}
					}
				}
			}
		}
	}

	/**
	 * «VAL» Validar que si existe una diferencia a ser gestionada como un
	 * tratamiento de diferencia, la diferencia solo puede ser en moneda la
	 * moneda de la operación
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarConsistenciaTratamientoDiferenciaNoAplicaEnDiferenciasDondeDifiereMonedaOperacion(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {

				for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso || medioPago instanceof ShvCobMedioPagoCTA) {

						if (!cobro.getMonedaOperacion().equals(medioPago.getMoneda())) {

							StringBuffer mensajeError = new StringBuffer();

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
									mensajeError.append("<br>");
								}
							}

							mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
							mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
							mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.tratamientoDiferenciaNoAplicaEnDiferenciasDondeDifiereMonedaOperacion"));

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
							} else {
								transaccion.setMensajeEstado(mensajeError.toString());
							}

							transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarConsistenciaDebitoAProxima(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic || medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

					try {

						// «VAL» Validar que si existe una diferencia a ser
						// gestionada como un debito a
						// próxima factura, la diferencia solo puede ser en la
						// moneda de la operación
						validarConsistenciaDebitoAProximaFacturaNoAplicaEnDocumentosDondeDifiereMonedaOperacion(transaccion);

						// «RN» El monto aplicado del medio de pago próxima
						// factura no puede superar un
						// importe máximo configurado en el sistema
						validarMontoProximaFacturaContraImporteMaximo(transaccion);

						// «RN» El monto aplicado del medio de pago próxima
						// factura no puede superar un
						// porcentaje configurado en el sistema del importe
						// original del documento
						validarMontoProximaFacturaContraPorcentajeMaximo(transaccion);

					} catch (ValidacionMonedaDebitoProximaFacturaSimulacionCobroExcepcion ex) {
						// No hacemos nada, ya que solo usamos la excepción para
						// cortar el flujo de avance

					} catch (ValidacionImporteMaximoDebitoProximaFacturaSimulacionCobroExcepcion ex) {
						// No hacemos nada, ya que solo usamos la excepción para
						// cortar el flujo de avance

					} catch (ValidacionPorcentajeMaximoDebitoProximaFacturaSimulacionCobroExcepcion ex) {
						// No hacemos nada, ya que solo usamos la excepción para
						// cortar el flujo de avance

					}
				}
			}
		}
	}

	/**
	 * «VAL» Validar que si existe una diferencia a ser gestionada como un
	 * debito a próxima factura, la diferencia solo puede ser en moneda la
	 * moneda de la operación
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private void validarConsistenciaDebitoAProximaFacturaNoAplicaEnDocumentosDondeDifiereMonedaOperacion(ShvCobTransaccion transaccion) throws NegocioExcepcion {

		for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

			if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso || medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {

				if (transaccion.getFactura() instanceof ShvCobFacturaCalipso) {

					ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();

					if (!facturaCalipso.getTransaccion().getOperacion().getMonedaOperacion().equals(facturaCalipso.getMoneda())) {

						StringBuffer mensajeError = new StringBuffer();

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
								mensajeError.append("<br>");
							}
						}

						mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
						mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.debitoAProximaFacturaNoAplicaEnDocumentosDondeDifiereMonedaOperacion"));

						if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
							transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
						} else {
							transaccion.setMensajeEstado(mensajeError.toString());
						}

						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);

						throw new ValidacionMonedaDebitoProximaFacturaSimulacionCobroExcepcion();
					}
				}
			}
		}
	}

	/**
	 * «RN» El monto aplicado del medio de pago próxima factura no puede superar
	 * un importe máximo configurado en el sistema
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarMontoProximaFacturaContraImporteMaximo(ShvCobTransaccion transaccion) throws NegocioExcepcion, ValidacionImporteMaximoDebitoProximaFacturaSimulacionCobroExcepcion {

		for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

			if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso || medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {

				BigDecimal importeProximaFacturaTotal = medioPago.getImporte();

				ReglaCobroFiltro reglaCobroFiltro = new ReglaCobroFiltro();
				List<ShvParamReglaCobro> reglaCobro = new ArrayList<ShvParamReglaCobro>();
				ShvCobEdCobroSimplificado cobroSimplificado = new ShvCobEdCobroSimplificado();

				// Busco el cobro en la base para tomar los datos para el filtro

				try {
					cobroSimplificado = cobroOnlineDao.buscarCobroSimplificado(transaccion.getOperacion().getIdOperacion());
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e);
				}

				// Ahora busco en la base de datos el importe máximo permitido
				// que puedo utilizar para este cobro

				reglaCobroFiltro.setTipoRegla(TipoReglaCobroEnum.CARGO.name());
				reglaCobroFiltro.setEmpresa(cobroSimplificado.getIdEmpresa());
				reglaCobroFiltro.setSegmento(cobroSimplificado.getIdSegmento());
				reglaCobroFiltro.setMoneda(transaccion.getOperacion().getMonedaOperacion().name());
				reglaCobroFiltro.setMonto(importeProximaFacturaTotal);
				reglaCobroFiltro.setPorcentaje(BigDecimal.ZERO);

				try {
					reglaCobro = reglaCobroServicio.buscar(reglaCobroFiltro);
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e);
				}

				if (reglaCobro.isEmpty()) {
					String mensajeErrorTraza = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noExisteReglaParametrica");
					Traza.error(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), "'validarMontoProximaFacturaContraImporteMaximo'", reglaCobroFiltro.toString(), mensajeErrorTraza));
					throw new SimulacionCobroExcepcion("", null, mensajeErrorTraza);

				} else if (reglaCobro.size() > 1) {
					String mensajeErrorTraza = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.masDeUnaReglaParametrica");
					Traza.error(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), "'validarMontoProximaFacturaContraImporteMaximo'", reglaCobroFiltro.toString(), mensajeErrorTraza));
					throw new SimulacionCobroExcepcion("", null, mensajeErrorTraza);

				} else if (AccionesReglaCobroEnum.NO_PERMITIR.equals(reglaCobro.get(0).getAccion())) {

					// Armo el mensaje de error

					StringBuffer mensajeError = new StringBuffer();
					String importeMaxSuperado = new DecimalFormat("#,##0").format(reglaCobro.get(0).getMontoMinimo());

					mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
					mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
					mensajeError.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.montoProximaFacturaSuperaImporteMaximo"), importeMaxSuperado));

					if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
						if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
							mensajeError.append("<br>");
						}
					}

					if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
						transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
					} else {
						transaccion.setMensajeEstado(mensajeError.toString());
					}

					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);

					throw new ValidacionImporteMaximoDebitoProximaFacturaSimulacionCobroExcepcion();
				}
			}
		}
	}

	/**
	 * «RN» El monto aplicado del medio de pago próxima factura no puede superar
	 * un porcentaje configurado en el sistema del importe original del
	 * documento
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarMontoProximaFacturaContraPorcentajeMaximo(ShvCobTransaccion transaccion) throws NegocioExcepcion {

		for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

			if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic || medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

				BigDecimal importeProximaFactura = BigDecimal.ZERO;
				BigDecimal importeOriginalFactura = BigDecimal.ZERO;
				BigDecimal porcentajeImpProx;

				ReglaCobroFiltro reglaCobroFiltro = new ReglaCobroFiltro();
				List<ShvParamReglaCobro> reglaCobro = new ArrayList<ShvParamReglaCobro>();
				ShvCobEdCobroSimplificado cobroSimplificado = new ShvCobEdCobroSimplificado();

				importeProximaFactura = medioPago.getImporte();
				importeOriginalFactura = transaccion.getFactura().getImporteOriginal();

				// Calcula el porcentaje que representa el importe a porxima,
				// sobre el total de la factura.
				porcentajeImpProx = (importeProximaFactura.multiply(new BigDecimal(100))).divide(importeOriginalFactura, 2);

				try {
					cobroSimplificado = cobroOnlineDao.buscarCobroSimplificado(medioPago.getTransaccion().getOperacion().getIdOperacion());
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e);
				}

				reglaCobroFiltro.setTipoRegla(TipoReglaCobroEnum.CARGO.name());
				reglaCobroFiltro.setEmpresa(cobroSimplificado.getIdEmpresa());
				reglaCobroFiltro.setSegmento(cobroSimplificado.getIdSegmento());
				reglaCobroFiltro.setMoneda(medioPago.getTransaccion().getOperacion().getMonedaOperacion().name());
				reglaCobroFiltro.setMonto(medioPago.getImporte());
				reglaCobroFiltro.setPorcentaje(porcentajeImpProx);

				try {
					reglaCobro = reglaCobroServicio.buscar(reglaCobroFiltro);
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e);
				}

				if (reglaCobro.isEmpty()) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.noExisteReglaParametrica");
					Traza.error(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), "'validarMontoProximaFacturaContraPorcentajeMaximo'", reglaCobroFiltro.toString(), mensajeError));
					throw new SimulacionCobroExcepcion("", null, mensajeError);

				} else if (reglaCobro.size() > 1) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.masDeUnaReglaParametrica");
					Traza.error(getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), "'validarMontoProximaFacturaContraPorcentajeMaximo'", reglaCobroFiltro.toString(), mensajeError));
					throw new SimulacionCobroExcepcion("", null, mensajeError);

				} else if (AccionesReglaCobroEnum.NO_PERMITIR.equals(reglaCobro.get(0).getAccion())) {

					StringBuffer mensajeError = new StringBuffer();
					// chequear decimales
					String porcMaxSuperado = new DecimalFormat("#,##0").format(reglaCobro.get(0).getPorcentajeMinimo());

					if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
						if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
							mensajeError.append("<br>");
						}
					}

					mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
					mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
					mensajeError.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.montoProximaFacturaSuperaPorcentajeMaximo"), porcMaxSuperado));

					if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
						transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
					} else {
						transaccion.setMensajeEstado(mensajeError.toString());
					}

					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				}
			}
		}
	}

	/**
	 * «RN» Para un cobro en pesos, un medio de pago en dolares no puede aplicar
	 * sobre un debito en dolares, dentro de la misma transaccion
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarCobroPesosMedioPagoDolaresNoPuedeAplicarSobreDebitoDolares(ShvCobCobro cobro) throws NegocioExcepcion {

		if (MonedaEnum.PES.equals(cobro.getMonedaOperacion())) {
			for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

				if (!Validaciones.isObjectNull(transaccion.getFactura())) {

					if (transaccion.getFactura() instanceof ShvCobFacturaCalipso) {

						ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();

						if (!MonedaEnum.PES.equals(facturaCalipso.getMoneda())) {

							for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {

								// En primera instancia la moneda de la factura
								// no es PESOS
								// En segunda instancia, la moneda del medio de
								// pago no puede ser igual a la moneda de la
								// factura,
								// si asi fuera, quiere decir que estoy ante un
								// caso dolar-dolar, y debo generar error!
								if (medioPago.getMoneda().equals(facturaCalipso.getMoneda())) {

									StringBuffer mensajeError = new StringBuffer();

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
											mensajeError.append("<br>");
										}
									}

									mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
									mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
									mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobroPesosMedioPagoDolaresNoPuedeAplicarSobreDebitoDolares"));

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
									} else {
										transaccion.setMensajeEstado(mensajeError.toString());
									}

									transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
								}
							}
						}
					}else if (transaccion.getFactura() instanceof ShvCobFacturaUsuario) {

						ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();

						if (!MonedaEnum.PES.equals(facturaUsuario.getMoneda())) {

							for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {

								// En primera instancia la moneda de la factura
								// no es PESOS
								// En segunda instancia, la moneda del medio de
								// pago no puede ser igual a la moneda de la
								// factura,
								// si asi fuera, quiere decir que estoy ante un
								// caso dolar-dolar, y debo generar error!
								if (medioPago.getMoneda().equals(facturaUsuario.getMoneda())) {

									StringBuffer mensajeError = new StringBuffer();

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
											mensajeError.append("<br>");
										}
									}

									mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
									mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
									mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobroPesosMedioPagoDolaresNoPuedeAplicarSobreDebitoDolares"));

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
									} else {
										transaccion.setMensajeEstado(mensajeError.toString());
									}

									transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * «RN» Para un cobro en pesos, un medio de pago desistimiento o plande pago
	 * no puede aplicar sobre un debito en dolares, dentro de la misma
	 * transaccion
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarCobroPesosMedioPagoDesistimientoOPlanPagoNoPuedeAplicarSobreDebitoDolares(ShvCobCobro cobro) throws NegocioExcepcion {

		if (MonedaEnum.PES.equals(cobro.getMonedaOperacion())) {

			for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

				if (!Validaciones.isObjectNull(transaccion.getFactura())) {

					if (transaccion.getFactura() instanceof ShvCobFacturaCalipso) {

						ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();

						if (!MonedaEnum.PES.equals(facturaCalipso.getMoneda())) {

							for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

								if (medioPago instanceof ShvCobMedioPagoDesistimiento || medioPago instanceof ShvCobMedioPagoPlanDePago) {

									StringBuffer mensajeError = new StringBuffer();

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
											mensajeError.append("<br>");
										}
									}

									mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
									mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
									mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobroPesosMedioPagoDesistimientoOPlanPagoNoPuedeAplicarSobreDebitoDolares"));

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
									} else {
										transaccion.setMensajeEstado(mensajeError.toString());
									}

									transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
								}
							}
						}
					}else if (transaccion.getFactura() instanceof ShvCobFacturaUsuario) {

						ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();

						if (!MonedaEnum.PES.equals(facturaUsuario.getMoneda())) {

							for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

								if (medioPago instanceof ShvCobMedioPagoDesistimiento || medioPago instanceof ShvCobMedioPagoPlanDePago) {

									StringBuffer mensajeError = new StringBuffer();

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
											mensajeError.append("<br>");
										}
									}

									mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
									mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
									mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.validacion.cobroPesosMedioPagoDesistimientoOPlanPagoNoPuedeAplicarSobreDebitoDolares"));

									if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
										transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
									} else {
										transaccion.setMensajeEstado(mensajeError.toString());
									}

									transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * «RN» Para una factura Mic que no admite pagos parciales, la misma no
	 * puede quedar asociado a un medio de pago Saldo a Cobrar. La factura Mic
	 * no admite pagos parciales cuando - se quiere cobrar al segundo
	 * vencimiento - se quiere destransferir terceros - la factura posee
	 * conceptor de terceros parcialmente transferidos
	 *
	 * En tal caso se informa al usuario:
	 * "No se puede aplicar saldo a cobrar a una factura con conceptos de 3eros"
	 * o "No se puede aplicar saldo a cobrar a una factura con cobro al 2do vto"
	 * segun corresponda
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void validarSaldoACobrarNoApliqueSobreFacturaMicQueNoAdmitePagosParciales(ShvCobCobro cobro) throws NegocioExcepcion {

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
				if (medioPago instanceof ShvCobMedioPagoSaldoACobrar) {

					if (transaccion.getFactura() instanceof ShvCobFacturaMic) {

						ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) transaccion.getFactura();

						boolean facturaNoAdmiteCobrosParcialesPorDestransferenciaTerceros = false;
						boolean facturaNoAdmiteCobrosParcialesPorConceptosDeTerceros = false;
						boolean facturaNoAdmiteCobrosParcialesPorCobroSegundoVencimiento = false;

						String keyMensajeError = "";

						if (SiNoEnum.SI.equals(facturaMic.getDestransferirTerceros())) {
							facturaNoAdmiteCobrosParcialesPorDestransferenciaTerceros = true;
							keyMensajeError = "cobros.error.simulacion.validacion.saldoACobrarNoApliqueSobreFacturaMicQueNoAdmitePagosParcialesConceptosTerceros";
						}

						if (SiNoEnum.SI.equals(facturaMic.getCobrarSegundoVencimiento())) {
							facturaNoAdmiteCobrosParcialesPorCobroSegundoVencimiento = true;
							keyMensajeError = "cobros.error.simulacion.validacion.saldoACobrarNoApliqueSobreFacturaMicQueNoAdmitePagosParcialesCobro2doVto";
						}

						if (!Validaciones.isObjectNull(facturaMic.getEstadoConceptoTerceros()) && EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.equals(facturaMic.getEstadoConceptoTerceros())) {
							facturaNoAdmiteCobrosParcialesPorConceptosDeTerceros = true;
							keyMensajeError = "cobros.error.simulacion.validacion.saldoACobrarNoApliqueSobreFacturaMicQueNoAdmitePagosParcialesConceptosTerceros";
						}

						if (facturaNoAdmiteCobrosParcialesPorDestransferenciaTerceros || facturaNoAdmiteCobrosParcialesPorConceptosDeTerceros || facturaNoAdmiteCobrosParcialesPorCobroSegundoVencimiento) {

							StringBuffer mensajeError = new StringBuffer();

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
									mensajeError.append("<br>");
								}
							}

							mensajeError.append(TipoResultadoSimulacionEnum.ERROR.descripcion());
							mensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
							mensajeError.append(Propiedades.MENSAJES_PROPIEDADES.getString(keyMensajeError));

							if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
								transaccion.setMensajeEstado(transaccion.getMensajeEstado() + mensajeError.toString());
							} else {
								transaccion.setMensajeEstado(mensajeError.toString());
							}

							transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						}

					}
				}
			}
		}
	}

	/**
	 * El cálculo se debe hacer de la siguiente manera:
	 *
	 * Si el documento a cobrar es una factura o nota de débito de MIC Se
	 * compara la fecha de puesta al cobro del documento MIC vs. la fecha de
	 * último pago parcial realizado sobre el documento y se queda con la más
	 * nueva. Se compara la fecha del punto anterior vs. la fecha del medio de
	 * pago a utilizar. Si la fecha del medio de pago es anterior se debe
	 * aplicar el cobro a la fecha del documento a cobrar (fecha obtenida en el
	 * punto 1). Si la fecha del medio de pago es posterior se debe aplicar el
	 * cobro a la fecha del medio de pago. En caso que esto suceda la nueva
	 * fecha asociada a la factura será ésta (esto es para comparar fechas
	 * correctas si hay pagos parciales posteriores).
	 *
	 * Si el documento a cobrar es un DUC de MIC La fecha valor de la
	 * transacción es la fecha del día.
	 *
	 * Si el documento a cobrar es de Calipso
	 *
	 * Si la operación es sin diferencia de cambio La fecha de cobro es la fecha
	 * de emisión de documento a cobrar
	 *
	 * Si la operación es con diferencia de cambio Se compara la fecha de
	 * emisión del documento Calipso vs. la fecha del medio de pago a utilizar.
	 * Si la fecha del medio de pago es anterior se debe aplicar el cobro a la
	 * fecha de emisión del documento a cobrar. Si la fecha del medio de pago es
	 * posterior se debe aplicar el cobro a la fecha del medio de pago.
	 * 
	 * Si el documento a cobrar es una factura o nota de débito de Negocio.Net o Huawei:
	 * Se compara la fecha de vencimiento del documento Negocio.Net/Huawei vs. la fecha
	 * del medio de pago a utilizar. Si la fecha del medio de pago es anterior
	 * se debe aplicar el cobro a la fecha de vencimiento del documento a
	 * cobrar. Si la fecha del medio de pago es posterior se debe aplicar el
	 * cobro a la fecha del medio de pago. Si el documento a cobrar es una
	 * cuenta corriente o conjunto de débitos Negocio.Net o Huawei, Otros Débitos de SAP, Otros Débitos de
	 * Fibercorp u Otros Débitos de Nextel La fecha valor de la transacción es
	 * la fecha del día.
	 * 
	 * 
	 * @param factura
	 * @param medioPago
	 * @return
	 */
	private static Date calcularFechaValorTransaccion(ShvCobFactura factura, ShvCobMedioPago medioPago) {

		Date fechaValor = null;

		if (factura instanceof ShvCobFacturaMic) {

			ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;

			if (TipoComprobanteEnum.FAC.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante()) || TipoComprobanteEnum.DEB.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante())) {

				if (Validaciones.isObjectNull(facturaMic.getFechaUltimoPagoParcial()) || (!Validaciones.isObjectNull(facturaMic.getFechaUltimoPagoParcial()) && facturaMic.getFechaPuestaCobro().compareTo(facturaMic.getFechaUltimoPagoParcial()) > 0)) {
					fechaValor = facturaMic.getFechaPuestaCobro();
				} else {
					fechaValor = facturaMic.getFechaUltimoPagoParcial();
				}

				if (Validaciones.isObjectNull(fechaValor) || (!Validaciones.isObjectNull(fechaValor) && medioPago.getFechaValor().compareTo(fechaValor) > 0)) {
					fechaValor = medioPago.getFechaValor();
				}

			} else if (TipoComprobanteEnum.DUC.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante())) {
				fechaValor = Utilidad.obtenerFechaActual();
			}

		} else if (factura instanceof ShvCobFacturaCalipso) {

			ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;

			if (SiNoEnum.SI.equals(facturaCalipso.getSinDiferenciaCambio())) {
				fechaValor = facturaCalipso.getFechaEmision();
			} else {
				if (medioPago.getFechaValor().compareTo(facturaCalipso.getFechaEmision()) > 0) {
					fechaValor = medioPago.getFechaValor();
				} else {
					fechaValor = facturaCalipso.getFechaEmision();
				}
			}
		} else if (factura instanceof ShvCobFacturaUsuario) {

			ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) factura;

			// Se simplifica el calculo de la fecha valor para las facturas de usuario.
			// Basicamente si tiene fecha de vencimiento entonces comparamos contra la fecha del medio de pago, como para 
			// unificar criterios con MIC y Calipso, y en caso de no tenerla (como en la cuenta corriente 
			// y en el conjunto de débitos) se envía la del día.  
			
			if (!Validaciones.isObjectNull(facturaUsuario.getFechaVencimiento())) {
				if (medioPago.getFechaValor().compareTo(facturaUsuario.getFechaVencimiento()) > 0) {
					fechaValor = medioPago.getFechaValor();
				} else {
					fechaValor = facturaUsuario.getFechaVencimiento();
				}
			} else {
				fechaValor = Utilidad.obtenerFechaActual();
			}
		}
		// Si la factura ya posee una fecha valor, y la misma es mayor que la
		// nueva fecha calculada,
		// prevalecerá como fecha valor aquella existente en la factura.
		if (!Validaciones.isObjectNull(factura.getFechaValor())) {
			if (factura.getFechaValor().compareTo(fechaValor) > 0) {
				fechaValor = factura.getFechaValor();
			}
		}

		fechaValor = DateUtils.truncate(fechaValor, Calendar.DATE);

		return fechaValor;
	}

	/**
	 * Calculo del tipo de pago
	 * 
	 * Si el documento a cobrar es de MIC: Si no tiene fecha de pago parcial
	 * informado y el importe a cobrar es igual al saldo entonces es Pago Total.
	 * Si no tiene fecha de pago parcial informado y el importe a cobrar es
	 * menor al saldo entonces es Pago Parcial. Si tiene fecha de pago parcial
	 * anterior informado y el importe a cobrar es igual al saldo entonces es
	 * Pago Parcial Cancelatorio. Si tiene fecha de pago parcial anterior
	 * informado y el importe a cobrar es menor al saldo entonces es Pago
	 * Parcial.
	 *
	 * Si el documento a cobrar es de Calipso, con moneda de origen "Pesos" Si
	 * el saldo es igual al importe original de la factura y el importe a cobrar
	 * es igual al saldo entonces es Pago Total. Si el saldo es igual al importe
	 * original de la factura y el importe a cobrar es menor al saldo entonces
	 * es Pago Parcial. Si el saldo es distinto al importe original de la
	 * factura y el importe a cobrar es igual al saldo entonces es Pago Parcial
	 * Cancelatorio. Si el saldo es distinto al importe original de la factura y
	 * el importe a cobrar es menor al saldo entonces es Pago Parcial.
	 * 
	 * Si el documento a cobrar es de Calipso, con moneda de origen "Dólares" Si
	 * el saldo actual moneda origen es igual al importe original moneda origen
	 * de la factura y el importe aplicado en moneda origen (rta del servicio)
	 * es igual al saldo entonces es Pago Total. Si el saldo actual moneda
	 * origen es igual al importe original moneda origen de la factura y el
	 * importe aplicado en moneda origen (rta del servicio) es menor al saldo
	 * entonces es Pago Parcial. Si el saldo actual moneda origen es distinto al
	 * importe original moneda origen de la factura y el importe aplicado en
	 * moneda origen (rta del servicio) es igual al saldo entonces es Pago
	 * Parcial Cancelatorio. Si el saldo actual moneda origen es distinto al
	 * importe original moneda origen de la factura y el importe aplicado en
	 * moneda origen (rta del servicio) es menor al saldo entonces es Pago
	 * Parcial.
	 * 
	 * @param factura
	 * @return
	 */
	private static TipoPagoEnum calcularTipoPagoTransaccion(ShvCobFactura factura) {

		TipoPagoEnum tipoPago = null;

		if (factura instanceof ShvCobFacturaMic) {

			ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;

			if (SiNoEnum.SI.equals(facturaMic.getDestransferirTerceros()) || SiNoEnum.SI.equals(facturaMic.getCobrarSegundoVencimiento())) {
				if (Validaciones.isObjectNull(facturaMic.getFechaUltimoPagoParcial()) && Validaciones.isObjectNull(facturaMic.getFechaAcumuladoSimulacion())) {
					tipoPago = TipoPagoEnum.PAGO_TOTAL;
				} else {
					tipoPago = TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO;
				}
			} else {
				if (Validaciones.isObjectNull(facturaMic.getFechaUltimoPagoParcial()) && Validaciones.isObjectNull(facturaMic.getFechaAcumuladoSimulacion())) {
					if (facturaMic.getSaldoActual().compareTo(facturaMic.getImporteCobrar()) == 0) {
						tipoPago = TipoPagoEnum.PAGO_TOTAL;
					} else {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL;
					}
				} else {
					if (facturaMic.getSaldoActual().compareTo(facturaMic.getImporteCobrar()) == 0) {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO;
					} else {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL;
					}
				}
			}

		} else if (factura instanceof ShvCobFacturaCalipso) {

			ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;

			if (facturaCalipso.getMoneda().equals(facturaCalipso.getMonedaImporteCobrar())) {

				if (facturaCalipso.getSaldoActual().compareTo(facturaCalipso.getImporteOriginal()) == 0) {
					if (facturaCalipso.getImporteCobrar().compareTo(facturaCalipso.getSaldoActual()) == 0) {
						tipoPago = TipoPagoEnum.PAGO_TOTAL;
					} else {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL;
					}
				} else {
					if (facturaCalipso.getImporteCobrar().compareTo(facturaCalipso.getSaldoActual()) == 0) {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO;
					} else {
						tipoPago = TipoPagoEnum.PAGO_PARCIAL;
					}
				}

			} else {

				if (!Validaciones.isObjectNull(facturaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen())) {

					if (facturaCalipso.getSaldoActualMonedaOrigen().compareTo(facturaCalipso.getImporteOriginalMonedaOrigen()) == 0) {
						if (facturaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen().compareTo(facturaCalipso.getSaldoActualMonedaOrigen()) == 0) {
							tipoPago = TipoPagoEnum.PAGO_TOTAL;
						} else {
							tipoPago = TipoPagoEnum.PAGO_PARCIAL;
						}
					} else {
						if (facturaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen().compareTo(facturaCalipso.getSaldoActualMonedaOrigen()) == 0) {
							tipoPago = TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO;
						} else {
							tipoPago = TipoPagoEnum.PAGO_PARCIAL;
						}
					}
				}
			}
		} else if (factura instanceof ShvCobFacturaUsuario) {
			tipoPago = TipoPagoEnum.NO_APLICA;
		}

		return tipoPago;
	}

	/**
	 * Verifica si para una factura corresponde o no permitir que la misma sea
	 * cobrada con pagos parciales. Diferentes medios de pago con diferentes
	 * fechas pueden generar pagos parciales, pero si la factura fuera de MIC y
	 * la misma se cobrara a 2do vencimiento o se pida destransferir terceros,
	 * entonces el pago debe ser unico.
	 * En cambio si la factura es un conjunto de débitos, siempre será parcial
	 * ya que se espera una transaccion por medio de pago.
	 * 
	 * @param transaccion
	 * @param factura
	 * @param medioPago
	 * @return
	 */
	private static boolean facturaAdmiteCobrosParciales(ShvCobTransaccion transaccion, ShvCobFactura factura, ShvCobMedioPago medioPago) {

		boolean facturaAdmiteCobrosParcialesPorDestransferenciaTerceros = false;
		boolean facturaAdmiteCobrosParcialesPorConceptosDeTerceros = false;
		boolean facturaAdmiteCobrosParcialesPorCobroSegundoVencimiento = false;
		boolean facturaAdmiteCobrosParcialesPorCambioFechaValor = false;
		boolean facturaAdmiteCobrosParcialesPorCambioMedioPagoCalculaIntereses = false;
		boolean facturaObligaCobrosParcialesPorMedioPagoSaldoACobrar = false;
		boolean facturaObligaCobrosParcialesPorConjuntoDeDebitos = false;

		if (factura instanceof ShvCobFacturaMic) {
			ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;

			if (SiNoEnum.SI.equals(facturaMic.getDestransferirTerceros())) {
				facturaAdmiteCobrosParcialesPorDestransferenciaTerceros = true;
			}

			if (SiNoEnum.SI.equals(facturaMic.getCobrarSegundoVencimiento())) {
				facturaAdmiteCobrosParcialesPorCobroSegundoVencimiento = true;
			}

			if (!Validaciones.isObjectNull(facturaMic.getEstadoConceptoTerceros()) && EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.equals(facturaMic.getEstadoConceptoTerceros())) {
				facturaAdmiteCobrosParcialesPorConceptosDeTerceros = true;
			}
		}

		// Calculo la fecha valor
		Date fechaValorActual = calcularFechaValorTransaccion(factura, medioPago);

		if (transaccion.getFactura().getFechaValor().compareTo(fechaValorActual) < 0) {
			facturaAdmiteCobrosParcialesPorCambioFechaValor = true;
		}

		ShvCobMedioPago medioDePagoEnTransaccion = transaccion.getShvCobMedioPago().iterator().next();

		// Verifico si el medio de pago que estoy ingresando tiene un
		// tratamiento para el calculo de intereses diferente
		// a los existentes en la transaccion actual. Lo comparo contra el
		// primero, debiera ser suficiente
		facturaAdmiteCobrosParcialesPorCambioMedioPagoCalculaIntereses = !(medioPago.getTipoMedioPago().getGeneraIntereses().equals(medioDePagoEnTransaccion.getTipoMedioPago().getGeneraIntereses()));

		// Verifico que el medio de pago "Saldo a Cobrar" no se mezcle con otros
		// medios de pago
		facturaObligaCobrosParcialesPorMedioPagoSaldoACobrar = ((medioPago instanceof ShvCobMedioPagoSaldoACobrar) && !(medioDePagoEnTransaccion instanceof ShvCobMedioPagoSaldoACobrar));
		
		// Verifico si la factura es un "Conjunto de Débitos", si es así obligo a generar cobros parciales,
		// debiera ser uno por cada medio de pago
		facturaObligaCobrosParcialesPorConjuntoDeDebitos = TipoComprobanteEnum.CDD.name().equals(factura.getTipoComprobante().getIdTipoComprobante());		

		boolean retorno = (facturaAdmiteCobrosParcialesPorCambioFechaValor 
							|| facturaAdmiteCobrosParcialesPorCambioMedioPagoCalculaIntereses 
							|| facturaObligaCobrosParcialesPorMedioPagoSaldoACobrar
							|| facturaObligaCobrosParcialesPorConjuntoDeDebitos) 
						&& (!facturaAdmiteCobrosParcialesPorDestransferenciaTerceros 
								&& !facturaAdmiteCobrosParcialesPorConceptosDeTerceros 
								&& !facturaAdmiteCobrosParcialesPorCobroSegundoVencimiento);

		return retorno;
	}

	/**
	 * Este método es el encargado del armado de las transacciones de un cobro,
	 * aplicando las reglas de imputacion definidas
	 * 
	 * @param edCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Set<ShvCobTransaccion> armarTransacciones(ComponentesCobroBatch componentes) throws Exception {

		Set<ShvCobTransaccion> listaTransacciones = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenSimulacionShvCobTransaccion());

		int numeradorTransaccion = 0;
		int grupo = 0;
		int grupoUsuario = 1;

		//
		// Transacciones de Reintegros/Envios a Ganancias, si aplican
		//
		if (!Validaciones.isObjectNull(componentes.getTratamientoDiferencia())) {

			//
			// Ordeno los medios de pago de acuerdo a las reglas de imputacion,
			// para aplicar sobre tratamiento de diferencia
			// (reintegros / envío a ganancia)
			//
			Collections.sort(componentes.getListaMediosDePago(), new ComparatorOrdenSimulacionReintegroShvCobMedioPago());

			ShvCobTransaccion transaccionActual = null;
			ShvCobTratamientoDiferencia tratamientoDiferencia = null;

			BigDecimal saldoActualTratamientoDiferencia = componentes.getTratamientoDiferencia().getImporte();

			ShvCobMedioPago medioPagoActual = null;
			BigDecimal saldoActualMedioPago = BigDecimal.ZERO;

			while (saldoActualTratamientoDiferencia.compareTo(BigDecimal.ZERO) != 0) {

				// Cada vez que pido un medio de pago, genero una nueva
				// transaccion, con su correspondiente
				// tratamiento de diferencia 

				if (saldoActualMedioPago.compareTo(BigDecimal.ZERO) == 0) {

					if (!(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()))) {

						medioPagoActual = componentes.obtenerSiguienteMedioPagoParaReintegro();

					} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(componentes.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

						medioPagoActual = componentes.obtenerSiguienteMedioPagoParaAplicacionManual();

					}

					saldoActualMedioPago = medioPagoActual.getImporte();

					transaccionActual = new ShvCobTransaccion();
					transaccionActual.setOperacion(componentes.getOperacion());
					transaccionActual.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
					transaccionActual.setNumeroTransaccion(new Integer(++numeradorTransaccion));
					transaccionActual.setGrupo(grupo);
					transaccionActual.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
					transaccionActual.setSistema(medioPagoActual.getSistemaOrigen());
					// Este apartado trata sobre los reintegros/envios a ganancia. Estos actualmente aplican sobre créditos de Telecom (Calipso, MIC)
					// por lo tanto por defecto la sociedad queda como TELECOM. Si en algun momento cambia, se deberá tomar la sociedad del medio de pago
					// sobre el cuál se está aplicando el tratamiento.
					transaccionActual.setIdSociedad(SociedadEnum.TELECOM);
					listaTransacciones.add(transaccionActual);

					tratamientoDiferencia = (ShvCobTratamientoDiferencia) Utilidad.clonarObjeto(componentes.getTratamientoDiferencia());
					tratamientoDiferencia.setTransaccion(transaccionActual);
					transaccionActual.getListaTratamientosDiferencias().add(tratamientoDiferencia);
				}

				// Si el importe del medio de pago es mayor al del reintegro
				// actual, tengo que dividir el medio de pago para quedarme
				// con la parte que voy a aplicar
				if (saldoActualMedioPago.compareTo(saldoActualTratamientoDiferencia) > 0) {

					// Hago una copia del medio de pago, y le actualizo el
					// importe con el usado para la factura
					ShvCobMedioPago medioPagoParcial = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoActual);

					// En el caso de que sea un medio de pago de usuario, debo
					// clonar la lista de clientes de manera independiente, ya
					// que el
					// metodo clonarObjeto no se banca clonar objetos
					if (medioPagoActual instanceof ShvCobMedioPagoUsuario) {

						Set<ShvCobMedioPagoCliente> listaClientesMedioPagoParcial = new HashSet<ShvCobMedioPagoCliente>();
						for (ShvCobMedioPagoCliente clienteMedioPagoActual : ((ShvCobMedioPagoUsuario) medioPagoActual).getListaMedioPagoClientes()) {
							ShvCobMedioPagoCliente clienteMedioPagoParcial = (ShvCobMedioPagoCliente) Utilidad.clonarObjeto(clienteMedioPagoActual);
							clienteMedioPagoParcial.setMedioPagoUsuario((ShvCobMedioPagoUsuario) medioPagoParcial);
							listaClientesMedioPagoParcial.add(clienteMedioPagoParcial);
						}
						((ShvCobMedioPagoUsuario) medioPagoParcial).setListaMedioPagoClientes(listaClientesMedioPagoParcial);
					}

					medioPagoParcial.setImporte(saldoActualTratamientoDiferencia);

					// Agrego el medio de pago a la transaccion
					medioPagoParcial.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoParcial);

					// Actualizo el importe del reintegro/envio a ganancia en la
					// transaccion con el importe aplicado del medio de pago
					tratamientoDiferencia.setImporte(medioPagoParcial.getImporte());

					// Actualizo el saldo pendiente de cubrir para la factura
					saldoActualTratamientoDiferencia = saldoActualTratamientoDiferencia.subtract(medioPagoParcial.getImporte());

					// Actualizo el importe del medio de pago, restando lo que
					// voy a utilizar para la factura
					medioPagoActual.setImporte(medioPagoActual.getImporte().subtract(medioPagoParcial.getImporte()));

					saldoActualMedioPago = medioPagoActual.getImporte();

				} else {

					// Agrego el medio de pago a la transaccion
					medioPagoActual.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoActual);

					// Actualizo el importe del reintegro/envio a ganancia en la
					// transaccion con el importe aplicado del medio de pago
					tratamientoDiferencia.setImporte(medioPagoActual.getImporte());

					// Actualizo el saldo pendiente de cubrir para el reintegro
					saldoActualTratamientoDiferencia = saldoActualTratamientoDiferencia.subtract(medioPagoActual.getImporte());
					saldoActualMedioPago = BigDecimal.ZERO;
				}

				//
				// Calculo origen y leyendas
				calcularOrigenCargo(tratamientoDiferencia, medioPagoActual, componentes.getOperacion().getIdOperacion());

				// Si el saldo del medio de pago es cero, quiere decir que el
				// mismo fue utilizado en su totalidad,
				// por lo que debo eliminarlo de la lista de pendientes
				//
				if (saldoActualMedioPago.compareTo(BigDecimal.ZERO) == 0) {
					componentes.getListaMediosDePago().remove(medioPagoActual);
				}
			}
		}

		//
		// Ordeno los medios de pago de acuerdo a las reglas de imputacion, para
		// aplicar sobre facturas
		//
		Collections.sort(componentes.getListaMediosDePago(), new ComparatorOrdenSimulacionShvCobMedioPago());

		//
		// Resto de Transacciones
		//

		// ComponentesCobroBatch componentes = getDummyComponenteCobroBatch();

		ShvCobTransaccion transaccionActual = null;

		ShvCobFactura facturaActual = null;
		ShvCobMedioPago medioPagoActual = null;

		BigDecimal saldoActualFactura = BigDecimal.ZERO;
		BigDecimal saldoActualMedioPago = BigDecimal.ZERO;
		Date fechaValorActual = null;
		
		HashMap<String, Integer> listaGrupo = new HashMap<String, Integer>();
		

		while (!componentes.getListaFacturas().isEmpty()) {

			if (saldoActualFactura.compareTo(BigDecimal.ZERO) == 0) {
				facturaActual = componentes.obtenerSiguenteFactura();
			}

			if (saldoActualMedioPago.compareTo(BigDecimal.ZERO) == 0) {

				if (TipoComprobanteEnum.DUC.name().equals(facturaActual.getTipoComprobante().getIdTipoComprobante())) {

					//
					// Uso de medios de pago para documentos FAC y DEB
					//

					Date fechaActual = DateUtils.truncate(new Date(), Calendar.DATE);

					while (saldoActualMedioPago.compareTo(BigDecimal.ZERO) == 0) {

						medioPagoActual = componentes.obtenerSiguienteMedioPago();

						if (SiNoEnum.SI.equals(medioPagoActual.getUsoPorMoneda().getPermiteUsoDUC())) {

							Date fechaMedioPago = DateUtils.truncate(medioPagoActual.getFechaValor(), Calendar.DATE);

							//
							// Solo puedo usar el medio de pago si la fecha
							// valor del mismo es igual o menor a la del día de
							// hoy
							if (fechaMedioPago.compareTo(fechaActual) <= 0) {
								saldoActualMedioPago = medioPagoActual.getImporte();
							}
						}
					}
				} else {

					//
					// Uso de medios de pago para documentos FAC y DEB
					//
					medioPagoActual = componentes.obtenerSiguienteMedioPago();
					saldoActualMedioPago = medioPagoActual.getImporte();
				}
			}

			if (saldoActualFactura.compareTo(BigDecimal.ZERO) == 0) {

				// Si la factura cambia, estamos ante una nueva transaccion
				transaccionActual = new ShvCobTransaccion();
				transaccionActual.setOperacion(componentes.getOperacion());
				transaccionActual.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
				transaccionActual.setNumeroTransaccion(new Integer(++numeradorTransaccion));
				transaccionActual.setSistema(facturaActual.getSistemaOrigen());
				transaccionActual.setIdSociedad(facturaActual.getSociedad());
				
				// Si la factura pertenece a ShvCobFacturaMic y ShvCobFacturaCalipso el tipo de imputacion sera automatica y pertenecera al grupo 0
				// Si la factura pertenece a ShvCobFacturaUsuario el tipo de imputacion sera manual y dependiendo de la sociedad y sistema pertenecera a un grupo
				if (facturaActual instanceof ShvCobFacturaMic || facturaActual instanceof ShvCobFacturaCalipso) {				
					transaccionActual.setGrupo(grupo);
					transaccionActual.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
				} else {
				String keyGrupo = facturaActual.getSociedad().toString()+"-"+facturaActual.getSistemaOrigen().toString();
					
				if (!listaGrupo.containsKey(keyGrupo))
					{
						listaGrupo.put(keyGrupo, grupoUsuario++);
					} 
					transaccionActual.setGrupo(listaGrupo.get(keyGrupo));
					transaccionActual.setTipoImputacion(TipoImputacionEnum.MANUAL);
				}
	
				listaTransacciones.add(transaccionActual);

				// Agrego la factura a la transaccion
				facturaActual.setTransaccion(transaccionActual);
				transaccionActual.getShvCobFactura().add(facturaActual);

				facturaActual.setTipoPago(calcularTipoPagoTransaccion(facturaActual));

				saldoActualFactura = facturaActual.getImporteCobrar();

			} else {
				
				//
				// Aqui es donde se realiza la división de la factura en diferentes transacciones por diferentes motivos
				// La validación de si una factura puede o debe dividirse, se hace con la llamada a este método
				//
				if (facturaAdmiteCobrosParciales(transaccionActual, facturaActual, medioPagoActual)) {

					ShvCobFactura facturaParcial = (ShvCobFactura) Utilidad.clonarObjeto(facturaActual);
					facturaParcial.setImporteCobrar(facturaActual.getImporteCobrar().subtract(saldoActualFactura));
					// Recalculo el tipo de pago, parcial en este caso, siempre
					facturaParcial.setTipoPago(calcularTipoPagoTransaccion(facturaParcial));

					// Agrego la factura en la transaccion en curso,
					// reemplazando la original
					transaccionActual.getShvCobFactura().remove(facturaActual);
					facturaParcial.setTransaccion(transaccionActual);
					transaccionActual.getShvCobFactura().add(facturaParcial);

					transaccionActual = new ShvCobTransaccion();
					transaccionActual.setOperacion(componentes.getOperacion());
					transaccionActual.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
					transaccionActual.setNumeroTransaccion(new Integer(++numeradorTransaccion));
					transaccionActual.setSistema(facturaActual.getSistemaOrigen());
					transaccionActual.setIdSociedad(facturaActual.getSociedad());
					
					// Si la factura pertenece a ShvCobFacturaMic y ShvCobFacturaCalipso el tipo de imputacion sera automatica y pertenecera al grupo 0
					// Si la factura pertenece a ShvCobFacturaUsuario el tipo de imputacion sera manual y dependiendo de la sociedad y sistema pertenecera a un grupo
					if (facturaActual instanceof ShvCobFacturaMic || facturaActual instanceof ShvCobFacturaCalipso) {				
						transaccionActual.setGrupo(grupo);
						transaccionActual.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
					} else {
						
					String keyGrupo = facturaActual.getSociedad().toString()+"-"+facturaActual.getSistemaOrigen().toString();
					if(!listaGrupo.containsKey(keyGrupo))
						{
							listaGrupo.put(keyGrupo,grupoUsuario++);
						} 
						transaccionActual.setGrupo(listaGrupo.get(keyGrupo));
						transaccionActual.setTipoImputacion(TipoImputacionEnum.MANUAL);
					}

					listaTransacciones.add(transaccionActual);

					facturaActual.setTransaccion(transaccionActual);
					transaccionActual.getShvCobFactura().add(facturaActual);

					// Actualizo el saldo pendiente de cubrir en el objeto
					// factura, junto con el importe original y el saldo actual
					facturaActual.setImporteCobrar(saldoActualFactura);

					if (!Validaciones.isObjectNull(facturaActual.getSaldoActual())) {
						facturaActual.setSaldoActual(facturaActual.getSaldoActual().subtract(facturaParcial.getImporteCobrar()));
					}

					facturaActual.setMontoAcumuladoSimulacion(facturaActual.getMontoAcumuladoSimulacion().add(facturaParcial.getImporteCobrar()));
					facturaActual.setFechaAcumuladoSimulacion(facturaParcial.getFechaValor());

					if (facturaActual instanceof ShvCobFacturaMic) {
						ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) facturaActual;
						facturaMic.setFechaUltimoPagoParcial(facturaParcial.getFechaValor());
					}

					// Hago un "reset" de la fecha valor
					facturaActual.setFechaValor(new Date(0));

					facturaActual.setTipoPago(calcularTipoPagoTransaccion(facturaActual));
				}
			}

			//
			// Verifico el medio de pago
			//

			// Si el importe del medio de pago es mayor al de la factura actual,
			// tengo que dividir el medio de pago para quedarme
			// con la parte que voy a aplicar
			if (saldoActualMedioPago.compareTo(saldoActualFactura) > 0) {

				// Hago una copia del medio de pago, y le actualizo el importe
				// con el usado para la factura
				ShvCobMedioPago medioPagoParcial = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoActual);

				// En el caso de que sea un medio de pago de usuario, debo
				// clonar la lista de clientes de manera independiente, ya que
				// el
				// metodo clonarObjeto no se banca clonar objetos
				if (medioPagoActual instanceof ShvCobMedioPagoUsuario) {

					Set<ShvCobMedioPagoCliente> listaClientesMedioPagoParcial = new HashSet<ShvCobMedioPagoCliente>();
					for (ShvCobMedioPagoCliente clienteMedioPagoActual : ((ShvCobMedioPagoUsuario) medioPagoActual).getListaMedioPagoClientes()) {
						ShvCobMedioPagoCliente clienteMedioPagoParcial = (ShvCobMedioPagoCliente) Utilidad.clonarObjeto(clienteMedioPagoActual);
						clienteMedioPagoParcial.setMedioPagoUsuario((ShvCobMedioPagoUsuario) medioPagoParcial);
						listaClientesMedioPagoParcial.add(clienteMedioPagoParcial);
					}
					((ShvCobMedioPagoUsuario) medioPagoParcial).setListaMedioPagoClientes(listaClientesMedioPagoParcial);
				}

				medioPagoParcial.setImporte(saldoActualFactura);

				// Agrego el medio de pago a la transaccion
				if (medioPagoActual instanceof ShvCobMedioPagoDebitoProximaFactura && !(facturaActual instanceof ShvCobFacturaUsuario) ) {

					// Calculo de antemano la fecha valor ya que es posible que
					// en algunos casos no la tenga, y la necesito para
					// datos propios del debito a proxima
					facturaActual.setFechaValor(calcularFechaValorTransaccion(facturaActual, medioPagoParcial));

					ShvCobMedioPago medioPagoDebitoProximaFactura = instanciarMedioPagoDebitoProximaFacturaADemanda(medioPagoParcial, facturaActual, componentes.getIdOperacion());

					medioPagoDebitoProximaFactura.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoDebitoProximaFactura);
				} else {
					cobroBatchSoporteServicio.agregarTipoCambioFactura(facturaActual, medioPagoParcial);
					medioPagoParcial.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoParcial);
				}

				// Actualizo el saldo pendiente de cubrir para la factura
				saldoActualFactura = saldoActualFactura.subtract(medioPagoParcial.getImporte());

				// Actualizo el importe del medio de pago, restando lo que voy a
				// utilizar para la factura
				medioPagoActual.setImporte(medioPagoActual.getImporte().subtract(medioPagoParcial.getImporte()));

				if (Validaciones.isObjectNull(medioPagoActual.getMontoAcumuladoSimulacion())) {
					medioPagoActual.setMontoAcumuladoSimulacion(medioPagoParcial.getImporte());
				} else {
					medioPagoActual.setMontoAcumuladoSimulacion(medioPagoActual.getMontoAcumuladoSimulacion().add(medioPagoParcial.getImporte()));
				}

				saldoActualMedioPago = medioPagoActual.getImporte();

			} else {

				// Agrego el medio de pago a la transaccion
				if (medioPagoActual instanceof ShvCobMedioPagoDebitoProximaFactura && !(facturaActual instanceof ShvCobFacturaUsuario)) {

					// Calculo de antemano la fecha valor ya que es posible que
					// en algunos casos no la tenga, y la necesito para
					// datos propios del debito a proxima
					facturaActual.setFechaValor(calcularFechaValorTransaccion(facturaActual, medioPagoActual));

					ShvCobMedioPago medioPagoDebitoProximaFactura = instanciarMedioPagoDebitoProximaFacturaADemanda(medioPagoActual, facturaActual, componentes.getIdOperacion());

					medioPagoDebitoProximaFactura.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoDebitoProximaFactura);
				} else {
					cobroBatchSoporteServicio.agregarTipoCambioFactura(facturaActual, medioPagoActual);
					medioPagoActual.setTransaccion(transaccionActual);
					transaccionActual.getShvCobMedioPago().add(medioPagoActual);
				}

				// Actualizo el saldo pendiente de cubrir para la factura
				saldoActualFactura = saldoActualFactura.subtract(medioPagoActual.getImporte());
				saldoActualMedioPago = BigDecimal.ZERO;
			}

			// Calculo la fecha valor
			fechaValorActual = calcularFechaValorTransaccion(facturaActual, medioPagoActual);
			transaccionActual.getFactura().setFechaValor(fechaValorActual);

			// Si el saldo del medio de pago es cero, quiere decir que el mismo
			// fue utilizado en su totalidad,
			// por lo que debo eliminarlo de la lista de pendientes
			//
			if (saldoActualMedioPago.compareTo(BigDecimal.ZERO) == 0) {
				componentes.getListaMediosDePago().remove(medioPagoActual);
			}

			// Si el saldo de la factura es cero quiere decir que la misma ya
			// fue saldada,
			// por lo que debo eliminarla de la lista de pendientes.
			//
			if (saldoActualFactura.compareTo(BigDecimal.ZERO) == 0) {
				componentes.getListaFacturas().remove(facturaActual);
			}
		}
		// Metodo para calcular el numero de transaccion ficticio que tendran las transacciones
		listaTransacciones = calcularNumeroTransaccionFicticio(listaTransacciones);
		return listaTransacciones;
	}
	// Se agrega un numero de transaccion ficticio el cual agrupara a las transacciones que tengan la misma sociedad y sistema que el parametro configurado en shv_param_parametro e igual fecha valor.
	// Las transacciones que su sociedad y sistema no se encuentren en la parametrica tendran como numero de transaccion ficticio el numero de transaccion real.
	private Set<ShvCobTransaccion> calcularNumeroTransaccionFicticio(Set<ShvCobTransaccion> listaTransacciones) throws NegocioExcepcion {
		HashMap<String, Integer> listaTransaccion = new HashMap<String, Integer>();
		String valores = parametroServicio.getValorTexto(Constantes.LISTA_SOCIEDAD_SISTEMA_AGRUPAMIENTO_NRO_TRANSACCION_FICTICIO);
		
		for(ShvCobTransaccion transaccion  : listaTransacciones){
			String valor = transaccion.getIdSociedad().getApocope() + Constantes.SEPARADOR_PIPE + transaccion.getSistema().getDescripcionCorta() + Constantes.SEPARADOR_PIPE;
			if(valores.contains(valor)){
				String keyTransaccion = transaccion.getFactura().getFechaValor().toString() + transaccion.getIdSociedad() + transaccion.getSistema();
				if (!listaTransaccion.containsKey(keyTransaccion)){
					listaTransaccion.put(keyTransaccion, transaccion.getNumeroTransaccion());
				} 
					transaccion.setNumeroTransaccionFicticio(listaTransaccion.get(keyTransaccion));
			} else{
				transaccion.setNumeroTransaccionFicticio(transaccion.getNumeroTransaccion());
			}
		}
		return listaTransacciones;
	}
	

	/**
	 * 
	 * @param medioPago
	 * @param factura
	 * @return
	 * @throws Exception
	 */
	private ShvCobMedioPago instanciarMedioPagoDebitoProximaFacturaADemanda(ShvCobMedioPago medioPago, ShvCobFactura factura, Long idOperacion) throws Exception {
		if (factura instanceof ShvCobFacturaMic) {

			ShvCobMedioPagoDebitoProximaFacturaMic medioPagoDebitoProxima = new ShvCobMedioPagoDebitoProximaFacturaMic();
			medioPagoDebitoProxima.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC));
			medioPagoDebitoProxima.setSistemaOrigen(SistemaEnum.MIC);

			medioPagoDebitoProxima.setFecha(Utilidad.obtenerFechaActual());

			// Seteo datos por defecto para poder grabar el tratamiento sin
			// problemas.
			medioPagoDebitoProxima.setCalcularFechaHasta(SiNoEnum.SI);
			medioPagoDebitoProxima.setTrasladarIntereses(SiNoEnum.NO);

			// Este valor se convierte en una "T" cuando se invoca al servicio
			// de generacion de cargos
			medioPagoDebitoProxima.setQueHacerConIntereses(TratamientoInteresesEnum.TM);

			calcularOrigenCargo(medioPagoDebitoProxima, factura, idOperacion);

			medioPagoDebitoProxima.setMigradoDeimos(SiNoEnum.NO);
			medioPagoDebitoProxima.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
			medioPagoDebitoProxima.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
			medioPagoDebitoProxima.setImporte(medioPago.getImporte());
			medioPagoDebitoProxima.setMoneda(MonedaEnum.PES);
			medioPagoDebitoProxima.setMonedaImporte(medioPago.getMonedaImporte());

			medioPagoDebitoProxima.setAcuerdoTrasladoCargo(factura.getAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setEstadoAcuerdoTrasladoCargo(factura.getEstadoAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setIdClienteLegadoAcuerdoTrasladoCargo(factura.getIdClienteLegadoAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setIdClienteLegado(factura.getIdClienteLegadoAcuerdoTrasladoCargo());
			// El campo fechaVencimiento en el medio de pago debito a proxima
			// originalmente guardaba la
			// fecha de vencimiento de la factura, por ello el nombre del
			// atributo y campo en la tabla
			// Luego esa fecha cambio a ser fecha valor, cambiamos el dato pero
			// el nombre de la tabla nos quedará mal
			medioPagoDebitoProxima.setFechaVencimientoFactura(factura.getFechaValor());

			return medioPagoDebitoProxima;

		} else {

			ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoDebitoProxima = new ShvCobMedioPagoDebitoProximaFacturaCalipso();
			medioPagoDebitoProxima.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO));
			medioPagoDebitoProxima.setSistemaOrigen(SistemaEnum.CALIPSO);

			medioPagoDebitoProxima.setFecha(Utilidad.obtenerFechaActual());

			// Seteo datos por defecto para poder grabar el tratamiento sin
			// problemas.
			medioPagoDebitoProxima.setCalcularFechaHasta(SiNoEnum.SI);
			medioPagoDebitoProxima.setTrasladarIntereses(SiNoEnum.NO);

			// Este valor se convierte en una "T" cuando se invoca al servicio
			// de generacion de cargos
			medioPagoDebitoProxima.setQueHacerConIntereses(TratamientoInteresesEnum.TM);

			calcularOrigenCargo(medioPagoDebitoProxima, factura, idOperacion);

			medioPagoDebitoProxima.setMigradoDeimos(SiNoEnum.NO);
			medioPagoDebitoProxima.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
			medioPagoDebitoProxima.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
			medioPagoDebitoProxima.setImporte(medioPago.getImporte());
			// medioPagoDebitoProxima.setMoneda(MonedaEnum.PES);
			medioPagoDebitoProxima.setMoneda(medioPago.getMoneda());
			medioPagoDebitoProxima.setMonedaImporte(medioPago.getMonedaImporte());

			medioPagoDebitoProxima.setAcuerdoTrasladoCargo(factura.getAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setEstadoAcuerdoTrasladoCargo(factura.getEstadoAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setIdClienteLegadoAcuerdoTrasladoCargo(factura.getIdClienteLegadoAcuerdoTrasladoCargo());
			medioPagoDebitoProxima.setIdClienteLegado(factura.getIdClienteLegadoAcuerdoTrasladoCargo());
			// El campo fechaVencimiento en el medio de pago debito a proxima
			// originalmente guardaba la
			// fecha de vencimiento de la factura, por ello el nombre del
			// atributo y campo en la tabla
			// Luego esa fecha cambio a ser fecha valor, cambiamos el dato pero
			// el nombre de la tabla nos quedará mal
			medioPagoDebitoProxima.setFechaVencimientoFactura(factura.getFechaValor());

			medioPagoDebitoProxima.setTipoCambio(((ShvCobFacturaCalipso) factura).getTipoCambio());

			return medioPagoDebitoProxima;
			
		}
		
		
	}

	/**
	 * Calcula el cargo origen de acuerdo a los medios de pago residentes
	 * 
	 * @param
	 * @param medioPago
	 *            , el medio de pago sobre el cuál está aplicando el tratamiento
	 *            de la diferencia
	 * @param idOperacion
	 * @return
	 */
	private Modelo calcularOrigenCargo(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPago medioPago, Long idOperacion) {

		OrigenCargoEnum origenCargo = null;
		String leyendaFacturaCargo = null;
		String leyendaFacturaInteres = null;
		String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);

		if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia()) || TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {

			if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_NC;

			} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) || TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {

				origenCargo = OrigenCargoEnum.TRASLADO_REMANENTE;

			} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_PAGO_CUENTA;

			} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) || TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_CHEQUE;

			} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_DEPOSITO;

			} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_TRANSFERENCIA;

			} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_INTERDEPOSITO;

			} else if (TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
				origenCargo = OrigenCargoEnum.TRASLADO_SALDO_COMPENSACION;

			}
			if (!Validaciones.isObjectNull(origenCargo)) {
				leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.cargo." + origenCargo.name());
				leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.intereses." + origenCargo.name());
			}

			leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, medioPago.getReferencia(), idOperacionFormateado);
			leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, medioPago.getReferencia(), idOperacionFormateado);

			tratamientoDiferencia.setOrigenCargo(origenCargo);
			tratamientoDiferencia.setLeyendaFacturaCargo(leyendaFacturaCargo);
			tratamientoDiferencia.setLeyendaFacturaInteres(leyendaFacturaInteres);
		}
		return tratamientoDiferencia;
	}

	/**
	 * 
	 * @param medioPagoDebitoProxima
	 *            (puede ser un debito a proxima mic o calipso)
	 * @param factura
	 * @param idOperacion
	 * @return
	 */
	private Modelo calcularOrigenCargo(ShvCobMedioPago medioPagoDebitoProxima, ShvCobFactura factura, Long idOperacion) {

		OrigenCargoEnum origenCargo = OrigenCargoEnum.TRASLADO_SALDO_FACTURA;

		String leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.cargo." + origenCargo.name());
		String leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.intereses." + origenCargo.name());

		String referenciaFactura = "";

		if (TipoComprobanteEnum.DUC.name().equals(factura.getTipoComprobante().getIdTipoComprobante())) {
			referenciaFactura = ((ShvCobFacturaMic) factura).getIdReferenciaFactura();
		} else {
			referenciaFactura = Utilidad.parsearNroDocNoDuc(factura.getClaseComprobante(), factura.getSucursalComprobante(), factura.getNumeroComprobante());
		}

		String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);

		leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, referenciaFactura, idOperacionFormateado);
		leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, referenciaFactura, idOperacionFormateado);

		if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoDebitoProxima;

			debitoProximaFacturaCalipso.setOrigenCargo(origenCargo);
			debitoProximaFacturaCalipso.setLeyendaFacturaCargo(leyendaFacturaCargo);
			debitoProximaFacturaCalipso.setLeyendaFacturaInteres(leyendaFacturaInteres);

		} else if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {

			ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoDebitoProxima;

			debitoProximaFacturaMic.setOrigenCargo(origenCargo);
			debitoProximaFacturaMic.setLeyendaFacturaCargo(leyendaFacturaCargo);
			debitoProximaFacturaMic.setLeyendaFacturaInteres(leyendaFacturaInteres);
		}

		return medioPagoDebitoProxima;
	}

	public void simularCobroEnCobradores(Long idCobro) {
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			simularCobroEnCobradores(cobro);

		} catch (PersistenciaExcepcion | NegocioExcepcion e) {
			Traza.error(getClass(), e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobCobro simularCobroEnCobradores(ShvCobCobro cobro) throws NegocioExcepcion {

		List<ApropiacionCalipsoRto> listaApropiacionCalipsoRto = new ArrayList<ApropiacionCalipsoRto>();
		List<ApropiacionMicRto> listaApropiacionMicRto = new ArrayList<ApropiacionMicRto>();
		List<GeneracionCargoCalipsoRto> listaGeneracionCargoCalipsoRto = new ArrayList<GeneracionCargoCalipsoRto>();
		List<GeneracionCargoMicRto> listaGeneracionCargoMicRto = new ArrayList<GeneracionCargoMicRto>();
		List<ApropiacionDeimosRto> listaApropiacionDeimosRto = new ArrayList<ApropiacionDeimosRto>();

		Long idCliente = new Long(0);
		List<VerificarPartidasSapRto> listaVerificarPartidasSapRto = new ArrayList<VerificarPartidasSapRto>();
		List<ConsultarProveedoresSapRto> listaConsultarProveedoresSapRto = new ArrayList<ConsultarProveedoresSapRto>();
		ShvCobMedioPagoDocumentoCap documentoCap = null;

		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Recorro la lista de transacciones. ", String.valueOf(cobro.getOperacion().getIdOperacion())));

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			// TODO: si la transaccion tiene error a nivel de transaccion, es
			// correcto que la siga simulando?

			ShvCobFactura factura = null;

			// Si existe medio de pago "Saldo a Cobrar", la factura asociada en
			// la transaccion no se simula
			// ni se imputa, ya que no se está apropiando realmente. Por lo
			// tanto, dejamos a la variable "factura" en nulo para que
			// no sea tomada por error mas adelante en el codigo. Seteamos OK al
			// estado de la factura en esta transacción, igual que al medio de
			// pago.
			// Aqui el medio de pado "Saldo a Cobrar" solo se usa para que las
			// cuentas entre debitos y creditos cierre en cero.

			// 05/04 - Actualización por defecto 1543: es necesario tener el
			// saldo actual en moneda origen para la factura asociada al medio
			// de pago
			// "Saldo a Cobrar", por lo tanto si lo vamos a simular, pero solo
			// eso. En la imputación sigue como hasta ahora, no se tendrá en
			// cuenta.

			// if (transaccion.getMediosPago().iterator().next() instanceof
			// ShvCobMedioPagoSaldoACobrar) {
			// transaccion.getFactura().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			// transaccion.getMediosPago().iterator().next().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			// } else {
			factura = transaccion.getFactura();
			// }

			List<ShvCobMedioPago> listaMediosPagoMIC = new ArrayList<ShvCobMedioPago>();
			List<ShvCobMedioPago> listaMediosPagoCalipso = new ArrayList<ShvCobMedioPago>();
			List<ShvCobMedioPagoShiva> listaMediosPagoShiva = new ArrayList<ShvCobMedioPagoShiva>();
			List<ShvCobMedioPagoUsuario> listaMediosPagoUsuario = new ArrayList<ShvCobMedioPagoUsuario>();

			ShvCobMedioPago debitoProximaFacturaMic = null;
			ShvCobMedioPago debitoProximaFacturaCalipso = null;

			ShvCobTratamientoDiferencia creditoProximaFacturaMic = null;
			ShvCobTratamientoDiferencia creditoProximaFacturaCalipso = null;
			ShvCobTratamientoDiferencia tratamientoDiferencia = null;

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {

				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

					creditoProximaFacturaMic = transaccion.getTratamientoDiferencia();
				} else {
					if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

						creditoProximaFacturaCalipso = transaccion.getTratamientoDiferencia();
					} else {
						tratamientoDiferencia = transaccion.getTratamientoDiferencia();
					}
				}
			}

			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
					debitoProximaFacturaCalipso = medioPago;

				} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					debitoProximaFacturaMic = medioPago;

				} else if (medioPago instanceof ShvCobMedioPagoMic) {
					listaMediosPagoMIC.add(medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoCalipso) {
					listaMediosPagoCalipso.add(medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoShiva) {
					listaMediosPagoShiva.add((ShvCobMedioPagoShiva) medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoUsuario) {
					listaMediosPagoUsuario.add((ShvCobMedioPagoUsuario) medioPago);

					// El documento CAP se encuentra asociado a cada 'parte' del
					// medio de pago que se encuentra dividido en
					// diferentes transacciones. Como es siemre el mismo, una
					// vez encontrado ya me quedo con él
					Set<ShvCobMedioPagoCliente> setClientesmp = ((ShvCobMedioPagoUsuario) medioPago).getListaMedioPagoClientes();
					if (setClientesmp.iterator().hasNext()) {

						ShvCobMedioPagoCliente medioPagoCliente = setClientesmp.iterator().next();
						if (!Validaciones.isObjectNull(medioPagoCliente.getIdClienteLegado())) {
							idCliente = new Long(medioPagoCliente.getIdClienteLegado());
						}
					}

					if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
						if (Validaciones.isObjectNull(documentoCap)) {
							documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getDocumentoCap();
						}

					} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
						if (Validaciones.isObjectNull(documentoCap)) {
							documentoCap = ((ShvCobMedioPagoCompensacionProveedor) medioPago).getDocumentoCap();
						}
					}
				}
			}

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Comienzo simulación de apropiaciones en los cobradores. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

			// Simular apropiacion de medios de pago en Shiva
			if (Validaciones.isCollectionNotEmpty(listaMediosPagoShiva)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de Medios de pago en Shiva. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				List<ResultadoSimulacion> resultado = simularApropiacionShiva(listaMediosPagoShiva);
				listaResultadoSimulacion.addAll(resultado);
			}

			// Simular apropiacion de medios de pago de usuario
			if (Validaciones.isCollectionNotEmpty(listaMediosPagoUsuario)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de Medios de pago de usuario. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				List<ResultadoSimulacion> resultado = simularApropiacionMedioPagoUsuario(listaMediosPagoUsuario);
				listaResultadoSimulacion.addAll(resultado);
			}

			// Simular tratamiento de diferencia, envio a ganancias y reintegros
			if (!Validaciones.isObjectNull(tratamientoDiferencia)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de tratamiento de diferencia. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				ResultadoSimulacion resultado = simularApropiacionTratamientoDiferencia(tratamientoDiferencia);
				listaResultadoSimulacion.add(resultado);
			}

			// Simular apropiacion de facturas y/o medios de pago en Calipso
			if ((!Validaciones.isObjectNull(factura) && factura instanceof ShvCobFacturaCalipso) || Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de medio de pago y/o factura Calipso. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));
				EntradaCalipsoApropiacionWS entradaWS = null;

				// Resuelvo la fecha de cobranza a enviar a Calipso
				Date fechaCobranza = null;
				if (!Validaciones.isObjectNull(creditoProximaFacturaMic)) {
					fechaCobranza = creditoProximaFacturaMic.getFechaValor();
				}
				if (!Validaciones.isObjectNull(creditoProximaFacturaCalipso)) {
					fechaCobranza = creditoProximaFacturaCalipso.getFechaValor();
				}
				if (!Validaciones.isObjectNull(factura)) {
					fechaCobranza = factura.getFechaValor();
				}
				if (!Validaciones.isObjectNull(tratamientoDiferencia)) {
					fechaCobranza = tratamientoDiferencia.getFechaValor();
				}

				ApropiacionCalipsoRto dto = new ApropiacionCalipsoRto();

				if (factura instanceof ShvCobFacturaCalipso) {
					entradaWS = prepararSimulacionApropiacionCalipso(listaMediosPagoCalipso, (ShvCobFacturaCalipso) factura, cobro, fechaCobranza);
					// Se setea la factura de calipso para utilizar cuando
					// responde
					dto.setFactura((ShvCobFacturaCalipso) factura);
				} else {
					entradaWS = prepararSimulacionApropiacionCalipso(listaMediosPagoCalipso, null, cobro, fechaCobranza);
				}

				dto.setEntradaCalipsoApropiacionWS(entradaWS);
				dto.setListaMediosPagoAEnviar(listaMediosPagoCalipso);
				dto.setTipoMedioPagoNotaCredito(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.NOTA_CREDITO));

				listaApropiacionCalipsoRto.add(dto);
			}

			// Simular generacion de cargos creditos en Calipso
			if (!Validaciones.isObjectNull(creditoProximaFacturaCalipso)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo generacion cargo credito Calipso. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				EntradaCalipsoCargosWS entrada = prepararSimulacionGeneracionCargosCreditoCalipso(creditoProximaFacturaCalipso);
				if (entrada != null) {
					GeneracionCargoCalipsoRto dto = new GeneracionCargoCalipsoRto();
					dto.setEntradaCalipsoGeneracionCargosWs(entrada);
					dto.setOperacionTransaccionFormateado(creditoProximaFacturaCalipso.getTransaccion().getOperacionTransaccionFormateado());
					dto.setTipoDebito(false);
					listaGeneracionCargoCalipsoRto.add(dto);
				}
			}

			// Simular generacion de cargos debitos en Calipso
			if (!Validaciones.isObjectNull(debitoProximaFacturaCalipso)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo generacion cargo debito Calipso. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				EntradaCalipsoCargosWS entrada = this.prepararSimulacionGeneracionCargosDebitoCalipso((ShvCobMedioPagoDebitoProximaFacturaCalipso) debitoProximaFacturaCalipso);
				GeneracionCargoCalipsoRto dto = new GeneracionCargoCalipsoRto();
				dto.setEntradaCalipsoGeneracionCargosWs(entrada);
				dto.setOperacionTransaccionFormateado(((ShvCobMedioPagoDebitoProximaFacturaCalipso) debitoProximaFacturaCalipso).getTransaccion().getOperacionTransaccionFormateado());
				dto.setTipoDebito(true);
				listaGeneracionCargoCalipsoRto.add(dto);
			}

			// Simular apropiacion de facturas y/o medios de pago en Mic
			if ((!Validaciones.isObjectNull(factura) && factura instanceof ShvCobFacturaMic) || Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de medio de pago y/o factura MIC. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				MicTransaccionADCDto entradaMicApropiacionJms = null;
				if (factura instanceof ShvCobFacturaMic) {
					entradaMicApropiacionJms = prepararSimulacionApropiacionMic(listaMediosPagoMIC, (ShvCobFacturaMic) factura, cobro);
				} else {
					entradaMicApropiacionJms = prepararSimulacionApropiacionMic(listaMediosPagoMIC, null, cobro);
				}

				ApropiacionMicRto dto = new ApropiacionMicRto();
				dto.setFactura(factura instanceof ShvCobFacturaMic ? (ShvCobFacturaMic) factura : null);
				dto.setEntradaMicApropiacionJms(entradaMicApropiacionJms);
				dto.setListaMediosPagoAEnviar(listaMediosPagoMIC);
				listaApropiacionMicRto.add(dto);
			}

			// Simular generacion de cargos creditos en Mic
			if (!Validaciones.isObjectNull(creditoProximaFacturaMic)) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo generacion cargo credito Mic. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				MicTransaccionGeneracionCargosDto entradaMicGeneracionCargoJms = prepararSimulacionGeneracionCargosCreditoMic(creditoProximaFacturaMic);
				GeneracionCargoMicRto dto = new GeneracionCargoMicRto();
				dto.setEntradaMicGeneracionCargosJms(entradaMicGeneracionCargoJms);
				dto.setOperacionTransaccionFormateado(creditoProximaFacturaMic.getTransaccion().getOperacionTransaccionFormateado());
				dto.setTipoDebito(false);
				listaGeneracionCargoMicRto.add(dto);
			}

			// Simular generacion de cargos debitos en Mic
			if (!Validaciones.isObjectNull(debitoProximaFacturaMic)) {
				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo generacion cargo debito Mic. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				MicTransaccionGeneracionCargosDto entradaMicGeneracionCargoJms = prepararSimulacionGeneracionCargosDebitoMic((ShvCobMedioPagoDebitoProximaFacturaMic) debitoProximaFacturaMic);
				GeneracionCargoMicRto dto = new GeneracionCargoMicRto();
				dto.setEntradaMicGeneracionCargosJms(entradaMicGeneracionCargoJms);
				dto.setOperacionTransaccionFormateado(((ShvCobMedioPagoDebitoProximaFacturaMic) debitoProximaFacturaMic).getTransaccion().getOperacionTransaccionFormateado());
				dto.setTipoDebito(true);
				listaGeneracionCargoMicRto.add(dto);
			}

			// Simular apropiacion de facturas de usuario
			if (!Validaciones.isObjectNull(factura) && factura instanceof ShvCobFacturaUsuario) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Simulo apropiacion de factura de usuario. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));
				List<ResultadoSimulacion> resultado = simularApropiacionFacturaUsuario((ShvCobFacturaUsuario) factura);
				listaResultadoSimulacion.addAll(resultado);
			}

		}

		// Verificar partidas en SAP (simular compensacion).
		if (!Validaciones.isObjectNull(documentoCap)) {

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: -) Simulo generacion de documento K2 en SAP. ", String.valueOf(cobro.getOperacion().getIdOperacion())));

			EntradaSapVerificacionPartidasWS entradaSapVerificacionPartidas = prepararSimulacionVerificarPartidasSap(documentoCap, cobro.getOperacion().getIdOperacion());
			if (entradaSapVerificacionPartidas != null) {
				VerificarPartidasSapRto dto = new VerificarPartidasSapRto();
				dto.setEntradaSapVerificacionPartidas(entradaSapVerificacionPartidas);
				dto.setOperacionFormateado(Utilidad.rellenarCerosIzquierda(String.valueOf(cobro.getOperacion().getIdOperacion()), 7));
				listaVerificarPartidasSapRto.add(dto);
			}
		}

		// Verificar partidas en SAP (simular compensacion).
		if (!Validaciones.isObjectNull(documentoCap)) {

			Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: -) Voy a buscar el estado del proveedor en SAP. ", String.valueOf(cobro.getOperacion().getIdOperacion())));

			EntradaSapConsultaProveedoresWS entradaSapConsultaProveedores = new EntradaSapConsultaProveedoresWS();

			try {
				ShvCobEdCliente cliente = cobroOnlineClienteDao.buscarClientePorIdCobroYIdClienteLegado(cobro.getIdCobro(), idCliente);
				if (!Validaciones.isObjectNull(cliente) && !Validaciones.isNullOrEmpty(cliente.getCuit())) {

					List<String> listaCuit = new ArrayList<String>();
					listaCuit.add(cliente.getCuit());
					entradaSapConsultaProveedores.setListaCuits(listaCuit);
					entradaSapConsultaProveedores.setListaCuits(entradaSapConsultaProveedores.getListaCuits());
				}

			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			if (entradaSapConsultaProveedores != null) {
				ConsultarProveedoresSapRto dto = new ConsultarProveedoresSapRto();
				dto.setEntradaSapConsultaProveedoresWS(entradaSapConsultaProveedores);
				dto.setOperacionFormateado(Utilidad.rellenarCerosIzquierda(String.valueOf(cobro.getOperacion().getIdOperacion()), 7));
				dto.setIdOperacion(cobro.getOperacion().getIdOperacion());
				listaConsultarProveedoresSapRto.add(dto);
			}
		}

		// Ahi se invoca ejecutando los hilos en paralelos
		String idOperacion = Utilidad.rellenarCerosIzquierda(cobro.getOperacion().getIdOperacion().toString(), 7);
		String usuario = cobro.getWorkflow().getUsuarioAlta();

		// Ejecutar la simulacion usando hilos en paralelos
		CobroSimulacionExecutor cobroSimulacionExcecutor = new CobroSimulacionExecutor(usuario, idOperacion);
		cobroSimulacionExcecutor.inicializarListas(listaApropiacionCalipsoRto, listaApropiacionMicRto, listaGeneracionCargoCalipsoRto, listaGeneracionCargoMicRto, null, listaVerificarPartidasSapRto, listaConsultarProveedoresSapRto);

		List<ResultadoSimulacion> listaResultadoSimulacionHilos = cobroSimulacionExcecutor.ejecutarSimulacion();
		listaResultadoSimulacion.addAll(listaResultadoSimulacionHilos);

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Actualizo la info del cobro, post simulacion Mic/Calipso ", String.valueOf(cobro.getOperacion().getIdOperacion())));

		// Actualizo el cobro con las respuestas de Mic/Calipso
		cobro = actualizarCobroPostSimulacion(listaResultadoSimulacion, cobro);

		// Aqui actualiza los saldos en moneda origen de documentos en dólares
		// para cobros en pesos
		cobro = calcularSaldoActualMonedaOrigen(cobro);

		//
		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
			ShvCobFactura factura = transaccion.getFactura();

			List<ShvCobMedioPago> listaMediosPagoMIC = new ArrayList<ShvCobMedioPago>();
			List<ShvCobMedioPago> listaMediosPagoCalipso = new ArrayList<ShvCobMedioPago>();

			// Actualizo los medios de pago con posible info de Deimos
			// proveniente de Mic / Calipso
			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
				if (medioPago instanceof ShvCobMedioPagoMic) {
					listaMediosPagoMIC.add(medioPago);
				} else if (medioPago instanceof ShvCobMedioPagoCalipso) {
					listaMediosPagoCalipso.add(medioPago);
				}
			}

			// Actualizo facturas con posible info de Deimos proveniente de Mic
			// / Calipso
			if ((!Validaciones.isObjectNull(factura) && factura instanceof ShvCobFacturaCalipso) || Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)) {
				if (factura instanceof ShvCobFacturaCalipso) {
					actualizarInformacionDeimos(listaResultadoSimulacionHilos, listaMediosPagoCalipso, (ShvCobFacturaCalipso) factura);
				} else {
					actualizarInformacionDeimos(listaResultadoSimulacionHilos, listaMediosPagoCalipso, null);
				}
			}

			// Simular apropiacion de facturas y/o medios de pago en Mic
			if ((!Validaciones.isObjectNull(factura) && factura instanceof ShvCobFacturaMic) || Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)) {
				if (factura instanceof ShvCobFacturaMic) {
					actualizarInformacionDeimos(listaResultadoSimulacionHilos, listaMediosPagoMIC, (ShvCobFacturaMic) factura);
				} else {
					actualizarInformacionDeimos(listaResultadoSimulacionHilos, listaMediosPagoMIC, null);
				}
			}

			// preparar datos para apropiacion en Deimos
			EntradaDeimosApropiacionWS entradaDeimos = prepararSimulacionApropiacionDeimos(transaccion);
			if (entradaDeimos != null) {
				ApropiacionDeimosRto dto = new ApropiacionDeimosRto();
				dto.setEntradaDeimosApropiacionWS(entradaDeimos);
				listaApropiacionDeimosRto.add(dto);
			}
		}

		// Ejecutar la simulacion para deimos
		CobroSimulacionExecutor cobroSimulacionDeimosExcecutor = new CobroSimulacionExecutor(usuario, idOperacion);
		cobroSimulacionDeimosExcecutor.inicializarListas(null, null, null, null, listaApropiacionDeimosRto, null, null);

		List<ResultadoSimulacion> listaResultadoSimulacionDeimos = cobroSimulacionDeimosExcecutor.ejecutarSimulacion();
		// Fin - ejecucion de simulacion

		Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Actualizo la info del cobro, post simulacion Deimos. ", String.valueOf(cobro.getOperacion().getIdOperacion())));

		// Actualizo el cobro con las respuestas de Deimos
		cobro = actualizarCobroPostSimulacion(listaResultadoSimulacionDeimos, cobro);

		return cobro;
	}

	/**
	 * 
	 * @param resultado
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 */
	private void actualizarInformacionDeimos(List<ResultadoSimulacion> resultado, List<ShvCobMedioPago> listaMediosPagoAEnviar, ShvCobFactura factura) {

		for (ResultadoSimulacion resultadoSimulacion : resultado) {

			SiNoEnum siNoEnum = null;
			if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionFacturaMic) {
				siNoEnum = ((ResultadoSimulacionApropiacionFacturaMic) resultadoSimulacion).getMigradoDeimos();
			}
			if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionFacturaCalipso) {
				siNoEnum = ((ResultadoSimulacionApropiacionFacturaCalipso) resultadoSimulacion).getMigradoDeimos();
			}

			if (!Validaciones.isObjectNull(factura) && !Validaciones.isObjectNull(siNoEnum)) {
				if (resultadoSimulacion.getIdOperacionShiva().equalsIgnoreCase(factura.getTransaccion().getOperacionTransaccionFormateado())) {
					factura.setMigradoDeimos(siNoEnum);
				}
			}

			if (Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)) {
				for (ShvCobMedioPago medioPago : listaMediosPagoAEnviar) {

					siNoEnum = null;
					if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoMic && medioPago instanceof ShvCobMedioPagoMic) {
						ResultadoSimulacionApropiacionMedioPagoMic rs = (ResultadoSimulacionApropiacionMedioPagoMic) resultadoSimulacion;
						siNoEnum = rs.getMigradoDeimos();

						if (medioPago.getTransaccion().getNumeroTransaccion().compareTo(rs.getNumeroTransaccion()) == 0 && medioPago.getIdBusquedaRespuestaCobrador().equals(rs.getIdBusquedaRespuestaCobrador()) && !Validaciones.isObjectNull(siNoEnum)) {

							medioPago.setMigradoDeimos(siNoEnum);
						}
					}

					if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoCalipso && medioPago instanceof ShvCobMedioPagoCalipso) {

						ResultadoSimulacionApropiacionMedioPagoCalipso rs = (ResultadoSimulacionApropiacionMedioPagoCalipso) resultadoSimulacion;
						siNoEnum = rs.getMigradoDeimos();

						if (medioPago.getTransaccion().getNumeroTransaccion().compareTo(rs.getNumeroTransaccion()) == 0 && medioPago.getIdBusquedaRespuestaCobrador().equals(rs.getIdBusquedaRespuestaCobrador()) && !Validaciones.isObjectNull(siNoEnum)) {

							medioPago.setMigradoDeimos(siNoEnum);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobCobro actualizarCobroPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobCobro cobro) throws NegocioExcepcion {

		Collection<ShvCobTransaccion> listaTransaccionesDiferenciaCambioGenerdas = null;
		Collection<ShvCobTransaccion> listaTransaccionesDiferenciaCambioAlmacenadas = new ArrayList<ShvCobTransaccion>();

		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Actualizo info tratamiento de diferencia. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				actualizarTratamientoDiferneciaDeTransaccionPostSimulacion(listaResultadoSimulacion, transaccion.getTratamientoDiferencia());

			} else if (!Validaciones.isObjectNull(transaccion.getFactura())) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Actualizo info tratamiento de factura. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				int cantidadTransaccionesActual = cobro.getOperacion().getTransacciones().size() + listaTransaccionesDiferenciaCambioAlmacenadas.size();
				listaTransaccionesDiferenciaCambioGenerdas = actualizarFacturasDeTransaccionPostSimulacion(listaResultadoSimulacion, transaccion.getFactura(), cantidadTransaccionesActual);

				if (Validaciones.isCollectionNotEmpty(listaTransaccionesDiferenciaCambioGenerdas)) {
					listaTransaccionesDiferenciaCambioAlmacenadas.addAll(listaTransaccionesDiferenciaCambioGenerdas);
				}
			}

			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {

				Traza.auditoria(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0} nroTransaccion: {1}) Actualizo info tratamiento de medio de pago. ", String.valueOf(cobro.getOperacion().getIdOperacion()), String.valueOf(transaccion.getNumeroTransaccion())));

				actualizarMediosPagoDeTransaccionPostSimulacion(listaResultadoSimulacion, medioPago);
			}
		}

		if (Validaciones.isCollectionNotEmpty(listaTransaccionesDiferenciaCambioAlmacenadas)) {
			cobro.getOperacion().getTransacciones().addAll(listaTransaccionesDiferenciaCambioAlmacenadas);
		}

		return cobro;
	}

	/**
	 * Este método se encarga de recalculuar el saldo actual en moneda origen en
	 * base a los pagos parciales que pueda tener un documento en dólares, y
	 * tomando en cuenta lo respondido por Calipso al momento de simular la
	 * apropiación.
	 * 
	 * Porque? Porque Calipso cuando simula la apropiación de un documento en
	 * dólares para un cobro en pesos, donde Shiva envía un monto acumulado de
	 * simulación, Calipso utiliza el mismo tipo de cambio para tener en cuenta
	 * el importe acumulado a fin de verificar si puede o no apropiar saldo de
	 * ese documento. Esto del lado de Calipso es incorrecto.. la realidad es
	 * que debiera simular la apropiacion de los montos acumulados en la fecha
	 * que se simularon los pagos parciales anteriores, pero esta info no la
	 * tiene. Como esto del lado de Calipso implica bastante trabajo además de
	 * modificar la interfaz, se resuelve del lado de Shiva actualizando los
	 * saldos reales en moneda origen con la respuesta obtenida de la
	 * simulación.
	 * 
	 * En caso de que se detecte que se está apropiando un importe en moneda
	 * origen mayor al saldo actual en moneda origen, aparecerá un Warning a
	 * nivel débito indicando dicha situación
	 * 
	 * La lista de transacciones sin diferencia de cambio a recorrer, debe estar
	 * ordenada.
	 * 
	 * Compensaciones SAP: Si existe medio de pago "Saldo a Cobrar", la factura
	 * asociada en la transaccion no se simula, ni se imputa, ya que no se está
	 * apropiando realmente. Por lo tanto, no se debe tener en cuenta en el
	 * siguiente cálculo.
	 * 
	 * 05/04 - Actualización por defecto 1543: es necesario tener el saldo
	 * actual en moneda origen para la factura asociada al medio de pago
	 * "Saldo a Cobrar", por lo tanto si lo vamos a simular, pero solo eso. En
	 * la imputación sigue como hasta ahora, no se tendrá en cuenta.
	 * 
	 * @param cobro
	 * @return
	 */
	private ShvCobCobro calcularSaldoActualMonedaOrigen(ShvCobCobro cobro) {

		BigDecimal saldoActualMonedaOrigenFacturaPagoAnterior = null;
		BigDecimal importeAplicadoFechaEmisionMonedaOrigenFacturaPagoAnterior = null;
		String numeroLegalFacturaAnterior = null;

		Set<ShvCobTransaccion> listaTransaccionesSinDifCambio = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenSimulacionShvCobTransaccion());
		listaTransaccionesSinDifCambio.addAll(cobro.getOperacion().getTransaccionesSinDifCambio());

		for (ShvCobTransaccion transaccion : listaTransaccionesSinDifCambio) {

			if (!Validaciones.isObjectNull(transaccion.getFactura()) && !TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())) {

				if (transaccion.getFactura() instanceof ShvCobFacturaCalipso
				// 05/04 - Actualización
				// && !(transaccion.getMediosPago().iterator().next() instanceof
				// ShvCobMedioPagoSaldoACobrar)
				) {

					ShvCobFacturaCalipso facturaActual = (ShvCobFacturaCalipso) transaccion.getFactura();

					// Se valida que la factura a evaluar:
					// - no se encuentre en error
					// - que posea saldo actual en moneda origen
					// - que posea importe aplicado a fecha de emision

					boolean existeFacturaSinError = !TipoMensajeEstadoEnum.ERR.equals(facturaActual.getTipoMensajeEstado());
					boolean existeSaldoActualMonedaOrigen = !Validaciones.isObjectNull(facturaActual.getSaldoActualMonedaOrigen());
					boolean existeImporteAplicadoFechaEmisionMonedaOrigen = !Validaciones.isObjectNull(facturaActual.getImporteAplicadoAFechaEmisionMonedaOrigen());

					if (existeFacturaSinError && existeSaldoActualMonedaOrigen && existeImporteAplicadoFechaEmisionMonedaOrigen) {

						// Esta lógica solo aplicará a documentos en dólares
						// para cobros en pesos
						if (MonedaEnum.PES.equals(cobro.getMonedaOperacion()) && MonedaEnum.DOL.equals(((ShvCobFacturaCalipso) facturaActual).getMoneda())) {

							if (Validaciones.isObjectNull(numeroLegalFacturaAnterior)) {

								// No hay factura anterior... defino los datos
								// del corte de control

								// Saldo actual en moneda origen
								saldoActualMonedaOrigenFacturaPagoAnterior = facturaActual.getSaldoActualMonedaOrigen();

								// Importe aplicado a fecha emisión en moneda
								// origen
								importeAplicadoFechaEmisionMonedaOrigenFacturaPagoAnterior = facturaActual.getImporteAplicadoAFechaEmisionMonedaOrigen();

								// Numero Legar de documento
								numeroLegalFacturaAnterior = Utilidad.obtenerNumeroLegalFormateado(facturaActual);

							} else {

								// Hay datos de un pago anterior, comparo contra
								// el pago actual

								if (numeroLegalFacturaAnterior.equals(Utilidad.obtenerNumeroLegalFormateado(facturaActual))) {

									// Estoy con un pago parcial de la misma
									// factura, actualizo saldo actual
									facturaActual.setSaldoActualMonedaOrigen(saldoActualMonedaOrigenFacturaPagoAnterior.subtract(importeAplicadoFechaEmisionMonedaOrigenFacturaPagoAnterior));

									// Verifico si estoy tratando de cobrar mas
									// de lo que debiera
									if (facturaActual.getImporteAplicadoAFechaEmisionMonedaOrigen().compareTo(facturaActual.getSaldoActualMonedaOrigen()) > 0) {

										facturaActual.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);

										StringBuffer detalleMensaje = new StringBuffer("");

										// Si ya existe un mensaje actual,
										// preparo para contatenar el nuevo...
										if (!Validaciones.isObjectNull(facturaActual.getMensajeEstado()) && !Constantes.EMPTY_STRING.equals(facturaActual.getMensajeEstado().trim())) {
											detalleMensaje.append(facturaActual.getMensajeEstado());
											detalleMensaje.append("<br>");
										}

										// Informo al usuario el saldo máximo
										// que tiene para pagar a la fecha de
										// cobro configurada actualmente
										BigDecimal saldoActualPesificadoAFechaCobro = facturaActual.getSaldoActualMonedaOrigen().multiply(facturaActual.getTipoDeCambioFechaCobro());

										detalleMensaje.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
										detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
										detalleMensaje.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.warning.simulacion.montoMaximoDeImputacionSobrepasado"), Utilidad.formatCurrency(saldoActualPesificadoAFechaCobro, 2)));

										facturaActual.setMensajeEstado(detalleMensaje.toString());
									}

									// Actualizo los valores de corte de control
									// Saldo actual en moneda origen
									saldoActualMonedaOrigenFacturaPagoAnterior = facturaActual.getSaldoActualMonedaOrigen();

									// Importe aplicado a fecha emisión en
									// moneda origen
									importeAplicadoFechaEmisionMonedaOrigenFacturaPagoAnterior = facturaActual.getImporteAplicadoAFechaEmisionMonedaOrigen();

									// Numero Legar de documento
									numeroLegalFacturaAnterior = Utilidad.obtenerNumeroLegalFormateado(facturaActual);

								} else {

									numeroLegalFacturaAnterior = null;
								}
							}
						}

					} else {

						Traza.error(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Error/Warning en calcularSaldoActualMonedaOrigen.", String.valueOf(cobro.getOperacion().getIdOperacion())));
						Traza.error(CobroBatchSimulacionServicioImpl.class, "    Numero de transaccion: " + facturaActual.getTransaccion().getNumeroTransaccion());
						Traza.error(CobroBatchSimulacionServicioImpl.class, "                  Factura: " + Utilidad.obtenerNumeroLegalFormateado(facturaActual));
						Traza.error(CobroBatchSimulacionServicioImpl.class, "                           Saldo actual moneda origen: " + facturaActual.getSaldoActualMonedaOrigen());
						Traza.error(CobroBatchSimulacionServicioImpl.class, "       Importe aplicado a fecha emision moneda origen: " + facturaActual.getImporteAplicadoAFechaEmisionMonedaOrigen());
						Traza.error(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) El calculo de saldos no ha sido realizado por no tener info suficiente.", String.valueOf(cobro.getOperacion().getIdOperacion())));
						Traza.error(CobroBatchSimulacionServicioImpl.class, " ");

					}
				}

			} else {

				if (!Validaciones.isObjectNull(transaccion.getFactura())) {

					Traza.error(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) Error/Warning en calcularSaldoActualMonedaOrigen.", String.valueOf(cobro.getOperacion().getIdOperacion())));
					Traza.error(CobroBatchSimulacionServicioImpl.class, "    Numero de transaccion: " + transaccion.getNumeroTransaccion());
					Traza.error(CobroBatchSimulacionServicioImpl.class, "      Tipo mensaje estado: " + transaccion.getTipoMensajeEstado());
					Traza.error(CobroBatchSimulacionServicioImpl.class, "           Mensaje estado: " + transaccion.getMensajeEstado());
					Traza.error(CobroBatchSimulacionServicioImpl.class, Utilidad.reemplazarMensajes("(idOperacion: {0}) El calculo de saldos no ha sido realizado por no tener info suficiente.", String.valueOf(cobro.getOperacion().getIdOperacion())));
					Traza.error(CobroBatchSimulacionServicioImpl.class, " ");
				}
			}
		}

		return cobro;
	}

	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param tratamientoDiferencia
	 * @throws NegocioExcepcion
	 */
	private void actualizarTratamientoDiferneciaDeTransaccionPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobTratamientoDiferencia tratamientoDiferencia) throws NegocioExcepcion {

		//
		// Actualizo los resultados de las diferentes llamadas en el cobro
		//
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {

			if (tratamientoDiferencia.getTransaccion().getNumeroTransaccion().equals(resultadoSimulacion.getNumeroTransaccion())) {

				if (resultadoSimulacion instanceof ResultadoSimulacionGeneracionCargoCreditoCalipso) {

					ResultadoSimulacionGeneracionCargoCreditoCalipso resultadoGenCargoCreditoCalipso = (ResultadoSimulacionGeneracionCargoCreditoCalipso) resultadoSimulacion;

					actualizarInformacionMensajeEstado(resultadoGenCargoCreditoCalipso.getListaRespuestasInvocacion(), tratamientoDiferencia);

					if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
						tratamientoDiferencia.setCobradorInteresesBonificados(resultadoGenCargoCreditoCalipso.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						tratamientoDiferencia.setCobradorInteresesGenerados(resultadoGenCargoCreditoCalipso.getInteresesTrasladados());
						tratamientoDiferencia.setCobradorInteresesTrasladados(resultadoGenCargoCreditoCalipso.getInteresesTrasladados());
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionGeneracionCargoCreditoMic) {

					ResultadoSimulacionGeneracionCargoCreditoMic resultadoGenCargoCreditoMic = (ResultadoSimulacionGeneracionCargoCreditoMic) resultadoSimulacion;

					actualizarInformacionMensajeEstado(resultadoGenCargoCreditoMic.getListaRespuestasInvocacion(), tratamientoDiferencia);

					if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
						tratamientoDiferencia.setCobradorInteresesBonificados(resultadoGenCargoCreditoMic.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						tratamientoDiferencia.setCobradorInteresesGenerados(resultadoGenCargoCreditoMic.getInteresesTrasladados());
						tratamientoDiferencia.setCobradorInteresesTrasladados(resultadoGenCargoCreditoMic.getInteresesTrasladados());
					}

					if (resultadoGenCargoCreditoMic.isRequiereBuscarAcuerdoActivo()) {
						autoCompletarAcuerdoActivoMic(tratamientoDiferencia);
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionTratamientoDiferencia) {
					ResultadoSimulacionApropiacionTratamientoDiferencia resultadoApropTratamDif = (ResultadoSimulacionApropiacionTratamientoDiferencia) resultadoSimulacion;

					actualizarInformacionMensajeEstado(resultadoApropTratamDif.getListaRespuestasInvocacion(), tratamientoDiferencia);
				}
			}
		}
	}

	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param factura
	 * @throws NegocioExcepcion
	 */
	private Collection<ShvCobTransaccion> actualizarFacturasDeTransaccionPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobFactura factura, int cantTransaccionesActual) throws NegocioExcepcion {

		Collection<ShvCobTransaccion> nuevasTransaccionesPorDifCambio = null;
		//
		// Actualizo los resultados de las diferentes llamadas en el cobro
		//
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {

			if (factura.getTransaccion().getNumeroTransaccion().equals(resultadoSimulacion.getNumeroTransaccion())) {

				if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionFacturaCalipso) {
					ResultadoSimulacionApropiacionFacturaCalipso resultadoApropFacturaCalipso = (ResultadoSimulacionApropiacionFacturaCalipso) resultadoSimulacion;
					actualizarInformacionMensajeEstado(resultadoApropFacturaCalipso.getListaRespuestasInvocacion(), factura);

					if (!TipoMensajeEstadoEnum.ERR.equals(factura.getTipoMensajeEstado())) {

						ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;

						// Registro de factura original
						// Tipo de cambio al cobro: <tipoCambioFechaCobro>
						// Tipo de cambio a fecha de emisión: -- (sin Info)
						// Importe cobrado en moneda origen a fecha de emisión:
						// <importeAplicadoMonedaOrigen>
						facturaCalipso.setCobradorInteresesBonificados(resultadoApropFacturaCalipso.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						facturaCalipso.setCobradorInteresesGenerados(resultadoApropFacturaCalipso.getInteresesTrasladados());
						facturaCalipso.setCobradorInteresesTrasladados(resultadoApropFacturaCalipso.getInteresesTrasladados());

						facturaCalipso.setImporteAplicadoAFechaEmisionMonedaOrigen(resultadoApropFacturaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen());
						facturaCalipso.setImporteAplicadoAFechaEmisionMonedaPesos(resultadoApropFacturaCalipso.getImporteAplicadoAFechaEmisionMonedaPesos());
						facturaCalipso.setTipoDeCambioFechaCobro(resultadoApropFacturaCalipso.getTipoDeCambioFechaCobro());
						facturaCalipso.setTipoDeCambioFechaEmision(resultadoApropFacturaCalipso.getTipoDeCambioFechaEmision());

						cobroBatchServicio.actualizarTratamientoInteresesTransaccion(facturaCalipso.getTransaccion());

						if (!Validaciones.isObjectNull(resultadoApropFacturaCalipso.getNotaCreditoPorDiferenciaCambio())) {

							ShvCobMedioPagoNotaCreditoCalipso notaCredito = resultadoApropFacturaCalipso.getNotaCreditoPorDiferenciaCambio();

							// Le seteo a la nota de credito generada por
							// diferencia de cambio, el mismo cliente que la
							// factura que le da origen
							notaCredito.setIdClienteLegado(factura.getIdClienteLegado());
							notaCredito.setIdCobro(factura.getIdCobro());
							notaCredito.setIdDocumentoCuentaCobranzaPadre(facturaCalipso.getIdDocumentoCuentaCobranza());
							ShvCobTransaccion transaccionDifCambio = new ShvCobTransaccion();
							transaccionDifCambio.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM);
							transaccionDifCambio.setNumeroTransaccion(cantTransaccionesActual + 1);
							transaccionDifCambio.setNumeroTransaccionFicticio(cantTransaccionesActual + 1);
							transaccionDifCambio.setOperacion(factura.getTransaccion().getOperacion());
							transaccionDifCambio.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());
							transaccionDifCambio.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
							transaccionDifCambio.setGrupo(factura.getTransaccion().getGrupo());
							transaccionDifCambio.setIdSociedad(factura.getSociedad());
							transaccionDifCambio.setSistema(factura.getSistemaOrigen());
							transaccionDifCambio.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
							try {

								// Copio la factura, le seteo el importe
								// aplicado en Calipso, y la agrego a la nueva
								// transaccion
								ShvCobFacturaCalipso copiaFactura = (ShvCobFacturaCalipso) Utilidad.clonarObjeto(factura);
								copiaFactura.setIdFactura(null);
								copiaFactura.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
								copiaFactura.setImporteCobrar(copiaFactura.getImporteAplicadoAFechaEmisionMonedaPesos());
								copiaFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
								copiaFactura.setTransaccion(transaccionDifCambio);
								copiaFactura.setSociedad(SociedadEnum.TELECOM);

								// Limpio campos que no deben ser informados en
								// la copia de la Factura
								cobroBatchSoporteServicio.limpiarCamposCopiaDocumentoAplicacionPorDifCambio(copiaFactura);

								transaccionDifCambio.getShvCobFactura().add(copiaFactura);

								// Copio el/los medios de pago y los agrego a la
								// transaccion
								for (ShvCobMedioPago medioPagoOriginal : factura.getTransaccion().getShvCobMedioPago()) {
									ShvCobMedioPago copiaMedioPago = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoOriginal);

									// En el caso de que sea un medio de pago de
									// usuario, debo clonar la lista de clientes
									// de manera independiente, ya que el
									// metodo clonarObjeto no se banca clonar
									// objetos
									if (copiaMedioPago instanceof ShvCobMedioPagoUsuario) {

										Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>(0);
										for (ShvCobMedioPagoCliente clienteMedioPagoActual : ((ShvCobMedioPagoUsuario) medioPagoOriginal).getListaMedioPagoClientes()) {

											ShvCobMedioPagoCliente clienteMedioPago = new ShvCobMedioPagoCliente();
											clienteMedioPago.setIdClienteLegado(clienteMedioPagoActual.getIdClienteLegado());
											clienteMedioPago.setMedioPagoUsuario((ShvCobMedioPagoUsuario) copiaMedioPago);
											listaClientesMedioPago.add(clienteMedioPago);
										}
										((ShvCobMedioPagoUsuario) copiaMedioPago).setListaMedioPagoClientes(listaClientesMedioPago);
									}

									copiaMedioPago.setIdMedioPago(null);
									copiaMedioPago.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
									copiaMedioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
									copiaMedioPago.setTransaccion(transaccionDifCambio);
									transaccionDifCambio.getShvCobMedioPago().add(copiaMedioPago);
								}

								// Agrego la nota de credito generada en el
								// cobrador
								notaCredito.setTransaccion(transaccionDifCambio);
								transaccionDifCambio.getShvCobMedioPago().add(notaCredito);

								nuevasTransaccionesPorDifCambio = new ArrayList<ShvCobTransaccion>();
								nuevasTransaccionesPorDifCambio.add(transaccionDifCambio);

							} catch (Exception e) {
								throw new NegocioExcepcion(e);
							}
						}

						if (!Validaciones.isObjectNull(resultadoApropFacturaCalipso.getNotaDebitoPorDiferenciaCambio())) {

							try {
								nuevasTransaccionesPorDifCambio = new ArrayList<ShvCobTransaccion>();

								// Genero una transaccion que contendrá la nota
								// de debito
								ShvCobTransaccion transaccionDifCambioNotaDebitoGenerada = new ShvCobTransaccion();
								transaccionDifCambioNotaDebitoGenerada.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM);
								transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccion(cantTransaccionesActual + 1);
								transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionFicticio(cantTransaccionesActual + 1);
								transaccionDifCambioNotaDebitoGenerada.setOperacion(factura.getTransaccion().getOperacion());
								transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());
								transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
								transaccionDifCambioNotaDebitoGenerada.setGrupo(factura.getTransaccion().getGrupo());
								transaccionDifCambioNotaDebitoGenerada.setIdSociedad(factura.getSociedad());
								transaccionDifCambioNotaDebitoGenerada.setSistema(factura.getSistemaOrigen());
								transaccionDifCambioNotaDebitoGenerada.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
								
								ShvCobFacturaCalipso notaDebito = resultadoApropFacturaCalipso.getNotaDebitoPorDiferenciaCambio();
								// Le seteo a la nota de debito generada por
								// diferencia de cambio, el mismo cliente que la
								// factura que le da origen
								notaDebito.setIdClienteLegado(factura.getIdClienteLegado());

								notaDebito.setTransaccion(transaccionDifCambioNotaDebitoGenerada);
								notaDebito.setIdCobro(factura.getIdCobro());
								notaDebito.setIdDocumentoCuentaCobranzaPadre(facturaCalipso.getIdDocumentoCuentaCobranza());
								transaccionDifCambioNotaDebitoGenerada.getShvCobFactura().add(notaDebito);

								nuevasTransaccionesPorDifCambio.add(transaccionDifCambioNotaDebitoGenerada);

								// Genero una transaccion que contendrá una
								// copia de la transaccion original, cuya
								// factura tendrá el
								// importe modificado
								ShvCobTransaccion transaccionDifCambioFacturaOriginal = new ShvCobTransaccion();
								transaccionDifCambioFacturaOriginal.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM);
								transaccionDifCambioFacturaOriginal.setNumeroTransaccion(cantTransaccionesActual + 2);
								transaccionDifCambioFacturaOriginal.setNumeroTransaccionFicticio(cantTransaccionesActual + 2);
								transaccionDifCambioFacturaOriginal.setOperacion(factura.getTransaccion().getOperacion());
								transaccionDifCambioFacturaOriginal.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());	
								transaccionDifCambioFacturaOriginal.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());		
								transaccionDifCambioFacturaOriginal.setGrupo(factura.getTransaccion().getGrupo());
								transaccionDifCambioFacturaOriginal.setIdSociedad(factura.getSociedad());
								transaccionDifCambioFacturaOriginal.setSistema(factura.getSistemaOrigen());
								transaccionDifCambioFacturaOriginal.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
								
								// Copio la factura, le seteo el importe
								// aplicado en Calipso, y la agrego a la nueva
								// transaccion

								// Registro de cobro de factura
								// Tipo de cambio al cobro: -- (sin Info)
								// Tipo de cambio a fecha de emisión:
								// <tipoCambioFechaEmision>
								// Importe cobrado en moneda origen a fecha de
								// emisión: <importeAplicadoMonedaOrigen>

								ShvCobFacturaCalipso copiaFactura = (ShvCobFacturaCalipso) Utilidad.clonarObjeto(factura);
								copiaFactura.setIdFactura(null);
								copiaFactura.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
								copiaFactura.setImporteCobrar(copiaFactura.getImporteAplicadoAFechaEmisionMonedaPesos());
								copiaFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
								copiaFactura.setTransaccion(transaccionDifCambioFacturaOriginal);
								copiaFactura.setSociedad(SociedadEnum.TELECOM);

								// Limpio campos que no deben ser informados en
								// la copia de la Factura
								cobroBatchSoporteServicio.limpiarCamposCopiaDocumentoAplicacionPorDifCambio(copiaFactura);

								transaccionDifCambioFacturaOriginal.getShvCobFactura().add(copiaFactura);

								nuevasTransaccionesPorDifCambio.add(transaccionDifCambioFacturaOriginal);

								BigDecimal importeCopiaFacturaPendiente = copiaFactura.getImporteCobrar();
								BigDecimal importeNotaDebitoPendiente = notaDebito.getImporteCobrar();

								// Copio el/los medios de pago y los agrego a la
								// transaccion según corresponda
								for (ShvCobMedioPago medioPagoOriginal : factura.getTransaccion().getShvCobMedioPago()) {

									ShvCobMedioPago copiaMedioPago = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoOriginal);
									copiaMedioPago.setIdMedioPago(null);
									copiaMedioPago.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
									copiaMedioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);

									// En el caso de que sea un medio de pago de
									// usuario, debo clonar la lista de clientes
									// de manera independiente, ya que el
									// metodo clonarObjeto no se banca clonar
									// objetos
									if (copiaMedioPago instanceof ShvCobMedioPagoUsuario) {

										Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>(0);
										for (ShvCobMedioPagoCliente clienteMedioPagoActual : ((ShvCobMedioPagoUsuario) medioPagoOriginal).getListaMedioPagoClientes()) {

											ShvCobMedioPagoCliente clienteMedioPago = new ShvCobMedioPagoCliente();
											clienteMedioPago.setIdClienteLegado(clienteMedioPagoActual.getIdClienteLegado());
											clienteMedioPago.setMedioPagoUsuario((ShvCobMedioPagoUsuario) copiaMedioPago);
											listaClientesMedioPago.add(clienteMedioPago);
										}
										((ShvCobMedioPagoUsuario) copiaMedioPago).setListaMedioPagoClientes(listaClientesMedioPago);
									}

									// Completo primero los medios de pago de la
									// transaccion que contiene la
									// copia de la factura original
									if (!Validaciones.isObjectNull(importeCopiaFacturaPendiente) && importeCopiaFacturaPendiente.compareTo(BigDecimal.ZERO) != 0) {

										if (copiaMedioPago.getImporte().compareTo(importeCopiaFacturaPendiente) > 0) {

											// Debo partir el medio de pago
											ShvCobMedioPago copiaMedioPagoParcial = (ShvCobMedioPago) Utilidad.clonarObjeto(copiaMedioPago);

											// En el caso de que sea un medio de
											// pago de usuario, debo clonar la
											// lista de clientes de manera
											// independiente, ya que el
											// metodo clonarObjeto no se banca
											// clonar objetos
											if (copiaMedioPagoParcial instanceof ShvCobMedioPagoUsuario) {

												Set<ShvCobMedioPagoCliente> listaClientesMedioPagoParcial = new HashSet<ShvCobMedioPagoCliente>(0);
												for (ShvCobMedioPagoCliente clienteMedioPagoActual : ((ShvCobMedioPagoUsuario) copiaMedioPago).getListaMedioPagoClientes()) {

													ShvCobMedioPagoCliente clienteMedioPagoParcial = new ShvCobMedioPagoCliente();
													clienteMedioPagoParcial.setIdClienteLegado(clienteMedioPagoActual.getIdClienteLegado());
													clienteMedioPagoParcial.setMedioPagoUsuario((ShvCobMedioPagoUsuario) copiaMedioPagoParcial);
													listaClientesMedioPagoParcial.add(clienteMedioPagoParcial);
												}
												((ShvCobMedioPagoUsuario) copiaMedioPagoParcial).setListaMedioPagoClientes(listaClientesMedioPagoParcial);
											}

											copiaMedioPagoParcial.setImporte(importeCopiaFacturaPendiente);

											// Asigno la copia del medio de pago
											// parcial a la transaccion
											copiaMedioPagoParcial.setTransaccion(transaccionDifCambioFacturaOriginal);
											transaccionDifCambioFacturaOriginal.getShvCobMedioPago().add(copiaMedioPagoParcial);

											// Actualizo el importe del medio de
											// pago parcial que todavia queda
											// pendiente para uso
											copiaMedioPago.setImporte(copiaMedioPago.getImporte().subtract(importeCopiaFacturaPendiente));

											// Actualizo el importe de copia
											// factura pendiente de cubrir, a
											// cero.
											importeCopiaFacturaPendiente = BigDecimal.ZERO;

										} else {

											// Asigno la copia del medio de pago
											// parcial a la transaccion
											copiaMedioPago.setTransaccion(transaccionDifCambioFacturaOriginal);
											transaccionDifCambioFacturaOriginal.getShvCobMedioPago().add(copiaMedioPago);

											// Actualizo el importe de copia
											// factura pendiente.
											importeCopiaFacturaPendiente = importeCopiaFacturaPendiente.subtract(copiaMedioPago.getImporte());
										}
									}

									// Completo luego los medios de pago para la
									// transaccion que contiene la
									// nueva nota de debito
									if (!Validaciones.isObjectNull(importeCopiaFacturaPendiente) && importeCopiaFacturaPendiente.compareTo(BigDecimal.ZERO) == 0 && importeNotaDebitoPendiente.compareTo(BigDecimal.ZERO) != 0) {

										// Asigno la copia del medio de pago
										// parcial a la transaccion
										copiaMedioPago.setTransaccion(transaccionDifCambioNotaDebitoGenerada);
										transaccionDifCambioNotaDebitoGenerada.getShvCobMedioPago().add(copiaMedioPago);

										// Actualizo el importe de copia factura
										// pendiente.
										importeNotaDebitoPendiente = importeNotaDebitoPendiente.subtract(copiaMedioPago.getImporte());
									}
								}

							} catch (Exception e) {
								throw new NegocioExcepcion(e);
							}

						}

					} else if (resultadoApropFacturaCalipso.isRequiereHabilitaraSinDiferenciaCambio()) {
						ShvCobEdDebito debito;
						try {
							debito = cobroOnlineDebitoDao.buscarDebito(factura.getIdDebitoOrigen(), factura.getIdCobro());
							debito.setHabilitarEdicionSinDifCambio(SiNoEnum.SI);
							cobroOnlineDebitoDao.guardarDebito(debito);
						} catch (PersistenciaExcepcion e) {
							throw new NegocioExcepcion(e);
						}

					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionFacturaMic) {
					ResultadoSimulacionApropiacionFacturaMic resultadoApropFacturaMic = (ResultadoSimulacionApropiacionFacturaMic) resultadoSimulacion;
					actualizarInformacionMensajeEstado(resultadoApropFacturaMic.getListaRespuestasInvocacion(), factura);

					if (!TipoMensajeEstadoEnum.ERR.equals(factura.getTipoMensajeEstado())) {
						ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;

						facturaMic.setCobradorInteresesBonificadosRegulados(resultadoApropFacturaMic.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						facturaMic.setCobradorInteresesGenerados(resultadoApropFacturaMic.getInteresesTrasladados());
						facturaMic.setCobradorInteresesTrasladados(resultadoApropFacturaMic.getInteresesTrasladados());
					}

					if (resultadoApropFacturaMic.isRequiereBuscarAcuerdoActivo()) {
						autoCompletarAcuerdoActivoMic(factura);
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionDocumentoMicEnDeimos) {
					ResultadoSimulacionApropiacionDocumentoMicEnDeimos resultadoApropDocDeimos = (ResultadoSimulacionApropiacionDocumentoMicEnDeimos) resultadoSimulacion;

					if (factura instanceof ShvCobFacturaMic) {
						ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;

						if (facturaMic.getIdReferenciaFactura().equals(resultadoApropDocDeimos.getNumeroReferenciaMic())) {
							actualizarInformacionMensajeEstado(resultadoApropDocDeimos.getListaRespuestasInvocacion(), factura);
						}
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos) {
					ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos resultadoApropDocDeimos = (ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos) resultadoSimulacion;

					if (factura instanceof ShvCobFacturaCalipso) {
						ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;

						String idFacturaCalipso = facturaCalipso.getTipoComprobante().getIdTipoComprobante() + facturaCalipso.getClaseComprobante().name() + facturaCalipso.getSucursalComprobante() + facturaCalipso.getNumeroComprobante();

						if (idFacturaCalipso.equals(resultadoApropDocDeimos.getIdBusquedaRespuestaCobrador())) {
							actualizarInformacionMensajeEstado(resultadoApropDocDeimos.getListaRespuestasInvocacion(), factura);
						}
					}
				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionFacturaUsuario) {
					ResultadoSimulacionApropiacionFacturaUsuario resultadoApropFacturaUsuario = (ResultadoSimulacionApropiacionFacturaUsuario) resultadoSimulacion;

					if (factura instanceof ShvCobFacturaUsuario) {
						ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) factura;

						if (facturaUsuario.getIdFactura().equals(resultadoApropFacturaUsuario.getIdFactura())) {
							actualizarInformacionMensajeEstado(resultadoApropFacturaUsuario.getListaRespuestasInvocacion(), factura);
						}
					}
				}
			}
		}

		return nuevasTransaccionesPorDifCambio;
	}

	/**
	 * 
	 * @param factura
	 * @throws NegocioExcepcion
	 */
	private void autoCompletarAcuerdoActivoMic(Modelo destino) throws NegocioExcepcion {

		ShvCobTransaccion transaccion = null;

		if (destino instanceof ShvCobMedioPago) {
			ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
			transaccion = medioPago.getTransaccion();

		} else if (destino instanceof ShvCobFactura) {
			ShvCobFactura factura = (ShvCobFactura) destino;
			transaccion = factura.getTransaccion();

		} else if (destino instanceof ShvCobTratamientoDiferencia) {
			ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
			transaccion = tratamientoDiferencia.getTransaccion();
		}

		AcuerdoLegado acuerdo = buscarAcuerdoActivo(destino);

		// 26/04/2018 - Cambio por emergencia - (PI)
		// Debido a una necesidad en ambiente productivo de poder avanzar cobros
		// con bonificaciones
		// cuyo estado del acuerdo es no válido (debido a un error de origen),
		// seteamos el estado
		// de la transaccion como Warning a efectos de que el usuario pueda
		// imputar
		//
		// TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
		TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;

		String mensajeWarning = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.warning.simulacion.ningunClienteAcuerdoActivo");

		// Si ninguno posee acuerdo activo, se mostrará un mensaje indicando que
		// se debe agregar un nuevo cliente al cobro.
		if (!Validaciones.isObjectNull(acuerdo)) {

			if (destino instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
				ShvCobMedioPagoDebitoProximaFacturaMic medioPago = (ShvCobMedioPagoDebitoProximaFacturaMic) destino;

				medioPago.setAcuerdoTrasladoCargoOriginal(medioPago.getAcuerdoTrasladoCargo());
				medioPago.setAcuerdoTrasladoCargo(acuerdo.getNumero().toString());
				medioPago.setEstadoAcuerdoTrasladoCargo(acuerdo.getEstado());
				medioPago.setIdClienteLegadoAcuerdoTrasladoCargo(acuerdo.getIdClienteLegado());

			} else if (destino instanceof ShvCobFactura) {
				ShvCobFactura factura = (ShvCobFactura) destino;

				factura.setAcuerdoTrasladoCargoOriginal(factura.getAcuerdoTrasladoCargo());
				factura.setAcuerdoTrasladoCargo(acuerdo.getNumero().toString());
				factura.setEstadoAcuerdoTrasladoCargo(acuerdo.getEstado());
				factura.setIdClienteLegadoAcuerdoTrasladoCargo(acuerdo.getIdClienteLegado());

			} else if (destino instanceof ShvCobTratamientoDiferencia) {
				ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;

				tratamientoDiferencia.setAcuerdoTrasladoCargoOriginal(tratamientoDiferencia.getAcuerdoTrasladoCargo());
				tratamientoDiferencia.setAcuerdoTrasladoCargo(acuerdo.getNumero().toString());
				tratamientoDiferencia.setEstadoAcuerdoTrasladoCargo(acuerdo.getEstado());
				tratamientoDiferencia.setIdClienteLegadoAcuerdoTrasladoCargo(acuerdo.getIdClienteLegado());
			}

			tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;
			mensajeWarning = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.warning.simulacion.sugiereNuevoAcuerdo");
		}

		StringBuffer detalleMensaje = new StringBuffer();

		//
		// Error detectado en PI, que no seteaba correctamente el estado de la
		// transaccion a error, y la simulacion quedaba OK
		// Cuando se decida implementar, se deben descomentar estas líneas
		if (TipoMensajeEstadoEnum.ERR.equals(tipoMensajeEstado)) {
			transaccion.setTipoMensajeEstado(tipoMensajeEstado);
		}

		if (!TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())) {
			transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
		}

		if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
			if (!Constantes.EMPTY_STRING.equals(transaccion.getMensajeEstado().trim())) {
				detalleMensaje.append("<br>");
			}
		}

		detalleMensaje.append(tipoMensajeEstado.getDescripcion());
		detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
		detalleMensaje.append(mensajeWarning);

		if (!Validaciones.isObjectNull(transaccion.getMensajeEstado())) {
			if (!transaccion.getMensajeEstado().contains(mensajeWarning)) {
				transaccion.setMensajeEstado(transaccion.getMensajeEstado() + detalleMensaje);
			}
		} else {
			transaccion.setMensajeEstado(detalleMensaje.toString());
		}
	}

	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param medioPago
	 * @throws NegocioExcepcion
	 */
	private void actualizarMediosPagoDeTransaccionPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobMedioPago medioPago) throws NegocioExcepcion {

		//
		// Actualizo los resultados de las diferentes llamadas en el cobro
		//
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {

			if (medioPago.getTransaccion().getNumeroTransaccion().equals(resultadoSimulacion.getNumeroTransaccion())) {

				if (resultadoSimulacion instanceof ResultadoSimulacionGeneracionCargoDebitoCalipso && medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

					ResultadoSimulacionGeneracionCargoDebitoCalipso resultadoGeneracionCargoDebitoCalipso = (ResultadoSimulacionGeneracionCargoDebitoCalipso) resultadoSimulacion;
					actualizarInformacionMensajeEstado(resultadoGeneracionCargoDebitoCalipso.getListaRespuestasInvocacion(), medioPago);

					if (!TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
						ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProxFactCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;

						debitoProxFactCalipso.setCobradorInteresesBonificados(resultadoGeneracionCargoDebitoCalipso.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						debitoProxFactCalipso.setCobradorIntereseGenerados(resultadoGeneracionCargoDebitoCalipso.getInteresesTrasladados());
						debitoProxFactCalipso.setCobradorInteresesTrasladados(resultadoGeneracionCargoDebitoCalipso.getInteresesTrasladados());
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionGeneracionCargoDebitoMic && medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					ResultadoSimulacionGeneracionCargoDebitoMic resultadoGenCargoCreditoCalipso = (ResultadoSimulacionGeneracionCargoDebitoMic) resultadoSimulacion;

					actualizarInformacionMensajeEstado(resultadoGenCargoCreditoCalipso.getListaRespuestasInvocacion(), medioPago);

					if (!TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
						ShvCobMedioPagoDebitoProximaFacturaMic debitoProxFactMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;

						debitoProxFactMic.setCobradorInteresesBonificados(resultadoGenCargoCreditoCalipso.getInteresesBonificados());
						// Las llamadas en la simulación se hacen con
						// tratamiento de intereses = trasladar, por lo que la
						// cantidad de
						// intereses generados e intereses trasladados será la
						// misma siempre
						debitoProxFactMic.setCobradorInteresesGenerados(resultadoGenCargoCreditoCalipso.getInteresesTrasladados());
						debitoProxFactMic.setCobradorInteresesTrasladados(resultadoGenCargoCreditoCalipso.getInteresesTrasladados());
					}

					if (resultadoGenCargoCreditoCalipso.isRequiereBuscarAcuerdoActivo()) {
						autoCompletarAcuerdoActivoMic(medioPago);
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoCalipso || resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoMic || resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoShiva || resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoUsuario) {

					if (!(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) && !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) && medioPago.getIdBusquedaRespuestaCobrador().equals(resultadoSimulacion.getIdBusquedaRespuestaCobrador())) {

						// Dolares
						if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionMedioPagoCalipso) {

							ResultadoSimulacionApropiacionMedioPagoCalipso resultadoSimulacionMPClp = (ResultadoSimulacionApropiacionMedioPagoCalipso) resultadoSimulacion;

							if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
								ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoClp = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
								medioPagoNotaCreditoClp.setTipoDeCambioFechaEmision(resultadoSimulacionMPClp.getTipoCambioFechaEmision());
								medioPagoNotaCreditoClp.setTipoDeCambioFechaCobro(resultadoSimulacionMPClp.getTipoCambioFechaCobro());
								medioPagoNotaCreditoClp.setImporteAplicadoAFechaEmisionMonedaPesos(resultadoSimulacionMPClp.getImporteAplicadoFechaEmisionPesos());
								medioPagoNotaCreditoClp.setImporteAplicadoAFechaEmisionMonedaOrigen(resultadoSimulacionMPClp.getImporteAplicadoMonedaOrigen());
							}

							if (medioPago instanceof ShvCobMedioPagoCTA) {
								ShvCobMedioPagoCTA medioPagoCtaClp = (ShvCobMedioPagoCTA) medioPago;
								medioPagoCtaClp.setTipoDeCambioFechaEmision(resultadoSimulacionMPClp.getTipoCambioFechaEmision());
								medioPagoCtaClp.setTipoDeCambioFechaCobro(resultadoSimulacionMPClp.getTipoCambioFechaCobro());
								medioPagoCtaClp.setImporteAplicadoAFechaEmisionMonedaPesos(resultadoSimulacionMPClp.getImporteAplicadoFechaEmisionPesos());
								medioPagoCtaClp.setImporteAplicadoAFechaEmisionMonedaOrigen(resultadoSimulacionMPClp.getImporteAplicadoMonedaOrigen());
								// medioPagoCtaClp.setMoneda(resultadoSimulacionMPClp.getMoneda());
							}

						}

						actualizarInformacionMensajeEstado(resultadoSimulacion.getListaRespuestasInvocacion(), medioPago);
					}
				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionDocumentoMicEnDeimos) {
					ResultadoSimulacionApropiacionDocumentoMicEnDeimos rs = (ResultadoSimulacionApropiacionDocumentoMicEnDeimos) resultadoSimulacion;

					if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic && medioPago.getIdBusquedaRespuestaCobrador().equals(rs.getIdBusquedaRespuestaCobrador())) {
						actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);
					}
				} else if (resultadoSimulacion instanceof ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos) {
					ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos rs = (ResultadoSimulacionApropiacionDocumentoCalipsoEnDeimos) resultadoSimulacion;

					if ((medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso || medioPago instanceof ShvCobMedioPagoCTA) && medioPago.getIdBusquedaRespuestaCobrador().equals(rs.getIdBusquedaRespuestaCobrador())) {

						actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);
					}
				}

			} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor && resultadoSimulacion instanceof ResultadoSimulacionVerificarPartidasSap) {

				ResultadoSimulacionVerificarPartidasSap rs = (ResultadoSimulacionVerificarPartidasSap) resultadoSimulacion;
				actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);

				ShvCobMedioPagoCompensacionProveedor medioPagoCompensacionProveedor = (ShvCobMedioPagoCompensacionProveedor) medioPago;
				medioPagoCompensacionProveedor.getDocumentoCap().setResultado(new HashSet<ShvCobMedioPagoDocumentoCapResultado>());

				for (ShvCobMedioPagoDocumentoCapResultado documentoCapResultado : rs.getListaDocumentoCapResultado()) {
					documentoCapResultado.setDocumentoCap(medioPagoCompensacionProveedor.getDocumentoCap());
					medioPagoCompensacionProveedor.getDocumentoCap().getResultado().add(documentoCapResultado);
				}

			} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto && resultadoSimulacion instanceof ResultadoSimulacionVerificarPartidasSap) {

				ResultadoSimulacionVerificarPartidasSap rs = (ResultadoSimulacionVerificarPartidasSap) resultadoSimulacion;
				actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);

				ShvCobMedioPagoCompensacionLiquidoProducto medioPagoCompensacionLiquidoProducto = (ShvCobMedioPagoCompensacionLiquidoProducto) medioPago;
				medioPagoCompensacionLiquidoProducto.getDocumentoCap().setResultado(new HashSet<ShvCobMedioPagoDocumentoCapResultado>());

				for (ShvCobMedioPagoDocumentoCapResultado documentoCapResultado : rs.getListaDocumentoCapResultado()) {
					documentoCapResultado.setDocumentoCap(medioPagoCompensacionLiquidoProducto.getDocumentoCap());
					medioPagoCompensacionLiquidoProducto.getDocumentoCap().getResultado().add(documentoCapResultado);
				}
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor && resultadoSimulacion instanceof ResultadoSimulacionConsultarProveedorSap) {

				ResultadoSimulacionConsultarProveedorSap rs = (ResultadoSimulacionConsultarProveedorSap) resultadoSimulacion;
				actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);

				ShvCobMedioPagoCompensacionProveedor medioPagoCompensacionProveedor = (ShvCobMedioPagoCompensacionProveedor) medioPago;
				medioPagoCompensacionProveedor.getDocumentoCap().setResultado(new HashSet<ShvCobMedioPagoDocumentoCapResultado>());

				for (ShvCobMedioPagoDocumentoCapResultado documentoCapResultado : rs.getListaDocumentoCapResultado()) {
					documentoCapResultado.setDocumentoCap(medioPagoCompensacionProveedor.getDocumentoCap());
					medioPagoCompensacionProveedor.getDocumentoCap().getResultado().add(documentoCapResultado);
				}

			} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto && resultadoSimulacion instanceof ResultadoSimulacionConsultarProveedorSap) {

				ResultadoSimulacionConsultarProveedorSap rs = (ResultadoSimulacionConsultarProveedorSap) resultadoSimulacion;
				actualizarInformacionMensajeEstado(rs.getListaRespuestasInvocacion(), medioPago);

				ShvCobMedioPagoCompensacionLiquidoProducto medioPagoCompensacionLiquidoProducto = (ShvCobMedioPagoCompensacionLiquidoProducto) medioPago;
				medioPagoCompensacionLiquidoProducto.getDocumentoCap().setResultado(new HashSet<ShvCobMedioPagoDocumentoCapResultado>());

				for (ShvCobMedioPagoDocumentoCapResultado documentoCapResultado : rs.getListaDocumentoCapResultado()) {
					documentoCapResultado.setDocumentoCap(medioPagoCompensacionLiquidoProducto.getDocumentoCap());
					medioPagoCompensacionLiquidoProducto.getDocumentoCap().getResultado().add(documentoCapResultado);
				}
			}
		}
	}

	/**
	 * 
	 * @param listaRespuestasInvocacion
	 * @param destino
	 */
	private void actualizarInformacionMensajeEstado(List<RespuestaInvocacion> listaRespuestasInvocacion, Modelo destino) {

		StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);
		TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.OK;

		for (RespuestaInvocacion respuesta : listaRespuestasInvocacion) {

			// El resultado Warning tiene mas peso que el OK
			if (tipoMensajeEstado.equals(TipoMensajeEstadoEnum.OK) && respuesta.getResultado().equals(TipoResultadoSimulacionEnum.WARNING)) {
				tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;
			}

			// El resultado Error tiene mas peso que el OK o el Warning
			if ((tipoMensajeEstado.equals(TipoMensajeEstadoEnum.OK) || tipoMensajeEstado.equals(TipoMensajeEstadoEnum.WRN)) && (respuesta.getResultado().equals(TipoResultadoSimulacionEnum.ERROR) || respuesta.getResultado().equals(TipoResultadoSimulacionEnum.NOK))) {
				tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
			}

			if (TipoResultadoSimulacionEnum.ERROR_SERVICIO.equals(respuesta.getResultado())) {
				tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
			}

			if (!respuesta.getResultado().equals(TipoResultadoSimulacionEnum.OK)) {
				if (!Constantes.EMPTY_STRING.equals(detalleMensaje.toString().trim())) {
					detalleMensaje.append("<br>");
				}
				if (!Validaciones.isObjectNull(respuesta.getDescripcionError()) && !Validaciones.isNullEmptyOrDash(respuesta.getDescripcionError().toString()) && !ConstantesCobro.CODIGO_ERROR_9999.equals(respuesta.getCodigoError())) {

					// Esto lo hacemos en el caso de que se concatenen mensajes,
					// respetar el tipo de mensaje de los
					// mensajes posteriores
					if (!Constantes.EMPTY_STRING.equals(detalleMensaje.toString().trim())) {
						detalleMensaje.append(respuesta.getResultado().descripcion());
					} else {
						detalleMensaje.append(tipoMensajeEstado.getDescripcion());
					}
					detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
					detalleMensaje.append(respuesta.getDescripcionError().toString().trim());
				}
			}
		}

		if (destino instanceof ShvCobMedioPago) {
			ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
			medioPago.setMensajeEstado(detalleMensaje.toString());
			medioPago.setTipoMensajeEstado(tipoMensajeEstado);
		} else if (destino instanceof ShvCobFactura) {
			ShvCobFactura factura = (ShvCobFactura) destino;
			factura.setMensajeEstado(detalleMensaje.toString());
			factura.setTipoMensajeEstado(tipoMensajeEstado);
		} else if (destino instanceof ShvCobTratamientoDiferencia) {
			ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
			tratamientoDiferencia.setMensajeEstado(detalleMensaje.toString());
			tratamientoDiferencia.setTipoMensajeEstado(tipoMensajeEstado);
		}
	}

	/**
	 * Se realizar las siguientes validaciones al simular apropiacion de Shiva
	 * <<VAL>> Validar que el documento no haya sido cobrado/usado en su
	 * totalidad <<VAL>> Validar que el documento todavía tenga un saldo
	 * disponible para poder aplicar el monto de la imputación y que el cobro no
	 * esté en proceso. <<VAL>> Validar que el estado del documento sea
	 * gestionable <<VAL>> Si el documento es de SHIVA, validar que tenga un
	 * motivo gestionable <<VAL>> Validar que el documento no esté incluido en
	 * una operación en proceso
	 * 
	 * @param listaMediosPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularApropiacionShiva(List<ShvCobMedioPagoShiva> listaMediosPago) throws NegocioExcepcion {

		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		List<Long> listaIdValores = new ArrayList<Long>();

		for (ShvCobMedioPago medioPago : listaMediosPago) {
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				listaIdValores.add(((ShvCobMedioPagoShiva) medioPago).getIdValor());
			}
		}

		// Busco los valores asociados a mis medios de pago
		List<ShvValValorSimplificado> listaValoresSimplificados = valorServicio.listarValoresSimplificados(listaIdValores);

		// Ahora tengo que recorrer nuevamente los medios de pago
		// y comparar el importe a cobrar + saldo acumulado de simulacion contra
		// el saldo del valor correspondiente
		if (Validaciones.isCollectionNotEmpty(listaIdValores)) {

			for (ShvCobMedioPago medioPago : listaMediosPago) {
				if (medioPago instanceof ShvCobMedioPagoShiva) {

					for (ShvValValorSimplificado valorSimplificado : listaValoresSimplificados) {

						if (valorSimplificado.getIdValor().equals(((ShvCobMedioPagoShiva) medioPago).getIdValor())) {

							List<RespuestaInvocacion> listaRespuestaInvocacion = new ArrayList<RespuestaInvocacion>();

							// <<VAL>> Validar que el documento no haya sido
							// cobrado/usado en su totalidad
							if (Estado.VAL_USADO.equals(valorSimplificado.getWorkFlow().getEstado())) {

								String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.apropiacion.validacion.medioPagoCobradoUsado");

								RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.ERROR, Constantes.ERROR_GENERICO_APROPIACION, mensajeError);

								listaRespuestaInvocacion.add(respuesta);
							}

							// <<VAL>> Validar que el documento todavía tenga un
							// saldo disponible para poder aplicar el monto de
							// la imputación y que el cobro no esté en proceso.

							try {
								cobroBatchSoporteServicio.validarSaldoYDisponibilidad(valorSimplificado.getIdValor(), medioPago.getImporte());

							} catch (SimulacionCobroExcepcion e) {

								RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.ERROR, Constantes.ERROR_GENERICO_APROPIACION, e.getMensajeAuxiliar());

								listaRespuestaInvocacion.add(respuesta);
							}

							// <<VAL>> Validar que el estado del documento sea
							// gestionable
							if (!Estado.VAL_DISPONIBLE.equals(valorSimplificado.getWorkFlow().getEstado())) {

								String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.apropiacion.validacion.medioPagoEstadoNoGestionable"), valorSimplificado.getWorkFlow().getEstado().descripcion());

								RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.ERROR, Constantes.ERROR_GENERICO_APROPIACION, mensajeError);

								listaRespuestaInvocacion.add(respuesta);
							}

							// <<VAL>> Si el documento es de SHIVA, validar que
							// tenga un motivo gestionable
							// No puede ser Valor en Garantia o Valor por
							// reclamo de facturacion
							if (!Validaciones.isObjectNull(valorSimplificado.getMotivo()) && (MotivoShivaEnum.VALOR_EN_GARANTIA.idMotivo().equals(valorSimplificado.getMotivo().getIdMotivo().toString()) || MotivoShivaEnum.VALOR_POR_RECLAMO_DE_FACTURACION.idMotivo().equals(valorSimplificado.getMotivo().getIdMotivo().toString()))) {

								String mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.apropiacion.validacion.medioPagoMotivoNoGestionable"), valorSimplificado.getMotivo().getDescripcion());

								RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.ERROR, Constantes.ERROR_GENERICO_APROPIACION, mensajeError);

								listaRespuestaInvocacion.add(respuesta);
							}

							// <<VAL>> Validar que el documento no esté incluido
							// en una operación en proceso
							// cobros.error.simulacion.apropiacion.validacion.medioPagoEnOperacionEnProceso=El
							// documento está incluido en una operación en
							// proceso: {0}

							//
							// try {
							// List<Long> listaOperacionesCobroEnProceso =
							// cobroDao.listarOperacionesDeCobroEnProcesoPorIdValor(valorSimplificado.getIdValor());
							//
							// if (!listaOperacionesCobroEnProceso.isEmpty()) {
							//
							// String mensajeError =
							// Utilidad.reemplazarMensajes(
							// Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.simulacion.apropiacion.validacion.medioPagoEnOperacionEnProceso"),
							// Utilidad.rellenarCerosIzquierda(listaOperacionesCobroEnProceso.get(0).toString(),
							// 7));
							//
							// RespuestaInvocacion respuesta = new
							// RespuestaInvocacion(
							// TipoResultadoSimulacionEnum.ERROR,
							// Constantes.ERROR_GENERICO_APROPIACION,
							// mensajeError);
							//
							// listaRespuestaInvocacion.add(respuesta);
							// }
							//
							// } catch (PersistenciaExcepcion e) {
							// throw new NegocioExcepcion(e);
							// }
							//
							//
							// Genero la respuesta del resultado de la
							// apropiacion
							//
							ResultadoSimulacionApropiacionMedioPagoShiva resultadoSimulacion = new ResultadoSimulacionApropiacionMedioPagoShiva();
							resultadoSimulacion.setIdMedioPago(medioPago.getIdMedioPago());
							resultadoSimulacion.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());

							// Si las validaciones fueron todas corretas,
							// entonces debo generar una respuesta de OK
							if (!Validaciones.isCollectionNotEmpty(listaRespuestaInvocacion)) {

								RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.OK, null, null);

								listaRespuestaInvocacion.add(respuesta);
							}
							resultadoSimulacion.setListaRespuestasInvocacion(listaRespuestaInvocacion);

							listaResultadoSimulacion.add(resultadoSimulacion);
						}
					}
				}
			}
		}

		return listaResultadoSimulacion;
	}

	/**
	 * 
	 * @param listaMediosPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularApropiacionMedioPagoUsuario(List<ShvCobMedioPagoUsuario> listaMediosPago) throws NegocioExcepcion {

		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		for (ShvCobMedioPago medioPago : listaMediosPago) {

			if (medioPago instanceof ShvCobMedioPagoUsuario) {

				//
				// Genero la respuesta del resultado de la apropiacion, esto
				// siempre va a dar ok
				//
				ResultadoSimulacionApropiacionMedioPagoUsuario resultadoSimulacion = new ResultadoSimulacionApropiacionMedioPagoUsuario();
				resultadoSimulacion.setIdMedioPago(medioPago.getIdMedioPago());
				resultadoSimulacion.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());

				RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.OK, null, null);

				resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);

				// Agrego este resultado a la lista general
				listaResultadoSimulacion.add(resultadoSimulacion);
			}
		}

		return listaResultadoSimulacion;
	}

	/**
	 * 
	 * @param listaFacturaUsuario
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularApropiacionFacturaUsuario(
			ShvCobFacturaUsuario facturaUsuario)
			throws NegocioExcepcion {

		List <ResultadoSimulacion> resultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		

			if (!Validaciones.isObjectNull(facturaUsuario)) {

				//
				// Genero la respuesta del resultado de la apropiacion, esto
				// siempre va a dar ok
				//
				ResultadoSimulacionApropiacionFacturaUsuario resultadoSimulacionUsuario = new ResultadoSimulacionApropiacionFacturaUsuario();
				resultadoSimulacionUsuario.setIdFactura(facturaUsuario.getIdFactura());
				resultadoSimulacionUsuario.setIdOperacionShiva(facturaUsuario
						.getTransaccion().getOperacionTransaccionFormateado());

				RespuestaInvocacion respuesta = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.OK, null, null);

				resultadoSimulacionUsuario.getListaRespuestasInvocacion().add(
						respuesta);

				// Agrego este resultado a la lista general
				resultadoSimulacion.add(resultadoSimulacionUsuario);
			}
		

		return resultadoSimulacion;
	}

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ResultadoSimulacion simularApropiacionTratamientoDiferencia(ShvCobTratamientoDiferencia tratamientoDiferencia) throws NegocioExcepcion {

		//
		// Genero la respuesta del resultado de la apropiacion, esto siempre va
		// a dar ok
		//
		ResultadoSimulacionApropiacionTratamientoDiferencia resultadoSimulacion = new ResultadoSimulacionApropiacionTratamientoDiferencia();
		resultadoSimulacion.setIdTratamientoDiferencia(tratamientoDiferencia.getIdTratamientoDiferencia());
		resultadoSimulacion.setIdOperacionShiva(tratamientoDiferencia.getTransaccion().getOperacionTransaccionFormateado());

		RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.OK, null, null);

		resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);

		return resultadoSimulacion;
	}

	/**
	 * 
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private EntradaCalipsoApropiacionWS prepararSimulacionApropiacionCalipso(List<ShvCobMedioPago> listaMediosPagoAEnviar, ShvCobFacturaCalipso factura, ShvCobCobro cobro, Date fechaCobranza) throws NegocioExcepcion {

		EntradaCalipsoApropiacionWS eEntrada = new EntradaCalipsoApropiacionWS();
		eEntrada.setIdOperacion(cobro.getOperacion().getIdOperacion());
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		eEntrada.setModoOperacion(SiNoEnum.SI);
		eEntrada.setMonedaOperacion(cobro.getMonedaOperacion());
		eEntrada.setFechaCobranza(fechaCobranza);

		if (!Validaciones.isObjectNull(factura) && Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)) {
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP_SIM);
		} else if (Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)) {
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_MP_SIM);
		} else {
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA_SIM);
		}

		//
		// Mapeo la factura
		//
		if (!Validaciones.isObjectNull(factura)) {

			eEntrada.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			eEntrada.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());

			// Armo mensaje apropiacion con factura
			DetalleFactura detalleFactura = new DetalleFactura();
			IdDocumento idDocumento = new IdDocumento();
			idDocumento.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(factura.getTipoComprobante().getIdTipoComprobante()));
			idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(factura.getClaseComprobante())));
			idDocumento.setSucursalComprobante(factura.getSucursalComprobante());
			idDocumento.setNumeroComprobante(factura.getNumeroComprobante());

			detalleFactura.setIdDocumento(idDocumento);
			detalleFactura.setMontoACancelarEnPesos(factura.getImporteCobrar());

			// Si la factura es de Calipso, el “Pago Parcial Cancelatorio” se
			// enviará como “Pago Total”,
			// siempre y cuando la moneda del cobro se corresponda con la moneda
			// del documento.
			// Caso contrario, el tipo de pago se calcula en base a la respuesta
			// del servicio, y en esta llamada se enviará vacío.

			if (cobro.getMonedaOperacion().equals(factura.getMoneda())) {
				if (TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO.equals(factura.getTipoPago())) {
					detalleFactura.setTipoOperacion(TipoPagoEnum.PAGO_TOTAL);
				} else {
					detalleFactura.setTipoOperacion(factura.getTipoPago());
				}
			}

			detalleFactura.setTipoMora(factura.getQueHacerConIntereses().getCodigoCalipsoSimulacion());
			detalleFactura.setImporteBonificacionIntereses(factura.getImporteBonificacionIntereses());
			detalleFactura.setAcuerdoFacturacion(factura.getAcuerdoTrasladoCargo());

			// Solo voy a enviar el monto acumulado de simulacion siempre que la
			// moneda del cobro
			// sea la misma que la moneda del documento
			if (cobro.getMonedaOperacion().equals(factura.getMoneda())) {
				detalleFactura.setMontoAcumuladoSimulacion(factura.getMontoAcumuladoSimulacion());
			} else {
				detalleFactura.setMontoAcumuladoSimulacion(BigDecimal.ZERO);
			}
			detalleFactura.setAccionSobreDiferenciaDeCambio(AccionesSobreDiferenciaDeCambioEnum.NA);
			eEntrada.setDetalleFactura(detalleFactura);

		}

		if (Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)) {

			List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();

			for (ShvCobMedioPago medioPago : listaMediosPagoAEnviar) {

				eEntrada.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion());
				eEntrada.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());

				DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();

				IdDocumento idDocumento = new IdDocumento();

				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
					ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;

					idDocumento.setTipoComprobante(TipoComprobanteEnum.CRE);
					idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoNotaCreditoCalipso.getClaseComprobante())));
					idDocumento.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
					idDocumento.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());

				} else {
					if (medioPago instanceof ShvCobMedioPagoCTA) {
						ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;

						idDocumento.setTipoComprobante(TipoComprobanteEnum.CTA);
						idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoCTA.getClaseComprobante())));
						idDocumento.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
						idDocumento.setNumeroComprobante(medioPagoCTA.getNroComprobante());
					}
				}

				detalleNotaCredito.setImporte(medioPago.getImporte());
				detalleNotaCredito.setIdDocumento(idDocumento);
				// Solo voy a enviar el monto acumulado de simulacion siempre
				// que la moneda del cobro
				// sea la misma que la moneda del documento
				if (cobro.getMonedaOperacion().equals(medioPago.getMoneda())) {
					detalleNotaCredito.setMontoAcumuladoSimulacion(medioPago.getMontoAcumuladoSimulacion());
				} else {
					detalleNotaCredito.setMontoAcumuladoSimulacion(BigDecimal.ZERO);
				}
				listaCtaONotaCredito.add(detalleNotaCredito);

			}

			eEntrada.setListaCtaONotaCredito(listaCtaONotaCredito);
		}

		return eEntrada;
	}

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	private EntradaCalipsoCargosWS prepararSimulacionGeneracionCargosCreditoCalipso(

	ShvCobTratamientoDiferencia tratamientoDiferencia) throws NegocioExcepcion {

		if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {

			EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();

			entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion().toString(), 7));
			entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(tratamientoDiferencia.getTransaccion().getNumeroTransaccion().toString(), 5));
			entrada.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion().toString());
			entrada.setTipoMensaje(MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO_SIM);

			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
			entrada.setModoOperacion(SiNoEnum.SI);

			DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
			detalleCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoTrasladoCargo());
			detalleCargo.setImporte(tratamientoDiferencia.getImporte());
			detalleCargo.setFechaDesde(tratamientoDiferencia.getFechaTramiteReintegro());
			detalleCargo.setTipoMora(TipoMoraEnum.SIMULAR);
			detalleCargo.setImporteBonificacionIntereses(tratamientoDiferencia.getImporteBonificacionIntereses());
			detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
			detalleCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
			detalleCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
			// Cobros Backlog - Envio la moneda del reintegro
			detalleCargo.setMonedaCargo(tratamientoDiferencia.getMoneda());
			entrada.setDetalleCargo(detalleCargo);

			return entrada;
		}
		return null;
	}

	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	public EntradaCalipsoCargosWS prepararSimulacionGeneracionCargosDebitoCalipso(ShvCobMedioPagoDebitoProximaFacturaCalipso medioPago) throws NegocioExcepcion {

		EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();

		entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(medioPago.getTransaccion().getOperacion().getIdOperacion().toString(), 7));
		entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(medioPago.getTransaccion().getNumeroTransaccion().toString(), 5));
		entrada.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().toString());
		entrada.setTipoMensaje(MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO_SIM);

		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		entrada.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
		entrada.setModoOperacion(SiNoEnum.SI);

		DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
		detalleCargo.setAcuerdoFacturacion(medioPago.getAcuerdoTrasladoCargo());
		detalleCargo.setImporte(medioPago.getImporte());
		detalleCargo.setFechaDesde(medioPago.getFechaVencimientoFactura());
		detalleCargo.setTipoMora(TipoMoraEnum.SIMULAR);
		detalleCargo.setImporteBonificacionIntereses(medioPago.getImporteBonificacionIntereses());
		detalleCargo.setOrigenCargo(medioPago.getOrigenCargo());
		detalleCargo.setLeyendaFacturaCargo(medioPago.getLeyendaFacturaCargo());
		detalleCargo.setLeyendaFacturaInteres(medioPago.getLeyendaFacturaInteres());
		// Cobros Backlog - Envio la moneda del debito a prox
		detalleCargo.setMonedaCargo(medioPago.getMoneda());
		entrada.setDetalleCargo(detalleCargo);

		return entrada;
	}

	/**
	 * 
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private MicTransaccionADCDto prepararSimulacionApropiacionMic(List<ShvCobMedioPago> listaMediosPagoAEnviar, ShvCobFacturaMic factura, ShvCobCobro cobro) throws NegocioExcepcion {

		MicTransaccionADCDto mensajeMIC = null;

		if (!Validaciones.isObjectNull(factura) && Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)) {

			// Armo mensaje apropiacion con factura y Medios de pago
			mensajeMIC = new MicApropiacionDeudaMPDto();
			mensajeMIC.setIdOperacion(factura.getTransaccion().getOperacion().getIdOperacion());
			mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
			
			mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$03);
			mensajeMIC.setModoEjecucion(SiNoEnum.SI);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

			cargarFacturaAMensaje(factura, mensajeMIC);

			cargarMediosPagoMensajeMic(listaMediosPagoAEnviar, mensajeMIC);

			return mensajeMIC;

		} else if (!Validaciones.isObjectNull(factura)) {

			// Armo mensaje apropiacion con factura
			mensajeMIC = new MicApropiacionDeudaDto();
			mensajeMIC.setIdOperacion(factura.getTransaccion().getOperacion().getIdOperacion());
			mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
			
			mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$01);
			mensajeMIC.setModoEjecucion(SiNoEnum.SI);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

			cargarFacturaAMensaje(factura, mensajeMIC);

			return mensajeMIC;

		} else {
			mensajeMIC = new MicApropiacionMPDto();
			mensajeMIC.setIdOperacion(listaMediosPagoAEnviar.get(0).getTransaccion().getOperacion().getIdOperacion());
			mensajeMIC.setIdTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccionFicticio());
			
			mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$02);
			mensajeMIC.setModoEjecucion(SiNoEnum.SI);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

			// Ahi le seteo la fecha de factura de calipso, o del tratamiento de
			// la diferencia
			Date fechaValor = null;
			if (!Validaciones.isObjectNull(listaMediosPagoAEnviar.get(0).getTransaccion().getFactura())) {
				fechaValor = listaMediosPagoAEnviar.get(0).getTransaccion().getFactura().getFechaValor();
			} else if (!Validaciones.isObjectNull(listaMediosPagoAEnviar.get(0).getTransaccion().getTratamientoDiferencia())) {
				fechaValor = listaMediosPagoAEnviar.get(0).getTransaccion().getTratamientoDiferencia().getFechaValor();
			}

			mensajeMIC.getDetalleFactura().setFechaValor(Utilidad.formatDateAAAAMMDD(fechaValor));

			cargarMediosPagoMensajeMic(listaMediosPagoAEnviar, mensajeMIC);

			return mensajeMIC;
		}
	}

	/**
	 * 
	 * @param facturaMic
	 * @param mensajeMIC
	 */
	private void cargarFacturaAMensaje(ShvCobFacturaMic facturaMic, MicTransaccionADCDto mensajeMIC) {

		// Si la factura es de MIC, el “Pago Parcial Cancelatorio” se enviará
		// como “Pago Parcial”.

		if (TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO.equals(facturaMic.getTipoPago())) {
			mensajeMIC.getDetalleFactura().setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
		} else {
			mensajeMIC.getDetalleFactura().setTipoOperacion(facturaMic.getTipoPago());
		}

		if (TipoComprobanteEnum.DUC.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante())) {
			mensajeMIC.getDetalleFactura().setTipoDocumento(TipoDocumentoEnum.DUC);
		} else if (TipoComprobanteEnum.FAC.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante())) {
			mensajeMIC.getDetalleFactura().setTipoDocumento(TipoDocumentoEnum.FACTURA_NOTA_DEBITO);
		} else if (TipoComprobanteEnum.DEB.getValor().equals(facturaMic.getTipoComprobante().getIdTipoComprobante())) {
			mensajeMIC.getDetalleFactura().setTipoDocumento(TipoDocumentoEnum.FACTURA_NOTA_DEBITO);
		}

		mensajeMIC.getDetalleFactura().setReferenciaFactura(facturaMic.getIdReferenciaFactura());
		mensajeMIC.getDetalleFactura().setFechaValor(Utilidad.formatDateAAAAMMDD(facturaMic.getFechaValor()));
		mensajeMIC.getDetalleFactura().setAcuerdoFacturacionIntereses(facturaMic.getAcuerdoTrasladoCargo());
		mensajeMIC.getDetalleFactura().setImporte(facturaMic.getImporteCobrar());

		mensajeMIC.getDetalleFactura().setQueHacerConLosIntereses(facturaMic.getQueHacerConIntereses().getCodigoMicSimulacion());
		mensajeMIC.getDetalleFactura().setImporteBonificacionIntereses(facturaMic.getImporteBonificacionIntereses());
		mensajeMIC.getDetalleFactura().setQueHacerConLosTerceros(facturaMic.getDestransferirTerceros());

		mensajeMIC.getDetalleFactura().setMontoAcumuladoSimulacion(facturaMic.getMontoAcumuladoSimulacion());
		mensajeMIC.getDetalleFactura().setFechaCobranzaAcumuladaSimulacion(facturaMic.getFechaAcumuladoSimulacion());
	}

	/**
	 * Carga todos los datos de los mediosd e pago al mensaje
	 * 
	 * @param listaMediosPagoAEnviar
	 * @param mensajeMIC
	 */
	private void cargarMediosPagoMensajeMic(List<ShvCobMedioPago> listaMediosPagoAEnviar, MicTransaccionADCDto mensajeMIC) {
		for (ShvCobMedioPago medioPago : listaMediosPagoAEnviar) {
			MicDetalleMedioPagoDto mp = new MicDetalleMedioPagoDto();

			if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
				ShvCobMedioPagoNotaCreditoMic medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMic) medioPago;
				mp.setTipoMedioPago(TipoMedioPagoEnum.NC);
				mp.setNumeroNC(Long.valueOf(medioPagoNotaCreditoMic.getNumeroNotaCredito()));
			} else if (medioPago instanceof ShvCobMedioPagoRemanente) {
				ShvCobMedioPagoRemanente medioPagoRemanente = (ShvCobMedioPagoRemanente) medioPago;
				mp.setTipoMedioPago(TipoMedioPagoEnum.RT);
				mp.setCuentaRemanente(medioPagoRemanente.getCuentaRemanente());
				mp.setTipoRemanente(TipoRemanenteEnum.getEnumByCodigo(medioPagoRemanente.getTipoRemanente()));
			}

			mp.setImporteMedioPago(medioPago.getImporte());
			mensajeMIC.getListaMediosPago().add(mp);
		}
	}

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicTransaccionGeneracionCargosDto prepararSimulacionGeneracionCargosCreditoMic(ShvCobTratamientoDiferencia tratamientoDiferencia) throws NegocioExcepcion {

		MicTransaccionGeneracionCargosDto mensajeMIC = new MicTransaccionGeneracionCargosDto();

		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$07);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setIdOperacion(tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion().longValue());
		mensajeMIC.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion());
		mensajeMIC.setNumeroTransaccion(tratamientoDiferencia.getTransaccion().getNumeroTransaccion());
		mensajeMIC.setNumeroTransaccionFicticio(tratamientoDiferencia.getTransaccion().getNumeroTransaccionFicticio());
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

		MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();

		detalleGeneracionCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoTrasladoCargo());
		detalleGeneracionCargo.setImporteNoRegulado(tratamientoDiferencia.getImporte());
		// detalleGeneracionCargo.setImporteRegulado(tratamientoDiferencia.getImporte());
		detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(tratamientoDiferencia.getFechaTramiteReintegro()));
		detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.SI);

		// Este valor se convierte en una "T" cuando se invoca al servicio de
		// generacion de cargos
		detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.TM);
		detalleGeneracionCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
		detalleGeneracionCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
		detalleGeneracionCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());

		mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);

		return mensajeMIC;
	}

	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	public MicTransaccionGeneracionCargosDto prepararSimulacionGeneracionCargosDebitoMic(ShvCobMedioPagoDebitoProximaFacturaMic medioPago) throws NegocioExcepcion {

		MicTransaccionGeneracionCargosDto mensajeMIC = new MicTransaccionGeneracionCargosDto();

		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setIdOperacion(medioPago.getTransaccion().getOperacion().getIdOperacion().longValue());
		mensajeMIC.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion());
		mensajeMIC.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
		mensajeMIC.setNumeroTransaccionFicticio(medioPago.getTransaccion().getNumeroTransaccionFicticio());
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

		MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();

		detalleGeneracionCargo.setAcuerdoFacturacion(medioPago.getAcuerdoTrasladoCargo());
		detalleGeneracionCargo.setImporteRegulado(medioPago.getImporte());
		detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(medioPago.getFechaVencimientoFactura()));
		detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.SI);

		// Este valor se convierte en una "T" cuando se invoca al servicio de
		// generacion de cargos
		detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.TM);
		detalleGeneracionCargo.setOrigenCargo(medioPago.getOrigenCargo());
		detalleGeneracionCargo.setLeyendaFacturaCargo(medioPago.getLeyendaFacturaCargo());
		detalleGeneracionCargo.setLeyendaFacturaInteres(medioPago.getLeyendaFacturaInteres());

		mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);

		return mensajeMIC;
	}

	/**
	 * 
	 * @param shvCobTransaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private EntradaDeimosApropiacionWS prepararSimulacionApropiacionDeimos(ShvCobTransaccion shvCobTransaccion) throws NegocioExcepcion {
		List<Documento> listaDocumentos = new ArrayList<Documento>();

		MonedaEnum monedaOperacion = shvCobTransaccion.getOperacion().getMonedaOperacion();

		//
		// Si existe una factura, verifico si debo apropiar en Deimos, y agrego
		// a la lista de documentos
		//
		if (!Validaciones.isObjectNull(shvCobTransaccion.getFactura())) {

			ShvCobFactura factura = shvCobTransaccion.getFactura();

			if (SiNoEnum.SI.equals(factura.getMigradoDeimos())) {

				Documento documentoFactura = new Documento();
				documentoFactura.setEmpresa(EmpresaEnum.TA);
				documentoFactura.setImporte(factura.getImporteCobrar().setScale(2, BigDecimal.ROUND_HALF_UP));

				if (factura instanceof ShvCobFacturaCalipso) {

					documentoFactura.setSistema(SistemaEnum.CALIPSO);

					// Si el documento que vamos a apropiar tiene una moneda que
					// difiere de la moneda del cobro, debemos enviar como
					// importe el
					// importe imputado en pesos a fecha de emisión
					if (!monedaOperacion.equals(((ShvCobFacturaCalipso) factura).getMoneda())) {
						BigDecimal importePesificadoFechaEmision = ((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaPesos().setScale(2, BigDecimal.ROUND_HALF_UP);
						documentoFactura.setImporte(importePesificadoFechaEmision);

						// Si es una operación en dolares (u otra moneda) donde
						// no existe diferencia de moneda (moneda operacion =
						// moneda de documento)
						// debo enviar el importe pesificado a fecha de emisión
					} else if (monedaOperacion.equals(((ShvCobFacturaCalipso) factura).getMoneda()) && !MonedaEnum.PES.equals(monedaOperacion)) {
						BigDecimal importePesificadoFechaEmision = (((ShvCobFacturaCalipso) factura).getImporteCobrar().multiply(((ShvCobFacturaCalipso) factura).getTipoCambio())).setScale(2, BigDecimal.ROUND_HALF_UP);
						documentoFactura.setImporte(importePesificadoFechaEmision);
					}

					IdDocumento idDoc = new IdDocumento();
					idDoc.setClaseComprobante(factura.getClaseComprobante());
					idDoc.setNumeroComprobante(factura.getNumeroComprobante());
					idDoc.setSucursalComprobante(factura.getSucursalComprobante());
					idDoc.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(factura.getTipoComprobante().getIdTipoComprobante()));
					documentoFactura.setIdDocumentoCalipso(idDoc);

				} else if (factura instanceof ShvCobFacturaMic) {

					documentoFactura.setSistema(SistemaEnum.MIC);
					IdDocumentoMic idDocumentoMic = new IdDocumentoMic();
					idDocumentoMic.setNumeroReferenciaMic(((ShvCobFacturaMic) factura).getIdReferenciaFactura());
					documentoFactura.setIdDocumentoMic(idDocumentoMic);
				}

				listaDocumentos.add(documentoFactura);
			}
		}

		//
		// Si existen medios de pago, verifico si debo apropiar en Deimos, y
		// agrego a la lista de documentos
		//
		for (ShvCobMedioPago medioPago : shvCobTransaccion.getShvCobMedioPago()) {

			if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos())) {

				Documento documentoMedioPago = new Documento();
				documentoMedioPago.setEmpresa(EmpresaEnum.TA);
				documentoMedioPago.setImporte(medioPago.getImporte().setScale(2, BigDecimal.ROUND_HALF_UP));

				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {

					documentoMedioPago.setSistema(SistemaEnum.CALIPSO);

					ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;

					// Si el documento que vamos a apropiar tiene una moneda que
					// difiere de la moneda del cobro, debemos enviar como
					// importe el
					// importe apropiado en pesos a fecha de emisión
					if (!monedaOperacion.equals(medioPago.getMoneda())) {
						BigDecimal importePesificadoFechaEmision = medioPagoNotaCreditoCalipso.getImporteAplicadoAFechaEmisionMonedaPesos().setScale(2, BigDecimal.ROUND_HALF_UP);
						documentoMedioPago.setImporte(importePesificadoFechaEmision);

						// Si es una operación en dolares (u otra moneda) donde
						// no existe diferencia de moneda (moneda operacion =
						// moneda de documento)
						// debo enviar el importe pesificado a fecha de emisión
					} else if (monedaOperacion.equals(medioPago.getMoneda()) && !MonedaEnum.PES.equals(monedaOperacion)) {
						BigDecimal importePesificadoFechaEmision = (medioPagoNotaCreditoCalipso.getImporte().multiply(medioPagoNotaCreditoCalipso.getTipoCambio())).setScale(2, BigDecimal.ROUND_HALF_UP);
						documentoMedioPago.setImporte(importePesificadoFechaEmision);
					}

					IdDocumento idDoc = new IdDocumento();
					idDoc.setClaseComprobante(medioPagoNotaCreditoCalipso.getClaseComprobante());
					idDoc.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
					idDoc.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
					idDoc.setTipoComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante());
					documentoMedioPago.setIdDocumentoCalipso(idDoc);

				} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {

					documentoMedioPago.setSistema(SistemaEnum.MIC);
					ShvCobMedioPagoNotaCreditoMic medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMic) medioPago;
					IdDocumentoMic idDocumentoMic = new IdDocumentoMic();
					idDocumentoMic.setNumeroReferenciaMic(medioPagoNotaCreditoMic.getNumeroNotaCredito());
					documentoMedioPago.setIdDocumentoMic(idDocumentoMic);
				}

				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso || medioPago instanceof ShvCobMedioPagoCTA || medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {

					InfoAdicionalMedPagNoComisionables infoAdicionalMedPagNoComisionables = new InfoAdicionalMedPagNoComisionables();
					infoAdicionalMedPagNoComisionables.setImporte(documentoMedioPago.getImporte());
					infoAdicionalMedPagNoComisionables.setCodigoTipoMedioPago(new Long(medioPago.getTipoMedioPago().getIdTipoMedioPago()));
					documentoMedioPago.setInfoAdicionalMedPagNoComisionables(infoAdicionalMedPagNoComisionables);

					listaDocumentos.add(documentoMedioPago);
				}
			}
		}

		//
		// Si existen documentos para apropiar...
		//
		if (Validaciones.isCollectionNotEmpty(listaDocumentos)) {

			EntradaDeimosApropiacionWS entrada = new EntradaDeimosApropiacionWS();

			entrada.setIdOperacionShiva(shvCobTransaccion.getOperacion().getIdOperacion());
			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setModoOperacion(SiNoEnum.SI); // "S"-> simulación

			Transaccion entradaTransaccion = new Transaccion();
			entradaTransaccion.setIdTransaccion(shvCobTransaccion.getIdTransaccion());
			entradaTransaccion.setIdSecuencia(shvCobTransaccion.getNumeroTransaccion());
			entradaTransaccion.setListaDocumentos(listaDocumentos);

			entrada.setTransaccion(entradaTransaccion);

			return entrada;
		}

		return null;
	}

	/**
	 * 
	 * @param documentoCap
	 * @param idOperacion
	 * @return
	 */
	private EntradaSapVerificacionPartidasWS prepararSimulacionVerificarPartidasSap(ShvCobMedioPagoDocumentoCap documentoCap, Long idOperacion) {

		EntradaSapVerificacionPartidasWS entrada = new EntradaSapVerificacionPartidasWS();

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
			partida.setImporteRenglonMonedaOrigenAFechaEmision(documentoCapDetalle.getImporteRenglonMonedaOrigenAFechaEmision());
			partida.setBloqueoPago(documentoCapDetalle.getBloqueoPago().getCodigo());
			partida.setNumeroDocSAPVinculado(documentoCapDetalle.getNumeroDocSAPVinculado());

			if (!Validaciones.isObjectNull(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado())) {
				partida.setEjercicioFiscalDocSAPVinculado(documentoCapDetalle.getEjercicioFiscalDocSAPVinculado().toString());
			}

			partida.setPosicionDocSAPVinculado(documentoCapDetalle.getPosicionDocSAPVinculado().toString());
			partida.setClaveRef2(documentoCapDetalle.getClaveRef2());

			entrada.getListaPartidas().add(partida);
		}

		return entrada;
	}

	/**
	 * 
	 */
	public ShvCobCobro buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion {

		try {
			return cobroDao.buscarCobroPorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void enviarMailyGenerarTareaSimulacionBatchFinalizada(ShvCobCobro cobro) throws NegocioExcepcion {

		try {
			List<ResultadoBusquedaDatosSimulacion> listaDatos = (List<ResultadoBusquedaDatosSimulacion>) cobroDao.buscarDatosSimulacion(cobro.getOperacion().getIdOperacion());
			StringBuffer cuerpo = new StringBuffer();
			cuerpo.append("Listado de clientes incluidos en el cobro: <br><br>");
			BigDecimal importe = new BigDecimal(0);

			if (!Validaciones.isObjectNull(listaDatos.get(0).getImporte())) {
				importe = importe.add(listaDatos.get(0).getImporte());
			}

			for (ResultadoBusquedaDatosSimulacion datos : listaDatos) {
				cuerpo.append("Cliente: " + datos.getIdClienteLegado() + " / " + datos.getRazonSocial() + "<br>");
			}

			MarcaEnum marca = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO;

			if (!esSimulacionExitosa(cobro)) {
				marca = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR;
			}

			enviarMailResultadoSimulacionBatch(listaDatos.get(0), marca, cobro, cuerpo);
			crearTareaPendienteResultadoSimulacionBatch(cobro, listaDatos, marca, importe, false);

		} catch (IOException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * 
	 * @param cobro
	 * @param listaDatos
	 * @param marca
	 * @param importe
	 * @param enviarMail
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendienteResultadoSimulacionBatch(ShvCobCobro cobro, List<ResultadoBusquedaDatosSimulacion> listaDatos, MarcaEnum marca, BigDecimal importe, Boolean enviarMail) throws NegocioExcepcion {

		TareaDto tarea = new TareaDto();

		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());

		if (MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.equals(marca)) {
			tarea.setTipoTarea(TipoTareaEnum.COB_RES_SIM_OK);
		} else if (MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.equals(marca)) {
			tarea.setTipoTarea(TipoTareaEnum.COB_RES_SIM_ERR);
		}
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioCreacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
		tarea.setNroCliente(listaDatos.get(0).getIdClienteLegado().toString());
		tarea.setRazonSocial(listaDatos.get(0).getRazonSocial());
		tarea.setUsuarioAsignacion(listaDatos.get(0).getAnalista());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);

		tarea.setMonedaImporte(cobro.getMonedaOperacion().getSigno2());

		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));

		tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
		tarea.setIdOperacion(cobro.getOperacion().getIdOperacion());
		tarea.setEnviarMail(enviarMail);
		// MonedaOperacion

		tareaServicio.crearTareaResultadoSimulacion(tarea);
	}

	/**
	 * 
	 * @param Datos
	 * @param marca
	 * @param cobro
	 * @param cuerpo
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 * @throws IOException
	 */
	private void enviarMailResultadoSimulacionBatch(ResultadoBusquedaDatosSimulacion Datos, MarcaEnum marca, ShvCobCobro cobro, StringBuffer cuerpo) throws NegocioExcepcion, PersistenciaExcepcion, IOException {

		ByteArrayOutputStream outputStream = null;

		try {
			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(Datos.getAnalista());

			outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workbook = cobroOnlineServicio.exportarCobro(cobro.getIdCobro());
			workbook.write(outputStream);
			String asunto = ("Nueva Tarea - Cobro " + marca.descripcion() + ": " + cobro.getOperacion().getIdOperacion() + " - " + Datos.getIdClienteLegado() + "/" + Datos.getRazonSocial());
			String name = Utilidad.EMPTY_STRING;
			if (!Validaciones.isObjectNull(cobro.getOperacion().getIdOperacion())) {
				name = "ID Operación Cobro " + Utilidad.rellenarCerosIzquierda(cobro.getOperacion().getIdOperacion().toString().trim(), 7);
			} else {
				name = "ID Operación Cobro XXXXXXX";
			}

			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), name, EXTENSION_ARCHIVO_ADJUNTO));

			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(Datos.getCopropietario());

			if (!Validaciones.isObjectNull(copropietario)) {
				Mail mail = new Mail(analista.getMail(), copropietario.getMail(), asunto, cuerpo);
				mail.setAdjuntos(listaAdjuntos);
				mailServicio.sendMail(mail);
			} else {
				Mail mail = new Mail(analista.getMail(), asunto, cuerpo);
				mail.setAdjuntos(listaAdjuntos);
				mailServicio.sendMail(mail);
			}
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param idClienteLegado
	 * @return
	 * @throws NegocioExcepcion
	 */
	private AcuerdoLegado buscarPrimerAcuerdoActivo(Long idClienteLegado) throws NegocioExcepcion {

		AcuerdoLegado acuerdoLegado = null;

		try {
			// Busco los acuerdos de un cliente
			FacConsultaAcuerdoSalida consultarAcuerdoxCliente = facJmsSyncServicio.consultarAcuerdoxCliente(idClienteLegado);

			if (!Validaciones.isObjectNull(consultarAcuerdoxCliente)) {

				List<AcuerdoFacturacion> listaAcuerdos = consultarAcuerdoxCliente.getListaAcuerdoFacturacion();

				if (Validaciones.isCollectionNotEmpty(listaAcuerdos)) {

					// Recorro el/los acuerdos para validar su estado y quedarme
					// con el primero que cumpla
					for (AcuerdoFacturacion acuerdo : listaAcuerdos) {
						if (!Validaciones.isObjectNull(acuerdo)) {

							// Mejora Performance
							// Si estos dos cumplen con este criterio, puedo ir
							// a consultar por el acuerdo (no borrar los
							// espacios)
							if (!Validaciones.isNullOrEmpty(acuerdo.getFechaFinalizacionAcuerdoFactura()) && !Validaciones.isNullOrEmpty(acuerdo.getFechaUltimaFactura()) && Constantes.JULIANO_ULTIMO_DIA.equalsIgnoreCase(acuerdo.getFechaFinalizacionAcuerdoFactura().trim()) && !Constantes.JULIANO_PRIMER_DIA.equalsIgnoreCase(acuerdo.getFechaUltimaFactura().trim())) {

								FacConsultaAcuerdoSalida salida = facJmsSyncServicio.consultarAcuerdo(acuerdo.getNumeroAcuerdo());
								EstadoAcuerdoFacturacionEnum estadoAcuerdo = null;

								if (!Validaciones.isObjectNull(salida)) {
									estadoAcuerdo = salida.getPrimerAcuerdoFacturacion().getEstadoAcuerdo();

									if (!Validaciones.isObjectNull(estadoAcuerdo)) {
										if (EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(estadoAcuerdo.codigo()) || EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(estadoAcuerdo.codigo())) {

											acuerdoLegado = new AcuerdoLegado();

											acuerdoLegado.setNumero(acuerdo.getNumeroAcuerdo());
											acuerdoLegado.setEstado(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(estadoAcuerdo.codigo()));
											acuerdoLegado.setSistema(SistemaEnum.MIC);
											acuerdoLegado.setIdClienteLegado(idClienteLegado);

											break;
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (JmsException e) {
			throw new NegocioExcepcion(e);
		}

		return acuerdoLegado;
	}

	/**
	 * «RF» Permitir indicar Acuerdo destino del envío a Próxima Factura de
	 * débitos
	 *
	 * Luego de realizada la simulación, en la grilla de transacciones se
	 * mostrará en el campo "Acuerdo de facturación destino de cargos":
	 *
	 * - Si la factura es MIC y el acuerdo de la factura está activo, se
	 * mostrará por defecto dicho acuerdo grisado (es decir, no será editable).
	 * Si dicho acuerdo no está activo, se autocompletará el primer acuerdo
	 * activo del cliente de la factura. Si no posee acuerdo activo, se buscará
	 * el primer acuerdo activo de alguno de los otros clientes seleccionados
	 * para el cobro. Si ninguno posee acuerdo activo, se mostrará un mensaje
	 * indicando que se debe agregar un nuevo cliente al cobro.
	 * 
	 * @param factura
	 */
	private AcuerdoLegado buscarAcuerdoActivo(Modelo destino) throws NegocioExcepcion {

		AcuerdoLegado acuerdo = null;
		Long cliente = null;
		Long idOperacion = null;

		boolean buscarAcuerdoMic = false;

		if (destino instanceof ShvCobMedioPago) {
			ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
			cliente = medioPago.getIdClienteLegado();
			idOperacion = medioPago.getTransaccion().getOperacion().getIdOperacion();

			buscarAcuerdoMic = destino instanceof ShvCobMedioPagoDebitoProximaFacturaMic;

		} else if (destino instanceof ShvCobFactura) {
			ShvCobFactura factura = (ShvCobFactura) destino;
			cliente = factura.getIdClienteLegado();
			idOperacion = factura.getTransaccion().getOperacion().getIdOperacion();

			buscarAcuerdoMic = destino instanceof ShvCobFacturaMic;

		} else if (destino instanceof ShvCobTratamientoDiferencia) {
			ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
			cliente = tratamientoDiferencia.getIdClienteLegadoAcuerdoTrasladoCargo();
			idOperacion = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion();

			buscarAcuerdoMic = TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia());
		}

		//
		// Valido acuerdo contra MIC
		//
		if (buscarAcuerdoMic) {
			// Si dicho acuerdo no está activo, se autocompletará el primer
			// acuerdo activo del cliente de la factura.
			acuerdo = buscarPrimerAcuerdoActivo(cliente);

			// Si no posee acuerdo activo, se buscará el primer acuerdo activo
			// de alguno de los otros clientes seleccionados para el cobro.
			if (Validaciones.isObjectNull(acuerdo)) {
				try {
					List<ShvCobEdCliente> listaClientesCobro = cobroOnlineClienteDao.listarClientesPorIdOperacionCobro(idOperacion);

					for (ShvCobEdCliente clienteCobro : listaClientesCobro) {
						acuerdo = buscarPrimerAcuerdoActivo(clienteCobro.getIdClienteLegado());

						if (!Validaciones.isObjectNull(acuerdo)) {
							break;
						}
					}
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e);
				}
			}

			// Valido acuerdo contra Calipso
		} else {

		}

		return acuerdo;
	}
	
	// Si el cobro contiene facturas pertenecientes a ShvCobFacturaUsuario va a contener aplicacion manual.
	private void verificarSiContieneAplicacionManual(ShvCobCobro cobro){
		SiNoEnum contieneAplicacionManual = null;
		for(ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()){
			if(!SiNoEnum.SI.equals(contieneAplicacionManual)){	
				if(transaccion.getFactura() instanceof ShvCobFacturaUsuario){
						contieneAplicacionManual=SiNoEnum.SI;
					} else {
						contieneAplicacionManual= SiNoEnum.NO;
					}
				}
		}
		cobro.setContieneAplicacionManual(contieneAplicacionManual);;
	}
	
	// Si es la primera transaccion que pertenece al medio de pago no dependera de ninguna otra transaccion
	// Si no es la primera transaccion dependera de la transaccion anterior.
	//Una transaccion del grupo 0 no puede depender de una transaccion de un grupo mayor.
private void verificarDependenciaTransaccion(ShvCobCobro cobro){
	

	HashMap<String,ArrayList<String>> dependencia = new HashMap<String,ArrayList<String>>();
	HashMap<Integer,ArrayList<String>> dependenciaXGrupo = new HashMap<Integer,ArrayList<String>>();
	List<ShvCobTransaccion> listaTransaccion = new ArrayList<ShvCobTransaccion>();
	boolean setearGrupo = false;
	Integer grupoAnterior = null;
	
	for(ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()){
		listaTransaccion.add(transaccion);
	}
	Collections.sort(listaTransaccion, new ComparatorOrdenSimulacionShvCobTransaccion());
	
	for(ShvCobTransaccion transaccionActual : listaTransaccion){
		
         
	if(transaccionActual.getGrupo().equals(grupoAnterior)){
	     if(setearGrupo){
			ArrayList<String> listaResultadoXGrupo = new ArrayList<>();
			listaResultadoXGrupo = dependenciaXGrupo.get(transaccionActual.getGrupo());
		
				if (Validaciones.isCollectionNotEmpty(listaResultadoXGrupo)){
					transaccionActual.setNumeroTransaccionDependencia(Integer.valueOf(listaResultadoXGrupo.get(0)));
					transaccionActual.setIdSociedadDependencia(SociedadEnum.getEnumByName(listaResultadoXGrupo.get(1)));
					transaccionActual.setSistemaDependencia(SistemaEnum.getEnumByName(listaResultadoXGrupo.get(2)));
					transaccionActual.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE_FINALIZACION_TRANSACCION);
				}
		}
     } else {
    	 setearGrupo = false;
    	 grupoAnterior = transaccionActual.getGrupo();
    	 for (ShvCobMedioPago medioPagoActual : transaccionActual.getShvCobMedioPago()) {
		 if (medioPagoActual instanceof ShvCobMedioPagoCTA || medioPagoActual instanceof ShvCobMedioPagoNotaCreditoMic){
			
			ArrayList<String> lista = new ArrayList<String>();
			lista.add(transaccionActual.getGrupo().toString());
			lista.add(transaccionActual.getNumeroTransaccion().toString());
			lista.add(transaccionActual.getIdSociedad().toString());
			lista.add(transaccionActual.getSistema().toString());	
			
			ArrayList<String> listaXGrupo= new ArrayList<String>();
			listaXGrupo.add(transaccionActual.getNumeroTransaccion().toString());
			listaXGrupo.add(transaccionActual.getIdSociedad().toString());
			listaXGrupo.add(transaccionActual.getSistema().toString());	
			dependenciaXGrupo.put(transaccionActual.getGrupo()+1,listaXGrupo);
			
			if (!dependencia.containsKey(medioPagoActual.getReferencia())){
				
				dependencia.put(medioPagoActual.getReferencia(),lista);
				
			} else {
				ArrayList<String> listaResultado = new ArrayList<>();
				listaResultado = dependencia.get(medioPagoActual.getReferencia());
				
				if (Validaciones.isCollectionNotEmpty(listaResultado)){
					
				// Transacciones del mismo grupo no pueden depender una de la otra. Pero van a depender de la ultima transaccion del anterior grupo
					if (!listaResultado.get(0).equals(transaccionActual.getGrupo().toString())){
						
						transaccionActual.setNumeroTransaccionDependencia(Integer.valueOf(listaResultado.get(1)));
						transaccionActual.setIdSociedadDependencia(SociedadEnum.getEnumByName(listaResultado.get(2)));
						transaccionActual.setSistemaDependencia(SistemaEnum.getEnumByName(listaResultado.get(3)));
						transaccionActual.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE_FINALIZACION_TRANSACCION);
						setearGrupo = true;
						
					
					} 
				}
			
				dependencia.put( medioPagoActual.getReferencia(),lista);
				}

			  }
    	 	}
     	   }
		 }
       }
	}