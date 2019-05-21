package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida.AcuerdoFacturacion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroMedioPago;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroOperacionPosteriorRelacionada;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebitoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleMedioPagoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleOperacionRelacionadaDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosInteresDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroCargoGeneradoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroCargoGeneradoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroDiferenciaCambio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroFactura;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroFacturaUsuario;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroMedioPagoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroMedioPagoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroMedioPagoShiva;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroMedioPagoUsuario;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroOperacionRelacionada;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroOperacionRelacionadaCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroOperacionRelacionadaMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionDescobroTratamientoDiferencia;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

/**
 * @author u573005, fabio.giaquinta.ruiz
 *
 */
public class DescobroBatchSimulacionServicioImpl extends Servicio implements IDescobroSimulacionServicio {

	@Autowired private IParametroServicio parametroServicio;
	@Autowired private ITipoMedioPagoServicio tipoMedioPagoServicio;
	@Autowired private IValorServicio valorServicio;
	@Autowired private ICobroDao cobroDao;
	@Autowired private IDescobroDao descobroDao;
	@Autowired private ITareaServicio tareaServicio;
	@Autowired private MailServicio mailServicio;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private ExportacionDetalleCobroImpl exportacionDetalleCobro;
	@Autowired private IWorkflowService workflowService;
	@Autowired private ITipoMedioPagoDao tipoMedioPagoDao;
	
	@Autowired private IDescobroServicio descobroServicio;
	
	@Autowired private IClienteCalipsoServicio clienteCalipsoServicio;
	@Autowired private IClienteDeimosServicio clienteDeimosServicio;
	@Autowired private IMicJmsSyncServicio micJmsSyncServicio;
	@Autowired private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired private IFacJmsSyncServicio facJmsSyncServicio;
	@Autowired private ICobroOnlineClienteDao cobroOnlineClienteDao;
	
