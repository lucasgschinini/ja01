package ar.com.telecom.shiva.batch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.AccionesSobreDiferenciaDeCambioEnum;
import ar.com.telecom.shiva.base.enumeradores.AlgoritmoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.utils.UnicaInstanciaFileKey;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.MedioPagoTralasdo;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

public class CalipsoWsSecurityTestRunner {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "INICIO - Prueba de conexion al Calipso usando la certificacion");
			if (!UnicaInstanciaFileKey.setLock(Constantes.LOCK_KEY_ACTUALIZACION_TEAM_COMERCIAL_BATCH))
				throw new AccessControlException(Mensajes.LISTENER_UNICA_INSTANCIA);
			
 			IParametroServicio parametroServicio =  (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
			Utilidad.verificarVersion(parametroServicio);
			
			//PROCESO
			testCalipso();
		
		} catch (AccessControlException e) {
			System.err.println(Mensajes.LISTENER_UNICA_INSTANCIA);
			Traza.error(CalipsoWsSecurityTestRunner.class, Mensajes.LISTENER_UNICA_INSTANCIA, e);
			System.exit(Constantes.SH_ERROR_INSTANCIA);
		} catch (Exception e) {
			System.err.println(Utilidad.getStackTrace(e));
			Traza.error(CalipsoWsSecurityTestRunner.class, "Se ha producido el error en la prueba de conexion al calipso usando la certificacion", e);
			System.exit(Constantes.SH_ERROR);
		} finally{
			UnicaInstanciaFileKey.releaseLock();
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "Se ha finalizado la prueba de conexion al Calipso usando la certificacion");
		}
		System.exit(0);
	}
	
	/**
	 * 
	 * @throws ShivaExcepcion
	 */
	private static void testCalipso() throws ShivaExcepcion {
		try {
			testApropiacionCalipso();
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "---- Apropiacion OK");
			
			testConfirmacionCalipso();
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "---- Confirmacion OK");
			
			testDesapropiacionCalipso();
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "---- Desapropiacion OK");
			
			System.out.println("Se ha finalizado exitosamente el proceso de testeo");
			Traza.auditoria(CalipsoWsSecurityTestRunner.class, "---- Se ha finalizado exitosamente el proceso de testeo");
		} catch (Throwable e) {
			Traza.error(CalipsoWsSecurityTestRunner.class, "Se ha producido un error en el proceso de testeo", e);
			Traza.advertencia(CalipsoWsSecurityTestRunner.class, "---- Se ha producido un error en el proceso de testeo");
			throw new ShivaExcepcion(e);
		}
	}
	
	public static void testApropiacionCalipso() throws NegocioExcepcion {
		System.out.println("---------------- Apropiacion ----------------------");
		EntradaCalipsoApropiacionWS eEntrada = new EntradaCalipsoApropiacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP);
		
		eEntrada.setIdOperacion(new Long(0));
		eEntrada.setIdTransaccion(0);
		eEntrada.setNumeroTransaccion(0);
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		eEntrada.setModoOperacion(SiNoEnum.NO);
		eEntrada.setMonedaOperacion(MonedaEnum.DOL);
		try {
			eEntrada.setFechaCobranza(Utilidad.parseFechaBarraString("2011/03/10"));
		} catch (ParseException e) {
			throw new WebServiceExcepcion(e);
		}
		
		DetalleFactura detalleFactura = new DetalleFactura();
		IdDocumento idDocumento = new IdDocumento();
		idDocumento.setTipoComprobante(TipoComprobanteEnum.FAC);
		idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.A);
		idDocumento.setSucursalComprobante("0424");
		idDocumento.setNumeroComprobante("00001427");
		detalleFactura.setIdDocumento(idDocumento);
		
		detalleFactura.setMontoACancelarEnPesos(new BigDecimal("500"));
		
		
		
		detalleFactura.setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
		detalleFactura.setTipoMora(TratamientoInteresesEnum.TC.getCodigoCalipsoApropiacion());
		detalleFactura.setAlgoritmoMora(AlgoritmoMoraEnum.CALCULO_LINEAL);
		detalleFactura.setImporteBonificacionIntereses(new BigDecimal(30));
		detalleFactura.setAcuerdoFacturacion("54203");
		detalleFactura.setAccionSobreDiferenciaDeCambio(AccionesSobreDiferenciaDeCambioEnum.NA);
		eEntrada.setDetalleFactura(detalleFactura);
		
		List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();
		DetalleCTAoNotaCredito detalleCTA = new DetalleCTAoNotaCredito();
		detalleCTA.setImporte(new BigDecimal(300));
		detalleCTA.setIdCobranza(new BigInteger("0"));
		idDocumento = new IdDocumento();
		idDocumento.setTipoComprobante(TipoComprobanteEnum.CTA);
		idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.X);
		idDocumento.setSucursalComprobante("0416");
		idDocumento.setNumeroComprobante("00007581");
		detalleCTA.setIdDocumento(idDocumento);
		listaCtaONotaCredito.add(detalleCTA);
		
		detalleCTA = new DetalleCTAoNotaCredito();
		detalleCTA.setImporte(new BigDecimal(1000));
		detalleCTA.setIdCobranza(new BigInteger("0"));
		idDocumento = new IdDocumento();
		idDocumento.setTipoComprobante(TipoComprobanteEnum.CRE);
		idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.A);
		idDocumento.setSucursalComprobante("0424");
		idDocumento.setNumeroComprobante("00000174");
		detalleCTA.setIdDocumento(idDocumento);
		listaCtaONotaCredito.add(detalleCTA);
		eEntrada.setListaCtaONotaCredito(listaCtaONotaCredito);
		
		List<MedioPagoTralasdo> listaMedioPago = new ArrayList<MedioPagoTralasdo>();
		MedioPagoTralasdo medioPago = new MedioPagoTralasdo();
		medioPago.setImporte(new BigDecimal(100));
		listaMedioPago.add(medioPago);
		medioPago = new MedioPagoTralasdo();
		medioPago.setImporte(new BigDecimal(500));
		listaMedioPago.add(medioPago);
		medioPago = new MedioPagoTralasdo();
		medioPago.setImporte(new BigDecimal(600));
		listaMedioPago.add(medioPago);
		eEntrada.setListaMedioPago(listaMedioPago);
		
		IClienteCalipsoServicio clienteCalipsoServicio =  (IClienteCalipsoServicio) Configuracion.getBeanBatch("clienteCalipsoServicio");
		SalidaCalipsoApropiacionWS datos = 
				clienteCalipsoServicio.testApropiarCobro(eEntrada);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getIdOperacionTransaccion().toString());
			System.out.println(datos.getResultadoInvocacion().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
	public static void testConfirmacionCalipso() throws NegocioExcepcion {
		System.out.println("---------------- Confirmacion ----------------------");
		EntradaCalipsoConfirmacionWS eEntrada = new EntradaCalipsoConfirmacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_CONFIRMACION);
		
		eEntrada.setIdOperacion(new BigInteger("0"));
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		List<DetalleFactura> listaFacturasAConfirmar = new ArrayList<DetalleFactura>();
		DetalleFactura detalleFac = new DetalleFactura();
		detalleFac.setIdCobranza(new BigInteger("286588"));
		listaFacturasAConfirmar.add(detalleFac);
		eEntrada.setListaFacturasAConfirmar(listaFacturasAConfirmar);
		
		List<DetalleCTAoNotaCredito> listaCtaONotaCreditoAConfirmar = new ArrayList<DetalleCTAoNotaCredito>();
		DetalleCTAoNotaCredito detalleNC = new DetalleCTAoNotaCredito();
		detalleNC.setIdCobranza(new BigInteger("2"));
		listaCtaONotaCreditoAConfirmar.add(detalleNC);
		detalleNC = new DetalleCTAoNotaCredito();
		detalleNC.setIdCobranza(new BigInteger("3"));
		listaCtaONotaCreditoAConfirmar.add(detalleNC);
		eEntrada.setListaCtaONotaCreditoAConfirmar(listaCtaONotaCreditoAConfirmar);
	
		IClienteCalipsoServicio clienteCalipsoServicio =  (IClienteCalipsoServicio) Configuracion.getBeanBatch("clienteCalipsoServicio");
		SalidaCalipsoConfirmacionWS datos = 
				clienteCalipsoServicio.testConfirmarCobro(eEntrada);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getIdOperacion().toString());
			System.out.println(datos.getResultadoInvocacion().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
	
	public static void testDesapropiacionCalipso() throws NegocioExcepcion {
		System.out.println("-------------- Desapropiacion ------------------------");
		EntradaCalipsoDesapropiacionWS eEntrada = new EntradaCalipsoDesapropiacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_DESAPROPIACION);
		
		eEntrada.setIdOperacion(new BigInteger("0"));
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		List<DetalleFactura> listaFacturasADesapropiar = new ArrayList<DetalleFactura>();
		DetalleFactura detalleFac = new DetalleFactura();
		detalleFac.setIdCobranza(new BigInteger("287098"));
		listaFacturasADesapropiar.add(detalleFac);
		eEntrada.setListaFacturasADesapropiar(listaFacturasADesapropiar);
		
		IClienteCalipsoServicio clienteCalipsoServicio =  (IClienteCalipsoServicio) Configuracion.getBeanBatch("clienteCalipsoServicio");
		SalidaCalipsoDesapropiacionWS datos = 
				clienteCalipsoServicio.testDesapropiarOperacion(eEntrada);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getIdOperacion().toString());
			System.out.println(datos.getResultadoInvocacion().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
}
