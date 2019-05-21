package ar.com.telecom.shiva.test.soa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMoraEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WSCalipsoTest extends SoporteContextoSpringTest{

	@Autowired 
	IClienteCalipsoServicio clienteCalipsoServicio;
	
	/**
	 * 
	  		<idShiva>0001735</idShiva>
			<idTransaccion>00001</idTransaccion>
			<usuario>SHIVA</usuario>
			<modoOperacion>S</modoOperacion>
			<detFac>
				<idDocctascob>
					<tipo>FAC</tipo>
					<clase>A</clase>
					<sucursal>0308</sucursal>
					<numero>00181116</numero>
				</idDocctascob>
				<saldo>200</saldo>
				<fechaCob>2015/06/19</fechaCob>
				<tipoOperacion>P</tipoOperacion>
				<tipoMora>SIMULAR</tipoMora>
				<algoritmoMora />
				<importeBonificacionIntereses>0</importeBonificacionIntereses>
				<acuerdo>7804</acuerdo>
				<montoAcumuladoSimulacion>0</montoAcumuladoSimulacion>
			</detFac>
			<listaCtaCre>
				<detalle>
					<importe>200</importe>
					<idDocumento>0</idDocumento>
					<tipo>CTA</tipo>
					<clase>X</clase>
					<sucursal>0308</sucursal>
					<numero>00003984</numero>
				</detalle>
			</listaCtaCre>
	 * @throws NegocioExcepcion
	 */
	@Test
	public void testApropiacionCalipso() throws NegocioExcepcion {
		System.out.println("---------------- Apropiacion ----------------------");
		//try {
			EntradaCalipsoApropiacionWS eEntrada = new EntradaCalipsoApropiacionWS();
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP);
			
			eEntrada.setIdOperacion(new Long(5002113));
			eEntrada.setIdTransaccion(1);
			eEntrada.setNumeroTransaccion(1);
			eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			eEntrada.setModoOperacion(SiNoEnum.SI);
			
//			DetalleFactura detalleFactura = new DetalleFactura();
//			IdDocumento idDocumento = new IdDocumento();
//			idDocumento.setTipoComprobante(TipoComprobanteEnum.FAC);
//			idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.A);
//			idDocumento.setSucursalComprobante("0308");
//			idDocumento.setNumeroComprobante("00181116");
//			detalleFactura.setIdDocumento(idDocumento);
//			
//			detalleFactura.setMontoACancelarEnPesos(new BigDecimal("200"));
//			detalleFactura.setFechaCobranza(Utilidad.parseFechaBarraString("2015/06/19"));
//			detalleFactura.setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
//			detalleFactura.setTipoMora(TratamientoInteresesEnum.SI.getCodigoCalipsoApropiacion());
//			//detalleFactura.setAlgoritmoMora(AlgoritmoMoraEnum.CALCULO_LINEAL);
//			detalleFactura.setImporteBonificacionIntereses(new BigDecimal(0));
//			detalleFactura.setAcuerdoFacturacion("7804");
//			detalleFactura.setMontoAcumuladoSimulacion(new BigDecimal(0));
//			eEntrada.setDetalleFactura(detalleFactura);
			eEntrada.setDetalleFactura(null);
			
			List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();
			DetalleCTAoNotaCredito detalleCTA = new DetalleCTAoNotaCredito();
			detalleCTA.setImporte(new BigDecimal(432.32));
			IdDocumento idDocumento = new IdDocumento();
			idDocumento.setTipoComprobante(TipoComprobanteEnum.CRE);
			idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.B);
			idDocumento.setSucursalComprobante("0471");
			idDocumento.setNumeroComprobante("90000770");
			detalleCTA.setIdDocumento(idDocumento);
			listaCtaONotaCredito.add(detalleCTA);
			
			detalleCTA = new DetalleCTAoNotaCredito();
			detalleCTA.setImporte(new BigDecimal(203.5));
			idDocumento = new IdDocumento();
			idDocumento.setTipoComprobante(TipoComprobanteEnum.CRE);
			idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.B);
			idDocumento.setSucursalComprobante("0471");
			idDocumento.setNumeroComprobante("90000769");
			detalleCTA.setIdDocumento(idDocumento);
			listaCtaONotaCredito.add(detalleCTA);
			
			eEntrada.setListaCtaONotaCredito(listaCtaONotaCredito);
			
			SalidaCalipsoApropiacionWS datos = 
					//		clienteCalipsoServicio.apropiarCobro(eEntrada);
							clienteCalipsoServicio.testApropiarCobro(eEntrada);
			
			if (datos!=null) {
				System.out.println("resultado: ");
				System.out.println(datos.getIdOperacionTransaccion().toString());
				System.out.println(datos.getResultadoInvocacion().toString());
				
			} else {
				System.out.println("resultado: sin datos");
			}
			
			
		//} catch (ParseException e) {
		//	throw new WebServiceExcepcion(e);
		//}
		
		System.out.println("--------------------------------------");
	}
	
	//@Test
	public void testConfirmacionCalipso() throws NegocioExcepcion {
		System.out.println("---------------- Confirmacion ----------------------");
		EntradaCalipsoConfirmacionWS eEntrada = new EntradaCalipsoConfirmacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_CONFIRMACION);
		
		eEntrada.setIdOperacion(new BigInteger("176"));
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
		
		SalidaCalipsoConfirmacionWS datos = 
				//clienteCalipsoServicio.confirmarCobro(eEntrada);
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
	
	
	//@Test
	public void testDesapropiacionCalipso() throws NegocioExcepcion {
		System.out.println("-------------- Desapropiacion ------------------------");
		EntradaCalipsoDesapropiacionWS eEntrada = new EntradaCalipsoDesapropiacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_DESAPROPIACION);
		
		eEntrada.setIdOperacion(new BigInteger("176"));
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		List<DetalleFactura> listaFacturasADesapropiar = new ArrayList<DetalleFactura>();
		DetalleFactura detalleFac = new DetalleFactura();
		detalleFac.setIdCobranza(new BigInteger("287098"));
		listaFacturasADesapropiar.add(detalleFac);
		eEntrada.setListaFacturasADesapropiar(listaFacturasADesapropiar);
		
		//List<DetalleCTAoNotaCredito> listaCtaONotaCreditoADesapropiar = new ArrayList<DetalleCTAoNotaCredito>();
		//eEntrada.setListaCtaONotaCreditoADesapropiar(listaCtaONotaCreditoADesapropiar);
		
		SalidaCalipsoDesapropiacionWS datos = 
				//clienteCalipsoServicio.desapropiarOperacion(eEntrada);
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
	
	
	/**
	 * 
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:WSDL">
   <soapenv:Header/>
   <soapenv:Body>
      <urn:ConsultaDeudaFiltroCalipso>
         <ListaClientes>
            <!--1 or more repetitions:-->
            <IdClienteLegado>93015</IdClienteLegado>
            <IdClienteLegado>93018</IdClienteLegado>
         </ListaClientes>
         <IdDocumento>
            <TipoComprobante>TODOS</TipoComprobante>
            <ClaseComprobante>TODOS</ClaseComprobante>
            <SucursalComprobante></SucursalComprobante>
            <NumeroComprobante></NumeroComprobante>
         </IdDocumento>
         <FechaVencimientoDesde>19000101</FechaVencimientoDesde>
         <FechaVencimientoHasta>20160101</FechaVencimientoHasta>
         <Acuerdo>0</Acuerdo>
         <Moneda>TODOS</Moneda>
         <CantidadRegistrosARetornar>999</CantidadRegistrosARetornar>
         <UltimoIdDocCtasCob>100</UltimoIdDocCtasCob>
         <UltimaFechaDocumento>19000101</UltimaFechaDocumento>
         <UltimoClienteDocumento>93014</UltimoClienteDocumento>
      </urn:ConsultaDeudaFiltroCalipso>
   </soapenv:Body>
</soapenv:Envelope>
	 * @throws NegocioExcepcion
	 */
	//@Test
	public void testConsultaDebito() throws NegocioExcepcion {
		System.out.println("---------------- ConsultaDeuda ----------------------");
		
		EntradaCalipsoDeudaWS eEntrada = new EntradaCalipsoDeudaWS();
		Cliente cliente = new Cliente();
		cliente.setIdClienteLegado(new BigInteger("9032"));
		eEntrada.getListaClientes().add(cliente);
		
		//cliente = new Cliente();
		//cliente.setIdClienteLegado(new BigInteger("93018"));
		//eEntrada.getListaClientes().add(cliente);
		
		IdDocumento idDocumento = new IdDocumento();
		idDocumento.setTipoComprobante(TipoComprobanteEnum.TOD);
		//idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.A);
		eEntrada.setIdDocumento(idDocumento);
		
//		eEntrada.setFechaVencimientoDesde(Utilidad.deserializeAndFormatDate("19000101"));
//		eEntrada.setFechaVencimientoHasta(Utilidad.deserializeAndFormatDate("20160101"));
		eEntrada.setAcuerdo(new BigInteger("0"));
		eEntrada.setMoneda(MonedaEnum.TOD);
		
		eEntrada.getInformacionParaPaginado().setCantidadDeRegistrosARetornar(new BigInteger("80"));
		//eEntrada.getInformacionParaPaginado().setUltimoIdDocCtasCob(new BigInteger("0"));
		//eEntrada.getInformacionParaPaginado().setUltimaFechaDocumento(Utilidad.deserializeAndFormatDate("19000101"));
		//eEntrada.getInformacionParaPaginado().setUltimoClienteDocumento(new BigInteger("93014"));
		
		SalidaCalipsoDeudaWS datos = 
				clienteCalipsoServicio.consultaDebito(eEntrada);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getListaDebitos().size());
			System.out.println(datos.getControlPaginado().toString());
			System.out.println(datos.getResultadoProcesamiento().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
	/**
	 * 
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:WSDL">
   <soapenv:Header/>
   <soapenv:Body>
      <urn:ConsultaCreditoFiltroCalipso>
         <ListaClientes>
            <!--1 or more repetitions:-->
            <IdClienteLegado>93015</IdClienteLegado>
         </ListaClientes>
         <TipoMedioPago>CTA</TipoMedioPago>
         <FechaDesde></FechaDesde>
         <FechaHasta></FechaHasta>
         <Acuerdo></Acuerdo>
         <Moneda>TODOS</Moneda>
         <CantidadRegistrosARetornar>10</CantidadRegistrosARetornar>
         <UltimoIdDocCuentasCobranzaCTA></UltimoIdDocCuentasCobranzaCTA>
         <UltimaFechaCTA></UltimaFechaCTA>
         <UltimoClienteCTA></UltimoClienteCTA>
         <UltimoIdDocCuentasCobranzaNC></UltimoIdDocCuentasCobranzaNC>
         <UltimaFechaNC></UltimaFechaNC>
         <UltimaClienteNC></UltimaClienteNC>
      </urn:ConsultaCreditoFiltroCalipso>
   </soapenv:Body>
</soapenv:Envelope>
	 * @throws NegocioExcepcion
	 */
	//@Test
	public void testConsultaCredito() throws NegocioExcepcion {
		System.out.println("---------------- ConsultaCredito ----------------------");
		
		EntradaCalipsoCreditoWS eEntrada = new EntradaCalipsoCreditoWS();
		Cliente cliente = new Cliente();
		cliente.setIdClienteLegado(new BigInteger("93015"));
		eEntrada.getListaClientes().add(cliente);
		
		eEntrada.setTipoMedioPago(TipoMedioPagoEnum.CTA);
		eEntrada.setMoneda(MonedaEnum.TOD);
		
		eEntrada.getInformacionParaPaginado().setCantidadDeRegistrosARetornar(new BigInteger("10"));
		
		
		SalidaCalipsoCreditoWS datos = 
				clienteCalipsoServicio.consultaCredito(eEntrada);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getListaCreditos().size());
			System.out.println(datos.getControlPaginado().toString());
			System.out.println(datos.getResultadoProcesamiento().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
	/**
	 * 
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" />
       <soap:Body>
             <ns1:generacionCargo xmlns:ns1="urn:WSDL">
                    <idOperacionShiva>5001412</idOperacionShiva>
                    <idTransaccionShiva>6417</idTransaccionShiva>
                    <usuarioCobrador>SHIVA</usuarioCobrador>
                    <tipoOperacion>DESCUENTO</tipoOperacion>
                    <modoOperacion>S</modoOperacion>
                    <detalleCargo>
                           <acuerdo>4242</acuerdo>
                           <importe>2000</importe>
                           <fechaDesde>2015/08/18</fechaDesde>
                           <tipoMora>SIMULAR</tipoMora>
                           <origenCargo>05</origenCargo>
                           <leyendaFacturaCargo>Traslado saldo CHQ 10002000 (Op.5001412)</leyendaFacturaCargo>
                           <leyendaFacturaInteres>Int. Tras. saldo CHQ 10002000 (Op.5001412)</leyendaFacturaInteres>
                    </detalleCargo>
             </ns1:generacionCargo>
       </soap:Body>
</soap:Envelope>
	 */
	//@Test
	public void testGenerarCargo() throws NegocioExcepcion {
		System.out.println("---------------- GenerarCargo ----------------------");
		
		EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
		
		entrada.setIdOperacion("5001412");
		entrada.setIdTransaccion("6417");
		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		entrada.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
		entrada.setModoOperacion(SiNoEnum.SI);

		DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
		detalleCargo.setAcuerdoFacturacion("4242");
		detalleCargo.setImporte(new BigDecimal("2000")); 
		detalleCargo.setFechaDesde(Utilidad.deserializeAndFormatDate("20150818"));
		detalleCargo.setTipoMora(TipoMoraEnum.SIMULAR);
		detalleCargo.setOrigenCargo(OrigenCargoEnum.TRASLADO_SALDO_CHEQUE);
		detalleCargo.setLeyendaFacturaCargo("Traslado saldo CHQ 10002000 (Op.5001412)");
		detalleCargo.setLeyendaFacturaInteres("Int. Tras. saldo CHQ 10002000 (Op.5001412)");
		
		entrada.setDetalleCargo(detalleCargo);
		
		SalidaCalipsoCargosWS datos  
			= clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.COBRO);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getIdOperacionTransaccion());
			System.out.println(datos.getResultado().toString());
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
	
	
	/**
	 * 
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" />
       <soap:Body>
             <ns1:generacionCargo xmlns:ns1="urn:WSDL">
                    <idOperacionShiva>5001412</idOperacionShiva>
                    <idTransaccionShiva>6417</idTransaccionShiva>
                    <usuarioCobrador>SHIVA</usuarioCobrador>
                    <tipoOperacion>DESCUENTO</tipoOperacion>
                    <modoOperacion>S</modoOperacion>
                    <detalleCargo>
                           <acuerdo>4242</acuerdo>
                           <importe>2000</importe>
                           <fechaDesde>2015/08/18</fechaDesde>
                           <tipoMora>SIMULAR</tipoMora>
                           <origenCargo>05</origenCargo>
                           <leyendaFacturaCargo>Traslado saldo CHQ 10002000 (Op.5001412)</leyendaFacturaCargo>
                           <leyendaFacturaInteres>Int. Tras. saldo CHQ 10002000 (Op.5001412)</leyendaFacturaInteres>
                    </detalleCargo>
             </ns1:generacionCargo>
       </soap:Body>
</soap:Envelope>
	 */
	@Test
	public void testGenerarContraCargo() throws NegocioExcepcion {
		System.out.println("---------------- GenerarCargo ----------------------");
		
		EntradaCalipsoCargosWS entrada = new EntradaCalipsoCargosWS();
		
		entrada.setIdOperacion("5001412");
		entrada.setIdTransaccion("6417");
		entrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		entrada.setTipoOperacion(TipoOperacionCargoEnum.DESCUENTO);
		entrada.setModoOperacion(SiNoEnum.SI);

		DetalleCargoEntradaCargosWs detalleCargo = entrada.getDetalleCargo();
		detalleCargo.setAcuerdoFacturacion("4242");
		detalleCargo.setImporte(new BigDecimal("2000")); 
		detalleCargo.setFechaDesde(Utilidad.deserializeAndFormatDate("20150818"));
		detalleCargo.setTipoMora(TipoMoraEnum.SIMULAR);
		detalleCargo.setOrigenCargo(OrigenCargoEnum.TRASLADO_SALDO_CHEQUE);
		detalleCargo.setLeyendaFacturaCargo("Traslado saldo CHQ 10002000 (Op.5001412)");
		detalleCargo.setLeyendaFacturaInteres("Int. Tras. saldo CHQ 10002000 (Op.5001412)");
		
		entrada.setDetalleCargo(detalleCargo);
		
		SalidaCalipsoCargosWS datos  
			= clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.COBRO);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getIdOperacionTransaccion());
			System.out.println(datos.getResultado().toString());
		} else {
			System.out.println("resultado: sin datos");
		}
		System.out.println("--------------------------------------");
	}
}
