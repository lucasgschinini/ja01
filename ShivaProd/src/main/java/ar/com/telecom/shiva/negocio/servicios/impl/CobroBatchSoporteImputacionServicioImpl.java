package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobTransaccion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.mapeos.MicRespuestaADCMapeador;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumentoMic;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Documento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.InfoAdicionalMedPagNoComisionables;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Transaccion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.DocumentoSalida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.ResultadoApropiacionDocumento;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.batch.ImputacionCobrosBatchRunner;
import ar.com.telecom.shiva.negocio.batch.bean.SaldoADescontarImputacionBatch;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParamRespWfTareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacionSap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.impl.TareaDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCompensacionLiquidoProductoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCompensacionProveedorSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoNotaCreditoCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoNotaCreditoMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoShivaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;

public class CobroBatchSoporteImputacionServicioImpl extends Servicio implements ICobroBatchSoporteImputacionServicio{

	public CobroBatchSoporteImputacionServicioImpl() {
		
	}
	@Autowired 
	ICobroOnlineDao cobroOnlineDao;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	@Autowired 
	IGenericoDao genericoDao;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired 
	private ICobroDao cobroDao;
	
	@Autowired 
	private IClienteDeimosServicio deimosServicio;
	
	@Autowired 
	private IWorkflowCobros workflowCobros;
	
	@Autowired
	private ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;
	
	@Autowired
	private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired 
	private ITareaServicio tareaServicio;
	
	@Autowired 
	private ICobroOnlineServicio cobroOnlineServicio;

	@Autowired 
	private IValorDao valorDao;
	
	@Autowired 
	private IScardServicio scardServicio;
	
	@Autowired
	private ICobroBatchServicio cobroBatchServicio;
	
	@Autowired
	private MailServicio mailServicio;
	
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	
	@Autowired
	private ICobroBatchSoporteImputacionContabilidadServicio cobroBatchSoporteImputacionContabilidadServicio;
	
	@Autowired
	private MicRespuestaADCMapeador micRespuestaADCMapeoJMS;

	@Autowired
	private IParamRespWfTareaServicio paramRespWfTareaServicio;
	
	@Autowired
	private ParamRespWfTareaServicioImpl paramRespWfTareaServicioImpl;
	
	@Autowired
	private TareaDaoImpl tareaDaoImpl;
	
	/**
	 * Busco si existe el mensaje pendiente o enviado para una transaccion
	 * @param mensajeMIC
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean puedeEnviarMensaje(Long idOperacion, Integer idTransaccion, Integer numeroTransaccion, 
			SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion) throws NegocioExcepcion, ReintentoExcepcion {
		
		MensajeServicioEnum servicio = getMensajeServicio(cobrador, tipoInvocacion);
		
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
					String mensaje = TipoResultadoEnum.ERROR + "-Se ha finalizado el proceso del reintento pues se ha producido inconvenientes en el servicio de mensajeria";
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "-" + mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion,cobrador, tipoInvocacion, mensaje);
				}
				
				if (MensajeEstadoEnum.REC_ERROR.equals(mensajeDto.getEstado())) {
					mensajeriaTransaccionServicio.cancelarMensaje(mensajeDto);
					String mensaje = TipoResultadoEnum.ERROR + "-Se ha finalizado el proceso del reintento pues se ha encontrado errores en el mensaje recibido";
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "-" + mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, cobrador, tipoInvocacion, mensaje);
				}
				
				if (MensajeEstadoEnum.COMPLETADO.equals(mensajeDto.getEstado())) {
					mensajeriaTransaccionServicio.cancelarMensaje(mensajeDto);
					String mensaje = TipoResultadoEnum.ERROR + "-Se ha finalizado el proceso del reintento pues se ha producido error de sistema";
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "-" + mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, cobrador, tipoInvocacion, mensaje);
				}
				
				if (MensajeEstadoEnum.CANCELADO.equals(mensajeDto.getEstado())) {
					String mensaje = TipoResultadoEnum.ERROR + "-Se ha finalizado el proceso del reintento pues se ha producido inconvenientes en el servicio de mensajeria";
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "-" + mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, cobrador, tipoInvocacion, mensaje);
				}
				
				if (MensajeEstadoEnum.NORECIBIDO.equals(mensajeDto.getEstado())) {
					String mensaje = TipoResultadoEnum.ERROR + "-Se ha finalizado el proceso del reintento pues se ha encontrado errores en el mensaje recibido";
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "-" + mensaje);
					throw new ReintentoExcepcion(idOperacion, idTransaccion, cobrador, tipoInvocacion, mensaje);
				}
				
			} // fin Mayor Cant Reintentos
			else {
				if (SistemaEnum.CALIPSO.equals(cobrador)) {
					//Calipso - Sincronico y puedo reenviar el mensaje pero reutilizando el registro existente
					
					return reenviarMensajeAlCobrador(cobrador,mensajeDto,idOperacionTransaccion,servicio);
					
				} else if (SistemaEnum.DEIMOS.equals(cobrador)) {
					
					return reenviarMensajeAlCobrador(cobrador,mensajeDto,idOperacionTransaccion,servicio);
					
				} else if (SistemaEnum.SAP.equals(cobrador)) {
					
					return reenviarMensajeAlCobrador(cobrador,mensajeDto,idOperacionTransaccion,servicio);
				
				} else{
					
					//MIC - Asincronico
					CobMensajeriaTransaccionDto dtoRespuesta = (CobMensajeriaTransaccionDto) 
								mensajeriaTransaccionServicio.buscarUltimoMensajeRespuesta(idOperacion);

					if (dtoRespuesta != null 
							&& !Validaciones.isNullOrEmpty(dtoRespuesta.getRespuestaRecibida())) 
					{
						if (MensajeServicioEnum.MIC_CONFIRMACION.equals(servicio)
								|| MensajeServicioEnum.MIC_DESAPROPIACION.equals(servicio)) {
							if (dtoRespuesta.getIdTransaccion() == null 
									&& mensajeDto.getIdTransaccion() == null) { 
								
								//Puede reenviar el mensaje
								String fechaUltimoEnvio = Utilidad.formatDateAndTimeFull(mensajeDto.getFechaEnvio());
								String fechaRecepcion = Utilidad.formatDateAndTimeFull(dtoRespuesta.getFechaRecepcion());
								Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "- se va a reenviar al MIC ya que "
										+ " en la corrida anterior no se proceso la respuesta recibida en la fecha: " + fechaRecepcion 
										+ " Tipo Mensaje: " + mensajeDto.getServicio() + " - Estado Mensaje: " + mensajeDto.getEstado()
										+ " - Fecha Ultimo Envio: "+ fechaUltimoEnvio  + "- Cantidad Reintentos: "+ mensajeDto.getCantReintentos());
								
								return true;
							}	
						} else {
							if (MensajeServicioEnum.MIC_APROP_DEUDA.equals(servicio)
									|| MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP.equals(servicio)
									|| MensajeServicioEnum.MIC_APROP_MP.equals(servicio)
									|| MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO.equals(servicio)
									|| MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO.equals(servicio)
									|| MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES.equals(servicio)) {
								
								if (dtoRespuesta.getIdTransaccion() != null && mensajeDto.getIdTransaccion() != null
										&& dtoRespuesta.getIdTransaccion().compareTo(mensajeDto.getIdTransaccion()) == 0) {
									
									//Puede reenviar el mensaje
									String fechaUltimoEnvio = Utilidad.formatDateAndTimeFull(mensajeDto.getFechaEnvio());
									String fechaRecepcion = Utilidad.formatDateAndTimeFull(dtoRespuesta.getFechaRecepcion());
									Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "- se va a reenviar al MIC ya que "
											+ " en la corrida anterior no se proceso la respuesta recibida en la fecha: " + fechaRecepcion 
											+ " Tipo Mensaje: " + mensajeDto.getServicio() + " - Estado Mensaje: " + mensajeDto.getEstado()
											+ " - Fecha Ultimo Envio: "+ fechaUltimoEnvio  + "- Cantidad Reintentos: "+ mensajeDto.getCantReintentos());
									
									return true;
								}
							}
						}
					}
					
					//Decimos que no hagamos nada ya que se encuentra todavia en ejecucion de la logica de reintentos (NO ENVIAR MENSAJE)
					// ya que tenemos la logica (2 Tarea cuya su funcion es reenviar mensajes hasta obtener alguna respuesta por parte de MIC)
					// o ya fue respondida
					
					String fechaUltimoEnvio = Utilidad.formatDateAndTimeFull(mensajeDto.getFechaEnvio());
					Traza.advertencia(getClass(), servicio.name() + "-" + idOperacionTransaccion + "- No se va a reenviar al MIC ya que se encuentra en el proceso de reintentos " 
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
	 * 
	 * @param shvCobTransaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean apropiacionDeimos(ShvCobTransaccionSinOperacion shvCobTransaccion) throws NegocioExcepcion{
		
		boolean resultadoApropiacion = true;
		EntradaDeimosApropiacionWS entradaDeimosApropiacion = new EntradaDeimosApropiacionWS();
		
		//Seteo Numero Operacion y el modo operacion : "N"-> imputación
		entradaDeimosApropiacion.setIdOperacionShiva(shvCobTransaccion.getIdOperacion());
		entradaDeimosApropiacion.setModoOperacion(SiNoEnum.NO);
		
		List<Documento> listaDocumentos = new ArrayList<Documento>();
		
		/**Tomo la factura de la transaccion y pregunto si hay que apropiar en Deimos **/
		
		ShvCobFacturaSinOperacion factura = shvCobTransaccion.getFactura();
		
		boolean apropiarFactura = false;
		boolean apropiarMediosPago= false;
		
		MonedaEnum monedaOperacion = null;
		
		if (!Validaciones.isObjectNull(factura) && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
				&& SiNoEnum.SI.equals(factura.getMigradoDeimos())){
			//Cargo los datos de CALIPSO O MIC y los agrego a la lista de documentos a consultar a DEIMOS
			
			apropiarFactura = true;
			monedaOperacion = factura.getMonedaImporteCobrar();
			
			Documento doc = new Documento();
			doc.setEmpresa(EmpresaEnum.TA);
			doc.setImporte(factura.getImporteCobrar().setScale(2, BigDecimal.ROUND_HALF_UP));
			
			if (factura instanceof ShvCobFacturaCalipsoSinOperacion) {
		
				doc.setSistema(SistemaEnum.CALIPSO);
				
				// Si el documento que vamos a apropiar tiene una moneda que difiere de la moneda del cobro, debemos enviar como importe el
				// importe imputado en pesos a fecha de emisión
				if (!monedaOperacion.equals(((ShvCobFacturaCalipsoSinOperacion) factura).getMoneda())) {
					BigDecimal importePesificadoFechaEmision = ((ShvCobFacturaCalipsoSinOperacion) factura).getImporteAplicadoAFechaEmisionMonedaPesos().setScale(2, BigDecimal.ROUND_HALF_UP); 
					doc.setImporte(importePesificadoFechaEmision);
				
				// Si es una operación en dolares (u otra moneda) donde no existe diferencia de moneda (moneda operacion = moneda de documento)
				// debo enviar el importe pesificado a fecha de emisión
				} else if (monedaOperacion.equals(((ShvCobFacturaCalipsoSinOperacion) factura).getMoneda()) && !MonedaEnum.PES.equals(monedaOperacion)) {
					BigDecimal importePesificadoFechaEmision = (((ShvCobFacturaCalipsoSinOperacion) factura).getImporteCobrar().multiply(((ShvCobFacturaCalipsoSinOperacion) factura).getTipoCambio())).setScale(2, BigDecimal.ROUND_HALF_UP); 
					doc.setImporte(importePesificadoFechaEmision);
				}
				
				IdDocumento idDoc = new IdDocumento();
				idDoc.setClaseComprobante(factura.getClaseComprobante());
				idDoc.setNumeroComprobante(factura.getNumeroComprobante());
				idDoc.setSucursalComprobante(factura.getSucursalComprobante());
				idDoc.setTipoComprobante(factura.getTipoComprobante());
				doc.setIdDocumentoCalipso(idDoc);
				
			} else if (factura instanceof ShvCobFacturaMicSinOperacion){
				
				doc.setSistema(SistemaEnum.MIC);
				IdDocumentoMic idDocumentoMic = new IdDocumentoMic();
				idDocumentoMic.setNumeroReferenciaMic(((ShvCobFacturaMicSinOperacion) factura).getIdReferenciaFactura());
				doc.setIdDocumentoMic(idDocumentoMic);
			}
			
		
			listaDocumentos.add(doc);
		}

		/**Tomo los medios de pago apropiar**/

		Set<ShvCobMedioPagoSinOperacion> listaMedioPago = shvCobTransaccion.getMediosPago();
		
		if (Validaciones.isCollectionNotEmpty(listaMedioPago) && !existeMedioPagoEnError(shvCobTransaccion)){
			for (ShvCobMedioPagoSinOperacion medioPago : listaMedioPago){

				if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos()) 
						&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPago.getEstado())){
					
					apropiarMediosPago = true;
					monedaOperacion = medioPago.getMonedaImporte();
					Documento docMp = new Documento();
					docMp.setEmpresa(EmpresaEnum.TA);
					docMp.setImporte(medioPago.getImporte().setScale(2, BigDecimal.ROUND_HALF_UP));
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
						
						docMp.setSistema(SistemaEnum.CALIPSO);
						
						ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;

						// Si el documento que vamos a apropiar tiene una moneda que difiere de la moneda del cobro, debemos enviar como importe el
						// importe apropiado en pesos a fecha de emisión
						if (!monedaOperacion.equals(medioPago.getMoneda())) {
							BigDecimal importePesificadoFechaEmision = medioPagoNotaCreditoCalipso.getImporteAplicadoAFechaEmisionMonedaPesos().setScale(2, BigDecimal.ROUND_HALF_UP);
							docMp.setImporte(importePesificadoFechaEmision);

						// Si es una operación en dolares (u otra moneda) donde no existe diferencia de moneda (moneda operacion = moneda de documento)
						// debo enviar el importe pesificado a fecha de emisión
						} else if (monedaOperacion.equals(medioPago.getMoneda()) && !MonedaEnum.PES.equals(monedaOperacion)) {
							BigDecimal importePesificadoFechaEmision = (medioPagoNotaCreditoCalipso.getImporte().multiply(medioPagoNotaCreditoCalipso.getTipoCambio())).setScale(2, BigDecimal.ROUND_HALF_UP);
							docMp.setImporte(importePesificadoFechaEmision);
						}
						
						IdDocumento idDoc = new IdDocumento();
						idDoc.setClaseComprobante(medioPagoNotaCreditoCalipso.getClaseComprobante());
						idDoc.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
						idDoc.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
						idDoc.setTipoComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante());
						docMp.setIdDocumentoCalipso(idDoc);
						
					} else if (medioPago instanceof ShvCobMedioPagoCTASinOperacion){
						
						docMp.setSistema(SistemaEnum.CALIPSO);
						ShvCobMedioPagoCTASinOperacion medioPagoCTA 
							= (ShvCobMedioPagoCTASinOperacion)medioPago;
						IdDocumento idDoc = new IdDocumento();
						idDoc.setClaseComprobante(medioPagoCTA.getClaseComprobante());
						idDoc.setNumeroComprobante(medioPagoCTA.getNroComprobante());
						idDoc.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
						idDoc.setTipoComprobante(medioPagoCTA.getTipoComprobante());
						docMp.setIdDocumentoCalipso(idDoc);
						
					} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoMicSinOperacion){

						docMp.setSistema(SistemaEnum.MIC);
						ShvCobMedioPagoNotaCreditoMicSinOperacion medioPagoNotaCreditoMic 
							= (ShvCobMedioPagoNotaCreditoMicSinOperacion)medioPago;
						IdDocumentoMic idDocumentoMic = new IdDocumentoMic();
						idDocumentoMic.setNumeroReferenciaMic(medioPagoNotaCreditoMic.getNumeroNotaCredito());
						docMp.setIdDocumentoMic(idDocumentoMic);
						
					}

					InfoAdicionalMedPagNoComisionables infoAdicionalMedPagNoComisionables = new InfoAdicionalMedPagNoComisionables();
					infoAdicionalMedPagNoComisionables.setImporte(docMp.getImporte());
					infoAdicionalMedPagNoComisionables.setCodigoTipoMedioPago(new Long(medioPago.getTipoMedioPago().getIdTipoMedioPago()));
					docMp.setInfoAdicionalMedPagNoComisionables(infoAdicionalMedPagNoComisionables);
				
					listaDocumentos.add(docMp);
				}
			}
		}

		if (!apropiarFactura && !apropiarMediosPago){
			return resultadoApropiacion;
		}

		Transaccion transaccion = new Transaccion();
		transaccion.setIdTransaccion(shvCobTransaccion.getIdTransaccion());
		transaccion.setIdSecuencia(shvCobTransaccion.getNumeroTransaccion());
		transaccion.setListaDocumentos(listaDocumentos);
		entradaDeimosApropiacion.setTransaccion(transaccion);
		
		
		try {
			
			if (puedeEnviarMensaje(entradaDeimosApropiacion.getIdOperacionShiva(), entradaDeimosApropiacion.getTransaccion().getIdTransaccion(), 
					entradaDeimosApropiacion.getTransaccion().getIdSecuencia(), SistemaEnum.DEIMOS, TipoInvocacionEnum.$10)) 
			{
			
				Traza.advertencia(getClass(), "Se envia la apropiacion de la operacion id: "+entradaDeimosApropiacion.getIdOperacionShiva()
						+ " a Deimos ");
				
				SalidaDeimosApropiacionWS respuesta = deimosServicio.apropiarDocumento(entradaDeimosApropiacion);
				
				if (!Validaciones.isObjectNull(respuesta)){
					Traza.advertencia(getClass(), "El resultado de la respuesta de Deimos ("+entradaDeimosApropiacion.getIdOperacionShiva()+") "
							+ "fue: " + respuesta.getResultadoProcesamiento().getResultadoImputacion());
					
					resultadoApropiacion = setearDatosApropiacionDeimos(respuesta,factura,resultadoApropiacion,listaMedioPago);
					
				}
			}
			
		}catch (NegocioExcepcion e){
			resultadoApropiacion = false;
			throw new NegocioExcepcion(e.getMessage());
		} catch (ReintentoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		return resultadoApropiacion;
		
	}
	
	/**
	 * Me devuelve el tipo de servicio de acuerdo a los criterios
	 * @param cobrador
	 * @param tipoInvocacion
	 * @return
	 * @throws NegocioExcepcion
	 * 
	 * $01(1, "Apropiacion de deuda"),
	 * $02(2, "Apropiacion de medio de pago"),
	 * $03(3, "Apropiacion de deuda y medio de pago"),
	 * $04(4, "Desapropiacion de operacion"),
	 * $05(5, "Confirmacion de operacion");
	 * $06(6, "Cargo a Proxima Factura Debito"),
	 * $07(7, "Cargo a Proxima Factura Crédito"),
	 * $08(8, "Simulación de Intereses");
	 */
	public MensajeServicioEnum getMensajeServicio(SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion) throws NegocioExcepcion {
		MensajeServicioEnum servicio = null;
		
		switch (cobrador) {
			case MIC:
				switch (tipoInvocacion) {
					case $01:
						servicio = MensajeServicioEnum.MIC_APROP_DEUDA;
						break;
					case $02:
						servicio = MensajeServicioEnum.MIC_APROP_MP;
						break;
					case $03:
						servicio = MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP;
						break;
					case $04:
						servicio = MensajeServicioEnum.MIC_DESAPROPIACION;
						break;
					case $05:
						servicio = MensajeServicioEnum.MIC_CONFIRMACION;
						break;
					case $06:
						servicio = MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO;
						break;
					case $07:
						servicio = MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO;
						break;
					case $08:
						servicio = MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES;
						break;
					default:
						throw new NegocioExcepcion("Error - Tipo de invocacion no identificado");
				}
				break;
			case CALIPSO:
				switch (tipoInvocacion) {
					case $01:
						servicio = MensajeServicioEnum.CLP_APROP_DEUDA;
						break;
					case $02:
						servicio = MensajeServicioEnum.CLP_APROP_MP;
						break;
					case $03:
						servicio = MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP;
						break;
					case $04:
						servicio = MensajeServicioEnum.CLP_DESAPROPIACION;
						break;
					case $05:
						servicio = MensajeServicioEnum.CLP_CONFIRMACION;
						break;
					case $06:
						servicio = MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO;
						break;
					case $07:
						servicio = MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO;
						break;
					case $08:
						servicio = MensajeServicioEnum.CLP_GENERACION_CARGO_INTERES;
						break;
					default:
						throw new NegocioExcepcion("Error - Tipo de invocacion no identificado");
				}
				break;
			case DEIMOS:
				switch (tipoInvocacion) {
					case $10:
						servicio = MensajeServicioEnum.DEI_APROPIACION;
						break;
					case $11:
						servicio = MensajeServicioEnum.DEI_DESAPROPIACION;
						break;
					default:
						throw new NegocioExcepcion("Error - Tipo de invocacion no identificado");
				}
				break;
			case SAP:
				switch (tipoInvocacion){
					case $12:
						servicio = MensajeServicioEnum.SAP_REGISTRAR_COMPENSACION;
						break;
					case $13:
						servicio = MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR;
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
		
		//Puedo reenviar
		return true;
	}
	
	/**
	 * GENERA LOS CARGOS/REINTEGRO DE CALIPSO Y MIC
	 * @param transaccion
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion 
	 */
	public ShvCobTransaccionSinOperacion generarCargosOReintegro(ShvCobTransaccionSinOperacion transaccion, ShvWfWorkflow workflow) throws NegocioExcepcion, ReintentoExcepcion {
		
		//Listas para generacion de cargos
		List<ShvCobMedioPagoSinOperacion> listaMediosPagoProxFacturaMic = listarMediosPagoProxFactura(transaccion,SistemaEnum.MIC);
		List<ShvCobMedioPagoSinOperacion> listaMediosPagoProxFacturaCalipso = listarMediosPagoProxFactura(transaccion,SistemaEnum.CALIPSO);
		
		//Para reintegros
		ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia = transaccion.getTratamientoDiferencia();
		
		EntradaCalipsoCargosWS entradaCalipsoCargo = null;
		DetalleCargoEntradaCargosWs detalleCargo = null;
		
		String idOperacion = transaccion.getIdOperacion().toString();
		String idTransaccion = transaccion.getIdTransaccion().toString();
		String numeroTransaccion = transaccion.getNumeroTransaccion().toString();
		
		//Si hay para generar cargo en mic
		if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaMic)){
			
			ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion mpProxFacturaMic = null;
			
			for (ShvCobMedioPagoSinOperacion mp : listaMediosPagoProxFacturaMic){
				
				mpProxFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)mp;
				
			}
			cobroBatchSoporteImputacionMicServicio.generarCargoReintegroMic(mpProxFacturaMic,null,workflow);
			
			//Si hay para generar cargo en calipso	
		}else if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaCalipso)){
			
			entradaCalipsoCargo = new EntradaCalipsoCargosWS();
			entradaCalipsoCargo.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacion, 7));
			entradaCalipsoCargo.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(numeroTransaccion, 5));
			entradaCalipsoCargo.setIdTransaccion(idTransaccion);
			entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO);
			
			entradaCalipsoCargo.setModoOperacion(SiNoEnum.NO);
			entradaCalipsoCargo.setTipoOperacion(TipoOperacionCargoEnum.CARGO);
			entradaCalipsoCargo.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
			
			ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion mpProxFacturaCalipso = null;
			
			for (ShvCobMedioPagoSinOperacion mp : listaMediosPagoProxFacturaCalipso){
				
				mpProxFacturaCalipso = (ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)mp;
				
				detalleCargo = new DetalleCargoEntradaCargosWs();
				detalleCargo = setearDetalleCargoEntradaCargosWs(detalleCargo, mpProxFacturaCalipso, null);
				entradaCalipsoCargo.setDetalleCargo(detalleCargo);
				
			}
			
			cobroBatchSoporteImputacionCalipsoServicio.generarCargoReintegroCalipso(mpProxFacturaCalipso,null,entradaCalipsoCargo);
			
		} else if (!Validaciones.isObjectNull(tratamientoDiferencia)){
				
			//Si hay para generar reintegro en calipso
			if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
				
				entradaCalipsoCargo = new EntradaCalipsoCargosWS();
				entradaCalipsoCargo.setIdOperacion(Utilidad.rellenarCerosIzquierda(idOperacion, 7));
				entradaCalipsoCargo.setNumeroTransaccion(Utilidad.rellenarCerosIzquierda(numeroTransaccion, 5));
				entradaCalipsoCargo.setIdTransaccion(idTransaccion);
				entradaCalipsoCargo.setTipoMensaje(MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO);
				
				entradaCalipsoCargo.setModoOperacion(SiNoEnum.NO);
				entradaCalipsoCargo.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
				entradaCalipsoCargo.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				detalleCargo = new DetalleCargoEntradaCargosWs();
				detalleCargo = setearDetalleCargoEntradaCargosWs(detalleCargo, null, tratamientoDiferencia);
				entradaCalipsoCargo.setDetalleCargo(detalleCargo);
				
				cobroBatchSoporteImputacionCalipsoServicio.generarCargoReintegroCalipso(null,tratamientoDiferencia,entradaCalipsoCargo);
				
			} else {
				
				//Si hay para generar reintegro en MIC
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
					
					cobroBatchSoporteImputacionMicServicio.generarCargoReintegroMic(null,tratamientoDiferencia, workflow);
						
				}else {
					tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
					tratamientoDiferencia.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				}
			}
		}
		
		return transaccion;
	}
	
	/**
	 * 
	 * @param medioPago
	 * @param detalleCTAoNC
	 * @param idOperacion
	 */
