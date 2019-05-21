package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.registros.mapeos.MicOperacionMasivaRegistroDtoMapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.OperacionMasivaRegistroVo;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class OperacionMasivaCobroServicioImpl extends CobroBatchSoporteServicioImpl implements IOperacionMasivaCobroServicio {

	@Autowired
	private ICobroOnlineDao cobroOnlineDao;

	@Autowired
	private IWorkflowCobros workflowCobros;

	@Autowired
	private IGenericoDao genericoDao;

	@Autowired
	private ICobroDao cobroDao;	

	@Autowired
	private ICobroBatchServicio cobroBatchServicio;	

	@Autowired
	private IParametroServicio parametroServicio;

	@Autowired
	private ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;

	@Autowired
	private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;

	@Autowired
	private ITeamComercialServicio teamComercialServicio;

	/****************************************************************************************************
	 * Servicios Comunes
	 ***************************************************************************************************/
	@Override
	public Long crearCobro(OperacionMasivaRegistroVo vo) throws NegocioExcepcion {
		try {
			String usuarioBatch = Utilidad.EMPTY_STRING;
			ShvWfWorkflow wf = null;
			ShvCobEdCobro modelo = new ShvCobEdCobro();
			CobroDto dto = new CobroDto();
			Date fechaWf = null;

			usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			wf = workflowCobros.crearWorkflow("", vo.getIdAnalista());
			wf = workflowCobros.registrarCobroMasivoInicioCobrado(wf, "", vo.getIdAnalista());
			fechaWf = wf.getFechaUltimaModificacion();
			modelo.setIdCobro(genericoDao.proximoValorSecuencia("SEQ_SHV_COB_COBRO"));
			
			modelo.setIdOperacion(vo.getRegistro().getIdOperacion());
			modelo.setIdCobroPadre(modelo.getIdCobro());
			
			dto.setIdAnalista(vo.getIdAnalista());
			dto.setIdCopropietario(vo.getIdCopropietario());
			dto.setSegmento(vo.getSegmento().getIdSegmento());
			dto.setEmpresa(vo.getEmpresa().getIdEmpresa());
			dto.setIdEmpresa(vo.getEmpresa().getIdEmpresa());
			dto.setIdSegmento(vo.getSegmento().getIdSegmento());
			dto.setIdMotivoCobro(vo.getMotivo().getIdMotivoCobro().toString());

			dto.setFechaAlta(vo.getFechaDerivacion());
			dto.setUsuarioAlta(vo.getUsuarioOperacionMasiva());
			dto.setOperation(ConstantesCobro.NUEVO_COBRO);
			dto.setUsuarioModificacion(vo.getUsuarioOperacionMasiva());
			
			/// la fecha de imputacion y ultima modificacion deben ser de cuando se modifica el workflow y no la que viene de mic ///
			//dto.setFechaUltimaModificacion(fechaNow);
			//dto.setFechaImputacion(vo.getRegistro().getFechaValorCobranza());
			dto.setFechaUltimaModificacion(fechaWf);
			dto.setFechaImputacion(fechaWf);
			
			
			
			dto.setFechaDerivacion(vo.getRegistro().getFechaEntrada());
			dto.setUsuarioDerivacion(usuarioBatch);
			dto.setMonedaOperacion(vo.getMonedaDelCobro().getSigno2());
//			cobroDto.getFechaAprobacionSupervisorCobranza());
//			cobroDto.getUsuarioAprobacionSupervisorCobranza());
//			cobroDto.getFechaAprobacionReferenteCobranza());
//			cobroDto.getUsuarioAprobacionReferenteCobranza());
//			
//			modelo.setFechaImputacion(cobroDto.getFechaImputacion());
//			modelo.setUsuarioImputacion(cobroDto.getUsuarioImputacion());
//			modelo.setFechaUltimaModificacion(cobroDto.getFechaUltimaModificacion());
//			modelo.setUsuarioUltimaModificacion(cobroDto.getUsuarioModificacion());
//			modelo.setObservacion(cobroDto.getObservacion());
			
			
			
			List<ClienteDto> clientes =  MicOperacionMasivaRegistroDtoMapeador.map(vo.getRegistro().getClientesSiebel());
			CobroDebitoDto debito = null;
			CobroCreditoDto credito = null;
			CobroMedioDePagoDto medioPago = null;
			CobroTratamientoDiferenciaDto tratamiento = null;
			
			dto.setClientes(new HashSet<ClienteDto>());
			
			if (
				vo.getRegistro() instanceof ShvMasRegistroAplicarDeuda ||
				vo.getRegistro() instanceof ShvMasRegistroDesistimiento
			) {
				debito = MicOperacionMasivaRegistroDtoMapeador.map(new CobroDebitoDto(), vo.getRegistro());
			}
			if (
				vo.getRegistro() instanceof ShvMasRegistroAplicarDeuda ||
				vo.getRegistro() instanceof ShvMasRegistroGanancias ||
				vo.getRegistro() instanceof ShvMasRegistroReintegro
			) {
				credito = MicOperacionMasivaRegistroDtoMapeador.map(new CobroCreditoDto(), vo.getRegistro());
			}
			if (vo.getRegistro() instanceof ShvMasRegistroDesistimiento) {
				medioPago = MicOperacionMasivaRegistroDtoMapeador.map(new CobroMedioDePagoDto(), vo.getRegistro());
			}
			if (
				vo.getRegistro() instanceof ShvMasRegistroReintegro ||
				vo.getRegistro() instanceof ShvMasRegistroGanancias
			) {
				tratamiento = MicOperacionMasivaRegistroDtoMapeador.map(new CobroTratamientoDiferenciaDto(), vo.getRegistro());
			}
			for (ClienteDto cli : clientes) {
				dto.getClientes().add(cli);
			}
			if (!Validaciones.isObjectNull(debito)) {
				dto.setDebitos(new HashSet<CobroDebitoDto>());
				dto.getDebitos().add(debito);
			}
			if (!Validaciones.isObjectNull(credito)) {
				dto.setCreditos(new HashSet<CobroCreditoDto>());
				dto.getCreditos().add(credito);
			}
			if (!Validaciones.isObjectNull(medioPago)) {
				dto.setMedios(new HashSet<CobroMedioDePagoDto>());
				dto.getMedios().add(medioPago);
			}
			if (!Validaciones.isObjectNull(tratamiento)) {
				dto.setTratamientoDiferencia(tratamiento);
			}
			if (!dto.getClientes().isEmpty()) {
				UsuarioLdapDto usuarioAnalistaTeamComercial = teamComercialServicio.obtenerAnalistaTeamComercial(dto.getClientes());
				if (!Validaciones.isObjectNull(usuarioAnalistaTeamComercial)) {
					dto.setNombreAnalistaTeamComercial(usuarioAnalistaTeamComercial.getNombreCompleto());
					dto.setIdAnalistaTeamComercial(usuarioAnalistaTeamComercial.getTuid());
				}
			}
			modelo = (ShvCobEdCobro) defaultMapeador.map(dto, modelo);

			if (!Validaciones.isObjectNull(modelo.getDebitos()) && !modelo.getDebitos().isEmpty()) {
				modelo.getDebitos().iterator().next().setIdDebitoReferencia(debito.getIdDebitoPantalla());
			}
			if (!Validaciones.isObjectNull(modelo.getCreditos()) && !modelo.getCreditos().isEmpty()) {
				modelo.getCreditos().iterator().next().setIdCreditoReferencia(credito.getIdCreditoPantalla());
			}
			modelo.setWorkflow(wf);
			modelo.setImporteTotal(calcularImporteTotal(modelo));
			modelo.setImporteTotal(this.calcularImporteTotal(modelo));
			modelo.setIdCobroPadre(modelo.getIdCobro());
			//modelo.setCheckSumAlSimular();
			modelo.setUsuarioUltimaModificacion(usuarioBatch);
			modelo.setFechaUltimaModificacion(Utilidad.obtenerFechaActual());
			modelo.setIdOperacionMasiva(vo.getIdOperacionMasiva());
			modelo = cobroOnlineDao.guardarCobro(modelo);

			return modelo.getIdCobro();
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public Long generarCobroBatch(Long idCobro, ShvMasRegistro shvMasRegistroModelo) throws NegocioExcepcion {
		// Buscar ShvCobEdCobro
		
		ShvCobEdCobro shvCobEdCobro = null;
		
		try {
			shvCobEdCobro = cobroOnlineDao.buscarCobro(idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	
		ComponentesCobroBatch componentesCobroBatch = this.convertirCobroEnEdicionEnComponentesCobroBatch(shvCobEdCobro);
		
		
		// Aplicar Deuda y desistimiento
		if (shvMasRegistroModelo instanceof ShvMasRegistroDesistimiento || shvMasRegistroModelo instanceof ShvMasRegistroAplicarDeuda) {
			if (!componentesCobroBatch.getListaFacturas().isEmpty()) {
				ShvCobFacturaMic shvCobFacturaMic = (ShvCobFacturaMic) componentesCobroBatch.getListaFacturas().get(0);
				ShvCobEdDebito debito  = shvCobEdCobro.getDebitos().iterator().next();
	
				if (shvMasRegistroModelo instanceof ShvMasRegistroAplicarDeuda) {
					ShvMasRegistroAplicarDeuda shvMasRegistro = (ShvMasRegistroAplicarDeuda) shvMasRegistroModelo;
					
					shvCobFacturaMic.setFechaValor(shvMasRegistro.getFechaValorCobranza());			
					shvCobFacturaMic.setCobradorInteresesBonificadosNoRegulados(
						!Validaciones.isObjectNull(shvMasRegistro.getCobranzaApropiacionInteresesBonificadosNoRegulados()) ? shvMasRegistro.getCobranzaApropiacionInteresesBonificadosNoRegulados() : new BigDecimal(0)
					);
					shvCobFacturaMic.setCobradorInteresesBonificadosRegulados(
							!Validaciones.isObjectNull(shvMasRegistro.getCobranzaApropiacionInteresesBonificadosRegulados()) ? shvMasRegistro.getCobranzaApropiacionInteresesBonificadosRegulados() : new BigDecimal(0)
					);
					shvCobFacturaMic.setCobradorInteresesTrasladadosNoRegulados(
							!Validaciones.isObjectNull(shvMasRegistro.getCobranzaApropiacionInteresesTrasladadosNoRegulados()) ? shvMasRegistro.getCobranzaApropiacionInteresesTrasladadosNoRegulados() : new BigDecimal(0)
					);
					shvCobFacturaMic.setCobradorInteresesTrasladadosRegulados(
							!Validaciones.isObjectNull(shvMasRegistro.getCobranzaApropiacionInteresesTrasladadosRegulados()) ? shvMasRegistro.getCobranzaApropiacionInteresesTrasladadosRegulados() : new BigDecimal(0)
					);
					shvCobFacturaMic.setCobradorInteresesTrasladados(shvCobFacturaMic.getCobradorInteresesTrasladadosRegulados().add(shvCobFacturaMic.getCobradorInteresesTrasladadosNoRegulados()));
					shvCobFacturaMic.setCobradorInteresesGenerados(
						(shvCobFacturaMic.getCobradorInteresesTrasladados().add(shvCobFacturaMic.getCobradorInteresesBonificadosRegulados()).add(shvCobFacturaMic.getCobradorInteresesBonificadosNoRegulados()))				
					);
					shvCobFacturaMic.setFechaValor(shvMasRegistro.getDebitoImputadoGralFechaPuestaCobro());
					shvCobFacturaMic.setTipoPago(shvMasRegistro.getTipoOperacion());
					shvCobFacturaMic.setMensajeEstado(TipoMensajeEstadoEnum.OK.name());
					shvCobFacturaMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					
					shvCobFacturaMic.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
					shvCobFacturaMic.setQueHacerConIntereses(shvMasRegistro.getAccionSobreIntereses());
					if (
						TratamientoInteresesEnum.TM.equals(shvMasRegistro.getAccionSobreIntereses()) ||
						TratamientoInteresesEnum.TV.equals(shvMasRegistro.getAccionSobreIntereses())
					) {
						shvCobFacturaMic.setTrasladarIntereses(SiNoEnum.SI);
						shvCobFacturaMic.setPorcentajeBonifIntereses(0);
						shvCobFacturaMic.setImporteBonificacionIntereses(BigDecimal.ZERO);
					} else {
						if (TratamientoInteresesEnum.BV.equals(shvMasRegistro.getAccionSobreIntereses())) {
							if (
								shvMasRegistro.getPorcentajeBonificacionIntereses() != null &&
								shvMasRegistro.getPorcentajeBonificacionIntereses() < Constantes.CIEN_PORCIENTO
							) {
								shvCobFacturaMic.setQueHacerConIntereses(TratamientoInteresesEnum.TC);
								shvCobFacturaMic.setPorcentajeBonifIntereses(
									Integer.parseInt(!Validaciones.isObjectNull(shvMasRegistro.getPorcentajeBonificacionIntereses()) ?  "" + shvMasRegistro.getPorcentajeBonificacionIntereses() : "0") 
								);
								shvCobFacturaMic.setImporteBonificacionIntereses(shvCobFacturaMic.getImporteBonificacionIntereses());	
							} else {
								shvCobFacturaMic.setPorcentajeBonifIntereses(100);
								shvCobFacturaMic.setImporteBonificacionIntereses(shvCobFacturaMic.getImporteBonificacionIntereses());
							}
						} else {
							shvCobFacturaMic.setPorcentajeBonifIntereses(0);
							shvCobFacturaMic.setImporteBonificacionIntereses(shvCobFacturaMic.getImporteBonificacionIntereses());
						}
						shvCobFacturaMic.setTrasladarIntereses(SiNoEnum.NO);

						if (!Validaciones.isNullEmptyOrDash(debito.getAcuerdoFacturacion()) && !"null".equals(debito.getAcuerdoFacturacion())) {							if (debito.getAcuerdoFacturacion().equals(shvMasRegistro.getAcuerdoFacturacionDestino())) {
								shvCobFacturaMic.setAcuerdoTrasladoCargo(debito.getAcuerdoFacturacion());
							
							} else {
								shvCobFacturaMic.setAcuerdoTrasladoCargoOriginal(debito.getAcuerdoFacturacion());
								shvCobFacturaMic.setAcuerdoTrasladoCargo(shvMasRegistro.getAcuerdoFacturacionDestino() != null?shvMasRegistro.getAcuerdoFacturacionDestino().toString():null);
							}
							shvCobFacturaMic.setIdClienteLegadoAcuerdoTrasladoCargo(shvMasRegistro.getAcuerdoGralCodigoCliente());
						}
					}
				} else {
					ShvMasRegistroDesistimiento shvMasRegistro = (ShvMasRegistroDesistimiento) shvMasRegistroModelo;
					shvCobFacturaMic.setFechaValor(shvMasRegistro.getDebitoImputadoGralFechaPuestaCobro());
					shvCobFacturaMic.setTipoPago(TipoPagoEnum.PAGO_TOTAL);
					shvCobFacturaMic.setPorcentajeBonifIntereses(0);
					shvCobFacturaMic.setImporteBonificacionIntereses(BigDecimal.ZERO);
					shvCobFacturaMic.setCobradorInteresesGenerados(new BigDecimal(0));
					shvCobFacturaMic.setCobradorInteresesTrasladados(new BigDecimal(0));
					shvCobFacturaMic.setCobradorInteresesBonificadosNoRegulados(new BigDecimal(0));
					shvCobFacturaMic.setCobradorInteresesBonificadosRegulados(new BigDecimal(0));
					
					shvCobFacturaMic.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
					shvCobFacturaMic.setMensajeEstado(TipoMensajeEstadoEnum.OK.name());
					shvCobFacturaMic.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				}
			}
		}
		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia())) {
			if (shvMasRegistroModelo instanceof ShvMasRegistroReintegro) {
				ShvMasRegistroReintegro shvMasRegistro = (ShvMasRegistroReintegro) shvMasRegistroModelo;
				
				BigDecimal intereses = new BigDecimal(0);
				if (!Validaciones.isObjectNull(shvMasRegistro.getCobranzaCargoInteresesBonificadosNoRegulados())) {
					intereses = intereses.add(shvMasRegistro.getCobranzaCargoInteresesBonificadosNoRegulados());
				}
				if (!Validaciones.isObjectNull(shvMasRegistro.getCobranzaCargoInteresesBonificadosRegulados())) {
					intereses = intereses.add(shvMasRegistro.getCobranzaCargoInteresesBonificadosNoRegulados());
				}
				componentesCobroBatch.getTratamientoDiferencia().setCobradorInteresesBonificados(intereses);
				if (!Validaciones.isObjectNull(shvMasRegistro.getCobranzaCargoInteresesTrasladadosNoRegulados())) {
					intereses = intereses.add(shvMasRegistro.getCobranzaCargoInteresesTrasladadosNoRegulados());
				}
				if (!Validaciones.isObjectNull(shvMasRegistro.getCobranzaCargoInteresesBonificadosRegulados())) {
					intereses = intereses.add(shvMasRegistro.getCobranzaCargoInteresesTrasladadosRegulados());
				}
				
				componentesCobroBatch.getTratamientoDiferencia().setCobradorInteresesGenerados(intereses);
			} else if (shvMasRegistroModelo instanceof ShvMasRegistroGanancias) {
				//ShvMasRegistroGanancias shvMasRegistro = (ShvMasRegistroGanancias) shvMasRegistroModelo;
				
			}
		}
		ShvCobCobro shvCobCobro = this.armarCobro(componentesCobroBatch);

		ShvCobTransaccion shvCobTransaccion = new ShvCobTransaccion();

		if (!componentesCobroBatch.getListaFacturas().isEmpty()) {
			componentesCobroBatch.getListaFacturas().get(0).setTransaccion(shvCobTransaccion);
			shvCobTransaccion.getShvCobFactura().add(componentesCobroBatch.getListaFacturas().get(0));
			shvCobTransaccion.setGrupo(0);
			shvCobTransaccion.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
		}
		if (!componentesCobroBatch.getListaMediosDePago().isEmpty()) {
			for (ShvCobMedioPago medio : componentesCobroBatch.getListaMediosDePago()) {
				medio.setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
				medio.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				medio.setTransaccion(shvCobTransaccion);
				shvCobTransaccion.getShvCobMedioPago().add(medio);
				
			}
		}
		if (!Validaciones.isObjectNull(componentesCobroBatch.getTratamientoDiferencia())) {
			componentesCobroBatch.getTratamientoDiferencia().setTransaccion(shvCobTransaccion);
			// Calculo origen y leyendas
			componentesCobroBatch.setTratamientoDiferencia((ShvCobTratamientoDiferencia)
				this.calcularOrigenCargo(componentesCobroBatch.getTratamientoDiferencia(),
				componentesCobroBatch.getListaMediosDePago().get(0), 
				componentesCobroBatch.getOperacion().getIdOperacion()));
			componentesCobroBatch.getTratamientoDiferencia().setEstado(EstadoFacturaMedioPagoEnum.CONFIRMADA);
			if (shvMasRegistroModelo instanceof ShvMasRegistroGanancias) {
				ShvMasRegistroGanancias shv = (ShvMasRegistroGanancias) shvMasRegistroModelo;
				if (!Validaciones.isObjectNull(shv.getNumeroReferenciaNC())) {
					componentesCobroBatch.getTratamientoDiferencia().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				}
			} else {
				componentesCobroBatch.getTratamientoDiferencia().setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			}
			shvCobTransaccion.getListaTratamientosDiferencias().add(componentesCobroBatch.getTratamientoDiferencia());
			shvCobTransaccion.setGrupo(0);
			shvCobTransaccion.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
		}
		// COMO el idTransaccion es siempre 1
		shvCobTransaccion.setNumeroTransaccion(1);
		shvCobTransaccion.setOperacion(componentesCobroBatch.getOperacion());
		shvCobTransaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK); 
		shvCobTransaccion.setEstadoProcesamiento(EstadoTransaccionEnum.CONFIRMADA);
	
		shvCobCobro.getOperacion().getTransacciones().add(shvCobTransaccion);
		cobroBatchServicio.actualizarTratamientoIntereses(shvCobCobro);
		try {
			shvCobCobro = cobroDao.modificar(shvCobCobro);
			cobroBatchSoporteImputacionServicio.informarAContabilidadScard(shvCobCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return shvCobCobro.getIdCobro();
	}
	/**
	 * El importe total del cobro será la suma de los medios de pago que se hayan seleccionado para configurar el cobro
	 * mas el tratamiento de la diferencia cuando la misma es tratada como Debito a Proxima Factura (que termina siendo
	 * un medio de pago)
	 *  
	 * @param edCobro
	 * @return
	 */
	private BigDecimal calcularImporteTotal(ShvCobEdCobro edCobro) {
		
		BigDecimal importeTotal = BigDecimal.ZERO;
		
		for (ShvCobEdCredito credito : edCobro.getCreditos()) {
			importeTotal = importeTotal.add(credito.getImporteAUtilizar());
		}
		
		for (ShvCobEdOtrosMedioPago otroMedioPago : edCobro.getMediosPagos()) {
			importeTotal = importeTotal.add(otroMedioPago.getImporte());
		}
		
		if (!Validaciones.isObjectNull(edCobro.getTratamientoDiferencia())) {
			if (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.equals(edCobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				importeTotal = importeTotal.add(edCobro.getTratamientoDiferencia().getImporte());
			}
		}
		
		return importeTotal;
	}
}