	private static final String EXTENSION_ARCHIVO_ADJUNTO = "xls";
	Set<String> operacionesRepetidas = new HashSet <String>();

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public String simularDescobro(Long idDescobro) throws NegocioExcepcion {
		
		try {
			// Busco el descobro en la base de datos
			ShvCobDescobro descobro = descobroDao.buscarDescobro(idDescobro);

			
			// Seteo la marca de simulación batch en proceso
			workflowService.agregarWorkflowMarca(
					descobro.getWorkflow(), 
					descobro.getUsuarioUltimaModificacion(), 
					MarcaEnum.SIMULACION_BATCH_EN_PROCESO);

			//Mando a la pantalla el mensaje de aviso de simulacion por batch
			return Mensajes.DESCOBROS_AVISO_SIMULACION_BATCH;
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} 
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void simularDescobroProcesamientoBatch(ShvCobDescobro descobro, String usuarioModificacion) throws NegocioExcepcion {
		
		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();

		
		// 
		// Simulo contra los cobradores
		//
		descobro = simularDescobroEnCobradores(descobro);
		
		MarcaEnum marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO;
		
		// Determino si la simulacion ha finalizado correctamente
		if (!esSimulacionExitosa(descobro)) {
			marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR;
		}
		
		Traza.loguearInfoProcesamiento(this.getClass(),"simularDescobroProcesamientoBatch Sin Guardado", fechaHoraInicio, fechaHoraInicioNanoTime, 1);
		
		// Seteo la marca de resultado de simulacion
		workflowService.agregarWorkflowMarca(
					descobro.getWorkflow(), 
					usuarioModificacion, 
					marcaResultadoSimulacion);
		
		// Medicion de tiempo al inicio del procesamiento
		fechaHoraInicioNanoTime = System.nanoTime();
		fechaHoraInicio = new Date();
		
		//guardo el descobro con todo los cambios
		try {	
			descobroDao.modificar(descobro);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e);
		}
		

		Traza.loguearInfoProcesamiento(this.getClass(),"descobroDao.modificar", fechaHoraInicio, fechaHoraInicioNanoTime, 0);
		
		enviarMailyGenerarTareaSimulacionDescobroBatchFinalizada(descobro);
	}

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobDescobro simularDescobroEnCobradores(ShvCobDescobro descobro) throws NegocioExcepcion {

		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		//obtengo el id operacion original (del cobro) para enviarlo a los cobradores
		Long idOperacionCobro = descobro.getOperacion().getIdOperacionOriginal();
		Long idOperacionDescobro = descobro.getOperacion().getIdOperacion();
		
		//Se elimina toda la mensajeria 
		mensajeriaTransaccionServicio.borrarMensajesPorIdOperacion(idOperacionDescobro);
		
		//recorro las transacciones de atras para adelante
		for (ShvCobTransaccion transaccion : descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio()){
			
			// Medicion de tiempo al inicio del procesamiento
			double fechaHoraInicioNanoTimeFor = System.nanoTime();
			Date fechaHoraInicioFor = new Date();
			
			ShvCobFacturaMic facturaMic = null;
			ShvCobFacturaCalipso facturaClp = null;
			ShvCobFacturaUsuario facturaUsuario = null;
			
			List<ShvCobMedioPagoMic> listaMediosPagoMic = new ArrayList<ShvCobMedioPagoMic>();
			List<ShvCobMedioPagoCalipso> listaMediosPagoClp = new ArrayList<ShvCobMedioPagoCalipso>();
			List<ShvCobMedioPagoShiva> listaMediosPagoShiva = new ArrayList<ShvCobMedioPagoShiva>();
			List<ShvCobMedioPagoUsuario> listaMediosPagoUsuario = new ArrayList<ShvCobMedioPagoUsuario>();

			ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic = null;
			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp = null;
			
			ShvCobTratamientoDiferencia creditoProximaFacturaMic = null;
			ShvCobTratamientoDiferencia creditoProximaFacturaClp = null;
			ShvCobTratamientoDiferencia tratamientoDiferencia = null;
			
			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(
							transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
							
					creditoProximaFacturaMic = transaccion.getTratamientoDiferencia();
				} else {		
					if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(
								transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
								
						creditoProximaFacturaClp = transaccion.getTratamientoDiferencia();
					} else {
						tratamientoDiferencia = transaccion.getTratamientoDiferencia();
					}
				}
			}

			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {

				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
					debitoProximaFacturaClp = (ShvCobMedioPagoDebitoProximaFacturaCalipso)medioPago;
				
				} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					debitoProximaFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMic)medioPago;

				} else if (medioPago instanceof ShvCobMedioPagoMic) {
					listaMediosPagoMic.add((ShvCobMedioPagoMic)medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoCalipso) {
					listaMediosPagoClp.add((ShvCobMedioPagoCalipso)medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoShiva) {
					listaMediosPagoShiva.add((ShvCobMedioPagoShiva)medioPago);

				} else if (medioPago instanceof ShvCobMedioPagoUsuario) {
					listaMediosPagoUsuario.add((ShvCobMedioPagoUsuario)medioPago);
				}
			}

			Traza.loguearInfoProcesamiento(this.getClass(),"simularDescobroEnCobradores clasificacionTR for", fechaHoraInicioFor, fechaHoraInicioNanoTimeFor, 0);
			
			
			fechaHoraInicioNanoTimeFor = System.nanoTime();
			fechaHoraInicioFor = new Date();
			
			//Simular descobro de factura relacionada con saldo a cobrar
			if (haySaldoACobrar(transaccion)){
				ResultadoSimulacion resultado = simularDescobroFacturaDeSaldoACobrar(transaccion.getFactura(), idOperacionCobro);
				listaResultadoSimulacion.add(resultado);
			} else {
				if(transaccion.getFactura() instanceof ShvCobFacturaMic){
					facturaMic = (ShvCobFacturaMic) transaccion.getFactura();				
				} else {
					if(transaccion.getFactura() instanceof ShvCobFacturaUsuario){
						facturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();
					} else if(transaccion.getFactura() instanceof ShvCobFacturaCalipso){
						facturaClp = (ShvCobFacturaCalipso) transaccion.getFactura();
					}
									
				}
			}
			//Simular descobro de medios de pago sap / saldo a cobrar
			// Simular descobro de medios de pago en Shiva
			if (Validaciones.isCollectionNotEmpty(listaMediosPagoShiva)) {
				List<ResultadoSimulacion> resultado = simularDescobroShiva(listaMediosPagoShiva, idOperacionCobro);
				listaResultadoSimulacion.addAll(resultado);
			}
			
			// Simular descobro de medios de pago de usuario
			if (Validaciones.isCollectionNotEmpty(listaMediosPagoUsuario)) {
				List<ResultadoSimulacion> resultado = simularDescobroMedioPagoUsuario(listaMediosPagoUsuario, idOperacionCobro);
				listaResultadoSimulacion.addAll(resultado);
			}
			
			// Simular descobro del tratamiento de la diferencia, envio a ganancias y reintegros, saldo a pagar
			if (!Validaciones.isObjectNull(tratamientoDiferencia)) {
				ResultadoSimulacion resultado = simularDescobroTratamientoDiferencia(tratamientoDiferencia, idOperacionCobro);
				listaResultadoSimulacion.add(resultado);
			}

			// Simular descobro en Calipso
			if (!Validaciones.isObjectNull(facturaClp) 
					|| Validaciones.isCollectionNotEmpty(listaMediosPagoClp)
					|| !Validaciones.isObjectNull(creditoProximaFacturaClp)
					|| !Validaciones.isObjectNull(debitoProximaFacturaClp)) {
				
				List<ResultadoSimulacion> resultado = simularDescobroCalipso(listaMediosPagoClp, facturaClp, creditoProximaFacturaClp, debitoProximaFacturaClp, idOperacionCobro, idOperacionDescobro);
				
				listaResultadoSimulacion.addAll(resultado);
			}
			
			// Simular descobro en Mic			
			if (!Validaciones.isObjectNull(facturaMic)
					|| Validaciones.isCollectionNotEmpty(listaMediosPagoMic)
					|| !Validaciones.isObjectNull(creditoProximaFacturaMic)
					|| !Validaciones.isObjectNull(debitoProximaFacturaMic)) {

				List<ResultadoSimulacion> resultado = simularDescobroMic(listaMediosPagoMic, facturaMic, creditoProximaFacturaMic, debitoProximaFacturaMic, idOperacionCobro, idOperacionDescobro);
				
				listaResultadoSimulacion.addAll(resultado);
			}
			
			// Simular descobro de facturas de usuario
			if (!Validaciones.isObjectNull(facturaUsuario)) {
				List<ResultadoSimulacion> resultado = simularDescobroFacturaUsuario(facturaUsuario, idOperacionCobro);
				listaResultadoSimulacion.addAll(resultado);
			}
			
			Traza.loguearInfoProcesamiento(this.getClass(),"simularDescobroEnCobradores envios for", fechaHoraInicioFor, fechaHoraInicioNanoTimeFor, listaResultadoSimulacion.size());

		}
		
		Traza.loguearInfoProcesamiento(this.getClass(),"simularDescobroEnCobradores proceso completo", fechaHoraInicio, fechaHoraInicioNanoTime, listaResultadoSimulacion.size());
		
		descobro = actualizarDescobroPostSimulacion(listaResultadoSimulacion, descobro);
		
		return descobro;
	}
	
	private boolean haySaldoACobrar(ShvCobTransaccion transaccionDescobro) {
		for (ShvCobMedioPago medioPago : transaccionDescobro.getMediosPago()) {
			if (TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
				return true;
			}
		}
		return false;
	}

	private ShvCobDescobro actualizarDescobroPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobDescobro descobro) throws NegocioExcepcion {

		// Medicion de tiempo al inicio del procesamiento
		double fechaHoraInicioNanoTime = System.nanoTime();
		Date fechaHoraInicio = new Date();
		

		//Actualizo los documentos relacionados ahora, para limpiar la lista de resultados
		listaResultadoSimulacion = actualizarDocumentosRelacionados(listaResultadoSimulacion, descobro);
		
		
		//traigo tambien las transaccion con diferencia de cambio para setearle OK
		for (ShvCobTransaccion transaccion : descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio()) {
			
			Boolean hayErrores = false;

			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
				
				actualizarTratamientoDiferenciaDeTransaccionPostSimulacion(listaResultadoSimulacion, transaccion.getTratamientoDiferencia());
				if(TipoMensajeEstadoEnum.ERR.equals(transaccion.getTratamientoDiferencia().getTipoMensajeEstado())){
					hayErrores = true;
				}
			} else if (!Validaciones.isObjectNull(transaccion.getFactura())) {
				
				actualizarFacturasDeTransaccionPostSimulacion(listaResultadoSimulacion, transaccion.getFactura(), descobro);
				
				if(TipoMensajeEstadoEnum.ERR.equals(transaccion.getFactura().getTipoMensajeEstado())){
					hayErrores = true;
				}
			}
			
			for (ShvCobMedioPago medioPago : transaccion.getMediosPagoOrdenadosPorIdMedioPago()) {
				
				
				actualizarMediosPagoDeTransaccionPostSimulacion(listaResultadoSimulacion, medioPago, descobro);
				
				if(TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())){
					hayErrores = true;
				}
			}
			
			//creo una lista vacia para reutilizar el metodo de actualizacion de mensaje estado
			//La lista vacia quiere decir que esta OK, si no fuerzo un error
			List<RespuestaInvocacion> lista = new ArrayList<RespuestaInvocacion>();
						
			if(hayErrores){
				transaccion.setMensajeEstado(null);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				
				//Actualizo el tipo mensaje estado a ERROR de los registros de diferencia de cambio
				actualizarMensajeEstadoDiferenciaCambio(null, transaccion, true);
				
			} else {
				transaccion.setMensajeEstado(TipoMensajeEstadoEnum.OK.name());
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				
				//Actualizo el tipo mensaje estado a OK de los registros de diferencia de cambio
				//La lista vacia quiere decir que esta OK
				actualizarMensajeEstadoDiferenciaCambio(lista, transaccion, false);
			}
			
		}

		//actualizo las operaciones relacionadas
		actualizarOperacionesRelacionadasEnDescobroPostSimulacion(listaResultadoSimulacion, descobro);
		
		Traza.loguearInfoProcesamiento(this.getClass(),"actualizarDescobroPostSimulacion", fechaHoraInicio, fechaHoraInicioNanoTime, listaResultadoSimulacion.size());
		return descobro;
	}
	
	/**
	 * actualizo el tipo de mensaje estado de las facturas y medios de pago copias y por diferencia de cambio
	 * Ademas actualizo el tipo mensaje estado de la transaccion tmabien
	 * @param listaRespuestaForzada
	 * @param transaccion
	 */
	private void actualizarMensajeEstadoDiferenciaCambio(List<RespuestaInvocacion> listaRespuestaForzada, ShvCobTransaccion transaccion, Boolean resetearTipoyMensajeEstado){
		
		Set<ShvCobTransaccion> listaTransaccionDifCambio = transaccion.getOperacion().getTransaccionHijoConNroTransaccionPadre(transaccion.getNumeroTransaccion());
		
		if(Validaciones.isCollectionNotEmpty(listaTransaccionDifCambio)){
			
			for(ShvCobTransaccion transaccionDifCambio : listaTransaccionDifCambio){
				if(!Validaciones.isObjectNull(transaccionDifCambio)){
					
					//Actualizo las facturas de diferencia de cambio
					for(ShvCobFactura facturaDifCambio : transaccionDifCambio.getListaFacturasDiferenciaCambio()){
						actualizarInformacionMensajeEstado(listaRespuestaForzada, facturaDifCambio, resetearTipoyMensajeEstado);
					}
					
					//Actualizo los medios de pago de diferencia de cambio
					for(ShvCobMedioPago medioPagoDifCambio : transaccionDifCambio.getListaMediosPagoDiferenciaCambio()){
						actualizarInformacionMensajeEstado(listaRespuestaForzada, medioPagoDifCambio, resetearTipoyMensajeEstado);
					}
					
					//Actualizo la transaccion de diferencia de cambio
					transaccionDifCambio.setTipoMensajeEstado(transaccion.getTipoMensajeEstado());
				}
			}
		}
				
	}
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param descobro
	 * @return
	 */
	private List<ResultadoSimulacion> actualizarDocumentosRelacionados(List<ResultadoSimulacion> listaResultadoSimulacion,
			ShvCobDescobro descobro) {
		
		//Copio todos los elementos
		List<ResultadoSimulacion> listaResultadoSimulacionSinDifCambio = new ArrayList<ResultadoSimulacion>();
		listaResultadoSimulacionSinDifCambio.addAll(listaResultadoSimulacion);
		
		//Limpio los anteriores si hay
		if(Validaciones.isCollectionNotEmpty(descobro.getDocumentosRelacionados())){
			descobro.getDocumentosRelacionados().removeAll(descobro.getDocumentosRelacionados());
		};
		
		//recorro los nuevos
		for(ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion){
			
			//si son de diferencia de cambio, los agrego a la base
			if(resultadoSimulacion instanceof ResultadoSimulacionDescobroDiferenciaCambio){
				
				ResultadoSimulacionDescobroDiferenciaCambio resultadoDiferenciaCambio = (ResultadoSimulacionDescobroDiferenciaCambio) resultadoSimulacion; 
				
				if(Validaciones.isObjectNull(descobro.getDocumentosRelacionados())){
					descobro.setDocumentosRelacionados(new HashSet<ShvCobDescobroDocumentoRelacionado>());
				}
				
				ShvCobDescobroDocumentoRelacionado diferenciaCambio = mapearDiferenciaCambio(resultadoDiferenciaCambio);
				
				//lo asocio al descobro
				diferenciaCambio.setDescobro(descobro);
				Traza.auditoria(getClass(), "unico " +  diferenciaCambio.getNroTransaccion().toString() + " doc: " + diferenciaCambio.getNumeroComprobanteOriginal());
				//lo agrego a la lista del descobro
				descobro.getDocumentosRelacionados().add(diferenciaCambio);
				
				//elimino lo que ya agregue
				listaResultadoSimulacionSinDifCambio.remove(resultadoSimulacion);
				
			}
		}	
		
		//si la lista original tiene mas elementos que la nueva,
		//se manda la nueva para que el resto de las actualizaciones den menos vueltas
		if(Validaciones.isCollectionNotEmpty(listaResultadoSimulacionSinDifCambio)
			&& Validaciones.isCollectionNotEmpty(listaResultadoSimulacionSinDifCambio)
			&& listaResultadoSimulacion.size() > listaResultadoSimulacionSinDifCambio.size()){
			return listaResultadoSimulacionSinDifCambio;
		} else{
			return listaResultadoSimulacion;
		}
	}

	private void actualizarTratamientoDiferenciaDeTransaccionPostSimulacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobTratamientoDiferencia tratamientoDiferencia) throws NegocioExcepcion {
		
		// recorro los resultados para actualizar el modelo
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {
			
			if (tratamientoDiferencia.getTransaccion().getNumeroTransaccion().equals(resultadoSimulacion.getNumeroTransaccion())) {
				
				Long idOperacion = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacionOriginal();
				
				if (resultadoSimulacion instanceof ResultadoSimulacionDescobroCargoGeneradoMic) {
				
					ResultadoSimulacionDescobroCargoGeneradoMic resultadoCargoGeneradoMic = (ResultadoSimulacionDescobroCargoGeneradoMic) resultadoSimulacion;  

					actualizarInformacionMensajeEstado(resultadoCargoGeneradoMic.getListaRespuestasInvocacion(), tratamientoDiferencia, false);
					
					if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
						
						tratamientoDiferencia.setEstadoCargoGenerado(resultadoCargoGeneradoMic.getEstadoCargoGenerado());
						
						Set<ShvCobMedioPago> listaMp= tratamientoDiferencia.getTransaccion().getMediosPago();
						Iterator<ShvCobMedioPago> iterator =listaMp.iterator();
						ShvCobMedioPago medioPago=null;
						
						if(iterator.hasNext()){
							medioPago=iterator.next();
						}
						
						tratamientoDiferencia = calcularOrigenContracargoCreditoProxima(tratamientoDiferencia, medioPago, idOperacion, false,SistemaEnum.MIC);
						
						tratamientoDiferencia.setCobradorInteresesRealesNoReguladosTrasladados(resultadoCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados());
//						tratamientoDiferencia.setCobradorInteresesRealesReguladosTrasladados(resultadoCargoGeneradoMic.getInteresesRealesReguladosTrasladados());
						if (!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados()) 
								&& resultadoCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados().compareTo(BigDecimal.ZERO) > 0){
							
							tratamientoDiferencia = calcularOrigenContracargoCreditoProxima(tratamientoDiferencia, medioPago, idOperacion, true,SistemaEnum.MIC);
						
						}
						//modifico los 4 campos sistema, acuerdo, estado y cliente solo si el estado no es activo, si es activo me quedo con los datos del cobro
						if (resultadoCargoGeneradoMic.isRequiereBuscarAcuerdoActivo()) {
							autoCompletarAcuerdoActivoMic(tratamientoDiferencia);
						}
					}
					
					
				} else if (resultadoSimulacion instanceof ResultadoSimulacionDescobroCargoGeneradoCalipso) {
					
					ResultadoSimulacionDescobroCargoGeneradoCalipso resultadoCargoGeneradoCalipso = (ResultadoSimulacionDescobroCargoGeneradoCalipso) resultadoSimulacion;  

					actualizarInformacionMensajeEstado(resultadoCargoGeneradoCalipso.getListaRespuestasInvocacion(), tratamientoDiferencia, false);
					
					if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
						tratamientoDiferencia.setEstadoCargoGenerado(resultadoCargoGeneradoCalipso.getEstadoCargoGenerado());
						tratamientoDiferencia.setEstadoAcuerdoContracargo(resultadoCargoGeneradoCalipso.getEstadoAcuerdoContracargo());
						
						//Guardo el sistema, cliente y el acuerdo solamente si no estan vacios
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getSistemaCcontracargo())){
							tratamientoDiferencia.setSistemaAcuerdoContracargo(resultadoCargoGeneradoCalipso.getSistemaCcontracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getIdClienteLegadoContracargo())){
							tratamientoDiferencia.setIdClienteLegadoAcuerdoContracargo(resultadoCargoGeneradoCalipso.getIdClienteLegadoContracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getAcuerdoContracargo())){
							tratamientoDiferencia.setAcuerdoContracargo(resultadoCargoGeneradoCalipso.getAcuerdoContracargo().toString());
						}
						
						Set<ShvCobMedioPago> listaMp= tratamientoDiferencia.getTransaccion().getMediosPago();
						Iterator<ShvCobMedioPago> iterator =listaMp.iterator();
						ShvCobMedioPago medioPago=null;
						
						if(iterator.hasNext()){
							medioPago=iterator.next();
						}
						
						tratamientoDiferencia = calcularOrigenContracargoCreditoProxima(tratamientoDiferencia, medioPago, idOperacion, false,SistemaEnum.CALIPSO);
						
						tratamientoDiferencia.setCobradorInteresesRealesNoReguladosTrasladados(resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados());
						if (!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados()) 
								&& resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados().compareTo(BigDecimal.ZERO) > 0){
							
							tratamientoDiferencia = calcularOrigenContracargoCreditoProxima(tratamientoDiferencia, medioPago, idOperacion, true,SistemaEnum.CALIPSO);
						}
					}

				} else if (resultadoSimulacion instanceof ResultadoSimulacionDescobroTratamientoDiferencia) {
					ResultadoSimulacionDescobroTratamientoDiferencia resultadoTratamDif = (ResultadoSimulacionDescobroTratamientoDiferencia) resultadoSimulacion;  

					actualizarInformacionMensajeEstado(resultadoTratamDif.getListaRespuestasInvocacion(), tratamientoDiferencia, false);
					
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
	private void actualizarFacturasDeTransaccionPostSimulacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobFactura factura, ShvCobDescobro descobro) throws NegocioExcepcion {
		
		// Actualizo los resultados de las diferentes llamadas en el descobro
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {
			
			if(!Validaciones.isObjectNull(resultadoSimulacion)
					&& !Validaciones.isObjectNull(resultadoSimulacion.getNumeroTransaccion())
					&& resultadoSimulacion.getNumeroTransaccion().equals(factura.getTransaccion().getNumeroTransaccion())){
			
				if(resultadoSimulacion instanceof ResultadoSimulacionDescobroFactura){
						
					ResultadoSimulacionDescobroFactura resultadoFactura = (ResultadoSimulacionDescobroFactura) resultadoSimulacion;
					actualizarInformacionMensajeEstado(resultadoFactura.getListaRespuestasInvocacion(), factura, false);
					
					if (!TipoMensajeEstadoEnum.ERR.equals(factura.getTipoMensajeEstado())) {
						
						factura.setEstadoCargoGenerado(resultadoFactura.getEstadoCargoGenerado());
						factura.setEstadoAcuerdoContracargo(resultadoFactura.getEstadoAcuerdoContracargo());
						
						Long idOperacionCobro = descobro.getOperacion().getIdOperacionOriginal();
						
						if(factura instanceof ShvCobFacturaMic){
							
							((ShvCobFacturaMic) factura).setCobradorInteresesTrasladadosNoRegulados(resultadoFactura.getCobradorInteresesTrasladadosNoRegulados());
							((ShvCobFacturaMic) factura).setCobradorInteresesTrasladadosRegulados(resultadoFactura.getCobradorInteresesTrasladadosRegulados());
							
							if (!Validaciones.isObjectNull(resultadoFactura.getCobradorInteresesTrasladadosNoRegulados()) && 
									(resultadoFactura.getCobradorInteresesTrasladadosNoRegulados()).compareTo(BigDecimal.ZERO) > 0){
								
								factura = calcularOrigenContracargoFactura(factura, idOperacionCobro, false);
							}
							
							if (!Validaciones.isObjectNull(resultadoFactura.getCobradorInteresesTrasladadosNoRegulados()) && 
									(resultadoFactura.getCobradorInteresesTrasladadosRegulados()).compareTo(BigDecimal.ZERO) > 0){
								
								factura = calcularOrigenContracargoFactura(factura, idOperacionCobro, true);
							}
							
							if (resultadoFactura.isRequiereBuscarAcuerdoActivo()) {
								autoCompletarAcuerdoActivoMic(((ShvCobFacturaMic) factura));
							}
							
						} else if (factura instanceof ShvCobFacturaCalipso){
							((ShvCobFacturaCalipso) factura).setCobradorInteresesTrasladadosRegulados(resultadoFactura.getCobradorInteresesTrasladadosRegulados());
							factura = calcularOrigenContracargoFactura(factura, idOperacionCobro, true);
						}
						
						//Guardo el sistema, cliente y el acuerdo solamente si no estan vacios
						if(!Validaciones.isObjectNull(resultadoFactura.getSistemaCcontracargo())){
							factura.setSistemaAcuerdoContracargo(resultadoFactura.getSistemaCcontracargo());
						}
						if(!Validaciones.isObjectNull(resultadoFactura.getIdClienteLegadoContracargo())){
							factura.setIdClienteLegadoAcuerdoContracargo(resultadoFactura.getIdClienteLegadoContracargo());
						}
						if(!Validaciones.isObjectNull(resultadoFactura.getAcuerdoContracargo())){
							factura.setAcuerdoContracargo(resultadoFactura.getAcuerdoContracargo().toString());
						}
					}
				
				} else if(resultadoSimulacion instanceof ResultadoSimulacionDescobroFacturaUsuario){
					
					ResultadoSimulacionDescobroFacturaUsuario resultadoDescobroFacturaUsuario = (ResultadoSimulacionDescobroFacturaUsuario) resultadoSimulacion;  
					actualizarInformacionMensajeEstado(resultadoDescobroFacturaUsuario.getListaRespuestasInvocacion(), factura, false);
					
				}
			}
		}
	}	
	
	
	private ShvCobDescobroDocumentoRelacionado mapearDiferenciaCambio(ResultadoSimulacionDescobroDiferenciaCambio resultadoDiferenciaCambio){
		
		ShvCobDescobroDocumentoRelacionado diferenciaCambio = new ShvCobDescobroDocumentoRelacionado();
		diferenciaCambio.setIdOperacion(resultadoDiferenciaCambio.getIdOperacion());
		diferenciaCambio.setNroTransaccion(resultadoDiferenciaCambio.getNumeroTransaccion());
		diferenciaCambio.setSistemaOrigen(resultadoDiferenciaCambio.getSistemaOrigen());
		diferenciaCambio.setIdCobranzaGenerado(resultadoDiferenciaCambio.getIdCobranza());
		diferenciaCambio.setImporteGenerado(resultadoDiferenciaCambio.getImporte());
		diferenciaCambio.setImporteCapitalGenerado(resultadoDiferenciaCambio.getImporteCapital());
		diferenciaCambio.setImporteImpuestosGenerado(resultadoDiferenciaCambio.getImporteImpuestos());
		diferenciaCambio.setTipoComprobanteGenerado(resultadoDiferenciaCambio.getTipoComprobante());
		diferenciaCambio.setClaseComprobanteGenerado(resultadoDiferenciaCambio.getClaseComprobante());
		diferenciaCambio.setSucursalComprobanteGenerado(resultadoDiferenciaCambio.getSucursalComprobante());
		diferenciaCambio.setNumeroComprobanteGenerado(resultadoDiferenciaCambio.getNumeroComprobante());
		diferenciaCambio.setIdDocumentoCuentasCobranzaGenerado(resultadoDiferenciaCambio.getIdDocumentoCuentasCobranza());
		diferenciaCambio.setFechaVencimientoGenerada(resultadoDiferenciaCambio.getFechaVencimiento());
		diferenciaCambio.setFechaImputacion(resultadoDiferenciaCambio.getFechaImputacion());
		diferenciaCambio.setImporteAplicadoGenerado(resultadoDiferenciaCambio.getImporteAplicado());
		diferenciaCambio.setOrigenDocumentoGenerado(resultadoDiferenciaCambio.getOrigenDocumento());
		
		diferenciaCambio.setTipoComprobanteOriginal(resultadoDiferenciaCambio.getTipoComprobanteOriginal());
		diferenciaCambio.setClaseComprobanteOriginal(resultadoDiferenciaCambio.getClaseComprobanteOriginal());
		diferenciaCambio.setSucursalComprobanteOriginal(resultadoDiferenciaCambio.getSucursalComprobanteOriginal());
		diferenciaCambio.setNumeroComprobanteOriginal(resultadoDiferenciaCambio.getNumeroComprobanteOriginal());
		diferenciaCambio.setIdDocumentoCuentasCobranzaOriginal(resultadoDiferenciaCambio.getIdDocumentoCuentasCobranzaOriginal());
		
		return diferenciaCambio;
	}
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param medioPago
	 * @throws NegocioExcepcion 
	 */
	private void actualizarMediosPagoDeTransaccionPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobMedioPago medioPago, ShvCobDescobro descobro) throws NegocioExcepcion {
		
		// Actualizo los resultados de las diferentes llamadas en el descobro
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {
			
			if (medioPago.getTransaccion().getNumeroTransaccion().equals(resultadoSimulacion.getNumeroTransaccion())) {
				
				Long idOperacion = descobro.getOperacion().getIdOperacionOriginal();
					
				if (resultadoSimulacion instanceof ResultadoSimulacionDescobroCargoGeneradoMic
						&& medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					
					ShvCobMedioPagoDebitoProximaFacturaMic medioPagoMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;
					
					ResultadoSimulacionDescobroCargoGeneradoMic resultadoCargoGeneradoMic = (ResultadoSimulacionDescobroCargoGeneradoMic) resultadoSimulacion;  

					actualizarInformacionMensajeEstado(resultadoCargoGeneradoMic.getListaRespuestasInvocacion(), medioPago, false);
					
					if (!TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
						medioPagoMic.setEstadoCargoGenerado(resultadoCargoGeneradoMic.getEstadoCargoGenerado());
						
						//Guardo el cliente y el acuerdo solamente si no estan vacios
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getSistemaCcontracargo())){
							medioPagoMic.setSistemaAcuerdoContracargo(resultadoCargoGeneradoMic.getSistemaCcontracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getIdClienteLegadoContracargo())){
							medioPagoMic.setIdClienteLegadoAcuerdoContracargo(resultadoCargoGeneradoMic.getIdClienteLegadoContracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getAcuerdoContracargo())){
							medioPagoMic.setAcuerdoContracargo(resultadoCargoGeneradoMic.getAcuerdoContracargo().toString());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getEstadoAcuerdoContracargo())){
							medioPagoMic.setEstadoAcuerdoContracargo(resultadoCargoGeneradoMic.getEstadoAcuerdoContracargo());
						}
						ShvCobFactura factura = descobro.getTransaccionPorNumeroTransaccion(resultadoCargoGeneradoMic.getNumeroTransaccion()).getFactura();
						medioPagoMic = (ShvCobMedioPagoDebitoProximaFacturaMic) calcularOrigenContracargoDebitoProxima(medioPagoMic, factura, idOperacion, false);
						
//						medioPagoMic.setCobradorInteresesRealesNoReguladosTrasladados(resultadoCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados());
						medioPagoMic.setCobradorInteresesRealesReguladosTrasladados(resultadoCargoGeneradoMic.getInteresesRealesReguladosTrasladados());
						if (!Validaciones.isObjectNull(resultadoCargoGeneradoMic.getInteresesRealesReguladosTrasladados()) 
								&& resultadoCargoGeneradoMic.getInteresesRealesReguladosTrasladados().compareTo(BigDecimal.ZERO) > 0){
//							ShvCobFactura factura = descobro.getTransaccionPorNumeroTransaccion(resultadoCargoGeneradoMic.getNumeroTransaccion()).getFactura();
							medioPagoMic = (ShvCobMedioPagoDebitoProximaFacturaMic) calcularOrigenContracargoDebitoProxima(medioPagoMic, factura, idOperacion, true);
							
						}
					}
					
					if (resultadoCargoGeneradoMic.isRequiereBuscarAcuerdoActivo()) {
						autoCompletarAcuerdoActivoMic(medioPagoMic);
					}
					
				} else if(resultadoSimulacion instanceof ResultadoSimulacionDescobroCargoGeneradoCalipso 
						&& medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
					
					ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;
					
					ResultadoSimulacionDescobroCargoGeneradoCalipso resultadoCargoGeneradoCalipso = (ResultadoSimulacionDescobroCargoGeneradoCalipso) resultadoSimulacion;  

					actualizarInformacionMensajeEstado(resultadoCargoGeneradoCalipso.getListaRespuestasInvocacion(), medioPago, false);
					
					if (!TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
						medioPagoCalipso.setEstadoCargoGenerado(resultadoCargoGeneradoCalipso.getEstadoCargoGenerado());
						medioPagoCalipso.setEstadoAcuerdoContracargo(resultadoCargoGeneradoCalipso.getEstadoAcuerdoContracargo());
						
						//Guardo el cliente y el acuerdo solamente si no estan vacios
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getSistemaCcontracargo())){
							medioPagoCalipso.setSistemaAcuerdoContracargo(resultadoCargoGeneradoCalipso.getSistemaCcontracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getIdClienteLegadoContracargo())){
							medioPagoCalipso.setIdClienteLegadoAcuerdoContracargo(resultadoCargoGeneradoCalipso.getIdClienteLegadoContracargo());
						}
						if(!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getAcuerdoContracargo())){
							medioPagoCalipso.setAcuerdoContracargo(resultadoCargoGeneradoCalipso.getAcuerdoContracargo().toString());
						}
						ShvCobFactura factura = descobro.getTransaccionPorNumeroTransaccion(resultadoCargoGeneradoCalipso.getNumeroTransaccion()).getFactura();
						
						medioPagoCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) calcularOrigenContracargoDebitoProxima(medioPagoCalipso, factura, idOperacion, false);
						
						medioPagoCalipso.setCobradorInteresesRealesTrasladados(resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados());
						
						if (!Validaciones.isObjectNull(resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados()) 
								&& resultadoCargoGeneradoCalipso.getInteresesRealesTrasladados().compareTo(BigDecimal.ZERO) > 0){
							medioPagoCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso) calcularOrigenContracargoDebitoProxima(medioPagoCalipso, factura, idOperacion, true);
							
						}
					} 
					
				} else if (resultadoSimulacion instanceof ResultadoSimulacionDescobroMedioPagoShiva
						|| resultadoSimulacion instanceof ResultadoSimulacionDescobroMedioPagoUsuario
						|| resultadoSimulacion instanceof ResultadoSimulacionDescobroMedioPagoMic
						|| resultadoSimulacion instanceof ResultadoSimulacionDescobroMedioPagoCalipso) {
			
			
					if (!(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) 
							&& !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic)
							&& (!Validaciones.isNullOrEmpty(medioPago.getIdBusquedaRespuestaCobrador()) 
									&& !Validaciones.isNullOrEmpty(resultadoSimulacion.getIdBusquedaRespuestaCobrador())
									&& medioPago.getIdBusquedaRespuestaCobrador().equals(resultadoSimulacion.getIdBusquedaRespuestaCobrador())
									|| (
										(resultadoSimulacion instanceof ResultadoSimulacionDescobroMedioPagoCalipso)
										&& (
											(
											(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso)
										&& !Validaciones.isObjectNull(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getIdDocumentoCuentaCobranza())
										&& !Validaciones.isObjectNull(((ResultadoSimulacionDescobroMedioPagoCalipso) resultadoSimulacion).getIdDocumentoCuentaCobranza())
										&& ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getIdDocumentoCuentaCobranza().equals(((ResultadoSimulacionDescobroMedioPagoCalipso) resultadoSimulacion).getIdDocumentoCuentaCobranza())
											)
											||
											(
											(medioPago instanceof ShvCobMedioPagoCTA)
										&& !Validaciones.isObjectNull(((ShvCobMedioPagoCTA) medioPago).getIdDocumentoCuentaCobranza())
										&& !Validaciones.isObjectNull(((ResultadoSimulacionDescobroMedioPagoCalipso) resultadoSimulacion).getIdDocumentoCuentaCobranza())
										&& ((ShvCobMedioPagoCTA) medioPago).getIdDocumentoCuentaCobranza().equals(((ResultadoSimulacionDescobroMedioPagoCalipso) resultadoSimulacion).getIdDocumentoCuentaCobranza())
											)
										)
									   )
							)
						) {
						
						actualizarInformacionMensajeEstado(resultadoSimulacion.getListaRespuestasInvocacion(), medioPago, false);
						
					}
	
				} 
			
			}
		}
	}
	
	private void actualizarOperacionesRelacionadasEnDescobroPostSimulacion(List<ResultadoSimulacion> listaResultadoSimulacion, ShvCobDescobro descobro) throws NegocioExcepcion{

		// Agrego las operaciones relacionadas en el descobro
		descobro.getOperacionesRelacionadas().removeAll(descobro.getOperacionesRelacionadas());
		
		Set<ShvCobDescobroOperacionRelacionada> nuevasOperacionesRelacionadas = new HashSet<ShvCobDescobroOperacionRelacionada>();
		
		for (ResultadoSimulacion resultadoSimulacion : listaResultadoSimulacion) {
			
			if(resultadoSimulacion instanceof ResultadoSimulacionDescobroOperacionRelacionada){
				
				ResultadoSimulacionDescobroOperacionRelacionada resultadoOperacionRelacionada = (ResultadoSimulacionDescobroOperacionRelacionada) resultadoSimulacion;
				
				if(!Validaciones.isObjectNull(resultadoOperacionRelacionada.getSistema())){
					
					//seteo los campos comunes
					ShvCobDescobroOperacionRelacionada operacionRelacionada = new ShvCobDescobroOperacionRelacionada();
					operacionRelacionada.setSistema(resultadoOperacionRelacionada.getSistema());
					operacionRelacionada.setIdOperacion(resultadoOperacionRelacionada.getIdOperacion());
					operacionRelacionada.setNroTransaccion(resultadoOperacionRelacionada.getNumeroTransaccion());
					operacionRelacionada.setIdClienteLegado(resultadoOperacionRelacionada.getIdClienteLegado());
					operacionRelacionada.setImporteCobrado(resultadoOperacionRelacionada.getImporteCobrado());
					operacionRelacionada.setFechaCobranza(resultadoOperacionRelacionada.getFechaCobranza());
					operacionRelacionada.setIdOperacionPadre(resultadoOperacionRelacionada.getIdOperacionPadre());
					operacionRelacionada.setNroTransaccionPadre(resultadoOperacionRelacionada.getNroTransaccionPadre());
					
					
					if(resultadoSimulacion instanceof ResultadoSimulacionDescobroOperacionRelacionadaCalipso){
						
						ResultadoSimulacionDescobroOperacionRelacionadaCalipso resultadoOperacionRelacionadaCalipso = (ResultadoSimulacionDescobroOperacionRelacionadaCalipso) resultadoSimulacion;
						operacionRelacionada.setIdCobranza(resultadoOperacionRelacionadaCalipso.getIdCobranza());
						operacionRelacionada.setTipoComprobante(resultadoOperacionRelacionadaCalipso.getTipoComprobante());
						operacionRelacionada.setClaseComprobante(resultadoOperacionRelacionadaCalipso.getClaseComprobante());
						operacionRelacionada.setSucursalComprobante(resultadoOperacionRelacionadaCalipso.getSucursalComprobante());
						operacionRelacionada.setNumeroComprobante(resultadoOperacionRelacionadaCalipso.getNumeroComprobante());
						operacionRelacionada.setIdDocumentoCuentasCobranza(resultadoOperacionRelacionadaCalipso.getIdDocumentoCuentasCobranza());
						
					} else if(resultadoSimulacion instanceof ResultadoSimulacionDescobroOperacionRelacionadaMic){
						
						ResultadoSimulacionDescobroOperacionRelacionadaMic resultadoOperacionRelacionadaMic = (ResultadoSimulacionDescobroOperacionRelacionadaMic) resultadoSimulacion;
						operacionRelacionada.setTipoComprobante(resultadoOperacionRelacionadaMic.getTipoComprobante());
						operacionRelacionada.setNumeroReferenciaMic(resultadoOperacionRelacionadaMic.getNumeroReferenciaMic());
						
					} 
					operacionRelacionada.setDescobro(descobro);
					nuevasOperacionesRelacionadas.add(operacionRelacionada);					
				}			
				
			}

		}
		
		descobro.setOperacionesRelacionadas(nuevasOperacionesRelacionadas);
	}
	
	
	/**
	 * 
	 * @param descobro
	 * @return
	 */
	private boolean esSimulacionExitosa(ShvCobDescobro descobro) {
		
		boolean simulacionExitosa = true;
		
		for (ShvCobTransaccion transaccion : descobro.getTransaccionesOrdenadas()) {
			
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
			
			if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccion.getEstadoProcesamiento())){
				if(TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())) {
					simulacionExitosa = false;
					break;
				}
			}
			
		}
		
		return simulacionExitosa;
	}
	
	private void actualizarInformacionMensajeEstado(List<RespuestaInvocacion> listaRespuestasInvocacion, Modelo destino, Boolean restearTipoYMensajeEstado) {
		
		if(restearTipoYMensajeEstado){
			
			if (destino instanceof ShvCobMedioPago) {
				ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
				medioPago.setMensajeEstado(null);
				medioPago.setTipoMensajeEstado(null);
				
				Traza.auditoria(getClass(), "Se actualizo medio Pago id:" + medioPago.getIdMedioPago()
						+ " operacion id:" + medioPago.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
						+ " tipo mensaje estado: null"
						+ " mensaje estado: null (Diferencia de Cambio)"
						);
			} else if (destino instanceof ShvCobFactura) {
				ShvCobFactura factura = (ShvCobFactura) destino;
				factura.setMensajeEstado(null);
				factura.setTipoMensajeEstado(null);
				
				Traza.auditoria(getClass(), "Se actualizo factura id:" + factura.getIdFactura() 
						+ " operacion id:" + factura.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + factura.getSistemaOrigen().getDescripcion()
						+ " tipo mensaje estado: null"
						+ " mensaje estado: null (Diferencia de Cambio)"
						);
			}
			
		} else {
			
			StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);
			TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.OK;
			
			for (RespuestaInvocacion respuesta : listaRespuestasInvocacion) {
				
				// El resultado Warning tiene mas peso que el OK
				if (tipoMensajeEstado.equals(TipoMensajeEstadoEnum.OK) && TipoResultadoSimulacionEnum.WARNING.equals(respuesta.getResultado())) {
					tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;
				}
				
				// El resultado Error tiene mas peso que el OK o el Warning
				if ((tipoMensajeEstado.equals(TipoMensajeEstadoEnum.OK) || tipoMensajeEstado.equals(TipoMensajeEstadoEnum.WRN))
						&& (TipoResultadoSimulacionEnum.ERROR.equals(respuesta.getResultado()) || TipoResultadoSimulacionEnum.NOK.equals(respuesta.getResultado())))  {
					tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
				}
				
				if (TipoResultadoSimulacionEnum.ERROR_SERVICIO.equals(respuesta.getResultado())) {
					tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
				}
				
				if (!respuesta.getResultado().equals(TipoResultadoSimulacionEnum.OK)) {
					if (!Constantes.EMPTY_STRING.equals(detalleMensaje.toString().trim())) {
						detalleMensaje.append("<br>");
					}
					
					detalleMensaje.append(tipoMensajeEstado.getDescripcion());
					detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
					detalleMensaje.append(respuesta.getDescripcionError().toString().trim());
				}
			}
			
			if (destino instanceof ShvCobMedioPago) {
				ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
				medioPago.setMensajeEstado(detalleMensaje.toString());
				medioPago.setTipoMensajeEstado(tipoMensajeEstado);
				
				Traza.auditoria(getClass(), "Se actualizo medio Pago id:" + medioPago.getIdMedioPago()
						+ " operacion id:" + medioPago.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
						+ " tipo mensaje estado: " + tipoMensajeEstado.getDescripcion()
						+ " mensaje estado: " + detalleMensaje.toString()
						);
			} else if (destino instanceof ShvCobFactura) {
				ShvCobFactura factura = (ShvCobFactura) destino;
				factura.setMensajeEstado(detalleMensaje.toString());
				factura.setTipoMensajeEstado(tipoMensajeEstado);
				
				Traza.auditoria(getClass(), "Se actualizo factura id:" + factura.getIdFactura() 
						+ " operacion id:" + factura.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + factura.getSistemaOrigen().getDescripcion()
						+ " tipo mensaje estado: " + tipoMensajeEstado.getDescripcion()
						+ " mensaje estado: " + detalleMensaje.toString()
						);
			} else if (destino instanceof ShvCobTratamientoDiferencia) {
				ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
				tratamientoDiferencia.setMensajeEstado(detalleMensaje.toString());
				tratamientoDiferencia.setTipoMensajeEstado(tipoMensajeEstado);
				
				Traza.auditoria(getClass(), "Se actualizo tratamiento Diferencia id:" + tratamientoDiferencia.getIdTratamientoDiferencia()
						+ " operacion id:" + tratamientoDiferencia.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + tratamientoDiferencia.getTipoTratamientoDiferencia().getDescripcion()
						+ " tipo mensaje estado: " + tipoMensajeEstado.getDescripcion()
						+ " mensaje estado: " + detalleMensaje.toString()
						);
			}
		}
		
	}
	
	private ResultadoSimulacion simularDescobroFacturaDeSaldoACobrar(ShvCobFactura factura, Long idOperacion) throws NegocioExcepcion {

		//
		// Genero la respuesta del resultado de la apropiacion, esto siempre va a dar ok
		//
		ResultadoSimulacion resultadoSimulacion = new ResultadoSimulacion();
		resultadoSimulacion.setIdOperacion(idOperacion);
		resultadoSimulacion.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
		
		RespuestaInvocacion respuesta = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.OK,
				null,
				null);
			
		resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);
		
		return resultadoSimulacion;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz
	 * Se realiza directamente la simulacion del descobro en Shiva
	 * 
	 * @param listaMediosPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularDescobroShiva(
				List<ShvCobMedioPagoShiva> listaMediosPago, Long idOperacionOriginal) throws NegocioExcepcion {
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		List<Long> listaIdValores = new ArrayList<Long>();
		
		for (ShvCobMedioPago medioPago : listaMediosPago) {
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				listaIdValores.add(((ShvCobMedioPagoShiva)medioPago).getIdValor());
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

						if (valorSimplificado.getIdValor().equals(((ShvCobMedioPagoShiva)medioPago).getIdValor())) {

							List<RespuestaInvocacion> listaRespuestaInvocacion = new ArrayList<RespuestaInvocacion>();

							// <<VAL>> Validar que el documento haya sido cobrado/usado en su totalidad/suspendido si no se agrega el error
							if (!Estado.VAL_DISPONIBLE.equals(valorSimplificado.getWorkFlow().getEstado())
									&& !Estado.VAL_USADO.equals(valorSimplificado.getWorkFlow().getEstado())
									&& !Estado.VAL_SUSPENDIDO.equals(valorSimplificado.getWorkFlow().getEstado())
									) {
								String mensajeError = Utilidad.reemplazarMensajes(
										Mensajes.DESCOBROS_SIMULACION_ESTADO_NO_VALIDO, valorSimplificado.getWorkFlow().getEstado().descripcion());
								
								Traza.error(getClass(), "Id Medio Pago: " +  medioPago.getIdMedioPago() + " Id Valor:" + valorSimplificado.getIdValor() + " Mensaje:" + mensajeError);
								
								RespuestaInvocacion respuesta = new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.ERROR,
											Constantes.ERROR_GENERICO_DESCOBRO,
											mensajeError);
								
								listaRespuestaInvocacion.add(respuesta);
							
							//si el valor esta en estado disponible, entonces valido que el saldo sea correcto
							} else if(Estado.VAL_DISPONIBLE.equals(valorSimplificado.getWorkFlow().getEstado())){
								
								// <<VAL>> Validar que el saldo disponible del documento no supere el restante entre el importe original y lo usado
								// Ejemplo: Valor con Importe Original(1) $100 = Saldo Usado a Devolver(2) $60 + Saldo Disponible(3) $40
								// La suma entre lo acumulado de las anteriores transacciones $30 y el actual medio de pago $30 = $60 Saldo Usado a Devolver(2)
								// más el saldo disponible = $40 Saldo Disponible(3) tiene que ser menor o igual al importe original del valor = $100 Importe Original(1)
								// si es mayor quiere decir que se intenta devolver mas de lo que se uso entonces grabo el error
								BigDecimal importeADescobrar = BigDecimal.ZERO;
								
								importeADescobrar = importeADescobrar.add(medioPago.getImporte());
								
								BigDecimal importeValorReconstruido = importeADescobrar.add(valorSimplificado.getSaldoDisponible());
								
								if (!Validaciones.isObjectNull(medioPago.getMontoAcumuladoSimulacion())) {
									importeValorReconstruido = importeValorReconstruido.add(medioPago.getMontoAcumuladoSimulacion());
								}								
								
								if (importeValorReconstruido.compareTo(valorSimplificado.getImporte()) > 0) {
									String mensajeError = Utilidad.reemplazarMensajes(
											Mensajes.DESCOBROS_SIMULACION_DEVOLUCION_SALDO_MAYOR_A_LO_USADO, 
											Utilidad.formatCurrency(importeValorReconstruido, 2),
											Utilidad.formatCurrency(valorSimplificado.getImporte(), 2));
									
									Traza.error(getClass(), "Id Medio Pago: " +  medioPago.getIdMedioPago() + " Id Valor:" + valorSimplificado.getIdValor() +  " Mensaje:" + mensajeError);
									RespuestaInvocacion respuesta = new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.ERROR,
											Constantes.ERROR_GENERICO_DESCOBRO,
											mensajeError);
									
									listaRespuestaInvocacion.add(respuesta);
								}
							}

							
							// Genero la respuesta del resultado del descobro
							ResultadoSimulacionDescobroMedioPagoShiva resultadoSimulacion = new ResultadoSimulacionDescobroMedioPagoShiva();
							resultadoSimulacion.setIdMedioPago(medioPago.getIdMedioPago());
							resultadoSimulacion.setIdOperacion(idOperacionOriginal);
							resultadoSimulacion.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());

							// Si las validaciones fueron todas corretas, entonces debo generar una respuesta de OK
							if (!Validaciones.isCollectionNotEmpty(listaRespuestaInvocacion)) {
								
								RespuestaInvocacion respuesta = new RespuestaInvocacion(
												TipoResultadoSimulacionEnum.OK,
												null,
												null);
								
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
	 * @param idOperacionOriginal
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularDescobroMedioPagoUsuario(
							List<ShvCobMedioPagoUsuario> listaMediosPago, Long idOperacionOriginal) throws NegocioExcepcion {
	
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
	
		for (ShvCobMedioPago medioPago : listaMediosPago) {
		
			if (medioPago instanceof ShvCobMedioPagoUsuario) {

				// Genero la respuesta del resultado del descobro, esto siempre va a dar ok
				ResultadoSimulacionDescobroMedioPagoUsuario resultadoSimulacion = new ResultadoSimulacionDescobroMedioPagoUsuario();
				resultadoSimulacion.setIdMedioPago(medioPago.getIdMedioPago());
				resultadoSimulacion.setIdOperacion(idOperacionOriginal);
				resultadoSimulacion.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
	
				RespuestaInvocacion respuesta = new RespuestaInvocacion(
								TipoResultadoSimulacionEnum.OK,
								null,
								null);
							
				resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);

				// Agrego este resultado a la lista general
				listaResultadoSimulacion.add(resultadoSimulacion);
			}
		}

		return listaResultadoSimulacion;
	}


	/**
	 * 
	 * @param tratamientoDiferencia
	 * @param idOperacionOriginal
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ResultadoSimulacion simularDescobroTratamientoDiferencia(
							ShvCobTratamientoDiferencia tratamientoDiferencia, Long idOperacionOriginal) throws NegocioExcepcion {
	
		//
		// Genero la respuesta del resultado del descobro, esto siempre va a dar ok
		//
		ResultadoSimulacionDescobroTratamientoDiferencia resultadoSimulacion = new ResultadoSimulacionDescobroTratamientoDiferencia();
		resultadoSimulacion.setIdTratamientoDiferencia(tratamientoDiferencia.getIdTratamientoDiferencia());
		resultadoSimulacion.setIdOperacion(idOperacionOriginal);
		resultadoSimulacion.setNumeroTransaccion(tratamientoDiferencia.getTransaccion().getNumeroTransaccion());

		RespuestaInvocacion respuesta = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.OK,
						null,
						null);
					
		resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);

		return resultadoSimulacion;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint7
	 * @param listaMediosPagoClp
	 * @param facturaClp
	 * @param creditoProximaFacturaClp
	 * @param debitoProximaFacturaClp
	 * @param idOperacionOriginal
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularDescobroCalipso(List<ShvCobMedioPagoCalipso> listaMediosPagoClp, ShvCobFacturaCalipso facturaClp, 
			ShvCobTratamientoDiferencia creditoProximaFacturaClp, 
			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp, Long idOperacionCobro, Long idOperacionDescobro) throws NegocioExcepcion {

		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();

		EntradaCalipsoDescobroWS entrada = new EntradaCalipsoDescobroWS();
		entrada.setIdOperacion(idOperacionCobro);
		entrada.setIdOperacionDescobroMensajeria(idOperacionDescobro);
		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		entrada.setModoOperacion(SiNoEnum.SI);
		entrada.setFacturarContracargoFactura(SiNoEnum.NO);
		entrada.setFacturarContracargoCargo(SiNoEnum.NO);
		entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_SIM);

		//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 CALIPSO DESCOBRO
		
		//CREDITO PROXIMA CLP
		if (!Validaciones.isObjectNull(creditoProximaFacturaClp)) {
			
			entrada.setIdTransaccion(creditoProximaFacturaClp.getTransaccion().getIdTransaccion());
			entrada.setNumeroTransaccion(creditoProximaFacturaClp.getTransaccion().getNumeroTransaccion());
			entrada.setNumeroTransaccionFicticio(creditoProximaFacturaClp.getTransaccion().getNumeroTransaccionFicticio());
			//Detalle de Cargos  a revertir (Agrupador)
			if(!Validaciones.isObjectNull(creditoProximaFacturaClp.getIdMovMerCobrador())){
				
				entrada.setIdMovMer(new BigInteger(creditoProximaFacturaClp.getIdMovMerCobrador().toString()));
				
				if((Utilidad.decimalMayorACero(creditoProximaFacturaClp.getCobradorInteresesTrasladados())
						&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProximaFacturaClp.getTipoTratamientoDiferencia())
						&& SistemaEnum.CALIPSO.equals(creditoProximaFacturaClp.getSistemaAcuerdoContracargo()))
						|| (!Utilidad.decimalMayorACero(creditoProximaFacturaClp.getCobradorInteresesTrasladados())
								&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProximaFacturaClp.getTipoTratamientoDiferencia())
								&& SistemaEnum.CALIPSO.equals(creditoProximaFacturaClp.getSistemaAcuerdoContracargo()))){
						
					entrada.setFacturarContracargoCargo(SiNoEnum.SI);
					entrada.setAcuerdoFacturacionContracargoCargo(creditoProximaFacturaClp.getAcuerdoContracargo());
				}
			}
		
		} else {
			//FACTURA CLP
			if (!Validaciones.isObjectNull(facturaClp)) {
				
				entrada.setIdTransaccion(facturaClp.getTransaccion().getIdTransaccion());
				entrada.setNumeroTransaccion(facturaClp.getTransaccion().getNumeroTransaccion());
				entrada.setNumeroTransaccionFicticio(facturaClp.getTransaccion().getNumeroTransaccionFicticio());
				
				//Detalle de Factura a revertir (Agrupador)
				if(!Validaciones.isObjectNull(facturaClp.getIdCobranza())){
					
					entrada.setIdCobranza(new BigInteger(facturaClp.getIdCobranza().toString()));
					
					if(Utilidad.decimalMayorACero(facturaClp.getCobradorInteresesTrasladados())){
					
						//si tiene acuerdos, los valido
						if(!Validaciones.isNullOrEmpty(facturaClp.getAcuerdoTrasladoCargo())
								&& !Validaciones.isNullOrEmpty(facturaClp.getAcuerdoContracargo())){
							
							//Detalle del Contracargo a Generar por Factura (Agrupador) 
							if(SistemaEnum.CALIPSO.equals(facturaClp.getSistemaOrigen()) 
									&& facturaClp.getSistemaOrigen().equals(facturaClp.getSistemaAcuerdoContracargo())){
								
								entrada.setFacturarContracargoFactura(SiNoEnum.SI);
								entrada.setAcuerdoFacturacionContracargoFactura(facturaClp.getAcuerdoContracargo());
								
							}				
						}
					}
				}
			} 
			
			//DEBITO PROXIMA CLP
			if (!Validaciones.isObjectNull(debitoProximaFacturaClp)) {
				
				entrada.setIdTransaccion(debitoProximaFacturaClp.getTransaccion().getIdTransaccion());
				entrada.setNumeroTransaccion(debitoProximaFacturaClp.getTransaccion().getNumeroTransaccion());
				entrada.setNumeroTransaccionFicticio(debitoProximaFacturaClp.getTransaccion().getNumeroTransaccionFicticio());
				
				//Detalle de Cargos  a revertir (Agrupador)
				if(!Validaciones.isObjectNull(debitoProximaFacturaClp.getCobradorIdMovMer())){
					entrada.setIdMovMer(new BigInteger(debitoProximaFacturaClp.getCobradorIdMovMer().toString()));
					
					entrada = armarDebitoProximaCalipso(entrada, debitoProximaFacturaClp, listaResultadoSimulacion);
					
				}			
				
			}
		}
		
		//MEDIOS DE PAGO CLP
		if (Validaciones.isCollectionNotEmpty(listaMediosPagoClp)) {
			
			entrada.setIdTransaccion(listaMediosPagoClp.get(0).getTransaccion().getIdTransaccion());
			entrada.setNumeroTransaccion(listaMediosPagoClp.get(0).getTransaccion().getNumeroTransaccion());
			entrada.setNumeroTransaccionFicticio(listaMediosPagoClp.get(0).getTransaccion().getNumeroTransaccionFicticio());
			
			List<DetalleCTAoNotaCreditoDescobro> listaDetalleCTAoNotaCreditoEntrada = new ArrayList<DetalleCTAoNotaCreditoDescobro>();
			
			for (ShvCobMedioPagoCalipso medioPagoClp : listaMediosPagoClp) {
				
				DetalleCTAoNotaCreditoDescobro detalleCTAoNotaCreditoEntrada = new DetalleCTAoNotaCreditoDescobro();
				
				if(medioPagoClp instanceof ShvCobMedioPagoCTA
						&& !Validaciones.isObjectNull(((ShvCobMedioPagoCTA) medioPagoClp).getIdCobranza())){
						
						detalleCTAoNotaCreditoEntrada.setIdCobranza(new BigInteger(((ShvCobMedioPagoCTA) medioPagoClp).getIdCobranza().toString()));
						listaDetalleCTAoNotaCreditoEntrada.add(detalleCTAoNotaCreditoEntrada);
						
				} else if(medioPagoClp instanceof ShvCobMedioPagoNotaCreditoCalipso
						&& !Validaciones.isObjectNull(((ShvCobMedioPagoNotaCreditoCalipso) medioPagoClp).getIdCobranza())){
					
						detalleCTAoNotaCreditoEntrada.setIdCobranza(new BigInteger(((ShvCobMedioPagoNotaCreditoCalipso) medioPagoClp).getIdCobranza().toString()));
						listaDetalleCTAoNotaCreditoEntrada.add(detalleCTAoNotaCreditoEntrada);
				}
				
			}
			
			entrada.setListaCtaONotaCredito(listaDetalleCTAoNotaCreditoEntrada);
		}
		
		//envio el mensaje a mic e interpreto la salida para luego agregar todos los resultados a la lista
		//seteo el booleano en false, ya que en esta llamada busco todo no solo las operaciones relacionadas
		listaResultadoSimulacion.addAll(procesarSimulacionDescobroCalipso(entrada, false, listaMediosPagoClp, facturaClp, creditoProximaFacturaClp, debitoProximaFacturaClp));
		
		return listaResultadoSimulacion;
	}
	
	/**
	 * 
	 * @param entrada
	 * @param debitoProximaFacturaClp
	 * @param listaResultadoSimulacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private EntradaCalipsoDescobroWS armarDebitoProximaCalipso(EntradaCalipsoDescobroWS entrada,
			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp, List<ResultadoSimulacion> listaResultadoSimulacion) throws NegocioExcepcion{
	
		if((Utilidad.decimalMayorACero(debitoProximaFacturaClp.getCobradorInteresesTrasladados())
				&& SistemaEnum.CALIPSO.equals(debitoProximaFacturaClp.getSistemaOrigen())
				&& debitoProximaFacturaClp.getSistemaOrigen().equals(debitoProximaFacturaClp.getSistemaAcuerdoContracargo()))
				|| (!Utilidad.decimalMayorACero(debitoProximaFacturaClp.getCobradorInteresesTrasladados())
						&& SistemaEnum.CALIPSO.equals(debitoProximaFacturaClp.getSistemaOrigen())
						&& debitoProximaFacturaClp.getSistemaOrigen().equals(debitoProximaFacturaClp.getSistemaAcuerdoContracargo()))){
					
			entrada.setFacturarContracargoCargo(SiNoEnum.SI);
			entrada.setAcuerdoFacturacionContracargoCargo(debitoProximaFacturaClp.getAcuerdoContracargo());
		}
		
		
		return entrada;
	}
		
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param entrada
	 * @param buscoSoloOperacionRelacionada
	 * @param listaMediosPagoClp
	 * @param facturaClp
	 * @param creditoProximaFacturaClp
	 * @param debitoProximaFacturaClp
	 * @return
	 */
	private List<ResultadoSimulacion> procesarSimulacionDescobroCalipso(EntradaCalipsoDescobroWS entrada, boolean buscoSoloOperacionRelacionada
			, List<ShvCobMedioPagoCalipso> listaMediosPagoClp, ShvCobFacturaCalipso facturaClp, 
			ShvCobTratamientoDiferencia creditoProximaFacturaClp, ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp) throws NegocioExcepcion{
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		
		SalidaCalipsoDescobroWS salida = null;
		
		try {
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 CALIPSO DESCOBRO
			salida = clienteCalipsoServicio.descobrarCobro(entrada);
			
			if(!Validaciones.isObjectNull(salida.getResultadoLlamadaServicio())
					&& TipoResultadoSimulacionEnum.ERROR.codigoCalipso().equals(salida.getResultadoLlamadaServicio().getResultado())){
				
				Traza.error(getClass(), salida.getResultadoLlamadaServicio().getDescripcionError());
				listaResultadoSimulacion.addAll(errorServicioCalipso(listaMediosPagoClp, facturaClp, creditoProximaFacturaClp, debitoProximaFacturaClp, salida.getResultadoLlamadaServicio().getDescripcionError()));
				
			} else {
				
				if(!buscoSoloOperacionRelacionada){
					
					//Cargo el resultado de la factura de calipso
					listaResultadoSimulacion.addAll(cargarResultadoFacturaCalipso(entrada, salida, facturaClp));
					
					//Cargo los resultados de los medios de pago de calipso
					listaResultadoSimulacion.addAll(cargarResultadoMedioPagoCalipso(entrada, salida));
					
					//Cargo el resultado del cargo generado de calipso
					listaResultadoSimulacion.addAll(cargarResultadoCargoGeneradoCalipso(entrada, salida, creditoProximaFacturaClp, debitoProximaFacturaClp, listaMediosPagoClp, facturaClp));
					
					//Cargo el resultado de las operaciones relacionadas de calipso
					listaResultadoSimulacion.addAll(cargarResultadoYBuscarOperacionRelacionadaCalipso(entrada, salida));
					
					//Cargo el resultado de la diferencia de cambio
					listaResultadoSimulacion.addAll(cargarResultadoDiferenciaCambioCalipso(entrada, salida));
					
				} else {
					
					//Cargo el resultado de las operaciones relacionadas de calipso
					listaResultadoSimulacion.addAll(cargarResultadoYBuscarOperacionRelacionadaCalipso(entrada, salida));
				}
			}
			
			
		} catch (NegocioExcepcion e) {
			
			listaResultadoSimulacion.addAll(errorServicioCalipso(listaMediosPagoClp, facturaClp, creditoProximaFacturaClp, debitoProximaFacturaClp, e.getMensajeAuxiliar()));
			Traza.error(getClass(), e.getMensajeAuxiliar());
		}
			
		return listaResultadoSimulacion;
	}
	
	public RespuestaInvocacion filtrarRespuestaSimulacionDescobroCalipso(Resultado resultado, List<DetalleOperacionRelacionadaDescobro> listaOperacionesRelacionadas,
			EntradaCalipsoDescobroWS entrada, String nroDocumentoLegalCta) {
		
		boolean tieneResultado = true;
		boolean noTieneOperacionesRelacionadasPropiasParaFiltrar = true;
		
		if(!Validaciones.isObjectNull(resultado)){
			if(Constantes.COD_DESCOBRO_ERROR_8200_OP_RELAC.equals(resultado.getCodigoError())){
				Set<Long> listaOperacionesRelacionadasSinDuplicados = new HashSet<Long>();
				for(DetalleOperacionRelacionadaDescobro operacionRelacionada : listaOperacionesRelacionadas){
					
					String nroDocuemntoLegalCtaOpRel = null;
					
					if(!Validaciones.isObjectNull(operacionRelacionada.getDetalleDocumento())){
						
						nroDocuemntoLegalCtaOpRel = operacionRelacionada.getDetalleDocumento().getClaseComprobante().name() + "-"
												+ operacionRelacionada.getDetalleDocumento().getSucursalComprobante() +"-"
												+ operacionRelacionada.getDetalleDocumento().getNumeroComprobante();
					}
					
					if(!Validaciones.isObjectNull(operacionRelacionada)
						&& !Validaciones.isObjectNull(nroDocumentoLegalCta)
						&& nroDocumentoLegalCta.equals(nroDocuemntoLegalCtaOpRel)){
						
						listaOperacionesRelacionadasSinDuplicados.add(operacionRelacionada.getIdOperacion());
					}
					
				}
				listaOperacionesRelacionadasSinDuplicados.remove(new Long(0));
				listaOperacionesRelacionadasSinDuplicados.remove(entrada.getIdOperacion());
				
				if(!Validaciones.isCollectionNotEmpty(listaOperacionesRelacionadasSinDuplicados)){
					noTieneOperacionesRelacionadasPropiasParaFiltrar = false;
				}
			}				
		} else {
			tieneResultado = false;
		}		
		
		//si el acuerdo que responde el servicio esta activo, muestro la respuesta del servicio directamente
		if(tieneResultado && noTieneOperacionesRelacionadasPropiasParaFiltrar){
			
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultado.getResultado()),
					resultado.getCodigoError(),
					resultado.getDescripcionError());
			
			return resultadoInvocacion;
		}
		
		// si esto es false quiere decir que vamos a mostrarle un OK al usuario, ya que el unico problema que hay es una operacion relacionada propia
		//y esto no se debe mostrar
		if(!noTieneOperacionesRelacionadasPropiasParaFiltrar){
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.OK,
					null,
					null);
			
			return resultadoInvocacion;
		}
		return null;
	}
	
	/**
	 * 
	 * @param entrada
	 * @param salida
	 * @return
	 * @throws NegocioExcepcion 
	 */
	private List<ResultadoSimulacion> cargarResultadoFacturaCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida, ShvCobFacturaCalipso facturaClp) throws NegocioExcepcion{
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		//Detalle de Resultado de Reversión de Factura (Agrupador)
		if (!Validaciones.isObjectNull(salida.getDetalleFacturaDescobro())
				&& !Validaciones.isObjectNull(facturaClp)
				&& !Validaciones.isObjectNull(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getResultado())) {
			
			ResultadoSimulacionDescobroFactura respuestaSimulacionFacClp = new ResultadoSimulacionDescobroFactura();
			
			//aca seteo el id operacion original del cobro que se mando al cobrador
			respuestaSimulacionFacClp.setIdOperacion(new Long(salida.getIdOperacionTransaccion().substring(0,7)));
			respuestaSimulacionFacClp.setNumeroTransaccion(entrada.getNumeroTransaccion());
			respuestaSimulacionFacClp.setNumeroTransaccionFicticio(entrada.getNumeroTransaccionFicticio());
			respuestaSimulacionFacClp.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			respuestaSimulacionFacClp.setTipoInvocacion(salida.getTipoOperacion());
			
			respuestaSimulacionFacClp.setEstadoAcuerdoContracargo(salida.getDetalleFacturaDescobro().getEstadoAcuerdoFacturacion());
			
			respuestaSimulacionFacClp.setEstadoCargoGenerado(salida.getDetalleFacturaDescobro().getEstadoCargoGenerado());
			
			if(!Validaciones.isObjectNull(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura())){
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getResultado()),
						salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getCodigoError(),
						salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getDescripcionError());
				
				respuestaSimulacionFacClp.getListaRespuestasInvocacion().add(resultadoInvocacion);
			}
			
			listaResultadoSimulacion.add(respuestaSimulacionFacClp);
			
			//si tiene intereses trasladados, si facturarContracargo es no y el estado del cargo generado es facturado, entonces llamamos calipso
			if(Utilidad.decimalMayorACero(facturaClp.getCobradorInteresesTrasladados())
					&& SiNoEnum.NO.equals(entrada.getFacturarContracargoFactura())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(respuestaSimulacionFacClp.getEstadoCargoGenerado())){
				
				// Simular contracargo cruzado de factura Mic en Calipso
				respuestaSimulacionFacClp.getListaRespuestasInvocacion().add(simularContracargoCruzadoFacturaCalipsoEnMic(facturaClp));
				listaResultadoSimulacion.add(respuestaSimulacionFacClp);
				
			}
			
		}
		
		return listaResultadoSimulacion;
	}
	
	/**
	 * 
	 * @param entrada
	 * @param salida
	 * @return
	 */
	private List<ResultadoSimulacion> cargarResultadoMedioPagoCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida){
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de calipso por medios de pago
		if (Validaciones.isCollectionNotEmpty(salida.getListaDetalleMedioPagoDescobro())) {
			
			for(DetalleMedioPagoDescobro medioPago : salida.getListaDetalleMedioPagoDescobro()){
				
				if (!Validaciones.isObjectNull(medioPago)
						&& !Validaciones.isObjectNull(medioPago.getResultadoDescobroMedioPago())
						&& !Validaciones.isNullOrEmpty(medioPago.getResultadoDescobroMedioPago().getResultado())) {
					
					ResultadoSimulacionDescobroMedioPagoCalipso resultadoSimulacion = new ResultadoSimulacionDescobroMedioPagoCalipso();
					
					// Datos de respuesta
					resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
					resultadoSimulacion.setIdOperacion(entrada.getIdOperacion());		
					resultadoSimulacion.setNumeroTransaccion(entrada.getNumeroTransaccion());
					resultadoSimulacion.setNumeroTransaccionFicticio(entrada.getNumeroTransaccionFicticio());
					resultadoSimulacion.setTipoInvocacion(salida.getTipoOperacion());	

					String nroDocumentoLegalCta = null;
					
					if(!Validaciones.isObjectNull(medioPago.getIdMedioPago())){
						resultadoSimulacion.setTipoComprobante(medioPago.getIdMedioPago().getTipoComprobante());
						resultadoSimulacion.setClaseComprobante(medioPago.getIdMedioPago().getClaseComprobante());
						resultadoSimulacion.setSucursalComprobante(medioPago.getIdMedioPago().getSucursalComprobante());
						resultadoSimulacion.setNumeroComprobante(medioPago.getIdMedioPago().getNumeroComprobante());
						nroDocumentoLegalCta = medioPago.getIdMedioPago().getClaseComprobante() +"-"+medioPago.getIdMedioPago().getSucursalComprobante()+"-"+medioPago.getIdMedioPago().getNumeroComprobante();
					}

					if(!Validaciones.isObjectNull(medioPago.getIdDocCtasCob())){
						resultadoSimulacion.setIdDocumentoCuentaCobranza(new Long(medioPago.getIdDocCtasCob().toString()));
					}
					
						
					Resultado resultado = medioPago.getResultadoDescobroMedioPago();
					List<DetalleOperacionRelacionadaDescobro> listaOperacionesRelacionadas = salida.getListaOperacionesRelacionadas();
					
					
					resultadoSimulacion.getListaRespuestasInvocacion().add(filtrarRespuestaSimulacionDescobroCalipso(resultado, listaOperacionesRelacionadas, entrada, nroDocumentoLegalCta));
					
					
					listaResultadoSimulacion.add(resultadoSimulacion);
				}
			}
		}
		
		return listaResultadoSimulacion;
	}
	
	private List<ResultadoSimulacion> cargarResultadoCargoGeneradoCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida
			, ShvCobTratamientoDiferencia creditoProxima, ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProxima, List<ShvCobMedioPagoCalipso> listaMediosPagoClp
			, ShvCobFacturaCalipso facturaClp) throws NegocioExcepcion{
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		//Detalle de Cargo Generado (Agrupador)
		if (!Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro())
				&& !Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getResultado())) {
			
			ResultadoSimulacionDescobroCargoGeneradoCalipso respuestaSimulacionCargoClp = new ResultadoSimulacionDescobroCargoGeneradoCalipso();
			
			//aca seteo el id operacion original que se mando al cobrador
			respuestaSimulacionCargoClp.setIdOperacion(entrada.getIdOperacion());
			respuestaSimulacionCargoClp.setNumeroTransaccion(entrada.getNumeroTransaccion());
			respuestaSimulacionCargoClp.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			respuestaSimulacionCargoClp.setTipoInvocacion(salida.getTipoOperacion());
			
			respuestaSimulacionCargoClp.setEstadoAcuerdoContracargo(salida.getDetalleCargoGeneradoDescobro().getEstadoAcuerdoFacturacion());
			
			respuestaSimulacionCargoClp.setEstadoCargoGenerado(salida.getDetalleCargoGeneradoDescobro().getEstadoCargoGenerado());
			
			respuestaSimulacionCargoClp.setInteresesRealesTrasladados(salida.getDetalleCargoGeneradoDescobro().getInteresesRealesTrasladados());
			
			if(!Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado())){
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getResultado()),
						salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getCodigoError(),
						salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getDescripcionError());
				
				respuestaSimulacionCargoClp.getListaRespuestasInvocacion().add(resultadoInvocacion);
			}
			
			listaResultadoSimulacion.add(respuestaSimulacionCargoClp);
			
			//si facturarContracargo es no y el estado del cargo generado es facturado, entonces llamamos calipso
			if(SiNoEnum.NO.equals(entrada.getFacturarContracargoCargo())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(respuestaSimulacionCargoClp.getEstadoCargoGenerado())){
			
				//llamadas contracargo cruzado
				if(!Validaciones.isObjectNull(creditoProxima)){
						
					ShvCobMedioPagoCalipso medioPagoClp = listaMediosPagoClp.get(0);
					
					// Simular CARGO contracargo cruzado de credito a proxima Calipso en Mic
					respuestaSimulacionCargoClp.getListaRespuestasInvocacion().add(simularContracargoCruzadoCreditoCalipsoEnMic(creditoProxima, medioPagoClp, false));
					listaResultadoSimulacion.add(respuestaSimulacionCargoClp);
					
					if(Utilidad.decimalMayorACero(creditoProxima.getCobradorInteresesTrasladados())){
						// Simular INTERES contracargo cruzado de credito a proxima Calipso en Mic
						respuestaSimulacionCargoClp.getListaRespuestasInvocacion().add(simularContracargoCruzadoCreditoCalipsoEnMic(creditoProxima, medioPagoClp, true));
						listaResultadoSimulacion.add(respuestaSimulacionCargoClp);
					}
					
				} else if(!Validaciones.isObjectNull(debitoProxima)) {
					
					// Simular CARGO contracargo cruzado de Debito a proxima Calipso en Mic
					respuestaSimulacionCargoClp.getListaRespuestasInvocacion().add(simularContracargoCruzadoDebitoCalipsoEnMic(debitoProxima, facturaClp,  false));
					listaResultadoSimulacion.add(respuestaSimulacionCargoClp);
					
					if(Utilidad.decimalMayorACero(debitoProxima.getCobradorInteresesTrasladados())){
						// Simular INTERES contracargo cruzado de Debito a proxima Calipso en Mic
						respuestaSimulacionCargoClp.getListaRespuestasInvocacion().add(simularContracargoCruzadoDebitoCalipsoEnMic(debitoProxima, facturaClp, true));
						listaResultadoSimulacion.add(respuestaSimulacionCargoClp);
					}
				}
			}
		}
		
		return listaResultadoSimulacion;
	}
	
	private List<ResultadoSimulacion> cargarResultadoYBuscarOperacionRelacionadaCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de calipso por operaciones relacionadas
		if (Validaciones.isCollectionNotEmpty(salida.getListaOperacionesRelacionadas())) {
			
			for(DetalleOperacionRelacionadaDescobro operacionRelacionada : salida.getListaOperacionesRelacionadas()){
				
				String operacionTransaccion = operacionRelacionada.getIdOperacion().toString()+"."+operacionRelacionada.getNumeroTransaccion().toString();
				
				Traza.auditoria(getClass(),"[Op. Rel. CLP Condicion Op. Cobro: "+operacionRelacionada.getIdOperacion().toString()+" != Op. Rel.: "+entrada.getIdOperacion().toString()+"]");
				//Cargo la operacion relacionada solo si la operacion es diferente de la operacion que realizo la llamada a los legados
				if(!Validaciones.isObjectNull(operacionRelacionada) 
						&& !Validaciones.isObjectNull(operacionRelacionada.getSistema())
						&& !operacionRelacionada.getIdOperacion().equals(entrada.getIdOperacion())
						&& !operacionesRepetidas.contains(operacionTransaccion)){
					 
					operacionesRepetidas.add(operacionTransaccion);
					Traza.auditoria(getClass(),"[Op. Rel. CLP Agrego Op. Cobro: "+operacionTransaccion+"]");
					
					//Cargo los datos que me llegaron de la respuesta en un objeto resultado para despues impactar en la base de datos
					listaResultadoSimulacion.add(cargarResultadoOperacionRelacionadaCalipso(entrada, operacionRelacionada));
					
					// Consulto a SHIVA para ver si tiene otro padre (operacion relacionada)
					if(SistemaEnum.SHIVA.equals(operacionRelacionada.getSistema())){
						
						//Busco si hay operaciones relacionadas a la de shiva
						listaResultadoSimulacion.addAll(buscarOperacionesRelacionadasMicYCalipso(operacionRelacionada.getIdOperacion(), entrada.getIdOperacionDescobroMensajeria()));
					}
				}			
			}
		}		
		
		return listaResultadoSimulacion;
	}
	
	private ResultadoSimulacion cargarResultadoOperacionRelacionadaCalipso(EntradaCalipsoDescobroWS entrada, DetalleOperacionRelacionadaDescobro operacionRelacionadaClp){
		
		ResultadoSimulacionDescobroOperacionRelacionadaCalipso resultadoSimulacion = new ResultadoSimulacionDescobroOperacionRelacionadaCalipso();
		
		if(!Validaciones.isObjectNull(operacionRelacionadaClp)){
			resultadoSimulacion.setIdOperacion(operacionRelacionadaClp.getIdOperacion());
			resultadoSimulacion.setNumeroTransaccion(operacionRelacionadaClp.getNumeroTransaccion());
			resultadoSimulacion.setNumeroTransaccionFicticio(operacionRelacionadaClp.getNumeroTransaccionFicticio());
			resultadoSimulacion.setIdCobranza(operacionRelacionadaClp.getIdCobranza());
			resultadoSimulacion.setSistema(operacionRelacionadaClp.getSistema());
			
			if(!Validaciones.isObjectNull(operacionRelacionadaClp.getDetalleDocumento())){
				resultadoSimulacion.setTipoComprobante(operacionRelacionadaClp.getDetalleDocumento().getTipoComprobante());
				resultadoSimulacion.setClaseComprobante(operacionRelacionadaClp.getDetalleDocumento().getClaseComprobante());
				resultadoSimulacion.setSucursalComprobante(operacionRelacionadaClp.getDetalleDocumento().getSucursalComprobante());
				resultadoSimulacion.setNumeroComprobante(operacionRelacionadaClp.getDetalleDocumento().getNumeroComprobante());
			}
			
			if(!Validaciones.isObjectNull(operacionRelacionadaClp.getIdDocCtasCob())){
				resultadoSimulacion.setIdDocumentoCuentasCobranza(Long.valueOf(String.valueOf(operacionRelacionadaClp.getIdDocCtasCob())));
			}
			if(!Validaciones.isObjectNull(operacionRelacionadaClp.getCodigoCliente())){
				resultadoSimulacion.setIdClienteLegado(Long.valueOf(String.valueOf(operacionRelacionadaClp.getCodigoCliente())));
			}
			resultadoSimulacion.setImporteCobrado(operacionRelacionadaClp.getImporteCobrado());
			resultadoSimulacion.setFechaCobranza(operacionRelacionadaClp.getFechaCobranza());
			resultadoSimulacion.setIdOperacionPadre(entrada.getIdOperacion());
			resultadoSimulacion.setNroTransaccionPadre(entrada.getNumeroTransaccionFicticio());
		}
		
		return resultadoSimulacion;
	}
	
	private List<ResultadoSimulacion> cargarResultadoDiferenciaCambioCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida){
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de calipso por diferencia de cambio
		if (Validaciones.isCollectionNotEmpty(salida.getListaNotasCreditoDebitoDescobro())) {
			ResultadoSimulacionDescobroDiferenciaCambio resultadoSimulacion = new ResultadoSimulacionDescobroDiferenciaCambio();
			
			for(DetalleNotaCreditoDebitoDescobro diferenciaCambio : salida.getListaNotasCreditoDebitoDescobro()){
				if (!Validaciones.isObjectNull(diferenciaCambio) 
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado())
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal())
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado())) {
					
					resultadoSimulacion.setSistemaOrigen(SistemaEnum.CALIPSO);
					
					resultadoSimulacion.setIdOperacion(entrada.getIdOperacionDescobroMensajeria());
					
					String idOperacionTransaccionCobro = salida.getIdOperacionTransaccion();
					
					//Separo el idOperacion del nroTransaccion y me quedo con el nro de transaccion
					if(!Validaciones.isNullEmptyOrDash(idOperacionTransaccionCobro) 
							&& idOperacionTransaccionCobro.length() == 13
							&& idOperacionTransaccionCobro.contains(".")){
						
						Integer nroTransaccion = new Integer(idOperacionTransaccionCobro.substring(8, idOperacionTransaccionCobro.length()));
						
						resultadoSimulacion.setNumeroTransaccion(nroTransaccion);
					}
					
					
					// Datos de respuesta - Generado
					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getIdCobranza())){
						resultadoSimulacion.setIdCobranza(new Long(diferenciaCambio.getNotaCreDebGenerado().getIdCobranza().toString()));
					}
					
					resultadoSimulacion.setImporte(diferenciaCambio.getNotaCreDebGenerado().getImporte());
					resultadoSimulacion.setImporteCapital(diferenciaCambio.getNotaCreDebGenerado().getImporteCapital());
					resultadoSimulacion.setImporteImpuestos(diferenciaCambio.getNotaCreDebGenerado().getImporteImpuestos());
					
					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado())){
						
						resultadoSimulacion.setTipoComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getTipoComprobante());
						resultadoSimulacion.setClaseComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getClaseComprobante());
						resultadoSimulacion.setNumeroComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getNumeroComprobante());
						resultadoSimulacion.setSucursalComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getSucursalComprobante());
					}

					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getIdDocumentoCuentasCobranza())){
						resultadoSimulacion.setIdDocumentoCuentasCobranza(new Long(diferenciaCambio.getNotaCreDebGenerado().getIdDocumentoCuentasCobranza().toString()));
					}
					
					resultadoSimulacion.setFechaVencimiento(diferenciaCambio.getNotaCreDebGenerado().getFechaVencimiento());
					resultadoSimulacion.setFechaImputacion(diferenciaCambio.getNotaCreDebGenerado().getFechaImputacion());
					resultadoSimulacion.setImporteAplicado(diferenciaCambio.getNotaCreDebGenerado().getImporteAplicado());
					resultadoSimulacion.setOrigenDocumento(diferenciaCambio.getNotaCreDebGenerado().getOrigenDelDocumento());
					
					
					// Datos de respuesta - Original
					resultadoSimulacion.setTipoComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getTipoComprobante());
					resultadoSimulacion.setClaseComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getClaseComprobante());
					resultadoSimulacion.setNumeroComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getNumeroComprobante());
					resultadoSimulacion.setSucursalComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getSucursalComprobante());

					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal().getIdDocumentoCuentasCobranza())){
						resultadoSimulacion.setIdDocumentoCuentasCobranzaOriginal(new Long(diferenciaCambio.getNotaCreDebOriginal().getIdDocumentoCuentasCobranza().toString()));
					}
					
					listaResultadoSimulacion.add(resultadoSimulacion);
				}
			}
		}
		
		return listaResultadoSimulacion;
	}
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param listaMediosPagoClp
	 * @param facturaClp
	 * @param creditoProximaFacturaClp
	 * @param debitoProximaFacturaClp
	 * @param mensajeError
	 * @return
	 */
	private List<ResultadoSimulacion> errorServicioCalipso(List<ShvCobMedioPagoCalipso> listaMediosPagoClp, ShvCobFacturaCalipso facturaClp, 
			ShvCobTratamientoDiferencia creditoProximaFacturaClp, 
			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp, String mensajeError) {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.ERROR_SERVICIO,
				null,
				mensajeError);
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		//ERROR FACTURA CALIPSO
		if (!Validaciones.isObjectNull(facturaClp)) {
			ResultadoSimulacionDescobroFactura resultadoSimulacion = new ResultadoSimulacionDescobroFactura();
			
			resultadoSimulacion.setIdOperacion(facturaClp.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacion.setIdOperacionShiva(facturaClp.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacion.setNumeroTransaccion(facturaClp.getTransaccion().getNumeroTransaccion());
			resultadoSimulacion.setNumeroTransaccionFicticio(facturaClp.getTransaccion().getNumeroTransaccionFicticio());
			resultadoSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
			
			listaResultadoSimulacion.add(resultadoSimulacion);
		}
		
		//ERROR MEDIOS DE PAGO CALIPSO
		if(Validaciones.isCollectionNotEmpty(listaMediosPagoClp)){
			
			for (ShvCobMedioPagoCalipso medioPagoClp : listaMediosPagoClp) {
				ResultadoSimulacionDescobroMedioPagoCalipso resultadoSimulacionMp = new ResultadoSimulacionDescobroMedioPagoCalipso(); 
				resultadoSimulacionMp.setIdOperacion(medioPagoClp.getTransaccion().getOperacion().getIdOperacion());
				resultadoSimulacionMp.setIdOperacionShiva(medioPagoClp.getTransaccion().getOperacionTransaccionFormateado());
				
				if (medioPagoClp instanceof ShvCobMedioPagoNotaCreditoCalipso) {
					ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPagoClp;
					
					resultadoSimulacionMp.setTipoComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante());
					resultadoSimulacionMp.setClaseComprobante(medioPagoNotaCreditoCalipso.getClaseComprobante());
					resultadoSimulacionMp.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
					resultadoSimulacionMp.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
				} else {
					ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPagoClp;
					
					resultadoSimulacionMp.setTipoComprobante(medioPagoCTA.getTipoComprobante());
					resultadoSimulacionMp.setClaseComprobante(medioPagoCTA.getClaseComprobante());
					resultadoSimulacionMp.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
					resultadoSimulacionMp.setNumeroComprobante(medioPagoCTA.getNroComprobante());
				}
				
				resultadoSimulacionMp.getListaRespuestasInvocacion().add(resultadoInvocacion);
				listaResultadoSimulacion.add(resultadoSimulacionMp);
			}	
		}
		
		// ERROR CREDITO PROXIMA CALIPSO
		if(!Validaciones.isObjectNull(creditoProximaFacturaClp)){
			ResultadoSimulacionDescobroCargoGeneradoCalipso resultadoSimulacionCg = new ResultadoSimulacionDescobroCargoGeneradoCalipso();
			
			resultadoSimulacionCg.setIdOperacion(creditoProximaFacturaClp.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacionCg.setIdOperacionShiva(creditoProximaFacturaClp.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacionCg.setNumeroTransaccion(creditoProximaFacturaClp.getTransaccion().getNumeroTransaccion());
			resultadoSimulacionCg.setNumeroTransaccionFicticio(creditoProximaFacturaClp.getTransaccion().getNumeroTransaccionFicticio());
			resultadoSimulacionCg.getListaRespuestasInvocacion().add(resultadoInvocacion);
			
			listaResultadoSimulacion.add(resultadoSimulacionCg);
		}
		
		// ERROR DEBITO PROXIMA CALIPSO
		if(!Validaciones.isObjectNull(debitoProximaFacturaClp)){
			ResultadoSimulacionDescobroCargoGeneradoCalipso resultadoSimulacionCg = new ResultadoSimulacionDescobroCargoGeneradoCalipso();
			
			resultadoSimulacionCg.setIdOperacion(debitoProximaFacturaClp.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacionCg.setIdOperacionShiva(debitoProximaFacturaClp.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacionCg.setNumeroTransaccion(debitoProximaFacturaClp.getTransaccion().getNumeroTransaccion());
			resultadoSimulacionCg.setNumeroTransaccionFicticio(debitoProximaFacturaClp.getTransaccion().getNumeroTransaccionFicticio());
			resultadoSimulacionCg.getListaRespuestasInvocacion().add(resultadoInvocacion);
			
			listaResultadoSimulacion.add(resultadoSimulacionCg);
		}
		
		return listaResultadoSimulacion;
	}
	
	
	/**
	 * Calcula el cargo origen de acuerdo a los medios de pago residentes
	 * 
	 * @param 
	 * @param medioPago, el medio de pago sobre el cuál está aplicando el tratamiento de la diferencia
	 * @param idOperacion
	 * @return
	 */
	private ShvCobTratamientoDiferencia calcularOrigenContracargoCreditoProxima(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPago medioPago, Long idOperacion, boolean esInteres,SistemaEnum sistema) {
	
		String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		
		if(!esInteres){
			
			OrigenCargoEnum origenCargo  = null;		
			String leyendaFacturaCargo   = null;
			
			if(SistemaEnum.MIC.equals(sistema)){
				
				if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_NC;

				} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) 
							|| TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_REMANENTE;

				} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_PAG_CTA;

				} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_CHEQUE;

				} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_DEPOSITO;

				} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_TRANS;

				} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_INTER;
				}
				
			} else if(SistemaEnum.CALIPSO.equals(sistema)){
				
				if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_NC;

				} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) 
							|| TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_REMANENTE;

				} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_PAG_CTA;

				} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_CHEQUE;

				} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_DEPOSITO;

				} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_TRANS;

				} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_INTER;
				}
			}
			
			leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.cargo." + origenCargo.name()); 
			leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, medioPago.getReferencia(), idOperacionFormateado);
			tratamientoDiferencia.setOrigenCargo(origenCargo);
			tratamientoDiferencia.setLeyendaFacturaCargo(leyendaFacturaCargo);
			
		} else {
			
			OrigenCargoEnum origenInteres  = null;
			String leyendaFacturaInteres = null;
			
			if(SistemaEnum.MIC.equals(sistema)){
				
				if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_NC;

				} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) 
							|| TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_REMANENTE;

				} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_PAG_CTA;

				} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_CHEQUE;

				} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_DEPOSITO;

				} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_TRANS;

				} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_INTER;
				}
				
			} else if(SistemaEnum.CALIPSO.equals(sistema)){
				
				if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_NC;

				} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago()) 
							|| TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_REMANENTE;

				} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_PAG_CTA;

				} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
							|| TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_CHEQUE;

				} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_DEPOS;

				} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_TRANS;

				} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_INTER;
				}
			}
			
			leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.intereses." + origenInteres.name());
			leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, medioPago.getReferencia(), idOperacionFormateado);
			tratamientoDiferencia.setOrigenInteres(origenInteres);
			tratamientoDiferencia.setLeyendaFacturaInteres(leyendaFacturaInteres);
		}
		
		return tratamientoDiferencia;
	}

	/**
	 * 
	 * @param medioPagoDebitoProxima (puede ser un debito a proxima mic o calipso)
	 * @param factura
	 * @param idOperacion
	 * @return
	 */
	private ShvCobMedioPagoUsuario calcularOrigenContracargoDebitoProxima(ShvCobMedioPagoUsuario medioPagoDebitoProxima, ShvCobFactura factura, Long idOperacion, boolean esInteres) { 

		if(!Validaciones.isObjectNull(factura)){
			
			String referenciaFactura = "";
			
			if (TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante().getIdTipoComprobante())) {
				referenciaFactura = ((ShvCobFacturaMic)factura).getIdReferenciaFactura();
			} else {
				referenciaFactura = Utilidad.parsearNroDocNoDuc(
						factura.getClaseComprobante(), factura.getSucursalComprobante(), factura.getNumeroComprobante());	
			}
	
			String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
			
			if(!esInteres){
	
				OrigenCargoEnum origenCargo = null;
				
				if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
	
					origenCargo = OrigenCargoEnum.CC_CLP_TRAS_SALDO_FACTURA;
					String leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.cargo." + origenCargo.name());
					leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, referenciaFactura, idOperacionFormateado);
					((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoDebitoProxima).setOrigenCargo(origenCargo);
					((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoDebitoProxima).setLeyendaFacturaCargo(leyendaFacturaCargo);
					
				} else if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
					
					origenCargo = OrigenCargoEnum.CC_GEN_TRAS_SALDO_FACTURA;
					String leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.cargo." + origenCargo.name());
					leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, referenciaFactura, idOperacionFormateado);
					((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoDebitoProxima).setOrigenCargo(origenCargo);
					((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoDebitoProxima).setLeyendaFacturaCargo(leyendaFacturaCargo);					
				}
				
			} else {
				
				OrigenCargoEnum origenInteres = null;
				
				if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
	
					origenInteres = OrigenCargoEnum.CC_INT_CLP_TRAS_SALDO_FACTURA;
					String leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.intereses." + origenInteres.name());
					leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, referenciaFactura, idOperacionFormateado);
					((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoDebitoProxima).setOrigenInteres(origenInteres);
					((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoDebitoProxima).setLeyendaFacturaInteres(leyendaFacturaInteres);
					
				} else if (medioPagoDebitoProxima instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
	
					origenInteres = OrigenCargoEnum.CC_INT_GEN_TRAS_SALDO_FACTURA;
					String leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.intereses." + origenInteres.name());
					leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, referenciaFactura, idOperacionFormateado);
					((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoDebitoProxima).setOrigenInteres(origenInteres);
					((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoDebitoProxima).setLeyendaFacturaInteres(leyendaFacturaInteres);
				}
			}
		}
		
		return medioPagoDebitoProxima;
	}
	
	
	
	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoCreditoMicEnCalipso(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPagoMic medioPago
			, boolean esContracargoInteres) throws NegocioExcepcion {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion();

		if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
			
			Long idOperacionCobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion();
			
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 CALIPSO CARGO CRED
			EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
			
			entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
			entrada.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
			entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(tratamientoDiferencia.getTransaccion().getNumeroTransaccion().toString(), 5));
			entrada.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion().toString());
			
			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
			entrada.setModoOperacion(SiNoEnum.SI);

			DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
			detalleCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoContracargo());
			detalleCargo.setFechaDesde(new Date());
			
			if(!esContracargoInteres){
				detalleCargo.setImporte(tratamientoDiferencia.getImporte());
				detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
				detalleCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
				entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_SIM);
			} else {
				detalleCargo.setImporte(tratamientoDiferencia.getCobradorInteresesRealesNoReguladosTrasladados().add(tratamientoDiferencia.getCobradorInteresesRealesReguladosTrasladados()));
				detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
				detalleCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
				entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES_SIM);
			}
					
			entrada.setDetalleCargo(detalleCargo);
					
			SalidaCalipsoCargosWS respuesta = null;

			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 CALIPSO CARGO CRED
				respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.DESCOBRO);
				
				// Resultados posibles: OK / NOK / ERR

				resultadoInvocacion = new RespuestaInvocacion(
									TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()),
									respuesta.getResultado().getCodigoError(),
									respuesta.getResultado().getDescripcionError());
									
			 	
			} catch (NegocioExcepcion e) {
				
				// Resultados posibles: OK / NOK / ERR

				resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.ERROR_SERVICIO,
						null,
						e.getMensajeAuxiliar());
				
			}
			
		}
		
		return resultadoInvocacion;
		
	}
	
	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoDebitoMicEnCalipso(ShvCobMedioPagoDebitoProximaFacturaMic medioPago, ShvCobFactura factura, boolean esContracargoInteres) throws NegocioExcepcion {
		
		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion();
		
		if(!Validaciones.isObjectNull(medioPago)){
			
			Long idOperacionCobro = medioPago.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = medioPago.getTransaccion().getOperacion().getIdOperacion();
			
			medioPago = (ShvCobMedioPagoDebitoProximaFacturaMic) calcularOrigenContracargoDebitoProxima(medioPago, factura, idOperacionCobro, esContracargoInteres);
			
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 CALIPSO CARGO DEB
			EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
			
			entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
			entrada.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
			entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(medioPago.getTransaccion().getNumeroTransaccion().toString(), 5));
			entrada.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().toString());
			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
			entrada.setModoOperacion(SiNoEnum.SI);

			DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
			detalleCargo.setAcuerdoFacturacion(medioPago.getAcuerdoContracargo());
			detalleCargo.setFechaDesde(new Date());
			
			if(!esContracargoInteres){
				detalleCargo.setImporte(medioPago.getImporte()); 
				detalleCargo.setOrigenCargo(medioPago.getOrigenCargo());
				detalleCargo.setLeyendaFacturaCargo(medioPago.getLeyendaFacturaCargo());
				entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_SIM);
			} else {
				detalleCargo.setImporte(medioPago.getCobradorInteresesTrasladados()); 
				detalleCargo.setOrigenCargo(medioPago.getOrigenInteres());
				detalleCargo.setLeyendaFacturaInteres(medioPago.getLeyendaFacturaInteres());
				entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES_SIM);
			}
			
			entrada.setDetalleCargo(detalleCargo);
			
			SalidaCalipsoCargosWS respuesta = null;

			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 CALIPSO CARGO DEB
				respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.DESCOBRO);
				
				// Resultados posibles: OK / NOK / ERR
				resultadoInvocacion = new RespuestaInvocacion(
									TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()),
									respuesta.getResultado().getCodigoError(),
									respuesta.getResultado().getDescripcionError());
									
			 	
			} catch (NegocioExcepcion e) {
				
				// Resultados posibles: OK / NOK / ERR
				resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.ERROR_SERVICIO,
						null,
						e.getMensajeAuxiliar());
				
			}
		}
		
		return resultadoInvocacion;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoFacturaMicEnCalipso(ShvCobFactura factura, BigDecimal interesesTrasladados, boolean esInteresRegulado) throws NegocioExcepcion {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion();

		if (!Validaciones.isObjectNull(factura)) {
			
			Long idOperacionCobro = factura.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = factura.getTransaccion().getOperacion().getIdOperacion();
			
			factura = calcularOrigenContracargoFactura(factura, idOperacionCobro, esInteresRegulado);
			
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 CALIPSO CARGO FAC
			EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
			
			entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
			entrada.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
			entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(factura.getTransaccion().getNumeroTransaccion().toString(), 5));
			entrada.setIdTransaccion(factura.getTransaccion().getIdTransaccion().toString());
			entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES_SIM);
			
			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
			entrada.setModoOperacion(SiNoEnum.SI);

			DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
			detalleCargo.setAcuerdoFacturacion(factura.getAcuerdoContracargo());
			 
			//Se manda con estos campos cargados de esta forma para que genere el contracargo cruzado
			detalleCargo.setFechaDesde(new Date());
			detalleCargo.setTipoMora(null);
			detalleCargo.setImporte(interesesTrasladados);
			detalleCargo.setOrigenCargo(factura.getOrigenCargo());
			detalleCargo.setLeyendaFacturaInteres(factura.getLeyendaFacturaInteres());
			
			entrada.setDetalleCargo(detalleCargo);
					
			SalidaCalipsoCargosWS respuesta = null;

			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 CALIPSO CARGO FAC
				respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.DESCOBRO);

				// Resultados posibles: OK / NOK / ERR
				resultadoInvocacion = new RespuestaInvocacion(
									TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(respuesta.getResultado().getResultado()),
									respuesta.getResultado().getCodigoError(),
									respuesta.getResultado().getDescripcionError());
									
			} catch (NegocioExcepcion e) {
				
				// Resultados posibles: OK / NOK / ERR
				resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoSimulacionEnum.ERROR_SERVICIO,
						null,
						e.getMensajeAuxiliar());
				
			}
		}
		
		return resultadoInvocacion;
	}
	
	/**
	 * 
	 * @param listaMediosPagoAEnviar
	 * @param facturaMic
	 * @param creditoProximaFacturaMic
	 * @param debitoProximaFacturaMic
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> simularDescobroMic(List<ShvCobMedioPagoMic> listaMediosPagoAEnviar, ShvCobFacturaMic facturaMic,
			ShvCobTratamientoDiferencia creditoProximaFacturaMic, ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic, Long idOperacionCobro, Long idOperacionDescobro) throws NegocioExcepcion {
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 MIC DESCOBRO
		MicTransaccionDescobroDto mensajeMIC = new MicTransaccionDescobroDto();
		mensajeMIC.setIdOperacion(idOperacionCobro);
		mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		mensajeMIC.setFacturarContracargoCargo(SiNoEnum.NO);
		mensajeMIC.setFacturarContracargoFactura(SiNoEnum.NO);
		
		//CREDITO PROXIMA MIC
		if (!Validaciones.isObjectNull(creditoProximaFacturaMic)) {
			
			// Armo mensaje descobro
			mensajeMIC.setIdTransaccion(creditoProximaFacturaMic.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(creditoProximaFacturaMic.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(creditoProximaFacturaMic.getTransaccion().getNumeroTransaccionFicticio());
			
			if((Utilidad.decimalMayorACero(creditoProximaFacturaMic.getCobradorInteresesTrasladados())
					&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProximaFacturaMic.getTipoTratamientoDiferencia())
					&& SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo()))
					|| (!Utilidad.decimalMayorACero(creditoProximaFacturaMic.getCobradorInteresesTrasladados())
							&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProximaFacturaMic.getTipoTratamientoDiferencia())
							&& SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo()))){
					
				mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
				mensajeMIC.setAcuerdoFacturacionContracargoCargo(creditoProximaFacturaMic.getAcuerdoContracargo());
			}
		
		} else {
			
			//FACTURA MIC
			if (!Validaciones.isObjectNull(facturaMic)) {
				
				mensajeMIC.setIdTransaccion(facturaMic.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(facturaMic.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(facturaMic.getTransaccion().getNumeroTransaccionFicticio());
				
				//si tiene acuerdos, los valido
				if(!Validaciones.isObjectNull(facturaMic.getAcuerdoTrasladoCargo())
						&& !Validaciones.isObjectNull(facturaMic.getAcuerdoContracargo())){
					
					boolean tieneIntereses = Utilidad.decimalMayorACero(facturaMic.getCobradorInteresesTrasladados());
					
					if(tieneIntereses){
						
						//si la factura tiene sistema contracargo, quiere decir que tiene un cargo y si es de mic, entonces hago los seteos correspondientes
						if(SistemaEnum.MIC.equals(facturaMic.getSistemaOrigen())
								&& facturaMic.getSistemaOrigen().equals(facturaMic.getSistemaAcuerdoContracargo())){
							
							mensajeMIC.setFacturarContracargoFactura(SiNoEnum.SI);					
							mensajeMIC.setAcuerdoFacturacionContracargoFactura(facturaMic.getAcuerdoContracargo());
						}
					}
				}
			}
			
			//DEBITO PROXIMA MIC
			if (!Validaciones.isObjectNull(debitoProximaFacturaMic)) {
				
				// Armo mensaje descobro
				mensajeMIC.setIdTransaccion(debitoProximaFacturaMic.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(debitoProximaFacturaMic.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(debitoProximaFacturaMic.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC = armarDebitoProximaMic(mensajeMIC, debitoProximaFacturaMic, listaResultadoSimulacion);
			
			} 
		}
		
		//MEDIOS DE PAGO MIC
		if (Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar))  {
			
			// Armo mensaje descobro
			mensajeMIC.setIdTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccionFicticio());
			
		}
		
		//envio el mensaje a mic e interpreto la salida para luego agregar todos los resultados a la lista
		//seteo el booleano en false, ya que en esta llamada busco todo no solo las operaciones relacionadas
		listaResultadoSimulacion.addAll(procesarSimulacionDescobroMic(mensajeMIC, false, listaMediosPagoAEnviar, facturaMic, creditoProximaFacturaMic, debitoProximaFacturaMic));
		
		return listaResultadoSimulacion;
	}	
	
	/**
	 * 
	 * @param mensajeMIC
	 * @param debitoProximaFacturaMic
	 * @param listaResultadoSimulacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicTransaccionDescobroDto armarDebitoProximaMic(MicTransaccionDescobroDto mensajeMIC, 
			ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic, List<ResultadoSimulacion> listaResultadoSimulacion) throws NegocioExcepcion{
		
		//si tiene acuerdos, los valido
		if((Utilidad.decimalMayorACero(debitoProximaFacturaMic.getCobradorInteresesTrasladados()) 
				&& debitoProximaFacturaMic.getSistemaOrigen().equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo()))
				&& SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo())
				|| (!Utilidad.decimalMayorACero(debitoProximaFacturaMic.getCobradorInteresesTrasladados()) 
						&& debitoProximaFacturaMic.getSistemaOrigen().equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo())
						&& SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo()))){
					
			mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
			mensajeMIC.setAcuerdoFacturacionContracargoCargo(debitoProximaFacturaMic.getAcuerdoContracargo());
		}
		
		return mensajeMIC;
	}
	
	private List<ResultadoSimulacion> simularDescobroFacturaUsuario(ShvCobFacturaUsuario facturaUsuario, Long idOperacionCobro) throws NegocioExcepcion {
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
				
		//
		// Genero la respuesta del resultado de la reversion, esto
		// siempre va a dar ok
		//
		ResultadoSimulacionDescobroFacturaUsuario resultadoSimulacion = new ResultadoSimulacionDescobroFacturaUsuario();
		resultadoSimulacion.setIdFactura(facturaUsuario.getIdFactura());
		resultadoSimulacion.setIdOperacion(idOperacionCobro);
		resultadoSimulacion.setNumeroTransaccion(facturaUsuario.getTransaccion().getNumeroTransaccion());
		
		RespuestaInvocacion respuesta = new RespuestaInvocacion(TipoResultadoSimulacionEnum.OK, null, null);
		resultadoSimulacion.getListaRespuestasInvocacion().add(respuesta);

		// Agrego este resultado a la lista general
		listaResultadoSimulacion.add(resultadoSimulacion);
		return listaResultadoSimulacion;
		
	}

	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7, este metodo es recursivo cuando mic responde con operaciones relacionadas
	 * @param mensajeMIC
	 * @param buscoSoloOperacionRelacionada
	 * @param listaMediosPagoAEnviar
	 * @param facturaMic
	 * @param creditoProximaFacturaMic
	 * @param debitoProximaFacturaMic
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> procesarSimulacionDescobroMic(MicTransaccionDescobroDto mensajeMIC, boolean buscoSoloOperacionRelacionada, List<ShvCobMedioPagoMic> listaMediosPagoAEnviar, ShvCobFacturaMic facturaMic,
			ShvCobTratamientoDiferencia creditoProximaFacturaMic, ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		if(!Validaciones.isObjectNull(mensajeMIC)){
			
			MicRespuestaDescobroSalida salida = null;
			
			//Envio el mensaje a MIC
			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 MIC DESCOBRO
				salida = micJmsSyncServicio.simularDescobro(mensajeMIC);
				
				if(!Validaciones.isObjectNull(salida.getResultadoLlamadaServicio())
						&& TipoResultadoSimulacionEnum.ERROR.codigoMic().equals(salida.getResultadoLlamadaServicio().getResultadoInvocacion())){
					
					Traza.error(getClass(), salida.getResultadoLlamadaServicio().getDescripcionError());
					listaResultadoSimulacion.addAll(errorServicioMic(listaMediosPagoAEnviar, facturaMic, creditoProximaFacturaMic, debitoProximaFacturaMic, salida.getResultadoLlamadaServicio().getDescripcionError()));
					
				} else {
				
					//pregunto si estoy llamando el metodo en busqueda de otras operaciones relacionadas o es una llamada normal que carga todo
					if(!buscoSoloOperacionRelacionada){
						
						//Cargo los resultados de la factura
						listaResultadoSimulacion.addAll(cargarResultadoFacturaMic(mensajeMIC, salida, facturaMic));
						
						//Cargo los resultados de los medios de pago
						listaResultadoSimulacion.addAll(cargarResultadoMedioPagoMic(mensajeMIC, salida));
						
						//Cargo los resultados del cargo generado
						listaResultadoSimulacion.addAll(cargarResultadoCargoGeneradoMic(mensajeMIC, salida, creditoProximaFacturaMic, debitoProximaFacturaMic, listaMediosPagoAEnviar, facturaMic));
						
						//Cargo los resultados de las operaciones relacionadas
						listaResultadoSimulacion.addAll(cargarResultadoYBuscarOperacionRelacionadaMic(mensajeMIC, salida));
						
					} else {
						
						//Cargo los resultados de las operaciones relacionadas
						listaResultadoSimulacion.addAll(cargarResultadoYBuscarOperacionRelacionadaMic(mensajeMIC, salida));
					}
				}
				
			} catch (JmsExcepcion e) {
				Traza.error(getClass(), e.getMessage(), e);
				listaResultadoSimulacion.addAll(errorServicioMic(listaMediosPagoAEnviar, facturaMic, creditoProximaFacturaMic, debitoProximaFacturaMic, e.getMensajeAuxiliar()));
				
			}
			
		}
		
		return listaResultadoSimulacion;
	}
	
	private List<ResultadoSimulacion> cargarResultadoFacturaMic(MicTransaccionDescobroDto mensajeMIC, MicRespuestaDescobroSalida salida, ShvCobFacturaMic facturaMic) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de mic por factura
		if (!Validaciones.isObjectNull(salida.getDetalleFactura())
				&& !Validaciones.isObjectNull(salida.getDetalleFactura().getResultadoDescobroFactura())
				&& !Validaciones.isObjectNull(facturaMic)
				&& !Validaciones.isNullOrEmpty(salida.getDetalleFactura().getResultadoDescobroFactura().getResultadoInvocacion())) {
			
			ResultadoSimulacionDescobroFactura resultadoSimulacion = new ResultadoSimulacionDescobroFactura();
				
			// Datos de respuesta
			resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			resultadoSimulacion.setIdOperacion(new Long(salida.getIdOperacionTransaccion().substring(0,7)));
			resultadoSimulacion.setNumeroTransaccion(mensajeMIC.getNumeroTransaccion());
			resultadoSimulacion.setTipoInvocacion(salida.getTipoInvocacion());
			
			if(SistemaEnum.MIC.equals(facturaMic.getSistemaAcuerdoContracargo())){
				
				if(!Validaciones.isNullOrEmpty(salida.getDetalleFactura().getCodigoEstadoAcuerdoFacturacion())){
	
					EstadoAcuerdoFacturacionEnum estadoAcuerdo = EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoAcuerdoFacturacion());
					
					if(!EstadoAcuerdoFacturacionEnum.ACTIVO.equals(estadoAcuerdo)
						&& !EstadoAcuerdoFacturacionEnum.INCOMUNICADO.equals(estadoAcuerdo)){
						//TODO - Se comenta hasta que funcionen los cargos cruzados
//						resultadoSimulacion.setRequiereBuscarAcuerdoActivo(true);
						
					}
				}
			}
			
			resultadoSimulacion.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoAcuerdoFacturacion()));
			resultadoSimulacion.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoCargoGenerado()));
			resultadoSimulacion.setCobradorInteresesTrasladadosNoRegulados(salida.getDetalleFactura().getInteresesRealesNoReguladosTrasladados());
			resultadoSimulacion.setCobradorInteresesTrasladadosRegulados(salida.getDetalleFactura().getInteresesRealesReguladosTrasladados());
			
			
			MicResultado resultado = salida.getDetalleFactura().getResultadoDescobroFactura();
			List<MicDetalleDescobroOperacionPosteriorRelacionada> listaOperacionesRelacionadas = salida.getListaDetalleOperacionesPosterioresRelacionadas();
			
			
			resultadoSimulacion.getListaRespuestasInvocacion().add(filtrarRespuestaSimulacionDescobroMic(resultado, listaOperacionesRelacionadas, mensajeMIC, facturaMic.getIdReferenciaFactura()));
			
			//si tiene intereses trasladados No regulados, si facturarContracargo es no y el estado del cargo generado es facturado, entonces llamamos calipso
			BigDecimal interesesTrasladadosNoRegulados = resultadoSimulacion.getCobradorInteresesTrasladadosNoRegulados();
			if(Utilidad.decimalMayorACero(interesesTrasladadosNoRegulados)
					&& SiNoEnum.NO.equals(mensajeMIC.getFacturarContracargoFactura())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(resultadoSimulacion.getEstadoCargoGenerado())){
				
				// Simular contracargo cruzado de factura Mic en Calipso
				resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoFacturaMicEnCalipso(facturaMic, interesesTrasladadosNoRegulados, false));
			}
			
			//si tiene intereses trasladados regulados, si facturarContracargo es no y el estado del cargo generado es facturado, entonces llamamos calipso
			BigDecimal interesesTrasladadosRegulados = resultadoSimulacion.getCobradorInteresesTrasladadosRegulados();
			if(Utilidad.decimalMayorACero(interesesTrasladadosRegulados)
					&& SiNoEnum.NO.equals(mensajeMIC.getFacturarContracargoFactura())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(resultadoSimulacion.getEstadoCargoGenerado())){
				
				// Simular contracargo cruzado de factura Mic en Calipso
				resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoFacturaMicEnCalipso(facturaMic, interesesTrasladadosRegulados, true));
			}
			
			listaResultadoSimulacion.add(resultadoSimulacion);
		}
		
		return listaResultadoSimulacion;
	}
	
	public RespuestaInvocacion filtrarRespuestaSimulacionDescobroMic(MicResultado resultado, List<MicDetalleDescobroOperacionPosteriorRelacionada> listaOperacionesRelacionadas,
			MicTransaccionDescobroDto mensajeMIC, String numeroReferenciaMic) {
		
		boolean tieneResultado = true;
		boolean noTieneOperacionesRelacionadasPropiasParaFiltrar = true;
		
		if(!Validaciones.isObjectNull(resultado)){
			if(Constantes.COD_DESCOBRO_ERROR_8200_OP_RELAC.equals(resultado.getCodigoError())){
				Set<Long> listaOperacionesRelacionadasSinDuplicados = new HashSet<Long>();
				for(MicDetalleDescobroOperacionPosteriorRelacionada operacionRelacionada : listaOperacionesRelacionadas){
					
					if(!Validaciones.isObjectNull(operacionRelacionada.getNumeroReferenciaMic())
						&& !Validaciones.isObjectNull(numeroReferenciaMic)
						&& numeroReferenciaMic.equals(operacionRelacionada.getNumeroReferenciaMic().toString())){
						
						listaOperacionesRelacionadasSinDuplicados.add(operacionRelacionada.getIdOperacion());
					}
					
				}
				listaOperacionesRelacionadasSinDuplicados.remove(new Long(0));
				listaOperacionesRelacionadasSinDuplicados.remove(mensajeMIC.getIdOperacion());
				
				if(!Validaciones.isCollectionNotEmpty(listaOperacionesRelacionadasSinDuplicados)){
					noTieneOperacionesRelacionadasPropiasParaFiltrar = false;
				}
			}				
		} else {
			tieneResultado = false;
		}		
		
		//si el acuerdo que responde el servicio esta activo, muestro la respuesta del servicio directamente
		if(tieneResultado && noTieneOperacionesRelacionadasPropiasParaFiltrar){
			
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.getEnumByCodigoMic(resultado.getResultadoInvocacion()),
					resultado.getCodigoError(),
					resultado.getDescripcionError());
			
			return resultadoInvocacion;
		}
		
		// si esto es false quiere decir que vamos a mostrarle un OK al usuario, ya que el unico problema que hay es una operacion relacionada propia
		//y esto no se debe mostrar
		if(!noTieneOperacionesRelacionadasPropiasParaFiltrar){
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.OK,
					null,
					null);
			
			return resultadoInvocacion;
		}
		return null;
	}
	
	private List<ResultadoSimulacion> cargarResultadoMedioPagoMic(MicTransaccionDescobroDto mensajeMIC, MicRespuestaDescobroSalida salida){
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de mic por medios de pago
		if (Validaciones.isCollectionNotEmpty(salida.getListaDetalleMedioPago())) {
			
			for(MicDetalleDescobroMedioPago medioPago : salida.getListaDetalleMedioPago()){
				
				if(!Validaciones.isObjectNull(medioPago.getTipoMedioPago())
						&& !Validaciones.isObjectNull(medioPago.getResultadoDescobroMedioPago())
						&& !Validaciones.isNullOrEmpty(medioPago.getResultadoDescobroMedioPago().getResultadoInvocacion())){
					
					ResultadoSimulacionDescobroMedioPagoMic resultadoSimulacion = new ResultadoSimulacionDescobroMedioPagoMic();
					
					// Datos de respuesta
					resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
					resultadoSimulacion.setIdOperacion(mensajeMIC.getIdOperacion());		
					resultadoSimulacion.setNumeroTransaccion(mensajeMIC.getNumeroTransaccion());		
					resultadoSimulacion.setTipoInvocacion(salida.getTipoInvocacion());	
					
					resultadoSimulacion.setTipoMedioPago(medioPago.getTipoMedioPago());
					resultadoSimulacion.setCuentaRemanente(medioPago.getCuentaRemanente());
					resultadoSimulacion.setTipoRemanente(medioPago.getTipoRemanente());
					resultadoSimulacion.setNumeroReferenciaMic(medioPago.getNumeroReferenciaMic());
					
					String idBusqueraRespuestaCobrador = "";
					
					if(TipoMedioPagoEnum.NC.equals(medioPago.getTipoMedioPago())
							&& !Validaciones.isObjectNull(medioPago.getNumeroReferenciaMic())){
						idBusqueraRespuestaCobrador = medioPago.getTipoMedioPago() + medioPago.getNumeroReferenciaMic().toString();
						
					} else if(!Validaciones.isObjectNull(medioPago.getTipoRemanente())
								&& !Validaciones.isObjectNull(medioPago.getCuentaRemanente())){
						idBusqueraRespuestaCobrador = medioPago.getTipoMedioPago() + medioPago.getTipoRemanente().toString() + medioPago.getCuentaRemanente();
					}
					
					resultadoSimulacion.setIdBusquedaRespuestaCobrador(idBusqueraRespuestaCobrador);
					
					MicResultado resultado = medioPago.getResultadoDescobroMedioPago();
					List<MicDetalleDescobroOperacionPosteriorRelacionada> listaOperacionesRelacionadas = salida.getListaDetalleOperacionesPosterioresRelacionadas();
					
					resultadoSimulacion.getListaRespuestasInvocacion().add(filtrarRespuestaSimulacionDescobroMic(resultado, listaOperacionesRelacionadas, mensajeMIC, medioPago.getNumeroReferenciaMic().toString()));
					
					listaResultadoSimulacion.add(resultadoSimulacion);
				}
			}
		}
		
		return listaResultadoSimulacion;
	}
	
	
	private List<ResultadoSimulacion> cargarResultadoCargoGeneradoMic(MicTransaccionDescobroDto mensajeMIC, MicRespuestaDescobroSalida salida
			, ShvCobTratamientoDiferencia creditoProximaFacturaMic, ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic
			, List<ShvCobMedioPagoMic> listaMediosPagoAEnviar, ShvCobFacturaMic facturaMic) throws NegocioExcepcion{
				
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de mic por cargos
		if (!Validaciones.isObjectNull(salida.getDetalleCargoGenerado())
				&& !Validaciones.isObjectNull(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getResultadoInvocacion())) {
							
			ResultadoSimulacionDescobroCargoGeneradoMic resultadoSimulacion = new ResultadoSimulacionDescobroCargoGeneradoMic();
			// Datos de respuesta
			resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			resultadoSimulacion.setIdOperacion(mensajeMIC.getIdOperacion());		
			resultadoSimulacion.setNumeroTransaccion(mensajeMIC.getNumeroTransaccion());		
			resultadoSimulacion.setTipoInvocacion(salida.getTipoInvocacion());	
			
			if((!Validaciones.isObjectNull(debitoProximaFacturaMic) && SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo()))
					|| (!Validaciones.isObjectNull(creditoProximaFacturaMic) && SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo()))){
				
				if(!Validaciones.isObjectNull(salida.getDetalleCargoGenerado().getCodigoEstadoAcuerdoFacturacion())){
					
					EstadoAcuerdoFacturacionEnum estadoAcuerdo = EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleCargoGenerado().getCodigoEstadoAcuerdoFacturacion());
					//Si el estado no es activo, seteo un flag para ir a buscar uno despues, si esta activo me quedo con los datos del cobro
					if(!EstadoAcuerdoFacturacionEnum.ACTIVO.equals(estadoAcuerdo)
							&& !EstadoAcuerdoFacturacionEnum.INCOMUNICADO.equals(estadoAcuerdo)){
						//TODO - Se comenta hasta que funcionen los cargos cruzados
//						resultadoSimulacion.setRequiereBuscarAcuerdoActivo(true);
						
					}
				}
			}			
			
			resultadoSimulacion.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoAcuerdoFacturacion()));
			resultadoSimulacion.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByCodigo(salida.getDetalleCargoGenerado().getCodigoEstadoCargoGenerado()));
			resultadoSimulacion.setInteresesRealesNoReguladosTrasladados(salida.getDetalleCargoGenerado().getInteresesRealesNoReguladosTrasladados());
			resultadoSimulacion.setInteresesRealesReguladosTrasladados(salida.getDetalleCargoGenerado().getInteresesRealesReguladosTrasladados());
			
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getResultadoInvocacion()),
					salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getCodigoError(),
					salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getDescripcionError());
			
			resultadoSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
			
			//si facturarContracargo es no y el estado del cargo generado es facturado, entonces llamamos calipso
			if(SiNoEnum.NO.equals(mensajeMIC.getFacturarContracargoCargo())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(resultadoSimulacion.getEstadoCargoGenerado())){
								
				//llamadas contracargo cruzado
				if(!Validaciones.isObjectNull(creditoProximaFacturaMic) 
						&& !SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo())){
											
					ShvCobMedioPagoMic medioPagoMic = listaMediosPagoAEnviar.get(0);
					
					// Simular CARGO contracargo cruzado de credito a proxima Mic en Calipso
					resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoCreditoMicEnCalipso(creditoProximaFacturaMic, medioPagoMic, false));
					
					if(Utilidad.decimalMayorACero(creditoProximaFacturaMic.getCobradorInteresesTrasladados())){
						// Simular INTERES contracargo cruzado de credito a proxima Mic en Calipso
						resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoCreditoMicEnCalipso(creditoProximaFacturaMic, medioPagoMic, true));
					}
						
				} else if(!Validaciones.isObjectNull(debitoProximaFacturaMic)
						&& !SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo())) {
					// Simular CARGO contracargo cruzado de credito a proxima Mic en Calipso
					resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoDebitoMicEnCalipso(debitoProximaFacturaMic, facturaMic, false));
					
					if(Utilidad.decimalMayorACero(debitoProximaFacturaMic.getCobradorInteresesTrasladados())){
						// Simular INTERES contracargo cruzado de credito a proxima Mic en Calipso
						resultadoSimulacion.getListaRespuestasInvocacion().add(simularContracargoCruzadoDebitoMicEnCalipso(debitoProximaFacturaMic, facturaMic, true));
					}
				}
			}
			
			listaResultadoSimulacion.add(resultadoSimulacion);
		}
		
		return listaResultadoSimulacion;
	}
	
	private List<ResultadoSimulacion> cargarResultadoYBuscarOperacionRelacionadaMic(MicTransaccionDescobroDto mensajeMIC, MicRespuestaDescobroSalida salida) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Reviso la respuesta de mic por operaciones relacionadas
		if (Validaciones.isCollectionNotEmpty(salida.getListaDetalleOperacionesPosterioresRelacionadas())) {
			
			for(MicDetalleDescobroOperacionPosteriorRelacionada operacionRelacionada : salida.getListaDetalleOperacionesPosterioresRelacionadas()){
				
				String operacionTransaccion = operacionRelacionada.getIdOperacion().toString()+"."+operacionRelacionada.getIdSecuencia().toString();
				
				Traza.auditoria(getClass(),"[Op. Rel. MIC Condicion Op. Cobro: "+operacionRelacionada.getIdOperacion().toString()+" != Op. Rel.: "+mensajeMIC.getIdOperacion().toString()+"]");
				if(!Validaciones.isObjectNull(operacionRelacionada) 
						&& !Validaciones.isObjectNull(operacionRelacionada.getSistema())
						&& !operacionRelacionada.getIdOperacion().equals(mensajeMIC.getIdOperacion())
						&& !operacionesRepetidas.contains(operacionTransaccion)){
					
					operacionesRepetidas.add(operacionTransaccion);
					Traza.auditoria(getClass(),"[Op. Rel. MIC Agrego Op. Cobro: "+operacionTransaccion+"]");
				
					//Cargo los datos que me llegaron de la respuesta en un objeto resultado para despues impactar en la base de datos
					listaResultadoSimulacion.add(cargarResultadoOperacionRelacionadaMic(mensajeMIC, operacionRelacionada));
					
					// Consulto a SHIVA para ver si tiene otro padre (operacion relacionada)
					if(SistemaEnum.SHIVA.equals(operacionRelacionada.getSistema())){
						
						//Busco si hay operaciones relacionadas a la de shiva
						listaResultadoSimulacion.addAll(buscarOperacionesRelacionadasMicYCalipso(operacionRelacionada.getIdOperacion(), mensajeMIC.getIdOperacionDescobroMensajeria()));
					}
				}
			}
		}		
		
		return listaResultadoSimulacion;
	}
	
	
	private ResultadoSimulacion cargarResultadoOperacionRelacionadaMic(MicTransaccionDescobroDto mensajeMIC, MicDetalleDescobroOperacionPosteriorRelacionada operacionRelacionada){
		
		ResultadoSimulacionDescobroOperacionRelacionadaMic resultadoSimulacion = new ResultadoSimulacionDescobroOperacionRelacionadaMic();
		resultadoSimulacion.setIdOperacion(operacionRelacionada.getIdOperacion());
		resultadoSimulacion.setSistema(operacionRelacionada.getSistema());
		resultadoSimulacion.setNumeroTransaccion(operacionRelacionada.getIdSecuencia());
		resultadoSimulacion.setTipoComprobante(operacionRelacionada.getTipoDocumento());
		resultadoSimulacion.setNumeroReferenciaMic(operacionRelacionada.getNumeroReferenciaMic());
		
		if(!Validaciones.isObjectNull(operacionRelacionada.getCodigoCliente())){
			resultadoSimulacion.setIdClienteLegado(Long.valueOf(String.valueOf(operacionRelacionada.getCodigoCliente())));
		}
		resultadoSimulacion.setImporteCobrado(operacionRelacionada.getImporteCobrado());
		resultadoSimulacion.setFechaCobranza(operacionRelacionada.getFechaCobranza());
		resultadoSimulacion.setIdOperacionPadre(mensajeMIC.getIdOperacion());
		resultadoSimulacion.setNroTransaccionPadre(mensajeMIC.getNumeroTransaccionFicticio());
		
		return resultadoSimulacion;
	}
	
	/**
	 * Se pasa por parametro el id transaccion del descobro para hacer mas facil la busqueda de mensajeria asociada
	 * Se debera buscar la relacion del id_transaccion de la tabla SHV_COB_TRANSACCION_MSJ_DET con SHV_COB_TRANSACCION
	 * y su relacion con la operacion del descobro
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param idOperacionRelacionada
	 * @param idTransaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ResultadoSimulacion> buscarOperacionesRelacionadasMicYCalipso(Long idOperacionRelacionada, Long idOperacionDescobro) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		if(!Validaciones.isObjectNull(idOperacionRelacionada)){
			
			ShvCobCobro cobroRelacionado;
			try {
				cobroRelacionado = cobroDao.buscarCobroPorIdOperacion(idOperacionRelacionada);
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			
			if(!Validaciones.isObjectNull(cobroRelacionado)){
				
				for(ShvCobTransaccion transaccion : cobroRelacionado.getTransaccionesOrdenadasInversaSinDiferenciaCambio()){
					
					//busco operaciones relacionadas en Mic
					listaResultadoSimulacion.addAll(buscarOperacionesRelacionadasMic(transaccion, idOperacionDescobro, transaccion.getIdTransaccion()));
					
					//busco operaciones relacionadas en Calipso
					listaResultadoSimulacion.addAll(prepararBuscarOperacionesRelacionadasCalipso(transaccion, idOperacionDescobro, transaccion.getIdTransaccion()));
				}
			}
			
		}
		return listaResultadoSimulacion;
	}
	
	
	private List<ResultadoSimulacion> buscarOperacionesRelacionadasMic(ShvCobTransaccion transaccion, Long idOperacionDescobro, Integer idTransaccion) throws NegocioExcepcion{
		
		boolean hayFacturaMedioPagoMic = false;
		
		if(!Validaciones.isObjectNull(transaccion.getFactura())){
			hayFacturaMedioPagoMic = true;
		} else if(Validaciones.isCollectionNotEmpty(transaccion.getMediosPago())){
			for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
				
				if(medioPago instanceof ShvCobMedioPagoNotaCreditoMic){
					hayFacturaMedioPagoMic = true;
					break;
				}
			}
		}
		
		if(hayFacturaMedioPagoMic){
			Traza.auditoria(getClass(), "[Op. Rel. MIC Busco Op. Cobro:" + transaccion.getOperacion().getIdOperacion().toString() + "."+ transaccion.getNumeroTransaccion().toString()+" Op. Descobro: "+idOperacionDescobro+"]");
			// Consulto a MIC para ver si tiene otro padre (operacion relacionada)
			MicTransaccionDescobroDto mensajeMICAnidado = new MicTransaccionDescobroDto();
			mensajeMICAnidado.setIdOperacion(transaccion.getOperacion().getIdOperacion());
			mensajeMICAnidado.setIdOperacionDescobroMensajeria(idOperacionDescobro);
			mensajeMICAnidado.setIdTransaccion(idTransaccion);
			mensajeMICAnidado.setNumeroTransaccion(transaccion.getNumeroTransaccion());
			mensajeMICAnidado.setNumeroTransaccionFicticio(transaccion.getNumeroTransaccionFicticio());
			
			mensajeMICAnidado.setModoEjecucion(SiNoEnum.SI);
			mensajeMICAnidado.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			mensajeMICAnidado.setFacturarContracargoFactura(SiNoEnum.NO);
			mensajeMICAnidado.setFacturarContracargoCargo(SiNoEnum.NO);
			return procesarSimulacionDescobroMic(mensajeMICAnidado, true, null, null, null,null);
		} else {
			List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
			return listaResultadoSimulacion;
		}
	}
	
	private List<ResultadoSimulacion> prepararBuscarOperacionesRelacionadasCalipso(ShvCobTransaccion transaccion, Long idOperacionDescobro, Integer idTransaccion) throws NegocioExcepcion{
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		// Consulto a CALIPSO para ver si tiene otro padre (operacion relacionada)
		
		Set<ShvCobMedioPago> listaMedioPago = transaccion.getMediosPago();
		Iterator<ShvCobMedioPago> itMedioPago = listaMedioPago.iterator();
		
		Long idOperacionCobro = transaccion.getOperacion().getIdOperacion();
		Long idCobranzaFactura = null;
		List<Long> listaIdCobranzasMedioPago = new ArrayList<Long>();
		
		while(itMedioPago.hasNext()){
			
			ShvCobMedioPago medioPago = itMedioPago.next();
			
			if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
				
				ShvCobMedioPagoNotaCreditoCalipso medioPagoNC = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
				listaIdCobranzasMedioPago.add(medioPagoNC.getIdCobranza());
				
			} else if (medioPago instanceof ShvCobMedioPagoCTA) {
				
				ShvCobMedioPagoCTA medioPagoCta = (ShvCobMedioPagoCTA) medioPago;
				listaIdCobranzasMedioPago.add(medioPagoCta.getIdCobranza());
			}
		}
		
		ShvCobFactura facturaCalipso = transaccion.getFactura();
		if (facturaCalipso instanceof ShvCobFacturaCalipso) {
			
			idCobranzaFactura = facturaCalipso.getIdCobranza();
		}
		
		if(!Validaciones.isObjectNull(idCobranzaFactura) || Validaciones.isCollectionNotEmpty(listaIdCobranzasMedioPago)){
			
			listaResultadoSimulacion.addAll(buscarOperacionesRelacionadasCalipso(idOperacionCobro, idOperacionDescobro, idTransaccion, transaccion.getNumeroTransaccion(), idCobranzaFactura, listaIdCobranzasMedioPago,transaccion.getNumeroTransaccionFicticio()));
		}
		
		return listaResultadoSimulacion;
	}
	
	
	private List<ResultadoSimulacion> buscarOperacionesRelacionadasCalipso(Long idOperacionCobro, Long idOperacionDescobro, Integer idTransaccion, Integer numeroTransaccion, Long idCobranzaFactura, List<Long> listaIdCobranzasMedioPago, Integer numeroTransaccionFicticio) throws NegocioExcepcion{
		
		Traza.auditoria(getClass(), "[Op. Rel. CLP Busco Op. Cobro:" + idOperacionCobro.toString() + "."+ numeroTransaccion.toString()+" Op. Descobro: "+idOperacionDescobro+"]");
		EntradaCalipsoDescobroWS entradaAnidada = new EntradaCalipsoDescobroWS();
		entradaAnidada.setIdOperacion(idOperacionCobro);
		entradaAnidada.setIdOperacionDescobroMensajeria(idOperacionDescobro);
		entradaAnidada.setIdTransaccion(idTransaccion);
		entradaAnidada.setNumeroTransaccion(numeroTransaccion);
		entradaAnidada.setNumeroTransaccionFicticio(numeroTransaccionFicticio);
		entradaAnidada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_SIM);
		
		if(!Validaciones.isObjectNull(idCobranzaFactura)){
			entradaAnidada.setIdCobranza(new BigInteger(idCobranzaFactura.toString()));
		}
		
		if(Validaciones.isCollectionNotEmpty(listaIdCobranzasMedioPago)){
			List<DetalleCTAoNotaCreditoDescobro> listaMediosPago = new ArrayList<DetalleCTAoNotaCreditoDescobro>();

			for(Long idCobranzaMedioPago : listaIdCobranzasMedioPago){
				
				DetalleCTAoNotaCreditoDescobro medioPago = new DetalleCTAoNotaCreditoDescobro();
				medioPago.setIdCobranza(new BigInteger(idCobranzaMedioPago.toString()));
				listaMediosPago.add(medioPago);
				
			}
			
			entradaAnidada.setListaCtaONotaCredito(listaMediosPago);
			
		}	
		
		entradaAnidada.setModoOperacion(SiNoEnum.SI);
		entradaAnidada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		entradaAnidada.setFacturarContracargoFactura(SiNoEnum.NO);
		entradaAnidada.setFacturarContracargoCargo(SiNoEnum.NO);
		
		return procesarSimulacionDescobroCalipso(entradaAnidada, true, null, null, null,null);
	}
	
	
	private List<ResultadoSimulacion> errorServicioMic(List<ShvCobMedioPagoMic> listaMediosPagoAEnviar, ShvCobFacturaMic facturaMic,
			ShvCobTratamientoDiferencia creditoProximaFacturaMic, ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic, String mensajeError){
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.ERROR_SERVICIO,
				null,
				mensajeError);
		
		// ERROR FACTURA MIC
		if(!Validaciones.isObjectNull(facturaMic)){
			ResultadoSimulacionDescobroFactura resultadoSimulacion = new ResultadoSimulacionDescobroFactura();
			resultadoSimulacion.setIdOperacion(facturaMic.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacion.setIdOperacionShiva(facturaMic.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacion.setNumeroTransaccion(facturaMic.getTransaccion().getNumeroTransaccion());
			resultadoSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
			listaResultadoSimulacion.add(resultadoSimulacion);
		}		
		
		// ERROR MEDIOS DE PAGO MIC
		if(Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)){
			for(ShvCobMedioPago medioPago : listaMediosPagoAEnviar){
				ResultadoSimulacionDescobroMedioPagoMic resultadoSimulacionMp = new ResultadoSimulacionDescobroMedioPagoMic();
				
				// Datos de respuesta
				resultadoSimulacionMp.setIdOperacion(medioPago.getTransaccion().getOperacion().getIdOperacion());
				resultadoSimulacionMp.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
				resultadoSimulacionMp.setIdBusquedaRespuestaCobrador(medioPago.getIdBusquedaRespuestaCobrador());
				resultadoSimulacionMp.getListaRespuestasInvocacion().add(resultadoInvocacion);
				
				listaResultadoSimulacion.add(resultadoSimulacionMp);
			}
		}
		
		// ERROR CREDITO PROXIMA MIC
		if(!Validaciones.isObjectNull(creditoProximaFacturaMic)){
			ResultadoSimulacionDescobroCargoGeneradoMic resultadoSimulacionCg = new ResultadoSimulacionDescobroCargoGeneradoMic();
			resultadoSimulacionCg.setIdOperacion(creditoProximaFacturaMic.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacionCg.setIdOperacionShiva(creditoProximaFacturaMic.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacionCg.setNumeroTransaccion(creditoProximaFacturaMic.getTransaccion().getNumeroTransaccion());
			resultadoSimulacionCg.getListaRespuestasInvocacion().add(resultadoInvocacion);
			listaResultadoSimulacion.add(resultadoSimulacionCg);
		}
		
		// ERROR DEBITO PROXIMA MIC
		if(!Validaciones.isObjectNull(debitoProximaFacturaMic)){
			ResultadoSimulacionDescobroCargoGeneradoMic resultadoSimulacionCg = new ResultadoSimulacionDescobroCargoGeneradoMic();
			resultadoSimulacionCg.setIdOperacion(debitoProximaFacturaMic.getTransaccion().getOperacion().getIdOperacion());
			resultadoSimulacionCg.setIdOperacionShiva(debitoProximaFacturaMic.getTransaccion().getOperacionTransaccionFormateado());
			resultadoSimulacionCg.setNumeroTransaccion(debitoProximaFacturaMic.getTransaccion().getNumeroTransaccion());
			resultadoSimulacionCg.getListaRespuestasInvocacion().add(resultadoInvocacion);
			listaResultadoSimulacion.add(resultadoSimulacionCg);
		}
		
		return listaResultadoSimulacion;
	}
	
	
	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoCreditoCalipsoEnMic(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPago medioPago
			, boolean esContracargoInteres) throws NegocioExcepcion {
	
		RespuestaInvocacion resultadoInvocacion = null;
		Long idOperacionCobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacionOriginal();
		Long idOperacionDescobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion();
				
		tratamientoDiferencia = calcularOrigenContracargoCreditoProxima(tratamientoDiferencia, medioPago, idOperacionCobro, esContracargoInteres,SistemaEnum.MIC);
		
		//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 MIC CARGO CRED
		MicDescobroGeneracionCargosCreditoDto mensajeMIC = new MicDescobroGeneracionCargosCreditoDto();
		
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$07);
		mensajeMIC.setModoEjecucion(SiNoEnum.SI);
		mensajeMIC.setIdOperacion(idOperacionCobro);
		mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
		mensajeMIC.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion());
		mensajeMIC.setNumeroTransaccion(tratamientoDiferencia.getTransaccion().getNumeroTransaccion());
		mensajeMIC.setNumeroTransaccionFicticio(tratamientoDiferencia.getTransaccion().getNumeroTransaccionFicticio());
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();
		
		detalleGeneracionCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoContracargo());
		detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
		detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.NO);
		detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);
		
		if(!esContracargoInteres){
			detalleGeneracionCargo.setImporteNoRegulado(tratamientoDiferencia.getImporte());
			detalleGeneracionCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
			detalleGeneracionCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
			mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_SIM);
		} else {
			detalleGeneracionCargo.setImporteNoRegulado(tratamientoDiferencia.getCobradorInteresesTrasladados());
			detalleGeneracionCargo.setOrigenCargo(tratamientoDiferencia.getOrigenInteres());
			detalleGeneracionCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
			mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES_SIM);
		}
		
		mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);
		
		MicRespuestaGeneracionCargoSalida salida = null;

		try {
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 MIC CARGO CRED
			salida = micJmsSyncServicio.simularContracargoCredito(mensajeMIC);
			
			// Resultados posibles: OK / ER 
			resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getResultadoLLamadaServicio().getResultadoInvocacion()),
				salida.getResultadoLLamadaServicio().getCodigoError(),
				salida.getResultadoLLamadaServicio().getDescripcionError());
								
		} catch (JmsExcepcion e) {
			
			resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.ERROR_SERVICIO,
				null,
				e.getMensajeAuxiliar());
					
		}
		
		return resultadoInvocacion;		
	}


	/**
	 * 
	 * @param medioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoDebitoCalipsoEnMic(ShvCobMedioPagoDebitoProximaFacturaCalipso medioPago
			, ShvCobFactura facturaClp, boolean esContracargoInteres) throws NegocioExcepcion {
		
		RespuestaInvocacion resultadoInvocacion = null;
		
		if(!Validaciones.isObjectNull(medioPago)){
			Long idOperacionCobro = medioPago.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = medioPago.getTransaccion().getOperacion().getIdOperacion();
			
			medioPago = (ShvCobMedioPagoDebitoProximaFacturaCalipso) calcularOrigenContracargoDebitoProxima(medioPago, facturaClp, idOperacionCobro, esContracargoInteres);
			
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 MIC CARGO DEB
			MicDescobroGeneracionCargosDebitoDto mensajeMIC = new MicDescobroGeneracionCargosDebitoDto();
			
			mensajeMIC.setModoEjecucion(SiNoEnum.SI);
			mensajeMIC.setIdOperacion(idOperacionCobro);
			mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
			mensajeMIC.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(medioPago.getTransaccion().getNumeroTransaccionFicticio());
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			
			MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();
			
			detalleGeneracionCargo.setAcuerdoFacturacion(medioPago.getAcuerdoContracargo());
			detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
			detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.NO);
			detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);
			
			if(!esContracargoInteres){
				detalleGeneracionCargo.setImporteRegulado(medioPago.getImporte());
				detalleGeneracionCargo.setOrigenCargo(medioPago.getOrigenCargo());
				detalleGeneracionCargo.setLeyendaFacturaCargo(medioPago.getLeyendaFacturaCargo());
				mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_SIM);

			} else {
				detalleGeneracionCargo.setImporteRegulado(medioPago.getCobradorInteresesTrasladados());
				detalleGeneracionCargo.setOrigenCargo(medioPago.getOrigenInteres());
				detalleGeneracionCargo.setLeyendaFacturaInteres(medioPago.getLeyendaFacturaInteres());
				mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES_SIM);
			}
			
			mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);
			
			// Resultados posibles: OK / ER 
			MicRespuestaGeneracionCargoSalida salida = null;
			
			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 MIC CARGO DEB
				salida = micJmsSyncServicio.simularContracargoDebito(mensajeMIC);
				
				// Resultados posibles: OK / ER 

				resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getResultadoLLamadaServicio().getResultadoInvocacion()),
					salida.getResultadoLLamadaServicio().getCodigoError(),
					salida.getResultadoLLamadaServicio().getDescripcionError());
									
			} catch (JmsExcepcion e) {

				resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
			}
		}
		
		return resultadoInvocacion;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 */
	private RespuestaInvocacion simularContracargoCruzadoFacturaCalipsoEnMic(ShvCobFactura factura) throws NegocioExcepcion {

		RespuestaInvocacion resultadoInvocacion =null;
		
		if(!Validaciones.isObjectNull(factura)){
			
			Long idOperacionCobro = factura.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = factura.getTransaccion().getOperacion().getIdOperacion();
			
			//calculo el origen y la leyenda de los intereses
			factura = calcularOrigenContracargoFactura(factura, idOperacionCobro, false);
			
			//TODO - a Fabio 1Reversion PENDIENTE HILOS 1 MIC CARGO FAC
			MicDescobroGeneracionCargosInteresDto mensajeMIC = new MicDescobroGeneracionCargosInteresDto();
			
			mensajeMIC.setModoEjecucion(SiNoEnum.SI);
			mensajeMIC.setIdOperacion(idOperacionCobro);
			mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
			mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			
			MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();
			
			detalleGeneracionCargo.setAcuerdoFacturacion(factura.getAcuerdoContracargo());
			detalleGeneracionCargo.setImporteRegulado(factura.getCobradorInteresesTrasladados());
			detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
			detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.NO);
			
			detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);
			detalleGeneracionCargo.setOrigenCargo(factura.getOrigenCargo());
			detalleGeneracionCargo.setLeyendaFacturaInteres(factura.getLeyendaFacturaInteres());
			
			mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);
			
			MicRespuestaGeneracionCargoSalida salida = null;
		
			try {
				//TODO - a Fabio 1Reversion PENDIENTE HILOS 2 MIC CARGO FAC
				salida = micJmsSyncServicio.simularContracargoDebito(mensajeMIC);
				
				// Resultados posibles: OK / ER 
				resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.getEnumByCodigoMic(salida.getResultadoLLamadaServicio().getResultadoInvocacion()),
					salida.getResultadoLLamadaServicio().getCodigoError(),
					salida.getResultadoLLamadaServicio().getDescripcionError());
									
			} catch (JmsExcepcion e) {
				
				resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMensajeAuxiliar());
						
			}
		}	
		
		return resultadoInvocacion;		
	}


	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param idClienteLegado
	 * @param idOperacion
	 * @return AcuerdoLegado
	 * @throws NegocioExcepcion
	 */
	private AcuerdoLegado autoCompletarAcuerdoActivoMic(Modelo destino) throws NegocioExcepcion {

		ShvCobTransaccion transaccion = null;
		
		if (destino instanceof ShvCobMedioPago) {
			ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
			ShvCobMedioPagoDebitoProximaFacturaMic medioPagoProxFact = (ShvCobMedioPagoDebitoProximaFacturaMic) destino;
			transaccion = medioPago.getTransaccion();
			medioPagoProxFact.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.POTENCIAL);

		} else if (destino instanceof ShvCobFactura) {
			ShvCobFactura factura = (ShvCobFactura) destino;
			transaccion = factura.getTransaccion();
			factura.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.POTENCIAL);

		} else if (destino instanceof ShvCobTratamientoDiferencia) {
			ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
			transaccion = tratamientoDiferencia.getTransaccion();
			tratamientoDiferencia.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.POTENCIAL);
		}		

		AcuerdoLegado acuerdo = buscarAcuerdoActivo(destino);
		
		TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;
		
		String mensajeWarning = Mensajes.DESCOBROS_WRN_SIMULACION_NO_ENCUENTRO_ACUERDO;
		
		// Si ninguno posee acuerdo activo, se mostrará un mensaje indicando que se debe agregar un nuevo cliente al cobro.
		if (!Validaciones.isObjectNull(acuerdo)) {
			
			if (destino instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
				ShvCobMedioPagoDebitoProximaFacturaMic medioPago = (ShvCobMedioPagoDebitoProximaFacturaMic) destino;
				
				medioPago.setAcuerdoContracargo(acuerdo.getNumero().toString());
				medioPago.setEstadoAcuerdoContracargo(acuerdo.getEstado());
				medioPago.setIdClienteLegadoAcuerdoContracargo(acuerdo.getIdClienteLegado());
				
			} else if (destino instanceof ShvCobFactura) {
				ShvCobFactura factura = (ShvCobFactura) destino;

				factura.setAcuerdoContracargo(acuerdo.getNumero().toString());
				factura.setEstadoAcuerdoContracargo(acuerdo.getEstado());
				factura.setIdClienteLegadoAcuerdoContracargo(acuerdo.getIdClienteLegado());
			
			} else if (destino instanceof ShvCobTratamientoDiferencia) {
				ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;

				tratamientoDiferencia.setAcuerdoContracargo(acuerdo.getNumero().toString());
				tratamientoDiferencia.setEstadoAcuerdoContracargo(acuerdo.getEstado());
				tratamientoDiferencia.setIdClienteLegadoAcuerdoContracargo(acuerdo.getIdClienteLegado());
			}		

			mensajeWarning = Mensajes.DESCOBROS_WRN_SIMULACION_SUGIERE_NUEVO_ACUERDO;
		}

		StringBuffer detalleMensaje = new StringBuffer();

		if (!TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())) {
			transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
		}
		
		transaccion.setMensajeEstado(null);
			
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
			
		return acuerdo;
	}
	
	/**
	 * 
	 * @param destino
	 * @return
	 * @throws NegocioExcepcion
	 */
	private AcuerdoLegado buscarAcuerdoActivo(Modelo destino) throws NegocioExcepcion {
		
		AcuerdoLegado acuerdo 	= null; 
		Long cliente 			= null;
		Long idOperacion 		= null;
		
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
			// Si dicho acuerdo no está activo, se autocompletará el primer acuerdo activo del cliente de la factura. 
			acuerdo = buscarPrimerAcuerdoActivo(cliente);
			
			// Si no posee acuerdo activo, se buscará el primer acuerdo activo de alguno de los otros clientes seleccionados para el cobro.
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
	
	
	private AcuerdoLegado buscarPrimerAcuerdoActivo(Long idClienteLegado) throws NegocioExcepcion {
		
		AcuerdoLegado acuerdoLegado = null;
		
		try {
			// Busco los acuerdos de un cliente
			FacConsultaAcuerdoSalida consultarAcuerdoxCliente = facJmsSyncServicio.consultarAcuerdoxCliente(idClienteLegado);
			
			if (!Validaciones.isObjectNull(consultarAcuerdoxCliente)) {
				
				List<AcuerdoFacturacion> listaAcuerdos = consultarAcuerdoxCliente.getListaAcuerdoFacturacion();
	
				if (Validaciones.isCollectionNotEmpty(listaAcuerdos)) {
	
					// Recorro el/los acuerdos para validar su estado y quedarme con el primero que cumpla
					for (AcuerdoFacturacion acuerdo : listaAcuerdos) {
						if (!Validaciones.isObjectNull(acuerdo)) {
							
							// Mejora Performance
							// Si estos dos cumplen con este criterio, puedo ir a consultar por el acuerdo (no borrar los espacios)
							if (!Validaciones.isNullOrEmpty(acuerdo.getFechaFinalizacionAcuerdoFactura())
									&& !Validaciones.isNullOrEmpty(acuerdo.getFechaUltimaFactura())
									&& Constantes.JULIANO_ULTIMO_DIA.equalsIgnoreCase(acuerdo.getFechaFinalizacionAcuerdoFactura().trim())
									&& !Constantes.JULIANO_PRIMER_DIA.equalsIgnoreCase(acuerdo.getFechaUltimaFactura().trim())) {
							
								FacConsultaAcuerdoSalida salida = facJmsSyncServicio.consultarAcuerdo(acuerdo.getNumeroAcuerdo());
							    EstadoAcuerdoFacturacionEnum estadoAcuerdo = null;
							       
							    if (!Validaciones.isObjectNull(salida)) {
							    	estadoAcuerdo = salida.getPrimerAcuerdoFacturacion().getEstadoAcuerdo();
							    	
							    	if (!Validaciones.isObjectNull(estadoAcuerdo)) {
							    		if (EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(estadoAcuerdo.codigo())
							    				|| EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(estadoAcuerdo.codigo())) {
							                     
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
		}
		catch (JmsException e) {
			throw new NegocioExcepcion(e);
		}

		return acuerdoLegado;		
	}
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7 
	 * @param descobro
	 * @throws NegocioExcepcion
	 */
	private void enviarMailyGenerarTareaSimulacionDescobroBatchFinalizada(ShvCobDescobro descobro) throws NegocioExcepcion {
		
		try {

			List<ResultadoBusquedaDatosSimulacion> listaDatos = (List<ResultadoBusquedaDatosSimulacion>) descobroDao.buscarDatosSimulacion(descobro.getOperacion().getIdOperacion());
			StringBuffer cuerpo = new StringBuffer();
			cuerpo.append(Mensajes.DESCOBROS_MAIL_CUERPO_CABECERA);
			
			BigDecimal importe = new BigDecimal(0);
			
			if(Validaciones.isCollectionNotEmpty(listaDatos)){
				if(!Validaciones.isObjectNull(listaDatos.get(0).getImporte())){
					importe = importe.add(listaDatos.get(0).getImporte());
				}
			}
			
			//se manda con copia al analista de team comercial, si es único para todos los clientes incluidos en la operación
			for (ResultadoBusquedaDatosSimulacion datos : listaDatos){
				
				String cliente = "";
				if(!Validaciones.isObjectNull(datos.getIdClienteLegado())){
					cliente = datos.getIdClienteLegado().toString();
				}
				cuerpo.append(Utilidad.reemplazarMensajes(Mensajes.DESCOBROS_MAIL_CUERPO_DETALLE, cliente, datos.getRazonSocial()));
			}
			
			MarcaEnum marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO;
			
			// Determino si la simulacion ha finalizado correctamente
			if (!esSimulacionExitosa(descobro)) {
				marcaResultadoSimulacion = MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR;
			}
			
			//Envio el mail
			enviarMailResultadoSimulacionBatch(listaDatos, marcaResultadoSimulacion, descobro, cuerpo);
			
			//Creo la tarea
			crearTareaPendienteResultadoSimulacionBatch(descobro, listaDatos, marcaResultadoSimulacion, importe, false);
			
		} catch (PersistenciaExcepcion | IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}	
	}
	
	private ShvCobFactura calcularOrigenContracargoFactura(ShvCobFactura factura, Long idOperacionCobro, boolean esInteresRegulado) throws NegocioExcepcion {

		if(!Validaciones.isObjectNull(factura)
				&& !Validaciones.isObjectNull(idOperacionCobro)){
			
			OrigenCargoEnum origenInteres = OrigenCargoEnum.CC_INT_MORA_CLP_REGULADOS;
			
			//si la factura no es calipso decido el origen mic correspondiente
			if(factura instanceof ShvCobFacturaMic){
				if(esInteresRegulado){
					origenInteres = OrigenCargoEnum.CC_INT_MORA_GENESIS_REGULADOS;
				} else{
					origenInteres = OrigenCargoEnum.CC_INT_MORA_GENESIS_NO_REGULAD;
				}
			}
			
			String leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("descobros.leyendaExposicionFactura.intereses." + origenInteres.name());
			
			String referenciaFactura = "";
			
			if (TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante().getIdTipoComprobante())) {
				referenciaFactura = ((ShvCobFacturaMic)factura).getIdReferenciaFactura();
			} else {
				referenciaFactura = Utilidad.parsearNroDocNoDuc(
						factura.getClaseComprobante(), factura.getSucursalComprobante(), factura.getNumeroComprobante());	
			}

			String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7);
			
			leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, referenciaFactura, idOperacionFormateado);

			if(esInteresRegulado){
				factura.setLeyendaFacturaInteres(leyendaFacturaInteres);
				factura.setOrigenCargo(origenInteres);
			}else {
				factura.setLeyendaFacturaInteresNoReg(leyendaFacturaInteres);
				factura.setOrigenCargoInteresNoReg(origenInteres);
			}
			
			
		} else {
			
			String mensaje = "La factura y el id operacion cobro son nulos.";
			Traza.error(getClass(), mensaje);
			throw new NegocioExcepcion(mensaje);
		}
		
		return factura;
	}
	
	/**
	 * 
	 * @param datos
	 * @param marca
	 * @param cobro
	 * @param cuerpo
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 * @throws IOException
	 */
	private void enviarMailResultadoSimulacionBatch (List<ResultadoBusquedaDatosSimulacion> listaDatos, 
			MarcaEnum marca, ShvCobDescobro descobro, StringBuffer cuerpo) throws NegocioExcepcion, PersistenciaExcepcion, IOException {
		
		ByteArrayOutputStream outputStream = null;
		ResultadoBusquedaDatosSimulacion datos = listaDatos.get(0);
		String cc ="";
		try {
			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(datos.getAnalista());

			outputStream = new ByteArrayOutputStream();
			
			HSSFWorkbook workbook = descobroServicio.exportarDescobro(descobro.getIdDescobro(), descobro.getIdCobro());
			
			workbook.write(outputStream);
			
			String cliente = "";
			if(!Validaciones.isObjectNull(datos.getIdClienteLegado())){
				cliente = datos.getIdClienteLegado().toString();
			}
			
			String name = Utilidad.EMPTY_STRING;
			String asunto = Utilidad.EMPTY_STRING;			
			
			if (!Validaciones.isObjectNull(descobro.getOperacion().getIdOperacion())) {
				asunto = Utilidad.reemplazarMensajes(Mensajes.DESCOBROS_MAIL_ASUNTO, 
						//la descripcion de la marca es la dice en el mail si la simulacion salio OK o con ERROR
						marca.descripcion(), 
						descobro.getOperacion().getIdOperacion().toString(), cliente, datos.getRazonSocial());
				name = "ID Operación Reversión Cobro " + Utilidad.rellenarCerosIzquierda(descobro.getOperacion().getIdOperacion().toString().trim(), 7);
			} else {
				name = "ID Operación Reversión Cobro XXXXXXX";
			}
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), name, EXTENSION_ARCHIVO_ADJUNTO));

			String conCopia = Utilidad.agregarAnalistaTeamComercialACopropietario(listaDatos, datos.getCopropietario());
			
			if(!Validaciones.isNullOrEmpty(conCopia)){
				for(String usuario : conCopia.split(";")){
					UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(usuario);
					if(!Validaciones.isObjectNull(usuarioLdap)) {
						if(!Validaciones.isNullOrEmpty(usuarioLdap.getMail())){
							cc += usuarioLdap.getMail() + ";";
						}
					}
				}
			}
			
			Mail mail = new Mail(analista.getMail(), asunto, cuerpo);
			mail.setAdjuntos(listaAdjuntos);
			
			if(!Validaciones.isNullOrEmpty(cc)) {
				
				mail.setDestinatarioCC(cc);
			}
			
			mailServicio.sendMail(mail);
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
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
	private void crearTareaPendienteResultadoSimulacionBatch(ShvCobDescobro descobro, List<ResultadoBusquedaDatosSimulacion> listaDatos, 
									MarcaEnum marca, BigDecimal importe, Boolean enviarMail) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(descobro.getWorkflow().getIdWorkflow());
		
		if(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.equals(marca)) {
			tarea.setTipoTarea(TipoTareaEnum.DES_RES_SIM_OK);
		}else if(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.equals(marca)) {
			tarea.setTipoTarea(TipoTareaEnum.DES_RES_SIM_ERR);
		}
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioCreacion(descobro.getWorkflow().getUsuarioAlta());
		tarea.setNroCliente(listaDatos.get(0).getIdClienteLegado().toString());
		tarea.setRazonSocial(listaDatos.get(0).getRazonSocial());
		tarea.setUsuarioAsignacion(listaDatos.get(0).getAnalista());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.DESCOBRO);
		tarea.setMonedaImporte(descobro.getMonedaOperacion().getSigno2());
		
		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));
		
		
		tarea.setIdDescobro(Long.valueOf(descobro.getIdDescobro()));
		tarea.setIdOperacion(descobro.getOperacion().getIdOperacion());
		tarea.setEnviarMail(enviarMail);
		

		tareaServicio.crearTareaResultadoSimulacion(tarea);
	}
}