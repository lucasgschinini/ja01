package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPagoRespuestaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.util.JmsMonitorMensajeria;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionDeudaMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicApropiacionMPDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicConfirmacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDesapropiacionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosCreditoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicGeneracionCargosDebitoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosRespuestaDto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoNotaCreditoMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoRemanenteSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;

public class CobroBatchSoporteImputacionMicServicioImpl extends Servicio implements ICobroBatchSoporteImputacionMicServicio{

	public CobroBatchSoporteImputacionMicServicioImpl() {
		
	}
	@Autowired private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	@Autowired private ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;
	@Autowired private JmsMonitorMensajeria monitor;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private ICobroDao cobroDao;
	@Autowired private IWorkflowCobros workflowCobros;
	@Autowired private ICobroBatchServicio cobroBatchServicio;
	
	@Override
	public void generarCargoReintegroMic(ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion mpProxFacturaMic,
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia, ShvWfWorkflow workflow) throws NegocioExcepcion, ReintentoExcepcion {

		MicTransaccionGeneracionCargosDto mensajeMIC = null;
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		
		try {
			if (!Validaciones.isObjectNull(mpProxFacturaMic)){
				
				// Armo mensaje generacion de cargo
				mensajeMIC = new MicGeneracionCargosDebitoDto();
				mensajeMIC.setServicio(MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO);
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$06);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				mensajeMIC.setIdOperacion(mpProxFacturaMic.getTransaccion().getIdOperacion().longValue());
				mensajeMIC.setIdTransaccion(mpProxFacturaMic.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(mpProxFacturaMic.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(mpProxFacturaMic.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				cargarDetalleGeneracionCargoAMensaje(mpProxFacturaMic, null, mensajeMIC);
					
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(mensajeMIC.getIdOperacion().longValue(), mensajeMIC.getIdTransaccion(), 
						mensajeMIC.getNumeroTransaccion(), SistemaEnum.MIC, mensajeMIC.getTipoInvocacion())){
					
					// envio a MIC la generacion de cargo
					
					monitor.enviarMensaje(mensajeMIC);
					pasarWorkflowCobrosProcesoAPendienteMIC(
							mensajeMIC.getIdOperacion(), 
							mensajeMIC.getTipoInvocacion(),
							mpProxFacturaMic.getTransaccion().getOperacionTransaccionFormateado(),
							usuarioBatchCobroImputacion,
							workflow);
				}
				
				
			} else {
				mensajeMIC = new MicGeneracionCargosCreditoDto();
				mensajeMIC.setServicio(MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO);
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$07);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				mensajeMIC.setIdOperacion(tratamientoDiferencia.getTransaccion().getIdOperacion().longValue());
				mensajeMIC.setIdTransaccion(tratamientoDiferencia.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(tratamientoDiferencia.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(tratamientoDiferencia.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setUsuarioCobrador(SistemaEnum.SHIVA.getDescripcion().toUpperCase());
				
				cargarDetalleGeneracionCargoAMensaje(null,tratamientoDiferencia, mensajeMIC);
					
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(mensajeMIC.getIdOperacion().longValue(), mensajeMIC.getIdTransaccion(), 
						mensajeMIC.getNumeroTransaccion(), SistemaEnum.MIC, mensajeMIC.getTipoInvocacion())){
					
					// envio a MIC la generacion de cargo
					monitor.enviarMensaje(mensajeMIC);
					pasarWorkflowCobrosProcesoAPendienteMIC(
							mensajeMIC.getIdOperacion(), 
							mensajeMIC.getTipoInvocacion(),
							tratamientoDiferencia.getTransaccion().getOperacionTransaccionFormateado(),
							usuarioBatchCobroImputacion,
							workflow);
				}
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param mpProxFacturaMic
	 * @param tratamientoDiferencia
	 * @param mensajeMIC
	 * @throws NegocioExcepcion 
	 */
	private void cargarDetalleGeneracionCargoAMensaje(
			ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion mpProxFacturaMic,
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia, 
			MicTransaccionGeneracionCargosDto mensajeMIC) throws NegocioExcepcion{

		if (!Validaciones.isObjectNull(mpProxFacturaMic)){
			
			mensajeMIC.getDetalleGeneracionCargos().setAcuerdoFacturacion(mpProxFacturaMic.getAcuerdoTrasladoCargo());
			mensajeMIC.getDetalleGeneracionCargos().setImporteRegulado(mpProxFacturaMic.getImporte());
			mensajeMIC.getDetalleGeneracionCargos().setFechaDesde(Utilidad.formatDateAAAAMMDD(mpProxFacturaMic.getFechaVencimientoFactura()));
			mensajeMIC.getDetalleGeneracionCargos().setCalcularFechaHasta(SiNoEnum.SI);
			mensajeMIC.getDetalleGeneracionCargos().setQueHacerConLosIntereses(mpProxFacturaMic.getQueHacerConIntereses());
			
			if(!Validaciones.isObjectNull(mpProxFacturaMic.getImporteBonificacionIntereses())){
				if (mpProxFacturaMic.getCobradorInteresesGenerados().compareTo(mpProxFacturaMic.getImporteBonificacionIntereses()) != 0) {
					mensajeMIC.getDetalleGeneracionCargos().setImporteBonificacionInteresesRegulado(mpProxFacturaMic.getImporteBonificacionIntereses());
				}
			}
			
			mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(mpProxFacturaMic.getOrigenCargo());
			mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaCargo(mpProxFacturaMic.getLeyendaFacturaCargo());
			mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaInteres(mpProxFacturaMic.getLeyendaFacturaInteres());
			
		} else{
			if (!Validaciones.isObjectNull(tratamientoDiferencia)) {
				
				mensajeMIC.getDetalleGeneracionCargos().setAcuerdoFacturacion(tratamientoDiferencia.getAcuerdoTrasladoCargo());
				mensajeMIC.getDetalleGeneracionCargos().setImporteNoRegulado(tratamientoDiferencia.getImporte());
				mensajeMIC.getDetalleGeneracionCargos().setFechaDesde(Utilidad.formatDateAAAAMMDD(tratamientoDiferencia.getFechaTramiteReintegro()));
				mensajeMIC.getDetalleGeneracionCargos().setCalcularFechaHasta(SiNoEnum.SI);
				mensajeMIC.getDetalleGeneracionCargos().setQueHacerConLosIntereses(tratamientoDiferencia.getQueHacerConIntereses());

				if(!Validaciones.isObjectNull(tratamientoDiferencia.getImporteBonificacionIntereses())){
					if (tratamientoDiferencia.getCobradorInteresesGenerados().compareTo(tratamientoDiferencia.getImporteBonificacionIntereses()) != 0) {
						mensajeMIC.getDetalleGeneracionCargos().setImporteBonificacionInteresesRegulado(tratamientoDiferencia.getImporteBonificacionIntereses());
					}
				}

				mensajeMIC.getDetalleGeneracionCargos().setOrigenCargo(tratamientoDiferencia.getOrigenCargo());
				mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaCargo(tratamientoDiferencia.getLeyendaFacturaCargo());
				mensajeMIC.getDetalleGeneracionCargos().setLeyendaFacturaInteres(tratamientoDiferencia.getLeyendaFacturaInteres());
			}
		}
	}
	
	
	/**
	 * 
	 * @param idOperacion
	 * @param tipoInvocacion
	 * @param idOperacionTransaccion
	 * @param usuario
	 * @param wf
	 * @throws NegocioExcepcion
	 */
	public void pasarWorkflowCobrosProcesoAPendienteMIC(Long idOperacion,TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,String usuario, ShvWfWorkflow wf) throws NegocioExcepcion {
		if (tipoInvocacion != null) {
			//Busco el workflow del cobro
			ShvWfWorkflow workflow = null;
			try {
				if (!Validaciones.isObjectNull(wf)){
					workflow=wf;
				} else {
					workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
				}
				String datosModificados = " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")";
				
				//Modificacion por nuevos estados de imputación manual
				if (Estado.COB_PROCESO.equals(workflow.getEstado())){
					workflow = workflowCobros.registrarCobroDeEnProcesoAPendienteMic(workflow, datosModificados, usuario);
					
				} else if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
					workflow = workflowCobros.registrarProcesoDeAplicacionExternaAEnProcesoAplicacionExternaConRtaPendienteMIC(workflow, datosModificados, usuario);
				
				} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
					workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteMIC(workflow, datosModificados, usuario);
				}
				
				Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El cobro se pasa al estado[ "+ workflow.getEstado().descripcion() + " ] - " + tipoInvocacion.getDescripcion());
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			} finally {
				workflow = null;
			}
		} else {
			Traza.error(getClass(), "A: (" +idOperacionTransaccion+ ") el tipo de invocacion está vacio por eso no se pasa al estado Pendiente MIC.");
		}
	}
	
	
	/**
	 * Prepara el mensaje a MIC y lo agrega a JmsMonitorMensajeria.
	 * Ademas inserta en las tablas de MensajeriaFactura y mensajeriaMediosPago.
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 * @throws NegocioExcepcion 
	 * @throws ReintentoExcepcion 
	 */
	@Override
	public void apropiacionMic(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, ShvCobFacturaSinOperacion factura, ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion, ReintentoExcepcion {
		MicTransaccionADCDto mensajeMIC = null;
		
		String usuarioBatchCobrosImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		ShvCobFacturaMicSinOperacion facturaMic = null;
		if (factura instanceof ShvCobFacturaMicSinOperacion){
			
			facturaMic = (ShvCobFacturaMicSinOperacion) factura;
			
			// Armo mensaje apropiacion con factura
			mensajeMIC = new MicApropiacionDeudaDto();
			mensajeMIC.setIdOperacion(factura.getTransaccion().getIdOperacion());
			mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
			mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$01);
			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
			
			cargarFacturaAMensaje(facturaMic, mensajeMIC);
			
			if(Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)){
				// Armo mensaje apropiacion con factura y Medios de pago
				mensajeMIC = new MicApropiacionDeudaMPDto();
				mensajeMIC.setIdOperacion(factura.getTransaccion().getIdOperacion());
				mensajeMIC.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$03);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				
				cargarFacturaAMensaje(facturaMic, mensajeMIC);
				
				cargarMediosPagoMensajeMic(listaMediosPagoAEnviar, mensajeMIC);
			
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(mensajeMIC.getIdOperacion(), mensajeMIC.getIdTransaccion(), mensajeMIC.getNumeroTransaccion(), 
						SistemaEnum.MIC, mensajeMIC.getTipoInvocacion())) 
				{
					// envio a MIC la factura y medios de pagos
					monitor.enviarMensaje(mensajeMIC);
					pasarWorkflowCobrosProcesoAPendienteMIC(
							mensajeMIC.getIdOperacion(), 
							mensajeMIC.getTipoInvocacion(),
							factura.getTransaccion().getOperacionTransaccionFormateado(),
							usuarioBatchCobrosImputacion,
							null);
				}
				
			} else {
				
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(mensajeMIC.getIdOperacion(), mensajeMIC.getIdTransaccion(), 
						mensajeMIC.getNumeroTransaccion(), SistemaEnum.MIC, mensajeMIC.getTipoInvocacion())) 
				{
					// envio a MIC solo la factura
					monitor.enviarMensaje(mensajeMIC);
					pasarWorkflowCobrosProcesoAPendienteMIC(
							mensajeMIC.getIdOperacion(), 
							mensajeMIC.getTipoInvocacion(),
							factura.getTransaccion().getOperacionTransaccionFormateado(),
							usuarioBatchCobrosImputacion,
							null);
				}
			}
			
		} else {
			// Pues tengo que enviar la fecha de factura de calipso
			Date fechaValor = null;
			String transaccionFormateada="";
			if (Validaciones.isObjectNull(factura)){
				if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia().getFechaValor())){
					fechaValor = transaccion.getTratamientoDiferencia().getFechaValor();
					transaccionFormateada = transaccion.getOperacionTransaccionFormateado();
				}
			}else if (factura instanceof ShvCobFacturaCalipsoSinOperacion
					|| factura instanceof ShvCobFacturaUsuarioSinOperacion){
				fechaValor = factura.getFechaValor();
				transaccionFormateada = factura.getTransaccion().getOperacionTransaccionFormateado();
			}
			
			if(Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)){
				// Armo mensaje apropiacion con Medios de pago
				mensajeMIC = new MicApropiacionMPDto();
				mensajeMIC.setIdOperacion(listaMediosPagoAEnviar.get(0).getTransaccion().getIdOperacion());
				mensajeMIC.setIdTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getIdTransaccion());
				mensajeMIC.setNumeroTransaccion(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccion());
				mensajeMIC.setNumeroTransaccionFicticio(listaMediosPagoAEnviar.get(0).getTransaccion().getNumeroTransaccionFicticio());
				mensajeMIC.setTipoInvocacion(TipoInvocacionEnum.$02);
				mensajeMIC.setModoEjecucion(SiNoEnum.NO);
				
				// Ahi le seteo la fecha de factura de calipso
				mensajeMIC.getDetalleFactura().setFechaValor(Utilidad.formatDateAAAAMMDD(fechaValor));
				
				cargarMediosPagoMensajeMic(listaMediosPagoAEnviar, mensajeMIC);
				
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(mensajeMIC.getIdOperacion(), mensajeMIC.getIdTransaccion(), mensajeMIC.getNumeroTransaccion(), 
						SistemaEnum.MIC, mensajeMIC.getTipoInvocacion())) 
				{
					monitor.enviarMensaje(mensajeMIC);
					pasarWorkflowCobrosProcesoAPendienteMIC(
							mensajeMIC.getIdOperacion(), 
							mensajeMIC.getTipoInvocacion(),
							transaccionFormateada,
							usuarioBatchCobrosImputacion,
							null);
				}
			}
		}
	}
	
	/**
	 * Carga todos los datos de los mediosd e pago al mensaje
	 * @param listaMediosPagoAEnviar
	 * @param mensajeMIC
	 */
	private void cargarMediosPagoMensajeMic(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, MicTransaccionADCDto mensajeMIC) {
		for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoAEnviar) {
			MicDetalleMedioPagoDto mp = new MicDetalleMedioPagoDto();
			
			if(medioPago instanceof ShvCobMedioPagoNotaCreditoMicSinOperacion){
				ShvCobMedioPagoNotaCreditoMicSinOperacion medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMicSinOperacion)medioPago;
				mp.setTipoMedioPago(TipoMedioPagoEnum.NC);
				mp.setNumeroNC(Long.valueOf(medioPagoNotaCreditoMic.getNumeroNotaCredito()));
			} else 
			if(medioPago instanceof ShvCobMedioPagoRemanenteSinOperacion){
				ShvCobMedioPagoRemanenteSinOperacion medioPagoRemanente = (ShvCobMedioPagoRemanenteSinOperacion)medioPago;
				mp.setTipoMedioPago(TipoMedioPagoEnum.RT);
				mp.setCuentaRemanente(medioPagoRemanente.getCuentaRemanente());
				mp.setTipoRemanente(TipoRemanenteEnum.getEnumByCodigo(medioPagoRemanente.getTipoRemanente()));
			} else {
				mp.setTipoMedioPago(TipoMedioPagoEnum.TC);
			}
			
			mp.setImporteMedioPago(medioPago.getImporte());
			mensajeMIC.getListaMediosPago().add(mp);
		}
	}
	
	
	/**
	 * Carga todos los datos de la factura MIC al Mensaje
	 * @param facturaMic
	 * @param mensajeMIC
	 */
	private void cargarFacturaAMensaje(ShvCobFacturaMicSinOperacion facturaMic, MicTransaccionADCDto mensajeMIC) {

		// Si la factura es de MIC, el “Pago Parcial Cancelatorio” se enviará como “Pago Parcial”.
		
		if (TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO.equals(facturaMic.getTipoPago())) {
			mensajeMIC.getDetalleFactura().setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
		} else {
			mensajeMIC.getDetalleFactura().setTipoOperacion(facturaMic.getTipoPago());
		}
		
		if (TipoDocumentoEnum.DUC.descripcion().equals(facturaMic.getTipoComprobante().descripcion())){
			
			mensajeMIC.getDetalleFactura().setTipoDocumento(TipoDocumentoEnum.DUC);
		} else {
			mensajeMIC.getDetalleFactura().setTipoDocumento(TipoDocumentoEnum.FACTURA_NOTA_DEBITO);
		}

		mensajeMIC.getDetalleFactura().setReferenciaFactura(facturaMic.getIdReferenciaFactura());
		mensajeMIC.getDetalleFactura().setFechaValor(Utilidad.formatDateAAAAMMDD(facturaMic.getFechaValor()));
		mensajeMIC.getDetalleFactura().setAcuerdoFacturacionIntereses(facturaMic.getAcuerdoTrasladoCargo());
		mensajeMIC.getDetalleFactura().setImporte(facturaMic.getImporteCobrar());
		mensajeMIC.getDetalleFactura().setQueHacerConLosIntereses(
					facturaMic.getQueHacerConIntereses() !=null ? facturaMic.getQueHacerConIntereses().getCodigoMicApropiacion() : null);
		
		// Solo se envían intereses bonificados cuando el tratamiento de intereses es TC
		if (TratamientoInteresesEnum.TC.equals(facturaMic.getQueHacerConIntereses())) {
			mensajeMIC.getDetalleFactura().setImporteBonificacionIntereses(facturaMic.getImporteBonificacionIntereses());		
		}
		
		mensajeMIC.getDetalleFactura().setQueHacerConLosTerceros(facturaMic.getDestransferirTerceros());
	}

	/**
	 * Se encarga de la confirmacion de la factura y/o medios de pago Mic.
	 * Ademas, recibe el cobro para acutalizar el estado en el objeto.
	 * 
	 * @param listaOperacionTransacciones  se usa para saber a que transacciones modificar el estado.
	 * @param cobro del cual se obtienen los datos para enviar a MIC el mensaje de confirmacion.
	 * @throws NegocioExcepcion 
	 * @throws ReintentoExcepcion 
	 */
	@Override
	public void confirmacionMic(List<String> listaOperacionTransacciones, ShvCobCobro cobro, boolean reenvioConfirmacion) 
			throws NegocioExcepcion, ReintentoExcepcion {
		
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		
		try{
			for (String operacionTransaccion : listaOperacionTransacciones) {
				cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(operacionTransaccion.split("\\.")[1], cobro, EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION);
			}
			
			Long idOperacion = cobro.getOperacion().getIdOperacion();
			
			MicConfirmacionDto mensajeMIC = new MicConfirmacionDto();
			mensajeMIC.setIdOperacion(idOperacion);
			mensajeMIC.setIdTransaccion(null);
			mensajeMIC.setNumeroTransaccion(null);
			mensajeMIC.setNumeroTransaccionFicticio(null);
			
			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			
			if (reenvioConfirmacion) {
				monitor.enviarMensaje(mensajeMIC);
				cobro =	pasarWorkflowCobrosProcesoAPendienteMICConfirmacion(idOperacion, TipoInvocacionEnum.$05,idOperacion.toString(),usuarioBatchCobroImputacion,cobro);
			} else { 
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.MIC, TipoInvocacionEnum.$05)) {
					monitor.enviarMensaje(mensajeMIC);
					cobro =	pasarWorkflowCobrosProcesoAPendienteMICConfirmacion(idOperacion, TipoInvocacionEnum.$05,idOperacion.toString(),usuarioBatchCobroImputacion,cobro);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param idOperacion
	 * @param tipoInvocacion
	 * @param idOperacionTransaccion
	 * @param usuario
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobCobro pasarWorkflowCobrosProcesoAPendienteMICConfirmacion(Long idOperacion,TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,String usuario, ShvCobCobro cobro) throws NegocioExcepcion {
		if (tipoInvocacion != null) {
			//Busco el workflow del cobro
			String datosModificados = " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")";
			cobro.setWorkflow(workflowCobros.registrarCobroDeEnProcesoAPendienteMic(cobro.getWorkflow(), datosModificados, usuario));
			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El cobro se pasa al estado[ "+ cobro.getWorkflow().getEstado().descripcion() + " ] - " + tipoInvocacion.getDescripcion());
		} else {
			Traza.error(getClass(), "A: (" +idOperacionTransaccion+ ") el tipo de invocacion está vacio por eso no se pasa al estado Pendiente MIC.");
		}
		
		return cobro;
	}
	
	@Override
	public void desapropiacionParcialMic(ShvCobTransaccionSinOperacion transaccion) throws NegocioExcepcion{
		
		try{
			
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			
			Long idOperacion = transaccion.getIdOperacion();
			MicDesapropiacionDto mensajeMIC = new MicDesapropiacionDto();
			mensajeMIC.setIdOperacion(idOperacion);
			mensajeMIC.setIdTransaccion(transaccion.getIdTransaccion());
			mensajeMIC.setNumeroTransaccion(transaccion.getNumeroTransaccion());
			mensajeMIC.setNumeroTransaccionFicticio(transaccion.getNumeroTransaccionFicticio());
			
			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			
			Traza.auditoria(this.getClass(), "Envío de la desapropiacion parcial de MIC: " +"[GRUPO] = " + transaccion.getGrupo() + " [ID TRANSACCION] = " + transaccion.getIdTransaccion());
			
			if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.MIC, TipoInvocacionEnum.$04)) {
				monitor.enviarMensaje(mensajeMIC);
				pasarWorkflowCobrosProcesoAPendienteMIC(idOperacion,TipoInvocacionEnum.$04,idOperacion.toString(),usuarioBatchCobroImputacion,null);
			}
			
		} catch (ReintentoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Se encarga de la desapropiacion de la/s factura/s y/o medio/s de pago MIC
	 * pertenecientes a las transacciones que recibe por parametro.
	 * 
	 * @param listaOperacionTransacciones  se usa para saber a que transacciones modificar el estado.
	 * @param cobro del cual se obtienen los datos para enviar a MIC el mensaje de desapropiacion.
	 * @throws ReintentoExcepcion 
	 */
	@Override
	public void desapropiacionMic(List<String> listaOperacionTransacciones, ShvCobCobro cobro, String idOperacionADesapropiar) throws NegocioExcepcion, ReintentoExcepcion{
		try{
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			Long idOperacion=null;
			if (Validaciones.isCollectionNotEmpty(listaOperacionTransacciones)){
				
				for (String operacionTransaccion : listaOperacionTransacciones) {
					if(!EstadoTransaccionEnum.listarEstadosError().contains(cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1])).getEstadoProcesamiento())){
						cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(operacionTransaccion.split("\\.")[1], cobro, EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION);
					}
					cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro, operacionTransaccion.split("\\.")[1], EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION, SistemaEnum.MIC,null);
				}
				idOperacion = Long.valueOf(listaOperacionTransacciones.get(0).split("\\.")[0]);
			} else{
				idOperacion = Long.valueOf(idOperacionADesapropiar);
			}
			
			MicDesapropiacionDto mensajeMIC = new MicDesapropiacionDto();
			mensajeMIC.setIdOperacion(idOperacion);
			mensajeMIC.setIdTransaccion(null);
			mensajeMIC.setNumeroTransaccion(null);
			mensajeMIC.setNumeroTransaccionFicticio(null);
			
			mensajeMIC.setModoEjecucion(SiNoEnum.NO);
			mensajeMIC.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
			if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(idOperacion, null, null, SistemaEnum.MIC, TipoInvocacionEnum.$04)) {
				monitor.enviarMensaje(mensajeMIC);
				pasarWorkflowCobrosProcesoAPendienteMIC(idOperacion,TipoInvocacionEnum.$04,idOperacion.toString(),usuarioBatchCobroImputacion,cobro.getWorkflow());
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/***************************************************************************
	 * MENSAJES RECEPCION RESPUESTA
	 * ************************************************************************/
	
	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio apropiacion de deuda.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionApropiacionDeuda(DTO respuesta) throws NegocioExcepcion {
		MicTransaccionADCRespuestaDto mensajeRespuesta = (MicTransaccionADCRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeuda MicTransaccionRespuestaDto: "+  mensajeRespuesta.toString());
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuesta = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		ShvCobTransaccionSinOperacion transaccion = null;
		
		try {
			String idTransaccion = mensajeRespuesta.getIdTransaccion().toString();
			transaccion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(idTransaccion));
			
			if (EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
				
				if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
						&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionMic();
					Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeuda ("+idOperacionTransaccion+", rta: " + estadoRespuesta+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
				}
				
				Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeuda ("+idOperacionTransaccion+", idTransaccion: " + idTransaccion + ", rta: " + estadoRespuesta+") en ejecucion");
				
				Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
				String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
				
				if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta)){
					
					guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
					cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.APROPIADA,SistemaEnum.MIC, null);
					ShvWfWorkflow workflow = pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
					
					if (cobroBatchSoporteImputacionServicio.existenCargosProximaFactura(null,transaccion)) {
						cobroBatchSoporteImputacionServicio.generarCargosOReintegro(transaccion,workflow);
					}
					cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion);
					
				}else{
					if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)) {

						StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
						detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
						detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeError.append(mensajeRespuesta.getDescripcionError());
						
						guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
						cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC, detalleMensajeError.toString());
						this.pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
					} else {
						this.pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
						Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
					}
				}
				
				transaccion = null;
				
			} else {
				Traza.advertencia(getClass(), "No se procesa el mensaje de apropiacion de MIC ("+idOperacionTransaccion+"), dado que ya fue procesado anteriormente.");
			}
		} catch(PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (ReintentoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeuda ("+idOperacionTransaccion+") ejecutado");
	}
	
	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio apropiacion de Medios de pago.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionApropiacionMedioPago(DTO respuesta) throws NegocioExcepcion {
		MicTransaccionADCRespuestaDto mensajeRespuesta = (MicTransaccionADCRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionMedioPago MicTransaccionRespuestaDto: "+  mensajeRespuesta.toString());
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuesta = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		ShvCobTransaccionSinOperacion transaccion = null;
		
		try{
			String idTransaccion = mensajeRespuesta.getIdTransaccion().toString();
			Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionMedioPago ("+idOperacionTransaccion+", idTransaccion: " + idTransaccion + ", rta: " + estadoRespuesta+") en ejecucion");
		
			transaccion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(idTransaccion));
			ShvCobFacturaSinOperacion fact = transaccion.getFactura();
			boolean hayTratamiento = false;
			if (Validaciones.isObjectNull(fact)){
				hayTratamiento=true;
			}
			
			//Para procesar la respuesta de la apropiacion del medio de pago, me aseguro que la transaccion esté en estado en proceso de apropiacion
			//Si hay factura, debe ser de calipso y debe estar pendiente. 
			if (EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())
					&& (((!Validaciones.isObjectNull(fact)
							&& EstadoFacturaMedioPagoEnum.PENDIENTE.equals(fact.getEstado())
							&& SistemaEnum.CALIPSO.equals(fact.getSistemaOrigen()))
							|| hayTratamiento) || transaccion.getGrupo() > 0)){
				
				if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
						&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionMic();
					Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionMedioPago ("+idOperacionTransaccion+", rta: " + estadoRespuesta+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
				}
				
				if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta)){
					guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
					cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.APROPIADA,SistemaEnum.MIC,null);
					cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion);
				}else{
					if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)){

						StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
						detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
						detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeError.append(mensajeRespuesta.getDescripcionError());
						
						guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
						cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC, detalleMensajeError.toString());
					}else{
						Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
					}
				}
				
				Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
				String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
				this.pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
				transaccion = null;
			
			} else {
				Traza.advertencia(getClass(), "No se procesa el mensaje de apropiacion de MIC ("+idOperacionTransaccion+"), dado que ya fue procesado anteriormente.");
			}
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionMedioPago ("+idOperacionTransaccion+") ejecutado");
	}
	
	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio apropiacion de deuda y medios de pago..
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionApropiacionDeudaMedioPago(DTO respuesta) throws NegocioExcepcion {
		MicTransaccionADCRespuestaDto mensajeRespuesta = (MicTransaccionADCRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeudaMedioPago MicTransaccionRespuestaDto: "+  mensajeRespuesta.toString());
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuesta = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		ShvCobTransaccionSinOperacion transaccion = null;
		
		try {
			
			String idTransaccion = mensajeRespuesta.getIdTransaccion().toString();
			Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeudaMedioPago ("+idOperacionTransaccion+", idTransaccion: " + idTransaccion + ", rta: " + estadoRespuesta+") en ejecucion");
		
			transaccion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(idTransaccion));
			
			if (EstadoTransaccionEnum.EN_PROCESO_APROPIACION.equals(transaccion.getEstadoProcesamiento())){
				
				if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)
						&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionMic();
					Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeudaMedioPago ("+idOperacionTransaccion+", rta: " + estadoRespuesta+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
				}
		
				Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
				String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
				if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta)) {
					
					guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
					cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion, EstadoFacturaMedioPagoEnum.APROPIADA,SistemaEnum.MIC,null);
					ShvWfWorkflow workflow = pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
					
					if (cobroBatchSoporteImputacionServicio.existenCargosProximaFactura(null,transaccion)){
						cobroBatchSoporteImputacionServicio.generarCargosOReintegro(transaccion,workflow);
					}
					
					cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion);
				}else{
					if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta)){

						StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
						detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
						detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeError.append(mensajeRespuesta.getDescripcionError());
						
						guardarDatosRespuestaApropiacionMic(transaccion, mensajeRespuesta);
						cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC, detalleMensajeError.toString());
						pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
					}else{
						pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);
						Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
					}
				}
				
				
			} else {
				Traza.advertencia(getClass(), "No se procesa el mensaje de apropiacion de deuda y medio de pago de MIC ("+idOperacionTransaccion+"), dado que ya fue procesado anteriormente.");
			}
			
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}catch(ReintentoExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		} finally {
			transaccion = null;
		}
		
		Traza.auditoria(getClass(), "Metodo micRecepcionApropiacionDeudaMedioPago ("+idOperacionTransaccion+") ejecutado");	
	}

	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio desapropiacion.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionDesapropiacion(DTO respuesta) throws NegocioExcepcion {
		MicTransaccionADCRespuestaDto mensajeRespuesta = (MicTransaccionADCRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo micRecepcionDesapropiacion MicTransaccionRespuestaDto: "+  mensajeRespuesta.toString());
		
		String estadoRespuestaCalipso = "";
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuestaMic = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)
				&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
			estadoRespuestaMic = TipoResultadoEnum.OK.getDescripcionMic();
			Traza.auditoria(getClass(), "Metodo micRecepcionDesapropiacion ("+idOperacionTransaccion+", rta: " + estadoRespuestaMic+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
		}
		
		Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
		Integer numeroTransaccion = Integer.valueOf(idOperacionTransaccion.split("\\.")[1]);
		
		Traza.auditoria(getClass(), "Metodo micRecepcionDesapropiacion ("+idOperacionTransaccion+", rta: " + estadoRespuestaMic+") en ejecucion");
		
		try {
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
			List<ShvCobTransaccion> listaTransaccionesOrdenada = cobro.getTransaccionesOrdenadas();
			
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			/**
			 * Si el número de transaccion es mayor a 0, significa que tenemos una desapropiacion parcial.
			 */
			if (numeroTransaccion > 0){
				micRecepcionDesapropiacionParcial(estadoRespuestaMic,idOperacion,numeroTransaccion,usuarioBatchCobroImputacion,cobro.getWorkflow().getEstado());
				return;
			}
			cambiarEstadoCobroPendienteMicAEnProceso(cobro, usuarioBatchCobroImputacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion);
			
			cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), listaTransaccionesOrdenada, true, 1);
			
			
			// Evaluo si envio a Desapropiar A Calipso
			List<String> listaOperacionTransaccionesApropiadasCalipso = new ArrayList<String>(); /*operacion.transaccion*/
			for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
				
				// MEDIOS DE PAGO
				if(cobroBatchSoporteImputacionServicio.existenMediosPagoCalipso(transaccion)){
					if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(cobroBatchSoporteImputacionServicio.getEstadoMedioPagoCalipso(transaccion))
							&& !EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
							&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
						listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
					}
				}
				
				// FACTURAS
				ShvCobFactura factura = transaccion.getFactura();
				if(factura instanceof ShvCobFacturaCalipso && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
						&& !EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
					listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
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
				}
			}
			
			if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso)){
				// Hay Facturas y/o Medios de pago CALIPSO
				// No pregunto si puedo enviar porque es la primera vez que envio. Y la unica que va a pasar por aca
				estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.desapropiacionCalipso(listaOperacionTransaccionesApropiadasCalipso, cobro,null);
				if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
					cobroBatchSoporteImputacionServicio.aplicarCambiosEstadosSegunRespuestasDesapropiacion(estadoRespuestaCalipso,estadoRespuestaMic,cobro);
					cobroBatchSoporteImputacionServicio.enviarMailyGenerarTarea(cobro,null);
				} else {
					//Si no obtuve respuesta de Calipso y era mixto es porque esta caido Calipso
					// Por lo tanto debo setear los elementos de MIC y no las transacciones
					for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
						if (cobroBatchSoporteImputacionServicio.contieneFacturaOMedioPagoMicEnProcesoDesapropiacion(transaccion)) {
							String idTransaccion = transaccion.getIdTransaccion().toString();
							EstadoTransaccionEnum estado = transaccion.getEstadoProcesamiento();
						
							if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
								if(!EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(estado)){
									if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
										cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.DESAPROPIADA);
									}
								}
								cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.DESAPROPIADA,SistemaEnum.MIC,null);
								
							}else{
								if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
									if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
										cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.ERROR_DESAPROPIACION);
									}
								}
							}
						}
					}
				}
			} else {
				// Solo habia Facturas y/o Medios de pago MIC
				for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
					if (cobroBatchSoporteImputacionServicio.contieneFacturaOMedioPagoMicEnProcesoDesapropiacion(transaccion)) {
						String idTransaccion = transaccion.getIdTransaccion().toString();
						EstadoTransaccionEnum estado = transaccion.getEstadoProcesamiento();
					
						if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
							if(!EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(estado)){
								if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
									cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.DESAPROPIADA);
								}
							}
							cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.DESAPROPIADA,SistemaEnum.MIC,null);
							
						}else{
							if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
								cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC,null);
								if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
									cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.ERROR_DESAPROPIACION);
								}
							}
						}
					}
				}
				
				//U562163 - Cambio el estado del cobro
				if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
					cobroBatchServicio.cambiarEstadoCobro(cobro,Estado.COB_ERROR_COBRO,usuarioBatchCobroImputacion);
					cobroBatchSoporteImputacionServicio.realizarReversionMedioPagoValor(cobro);
					cobroBatchSoporteImputacionServicio.enviarMailyGenerarTarea(cobro,null);

				}else{
					if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
						cobroBatchServicio.cambiarEstadoCobro(cobro,Estado.COB_ERROR_DESAPROPIACION,usuarioBatchCobroImputacion);
						cobroBatchSoporteImputacionServicio.enviarMailyGenerarTarea(cobro,null);
					}
				}
			}
			
			cobro = cobroDao.modificar(cobro);
			cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), false, 1);
			
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		Traza.auditoria(getClass(), "Metodo micRecepcionDesapropiacion ("+idOperacionTransaccion+") ejecutado");
	}

	private void micRecepcionDesapropiacionParcial(String estadoRespuestaMic, Long idOperacion, Integer numeroTransaccion, String usuario, Estado estadoCobro) throws NegocioExcepcion{
		
		ShvCobTransaccionSinOperacion transaccion;
		boolean errorEnDesapropiacion = false;
		
		try {
			transaccion = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYNumeroTransaccion(idOperacion, numeroTransaccion);
		
			if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
				cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion,EstadoFacturaMedioPagoEnum.DESAPROPIADA,SistemaEnum.MIC,null);
				
			}else{
				if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
					cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMPSinCobro(transaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC,null);
					if(!EstadoTransaccionEnum.listarEstadosErrorSinEnProcesoDesapropiacionAplManualRechazada().contains(transaccion.getEstadoProcesamiento())){
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_DESAPROPIACION);
						transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
						errorEnDesapropiacion = true;
					}
				}
			}
			
			ShvWfWorkflow workflow = pasarCobroEnProcesoAplicacionExternaPendProcesarMicAEstadoCorrespondiente(idOperacion, usuario, estadoCobro);
			//todo pensar aca, si tengo que ir a verificar estado cobro
			
			if (errorEnDesapropiacion){
				//AVANZAR WF HASTA EN ERROR EN DESAPROPIACION
				cobroBatchSoporteImputacionServicio.avanzarCobroAErrorEnDesapropiacionParcial(workflow, idOperacion, usuario,true);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
			
		}
		
	}

	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio confirmacion.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionConfirmacion(DTO respuesta) throws NegocioExcepcion {
		
		String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		
		MicTransaccionADCRespuestaDto mensajeRespuesta = (MicTransaccionADCRespuestaDto) respuesta;
		Traza.auditoria(getClass(), "Metodo micRecepcionConfirmacion MicTransaccionRespuestaDto: "+  mensajeRespuesta.toString());
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		String estadoRespuestaMic = mensajeRespuesta.getResultadoInvocacion(); //OK / ER
		if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)
				&& Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(mensajeRespuesta.getCodigoError())) {
			estadoRespuestaMic = TipoResultadoEnum.OK.getDescripcionMic();
			Traza.auditoria(getClass(), "Metodo micRecepcionConfirmacion ("+idOperacionTransaccion+", rta: " + estadoRespuestaMic+") cambiada por " + Constantes.MIC_COD_TRANSACCION_YA_PROCESADA);
		}
		Long idOperacion = Long.valueOf(idOperacionTransaccion.split("\\.")[0]);
		Traza.auditoria(getClass(), "Metodo micRecepcionConfirmacion ("+idOperacionTransaccion+", rta: " + estadoRespuestaMic+") en ejecucion");
		
		try{
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacion(idOperacion);
			List<ShvCobTransaccion> listaTransaccionesOrdenada = cobro.getTransaccionesOrdenadas();

			cambiarEstadoCobroPendienteMicAEnProceso(cobro, usuarioBatchCobroImputacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion);
			
			cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), listaTransaccionesOrdenada, true, 1);
			
			// Evaluo si envio a confirmar A Calipso
			List<String> listaOperacionTransaccionesApropiadasCalipso = new ArrayList<String>(); /*operacion.transaccion*/
			for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
				
				// MEDIOS DE PAGO
				if(cobroBatchSoporteImputacionServicio.existenMediosPagoCalipso(transaccion)){
					if(EstadoFacturaMedioPagoEnum.APROPIADA.equals(cobroBatchSoporteImputacionServicio.getEstadoMedioPagoCalipso(transaccion))
							&& !EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
							&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
						listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
					}
				}
				
				// FACTURAS
				ShvCobFactura factura = transaccion.getFactura();
				if(factura instanceof ShvCobFacturaCalipso && EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
						&& !EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(transaccion.getEstadoProcesamiento())
						&& !listaOperacionTransaccionesApropiadasCalipso.contains(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion())){
					listaOperacionTransaccionesApropiadasCalipso.add(transaccion.getOperacion().getIdOperacion() + "." + transaccion.getIdTransaccion());
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
				}
			}
			
			//Cuando es un cobro MIXTO (CALIPSO/MIC)
			if(Validaciones.isCollectionNotEmpty(listaOperacionTransaccionesApropiadasCalipso)){
				// Hay Facturas y/o Medios de pago CALIPSO
				// No pregunto si puedo enviar porque es la primera vez que envio. Y la unica que va a pasar por aca
				String estadoRespuestaCalipso = cobroBatchSoporteImputacionCalipsoServicio.confirmacionCalipso(listaOperacionTransaccionesApropiadasCalipso, cobro);
				if(!Validaciones.isNullOrEmpty(estadoRespuestaCalipso)){
					cobroBatchSoporteImputacionServicio.aplicarCambiosEstadosSegunRespuestasConfirmacion(estadoRespuestaCalipso,estadoRespuestaMic,cobro);
				} else {
					//Si no obtuve respuesta de Calipso y era mixto es porque esta caido Calipso
					// Por lo tanto debo setear los elementos de MIC y luego
					for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
						if (contieneFacturaTratamientoOMedioPagoMicApropiado(transaccion)) {
							String idTransaccion = transaccion.getIdTransaccion().toString();
						
							if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
								cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.CONFIRMADA,SistemaEnum.MIC,null);
							}else{
								if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
									cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.ERROR,SistemaEnum.MIC,null);
								}
							}
						}
					}
				}
			} else {
				// Cuando es un cobro MIC - Solo habia Facturas y/o Medios de pago MIC
				for (ShvCobTransaccion transaccion : listaTransaccionesOrdenada) {
					if (contieneFacturaTratamientoOMedioPagoMicApropiado(transaccion)) {
						String idTransaccion = transaccion.getIdTransaccion().toString();
						EstadoTransaccionEnum estado = transaccion.getEstadoProcesamiento();
					
						if(TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuestaMic)){
							if(!EstadoTransaccionEnum.ERROR_CONFIRMACION.equals(estado)){
								if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
									cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.CONFIRMADA);
								}
							}
							cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro,idTransaccion,EstadoFacturaMedioPagoEnum.CONFIRMADA,SistemaEnum.MIC,null);
							//MODIFICADO 2/11 LUCAS
							if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){ 
									if(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())){
								
										transaccion.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
									}
							}
						}else{
							if(TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuestaMic)){
								if(!EstadoTransaccionEnum.listarEstadosError().contains(estado)){
									cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(idTransaccion, cobro, EstadoTransaccionEnum.ERROR_CONFIRMACION);
								}
							}
						}
					}
				}
			}
			
			// Unicamente para el batch ReconfirmacionCobrosBatchRunner
            if (Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado())) {
            	// Pregunto si las transacciones estan confirmadas
            	if (cobroBatchSoporteImputacionServicio.todasTransaccionesConfirmadas(cobro)) {
            		// si las transacciones estan confirmadas -> cambio el WF a CONFIRMADO CON ERROR
            	   	cobroBatchServicio.cambiarEstadoCobro(cobro, Estado.COB_CONFIRMADO_CON_ERROR, usuarioBatchCobroImputacion);
                      
                    //Informo los cambios a contabilidad y scard
            	   	cobroBatchSoporteImputacionServicio.informarAContabilidadScard(cobro);
                    cobro = cobroDao.modificar(cobro);
                    cobroBatchSoporteImputacionServicio.enviarMailyGenerarTarea(cobro,null);
            	} else {
                    // Si me volvio a dar error la confirmacion de alguno de los cobradores,
                    // entonces dejo el estado del cobro y solo envio mail y genero la tarea. 
					cobro = cobroDao.modificar(cobro);
					cobroBatchSoporteImputacionServicio.enviarMailyGenerarTarea(cobro,null);
            	}

            } else {
				cobro = cobroDao.modificar(cobro);
			}
			
            cobroBatchSoporteImputacionServicio.tracearDatosImputacionCobro(cobro.getIdCobro(), cobro.getWorkflow(), cobro.getTransaccionesOrdenadas(), false, 1);
			
		} catch(PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		Traza.auditoria(getClass(), "Metodo micRecepcionConfirmacion ("+idOperacionTransaccion+") ejecutado");
	}
	
	/**
	 * Guarda en la base los datos que vienen en el mensaje de respuesta de MIC
	 * @param mensajeRespuesta
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void guardarDatosRespuestaApropiacionMic(ShvCobTransaccionSinOperacion transaccion, 
			MicTransaccionADCRespuestaDto mensajeRespuesta) throws NegocioExcepcion {
		
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		try{
		
				if (transaccion != null) {
					ShvCobFacturaSinOperacion fact = transaccion.getFactura();
					
					if (Validaciones.isCollectionNotEmpty(mensajeRespuesta.getListaDetalleMedioPago())
							&& (TipoInvocacionEnum.$02.equals(mensajeRespuesta.getTipoInvocacion())
							|| TipoInvocacionEnum.$03.equals(mensajeRespuesta.getTipoInvocacion()))){
						
						//obtengo la lista de medios de pago
						Set<ShvCobMedioPagoSinOperacion> listaMedioPago = transaccion.getMediosPago();
						
						//obtengo la respuesta de los medios de pagos enviados a apropiar
						List<MicDetalleMedioPagoRespuestaEntrada> listaDetalleMedioPago = mensajeRespuesta.getListaDetalleMedioPago();
						
						StringBuffer detalleMensajeError   = new StringBuffer(Constantes.EMPTY_STRING);
						
						if (Validaciones.isCollectionNotEmpty(listaDetalleMedioPago)){
							for (MicDetalleMedioPagoRespuestaEntrada medioPagoRespuesta : listaDetalleMedioPago){
								for (ShvCobMedioPagoSinOperacion mp : listaMedioPago){
									
									//si el medio de pago respuesta es una Nota de Credito MIC
									if(TipoMedioPagoEnum.NC.equals(medioPagoRespuesta.getTipoMedioPago())){
										
										//si el medio de pago actual del cobro es una Nota de Credito MIC
										if (mp instanceof ShvCobMedioPagoNotaCreditoMicSinOperacion){
											ShvCobMedioPagoNotaCreditoMicSinOperacion notaCredMic = (ShvCobMedioPagoNotaCreditoMicSinOperacion)mp;
											
											//Si los numeros de NC son iguales pregunto por el resultado de la apropiacion
											if (Long.valueOf(notaCredMic.getNumeroNotaCredito()).equals(medioPagoRespuesta.getNumeroNC())){
												
												if ((TipoResultadoEnum.ERROR.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())
														&& !medioPagoRespuesta.getResultadoApropiacion().getCodigoError().equals(Constantes.MIC_COD_TRANSACCION_YA_PROCESADA))
														||	TipoResultadoEnum.NOK.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())){
													
													detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
													detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
													detalleMensajeError.append( medioPagoRespuesta.getResultadoApropiacion().getDescripcionError());
													
													notaCredMic.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
													notaCredMic.setMensajeEstado(detalleMensajeError.toString());
													notaCredMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
													
												} else if (TipoResultadoEnum.WRN.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())
														&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(medioPagoRespuesta.getResultadoApropiacion().getCodigoError())){
													notaCredMic.setMigradoDeimos(SiNoEnum.SI);
														
												} else if(TipoResultadoEnum.OK.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())){
													notaCredMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
												}
											}
										}
										//si el medio de pago respuesta es Remanente
									} else if(TipoMedioPagoEnum.RT.equals(medioPagoRespuesta.getTipoMedioPago())){
										
										//si el medio de pago actual del cobro es Remanente
										if(mp instanceof ShvCobMedioPagoRemanenteSinOperacion){
											ShvCobMedioPagoRemanenteSinOperacion mpRemanente = (ShvCobMedioPagoRemanenteSinOperacion)mp;
											
											//si cuenta remanente y el tipo remanente son iguales
											if (mpRemanente.getCuentaRemanente().equals(medioPagoRespuesta.getCuentaRemanente())
													&& mpRemanente.getTipoRemanente().equals(medioPagoRespuesta.getTipoRemanente().codigo())){
												
												if ((TipoResultadoEnum.ERROR.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())
														&& !Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equals(medioPagoRespuesta.getResultadoApropiacion().getCodigoError()))
														||	TipoResultadoEnum.NOK.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())){
													
													detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
													detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
													detalleMensajeError.append(medioPagoRespuesta.getResultadoApropiacion().getDescripcionError());
													
													mpRemanente.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
													mpRemanente.setMensajeEstado(detalleMensajeError.toString());
													mpRemanente.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
												
												} else if (TipoResultadoEnum.WRN.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())
														&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(medioPagoRespuesta.getResultadoApropiacion().getCodigoError())){
													mpRemanente.setMigradoDeimos(SiNoEnum.SI);
														
												} else if(TipoResultadoEnum.OK.getDescripcionMic().equals(medioPagoRespuesta.getResultadoApropiacion().getResultadoInvocacion())){
													mpRemanente.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
												}
											}
										}
									}
								}
							}
							
						}
					} 
					//u562163 - Defectos 586, 589, 593, 620
					if (fact instanceof ShvCobFacturaMicSinOperacion) {
						ShvCobFacturaMicSinOperacion factura = (ShvCobFacturaMicSinOperacion) fact;
						factura.setIdCobranza(new Long("0"));
						if (TipoResultadoEnum.OK.getDescripcionMic().equals(mensajeRespuesta.getResultadoInvocacion())){
							factura.setCobradorInteresesGenerados(sumaBigDecimal(mensajeRespuesta.getInteresesGenerados(),
									mensajeRespuesta.getInteresesBonificadosRegulados(),
									mensajeRespuesta.getInteresesBonificadosNoRegulados()));
							factura.setCobradorInteresesTrasladados(mensajeRespuesta.getInteresesGenerados());

							factura.setCobradorInteresesBonificadosRegulados(mensajeRespuesta.getInteresesBonificadosRegulados());
							factura.setCobradorInteresesBonificadosNoRegulados(mensajeRespuesta.getInteresesBonificadosNoRegulados());
						}
						factura.setIdCobranza(mensajeRespuesta.getIdCobranza());
						
						List<MicResultado> listaResultadoApropiacionFactura = mensajeRespuesta.getDetalleCobroFactura().getResultadoApropiacion();
						
						Boolean existeErrorONOK = false;
						Boolean apropiarDeimos = false;
						
						StringBuffer detalleMensajeError   = new StringBuffer(Constantes.EMPTY_STRING);
						
						for (MicResultado resultado : listaResultadoApropiacionFactura){
							if ((TipoResultadoEnum.ERROR.getDescripcionMic().equals(resultado.getResultadoInvocacion())
									&& !Constantes.MIC_COD_TRANSACCION_YA_PROCESADA.equals(resultado.getCodigoError()))
								||	TipoResultadoEnum.NOK.getDescripcionMic().equals(resultado.getResultadoInvocacion())){

								existeErrorONOK = true;
								
								detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
								detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
								detalleMensajeError.append(resultado.getDescripcionError().toString().trim());
								break;
								
							} else if (TipoResultadoEnum.WRN.getDescripcionMic().equals(resultado.getResultadoInvocacion())
									&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(resultado.getCodigoError())){
								
								apropiarDeimos = true;
								
							} else if (TipoResultadoEnum.OK.getDescripcionMic().equals(resultado.getResultadoInvocacion())){
								factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
							}
						}
						
						if (existeErrorONOK) {
							factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
							factura.setMensajeEstado(detalleMensajeError.toString() );
							
							factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR);

							Traza.advertencia(getClass(), "Se realizo el cambio de estado de la factura " 
									+ factura.getEstado().descripcion() + " correspondientes a la operacion id: " + factura.getTransaccion().getIdOperacion());

						} else if(apropiarDeimos) {
							factura.setMigradoDeimos(SiNoEnum.SI);
						}
				}
			} else {
				Traza.advertencia(getClass(), "("+idOperacionTransaccion+") transaccion no encontrada");
			}
		
		}catch (Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Permite modificar el estado del workflow (Pendiente MIC a Pendiente procesar MIC) 
	 * @param idOperacion
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	@Override
	public void pasarWorkflowPendienteMICAPendienteProcesarMic(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,  String usuario) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			if (Estado.COB_PENDIENTE_MIC.equals(workflow.getEstado())){
				workflow = workflowCobros.registrarCobroDePendienteMicAPendienteProcesarMic(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuario);				
				
			} else if(Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())) {
				workflow = workflowCobros.registrarProcesoDeAplicacionExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteProcesarMIC(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuario);
				
			} else if (Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())){
				workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnPendienteConfirmacionManualEnProcesoApliExternaPendienteProcesarMIC(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuario);
				
			}
			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - " +tipoInvocacion.getDescripcion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} finally {
			workflow = null;
		}
	}
	
	/**
	 * Permite modificar el estado del workflow (Pendiente MIC a Proceso) 
	 * @param idOperacion
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	@Override
	public ShvWfWorkflow pasarWorkflowPendienteProcesarMicAEnProceso(Long idOperacion, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion,  String usuario) throws NegocioExcepcion {
		//Busco el workflow del cobro
		ShvWfWorkflow workflow = null;
		try {
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			if (Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC.equals(workflow.getEstado())){
				workflow = workflowCobros.registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(workflow, "", usuario);
				
			} else if (Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado())){
				workflow = workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnPendienteConfirmacionManualYEnProcesoApliExterna(workflow, "", usuario);
				
			} else if (Estado.COB_PEND_PROCESAR_MIC.equals(workflow.getEstado())){
				workflow = workflowCobros.registrarCobroPendienteProcesarMicAEnProceso(workflow, " " + tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")" ,usuario);				
				
			}
			Traza.advertencia(getClass(), "A: (" +idOperacionTransaccion+ ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ] - " + tipoInvocacion.getDescripcion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return workflow;
	}
	
	/**
	 * 
	 * @param cobro
	 * @param estado
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	@Override
	public void cambiarEstadoCobroPendienteMicAEnProceso(ShvCobCobro cobro, String usuario, TipoInvocacionEnum tipoInvocacion, String idOperacionTransaccion) throws NegocioExcepcion {
		try{
			if (Estado.COB_PEND_PROCESAR_MIC.equals(cobro.getWorkflow().getEstado())){
				String datosModificados = tipoInvocacion.getDescripcion() + " (" +idOperacionTransaccion+ ")";
				
				ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroPendienteProcesarMicAEnProceso(cobro.getWorkflow(), datosModificados, usuario);
				cobro.setWorkflow(workflowActualizado);
				cobro = cobroDao.modificar(cobro);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Verifico que no se haya modificado el workflow del cobro, y lo avanzo al estado correspondiente
	 * @param cobro
	 * @param usuario
	 * @param tipoInvocacion
	 * @param idOperacionTransaccion
	 * @throws NegocioExcepcion
	 */
	public ShvWfWorkflow pasarCobroEnProcesoAplicacionExternaPendProcesarMicAEstadoCorrespondiente(Long idOperacion, String usuario, Estado estadoCobro) throws NegocioExcepcion {

		ShvWfWorkflow workflow = null;
		
		try{
			
			workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
			
			
			//Si el estado actual del cobro es igual al del wf, avanzo el wf al estado que le corresponde
			if (estadoCobro.equals(workflow.getEstado())){
				
				if (Estado.COB_PEND_PROCESAR_MIC.equals(workflow.getEstado())){
					workflow = workflowCobros.registrarCobroPendienteProcesarMicAEnProceso(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				} else

				if (Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC.equals(workflow.getEstado())){
					workflow = workflowCobros.registrarProcesoDeAplicacionExternaPendienteProcesarMICAEnProcesoDeAplicacionExterna(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				} else
				
				if (Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado())){
					
					workflow = 
						workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnPendienteConfirmacionManualYEnProcesoApliExterna
						(workflow, "", usuario);
					Traza.advertencia(getClass(), "A: (" + idOperacion + ") El cobro se pasa al estado [ " + workflow.getEstado().descripcion() + " ]");
				}
			
			} else {

				//Si el estado actual del cobro es diferente al wf recien obtenido de la base, significa que se actualizó en el online y debemos lanzar excepcion
				throw new NegocioExcepcion("ERROR: El estado del Workflow fue modificado, se procesará nuevamente", "");
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return workflow;
	}
	
	/**
	 * verifica si la transaccion contiene una factura, tratamiento y/o medios de pago
	 * MIC en estado Apropiado.
	 * @param transaccion
	 * @return
	 */
	private Boolean contieneFacturaTratamientoOMedioPagoMicApropiado(ShvCobTransaccion transaccion) {
		
		ShvCobFactura factura = transaccion.getFactura();
		
		if (!Validaciones.isObjectNull(factura)){
			
			if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())
					&& factura instanceof ShvCobFacturaMic){
				return true;
			}
		} else {
			
			ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();
			
			if (!Validaciones.isObjectNull(tratamiento)){
				
				if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(tratamiento.getEstado())
						&& TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())){
					return true;
				}
			}
		}
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(medioPago.getEstado())
					&& (medioPago instanceof ShvCobMedioPagoMic
						|| medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic)){
				return true;
			}
		}
		
		return false;
	}
	
	/***************************************************************************
	 * MENSAJES RECEPCION RESPUESTA CARGOS 
	 * ************************************************************************/
	
	/**
	 * Método para registrar la recepcion de los mensajes de respuesta de MIC 
	 * cuando se envio apropiacion de deuda.
	 * @throws NegocioExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void micRecepcionCargo(DTO respuesta) throws NegocioExcepcion {
		
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
			ShvCobTransaccionSinOperacion transaccion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(idTransaccion));
//			ShvCobTransaccionSinOperacion transaccion = cobroDao.buscarTransaccionAProcesarPorIdOperacion(idOperacion);
			cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, true);
			
			if (TipoResultadoEnum.OK.getDescripcionMic().equals(estadoRespuesta) || (TipoResultadoEnum.ERROR.getDescripcionMic().equals(estadoRespuesta))){
				guardarDatosRespuestaCargoMic(transaccion, mensajeRespuesta);
				transaccion = cobroDao.guardarTransaccionSinOperacion(transaccion);
			}else{
				Traza.advertencia(getClass(), "No se actualizan las facturas y medios de pago por respuesta invalida. La respuesta fue '"+estadoRespuesta+"'");
			}
			
			cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(idOperacion, transaccion, false);
			
			String usuarioBatchCobroImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
			this.pasarWorkflowPendienteProcesarMicAEnProceso(idOperacion, mensajeRespuesta.getTipoInvocacion(), idOperacionTransaccion, usuarioBatchCobroImputacion);

		} catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		Traza.auditoria(getClass(),  "Metodo " + nombreMetodo + " ("+idOperacionTransaccion+") ejecutado");
	}
	
	
	/**
	 * Guarda en la base los datos que vienen en el mensaje de respuesta de MIC
	 * @param mensajeRespuesta
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void guardarDatosRespuestaCargoMic(ShvCobTransaccionSinOperacion transaccion, 
			MicTransaccionGeneracionCargosRespuestaDto mensajeRespuesta) throws NegocioExcepcion {
		String idOperacionTransaccion = mensajeRespuesta.getIdOperacionTransaccion();
		
		if (!Validaciones.isObjectNull(transaccion)) {
			
			if (TipoInvocacionEnum.$06.equals(mensajeRespuesta.getTipoInvocacion())){
				List<ShvCobMedioPagoSinOperacion> listaMediosPagoProxFacturaMic = 
						cobroBatchSoporteImputacionServicio.listarMediosPagoProxFactura(transaccion,SistemaEnum.MIC);
				
				if (Validaciones.isCollectionNotEmpty(listaMediosPagoProxFacturaMic)){
					
					ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion mpProxFacturaMic = null;
					
					for (ShvCobMedioPagoSinOperacion mp : listaMediosPagoProxFacturaMic){
						
						mpProxFacturaMic = (ShvCobMedioPagoDebitoProximaFacturaMicSinOperacion)mp;
						
					}
					
					if (TipoResultadoEnum.OK.getDescripcionMic().equals(mensajeRespuesta.getResultadoInvocacion())){
						
						mpProxFacturaMic.setImporteBonificacionIntereses(mensajeRespuesta.getInteresesBonificadosRegulados());
						//u562163 - Defectos 586, 589, 593, 620
						mpProxFacturaMic.setCobradorInteresesGenerados(sumaBigDecimal(mensajeRespuesta.getInteresesGeneradosRegulados(), mensajeRespuesta.getInteresesBonificadosRegulados(), null));
						mpProxFacturaMic.setCobradorInteresesTrasladados(mensajeRespuesta.getInteresesGeneradosRegulados());
						mpProxFacturaMic.setMensajeEstado(TipoResultadoEnum.OK.getDescripcionMic());
						mpProxFacturaMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
						mpProxFacturaMic.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
						
					} else if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(mensajeRespuesta.getResultadoInvocacion())){
						
						mpProxFacturaMic.setMensajeEstado(EstadoFacturaMedioPagoEnum.ERROR.descripcion());

						StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
						detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
						detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeError.append(mensajeRespuesta.getDescripcionError());
						
						mpProxFacturaMic.setMensajeEstado(detalleMensajeError.toString());
						mpProxFacturaMic.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
						mpProxFacturaMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
					}
				}
			} else if (TipoInvocacionEnum.$07.equals(mensajeRespuesta.getTipoInvocacion())){
				ShvCobTratamientoDiferenciaSinOperacion tratamiento = transaccion.getTratamientoDiferencia();
				
				if (TipoResultadoEnum.OK.getDescripcionMic().equals(mensajeRespuesta.getResultadoInvocacion())){
					tratamiento.setCobradorInteresesBonificados(mensajeRespuesta.getInteresesBonificadosNoRegulados());
					//u562163 - Defectos 586, 589, 593, 620
					tratamiento.setCobradorInteresesGenerados(sumaBigDecimal(mensajeRespuesta.getInteresesGeneradosNoRegulados(), mensajeRespuesta.getInteresesBonificadosNoRegulados(), null));
					tratamiento.setCobradorInteresesTrasladados(mensajeRespuesta.getInteresesGeneradosNoRegulados());
					tratamiento.setMensajeEstado(TipoResultadoEnum.OK.getDescripcionMic());
					tratamiento.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
				} else if (TipoResultadoEnum.ERROR.getDescripcionMic().equals(mensajeRespuesta.getResultadoInvocacion())){
					tratamiento.setMensajeEstado(EstadoFacturaMedioPagoEnum.ERROR.descripcion());

					StringBuffer detalleMensajeError = new StringBuffer(Constantes.EMPTY_STRING);
					detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
					detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
					detalleMensajeError.append(mensajeRespuesta.getDescripcionError());
					
					tratamiento.setMensajeEstado(detalleMensajeError.toString());
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
					tratamiento.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
				}
			}
		} else {
			Traza.advertencia(getClass(), "("+idOperacionTransaccion+") Transaccion no encontrada");
		}
	}
	
	/**
     * Suma los tres BigDecimal que recibe por parametro evaluando que si 
     * alguno es null lo toma como cero. Pero si todos son null retorna null.
     * @return
     */
    private BigDecimal sumaBigDecimal(BigDecimal numero1, BigDecimal numero2, BigDecimal numero3){
    	if (Validaciones.isObjectNull(numero1) && Validaciones.isObjectNull(numero2) && Validaciones.isObjectNull(numero3)){
    		return null;
    	}
    	
    	BigDecimal resultado = new BigDecimal(0);
    	if (!Validaciones.isObjectNull(numero1)){
    		resultado = resultado.add(numero1);
    	}
    	if (!Validaciones.isObjectNull(numero2)){
    		resultado = resultado.add(numero2);
    	}
    	if (!Validaciones.isObjectNull(numero3)){
    		resultado = resultado.add(numero3);
    	}
    	
    	return resultado;
    	
    }
    
	@Override
	public List<Long> listarCobrosPendientesProcesarMIC() throws NegocioExcepcion {
		try {
			List<Long> lista = cobroDao.listarCobrosPendientesProcesarMIC();
			return lista;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public List<Long> listarCobrosImpManualPendientesProcesarMIC() throws NegocioExcepcion {
		try {
			List<Long> lista = cobroDao.listarCobrosImpManualPendientesProcesarMIC();
			return lista;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

}



