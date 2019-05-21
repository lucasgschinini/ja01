package ar.com.telecom.shiva.negocio.servicios.impl;

import static java.lang.String.valueOf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenAplicacionImporteCompensacionDocumentoCapDetalle;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobFactura;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobMedioPago;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionCesionCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIIBB;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIntercompany;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionOtras;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoSaldoACobrar;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;

public class CobroBatchSoporteServicioImpl extends Servicio implements ICobroBatchSoporteServicio {

	@Autowired private ITipoMedioPagoServicio tipoMedioPagoServicio;
	
	@Autowired private IParametroServicio parametroServicio;
	
	@Autowired private IValorServicio valorServicio;

	@Autowired private ICobroDao cobroDao;
	
	@Autowired private ITipoComprobanteDao tipoComprobanteDao;
	
	@Autowired private IMensajeriaTransaccionDao mensajeriaTransaccionDao;
	

	/**
	 * 
	 * @param edCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	public ComponentesCobroBatch convertirCobroEnEdicionEnComponentesCobroBatch(ShvCobEdCobro edCobro) throws NegocioExcepcion,SimulacionCobroExcepcion {

		ComponentesCobroBatch componentesCobroBatch = new ComponentesCobroBatch();

		//
		// Mapeo de otros datos generales
		//
		componentesCobroBatch.setIdCobro(edCobro.getIdCobro());
		componentesCobroBatch.setIdOperacion(edCobro.getIdOperacion());
		componentesCobroBatch.setUsuarioCreacion(edCobro.getUsuarioUltimaModificacion());
		componentesCobroBatch.setWorkflow(edCobro.getWorkflow());
		componentesCobroBatch.setIdEmpresa(edCobro.getEmpresa().getIdEmpresa());
		componentesCobroBatch.setIdSegmento(edCobro.getSegmento().getIdSegmento());
		
		componentesCobroBatch.setMonedaOperacion(edCobro.getMonedaOperacion());
		//
		// Mapeo de Documentos a Cobrar, y ordeno la lista
		//
		componentesCobroBatch.setListaFacturas(mapearDocumentosACobrar(edCobro));
		if (componentesCobroBatch.getListaFacturas().size() > 1) {
			Collections.sort(componentesCobroBatch.getListaFacturas(),
					new ComparatorOrdenSimulacionShvCobFactura());
		}

		//
		// Mapeo de Medios de Pago
		//
		componentesCobroBatch.setListaMediosDePago(mapearMediosPagoAUtilizar(edCobro));
		if (componentesCobroBatch.getListaMediosDePago().size() > 1) {
			Collections.sort(componentesCobroBatch.getListaMediosDePago(),
					new ComparatorOrdenSimulacionShvCobMedioPago());
		}

		//
		// Mapeo de Debito Proxima Factura, a partir del tratamiento de la
		// diferencia
		//
		if (!Validaciones.isObjectNull(edCobro.getTratamientoDiferencia())
				&& TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.equals(edCobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

			componentesCobroBatch.getListaMediosDePago().add(
					mapearDebitoProximaFactura(edCobro
							.getTratamientoDiferencia(), componentesCobroBatch
							.obtenerUltimaFacturaPorFechaVencimiento(),
							componentesCobroBatch.getIdOperacion()));
		}
		
		//
		// Mapeo Saldo a Cobrar, a partir del tratamiento de la
		// diferencia
		//
		if (!Validaciones.isObjectNull(edCobro.getTratamientoDiferencia())
				&& TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.equals(edCobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {

			componentesCobroBatch.getListaMediosDePago().add(
					mapearSaldoACobrar(edCobro
							.getTratamientoDiferencia(), componentesCobroBatch
							.obtenerUltimaFacturaPorFechaVencimiento(),
							componentesCobroBatch.getIdOperacion()));
		}

		//
		// Mapeo de Reintegros
		//
		componentesCobroBatch.setTratamientoDiferencia(mapearTratamientoDiferencia(edCobro));

		return componentesCobroBatch;
	}

	/**
	 * 
	 * @param componentesCobroBatch
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	public ShvCobCobro armarCobro(ComponentesCobroBatch componentesCobroBatch) throws NegocioExcepcion {

		Long idCobro = componentesCobroBatch.getIdCobro();
		Long idOperacion = componentesCobroBatch.getIdOperacion();

		//
		// Elimino el cobro de la base, si existiera
		//
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			if (!Validaciones.isObjectNull(cobro)) {
				mensajeriaTransaccionDao.borrarMensajesPorIdOperacion(idOperacion);
				cobroDao.eliminar(idCobro);
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

		//
		// Seteo la caja
		//
		componentesCobroBatch.getOperacion().setIdCaja(parametroServicio.getValorTexto("idCaja.shiva"));

		//
		// Armado del cobro
		//
		ShvCobCobro nuevoCobro = new ShvCobCobro();
		nuevoCobro.setIdCobro(idCobro);
		nuevoCobro.setOperacion(componentesCobroBatch.getOperacion());
		nuevoCobro.setWorkflow(componentesCobroBatch.getWorkflow());
		nuevoCobro.setMonedaOperacion(componentesCobroBatch.getMonedaOperacion());

		return nuevoCobro;
	}

	/**
	 * 
	 * @param edCobro
	 * @return
	 */
	private List<ShvCobFactura> mapearDocumentosACobrar(ShvCobEdCobro edCobro) {

		List<ShvCobFactura> listaFacturas = new ArrayList<ShvCobFactura>();

		// Debitos
		for (ShvCobEdDebito debito : edCobro.getDebitos()) {

			ShvCobFactura factura = null;

			if (SistemaEnum.CALIPSO.equals(debito.getSistemaOrigen())) {
				factura = mapearDocumentoACobrarCalipso(debito);

			} else if (SistemaEnum.MIC.equals(debito.getSistemaOrigen())) {
				factura = mapearDocumentoACobrarMic(debito);

			} else {
				// throw new
				// NegocioExcepcion("Error al convertir cobro en edicion en componentes de cobro: Factura con sistema origen no soportado.");
			}

			listaFacturas.add(factura);
		}
		// Otros Debitos
		for (ShvCobEdOtrosDebito debito : edCobro.getOtrosDebitos()) {

			ShvCobFactura factura = null;
			factura = mapearDocumentoACobrarUsuario(debito);
			listaFacturas.add(factura);
		}

		return listaFacturas;
	}