public void setearEstadoMedioPago(ShvCobMedioPagoSinOperacion medioPago, DetalleCTAoNotaCredito detalleCTAoNC, Long idOperacion) {
		
		//Marca Migrado deimos
		setearMarcaDeimosAMediosPago(medioPago,detalleCTAoNC.getResultadoApropiacion().getResultado(),
				detalleCTAoNC.getResultadoApropiacion().getCodigoError());
		//Cambio de estado al medio de pago
		if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(detalleCTAoNC.getResultadoApropiacion().getResultado())
				||	(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(detalleCTAoNC.getResultadoApropiacion().getResultado())
						  &&  Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(detalleCTAoNC.getResultadoApropiacion() .getCodigoError()))
				|| TipoResultadoEnum.WRN.getDescripcionCalipso().equals(detalleCTAoNC.getResultadoApropiacion().getResultado())){
			medioPago.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
			medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			
			Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago a " 
					+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " + idOperacion);
			
		} else if (TipoResultadoEnum.NOK.getDescripcionCalipso().equals(detalleCTAoNC.getResultadoApropiacion().getResultado())
				|| TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(detalleCTAoNC.getResultadoApropiacion().getResultado())){
			
			medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR);

			StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
			detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
			detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
			detalleMensajeError.append(detalleCTAoNC.getResultadoApropiacion().getDescripcionError());
			
			medioPago.setMensajeEstado(detalleMensajeError.toString());
			
			Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago a " 
					+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " + idOperacion);
			
		}
	}
	

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion 
	 */
	
	public ShvCobCobro eliminarTransaccionesEnEstadoDiferenciaDeCambioSim(ShvCobCobro cobro) throws NegocioExcepcion {
		try {
			Set<ShvCobTransaccion> listaTransaccionesOrdenadas = cobro.getOperacion().getTransacciones();
			Set<ShvCobTransaccion> listaTransaccionesActualizada = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenSimulacionShvCobTransaccion());
	
			for (ShvCobTransaccion transaccion : listaTransaccionesOrdenadas) {
				if (!EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())){
					
					listaTransaccionesActualizada.add(transaccion);
			
				}
			}
			
			cobro.getOperacion().getTransacciones().clear();
			cobro.getOperacion().getTransacciones().removeAll(listaTransaccionesOrdenadas);
			cobro.getOperacion().getTransacciones().addAll(listaTransaccionesActualizada);
			cobro = cobroDao.modificar(cobro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return cobro;
		
	}

	
	public ShvCobMedioPago clonarListaDeClientesMedioPagoUsuario(ShvCobMedioPago medioPagoOrigen,ShvCobMedioPago medioPagoDestino) throws Exception {

		if (medioPagoOrigen instanceof ShvCobMedioPagoUsuario) {
			
			Set<ShvCobMedioPagoCliente> listaClientesMedioPagoDestino = new HashSet<ShvCobMedioPagoCliente>();
			for (ShvCobMedioPagoCliente clienteMedioPagoOrigen : ((ShvCobMedioPagoUsuario) medioPagoOrigen).getListaMedioPagoClientes()) {
				ShvCobMedioPagoCliente clienteMedioPagoDestino = new ShvCobMedioPagoCliente();// (ShvCobMedioPagoCliente) Utilidad.clonarObjeto(clienteMedioPagoOrigen);
				clienteMedioPagoDestino.setIdClienteLegado(clienteMedioPagoOrigen.getIdClienteLegado());
				clienteMedioPagoDestino.setMedioPagoUsuario((ShvCobMedioPagoUsuario) medioPagoDestino);
				listaClientesMedioPagoDestino.add(clienteMedioPagoDestino);
			}
			((ShvCobMedioPagoUsuario) medioPagoDestino).setListaMedioPagoClientes(listaClientesMedioPagoDestino);
		}
		
		return medioPagoDestino;
	}
	
	/**
	 * Retorna una lista con todos los Medios de Pago de tipo que coincida con el parametro cobrador
	 * pertenecientes a la transaccion.
	 * 
	 * @param transaccion
	 * @param cobrador (MIC/CALIPSO)
	 * @return
	 */
	@Override
	public List<ShvCobMedioPagoSinOperacion> listarMediosPago(ShvCobTransaccionSinOperacion transaccion, SistemaEnum cobrador){
		List<ShvCobMedioPagoSinOperacion> lista = new ArrayList<ShvCobMedioPagoSinOperacion>();
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(SistemaEnum.CALIPSO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion)){
				// Si tipo calipso agrego a la lista.
					lista.add(medioPago);
			}else{
				if(SistemaEnum.MIC.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoMicSinOperacion)){
					// Si tipo MIC agrego a la lista.
						lista.add(medioPago);
				}else{
					if(SistemaEnum.SHIVA.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoShivaSinOperacion)){
						// Si tipo SHIVA agrego a la lista.
							lista.add(medioPago);
					} else {
						if(SistemaEnum.USUARIO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoUsuarioSinOperacion)){
							// Si tipo USUARIO agrego a la lista.
							lista.add(medioPago);
						}
					}
				}
			}
		}
		return lista;
	}
	
	
	/**
	 * Verifica si existe al menos un Medio de pago en estado ERROR.
	 * @param cobro
	 * @return
	 */
	private Boolean existeMedioPagoEnError(ShvCobTransaccionSinOperacion transaccion) {
		for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()) {
			if(medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion 
					|| medioPago instanceof ShvCobMedioPagoMicSinOperacion
					|| medioPago instanceof ShvCobMedioPagoShivaSinOperacion
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion
					|| medioPago instanceof ShvCobMedioPagoCompensacionProveedorSinOperacion
					|| medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProductoSinOperacion){
				if(EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())
						||EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstadoDeimos())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param respuesta
	 * @param factura
	 * @param resultadoApropiacion
	 * @param listaMedioPago
	 * @return
	 */
	private boolean setearDatosApropiacionDeimos(SalidaDeimosApropiacionWS respuesta, ShvCobFacturaSinOperacion factura, boolean resultadoApropiacion,
			Set<ShvCobMedioPagoSinOperacion> listaMedioPago) {

		
		List<Resultado> listaResultados = respuesta.getListaResultados();
		
		for (Resultado resultado : listaResultados){
			DocumentoSalida documento = resultado.getDocumento();
			
			if (SistemaEnum.CALIPSO.equals(documento.getSistema())){
				
				if (factura instanceof ShvCobFacturaCalipsoSinOperacion){
					ShvCobFacturaCalipsoSinOperacion facturaCalipso = (ShvCobFacturaCalipsoSinOperacion)factura;
					//Verifico si el documento actual es la respuesta de la apropiacion de la factura
					if(documento.getIdDocumentoCalipso().getClaseComprobante().equals(facturaCalipso.getClaseComprobante())
							&& documento.getIdDocumentoCalipso().getNumeroComprobante().equals(facturaCalipso.getNumeroComprobante())
							&& documento.getIdDocumentoCalipso().getSucursalComprobante().equals(facturaCalipso.getSucursalComprobante())
							&& documento.getIdDocumentoCalipso().getTipoComprobante().equals(facturaCalipso.getTipoComprobante())){
						
						//Seteo el resultado de la apropiacion
						if (estadoApropiacionDocumentoDeimos(resultado.getResultadoApropiacionDocumento())){
							facturaCalipso.setEstadoDeimos(EstadoFacturaMedioPagoEnum.APROPIADA);
						}else{
							facturaCalipso.setEstadoDeimos(EstadoFacturaMedioPagoEnum.ERROR);
							
							StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
							detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
							detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
							detalleMensajeError.append(resultado.getResultadoApropiacionDocumento().getDescripcionError());

							facturaCalipso.setMensajeEstado(detalleMensajeError.toString());
							resultadoApropiacion = false;
							
						}
					}
				}
			
				//Verifico si el documento actual es la respuesta de la apropiacion de algún medio de pago
				for (ShvCobMedioPagoSinOperacion medioPago : listaMedioPago){
					
					//Si el medio de pago es Nota de Credito Calipso
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
						
						ShvCobMedioPagoNotaCreditoCalipsoSinOperacion notaCredCalipso 
							= (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;
						
						if(documento.getIdDocumentoCalipso().getClaseComprobante().equals(notaCredCalipso.getClaseComprobante())
								&& documento.getIdDocumentoCalipso().getNumeroComprobante().equals(notaCredCalipso.getNroComprobante())
								&& documento.getIdDocumentoCalipso().getSucursalComprobante().equals(notaCredCalipso.getSucursalComprobante())
								&& documento.getIdDocumentoCalipso().getTipoComprobante().equals(notaCredCalipso.getTipoComprobante())){
							
							//Seteo el resultado de la apropiacion
							if (estadoApropiacionDocumentoDeimos(resultado.getResultadoApropiacionDocumento())){
								medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.APROPIADA);
							}else{
								medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.ERROR);
								
								StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
								detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
								detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
								detalleMensajeError.append(resultado.getResultadoApropiacionDocumento().getDescripcionError());
								
								medioPago.setMensajeEstado(detalleMensajeError.toString());
								resultadoApropiacion = false;
							}
						}
						
					} else if (medioPago instanceof ShvCobMedioPagoCTASinOperacion){ //Si el medio de pago es CTA
						
						
						ShvCobMedioPagoCTASinOperacion medioPagoCTA 
							= (ShvCobMedioPagoCTASinOperacion)medioPago;
						if(documento.getIdDocumentoCalipso().getClaseComprobante().equals(medioPagoCTA.getClaseComprobante())
								&& documento.getIdDocumentoCalipso().getNumeroComprobante().equals(medioPagoCTA.getNroComprobante())
								&& documento.getIdDocumentoCalipso().getSucursalComprobante().equals(medioPagoCTA.getSucursalComprobante())
								&& documento.getIdDocumentoCalipso().getTipoComprobante().equals(medioPagoCTA.getTipoComprobante())){
							
							//Seteo el resultado de la apropiacion
							if (estadoApropiacionDocumentoDeimos(resultado.getResultadoApropiacionDocumento())){
								medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.APROPIADA);
							}else{
								medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.ERROR);
								
								StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
								detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
								detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
								detalleMensajeError.append(resultado.getResultadoApropiacionDocumento().getDescripcionError());
	
								medioPago.setMensajeEstado(detalleMensajeError.toString());
								resultadoApropiacion = false;
							}
						}
					}
				}
			} else {
				if (SistemaEnum.MIC.equals(documento.getSistema())){
					if (factura instanceof ShvCobFacturaMicSinOperacion){
						ShvCobFacturaMicSinOperacion facturaMic 
							= (ShvCobFacturaMicSinOperacion)factura;
						
						//Verifico si el documento actual es la respuesta de la apropiacion de la factura
						if(documento.getIdDocumentoMic().getNumeroReferenciaMic().equals(facturaMic.getIdReferenciaFactura())){
							
							//Seteo el resultado de la apropiacion
							if (estadoApropiacionDocumentoDeimos(resultado.getResultadoApropiacionDocumento())){
								factura.setEstadoDeimos(EstadoFacturaMedioPagoEnum.APROPIADA);
							}else{
								factura.setEstadoDeimos(EstadoFacturaMedioPagoEnum.ERROR);
								
								StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
								detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
								detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
								detalleMensajeError.append(resultado.getResultadoApropiacionDocumento().getDescripcionError());
								
								factura.setMensajeEstado(detalleMensajeError.toString());
								resultadoApropiacion = false;
							}
						}
					}
					
					for (ShvCobMedioPagoSinOperacion medioPago : listaMedioPago){
						
						//Si el medio de pago es Nota de Credito MIC
						if (medioPago instanceof ShvCobMedioPagoNotaCreditoMicSinOperacion){
							ShvCobMedioPagoNotaCreditoMicSinOperacion notaCredMic 
								= (ShvCobMedioPagoNotaCreditoMicSinOperacion)medioPago;
							
							if(documento.getIdDocumentoMic().getNumeroReferenciaMic().equals(notaCredMic.getNumeroNotaCredito())){
								
								//Seteo el resultado de la apropiacion
								if (estadoApropiacionDocumentoDeimos(resultado.getResultadoApropiacionDocumento())){
									medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.APROPIADA);
								}else{
									medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.ERROR);
									
									StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
									detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
									detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
									detalleMensajeError.append(resultado.getResultadoApropiacionDocumento().getDescripcionError());
									
									medioPago.setMensajeEstado(detalleMensajeError.toString());
									resultadoApropiacion = false;
								}
							}
						}
					}
				}
			}
		}
		
		return resultadoApropiacion;
	}
	
	
	/**
	 * 
	 * @param transaccion
	 * @param cobrador
	 * @return
	 */
	@Override
	public List<ShvCobMedioPagoSinOperacion> listarMediosPagoProxFactura(ShvCobTransaccionSinOperacion transaccion, SistemaEnum cobrador){
		
		List<ShvCobMedioPagoSinOperacion> lista = new ArrayList<ShvCobMedioPagoSinOperacion>();
		
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			
			if(SistemaEnum.CALIPSO.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)){
				// Si tipo calipso agrego a la lista.
				lista.add(medioPago);
				
			}else if(SistemaEnum.MIC.equals(cobrador) && (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)){
				// Si tipo MIC agrego a la lista.
				lista.add(medioPago);
			}
		}
		
		return lista;
	}
	
	/**
	 * 
	 * @param transaccion
	 * @param cobrador
	 * @return
	 */
	@Override
	public List<ShvCobMedioPago> listarMediosPagoProxFactura(ShvCobTransaccion transaccion, SistemaEnum cobrador){
		
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
	 * Setea la respuesta de calipso del medio de pago o factura, si hay que consultar a deimos, y setea los errores.
	 * Nota: solo uno de los 2 parametros(medio de pago o factura) debe ser not null
	 * @param listaMediosPagoAEnviar
	 * @param respuesta
	 */
	private void setearMarcaDeimosAMediosPago (ShvCobMedioPagoSinOperacion medioPago, 
			String resultado, String codError){
		
		//Si la respuesta es WRN, hay que consultar a deimos
		if (TipoResultadoEnum.WRN.getDescripcionCalipso().equals(resultado)
				&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(codError)){
			
			if (!Validaciones.isObjectNull(medioPago)){
				medioPago.setMigradoDeimos(SiNoEnum.SI);
			}
		}
	}
	
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
	 * Setea el objeto DetalleCargoEntradaCargosWs para cargos o reintegros (CALIPSO)
	 * @param detalleCargo
	 * @param mpProxFactura
	 * @param tratamientoDiferencia
	 * @return
	 */
	private DetalleCargoEntradaCargosWs setearDetalleCargoEntradaCargosWs(
			DetalleCargoEntradaCargosWs detalleCargo,
			ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion mpProxFactura,
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia){
		
		if(!Validaciones.isObjectNull(mpProxFactura)){
			detalleCargo.setAcuerdoFacturacion(mpProxFactura.getAcuerdoTrasladoCargo());
			detalleCargo.setImporte(mpProxFactura.getImporte()); 
			detalleCargo.setFechaDesde(mpProxFactura.getFecha());
			detalleCargo.setTipoMora(TipoMoraEnum.getEnumByName(mpProxFactura.getQueHacerConIntereses().getCodigoCalipsoApropiacion()));
			detalleCargo.setImporteBonificacionIntereses(mpProxFactura.getImporteBonificacionIntereses());
			detalleCargo.setOrigenCargo(mpProxFactura.getOrigenCargo());
			detalleCargo.setLeyendaFacturaCargo(mpProxFactura.getLeyendaFacturaCargo());
			detalleCargo.setLeyendaFacturaInteres(mpProxFactura.getLeyendaFacturaInteres());
			// Cobros Backlog - Envio la moneda del debito a prox
			detalleCargo.setMonedaCargo(mpProxFactura.getMoneda());
			
		}else {
			if(!Validaciones.isObjectNull(tratamientoDiferencia)){
				detalleCargo.setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoTrasladoCargo());
				detalleCargo.setImporte(tratamientoDiferencia.getImporte()); 
				detalleCargo.setFechaDesde(tratamientoDiferencia.getFechaTramiteReintegro());
				detalleCargo.setTipoMora(TipoMoraEnum.getEnumByName(tratamientoDiferencia.getQueHacerConIntereses().getCodigoCalipsoApropiacion()));
				detalleCargo.setImporteBonificacionIntereses(tratamientoDiferencia.getImporteBonificacionIntereses());
				detalleCargo.setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
				detalleCargo.setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
				detalleCargo.setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
				// Cobros Backlog - Envio la moneda del credito a prox
				detalleCargo.setMonedaCargo(tratamientoDiferencia.getMoneda());
			}
		}
		return detalleCargo;
		
	}
	
	/**
	 * Apropia los medios de pago Shiva y actualiza los estados de los mismos. Ademas le resta el saldo a los valores.
	 * @param listaMediosPagoAEnviar
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	public void apropiacionShiva(List<ShvCobMedioPagoSinOperacion> listaMediosPagoShiva, ShvCobTransaccionSinOperacion transaccion, String usuario) throws NegocioExcepcion, ReintentoExcepcion {
		
		ShvCobFacturaSinOperacion factura = transaccion.getFactura();
		List<Long> listaIdValores = new ArrayList<Long>();
		for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoShiva){
			ShvCobMedioPagoShivaSinOperacion medioPagoShiva = 
					(ShvCobMedioPagoShivaSinOperacion)medioPago;
			listaIdValores.add(medioPagoShiva.getIdValor());
		}
		
		List<ShvValValorSimplificado> listaValoresSimplificados = valorServicio.listarValoresSimplificados(listaIdValores);
		String descripError="";
		for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoShiva){
			
			for (ShvValValorSimplificado valor : listaValoresSimplificados){
				ShvCobMedioPagoShivaSinOperacion medioPagoShiva = (ShvCobMedioPagoShivaSinOperacion)medioPago;
				if (medioPagoShiva.getIdValor().equals(valor.getIdValor())){
					
					if (!Estado.VAL_DISPONIBLE.equals(valor.getWorkFlow().getEstado())){
						descripError =Propiedades.MENSAJES_PROPIEDADES.getString("error.cobros.apropiacion.shiva.estado");
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
						medioPago.setMensajeEstado(descripError);
						medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						return;
					}
					
					if (!Validaciones.isObjectNull(valor.getMotivo())
							&& (MotivoShivaEnum.VALOR_EN_GARANTIA.descripcion().equals(valor.getMotivo().getDescripcion())
							|| MotivoShivaEnum.VALOR_POR_RECLAMO_DE_FACTURACION.descripcion().equals(valor.getMotivo().getDescripcion()))){
					
						descripError =Propiedades.MENSAJES_PROPIEDADES.getString("error.cobros.apropiacion.shiva.motivo");
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
						medioPago.setMensajeEstado(descripError);
						medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						return;
					}
					
					
					SaldoADescontarImputacionBatch objSaldoADescontar = new SaldoADescontarImputacionBatch();
					objSaldoADescontar.setSaldoADescontar(medioPago.getImporte());
					objSaldoADescontar.setUsuarioModificacion(usuario);
					objSaldoADescontar.setIdOperacionIdTransaccion(transaccion.getOperacionTransaccionFicticioFormateado());
					
					String descripcionDebito= generarDescripcionDebito(factura,objSaldoADescontar, transaccion);
					
					objSaldoADescontar.setDescFactura(descripcionDebito);
					
					try{
						Traza.advertencia(getClass(), "Se envia la apropiacion del medio de pago Shiva id:" + medioPago.getIdMedioPago()
								+ "| importe: " + medioPago.getImporte());
						
						valor = valorServicio.actualizarSaldoPorApropiacion(valor, objSaldoADescontar);
						
						
//						int index = listaValoresSimplificados.indexOf(valor);
//						listaValoresSimplificados.set(index, valor);
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
						medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
						Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago Shiva id: " + + medioPago.getIdMedioPago() + " a " 
								+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " + transaccion.getIdOperacion());
						
					}catch (NegocioExcepcion e){
						Traza.error(getClass(), e.getMessage());
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
						medioPago.setMensajeEstado(e.getMessage());
						descripError = e.getMessage();
						medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						Traza.advertencia(getClass(), "Se realizo el cambio de estado del medio de pago Shiva id: " + + medioPago.getIdMedioPago() + " a " 
								+ medioPago.getEstado().descripcion() + " correspondientes a la operacion id: " + transaccion.getIdOperacion());
						return;
					}
					
					break;
				}
			}
		}
	}
	
	/**
	 * Genera la descripcion del debito.
	 * @param factura
	 * @param objSaldoADescontar
	 * @param transaccion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private String generarDescripcionDebito(ShvCobFacturaSinOperacion factura, SaldoADescontarImputacionBatch objSaldoADescontar, ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion{
		String descripcionDebito = "";
		
		if (!Validaciones.isObjectNull(factura)){
			StringBuffer origenFactura = new StringBuffer();
			origenFactura.append(factura.getSociedad() != null ? factura.getSociedad().getApocope() : "-").append(" - ");
			if(!SistemaEnum.NEGOCIO_NET.equals(factura.getSistemaOrigen())){
				origenFactura.append((factura.getSistemaOrigen() != null ? factura.getSistemaOrigen().name():"-"));
    		} else {
    			origenFactura.append((factura.getSistemaOrigen() != null ? factura.getSistemaOrigen().name().replace("_","."):"-"));
    		}
			
			 if( factura instanceof ShvCobFacturaSinOperacion){
				objSaldoADescontar.setOrigenFactura(origenFactura.toString());
			}
			 
			objSaldoADescontar.setIdFactura(valorServicio.generarIdFacturaParaMostrar(factura));
			
			descripcionDebito=" Id Factura: " +  valorServicio.generarIdFacturaParaMostrar(factura)
			+ " Origen de la Factura: " + objSaldoADescontar.getOrigenFactura();
		} else {
			TipoTratamientoDiferenciaEnum tratamiento = transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia();
			if (
				TipoTratamientoDiferenciaEnum.getEnumComboTratamientoDiferenciaCredito().contains(tratamiento) ||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tratamiento) ||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tratamiento)||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(tratamiento)||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(tratamiento)||
				TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(tratamiento)
			) {
				if (
					TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.equals(tratamiento) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tratamiento) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tratamiento)||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(tratamiento) ||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(tratamiento)||
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(tratamiento) 
				) {
					descripcionDebito=" " + tratamiento.getDescripcion();

				} else { 
					
					String numeroTramiteReintegro = "-";
					
					if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia().getNumeroTramiteReintegro())) {
						numeroTramiteReintegro = transaccion.getTratamientoDiferencia().getNumeroTramiteReintegro().toString();
					}
					
					descripcionDebito= " " + tratamiento.getDescripcion() + " - Numero de tramite: " + numeroTramiteReintegro;
				}
			}
		}
	return descripcionDebito;
	 
	}
	
	/**
	 * Recorre todas las transferencias que posea el cobro. Si todas estan en
	 * estado APROPIACION envio la confirmacion. Si existe alguna transaccion en
	 * estado ERROR verifico si es necesario desapropiar y envio la desapropiacion.
	 * 
	 * @param cobro
	 * @throws ReintentoExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override 
	public void verificarEstadoCobro(ShvCobCobro cobro, String usuario, boolean reenvioConfirmacion) throws NegocioExcepcion, ReintentoExcepcion{
		
		try {
			Long idOperacion = cobro.getOperacion().getIdOperacion();
			if(reenvioConfirmacion){
				List<ShvCobCobro> listaCobrosErrorConfirmacion = cobroDao.listarCobrosErrorConfirmacion(idOperacion);
				
				if(Validaciones.isCollectionNotEmpty(listaCobrosErrorConfirmacion)){
					cobro = listaCobrosErrorConfirmacion.get(0);
				}else{ 
					String mensaje = "No se ha encontrado el cobro con el id de operacion " + idOperacion + " en estado Error en confirmacion.";
					System.out.println(mensaje);
					Traza.advertencia(getClass(), mensaje);
				}
			}
			
			List<String> listaOperacionTransaccionesApropiadasCalipso = new ArrayList<String>(); /*operacion.transaccion*/
			List<String> listaOperacionTransaccionesApropiadasMic = new ArrayList<String>();     /*operacion.transaccion*/
			List<String> listaIdMovMer = new ArrayList<String>();     /*operacion.transaccion*/

			List<ShvCobTransaccion> listaOperacionTransaccionesOtrosDebitos = new ArrayList<ShvCobTransaccion>();
			boolean esCompensacion = hayCompensacionesSap(cobro);
			boolean errorCompensacionesSap = false;
			if (esCompensacion) {
				
			}
			

			Boolean enviarDesapropiacion = false;
			String idOperacionADesapropiar="";
			
			boolean aplicacionManualConfirmada = false;
			boolean hayAplicacionManual = false;
			boolean todasTransaccionesApropiadas = false;
			
			Estado estadoCobroAAvanzar = null;
			List<EstadoTransaccionEnum> listaEstadosTransaccionError = EstadoTransaccionEnum.listarEstadosError();
			List<ShvCobTransaccion> listaTransaccionesOrdenada = cobro.getTransaccionesOrdenadas();
			
			tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), listaTransaccionesOrdenada, false, 1);
			// Si existe alguna transaccion en estado "EN_PROCESO_APROPIACION" no tomo ninguna accion
			if(!existenTransaccionesEnProcesoApropiacion(cobro)){
				
				// Si la primer transaccion esta en estado Error
				if(verificarErrorEnApropiacion(listaTransaccionesOrdenada)){
					// cambiar el estado del cobro a COB_ERROR_APROPIACION
					estadoCobroAAvanzar = Estado.COB_ERROR_APROPIACION;
//					cambiarEstadoCobro(cobro,Estado.COB_ERROR_APROPIACION,usuario);
//					return;
				}else{
					todasTransaccionesApropiadas = todasTransaccionesApropiadas(cobro);
					for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
						
						hayAplicacionManual = transaccion.hayAplicacionManual();
						//Si el cobro tiene aplicacion manual, busco el usuario que confirmó la aplicacion manual. 
						//Si retorna null significa que todavía no se confirmó
						//y tengo que avanzar el cobro al estado COB_PENDIENTE_CONFIRMACION_MANUAL
						
						if (hayAplicacionManual){
							if (todasTransaccionesApropiadas){
								String usuarioConfirmacionManual = cobroDao.buscarUsuarioConfirmacionManual(idOperacion);
								if (Validaciones.isNullOrEmpty(usuarioConfirmacionManual)){
									estadoCobroAAvanzar = Estado.COB_PENDIENTE_CONFIRMACION_MANUAL;
//									cambiarEstadoCobro(cobro, Estado.COB_PENDIENTE_CONFIRMACION_MANUAL, usuario);
									break;
								} else {
									
									aplicacionManualConfirmada = true;
								}
							}
						}
						
						if (!transaccion.haySaldoAPagarOSaldoACobrar()){
							
							// MEDIOS DE PAGO
							if(existenMediosPagoCalipso(transaccion)){
								if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoCalipso(transaccion))
										&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
										&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
									listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
								}
							}
							if(existenMediosPagoMic(transaccion)){
								if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoMIC(transaccion))
										&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
										&& !listaOperacionTransaccionesApropiadasMic.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
									listaOperacionTransaccionesApropiadasMic.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
								}
							}
							
							// FACTURAS
							ShvCobFactura factura = transaccion.getFactura();
							if(factura instanceof ShvCobFacturaCalipso && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
									&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
									&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
								listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
							}
							if(factura instanceof ShvCobFacturaMic && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
									&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
									&& !listaOperacionTransaccionesApropiadasMic.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
								listaOperacionTransaccionesApropiadasMic.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
							}
							// TODO MAX 4Up
							if (
								esCompensacion &&
								factura instanceof ShvCobFacturaUsuario &&
								(
								EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado()) ||
								EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())
								) &&
								!EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())
							) {
								errorCompensacionesSap = true;
								listaOperacionTransaccionesOtrosDebitos.add(transaccion);
							}
							//TRATAMIENTO DE DIFERENCIA
							ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();
							if (!Validaciones.isObjectNull(tratamiento)){
							
								if ((TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia()))
										&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamiento.getEstado())
										&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
										&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
									listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
									
								}
								
								if ((TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia()))
										&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamiento.getEstado())
										&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
										&& !listaOperacionTransaccionesApropiadasMic.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
									listaOperacionTransaccionesApropiadasMic.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
									
								}
							}
							
							// Si la transaccion esta en estado de ERROR tengo que dejar de procesar las transacciones
							if (
								listaEstadosTransaccionError.contains(transaccion.getEstadoProcesamiento()) &&
								(
									(
									SiNoEnum.SI.equals(cobro.getContieneAplicacionManual()) &&
									esCompensacion
									) ||
									SiNoEnum.NO.equals(cobro.getContieneAplicacionManual())
								)
							) {
								enviarDesapropiacion = true;
								if (!transaccion.hayAplicacionManual() && SiNoEnum.NO.equals(cobro.getContieneAplicacionManual())) {
									break;
								}
							}
						
						}
						
					}//-Fin del For
					
					if(reenvioConfirmacion){
					//Finalizo la tarea de reenviar confirmacion para el cobro
					tareaServicio.finalizarTarea(TipoTareaEnum.COB_REV_COB_CON, cobro.getWorkflow().getIdWorkflow(), new Date(), cobro.getWorkflow().getUsuarioModificacion(), null);
					
					if(Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado())){
							if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasMic)){
								cobroBatchSoporteImputacionMicServicio.confirmacionMic(listaOperacionTransaccionesApropiadasMic,cobro, reenvioConfirmacion);
							}else{
								// si no existen cosas apropiadas de MIC envio la confirmacion a Calipso solamente
								if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso)){
									
									//Defecto 763, no lo necesito preguntar por el metodo puedeEnviarMensaje
									//if(puedeEnviarMensaje(cobro.getOperacion().getIdOperacion(), null, null, SistemaEnum.CALIPSO, TipoInvocacionEnum.$05)){
										String estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.confirmacionCalipso(listaOperacionTransaccionesApropiadasCalipso,cobro);
										if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
											for (String operacionTransaccion : listaOperacionTransaccionesApropiadasCalipso) {
												
												ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));
												String idTransaccion = transaccion.getIdTransaccion().toString();
												
												if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
													actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.CONFIRMADA, SistemaEnum.CALIPSO,null);
												}else{
													actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
												}
												
												// Cambio estado de la transaccion
												if(EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
													if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
														transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
													}else{
														transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_CONFIRMACION);
													}
												}
												
											} // Fin for transacciones
										}
									//}
								}
							}
						} // Fin COB_ERROR_CONFIRMACION
					}else{
						if (enviarDesapropiacion) {
							if (noExisteNadaEnProceso(cobro)) {
								//Tratamiento Diferencia
								if (existeTratamientoDiferencia(cobro)
										|| existenCargosProximaFactura(cobro,null)){
									desapropiarTratamientoDiferencia(cobro,listaIdMovMer,idOperacionADesapropiar,usuario);
								}
								
								if (
									!Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasMic) &&
									!Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso) &&
									!Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesOtrosDebitos)
								
								){
									// existen transacciones en proceso de desapropiacion? 
									if (existenFacturasOMedioPagosEnProcesoDesapropiacion(cobro)) {
										//SI -> reintentos de desapropiacion, no importa el cobrador
										estadoCobroAAvanzar = reintentosDesapropiacion(cobro,listaIdMovMer,idOperacionADesapropiar);
									}else{
										//NO ->
										// Significa que ya respondieron los cobradores y hay que
										// evaluar el estado del cobro
										if (existeTransaccionesErrorDesapropiacion(cobro)){
											estadoCobroAAvanzar = Estado.COB_ERROR_DESAPROPIACION;
//											cambiarEstadoCobro(cobro,Estado.COB_ERROR_DESAPROPIACION,usuario);
										} else {
											estadoCobroAAvanzar = Estado.COB_ERROR_COBRO;
//											cambiarEstadoCobro(cobro,Estado.COB_ERROR_COBRO,usuario);
											realizarReversionMedioPagoValor(cobro,estadoCobroAAvanzar);
										}
									}
									
									
								} else {
									if (
										esCompensacion &&
										errorCompensacionesSap
									) {
										// TODO
										Set<Integer> grupoTareaEnviada = new HashSet<Integer>();
										for (ShvCobTransaccion transaccion : listaOperacionTransaccionesOtrosDebitos) {
											ShvCobFacturaUsuario shvCobFacturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();
											transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
											if (!grupoTareaEnviada.contains(transaccion.getGrupo())) {
												grupoTareaEnviada.add(transaccion.getGrupo());
												this.enviarMailyGenerarTareaDesapropiacionManual(
													cobro.getOperacion().getIdOperacion(),
													transaccion.getGrupo().longValue(),
													shvCobFacturaUsuario.getSociedad(),
													shvCobFacturaUsuario.getSistemaOrigen(),
													cobro.getIdCobro()
												);
											}
											if (Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesOtrosDebitos)) {
												estadoCobroAAvanzar = Estado.COB_PEND_DESAPROPIACION_MANUAL_EXTERNA;
											}
											shvCobFacturaUsuario.setEstado(EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION_MANUAL);
										}
										grupoTareaEnviada.clear();
										//this.enviarMailyGenerarTareaImputacionManual()
									}
									// Si hay cosas de MIC solo envio a MIC. A Calipso se envia con la respuesta de MIC
									if (Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasMic) 
											&& Validaciones.isNullOrEmpty(idOperacionADesapropiar)) {
										cobroBatchSoporteImputacionMicServicio.desapropiacionMic(listaOperacionTransaccionesApropiadasMic, cobro,idOperacionADesapropiar);
									} else {
										if (Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso)) {
											// si no hay nada de MIC, envio a CALIPSO la desapropiacion
											if(puedeEnviarMensaje(cobro.getOperacion().getIdOperacion(), null, null, SistemaEnum.CALIPSO, TipoInvocacionEnum.$04)){
												String estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.desapropiacionCalipso(listaOperacionTransaccionesApropiadasCalipso, cobro, listaIdMovMer);
												if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
													CobMensajeriaTransaccionDto mensajeriaDto = 
															(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarRespuestaDesapropiacionMic(cobro.getOperacion().getIdOperacion());
													
													if (!Validaciones.isObjectNull(mensajeriaDto)) {
														MicRespuestaADCSalida rta = (MicRespuestaADCSalida)micRespuestaADCMapeoJMS.deserializar(mensajeriaDto.getRespuestaRecibida(), true, false);
														
														String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
																rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
														
														aplicarCambiosEstadosSegunRespuestasDesapropiacion(estadoRespuestaCalipso,resultadoInvocacion,cobro);
														
													} else {
														for (String operacionTransaccion : listaOperacionTransaccionesApropiadasCalipso) {
															ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));
															String idTransaccion = transaccion.getIdTransaccion().toString();
															if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
																actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.CALIPSO,null);
															}else{
																actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
															}
															// Cambio estado de la transaccion
															if(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
																if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
																	transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
																}else{
																	transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
																}
															}
														}
														
														//segun respuesta de calipso seteo el estado del cobro
														if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
															estadoCobroAAvanzar = Estado.COB_ERROR_COBRO;
	//														cambiarEstadoCobro(cobro, Estado.COB_ERROR_COBRO, usuario);
															realizarReversionMedioPagoValor(cobro,estadoCobroAAvanzar);
														}else{
															if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
																estadoCobroAAvanzar = Estado.COB_ERROR_DESAPROPIACION;
	//															cambiarEstadoCobro(cobro, Estado.COB_ERROR_DESAPROPIACION, usuario);
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								if (existenFacturasOMedioPagosEnProcesoDesapropiacion(cobro)) {
									reintentosDesapropiacion(cobro,listaIdMovMer,idOperacionADesapropiar);
								}
							}
							
						} else {
							// SI TENEMOS TODAS LAS TRANSACCIONES APROPIADAS, PEDIMOS LA CONFIRMACION 
							if (
								(todasTransaccionesApropiadasOEnProcesoConfirmacion(cobro) && (!hayAplicacionManual || aplicacionManualConfirmada))
									|| (hayAlgunaTransaccionApropiada(cobro) && SiNoEnum.SI.equals(cobro.getContieneAplicacionManual()))
								){
								
								if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasMic)){
									cobroBatchSoporteImputacionMicServicio.confirmacionMic(listaOperacionTransaccionesApropiadasMic,cobro, false);
									Traza.auditoria(this.getClass(), "Se envio la confirmacion a MIC. EL WORKFLOW DEL COBRO ES: ["+cobro.getWorkflow().getEstado().descripcion()+"]");
								}else{
									// si no existen cosas apropiadas de MIC envio la confirmacion a Calipso solamente
									if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso)){
										if(puedeEnviarMensaje(cobro.getOperacion().getIdOperacion(), null, null, SistemaEnum.CALIPSO, TipoInvocacionEnum.$05)){
											String estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.confirmacionCalipso(listaOperacionTransaccionesApropiadasCalipso,cobro);
											if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
												CobMensajeriaTransaccionDto mensajeriaDto = 
														(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarRespuestaConfirmacionMic(cobro.getOperacion().getIdOperacion());
												
												if (!Validaciones.isObjectNull(mensajeriaDto)) {
													
													MicRespuestaADCSalida rta = (MicRespuestaADCSalida)micRespuestaADCMapeoJMS.deserializar(mensajeriaDto.getRespuestaRecibida(), true, false);
													
													String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
															rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
															
													aplicarCambiosEstadosSegunRespuestasConfirmacion(estadoRespuestaCalipso,resultadoInvocacion,cobro);
												} else {
													
													// Recorro las transacciones que envie a confirmar de calipso 
													for (String operacionTransaccion : listaOperacionTransaccionesApropiadasCalipso) {
														ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));
														String idTransaccion = transaccion.getIdTransaccion().toString();
														
														// Cambio el estado a la factura o tratamiento diferencia y a los medios de pago
														if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
															actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.CONFIRMADA, SistemaEnum.CALIPSO,null);
															
															if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
																ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
																if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
																	&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamientoDiferencia.getEstado())){
																	tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
																}
															}
														}else{
															actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
															
															if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
																ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
																if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
																	&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamientoDiferencia.getEstado())){
																	tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
																}
															}
														}
														
														// Cambio estado de la transaccion
														if(EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
															if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
																transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
															}else{
																transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_CONFIRMACION);
															}
														}
													}
												}
											}
										}
									}
									
									//Seteo en confirmada todos los tratamientos diferencia y medios de pago Shiva pasan a confirmados
									// ya que todas las tranasccoiens del cobro estan en estado apropiadas
									
								}
								confirmarMediosPagoShivaCompensacionYTratamientoDiferenciaYSaldoAPagar(cobro);
								Traza.auditoria(this.getClass(), "Se guarda el wf del cobro post envio de confirmacion a MIC. EL WORKFLOW DEL COBRO ES: ["+cobro.getWorkflow().getEstado().descripcion()+"]");
							}
						}
					}
				}
			}
			cobro = cobroDao.modificar(cobro);
			
			// Pregunto si las transacciones estan confirmadas
			if(todasTransaccionesConfirmadas(cobro)){
				if(reenvioConfirmacion){
					// si las transacciones estan confirmadas -> cambio el WF a CONFIRMADO CON ERROR
					estadoCobroAAvanzar = Estado.COB_CONFIRMADO_CON_ERROR;
//					cambiarEstadoCobro(cobro, Estado.COB_CONFIRMADO_CON_ERROR, usuario);
				}else{
					// si las transacciones estan confirmadas -> cambio el WF a COBRADO
					estadoCobroAAvanzar = Estado.COB_COBRADO;
//					cambiarEstadoCobro(cobro, Estado.COB_COBRADO, usuario);
	
				}
	
				//Informo los cambios a contabilidad y scard
				informarAContabilidadScard(cobro);
				
				if (hayCompensacionesSap(cobro)){
					enviarMailSap(cobro);
				}
				
			}else{
				if(SiNoEnum.SI.equals(cobro.getContieneAplicacionManual()) && Validaciones.isObjectNull(estadoCobroAAvanzar)
						&& !Estado.COB_PENDIENTE_MIC.equals(cobro.getWorkflow().getEstado())){
					estadoCobroAAvanzar = obtenerEstadoFinal(cobro);
				
				} else if(existenTransaccionesErrorConfirmacion(cobro)){
					// si existe transacciones en error de confirmacion -> cambio el WF a ERROR EN CONFIRMACION
					estadoCobroAAvanzar = Estado.COB_ERROR_CONFIRMACION;
//					cambiarEstadoCobro(cobro, Estado.COB_ERROR_CONFIRMACION, usuario);
				}
			}
			
			tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(),false,1);
			if (!Validaciones.isObjectNull(estadoCobroAAvanzar)){
				ShvCobEdCobroSimplificado cobroSimple = cobroOnlineDao.buscarCobroSimplificado(cobro.getOperacion().getIdOperacion());
				ShvCobTransaccion transAComparar = null;
				for(ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
					if (Validaciones.isObjectNull(transAComparar)) {
						transAComparar = transaccion;
						transaccion.setApocope(apocopeGrupo(transaccion, cobroSimple.getIdSegmento(), cobroSimple.getIdEmpresa()));					
					} else if (!transAComparar.getNumeroTransaccion().equals(transaccion.getNumeroTransaccion())) {
						transAComparar = transaccion;
						transaccion.setApocope(apocopeGrupo(transaccion, cobroSimple.getIdSegmento(), cobroSimple.getIdEmpresa()));	
					} else {
						transaccion.setApocope(apocopeGrupo(transAComparar, cobroSimple.getIdSegmento(), cobroSimple.getIdEmpresa()));	
					
					}
				}	
				
				if(Estado.COB_PARCIALMENTE_EN_ERROR.equals(estadoCobroAAvanzar)
						|| (Estado.COB_ERROR_COBRO.equals(estadoCobroAAvanzar) && SiNoEnum.SI.equals(cobro.getContieneAplicacionManual()))){
					
					cobroBatchServicio.cambiarEstadoCobro(cobro, estadoCobroAAvanzar, usuario);
					evaluarEnvioMailYGenerarTarea(cobro,estadoCobroAAvanzar);
					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(cobro,usuario);
				}else {
					
					evaluarEnvioMailYGenerarTarea(cobro,estadoCobroAAvanzar);
					cobroBatchServicio.cambiarEstadoCobro(cobro, estadoCobroAAvanzar, usuario);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public void calcularImporteParcial(ShvCobCobro cobro) throws PersistenciaExcepcion{
		boolean hayTransaccionesConfirmadas = false;
		boolean hayTransaccionesErrorDesapropiacion = false;
		boolean hayTransaccionesErrorConfirmacion = false;
		
		BigDecimal importeParcial = BigDecimal.ZERO;
		Traza.advertencia(this.getClass(), "importe parcial es: " + importeParcial);
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
			
			if(EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesConfirmadas = true;
				importeParcial = importeParcial.add(transaccion.calcularImporteParcial());
				Traza.advertencia(this.getClass(), "importe parcial es: " + importeParcial);
				
			} else if(EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesErrorDesapropiacion = true;
				break;
				
			} else if (EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesErrorConfirmacion = true;
				break;
			
			} 
		}
		
		if (!hayTransaccionesErrorDesapropiacion && !hayTransaccionesErrorConfirmacion && hayTransaccionesConfirmadas){
		
				
			ShvCobEdCobroSimplificado cobEdCobro = cobroOnlineDao.buscarCobroSimplificado(cobro.getOperacion().getIdOperacion());
			Traza.advertencia(this.getClass(), "importe parcial es: " + cobEdCobro.getImporteParcial());
			cobEdCobro.setImporteParcial(importeParcial);
			Traza.advertencia(this.getClass(), "importe parcial es: " + cobEdCobro.getImporteParcial());
			cobEdCobro = cobroOnlineDao.guardarCobroSimplificado(cobEdCobro);
			Traza.advertencia(this.getClass(), "importe parcial es: " + cobEdCobro.getImporteParcial());
			
		}
	}

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Estado obtenerEstadoFinal(ShvCobCobro cobro) throws NegocioExcepcion {

		Estado estado = null;
			
		boolean hayTransaccionesConfirmadas = false;
		boolean hayTransaccionesErrorDesapropiacion = false;
		boolean hayTransaccionesErrorConfirmacion = false;
		
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
			
			if(EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesConfirmadas = true;
								
			} else if(EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesErrorDesapropiacion = true;
				break;
				
			} else if (EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				hayTransaccionesErrorConfirmacion = true;
				break;
			
			} 
		}
			
		if (hayTransaccionesErrorDesapropiacion){
			estado = Estado.COB_ERROR_DESAPROPIACION_PARCIAL;
				
		} else if (hayTransaccionesErrorConfirmacion){
			estado = Estado.COB_ERROR_CONFIRMACION_PARCIAL;
			
		} else if (hayTransaccionesConfirmadas){
		
			estado = Estado.COB_PARCIALMENTE_EN_ERROR;
			
			//Informo los cambios a contabilidad y scard
			informarAContabilidadScard(cobro);
			
			/*if(todasAnuladas){
				estado = Estado.COB_COBRADO_PARCIALMENTE;
			
			//Si hay alguna tarea pendiente y ninguna reeditada 
			} else if (hayTareasPendientes && !hayTareasReeditadas){
				estado = Estado.COB_PARCIALMENTE_EN_ERROR;
			
			//Si hay alguna tarea reeditada
			} else if (hayTareasReeditadas){
				estado = Estado.COB_REEDITADO_PARCIAL;
			}
			
			
		
		} else if (hayTransaccionesDesapropiadas || hayTransaccionesAplRechazada ||hayTransaccionesError){
			
			
		*/		
		} else {
			estado = Estado.COB_ERROR_COBRO;
		}
			
		return estado;
	}

	/**
	 * Se Tracean los datos de imputacion 
	 * @param cobro
	 */
	@Override
	public void tracearDatosImputacionCobro(Long idCobro, ShvWfWorkflow workflow,
			    List<ShvCobTransaccion> transaccionesOrdenadas,
				boolean esInicio, int contadorCobro)
	{
		
		StringBuffer mensaje = new StringBuffer("\n");
		if(esInicio){
			mensaje.append("Cobro " + contadorCobro + " a imputar: ");
		}else{
			mensaje.append("Cobro - resultado: ");
		}
		
		String cobroEstado = "idCobro: " + idCobro + " | estado: " + workflow.getEstado().descripcion();
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
						Utilidad.rellenarEspaciosDerecha("| tipo: " + ((fact instanceof ShvCobFacturaMic)?"MIC":
							(fact instanceof ShvCobFacturaCalipso)?"CALIPSO":"-"), 17) + 
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
	 * Verifica si exiten transacciones pertenecientes al cobro en estado "En Proceso Apropiación".
	 * @param cobro
	 * @return
	 */
	private boolean existenTransaccionesEnProcesoApropiacion(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
			if(EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica que si la primer transaccion esta en un estado de ERROR y las demas
	 * transacciones estan en estado PENDIENTE retorna true para que se aplique el estado
	 * COB_ERROR_APROPIACION al cobro.
	 * @param listaTransaccionesOrdenada
	 * @return
	 */
	@Override
	public boolean verificarErrorEnApropiacion(List<ShvCobTransaccion> listaTransaccionesOrdenada) {
		
		ShvCobTransaccion primeraTransaccion = listaTransaccionesOrdenada.get(0);
		if (EstadoTransaccionEnum.listarEstadosError().contains(primeraTransaccion.getEstadoProcesamiento())) {
			if (!Validaciones.isObjectNull(primeraTransaccion.getFactura())){
				if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(primeraTransaccion.getFactura().getEstado())
						|| EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(primeraTransaccion.getFactura().getEstado())
						|| EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(primeraTransaccion.getFactura().getEstado())){
					return false;
				}
			}
			if (!Validaciones.isObjectNull(primeraTransaccion.getTratamientoDiferencia())){
				if (
					TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(primeraTransaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()) &&
					(
						EstadoFacturaMedioPagoEnum.APROPIADA.equals(primeraTransaccion.getFactura().getEstado()) ||
						EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(primeraTransaccion.getFactura().getEstado()) ||
						EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(primeraTransaccion.getFactura().getEstado()) ||
						EstadoFacturaMedioPagoEnum.PENDIENTE.equals(primeraTransaccion.getFactura().getEstado())
					)
				) {
					return false;
				}
			}
			for (ShvCobMedioPago medioPago : primeraTransaccion.getMediosPago()) {
				if (TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
					if (EstadoFacturaMedioPagoEnum.PENDIENTE.equals(medioPago.getEstado())) {
						return false;
					}
					break;
				}
			}
			
			for (ShvCobMedioPago medioPago : primeraTransaccion.getMediosPago()) {
				if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPago.getEstado())
						|| EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medioPago.getEstado())
						|| EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(medioPago.getEstado())){
					return false;
				}
			}

		} else{
			return false;
		}
		
		return true;
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void cambiarEstadoCobroImputacionManual(Long idOperacion, Estado estado, String usuario) throws NegocioExcepcion {
		
		ShvWfWorkflow workflow = null;
		
		try{
			
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			
			if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
				
				if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(estado)){
					workflow = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManualYEnProcesoAplicacionExterna(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
					
				}
				
				if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)){
					workflow = workflowCobros.registrarProcesoDeAplicacionExternaAEnPendienteDeConfirmacionManual(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				}
				
				if(Estado.COB_PROCESO.equals(estado)){
					workflow = workflowCobros.registrarProcesoDeAplicacionManualAEnProceso(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				}
				
			} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
				
				if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)){
					workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManual(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				}
				
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
//	/**
//	 * Cambia el estado del workflow del cobro y lo guarda en la base
//	 * @param cobro
//	 * @param cobErrorCobro
//	 * @throws NegocioExcepcion 
//	 */
//	@Override
//	public void cambiarEstadoCobro(ShvCobCobro cobro, Estado estado, String usuario) throws NegocioExcepcion {
//		try{
//			if (Estado.COB_PROCESO.equals(cobro.getWorkflow().getEstado())){
//				if(Estado.COB_ERROR_COBRO.equals(estado)){
//					ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorCobro(cobro.getWorkflow(), "", usuario);
//					cobro.setWorkflow(workflowActualizado);
//					cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//					cobro = cobroDao.modificar(cobro);
//				}else{
//					if(Estado.COB_ERROR_APROPIACION.equals(estado)){
//						ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorApropiacion(cobro.getWorkflow(), "", usuario);
//						cobro.setWorkflow(workflowActualizado);
//						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//						cobro = cobroDao.modificar(cobro);
//					}else{
//						if(Estado.COB_ERROR_CONFIRMACION.equals(estado)){
//							ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorConfirmacion(cobro.getWorkflow(), "", usuario);
//							cobro.setWorkflow(workflowActualizado);
//							//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//							cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//							cobro = cobroDao.modificar(cobro);
//						} else {
//							if(Estado.COB_COBRADO.equals(estado)){
//								ShvWfWorkflow workflowActualizado = workflowCobros.cobrarCobro(cobro.getWorkflow(), "", usuario);
//								cobro.setWorkflow(workflowActualizado);
//								//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//								cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
//								cobro = cobroDao.modificar(cobro);
//							} else {
//								if(Estado.COB_ERROR_DESAPROPIACION.equals(estado)){
//									ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorDesapropiacion(cobro.getWorkflow(), "", usuario);
//									cobro.setWorkflow(workflowActualizado);
//									//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//									cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//									cobro = cobroDao.modificar(cobro);
//								} else {
//									if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)){
//										ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroEnProcesoAPendienteDeConfirmacionManual(cobro.getWorkflow(), "", usuario);
//										cobro.setWorkflow(workflowActualizado);
//										//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//										cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//										cobro = cobroDao.modificar(cobro);
//									} else {
//										if (Estado.COB_COBRADO_PARCIALMENTE.equals(estado)){
//											ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroCobradoParcialmente(cobro.getWorkflow(), "", usuario);
//											cobro.setWorkflow(workflowActualizado);
//											//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//											cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
//											//TODO:
//											/**
//											 * Cuando el proceso de imputación de cobros deba modificar el estado a un estado final de tipo parcial, 
//											 * se debe actualizar el importe parcial imputado del cobro.El valor con el cuál se debe actualizar 
//											 * el importe parcial es el siguiente cálculo: El importe parcial del cobro será la suma de los importes 
//											 * de los medios de pago de las transacciones que se encuentren apropiadas correctamente, 
//											 * más el importe del tratamiento de la diferencia de tipo “débito a próxima factura” de aquellas transacciones 
//											 * que se encuentren correctamente apropiadas.
//											 * Para ello también será necesario agregar una nueva columna IMPORTE_PARCIAL en la tabla SHV_COB_ED_COBRO 
//											 * a fin de poder almacenar 
//											 */
//											cobro = cobroDao.modificar(cobro);
//										} else {
//											if (Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(estado)){
//												//TODO:VER
//											} else {
//												if (Estado.COB_ERROR_DESAPROPIACION_PARCIAL.equals(estado)){
//													//TODO:VER
//												} else {
//													if (Estado.COB_PEND_DESAPROPIACION_MANUAL_EXTERNA.equals(estado)) {
//														ShvWfWorkflow workflowActualizado = workflowCobros.registrarEnProcesoACobroPendienteDesapropiacionManualExterna(cobro.getWorkflow(), "", usuario);
//														cobro.setWorkflow(workflowActualizado);
//														//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//														cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			} else {
//				if (Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado())){
//					if(Estado.COB_CONFIRMADO_CON_ERROR.equals(estado)){
//						ShvWfWorkflow workflowActualizado = workflowCobros.reintentarConfirmacion(cobro.getWorkflow(), "", usuario);
//						cobro.setWorkflow(workflowActualizado);
//						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
//						cobro = cobroDao.modificar(cobro);
//					}
//				} else if (Estado.COB_ERROR_DESAPROPIACION.equals(cobro.getWorkflow().getEstado())) {
//					if (Estado.COB_PROCESO.equals(estado)) {
//						ShvWfWorkflow workflowActualizado = workflowCobros.solicitarEnviarDesapropiacion(cobro.getWorkflow(), "", usuario);
//						cobro.setWorkflow(workflowActualizado);
//						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
//						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//						cobro = cobroDao.modificar(cobro);
//					}
//				} else {
//					Traza.advertencia(getClass(), "Se quizo avanzar en el workflow de cobros a un estado incorrecto. Id cobro: " + cobro.getOperacion().getIdOperacion() + "pero el cobro no esta en estado 'En Proceso'. ");
//				}
//			}
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//	}
	
	@Override
	public void avanzarEstadoCobroImputacionManual(Long idOperacion, Estado estado, String usuario) throws NegocioExcepcion {
		
		//Busco el workflow del cobro
				ShvWfWorkflow workflow = null;
				try {
					workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
					
					if (!Validaciones.isObjectNull(workflow)){
						if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
							workflow = workflowCobros.registrarProcesoDeAplicacionManualAEnProceso(workflow, "", usuario);				
						}
					}
					Traza.advertencia(getClass(), "A: (" +idOperacion+ ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] ");
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e.getMessage(),e);
				}
	}
	/**
	 * Este método avanza el wf del cobro hasta PENDIENTE CONFIRMACION MANUAL porque hubo un error en desapropiacion
	 * y el cobro no puede seguir avanzando( SEGUIR APROPIANDO LOS SIGUIENTES GRUPOS PENDIENTES).
	 * SI TENGO EL WF EN COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA, QUE SE HACE? SE CANCELA LA TAREA GENERADA ANTERIORMENTE? Y SI EL USUARIO
	 * RECHAZA LA TAREA ANTERIOR?
	 */
	@Override
	public void avanzarCobroAErrorEnDesapropiacionParcial(ShvWfWorkflow workflow,Long idOperacion, String usuario, boolean cambioEstado) throws NegocioExcepcion{

		ShvWfWorkflow workflowActual = null;
		try{

			workflowActual = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			
			if (workflow.getEstado().equals(workflowActual.getEstado()) || cambioEstado ){
				
				if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
					
					workflowActual = workflowCobros.registrarCobroDePendienteConfirmacionManualYEnProcesoDeAplicacionExternaAErrorEnDesapropiacion(workflow, "", usuario);

				} else {
					
					if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
						
						workflowActual = workflowCobros.registrarCobroDeEnProcesoDeAplicacionExternaAErrorEnDesapropiacion(workflow, "", usuario);
					
					} else {
						
						if (Estado.COB_PROCESO.equals(workflow.getEstado())){
							workflowActual = workflowCobros.registrarErrorDesapropiacion(workflow, "", usuario);
							
						}
					}
				}
			} else {
				//Si el estado actual del cobro es diferente al wf recien obtenido de la base, significa que se actualizó en el online y debemos lanzar excepcion
				throw new NegocioExcepcion("ERROR: El estado del Workflow fue modificado, se procesará nuevamente", "");
			}
			Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflowActual.getEstado().descripcion() + " ]");

			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
			
			enviarMailyGenerarTarea(cobro, workflowActual.getEstado());
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	/**
	 * Verifica si todas las transacciones del cobro estan Apropiadas 
	 * o en proceso de confirmacion.
	 * 
	 * @param cobro
	 * @return
	 */
	private Boolean todasTransaccionesApropiadas(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(!EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo Shiva.
	 * @param transaccion
	 * @return
	 **/
	 public boolean existenMediosPagoShiva(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoShivaSinOperacion){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo Calipso.
	 * @param transaccion
	 * @return
	 */@Override
	 public boolean existenMediosPagoCalipso(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo Calipso.
	 * @param transaccion
	 * @return
	 */
	 @Override
	public boolean existenMediosPagoCalipso(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipso
					|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Retorna el estado del primer Medio de pago de tipo Calipso de la transaccion,
	 * ya que todos los medios de pago de tipo Calipso deberian tener el mismo estado siempre.
	 * @param transaccion
	 * @return Si la transaccion no tiene Medios de pago Calipso retorna null.
	 */
	@Override
	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoCalipso(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion){
				return medioPago.getEstado();
			}
		}
		return null;
	}
	
	/**
	 * Retorna el estado del primer Medio de pago de tipo Shiva de la transaccion,
	 * ya que todos los medios de pago de tipo Shiva deberian tener el mismo estado siempre.
	 * @param transaccion
	 * @return Si la transaccion no tiene Medios de pago Shiva retorna null.
	 */
	@Override
	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoShiva(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoShivaSinOperacion){
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
	@Override
	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoCalipso(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipso){
				return medioPago.getEstado();
			}
		}
		return null;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo MIC.
	 * @param transaccion
	 * @return
	 */
	@Override
	public boolean existenMediosPagoMic(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMic){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo MIC.
	 * @param transaccion
	 * @return
	 */@Override
	 public boolean existenMediosPagoMic(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMicSinOperacion){
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna el estado del primer Medio de pago de tipo MIC de la transaccion,
	 * ya que todos los medios de pago de tipo MIC deberian tener el mismo estado siempre.
	 * @param transaccion
	 * @return Si la transaccion no tiene Medios de pago MIC retorna null.
	 */
	@Override
	public EstadoFacturaMedioPagoEnum getEstadoMedioPagoMIC(ShvCobTransaccionSinOperacion transaccion) {
		for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMicSinOperacion){
				return medioPago.getEstado();
			}
		}
		return null;
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
	 * Actualiza el estado de todos los medios de pago y/o factura de tipo "cobrador" 
	 * con el estado que recibe como parametro.
	 */
	@Override
	public void actualizarEstadoFacturaYMP(ShvCobCobro cobro, String idTransaccion, EstadoFacturaMedioPagoEnum estado, SistemaEnum cobrador, String descripcionError) throws NegocioExcepcion {
		try{	
			Boolean cambioEstadoDeFactura = false;
			
			ShvCobTransaccion transaccionDelCobro = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(idTransaccion));
			
			ShvCobTransaccionSinOperacion transaccion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.parseInt(idTransaccion));
			
			ShvCobFacturaSinOperacion factura = transaccion.getFactura();
			ShvCobFactura facturaDelCobro = transaccionDelCobro.getFactura();

			
			if (!Validaciones.isObjectNull(factura)){
				EstadoFacturaMedioPagoEnum estadoAnterior = factura.getEstado();
				
				//Cambio estado de la factura
				if(cobrador.equals(SistemaEnum.MIC) && (factura instanceof ShvCobFacturaMicSinOperacion)){
					if(!EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
						factura.setEstado(estado);
						facturaDelCobro.setEstado(estado);
						if (!Validaciones.isNullOrEmpty(descripcionError)){
							factura.setMensajeEstado(descripcionError);
							facturaDelCobro.setMensajeEstado(descripcionError);
						}
						cambioEstadoDeFactura = true;
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se cambio el estado ("+estadoAnterior+") de la factura " +factura.getIdFactura()+ " MIC por el nuevo estado: " + estado);
					} else {
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia la factura Mic de la transaccion nro " + transaccion.getNumeroTransaccion() +
								" al estado " + estado + ", por estar en estado de ERROR");
					}
				}else{
					if(cobrador.equals(SistemaEnum.CALIPSO) && (factura instanceof ShvCobFacturaCalipsoSinOperacion)){
						if(!EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
							factura.setEstado(estado);
							facturaDelCobro.setEstado(estado);
							if (!Validaciones.isNullOrEmpty(descripcionError)){
								factura.setMensajeEstado(descripcionError);
								facturaDelCobro.setMensajeEstado(descripcionError);
								
							}
							cambioEstadoDeFactura = true;
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se cambio el estado ("+estadoAnterior+") de la factura " +factura.getIdFactura()+ " CALIPSO por el nuevo estado: " + estado);
						} else {
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia la factura Calipso de la transaccion nro " + transaccion.getNumeroTransaccion() +
									" al estado " + estado + ", por estar en estado de ERROR");
						}
					} else {
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el estado de la factura id: " +factura.getIdFactura());
					}
				}
			}
			
			//Cambio el estado de los Medios de Pago
			for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
				if(cobrador.equals(SistemaEnum.MIC) && (medioPago instanceof ShvCobMedioPagoMicSinOperacion
						|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)){
					if(!EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())) {
						medioPago.setEstado(estado);
						transaccionDelCobro.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago()).setEstado(estado);
					
						if (!Validaciones.isNullOrEmpty(descripcionError)){
							medioPago.setMensajeEstado(descripcionError);
							transaccionDelCobro.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago()).setMensajeEstado(descripcionError);
							
						}
					} else {
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia los medios de pago Mic de la transaccion nro " + transaccion.getNumeroTransaccion() +
								" al estado " + estado + ", por estar en estado de ERROR");
					}
				}else{
					if(cobrador.equals(SistemaEnum.CALIPSO) && (medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion
							|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)){
						if(!EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())) {
							medioPago.setEstado(estado);
							transaccionDelCobro.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago()).setEstado(estado);
							if (!Validaciones.isNullOrEmpty(descripcionError)){
								medioPago.setMensajeEstado(descripcionError);
								transaccionDelCobro.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago()).setMensajeEstado(descripcionError);
								
							}
						} else {
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia los medios de pago Calipso de la transaccion nro " + transaccion.getNumeroTransaccion() +
									" al estado " + estado + ", por estar en estado de ERROR");
						}
					}else{
						
						//Si se cambio el estado de la factura entonces cambio el estado de 
						//los medios de pago usuario
						if(cambioEstadoDeFactura){
							if( !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)
									&& !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)){
								if(medioPago instanceof ShvCobMedioPagoUsuarioSinOperacion){
									medioPago.setEstado(estado);
									transaccionDelCobro.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago()).setEstado(estado);
								}
							}
							
						}
					}
				}
			}
			
		//	cobro = cobroDao.modificar(cobro);
			
			cobroDao.guardarTransaccionSinOperacion(transaccion);
			
			Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se realizo el cambio de los estados de la factura y/o medios de pago a " 
					+ estado.descripcion() + " correspondientes a la operacion id: " + cobro.getOperacion().getIdOperacion());
			
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	private boolean noExisteNadaEnProceso(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
					|| EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())
					|| EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
				return false;
			}
			if (!Validaciones.isObjectNull(transaccion.getFactura())){
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getFactura().getEstado())){
					return false;
				}
			}
			for (ShvCobMedioPago medio : transaccion.getMediosPago()) {
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medio.getEstado())){
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verifica si la transaccion contiene Tratamiento de diferencia
	 * @param transaccion
	 * @return
	 */
	private boolean existeTratamientoDiferencia(ShvCobCobro cobro) {
		
		for (ShvCobTransaccion transaccion: cobro.getTransaccionesOrdenadas()){
			if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Verifica si la transaccion o el cobro contiene Cargos proxima factura
	 * @param transaccion
	 * @return
	 */
	@Override
	public boolean existenCargosProximaFactura(ShvCobCobro cobro, ShvCobTransaccionSinOperacion transaccion) {
		
		if (!Validaciones.isObjectNull(cobro)){
			for(ShvCobTransaccion tran : cobro.getTransaccionesOrdenadas()){
				
				for(ShvCobMedioPago medioPago : tran.getMediosPago()){
					if(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic
							|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
						return true;
					}
				}
			}
		} else if (!Validaciones.isObjectNull(transaccion)){
			
			for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
				if(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion
						|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Setea en estado desapropiado los medios de pago Shiva y usuario. Ademas si tiene
	 * tratamiento diferencia de tipo ...... le setea el estado en desapropiado.
	 * Si el tratamiento diferencia es de tipo proxima factura calipso y esta en estado 
	 * apropiado me guardo el IdMovMer en la lista.
	 * Si el tratamiento diferencia es de tipo proxima factura mic y esta en estado 
	 * apropiado me guardo el IdOperacion en idOperacionADesapropiar.
	 * @param transaccion
	 * @throws NegocioExcepcion 
	 */
	private void desapropiarTratamientoDiferencia(ShvCobCobro cobro, List<String> listaIdMovMer, String idOperacionADesapropiar, String usuario) throws NegocioExcepcion{
		
		for(ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
			
			boolean tratamientoDesapropiado= false;
			
			// Desapropio tratamiento
			ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
			if (!Validaciones.isObjectNull(tratamientoDiferencia)){
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
					// si el tratamiento es REINTEGRO_CRED_PROX_FAC_CLP
					if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamientoDiferencia.getEstado())){
						listaIdMovMer.add(String.valueOf(tratamientoDiferencia.getIdMovMerCobrador()));
					}
					
				} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())){
					// si el tratamiento es REINTEGRO_CRED_PROX_FAC_MIC
					if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamientoDiferencia.getEstado())){ 
						idOperacionADesapropiar = Utilidad.rellenarCerosIzquierda(String.valueOf(transaccion.getOperacion().getIdOperacion()), 7);
					}
				} else {
					
					// si el tipo de tratamiento no es REINTEGRO_CRED_PROX_FAC_CLP ni REINTEGRO_CRED_PROX_FAC_MIC
					tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
					tratamientoDesapropiado=true;
				}
			}
			
			//Recorro los medios de pago. Si son Shiva y Usuario, los seteo en estado desapropiado
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
					
				if((medioPago instanceof ShvCobMedioPagoUsuario) 
						&& !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic)
						&& !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) ){
					medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
				}
			}
			
			//si el tratamiento y el medio de pago estan desapropiados, seteo la transaccion en desapropiada
			if (tratamientoDesapropiado && soloExistenMedioPagoShiva(transaccion)
					&& !(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					)
					){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
			}
		}
	}
	
	/**
	 * Verifica si exiten transacciones pertenecientes al cobro en estado "En Proceso Desapropiación".
	 * @param cobro
	 * @return
	 */
	private boolean existenFacturasOMedioPagosEnProcesoDesapropiacion(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			
			for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
				if(EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medioPago.getEstado())){
					return true;
				}
			}
			
			if (!Validaciones.isObjectNull(transaccion.getFactura())){
				if(EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getFactura().getEstado())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param cobro
	 * @param listaIdMovMev
	 * @param idOperacionDesapropiacion
	 * @throws NegocioExcepcion
	 * @throws ReintentoExcepcion
	 */
	private Estado reintentosDesapropiacion(ShvCobCobro cobro, List<String> listaIdMovMev, String idOperacionDesapropiacion) throws NegocioExcepcion, ReintentoExcepcion {
//		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		List<String> listaOperacionTransaccionesEnProcesoDesapropiacionMic = new ArrayList<String>();
		List<String> listaOperacionTransaccionesEnProcesoDesapropiacionCalipso = new ArrayList<String>();
		Estado estadoCobro = null;
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			
			// MEDIOS DE PAGO
			if(existenMediosPagoCalipso(transaccion)){
				if(EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(getEstadoMedioPagoCalipso(transaccion))
						&& EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						&& !listaOperacionTransaccionesEnProcesoDesapropiacionCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
					listaOperacionTransaccionesEnProcesoDesapropiacionCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
				}
			}
			if(existenMediosPagoMic(transaccion)){
				if(EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(getEstadoMedioPagoMIC(transaccion))
						&& EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						&& !listaOperacionTransaccionesEnProcesoDesapropiacionMic.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
					listaOperacionTransaccionesEnProcesoDesapropiacionMic.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
				}
			}
			
			// FACTURAS
			ShvCobFactura factura = transaccion.getFactura();
			if(factura instanceof ShvCobFacturaCalipso && EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())
					&& EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
					&& !listaOperacionTransaccionesEnProcesoDesapropiacionCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
				listaOperacionTransaccionesEnProcesoDesapropiacionCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
			}
			if(factura instanceof ShvCobFacturaMic && EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())
					&& EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
					&& !listaOperacionTransaccionesEnProcesoDesapropiacionMic.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
				listaOperacionTransaccionesEnProcesoDesapropiacionMic.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
			}
		}
		// Si hay cosas de MIC solo envio a MIC. A Calipso se envia con la respuesta de MIC
		if (Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesEnProcesoDesapropiacionMic)) {
			cobroBatchSoporteImputacionMicServicio.desapropiacionMic(listaOperacionTransaccionesEnProcesoDesapropiacionMic, cobro,idOperacionDesapropiacion);
		} else {
			if (Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesEnProcesoDesapropiacionCalipso)) {
				// si no hay nada de MIC, envio a CALIPSO la desapropiacion
				if(puedeEnviarMensaje(cobro.getOperacion().getIdOperacion(), null, null, SistemaEnum.CALIPSO, TipoInvocacionEnum.$04)){
					String estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.desapropiacionCalipso(listaOperacionTransaccionesEnProcesoDesapropiacionCalipso, cobro,listaIdMovMev);
					
					if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
						CobMensajeriaTransaccionDto mensajeriaDto = 
								(CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarRespuestaDesapropiacionMic(cobro.getOperacion().getIdOperacion());
						
						if (!Validaciones.isObjectNull(mensajeriaDto)) {
							MicRespuestaADCSalida rta = (MicRespuestaADCSalida)micRespuestaADCMapeoJMS.deserializar(mensajeriaDto.getRespuestaRecibida(), true, false);
							
							String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
									rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
									
							aplicarCambiosEstadosSegunRespuestasDesapropiacion(estadoRespuestaCalipso,resultadoInvocacion,cobro);
							
						} else {
							for (String operacionTransaccion : listaOperacionTransaccionesEnProcesoDesapropiacionCalipso) {
								ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));
								String idTransaccion = transaccion.getIdTransaccion().toString();
								if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
									actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.CALIPSO,null);
								}else{
									actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
								}
								// Cambio estado de la transaccion
								if(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
									if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
										transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
									}else{
										transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
									}
								}
							}
							
							//segun respuesta de calipso seteo el estado del cobro
							if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
								estadoCobro = Estado.COB_ERROR_COBRO;
