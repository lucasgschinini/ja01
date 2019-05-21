package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsServicio;
import ar.com.telecom.shiva.base.jms.util.JmsMonitorMensajeria;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebitoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoSalidaCobranzasWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleMedioPagoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDescobroGeneracionCargosInteresDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleDescobroMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicResultadoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacion;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroCargoGeneradoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroCargoGeneradoMic;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroDiferenciaCambio;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroFactura;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroMedioPagoCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionDescobroMedioPagoMic;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;

@Service
public class DescobroBatchImputacionServicioImpl extends Servicio implements IDescobroImputacionServicio {

	@Autowired 
	private JmsMonitorMensajeria monitor;

	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired 
	IWorkflowDescobros workflowDescobros;
	
	@Autowired 
	ICobroDao cobroDao;
	
	@Autowired 
	IDescobroDao descobroDao;
	
	@Autowired
	IMicJmsServicio micJmsServicio;
	
	@Autowired 
	ITareaServicio tareaServicio;
	
	@Autowired
	MailServicio mailServicio;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	@Autowired
	IDescobroServicio descobroServicio;
	
	@Autowired
	IValorServicio valorServicio;
	
	@Autowired 
	IValorDao valorDao;
	
	@Autowired 
	IClienteCalipsoServicio clienteCalipsoServicio;
	
	@Autowired
	IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired 
	IScardServicio scardServicio;
	@Autowired private ParamRespWfTareaServicioImpl paramRespWfTareaServicioImpl;
	
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired ITareaDao tareaDao;
	
	@Autowired 
	ICobroOnlineDao cobroOnlineDao;
	
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
	
	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#modificar(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {

	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#anular(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public void anular(DTO dto) throws NegocioExcepcion {

	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#getDatosModificados(ar.com.telecom.shiva.base.dto.DTO)
	 */
	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.IServicio#verificarConcurrencia(java.io.Serializable, java.lang.Long)
	 */
	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {

	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public List<Long> listarDescobrosPendientesImputacion()
			throws NegocioExcepcion {
		try {
			List<Long> lista = descobroDao.listarDescobrosPendientes();
			return lista;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public List<Long> listarDescobrosPendientesProcesarMIC() throws NegocioExcepcion {
		try {
			List<Long> lista = descobroDao.listarDescobrosPendientesProcesarMIC();
			return lista;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion)
			throws NegocioExcepcion {
		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdOperacion(idOperacion);
			return descobro;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public ShvCobDescobro buscarDescobro(Long idDescobro)
			throws NegocioExcepcion {
		
		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobro(idDescobro);
			return descobro;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/***************************************************************************
	 * BATCH IMPUTAR DESCOBRO
	 *************************************************************************/
	/**
	 * Método que recibe un cobro y se comunica con Calipso y MIC.
	 * 
	 * @throws NegocioExcepcion 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvCobDescobro imputarDescobro(Long idOperacionDescobro, int count) throws NegocioExcepcion {
		ControlVariablesSingleton.setReintentoExcepcion(null);
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		String descripcionErrorDescobro = null;
		
		
		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			ShvCobDescobro descobro = this.buscarDescobroPorIdOperacion(idOperacionDescobro);
			
			List<ShvCobTransaccion> listaTransaccionesDescobroInversasOrdenadasSinDiferenciaCambio = descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio();
//			List<ShvCobTransaccion> listaTransaccionesDescobroInversasOrdenadas = descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia();
			
			tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), listaTransaccionesDescobroInversasOrdenadasSinDiferenciaCambio, true, count);
			ShvWfWorkflow workflowActualizado=descobro.getWorkflow();
			// Actualizo el estado del descobro a "Descobro EN PROCESO" si el estado actual es "PENDIENTE".
			if (Estado.DES_PENDIENTE.equals(descobro.getWorkflow().getEstado())) {
				workflowActualizado = workflowDescobros.iniciarDescobro(descobro.getWorkflow(), " ",usuarioBatch);
				descobro.setWorkflow(workflowActualizado);
				//u562163 - 21/11 - se pisan los estados del qorkflow con el batch de recepcion de MIC
				//descobro = descobroDao.modificar(descobro);*
			}
			
			//Para esto se recorren aquellas transacciones que se encuentran en los siguientes estados:
			//	Apropiada
			
			boolean cobroPendienteMic = false;
			
			descobrarCompensacionesSap(descobro);
			
			for (ShvCobTransaccion transaccionDescobro : descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia()) {
				String key = transaccionDescobro.getOperacionTransaccionFormateado();

				// Si la transaccion esta en uno de los estados de error, 
				// hago avanzar el workflow a error del descobro para notificar al usuario el error
				if (EstadoTransaccionEnum.listarEstadosErrorDescobro().contains(transaccionDescobro.getEstadoProcesamiento())) {
					descripcionErrorDescobro = 
								"Se ha intentado descobrar a la transaccion "
								+ "("+key+") con el estado: " + transaccionDescobro.getEstadoProcesamiento();
					break;
				}
				
				
				// Actualizo el estado de la transaccion a "EN PROCESO" si el estado actual es "PENDIENTE".
				if(EstadoTransaccionEnum.PENDIENTE_DESCOBRO.equals(transaccionDescobro.getEstadoProcesamiento())){
					transaccionDescobro.setEstadoProcesamiento(EstadoTransaccionEnum.EN_PROCESO_DESCOBRO);
//					transaccionDescobro.getEstadoProcesamiento();
					descobro.getTransaccionPorIdTransaccion(transaccionDescobro.getIdTransaccion()).setEstadoProcesamiento(transaccionDescobro.getEstadoProcesamiento());
					//u562163 - 21/11 - se pisan los estados del qorkflow con el batch de recepcion de MIC
					//descobro = descobroDao.modificar(descobro);*
				}
				
				try {
					
					// Primera vez o estoy esperando respuesta de MIC.
					if(EstadoTransaccionEnum.EN_PROCESO_DESCOBRO.equals(transaccionDescobro.getEstadoProcesamiento())){
						
						ShvCobFactura factura = transaccionDescobro.getFactura(); 
						
						//Lista de medios de pago a enviar
						List<ShvCobMedioPago> listaMediosPagoMIC = listarMediosPago(transaccionDescobro,SistemaEnum.MIC);
						List<ShvCobMedioPago> listaMediosPagoCalipso = listarMediosPago(transaccionDescobro,SistemaEnum.CALIPSO);
						//Listas para generacion de cargos
						List<ShvCobMedioPago> listaMediosPagoProxFacturaMic = listarMediosPagoProxFactura(transaccionDescobro,SistemaEnum.MIC);
						List<ShvCobMedioPago> listaMediosPagoProxFacturaCalipso = listarMediosPagoProxFactura(transaccionDescobro,SistemaEnum.CALIPSO);
						//Para reintegros
						ShvCobTratamientoDiferencia tratamientoDiferencia = transaccionDescobro.getTratamientoDiferencia();
						
						EstadoFacturaMedioPagoEnum estadoMedioPagoMIC = getEstadoMedioPagoMIC(transaccionDescobro);
						EstadoFacturaMedioPagoEnum estadoMedioPagoCalipso = getEstadoMedioPagoCalipso(transaccionDescobro);
						EstadoFacturaMedioPagoEnum estadoTratamientoDiferencia = getEstadoTratamientoDiferencia(transaccionDescobro);
						
						ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProxFacturaClp = 
								Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaCalipso) ? (ShvCobMedioPagoDebitoProximaFacturaCalipso) listaMediosPagoProxFacturaCalipso.get(0): null;
								
						ShvCobMedioPagoDebitoProximaFacturaMic debitoProxFacturaMic = 
								Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaMic) ? (ShvCobMedioPagoDebitoProximaFacturaMic) listaMediosPagoProxFacturaMic.get(0): null;
						
						Utilidad.tracearTiempo(getClass(), "Tiempo en comenzar algun descobro", fechaHoraInicioNanoTime);
						
						//Si esta transaccion no tiene factura
						if (Validaciones.isObjectNull(factura)) {
							if (TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							} else {
								
								//si hay mp/tratamiento pendiente para enviar a  calipso 
								if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)
										|| (EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoTratamientoDiferencia)
												&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia()))
										) {
									
									if(descobroCalipso(descobro, transaccionDescobro, null, listaMediosPagoCalipso, tratamientoDiferencia, debitoProxFacturaClp)){
										Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
										tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
										//si se descobró bien en calipso, pregunto si hay mp o tratamiento para enviar a mic
										if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)
												|| (EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoTratamientoDiferencia)
														&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia()))){
											descobroMic(transaccionDescobro, null, listaMediosPagoMIC, tratamientoDiferencia, debitoProxFacturaMic);
											cobroPendienteMic = true;
											workflowActualizado=pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
										}
									}
								}else {
									//si hay mp/tratamiento para enviar a mic y no hay mp/tratamiento calipso
									if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)
											|| (EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoTratamientoDiferencia)
													&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia()))){
										descobroMic(transaccionDescobro, null, listaMediosPagoMIC, tratamientoDiferencia, debitoProxFacturaMic);
										cobroPendienteMic = true;
										workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
									}
								}
							}
							
						} else {
							if (haySaldoACobrar(transaccionDescobro)){
								factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
								factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
								
							} else{
								
								// Si la factura es de MIC, primero descobro los MP de Calipso y luego los de MIC.
								if(factura instanceof ShvCobFacturaMic){
									if(existenMediosPagoCalipso(transaccionDescobro)){
										if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)) {
											
											// Enviar el mensaje a la interfaz calipso.
											if(descobroCalipso(descobro, transaccionDescobro, null, listaMediosPagoCalipso, null, debitoProxFacturaClp)){
												Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
												tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
												//Descobro a Mic 
												if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)){
														// descobro la factura y los MP y Prox Factura de MIC.
														descobroMic(transaccionDescobro, factura, listaMediosPagoMIC, null, debitoProxFacturaMic);
														cobroPendienteMic = true;
														workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
													}
												} else {
													//solo hay factura de MIC
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
														// descobro la factura.
														descobroMic(transaccionDescobro, factura, null, null, debitoProxFacturaMic);
														cobroPendienteMic = true;
														workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
													}
												}
											}
										} else {
											if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(estadoMedioPagoCalipso)) {
												//Descobro a Mic 
												if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)){
														//descobro la factura y los MP y Prox Factura de MIC.
														descobroMic(transaccionDescobro, factura, listaMediosPagoMIC, null, debitoProxFacturaMic);
														cobroPendienteMic = true;
														workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
													}
												}else{
													//solo hay factura de MIC
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
														// descobro la factura.
														descobroMic(transaccionDescobro, factura, null, null, debitoProxFacturaMic);
														cobroPendienteMic = true;
														workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
													}
												}
											}
										}
									}else{
										// No hay medios de pago Calipso
										if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
											// La transaccion no contiene MP de Calipso. Descobro a MIC
											if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)){
												// descobro la factura y los MP y ProxFactura de MIC 
												descobroMic(transaccionDescobro, factura, listaMediosPagoMIC, null, debitoProxFacturaMic);
												cobroPendienteMic = true;
												workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
											}
										} else {
											// No hay medios de pago MIC ni proxima factura. Por lo tanto contiene una factura MIC
											// y Medios de pago Shiva y/o Usuario
											if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
												//Descobro Deuda
												descobroMic(transaccionDescobro, factura, null, null, debitoProxFacturaMic);
												cobroPendienteMic = true;
												workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
											}
										}
									}
								} else {
									// Si la factura es de CALIPSO, primero descobro los MP de MIC y luego los de CALIPSO.
									if(factura instanceof ShvCobFacturaCalipso){
										if(existenMediosPagoMic(transaccionDescobro)){
											// Tengo que descobrar los Medios de pago de MIC. 
											if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)){
												descobroMic(transaccionDescobro, factura, listaMediosPagoMIC, null, debitoProxFacturaMic);
												cobroPendienteMic = true;
												workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
											} else {
												if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(estadoMedioPagoMIC)){
													// Si los MP de MIC estan Descobrados.
													// Descobro los MP / prox factura y la factura de Calipso.
													if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)){
														if (EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)){
															descobroCalipso(descobro, transaccionDescobro, factura, listaMediosPagoCalipso, null, debitoProxFacturaClp);
															Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
															tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
														}
													} else {
														//Descobro Deuda
														if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
															descobroCalipso(descobro, transaccionDescobro, factura, null, null, debitoProxFacturaClp);
															Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
															tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
														}
													}
												}
											}
										} else {
											// si la transaccion no tiene MP de MIC. Descobro los MP / debito prox factura de Calipso.
											if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)) {
												if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)) {
													descobroCalipso(descobro, transaccionDescobro, factura, listaMediosPagoCalipso, null, debitoProxFacturaClp);
													Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
													tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
												}
											} else {
												// No hay medios de pago CALIPSO ni proxima factura. Por lo tanto contiene una factura CALIPSO
												// y Medios de pago Shiva y/o Usuario
												if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
													//Descobro Deuda
													descobroCalipso(descobro, transaccionDescobro, factura, null, null, debitoProxFacturaClp);
													Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
													tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
												}
											}
										}
									} else 
										// Si la factura es Otro Debito, primero descobro los MP de MIC y luego los de CALIPSO.
										if(factura instanceof ShvCobFacturaUsuario){
											if(existenMediosPagoMic(transaccionDescobro)){
												// Tengo que descobrar los Medios de pago de MIC. 
												if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoMIC)){
													descobroMic(transaccionDescobro, null, listaMediosPagoMIC, null, null);
													cobroPendienteMic = true;
													workflowActualizado= pasarWorkflowProcesoAPendienteMIC(transaccionDescobro.getOperacion().getIdOperacion(), usuarioBatch, TipoInvocacionEnum.$09, transaccionDescobro.getOperacionTransaccionFormateado(),workflowActualizado);
												} else {
													if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(estadoMedioPagoMIC)){
														// Si los MP de MIC estan Descobrados. Descobro los MP de Calipso.
														if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)){
															if (EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)){
																descobroCalipso(descobro, transaccionDescobro, null, listaMediosPagoCalipso, null, null);
																Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
																tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
															} else if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(estadoMedioPagoCalipso)){
																factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
															}
														} else {
															//Descobro Deuda
															if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
																factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
															}
														}
													}
												}
											} else {
												// si la transaccion no tiene MP de MIC. Descobro los MP de Calipso.
												if(Validaciones.isCollectionNotEmpty(listaMediosPagoCalipso)) {
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(estadoMedioPagoCalipso)) {
														descobroCalipso(descobro, transaccionDescobro, null, listaMediosPagoCalipso, null, null);
														Traza.auditoria(getClass(), "[TRACEO DESPUES DE DESCOBRO CALIPSO]");
														tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
													} else if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(estadoMedioPagoCalipso)){
														factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
														factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
													}
												} else {
													// No hay medios de pago CALIPSO. Por lo tanto contiene una factura Usuario
													if(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(factura.getEstado())){
														//Descobro Deuda
														factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
														factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
													}
												}
											}
										
									}
								}
							}
						}
						
						//hago esto porque sino pierdo los cambios que hice en la transaccion
						verificarEstadoTransaccion(descobro.getTransaccionPorNumeroTransaccion(transaccionDescobro.getNumeroTransaccion()), usuarioBatch);
						
						tracearDatosImputacionDescobro(descobro.getIdDescobro(), workflowActualizado, descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
						//actualizar el descobro
						descobro.setWorkflow(workflowActualizado);
						//u562163 - 21/11 - se pisan los estados del qorkflow con el batch de recepcion de MIC
						//descobro = descobroDao.modificar(descobro);*
						
						if(cobroPendienteMic || !EstadoTransaccionEnum.DESCOBRO.equals(descobro.getTransaccionPorNumeroTransaccion(transaccionDescobro.getNumeroTransaccion()).getEstadoProcesamiento())){
							break;
						}
					}
				} catch (ReintentoExcepcion e) {
					return actualizarEstadoDescobroPorReintentoMaximo(descobro, usuarioBatch, e);
				}
				
			} /*Fin FOR transaccion*/
			
			
			/**
			 * SHV - En caso que ocurra un error en la reversión del cobro en alguno de los sistemas legados, 
			 * Shiva finalizará con la ejecución del proceso batch
			 * 
			 * El sistema Shiva irá solicitando las reversiones de las transacciones a 
			 * los sistemas legados. Si ocurre una falla en alguno de ellos, 
			 * Shiva procederá a cortar la ejecución del proceso batch, 
			 * avisarle al usuario de lo ocurrido y mostrarle el error asociado 
			 * a la línea de la transacción que corresponda para que éste 
			 * determine si debe hacer una acción previa para solucionar 
			 * el error (por ejemplo revertir un cobro posterior que no se haya 
			 * detectado en la simulación debido a que se llevó a cabo posterior 
			 * a ella), reenviar el pedido de reversión de manera manual 
			 * (por ejemplo si se dio un error de conectividad con el sistema legado) 
			 * o bien levantar un incidente para que se resuelva el error.  
			 * En el caso que se haya detectado una nueva operación de cobro relacionada 
			 * se mostrará el detalle de la misma en la sección de mensaje asociado al 
			 * débito o al crédito pero no se cargará la grilla de operaciones relacionadas.
			 * 
			 * Cabe destacar que al contrario de lo que ocurre con los errores en el proceso de 
			 * cobros, la reversión no se volverá atrás.  
			 * Es decir, ocurrido un error se frenará la ejecución dejando la parte 
			 * que se pudo revertir revertida y la parte del error en adelante pendiente de procesar.
			 */
			if (!Validaciones.isNullOrEmpty(descripcionErrorDescobro)) {
				//Cambio estado del descobro por el error con la descripción
				cambiarEstadoErrorDescobro(descobro, descripcionErrorDescobro, usuarioBatch);
			}
			
			/**
			 * SHV - Revertir operaciones de cobros en Shiva
			 * En Shiva se deberá revertir:
			 * 		Apropiación de valores Shiva.
			 * Esta reversión interna se llevará a cabo si la reversión en el sistema legado 
			 * finalizó correctamente.  
			 * En caso que el débito asociado a la transacción haya quedado apropiado, 
			 * no se procederá a devolverle el saldo al correspondiente valor.
			 * Se agregan tambien la reversion de medios de pago usuario 
			 * y tratamiento a la diferencia que no sea credito a proxima factura			 */
			if(restoTransaccionesDescobradas(descobro)){
				
				descobro = descobroMedioPagoShivaUsuarioYTratamientoDiferencia(descobro);
				
			}
			
			//veo si puedo cambiar el estado del descobro
			verificarEstadoDescobro(descobro, "", usuarioBatch);
			
			//Actualizacion final
			descobro = descobroDao.modificar(descobro);
			
			//--Se tracea el resultado del descobro
			tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio(), false, 0);
			
			Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
			
			return descobro;
		
		} catch (PersistenciaExcepcion | ReintentoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}


	private void descobrarCompensacionesSap(ShvCobDescobro descobro) {
		
		List<ShvCobTransaccion> listaTransacciones = descobro.getTransaccionesOrdenadasInversaSinDiferenciaCambio();
		for (ShvCobTransaccion transaccion : listaTransacciones){
			
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
				
				if(medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto
						|| medioPago instanceof ShvCobMedioPagoCompensacionProveedor
						|| TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
					if (!EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
						Traza.auditoria(getClass(), "Descobro de medio de pago compensacion SAP de la transaccion[" + transaccion.getIdTransaccion() +"]");
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						medioPago.setMensajeEstado(TipoMensajeEstadoEnum.OK.getDescripcion());
					}
				} else {
					return;
				}
			}
		}
		
		tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), listaTransacciones, false, 1);
		
	}

	private boolean haySaldoACobrar(ShvCobTransaccion transaccionDescobro) {
		for (ShvCobMedioPago medioPago : transaccionDescobro.getMediosPago()) {
			if (TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Recorre todas las transferencias que posea el cobro. Si todas estan en
	 * estado APROPIACION envio la confirmacion. Si existe alguna transaccion en
	 * estado ERROR verifico si es necesario desapropiar y envio la desapropiacion.
	 * 
	 * @param descobro
	 * @throws ReintentoExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private void verificarEstadoDescobro(ShvCobDescobro descobro, String mensajeWorkflow, String usuarioBatch) throws NegocioExcepcion, ReintentoExcepcion, PersistenciaExcepcion {

		List<ShvCobTransaccion> listaTransaccionesOrdenadaInversa = descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio();

		// Si existe alguna transaccion en estado "EN_PROCESO_DESCOBRO" no tomo ninguna accion
		if (!existenTransaccionesEnProcesoDescobro(descobro)) {

			// Si la primer transaccion esta en estado Error
			if (verificarErrorEnPrimerDescobro(listaTransaccionesOrdenadaInversa)) {
				// se cambia el estado
				cambiarEstadoDescobro(descobro, mensajeWorkflow, Estado.DES_ERROR_EN_PRIMER_DESCOBRO, usuarioBatch);
				return;

			} else {

				if (todasTransaccionesDescobradas(descobro)) {
					if (!Validaciones.isObjectNull(this.retornaTratamientoDiferenciaAplicacionManual(descobro)) &&
					   (Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefCaja()) &&
						Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefOperacionesExternas())) ||
						existenTransaccionesConFacturaUsuario(descobro)){
						cambiarEstadoDescobro(descobro, mensajeWorkflow, Estado.DES_PENDIENTE_CONFIRMACION_MANUAL, usuarioBatch);
					} else {
						if (Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.DES_CONFIRMA_APL_MAN, null, null, descobro.getWorkflow().getIdWorkflow()))){
							cambiarEstadoDescobro(descobro, mensajeWorkflow, Estado.DES_DESCOBRADO, usuarioBatch);
							//Informo los cambios a contabilidad y scard
							descobroServicio.informarAContabilidadYScard(descobro);
						}
					}
				} else {
					cambiarEstadoDescobro(descobro, mensajeWorkflow, Estado.DES_EN_ERROR, usuarioBatch);
				}

			}

		}

	}
	
