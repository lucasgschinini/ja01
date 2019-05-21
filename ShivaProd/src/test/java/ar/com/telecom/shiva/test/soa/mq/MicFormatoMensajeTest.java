package ar.com.telecom.shiva.test.soa.mq;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicRespuestaRecepcionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPago;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaRecepcionSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/context/spring-jms-mapeos.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MicFormatoMensajeTest {

	@Autowired 
	MapeadorJMS micADCMapeoJMS;
	
	@Autowired 
	MapeadorJMS micRespuestaADCMapeoJMS;
	
	@BeforeClass
    public static void setUpClass() throws Exception {
		System.setProperty(Constantes.PROPIEDAD_ENTORNO, Constantes.ENTORNO_LOCAL);
    }
	
	@Test
	public void test1_formatoMsgMicADCSalidaTest() throws Exception {
		
		MicApropiacionDesapropiacionConfirmacionEntrada datosAEnviar = 
				new MicApropiacionDesapropiacionConfirmacionEntrada();
		
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$01);
		datosAEnviar.setModoEjecucion(SiNoEnum.NO);
		datosAEnviar.setIdOperacion(new Long(174));
		datosAEnviar.setIdSecuencia(new Integer(246));
		datosAEnviar.setUsuarioCobrador("SHIVA");
		
		datosAEnviar.getDetalleFactura().setTipoOperacion(TipoPagoEnum.PAGO_PARCIAL);
		datosAEnviar.getDetalleFactura().setReferenciaFactura("REF4872");
		datosAEnviar.getDetalleFactura().setFechaValor("20140321");
		datosAEnviar.getDetalleFactura().setAcuerdoFacturacionIntereses("acuerdo");
		datosAEnviar.getDetalleFactura().setImporte(new BigDecimal("154.4"));
		datosAEnviar.getDetalleFactura().setQueHacerConLosIntereses(TratamientoInteresesEnum.BM.name());
		datosAEnviar.getDetalleFactura().setQueHacerConLosTerceros(SiNoEnum.SI);

		MicDetalleMedioPago mp = new MicDetalleMedioPago();
		mp.setTipoMedioPago(TipoMedioPagoEnum.RT);
		mp.setImporte(new BigDecimal("1.26"));
		mp.setCuentaRemanente(new Long("1"));
		mp.setTipoRemanente(TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE);
		mp.setNumeroNC(new Long("4"));
		datosAEnviar.getListaMediosPago().add(mp);
		
		mp = new MicDetalleMedioPago();
		mp.setTipoMedioPago(TipoMedioPagoEnum.TC);
		mp.setImporte(new BigDecimal("4441.26"));
		mp.setCuentaRemanente(new Long("1"));
		mp.setTipoRemanente(TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE);
		mp.setNumeroNC(new Long("4"));
		datosAEnviar.getListaMediosPago().add(mp);
		
		mp = new MicDetalleMedioPago();
		mp.setTipoMedioPago(TipoMedioPagoEnum.NC);
		mp.setImporte(new BigDecimal("2221.26"));
		mp.setCuentaRemanente(new Long("1"));
		mp.setTipoRemanente(TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE);
		mp.setNumeroNC(new Long("4"));
		datosAEnviar.getListaMediosPago().add(mp);
		
		//Apropiacion de deuda
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$01);
		String mensajeAEnviar = micADCMapeoJMS.serializar(datosAEnviar, false);
		System.out.println("Apropiacion de deuda.................("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
		
		//Apropiacion de medio de pago
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$02);
		mensajeAEnviar = micADCMapeoJMS.serializar(datosAEnviar, false);
		System.out.println("Apropiacion de Medio de Pago.........("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
				
		//Apropiacion de deuda y medio de pago
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$03);
		mensajeAEnviar = micADCMapeoJMS.serializar(datosAEnviar, false);
		System.out.println("Apropiacion de deuda y medio de pago.("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
		
		//Desapropiacion
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$04);
		mensajeAEnviar = micADCMapeoJMS.serializar(datosAEnviar, false);
		System.out.println("Desapropiacion.......................("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
		
		//Confirmacion
		datosAEnviar.setTipoInvocacion(TipoInvocacionEnum.$05);
		mensajeAEnviar = micADCMapeoJMS.serializar(datosAEnviar, false);
		System.out.println("Confirmacion.........................("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
	}

	
	@Test
	public void test2_formatoMsgMicADCEntradaTest() throws Exception {
		
		String msgLength  = Utilidad.rellenarCerosIzquierda("0", 7);
		String codRetorno = Utilidad.rellenarEspaciosDerecha("7800", 4);
		String sqlCode    = Utilidad.rellenarEspaciosDerecha("", 10);
		String sqlErrml   = Utilidad.rellenarEspaciosDerecha("", 5);
		String sqlErrmc   = Utilidad.rellenarEspaciosDerecha("", 70);
		String ultRegProc = Utilidad.rellenarEspaciosDerecha("", 100);
		String cantRegistros = Utilidad.rellenarCerosIzquierda("0", 5);
		
		String idOperacion   = Utilidad.rellenarCerosIzquierda("174", 7);
		String idTransaccion = Utilidad.rellenarCerosIzquierda("246", 5);
		String idOperacionTransaccion = idOperacion+"."+idTransaccion;
		
		String mensajeRecibido =  
				Utilidad.rellenarEspaciosDerecha(Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio"),  
				micADCMapeoJMS.getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableAsync())
				+ msgLength+codRetorno+sqlCode+sqlErrml+sqlErrmc+ultRegProc+cantRegistros;
		mensajeRecibido += idOperacionTransaccion;
		
		System.out.println("Mensaje Recibido.....................("
				+mensajeRecibido.length()+ "):" + mensajeRecibido);
		MicRespuestaRecepcionSalida entrada = 
				(MicRespuestaRecepcionSalida) micADCMapeoJMS.deserializar(mensajeRecibido, true, false);
		
		System.out.println("Mensaje Deserializado:" + entrada.toString());
		
		
//		System.out.println("Prueba con ascii null:" + entrada.toString());
//		char ascii0 = 0; // Characters can be defined by their ASCII value
//	    char[] hello = ("Here comes a " + ascii0 + "new line.").toCharArray(); // Create a String, which contains an ASCII 0 and convert it to a char array
//	    System.out.println(new String(hello)); // Print it (just for checking)
//	 
//	    for(int i=0;i<hello.length;i++)
//	    {
//	        if(hello[i] == 0) hello[i] = '\n'; // If it finds a ASCII 0, it should replace it by a new line character
//	    }
//	    System.out.println("\n" + new String(hello)); // Print the result (just for checking)
	}
	
	@Test
	public void test3_formatoMsgMicRespuestaSalidaTest() throws Exception {
		
		MicRespuestaRecepcionEntrada salida = new MicRespuestaRecepcionEntrada();
		
		String idOperacion   = Utilidad.rellenarCerosIzquierda("174", 7);
		String idTransaccion = Utilidad.rellenarCerosIzquierda("246", 5);
		String idOperacionTransaccion = idOperacion+"."+idTransaccion;
		salida.setIdOperacionTransaccion(idOperacionTransaccion);
		
		MicResultado resultado = new MicResultado();
		resultado.setResultadoInvocacion(TipoResultadoEnum.OK.getDescripcionMic());
		salida.setResultado(resultado);
		
		String mensajeAEnviar = micRespuestaADCMapeoJMS.serializar(salida, false);
		System.out.println("Servicio de Respuesta: Respuesta ....("
				+mensajeAEnviar.length()+"):"  + mensajeAEnviar);
	}

	
	@Test
	public void test4_formatoMsgMicRespuestaEntradaTest() throws Exception {
		String idOperacion   = Utilidad.rellenarCerosIzquierda("174", 7);
		String idTransaccion = Utilidad.rellenarCerosIzquierda("246", 5);
		
		
		String msgLength  = Utilidad.rellenarCerosIzquierda("0", 7);
		String codRetorno = Utilidad.rellenarEspaciosDerecha("7800", 4);
		String sqlCode    = Utilidad.rellenarEspaciosDerecha("", 10);
		String sqlErrml   = Utilidad.rellenarEspaciosDerecha("", 5);
		String sqlErrmc   = Utilidad.rellenarEspaciosDerecha("", 70);
		String ultRegProc = Utilidad.rellenarEspaciosDerecha("", 100);
		String cantRegistros = Utilidad.rellenarCerosIzquierda("0", 5);
		String idOperacionTransaccionRetorno = idOperacion+"."+idTransaccion;
		String retorno = msgLength+codRetorno+sqlCode+sqlErrml+sqlErrmc+ultRegProc+cantRegistros;
		
		String idOperacionTransaccion = idOperacion+"."+idTransaccion;
		
		String tipoInvocacion = Utilidad.rellenarCerosIzquierda("01", 2);
		String idCobranza = Utilidad.rellenarCerosIzquierda("22", 12);
		
		String resultadoInvocacion = Utilidad.rellenarEspaciosDerecha("OK", 2);
		String codError = Utilidad.rellenarEspaciosDerecha("", 4);
		String descError = Utilidad.rellenarEspaciosDerecha("", 100);
		
		String intG = Utilidad.rellenarCerosIzquierda("50", 10);
		String intBR = Utilidad.rellenarCerosIzquierda("1540", 10);
		String intBNR = Utilidad.rellenarCerosIzquierda("0", 10);

		String numRef = Utilidad.rellenarCerosIzquierda("154", 15);
		String tipoComprobante = Utilidad.rellenarEspaciosDerecha("", 3);
		String claseComprobante = Utilidad.rellenarEspaciosDerecha("", 1);
		String sucursal = Utilidad.rellenarEspaciosDerecha("", 4);
		String numero = Utilidad.rellenarEspaciosDerecha("", 8);
		String fechaVencimiento = Utilidad.rellenarCerosIzquierda("20140328", 8);
		
		String mensajeRecibido = 
				Utilidad.rellenarEspaciosDerecha(Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio"),  
						micRespuestaADCMapeoJMS.getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableAsync())
				+ retorno + idOperacionTransaccionRetorno
				+ idOperacionTransaccion + tipoInvocacion
				+ idCobranza + resultadoInvocacion + codError + descError
				+ intG + intBR + intBNR + numRef + tipoComprobante 
				+ claseComprobante + sucursal + numero + fechaVencimiento;
		
		//Ejemplo de un mensaje
		mensajeRecibido = "CRASYNC                                                                                                                                 00004167800                                                                                                                                                                                         000005000227.000015000227.0000101240283009163OKK072FACTURA APROPIADA                                                                                   000000000000000057320000000000000000000000000                00000000";
		
		System.out.println("Mensaje Recibido.....................("
				+mensajeRecibido.length()+ "):" + mensajeRecibido);
		MicRespuestaADCSalida entrada = 
				(MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensajeRecibido, true, false);
		System.out.println("Mensaje Deserializado:" + entrada.toString());
	}
	
	/*******************************************************************
	 * Getters & Setters
	 ********************************************************************/
	
	
	public MapeadorJMS getMicADCMapeoJMS() {
		return micADCMapeoJMS;
	}

	public void setMicADCMapeoJMS(MapeadorJMS micADCMapeoJMS) {
		this.micADCMapeoJMS = micADCMapeoJMS;
	}


	public MapeadorJMS getMicRespuestaADCMapeoJMS() {
		return micRespuestaADCMapeoJMS;
	}


	public void setMicRespuestaADCMapeoJMS(MapeadorJMS micRespuestaADCMapeoJMS) {
		this.micRespuestaADCMapeoJMS = micRespuestaADCMapeoJMS;
	}
}