//								cambiarEstadoCobro(cobro, Estado.COB_ERROR_COBRO, usuarioBatchCobroImputacion);
								realizarReversionMedioPagoValor(cobro,estadoCobro);
							}else{
								if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
									estadoCobro = Estado.COB_ERROR_DESAPROPIACION;
//									cambiarEstadoCobro(cobro, Estado.COB_ERROR_DESAPROPIACION, usuarioBatchCobroImputacion);
								}
							}

						}
					}
				}
			}
		}
		return estadoCobro;
	}
	
	private boolean existeTransaccionesErrorDesapropiacion(ShvCobCobro cobro) {
		for(ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
			if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void realizarReversionParcialMedioPagoValor(List<ShvCobTransaccionSinOperacion> listaTransaccionesADesapropiar,Long idOperacion) throws NegocioExcepcion{
		
		Traza.auditoria(getClass(), "realizarReversionParcialMedioPagoValor", "Se ejecuta la reversion de medios de pago Shiva "
				+ " pertenecientes al id de operacion " + idOperacion);
		
		List<ShvCobTransaccionSinOperacion> listaTransacciones = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		try {
			
			// me guardo todos los valores de shiva
			Map<Long, ShvValValorSimplificado> valores = new Hashtable<Long, ShvValValorSimplificado>();
			for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesADesapropiar){
				
				for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()) {
					
					if(medioPago instanceof ShvCobMedioPagoShivaSinOperacion){
						ShvCobMedioPagoShivaSinOperacion medioPagoShiva = ((ShvCobMedioPagoShivaSinOperacion)medioPago);
						
						if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor()) && EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPagoShiva.getEstado())) {
							Long idValor = medioPagoShiva.getIdValor();
							
							if (!valores.containsKey(idValor)) {
								ShvValValorSimplificado valor;
								valor = valorServicio.buscarValorSimplificado(idValor);
								
								valores.put(valor.getIdValor(), valor);
							}
						}
					}
				}
				
				for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()) {
					
					if(medioPago instanceof ShvCobMedioPagoShivaSinOperacion){
						
						BigDecimal saldoOriginal = null;
						BigDecimal saldoModificado = null;
						
						ShvCobMedioPagoShivaSinOperacion medioPagoShiva = ((ShvCobMedioPagoShivaSinOperacion)medioPago);
						BigDecimal importe = medioPagoShiva.getImporte();
						
						ShvValValorSimplificado valor = valores.get(medioPagoShiva.getIdValor());
						
						if (!Validaciones.isObjectNull(valor)) {
							saldoOriginal = valor.getSaldoDisponible();
							
							/*
							 * Si la lista no contiene la transaccion a la que pertenece el medio de pago, la agrego a la lista de transacciones
							 * para luego guardar las transacciones a las que se revirtió el medio de pago shiva.
							 */
							if (!listaTransacciones.contains(medioPago.getTransaccion())){
								listaTransacciones.add(medioPago.getTransaccion());
							}
							valor = valorServicio.revertirValoresPertenecientesAGrupo(valor, medioPago.getTransaccion(), importe, medioPago.getTransaccion().getNumeroTransaccion(), true);
							valores.put(valor.getIdValor(), valor);
							
							saldoModificado = valor.getSaldoDisponible();
							
							//cambio de estado a DESAPROPIADA el medio de pago
							medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
							
							Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
									"["+medioPago.getTransaccion().getOperacionTransaccionFormateado()+"] Id Valor revertido: " + valor.getIdValor()
									+ " Saldo devuelto: " + importe
									+ " - Saldo original: " + saldoOriginal
									+ " - Saldo modificado: " + saldoModificado);
						}
					}
				}
			}
	    	
	    	
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
			
			for (ShvCobTransaccionSinOperacion transaccion : listaTransacciones){
				transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
			}
			
			
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		} catch (PersistenciaExcepcion e1) {
			throw new NegocioExcepcion(e1.getMessage(),e1);
			
		}
        	
	}
	
	/**
	 * Verifica que si el cobro esta en estado COB_ERROR_COBRO o COB_ERROR_APROPIACION entonces
	 * recorre las transacciones. Si estas cumplen con unas condiciones entonces recorre los medios
	 * de pago de esa transaccion y revierte a los medios de pago Shiva.
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void realizarReversionMedioPagoValor(ShvCobCobro cobro) throws NegocioExcepcion {
		if (Estado.COB_ERROR_COBRO.equals(cobro.getWorkflow().getEstado()) || 
				Estado.COB_ERROR_APROPIACION.equals(cobro.getWorkflow().getEstado()) ||
				Estado.COB_ERROR_DESAPROPIACION.equals(cobro.getWorkflow().getEstado())) {
			
			Traza.auditoria(getClass(), "realizarReversionMedioPagoValor", "Se ejecuta la reversion de medios de pago Shiva "
					+ " pertenecientes al id de operacion " + cobro.getOperacion().getIdOperacion());
			
			// me guardo todos los valores de shiva
			Map<Long, ShvValValorSimplificado> valores = new Hashtable<Long, ShvValValorSimplificado>();
            for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
            	for(ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			
            		if(medioPago instanceof ShvCobMedioPagoShiva){
            			ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
            			
            			if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor()) && EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPagoShiva.getEstado())) {
            				Long idValor = medioPagoShiva.getIdValor();
            				
            				if (!valores.containsKey(idValor)) {
            					ShvValValorSimplificado valor = valorServicio.buscarValorSimplificado(idValor);
								valores.put(valor.getIdValor(), valor);
            				}
						}
            		}
            	}
            }


            //Recorro las transacciones y evaluo si hay que revertir los medios de pago shiva.
            //Considero los estados finales de una transaccion y los cuales son error. Ya que
            //los medios de pago shiva son lo primoer que se apropia
			for(ShvCobTransaccion transaccion :cobro.getTransaccionesOrdenadas()){
				
				if (!Validaciones.isObjectNull(transaccion.getFactura())){
					if (EstadoTransaccionEnum.DESAPROPIADA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DEUDA.equals(transaccion.getEstadoProcesamiento())
						) {
						
						//Recorro los medios de pago shiva
						cobro = revertirValoresPertenecientesATransaccion(cobro,transaccion.getNumeroTransaccion(), valores);
					}
					
				}else{
					if(EstadoTransaccionEnum.DESAPROPIADA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_TRATAMIENTO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DEUDA.equals(transaccion.getEstadoProcesamiento())) {
						
						//Recorro los medios de pago shiva
						cobro = revertirValoresPertenecientesATransaccion(cobro,transaccion.getNumeroTransaccion(), valores);
					}
				}
			}
			
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
	}
	
	/**
	 * Verifica que si el cobro esta en estado COB_ERROR_COBRO o COB_ERROR_APROPIACION entonces
	 * recorre las transacciones. Si estas cumplen con unas condiciones entonces recorre los medios
	 * de pago de esa transaccion y revierte a los medios de pago Shiva.
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void realizarReversionMedioPagoValor(ShvCobCobro cobro, Estado estado) throws NegocioExcepcion {
		if (Estado.COB_ERROR_COBRO.equals(estado) || 
				Estado.COB_ERROR_APROPIACION.equals(estado) ||
				Estado.COB_ERROR_DESAPROPIACION.equals(estado)) {
			
			Traza.auditoria(getClass(), "realizarReversionMedioPagoValor", "Se ejecuta la reversion de medios de pago Shiva "
					+ " pertenecientes al id de operacion " + cobro.getOperacion().getIdOperacion());
			
			// me guardo todos los valores de shiva
			Map<Long, ShvValValorSimplificado> valores = new Hashtable<Long, ShvValValorSimplificado>();
            for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
            	for(ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			
            		if(medioPago instanceof ShvCobMedioPagoShiva){
            			ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
            			
            			if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor()) && EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPagoShiva.getEstado())) {
            				Long idValor = medioPagoShiva.getIdValor();
            				
            				if (!valores.containsKey(idValor)) {
            					ShvValValorSimplificado valor = valorServicio.buscarValorSimplificado(idValor);
								valores.put(valor.getIdValor(), valor);
            				}
						}
            		}
            	}
            }


            //Recorro las transacciones y evaluo si hay que revertir los medios de pago shiva.
            //Considero los estados finales de una transaccion y los cuales son error. Ya que
            //los medios de pago shiva son lo primoer que se apropia
			for(ShvCobTransaccion transaccion :cobro.getTransaccionesOrdenadas()){
				
				if (!Validaciones.isObjectNull(transaccion.getFactura())){
					if (EstadoTransaccionEnum.DESAPROPIADA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DEUDA.equals(transaccion.getEstadoProcesamiento())
						) {
						
						//Recorro los medios de pago shiva
						cobro = revertirValoresPertenecientesATransaccion(cobro,transaccion.getNumeroTransaccion(), valores);
					}
					
				}else{
					if(EstadoTransaccionEnum.DESAPROPIADA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_TRATAMIENTO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.ERROR_DEUDA.equals(transaccion.getEstadoProcesamiento())) {
						
						//Recorro los medios de pago shiva
						cobro = revertirValoresPertenecientesATransaccion(cobro,transaccion.getNumeroTransaccion(), valores);
					}
				}
			}
			
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
	}
	
	/**
	 * Ambos estados deben estar completos. Cambia los estados de las facturas, los medios de pago y de las transacciones
	 * @param estadoRespuestaCalipso
	 * @param estadoRespuestaMic
	 * @param cobro
	 * @throws NegocioExcepcion 
	 */
	@Override
	public void aplicarCambiosEstadosSegunRespuestasDesapropiacion(String estadoRespuestaCalipso, String estadoRespuestaMic, ShvCobCobro cobro) throws NegocioExcepcion {
		// Premisa: ambos estados estan completos
		List<ShvCobTransaccion> listaTransaccionesOrdenada = cobro.getTransaccionesOrdenadas();
		
		for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
			
			String idTransaccion = transaccion.getIdTransaccion().toString();
			
			// Cambio estados de la facturas y Medios de pago
			if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.CALIPSO,null);
			}else{
				if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
					actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
				}
			}
			
			if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.MIC,null);
			}else{
				if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
					actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.MIC,null);
				}
			}
			
			// Cambio estado del tratamiento diferencia de tipo credito prox factura mic y calipso
			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
				if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso) 
						&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
					transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso) 
							&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
						transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					}
				}
				
				if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic) 
						&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
					transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic) 
							&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
						transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					}
				}
				
			}
			
			// Cambio estado de la transaccion
			if(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
				if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic) && TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic) && TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
					}else{
						if (existenMediosPagoMic(transaccion) || 
								(!Validaciones.isObjectNull(transaccion.getFactura()) && transaccion.getFactura() instanceof ShvCobFacturaMic )||
								(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia()) && 
										(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())))) {
							
							if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)) {
								transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
							}else {
								transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
							}
						}
						
						if (existenMediosPagoCalipso(transaccion) || 
								(!Validaciones.isObjectNull(transaccion.getFactura()) && transaccion.getFactura() instanceof ShvCobFacturaCalipso )||
								(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia()) && 
										(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())))) {
							
							if(!EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())){
								if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)) {
									transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
								} else {
									transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
								}
							}
						}
					}
				}
			}
			if (EstadoTransaccionEnum.listarEstadosError().contains(transaccion.getEstadoProcesamiento())){
				//Si la transaccion tiene un error salgo del for
				break;
			}
		}
		
		//U562163 - Cambio el estado del cobro
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);

		if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic) &&
				TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)) {
			cobroBatchServicio.cambiarEstadoCobro(cobro,Estado.COB_ERROR_COBRO,usuarioBatchCobroImputacion);
			realizarReversionMedioPagoValor(cobro);

		} else {
			if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)) {
				cobroBatchServicio.cambiarEstadoCobro(cobro,Estado.COB_ERROR_DESAPROPIACION,usuarioBatchCobroImputacion);
			}
		}
	}
	
	/**
	 * Verifica si todas las transacciones del cobro estan Apropiadas
	 * @param cobro
	 * @return
	 */
	private Boolean todasTransaccionesApropiadasOEnProcesoConfirmacion(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(!EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())
					&& !EstadoTransaccionEnum.APL_MANUAL_APROBADA.equals(transaccion.getEstadoProcesamiento())
					&& !EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				return false;
			}
		}
		return true;
	}
	
	private Boolean hayAlgunaTransaccionApropiada(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())
					|| EstadoTransaccionEnum.APL_MANUAL_APROBADA.equals(transaccion.getEstadoProcesamiento())
					|| EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param estadoRespuestaCalipso
	 * @param estadoRespuestaMic
	 * @param cobro
	 * @throws NegocioExcepcion 
	 */
	@Override
	public void aplicarCambiosEstadosSegunRespuestasConfirmacion(String estadoRespuestaCalipso, String estadoRespuestaMic, ShvCobCobro cobro) throws NegocioExcepcion {
		// Premisa: ambos estados estan completos
		List<ShvCobTransaccion> listaTransaccionesOrdenada = cobro.getTransaccionesOrdenadas();
		
		for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
			String idTransaccion = transaccion.getIdTransaccion().toString();
			
			// Cambio estados de la facturas y Medios de pago
			if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.CONFIRMADA, SistemaEnum.CALIPSO,null);
			}else{
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO,null);
			}
			
			if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.CONFIRMADA, SistemaEnum.MIC,null);
			}else{
				actualizarEstadoFacturaYMP(cobro, idTransaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.MIC,null);
			}
			
			// Cambio estado del tratamiento diferencia de tipo credito porx factura mic y calipso
			if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
				if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso) 
						&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
					transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso) 
							&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
						transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					}
				}
				
				if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic) 
						&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
					transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic) 
							&& (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()))){
						transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					}
				}
				
			}
			
			// Cambio estado de la transaccion
			if(EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic) && TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic) && TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_CONFIRMACION);
					}else{
						if (existenMediosPagoMic(transaccion) || 
								(!Validaciones.isObjectNull(transaccion.getFactura()) && transaccion.getFactura() instanceof ShvCobFacturaMic )||
								(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia()) && 
										(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())))) {
							
							if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)) {
								transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
							}else {
								transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_CONFIRMACION);
							}
						}
						
						if (existenMediosPagoCalipso(transaccion) || 
								(!Validaciones.isObjectNull(transaccion.getFactura()) && transaccion.getFactura() instanceof ShvCobFacturaCalipso )||
								(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia()) && 
										(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
										|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())))) {
							
							if(!EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
								if(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuestaCalipso)) {
									transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_CONFIRMACION);
								} else {
									transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void confirmarMediosPagoShivaCompensacionYTratamientoDiferenciaYSaldoAPagar(ShvCobCobro cobro) {
		
		for(ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()){
			ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
			ShvCobFactura factura = transaccion.getFactura();
			
			for(ShvCobMedioPago mp : transaccion.getMediosPago()){
				if (mp instanceof ShvCobMedioPagoShiva && EstadoFacturaMedioPagoEnum.APROPIADA.equals(mp.getEstado())){
					mp.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				} else
				if (mp instanceof ShvCobMedioPagoCompensacionProveedor && EstadoFacturaMedioPagoEnum.APROPIADA.equals(mp.getEstado())){
					mp.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				} else
				if (mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto && EstadoFacturaMedioPagoEnum.APROPIADA.equals(mp.getEstado())){
					mp.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				} else if(TipoMedioPagoEnum.SALDO_A_COBRAR.getIdTipoMedioPago().equals(mp.getTipoMedioPago().getIdTipoMedioPago())){
					mp.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
					if (!Validaciones.isObjectNull(transaccion.getFactura()) && EstadoFacturaMedioPagoEnum.APROPIADA.equals(transaccion.getFactura().getEstado())){
						transaccion.getFactura().setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
					}
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
				} else if(mp instanceof ShvCobMedioPagoUsuario && EstadoFacturaMedioPagoEnum.APROPIADA.equals(mp.getEstado())){
					if (!Validaciones.isObjectNull(transaccion.getFactura()) && transaccion.getFactura() instanceof ShvCobFacturaUsuario){
						mp.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
						
					}
				}
			} //FIN FOR MP

			if (!Validaciones.isObjectNull(tratamientoDiferencia) ){
				if (!TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
						&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())
					&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamientoDiferencia.getEstado())){
					tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				}
				
				if (EstadoFacturaMedioPagoEnum.CONFIRMADA.equals(tratamientoDiferencia.getEstado())
						&& todosMediosPagoConfirmados(transaccion)){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
				}
			} else if (!Validaciones.isObjectNull(factura)){
				
					if (factura instanceof ShvCobFacturaUsuario && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())){
						factura.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
					}
					
					if (EstadoFacturaMedioPagoEnum.CONFIRMADA.equals(factura.getEstado())
							&& todosMediosPagoConfirmados(transaccion)){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
					}
			}
		}//fin for transacciones
	}
	
	/**
	 * Verifica si todas las transacciones del cobro estan Confirmadas
	 * @param cobro
	 * @return
	 */
	@Override
	public Boolean todasTransaccionesConfirmadas(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(!EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Metodo que informa a contabilidad, scard
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void informarAContabilidadScard(ShvCobCobro cobro) throws NegocioExcepcion {

		// Scard
		double fechaHoraInicioNanoTimeScard = System.nanoTime();
		scardServicio.inicializarDocumentoOperacionCobro(cobro.getOperacion());
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago Shiva asociado a una factura", fechaHoraInicioNanoTimeScard);
		
		double fechaHoraInicioNanoTimeContabilidad = System.nanoTime();
		//Lista de valores del cobro
		HashMap<Long,VistaSoporteResultadoBusquedaValor> listaValores = new HashMap<Long,VistaSoporteResultadoBusquedaValor>();
		// Contabilidad
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			
			//
			// Ahora solo enviamos a contabilizar las transacciones en estado Confirmada, dado que podemos tener
			// cobros parciales.
			//
			if (EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())) {
			
				if (!transaccion.haySaldoAPagarOSaldoACobrar()){
					
					if (MonedaEnum.PES.equals(cobro.getMonedaOperacion())){
						
						List<ShvCobTransaccion> listaTransaccionesDC = null;
						if (!Validaciones.isObjectNull(transaccion.getFactura()) 
								&& transaccion.getFactura() instanceof ShvCobFacturaCalipso
								&& MonedaEnum.DOL.equals(((ShvCobFacturaCalipso)transaccion.getFactura()).getMoneda())){
							
							listaTransaccionesDC = cobro.getTransaccionesDC(transaccion.getNumeroTransaccion());
						}
						//Si hay transacciones por diferencia de cambio, contabilizo los documentos de dichas transacciones
						
						if (Validaciones.isCollectionNotEmpty(listaTransaccionesDC)){
							
							for (ShvCobTransaccion transaccionDC : listaTransaccionesDC) {
								
								contabilizarDocumentos(cobro, transaccionDC, listaValores, true);
							}
						} else {
							//Sino contabilizo los documentos normalmente.
							contabilizarDocumentos(cobro,transaccion,listaValores,false);
							
						}
					} else {
						//TODO: ESTE IF NO VA, PERO SI CONTABILIZARDOCUMENTOS
						if (!EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento())
								&& !EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())){
							contabilizarDocumentos(cobro,transaccion,listaValores,false);
						}
					}
				}
			}
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago Shiva asociado a una factura", fechaHoraInicioNanoTimeContabilidad);
		// Fin Contabilidad
	}
	
	/**
	 * 
	 * @param cobro
	 * @return
	 */
	private boolean hayCompensacionesSap(ShvCobCobro cobro){
		
		if (!Validaciones.isObjectNull(cobro)){
			for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
				
				for (ShvCobMedioPago mp : transaccion.getMediosPago()) {
					
					if (mp instanceof ShvCobMedioPagoCompensacionProveedor
							|| mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
						return true;
					}
				}
			}			
		}
		return false;
	}
	
	/**
	 * 
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void enviarMailSap(ShvCobCobro cobro) throws NegocioExcepcion {
		
		try {
			boolean haympProveedor = hayMedioPagoComProveedores(cobro);
			
			List<ResultadoBusquedaDatosImputacionSap> listaDatos;
			listaDatos = (List<ResultadoBusquedaDatosImputacionSap>) cobroDao.buscarDatosImputacionSap(cobro.getOperacion().getIdOperacion(),haympProveedor);
			
			String destinatarios = parametroServicio.getValorTexto(Constantes.DESTINATARIOS_MAIL_CAP);
			
			String cuerpo = "Listado de clientes incluidos en el cobro: <br>";
			String asunto = "Cobro con compensación pendiente: K$ " + listaDatos.get(0).getIdCompensacion().toString() + " - Proveedor: ";
			asunto += listaDatos.get(0).getNumeroProveedor() + " / " + listaDatos.get(0).getRazonSocial() ;
			
			for (ResultadoBusquedaDatosImputacionSap datos : listaDatos){
				cuerpo+= " Cliente: " + datos.getNumCliente() + " / " + datos.getRazonSocial() + " <br> ";
			}
			
			//Envia el mail a CAP
			Mail mail = new Mail(destinatarios,null,asunto,new StringBuffer(cuerpo));
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			ArchivoByteArray byteArray = cobroBatchServicio.generarPdfResumenCompensacion(cobro.getIdCobro());
			listaAdjuntos.add(new Adjunto(byteArray.getByteArray(), byteArray.getNombreArchivo(), "pdf"));
			mail.setAdjuntos(listaAdjuntos);
			mailServicio.sendMail(mail);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	/**
	 * Verifica si existe al menos una transaccion en estado Error en confirmacion
	 * @param cobro
	 * @return
	 */
	private Boolean existenTransaccionesErrorConfirmacion(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Envia mail y genera tarea
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void evaluarEnvioMailYGenerarTarea(ShvCobCobro cobro, Estado estadoCobro) throws NegocioExcepcion{
		
		try {
			
			if (!Validaciones.isObjectNull(estadoCobro)){
				if (Estado.COB_COBRADO.equals(estadoCobro) 
						|| Estado.COB_ERROR_CONFIRMACION.equals(estadoCobro) 
						|| Estado.COB_ERROR_COBRO.equals(estadoCobro) 
						|| Estado.COB_ERROR_APROPIACION.equals(estadoCobro) 
						|| Estado.COB_ERROR_DESAPROPIACION.equals(estadoCobro) 
						|| Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estadoCobro)
						|| Estado.COB_PARCIALMENTE_EN_ERROR.equals(estadoCobro)
						|| Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(estadoCobro)
						) {
					
						enviarMailyGenerarTarea(cobro,estadoCobro);
				}
			} else
			if (Estado.COB_COBRADO.equals(cobro.getWorkflow().getEstado()) 
					|| Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado()) 
					|| Estado.COB_ERROR_COBRO.equals(cobro.getWorkflow().getEstado()) 
					|| Estado.COB_ERROR_APROPIACION.equals(cobro.getWorkflow().getEstado()) 
					|| Estado.COB_ERROR_DESAPROPIACION.equals(cobro.getWorkflow().getEstado()) 
					|| Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(cobro.getWorkflow().getEstado())
					|| Estado.COB_COBRADO_PARCIALMENTE.equals(cobro.getWorkflow().getEstado())
					|| Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(cobro.getWorkflow().getEstado())
					) {
	
					enviarMailyGenerarTarea(cobro,null);
			}
		
		} catch (Exception e){
			Traza.error(ImputacionCobrosBatchRunner.class, 
					"No se ha podido generar la tarea y enviar el mail. operacion id: " 
							+ cobro.getOperacion().getIdOperacion(), e);
			
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	private boolean soloExistenMedioPagoShiva(ShvCobTransaccion transaccion) {
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			if(!(medioPago instanceof ShvCobMedioPagoShiva)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Revierte los valores asociados a los medios de pago Shiva de la transaccion. Los medios de pago del cobro
	 * pasan a estado DESAPROPIADO y los medios de pago del descobro a estado DESCOBRO.
	 */
	private ShvCobCobro revertirValoresPertenecientesATransaccion(ShvCobCobro cobro, Integer numeroTransaccion, Map<Long, ShvValValorSimplificado> valores)
            throws NegocioExcepcion {
		
		if(!Validaciones.isObjectNull(cobro)){
			for(ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()){
				String idOperacionTransaccion = transaccion.getOperacionTransaccionFormateado();
	    		
				if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
					
					for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
						
						// Si es un valor shiva revierto el importe al valor
						if(medioPago instanceof ShvCobMedioPagoShiva){
							BigDecimal saldoOriginal = null;
							BigDecimal saldoModificado = null;
							
							ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
			 				BigDecimal importe = medioPagoShiva.getImporte();
							
							ShvValValorSimplificado valor = valores.get(medioPagoShiva.getIdValor());
							
							if (!Validaciones.isObjectNull(valor)) {
								saldoOriginal = valor.getSaldoDisponible();
								
								valor = valorServicio.revertirValoresPertenecientesATransaccion(valor, 
											importe, cobro,  numeroTransaccion, true);
								valores.put(valor.getIdValor(), valor);
							
								saldoModificado = valor.getSaldoDisponible();
								
								//cambio de estado a DESAPROPIADA el medio de pago
								medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
								
								Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
										"["+idOperacionTransaccion+"] Id Valor revertido: " + valor.getIdValor()
										+ " Saldo devuelto: " + importe
										+ " - Saldo original: " + saldoOriginal
										+ " - Saldo modificado: " + saldoModificado);
							} 
						}
					}
				}
			}
		} else {
			Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
					"Se ha recibido el objeto de cobro: null");
		}
		
		return cobro;
	}
	
	
	/**
	 * Verifica que todos los medios de pago de la transaccion esten en estado CONFIRMADA
	 * @param transaccion
	 * @return
	 */
	private boolean todosMediosPagoConfirmados(ShvCobTransaccion transaccion) {
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(!EstadoFacturaMedioPagoEnum.CONFIRMADA.equals(medioPago.getEstado())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param cobro
	 * @param transaccion
	 * @param listaValores
	 * @param esDiferenciaCambio
	 * @throws NegocioExcepcion
	 */
	private void contabilizarDocumentos(ShvCobCobro cobro, ShvCobTransaccion transaccion,
			HashMap<Long,VistaSoporteResultadoBusquedaValor> listaValores, boolean esDiferenciaCambio) throws NegocioExcepcion{
		
		ShvCobFactura factura=null;
		
		BigDecimal tipoCambioFactura = null;
		BigDecimal tipoCambioMedioPago = null;
		BigDecimal importeTotalMedioPagoEnPesos = null;
		BigDecimal importeTotalFacturaEnPesos = null;
		
		factura = transaccion.getFactura();
		
		if (!esDiferenciaCambio) {
			if (!Validaciones.isObjectNull(factura)) {
			
				if (!(factura instanceof ShvCobFacturaUsuario)){
					
					if (MonedaEnum.DOL.equals(cobro.getMonedaOperacion())){
						//Si la moneda del cobro es en DOLARES, guardo el tipo de cambio de la factura
						tipoCambioFactura = ((ShvCobFacturaCalipso) factura).getTipoCambio();
						importeTotalFacturaEnPesos = factura.getImporteCobrar().multiply(tipoCambioFactura);
					}
					
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarFactura(factura, true, cobro.getWorkflow().getUsuarioAlta(),
							cobro.getIdCobro(), transaccion.getOperacionTransaccionFicticioFormateado(), cobro.getWorkflow().getUsuarioAlta());
				}
			}
		} else {
			factura = transaccion.getFacturaDC();
		
			Traza.advertencia(getClass(), "contabilizando la factura " + factura.getIdFactura());
			cobroBatchSoporteImputacionContabilidadServicio.contabilizarFacturaEnMonedaOrigen((ShvCobFacturaCalipso)factura, true, cobro.getWorkflow().getUsuarioAlta(),
					cobro.getIdCobro(), transaccion.getOperacionTransaccionFicticioFormateadoDC(), cobro.getWorkflow().getUsuarioAlta());
		}
			
		for (ShvCobMedioPago mp : transaccion.getMediosPago()) {
			
			VistaSoporteResultadoBusquedaValor valor = null;
			
			//Si es un medio de pago Shiva
			if (mp instanceof ShvCobMedioPagoShiva) {
				ShvCobMedioPagoShiva mpShiva = ((ShvCobMedioPagoShiva)mp);
				if (listaValores.containsKey(mpShiva.getIdValor())) {
					//Si esta en la lista de valores lo saco de la lista
					valor=listaValores.get(mpShiva.getIdValor());
				} else {
					//Si no esta en la lista de valores lo busco en la base y lo agrego a la lista
					VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
					filtro.setIdValor(String.valueOf(mpShiva.getIdValor()));
					
					List<VistaSoporteResultadoBusquedaValor> lista = vistaSoporteServicio.buscarValores(filtro);
					if((Validaciones.isCollectionNotEmpty(lista))){
						valor = lista.get(0);
						Traza.advertencia(getClass(), "Se busco el valor id: " + valor.getIdValor() + " para contabilizar el cobro id: " + cobro.getOperacion().getIdOperacion());
						listaValores.put(valor.getIdValor(),valor);
					}
				}
			}
			
			Traza.advertencia(getClass(), "contabilizando el medio de pago " + mp.getIdMedioPago());
			
			if (!Validaciones.isObjectNull(factura)) {
					
				if (!(factura instanceof ShvCobFacturaUsuario)) {
				
					// transaccion con factura
					if (!esDiferenciaCambio) {
	
						if (MonedaEnum.DOL.equals(cobro.getMonedaOperacion())) {
						
							tipoCambioMedioPago = cobroBatchSoporteImputacionContabilidadServicio.calcularTipoCambioMedioPago(mp);
							
							if (Validaciones.isObjectNull(importeTotalMedioPagoEnPesos)){
								
								importeTotalMedioPagoEnPesos = new BigDecimal(0);
							}
							
							importeTotalMedioPagoEnPesos = importeTotalMedioPagoEnPesos.add(mp.getImporte().multiply(tipoCambioMedioPago));
						}
						cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFactura(mp, true, cobro.getWorkflow().getUsuarioAlta(), cobro.getIdCobro(), transaccion.getOperacionTransaccionFicticioFormateado(), valor, false);
					
					} else {
						cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFactura(mp, true, cobro.getWorkflow().getUsuarioAlta(), cobro.getIdCobro(), transaccion.getOperacionTransaccionFicticioFormateadoDC(), valor, true);
					}
				} else {
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFacturaUsuario(
							mp, true, cobro.getWorkflow().getUsuarioAlta(), 
							cobro, transaccion.getOperacionTransaccionFicticioFormateado(), null, valor);
				}
			} else {
				// transaccion sin factura
				if (!esDiferenciaCambio){
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoTratamiento(mp, true, cobro.getWorkflow().getUsuarioAlta(), cobro, transaccion.getOperacionTransaccionFicticioFormateado(), null, valor);
				} else {
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoTratamiento(mp, true, cobro.getWorkflow().getUsuarioAlta(), cobro, transaccion.getOperacionTransaccionFicticioFormateadoDC(), null, valor);
					
				}
			}
		}
		
		//pregunto si los importes en pesos de factura y mp no son nulos y si son diferentes, debo hacer el ajuste contable
		if (!Validaciones.isObjectNull(importeTotalFacturaEnPesos) && !Validaciones.isObjectNull(importeTotalMedioPagoEnPesos)
				&& !importeTotalFacturaEnPesos.equals(importeTotalMedioPagoEnPesos)){
			
			BigDecimal diferenciaImporteEnPesos = new BigDecimal(0);
			
			//Si el importe de la factura en pesos es mayor al importe del medio de pago en pesos, diferenciaImporteEnPesos tiene que ser negativo.
			if (importeTotalFacturaEnPesos.compareTo(importeTotalMedioPagoEnPesos) > 0 ){
				
				diferenciaImporteEnPesos = importeTotalMedioPagoEnPesos.subtract(importeTotalFacturaEnPesos);
				diferenciaImporteEnPesos = diferenciaImporteEnPesos.setScale(2, RoundingMode.HALF_EVEN);
				
				cobroBatchSoporteImputacionContabilidadServicio.contabilizarDocumentoEnDolar(factura,diferenciaImporteEnPesos,
						Long.valueOf(cobro.getIdCobro().longValue()),String.valueOf(transaccion.getOperacionTransaccionFicticioFormateado())
						, cobro.getWorkflow().getUsuarioAlta(),true);
			
				//Si el importe del medio de pago en pesos es mayor al de la factura en pesos , diferenciaImporteEnPesos tiene que ser positivo.
			} else if (importeTotalMedioPagoEnPesos.compareTo(importeTotalFacturaEnPesos) > 0 ){
				
				diferenciaImporteEnPesos = importeTotalMedioPagoEnPesos.subtract(importeTotalFacturaEnPesos);
				diferenciaImporteEnPesos = diferenciaImporteEnPesos.setScale(2, RoundingMode.HALF_EVEN);
				
				cobroBatchSoporteImputacionContabilidadServicio.contabilizarDocumentoEnDolar(factura,diferenciaImporteEnPesos,
						Long.valueOf(cobro.getIdCobro().longValue()),String.valueOf(transaccion.getOperacionTransaccionFicticioFormateado())
						, cobro.getWorkflow().getUsuarioAlta(),true);
			}
			
		}
		
	}


	
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	private boolean hayMedioPagoComProveedores(ShvCobCobro cobro) {
		
		if (!Validaciones.isObjectNull(cobro)){
			for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
				
				for (ShvCobMedioPago mp : transaccion.getMediosPago()) {
					
					if (mp instanceof ShvCobMedioPagoCompensacionProveedor){
						return true;
					}
				}
			}			
		}
		return false;
	}
	
	@Override
	public void enviarMailyGenerarTarea(ShvCobCobro cobro,Estado estadoCobro) throws NegocioExcepcion {
		
		try {
			
			if (Validaciones.isObjectNull(estadoCobro)){
				estadoCobro = cobro.getWorkflow().getEstado();
			}
			
			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) cobroDao.buscarDatosImputacion(cobro.getOperacion().getIdOperacion());
			
			String analista = listaDatos.get(0).getIdAnalista();
			String nombreAnalista = listaDatos.get(0).getAnalista();
			String copropietario = listaDatos.get(0).getCopropietario();
			
			String conCopia = Utilidad.agregarAnalistaTeamComercialACopropietario(listaDatos, copropietario);
			
			String cuerpo = Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuerpo.listado.clientes");
			String asunto = "";
			String nroCliente = listaDatos.get(0).getNumCliente().toString();
			String razonSocial = listaDatos.get(0).getRazonSocial();
			String empresa = listaDatos.get(0).getEmpresa();
			String cuit = listaDatos.get(0).getCuit();
			String empresaCuitClienteYRazonSocial = Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + empresa + " / " +
													Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cuit + " / " + 
													Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + nroCliente + " / " + razonSocial;
			Long idOperacion = cobro.getOperacion().getIdOperacion();
			BigDecimal importe = listaDatos.get(0).getImporte();
			
			String analistaRechazoCobroAplManual = listaDatos.get(0).getUsuarioRechazoAplicacionManual();
			
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			String observaciones = listaDatos.get(0).getObservacionesAplicacionManual();
			
			boolean hayAplicacionManual = false;
			boolean aplicacionManualSapONet = false;
			boolean cobroAutomaticoValores = false;
			SistemaEnum sistemaDestinoAplicacionManual = null;
	
			// Si es aplicacion manual 
			for (ShvCobTransaccion transaccion : cobro.getOperacion().getTransacciones()) {
				hayAplicacionManual = transaccion.hayAplicacionManual();
				aplicacionManualSapONet = transaccion.hayAplicacionManualSapONet();
				if (hayAplicacionManual) {
					sistemaDestinoAplicacionManual = transaccion.getTratamientoDiferencia().getSistemaOrigen();
				}
				break;
			}
	
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobro.getIdCobro());
			cobroAutomaticoValores = cobroModelo.esCobroAutomaticoValores();
			
			if (hayAplicacionManual){
				
				List<ShvCobEdCobroAdjunto> listaAdjuntoAplManual = new ArrayList<ShvCobEdCobroAdjunto>();
				listaAdjuntoAplManual = cobroOnlineDao.buscarAdjuntosDelCobroOnline(cobro.getIdCobro());
				
				for (ShvCobEdCobroAdjunto shvAdjunto : listaAdjuntoAplManual){
					
					if (MotivoAdjuntoEnum.APLICACION_MANUAL.equals(shvAdjunto.getPk().getMotivoAdjunto())
							|| (Estado.COB_COBRADO.equals(estadoCobro) && MotivoAdjuntoEnum.APLI_MANUAL_CONF.equals(shvAdjunto.getPk().getMotivoAdjunto()))
							){
						listaAdjuntos.add(new Adjunto(shvAdjunto.getPk().getDocumentoAdjunto().getArchivoAdjunto(),shvAdjunto.getPk().getDocumentoAdjunto().getNombreArchivo(), ""));
					}
				}
			}
			
			for (ResultadoBusquedaDatosImputacion datos : listaDatos){
	
				cuerpo+=Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + empresa + " / " +
						Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cuit + " / " + 
						Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + datos.getNumCliente() + " / " + datos.getRazonSocial();
				
				if (hayAplicacionManual) {
					if (!Validaciones.isNullEmptyOrDash(datos.getSegmento())) {
						cuerpo += " / Segmento: " + datos.getSegmento();
					} else {
						cuerpo += " / Segmento: ";
					}
				}
				cuerpo += " <br> ";
			}
			
			/**
			 * Según el estado del cobro, crea la tarea y envía el mail correspondiente
			 */
			
			
			//Error en cobro
			if (Estado.COB_ERROR_COBRO.equals(estadoCobro)){
				if (hayAplicacionManual){
					if (!Validaciones.isNullOrEmpty(analistaRechazoCobroAplManual)){
						TipoTareaEnum tipoTarea = this.retornaTareaAplicacionManual( sistemaDestinoAplicacionManual, cobro.getWorkflow().getEstado());
						if (aplicacionManualSapONet){
							asunto = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.aplicacion.manual.rechazada.referente.caja"),
								sistemaDestinoAplicacionManual.getDescripcion()
							);
						} else {
							asunto = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.aplicacion.manual.rechazada.referente.op.externas"),
								sistemaDestinoAplicacionManual.getDescripcion()
							);
						}
						
	//					cuerpo = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.cuerpo.aplicacion.manual.rechazada");
	//					
	//					for (ResultadoBusquedaDatosImputacion datos : listaDatos) {
	//						cuerpo+=Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + empresa + " / " + idOperacion + " / " +
	//								Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cuit + " / " + 
	//								Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + datos.getNumCliente() + " / " + datos.getRazonSocial() + " <br> ";
	//					}
	//					
						String usuarioCreacion = cobroModelo.getUsuarioRechazoAplicacionManual();
						asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
						
						crearTareaPendiente(cobro, analista, importe, tipoTarea, nroCliente, razonSocial,cuerpo, false, TipoPerfilEnum.ANALISTA_COBRANZA,null,null,usuarioCreacion);
						
						cuerpo += Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.cuerpo.aplicacion.manual.rechazada.usuario.que.rechazo");
						cuerpo += analistaRechazoCobroAplManual + " - " + observaciones;
					} else {
						if(!cobroAutomaticoValores){
							
							
							crearTareaPendiente(cobro, analista,importe,TipoTareaEnum.COB_REV_COB_ERR, nroCliente, razonSocial,cuerpo, false, TipoPerfilEnum.ANALISTA_COBRANZA,null,null, null);
							
							//limpio la lista de adjuntos, ya que sólo se adjuntará el export del cobro
							listaAdjuntos =  new ArrayList<Adjunto>();
							
							asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.nueva.tarea.cobro.rechazado");
							asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
							
						}
					}
						
				}else{
					
					if(!cobroAutomaticoValores){
						
						crearTareaPendiente(cobro, analista,importe,TipoTareaEnum.COB_REV_COB_ERR, nroCliente, razonSocial,cuerpo, false, TipoPerfilEnum.ANALISTA_COBRANZA, null, null, null);
						
						asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.nueva.tarea.cobro.rechazado");
						asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
						
					}
					
				}
			}
					
			//Error en apropiación
			if (Estado.COB_ERROR_APROPIACION.equals(estadoCobro)){
				if(!cobroAutomaticoValores){
					crearTareaPendiente(cobro, analista,importe,TipoTareaEnum.COB_REV_COB_APR,nroCliente, razonSocial,cuerpo, false,TipoPerfilEnum.ANALISTA_COBRANZA, null, null, null);
					asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.nueva.tarea.cobro.rechazado");
					asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
				}
			}
			
			//Error en desapropiación
			if (Estado.COB_ERROR_DESAPROPIACION.equals(estadoCobro)){
				
				crearTareaPendiente(cobro, analista,importe,TipoTareaEnum.COB_REV_COB_DES,nroCliente, razonSocial,cuerpo, false,TipoPerfilEnum.ANALISTA_COBRANZA,null,null,null);
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.nueva.tarea.cobro.imputado.parcialmente");
				asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
				
				
				cuerpo = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.cuerpo.cobro.error.desapropiacion") + cuerpo;
				
			}
	
			//Error en confirmación
			if (Estado.COB_ERROR_CONFIRMACION.equals(estadoCobro) || Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(estadoCobro)){
				
				crearTareaPendiente(cobro, analista,importe,TipoTareaEnum.COB_REV_COB_CON,nroCliente, razonSocial,cuerpo, false,TipoPerfilEnum.ANALISTA_COBRANZA,null,null,null);
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.nueva.tarea.cobro.pendiente.confirmacion");
				asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
				
	//			cuerpo = "El cobro no pudo ser confirmado. Por favor, asegúrese de realizar las tareas necesarias "
	//					+ "para confirmar el mismo. Recuerde que un cobro no confirmado no se contabilizará ni "
	//					+ "emitirá recibo por la operación. <br>" + cuerpo;
				
				cuerpo = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.cuerpo.cobro.pendiente.confirmacion") + cuerpo;
			}
			
			//Cobrado o confirmado con error
			if (Estado.COB_COBRADO.equals(estadoCobro)
					|| Estado.COB_CONFIRMADO_CON_ERROR.equals(estadoCobro)){
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.imputado");
				asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
			}
			
			//Cobrado parcialmente
			if (Estado.COB_PARCIALMENTE_EN_ERROR.equals(estadoCobro)){
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.imputado.parcialmente");
				asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
				
				cuerpo = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.cuerpo.cobro.imputado.parcialmente") + " <br> " + cuerpo;
			}
			
			//cobro pendiente confirmación manual
			if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estadoCobro)){
				
				asunto = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.con.imputacion.manual.pendiente"),
					sistemaDestinoAplicacionManual.getDescripcion()
				);
				asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
				
				ByteArrayOutputStream outputStream = null;
				
				outputStream = new ByteArrayOutputStream();
				HSSFWorkbook workbook = cobroOnlineServicio.exportarCobro(cobro.getIdCobro(),estadoCobro);
				workbook.write(outputStream);
				
				String nombreAdjunto = Utilidad.EMPTY_STRING;
				
				if (!Validaciones.isObjectNull(cobro.getOperacion().getIdOperacion())) {
					
					nombreAdjunto = "ID Operación Cobro " + Utilidad.rellenarCerosIzquierda(cobro.getOperacion().getIdOperacion().toString().trim(), 7);
					
				} else {
					
					nombreAdjunto = "ID Operación Cobro XXXXXXX";
					
				}
				
				listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreAdjunto, "xls"));
				
				TipoPerfilEnum tipoPerfil = null;
				TipoTareaEnum tipoTarea = null;
	
				if (aplicacionManualSapONet) {
					tipoPerfil = TipoPerfilEnum.REFERENTE_CAJA;
				} else {
					tipoPerfil = TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS;
				}
				cuerpo = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("mail.analista.nombre"),
					nombreAnalista
					) + cuerpo;
				tipoTarea = this.retornaTareaAplicacionManual(sistemaDestinoAplicacionManual, cobro.getWorkflow().getEstado());
				
				crearTareaPendiente(cobro, analista,importe, tipoTarea, nroCliente, razonSocial,cuerpo, true,tipoPerfil," - " + asunto,listaAdjuntos, null);
				
			} else {
	
				//Envia el mail del resultado de la imputación
				enviarMail(analista, conCopia, asunto, cuerpo, cobro.getIdCobro(), listaAdjuntos, cobro.getOperacion().getIdOperacion(),estadoCobro);
			}
	
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * retorno la tarea aplicacion manual segun el sistemas destino y el estado
	 * @param sistemaDestino
	 * @param estado
	 * @return
	 */
	private TipoTareaEnum retornaTareaAplicacionManual(SistemaEnum sistemaDestino, Estado estado) {
		TipoTareaEnum tipoTarea = null;
		
		
		if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
			tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
			
		} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
			tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN;
			
		} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
			tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL;
		}
		
		/*
		if (SistemaEnum.CABLEVISION.equals(sistemaDestino)) {
			if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL_CV;
			} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN_CV;
			} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLI_MAN_CV;
			}
		} else if (SistemaEnum.SAP.equals(sistemaDestino)) {
			if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL_SAP;
			} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN_SAP;
			} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLI_MAN_SAP;	
			}
		} else if (SistemaEnum.NEGOCIO_NET.equals(sistemaDestino)) {
			if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL_NET;
			} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN_NET;
			} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLI_MAN_NET;
			}
		} else if (SistemaEnum.NEXTEL.equals(sistemaDestino)) {
			if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL_NX;
			} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
				tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN_NX;
			} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
				tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLI_MAN_NX;
			}
		}*/
		return tipoTarea;
	}
	
	//Mail de desapropiación Manual Pendiente
	/*Destinatario: Grupo de Referentes de Caja o Grupo de Referentes de Operaciones Externas según corresponda
	En copia: N/A
	Asunto: Nueva Tarea - Desapropiación de Cobro con Imputación Manual Pendiente (Sociedad/Sistema): id operación - Empresa/CUIT/Nro de cliente/Razón Social
	Cuerpo: analista que creó la tarea, listado de clientes incluidos en el cobro (Empresa/CUIT/Nro de cliente/Razón Social/Segmento)/ID de operación en sistema externo
	Adjunto: archivo adjunto en el cobro
	*/
	private void enviarMailyGenerarTareaDesapropiacionManual(
		Long idOperacion,
		Long grupo,
		SociedadEnum sociedad,
		SistemaEnum sistema,
		Long idCobro
	) throws NegocioExcepcion {
		
		try {
			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) cobroDao.buscarDatosImputacion(idOperacion);
			Set<ShvCobEdCodigoOperacionExterna> listaCodigosOperacionesExternas = null;
			String analista = listaDatos.get(0).getIdAnalista();
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();

			StringBuffer cuerpo = new StringBuffer(
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuerpo.listado.desapropiacion"),
					listaDatos.get(0).getIdAnalista()
			));
			StringBuffer asunto = new StringBuffer();
	
			PerfilFiltro perfilFiltro = new PerfilFiltro();
	
			asunto.append(Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.con.imputacion.manual.desapropiacion"),
				sociedad.getDescripcion(),
				sistema.getDescripcion(),
				idOperacion.toString(),
				listaDatos.get(0).getEmpresa(),
				listaDatos.get(0).getCuit(),
				listaDatos.get(0).getNumCliente().toString(),
				listaDatos.get(0).getRazonSocial()
			));
	
			perfilFiltro.setEmpresa(listaDatos.get(0).getEmpresa());
			perfilFiltro.setSistema(sistema.name());
			perfilFiltro.setSociedad(sociedad.name());
			perfilFiltro.setTipoTarea(TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.name());
			perfilFiltro.setSegmento(listaDatos.get(0).getSegmentoCobro());

			TipoPerfilEnum perfil = paramRespWfTareaServicio.buscarPerfil(perfilFiltro);

			listaCodigosOperacionesExternas = cobroOnlineDao.buscarCobroSimplificadoCodigoExternoOperacion(idOperacion).getCodigosOperacionesExternas();

			List<String> cod = new ArrayList<>();
			
		
			if (Validaciones.isCollectionNotEmpty(listaCodigosOperacionesExternas)) {
				for (ShvCobEdCodigoOperacionExterna ex :listaCodigosOperacionesExternas) {
					cod.add(ex.getCodigoOperacionExterna());
				}
				
			}
			for (ResultadoBusquedaDatosImputacion datos : listaDatos) {
				cuerpo.append(
					Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("cobro.mail.cuerpo.detalle.imputacion.aplicacion.manual"),
					listaDatos.get(0).getEmpresa(),
					Validaciones.isNullEmptyOrDash(datos.getCuit()) ? "" : datos.getCuit(),
					datos.getNumCliente().toString(),
					datos.getRazonSocial(),
					listaDatos.get(0).getSegmento(), // Del cliente,
					Validaciones.isCollectionNotEmpty(cod) ? StringUtils.join(cod, " , ") : "")
				);

				cuerpo.append("</br>");
			}
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);

			this.crearTareaPendiente(
				cobro,
				analista,
				listaDatos.get(0).getImporte(),
				sociedad,
				sistema,
				TipoTareaEnum.COB_DESAPRO_APLI_MANUAL,
				listaDatos.get(0).getNumCliente().toString(),
				listaDatos.get(0).getRazonSocial(),
				cuerpo.toString(),
				true,
				perfil,
				" - " + asunto.toString(),
				listaAdjuntos,
				null
			);
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	/**
	 * 
	 * @param cobro
	 * @param analista
	 * @param importe
	 * @param tipoTarea
	 * @param nroCliente
	 * @param razonSocial
	 * @param cuerpo
	 * @param enviarMail
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendiente(ShvCobCobro cobro, String analista, BigDecimal importe,
			TipoTareaEnum tipoTarea, String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail, TipoPerfilEnum tipoPerfilEnum,
			String asunto, List<Adjunto> listaAdjuntos, String usuarioCreacion) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		
		
			if (TipoPerfilEnum.REFERENTE_CAJA.equals(tipoPerfilEnum) 
					|| TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.equals(tipoPerfilEnum)){
			usuarioBatchCobroImputacion = analista;
			analista = null;
		}
			if(TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.equals(tipoTarea)){
				tarea.setUsuarioCreacion(usuarioCreacion);
			} else{
				tarea.setUsuarioCreacion(usuarioBatchCobroImputacion);
			}
		
			
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
		
		tarea.setTipoTarea(tipoTarea);
		
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioAsignacion(analista);
		tarea.setPerfilAsignacion(tipoPerfilEnum.descripcion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
		
		tarea.setMonedaImporte(cobro.getMonedaOperacion().getSigno2());
		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));
		
		tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
		tarea.setNroCliente(nroCliente);
		tarea.setRazonSocial(razonSocial);
		tarea.setEnviarMail(enviarMail);
		
		tarea.setCuerpoMail(cuerpo);
		
		tarea.setIdOperacion(cobro.getOperacion().getIdOperacion());
		
		tarea.setAsuntoMail(asunto);
		
		if (Validaciones.isCollectionNotEmpty(listaAdjuntos)){
			tarea.setAdjuntosMail(listaAdjuntos);
		}
		
		tareaServicio.crearTareaResultadoImputacion(tarea);
	}
	private void crearTareaPendiente(ShvCobCobro cobro, String analista, BigDecimal importe, SociedadEnum sociedad, SistemaEnum sistema, TipoTareaEnum tipoTarea, 
			String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail, TipoPerfilEnum tipoPerfilEnum,
			String asunto, List<Adjunto> listaAdjuntos, String usuarioCreacion) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		
		
			if (TipoPerfilEnum.REFERENTE_CAJA.equals(tipoPerfilEnum) 
					|| TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.equals(tipoPerfilEnum)){
			usuarioBatchCobroImputacion = analista;
			analista = null;
		}
		
		tarea.setUsuarioCreacion(usuarioBatchCobroImputacion);
		
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
		
		tarea.setTipoTarea(tipoTarea);
		
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioAsignacion(analista);
		tarea.setPerfilAsignacion(tipoPerfilEnum.descripcion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
		
		tarea.setMonedaImporte(cobro.getMonedaOperacion().getSigno2());
		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));
		
		tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
		tarea.setNroCliente(nroCliente);
		tarea.setRazonSocial(razonSocial);
		tarea.setEnviarMail(enviarMail);
		
		tarea.setCuerpoMail(cuerpo);
		
		tarea.setIdOperacion(cobro.getOperacion().getIdOperacion());
		
		tarea.setAsuntoMail(asunto);
		
		tarea.setSistema(sistema);
		
		tarea.setSociedad(sociedad);
		
		
		if (Validaciones.isCollectionNotEmpty(listaAdjuntos)){
			tarea.setAdjuntosMail(listaAdjuntos);
		}
		
		tareaServicio.crearTareaImputacionManualCobro(tarea);
		
	}
	
	/**
	 * 
	 * @param analista
	 * @param conCopia
	 * @param asunto
	 * @param cuerpo
	 * @param idCobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void enviarMail(String analista, String conCopia, String asunto, String cuerpo, Long idCobro, List<Adjunto> listaAdjuntos, Long idOperacion,Estado estadoCobro) throws NegocioExcepcion {
		
		String para =""; 
		String cc ="";
		
		ByteArrayOutputStream outputStream = null;
		
		try {
			outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workbook = cobroOnlineServicio.exportarCobro(idCobro,estadoCobro);
			workbook.write(outputStream);
			
			if (!Validaciones.isCollectionNotEmpty(listaAdjuntos)){
				listaAdjuntos = new ArrayList<Adjunto>();
			}
			
			String nombreAdjunto = Utilidad.EMPTY_STRING;
			
			if (!Validaciones.isObjectNull(idOperacion)) {
				
				nombreAdjunto = "ID Operación Cobro " + Utilidad.rellenarCerosIzquierda(idOperacion.toString().trim(), 7);
				
			} else {
				
				nombreAdjunto = "ID Operación Cobro XXXXXXX";
				
			}
			
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreAdjunto, "xls"));
			
			UsuarioLdapDto usuarioLdapAnalista;
			if(!Validaciones.isNullOrEmpty(analista)){
				usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(analista);
				if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
					para = usuarioLdapAnalista.getMail() + ";";
				}
			}
			
			if(!Validaciones.isNullOrEmpty(conCopia)){
				for(String usuario : conCopia.split(";")){
					UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(usuario);
					if(!Validaciones.isNullOrEmpty(usuarioLdap.getMail())){
						cc += usuarioLdap.getMail() + ";";
					}
				}
			}
			
			Mail mail = new Mail(para,cc,asunto,new StringBuffer(cuerpo));
			mail.setAdjuntos(listaAdjuntos);
			mailServicio.sendMail(mail);
			
		}catch (LdapExcepcion | IOException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		
	}
	
	/**
	 * Actualiza el estado de la transaccion que se envia por parametro con el estado 
	 * que recibe como parametro.
	 */
	@Override
	public void actualizarEstadoTransaccion(String idTransaccion, ShvCobCobro cobro, EstadoTransaccionEnum estado)
			throws PersistenciaExcepcion {
		try {
			
			ShvCobTransaccion transaccion = (cobro.getTransaccionPorIdTransaccion(Integer.valueOf(idTransaccion))); 
			transaccion.setEstadoProcesamiento(estado);
			//cobro = cobroDao.modificar(cobro);
			
			ShvCobTransaccionSinOperacion transaccionSinOper = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(idTransaccion));
			transaccionSinOper.setEstadoProcesamiento(estado);
			transaccionSinOper = cobroDao.guardarTransaccionSinOperacion(transaccionSinOper);
						
			Traza.advertencia(getClass(), "actualizarEstadoTransaccion", "Se cambio al estado de la transaccion ("+ transaccion.getOperacionTransaccionFormateado() +") al estado nuevo: " + estado);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobMedioPago> listarMediosPago(ShvCobTransaccion transaccion, SistemaEnum cobrador){
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
	 * Se Tracean los datos de imputacion 
	 * @param cobro
	 */
	@Override
	public void tracearDatosProcesamientoTransaccion(Long idOperacion, ShvCobTransaccionSinOperacion transaccion, boolean esInicio){
		
		StringBuffer mensaje = new StringBuffer("\n");
		if(esInicio){
			mensaje.append("Transaccion a imputar: ");
		}else{
			mensaje.append("Transaccion - resultado: ");
		}
		
		mensaje.append("\n");
		
		String numeroTransaccion = Utilidad.rellenarCerosIzquierda(transaccion.getNumeroTransaccion().toString(), 5);
		String idOperacionTransaccion = idOperacion+"."+numeroTransaccion;
		
		String salidaTraceo = "-Transaccion ("+idOperacionTransaccion+"), grupo:" + transaccion.getGrupo() + ", id: " 
				+ transaccion.getIdTransaccion() + ", estado: " + transaccion.getEstadoProcesamiento().descripcion();
		mensaje.append(salidaTraceo).append("\n");
		
		for(ShvCobFacturaSinOperacion fact : transaccion.getShvCobFactura()){
			salidaTraceo = 
					Utilidad.rellenarEspaciosDerecha("|Fact   id: " + fact.getIdFactura(), 31) +
					Utilidad.rellenarEspaciosDerecha("| tipo: " + ((fact.getSistemaOrigen() != null ? fact.getSistemaOrigen():"-")), 17) + 
					"| estado: " + fact.getEstado().descripcion();
			
			mensaje.append(salidaTraceo).append("\n");
		}
		
		ShvCobTratamientoDiferenciaSinOperacion tratamiento = transaccion.getTratamientoDiferencia();
		if (!Validaciones.isObjectNull(tratamiento)){
			salidaTraceo = 
					Utilidad.rellenarEspaciosDerecha("|Tratamiento   id: " + tratamiento.getIdTratamientoDiferencia(), 24) +
					Utilidad.rellenarEspaciosDerecha("| tipo: " + tratamiento.getTipoTratamientoDiferencia(), 17) + 
						"| estado: " + tratamiento.getEstado().descripcion();
			
			mensaje.append(salidaTraceo).append("\n");
		}
		
		int count = 1;
		
		for(ShvCobMedioPagoSinOperacion mp : transaccion.getShvCobMedioPago()){
			String medioPagoTraceo = Utilidad.rellenarEspaciosDerecha("|MP " + count +"   id: " + mp.getIdMedioPago(), 31); 
			medioPagoTraceo += Utilidad.rellenarEspaciosDerecha("| tipo: " + ((mp instanceof ShvCobMedioPagoMicSinOperacion)?"MIC":(mp instanceof ShvCobMedioPagoCalipsoSinOperacion)?"CALIPSO":
					(mp instanceof ShvCobMedioPagoUsuarioSinOperacion)?"USUARIO":
					(mp instanceof ShvCobMedioPagoShivaSinOperacion)?"SHIVA":"-"), 17);
			medioPagoTraceo += "| estado: " + mp.getEstado().descripcion();
			
			mensaje.append(medioPagoTraceo).append("\n");
			count++;
		}
			
		Traza.auditoria(getClass(), mensaje.toString());
	}
	
	/**
	 * 
	 * @param cobro
	 * @param idTransaccion
	 * @param estado
	 * @param cobrador
	 * @param descripcionError
	 * @param trans
	 * @throws NegocioExcepcion
	 */
	@Override
	public void actualizarEstadoFacturaYMPSinCobro(ShvCobTransaccionSinOperacion transaccion, EstadoFacturaMedioPagoEnum estado, SistemaEnum cobrador, String descripcionError)
			throws NegocioExcepcion {
		try {
			
			Boolean cambioEstadoDeFactura = false;
			
			
			ShvCobFacturaSinOperacion factura = transaccion.getFactura();
			
			if (!Validaciones.isObjectNull(factura)){
				EstadoFacturaMedioPagoEnum estadoAnterior = factura.getEstado();
				
				//Cambio estado de la factura
				if(cobrador.equals(SistemaEnum.MIC) && (factura instanceof ShvCobFacturaMicSinOperacion)){
					if(!EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
						factura.setEstado(estado);
						if (!Validaciones.isNullOrEmpty(descripcionError)){
							factura.setMensajeEstado(descripcionError);
							
						}
						cambioEstadoDeFactura = true;
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se cambio el estado ("+estadoAnterior+") de la factura " +factura.getIdFactura()+ " MIC por el nuevo estado: " + estado);
					} else {
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia la factura Mic de la transaccion nro " + transaccion.getNumeroTransaccion() +
								" al estado " + estado + ", por estar en estado de ERROR");
					}
				}else{
					if(cobrador.equals(SistemaEnum.CALIPSO) && (factura instanceof ShvCobFacturaCalipsoSinOperacion)){
						if(!EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
							factura.setEstado(estado);
							if (!Validaciones.isNullOrEmpty(descripcionError)){
								factura.setMensajeEstado(descripcionError);
								
							}
							cambioEstadoDeFactura = true;
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se cambio el estado ("+estadoAnterior+") de la factura " +factura.getIdFactura()+ " CALIPSO por el nuevo estado: " + estado);
						} else {
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia la factura Calipso de la transaccion nro " + transaccion.getNumeroTransaccion() +
									" al estado " + estado + ", por estar en estado de ERROR");
						}
					} else{
						if(cobrador.equals(SistemaEnum.USUARIO) && (factura instanceof ShvCobFacturaUsuarioSinOperacion)){
							if(!EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())) {
								factura.setEstado(estado);
								if (!Validaciones.isNullOrEmpty(descripcionError)){
									factura.setMensajeEstado(descripcionError);
									
								}
								cambioEstadoDeFactura = true;
								Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se cambio el estado ("+estadoAnterior+") de la factura " +factura.getIdFactura()+ " CALIPSO por el nuevo estado: " + estado);
							} else {
								Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia la factura Calipso de la transaccion nro " + transaccion.getNumeroTransaccion() +
										" al estado " + estado + ", por estar en estado de ERROR");
							}
						}else {
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia el estado de la factura id: " +factura.getIdFactura());
						}
					}
				}
			}
			
			//Cambio el estado de los Medios de Pago
			for(ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
				if(cobrador.equals(SistemaEnum.MIC) && (medioPago instanceof ShvCobMedioPagoMicSinOperacion
						|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)){
					if(!EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())) {
						medioPago.setEstado(estado);
						if (!Validaciones.isNullOrEmpty(descripcionError)){
							medioPago.setMensajeEstado(descripcionError);
							
						}
					} else {
						Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia los medios de pago Mic de la transaccion nro " + transaccion.getNumeroTransaccion() +
								" al estado " + estado + ", por estar en estado de ERROR");
					}
				}else{
					if(cobrador.equals(SistemaEnum.CALIPSO) && (medioPago instanceof ShvCobMedioPagoCalipsoSinOperacion
							|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)){
						if(!EstadoFacturaMedioPagoEnum.ERROR.equals(medioPago.getEstado())) {
							medioPago.setEstado(estado);
							if (!Validaciones.isNullOrEmpty(descripcionError)){
								medioPago.setMensajeEstado(descripcionError);
								
							}
						} else {
							Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "No se cambia los medios de pago Calipso de la transaccion nro " + transaccion.getNumeroTransaccion() +
									" al estado " + estado + ", por estar en estado de ERROR");
						}
					}else{
						
						//Si se cambio el estado de la factura entonces cambio el estado de 
						//los medios de pago usuario
						if(cambioEstadoDeFactura){
							if( !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)
									&& !(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion)){
								if(medioPago instanceof ShvCobMedioPagoUsuarioSinOperacion
										&& !TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())
										&& !TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
									medioPago.setEstado(estado);
								}
							}
							
						}
					}
				}
			}
			
			transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
			Traza.advertencia(getClass(), "actualizarEstadoFacturaYMP", "Se realizo el cambio de los estados de la factura y/o medios de pago a " 
					+ estado.descripcion() + " correspondientes a la operacion id: " + transaccion.getIdOperacion());
			
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * verifica si la transaccion contiene una factura y/o medios de pago
	 * MIC en estado Apropiado.
	 * @param transaccion
	 * @return
	 */
	@Override
	public Boolean contieneFacturaOMedioPagoMicEnProcesoDesapropiacion(ShvCobTransaccion transaccion) {
		ShvCobFactura factura = transaccion.getFactura();
		
		if (!Validaciones.isObjectNull(factura)) {
			if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())
					&& factura instanceof ShvCobFacturaMic){
				return true;
			}
		} else {
			if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getTratamientoDiferencia().getEstado())
					&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(
									transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				return true;
			}
		}
		
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medioPago.getEstado())
					&& medioPago instanceof ShvCobMedioPagoMic){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean tieneTransaccionesConAplicacionManualPendienteDeProcesar(Long idOperacion) throws NegocioExcepcion {
		
		try {
			boolean hayPendienteProcesar = false;
			boolean hayErrorDesapropiacion = false;
			List<EstadoTransaccionEnum> listaEstadosTransaccionesAplManual = cobroDao.listarEstadosTransaccionesAplicacionManualPendientesProcesar(idOperacion);
			
			for (EstadoTransaccionEnum estadoTransaccionEnum : listaEstadosTransaccionesAplManual) {
				
				if (EstadoTransaccionEnum.PENDIENTE.equals(estadoTransaccionEnum)
//					|| EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(estadoTransaccionEnum)
//					|| EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(estadoTransaccionEnum)
					|| EstadoTransaccionEnum.PENDIENTE_FINALIZACION_TRANSACCION.equals(estadoTransaccionEnum)
//					|| EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA.equals(estadoTransaccionEnum)
					){
					
					hayPendienteProcesar = true;
				} else if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(estadoTransaccionEnum)){
					hayErrorDesapropiacion = true;
					break;
				}
			}
			
			if(hayPendienteProcesar &&!hayErrorDesapropiacion){
				return true;
			}
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return false;
	}
	
	@Override
	public void desapropiarTransaccionesPorGrupo(Long idOperacion,Long idCobro, Long grupo, String usuarioBatchCobroImputacion, boolean tareaRechazada) throws NegocioExcepcion {
		
		List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		List<ShvCobMedioPagoSinOperacion> listaMediosPagoMIC = null;
		List<ShvCobTransaccionSinOperacion> listaTransaccionesADesapropiar = new ArrayList<ShvCobTransaccionSinOperacion>();
		List<String> listaIdMovMer = new ArrayList<String>();     /*operacion.transaccion*/
		
		boolean enviadoAMIC = false;
		boolean enviarACalipso = false;
		
		/**
		 * Si hay que desapropiar por un error, avanzo las transacciones a EN PROCESO DE DESAPROPIACION, sino la desapropiacion se realiza por
		 * la tarea rechazada, ya las transacciones ya se encuentran en EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA 
		 */
		
		try {
			listaTransaccionesPorGrupo = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYGrupo(idOperacion, grupo);
			listaTransaccionesPorGrupo = ordenarListaParaDesapropiar(listaTransaccionesPorGrupo);

			if(!tareaRechazada){
				avanzarTransacciones(listaTransaccionesPorGrupo,EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION);
				
				for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
					
					// MEDIOS DE PAGO
					if(existenMediosPagoMic(transaccion)){
						if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoMIC(transaccion))){
							cobroBatchSoporteImputacionMicServicio.desapropiacionParcialMic(transaccion);
							enviadoAMIC = true;
							break;
						}
					}
					
//						if(!listaEstadosTransaccionError.contains(transaccion.getEstadoProcesamiento())){
//						}
					
					// FACTURAS
					ShvCobFacturaSinOperacion factura = transaccion.getFactura();
					if(factura instanceof ShvCobFacturaMicSinOperacion && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())){
						cobroBatchSoporteImputacionMicServicio.desapropiacionParcialMic(transaccion);
						enviadoAMIC = true;
						break;
					}
					
					actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.USUARIO, null);
					
					//TRATAMIENTO DE DIFERENCIA
					ShvCobTratamientoDiferenciaSinOperacion tratamiento = transaccion.getTratamientoDiferencia();
					
					if (!Validaciones.isObjectNull(tratamiento)){
						
						if ((TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())
								|| TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia()))
								&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamiento.getEstado())
								&& !EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
								){
							cobroBatchSoporteImputacionMicServicio.desapropiacionParcialMic(transaccion);
							enviadoAMIC = true;
							break;
						}
						
						if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia())
								&& EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamiento.getEstado())){
							listaIdMovMer.add(String.valueOf(tratamiento.getIdMovMerCobrador()));
							enviarACalipso = true;
						} else {
							tratamiento.setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
						}
						
						
					}
					
					if(factura instanceof ShvCobFacturaCalipsoSinOperacion && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())){
						enviarACalipso = true;
						if(!listaTransaccionesADesapropiar.contains(transaccion)){
							listaTransaccionesADesapropiar.add(transaccion);
						}
					}
					
					if(existenMediosPagoCalipso(transaccion)){
						if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoCalipso(transaccion))){
							enviarACalipso = true;
							if(!listaTransaccionesADesapropiar.contains(transaccion)){
								listaTransaccionesADesapropiar.add(transaccion);
							}
							
						}
					}
					
					if(existenMediosPagoShiva(transaccion)){
						if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoShiva(transaccion))){
							if(!listaTransaccionesADesapropiar.contains(transaccion)){
								listaTransaccionesADesapropiar.add(transaccion);
							}
						}
					}
					