	/**
	 * 
	 * @param debito
	 * @return
	 */
	private ShvCobFactura mapearDocumentoACobrarCalipso(ShvCobEdDebito debito) {

		ShvCobFacturaCalipso facturaCalipso = new ShvCobFacturaCalipso();
		
		// Mapeo de campos comunes
		facturaCalipso = (ShvCobFacturaCalipso) mapearDocumentosACobrarCamposComunes(debito, facturaCalipso);
		
		// Mapeo de campos particulares
		facturaCalipso.setRazonSocialClienteLegado(debito.getRazonSocialCliente());
		facturaCalipso.setTipoCliente(debito.getTipoCliente());
		facturaCalipso.setTipoCambio(debito.getTipoDeCambio());
		facturaCalipso.setCuit(debito.getCuit());
		facturaCalipso.setFechaVencimiento(debito.getFechaVencimiento());
		facturaCalipso.setMoneda(debito.getMoneda());
		facturaCalipso.setSaldoActual(debito.getSaldoPesificado());
		facturaCalipso.setImporteOriginal(debito.getImportePriVencPesificado());
		facturaCalipso.setFechaEmision(debito.getFechaEmision());
		facturaCalipso.setIdDocumentoCuentaCobranza(new Long(debito.getIdDocCuentaCobranza().intValue()));
		facturaCalipso.setGeneradoPorCobro(SiNoEnum.NO);
		facturaCalipso.setSistemaOrigen(SistemaEnum.CALIPSO);
		facturaCalipso.setSinDiferenciaCambio(debito.getCheckSinDiferenciaCambio());
		facturaCalipso.setSubtipo(debito.getSubtipoDakota());

		if (facturaCalipso.getMoneda().equals(facturaCalipso.getMonedaImporteCobrar())) {
			facturaCalipso.setImporteOriginal(debito.getImportePriVencMonedaOrigen());
			facturaCalipso.setSaldoActual(debito.getSaldoPriVencMonedaOrigen());
		} else {
			facturaCalipso.setImporteOriginal(debito.getImportePriVencPesificado());
			facturaCalipso.setSaldoActual(debito.getSaldoPesificado());

			facturaCalipso.setImporteOriginalMonedaOrigen(debito.getImportePriVencMonedaOrigen());
			facturaCalipso.setSaldoActualMonedaOrigen(debito.getSaldoPriVencMonedaOrigen());
		}
		
		return facturaCalipso;
	}

