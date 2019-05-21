package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionSoporteServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
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
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoCliente;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;

public class CobroBatchSoporteImputacionContabilidadServicioImpl extends Servicio implements ICobroBatchSoporteImputacionContabilidadServicio{

	public CobroBatchSoporteImputacionContabilidadServicioImpl() {
		
	}
	
	@Autowired 
	private IContabilidadServicio contabilidadServicio;
	
	@Autowired 
	private IGenericoDao genericoDao;

	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;
	
	@Autowired 
	private ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired
	private ITransaccionSoporteServicio transaccionSoporteServicio;
	
	/***************************************************************************
	 * CONTABILIDAD
	 * ************************************************************************/
	
	/**
	 * Contabiliza una factura, sea FAC, DUC o DEB. Origenes 004, 005, 006 y 009.
	 * Este metodo se llama desde la confirmacion de un cobro y
	 * desde el descobro de un descobro.
	 */
	@SuppressWarnings("unchecked")
	public void contabilizarFactura(ShvCobFactura factura, boolean esCobro, String usuarioAnalista, Long idCobro, String transaccion, String analistaCobro) throws NegocioExcepcion{
		double fechaHoraInicioNanoTime = System.nanoTime();
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setIdAnalista(usuarioAnalista);
		contabilidadDto.setFechaValor(factura.getFechaValor());
				
		if(esCobro){
			//Confirmacion de un cobro
			if(TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante()) && factura instanceof ShvCobFacturaMic){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_004);
				contabilidadDto.setNumeroComprobante(((ShvCobFacturaMic)factura).getIdReferenciaFactura());
			}else{
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_005);
				contabilidadDto.setIdAnalista(analistaCobro);
				if (factura instanceof ShvCobFacturaCalipso) {
					contabilidadDto.setNumeroComprobante(factura.getClaseComprobante()+factura.getSucursalComprobante()+factura.getNumeroComprobante());
				} else {
					contabilidadDto.setNumeroComprobante(((ShvCobFacturaMic)factura).getIdReferenciaFactura());
				}
			}
		}else{
			//Descobro de un descobro
			if(TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante()) && factura instanceof ShvCobFacturaMic){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_009);
				contabilidadDto.setNumeroComprobante(((ShvCobFacturaMic)factura).getIdReferenciaFactura());
			}else{
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_006);
				if (factura instanceof ShvCobFacturaCalipso) {
					contabilidadDto.setNumeroComprobante(factura.getClaseComprobante()+factura.getSucursalComprobante()+factura.getNumeroComprobante());
				} else {
					contabilidadDto.setNumeroComprobante(((ShvCobFacturaMic)factura).getIdReferenciaFactura());
				}
			}
		}
		
		contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);	
		contabilidadDto.setCodigoClienteLegado(factura.getIdClienteLegado());
		contabilidadDto.setImporte(factura.getImporteCobrar());
		contabilidadDto.setMoneda(MonedaEnum.PES.name());
		
		if (factura instanceof ShvCobFacturaCalipso) {
			
			BigDecimal importeAplicado = null;
	
			if (MonedaEnum.PES.equals(factura.getMonedaImporteCobrar())){
			
				if (!MonedaEnum.PES.equals(((ShvCobFacturaCalipso)factura).getMoneda())){
					contabilidadDto.setMoneda(((ShvCobFacturaCalipso)factura).getMoneda().name());
					importeAplicado = ((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaOrigen();
				} else {
					importeAplicado = ((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaPesos();
				}
			} else {
				contabilidadDto.setMoneda(((ShvCobFacturaCalipso)factura).getMoneda().name());
			}
			
			contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobFacturaCalipso)factura).getTipoCambio()));
			if(!Validaciones.isObjectNull(importeAplicado)){
				contabilidadDto.setImporte(importeAplicado);
			}
		} else {
			contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
		}
		
		contabilidadDto.setTransaccion(transaccion);
		contabilidadDto.setIdCobro(idCobro);
		contabilidadDto.setIdTransaccion(factura.getTransaccion().getIdTransaccion().longValue());
		contabilidadDto.setIdCaja(factura.getTransaccion().getOperacion().getIdCaja());
		List<ShvParamTipoCliente> listaIdCliente;
		try {
			listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(factura.getTipoCliente()));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
			contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
		}
		contabilidadServicio.contabilizar(contabilidadDto);
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar una factura", fechaHoraInicioNanoTime);
	}
	
	/**
	 * Contabiliza una factura generada por diferencia de cambio,  Origen 005.
	 * Este metodo se llama desde la confirmacion de un cobro y
	 * desde el descobro de un descobro.
	 */
	@SuppressWarnings("unchecked")
	public void contabilizarFacturaEnMonedaOrigen(ShvCobFacturaCalipso factura, boolean esCobro, String usuarioAnalista, Long idCobro, String transaccion, String analistaCobro) throws NegocioExcepcion{
		
		double fechaHoraInicioNanoTime = System.nanoTime();
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setIdAnalista(usuarioAnalista);
		contabilidadDto.setFechaValor(factura.getFechaValor());
		
		if(esCobro){
			//Confirmacion de un cobro
			contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_005);
			contabilidadDto.setIdAnalista(analistaCobro);
			contabilidadDto.setNumeroComprobante(factura.getClaseComprobante()+factura.getSucursalComprobante()+factura.getNumeroComprobante());
	
		}else{
			//Descobro de un descobro
			contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_006);
			contabilidadDto.setNumeroComprobante(factura.getClaseComprobante()+factura.getSucursalComprobante()+factura.getNumeroComprobante());
		}
		
		contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);	
		contabilidadDto.setCodigoClienteLegado(factura.getIdClienteLegado());
		contabilidadDto.setImporte(factura.getImporteCobrar());
		contabilidadDto.setMoneda(factura.getMoneda().name());
		
		if (!MonedaEnum.PES.equals(factura.getMoneda())){
			contabilidadDto.setTipoDeCambio(String.valueOf(factura.getTipoCambio()));
			BigDecimal importeAplicadoMonedaOrigen = factura.getImporteAplicadoAFechaEmisionMonedaOrigen();
		
			if(!Validaciones.isObjectNull(importeAplicadoMonedaOrigen)){
				contabilidadDto.setImporte(importeAplicadoMonedaOrigen);
			}
		} else {
			contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
		} 
	
		
		contabilidadDto.setTransaccion(transaccion);
		contabilidadDto.setIdCobro(idCobro);
		contabilidadDto.setIdTransaccion(factura.getTransaccion().getIdTransaccion().longValue());
		contabilidadDto.setIdCaja(factura.getTransaccion().getOperacion().getIdCaja());
		List<ShvParamTipoCliente> listaIdCliente;
		try {
			listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(factura.getTipoCliente()));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
			contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
		}
		contabilidadServicio.contabilizar(contabilidadDto);
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar una factura", fechaHoraInicioNanoTime);
	}
	
	
	/**
	 * Metodo que contabiliza cualquier medio de pago asociado a una factura (007 o 008).
	 * Si el medio de pago es Shiva llama al metodo contabilizarMedioPagoShivaAsociadoFactura. 
	 * Impacta en la base.
	 * Este metodo se llama desde la confirmacion de un cobro y
	 * desde el descobro de un descobro.
	 * Recibe el documento cap, solo para los descobros.
	 */
	@Override
	public void contabilizarMedioPagoAsociadoFactura(ShvCobMedioPago medioPago, boolean cobro, String usuarioAnalista, Long idCobro, String transaccion, VistaSoporteResultadoBusquedaValor valor, boolean hayDiferenciaCambio) throws NegocioExcepcion{
		double fechaHoraInicioNanoTime = System.nanoTime();
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		
		try {
			// dependen de la factura
			ShvCobFactura factura = null;
			
			if (!hayDiferenciaCambio){
				factura = medioPago.getTransaccion().getFactura();
			} else {
				factura = medioPago.getTransaccion().getFacturaDC();
			}
			contabilidadDto.setIdTransaccion(factura.getTransaccion().getIdTransaccion().longValue());
			contabilidadDto.setFechaValor(factura.getFechaValor());
			
			//idTipoCliente
			@SuppressWarnings("unchecked")
			List<ShvParamTipoCliente> listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(factura.getTipoCliente()));
			if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
				contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
			}
		
		
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				contabilidadDto = contabilizarMedioPagoShivaAsociadoFactura(medioPago, contabilidadDto, valor);
			} else {
				
				//Numero comprobante y CodigoClienteLegado
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getIdClienteLegado());
					String numeroComprobante = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getClaseComprobante().name()
							  				 + ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getSucursalComprobante()
							  				 + ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getNroComprobante();
					contabilidadDto.setNumeroComprobante(numeroComprobante);
				
				} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoNotaCreditoMic) medioPago).getIdClienteLegado());
					contabilidadDto.setNumeroComprobante(((ShvCobMedioPagoNotaCreditoMic) medioPago).getNumeroNotaCredito());
				
				} else if (medioPago instanceof ShvCobMedioPagoCTA) {
					ShvCobMedioPagoCTA cta = (ShvCobMedioPagoCTA) medioPago;
					contabilidadDto.setNumeroComprobante(cta.getClaseComprobante()+cta.getSucursalComprobante()+cta.getNroComprobante());
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoCTA) medioPago).getIdClienteLegado());
				
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionIntercompany) medioPago).getNumeroCompensacion())));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionOtras) medioPago).getNumeroCompensacion())));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionIIBB) medioPago).getNumeroCompensacion())));
					contabilidadDto.setIdJurisdiccion(((ShvCobMedioPagoCompensacionIIBB) medioPago).getProvincia().getIdProvincia());
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionCesionCredito) medioPago).getNumeroCompensacion())));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionProveedor) medioPago).getDocumentoCap().getIdDocumento())));
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getDocumentoCap().getIdDocumento())));
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					
				} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
					contabilidadDto.setNumeroComprobante(truncarNumeroComprobante(String.valueOf(((ShvCobMedioPagoDesistimiento) medioPago).getNumeroActa())));
				
				} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
					contabilidadDto.setCodigoClienteLegado(obtenerClienteDeMedioPagoUsuario(medioPago));
				
				} else if (medioPago instanceof ShvCobMedioPagoRemanente){
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoRemanente) medioPago).getIdClienteLegado()); 
					contabilidadDto.setNumeroComprobante(String.valueOf(((ShvCobMedioPagoRemanente) medioPago).getCuentaRemanente())
											+ ((ShvCobMedioPagoRemanente) medioPago).getTipoRemanente());
					
				} else if(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic){
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoDebitoProximaFacturaMic)medioPago).getIdClienteLegadoAcuerdoTrasladoCargo());
					
				} else if(medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
					contabilidadDto.setCodigoClienteLegado(((ShvCobMedioPagoDebitoProximaFacturaCalipso)medioPago).getIdClienteLegadoAcuerdoTrasladoCargo());
				}
				
				contabilidadDto.setTransaccion(transaccion);
			}
			
			// Tipo origen contable
			contabilidadDto.setDescripcionTipoOrigenContable((cobro)?ContabilidadServicioImpl.ORIGEN_CONT_007:ContabilidadServicioImpl.ORIGEN_CONT_008);
			
	
			
			// dependen de la factura
			contabilidadDto.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().longValue());
			
			// Moneda
			contabilidadDto.setMoneda(medioPago.getMoneda().name());
			contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
	
			// Datos comunes
			contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
			contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
			contabilidadDto.setIdTipoMedioPago(medioPago.getTipoMedioPago());
			
			if (!MonedaEnum.PES.equals(medioPago.getMoneda())){
				BigDecimal importeMonedaOrigen = null;
				
				if (medioPago.getMoneda().equals(medioPago.getMonedaImporte())){
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
						importeMonedaOrigen = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getImporteAplicadoAFechaEmisionMonedaOrigen();
						contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoCambio()));
						
					} else if (medioPago instanceof ShvCobMedioPagoCTA){
						importeMonedaOrigen = ((ShvCobMedioPagoCTA) medioPago).getImporteAplicadoAFechaEmisionMonedaOrigen();
						contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoCTA) medioPago).getTipoCambio()));
						
					}
				} else {
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
						importeMonedaOrigen = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getImporteAplicadoAFechaEmisionMonedaOrigen();
						contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoDeCambioFechaEmision()));
						
					} else if (medioPago instanceof ShvCobMedioPagoCTA){
						importeMonedaOrigen = ((ShvCobMedioPagoCTA) medioPago).getImporteAplicadoAFechaEmisionMonedaOrigen();
						contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoCTA) medioPago).getTipoDeCambioFechaCobro()));
						
					}
				}
				
				if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionIntercompany) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionOtras) medioPago).getTipoCambio()));
	
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionIIBB) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionCesionCredito) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionProveedor) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoDesistimiento) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoPlanDePago) medioPago).getTipoCambio()));
					
				} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
					contabilidadDto.setTipoDeCambio(String.valueOf( ((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago).getTipoCambio()));
				
				}
	//			else if (medioPago instanceof ShvCobMedioPagoShiva) {
	//				tipoCambioMedioPago = ((ShvCobMedioPagoShiva) medioPago).getTipoDeCambioFechaEmision();
	//			}
				
				if (!MonedaEnum.PES.equals(medioPago.getMonedaImporte())){
					importeMonedaOrigen = medioPago.getImporte();
				}
				if (!Validaciones.isObjectNull(importeMonedaOrigen)){
					contabilidadDto.setImporte(importeMonedaOrigen);
				}
				
			} else {
				contabilidadDto.setImporte(medioPago.getImporte());
			}
			contabilidadDto.setIdCobro(idCobro);
			contabilidadDto.setIdAnalista(usuarioAnalista);
			contabilidadDto.setIdCaja(medioPago.getTransaccion().getOperacion().getIdCaja());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		// graba en la base
		contabilidadServicio.contabilizar(contabilidadDto);
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago asociado a una factura", fechaHoraInicioNanoTime);
	}
	
	/**
	 * Retorna un ContabilidadDto para contabilizar un medio de pago Shiva asociado a una factura.
	 * 
	 * @param medioPago
	 * @param contabilidadDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ContabilidadDto contabilizarMedioPagoShivaAsociadoFactura(ShvCobMedioPago medioPago, 
						ContabilidadDto contabilidadDto, VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion {

		double fechaHoraInicioNanoTime = System.nanoTime();
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setCodigoClienteLegado(Long.valueOf(valor.getIdClienteLegado()));
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
		contabilidadDto.setIdAnalista(valor.getIdAnalista());
		contabilidadDto.setMoneda(medioPago.getMoneda().name());
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setTransaccion(String.valueOf(contabilidadDto.getIdValor()));
		
		if(TipoValorEnum.CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
		} else if(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
		} else if(TipoValorEnum.EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));	
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
		} else if(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));
		} else if(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setCuit(valor.getNroCuitRetencion());
			if(valor.getReferenciaValor().length() < 16){
				contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
				} else {
					contabilidadDto.setNumeroComprobante(valor.getReferenciaValor().substring(1,valor.getReferenciaValor().length()));
				}
			contabilidadDto.setIdJurisdiccion((valor.getIdProvinciaRetencion() != null)?valor.getIdProvinciaRetencion():null);
		} else if(TipoValorEnum.TRANSFERENCIA.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setCuit(valor.getNroCuitTransaccion());
		} else if(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setIdOrganismo(valor.getCodigoOrganismo());
		}
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago Shiva asociado a una factura", fechaHoraInicioNanoTime);
		return contabilidadDto;
	}
	
	/**
	 * Metodo que contabiliza cualquier medio de pago asociado a un tratamiento de la diferencia (007 o 008).
	 * Si el medio de pago es de tipo Shiva, llama al metodo contabilizarValorAsociadoMedPagoShivaAsociadoTratamiento.
	 * Este metodo se llama desde la confirmacion de un cobro y
	 * desde el descobro de un descobro.
	 */
	@Override
	public void contabilizarMedioPagoAsociadoTratamiento(ShvCobMedioPago medioPago, boolean esCobro, String usuarioAnalista, ShvCobCobro cobro, String transaccion, Long idDescobro, VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion{
		try{
			double fechaHoraInicioNanoTime = System.nanoTime();
			ContabilidadDto contabilidadDto = new ContabilidadDto();
			
			ShvCobTratamientoDiferencia tratamiento = medioPago.getTransaccion().getListaTratamientosDiferencias().iterator().next();	
			
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				//Contabilizo el medio de pago 
				contabilidadDto = contabilizarMedioPagoShivaAsociadoReintegro(medioPago, contabilidadDto, esCobro, usuarioAnalista, valor);
				//Contabilizo el valor con 014
				contabilizarValorAsociadoMedPagoShivaAsociadoReintegro(valor, medioPago, cobro, esCobro, idDescobro, tratamiento.getFechaValor(), usuarioAnalista);
				
			} else {
				
				//Numero comprobante y CodigoClienteLegado
				if (!Validaciones.isObjectNull(tratamiento.getNumeroTramiteReintegro())){
					contabilidadDto.setNumeroComprobante(String.valueOf(tratamiento.getNumeroTramiteReintegro()));
				}
				
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
					@SuppressWarnings("unchecked")
					List<ShvParamTipoCliente> listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoCliente()));
					if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
						contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
					}
				
				} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
					@SuppressWarnings("unchecked")
					List<ShvParamTipoCliente> listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(((ShvCobMedioPagoNotaCreditoMic) medioPago).getTipoCliente()));
					if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
						contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
					}
				
				} //Se quita los otros medios de pago porque no se hacia nada (ShvCobMedioPagoCTA, ShvCobMedioPagoCompensacionIntercompany,ShvCobMedioPagoCompensacion,
				//ShvCobMedioPagoCompensacionLiquidoProducto,ShvCobMedioPagoDesstimiento,ShvCobMedioPagoPlanDePago,ShvCobMedioPagoRemanente)
	
				// Tipo origen contable
				contabilidadDto.setDescripcionTipoOrigenContable((esCobro)?ContabilidadServicioImpl.ORIGEN_CONT_007:ContabilidadServicioImpl.ORIGEN_CONT_008);
			}
			
			//Seteo id Tipo de Medio Pago y Codigo Cliente Legado
			if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.CREDITO_PROXIMA_FACTURA_CALIPSO.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(tratamiento.getIdClienteLegadoAcuerdoTrasladoCargo());
				
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.CREDITO_PROXIMA_FACTURA_MIC.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(tratamiento.getIdClienteLegadoAcuerdoTrasladoCargo());
				
			} else if (TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.ENVIO_A_GANANCIAS.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				//U562163 - se informa la provincia del cliente.
				ShvCobEdCliente cliente = cobroOnlineServicio.buscarClientePorIdCobroYIdClienteLegado(medioPago.getIdCobro(),medioPago.getIdClienteLegado());
				contabilidadDto.setIdJurisdiccion(cliente.getIdProvincia());
				
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.REINTEGRO_POR_CHEQUE.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.REINTEGRO_CREDITO_RED_INTEL.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				
			} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.REINTEGRO_TRANSFERENCIA_BAN.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				
			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_NEGOCIO_NET.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
				
			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_SAP.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());

			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_OPEN.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
			
			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(tratamiento.getTipoTratamientoDiferencia())) {
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_SAP.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());

			} else if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(tratamiento.getTipoTratamientoDiferencia())) {
				//Buscar por idTipoMedioPago
				contabilidadDto.setIdTipoMedioPago((ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_NEXTEL_NEXUS.getIdTipoMedioPago()));
				contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
			}
			
			// dependen de la factura
			contabilidadDto.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().longValue());
			contabilidadDto.setTransaccion(transaccion);
			
			// Moneda
			contabilidadDto.setMoneda(medioPago.getMonedaImporte().name());
			contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
			contabilidadDto.setImporte(medioPago.getImporte());
			
			if (!MonedaEnum.PES.equals(medioPago.getMonedaImporte())){
				
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
					contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoDeCambioFechaEmision()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCTA){
					contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoCTA) medioPago).getTipoDeCambioFechaEmision()));
				}
			}
	
			// Datos comunes
			contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
			contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		
			contabilidadDto.setIdCobro((esCobro)?cobro.getIdCobro():idDescobro);
			contabilidadDto.setIdAnalista(usuarioAnalista);
			contabilidadDto.setIdCaja(medioPago.getTransaccion().getOperacion().getIdCaja());
			contabilidadDto.setFechaValor(tratamiento.getFechaValor());
			
			// graba en la base
			contabilidadServicio.contabilizar(contabilidadDto);
			
			Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago asociado a un tratamiento", fechaHoraInicioNanoTime);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Genera un contabilidadDto para la confirmacion de un medio de pago shiva asociado a un tratamiento diferencia (017 o 019).
	 * También será utilizado para las facturas de usuario (otros débitos) que tendrán el mismo tratamiento.
	 * 
	 * @param medioPago
	 * @param contabilidadDto
	 * @param esCobro
	 * @param usuarioAnalista
	 * @param valor
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ContabilidadDto contabilizarMedioPagoShivaAsociadoReintegro(ShvCobMedioPago medioPago, 
			ContabilidadDto contabilidadDto, Boolean esCobro, String usuarioAnalista, VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion {
		
		ShvCobTransaccion transaccion = medioPago.getTransaccion();
		
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {

			ShvCobTratamientoDiferencia tratamiento = transaccion.getTratamientoDiferencia();

			// Fecha Valor
			contabilidadDto.setFechaValor(tratamiento.getFechaValor());
			
			// Numeo de comprobante
			if (!Validaciones.isObjectNull(tratamiento.getNumeroTramiteReintegro())){
				contabilidadDto.setNumeroComprobante(String.valueOf(tratamiento.getNumeroTramiteReintegro()));
			}

			// Código de cliente
			if(!Validaciones.isObjectNull(tratamiento.getIdClienteLegadoAcuerdoTrasladoCargo())){
				contabilidadDto.setCodigoClienteLegado(tratamiento.getIdClienteLegadoAcuerdoTrasladoCargo());
			} else {
				contabilidadDto.setCodigoClienteLegado(Long.valueOf(valor.getIdClienteLegado()));
			}
		} else {
			ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();

			// Fecha Valor
			contabilidadDto.setFechaValor(facturaUsuario.getFechaValor());

			// Numero comprobante
			if (TipoComprobanteEnum.CDD.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
				// Para los "Conjuntos de Debitos" no se debe informar nada en el campo "comprobante"
				
			} else if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
				if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setNumeroComprobante(facturaUsuario.getIdClienteLegado().toString());
					
				} else {
					// Para las "Cuentas Corrientes" que no sean SAP, se debe informar la referencia, los primeros 15 digitos. 
					if (facturaUsuario.getReferencia().length() > 15) {
						contabilidadDto.setNumeroComprobante(facturaUsuario.getReferencia().substring(0, 15));
					} else {
						contabilidadDto.setNumeroComprobante(facturaUsuario.getReferencia());
					}
				}

			} else {
				// Para el resto de los documentos (Facturas, Nota de Débitos, Documentos SAP, Equipos y Servicios) se debe 
				// informar el "numero de documento" en el campo "comprobante"
				StringBuffer numeroComprobante = new StringBuffer();
				if (!Validaciones.isObjectNull(facturaUsuario.getClaseComprobante())) {
					numeroComprobante.append(facturaUsuario.getClaseComprobante());
				}
				numeroComprobante.append(facturaUsuario.getSucursalComprobante());
				numeroComprobante.append(facturaUsuario.getNumeroComprobante());
				
				contabilidadDto.setNumeroComprobante(numeroComprobante.toString());
			}

			// Código de cliente
			contabilidadDto.setCodigoClienteLegado(medioPago.getIdClienteLegado());
		}
			
		double fechaHoraInicioNanoTime = System.nanoTime();
		contabilidadDto.setIdValor(valor.getIdValor());
		
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
		contabilidadDto.setIdAnalista((esCobro)?valor.getIdAnalista():usuarioAnalista);
		contabilidadDto.setMoneda(Constantes.MONEDA_PES);
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setTransaccion(String.valueOf(contabilidadDto.getIdValor()));
	
		if(TipoValorEnum.CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
			
		} else if(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
			
		} else if(TipoValorEnum.EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
			
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setIdBanco((valor.getBancoDeposito() != null)?valor.getBancoDeposito():null);
			
		} else if(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			
		} else if(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setCuit(valor.getNroCuitRetencion());
			contabilidadDto.setIdJurisdiccion((valor.getIdProvinciaRetencion() != null)?valor.getIdProvinciaRetencion():null);
			
		} else if(TipoValorEnum.TRANSFERENCIA.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setCuit(valor.getNroCuitTransaccion());
			
		} else if(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
	
		}
		
		// Tipo origen contable
		contabilidadDto.setDescripcionTipoOrigenContable((esCobro)?ContabilidadServicioImpl.ORIGEN_CONT_017:ContabilidadServicioImpl.ORIGEN_CONT_019);
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago Shiva asociado a un tratamiento", fechaHoraInicioNanoTime);
		return contabilidadDto;
	}
	
	/**
	 * Metodo que contabiliza cualquier medio de pago asociado a una factura de usuario (otros debitos) cuyo tratamiento será
	 * el mismo que para tratamiento de la diferencia (007 o 008).
	 * Si el medio de pago es de tipo Shiva, llama al metodo contabilizarValorAsociadoMedPagoShivaAsociadoTratamiento.
	 * Este metodo se llama desde la confirmacion de un cobro y
	 * desde el descobro de un descobro.
	 */
	@Override
	public void contabilizarMedioPagoAsociadoFacturaUsuario(ShvCobMedioPago medioPago, boolean esCobro, String usuarioAnalista, 
			ShvCobCobro cobro, String transaccion, Long idDescobro, VistaSoporteResultadoBusquedaValor valor) throws NegocioExcepcion{

		try{
			double fechaHoraInicioNanoTime = System.nanoTime();
			ContabilidadDto contabilidadDto = new ContabilidadDto();
			
			ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) medioPago.getTransaccion().getFactura();
			
			if (medioPago instanceof ShvCobMedioPagoShiva) {
				// Contabilizo el medio de pago 
				contabilidadDto = contabilizarMedioPagoShivaAsociadoReintegro(medioPago, contabilidadDto, esCobro, usuarioAnalista, valor);
				// Contabilizo el valor con 014
				contabilizarValorAsociadoMedPagoShivaAsociadoReintegro(valor, medioPago, cobro, esCobro, idDescobro, facturaUsuario.getFechaValor(), usuarioAnalista);
				
			} else {
				
				// Numero comprobante
				if (TipoComprobanteEnum.CDD.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
					// Para los "Conjuntos de Debitos" no se debe informar nada en el campo "comprobante"
					
				} else if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
					if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
						contabilidadDto.setNumeroComprobante(facturaUsuario.getIdClienteLegado().toString());
						
					} else {
						// Para las "Cuentas Corrientes" que no sean SAP, se debe informar la referencia, los primeros 15 digitos. 
						if (facturaUsuario.getReferencia().length() > 15) {
							contabilidadDto.setNumeroComprobante(facturaUsuario.getReferencia().substring(0, 15));
						} else {
							contabilidadDto.setNumeroComprobante(facturaUsuario.getReferencia());
						}
					}

				} else {
					// Para el resto de los documentos (Facturas, Nota de Débitos, Documentos SAP, Equipos y Servicios) se debe 
					// informar el "numero de documento" en el campo "comprobante"
					StringBuffer numeroComprobante = new StringBuffer();
					if (!Validaciones.isObjectNull(facturaUsuario.getClaseComprobante())) {
						numeroComprobante.append(facturaUsuario.getClaseComprobante());
					}
					numeroComprobante.append(facturaUsuario.getSucursalComprobante());
					numeroComprobante.append(facturaUsuario.getNumeroComprobante());
					
					contabilidadDto.setNumeroComprobante(numeroComprobante.toString());
				}

				// Tipo de Cliente
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso || medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
					
					String tipoCliente = null;
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
						tipoCliente = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoCliente();
					}
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
						tipoCliente = ((ShvCobMedioPagoNotaCreditoMic) medioPago).getTipoCliente();
					}
					
					@SuppressWarnings("unchecked")
					List<ShvParamTipoCliente> listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(
							ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(tipoCliente));

					if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
						contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
					}
				}
				
				// Cliente
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso 
						|| medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso
						|| medioPago instanceof ShvCobMedioPagoUsuario) {

					String idClienteLegado = null;
					
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
						idClienteLegado = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getIdClienteLegado().toString(); 
					
					} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
						idClienteLegado = ((ShvCobMedioPagoNotaCreditoMic) medioPago).getIdClienteLegado().toString(); 

					} else if (medioPago instanceof ShvCobMedioPagoUsuario) {
						idClienteLegado = ((ShvCobMedioPagoUsuario) medioPago).obtenerPrimerIdClienteLegado(); 
					}
					
					if (!Validaciones.isObjectNull(idClienteLegado)) {
						contabilidadDto.setCodigoClienteLegado(new Long(idClienteLegado));
					}
				}
	
				// Tipo origen contable
				contabilidadDto.setDescripcionTipoOrigenContable((esCobro)?ContabilidadServicioImpl.ORIGEN_CONT_007:ContabilidadServicioImpl.ORIGEN_CONT_008);
			}
			
			//
			// Seteo id Tipo de Medio Pago y Codigo Cliente Legado
			//
			if (SociedadEnum.PERSONAL.equals(facturaUsuario.getSociedad())) {
				if (SistemaEnum.NEGOCIO_NET.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_NEGOCIO_NET.getIdTipoMedioPago()));
				} else if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PERSONAL_SAP.getIdTipoMedioPago()));
				}
				
			} else if (SociedadEnum.FIBERCORP.equals(facturaUsuario.getSociedad())) {
				if (SistemaEnum.OPEN.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_OPEN.getIdTipoMedioPago()));
				} else if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_FIBERCORP_SAP.getIdTipoMedioPago()));
				}
				
			} else if (SociedadEnum.NEXTEL.equals(facturaUsuario.getSociedad())) {
				if (SistemaEnum.NEXUS.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_NEXTEL_NEXUS.getIdTipoMedioPago()));
				}
				
			} else if (SociedadEnum.TELECOM.equals(facturaUsuario.getSociedad())) {
				if (SistemaEnum.HUAWEI.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_TELECOM_HUAWEI.getIdTipoMedioPago()));
				} else if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_TELECOM_SAP.getIdTipoMedioPago()));
				}
				
			} else if (SociedadEnum.PUBLICIDAD.equals(facturaUsuario.getSociedad())) {
				if (SistemaEnum.OPEN.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PUBLICIDAD_Y_WHOLE_SALES_OPEN.getIdTipoMedioPago()));
				} else if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					contabilidadDto.setIdTipoMedioPago(
							(ShvParamTipoMedioPago)tipoMedioPagoDao.buscar(TipoMedioPagoEnum.APLICACION_MANUAL_PUBLICIDAD_Y_WHOLE_SALES_SAP.getIdTipoMedioPago()));
				}
			}
			
			// Datos de Transaccion
			contabilidadDto.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().longValue());
			contabilidadDto.setTransaccion(transaccion);
			
			// Moneda
			contabilidadDto.setMoneda(medioPago.getMonedaImporte().name());
			contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
			contabilidadDto.setImporte(medioPago.getImporte());
			
			if (!MonedaEnum.PES.equals(medioPago.getMonedaImporte())){
				
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
					contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoDeCambioFechaEmision()));
					
				} else if (medioPago instanceof ShvCobMedioPagoCTA){
					contabilidadDto.setTipoDeCambio(String.valueOf(((ShvCobMedioPagoCTA) medioPago).getTipoDeCambioFechaEmision()));
				}
			}
	
			// Datos comunes
			contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
			contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		
			contabilidadDto.setIdCobro((esCobro)?cobro.getIdCobro():idDescobro);
			contabilidadDto.setIdAnalista(usuarioAnalista);
			contabilidadDto.setIdCaja(medioPago.getTransaccion().getOperacion().getIdCaja());
			contabilidadDto.setFechaValor(facturaUsuario.getFechaValor());
			
			// graba en la base
			contabilidadServicio.contabilizar(contabilidadDto);
			
			Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago asociado a una factura de usuario", fechaHoraInicioNanoTime);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Recibe un valor y genera un alta en contabilidad para dicho valor (014 o 018). 
	 * Impacta en la base.
	 *  
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	private void contabilizarValorAsociadoMedPagoShivaAsociadoReintegro(VistaSoporteResultadoBusquedaValor valor, ShvCobMedioPago medioPago, ShvCobCobro cobro, boolean esCobro, Long idDescobro, Date fechaValor, String usuarioAnalista) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
		
		if (esCobro) {
			contabilidadDto.setIdCobro(cobro.getIdCobro());
			contabilidadDto.setIdAnalista(cobro.getWorkflow().getUsuarioAlta());
		} else {
			contabilidadDto.setIdCobro(idDescobro);
			contabilidadDto.setIdAnalista(usuarioAnalista);
		}
		
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setTransaccion(String.valueOf(valor.getIdValor()));
		contabilidadDto.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion().longValue());
		contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		contabilidadDto.setCodigoClienteLegado(Long.valueOf(valor.getIdClienteLegado()));
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
		contabilidadDto.setMoneda(Constantes.MONEDA_PES);
		contabilidadDto.setImporte(medioPago.getImporte());
		contabilidadDto.setFechaValor(fechaValor);
		
		if(TipoValorEnum.CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.CHEQUEPROPIO));
		
		} else if(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.CHEQUEDIFERIDO));
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
		
		} else if(TipoValorEnum.EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.BOLETADEDEPOSITO));
			contabilidadDto.setNumeroBoleta((!Validaciones.isObjectNull(valor.getNroBoleta())?Long.valueOf(valor.getNroBoleta()):null));
		
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.CHEQUEPROPIO));
	
		} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.CHEQUEDIFERIDO));
		
		} else if(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroBoleta(Long.valueOf(valor.getNroBoleta()));
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.BOLETADEDEPOSITO));
		
		} else if(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_002);
			contabilidadDto.setCuit(valor.getNroCuitRetencion());
			if(valor.getReferenciaValor().length() < 16){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			} else {
				contabilidadDto.setNumeroComprobante(valor.getReferenciaValor().substring(1,valor.getReferenciaValor().length()));
			}
			contabilidadDto.setIdJurisdiccion((valor.getIdProvinciaRetencion() != null)?valor.getIdProvinciaRetencion():null);
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.RETENCIONIIBB));
		
		} else if(TipoValorEnum.TRANSFERENCIA.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setCuit(valor.getNroCuitTransaccion());
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.TRANSFBANCARIA));
		
		} else if(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor() == Integer.valueOf(valor.getIdTipoValor())){
			contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());
			contabilidadDto.setIdOrganismo(valor.getCodigoOrganismo());
			contabilidadDto.setIdTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.INTERDEPOSITO));
		}
		
		contabilidadDto.setDescripcionTipoOrigenContable((esCobro)?ContabilidadServicioImpl.ORIGEN_CONT_014:ContabilidadServicioImpl.ORIGEN_CONT_018);
		contabilidadDto.setIdCaja(medioPago.getTransaccion().getOperacion().getIdCaja());
		contabilidadServicio.contabilizar(contabilidadDto);
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un valor asociado a un medio de pago Shiva asociado a un tratamiento", fechaHoraInicioNanoTime);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void contabilizarDocumentoEnDolar(ShvCobFactura factura, BigDecimal importeEnPesos, Long idCobro, String transaccion, String usuarioAnalista, boolean esCobro) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		double fechaHoraInicioNanoTime = System.nanoTime();
		
		contabilidadDto.setIdTransaccion(factura.getTransaccion().getIdTransaccion().longValue());
		contabilidadDto.setTransaccion(transaccion);
		contabilidadDto.setFechaValor(factura.getFechaValor());
		
		//idTipoCliente
		List<ShvParamTipoCliente> listaIdCliente;
		
		try {
			
			listaIdCliente = (List<ShvParamTipoCliente>) genericoDao.listarPorValor(ShvParamTipoCliente.class, "idTipoCliente", String.valueOf(factura.getTipoCliente()));
			
			if ((listaIdCliente != null)?!listaIdCliente.isEmpty():false){
				contabilidadDto.setIdTipoCliente(listaIdCliente.get(0));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		// Tipo origen contable
		contabilidadDto.setDescripcionTipoOrigenContable((esCobro)?ContabilidadServicioImpl.ORIGEN_CONT_021 : ContabilidadServicioImpl.ORIGEN_CONT_022);
		
		// Moneda
		contabilidadDto.setMoneda(MonedaEnum.PES.name());
		contabilidadDto.setTipoDeCambio(Constantes.TIPO_DE_CAMBIO_POR_DEFECTO);
	
		// Datos comunes
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
		contabilidadDto.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		
		contabilidadDto.setImporte(importeEnPesos);
		contabilidadDto.setIdCobro(idCobro);
		contabilidadDto.setIdAnalista(usuarioAnalista);
		contabilidadDto.setIdCaja(factura.getTransaccion().getOperacion().getIdCaja());
		
		contabilidadDto.setCodigoClienteLegado(factura.getIdClienteLegado());
	
		// graba en la base
		contabilidadServicio.contabilizar(contabilidadDto);
		Utilidad.tracearTiempo(getClass(), "Tiempo en contabilizar un medio de pago asociado a una factura", fechaHoraInicioNanoTime);
	
	}

	/**
	 * Retorna un cliente cualquiera asociado al medio de pago de tipo usuario.
	 * @param medioPago
	 * @return
	 */
	private Long obtenerClienteDeMedioPagoUsuario(ShvCobMedioPago medioPago) {
		Set<ShvCobMedioPagoCliente> clientes = null;
		if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
			clientes = ((ShvCobMedioPagoCompensacionIntercompany) medioPago).getListaMedioPagoClientes();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
			clientes = ((ShvCobMedioPagoCompensacionOtras) medioPago).getListaMedioPagoClientes();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
			clientes = ((ShvCobMedioPagoCompensacionIIBB) medioPago).getListaMedioPagoClientes();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
			clientes = ((ShvCobMedioPagoCompensacionCesionCredito) medioPago).getListaMedioPagoClientes();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
			clientes = ((ShvCobMedioPagoCompensacionProveedor) medioPago).getListaMedioPagoClientes();
	
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
			clientes = ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getListaMedioPagoClientes();
	
		} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
			clientes = ((ShvCobMedioPagoDesistimiento) medioPago).getListaMedioPagoClientes();
		
		} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
			clientes = ((ShvCobMedioPagoPlanDePago) medioPago).getListaMedioPagoClientes();
		}
		
		// Ordena la lista de clientes, para siempre devolver el mismo.
		if(Validaciones.isCollectionNotEmpty(clientes)){
			Iterator<ShvCobMedioPagoCliente> iterator = clientes.iterator();
			List<String> clientesOrdenados = new ArrayList<String>();
	
			while (iterator.hasNext()) {
				clientesOrdenados.add(iterator.next().getIdClienteLegado());
			}
			Collections.sort(clientesOrdenados);
			return Long.valueOf(clientesOrdenados.get(0));
			
		} else {
			if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
				return ((ShvCobMedioPagoCompensacionIntercompany) medioPago).getIdClienteLegado();
				
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
				return ((ShvCobMedioPagoCompensacionOtras) medioPago).getIdClienteLegado();
				
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
				return ((ShvCobMedioPagoCompensacionIIBB) medioPago).getIdClienteLegado();
				
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
				return ((ShvCobMedioPagoCompensacionCesionCredito) medioPago).getIdClienteLegado();
				
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
				return ((ShvCobMedioPagoCompensacionProveedor) medioPago).getIdClienteLegado();
	
			} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
				return ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getIdClienteLegado();
	
			} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
				return ((ShvCobMedioPagoDesistimiento) medioPago).getIdClienteLegado();
			
			} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
				return ((ShvCobMedioPagoPlanDePago) medioPago).getIdClienteLegado();
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	@Override
	public BigDecimal calcularTipoCambioMedioPago(ShvCobMedioPago medioPago) throws NegocioExcepcion {
		
		BigDecimal tipoCambioMedioPago = null;
		
		if (medioPago instanceof ShvCobMedioPagoCTA) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCTA) medioPago).getTipoCambio();
		
		} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
			tipoCambioMedioPago = ((ShvCobMedioPagoNotaCreditoCalipso) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionIntercompany) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionOtras) medioPago).getTipoCambio();
	
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getTipoCambio();
	
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionIIBB) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionCesionCredito) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
			tipoCambioMedioPago = ((ShvCobMedioPagoCompensacionProveedor) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
			tipoCambioMedioPago = ((ShvCobMedioPagoDesistimiento) medioPago).getTipoCambio();
			
		} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
			tipoCambioMedioPago = ((ShvCobMedioPagoPlanDePago) medioPago).getTipoCambio();
		}
		else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
			tipoCambioMedioPago = ((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago).getTipoCambio();
	
		}
	//	else if (medioPago instanceof ShvCobMedioPagoShiva) {
	//		tipoCambioMedioPago = ((ShvCobMedioPagoShiva) medioPago).getTipoDeCambioFechaEmision();
	//	}
		return tipoCambioMedioPago;
	}

	private String truncarNumeroComprobante(String str){
		if(!Validaciones.isNullOrEmpty(str)){
			if(str.length()>15){
				return str.substring(0, 14);
			}
		}
		return str;
	}


}