//					listaMediosPagoShivaDelGrupo.addAll(listarMediosPago(transaccion,SistemaEnum.SHIVA));
					
					
				}//FIN FOR
					
				if(!enviadoAMIC){
					
					if (enviarACalipso){
						armarEnvioMensajeDesapropiacionParcialCalipso(listaTransaccionesADesapropiar,listaIdMovMer,idOperacion);
					} else {
						realizarReversionParcialMedioPagoValor(listaTransaccionesADesapropiar, idOperacion);
					}
				}
			} else {
				
				for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
					
					actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.USUARIO, null);
					
					//Lista de medios de pago a enviar Mic
					listaMediosPagoMIC = listarMediosPago(transaccion,SistemaEnum.MIC);
					
					/**
					 * Como la desapropiacion de MIC se hace transaccion a transaccion, voy enviando una por una.
					 * La desapropiacion de CALIPSO se realiza con un  solo envío, por lo que vamos juntando todos los mp de todas las transacciones para un único envío
					 */
					if(existenMediosPagoCalipso(transaccion)){
						if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoCalipso(transaccion))
								|| EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(getEstadoMedioPagoCalipso(transaccion))){
							if(!listaTransaccionesADesapropiar.contains(transaccion)){
								listaTransaccionesADesapropiar.add(transaccion);
							}
							enviarACalipso = true;
						}
					}
					
					if(existenMediosPagoShiva(transaccion)){
						if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(getEstadoMedioPagoShiva(transaccion))
								|| EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(getEstadoMedioPagoShiva(transaccion))){
							if(!listaTransaccionesADesapropiar.contains(transaccion)){
								listaTransaccionesADesapropiar.add(transaccion);
							}
						}
					}
					
					EstadoFacturaMedioPagoEnum estadoMedioPagoMIC = getEstadoMedioPagoMIC(transaccion);
					
					if(Validaciones.isCollectionNotEmpty(listaMediosPagoMIC)){
						
						if (!EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(estadoMedioPagoMIC)
								&& !EstadoFacturaMedioPagoEnum.ERROR.equals(estadoMedioPagoMIC)
								&& !EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(estadoMedioPagoMIC)){
							actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION, SistemaEnum.MIC, null);
							
							if (!enviadoAMIC){
								cobroBatchSoporteImputacionMicServicio.desapropiacionParcialMic(transaccion);
								enviadoAMIC = true;
								break;
							}
						}
						
					}
				}
				
				if (!enviadoAMIC){
					
					if (enviarACalipso){
						armarEnvioMensajeDesapropiacionParcialCalipso(listaTransaccionesADesapropiar, listaIdMovMer, idOperacion);
						
					} else{
						realizarReversionParcialMedioPagoValor(listaTransaccionesADesapropiar, idOperacion);
					}
				}
			}
			
			
			for (ShvCobTransaccionSinOperacion tr : listaTransaccionesPorGrupo){
				
				if (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(tr.getEstadoProcesamiento())){
					tr = cobroDao.guardarTransaccionSinOperacion(tr);
					ShvWfWorkflow workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
					avanzarCobroAErrorEnDesapropiacionParcial(workflow, idOperacion, usuarioBatchCobroImputacion,true);
					return;
				}
				
				if(todosMediosPagoDesapropiados(tr)){
//					if(!Validaciones.isObjectNull(tr.getFactura()) && tr.getFactura() instanceof ShvCobFacturaUsuarioSinOperacion){
//						tr.getFactura().setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
//					}
					
					if (!EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(tr.getEstadoProcesamiento())){
						
						if(tareaRechazada){
							tr.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_APL_MANUAL_RECHAZADA);
						} else {
							tr.setEstadoProcesamiento(EstadoTransaccionEnum.DESAPROPIADA);
						}
					}
				}
				
				if (EstadoTransaccionEnum.PENDIENTE.equals(tr.getEstadoProcesamiento())){
					tr.setEstadoProcesamiento(EstadoTransaccionEnum.SIN_PROCESAR_POR_ERROR_EN_GRUPO);
				}
				
				tr = cobroDao.guardarTransaccionSinOperacion(tr);
			}
			
			
			
			if(!enviadoAMIC 
				&& (EstadoTransaccionEnum.ERROR_APL_MANUAL_RECHAZADA.equals(listaTransaccionesPorGrupo.get(0).getEstadoProcesamiento())
				|| EstadoTransaccionEnum.DESAPROPIADA.equals(listaTransaccionesPorGrupo.get(0).getEstadoProcesamiento())
				|| EstadoTransaccionEnum.listarEstadosError().contains(listaTransaccionesPorGrupo.get(0).getEstadoProcesamiento())
				)){
				
				BigDecimal importeParcial = calcularImporteParcialDelGrupo(listaTransaccionesPorGrupo);
				
				enviarMailyGenerarTareaImputacionManual(idOperacion, grupo, listaTransaccionesPorGrupo.get(0).getEstadoProcesamiento(), 
						listaTransaccionesPorGrupo.get(0).getSociedad(), 
						listaTransaccionesPorGrupo.get(0).getSistema(), 
						idCobro,
						importeParcial);
				
			}
			
			if (!tareaRechazada){
				cobroBatchServicio.actualizarReferenciaMediosDePagoCTA(idCobro, null, null, grupo);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}


	/**
	 * 
	 * @param listaTransaccionesPorGrupo
	 * @return
	 */
	@Override
	public BigDecimal calcularImporteParcialDelGrupo(List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo) {
		
		BigDecimal importeParcial = BigDecimal.ZERO;
		
		for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
			for(ShvCobMedioPagoSinOperacion mp : transaccion.getMediosPago()){
				importeParcial = importeParcial.add(mp.getImporte());
			}
			
		}
		return importeParcial;
	}

	/**
	 * Este método se utiliza para ordenar la lista de transacciones para desapropiar en el siguiente orden:
	 * Primero las transacciones que tengan medios de pago MIC, Segundo las que tengan medios de pago CALIPSO, Y último medios de pago SHIVA
	 * @param listaTransaccionesPorGrupo
	 */
	private List<ShvCobTransaccionSinOperacion> ordenarListaParaDesapropiar(List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo) {
		
		List<ShvCobTransaccionSinOperacion> listaOrdenada = new ArrayList<ShvCobTransaccionSinOperacion>();
		List<ShvCobTransaccionSinOperacion> listaTransaccionesSinMIC = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
			
			if(!Validaciones.isObjectNull(transaccion.getFactura())){
				if (transaccion.getFactura() instanceof ShvCobFacturaMicSinOperacion){
					listaOrdenada.add(transaccion);
				}
			}
			
			for (ShvCobMedioPagoSinOperacion mp : transaccion.getMediosPago()){
			
				if (mp instanceof ShvCobMedioPagoMicSinOperacion && !EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(mp.getEstado())){
					if (!listaOrdenada.contains(transaccion)){
						listaOrdenada.add(transaccion);
						break;
					}
				}
			}
			
			if(!listaOrdenada.contains(transaccion)){
				listaTransaccionesSinMIC.add(transaccion);
			}
		}
		
		//Utilizo una lista auxiliar para ordenar de forma descendente la lista ordenada original
		List<ShvCobTransaccionSinOperacion> listaOrdenadaAux = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		for (int i=listaOrdenada.size()-1;i>=0;i--) {
			listaOrdenadaAux.add(listaOrdenada.get(i));
		}
		
		listaOrdenada = listaOrdenadaAux;
		
		for (ShvCobTransaccionSinOperacion tr : listaTransaccionesSinMIC) {
			listaOrdenada.add(tr);
		}
		
		for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
			tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
		}
		
		return listaOrdenada;
		
	}
	
	/**
	 * Verifica que todos los medios de pago de la transaccion esten en estado DESAPROPIADA
	 * @param transaccion
	 * @return
	 */
	private boolean todosMediosPagoDesapropiados(ShvCobTransaccionSinOperacion transaccion) {
		for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(!EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(medioPago.getEstado())){
				return false;
			}
		}
		return true;
	}
	

	/**
	 * Envia la desapropiacion parcial a calipso, una vez recibida la respuesta evalua si debe revertir valores (desapropiacion mp shiva)
	 * @param listaFacturas
	 * @param listaMediosPagoCalipsoDelGrupo
	 * @param listaMediosPagoShivaDelGrupo
	 * @param idOperacion
	 * @param enviadoAMIC
	 * @throws NegocioExcepcion
	 */
	private void armarEnvioMensajeDesapropiacionParcialCalipso(List<ShvCobTransaccionSinOperacion> listaTransaccionesADesapropiar,
			 List<String> listaIdMovMer, Long idOperacion) throws NegocioExcepcion {
		
		try {
				
			EstadoFacturaMedioPagoEnum estadoMedioPagoCalipso =null;
			
			for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesADesapropiar) {
				estadoMedioPagoCalipso = getEstadoMedioPagoCalipso(transaccion);
				
				if (!Validaciones.isObjectNull(estadoMedioPagoCalipso) && !EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(estadoMedioPagoCalipso)){
					
					actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION, SistemaEnum.CALIPSO, null);
				}
			}
			
			String resultadoDesapropiacionCalipso = cobroBatchSoporteImputacionCalipsoServicio.desapropiacionParcialCalipso
					(listaTransaccionesADesapropiar,listaIdMovMer,idOperacion);
	
			if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(resultadoDesapropiacionCalipso)) {
				realizarReversionParcialMedioPagoValor(listaTransaccionesADesapropiar, idOperacion);
				
			}
			
			for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesADesapropiar) {
				
				if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(resultadoDesapropiacionCalipso)) {
					actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.DESAPROPIADA, SistemaEnum.CALIPSO, null);
					if (Validaciones.isCollectionNotEmpty(listaIdMovMer)){
						if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())
								&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
							transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.DESAPROPIADA);
						}
					}
					
				} else {
					if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(resultadoDesapropiacionCalipso)
							|| TipoResultadoEnum.NOK.getDescripcionCalipso().equals(resultadoDesapropiacionCalipso)) {
						actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.ERROR, SistemaEnum.CALIPSO, null);
						if (Validaciones.isCollectionNotEmpty(listaIdMovMer)){
							if(!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())
									&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
								transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.ERROR);
							}
						}
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
						
					}
					
				}
			}
				
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 
	 * @param listaTransaccionesPorGrupo
	 * @throws NegocioExcepcion
	 */
	@Override
	public void avanzarTransacciones(List<ShvCobTransaccionSinOperacion> listaTransaccionesPorGrupo, EstadoTransaccionEnum estado) throws NegocioExcepcion {
		
		try {
			for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesPorGrupo) {
				
				if(EstadoTransaccionEnum.APROPIADA.equals(transaccion.getEstadoProcesamiento())
						|| EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento())){
					
					transaccion.setEstadoProcesamiento(estado);
					
					transaccion = cobroDao.guardarTransaccionSinOperacionNoTransaccional(transaccion);
					tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
					
				}
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	/**
	 * Método encargado de generar la tarea de imputación manual y envío de mail de respectiva tarea
	 * @param idOperacion
	 * @param grupo
	 * @param estadoDelGrupo
	 * @param sociedad
	 * @param sistema
	 * @param idCobro
	 * @throws NegocioExcepcion
	 */
	@Override
	public void enviarMailyGenerarTareaImputacionManual(Long idOperacion, Long grupo,EstadoTransaccionEnum estadoDelGrupo, SociedadEnum sociedad, SistemaEnum sistema, Long idCobro, BigDecimal importe) throws NegocioExcepcion {
		
		try {
			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) cobroDao.buscarDatosImputacion(idOperacion);
			
			String analista = listaDatos.get(0).getIdAnalista();
			String nombreAnalista = listaDatos.get(0).getAnalista();
			String copropietario = listaDatos.get(0).getCopropietario();
			
			String conCopia = Utilidad.agregarAnalistaTeamComercialACopropietario(listaDatos, copropietario);
			
			String cuerpo = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.analista.nombre"),nombreAnalista) 
					+ Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuerpo.listado.clientes");
			String asunto = "";
			String nroCliente = listaDatos.get(0).getNumCliente().toString();
			String razonSocial = listaDatos.get(0).getRazonSocial();
			String empresa = listaDatos.get(0).getEmpresa();
			String cuit = listaDatos.get(0).getCuit();
			String empresaCuitClienteYRazonSocial = Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + empresa + " / " +
													Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cuit + " / " + 
													Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + nroCliente + " / " + razonSocial;
			
			String segmento = listaDatos.get(0).getSegmento();
			
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			TipoPerfilEnum tipoPerfil = null;
			TipoTareaEnum tipoTarea = null; 
			PerfilFiltro filtro = new PerfilFiltro();
			
			
			boolean enviarMailAnalista = false;
			
			
			 //Si el estado del grupo está en alguno de de estos estados, debo generar la tarea para el analista, avisando que el grupo finalizó con error
			 //Y se envía el mail aparte
			 //Si no genero la tarea pendiente de confirmación de aplicación manual. Se envía el mail junto con la creación de la tarea. 
			 
			if(EstadoTransaccionEnum.ERROR_APL_MANUAL_RECHAZADA.equals(estadoDelGrupo)
					|| EstadoTransaccionEnum.DESAPROPIADA.equals(estadoDelGrupo)
					|| EstadoTransaccionEnum.listarEstadosError().contains(estadoDelGrupo)){
				
				tipoTarea = TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL;

				//Si tengo que generar la tarea para el grupo 0
				if(SociedadEnum.TELECOM.equals(sociedad)
						&& (!SistemaEnum.SAP.equals(sistema) && !SistemaEnum.HUAWEI.equals(sistema))){
					tipoTarea = TipoTareaEnum.COB_REV_COB_ERR;
				}
				
				tipoPerfil = TipoPerfilEnum.ANALISTA_COBRANZA;
				enviarMailAnalista = true;
				
				asunto = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.error.con.imputacion.manual"),
						sociedad.getDescripcion(), sistema.getDescripcion());
			} else {
				
				tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
				
				filtro.setTipoTarea(tipoTarea.name());
				filtro.setEmpresa(empresa);
				filtro.setSistema(sistema.name());
				filtro.setSociedad(sociedad.name());
				filtro.setSegmento(segmento);
				tipoPerfil = paramRespWfTareaServicioImpl.buscarPerfil(filtro);
				
				asunto = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.cobros.asunto.cobro.con.imputacion.manual.pendiente"),
						sociedad.getDescripcion(), sistema.getDescripcion());
				
				ShvCobEdCobroAdjunto adjuntoOtrosDebitos;
				
				adjuntoOtrosDebitos =  cobroOnlineDao.buscarAdjuntoPorGrupo(idOperacion, sociedad, sistema);
				
					if(!Validaciones.isObjectNull(adjuntoOtrosDebitos)){
						if (MotivoAdjuntoEnum.OTROS_DEBITO.equals(adjuntoOtrosDebitos.getPk().getMotivoAdjunto())){
							listaAdjuntos.add(new Adjunto(adjuntoOtrosDebitos.getPk().getDocumentoAdjunto().getArchivoAdjunto(),adjuntoOtrosDebitos.getPk().getDocumentoAdjunto().getNombreArchivo(), ""));
						}
					}
					
				ByteArrayOutputStream outputStream = null;
				
				Traza.auditoria(this.getClass(), "--------------------Inicio de la exportacion del cobro para adjuntarlo al mail------------");
				
				outputStream = new ByteArrayOutputStream();
				HSSFWorkbook workbook = cobroOnlineServicio.exportarCobro(idCobro);
				workbook.write(outputStream);
				
				Traza.auditoria(this.getClass(), "--------------------Fin de la exportacion del cobro para adjuntarlo al mail------------");
				
				String nombreAdjunto = Utilidad.EMPTY_STRING;
				
				if (!Validaciones.isObjectNull(idOperacion)) {
					
					nombreAdjunto = "ID Operación Cobro " + Utilidad.rellenarCerosIzquierda(idOperacion.toString().trim(), 7);
					
				} else {
					
					nombreAdjunto = "ID Operación Cobro XXXXXXX";
					
				}
				
				listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreAdjunto, "xls"));
			}
		
			asunto+= idOperacion + " - " + empresaCuitClienteYRazonSocial;
			
			for (ResultadoBusquedaDatosImputacion datos : listaDatos){

			cuerpo += " <br> ";
//			cuerpo += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + datos.getNumCliente() + " / " + datos.getRazonSocial();
			
			cuerpo += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + datos.getEmpresa() + " / " 
					+ Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + datos.getCuit() + " / " 
					+ Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + datos.getNumCliente() + " / " + datos.getRazonSocial()
					+ " / " + datos.getSegmento();
			
			}
			