	/**
	 * 
	 * @param debito
	 * @return
	 */
	private ShvCobFactura mapearDocumentoACobrarMic(ShvCobEdDebito debito) {

		ShvCobFacturaMic facturaMic = new ShvCobFacturaMic();
		
		// Mapeo de campos comunes
		facturaMic = (ShvCobFacturaMic) mapearDocumentosACobrarCamposComunes(debito, facturaMic);

		// Mapeo de campos particulares
		facturaMic.setFechaEmision(debito.getFechaEmision());
		facturaMic.setMarketingCustomerGroup(debito.getMarketingCustomerGroup());
		facturaMic.setRazonSocialClienteLegado(debito.getRazonSocialCliente());
		facturaMic.setTipoCliente(debito.getTipoCliente());

		if (!Validaciones.isObjectNull(debito.getNumeroReferenciaMic())) {
			facturaMic.setIdReferenciaFactura(debito.getNumeroReferenciaMic().toString());
		} else {
			facturaMic.setIdReferenciaFactura(debito.getNumeroReferenciaDuc());
		}

		if (!Validaciones.isObjectNull(debito.getTipoDuc())) {
			facturaMic.setTipoDuc(debito.getTipoDuc().codigo());	
		} else {
			facturaMic.setTipoFactura(!Validaciones.isObjectNull(debito.getTipoFactura())?debito.getTipoFactura().codigo(): 0);
		}
		facturaMic.setCuit(debito.getCuit());
		facturaMic.setFechaVencimiento(debito.getFechaVencimiento());
		facturaMic.setImporteOriginal(debito.getImportePriVencMonedaOrigen());
		facturaMic.setSaldoActual(debito.getSaldoPriVencMonedaOrigen());
		facturaMic.setFechaPuestaCobro(debito.getFechaPuestaCobro());
		facturaMic.setFechaUltimoPagoParcial(debito.getFechaUltimoPagoParcial());
		facturaMic.setFechaEmision(debito.getFechaEmision());
		facturaMic.setFechaSegundoVencimiento(debito.getFechaVencimientoMora());
		facturaMic.setGeneradoPorCobro(SiNoEnum.NO);
		facturaMic.setSistemaOrigen(SistemaEnum.MIC);
		
		facturaMic.setCicloFacturacion(debito.getCicloFacturacion());
		
		facturaMic.setEstadoConceptoTerceros(debito.getEstadoConceptoTerceros());
		
		facturaMic.setCobrarSegundoVencimiento(debito.getCheckCobrarSegVencimiento());
		facturaMic.setDestransferirTerceros(debito.getCheckDestranferirTerceros());

		facturaMic.setImporteOriginal(debito.getImportePriVencMonedaOrigen());
		facturaMic.setSaldoActual(debito.getSaldoPriVencMonedaOrigen());
		
		facturaMic.setPosibilidadDestransferencia(debito.getPosibilidadDestransferencia());
		facturaMic.setImporteTercerosTransferidos(debito.getImporteTercerosTransferidos());
		facturaMic.setSaldoTerceroFinanciableNOTransferible(debito.getSaldoTerceroFinanciableNOTransferible());
		facturaMic.setSaldoTerceroFinanciableTransferible(debito.getSaldoTerceroFinanciableTransferible());
		facturaMic.setSaldoTerceroNOFinanciableTransferible(debito.getSaldoTerceroNOFinanciableTransferible());
		
		return facturaMic;
	}
	/**
	 * 
	 * @param debito
	 * @return
	 */
	private ShvCobFactura mapearDocumentoACobrarUsuario(ShvCobEdOtrosDebito debito) {

		ShvCobFacturaUsuario facturaUsuario = new ShvCobFacturaUsuario();
		
		// Mapeo de campos comunes
		
		facturaUsuario.setClaseComprobante(debito.getClaseComprobante());
		facturaUsuario.setSucursalComprobante(debito.getSucursalComprobante());
		facturaUsuario.setNumeroComprobante(debito.getNumeroComprobante());
		facturaUsuario.setIdClienteLegado(debito.getIdClienteLegado());
		facturaUsuario.setImporteCobrar(debito.getImporte());
		facturaUsuario.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		facturaUsuario.setMontoAcumuladoSimulacion(BigDecimal.ZERO);
		facturaUsuario.setSistemaOrigen(debito.getSistema());
		facturaUsuario.setTipoComprobante(tipoComprobanteDao.buscarComprobante(debito.getTipoComprobante()));
		facturaUsuario.setSociedad(debito.getSociedad());
		facturaUsuario.setTrasladarIntereses(SiNoEnum.NO);
		facturaUsuario.setIdDebitoOrigen(debito.getPk().getIdOtrosDebito());
		facturaUsuario.setIdCobro(debito.getPk().getCobro().getIdCobro());
		
		// Mapeo de campos particulares
		facturaUsuario.setFechaVencimiento(debito.getFechaVencimiento());
		facturaUsuario.setMoneda(debito.getMoneda());
		facturaUsuario.setMonedaImporteCobrar(debito.getMonedaImporteACobrar());
		facturaUsuario.setTipoCambio(debito.getTipoCambio());
		facturaUsuario.setSinDiferenciaCambio(debito.getCheckSinDiferenciaCambio());
		facturaUsuario.setIdAdjunto(debito.getIdAdjunto());
		facturaUsuario.setReferencia(valueOf(debito.getReferencia()));
		facturaUsuario.setImporteAplicadoAFechaEmision(debito.getImporteCobroarMonedaOrigen());
		
		return facturaUsuario;
	}
	/**
	 * 
	 * @param edCobro
	 * @return
	 */
	private List<ShvCobMedioPago> mapearMediosPagoAUtilizar(ShvCobEdCobro edCobro) throws NegocioExcepcion {

		List<ShvCobMedioPago> listaMediosDePago = new ArrayList<ShvCobMedioPago>();

		ShvCobMedioPago medioPago = null;

		//
		// Mapeo de medios de pago de sistemas (Shiva, MIC y Calipso)
		//
		for (ShvCobEdCredito credito : edCobro.getCreditos()) {

			String creditoIdTipoMedioPago = credito.getTipoMedioPago().getIdTipoMedioPago();

			if (TipoCreditoEnum.CHEQUE.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.CHEQUEDIF.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.EFECTIVO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.IMPUESTO_AL_SELLO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.RETENCION_SEGURIDAD_SOCIAL.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.RETENCION_GANANCIA.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.RETENCION_IVA.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.RETENCION_IIBB.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.RETENCION_IVA_RG3349.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.IMPUESTO_TASAS_MUNICIPALES.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.TRANSFERENCIA.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearValorShiva(credito);

			} else if (TipoCreditoEnum.NOTACREDITO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago) && SistemaEnum.CALIPSO.equals(credito.getSistemaOrigen())) {
				medioPago = mapearNotaCreditoCalipso(credito);

			} else if (TipoCreditoEnum.REMANENTE_ACTUALIZABLE.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)
					|| TipoCreditoEnum.REMANENTE_NO_ACTUALIZABLE.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearRemanente(credito);

			} else if (TipoCreditoEnum.NOTACREDITO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago) && SistemaEnum.MIC.equals(credito.getSistemaOrigen())) {
				medioPago = mapearNotaCreditoMIC(credito);

			} else if (TipoCreditoEnum.PAGOACUENTA.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearPagoACuenta(credito);
			}

			listaMediosDePago.add(medioPago);
		}

		//
		// Mapeo medios de pago de usuarios
		//
		for (ShvCobEdOtrosMedioPago credito : edCobro.getMediosPagos()) {

			String creditoIdTipoMedioPago = credito.getTipoMedioPago().getIdTipoMedioPago();

			credito.setMonedaImporteAUtilizar(edCobro.getMonedaOperacion());
			if (TipoCreditoEnum.DESISTIMIENTO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearDesistimiento(credito);

			} else if (TipoCreditoEnum.PLANPAGO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearPlanDePago(credito);
			} else if (TipoCreditoEnum.OTRAS_COMPENSACIONES.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearOtraCompensacion(credito);
//				((ShvCobMedioPagoCompensacionOtras) medioPago).setTipoCambio(edCobro.getTipoCambio());
			} else if (TipoCreditoEnum.IIBB.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearCompensacionIIBB(credito);
//				((ShvCobMedioPagoCompensacionIIBB) medioPago).setTipoCambio(edCobro.getTipoCambio());
				

			} else if (TipoCreditoEnum.CESION_CREDITOS.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearCompensacionCesionCreditos(credito);
//				((ShvCobMedioPagoCompensacionCesionCredito) medioPago).setTipoCambio(edCobro.getTipoCambio());

			} else if (TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearCompensacionProveedores(credito);
//				((ShvCobMedioPagoCompensacionProveedor) medioPago).setTipoCambio(edCobro.getTipoCambio());
			
			} else if (TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearCompensacionLiquidoProducto(credito);
//				((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).setTipoCambio(edCobro.getTipoCambio());

			} else if (TipoCreditoEnum.INTERCOMPANY.getIdTipoMedioPago().equals(creditoIdTipoMedioPago)) {
				medioPago = mapearCompensacionIntercompany(credito);
//				((ShvCobMedioPagoCompensacionIntercompany) medioPago).setTipoCambio(edCobro.getTipoCambio());
			}
			
			listaMediosDePago.add(medioPago);
		}

		return listaMediosDePago;
	}
	/**
	 * 
	 * @param debito
	 * @param factura
	 * @return
	 */
	private ShvCobFactura mapearDocumentosACobrarCamposComunes(ShvCobEdDebito debito, ShvCobFactura factura) {

		factura.setTipoComprobante(tipoComprobanteDao.buscarComprobante(debito.getTipoComprobante()));
		factura.setClaseComprobante(debito.getClaseComprobante());
		factura.setSucursalComprobante(debito.getSucursalComprobante());
		factura.setNumeroComprobante(debito.getNumeroComprobante());
		factura.setIdClienteLegado(debito.getIdClienteLegado());
		factura.setImporteCobrar(debito.getImporteACobrar());
		factura.setAcuerdoTrasladoCargo(!Validaciones.isNullEmptyOrDash(debito.getAcuerdoFacturacion())?debito.getAcuerdoFacturacion():null);
		factura.setEstadoAcuerdoTrasladoCargo(Validaciones.isObjectNull(debito
				.getEstadoAcuerdoFacturacion()) ? null : EstadoAcuerdoFacturacionEnum.getEnumByName(debito.getEstadoAcuerdoFacturacion()));
		factura.setIdClienteLegadoAcuerdoTrasladoCargo(debito.getIdClienteLegado());
		factura.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		factura.setMontoAcumuladoSimulacion(BigDecimal.ZERO);

		factura.setTrasladarIntereses(SiNoEnum.NO);

		factura.setMigradoDeimos(debito.getMarcaMigradoDeimos());

		factura.setIdDebitoOrigen(debito.getPk().getIdDebito());
		factura.setIdCobro(debito.getPk().getCobro().getIdCobro());
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		factura.setMonedaImporteCobrar(debito.getMonedaImporteACobrar());
        factura.setSociedad(debito.getSociedad());
		return factura;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearRemanente(ShvCobEdCredito credito) throws NegocioExcepcion {

		ShvCobMedioPagoRemanente medioPago = new ShvCobMedioPagoRemanente();
		medioPago.setIdClienteLegado(credito.getIdClienteLegado());
		medioPago.setTipoRemanente(credito.getCodigoTipoRemamente());

		StringBuffer cuenta = new StringBuffer();
		cuenta.append(Utilidad.rellenarCerosIzquierda(credito.getIdClienteLegado().toString(), 10));
		cuenta.append(Utilidad.rellenarCerosIzquierda(credito.getCuenta().toString(), 3));
		medioPago.setCuentaRemanente(new Long(cuenta.toString()));

		medioPago.setMoneda(credito.getMoneda());
		medioPago.setFechaAlta(credito.getFechaAlta());

		medioPago.setMigradoDeimos(credito.getMarcaMigradoDeimos());

		if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos())) {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.PENDIENTE);
		} else {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
		}

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.MIC);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdCredito());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());

		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());
		
		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearNotaCreditoCalipso(ShvCobEdCredito credito) throws NegocioExcepcion {

		ShvCobMedioPagoNotaCreditoCalipso medioPago = new ShvCobMedioPagoNotaCreditoCalipso();

		medioPago.setIdClienteLegado(credito.getIdClienteLegado());

		medioPago.setTipoComprobante(credito.getTipoComprobante());
		medioPago.setClaseComprobante(credito.getClaseComprobante());
		medioPago.setSucursalComprobante(credito.getSucursalComprobante());
		medioPago.setNroComprobante(credito.getNumeroComprobante());

		medioPago.setFechaEmision(credito.getFechaEmision());
		medioPago.setGeneradoPorCobro(SiNoEnum.NO);
		medioPago.setIdDocumentoCuentaCobranza(credito.getIdDocumentoCuentaCobranza());
		medioPago.setRazonSocialClienteLegado(credito.getRazonSocialCliente());

		medioPago.setImporteOriginalMonedaOrigen(credito.getImporteMonedaOrigen());
		medioPago.setSaldoActualMonedaOrigen(credito.getSaldoMonedaOrigen());
		medioPago.setTipoCambio(credito.getTipoDeCambio());
		medioPago.setTipoCliente(credito.getTipoCliente());
		medioPago.setFechaVencimiento(credito.getFechaVencimiento());
		medioPago.setMoneda(credito.getMoneda());
		medioPago.setSubtipo(credito.getSubtipo());

		medioPago.setMigradoDeimos(credito.getMarcaMigradoDeimos());

		if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos())) {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.PENDIENTE);
		} else {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
		}

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.CALIPSO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdCredito());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearNotaCreditoMIC(ShvCobEdCredito credito) throws NegocioExcepcion {

		ShvCobMedioPagoNotaCreditoMic medioPago = new ShvCobMedioPagoNotaCreditoMic();

		medioPago.setIdClienteLegado(credito.getIdClienteLegado());

		medioPago.setTipoComprobante(credito.getTipoComprobante());
		medioPago.setClaseComprobante(credito.getClaseComprobante());
		medioPago.setSucursalComprobante(credito.getSucursalComprobante());
		medioPago.setNroComprobante(credito.getNumeroComprobante());

		medioPago.setNumeroNotaCredito(credito.getNroReferenciaMic().toString());
		medioPago.setRazonSocialClienteLegado(credito.getRazonSocialCliente());
		medioPago.setTipoCliente(credito.getTipoCliente());
		medioPago.setCuit(credito.getCuit());
		medioPago.setCicloFacturacion(credito.getCicloFacturacion() != null ? new Long(credito.getCicloFacturacion().toString()) : null);
		medioPago.setMarketingCustomerGroup(credito.getMarketingCustomerGroup());
		medioPago.setFechaVencimiento(credito.getFechaVencimiento());
		medioPago.setFechaEmision(credito.getFechaEmision());
		medioPago.setMoneda(credito.getMoneda());

		medioPago.setMigradoDeimos(credito.getMarcaMigradoDeimos());

		if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos())) {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.PENDIENTE);
		} else {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
		}

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.MIC);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdCredito());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearPagoACuenta(ShvCobEdCredito credito) throws NegocioExcepcion {

		ShvCobMedioPagoCTA medioPago = new ShvCobMedioPagoCTA();
		medioPago.setFechaEmision(credito.getFechaEmision());
		medioPago.setIdClienteLegado(credito.getIdClienteLegado());

		medioPago.setTipoComprobante(credito.getTipoComprobante());
		medioPago.setClaseComprobante(credito.getClaseComprobante());
		medioPago.setSucursalComprobante(credito.getSucursalComprobante());
		medioPago.setNroComprobante(credito.getNumeroComprobante());

		medioPago.setGeneradoPorCobro(SiNoEnum.NO);
		medioPago.setIdDocumentoCuentaCobranza(credito.getIdDocumentoCuentaCobranza());
		medioPago.setMoneda(credito.getMoneda());

		medioPago.setMigradoDeimos(credito.getMarcaMigradoDeimos());

		if (SiNoEnum.SI.equals(medioPago.getMigradoDeimos())) {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.PENDIENTE);
		} else {
			medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);
		}

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.CALIPSO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdCredito());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());
		
		medioPago.setTipoCambio(credito.getTipoDeCambio());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearOtraCompensacion(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionOtras medioPago = new ShvCobMedioPagoCompensacionOtras();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearCompensacionIIBB(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionIIBB medioPago = new ShvCobMedioPagoCompensacionIIBB();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);
		medioPago.setProvincia(credito.getProvincia());


		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());

		return medioPago;
	}	
	
	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearCompensacionCesionCreditos(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionCesionCredito medioPago = new ShvCobMedioPagoCompensacionCesionCredito();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());

		return medioPago;
	}
	
	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearCompensacionProveedores(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionProveedor medioPago = new ShvCobMedioPagoCompensacionProveedor();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);
		
		// Mapeo el documento CAP
		medioPago.setDocumentoCap(mapearDocumentoCap(credito));
		
		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa() != null ? credito.getNroActa() : "Pendiente Generar Sap");
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());

		return medioPago;
	}	
	
	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearCompensacionIntercompany(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionIntercompany medioPago = new ShvCobMedioPagoCompensacionIntercompany();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearCompensacionLiquidoProducto(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoCompensacionLiquidoProducto medioPago = new ShvCobMedioPagoCompensacionLiquidoProducto();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		// Mapeo el documento CAP
		medioPago.setDocumentoCap(mapearDocumentoCap(credito));

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroCompensacion(credito.getNroActa() != null ? credito.getNroActa() : "Pendiente Generar Sap");
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		medioPago.setSubTipo(credito.getSubTipo());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearDesistimiento(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoDesistimiento medioPago = new ShvCobMedioPagoDesistimiento();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setNumeroActa(credito.getNroActa());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearPlanDePago(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {

		ShvCobMedioPagoPlanDePago medioPago = new ShvCobMedioPagoPlanDePago();

		Set<ShvCobMedioPagoCliente> listaClientesMedioPago = new HashSet<ShvCobMedioPagoCliente>();  
		for (ShvCobEdMedioPagoCliente edMedioPagoCliente : credito.getListaMedioPagoCliente()) {

			ShvCobMedioPagoCliente medioPagoCliente = new ShvCobMedioPagoCliente();
			medioPagoCliente.setIdClienteLegado(edMedioPagoCliente.getIdClienteLegado());
			medioPagoCliente.setMedioPagoUsuario(medioPago);

			listaClientesMedioPago.add(medioPagoCliente);
		}
		medioPago.setListaMedioPagoClientes(listaClientesMedioPago);

		medioPago.setFecha(credito.getFecha());
		medioPago.setMoneda(credito.getMonedaImporteAUtilizar());

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporte());
		medioPago.setSistemaOrigen(SistemaEnum.USUARIO);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdMedioPago());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param credito
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearValorShiva(ShvCobEdCredito credito) throws NegocioExcepcion {

		ShvCobMedioPagoShiva medioPago = new ShvCobMedioPagoShiva();

		medioPago.setIdValor(credito.getIdValor());
		medioPago.setIdClienteLegado(credito.getIdClienteLegado());
		medioPago.setMoneda(MonedaEnum.PES);

		medioPago.setFechaValor(credito.getFechaValor());
		medioPago.setReferencia(credito.getReferenciaValor());
		medioPago.setIdTipoValor(new Long(credito.getTipoCredito().getIdTipoValor()));
		medioPago.setIdTipoRetencionImpuesto(Validaciones.isObjectNull(credito.getTipoRetencion()) ? null : new Long(credito.getTipoRetencion().getId()));

		medioPago.setMigradoDeimos(SiNoEnum.NO);
		medioPago.setEstadoDeimos(EstadoFacturaMedioPagoEnum.NO_APLICA);

		medioPago.setTipoMedioPago(credito.getTipoMedioPago());

		medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
		medioPago.setImporte(credito.getImporteAUtilizar());
		medioPago.setSistemaOrigen(SistemaEnum.SHIVA);

		medioPago.setIdCreditoOrigen(credito.getPk().getIdCredito());
		medioPago.setIdCobro(credito.getPk().getCobro().getIdCobro());
		
		//Se agrega  Moneda del importe- MONEDA OPERACION.
		medioPago.setMonedaImporte(credito.getMonedaImporteAUtilizar());

		return medioPago;
	}

	/**
	 * 
	 * @param tratamientoDiferencia
	 * @return
	 */
	private ShvCobMedioPago mapearDebitoProximaFactura(
			ShvCobEdTratamientoDiferencia tratamientoDiferencia, ShvCobFactura ultimaFactura, Long idOperacion) throws NegocioExcepcion {

		ShvCobMedioPagoDebitoProximaFactura medioPago = null;

		if (!Validaciones.isObjectNull(tratamientoDiferencia)
				&& TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {

			medioPago = new ShvCobMedioPagoDebitoProximaFactura();

			// Uso el tipo de medio de pago de proxima factura de MIC, solo a
			// efectos de poder tener datos de
			// ordenamiento, aplicacion sobre intereses, etc. Asumo que para
			// Calipso siempre es igual esta información
			medioPago.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC));

			medioPago.setFecha(Utilidad.obtenerFechaActual());

			//medioPago.setMoneda(MonedaEnum.PES);
			medioPago.setImporte(tratamientoDiferencia.getImporte());
			
			//Moneda operacion
			medioPago.setMonedaImporte(ultimaFactura.getMonedaImporteCobrar());
			medioPago.setMoneda(tratamientoDiferencia.getMoneda());
		}

		return medioPago;
	}
	
	/**
	 * 
	 * @param tratamientoDiferencia
	 * @param ultimaFactura
	 * @param idOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvCobMedioPago mapearSaldoACobrar(
			ShvCobEdTratamientoDiferencia tratamientoDiferencia, ShvCobFactura ultimaFactura, Long idOperacion) throws NegocioExcepcion {

		ShvCobMedioPagoSaldoACobrar medioPago = null;

		if (!Validaciones.isObjectNull(tratamientoDiferencia)
				&& TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {

			medioPago = new ShvCobMedioPagoSaldoACobrar();

			medioPago.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.SALDO_A_COBRAR));
			medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
			medioPago.setFecha(Utilidad.obtenerFechaActual());
			medioPago.setImporte(tratamientoDiferencia.getImporte());
			medioPago.setMonedaImporte(ultimaFactura.getMonedaImporteCobrar());
			medioPago.setMoneda(tratamientoDiferencia.getMoneda());
		}

		return medioPago;
	}	

	/**
	 * 
	 * @param edCobro
	 * @return
	 */
	private ShvCobTratamientoDiferencia mapearTratamientoDiferencia(ShvCobEdCobro edCobro) throws NegocioExcepcion {

		ShvCobEdTratamientoDiferencia edTratamientoDiferencia = edCobro.getTratamientoDiferencia();

		ShvCobTratamientoDiferencia tratamientoDiferencia = null;

		if (!Validaciones.isObjectNull(edTratamientoDiferencia)
				&& (TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia()) 
						|| TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())
						|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia()))) {

			tratamientoDiferencia = new ShvCobTratamientoDiferencia();

			// Seteo el tipo de medio de pago
			tratamientoDiferencia.setTipoMedioPago(edTratamientoDiferencia.getTipoMedioPago());

			if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())) {

				if (SistemaEnum.MIC.equals(edTratamientoDiferencia.getSistemaDestino())) {
					tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC);

					// Seteo el tipo de medio de pago para reintegro credito
					// proxima factura MIC
					tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.CREDITO_PROXIMA_FACTURA_MIC));
				} else {
					tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP);

					// Seteo el tipo de medio de pago para reintegro credito
					// proxima factura Calipso
					tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.CREDITO_PROXIMA_FACTURA_CALIPSO));
				}
				tratamientoDiferencia.setSistemaOrigen(edTratamientoDiferencia.getSistemaDestino());

			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())) {
				if (SistemaEnum.NEGOCIO_NET.equals(edTratamientoDiferencia.getSistemaDestino())) {
					tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET);

					// Seteo el tipo de medio de pago para reintegro credito
					// proxima factura MIC
					tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_NEGOCIO_NET));
				} else if (SistemaEnum.SAP.equals(edTratamientoDiferencia.getSistemaDestino())) {
					tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP);

					// Seteo el tipo de medio de pago para reintegro credito
					// proxima factura Calipso
					tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_SAP));
				} else if (SistemaEnum.CABLEVISION.equals(edTratamientoDiferencia.getSistemaDestino())) {
						tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION);

						// Seteo el tipo de medio de pago para reintegro credito
						// proxima factura Calipso
						tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_OPEN));
				} else if (SistemaEnum.FIBERTEL.equals(edTratamientoDiferencia.getSistemaDestino())) {
						tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL);

						// Seteo el tipo de medio de pago para reintegro credito
						// proxima factura Calipso
						tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_SAP));
				} else {
						tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL);

						// Seteo el tipo de medio de pago para reintegro credito
						// proxima factura Calipso
						tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL_NEXTEL_NEXUS));
					}
				tratamientoDiferencia.setSistemaOrigen(edTratamientoDiferencia.getSistemaDestino());
				tratamientoDiferencia.setReferencia(!Validaciones.isNullOrEmpty(edTratamientoDiferencia.getReferencia()) ? edTratamientoDiferencia.getReferencia() : null);
			} else {
				tratamientoDiferencia.setTipoTratamientoDiferencia(edTratamientoDiferencia.getTipoTratamientoDiferencia());
			}

			if (!Validaciones.isObjectNull(edTratamientoDiferencia.getAcuerdoFacturacion())) {
				tratamientoDiferencia.setAcuerdoTrasladoCargo(edTratamientoDiferencia.getAcuerdoFacturacion().toString());
				tratamientoDiferencia.setEstadoAcuerdoTrasladoCargo(edTratamientoDiferencia.getEstadoAcuerdoFacturacion());
				tratamientoDiferencia.setIdClienteLegadoAcuerdoTrasladoCargo(edTratamientoDiferencia.getIdClienteAcuerdoFacturacion());
			}
			tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
			tratamientoDiferencia.setNumeroTramiteReintegro(edTratamientoDiferencia.getNumeroTramiteReintegro());
			tratamientoDiferencia.setFechaTramiteReintegro(edTratamientoDiferencia.getFechaAltaTramiteReintegro());
			tratamientoDiferencia.setImporte(edTratamientoDiferencia.getImporte());

			// Este valor se convierte en una "T" cuando se invoca al servicio
			// de generacion de cargos
			tratamientoDiferencia.setQueHacerConIntereses(TratamientoInteresesEnum.TM);
			tratamientoDiferencia.setTrasladarIntereses(SiNoEnum.NO);
			tratamientoDiferencia.setFechaValor(new Date());

			// Seteo datos por defecto para poder grabar el tratamiento sin
			// problemas.
			tratamientoDiferencia.setCalcularFechaHasta(SiNoEnum.SI);
			tratamientoDiferencia.setMoneda(edTratamientoDiferencia.getMoneda());
		}

		return tratamientoDiferencia;
	}
	
	/**
	 * 
	 * @param listaEdicionDocumentosCap
	 * @return
	 */
	private ShvCobMedioPagoDocumentoCap mapearDocumentoCap(ShvCobEdOtrosMedioPago credito) throws NegocioExcepcion {
		
		// Mapeo los datos principales del documento CAP
		ShvCobMedioPagoDocumentoCap documentoCap = new ShvCobMedioPagoDocumentoCap();
		documentoCap.setTipoDocumento(TipoDocumentoCapEnum.K$);
		documentoCap.setMoneda(credito.getPk().getCobro().getMonedaOperacion());
		documentoCap.setIdCobro(credito.getPk().getCobro().getIdCobro());
		documentoCap.setImporte(credito.getImporte());
		documentoCap.setFechaValorCompensacion(credito.getFecha());
		
		// Si hay diferencia de cambio y es 'Saldo a Pagar', la debo tener en cuenta
		
		ShvCobEdTratamientoDiferencia edTratamientoDiferencia = credito.getPk().getCobro().getTratamientoDiferencia();
		
		if (!Validaciones.isObjectNull(edTratamientoDiferencia)) {
			if (TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(edTratamientoDiferencia.getTipoTratamientoDiferencia())) {
				documentoCap.setImporte(credito.getImporte().subtract(edTratamientoDiferencia.getImporte()));
			}
		}
		
		// Mapeo los datos del detalle, es decir, los renglones que van a conformmar el documento CAP
		Set<ShvCobMedioPagoDocumentoCapDetalle> listaDocumentosCapDetalle = new HashSet<ShvCobMedioPagoDocumentoCapDetalle>();
		
		for (ShvCobEdDocumentoCap edDocumentoCap : credito.getDocumentosCap()) {
			
			if (!TipoRenglonSapEnum.AGRUPADOR.equals(edDocumentoCap.getTipoRenglon())) {
				
				// Del primer documento Cap detalle tomo idProveedor y idSociedad
				if (Validaciones.isObjectNull(documentoCap.getIdProveedor())) {
					documentoCap.setIdProveedor(edDocumentoCap.getIdProveedor());
//					documentoCap.setIdSociedad(edDocumentoCap.getIdSociedad());
				}
				
				ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalle = new ShvCobMedioPagoDocumentoCapDetalle();
				documentoCapDetalle.setDocumentoCap(documentoCap);
				documentoCapDetalle.setIdClienteLegado(edDocumentoCap.getIdClienteLegado());
				documentoCapDetalle.setSistemaOrigen(edDocumentoCap.getSistemaOrigen());
				documentoCapDetalle.setIdSociedad(edDocumentoCap.getIdSociedad());
				documentoCapDetalle.setIdProveedor(edDocumentoCap.getIdProveedor());
				documentoCapDetalle.setAsignacion(edDocumentoCap.getAsignacion());
				documentoCapDetalle.setEjercicioFiscalDocSAP(edDocumentoCap.getEjercicioFiscalDocSAP());
				documentoCapDetalle.setNumeroDocSAP(edDocumentoCap.getNumeroDocSAP());
				documentoCapDetalle.setPosicionDocSAP(edDocumentoCap.getPosicionDocSAP());
				documentoCapDetalle.setFechaContableDocSAP(edDocumentoCap.getFechaContableDocSAP());
				documentoCapDetalle.setFechaEmision(edDocumentoCap.getFechaDocProveedor());
				documentoCapDetalle.setFechaRegistroSAP(edDocumentoCap.getFechaRegistroSAP());
				documentoCapDetalle.setMonedaDocProveedor(edDocumentoCap.getMonedaDocProveedor());
				documentoCapDetalle.setMonedaLocal(edDocumentoCap.getMonedaLocal());
				documentoCapDetalle.setTipoCambioRegistradoSAP(edDocumentoCap.getTipoCambioRegistradoSAP());
				documentoCapDetalle.setNumeroLegalDocProveedor(edDocumentoCap.getNumeroLegalDocProveedor());
				documentoCapDetalle.setTipoDocumento(edDocumentoCap.getClaseDocSAP());
				documentoCapDetalle.setMesFiscalDocSap(edDocumentoCap.getMesFiscalDocSap());
				documentoCapDetalle.setClaveContabilizacionSAP(edDocumentoCap.getClaveContabilizacionSAP());
				documentoCapDetalle.setIndicador(edDocumentoCap.getIndicador());
				documentoCapDetalle.setDivision(edDocumentoCap.getDivision());
				documentoCapDetalle.setImporteRenglonPesosAFechaEmision(edDocumentoCap.getImporteDoc());
				documentoCapDetalle.setImporteRenglonMonedaOrigenAFechaEmision(edDocumentoCap.getImporteDocMonedaDoc());
				documentoCapDetalle.setTextoPosicion(edDocumentoCap.getTextoPosicion());
				documentoCapDetalle.setFechaBase(edDocumentoCap.getFechaBase());
				documentoCapDetalle.setCondPago(edDocumentoCap.getCondPago());
				documentoCapDetalle.setViaPago(edDocumentoCap.getViaPago());
				documentoCapDetalle.setBloqueoPago(edDocumentoCap.getBloqueoPago());
				documentoCapDetalle.setDescripcionBloqueo(edDocumentoCap.getDescripcionBloqueo());
				documentoCapDetalle.setNumeroDocSAPVinculado(edDocumentoCap.getNumeroDocSAPVinculado());
				documentoCapDetalle.setEjercicioFiscalDocSAPVinculado(edDocumentoCap.getEjercicioFiscalDocSAPVinculado());
				documentoCapDetalle.setPosicionDocSAPVinculado(edDocumentoCap.getPosicionDocSAPVinculado());
				documentoCapDetalle.setClaveRef(edDocumentoCap.getClaveRef());
				documentoCapDetalle.setClaveRef2(edDocumentoCap.getClaveRef2());
				documentoCapDetalle.setFechaTipoCambio(edDocumentoCap.getFechaTipoCambio());
				documentoCapDetalle.setEstadoDocSAP(edDocumentoCap.getEstadoDocSAP());
				documentoCapDetalle.setImporteRenglonPesosAFechaShiva(edDocumentoCap.getImpDocPES());
				documentoCapDetalle.setImporteRenglonMonedaDolarAFechaEmision(edDocumentoCap.getImporteRenglonMonedaDolarAFechaEmision());
				documentoCapDetalle.setImporteRenglonMonedaDolarAFechaShiva(edDocumentoCap.getImpDocUSD());
				documentoCapDetalle.setImporteTotalDocumentoMonedaOrigenAFechaEmision(edDocumentoCap.getImpTotalDocMonedaDoc());
				documentoCapDetalle.setImporteTotalDocumentoPesosAFechaEmision(edDocumentoCap.getImpTotalDocMonedaLocal());
				documentoCapDetalle.setImporteTotalDocumentoPesosAFechaShiva(edDocumentoCap.getImpTotalDocPES());
				documentoCapDetalle.setImporteTotalDocumentoMonedaDolarAFechaShiva(edDocumentoCap.getImpTotalDocUSD());
				documentoCapDetalle.setTipoDeCambioAFechaTipoCambio(edDocumentoCap.getTipoDeCambioAFechaTipoCambio());
				documentoCapDetalle.setCheckSinDiferenciaCambio(edDocumentoCap.getCheckSinDiferenciaCambio());
				
				documentoCapDetalle.setImporteTotalMonedaOrigenDocumentosBloqueoPesos(edDocumentoCap.getImporteTotalMonedaOrigenDocumentosBloqueoPesos());
				documentoCapDetalle.setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(edDocumentoCap.getSaldoPesificadoEmisionDocumentosBloqueoNoPesos());
				documentoCapDetalle.setSaldoPesificadoCobroDocumentosBloqueoNoPesos(edDocumentoCap.getSaldoPesificadoCobroDocumentosBloqueoNoPesos());
				
				
				listaDocumentosCapDetalle.add(documentoCapDetalle);
				
			}
		}	
		
		documentoCap.setDetalle(listaDocumentosCapDetalle);
		
		// Ahora recorro los documentos de manera ordenada para poder setear los importes de aplicacion
		
		List<ShvCobMedioPagoDocumentoCapDetalle> listaOrdenadaDocumentosCapDetalle = new ArrayList<ShvCobMedioPagoDocumentoCapDetalle>();
		listaOrdenadaDocumentosCapDetalle.addAll(documentoCap.getDetalle());
		
		Collections.sort(listaOrdenadaDocumentosCapDetalle,
				new ComparatorOrdenAplicacionImporteCompensacionDocumentoCapDetalle());
		
		BigDecimal importeCompensacionPendienteAplicar = documentoCap.getImporte().multiply(new BigDecimal(-1));
		
		for (ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalle : listaOrdenadaDocumentosCapDetalle) {
			
			// Calculo el importe aplicado en pesos
			BigDecimal importeRenglonPesosEvaluandoCheckSinDiferenciaCambio = documentoCapDetalle.getImporteRenglonPesosEvaluandoCheckSinDiferenciaCambio();

			if (importeCompensacionPendienteAplicar.compareTo(importeRenglonPesosEvaluandoCheckSinDiferenciaCambio) <= 0 
					&& BigDecimal.ZERO.compareTo(importeCompensacionPendienteAplicar) != 0) {

				documentoCapDetalle.setImporteAplicadoMonedaPesos(importeRenglonPesosEvaluandoCheckSinDiferenciaCambio);
				importeCompensacionPendienteAplicar = importeCompensacionPendienteAplicar.subtract(importeRenglonPesosEvaluandoCheckSinDiferenciaCambio);

			} else {

				if (BigDecimal.ZERO.compareTo(importeCompensacionPendienteAplicar) == 0) {
					documentoCapDetalle.setImporteAplicadoMonedaPesos(BigDecimal.ZERO);
				} else {
					documentoCapDetalle.setImporteAplicadoMonedaPesos(importeCompensacionPendienteAplicar);
					importeCompensacionPendienteAplicar = BigDecimal.ZERO;
				}
			}
			
			// Seteo el importe aplicado en dolares
			BigDecimal proporcionalMontoEnPesos = documentoCapDetalle.getImporteAplicadoMonedaPesos().
																					multiply(new BigDecimal(100)).
																						divide(importeRenglonPesosEvaluandoCheckSinDiferenciaCambio, 2, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal importeAplicadoMonedaDolar = documentoCapDetalle.getImporteRenglonMonedaDolarAFechaEmision().
																					multiply(proporcionalMontoEnPesos).
																						divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			
			documentoCapDetalle.setImporteAplicadoMonedaDolar(importeAplicadoMonedaDolar);
		}
		
		return documentoCap;
	}
	

	/**
	 * Calcula el cargo origen de acuerdo a los medios de pago residentes
	 * 
	 * @param
	 * @param medioPago, el medio de pago sobre el cuál está aplicando el tratamientode la diferencia
	 * @param idOperacion
	 * @return
	 */
	@Override
	public Modelo calcularOrigenCargo(ShvCobTratamientoDiferencia tratamientoDiferencia, ShvCobMedioPago medioPago, Long idOperacion) {

		OrigenCargoEnum origenCargo = null;
		String leyendaFacturaCargo = null;
		String leyendaFacturaInteres = null;
		
		String idOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);

		if (TipoMedioPagoEnum.NOTA_CREDITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_NC;

		} else if (TipoMedioPagoEnum.REMANENTEACTUALIZADO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.REMANOACTUALIZABLE.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {

			origenCargo = OrigenCargoEnum.TRASLADO_REMANENTE;

		} else if (TipoMedioPagoEnum.PAGO_A_CUENTA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_SALDO_PAGO_CUENTA;

		} else if (TipoMedioPagoEnum.CHEQUEDIFERIDO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())
				|| TipoMedioPagoEnum.CHEQUEPROPIO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_SALDO_CHEQUE;

		} else if (TipoMedioPagoEnum.BOLETADEDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_SALDO_DEPOSITO;

		} else if (TipoMedioPagoEnum.TRANSFBANCARIA.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_SALDO_TRANSFERENCIA;

		} else if (TipoMedioPagoEnum.INTERDEPOSITO.getIdTipoMedioPago().equals(medioPago.getTipoMedioPago().getIdTipoMedioPago())) {
			origenCargo = OrigenCargoEnum.TRASLADO_SALDO_INTERDEPOSITO;
		}

		leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.cargo."+ origenCargo.name());
		leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.intereses."+ origenCargo.name());

		leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo,medioPago.getReferencia(), idOperacionFormateado);
		leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, medioPago.getReferencia(), idOperacionFormateado);

		tratamientoDiferencia.setOrigenCargo(origenCargo);
		tratamientoDiferencia.setLeyendaFacturaCargo(leyendaFacturaCargo);
		tratamientoDiferencia.setLeyendaFacturaInteres(leyendaFacturaInteres);

		return tratamientoDiferencia;
	}
	
	/**
	 * 
	 * @param documentoCalipso
	 */
	public void limpiarCamposCopiaDocumentoAplicacionPorDifCambio(ShvCobFacturaCalipso documentoCalipso) {
		
		documentoCalipso.setCobradorInteresesBonificados(null);
		documentoCalipso.setCobradorInteresesGenerados(null);
		documentoCalipso.setCobradorInteresesTrasladados(null);
		
		documentoCalipso.setImporteBonificacionIntereses(null);
		documentoCalipso.setPorcentajeBonifIntereses(null);
	}
	
	public void agregarTipoCambioFactura(ShvCobFactura shvCobFactura, ShvCobMedioPago shvCobMedioPago) {
		if (shvCobFactura instanceof ShvCobFacturaCalipso) {
			if (shvCobMedioPago instanceof ShvCobMedioPagoCompensacionOtras) {
				ShvCobMedioPagoCompensacionOtras shvCobMedioPagoCompensacion = (ShvCobMedioPagoCompensacionOtras) shvCobMedioPago;
				if (
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacion.getMoneda()) ||
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacion.getMonedaImporte())
				) {
					shvCobMedioPagoCompensacion.setTipoCambio(((ShvCobFacturaCalipso)shvCobFactura).getTipoCambio());
				}
			} else if (shvCobMedioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
				ShvCobMedioPagoCompensacionIntercompany shvCobMedioPagoCompensacionIntercompany = (ShvCobMedioPagoCompensacionIntercompany) shvCobMedioPago;
				if (
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacionIntercompany.getMoneda()) ||
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacionIntercompany.getMonedaImporte())
				) {
					shvCobMedioPagoCompensacionIntercompany.setTipoCambio(((ShvCobFacturaCalipso)shvCobFactura).getTipoCambio());
				}
			} else if (shvCobMedioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
				ShvCobMedioPagoCompensacionLiquidoProducto shvCobMedioPagoCompensacionLiquidoProducto = (ShvCobMedioPagoCompensacionLiquidoProducto) shvCobMedioPago;
				if (
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacionLiquidoProducto.getMoneda()) ||
					MonedaEnum.DOL.equals(shvCobMedioPagoCompensacionLiquidoProducto.getMonedaImporte())
				) {
					shvCobMedioPagoCompensacionLiquidoProducto.setTipoCambio(((ShvCobFacturaCalipso)shvCobFactura).getTipoCambio());
				}
			} else if (shvCobMedioPago instanceof ShvCobMedioPagoPlanDePago) {
				ShvCobMedioPagoPlanDePago shvCobMedioPagoPlanDePago = (ShvCobMedioPagoPlanDePago) shvCobMedioPago;
				if (
					MonedaEnum.DOL.equals(shvCobMedioPagoPlanDePago.getMoneda()) ||
					MonedaEnum.DOL.equals(shvCobMedioPagoPlanDePago.getMonedaImporte())
				) {
					shvCobMedioPagoPlanDePago.setTipoCambio(((ShvCobFacturaCalipso)shvCobFactura).getTipoCambio());
				}
			} else if (shvCobMedioPago instanceof ShvCobMedioPagoDesistimiento) {
				ShvCobMedioPagoDesistimiento shvCobMedioPagoDesistimiento = (ShvCobMedioPagoDesistimiento) shvCobMedioPago;
				if (
					MonedaEnum.DOL.equals(shvCobMedioPagoDesistimiento.getMoneda()) ||
					MonedaEnum.DOL.equals(shvCobMedioPagoDesistimiento.getMonedaImporte())
				) {
					shvCobMedioPagoDesistimiento.setTipoCambio(((ShvCobFacturaCalipso)shvCobFactura).getTipoCambio());
				}
			}
		}
		return;
	}
	
	/**
	 * @throws SimulacionCobroExcepcion 
	 * 
	 */
	@Override												
	public void validarSaldoYDisponibilidad(Long idValor, BigDecimal importeAApropiar) throws SimulacionCobroExcepcion, NegocioExcepcion{
	
		BigDecimal saldoDisponibleActual = BigDecimal.ZERO;
		
		saldoDisponibleActual = saldoDisponibleActualSimulacion(idValor);
		
		if (!tieneSaldoSuficiente(importeAApropiar, saldoDisponibleActual)) {
			String rutaError = "cobros.error.simulacion.apropiacion.validacion.medioPagoSinSaldoSuficiente";
			String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(rutaError), 
					Utilidad.formatCurrency(saldoDisponibleActual, 2));
			
			throw new SimulacionCobroExcepcion("", null, mensajeError); 
		}
	}

	private BigDecimal saldoDisponibleActualSimulacion(Long idValor) throws NegocioExcepcion{
		
		try {
				
			BigDecimal importeValoresPendientesDeAplicar = cobroDao.importeDeAplicacionesPendientesYCobrosEnProcesos(idValor);
			BigDecimal saldoDisponible = valorServicio.buscarValorSimplificado(idValor).getSaldoDisponible();
			
			BigDecimal saldoDisponibleActual= saldoDisponible.subtract(importeValoresPendientesDeAplicar);
			
			return saldoDisponibleActual;
			
			} catch (PersistenciaExcepcion e) {
				new NegocioExcepcion(e);
				
				return null;
			}
			
	}
	