	private TipoTratamientoDiferenciaEnum retornaTratamientoDiferenciaAplicacionManual(ShvCobDescobro descobro) {
		for (ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()) {
			ShvCobTratamientoDiferencia shvCobTratamientoDiferencia = transaccion.getTratamientoDiferencia();
			if (
				!Validaciones.isObjectNull(shvCobTratamientoDiferencia) && 
				(
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia()) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia()) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia()) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia()) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia()) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(shvCobTratamientoDiferencia.getTipoTratamientoDiferencia())
			)
			) {
				return shvCobTratamientoDiferencia.getTipoTratamientoDiferencia();
			}
		}
		return null;
	}
	
	private boolean existenTransaccionesConFacturaUsuario(ShvCobDescobro descobro) throws PersistenciaExcepcion {
		for (ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()) {
			ShvCobFactura factura = transaccion.getFactura();
			if(factura instanceof ShvCobFacturaUsuario){
				return (Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefCaja()) && Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefOperacionesExternas()));
			}
		}
		return false;
	}
	
	/**
	 * Verifica si exiten transacciones pertenecientes al descobro en estado "En Proceso Descobro".
	 * @param descobro
	 * @return
	 */
	private boolean existenTransaccionesEnProcesoDescobro(ShvCobDescobro descobro) {
		for (ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()) {
			if(EstadoTransaccionEnum.EN_PROCESO_DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	private boolean restoTransaccionesDescobradas(ShvCobDescobro descobro) {
		
		boolean descobrarMedioPagoShivaUsuarioTratamiento = false;
		
		if(!Validaciones.isObjectNull(descobro)){
			
			Integer pendienteDescobroCobrador = 0;
			Integer pendienteDescobroNoCobrador = 0;
			
			for(ShvCobTransaccion transaccion : descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio()){
				
				if (EstadoTransaccionEnum.listarEstadosDescobroNoDescobrado().contains(transaccion.getEstadoProcesamiento())) {
					if (!Validaciones.isObjectNull(transaccion.getFactura())
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getFactura().getEstado())){
						pendienteDescobroCobrador++;
					}
				
				
					if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
						if((TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								)
								&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
							pendienteDescobroCobrador++;
						}
						if((!TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
								)
								&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
							pendienteDescobroNoCobrador++;
						}
					}
					
					for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
						if ((
							!(medioPago instanceof ShvCobMedioPagoUsuario)
							&& !(medioPago instanceof ShvCobMedioPagoShiva)
							)
								&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
							pendienteDescobroCobrador++;
						}
						
						if((medioPago instanceof ShvCobMedioPagoUsuario)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
							pendienteDescobroNoCobrador++;
						}
									
						if((medioPago instanceof ShvCobMedioPagoShiva)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
							pendienteDescobroNoCobrador++;
						}
					}
				}
			}
			
			if(pendienteDescobroCobrador == 0 && pendienteDescobroNoCobrador > 0){
				descobrarMedioPagoShivaUsuarioTratamiento = true;
			}
		}		
		
		return descobrarMedioPagoShivaUsuarioTratamiento;
	}
	
	
	private boolean restoTransaccionDescobradaMenosTratamientoDiferencia(ShvCobTransaccion transaccion) {
		
		if (EstadoTransaccionEnum.listarEstadosErrorDescobro().contains(transaccion.getEstadoProcesamiento())) {
			if (!Validaciones.isObjectNull(transaccion.getFactura())
					&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getFactura().getEstado())){
				return false;
			}
		}
		
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			if (!EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Verifica que si la primer transaccion esta en un estado de ERROR y las demas
	 * transacciones estan en estado PENDIENTE retorna true para que se aplique el estado
	 * primer error en descobro
	 * @param listaTransaccionesOrdenadaInversa
	 * @return
	 */
	private boolean verificarErrorEnPrimerDescobro(List<ShvCobTransaccion> listaTransaccionesOrdenadaInversa) {
		
		ShvCobTransaccion primeraTransaccion = listaTransaccionesOrdenadaInversa.get(0);
		if (EstadoTransaccionEnum.listarEstadosErrorDescobro().contains(primeraTransaccion.getEstadoProcesamiento())) {
			if (!Validaciones.isObjectNull(primeraTransaccion.getFactura())){
				if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(primeraTransaccion.getFactura().getEstado())){
					return false;
				}
			}
			
			if (!Validaciones.isObjectNull(primeraTransaccion.getTratamientoDiferencia())){
				if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(primeraTransaccion.getTratamientoDiferencia().getEstado())){
					return false;
				}
			}
			
			for (ShvCobMedioPago medioPago : primeraTransaccion.getMediosPago()) {
				if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
					return false;
				}
			}
			
		} else{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica si todas las transacciones del descobro estan Descobradas
	 * 
	 * @param cobro
	 * @return
	 */
	private Boolean todasTransaccionesDescobradas(ShvCobDescobro descobro) {
		for (ShvCobTransaccion transaccion : descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio()) {
			if(!EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Cambia el estado del workflow del descobro y lo guarda en la base
	 * @param descobro
	 * @param estado
	 * @param usuarioBatch
	 * @throws NegocioExcepcion 
	 */
	private void cambiarEstadoDescobro(ShvCobDescobro descobro, String mensajeWorkflow, Estado estado, String usuarioBatch) throws NegocioExcepcion {
		//try{
			ShvWfWorkflow workflowActualizado = descobro.getWorkflow();
			if (Estado.DES_DESCOBRO_PROCESO.equals(descobro.getWorkflow().getEstado())) {
				if (Estado.DES_DESCOBRADO.equals(estado)) {
					workflowActualizado = workflowDescobros.aplicarDescobro(descobro.getWorkflow(), mensajeWorkflow, usuarioBatch);
				} else if (Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(estado)) {
					workflowActualizado = workflowDescobros.registrarErrorEnPrimerDescobro(descobro.getWorkflow(), mensajeWorkflow, usuarioBatch);
				} else if (Estado.DES_EN_ERROR.equals(estado)) {
					workflowActualizado = workflowDescobros.registrarDescobroEnError(descobro.getWorkflow(), mensajeWorkflow, usuarioBatch);
				} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
					Traza.auditoria(getClass(), "Se cambia a DES_PENDIENTE_CONFIRMACION_MANUAL");
					workflowActualizado = workflowDescobros.registrarDescobroEnProcesoAPendienteConfirmacionManual(descobro.getWorkflow(), mensajeWorkflow, usuarioBatch);
				}
			} else {
				String mensaje = "Se quizo avanzar en el workflow del Descobro a un estado final, pero el descobro no se encuentra en estado DES_DESCOBRO_PROCESO. Id Descobro: " + descobro.getIdDescobro().toString() + ". ";
				Traza.error(getClass(), mensaje);
				throw new NegocioExcepcion(mensaje);
			}
			
			descobro.setWorkflow(workflowActualizado);
			descobro.setFechaReversion(workflowActualizado.getFechaUltimaModificacion());
			descobro.setUsuarioReversion(usuarioBatch);
			descobro.setFechaUltimaModificacion(workflowActualizado.getFechaUltimaModificacion());
			descobro.setUsuarioUltimaModificacion(usuarioBatch);
			//u562163 - 21/11 - se pisan los estados del workflow con el batch de recepcion de MIC
			//descobroDao.modificar(descobro);*
			
		//} catch (PersistenciaExcepcion e) {
		//	throw new NegocioExcepcion(e.getMessage(),e);
		//}
		
	}
	
	
	
	
	/**
	 * Se Tracean los datos de imputacion 
	 * @param cobro
	 */
	private void tracearDatosImputacionDescobro(Long idDescobro, ShvWfWorkflow workflow,
			    List<ShvCobTransaccion> transaccionesOrdenadas,
				boolean esInicio, int contadorCobro)
	{
		
		StringBuffer mensaje = new StringBuffer("\n");
		if(esInicio){
			mensaje.append("Descobro " + contadorCobro + " a imputar: ");
		}else{
			mensaje.append("Descobro - resultado: ");
		}
		
		String cobroEstado = "idDescobro: " + idDescobro + " | estado: " + workflow.getEstado().descripcion();
		mensaje.append(cobroEstado);
		mensaje.append("\n");
		
		for(ShvCobTransaccion tran : transaccionesOrdenadas){
			String idOperacion   = Utilidad.rellenarCerosIzquierda(tran.getOperacion().getIdOperacion().toString(), 7);
    		String numeroTransaccion = Utilidad.rellenarCerosIzquierda(tran.getNumeroTransaccion().toString(), 5);
    		String idOperacionTransaccion = idOperacion+"."+numeroTransaccion;
    		
    		String salidaTraceo = "-Transaccion ("+idOperacionTransaccion+"), id: " 
    				+ tran.getIdTransaccion() + ", estado: " + tran.getEstadoProcesamiento().descripcion();
    		mensaje.append(salidaTraceo).append("\n");
    		
    		for(ShvCobFactura fact : tran.getShvCobFactura()){
				salidaTraceo = 
						Utilidad.rellenarEspaciosDerecha("|Fact   id: " + fact.getIdFactura(), 31) +
						Utilidad.rellenarEspaciosDerecha("| tipo: " + ((fact.getSistemaOrigen() != null ? fact.getSistemaOrigen():"-")), 17) + 
						"| estado: " + fact.getEstado().descripcion();
				
				mensaje.append(salidaTraceo).append("\n");
			}
    		
    		ShvCobTratamientoDiferencia tratamiento = tran.getTratamientoDiferencia();
    		if (!Validaciones.isObjectNull(tratamiento)){
    			salidaTraceo = 
    					Utilidad.rellenarEspaciosDerecha("|Tratamiento   id: " + tratamiento.getIdTratamientoDiferencia(), 24) +
    					Utilidad.rellenarEspaciosDerecha("| tipo: " + tratamiento.getTipoTratamientoDiferencia(), 17) + 
    						"| estado: " + tratamiento.getEstado().descripcion();
    			
    			mensaje.append(salidaTraceo).append("\n");
    		}
			
			int count = 1;
			
			for(ShvCobMedioPago mp : tran.getShvCobMedioPago()){
				String medioPagoTraceo = Utilidad.rellenarEspaciosDerecha("|MP " + count +"   id: " + mp.getIdMedioPago(), 31); 
				medioPagoTraceo += Utilidad.rellenarEspaciosDerecha("| tipo: " + ((mp instanceof ShvCobMedioPagoMic)?"MIC":(mp instanceof ShvCobMedioPagoCalipso)?"CALIPSO":
						(mp instanceof ShvCobMedioPagoUsuario)?"USUARIO":
						(mp instanceof ShvCobMedioPagoShiva)?"SHIVA":"-"), 17);
				medioPagoTraceo += "| estado: " + mp.getEstado().descripcion();
				
				mensaje.append(medioPagoTraceo).append("\n");		
				count++;
			}
		}
		Traza.auditoria(getClass(), mensaje.toString());
	}
	
	/**
	 * Recorre todas los Medios de pago y la factura que posea la transaccion y si todas tienen
	 * el mismo estado, actualiza el estado de la transaccion al mismo estado.
	 * @param transaccion
	 * @throws ReintentoExcepcion 
	 */
	private void verificarEstadoTransaccion(ShvCobTransaccion transaccion, String usuarioBatch) throws NegocioExcepcion, ReintentoExcepcion{
		
		//preparo la respuesta OK forzada para actualizar la diferencia de cambio
		RespuestaInvocacion respuestaOk = new RespuestaInvocacion();
		respuestaOk.setResultado(TipoResultadoEnum.OK);
		
		if (Validaciones.isObjectNull(transaccion.getFactura())){
			//Transaccion con tratamiento diferencia
			
			if(existeMedioPagoEnError(transaccion)){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESCOBRO_MEDIO_PAGO);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				
				return;
			}
			
			//genero cargos cuando los medios de pago estan descobrados
//			transaccion = imputarContracargosCruzados(transaccion);
			
			if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())
					&& todosMediosPagoDescobrados(transaccion)){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESCOBRO);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				
				//Actualizo el tipo mensaje estado a OK de los registros de diferencia de cambio
				actualizarMensajeEstadoDiferenciaCambio(respuestaOk, transaccion);
			}
			
		} else {
			//Transaccion con factura 
			ShvCobFactura factura = transaccion.getFactura();
			
			if(existeMedioPagoEnError(transaccion)){
				if (EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(factura.getEstado())
						|| EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO.equals(factura.getEstado())){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESCOBRO_MEDIO_PAGO_DEUDA);
					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					
					return;
				}else{
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESCOBRO_MEDIO_PAGO);
					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					
					return;
				}
			}else{
				if (EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(factura.getEstado())
						|| EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO.equals(factura.getEstado())){
				 	transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESCOBRO_DEUDA);
				 	transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				 	
					return;
				}
			}
			
			// Me guardo el estado de la factura para compararlo con los medios de pago
			EstadoFacturaMedioPagoEnum estadoFactura = factura.getEstado();
			
			// recorro los medios de Pago y comparo con el estado
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
				if(!medioPago.getEstado().equals(estadoFactura)){
					// No hago nada porque los estados son diferentes
					return;
				}
			}
			
			// El estado de la factura y los MPs, son el mismo.
			if(estadoFactura.equals(EstadoFacturaMedioPagoEnum.DESCOBRO)){
				if(EstadoTransaccionEnum.EN_PROCESO_DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESCOBRO);
					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					
					//Actualizo el tipo mensaje estado a OK de los registros de diferencia de cambio
					actualizarMensajeEstadoDiferenciaCambio(respuestaOk, transaccion);
				}else{
					Traza.advertencia(getClass(), "No se puede actualizar el estado de la transaccion numero " + transaccion.getNumeroTransaccion() + " de la operacion id "
							+ transaccion.getOperacionTransaccionFormateado() + " a Descobrada o Error de Descobro porque no esta en estado 'En proceso de descobro'");
				}
			}
		}
	}
	
	
	/**
	A Calipso se le solicitará la reversión de:
		Apropiación de facturas Calipso (en pesos y en dólares)
		Apropiación de Notas de Crédito Calipso (en pesos y en dólares)
		Apropiación de Notas de Débito Calipso (en pesos y en dólares)
		Apropiación de Pagos a Cuenta Calipso (en pesos)
		Reversión de intereses por mora enviados a próxima factura
		Reversión de bonificación de intereses por mora
		Reversión de cargos por tratamiento de la diferencia:
			Traslado de capital a próxima factura
			Reintegro de créditos a próxima factura
		Reversión de documentos emitidos por diferencia de cambio.
		  
		Se deberá imputar automáticamente el contradocumento contra las diferencia 
		de cambio en caso que luego de la reversión la diferencia de cambio quede 
		completamente disponible (sin pagos parciales posteriores en los casos de impuestos 
		por fuera de la diferencia).

		La reversión de los cargos enviados a facturar (cualquiera sean ellos) 
		se podrá hacer de varias formas dependiendo del estado del acuerdo de 
		facturación:
		- Si el cargo todavía no fue facturado, el contracargo se enviará a 
		facturar sobre ese.
		- Si el cargo original fue facturado, el contracargo se podrá enviar a
		facturar sobre el acuerdo de facturación original o sobre otro acuerdo
		de facturación de Calipso relacionado con algún cliente asociado al cobro
		original o bien no facturar el contracargo en Calipso sino por Génesis
		si es que existe un acuerdo facturando asociado a un cliente del cobro.

		Cabe aclarar que en Calipso no se puede validar si un acuerdo de 
		facturación está activo o no, solo se puede validar existencia del mismo.
	
	CLP - Revertir apropiación de Factura, ND, NC y CTA
	Las reversiones que se podrán hacer son:
		Apropiación de facturas Calipso (en pesos y en dólares)
		Apropiación de NC Calipso (en pesos y en dólares)
		Apropiación de ND Calipso (en pesos y en dólares)
		Apropiación de CTAs Calipso (en pesos)
		Reversión de intereses por mora enviados a próxima factura
		Reversión de bonificación de intereses por mora
		Reversión de cargos por tratamiento de la diferencia:
			Traslado de capital a próxima factura
			Reintegro de créditos a próxima factura
		Reversión de documentos emitidos por diferencia de cambio

		Tal como sucede en la simulación, en caso que haya transacciones 
		posteriores que apliquen sobre documentos incluidos en la 
		reversión del cobro (pagos parciales posteriores sobre Pagos 
		a Cuenta o documentos de diferencia de cambio), se deberá 
		obtener el detalle de las mismas.
	
	CLP - Reversion de cobros de doc en dólares
	En los casos de reversiones que incluyan facturas y/o notas de débito 
	en moneda dólar que se hubieran cobrado con diferencia de cambio, 
	se espera que Calipso impute automáticamente el contradocumento 
	contra la diferencia de cambio cuando la misma se encuentre totalmente 
	disponible (sin pagos parciales posteriores sobre los intereses generados 
	en la ND por diferencia de cambio). En consecuencia, como resultado de la 
	imputación, se esperan obtener los contradocumentos generados con su 
	correspondiente detalle de imputación
	 * 
	 * @param descobro
	 * @param transaccion
	 * @param factura
	 * @param listaMediosPagoClp
	 * @param creditoProximaFacturaClp
	 * @param debitoProximaFacturaClp
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	private boolean descobroCalipso(ShvCobDescobro descobro,
			ShvCobTransaccion transaccion,
			ShvCobFactura factura,
			List<ShvCobMedioPago> listaMediosPagoClp,
			ShvCobTratamientoDiferencia creditoProximaFacturaClp, 
			ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProximaFacturaClp)	throws NegocioExcepcion, ReintentoExcepcion {
		
		
		EntradaCalipsoDescobroWS eEntrada = new EntradaCalipsoDescobroWS();
				
		eEntrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO);
		eEntrada.setIdOperacion(transaccion.getOperacion().getIdOperacionOriginal());
		eEntrada.setIdOperacionDescobroMensajeria(transaccion.getOperacion().getIdOperacion());
		eEntrada.setIdTransaccion(transaccion.getIdTransaccion());
		eEntrada.setNumeroTransaccion(transaccion.getNumeroTransaccion());
		eEntrada.setNumeroTransaccionFicticio(transaccion.getNumeroTransaccionFicticio());
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		eEntrada.setTipoOperacion(TipoInvocacionEnum.$09);
		eEntrada.setModoOperacion(SiNoEnum.NO);
		eEntrada.setFacturarContracargoFactura(SiNoEnum.NO);
		eEntrada.setFacturarContracargoCargo(SiNoEnum.NO);
				
		
		//CREDITO PROXIMA CLP
		if (!Validaciones.isObjectNull(creditoProximaFacturaClp)) {
			
			//Detalle de Cargos  a revertir (Agrupador)
			if(!Validaciones.isObjectNull(creditoProximaFacturaClp.getIdMovMerCobrador())){
				eEntrada.setIdMovMer(new BigInteger(creditoProximaFacturaClp.getIdMovMerCobrador().toString()));
			}
			
			//Detalle del Contracargo a Generar por Cargo (Agrupador)
			if((Utilidad.decimalMayorACero(creditoProximaFacturaClp.getCobradorInteresesTrasladados())
                    && TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProximaFacturaClp.getTipoTratamientoDiferencia())
                    && SistemaEnum.CALIPSO.equals(creditoProximaFacturaClp.getSistemaAcuerdoContracargo()))
                    || (!Utilidad.decimalMayorACero(creditoProximaFacturaClp.getCobradorInteresesTrasladados())
                                  && TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProximaFacturaClp.getTipoTratamientoDiferencia())
                                  && SistemaEnum.CALIPSO.equals(creditoProximaFacturaClp.getSistemaAcuerdoContracargo()))){
				eEntrada.setFacturarContracargoCargo(SiNoEnum.SI);
				eEntrada.setAcuerdoFacturacionContracargoCargo(creditoProximaFacturaClp.getAcuerdoContracargo());
			}
		} else {
			
			//FACTURA CLP
			if(!Validaciones.isObjectNull(factura)){
				
				ShvCobFacturaCalipso facturaClp = (ShvCobFacturaCalipso) factura;
				//Detalle de Factura a revertir (Agrupador)
				if(!Validaciones.isObjectNull(facturaClp.getIdCobranza())){
					eEntrada.setIdCobranza(new BigInteger(facturaClp.getIdCobranza().toString()));
				}


				if(Utilidad.decimalMayorACero(facturaClp.getCobradorInteresesTrasladados())){
				
					//Detalle del Contracargo a Generar por Factura (Agrupador) 
					if(SistemaEnum.CALIPSO.equals(facturaClp.getSistemaOrigen()) 
							&& facturaClp.getSistemaOrigen().equals(facturaClp.getSistemaAcuerdoContracargo())){
						eEntrada.setAcuerdoFacturacionContracargoFactura(facturaClp.getAcuerdoContracargo());
						eEntrada.setFacturarContracargoFactura(SiNoEnum.SI);
					}
					
				}	
			}  
			
			//DEBITO PROXIMA CLP
			if (!Validaciones.isObjectNull(debitoProximaFacturaClp)) {
				
				//Detalle de Cargos  a revertir (Agrupador)
				if(!Validaciones.isObjectNull(debitoProximaFacturaClp.getCobradorIdMovMer())){
					eEntrada.setIdMovMer(new BigInteger(debitoProximaFacturaClp.getCobradorIdMovMer().toString()));
				}
				
				//Detalle del Contracargo a Generar por Cargo (Agrupador)
				if((Utilidad.decimalMayorACero(debitoProximaFacturaClp.getCobradorInteresesTrasladados())
                        && SistemaEnum.CALIPSO.equals(debitoProximaFacturaClp.getSistemaOrigen())
                        && debitoProximaFacturaClp.getSistemaOrigen().equals(debitoProximaFacturaClp.getSistemaAcuerdoContracargo()))
                        || (!Utilidad.decimalMayorACero(debitoProximaFacturaClp.getCobradorInteresesTrasladados())
                                      && SistemaEnum.CALIPSO.equals(debitoProximaFacturaClp.getSistemaOrigen())
                                      && debitoProximaFacturaClp.getSistemaOrigen().equals(debitoProximaFacturaClp.getSistemaAcuerdoContracargo()))){
					eEntrada.setFacturarContracargoCargo(SiNoEnum.SI);
					eEntrada.setAcuerdoFacturacionContracargoCargo(debitoProximaFacturaClp.getAcuerdoContracargo());
				}	
			}
		}	
		
		//MEDIOS DE PAGO CLP
		if (Validaciones.isCollectionNotEmpty(listaMediosPagoClp)) {
			
			List<DetalleCTAoNotaCreditoDescobro> listaDetalleCTAoNotaCreditoEntrada = new ArrayList<DetalleCTAoNotaCreditoDescobro>();
			
			for (ShvCobMedioPago medioPago : listaMediosPagoClp) {
				ShvCobMedioPagoCalipso medioPagoClp = (ShvCobMedioPagoCalipso) medioPago;  
				DetalleCTAoNotaCreditoDescobro detalleCTAoNotaCreditoEntrada = new DetalleCTAoNotaCreditoDescobro();
			
				if(medioPagoClp instanceof ShvCobMedioPagoCTA){
					if(!Validaciones.isObjectNull(((ShvCobMedioPagoCTA) medioPagoClp).getIdCobranza())){
						detalleCTAoNotaCreditoEntrada.setIdCobranza(new BigInteger(((ShvCobMedioPagoCTA) medioPagoClp).getIdCobranza().toString()));
					}
				} else if(medioPagoClp instanceof ShvCobMedioPagoNotaCreditoCalipso){
					if(!Validaciones.isObjectNull(((ShvCobMedioPagoNotaCreditoCalipso) medioPagoClp).getIdCobranza())){
						detalleCTAoNotaCreditoEntrada.setIdCobranza(new BigInteger(((ShvCobMedioPagoNotaCreditoCalipso) medioPagoClp).getIdCobranza().toString()));
					}
				}
				
				listaDetalleCTAoNotaCreditoEntrada.add(detalleCTAoNotaCreditoEntrada);
			}
			
			eEntrada.setListaCtaONotaCredito(listaDetalleCTAoNotaCreditoEntrada);
		}
		
		
		if (puedeEnviarMensaje(eEntrada.getIdOperacionDescobroMensajeria(), eEntrada.getIdTransaccion(), eEntrada.getNumeroTransaccion(), 
				SistemaEnum.CALIPSO, TipoInvocacionEnum.$09, false, false)) {
			
			// -> enviar mensaje a CALIPSO con solo medios de pago
			Traza.advertencia(getClass(), "Se envia el descobro de la operacion id: "+ eEntrada.getIdOperacion() +" a Calipso ");
			SalidaCalipsoDescobroWS respuesta = clienteCalipsoServicio.descobrarCobro(eEntrada);

			if(!Validaciones.isObjectNull(respuesta)){
				Traza.advertencia(getClass(), "El resultado de la respuesta de Calipso ("+eEntrada.getIdOperacion()+") "
						+ "fue: " + respuesta.getResultadoLlamadaServicio().toString());
				
				String estadoRespuesta = respuesta.getResultadoLlamadaServicio().getResultado(); //OK / ERR
				if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
						&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuesta.getResultadoLlamadaServicio().getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
					respuesta.getResultadoLlamadaServicio().setCodigoError("");
					respuesta.getResultadoLlamadaServicio().setDescripcionError("");
					respuesta.getResultadoLlamadaServicio().setResultado(estadoRespuesta);
					Traza.auditoria(getClass(), "Metodo "+ MensajeServicioEnum.CLP_DESCOBRO.getDescripcion()
							+" ("+eEntrada.getIdOperacion()+"."+eEntrada.getNumeroTransaccion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
				}
				
				//Si es un warning o un ok se considera imputado correctamente
				if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuesta)
						|| TipoResultadoEnum.WRN.getDescripcionCalipso().equals(estadoRespuesta)){
				
					//Cargo el resultado de la factura de calipso
					ResultadoImputacion resultadoImputacionFactura = this.cargarResultadoFacturaCalipso(eEntrada, respuesta);
					
					//Cargo los resultados de los medios de pago de calipso
					List<ResultadoImputacion> listaResultadoImputacionMediosPago = this.cargarResultadoMedioPagoCalipso(eEntrada, respuesta);
					
					//Cargo los resultados de cargos
					ResultadoImputacion resultadoImputacionCargoGenerado = this.cargarResultadoCargoGeneradoCalipso(eEntrada, respuesta);
					
					//Cargo los resultados de la diferencia de cambio
					List<ResultadoImputacion> resultadoImputacionDiferenciaCambio = this.cargarResultadoDiferenciaCambioCalipso(eEntrada, respuesta);
					
					//Actualizo los detalles de la transaccion
					actualizarDescobro(resultadoImputacionFactura, listaResultadoImputacionMediosPago, resultadoImputacionCargoGenerado, resultadoImputacionDiferenciaCambio, transaccion, descobro);
					Traza.auditoria(getClass(), "[TRACEO DESDE DESCOBRO CALIPSO]");
					tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
					return true;
					
				} else {
					
					//ERROR FACTURA
					ResultadoImputacion resultadoImputacionFactura = errorServicioFactura(factura, respuesta.getResultadoLlamadaServicio().getDescripcionError(), respuesta.getResultadoLlamadaServicio().getCodigoError(), SistemaEnum.CALIPSO);
					
					//ERROR MEDIOS DE PAGO CALIPSO
					List<ResultadoImputacion> listaResultadoImputacionMedioPago = errorServicioMedioPago(listaMediosPagoClp, respuesta.getResultadoLlamadaServicio().getDescripcionError(), respuesta.getResultadoLlamadaServicio().getCodigoError(), SistemaEnum.CALIPSO);
					
					//ERROR CARGO
					ResultadoImputacion resultadoImputacionCg = errorServicioCargo(creditoProximaFacturaClp, debitoProximaFacturaClp, respuesta.getResultadoLlamadaServicio().getDescripcionError(), respuesta.getResultadoLlamadaServicio().getCodigoError(), SistemaEnum.CALIPSO);
					
					//Actualizo los detalles de la transaccion
					actualizarDescobro(resultadoImputacionFactura, listaResultadoImputacionMedioPago, resultadoImputacionCg, null, transaccion, descobro);
					
					Traza.auditoria(getClass(), "[TRACEO DESDE DESCOBRO CALIPSO]");
					tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia(), false, 1);
					return false;
				}
			}
			
		} // fin puedeEnviarMensaje a calipso (Medios de pago)
		
		return false;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7 
	 * @param factura
	 * @param mensajeError
	 * @param codigoError
	 * @return
	 */
	private ResultadoImputacion errorServicioFactura(ShvCobFactura factura, String mensajeError, String codigoError, SistemaEnum sistemaRespuesta) {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoEnum.ERROR,
				codigoError,
				mensajeError);
		
		ResultadoImputacionDescobroFactura resultadoImputacion = new ResultadoImputacionDescobroFactura();
		
		if (!Validaciones.isObjectNull(factura)
				&& sistemaRespuesta.equals(factura.getSistemaOrigen())) {			
			
			resultadoImputacion.setIdOperacion(factura.getTransaccion().getOperacion().getIdOperacion());
			resultadoImputacion.setIdOperacionShiva(factura.getTransaccion().getOperacionTransaccionFormateado());
			resultadoImputacion.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			resultadoImputacion.setRespuestaInvocacion(resultadoInvocacion);
		}
		
		return resultadoImputacion;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7 
	 * @param listaMediosPago
	 * @param mensajeError
	 * @param codigoError
	 * @return
	 */
	private List<ResultadoImputacion> errorServicioMedioPago(List<ShvCobMedioPago> listaMediosPago, String mensajeError, String codigoError, SistemaEnum sistemaRespuesta) {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoEnum.ERROR,
				codigoError,
				mensajeError);
		
		List<ResultadoImputacion> listaResultadoImputacion = new ArrayList<ResultadoImputacion>();
		
		if(Validaciones.isCollectionNotEmpty(listaMediosPago)){
			
			for (ShvCobMedioPago medioPago : listaMediosPago) {
				
				if(SistemaEnum.CALIPSO.equals(sistemaRespuesta)){
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
						ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
						
						ResultadoImputacionDescobroMedioPagoCalipso resultadoImputacionMp = new ResultadoImputacionDescobroMedioPagoCalipso(); 
						resultadoImputacionMp.setIdOperacion(medioPago.getTransaccion().getOperacion().getIdOperacion());
						resultadoImputacionMp.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());
						resultadoImputacionMp.setTipoComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante());
						resultadoImputacionMp.setClaseComprobante(medioPagoNotaCreditoCalipso.getClaseComprobante());
						resultadoImputacionMp.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
						resultadoImputacionMp.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
						resultadoImputacionMp.setIdDocumentoCuentaCobranza(medioPagoNotaCreditoCalipso.getIdDocumentoCuentaCobranza());
						
						resultadoImputacionMp.setRespuestaInvocacion(resultadoInvocacion);
						listaResultadoImputacion.add(resultadoImputacionMp);
						
					} else if (medioPago instanceof ShvCobMedioPagoCTA) {
						ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;
						
						ResultadoImputacionDescobroMedioPagoCalipso resultadoImputacionMp = new ResultadoImputacionDescobroMedioPagoCalipso(); 
						resultadoImputacionMp.setIdOperacion(medioPago.getTransaccion().getOperacion().getIdOperacion());
						resultadoImputacionMp.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());
						resultadoImputacionMp.setTipoComprobante(medioPagoCTA.getTipoComprobante());
						resultadoImputacionMp.setClaseComprobante(medioPagoCTA.getClaseComprobante());
						resultadoImputacionMp.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
						resultadoImputacionMp.setNumeroComprobante(medioPagoCTA.getNroComprobante());
						resultadoImputacionMp.setIdDocumentoCuentaCobranza(medioPagoCTA.getIdDocumentoCuentaCobranza());
						
						resultadoImputacionMp.setRespuestaInvocacion(resultadoInvocacion);
						listaResultadoImputacion.add(resultadoImputacionMp);
					}
				} else if(SistemaEnum.MIC.equals(sistemaRespuesta)){
					ResultadoImputacionDescobroMedioPagoMic resultadoImputacionMp = new ResultadoImputacionDescobroMedioPagoMic();
					
					resultadoImputacionMp.setIdOperacion(medioPago.getTransaccion().getOperacion().getIdOperacion());
					resultadoImputacionMp.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
					resultadoImputacionMp.setTipoMedioPago(TipoMedioPagoEnum.getEnumByIdTipoMedioPago(medioPago.getTipoMedioPago().getIdTipoMedioPago()));
					resultadoImputacionMp.setIdBusqueraRespuestaCobrador(medioPago.getIdBusquedaRespuestaCobrador());
					
					resultadoImputacionMp.setRespuestaInvocacion(resultadoInvocacion);
					listaResultadoImputacion.add(resultadoImputacionMp);
				}
			}	
		}
		
		return listaResultadoImputacion;
	}
	
	private ResultadoImputacion errorServicioCargo(ShvCobTratamientoDiferencia creditoProximaFactura, 
			ShvCobMedioPagoUsuario debitoProximaFactura, String mensajeError, String codigoError, SistemaEnum sistemaRespuesta) {

		RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
				TipoResultadoEnum.ERROR,
				codigoError,
				mensajeError);
		
		if(!Validaciones.isObjectNull(creditoProximaFactura)){
			
			// ERROR CREDITO PROXIMA CALIPSO
			if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProximaFactura.getTipoTratamientoDiferencia())
					&& SistemaEnum.CALIPSO.equals(sistemaRespuesta)){
				
				ResultadoImputacionDescobroCargoGeneradoCalipso resultadoImputacionCg = new ResultadoImputacionDescobroCargoGeneradoCalipso();
				
				resultadoImputacionCg.setIdOperacion(creditoProximaFactura.getTransaccion().getOperacion().getIdOperacion());
				resultadoImputacionCg.setIdOperacionShiva(creditoProximaFactura.getTransaccion().getOperacionTransaccionFormateado());
				resultadoImputacionCg.setNumeroTransaccion(creditoProximaFactura.getTransaccion().getNumeroTransaccion());
				resultadoImputacionCg.setRespuestaInvocacion(resultadoInvocacion);
				
				return resultadoImputacionCg;
				
				// ERROR CREDITO PROXIMA MIC
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProximaFactura.getTipoTratamientoDiferencia())
					&& SistemaEnum.MIC.equals(sistemaRespuesta)){
				
				ResultadoImputacionDescobroCargoGeneradoMic resultadoImputacionCg = new ResultadoImputacionDescobroCargoGeneradoMic();
				
				resultadoImputacionCg.setIdOperacion(creditoProximaFactura.getTransaccion().getOperacion().getIdOperacion());
				resultadoImputacionCg.setIdOperacionShiva(creditoProximaFactura.getTransaccion().getOperacionTransaccionFormateado());
				resultadoImputacionCg.setNumeroTransaccion(creditoProximaFactura.getTransaccion().getNumeroTransaccion());
				resultadoImputacionCg.setRespuestaInvocacion(resultadoInvocacion);
				
				return resultadoImputacionCg;
			}
		} else if(!Validaciones.isObjectNull(debitoProximaFactura)){
			
			// ERROR DEBITO PROXIMA CALIPSO
			if (debitoProximaFactura instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso
					&& SistemaEnum.CALIPSO.equals(sistemaRespuesta)){
				
				ResultadoImputacionDescobroCargoGeneradoCalipso resultadoImputacionCg = new ResultadoImputacionDescobroCargoGeneradoCalipso();
				
				resultadoImputacionCg.setIdOperacion(debitoProximaFactura.getTransaccion().getOperacion().getIdOperacion());
				resultadoImputacionCg.setIdOperacionShiva(debitoProximaFactura.getTransaccion().getOperacionTransaccionFormateado());
				resultadoImputacionCg.setNumeroTransaccion(debitoProximaFactura.getTransaccion().getNumeroTransaccion());
				resultadoImputacionCg.setRespuestaInvocacion(resultadoInvocacion);
				
				return resultadoImputacionCg;
				
			// ERROR DEBITO PROXIMA MIC
			} else if (debitoProximaFactura instanceof ShvCobMedioPagoDebitoProximaFacturaMic
					&& SistemaEnum.MIC.equals(sistemaRespuesta)){
				
				ResultadoImputacionDescobroCargoGeneradoMic resultadoImputacionCg = new ResultadoImputacionDescobroCargoGeneradoMic();
				
				resultadoImputacionCg.setIdOperacion(debitoProximaFactura.getTransaccion().getOperacion().getIdOperacion());
				resultadoImputacionCg.setIdOperacionShiva(debitoProximaFactura.getTransaccion().getOperacionTransaccionFormateado());
				resultadoImputacionCg.setNumeroTransaccion(debitoProximaFactura.getTransaccion().getNumeroTransaccion());
				resultadoImputacionCg.setRespuestaInvocacion(resultadoInvocacion);
				
				return resultadoImputacionCg;
			}
			
		} else {
			return null;
		}
		return null;
	}
	
	
	/**
	 * A MIC se le solicitará la reversión de:<br>
		Apropiación de Facturas MIC<br>
		Apropiación de Notas de Crédito MIC<br> 
		Apropiación de Notas de Débito MIC<br>
		Apropiación de Remanentes MIC<br>
		Reversión de intereses por mora enviados a próxima factura<br>
		Reversión de bonificación de intereses por mora<br>
		Reversión de cargos por tratamiento de la diferencia:<br>
			Traslado de capital a próxima factura<br>
			Reintegro de créditos a próxima factura<br>
		<br>
		La reversión de los cargos enviados a facturar (cualquiera sean ellos) 
		se podrá hacer de varias formas dependiendo del estado del acuerdo de facturación:<br>
		- Si el acuerdo de facturación se encuentra activo o el cargo todavía no 
		fue facturado, el contracargo se enviará a facturar sobre ese.<br>
		- Si el acuerdo de facturación se encuentra dado de baja y el cargo original se facturó, 
		el contracargo se podrá enviar a facturar sobre ese o sobre otro acuerdo de 
		facturación de Génesis relacionado con algún cliente asociado al cobro original 
		o bien no facturar el contracargo en Génesis sino por Calipso si es que 
		existe un acuerdo facturando asociado a un cliente del cobro.
	 * 
	 * @param descobro
	 * @param transaccion
	 * @param factura
	 * @param listaMediosPagoMic
	 * @param creditoProximaFacturaMic
	 * @param debitoProximaFacturaMic
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion 
	 */
	private void descobroMic(ShvCobTransaccion transaccion,
			ShvCobFactura factura, List<ShvCobMedioPago> listaMediosPagoMic,
			ShvCobTratamientoDiferencia creditoProximaFacturaMic, 
			ShvCobMedioPagoDebitoProximaFacturaMic debitoProximaFacturaMic) throws NegocioExcepcion, ReintentoExcepcion {
		
		MicTransaccionDescobroDto mensajeMIC = new MicTransaccionDescobroDto();
		mensajeMIC.setIdOperacion(transaccion.getOperacion().getIdOperacionOriginal());
		mensajeMIC.setIdOperacionDescobroMensajeria(transaccion.getOperacion().getIdOperacion());
		mensajeMIC.setIdTransaccion(transaccion.getIdTransaccion());
		mensajeMIC.setNumeroTransaccion(transaccion.getNumeroTransaccion());
		mensajeMIC.setNumeroTransaccionFicticio(transaccion.getNumeroTransaccionFicticio());
		mensajeMIC.setModoEjecucion(SiNoEnum.NO);
		mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$09);
		mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO);
		mensajeMIC.setFacturarContracargoFactura(SiNoEnum.NO);
		mensajeMIC.setFacturarContracargoCargo(SiNoEnum.NO);
		
		if (puedeEnviarMensaje(mensajeMIC.getIdOperacionDescobroMensajeria(), mensajeMIC.getIdTransaccion(), mensajeMIC.getNumeroTransaccion(), 
				SistemaEnum.MIC, mensajeMIC.getTipoInvocacion(), false, false)) {
			
			//CREDITO PROXIMA MIC
			if (!Validaciones.isObjectNull(creditoProximaFacturaMic)) {
				
				if((Utilidad.decimalMayorACero(creditoProximaFacturaMic.getCobradorInteresesTrasladados())
                        && TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProximaFacturaMic.getTipoTratamientoDiferencia())
                        && SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo()))
                        || (!Utilidad.decimalMayorACero(creditoProximaFacturaMic.getCobradorInteresesTrasladados())
                                      && TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProximaFacturaMic.getTipoTratamientoDiferencia())
                                      && SistemaEnum.MIC.equals(creditoProximaFacturaMic.getSistemaAcuerdoContracargo()))){
					mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
					mensajeMIC.setAcuerdoFacturacionContracargoCargo(creditoProximaFacturaMic.getAcuerdoContracargo());
				}
			
			} else{
				//FACTURA MIC
				if (!Validaciones.isObjectNull(factura)) {
					
					//si la factura tiene sistema contracargo, quiere decir que tiene un cargo y si es de mic, entonces hago los seteos correspondientes
					if(Utilidad.decimalMayorACero(factura.getCobradorInteresesTrasladados()) 
							&& SistemaEnum.MIC.equals(factura.getSistemaOrigen())
							&& factura.getSistemaOrigen().equals(factura.getSistemaAcuerdoContracargo())){
						mensajeMIC.setFacturarContracargoFactura(SiNoEnum.SI);
						mensajeMIC.setAcuerdoFacturacionContracargoFactura(factura.getAcuerdoContracargo());
					}
				}
				//DEBITO PROXIMA MIC
				if (!Validaciones.isObjectNull(debitoProximaFacturaMic)) {
					
                    if((Utilidad.decimalMayorACero(debitoProximaFacturaMic.getCobradorInteresesTrasladados()) 
                            && debitoProximaFacturaMic.getSistemaOrigen().equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo()))
                            && SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo())
                            || (!Utilidad.decimalMayorACero(debitoProximaFacturaMic.getCobradorInteresesTrasladados()) 
                                         && debitoProximaFacturaMic.getSistemaOrigen().equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo())
                                         && SistemaEnum.MIC.equals(debitoProximaFacturaMic.getSistemaAcuerdoContracargo()))){
						mensajeMIC.setFacturarContracargoCargo(SiNoEnum.SI);
						mensajeMIC.setAcuerdoFacturacionContracargoCargo(debitoProximaFacturaMic.getAcuerdoContracargo());
					}
				} 
			}
			
			//MEDIOS DE PAGO MIC
			//Siempre va en no, ya que los medios de pago normales, no pueden tener cargos
			//Ademas para mic mando el id operacion y nro transaccion solamente, no es como calipso
			
			// envio a MIC la factura y medios de pagos
			monitor.enviarMensaje(mensajeMIC);
		}
	}
	
	/**
	 * SHV - Solicitar la generación de contracargos a MIC
	 * El usuario tendrá la opción de generar el contracargo de un 
	 * cargo de Calipso en MIC para que sea facturado por Génesis.  
	 * Esta opción se utilizará en los casos que el acuerdo original que 
	 * recibió el cargo no esté facturando y no haya ningún otro acuerdo de 
	 * facturación relacionado en Calipso pero si en Génesis.  
	 * 
	 * SHV - Solicitar la generación de contracargos a Calipso
	 * El usuario tendrá la opción de generar el contracargo de un cargo de MIC 
	 * en Calipso para que sea facturado por allí.  Esta opción se utilizará en los 
	 * casos que el acuerdo original que recibió el cargo esté dado de baja y no haya 
	 * ningún otro acuerdo de facturación relacionado en Génesis pero si en Calipso.
	 * 
	 * CLP - Generar contracargos
	 * Se deberán poder generar contracargos ya sea en el mismo acuerdo que 
	 * originalmente recibió el cargo u otro acuerdo de facturación ya sea MIC o Calipso
	 *
	 * IMPUTA LOS CONTRACARGOS CRUZADOS DE CALIPSO Y MIC
	 * @param transaccion
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion 
	 */
	private ShvCobTransaccion imputarContracargosCruzados(ShvCobTransaccion transaccion) throws NegocioExcepcion, ReintentoExcepcion {
		
		//Contracargo cruzado credito a proxima factura
		ShvCobTratamientoDiferencia creditoProxima = transaccion.getTratamientoDiferencia();
		
		if (!Validaciones.isObjectNull(creditoProxima)){
			
			if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO.equals(creditoProxima.getEstado())){	
				//Si hay para generar reintegro en calipso
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(creditoProxima.getTipoTratamientoDiferencia())){
					
					if (facturarContracargoTratamientoDiferencia(creditoProxima, SistemaEnum.CALIPSO)) {
					
						if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
								transaccion.getNumeroTransaccion(), SistemaEnum.MIC, TipoInvocacionEnum.$07, false, false)){
						
							imputarContracargoCruzadoCreditoCalipsoEnMic(creditoProxima, false);
						}
						
						if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
								transaccion.getNumeroTransaccion(), SistemaEnum.MIC, TipoInvocacionEnum.$07, true, false)){
							
							if(Utilidad.decimalMayorACero(creditoProxima.getCobradorInteresesTrasladados())){
								imputarContracargoCruzadoCreditoCalipsoEnMic(creditoProxima, true);
							}
						}
					}
					
				} else {
					
					//Si hay para generar reintegro en MIC
					if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(creditoProxima.getTipoTratamientoDiferencia())){
						
						if (facturarContracargoTratamientoDiferencia(creditoProxima, SistemaEnum.MIC)) {
						
							boolean contracargoOk = false;
							
							if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
									transaccion.getNumeroTransaccion(), SistemaEnum.CALIPSO, TipoInvocacionEnum.$07, false, false)){
							
								// generacion de cargos creditos en Calipso
								contracargoOk = imputarContracargoCruzadoCreditoMicEnCalipso(creditoProxima, false);
							}
							
							if (contracargoOk && puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
									transaccion.getNumeroTransaccion(), SistemaEnum.CALIPSO, TipoInvocacionEnum.$07, true, false)){
								
								if(Utilidad.decimalMayorACero(creditoProxima.getCobradorInteresesTrasladados())){
									imputarContracargoCruzadoCreditoMicEnCalipso(creditoProxima, true);
								}
							}
						}
					}
				}
			}
		} else {
			
			//Contracargo cruzado Factura
			ShvCobFactura factura = transaccion.getFactura();
			
			if (!Validaciones.isObjectNull(factura) && EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO.equals(factura.getEstado())) {
				
				//FACTURA MIC
				if (factura instanceof ShvCobFacturaMic) {
					
					ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;
					
					if (facturarContracargoFactura(factura, SistemaEnum.MIC)) {
					
						boolean esContracargoInteresOk = false;
						
						if(Utilidad.decimalMayorACero(facturaMic.getCobradorInteresesTrasladadosRegulados())){
							//generacion de cargos de la factura en Calipso
							if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), transaccion.getNumeroTransaccion(), 
										SistemaEnum.CALIPSO, TipoInvocacionEnum.$06, true, true)) {
								
								esContracargoInteresOk = imputarContracargoCruzadoFacturaMicEnCalipso(facturaMic, true);							
								
							}
						} else {
							//si no tiene intereses regulados, lo dejo en true para que procese los no regulados si los tuviese
							esContracargoInteresOk = true;
						}
						
						if(esContracargoInteresOk &&
							Utilidad.decimalMayorACero(facturaMic.getCobradorInteresesTrasladadosNoRegulados())){
							//generacion de contracargo del interes no regulado de la factura en Calipso
							if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), transaccion.getNumeroTransaccion(), 
										SistemaEnum.CALIPSO, TipoInvocacionEnum.$06, true, true)) {
								
								imputarContracargoCruzadoFacturaMicEnCalipso(facturaMic, false);							
								
							}
						}
					}
				}
				
				//FACTURA CALIPSO
				if (factura instanceof ShvCobFacturaCalipso) {
					ShvCobFacturaCalipso facturaClp = (ShvCobFacturaCalipso) factura;
					
					if (facturarContracargoFactura(facturaClp, SistemaEnum.CALIPSO)) {
						
						if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), transaccion.getNumeroTransaccion(), 
								SistemaEnum.MIC, TipoInvocacionEnum.$06, true, true)) {
							
							imputarContracargoCruzadoFacturaCalipsoEnMic(facturaClp);
														
						}
					}	
				}
			}
			
			//Contracargo cruzado debito a proxima factura
			List<ShvCobMedioPago> listaMediosPagoProxFacturaMic = listarMediosPagoProxFactura(transaccion,SistemaEnum.MIC);
			List<ShvCobMedioPago> listaMediosPagoProxFacturaCalipso = listarMediosPagoProxFactura(transaccion,SistemaEnum.CALIPSO);
					
			if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaMic)){
				ShvCobMedioPagoDebitoProximaFacturaMic mpProxFacturaMic = 
						(ShvCobMedioPagoDebitoProximaFacturaMic) listaMediosPagoProxFacturaMic.get(0);
				
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO.equals(mpProxFacturaMic.getEstado())){
					
					if (facturarContracargoCargoMic(mpProxFacturaMic)) {
					
						boolean contracargoOk = false;
						
						if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
								transaccion.getNumeroTransaccion(), SistemaEnum.CALIPSO, TipoInvocacionEnum.$06, false, false)){
						
							contracargoOk = imputarContracargoCruzadoDebitoMicEnCalipso(mpProxFacturaMic, false);
						}
						
						if (contracargoOk && puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
								transaccion.getNumeroTransaccion(), SistemaEnum.CALIPSO, TipoInvocacionEnum.$06, true, false)){
						
							if(Utilidad.decimalMayorACero(mpProxFacturaMic.getCobradorInteresesTrasladados())){
								imputarContracargoCruzadoDebitoMicEnCalipso(mpProxFacturaMic, true);
							}
						}
						
					}
				}
			} else if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaCalipso)){
				ShvCobMedioPagoDebitoProximaFacturaCalipso mpProxFacturaClp = 
						(ShvCobMedioPagoDebitoProximaFacturaCalipso) listaMediosPagoProxFacturaCalipso.get(0);
				
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO.equals(mpProxFacturaClp.getEstado())){
					if (facturarContracargoCargoClp(mpProxFacturaClp)) {
						
						if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
								transaccion.getNumeroTransaccion(), SistemaEnum.MIC, TipoInvocacionEnum.$06, false, false)){
						
							imputarContracargoCruzadoDebitoCalipsoEnMic(mpProxFacturaClp, false);
						}
					}
				}
			}  
		}
		
		return transaccion;
	}
	
	
	/**
	 * 
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean imputarContracargoCruzadoFacturaMicEnCalipso(ShvCobFacturaMic facturaMic, boolean esInteresRegulado) throws NegocioExcepcion {

		if (!Validaciones.isObjectNull(facturaMic)) {
			
			Long idOperacionCobro = facturaMic.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = facturaMic.getTransaccion().getOperacion().getIdOperacion();
			
			EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
			
			entrada.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
			entrada.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
			entrada.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(facturaMic.getTransaccion().getNumeroTransaccion().toString(), 5));
			entrada.setIdTransaccion(facturaMic.getTransaccion().getIdTransaccion().toString());
			entrada.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES);
			
			entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			entrada.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
			entrada.setModoOperacion(SiNoEnum.NO);

			DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
			detalleCargo.setAcuerdoFacturacion(facturaMic.getAcuerdoContracargo());
			
			if(esInteresRegulado){
				detalleCargo.setImporte(facturaMic.getCobradorInteresesTrasladadosRegulados());
			} else {
				detalleCargo.setImporte(facturaMic.getCobradorInteresesTrasladadosNoRegulados());
			}
			
			detalleCargo.setFechaDesde(new Date());
			
			detalleCargo.setOrigenCargo(facturaMic.getOrigenCargo());
			detalleCargo.setLeyendaFacturaInteres(facturaMic.getLeyendaFacturaInteres());
			entrada.setDetalleCargo(detalleCargo);
					
			SalidaCalipsoCargosWS respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.DESCOBRO);
				
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoEnum.getEnumByDescripcionCalipso(respuesta.getResultado().getResultado()),
								respuesta.getResultado().getCodigoError(),
								respuesta.getResultado().getDescripcionError());
								
			
			//Si se generó el cargo correctamente, mando a confirmar
			if (TipoResultadoEnum.OK.equals(resultadoInvocacion.getResultado())){
				facturaMic.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION);
				RespuestaInvocacion resultadoInvocacionConfirmar = confirmarContracargoCruzado(BigInteger.valueOf(idOperacionCobro),respuesta.getIdMovMer());
				
				actualizarInformacionMensajeEstado(resultadoInvocacionConfirmar, facturaMic, true, false, false);
			} else {
				actualizarInformacionMensajeEstado(resultadoInvocacion, facturaMic, true, false, false);
				
			}
			
			return true;
		}
		
		return false;
		
	}
	
	
	
	
	
	private RespuestaInvocacion confirmarContracargoCruzado(BigInteger idOperacionCobro, BigInteger idMovMer) throws NegocioExcepcion{

		EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS = new EntradaCalipsoConfirmacionWS();
		
		entradaCalipsoConfirmacionWS.setIdOperacion(idOperacionCobro);
		
		// CREAR UN NUEVO TIPO DE MENSAJE? CLP CONFIRMACION CARGOS CRUZADOS DESCOBROS
		entradaCalipsoConfirmacionWS.setTipoApropiacion(MensajeServicioEnum.CLP_CONFIRMACION);
		entradaCalipsoConfirmacionWS.setUsuarioCobrador("");
		List<DetalleCargoSalidaCobranzasWs> listaCargosAConfirmar = new ArrayList<DetalleCargoSalidaCobranzasWs>();
		
		DetalleCargoSalidaCobranzasWs detalleCargoAConfirmar = new DetalleCargoSalidaCobranzasWs();
		detalleCargoAConfirmar.setIdMovMer(idMovMer);
		
		listaCargosAConfirmar.add(detalleCargoAConfirmar);
		entradaCalipsoConfirmacionWS.setListaCargosAConfirmar(listaCargosAConfirmar);
		
		SalidaCalipsoConfirmacionWS respuestaConfirmacion = clienteCalipsoServicio.confirmarCobro(entradaCalipsoConfirmacionWS );
		
		// Resultados posibles: OK / NOK / ERR
		RespuestaInvocacion resultadoInvocacionConfirmar = new RespuestaInvocacion(
							TipoResultadoEnum.getEnumByDescripcionCalipso(respuestaConfirmacion.getResultadoInvocacion().getResultado()),
							respuestaConfirmacion.getResultadoInvocacion().getCodigoError(),
							respuestaConfirmacion.getResultadoInvocacion().getDescripcionError());
		
		return resultadoInvocacionConfirmar;
	}

	/**
	 * 
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion 
	 */
	private void imputarContracargoCruzadoFacturaCalipsoEnMic(ShvCobFacturaCalipso factura) throws NegocioExcepcion, ReintentoExcepcion {

		if(!Validaciones.isObjectNull(factura)){
			
			Long idOperacionCobro = factura.getTransaccion().getOperacion().getIdOperacionOriginal();
			Long idOperacionDescobro = factura.getTransaccion().getOperacion().getIdOperacion();
			
			MicDescobroGeneracionCargosInteresDto mensajeMIC = new MicDescobroGeneracionCargosInteresDto();
			mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
			mensajeMIC.setIdOperacion(idOperacionCobro);
			mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
			mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES);
			
			MicDetalleGeneracionCargosDto detalleGeneracionCargo = new MicDetalleGeneracionCargosDto();
			detalleGeneracionCargo.setAcuerdoFacturacion(factura.getAcuerdoContracargo());
			detalleGeneracionCargo.setCalcularFechaHasta(SiNoEnum.NO);
			detalleGeneracionCargo.setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);
			detalleGeneracionCargo.setImporteRegulado(factura.getCobradorInteresesTrasladados());
			detalleGeneracionCargo.setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
			detalleGeneracionCargo.setOrigenCargo(factura.getOrigenCargo());
			detalleGeneracionCargo.setLeyendaFacturaInteres(factura.getLeyendaFacturaInteres());
			mensajeMIC.setDetalleGeneracionCargos(detalleGeneracionCargo);
				
			// envio a MIC la generacion de cargo
			monitor.enviarMensaje(mensajeMIC);
			
		}
		
	}
	
	private boolean imputarContracargoCruzadoCreditoMicEnCalipso(ShvCobTratamientoDiferencia tratamientoDiferencia
			, boolean esContracargoInteres) throws NegocioExcepcion, ReintentoExcepcion{
		
		try {
		
			if(!Validaciones.isObjectNull(tratamientoDiferencia)){
				
				Long idOperacionCobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacionOriginal();
				Long idOperacionDescobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion();
				String numeroTransaccion = tratamientoDiferencia.getTransaccion().getNumeroTransaccion().toString();
				String idTransaccion = tratamientoDiferencia.getTransaccion().getIdTransaccion().toString();
						
				EntradaCalipsoCargosWS entradaCalipsoCargo = new EntradaCalipsoCargosWS();
				entradaCalipsoCargo.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
				entradaCalipsoCargo.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
				entradaCalipsoCargo.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(numeroTransaccion, 5));
				entradaCalipsoCargo.setIdTransaccion(idTransaccion);
				
				entradaCalipsoCargo.setModoOperacion(SiNoEnum.NO);
				entradaCalipsoCargo.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
				entradaCalipsoCargo.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				DetalleCargoEntradaCargosWs detalleCargo = new DetalleCargoEntradaCargosWs();
				detalleCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoContracargo());
				detalleCargo.setFechaDesde(new Date());
				
				if(!esContracargoInteres){
					detalleCargo.setImporte(tratamientoDiferencia.getImporte());
					detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
					detalleCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
					entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO);
				} else {
					detalleCargo.setImporte(tratamientoDiferencia.getCobradorInteresesTrasladados());
					detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenInteres());
					detalleCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
					entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES);
				}
				
				entradaCalipsoCargo.setDetalleCargo(detalleCargo);
				
				SalidaCalipsoCargosWS salidaCalipsoCargo = clienteCalipsoServicio.generarCargoCalipso(entradaCalipsoCargo, TipoProcesoEnum.DESCOBRO);
					
				if(!Validaciones.isObjectNull(salidaCalipsoCargo)
						&& !Validaciones.isObjectNull(salidaCalipsoCargo.getResultado())){
					
					if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())
							&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(salidaCalipsoCargo.getResultado().getCodigoError())){
						
						salidaCalipsoCargo.getResultado().setResultado(TipoResultadoEnum.OK.getDescripcionCalipso());
					}
					
					RespuestaInvocacion respuestaInvocacion = new RespuestaInvocacion();
					respuestaInvocacion.setResultado(TipoResultadoEnum.getEnumByDescripcionMic(salidaCalipsoCargo.getResultado().getResultado()));
					respuestaInvocacion.setDescripcionError(new StringBuffer(salidaCalipsoCargo.getResultado().getDescripcionError()));
					respuestaInvocacion.setCodigoError(salidaCalipsoCargo.getResultado().getCodigoError());
					
					
					//Si se generó el cargo correctamente, mando a confirmar
					if (TipoResultadoEnum.OK.equals(respuestaInvocacion.getResultado())){
						tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION);
						RespuestaInvocacion resultadoInvocacionConfirmar = confirmarContracargoCruzado(BigInteger.valueOf(idOperacionCobro),salidaCalipsoCargo.getIdMovMer());
						
						actualizarInformacionMensajeEstado(resultadoInvocacionConfirmar, tratamientoDiferencia, true, false, false);
					} else {
						actualizarInformacionMensajeEstado(respuestaInvocacion, tratamientoDiferencia, true, false, false);
						
					}
					
					return true;
				}
					
			}
			
		} catch (NegocioExcepcion | NumberFormatException e) {
			
			throw new NegocioExcepcion(e.getMessage());
		}
		
		return false;
	}
	
	/**
	 * Retorna la respuesta de generación de cargos o reintegros de Calipso
	 * @param mpProxFactura
	 * @param tratamientoDiferencia
	 * @param entradaCalipsoCargo
	 * @return
	 * @throws ReintentoExcepcion 
	 */
	private boolean imputarContracargoCruzadoDebitoMicEnCalipso(ShvCobMedioPagoDebitoProximaFacturaMic mpProxFacturaMic, boolean esContracargoInteres) 
					throws NegocioExcepcion, ReintentoExcepcion{
		
		try {
		
			if(!Validaciones.isObjectNull(mpProxFacturaMic)){
				
				Long idOperacionCobro = mpProxFacturaMic.getTransaccion().getOperacion().getIdOperacionOriginal();
				Long idOperacionDescobro = mpProxFacturaMic.getTransaccion().getOperacion().getIdOperacion();
				String numeroTransaccion = mpProxFacturaMic.getTransaccion().getNumeroTransaccion().toString();
				String idTransaccion = mpProxFacturaMic.getTransaccion().getIdTransaccion().toString();
				
				EntradaCalipsoCargosWS entradaCalipsoCargo = new EntradaCalipsoCargosWS();
				entradaCalipsoCargo.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacionCobro.toString(), 7));
				entradaCalipsoCargo.setIdOperacionDescobroMensajeria(idOperacionDescobro.toString());
				entradaCalipsoCargo.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(numeroTransaccion, 5));
				entradaCalipsoCargo.setIdTransaccion(idTransaccion);
				
				entradaCalipsoCargo.setModoOperacion(SiNoEnum.NO);
				entradaCalipsoCargo.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
				entradaCalipsoCargo.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				//Mapeo el objeto de mic a calipso para armar la llamada al contracargo
				DetalleCargoEntradaCargosWs detalleCargo = new DetalleCargoEntradaCargosWs();
				detalleCargo.setAcuerdoFacturacion(mpProxFacturaMic.getAcuerdoTrasladoCargo());
				detalleCargo.setFechaDesde(new Date());

				if(!esContracargoInteres){
					detalleCargo.setImporte(mpProxFacturaMic.getImporte()); 
					detalleCargo.setOrigenCargo(mpProxFacturaMic.getOrigenCargo());
					detalleCargo.setLeyendaFacturaCargo(mpProxFacturaMic.getLeyendaFacturaCargo());
					entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO);
				} else {
					detalleCargo.setImporte(mpProxFacturaMic.getImporte()); 
					detalleCargo.setOrigenCargo(mpProxFacturaMic.getOrigenCargo());
					detalleCargo.setLeyendaFacturaInteres(mpProxFacturaMic.getLeyendaFacturaInteres());
					entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES);
				}
				
				entradaCalipsoCargo.setDetalleCargo(detalleCargo);
				
				SalidaCalipsoCargosWS salidaCalipsoCargo =  clienteCalipsoServicio.generarCargoCalipso(entradaCalipsoCargo, TipoProcesoEnum.DESCOBRO);
					
				if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())
						&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(salidaCalipsoCargo.getResultado().getCodigoError())){
					
					salidaCalipsoCargo.getResultado().setResultado(TipoResultadoEnum.OK.getDescripcionCalipso());
				}
				
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoEnum.getEnumByDescripcionCalipso(salidaCalipsoCargo.getResultado().getResultado()),
						salidaCalipsoCargo.getResultado().getCodigoError(),
						salidaCalipsoCargo.getResultado().getDescripcionError());
				
				
				//Si se generó el cargo correctamente, mando a confirmar
				if (TipoResultadoEnum.OK.equals(resultadoInvocacion.getResultado())){
					mpProxFacturaMic.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION);
					RespuestaInvocacion resultadoInvocacionConfirmar = confirmarContracargoCruzado(BigInteger.valueOf(idOperacionCobro),salidaCalipsoCargo.getIdMovMer());
					
					actualizarInformacionMensajeEstado(resultadoInvocacionConfirmar, mpProxFacturaMic, esContracargoInteres,false, false);
				} else {
					
					actualizarInformacionMensajeEstado(resultadoInvocacion, mpProxFacturaMic, esContracargoInteres, false, false);
					
				}
				
				return true;
			}
			
		} catch (NegocioExcepcion | NumberFormatException e) {
			
			throw new NegocioExcepcion(e.getMessage());
		}
		
		return false;
	}
	

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @param esContracargoInteres
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	private void imputarContracargoCruzadoCreditoCalipsoEnMic(ShvCobTratamientoDiferencia tratamientoDiferencia, boolean esContracargoInteres) throws NegocioExcepcion, ReintentoExcepcion {

		try {
			if (!Validaciones.isObjectNull(tratamientoDiferencia)){				
				
				Long idOperacionCobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacionOriginal();
				Long idOperacionDescobro = tratamientoDiferencia.getTransaccion().getOperacion().getIdOperacion();
				
				MicDescobroGeneracionCargosCreditoDto mensajeMIC = new MicDescobroGeneracionCargosCreditoDto();
				
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$07);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				mensajeMIC.setIdOperacion(idOperacionCobro);
				mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
				mensajeMIC.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(tratamientoDiferencia.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(tratamientoDiferencia.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				mensajeMIC.getDetalleGeneracionCargos().setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoContracargo());
				mensajeMIC.getDetalleGeneracionCargos().setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
				mensajeMIC.getDetalleGeneracionCargos().setCalcularFechaHasta(SiNoEnum.NO);
				mensajeMIC.getDetalleGeneracionCargos().setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);

				if(!esContracargoInteres){
					mensajeMIC.getDetalleGeneracionCargos().setImporteNoRegulado(tratamientoDiferencia.getImporte());
					mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
					mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
					mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO);
				} else {
					mensajeMIC.getDetalleGeneracionCargos().setImporteNoRegulado(tratamientoDiferencia.getCobradorInteresesTrasladados());
					mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(tratamientoDiferencia.getOrigenInteres());
					mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
					mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES);
				}
					
				// envio a MIC la generacion de cargo
				monitor.enviarMensaje(mensajeMIC);
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		
	}
	
	
	private void imputarContracargoCruzadoDebitoCalipsoEnMic(ShvCobMedioPagoDebitoProximaFacturaCalipso mpProxFacturaClp, boolean esContracargoInteres) throws NegocioExcepcion, ReintentoExcepcion {

		try {
			if (!Validaciones.isObjectNull(mpProxFacturaClp)){
				
				Long idOperacionCobro = mpProxFacturaClp.getTransaccion().getOperacion().getIdOperacionOriginal();
				Long idOperacionDescobro = mpProxFacturaClp.getTransaccion().getOperacion().getIdOperacion();
				
				// Armo mensaje generacion de cargo
				MicDescobroGeneracionCargosDebitoDto mensajeMIC = new MicDescobroGeneracionCargosDebitoDto();
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				mensajeMIC.setIdOperacion(idOperacionCobro);
				mensajeMIC.setIdOperacionDescobroMensajeria(idOperacionDescobro);
				mensajeMIC.setIdTransaccion(mpProxFacturaClp.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(mpProxFacturaClp.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(mpProxFacturaClp.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				mensajeMIC.getDetalleGeneracionCargos().setAcuerdoFacturacion(mpProxFacturaClp.getAcuerdoContracargo());
				
				mensajeMIC.getDetalleGeneracionCargos().setFechaDesde(Utilidad.formatDateAAAAMMDD(new Date()));
				mensajeMIC.getDetalleGeneracionCargos().setCalcularFechaHasta(SiNoEnum.NO);
				mensajeMIC.getDetalleGeneracionCargos().setQueHacerConLosIntereses(TratamientoInteresesEnum.SC);
				
				if(!esContracargoInteres){
					mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO);
					mensajeMIC.getDetalleGeneracionCargos().setImporteRegulado(mpProxFacturaClp.getImporte());
					mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(mpProxFacturaClp.getOrigenCargo());
					mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaCargo(mpProxFacturaClp.getLeyendaFacturaCargo());
				} else {
					mensajeMIC.setServicio(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES);
					mensajeMIC.getDetalleGeneracionCargos().setImporteRegulado(mpProxFacturaClp.getCobradorInteresesTrasladados());
					mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(mpProxFacturaClp.getOrigenInteres());
					mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaInteres(mpProxFacturaClp.getLeyendaFacturaInteres());
				}
				
				// envio a MIC la generacion de cargo
				monitor.enviarMensaje(mensajeMIC);
			
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		
	}
	
	
	/***************************************************************************
	 * RECEPCION RESPUESTA DESCOBRO MIC 
	 *************************************************************************/
	
	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio la reversion de operaciones.
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void micRecepcionDescobro(DTO respuesta) throws NegocioExcepcion, PersistenciaExcepcion {
		
		MicTransaccionDescobroRespuestaDto mensajeRespuesta = (MicTransaccionDescobroRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "mensajeRespuesta: " + mensajeRespuesta.toString());
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		MicResultadoDto resultadoRespuesta = mensajeRespuesta.getResultadoLLamadaServicio();
		
		if(!Validaciones.isObjectNull(resultadoRespuesta)){
			
			Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
			Long nroTransaccion = Long.valueOf(idOperacionTransaccion.split("\\.")[1]);
			String idTransaccion = mensajeRespuesta.getIdTransaccion().toString();
			ShvCobDescobro descobro = null;
			String usuarioBatch = null;
			
			try {
				Long idOperacionDescobro = descobroDao.buscarIdDescobroPorIdOperacionCobro(idOperacion);
				descobro = descobroDao.buscarDescobroPorIdOperacion(idOperacionDescobro);
				usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
				String estadoRespuesta = resultadoRespuesta.getResultadoInvocacion(); //OK / ER
				
				idOperacionTransaccion = Utilidad.rellenarCerosIzquierda(idOperacionDescobro.toString(), 7) + "." + Utilidad.rellenarCerosIzquierda(nroTransaccion.toString(), 5);
				
				if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
						&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(resultadoRespuesta.getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionMic();
					mensajeRespuesta.getResultadoLLamadaServicio().setCodigoError("");
					mensajeRespuesta.getResultadoLLamadaServicio().setDescripcionError("");
					mensajeRespuesta.getResultadoLLamadaServicio().setResultadoInvocacion(estadoRespuesta);
					Traza.auditoria(getClass(),  "Mensaje: " + mensajeRespuesta.getTipoInvocacion() + " ("+idOperacionTransaccion+", rta: " + estadoRespuesta+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
				}
				
				Traza.auditoria(getClass(),  "Mensaje: " + mensajeRespuesta.getTipoInvocacion() + " ("+idOperacionTransaccion+", idTransaccion: " + idTransaccion + ", rta: " + estadoRespuesta+") en ejecucion");
			
				
				if (descobro != null) {
					ShvCobTransaccion transaccion = descobro.getTransaccionPorIdTransaccion(Integer.valueOf(idTransaccion));
					tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio(), true, 1);
					
					if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta)
							|| TipoResultadoEnum.WRN.getDescripcionMic().equals(estadoRespuesta)){
						
						guardarDatosRecepcionMic(transaccion, mensajeRespuesta, descobro);
					
					} else if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
							|| TipoResultadoEnum.NOK.getDescripcionMic().equals(estadoRespuesta)){
						
						//ERROR FACTURA
						ResultadoImputacion resultadoImputacionFactura = errorServicioFactura((transaccion.getFactura() != null? transaccion.getFactura():null), resultadoRespuesta.getDescripcionError(), resultadoRespuesta.getCodigoError(), SistemaEnum.MIC);
						ShvCobMedioPagoUsuario debitoProximaFactura = null;
						
						List<ShvCobMedioPago> listaMp = new ArrayList<ShvCobMedioPago>();
						for (ShvCobMedioPago shvCobMedioPago : transaccion.getMediosPago()) {
							
							//Si el mp es de tipo debito proxima factura, me lo guardo para despues poder setear el error de cargos
							if (shvCobMedioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso
									|| shvCobMedioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic){
								debitoProximaFactura = (ShvCobMedioPagoUsuario) shvCobMedioPago;
							}
							listaMp.add(shvCobMedioPago);
						}
						
						//ERROR MEDIOS DE PAGO
						List<ResultadoImputacion> listaResultadoImputacionMedioPago = errorServicioMedioPago(listaMp != null? listaMp:null, resultadoRespuesta.getDescripcionError(), resultadoRespuesta.getCodigoError(), SistemaEnum.MIC);
						
						//ERROR CARGO
						ResultadoImputacion resultadoImputacionCg = errorServicioCargo((transaccion.getTratamientoDiferencia() != null?transaccion.getTratamientoDiferencia():null), debitoProximaFactura, resultadoRespuesta.getDescripcionError(), resultadoRespuesta.getCodigoError(), SistemaEnum.MIC);
						
						//Actualizo los detalles de la transaccion
						actualizarDescobro(resultadoImputacionFactura, listaResultadoImputacionMedioPago, resultadoImputacionCg, null, transaccion, descobro);
						
					}
					
					//Guardo el descobro
					
					//Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
					tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOrdenadasInversaSinDiferenciaCambio(), false, 1);
					descobro = pasarWorkflowPendienteProcesarMICAProceso(idOperacionDescobro, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatch,descobro);
					descobro = descobroDao.modificar(descobro);

				} else {
					throw new NegocioExcepcion("No se ha encontrado el descobro de la operacion: " + idOperacionTransaccion);
				}
				
			} catch (ReintentoExcepcion e) {
				actualizarEstadoDescobroPorReintentoMaximo(descobro, usuarioBatch, e);
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			
			Traza.auditoria(getClass(),  "Mensaje: " + mensajeRespuesta.getTipoInvocacion() + " ("+idOperacionTransaccion+") ejecutado");
		}	
	}
	
	
	private ShvCobDescobro pasarWorkflowPendienteProcesarMICAProceso(Long idOperacionDescobro, TipoInvocacionEnum tipoInvocacion,String idOperacionTransaccion, String usuarioBatch,
			ShvCobDescobro descobro) throws NegocioExcepcion {

		descobro.setWorkflow(workflowDescobros.registrarDescobroPendienteProcesarMicAEnProceso(descobro.getWorkflow(), " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuarioBatch));				
//			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
		Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + descobro.getWorkflow().getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
		
		return descobro;
	}

	private void guardarDatosRecepcionMic(ShvCobTransaccion transaccion, MicTransaccionDescobroRespuestaDto mensajeRespuesta, ShvCobDescobro descobro) throws NegocioExcepcion, ReintentoExcepcion {
		
		//Cargo el resultado de la factura de mic
		ResultadoImputacion resultadoImputacionFactura = this.cargarResultadoFacturaMic(transaccion, mensajeRespuesta);
		
		//Cargo los resultados de los medios de pago de mic
		List<ResultadoImputacion> listaResultadoImputacionMediosPago = this.cargarResultadoMedioPagoMic(transaccion, mensajeRespuesta);
		
		//Cargo los resultados de cargos
		ResultadoImputacion resultadoImputacionCargoGenerado = this.cargarResultadoCargoGeneradoMic(transaccion, mensajeRespuesta);
		
		//Actualizo los detalles de la transaccion
		actualizarDescobro(resultadoImputacionFactura, listaResultadoImputacionMediosPago, resultadoImputacionCargoGenerado, null, transaccion, descobro);
		
		//Imputo los contracargos cruzados si corresponde
//		imputarContracargosCruzados(transaccion);
	}

	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio apropiacion de deuda.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void micRecepcionContraCargo(DTO respuesta) throws NegocioExcepcion {
		
		String nombreMetodo = new Object(){}.getClass().getEnclosingMethod().getName();
		
		MicTransaccionGeneracionCargosRespuestaDto mensajeRespuesta = (MicTransaccionGeneracionCargosRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo " + nombreMetodo + " MicTransaccionGeneracionCargosRespuestaDto: "+  mensajeRespuesta.toString());
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuesta = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		
		if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
				&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
			estadoRespuesta = TipoResultadoEnum.OK.getDescripcionMic();
			Traza.auditoria(getClass(),  "Metodo " + nombreMetodo + " ("+idOperacionTransaccion+", rta: " + estadoRespuesta+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
		}
		
		Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
		String idTransaccion = mensajeRespuesta.getIdTransaccion().toString();
		Traza.auditoria(getClass(),  "Metodo " + nombreMetodo + " ("+idOperacionTransaccion+", idTransaccion: " + idTransaccion + ", rta: " + estadoRespuesta+") en ejecucion");
		
		try {
			//traduzco de cobro a descobro
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
			
			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
			
			
			if(!Validaciones.isObjectNull(descobro)){
				tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio(), true, 1);
				
				//los cargos no devuelven warning con lo cual pregunto por los otros 3 resultados
				if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta)
						|| TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
						|| TipoResultadoEnum.NOK.getDescripcionMic().equals(estadoRespuesta)){
					
					guardarDatosRespuestaCargoMic(descobro, idTransaccion, mensajeRespuesta);
					
					descobro = descobroDao.modificar(descobro);
				}else{
					
					Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
				}
				
				tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOrdenadasInversaSinDiferenciaCambio(), false, 1);
			}			
			
			
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			this.pasarWorkflowPendienteProcesarMICAProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatch);
			
		} catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		Traza.auditoria(getClass(),  "Metodo " + nombreMetodo + " ("+idOperacionTransaccion+") ejecutado");
	}
	