//			cuerpo = Utilidad.reemplazarMensajes(
//				Propiedades.MENSAJES_PROPIEDADES.getString("mail.analista.nombre"),
//				nombreAnalista
//				) + cuerpo;
			
			Traza.auditoria(this.getClass(), "--------------------Inicio de busqueda del cobro para crear la tarea------------");
			
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			
			Traza.auditoria(this.getClass(), "--------------------Fin de busqueda del cobro para crear la tarea------------");
			
			Traza.auditoria(this.getClass(), "--------------------Inicio de creacion de la tarea------------");
			
			crearTareaPendiente(cobro, analista, importe,sociedad, sistema, tipoTarea, nroCliente, razonSocial,cuerpo, !enviarMailAnalista,tipoPerfil," - " + asunto,listaAdjuntos, null);
			
			Traza.auditoria(this.getClass(), "--------------------Fin de creacion de la tarea------------");
			
			if(enviarMailAnalista){
				
				Traza.auditoria(this.getClass(), "--------------------Inicio de envio de mail------------");
				enviarMail(analista, conCopia, asunto, cuerpo, idCobro, null, idOperacion,null);
				Traza.auditoria(this.getClass(), "--------------------Fin de envio de mail------------");
			}
	
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	@Override
	public boolean hayMedioPagoComProveedoresOLiquidoProducto(ShvCobTransaccionSinOperacion transaccion) {
		
		for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			
			if(TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())
				|| TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())
				|| TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Recorre todas los Medios de pago y la factura que posea la transferencia y si todas tienen
	 * el mismo estado, actualiza el estado de la transferencia al mismo estado.
	 * Si la transaccion tiene tratamiento diferencia evalua si tiene que generar cargos o reintegros
	 * y luego cambia el estado de la transaccion.
	 * @param transaccion
	 * @throws ReintentoExcepcion 
	 */
	@Override
	public ShvCobTransaccionSinOperacion verificarEstadoTransaccion(
			ShvCobTransaccionSinOperacion transaccion, String usuario) throws NegocioExcepcion, ReintentoExcepcion{
		
		if (Validaciones.isObjectNull(transaccion.getFactura())){
			//Transaccion con tratamiento diferencia
			
			if(existeMedioPagoEnError(transaccion)){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				return transaccion;
			}
			//genero cargos cuando los medios de pago estan apropiados y el tratamiento en estado PENDIENTE
			if (EstadoFacturaMedioPagoEnum.PENDIENTE.equals(transaccion.getTratamientoDiferencia().getEstado())
					&& todosMediosPagoApropiados(transaccion)
					&& !TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				transaccion = generarCargosOReintegro(transaccion,null);
			}
			
			if (EstadoFacturaMedioPagoEnum.PENDIENTE.equals(transaccion.getTratamientoDiferencia().getEstado())
					&& TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP);
				return transaccion;
			}
			
			if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(transaccion.getTratamientoDiferencia().getEstado())
					&& todosMediosPagoApropiados(transaccion)){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
				transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			}
			
			if (EstadoFacturaMedioPagoEnum.ERROR.equals(transaccion.getTratamientoDiferencia().getEstado())){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_TRATAMIENTO);
			}
			
		} else {
			//Transaccion con factura
			ShvCobFacturaSinOperacion factura = transaccion.getFactura();
			
			if(existeMedioPagoEnError(transaccion)){
				if(EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())
						||EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstadoDeimos())){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA);
					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					return transaccion;
				}else{
					if (existenCargosProximaFactura(null, transaccion)){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_TRATAMIENTO);
						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					}else{
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_MEDIO_PAGO);
						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					}
					return transaccion;
				}
			}else{
				if(EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstado())
						||EstadoFacturaMedioPagoEnum.ERROR.equals(factura.getEstadoDeimos())){
				 	transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DEUDA);
				 	transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					return transaccion;
				}
			}
			
			// Me guardo el estado de la factura para compararlo con los medios de pago
			EstadoFacturaMedioPagoEnum estadoFactura = factura.getEstado();

			if(estadoFactura.equals(EstadoFacturaMedioPagoEnum.APROPIADA)
					&& hayMedioPagoComProveedoresOLiquidoProducto(transaccion)
					&& !EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP.equals(transaccion.getEstadoProcesamiento())){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA_SIN_COMP_SAP);
				return transaccion;
			}
			
			// recorro los medios de Pago y comparo con el estado
			for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()) {
				if(!medioPago.getEstado().equals(estadoFactura)){
					// No hago nada porque los estados son diferentes
					return transaccion;
				}
			}
			// El estado de la factura y los MPs, son el mismo.
			if(estadoFactura.equals(EstadoFacturaMedioPagoEnum.APROPIADA)){
				if(EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APROPIADA);
					transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				}else{
					Traza.advertencia(getClass(), "No se puede actualizar el estado de la transaccion numero " + transaccion.getNumeroTransaccion() + " de la operacion id "
							+ transaccion.getIdOperacion() + " a Apropiada o Error porque no esta en estado 'En proceso de apropiacion'");
				}
			}
		}
		return transaccion;
	}
	
	/**
	 * Verifica que todos los medios de pago de la transaccion esten en estado APROPIADA
	 * @param transaccion
	 * @return
	 */
	private boolean todosMediosPagoApropiados(ShvCobTransaccionSinOperacion transaccion) {
		for (ShvCobMedioPagoSinOperacion medioPago : transaccion.getMediosPago()){
			if(!EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPago.getEstado())){
				return false;
			}
		}
		return true;
	}
	
	private String apocopeGrupo(ShvCobTransaccion transaccion, String segmento, String empresa) throws NegocioExcepcion, PersistenciaExcepcion {
		PerfilFiltro filtro = new PerfilFiltro();
		String apocopeGrupo = Constantes.EMPTY_STRING;
		
		if (!Validaciones.isObjectNull(transaccion.getIdSociedad()) && !Validaciones.isObjectNull(transaccion.getSistema()) && !ConstantesCobro.GRUPO_INTERNO_SHIVA_INTEGER.equals(transaccion.getGrupo())) {
			filtro.setSociedad(transaccion.getIdSociedad().name());
			filtro.setSistema(transaccion.getSistema().name());
			filtro.setSegmento(segmento);
			filtro.setEmpresa(empresa);
			filtro.setTipoTarea(TipoTareaEnum.COB_CONF_APLIC_MANUAL.name());
			apocopeGrupo = transaccion.getIdSociedad().getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			apocopeGrupo += TipoImputacionEnum.MANUAL.getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			
			TipoPerfilEnum perfil = paramRespWfTareaServicio.buscarPerfil(filtro);; 
			
			apocopeGrupo += TipoPerfilEnum.getApocopeResponsableApliManual(perfil);
			
			
		} else {
			apocopeGrupo = SociedadEnum.TELECOM.getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			apocopeGrupo += TipoImputacionEnum.AUTOMATICA.getApocope();
			
		}
		return apocopeGrupo;
	}
}