//	@Override
//	public void validarSaldoYDisponibilidad(ShvValValorSimplificado valorSimplificado, ShvCobMedioPagoSinOperacion medioPago, String descripError){
//		
//		BigDecimal importeAApropiar = BigDecimal.ZERO;
//		
//		importeAApropiar = importeAApropiar.add(medioPago.getImporte());
//		
//		if (!Validaciones.isObjectNull(medioPago.getMontoAcumuladoSimulacion())) {
//			importeAApropiar = importeAApropiar.add(medioPago.getMontoAcumuladoSimulacion());
//		}
//		
//		try {
//			
//			BigDecimal importeValoresPendientesDeAplicar = cobroDao.importeDeAplicacionesPendientesYCobrosEnProcesos(valorSimplificado.getIdValor());
//			
//			
//			BigDecimal saldoDisponibleActual= valorSimplificado.getSaldoDisponible().subtract(importeValoresPendientesDeAplicar).add(medioPago.getImporte());
//			
//			if (!tieneSaldoSuficiente(importeAApropiar, saldoDisponibleActual)) {
//
//				descripError =Propiedades.MENSAJES_PROPIEDADES.getString("error.cobros.apropiacion.shiva.sinSaldo");
//				medioPago.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
//				medioPago.setMensajeEstado(descripError);
//				medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
//				return;
//				
//			}
//			
//		} catch (PersistenciaExcepcion e) {	
//			new NegocioExcepcion(e);
//		}
//
//	}
	
	
	public Boolean tieneSaldoSuficiente(BigDecimal importeAApropiar, BigDecimal saldoDisponibleActual){
		return (importeAApropiar.compareTo(saldoDisponibleActual) < 0 || importeAApropiar.compareTo(saldoDisponibleActual) == 0);
	}
}