//	private void confirmarContracargoMic(ShvCobCobro cobro) throws NegocioExcepcion {
//
////		try{
//			
//			Long idOperacion = cobro.getOperacion().getIdOperacion();
//			
//			MicConfirmacionDto mensajeMIC = new MicConfirmacionDto();
//			mensajeMIC.setIdOperacion(idOperacion);
//			mensajeMIC.setIdTransaccion(null);
//			mensajeMIC.setNumeroTransaccion(null);
//			
//			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
//			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
//			
////			if (puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.MIC, TipoInvocacionEnum.$05)) {
//				monitor.enviarMensaje(mensajeMIC);
////			}
////		} catch (PersistenciaExcepcion e) {
////			throw new NegocioExcepcion(e.getMessage(), e);
////		}
//		
//	}
		/**
	 * Permite modificar el estado del workflow (Pendiente MIC a Proceso) 
	 * @param idOperacion
	 * @param usuarioBatch
	 * @throws NegocioExcepcion
	 */
	@Override
	public void pasarWorkflowPendienteMICAPendienteProcesarMIC(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,  String usuarioBatch) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = descobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			workflow = workflowDescobros.registrarDescobroDePendienteMicAPendienteProcesarMic(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuarioBatch);
//			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado Pendiente Procesar MIC - " + tipoInvocacion.getDescripcion());
			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			workflow = null;
		}
	}
	
	/**
	 * Permite modificar el estado del workflow (Pendiente MIC a Proceso) 
	 * @param idOperacion
	 * @param usuarioBatch
	 * @throws NegocioExcepcion
	 */
	private void pasarWorkflowPendienteProcesarMICAProceso(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,  String usuarioBatch) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = descobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			workflow = workflowDescobros.registrarDescobroPendienteProcesarMicAEnProceso(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuarioBatch);				
//			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			workflow = null;
		}
	}
	
	/**
	 * Permite modificar el estado del workflow (Proceso a Pendiente de MIC) 
	 * @param idOperacion
	 * @param usuarioBatch
	 * @throws NegocioExcepcion
	 */
	@Override
	public ShvWfWorkflow pasarWorkflowProcesoAPendienteMIC(Long idOperacion, String usuarioBatch, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion, ShvWfWorkflow wf) throws NegocioExcepcion {
		wf = workflowDescobros.registrarDescobroDeEnProcesoAPendienteMic(wf, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuarioBatch);				
//		Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado Pendiente de MIC - " + tipoInvocacion.getDescripcion());
		Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El descobro se pasa al estado [ " + wf.getEstado().descripcion() + " ] - "  + tipoInvocacion.getDescripcion());
		return wf;
	}
	/**
	 * Guarda en la base los datos que vienen en el mensaje de respuesta de MIC
	 * @param mensajeRespuesta
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void guardarDatosRespuestaCargoMic(ShvCobDescobro descobro, String idTransaccion, MicTransaccionGeneracionCargosRespuestaDto mensajeRespuesta) throws NegocioExcepcion {
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		
		if (!Validaciones.isObjectNull(descobro)) {
			ShvCobTransaccion transaccion = descobro.getTransaccionPorIdTransaccion(Integer.valueOf(idTransaccion));
			if (!Validaciones.isObjectNull(transaccion)) {
				
				RespuestaInvocacion respuestaInvocacion = new RespuestaInvocacion();
				respuestaInvocacion.setResultado(TipoResultadoEnum.getEnumByDescripcionMic(mensajeRespuesta.getResultadoInvocacion()));
				respuestaInvocacion.setDescripcionError(new StringBuffer(mensajeRespuesta.getDescripcionError()));
				respuestaInvocacion.setCodigoError(mensajeRespuesta.getCodigoError());
				
				if (TipoInvocacionEnum.$06.equals(mensajeRespuesta.getTipoInvocacion())){
					List<ShvCobMedioPago> listaMediosPagoProxFacturaCalipso = listarMediosPagoProxFactura(transaccion,SistemaEnum.MIC);
					
					if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaCalipso)){
						
						ShvCobMedioPagoDebitoProximaFacturaCalipso mpProxFacturaCalipso = null;
						for (ShvCobMedioPago mp : listaMediosPagoProxFacturaCalipso){
							mpProxFacturaCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipso)mp;
						}
						
						
						try{
							
							if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO.equals(mpProxFacturaCalipso.getEstado())){
								
								if (puedeEnviarMensaje(transaccion.getOperacion().getIdOperacion(), transaccion.getIdTransaccion(), 
										transaccion.getNumeroTransaccion(), SistemaEnum.MIC, TipoInvocacionEnum.$06, true, false)){
									
									if(Utilidad.decimalMayorACero(mpProxFacturaCalipso.getCobradorInteresesTrasladados())){
										mpProxFacturaCalipso.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
										imputarContracargoCruzadoDebitoCalipsoEnMic(mpProxFacturaCalipso, true);
									}
								}
							}
						} catch(ReintentoExcepcion e){
							Traza.error(this.getClass(),e.getMessage());
						}
						
						actualizarInformacionMensajeEstado(respuestaInvocacion, mpProxFacturaCalipso, true, false, false);
					}
					
					if (!Validaciones.isObjectNull(transaccion.getFactura())){
						
						ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();
						
						actualizarInformacionMensajeEstado(respuestaInvocacion, facturaCalipso, true, false, false);
						
					}
				} else if (TipoInvocacionEnum.$07.equals(mensajeRespuesta.getTipoInvocacion())){
					ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();
					
					actualizarInformacionMensajeEstado(respuestaInvocacion, tratamiento, true, false, false);
				}
			} else {
				Traza.advertencia(getClass(), "("+idOperacionTransaccion+") Transaccion no encontrada");
			}
		} else {
			Traza.advertencia(getClass(), "("+idOperacionTransaccion+") Descobro no encontrado");
		}
	}


	/**
	 * SHV - Ante errores de conectividad con MIC se deberá contar 
	 * con un método de recicle de mensajería
	 * Llegado el caso que ocurra un error de conectividad contra MIC, 
	 * Shiva deberá contar con un proceso de recicle de mensajería: 1 mensaje 
	 * con una diferencia de 15 minutos entre cada uno de ellos por 10 veces.
	 * 
	 * SHV - Ante errores de conectividad con Calipso se deberá contar 
	 * con un método de recicle de mensajería
	 * Llegado el caso que ocurra un error de conectividad contra Calipso, 
	 * Shiva deberá contar con un proceso de recicle de mensajería: 
	 * ráfagas 3 mensajes con una diferencia de 1 minuto, 
	 * con una diferencia de 15 minutos entre cada uno de ellos por 10 veces.
	 * 
	 * Busco si existe el mensaje pendiente o enviado para una transaccion
	 * @param mensajeMIC
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean puedeEnviarMensaje(Long idOperacion, Integer idTransaccion, Integer numeroTransaccion, 
			SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion, boolean esContracargoInteres, boolean esFactura) throws NegocioExcepcion, ReintentoExcepcion {
		
		MensajeServicioEnum servicio = getMensajeServicio(cobrador, tipoInvocacion, esContracargoInteres, esFactura);
		
		String operacion   = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		String transaccion = Utilidad.rellenarCerosIzquierda(numeroTransaccion!=null?numeroTransaccion.toString():"", 5);

		String idOperacionTransaccion = operacion+"."+transaccion;
		Integer cantMsgMaxReintentos = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.MSG_CANTIDAD_REINTENTOS).toString());
		
		CobMensajeriaTransaccionDto mensajeDto = 
				(CobMensajeriaTransaccionDto)mensajeriaTransaccionServicio.buscarMensaje(idOperacion, idTransaccion, servicio);
		
		if (!Validaciones.isObjectNull(mensajeDto)) {
			//Si se completan los reintentos, se cancelan los mensajes
			if (cantMsgMaxReintentos.compareTo(mensajeDto.getCantReintentos()) <= 0) {
				
				if (MensajeEstadoEnum.PENDIENTE.equals(mensajeDto.getEstado()) 
						|| MensajeEstadoEnum.ENVIADO.equals(mensajeDto.getEstado())) {
					
					mensajeriaTransaccionServicio.cancelarMensaje(mensajeDto);
					String mensaje = servicio.getSigla() + "-" + idOperacionTransaccion + "-Se ha finalizado el proceso del reintento pues se ha producido inconvenientes en el servicio de mensajeria";
					Traza.advertencia(getClass(), mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, mensaje, cobrador, servicio);
				}
				
				if (MensajeEstadoEnum.REC_ERROR.equals(mensajeDto.getEstado())) {
					mensajeriaTransaccionServicio.cancelarMensaje(mensajeDto);
					String mensaje = servicio.getSigla() + "-" + idOperacionTransaccion + "-Se ha finalizado el proceso del reintento pues se ha encontrado errores en el mensaje recibido";
					Traza.advertencia(getClass(), mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, mensaje, cobrador,  servicio);
				}
				
				if (MensajeEstadoEnum.COMPLETADO.equals(mensajeDto.getEstado())) {
					mensajeriaTransaccionServicio.cancelarMensaje(mensajeDto);
					String mensaje = servicio.getSigla() + "-" + idOperacionTransaccion + "-Se ha finalizado el proceso del reintento pues se ha producido error de sistema";
					Traza.advertencia(getClass(), mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, mensaje, cobrador, servicio);
				}
				
				if (MensajeEstadoEnum.CANCELADO.equals(mensajeDto.getEstado())) {
					String mensaje = servicio.getSigla() + "-" + idOperacionTransaccion + "-Se ha finalizado el proceso del reintento pues se ha producido inconvenientes en el servicio de mensajeria";
					Traza.advertencia(getClass(), mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, mensaje, cobrador, servicio);
				}
				
			} // fin Mayor Cant Reintentos
			else {
				if (SistemaEnum.CALIPSO.equals(cobrador)) {
					//Calipso - Sincronico y puedo reenviar el mensaje pero reutilizando el registro existente
					
					return reenviarMensajeAlCobrador(cobrador,mensajeDto,idOperacionTransaccion, servicio);
					
				} else {
					//MIC - Asincronico
					//Decimos que no hagamos nada ya que se encuentra todavia en ejecucion de la logica de reintentos (NO ENVIAR MENSAJE)
					// ya que tenemos la logica (2 Tarea cuya su funcion es reenviar mensajes hasta obtener alguna respuesta por parte de MIC)
					// o ya fue respondida
					
					String fechaUltimoEnvio = Utilidad.formatDateAndTimeFull(mensajeDto.getFechaEnvio());
					Traza.advertencia(getClass(), servicio.getSigla() + "-" + idOperacionTransaccion + "- No se va a reenviar al MIC ya que se encuentra en el proceso de reintentos " 
							+ " o se encuentra en espera de alguna respuesta OK o ER.\n Detalles: " 
							+ " Tipo Mensaje: " + mensajeDto.getServicio() + " - Estado Mensaje: " + mensajeDto.getEstado()
							+ " - Fecha Ultimo Envio: "+ fechaUltimoEnvio + "- Cantidad Reintentos: "+ mensajeDto.getCantReintentos() 
							+ "  - (Nota: Este proceso de reintento siempre se realiza en la 2da tarea de este proceso batch)");
					
					return false;
				}
			}
		} 
		
		//Decimos que no hay ningun mensaje enviado y vamos a enviar el primer mensaje (NUEVO MENSAJE)
		//Loggear
		return true;
	}
	
	/**
	 *  Retorna true en caso de que se pueda reenviar el mensaje al cobrador
	 * @param cobrador
	 * @param mensajeDto
	 * @param idOperacionTransaccion
	 * @param servicio
	 * @return
	 * @throws NumberFormatException
	 * @throws NegocioExcepcion
	 */
	private boolean reenviarMensajeAlCobrador(SistemaEnum cobrador,CobMensajeriaTransaccionDto mensajeDto,
			String idOperacionTransaccion, MensajeServicioEnum servicio) throws NumberFormatException, NegocioExcepcion{
		
		Integer tiempoParaReenvio = Integer.valueOf(parametroServicio.getValorNumerico(Constantes.MSG_TIEMPO_DE_REINTENTO_MINUTOS).toString());
		Date fechaPermitida = Utilidad.restMinutes(new Date(), tiempoParaReenvio);
		
		if (mensajeDto.getFechaEnvio()!=null) {
			if (mensajeDto.getFechaEnvio().after(fechaPermitida)) {
				
				String fechaUltimoEnvio = Utilidad.formatDateAndTimeFull(mensajeDto.getFechaEnvio());
				Traza.advertencia(getClass(), servicio.getSigla() + "-" + idOperacionTransaccion + "- No se va a reenviar a " + cobrador
						+ " ya que se encuentra en espera para el reenvio (Minutos de espera: "+tiempoParaReenvio+" "
								+ "- Fecha Ultimo Envio: "+ fechaUltimoEnvio + "- Cantidad Reintentos: "+ mensajeDto.getCantReintentos() +")");
				
				return false;		
			}
		}
		
		//Puedo reenviar al Cobrador
		return true;
	}
	
	
	/**
	 * Me devuelve el tipo de servicio de acuerdo a los criterios
	 * @param cobrador
	 * @param tipoInvocacion
	 * @return
	 * @throws NegocioExcepcion
	 * 
	 * $09(9, "Descobro"),
	 */
	private MensajeServicioEnum getMensajeServicio(SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion
			, boolean esContracargoInteres, boolean esFactura) throws NegocioExcepcion {
		MensajeServicioEnum servicio = null;
		
		switch (cobrador) {
			case MIC:
				switch (tipoInvocacion) {
					case $06:
						if(!esContracargoInteres){
							servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO;
						} else {
							if(!esFactura){
								servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES;
							} else {
								servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES;
							}
						}						
						break;
					case $07:
						if(!esContracargoInteres){
							servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO;
						} else {
							servicio = MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES;
						}
						break;
					case $08:
						servicio = MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES;
						break;
					case $09:
						servicio = MensajeServicioEnum.MIC_DESCOBRO;
						break;
					default:
						throw new NegocioExcepcion("Error - Tipo de invocacion no identificado");
				}
				break;
			case CALIPSO:
				switch (tipoInvocacion) {
					case $06:
						if(!esContracargoInteres){
							servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO;
						} else {
							if(!esFactura){
								servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES;
							} else {
								servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES;
							}
						}
						break;
					case $07:
						if(!esContracargoInteres){
							servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO;
						} else {
							servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES;
						}
						break;
					case $08:
						servicio = MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES;
						break;
					case $09:
						servicio = MensajeServicioEnum.CLP_DESCOBRO;
						break;
					default:
						throw new NegocioExcepcion("Error - Tipo de invocacion no identificado");
				}
				break;
			default:
				throw new NegocioExcepcion("Error - Tipo de Cobrador no identificado");
		}
		
		return servicio;
	}
	
	
	/**
	 * 
	 * @param cobro
	 * @param usuarioBatch
	 * @param e
	 * @param errorEnApropiacion
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 */
	private ShvCobDescobro actualizarEstadoDescobroPorReintentoMaximo(
			ShvCobDescobro descobro, String usuarioBatch, ReintentoExcepcion e) throws NegocioExcepcion, PersistenciaExcepcion {
		try{
			String errorReintentos = "Se ha cancelado el descobro despues de haber ejecutado varios reintentos";

			descobro = actualizarFacturaMedioPagoPorReintentoMaximo(descobro, e, usuarioBatch);
						
			verificarEstadoDescobro(descobro, errorReintentos, usuarioBatch);
			
			ControlVariablesSingleton.setReintentoExcepcion(e);
			
			tracearDatosImputacionDescobro(descobro.getIdDescobro(), descobro.getWorkflow(), descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio(), false, 0);
			Traza.auditoria(getClass(), "-------------------------------------------------------------------------------------");
			return descobro;
		} catch (ReintentoExcepcion exc) {
			throw new NegocioExcepcion(exc.getMessage(),exc);
		}
	}
	
	
	private ShvCobDescobro actualizarFacturaMedioPagoPorReintentoMaximo(
			ShvCobDescobro descobro, ReintentoExcepcion e, String usuarioBatch) throws NegocioExcepcion, ReintentoExcepcion {
		
		ShvCobTransaccion transaccionActualizar = descobro.getTransaccionPorIdTransaccion(e.getIdTransaccion());
		
		List<MensajeServicioEnum> listaMensajesErrorDescobro = MensajeServicioEnum.getEnumByTipoError(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
		
		if(!Validaciones.isObjectNull(transaccionActualizar)){
			
			if(listaMensajesErrorDescobro.contains(e.getServicio())){
				
				if(SistemaEnum.CALIPSO.equals(e.getCobrador())){
					
					for(ShvCobMedioPago medioPago : transaccionActualizar.getMediosPago()){
						if(!Validaciones.isObjectNull(medioPago) 
								&& medioPago instanceof ShvCobMedioPagoCalipso
								&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())){
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
						}
					}
					
					if(!Validaciones.isObjectNull(transaccionActualizar.getFactura())
						    && transaccionActualizar.getFactura() instanceof ShvCobFacturaCalipso
							&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccionActualizar.getFactura().getEstado())){
						transaccionActualizar.getFactura().setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
					}
					
					if(!Validaciones.isObjectNull(transaccionActualizar.getTratamientoDiferencia())
							 && TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccionActualizar.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							 && EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccionActualizar.getTratamientoDiferencia().getEstado())){
						transaccionActualizar.getFactura().setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
					}				
					
				} else if(SistemaEnum.MIC.equals(e.getCobrador())){
					
					for(ShvCobMedioPago medioPago : transaccionActualizar.getMediosPago()){
						if(!Validaciones.isObjectNull(medioPago) 
								&& medioPago instanceof ShvCobMedioPagoMic
								&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())){
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
						}
					}
					
					if(!Validaciones.isObjectNull(transaccionActualizar.getFactura())
							&& transaccionActualizar.getFactura() instanceof ShvCobFacturaMic
							&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccionActualizar.getFactura().getEstado())){
						transaccionActualizar.getFactura().setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
					}
					
					if(!Validaciones.isObjectNull(transaccionActualizar.getTratamientoDiferencia())
							&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccionActualizar.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccionActualizar.getTratamientoDiferencia().getEstado())){
						transaccionActualizar.getFactura().setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
					}					
					
				}				
			}
		}
		
		verificarEstadoTransaccion(transaccionActualizar, usuarioBatch);
		
		return descobro;
	}

	/**
	 * Cambia el estado del workflow del desccobro
	 * @param descCobro
	 * @param cobErrorCobro
	 * @throws NegocioExcepcion 
	 */
	private void cambiarEstadoErrorDescobro(ShvCobDescobro descobro, String descripcion, String usuarioBatch) throws NegocioExcepcion {
		if (Estado.DES_DESCOBRO_PROCESO.equals(descobro.getWorkflow().getEstado())){
			//Por primera vez
			if (!descobro.hayAlgunaTransaccionDescobrada()) {
				ShvWfWorkflow workflowActualizado = workflowDescobros.registrarErrorEnPrimerDescobro(descobro.getWorkflow(), descripcion, usuarioBatch);
				descobro.setWorkflow(workflowActualizado);
			} else {
				ShvWfWorkflow workflowActualizado = workflowDescobros.registrarDescobroEnError(descobro.getWorkflow(), descripcion, usuarioBatch);
				descobro.setWorkflow(workflowActualizado);
			}
		} else {
			Traza.advertencia(getClass(), "Se quizo avanzar en el workflow de descobros pero el descobro no esta en estado 'En Proceso de Descobro'. ");
		}
	}

	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param descobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private void actualizarDescobro(ResultadoImputacion resultadoImputacionFactura, List<ResultadoImputacion> resultadoImputacionMediosPago,
			ResultadoImputacion resultadoImputacionCargoGenerado, List<ResultadoImputacion> resultadoImputacionDiferenciaCambio,
			ShvCobTransaccion transaccion, ShvCobDescobro descobro) throws NegocioExcepcion {
		
		//agrego los registros de diferencia de cambio de la transaccion
		actualizarDocumentosRelacionados(resultadoImputacionDiferenciaCambio, transaccion, descobro);
		
		if (!Validaciones.isObjectNull(resultadoImputacionFactura)
				&& !Validaciones.isObjectNull(transaccion.getFactura())) {
			actualizarFacturasDeTransaccionPostImputacion(resultadoImputacionFactura, transaccion.getFactura());
		}
		
		if (Validaciones.isCollectionNotEmpty(resultadoImputacionMediosPago) 
				&& Validaciones.isCollectionNotEmpty(transaccion.getMediosPago())) {
			
			double fechaHoraInicioNanoTime = System.nanoTime();
			Date fechaHoraInicio = new Date();

			Traza.auditoria(getClass(), "[Tiempo Actualizar Medios Pago] Comienzo a recorrer los medios de pago Mic/Calipso (Debito a proxima no incluido) a actualizar. Cant. Medios de Pago recibidos de cobrador: " + resultadoImputacionMediosPago.size());
			for (ResultadoImputacion resultadoImputacion : resultadoImputacionMediosPago) {
				if(!Validaciones.isObjectNull(resultadoImputacion)){
					actualizarMediosPagoDeTransaccionPostImputacion(resultadoImputacion, transaccion);
				}
			}
			
			Traza.loguearInfoProcesamiento(this.getClass(),"[Tiempo Actualizar Medios Pago] Fin actualizacion medios de pago Mic/Calipso ", fechaHoraInicio, fechaHoraInicioNanoTime, resultadoImputacionMediosPago.size());
		}
		
		List<ShvCobMedioPago> listaMp = null;
		
		listaMp = listarMediosPagoProxFactura(transaccion, SistemaEnum.CALIPSO);
		ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProxFacturaClp = (ShvCobMedioPagoDebitoProximaFacturaCalipso) (listaMp.size() > 0 ? listaMp.get(0) : null);
		
		listaMp = listarMediosPagoProxFactura(transaccion, SistemaEnum.MIC);
		ShvCobMedioPagoDebitoProximaFacturaMic debitoProxFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMic) (listaMp.size() > 0 ? listaMp.get(0) : null);
		
		if (!Validaciones.isObjectNull(resultadoImputacionCargoGenerado) 
				&& (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())
						|| !Validaciones.isObjectNull(debitoProxFacturaClp)
						|| !Validaciones.isObjectNull(debitoProxFacturaMic)
				)) {
			actualizarTratamientoOCargoDeTransaccionPostImputacion(resultadoImputacionCargoGenerado,transaccion,debitoProxFacturaClp,debitoProxFacturaMic);
		}
	}
	
	private void actualizarMensajeEstadoDiferenciaCambio(RespuestaInvocacion respuestaForzada, ShvCobTransaccion transaccion){
		
		Set<ShvCobTransaccion> listaTransaccionDifCambio = transaccion.getOperacion().getTransaccionHijoConNroTransaccionPadre(transaccion.getNumeroTransaccion());
		
		if(Validaciones.isCollectionNotEmpty(listaTransaccionDifCambio)){
			
			for(ShvCobTransaccion transaccionDifCambio : listaTransaccionDifCambio){
				
				//Actualizo las facturas de diferencia de cambio
				for(ShvCobFactura facturaDifCambio : transaccionDifCambio.getListaFacturasDiferenciaCambio()){
					actualizarInformacionMensajeEstado(respuestaForzada, facturaDifCambio, false, false, true);
				}
				
				//Actualizo los medios de pago de diferencia de cambio
				for(ShvCobMedioPago medioPagoDifCambio : transaccionDifCambio.getListaMediosPagoDiferenciaCambio()){
					actualizarInformacionMensajeEstado(respuestaForzada, medioPagoDifCambio, false, false, true);
				}
				
//			if(!TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeEstado())){
				//Actualizo la transaccion de diferencia de cambio si la transaccion padre esta OK
//				transaccionDifCambio.setEstadoProcesamiento(transaccion.getEstadoProcesamiento());
				transaccionDifCambio.setTipoMensajeEstado(transaccion.getTipoMensajeEstado());
//			}
			}
			
		}
	}
	
	/**
	 * 
	 * @param listaResultadoImputacion
	 * @param transaccion
	 * @param descobro
	 */
	private void actualizarDocumentosRelacionados(List<ResultadoImputacion> listaResultadoImputacion, ShvCobTransaccion transaccion,
			ShvCobDescobro descobro) {		
		
		Set<ShvCobDescobroDocumentoRelacionado> listaDocRelacionadosTransaccion = descobro.getDocumentosRelacionadosDeTransaccion(transaccion.getNumeroTransaccion());
		
		//Limpio los registros de diferencia de cambio de la transaccion actual para agregar los nuevos
		if(Validaciones.isCollectionNotEmpty(listaDocRelacionadosTransaccion)){
			descobro.getDocumentosRelacionados().removeAll(listaDocRelacionadosTransaccion);
		};
		
		if(Validaciones.isCollectionNotEmpty(listaResultadoImputacion)){
			
			//recorro los registros de diferencia de cambio de la transaccion actual
			for(ResultadoImputacion resultadoImputacion : listaResultadoImputacion){
				
				//si son de diferencia de cambio, los agrego a la base
				if(resultadoImputacion instanceof ResultadoImputacionDescobroDiferenciaCambio){
					
					ResultadoImputacionDescobroDiferenciaCambio resultadoDiferenciaCambio = (ResultadoImputacionDescobroDiferenciaCambio) resultadoImputacion; 
					
					if(Validaciones.isObjectNull(descobro.getDocumentosRelacionados())){
						descobro.setDocumentosRelacionados(new HashSet<ShvCobDescobroDocumentoRelacionado>());
					}
					
					ShvCobDescobroDocumentoRelacionado diferenciaCambio = mapearDiferenciaCambio(resultadoDiferenciaCambio);
					
					//lo asocio al descobro
					diferenciaCambio.setDescobro(descobro);
					Traza.auditoria(getClass(), "prueba imputacion " +  diferenciaCambio.getNroTransaccion().toString() + " doc: " + diferenciaCambio.getNumeroComprobanteOriginal());
					//lo agrego a la lista del descobro
					descobro.getDocumentosRelacionados().add(diferenciaCambio);
					
				}
			}	
		}
	}
	
	
	private ShvCobDescobroDocumentoRelacionado mapearDiferenciaCambio(ResultadoImputacionDescobroDiferenciaCambio resultadoDiferenciaCambio){
		
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
	 * Actualizo los resultados de tratamiento de diferencia (con o sin cargos generados)
	 * @param resultadoImputacion
	 * @param tratamientoDiferencia
	 * @throws ReintentoExcepcion 
	 */
	private void actualizarTratamientoOCargoDeTransaccionPostImputacion(
			ResultadoImputacion resultadoImputacion, ShvCobTransaccion transaccion, ShvCobMedioPagoDebitoProximaFacturaCalipso debitoProxClp,
			 ShvCobMedioPagoDebitoProximaFacturaMic debitoProxMic){

		try{
			
			if (transaccion.getNumeroTransaccion().equals(resultadoImputacion.getNumeroTransaccion())) {
				ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
				
				//Cargo Mic
				if (resultadoImputacion instanceof ResultadoImputacionDescobroCargoGeneradoMic){
						
					ResultadoImputacionDescobroCargoGeneradoMic resultadoImputacionDescobroCargoGeneradoMic = (ResultadoImputacionDescobroCargoGeneradoMic) resultadoImputacion;  

					if(!Validaciones.isObjectNull(debitoProxMic)) {
							
						
						actualizarInformacionMensajeEstado(resultadoImputacionDescobroCargoGeneradoMic.getRespuestaInvocacion(), debitoProxMic, false, false, false);
						
						if (!TipoMensajeEstadoEnum.ERR.equals(debitoProxMic.getTipoMensajeEstado())) {
							debitoProxMic.setEstadoCargoGenerado(resultadoImputacionDescobroCargoGeneradoMic.getEstadoCargoGenerado());
							debitoProxMic.setEstadoAcuerdoContracargo(resultadoImputacionDescobroCargoGeneradoMic.getEstadoAcuerdoContracargo());
//							debitoProxMic.setCobradorInteresesRealesNoReguladosTrasladados(resultadoImputacionDescobroCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados());
							debitoProxMic.setCobradorInteresesRealesReguladosTrasladados(resultadoImputacionDescobroCargoGeneradoMic.getInteresesRealesReguladosTrasladados());
						}
						
						
						//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoFactura, 
						//para luego pasar al estado en proceso contracargo 
						if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(debitoProxMic.getEstado())
								&& facturarContracargoCargoMic(debitoProxMic)) {
							debitoProxMic.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
							imputarContracargosCruzados(transaccion);
						}
						
					} else if (!Validaciones.isObjectNull(tratamientoDiferencia)
							&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
						
							actualizarInformacionMensajeEstado(resultadoImputacionDescobroCargoGeneradoMic.getRespuestaInvocacion(), tratamientoDiferencia, false, false, false);
							
							if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
								
								tratamientoDiferencia.setEstadoCargoGenerado(resultadoImputacionDescobroCargoGeneradoMic.getEstadoCargoGenerado());
								tratamientoDiferencia.setEstadoAcuerdoContracargo(resultadoImputacionDescobroCargoGeneradoMic.getEstadoAcuerdoContracargo());
								tratamientoDiferencia.setCobradorInteresesRealesNoReguladosTrasladados(resultadoImputacionDescobroCargoGeneradoMic.getInteresesRealesNoReguladosTrasladados());
//								tratamientoDiferencia.setCobradorInteresesRealesReguladosTrasladados(resultadoImputacionDescobroCargoGeneradoMic.getInteresesRealesReguladosTrasladados());
							}
							
							
							//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoCargo, 
							//para luego pasar al estado en proceso contracargo 
							if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(tratamientoDiferencia.getEstado())
									&& facturarContracargoTratamientoDiferencia(tratamientoDiferencia, SistemaEnum.MIC)) {
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
								imputarContracargosCruzados(transaccion);
							}
					}
					
				}
				
				//Cargo Calipso
				if(resultadoImputacion instanceof ResultadoImputacionDescobroCargoGeneradoCalipso){
					
					ResultadoImputacionDescobroCargoGeneradoCalipso resultadoImputacionDescobroCargoGeneradoCalipso = (ResultadoImputacionDescobroCargoGeneradoCalipso) resultadoImputacion;  
				
					if(!Validaciones.isObjectNull(debitoProxClp)){
						
						actualizarInformacionMensajeEstado(resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion(), debitoProxClp, false, false, false);
						
						if (!TipoMensajeEstadoEnum.ERR.equals(debitoProxClp.getTipoMensajeEstado())) {
							debitoProxClp.setEstadoCargoGenerado(resultadoImputacionDescobroCargoGeneradoCalipso.getEstadoCargoGenerado());
							debitoProxClp.setEstadoAcuerdoContracargo(resultadoImputacionDescobroCargoGeneradoCalipso.getEstadoAcuerdoContracargo());
							debitoProxClp.setCobradorInteresesRealesTrasladados(resultadoImputacionDescobroCargoGeneradoCalipso.getInteresesRealesTrasladados());
						}
						
						//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoFactura, 
						//para luego pasar al estado en proceso contracargo 
						if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(debitoProxClp.getEstado())
								&& facturarContracargoCargoClp(debitoProxClp)) {
							debitoProxClp.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
							imputarContracargosCruzados(transaccion);
						}
					} else if (!Validaciones.isObjectNull(tratamientoDiferencia)
							&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
						
							String estadoRespuesta = resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion().getResultado().getDescripcionCalipso(); //OK / ERR
							String codigoError = resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion().getCodigoError();
							if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
								&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(codigoError)) 
							{
								resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion().setResultado(TipoResultadoEnum.OK);
								Traza.auditoria(getClass(), "Metodo "+ MensajeServicioEnum.CLP_DESCOBRO.getDescripcion()
									+" ("+resultadoImputacionDescobroCargoGeneradoCalipso.getIdOperacionShiva() 
									+", rta: " + resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion().toString() +") "
											+ "cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
							}

							actualizarInformacionMensajeEstado(resultadoImputacionDescobroCargoGeneradoCalipso.getRespuestaInvocacion(), tratamientoDiferencia, false, false, false);
							
							if (!TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
								
								tratamientoDiferencia.setEstadoCargoGenerado(resultadoImputacionDescobroCargoGeneradoCalipso.getEstadoCargoGenerado());
								tratamientoDiferencia.setEstadoAcuerdoContracargo(resultadoImputacionDescobroCargoGeneradoCalipso.getEstadoAcuerdoContracargo());
								tratamientoDiferencia.setCobradorInteresesRealesTrasladados(resultadoImputacionDescobroCargoGeneradoCalipso.getInteresesRealesTrasladados());
							}
							
							
							//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoCargo, 
							//para luego pasar al estado en proceso contracargo 
							if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(tratamientoDiferencia.getEstado())
									&& facturarContracargoTratamientoDiferencia(tratamientoDiferencia, SistemaEnum.CALIPSO)) {
								
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
								imputarContracargosCruzados(transaccion);
							}
					}
				} 
			}
			
		} catch (NegocioExcepcion | ReintentoExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
		}
	}

	/**
	* Actualizo los resultados de la factura 
	* @param resultadoImputacion
	* @param factura
	* @throws NegocioExcepcion
	*/
	private void actualizarFacturasDeTransaccionPostImputacion(
			ResultadoImputacion resultadoImputacion, ShvCobFactura factura) throws NegocioExcepcion {
	
		try {
			if (!Validaciones.isObjectNull(resultadoImputacion)){
				
				if (resultadoImputacion instanceof ResultadoImputacionDescobroFactura){
					ResultadoImputacionDescobroFactura resultadoFactura = (ResultadoImputacionDescobroFactura) resultadoImputacion;
					
					String estadoRespuestaCalipso = "";
					String estadoRespuestaMic = "";
					String codigoError = resultadoFactura.getRespuestaInvocacion().getCodigoError();
					
					if(!Validaciones.isObjectNull(resultadoFactura.getRespuestaInvocacion().getResultado())){
						estadoRespuestaCalipso = resultadoFactura.getRespuestaInvocacion().getResultado().getDescripcionCalipso();
						estadoRespuestaMic = resultadoFactura.getRespuestaInvocacion().getResultado().getDescripcionMic();
					}
					
					if (factura.getTransaccion().getNumeroTransaccion().equals(resultadoImputacion.getNumeroTransaccion())) {
						
						if(factura instanceof ShvCobFacturaCalipso){
							Traza.auditoria(getClass(), "Mensaje "+ MensajeServicioEnum.CLP_DESCOBRO.getDescripcion()
									+" ("+resultadoFactura.getIdOperacionShiva() + ", rta: " + resultadoFactura.getRespuestaInvocacion().toString() +") ");
							
							if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)
									&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(codigoError)) 
							{
								resultadoFactura.getRespuestaInvocacion().setResultado(TipoResultadoEnum.OK);
								Traza.auditoria(getClass(), "Mensaje "+ MensajeServicioEnum.CLP_DESCOBRO.getDescripcion()
										+" ("+resultadoFactura.getIdOperacionShiva() 
										+", rta: " + resultadoFactura.getRespuestaInvocacion().toString() +") "
										+ "cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
							}
							
							ShvCobFacturaCalipso facturaClp = (ShvCobFacturaCalipso) factura;
							
							actualizarInformacionMensajeEstado(resultadoFactura.getRespuestaInvocacion(), facturaClp, false, false, false);
							
							if (TipoResultadoEnum.OK.getDescripcionCalipso().equalsIgnoreCase(estadoRespuestaCalipso)
									|| TipoResultadoEnum.WRN.getDescripcionCalipso().equalsIgnoreCase(estadoRespuestaCalipso)) {
								facturaClp.setEstadoAcuerdoContracargo(resultadoFactura.getEstadoAcuerdoContracargo());
								facturaClp.setEstadoCargoGenerado(resultadoFactura.getEstadoCargoGenerado());
								facturaClp.setCobradorInteresesTrasladadosRegulados(resultadoFactura.getCobradorInteresesTrasladadosRegulados());
							} 
							
							//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoFactura, 
							//para luego pasar al estado en proceso contracargo 
							if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(facturaClp.getEstado())
									&& facturarContracargoFactura(facturaClp, SistemaEnum.MIC)) {
								
								facturaClp.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
								imputarContracargosCruzados(factura.getTransaccion());
								
							}
							
						} else {
							if(factura instanceof ShvCobFacturaMic){
								Traza.auditoria(getClass(), "Mensaje "+ MensajeServicioEnum.CLP_DESCOBRO.getDescripcion()
										+" ("+resultadoFactura.getIdOperacionShiva() + ", rta: " + resultadoFactura.getRespuestaInvocacion().toString() +") ");
								
								if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)
										&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(resultadoFactura.getRespuestaInvocacion().getCodigoError())) {
									estadoRespuestaMic = TipoResultadoEnum.OK.getDescripcionMic();
									Traza.auditoria(getClass(),  "Mensaje " + MensajeServicioEnum.MIC_DESCOBRO.getDescripcion() 
											+ " ("+resultadoFactura.getIdOperacionShiva()+", rta: " + estadoRespuestaMic+") "
											+ "cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
								}
								
								ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) factura;
								
								actualizarInformacionMensajeEstado(resultadoFactura.getRespuestaInvocacion(), facturaMic, false, false, false);
								
								if (TipoResultadoEnum.OK.getDescripcionMic().equalsIgnoreCase(estadoRespuestaMic)
										|| TipoResultadoEnum.WRN.getDescripcionMic().equalsIgnoreCase(estadoRespuestaMic)) {
									facturaMic.setEstadoAcuerdoContracargo(resultadoFactura.getEstadoAcuerdoContracargo());
									facturaMic.setEstadoCargoGenerado(resultadoFactura.getEstadoCargoGenerado());
									facturaMic.setCobradorInteresesTrasladadosNoRegulados(resultadoFactura.getCobradorInteresesTrasladadosNoRegulados());
									facturaMic.setCobradorInteresesTrasladadosRegulados(resultadoFactura.getCobradorInteresesTrasladadosRegulados());
								}
								
								//Si dio todo OK y quedo descobrado, me fijo que si puedo generar contracargoFactura, 
								//para luego pasar al estado en proceso contracargo 
								if (EstadoFacturaMedioPagoEnum.DESCOBRO.equals(facturaMic.getEstado())
										&& facturarContracargoFactura(facturaMic, SistemaEnum.MIC)) {
									
									facturaMic.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO);
									imputarContracargosCruzados(factura.getTransaccion());
								}
							}
						}
					} else {
						Traza.error(getClass(), "Se ha recibido el numero de transaccion diferente " + resultadoImputacion.getNumeroTransaccion()  
								+ "y no se actualizara la factura de la operacion id: " + factura.getTransaccion().getOperacionTransaccionFormateado());
					}
				} else {
					Traza.error(getClass(), "resultadoImputacion instanceof ResultadoImputacionDescobroFactura "
							+ "y no se actualizara la factura de la operacion id: " + factura.getTransaccion().getOperacionTransaccionFormateado());
				}
				
			} else {
				Traza.error(getClass(), "No se recibio el ResultadoImputacion correspondiente "
						+ "y no se actualizara la factura de la operacion id: " + factura.getTransaccion().getOperacionTransaccionFormateado());
			}
		
		} catch (ReintentoExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
		}
			
	}	
	
	/**
	 * Me permite determinar si puedo generar contracargo Factura
	 * 
	 * Si facturarContracargo es no y la factura tiene un cargo, 
	 *		entonces tengo que llamar a calipso o Mic para generar el contracargo ahi
	 * @param factura
	 * @return
	 */
	private boolean facturarContracargoFactura(ShvCobFactura factura, SistemaEnum sistema){
		
		if(!Validaciones.isObjectNull(factura)){
			
			SiNoEnum facturarContracargoFactura = null;
			
			//si la factura tiene sistema contracargo, quiere decir que tiene un cargo y si es de mic, entonces hago los seteos correspondientes
			if(sistema.equals(factura.getSistemaOrigen())
					&& factura.getSistemaOrigen().equals(factura.getSistemaAcuerdoContracargo())){
				facturarContracargoFactura = SiNoEnum.SI;
			} else {
				facturarContracargoFactura = SiNoEnum.NO;
			}
			
			//si la factura tiene intereses trasladados, si facturarContracargo es no, la factura tiene un acuerdo contracargo y el estado del cargo generado esta facturado
			//entonces tengo que llamar a calipso para generar el contracargo ahi
			if(Utilidad.decimalMayorACero(factura.getCobradorInteresesTrasladados())
					&& SiNoEnum.NO.equals(facturarContracargoFactura)
					&& !Validaciones.isNullOrEmpty(factura.getAcuerdoContracargo())
					&& EstadoCargoGeneradoEnum.FACTURADO.equals(factura.getEstadoCargoGenerado())){
				return true;
			}
		}		
		
		return false;
	}

	/**
	 * Me permite determinar si puedo generar contracargo Factura
	 * 
	 * Si facturarContracargo es no y la factura tiene un cargo, 
	 *		entonces tengo que llamar a calipso o Mic para generar el contracargo ahi
	 * @param factura
	 * @return
	 */
	private boolean facturarContracargoCargoClp(ShvCobMedioPagoDebitoProximaFacturaCalipso medioPago){
		
		SiNoEnum facturarContracargoCargo = null;
		if(SistemaEnum.CALIPSO.equals(medioPago.getSistemaOrigen())
				&& medioPago.getSistemaOrigen().equals(medioPago.getSistemaAcuerdoContracargo())){
			facturarContracargoCargo = SiNoEnum.SI;
		} else {
			facturarContracargoCargo = SiNoEnum.NO;
		}
		
		//si facturarContracargo es no, entonces tengo que llamar a calipso para generar el contracargo ahi
		if(SiNoEnum.NO.equals(facturarContracargoCargo) 
				&& EstadoCargoGeneradoEnum.FACTURADO.equals(medioPago.getEstadoCargoGenerado())
				&& !Validaciones.isNullOrEmpty(medioPago.getAcuerdoContracargo())){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Me permite determinar si puedo generar contracargo Tratamiento de diferencia
	 * 
	 * Si facturarContracargo es no y la factura tiene un cargo, 
	 *		entonces tengo que llamar a calipso o Mic para generar el contracargo ahi
	 * @param factura
	 * @return
	 */
	private boolean facturarContracargoTratamientoDiferencia(ShvCobTratamientoDiferencia tratamientoDiferencia, SistemaEnum sistema){
		SiNoEnum facturarContracargoCargo = null;
		
		//Detalle del Contracargo a Generar por Cargo (Agrupador)
		if(SistemaEnum.CALIPSO.equals(tratamientoDiferencia.getSistemaAcuerdoContracargo())){
			facturarContracargoCargo = SiNoEnum.SI;
		} else {
			facturarContracargoCargo = SiNoEnum.NO;
		}
		
		if(SiNoEnum.NO.equals(facturarContracargoCargo) 
				&& EstadoCargoGeneradoEnum.FACTURADO.equals(tratamientoDiferencia.getEstadoCargoGenerado())
				&& !Validaciones.isNullOrEmpty(tratamientoDiferencia.getAcuerdoContracargo())){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Me permite determinar si puedo generar contracargo Factura
	 * 
	 * Si facturarContracargo es no y la factura tiene un cargo, 
	 *		entonces tengo que llamar a calipso o Mic para generar el contracargo ahi
	 * @param factura
	 * @return
	 */
	private boolean facturarContracargoCargoMic(ShvCobMedioPagoDebitoProximaFacturaMic medioPago){
		
		SiNoEnum facturarContracargoCargo = null;
		if(SistemaEnum.MIC.equals(medioPago.getSistemaOrigen())
				&& medioPago.getSistemaOrigen().equals(medioPago.getSistemaAcuerdoContracargo())){
			facturarContracargoCargo = SiNoEnum.SI;
		} else {
			facturarContracargoCargo = SiNoEnum.NO;
		}
		
		//si facturarContracargo es no, entonces tengo que llamar a Mic para generar el contracargo ahi
		if(SiNoEnum.NO.equals(facturarContracargoCargo) 
				&& !Validaciones.isNullOrEmpty(medioPago.getAcuerdoTrasladoCargo())){
			return true;
		}
		
		return false;
	}
	
	/**
	* Actualizo los medios de pago de cualquier cobrador
	* @param listaResultadoSimulacion
	* @param medioPago
	*/
	private void actualizarMediosPagoDeTransaccionPostImputacion(ResultadoImputacion resultadoImputacion, ShvCobTransaccion transaccion) {
		
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			
			//Busco o Verifico que la transaccion es la misma 
			if (resultadoImputacion.getNumeroTransaccion().equals(medioPago.getTransaccion().getNumeroTransaccion())) {
				
				//Medio Pago Mic
				if (resultadoImputacion instanceof ResultadoImputacionDescobroMedioPagoMic 
							&& medioPago instanceof ShvCobMedioPagoMic){
						
					ResultadoImputacionDescobroMedioPagoMic resultado = (ResultadoImputacionDescobroMedioPagoMic) resultadoImputacion;
					
					if(!Validaciones.isObjectNull(resultado.getTipoMedioPago())){
						if(TipoMedioPagoEnum.RT.getCodigoGeneralTipoMedioPago().equals(resultado.getTipoMedioPago().getCodigoGeneralTipoMedioPago())
								&& medioPago instanceof ShvCobMedioPagoRemanente){
							ShvCobMedioPagoRemanente medioPagoMic = (ShvCobMedioPagoRemanente) medioPago;
							
							if(!EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(medioPagoMic.getEstado())) {
								if(!Validaciones.isNullEmptyOrDash(resultado.getIdBusquedaRespuestaCobrador())
										&& !Validaciones.isNullEmptyOrDash(medioPagoMic.getIdBusquedaRespuestaCobrador())
										&& resultado.getIdBusquedaRespuestaCobrador().equals(medioPagoMic.getIdBusquedaRespuestaCobrador())
										){
									actualizarInformacionMensajeEstado(resultado.getRespuestaInvocacion(), medioPagoMic, false, false, false);
									break;
								}
							} else {
								Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el medio de pago id:" + medioPago.getIdMedioPago().toString() 
										+ ", tipo:" + medioPago.getTipoMedioPago().getDescripcion() 
										+ " de la transaccion nro " + transaccion.getNumeroTransaccion()
										+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
										+ " al estado " + resultado.getRespuestaInvocacion().getResultado() + ", por estar en estado de ERROR DESCOBRO");
							}
						} else if(TipoMedioPagoEnum.NC.getCodigoGeneralTipoMedioPago().equals(resultado.getTipoMedioPago().getCodigoGeneralTipoMedioPago())
								&& medioPago instanceof ShvCobMedioPagoNotaCreditoMic){
							ShvCobMedioPagoNotaCreditoMic medioPagoMic = (ShvCobMedioPagoNotaCreditoMic) medioPago;
							
							if(!EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(medioPagoMic.getEstado())) {
								if(!Validaciones.isNullEmptyOrDash(resultado.getIdBusquedaRespuestaCobrador())
										&& !Validaciones.isNullEmptyOrDash(medioPagoMic.getIdBusquedaRespuestaCobrador())
										&& resultado.getIdBusquedaRespuestaCobrador().equals(medioPagoMic.getIdBusquedaRespuestaCobrador())
										){
									actualizarInformacionMensajeEstado(resultado.getRespuestaInvocacion(), medioPagoMic, false, false, false);
									break;
								}
							} else {
								Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el medio de pago id:" + medioPago.getIdMedioPago().toString() 
										+ ", tipo:" + medioPago.getTipoMedioPago().getDescripcion() 
										+ " de la transaccion nro " + transaccion.getNumeroTransaccion()
										+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
										+ " al estado " + resultado.getRespuestaInvocacion().getResultado() + ", por estar en estado de ERROR DESCOBRO");
							}
						}
					}
					
				} else if (resultadoImputacion instanceof ResultadoImputacionDescobroMedioPagoCalipso
						&& medioPago instanceof ShvCobMedioPagoCalipso) {
					
					ResultadoImputacionDescobroMedioPagoCalipso resultado = (ResultadoImputacionDescobroMedioPagoCalipso) resultadoImputacion; 
						
					if(medioPago instanceof ShvCobMedioPagoCTA){
						ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;
						
						// Comparar
						if (	
							(
							!Validaciones.isNullOrEmpty(medioPagoCTA.getIdBusquedaRespuestaCobrador()) 
								&& !Validaciones.isNullOrEmpty(resultado.getIdBusquedaRespuestaCobrador()) 
								&& medioPagoCTA.getIdBusquedaRespuestaCobrador().equals(resultado.getIdBusquedaRespuestaCobrador()) 
							)
							||
							(!Validaciones.isObjectNull(medioPagoCTA.getIdDocumentoCuentaCobranza())
								&& !Validaciones.isObjectNull(resultado.getIdDocumentoCuentaCobranza())
								&& resultado.getIdDocumentoCuentaCobranza().equals(medioPagoCTA.getIdDocumentoCuentaCobranza())))
						{
							if(!EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(medioPagoCTA.getEstado())) {
								actualizarInformacionMensajeEstado(resultado.getRespuestaInvocacion(), medioPagoCTA, false, false, false);
								break;
							} else {
								Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el medio de pago id:" + medioPago.getIdMedioPago().toString() 
										+ ", tipo:" + medioPago.getTipoMedioPago().getDescripcion() 
										+ " de la transaccion nro: " + transaccion.getNumeroTransaccion()
										+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
										+ " al estado " + resultado.getRespuestaInvocacion().getResultado() + ", por estar en estado de ERROR DESCOBRO");
							}
							//Por ser un medio de pago normal, no voy a generar cargos
						}
					}else{
						if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
							ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
							
							// Comparar
							if (
								(
								!Validaciones.isNullOrEmpty(medioPagoNotaCreditoCalipso.getIdBusquedaRespuestaCobrador()) 
									&& !Validaciones.isNullOrEmpty(resultado.getIdBusquedaRespuestaCobrador()) 
									&& medioPagoNotaCreditoCalipso.getIdBusquedaRespuestaCobrador().equals(resultado.getIdBusquedaRespuestaCobrador()) 
								)
								||
								(!Validaciones.isObjectNull(medioPagoNotaCreditoCalipso.getIdDocumentoCuentaCobranza())
									&& !Validaciones.isObjectNull(resultado.getIdDocumentoCuentaCobranza())
									&& resultado.getIdDocumentoCuentaCobranza().equals(medioPagoNotaCreditoCalipso.getIdDocumentoCuentaCobranza())))
							{
								if(!EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(medioPagoNotaCreditoCalipso.getEstado())) {
									actualizarInformacionMensajeEstado(resultado.getRespuestaInvocacion(), medioPagoNotaCreditoCalipso, false, false, false);
									break;
								} else {
									Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el medio de pago id:" + medioPago.getIdMedioPago().toString() 
											+ ", tipo:" + medioPago.getTipoMedioPago().getDescripcion() 
											+ " de la transaccion nro: " + transaccion.getNumeroTransaccion()
											+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
											+ " al estado " + resultado.getRespuestaInvocacion().getResultado() + ", por estar en estado de ERROR DESCOBRO");
								}
								//Por ser un medio de pago normal, no voy a generar cargos
							}
						}
					}			
				}
			}
			
		}//Fin For
		
	}
	
	/**
	 * Actualiza la informacion y los estados 
	 * @param respuestaInvocacion
	 * @param destino
	 */
	private void actualizarInformacionMensajeEstado(RespuestaInvocacion respuestaInvocacion, Modelo destino, boolean esContracargo, boolean tieneInteresPendienteContracargo, boolean esDiferenciaCambio) {
		
		TipoResultadoEnum tipoResultado = respuestaInvocacion.getResultado();
		
		StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);
		TipoMensajeEstadoEnum tipoMensajeEstado = TipoMensajeEstadoEnum.OK;
		
		// El resultado Warning tiene mas peso que el OK
		if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeEstado) && TipoResultadoEnum.WRN.equals(tipoResultado)) {
			tipoMensajeEstado = TipoMensajeEstadoEnum.WRN;
		} 
			
		// El resultado Error tiene mas peso que el OK o el Warning
		if ((TipoMensajeEstadoEnum.OK.equals(tipoMensajeEstado) || TipoMensajeEstadoEnum.WRN.equals(tipoMensajeEstado))
					&& (TipoResultadoEnum.ERROR.equals(tipoResultado) || TipoResultadoEnum.NOK.equals(tipoResultado)))  {
			tipoMensajeEstado = TipoMensajeEstadoEnum.ERR;
		} 

		if (!TipoResultadoEnum.OK.equals(tipoResultado)) {
			if (!Constantes.EMPTY_STRING.equals(detalleMensaje.toString().trim())) {
				detalleMensaje.append("<br>");
			}
			
			detalleMensaje.append(tipoMensajeEstado.getDescripcion());
			detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
			detalleMensaje.append(respuestaInvocacion.getDescripcionError().toString().trim());
		}
		
		if (destino instanceof ShvCobMedioPago) {
			ShvCobMedioPago medioPago = (ShvCobMedioPago) destino;
			
			if (destino instanceof ShvCobMedioPagoCalipso) {
				//seteo los campos si es calipso
				setearEstadoMedioPagoClp(respuestaInvocacion, medioPago, esContracargo, esDiferenciaCambio);
			} else if (destino instanceof ShvCobMedioPagoMic) {
				//seteo los campos si es mic
				setearEstadoMedioPagoMic(respuestaInvocacion, medioPago, esContracargo, esDiferenciaCambio);
			}
		} else { 
			if (destino instanceof ShvCobFactura) {
				ShvCobFactura factura = (ShvCobFactura) destino;
				factura.setMensajeEstado(detalleMensaje.toString());
				factura.setTipoMensajeEstado(tipoMensajeEstado);
				
				if(!esDiferenciaCambio){
					
					if(!tieneInteresPendienteContracargo){
						
						if (TipoResultadoEnum.OK.equals(tipoResultado)
								|| TipoResultadoEnum.WRN.equals(tipoResultado)) {
							factura.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						} else if (!esContracargo){
							factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
						} else {
							if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION.equals(factura.getEstado())) {
								factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO_CONFIRMACION);
							} else{
								
								factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO);
							}
						}
					}				
				}
				
				Traza.auditoria(getClass(), "Se actualizo factura id:" + factura.getIdFactura() 
						+ " operacion id:" + factura.getTransaccion().getOperacionTransaccionFormateado()
						+ " sistema: " + factura.getSistemaOrigen().getDescripcion()
						+ " tipo mensaje estado: " + tipoMensajeEstado.getDescripcion()
						+ " mensaje estado: " + detalleMensaje.toString()
						+ " con el estado: " + factura.getEstado().name().toString()
						);
			} else { 
				
				if (destino instanceof ShvCobTratamientoDiferencia) {
					ShvCobTratamientoDiferencia tratamientoDiferencia = (ShvCobTratamientoDiferencia) destino;
					tratamientoDiferencia.setMensajeEstado(detalleMensaje.toString());
					tratamientoDiferencia.setTipoMensajeEstado(tipoMensajeEstado);

					if(!tieneInteresPendienteContracargo){
						if (TipoResultadoEnum.OK.equals(tipoResultado)
								|| TipoResultadoEnum.WRN.equals(tipoResultado)) {
							tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						} else if (!esContracargo){
							tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
						} else {
							if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION.equals(tratamientoDiferencia.getEstado())) {
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO_CONFIRMACION);
							} else{
								
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO);
							}
						}
					}	
					
					Traza.auditoria(getClass(), "Se actualizo tratamiento Diferencia id:" + tratamientoDiferencia.getIdTratamientoDiferencia()
							+ " operacion id:" + tratamientoDiferencia.getTransaccion().getOperacionTransaccionFormateado()
							+ " sistema: " + tratamientoDiferencia.getTipoTratamientoDiferencia().getDescripcion()
							+ " tipo mensaje estado: " + tipoMensajeEstado.getDescripcion()
							+ " mensaje estado: " + detalleMensaje.toString()
							+ " con el estado: " + tratamientoDiferencia.getEstado().name().toString()
							);
				}
			}
		}
	}
	
	
	
	
	/***************************************************************************************
	 * Cargas Resultado Imputacion
	 ***************************************************************************************/
	
	/**
	 * CALIPSO: Cargo el resultado Factura del calipso
	 * Detalle de Resultado de Reversión de Factura (Agrupador)
	 * @param entrada
	 * @param salida
	 * @return
	 * @throws ReintentoExcepcion 
	 * @throws NegocioExcepcion 
	 */
	private ResultadoImputacion cargarResultadoFacturaCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida) throws NegocioExcepcion, ReintentoExcepcion{
		
		if (!Validaciones.isObjectNull(salida.getDetalleFacturaDescobro())
				&& !Validaciones.isObjectNull(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getResultado())) {
			
			ResultadoImputacionDescobroFactura respuestaImputacionFacClp = new ResultadoImputacionDescobroFactura();
			
			//aca seteo el id operacion original del cobro que se mando al cobrador
			respuestaImputacionFacClp.setIdOperacion(entrada.getIdOperacion());
			respuestaImputacionFacClp.setNumeroTransaccion(entrada.getNumeroTransaccion());
			respuestaImputacionFacClp.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			respuestaImputacionFacClp.setTipoInvocacion(salida.getTipoOperacion());
			
			respuestaImputacionFacClp.setEstadoAcuerdoContracargo(salida.getDetalleFacturaDescobro().getEstadoAcuerdoFacturacion());
			respuestaImputacionFacClp.setEstadoCargoGenerado(salida.getDetalleFacturaDescobro().getEstadoCargoGenerado());
			
			if(!Validaciones.isObjectNull(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura())){
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoEnum.getEnumByDescripcionCalipso(salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getResultado()),
						salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getCodigoError(),
						salida.getDetalleFacturaDescobro().getResultadoDescobroFactura().getDescripcionError());
				
				respuestaImputacionFacClp.setRespuestaInvocacion(resultadoInvocacion);
			}
			return respuestaImputacionFacClp;
		}
		
		return null;
	}
	
	/**
	 * CALIPSO: Reviso la respuesta de calipso por medios de pago
	 * @param entrada
	 * @param salida
	 * @return
	 */
	private List<ResultadoImputacion> cargarResultadoMedioPagoCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida){
		
		List<ResultadoImputacion> listaResultadoImputacion = new ArrayList<ResultadoImputacion>();
		
		if (Validaciones.isCollectionNotEmpty(salida.getListaDetalleMedioPagoDescobro())) {
			
			for(DetalleMedioPagoDescobro medioPago : salida.getListaDetalleMedioPagoDescobro()){
				
				if (!Validaciones.isObjectNull(medioPago)
						&& !Validaciones.isObjectNull(medioPago.getResultadoDescobroMedioPago())
						&& !Validaciones.isNullOrEmpty(medioPago.getResultadoDescobroMedioPago().getResultado())) {
					
					ResultadoImputacionDescobroMedioPagoCalipso resultadoImputacion = new ResultadoImputacionDescobroMedioPagoCalipso();
					
					// Datos de respuesta
					resultadoImputacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
					resultadoImputacion.setIdOperacion(entrada.getIdOperacion());		
					resultadoImputacion.setNumeroTransaccion(entrada.getNumeroTransaccion());		
					resultadoImputacion.setTipoInvocacion(salida.getTipoOperacion());	

					if(!Validaciones.isObjectNull(medioPago.getIdMedioPago())){
						resultadoImputacion.setTipoComprobante(medioPago.getIdMedioPago().getTipoComprobante());
						resultadoImputacion.setClaseComprobante(medioPago.getIdMedioPago().getClaseComprobante());
						resultadoImputacion.setSucursalComprobante(medioPago.getIdMedioPago().getSucursalComprobante());
						resultadoImputacion.setNumeroComprobante(medioPago.getIdMedioPago().getNumeroComprobante());
					}

					if(!Validaciones.isObjectNull(medioPago.getIdDocCtasCob())){
						resultadoImputacion.setIdDocumentoCuentaCobranza(new Long(medioPago.getIdDocCtasCob().toString()));
					}
					
					if (!Validaciones.isObjectNull(medioPago.getResultadoDescobroMedioPago())) {
						
						// Resultados posibles: OK / NOK / ERR
						RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoEnum.getEnumByDescripcionCalipso(medioPago.getResultadoDescobroMedioPago().getResultado()),
								medioPago.getResultadoDescobroMedioPago().getCodigoError(),
								medioPago.getResultadoDescobroMedioPago().getDescripcionError());
						
						resultadoImputacion.setRespuestaInvocacion(resultadoInvocacion);
					}					
					
					listaResultadoImputacion.add(resultadoImputacion);
				}
			}
		}
		
		return listaResultadoImputacion;
	}
	
	
	/**
	 * CALIPSO: Detalle de Cargo Generado (Agrupador)
	 * @param entrada
	 * @param salida
	 * @return
	 */
	private ResultadoImputacion cargarResultadoCargoGeneradoCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida){
		
		if (!Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro())
				&& !Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getResultado())) {
			
			ResultadoImputacionDescobroCargoGeneradoCalipso respuestaCargoClp = new ResultadoImputacionDescobroCargoGeneradoCalipso();
			
			//aca seteo el id operacion original que se mando al cobrador
			respuestaCargoClp.setIdOperacion(entrada.getIdOperacion());
			respuestaCargoClp.setNumeroTransaccion(entrada.getNumeroTransaccion());
			respuestaCargoClp.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			respuestaCargoClp.setTipoInvocacion(salida.getTipoOperacion());
			
			respuestaCargoClp.setEstadoAcuerdoContracargo(salida.getDetalleCargoGeneradoDescobro().getEstadoAcuerdoFacturacion());
			respuestaCargoClp.setEstadoCargoGenerado(salida.getDetalleCargoGeneradoDescobro().getEstadoCargoGenerado());
			respuestaCargoClp.setInteresesRealesTrasladados(salida.getDetalleCargoGeneradoDescobro().getInteresesRealesTrasladados());
			
			if(!Validaciones.isObjectNull(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado())){
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoEnum.getEnumByDescripcionCalipso(salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getResultado()),
						salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getCodigoError(),
						salida.getDetalleCargoGeneradoDescobro().getResultadoDescobroCargoGenerado().getDescripcionError());
				
				respuestaCargoClp.setRespuestaInvocacion(resultadoInvocacion);
			}
			return respuestaCargoClp;
		}
		return null;
	}
	
	
	private List<ResultadoImputacion> cargarResultadoDiferenciaCambioCalipso(EntradaCalipsoDescobroWS entrada, SalidaCalipsoDescobroWS salida){
		
		List<ResultadoImputacion> listaResultadoImputacion = new ArrayList<ResultadoImputacion>();
		
		// Reviso la respuesta de calipso por diferencia de cambio
		if (Validaciones.isCollectionNotEmpty(salida.getListaNotasCreditoDebitoDescobro())) {
			ResultadoImputacionDescobroDiferenciaCambio resultadoImputacion = new ResultadoImputacionDescobroDiferenciaCambio();
			
			for(DetalleNotaCreditoDebitoDescobro diferenciaCambio : salida.getListaNotasCreditoDebitoDescobro()){
				if (!Validaciones.isObjectNull(diferenciaCambio) 
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado())
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getIdCobranza())
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal())
						&& !Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado())) {
					
					resultadoImputacion.setSistemaOrigen(SistemaEnum.CALIPSO);
					
					resultadoImputacion.setIdOperacion(entrada.getIdOperacionDescobroMensajeria());
					
					String idOperacionTransaccionCobro = salida.getIdOperacionTransaccion();
					
					//Separo el idOperacion del nroTransaccion y me quedo con el nro de transaccion
					if(!Validaciones.isNullEmptyOrDash(idOperacionTransaccionCobro) 
							&& idOperacionTransaccionCobro.length() == 13
							&& idOperacionTransaccionCobro.contains(".")){
						
						Integer nroTransaccion = new Integer(idOperacionTransaccionCobro.substring(8, idOperacionTransaccionCobro.length()));
						
						resultadoImputacion.setNumeroTransaccion(nroTransaccion);
					}
					
					
					// Datos de respuesta - Generado
					resultadoImputacion.setIdCobranza(new Long(diferenciaCambio.getNotaCreDebGenerado().getIdCobranza().toString()));
					
					resultadoImputacion.setImporte(diferenciaCambio.getNotaCreDebGenerado().getImporte());
					resultadoImputacion.setImporteCapital(diferenciaCambio.getNotaCreDebGenerado().getImporteCapital());
					resultadoImputacion.setImporteImpuestos(diferenciaCambio.getNotaCreDebGenerado().getImporteImpuestos());
					
					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado())){
						
						resultadoImputacion.setTipoComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getTipoComprobante());
						resultadoImputacion.setClaseComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getClaseComprobante());
						resultadoImputacion.setNumeroComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getNumeroComprobante());
						resultadoImputacion.setSucursalComprobante(diferenciaCambio.getNotaCreDebGenerado().getDocumentoGenerado().getSucursalComprobante());
					}

					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebGenerado().getIdDocumentoCuentasCobranza())){
						resultadoImputacion.setIdDocumentoCuentasCobranza(new Long(diferenciaCambio.getNotaCreDebGenerado().getIdDocumentoCuentasCobranza().toString()));
					}
					
					resultadoImputacion.setFechaVencimiento(diferenciaCambio.getNotaCreDebGenerado().getFechaVencimiento());
					resultadoImputacion.setFechaImputacion(diferenciaCambio.getNotaCreDebGenerado().getFechaImputacion());
					resultadoImputacion.setImporteAplicado(diferenciaCambio.getNotaCreDebGenerado().getImporteAplicado());
					resultadoImputacion.setOrigenDocumento(diferenciaCambio.getNotaCreDebGenerado().getOrigenDelDocumento());
					
					
					// Datos de respuesta - Original
					resultadoImputacion.setTipoComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getTipoComprobante());
					resultadoImputacion.setClaseComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getClaseComprobante());
					resultadoImputacion.setNumeroComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getNumeroComprobante());
					resultadoImputacion.setSucursalComprobanteOriginal(diferenciaCambio.getNotaCreDebOriginal().getDocumentoGenerado().getSucursalComprobante());

					if(!Validaciones.isObjectNull(diferenciaCambio.getNotaCreDebOriginal().getIdDocumentoCuentasCobranza())){
						resultadoImputacion.setIdDocumentoCuentasCobranzaOriginal(new Long(diferenciaCambio.getNotaCreDebOriginal().getIdDocumentoCuentasCobranza().toString()));
					}
					
					listaResultadoImputacion.add(resultadoImputacion);
				}
			}
		}
		
		return listaResultadoImputacion;
	}
	
	/**
	 * MIC: respuesta de mic por factura
	 * @param mensajeMIC
	 * @param salida
	 * @return
	 */
	private ResultadoImputacion cargarResultadoFacturaMic(ShvCobTransaccion transaccion, MicTransaccionDescobroRespuestaDto salida){
		
		if (!Validaciones.isObjectNull(salida.getDetalleFactura())
				&& !Validaciones.isObjectNull(salida.getDetalleFactura().getResultadoDescobroFactura())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleFactura().getResultadoDescobroFactura().getResultadoInvocacion())) {
				
			ResultadoImputacionDescobroFactura resultadoImputacion = new ResultadoImputacionDescobroFactura();
			
			// Datos de respuesta
			resultadoImputacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			resultadoImputacion.setIdOperacion(transaccion.getOperacion().getIdOperacion());		
			resultadoImputacion.setNumeroTransaccion(transaccion.getNumeroTransaccion());		
			resultadoImputacion.setTipoInvocacion(salida.getTipoInvocacion());		
			
			// si eso tampoco trae un acuerdo activo tengo que buscar otros clientes del cobro y si no encuentro nada, le seteo el estado inactivo al acuerdo original y listo
			resultadoImputacion.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoAcuerdoFacturacion()));
			resultadoImputacion.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByCodigo(salida.getDetalleFactura().getCodigoEstadoCargoGenerado()));
			resultadoImputacion.setCobradorInteresesTrasladadosNoRegulados(salida.getDetalleFactura().getInteresesRealesNoReguladosTrasladados());
			resultadoImputacion.setCobradorInteresesTrasladadosRegulados(salida.getDetalleFactura().getInteresesRealesReguladosTrasladados());
			
			// Resultados posibles: OK / NOK / ERR
			RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
					TipoResultadoEnum.getEnumByDescripcionMic(salida.getDetalleFactura().getResultadoDescobroFactura().getResultadoInvocacion()),
					salida.getDetalleFactura().getResultadoDescobroFactura().getCodigoError(),
					salida.getDetalleFactura().getResultadoDescobroFactura().getDescripcionError());
			
			resultadoImputacion.setRespuestaInvocacion(resultadoInvocacion);
		
			return resultadoImputacion;
		}
		
		return null;
	}
	
	
	/**
	 * MIC: respuesta de mic por medios de pago
	 * @param mensajeMIC
	 * @param salida
	 * @return
	 */
	private List<ResultadoImputacion> cargarResultadoMedioPagoMic(ShvCobTransaccion transaccion, MicTransaccionDescobroRespuestaDto salida){
		
		List<ResultadoImputacion> listaResultadoImputacion = new ArrayList<ResultadoImputacion>();
		
		if (Validaciones.isCollectionNotEmpty(salida.getListaDetalleMedioPago())) {
			
				for(MicDetalleDescobroMedioPagoDto medioPago : salida.getListaDetalleMedioPago()){
						
					if (!Validaciones.isObjectNull(medioPago)
							&& !Validaciones.isObjectNull(medioPago.getResultadoDescobroMedioPago())
							&& !Validaciones.isNullOrEmpty(medioPago.getResultadoDescobroMedioPago().getResultadoInvocacion())) {
						
						ResultadoImputacionDescobroMedioPagoMic resultadoImputacion = new ResultadoImputacionDescobroMedioPagoMic();
						
						// Datos de respuesta
						resultadoImputacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
						resultadoImputacion.setIdOperacion(transaccion.getOperacion().getIdOperacion());		
						resultadoImputacion.setNumeroTransaccion(transaccion.getNumeroTransaccion());		
						resultadoImputacion.setTipoInvocacion(salida.getTipoInvocacion());	
						
						resultadoImputacion.setTipoMedioPago(medioPago.getTipoMedioPago());
						
						if(!Validaciones.isObjectNull(medioPago.getCuentaRemanente())
								&& !"0".equals(medioPago.getCuentaRemanente().toString())){
							resultadoImputacion.setCuentaRemanente(medioPago.getCuentaRemanente());
							resultadoImputacion.setIdBusqueraRespuestaCobrador(medioPago.getTipoMedioPago().name() + medioPago.getTipoRemanente().name() + medioPago.getCuentaRemanente().toString());
						}
						
						resultadoImputacion.setTipoRemanente(medioPago.getTipoRemanente());
						
						if(!Validaciones.isObjectNull(medioPago.getNumeroReferenciaMic())
								&& !"0".equals(medioPago.getNumeroReferenciaMic().toString())){
							resultadoImputacion.setNumeroReferenciaMic(medioPago.getNumeroReferenciaMic());
							resultadoImputacion.setIdBusqueraRespuestaCobrador(medioPago.getTipoMedioPago().name() + medioPago.getNumeroReferenciaMic().toString());
						}
						
						// Resultados posibles: OK / NOK / ERR
						RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
								TipoResultadoEnum.getEnumByDescripcionMic(medioPago.getResultadoDescobroMedioPago().getResultadoInvocacion()),
								medioPago.getResultadoDescobroMedioPago().getCodigoError(),
								medioPago.getResultadoDescobroMedioPago().getDescripcionError());
						
						resultadoImputacion.setRespuestaInvocacion(resultadoInvocacion);
						
						listaResultadoImputacion.add(resultadoImputacion); 
					}
				}
			
		}
		
		return listaResultadoImputacion;
	}
	
	/**
	 * MIC: respuesta de mic por cargos
	 * @param mensajeMIC
	 * @param salida
	 * @return
	 */
	private ResultadoImputacion cargarResultadoCargoGeneradoMic(ShvCobTransaccion transaccion, MicTransaccionDescobroRespuestaDto salida){
		
		if (!Validaciones.isObjectNull(salida.getDetalleCargoGenerado())
				&& !Validaciones.isObjectNull(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado())
				&& !Validaciones.isNullOrEmpty(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getResultadoInvocacion())) {
				
			ResultadoImputacionDescobroCargoGeneradoMic resultadoImputacion = new ResultadoImputacionDescobroCargoGeneradoMic();
			
			// Datos de respuesta
			resultadoImputacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
			resultadoImputacion.setIdOperacion(transaccion.getOperacion().getIdOperacion());		
			resultadoImputacion.setNumeroTransaccion(transaccion.getNumeroTransaccion());		
			resultadoImputacion.setTipoInvocacion(salida.getTipoInvocacion());	
			
			// si eso tampoco trae un acuerdo activo tengo que buscar otros clientes del cobro y si no encuentro nada, le seteo el estado inactivo al acuerdo original y listo
			resultadoImputacion.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(salida.getDetalleCargoGenerado().getCodigoEstadoAcuerdoFacturacion()));
			resultadoImputacion.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByCodigo(salida.getDetalleCargoGenerado().getCodigoEstadoCargoGenerado()));
			resultadoImputacion.setInteresesRealesNoReguladosTrasladados(salida.getDetalleCargoGenerado().getInteresesRealesNoReguladosTrasladados());
			resultadoImputacion.setInteresesRealesReguladosTrasladados(salida.getDetalleCargoGenerado().getInteresesRealesReguladosTrasladados());
			
			if(!Validaciones.isObjectNull(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado())){
				// Resultados posibles: OK / NOK / ERR
				RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
						TipoResultadoEnum.getEnumByDescripcionMic(salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getResultadoInvocacion()),
						salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getCodigoError(),
						salida.getDetalleCargoGenerado().getResultadoDescobroCargoGenerado().getDescripcionError());
				
				resultadoImputacion.setRespuestaInvocacion(resultadoInvocacion);
			}
			
			return resultadoImputacion;
		}
		
		return null;
	}
	
	
	/***************************************************************************************
	 * Descobro SHIVA
	 ***************************************************************************************/
	/**
	 * Verifica que si el descobro esta en estado DES_DESCOBRO_PROCESO entonces
	 * recorre las transacciones. Si estas cumplen con unas condiciones entonces recorre los medios
	 * de pago de esa transaccion y revierte a los medios de pago Shiva.
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private ShvCobDescobro descobroMedioPagoShivaUsuarioYTratamientoDiferencia(ShvCobDescobro descobro) throws NegocioExcepcion {
		
		if (Estado.DES_DESCOBRO_PROCESO.equals(descobro.getWorkflow().getEstado())) {
			
			Traza.auditoria(getClass(), "realizarReversionMedioPagoValor", "Se ejecuta la reversion de medios de pago Shiva "
					+ " pertenecientes al id de operacion " + descobro.getOperacion().getIdOperacion());
			
			// me guardo todos los valores de shiva
			Map<Long, ShvValValorSimplificado> valores = new Hashtable<Long, ShvValValorSimplificado>();
            for (ShvCobTransaccion transaccion : descobro.getTransaccionesOKOrdenadasInversaSinDiferenciaCambio()){
            	
            	//Recorro los medios de pago
            	for(ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			
            		//Si son Shiva 
            		if(medioPago instanceof ShvCobMedioPagoShiva){
            			ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
            			
            			if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor()) 
            					&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPagoShiva.getEstado())) {
            				Long idValor = medioPagoShiva.getIdValor();
            				
            				if (!valores.containsKey(idValor)) {
            					ShvValValorSimplificado valor = valorServicio.buscarValorSimplificado(idValor);
								valores.put(valor.getIdValor(), valor);
            				}
            			} else {
							Traza.auditoria(getClass(), "realizarReversionMedioPagoValor", 
									"["+transaccion.getOperacionTransaccionFormateado()+" - "
											+ " idMedioPago:"+medioPagoShiva.getIdMedioPago()+"] contiene el objeto de valor: null o el estado del medio de pago es diferente de " + EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.name());
						}
            		}
            	}
            }

			//Descobro los medios de pago Shiva, Usuario y el Tratamiento a la diferencia que no sea credito a proxima
			descobro = revertirMediosPagoShivaUsuarioYTratamientoDiferenciaATransaccion(descobro, valores);
			
			if (!valores.isEmpty()) {
				for (Entry<Long, ShvValValorSimplificado> e: valores.entrySet()) {
					ShvValValorSimplificado valor = (ShvValValorSimplificado) e.getValue();
					try {
						valor = valorDao.actualizarValorSimplificado(valor);
					} catch (PersistenciaExcepcion exc) {
						throw new NegocioExcepcion(exc.getMessage(),exc);
					}
					Traza.auditoria(getClass(), "realizarReversionMedioPagoValor", "Se actualizo el valor id: "
							+ valor.getIdValor() + " con el saldo disponible final: " + valor.getSaldoDisponible());
				}
			}
		}
		
		return descobro;
	}
	
	
	/**
	 * Revierte los valores asociados a los medios de pago Shiva de la transaccion. Los medios de pago del cobro
	 * pasan a estado DESAPROPIADO y los medios de pago del descobro a estado DESCOBRO.
	 */
	private ShvCobDescobro revertirMediosPagoShivaUsuarioYTratamientoDiferenciaATransaccion(ShvCobDescobro descobro, Map<Long, ShvValValorSimplificado> valores)
            throws NegocioExcepcion {
		
		if(!Validaciones.isObjectNull(descobro)){
			
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			try {
				ShvCobCobro cobro = cobroDao.buscarCobro(descobro.getIdCobro());	
			
				for(ShvCobTransaccion transaccion : descobro.getTransaccionesOrdenadasInversaSoloMedioPagoShivaUsuarioTratamientoDiferencia()){
					
					//proceso la transaccion original
					revertirMediosPagoShivaUsuarioYActualizarDifCambio(transaccion, valores, cobro, false);
					
					List<ShvCobTransaccion> listaTransaccionesDifCambio = descobro.getTransaccionesDC(transaccion.getNumeroTransaccion());
					
					if(Validaciones.isCollectionNotEmpty(listaTransaccionesDifCambio)){
						for(ShvCobTransaccion transaccionDifCambio : listaTransaccionesDifCambio){
							//proceso la transaccion original
							revertirMediosPagoShivaUsuarioYActualizarDifCambio(transaccionDifCambio, valores, cobro, true);
							
						}
					}
					
					//Extraigo el tratamiento a la diferencia que no sea credito a proxima
					if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
						
						if(restoTransaccionDescobradaMenosTratamientoDiferencia(transaccion)){
							
							transaccion.getTratamientoDiferencia().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
							Traza.auditoria(getClass(), "Se realizo el cambio de tipo mensaje estado del tratamiento '"+ transaccion.getTratamientoDiferencia().getIdTratamientoDiferencia() +"' a" 
									+ transaccion.getTratamientoDiferencia().getTipoMensajeEstado().getDescripcion() + " correspondientes a la operacion id: " 
									+ transaccion.getTratamientoDiferencia().getTransaccion().getOperacionTransaccionFormateado());
							
							transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							Traza.advertencia(getClass(), "Se realizo el cambio de estado del tratamiento '"+ transaccion.getTratamientoDiferencia().getIdTratamientoDiferencia() +"' a" 
									+ transaccion.getTratamientoDiferencia().getEstado().descripcion() + " correspondientes a la operacion id: " 
									+ transaccion.getTratamientoDiferencia().getTransaccion().getOperacionTransaccionFormateado());
						}
					}
					
					verificarEstadoTransaccion(descobro.getTransaccionPorNumeroTransaccion(transaccion.getNumeroTransaccion()), usuarioBatch);
				}
				
			} catch (NegocioExcepcion | PersistenciaExcepcion | ReintentoExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		} else {
			Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
					"Se ha recibido el objeto de descobro: null");
		}
		
		return descobro;
	}
	
	/**
	 * 
	 * @param transaccion
	 * @param esDiferenciaCambio
	 * @throws NegocioExcepcion 
	 */
	private void revertirMediosPagoShivaUsuarioYActualizarDifCambio(ShvCobTransaccion transaccion, Map<Long, ShvValValorSimplificado> valores, ShvCobCobro cobro, boolean esDiferenciaCambio) throws NegocioExcepcion{
		
		if(!Validaciones.isObjectNull(transaccion)){
			
			String idOperacionTransaccion = transaccion.getOperacionTransaccionFormateado();
			
			for(ShvCobMedioPago medioPago : transaccion.getMediosPagoShivaUsuario()){
				
				// Si es un valor shiva revierto el importe al valor
				if(medioPago instanceof ShvCobMedioPagoShiva
						&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
					
					ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
					
					if(!esDiferenciaCambio){
						
						BigDecimal importe = medioPagoShiva.getImporte();
						
						ShvValValorSimplificado valor = valores.get(medioPagoShiva.getIdValor());
						
						if (!Validaciones.isObjectNull(valor)) {
							
							valor = valorServicio.revertirValoresPertenecientesATransaccion(valor, importe, cobro,  transaccion.getNumeroTransaccion(), false);
							
							valores.put(valor.getIdValor(), valor);
							
							//cambio de estado del medio de pago y le seteo OK en el tipo de mensaje
							medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							
							Traza.auditoria(getClass(), "revertirMediosPagoShivaUsuarioYActualizarDifCambio", 
									"["+idOperacionTransaccion+"]"
									+ " Id Medio Pago Shiva: " + medioPagoShiva.getIdMedioPago().toString()
									+ ", Id Valor Shiva: " + medioPagoShiva.getIdValor().toString()
									+ ", estado medio de pago actualizado a " + medioPagoShiva.getEstado().name().toString());
						}
					}
					
					medioPagoShiva.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					
					Traza.auditoria(getClass(), "revertirMediosPagoShivaUsuarioYActualizarDifCambio", 
							"["+idOperacionTransaccion+"] Id Medio Pago Shiva: " + medioPagoShiva.getIdMedioPago().toString()
							+ ", tipo mensaje estado actualizado a " + medioPagoShiva.getTipoMensajeEstado().name().toString()
							+ ", transaccion padre: " + medioPagoShiva.getTransaccion().getNumeroTransaccionPadre());
					
				} else if (medioPago instanceof ShvCobMedioPagoUsuario
						&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
					//Descobro los medios de pago usuario
					ShvCobMedioPagoUsuario medioPagoUsuario = ((ShvCobMedioPagoUsuario)medioPago);
					
					medioPagoUsuario.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					
					Traza.auditoria(getClass(), "Se realizo el cambio de tipo mensaje estado del medio de pago '"+ medioPagoUsuario.getIdMedioPago() +"' a" 
							+ medioPagoUsuario.getTipoMensajeEstado().getDescripcion() + " correspondientes a la operacion id: " 
							+ medioPagoUsuario.getTransaccion().getOperacionTransaccionFormateado()
							+ ", transaccion padre: " + medioPagoUsuario.getTransaccion().getNumeroTransaccionPadre());
					
					if(!esDiferenciaCambio){
						
						medioPagoUsuario.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						
						Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago '"+ medioPagoUsuario.getIdMedioPago() +"' a" 
								+ medioPagoUsuario.getEstado().descripcion() + " correspondientes a la operacion id: " 
								+ medioPagoUsuario.getTransaccion().getOperacionTransaccionFormateado());
					}
				}
			}
		}
	}
	
	
	/***************************************************************************
	 * Medios de pagos 
	 *************************************************************************/
	
	/**
	 * Retorna una lista con todos los Medios de Pago de tipo que coincida con el parametro cobrador
	 * pertenecientes a la transaccion.
	 * 
	 * @param transaccion
	 * @param cobrador (MIC/CALIPSO)
	 * @return
	 */
	private List<ShvCobMedioPago> listarMediosPago(ShvCobTransaccion transaccion, SistemaEnum cobrador){
		List<ShvCobMedioPago> lista = new ArrayList<ShvCobMedioPago>();
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(SistemaEnum.CALIPSO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoCalipso)){
				// Si tipo calipso agrego a la lista.
					lista.add(medioPago);
			}else{
				if(SistemaEnum.MIC.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoMic)){
					// Si tipo MIC agrego a la lista.
						lista.add(medioPago);
				}else{
					if(SistemaEnum.SHIVA.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoShiva)){
						// Si tipo SHIVA agrego a la lista.
							lista.add(medioPago);
					} else {
						if(SistemaEnum.USUARIO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoUsuario)){
							// Si tipo SHIVA agrego a la lista.
								lista.add(medioPago);
						}
					}
				}
			}
		}
		return lista;
	}
	
	/**
	 * Retorna el estado del primer Medio de pago de tipo MIC de la transaccion,
	 * ya que todos los medios de pago de tipo MIC deberian tener el mismo estado siempre.
	 * @param transaccion
	 * @return Si la transaccion no tiene Medios de pago MIC retorna null.
	 */
	private EstadoFacturaMedioPagoEnum getEstadoMedioPagoMIC(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMic){
				return medioPago.getEstado();
			}
		}
		return null;
	}
	
	/**
	 * Retorna el estado del primer Medio de pago de tipo Calipso de la transaccion,
	 * ya que todos los medios de pago de tipo Calipso deberian tener el mismo estado siempre.
	 * @param transaccion
	 * @return Si la transaccion no tiene Medios de pago Calipso retorna null.
	 */
	private EstadoFacturaMedioPagoEnum getEstadoMedioPagoCalipso(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipso){
				return medioPago.getEstado();
			}
		}
		return null;
	}
	
	private EstadoFacturaMedioPagoEnum getEstadoTratamientoDiferencia(ShvCobTransaccion transaccion) {
		
		if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
				return transaccion.getTratamientoDiferencia().getEstado();
		}
		return null;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo Calipso.
	 * @param transaccion
	 * @return
	 */
	private boolean existenMediosPagoCalipso(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipso){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo MIC.
	 * @param transaccion
	 * @return
	 */
	private boolean existenMediosPagoMic(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMic){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica que todos los medios de pago de la transaccion esten en estado Descobro
	 * @param transaccion
	 * @return
	 */
	private boolean todosMediosPagoDescobrados(ShvCobTransaccion transaccion) {
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(!EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifica si existe al menos un Medio de pago en estado ERROR_DESCOBRO.
	 * @param cobro
	 * @return
	 */
	private Boolean existeMedioPagoEnError(ShvCobTransaccion transaccion) {
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			if(medioPago instanceof ShvCobMedioPagoCalipso || medioPago instanceof ShvCobMedioPagoMic
					|| medioPago instanceof ShvCobMedioPagoShiva
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic){
				if(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO.equals(medioPago.getEstado())
						|| EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO.equals(medioPago.getEstado())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * CALIPSO: Actualizo el estado del medio de pago
	 * @param medioPago
	 */
	private void setearEstadoMedioPagoClp(RespuestaInvocacion respuestaInvocacion, ShvCobMedioPago medioPago, boolean esContracargo, boolean esDiferenciaCambio) {
		//Cambio de estado al medio de pago
		if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(respuestaInvocacion.getResultado().getDescripcionCalipso())
				|| TipoResultadoEnum.WRN.getDescripcionCalipso().equals(respuestaInvocacion.getResultado().getDescripcionCalipso())
				||	(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(respuestaInvocacion.getResultado().getDescripcionCalipso())
						  &&  Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(respuestaInvocacion.getCodigoError()))){
			
			
			medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			Traza.auditoria(getClass(), "Se realizo el cambio de tipo mensaje estado del medio de pago '"+ medioPago.getIdMedioPago() +"' a " 
					+ medioPago.getTipoMensajeEstado().getDescripcion() + " correspondientes a la operacion id: " 
					+ medioPago.getTransaccion().getOperacionTransaccionFormateado());
			
			if(!esDiferenciaCambio){
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
				Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago '"+ medioPago.getIdMedioPago() +"' a " 
						+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " 
						+ medioPago.getTransaccion().getOperacionTransaccionFormateado());
			}
			
			
		} else if (TipoResultadoEnum.NOK.getDescripcionCalipso().equals(respuestaInvocacion.getResultado().getDescripcionCalipso())
				|| TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(respuestaInvocacion.getResultado().getDescripcionCalipso())){
			
			if(!esContracargo){
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
			} else {
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO);
			}
			medioPago.setMensajeEstado("Cod Error: " + respuestaInvocacion.getCodigoError() + " | " +   
					respuestaInvocacion.getResultado() + " " + respuestaInvocacion.getDescripcionError());
			medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
			
			Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago id:" + medioPago.getIdMedioPago() 
					+ " a "	+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id:" + medioPago.getTransaccion().getOperacionTransaccionFormateado()
					+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
					+ " tipo mensaje estado: " + medioPago.getTipoMensajeEstado().getDescripcion()
					+ " mensaje estado: " + medioPago.getMensajeEstado().toString())
					;
		}
	}
	
	/**
	 * Mic: Actualizo el estado del medio de pago
	 * @param medioPago
	 */
	private void setearEstadoMedioPagoMic(RespuestaInvocacion respuestaInvocacion, ShvCobMedioPago medioPago, boolean esContracargo, boolean esDiferenciaCambio) {
		//Cambio de estado al medio de pago
		if (TipoResultadoEnum.OK.getDescripcionMic().equals(respuestaInvocacion.getResultado().getDescripcionMic())
				|| TipoResultadoEnum.WRN.getDescripcionMic().equals(respuestaInvocacion.getResultado().getDescripcionMic())
				||	(TipoResultadoEnum.ERROR.getDescripcionMic().equals(respuestaInvocacion.getResultado().getDescripcionMic())
						  &&  Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equals(respuestaInvocacion.getCodigoError()))){
			
			medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			
			Traza.advertencia(getClass(), "Se realizo el cambio de tipo mensaje estado del medio de pago id:" + medioPago.getIdMedioPago() 
					+ " id busqueda cobrador: " + medioPago.getIdBusquedaRespuestaCobrador()
					+ " a " + medioPago.getTipoMensajeEstado().name().toString() + " correspondientes a la operacion id: " 
					+ medioPago.getTransaccion().getOperacionTransaccionFormateado());
			
			if(!esDiferenciaCambio){
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
				Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago id:" + medioPago.getIdMedioPago() 
						+ " id busqueda cobrador: " + medioPago.getIdBusquedaRespuestaCobrador()
						+ " a " + medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " 
						+ medioPago.getTransaccion().getOperacionTransaccionFormateado());
			}			
			
		} else if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(respuestaInvocacion.getResultado().getDescripcionMic())
				|| TipoResultadoEnum.NOK.getDescripcionMic().equals(respuestaInvocacion.getResultado().getDescripcionMic())){
			
			if(!esContracargo){
				medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO);
			} else {
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_CONTRACARGO_CONFIRMACION.equals(medioPago.getEstado())) {
					medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO_CONFIRMACION);
				} else{
					
					medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO);
				}
			}
			medioPago.setMensajeEstado("Cod Error: " + respuestaInvocacion.getCodigoError() + " | " +   
					respuestaInvocacion.getResultado() + " " + respuestaInvocacion.getDescripcionError());
			medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
			
			Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago id:" + medioPago.getIdMedioPago() 
					+ " a "	+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id:" + medioPago.getTransaccion().getOperacionTransaccionFormateado()
					+ " sistema: " + medioPago.getSistemaOrigen().getDescripcion()
					+ " tipo mensaje estado: " + medioPago.getTipoMensajeEstado().getDescripcion()
					+ " mensaje estado: " + medioPago.getMensajeEstado().toString())
					;
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 * @param cobrador
	 * @return
	 */
	private List<ShvCobMedioPago> listarMediosPagoProxFactura(ShvCobTransaccion transaccion, SistemaEnum cobrador){
		
		List<ShvCobMedioPago> lista = new ArrayList<ShvCobMedioPago>();
		
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			
			if(SistemaEnum.CALIPSO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso)){
				// Si tipo calipso agrego a la lista.
				lista.add(medioPago);
				
			}else if(SistemaEnum.MIC.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic)){
				// Si tipo MIC agrego a la lista.
				lista.add(medioPago);
			}
		}
		
		return lista;
	}
	
	/**
	 * 
	 */
	public Long buscarIdDescobroPorIdOperacionCobro(Long idOperacion) throws NegocioExcepcion{
		
		Long idOperacionDescobro=null;
		
		try{
			
			idOperacionDescobro = (Long) descobroDao.buscarIdDescobroPorIdOperacionCobro(idOperacion);
		
		} catch (PersistenciaExcepcion exc) {
			throw new NegocioExcepcion(exc.getMessage(),exc);
		}
		
		return idOperacionDescobro;
	}
	
	
	/***************************************************************************
	 * Envio mail y generacion de tarea
	 * SHV - Se le deberá dar aviso al usuario de la finalización 
	 * del proceso batch de reversión de cobro
	 * 
	 * SHV - Se deberá enviar un mail con el aviso de finalización correcta del proceso de reversión del cobro
	 * Destinatario: Analista dueño de la reversión de cobro
	 * En copia: Copropietario, analista del team comercial (si es único para todos los clientes incluidos en la operación)
	 * Asunto: Reversión de cobro finalizada: id operación - Empresa/CUIT/Nro de cliente/Razón Social 
	 * Cuerpo: listado de clientes incluidos en la reversión de cobro (Nro de cliente/Razón Social)
	 * Adjunto: archivo excel con la exportación de la reversión de cobro
	 * 
	 * SHV - La finalización con errores del proceso batch de reversión de cobro deberá generar 
	 * una tarea en la bandeja de entrada del dueño de la reversión de cobro
	 * Destinatario: Analista dueño de la reversión del cobro
	 * En copia: Copropietario, analista del team comercial (si es único para todos los clientes incluidos en la operación)
	 * Asunto: Nueva Tarea - Reversión de Cobro Imputada Parcialmente: id operación - Empresa/CUIT/Nro de cliente/Razón Social 
	 * Cuerpo: listado de clientes incluidos en la reversión de cobro (Empresa/CUIT/Nro de cliente/Razón Social)
	 * Adjunto: archivo excel con la exportación de la reversión del cobro
	 * 
	 * SHV - La finalización con error en la primera reversión del proceso batch de reversión 
	 * de cobro deberá generar una tarea en la bandeja de entrada del dueño de la reversión de cobro
	 * Destinatario: Analista dueño de la reversión del cobro
	 * En copia: Copropietario, analista del team comercial (si es único para todos los clientes incluidos en la operación)
	 * Asunto: Nueva Tarea - Reversión de Cobro Rechazada: id operación - Empresa/CUIT/Nro de cliente/Razón Social 
	 * Cuerpo: listado de clientes incluidos en la reversión de cobro (Empresa/CUIT/Nro de cliente/Razón Social)
	 * Adjunto: archivo excel con la exportación de la reversión del cobro
	 * 
	 * 
	 * Mail de Imputación Manual de reversión Pendiente
	 * 
	 * Destinatario: Grupo de Referentes de Caja o Grupo de Referentes de Operaciones Externas según corresponda
	 * En copia: N/A
	 * Asunto: Reversión de Cobro con Imputación Manual Pendiente: id operación - Empresa/CUIT/Nro de cliente/Razón Social
	 * Cuerpo: listado de clientes incluidos en el cobro (Empresa/CUIT/Nro de cliente/Razón Social)/ID de operación en sistema externo
	 * Adjunto: archivo adjunto en el cobro
	 * 
	 * ************************************************************************/
	@Override
	public void enviarMailyGenerarTarea(ShvCobDescobro descobro) throws NegocioExcepcion {
		try {

			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) descobroDao.buscarDatosImputacion(descobro.getOperacion().getIdOperacion());
			
			Long idCobroDelDescobro = descobro.getIdCobro();
			
			ShvCobCobroSimple cobroSimple =  cobroDao.buscarCobroSimplePorIdCobro(idCobroDelDescobro);
			Set<ShvCobEdCodigoOperacionExterna> listaCodigosOperacionesExternas = null;
			String codigosOperacionesExternas = Propiedades.MENSAJES_PROPIEDADES.getString("mail.id.codigo.operaciones.externas");
			
			//Se obtienen los codigos de operacion externa del cobro, y se los concatena en una cadena de string.
			if (cobroSimple.getIdCobro().equals(idCobroDelDescobro)) {
				listaCodigosOperacionesExternas = cobroOnlineDao.buscarCobroSimplificadoCodigoExternoOperacion(cobroSimple.getIdOperacion()).getCodigosOperacionesExternas();				
				for (ShvCobEdCodigoOperacionExterna codigoOperacionExterna : listaCodigosOperacionesExternas) {
					codigosOperacionesExternas += codigoOperacionExterna.getCodigoOperacionExterna();
					if(listaCodigosOperacionesExternas.iterator().hasNext()) {
						codigosOperacionesExternas += ", ";
					}
				}
			}
			
			String analista = listaDatos.get(0).getIdAnalista();
			String analistaNombre = listaDatos.get(0).getAnalista();
			String copropietario = listaDatos.get(0).getCopropietario();
			String nombreAdjunto = "";
			String nroCliente = listaDatos.get(0).getNumCliente().toString();
			String razonSocial = listaDatos.get(0).getRazonSocial();
			String cuit = listaDatos.get(0).getCuit();
			Long idOperacion = descobro.getOperacion().getIdOperacion();
			BigDecimal importe = listaDatos.get(0).getImporte();

			StringBuilder cuerpo = new StringBuilder(Mensajes.DESCOBROS_MAIL_CUERPO_CABECERA);
			String asunto = "";
			String mensajeAsunto = "";

			
			List<String> listaAnalistasTC = new ArrayList<String>();

			if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(descobro.getWorkflow().getEstado())) {
				cuerpo.append(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("mail.analista.nombre"),
					analistaNombre
				));
				for (ResultadoBusquedaDatosImputacion datos : listaDatos) {
					cuerpo.append(
						Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.cuerpo.detalle.imputacion.aplicacion.manual"),
						descobro.getEmpresa().getDescripcion(),
						Validaciones.isNullEmptyOrDash(datos.getCuit()) ? "" : datos.getCuit(),
						datos.getNumCliente().toString(),
						datos.getRazonSocial(),
						!Validaciones.isNullEmptyOrDash(codigosOperacionesExternas) ? codigosOperacionesExternas : ""
					));
					
					if (!Validaciones.isNullEmptyOrDash(datos.getSegmento())) {
						cuerpo.append(" / Segmento: ").append(datos.getSegmento());
					} else {
						cuerpo.append(" /Segmento: ");
					}
					cuerpo.append("</br>");
					if (!listaAnalistasTC.contains(datos.getAnalistaTeamComercial())) {
						listaAnalistasTC.add(datos.getAnalistaTeamComercial());
					}
				}
			} else {
				for (ResultadoBusquedaDatosImputacion datos : listaDatos) {
					cuerpo.append(
						Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.cuerpo.detalle.imputacion"),
							descobro.getEmpresa().getDescripcion(),
							Validaciones.isNullEmptyOrDash(datos.getCuit()) ? "" : datos.getCuit(),
							datos.getNumCliente().toString(),
							datos.getRazonSocial()
						));
					
					if (!listaAnalistasTC.contains(datos.getAnalistaTeamComercial())) {
						listaAnalistasTC.add(datos.getAnalistaTeamComercial());
					}
				}
			}
		
			/**
			 * Según el estado del cobro, crea la tarea y envía el mail correspondiente
			 */
			if (!Validaciones.isObjectNull(descobro.getOperacion().getIdOperacion())) {
				nombreAdjunto = "ID Operación Reversión Cobro " + Utilidad.rellenarCerosIzquierda(descobro.getOperacion().getIdOperacion().toString().trim(), 7);
			} else {
				nombreAdjunto = "ID Operación Reversión Cobro XXXXXXX";
			}
			if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(descobro.getWorkflow().getEstado())) {
				SistemaEnum sistemaDestino = null;
				SociedadEnum sociedadDestino = null;
				SistemaEnum sistemaDestinoN = null;
				SociedadEnum sociedadDestinoN = null;
				for (ShvCobTransaccion transaccion : descobro.getTransaccionesOrdenadas()) {
					if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
						sistemaDestino = transaccion.getTratamientoDiferencia().getSistemaOrigen();
						break;
					}
				}
				
				if (!Validaciones.isObjectNull(sistemaDestino)){
					asunto = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.asunto.imputacion.manual"),
							sistemaDestino.getDescripcion(),
							idOperacion.toString(),
							cuit,
							descobro.getEmpresa().getDescripcion(),
							nroCliente,
							razonSocial
						);
							
						this.crearTareaPendiente(descobro, analista, importe, this.retornaTareaAplicacionManual(descobro.getWorkflow().getEstado()) , nroCliente, razonSocial, cuerpo.toString(), true, asunto, nombreAdjunto, cuit, null, null, null);
				} else {
					
					for (ShvCobTransaccion transaccion: descobro.getTransaccionesOrdenadas()){
						
						if (!Validaciones.isObjectNull(transaccion.getFacturaTransaccion())){
							sociedadDestinoN = transaccion.getFacturaTransaccion().getSociedad();
						}
						
						if (!Validaciones.isObjectNull(transaccion.getFactura())){
							sistemaDestinoN = transaccion.getFactura().getSistemaOrigen();
						}
						
						if (!Validaciones.isObjectNull(sociedadDestinoN) && !Validaciones.isObjectNull(sistemaDestinoN)){
							if ((!sociedadDestinoN.equals(sociedadDestino)||!sistemaDestinoN.equals(sistemaDestino)) && !SistemaEnum.getEnumMICyCLP().contains(sistemaDestinoN)){
								sociedadDestino = sociedadDestinoN;
								sistemaDestino = sistemaDestinoN;
								asunto = Utilidad.reemplazarMensajes(
										Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.asunto.imputacion.manual"),
										sociedadDestino.getDescripcion()+"/"+sistemaDestino.getDescripcion(),
										idOperacion.toString(),
										cuit,
										descobro.getEmpresa().getDescripcion(),
										nroCliente,
										razonSocial
									);
											
									crearTareaPendiente(descobro, analista, importe, this.retornaTareaAplicacionManual(descobro.getWorkflow().getEstado()) , nroCliente, razonSocial, cuerpo.toString(), true, asunto, nombreAdjunto, cuit, null, sociedadDestino, sistemaDestino);
								}
						}
						
					}
				}

			} else {
				//Descobrado
				List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
				if (Estado.DES_DESCOBRADO.equals(descobro.getWorkflow().getEstado())) {

					TipoTratamientoDiferenciaEnum tipoTratamientoDiferenciaEnum = this.retornaTratamientoDiferenciaAplicacionManual(descobro);
					if (!Validaciones.isObjectNull(tipoTratamientoDiferenciaEnum)) {
						ShvCobCobroSimple cobro = cobroDao.buscarCobroSimplePorIdCobro(descobro.getIdCobro());
						finalizarTareaPendiente(cobro);	
					}
	//				Se busca el cobro para anular la TAREA de reversion de compensaciones SAP, la misma se genera por el batch de RecepcionNovedadesSAP con workflow de cobro
					mensajeAsunto = "descobro.mail.asunto.finalizada";
					List<ShvCobEdCobroAdjunto> listaAdjuntoAplManual = new ArrayList<ShvCobEdCobroAdjunto>();
					listaAdjuntoAplManual = cobroOnlineDao.buscarAdjuntosDelCobroOnline(descobro.getIdCobro());
					
					
					Adjunto adjunto = null;
					if (!Validaciones.isObjectNull(tipoTratamientoDiferenciaEnum)) {
						for (ShvCobEdCobroAdjunto shvAdjunto : listaAdjuntoAplManual) {
							
							if (MotivoAdjuntoEnum.APLICACION_MANUAL.equals(shvAdjunto.getPk().getMotivoAdjunto())){
								adjunto = new Adjunto(shvAdjunto.getPk().getDocumentoAdjunto().getArchivoAdjunto(),shvAdjunto.getPk().getDocumentoAdjunto().getNombreArchivo(), "");
								break;
							}
						}
						if (!Validaciones.isObjectNull(adjunto)) {
							listaAdjuntos.add(adjunto);
						}
					}
					
				} else if (Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(descobro.getWorkflow().getEstado())) {
					//Error en primer descobro
					crearTareaPendiente(descobro, analista, importe, TipoTareaEnum.DES_IMP_1ER_ERR, nroCliente, razonSocial, cuerpo.toString(), false);
					mensajeAsunto = "descobro.mail.asunto.parcialmente";
				} else if (Estado.DES_EN_ERROR.equals(descobro.getWorkflow().getEstado())) {
					crearTareaPendiente(descobro, analista, importe, TipoTareaEnum.DES_IMP_ERR, nroCliente, razonSocial, cuerpo.toString(), false);
					mensajeAsunto = "descobro.mail.asunto.rechazada";
				}
				
				asunto = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(mensajeAsunto),
						idOperacion.toString(),
						descobro.getEmpresa().getDescripcion(),
						cuit,
						nroCliente,
						razonSocial
					);
				//Envia el mail del resultado de la imputación
				enviarMail(listaDatos, analista, copropietario, listaAnalistasTC, asunto, cuerpo.toString(), descobro.getIdDescobro(), descobro.getIdCobro(), nombreAdjunto, listaAdjuntos);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	/**
	 * retorno la tarea aplicacion manual segun el estado
	 * @param estado
	 * @return
	 */
	private TipoTareaEnum retornaTareaAplicacionManual(Estado estado) {
		TipoTareaEnum tipoTarea = null;
		
		if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)){
			tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
		} else if (Estado.COB_ERROR_COBRO.equals(estado)){
			tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL;
		} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN;
			} 
		return tipoTarea;
	}
	/**
	 * Creo un cobro pendiente
	 * @param descobro
	 * @param analista
	 * @param importe
	 * @param tipoTarea
	 * @param nroCliente
	 * @param razonSocial
	 * @param cuerpo
	 * @param enviarMail
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendiente(ShvCobDescobro descobro, String analista, BigDecimal importe ,TipoTareaEnum tipoTarea, String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail) throws NegocioExcepcion {
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		
		TareaDto tarea = new TareaDto(
			descobro.getWorkflow().getIdWorkflow(),
			tipoTarea,
			descobro.getWorkflow().getFechaUltimaModificacion(),
			usuarioBatch,
			analista,
			TipoTareaGestionaEnum.DESCOBRO,
			TipoTareaEstadoEnum.PENDIENTE,
			nroCliente,
			razonSocial,
			Utilidad.formatCurrency(importe, false, true),
			Long.valueOf(descobro.getIdDescobro()),
			Long.valueOf(descobro.getIdDescobro()),
			descobro.getOperacion().getIdOperacion(),
			enviarMail,
			descobro.getMonedaOperacion().getSigno2()
		);
		tareaServicio.crearTareaResultadoImputacion(tarea);
	}
	/*
	 * 
	 */
	private void crearTareaPendiente(ShvCobDescobro descobro, String analista, BigDecimal importe ,TipoTareaEnum tipoTarea, String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail, String asunto, String nombreAdjunto, String cuit, Adjunto adjunto, SociedadEnum sociedad, SistemaEnum sistema) throws NegocioExcepcion {
//		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		
		TareaDto tarea = new TareaDto(
			descobro.getWorkflow().getIdWorkflow(),
			tipoTarea,
			descobro.getWorkflow().getFechaUltimaModificacion(),
			analista,
			null,
			TipoTareaGestionaEnum.DESCOBRO,
			TipoTareaEstadoEnum.PENDIENTE,
			nroCliente,
			razonSocial,
			Utilidad.formatCurrency(importe, false, true),
			Long.valueOf(descobro.getIdDescobro()),
			Long.valueOf(descobro.getIdDescobro()),
			descobro.getOperacion().getIdOperacion(),
			enviarMail,
			descobro.getMonedaOperacion().getSigno2()
		);
		tarea.setAsuntoMail(asunto);
		tarea.setCuit(cuit);
		tarea.setCuerpoMail(cuerpo);
		tarea.setSistema(sistema);
		tarea.setSociedad(sociedad);
		
		try {
			ByteArrayOutputStream outputStream = null;
			outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workbook = descobroServicio.exportarDescobro(descobro.getIdDescobro(), descobro.getIdCobro());
			workbook.write(outputStream);
	
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreAdjunto, "xls"));
			if (!Validaciones.isObjectNull(adjunto)) {
				listaAdjuntos.add(adjunto);
			}
			
			tarea.setAdjuntosMail(listaAdjuntos);
		} catch (IOException ex) {
			throw new NegocioExcepcion(ex.getMessage(),ex);
		}
		// TODO ACA DEBERIA BUSCAR EL ADJUNTO
		
		TipoTratamientoDiferenciaEnum tipoTratamientoDiferenciaEnum = this.retornaTratamientoDiferenciaAplicacionManual(descobro);
		if (!Validaciones.isObjectNull(tipoTratamientoDiferenciaEnum)) {
			if (
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tipoTratamientoDiferenciaEnum) ||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tipoTratamientoDiferenciaEnum)
			) {
				tarea.setPerfilAsignacion(TipoPerfilEnum.REFERENTE_CAJA.nombreLdap());
			} else {
				tarea.setPerfilAsignacion(TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.nombreLdap());
			}
		}
		
		PerfilFiltro filtro = new PerfilFiltro();
		
		if (!Validaciones.isObjectNull(sistema)&&!Validaciones.isObjectNull(sociedad)){
				
			filtro.setTipoTarea(tipoTarea.name());
			filtro.setEmpresa(descobro.getEmpresa().getIdEmpresa());
			filtro.setSistema(sistema.name());
			filtro.setSociedad(sociedad.name());
			filtro.setSegmento(descobro.getSegmento().getIdSegmento());
			tarea.setPerfilAsignacion(paramRespWfTareaServicioImpl.buscarPerfil(filtro).nombreLdap());
			
		}
		tareaServicio.crearTareaImputacionReversionCobroSistemaExterno(tarea);
	}
	
	/**
	 * Envio mail con adjunto Excel
	 * @param analista
	 * @param copropietario
	 * @param listaAnalistasTC
	 * @param asunto
	 * @param cuerpo
	 * @param idDescobro
	 * @param idCobro
	 * @throws NegocioExcepcion
	 */
	private void enviarMail(List<ResultadoBusquedaDatosImputacion> listaDatos, String analista, String copropietario, List<String> listaAnalistasTC, String asunto, String cuerpo, Long idDescobro, Long idCobro, String nombreAdjunto, List<Adjunto> adjuntos) throws NegocioExcepcion {
		
		String para =""; 
		String cc =""; 
		try {
			
		String conCopia = Utilidad.agregarAnalistaTeamComercialACopropietario(listaDatos, copropietario);
		
		if(!Validaciones.isNullOrEmpty(conCopia)){
			for(String usuario : conCopia.split(";")){
				UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(usuario);
				if(!Validaciones.isNullOrEmpty(usuarioLdap.getMail())){
					cc += usuarioLdap.getMail() + ";";
				}
			}
		}
		
		ByteArrayOutputStream outputStream = null;
		
			outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workbook = descobroServicio.exportarDescobro(idDescobro, idCobro);
			workbook.write(outputStream);
		
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreAdjunto, "xls"));
			if (Validaciones.isCollectionNotEmpty(adjuntos)) {
				listaAdjuntos.addAll(adjuntos);
			}

			UsuarioLdapDto usuarioLdapAnalista;
			if (!Validaciones.isNullOrEmpty(analista)) {
				usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(analista);
				if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
					para = usuarioLdapAnalista.getMail() + ";";
				}
			}
			
			Mail mail = new Mail(para,cc,asunto,new StringBuffer(cuerpo));
			mail.setAdjuntos(listaAdjuntos);
			mailServicio.sendMail(mail);
			
		}catch (LdapExcepcion | IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void finalizarTareaPendiente(ShvCobCobroSimple cobro) throws NegocioExcepcion {
		
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		
		tareaServicio.finalizarTarea(TipoTareaEnum.DES_COMP_PEND, cobro.getWorkflow().getIdWorkflow() , new Date(), usuarioBatch, null);
	}
	
	@Override
	public void enviarMailyFinalizarTareaAplicacionManual(ShvCobDescobro descobro, SociedadEnum sociedad, SistemaEnum sistema, UsuarioSesion userSesion) throws NegocioExcepcion {
		try {
			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) descobroDao.buscarDatosImputacion(descobro.getOperacion().getIdOperacion());

			String nombreAdjunto = "";
			String nroCliente = listaDatos.get(0).getNumCliente().toString();
			String razonSocial = listaDatos.get(0).getRazonSocial();
			String cuit = listaDatos.get(0).getCuit();
			
			String asunto = "";
			String asuntoDescriptor = "";
			String idOperacion = descobro.getOperacion().getIdOperacion().toString();
			
			if (!Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefCaja())) {
				asuntoDescriptor = "descobro.mail.cuerpo.confirmacion.manual.Reversion.caja";
				
			} else if (!Validaciones.isNullEmptyOrDash(descobro.getUsuarioAprobAplicacionManualRefOperacionesExternas())) {
				asuntoDescriptor = "descobro.mail.cuerpo.confirmacion.manual.Reversion.opext";
			}
			
			asunto = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString(asuntoDescriptor),
				descobro.getEmpresa().getDescripcion(),
				idOperacion,
				nroCliente,
				cuit,
				razonSocial
			);

			StringBuffer cuerpoMail = new StringBuffer();
			cuerpoMail.append( Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.cuerpo.confirmacion.manual.detalle"),
				descobro.getEmpresa().getDescripcion(),
				idOperacion
			));

			
			List<String> listaAnalistasTC = new ArrayList<String>();
			for (ResultadoBusquedaDatosImputacion resultadoBusquedaDatosImputacion : listaDatos) {
				cuerpoMail.append( Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.cuerpo.confirmacion.manual.detalle.cliente"),
					resultadoBusquedaDatosImputacion.getCuit(),
					resultadoBusquedaDatosImputacion.getNumCliente().toString(),
					resultadoBusquedaDatosImputacion.getRazonSocial()
				));
				if (!listaAnalistasTC.contains(resultadoBusquedaDatosImputacion.getAnalistaTeamComercial())) {
					listaAnalistasTC.add(resultadoBusquedaDatosImputacion.getAnalistaTeamComercial());
				}
			}
			
			String observacion = this.obtenerObseHistorial(descobro);
			if (!Validaciones.isNullOrEmpty(observacion)) {
				cuerpoMail.append(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("descobro.mail.cuerpo.confirmacion.manual.detalle.observaciones"),
					Utilidad.formateadoMail(
						observacion
				)));
			}
			
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.DES_CONFIRMA_APL_MAN,sociedad,sistema, descobro.getWorkflow().getIdWorkflow(), new Date(), userSesion.getIdUsuario(), null, TipoAccionEnum.CONFIRMADA, descobro.getObservacion());
			
			/**
			 * Según el estado del cobro, crea la tarea y envía el mail correspondiente
			 */
			if (!Validaciones.isObjectNull(descobro.getOperacion().getIdOperacion())) {
				nombreAdjunto = "ID Operación Reversión Cobro " + Utilidad.rellenarCerosIzquierda(descobro.getOperacion().getIdOperacion().toString().trim(), 7);
			} else {
				nombreAdjunto = "ID Operación Reversión Cobro XXXXXXX";
			}

			enviarMail(listaDatos, descobro.getIdAnalista(), descobro.getIdCopropietario(), listaAnalistasTC, asunto, cuerpoMail.toString(), descobro.getIdDescobro(), descobro.getIdCobro(), nombreAdjunto, null);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public String obtenerObseHistorial(ShvCobDescobro descobro) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);

			List<ShvWfWorkflowEstadoHist> historial = descobro.getWorkflow().getListaWorkflowEstadoHistOrdenadaPorFecha();

			//creo esta variable para no procesar el estado 'COB_PROCESO' varias veces
			boolean cobroProceso = false;
			for (ShvWfWorkflowEstadoHist historico : historial) {
				
				if (!Estado.DES_PEND_PROCESAR_MIC.equals(historico.getEstado())
					&& !Estado.DES_PENDIENTE_MIC.equals(historico.getEstado())){
					
					if (!Estado.DES_DESCOBRO_PROCESO.equals(historico.getEstado())){
						str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
						
					} else
					if (Estado.DES_DESCOBRO_PROCESO.equals(historico.getEstado()) && !cobroProceso){
						str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
						cobroProceso = true;
					}
					
				}
			}

			if (!Validaciones.isObjectNull(descobro.getWorkflow().getShvWfWorkflowEstado())) {
				if (descobro.getWorkflow().getShvWfWorkflowEstado().iterator().hasNext()) {
//					if (str.length() > 0) {
//						str.append(Constantes.RETORNO_HTML);
//					}
					str.append(
						this.returnObservacion(
							descobro.getWorkflow().getShvWfWorkflowEstado().iterator().next(),
							usuarioBatch,
							usuarioNombreBatch
					));
				}
			}
			
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e); 
		}
		return str.toString();
	}
	
	private String returnObservacion(ShvWfWorkflowEstado wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
				wfEstado.getDatosModificados(),
				wfEstado.getFechaModificacion(),
				wfEstado.getUsuarioModificacion(),
				usuarioBatch,
				usuarioNombreBatch
				);
	}
	private String returnObservacion(ShvWfWorkflowEstadoHist wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
				wfEstado.getDatosModificados(),
				wfEstado.getFechaModificacion(),
				wfEstado.getUsuarioModificacion(),
				usuarioBatch,
				usuarioNombreBatch
				);
	}
	
	private String returnObservacion(String datosModificados, Date fecha, String usuarioModificacion, String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
			StringBuffer str = new StringBuffer();
			datosModificados = Utilidad.mostrarDatosModificados(
				datosModificados,
				Utilidad.DATOS_MODIFICADOS_SOLO_DATOS
			);

			if (!Validaciones.isNullOrEmpty(datosModificados)) {
				str.append(Utilidad.formatDateAndTimeFull(fecha));
				str.append(" (");
				if (usuarioBatch.equalsIgnoreCase(usuarioModificacion)) {
					str.append(usuarioBatch);
					str.append(") ");
					str.append(usuarioNombreBatch);
				} else {
					UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(usuarioModificacion);
					if (usuarioLdapAnalista != null) {
						str.append(usuarioLdapAnalista.getTuid());
						str.append(") ");
						str.append(usuarioLdapAnalista.getNombreCompleto());
					}
				}
				str.append(Constantes.RETORNO_HTML);
				if (!Validaciones.isNullOrEmpty(datosModificados)) {
					str.append(datosModificados);
				}
				str.append(Constantes.RETORNO_HTML);
			}
			return str.toString();
		}
	
	/*******************************************************************************************
	 * Getters & Setters
	 *******************************************************************************************/
	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}

	public IWorkflowDescobros getWorkflowDescobros() {
		return workflowDescobros;
	}

	public void setWorkflowDescobros(IWorkflowDescobros workflowDescobros) {
		this.workflowDescobros = workflowDescobros;
	}

	public IDescobroServicio getDescobroServicio() {
		return descobroServicio;
	}

	public void setDescobroServicio(IDescobroServicio descobroServicio) {
		this.descobroServicio = descobroServicio;
	}
}
