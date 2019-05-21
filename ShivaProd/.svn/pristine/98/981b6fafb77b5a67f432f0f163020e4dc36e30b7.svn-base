package ar.com.telecom.shiva.test.util;

import java.math.BigDecimal;
import java.util.Formatter;

import junit.framework.TestCase;

import org.junit.Test;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;


public class ValidacionesTest extends TestCase {
	
	
	@SuppressWarnings("resource")
	@Test
	public void testValidaciones(){
		//byte[] array = null;
		//ByteArrayInputStream bis = new ByteArrayInputStream(array);
		//String cadena = new String(array);
		
		byte[] array = "".getBytes();
		String cadena = new String(array);
		
		byte[] array1 = "leo".getBytes();
		String cadena1 = new String(array1);
		
		String alfanumerico = "funcionanLetras Y numeros9234";
		String caracteres = "@!#$%¿?¡.:,;+-_()[]{}'";
		String acentuacion = "íÓú";
		String dieresis = "Ääöü";
		String vacio = "";
		String emailCorrecto = "a@a.com";
		String emailCorrecto2 = "a@a.com.ar";
		String email1 = "@a.com";
		String email2 = "a@.com";
		String email3 = "a@acom";
		String email4 = "a@a.";
		String email5 = "a a@a.com";
		String numeros = "12345";
		String numeros2 = "12345678";
		String numeros3 = "1234,5678";
		String numeros4 = "123456,78";
		String numeros5 = "123.456,78";
		String numeros6 = "12.3456,78";
		String numeros7 = "1234567,8";
		String numeros8 = "12345678,";
		
		
		BigDecimal d1 = new BigDecimal("2.0");
		BigDecimal d2 = new BigDecimal("2.00");
		System.out.println(d1.compareTo(d2)); //Son iguales
		
		String idLegado = "97033";
		String legado = (new Formatter().format("%010d", new Long(idLegado))).toString();
		System.out.println("legado: " + legado);
		legado = Utilidad.rellenarCerosIzquierda(idLegado, 10);
		System.out.println("legado formateado: " + legado);
		
		System.out.println("-------------TEXTO--------------");
		assertTrue("Cadena con array vacia",Validaciones.isNullOrEmpty(cadena));
		assertFalse("Cadena con array", Validaciones.isNullOrEmpty(cadena1));
		
		assertTrue("alfanumerico no funciona",Validaciones.esFormatoTexto(alfanumerico));
		assertTrue("caracteres no funciona",Validaciones.esFormatoTexto(caracteres));
		assertTrue("acentuacion no funciona",Validaciones.esFormatoTexto(acentuacion));
		assertTrue("dieresis no funciona",Validaciones.esFormatoTexto(dieresis));
		assertTrue("null no funciona",Validaciones.esFormatoTexto(vacio));
		System.out.println("----------FIN---TEXTO------------");

		System.out.println("-------------ALFANUMERICO---------------");
		assertTrue("alfanumerico no funciona",Validaciones.isNullOrAlphaNumeric(alfanumerico));
		assertTrue("null no funciona",Validaciones.isNullOrAlphaNumeric(vacio));
		assertFalse("caracteres no funciona",Validaciones.isNullOrAlphaNumeric(caracteres));
		assertTrue("vacio no funciona",Validaciones.isNullOrAlphaNumeric(vacio));
		assertTrue("null no funciona",Validaciones.isNullOrAlphaNumeric(null));
		System.out.println("----------FIN---ALFANUMERICO------------");
		
		System.out.println("-------------EMAIL------------");
		assertFalse("alfanumerico no funciona",Validaciones.isNullOrEmail(alfanumerico));
		assertTrue("emailCorrecto no funciona",Validaciones.isNullOrEmail(emailCorrecto));
		assertTrue("emailCorrecto2 no funciona",Validaciones.isNullOrEmail(emailCorrecto2));
		assertFalse("email1 no funciona",Validaciones.isNullOrEmail(email1));
		assertFalse("email2 no funciona",Validaciones.isNullOrEmail(email2));
		assertFalse("email3 no funciona",Validaciones.isNullOrEmail(email3));
		assertFalse("email4 no funciona",Validaciones.isNullOrEmail(email4));
		assertFalse("email5 no funciona",Validaciones.isNullOrEmail(email5));
		assertTrue("null no funciona",Validaciones.isNullOrEmail(null));
		System.out.println("----------FIN---EMAIL------------");
		
		System.out.println("------------NUMERO---------------");
		assertTrue("numeros no funciona",Validaciones.isNumeric(numeros, 1, 5));
		assertFalse("numeros2 no funciona",Validaciones.isNumeric(numeros2, 1, 5));
		assertFalse("null no funciona",Validaciones.isNumeric(vacio));
		assertFalse("null no funciona",Validaciones.isNumeric(vacio,1,5));
		System.out.println("----------FIN---NUMERO-----------");

		System.out.println("------------DECIMALES-------------");
		assertTrue("numeros no funciona",Validaciones.isNumericDecimal(numeros, 2));
		assertFalse("numeros3 no funciona",Validaciones.isNumericDecimal(numeros3, 2));
		assertTrue("numeros3-2 no funciona",Validaciones.isNumericDecimal(numeros3, 4));
		assertTrue("numeros4 no funciona",Validaciones.isNumericDecimal(numeros4, 2));
		assertTrue("numeros5 no funciona",Validaciones.isNumericDecimal(numeros5, 2));
		assertFalse("numeros6 no funciona",Validaciones.isNumericDecimal(numeros6, 2));
		assertTrue("numeros7 no funciona",Validaciones.isNumericDecimal(numeros7, 2));
		assertFalse("numeros8 no funciona",Validaciones.isNumericDecimal(numeros8, 2));

		System.out.println("------------DECIMALES-2------------");
		assertTrue("numeros no funciona",Validaciones.isNumericDecimal(numeros));
		assertTrue("numeros3 no funciona",Validaciones.isNumericDecimal(numeros3));
		assertTrue("numeros4 no funciona",Validaciones.isNumericDecimal(numeros4));
		assertTrue("numeros5 no funciona",Validaciones.isNumericDecimal(numeros5));
		assertFalse("numeros6 no funciona",Validaciones.isNumericDecimal(numeros6));
		assertTrue("numeros7 no funciona",Validaciones.isNumericDecimal(numeros7));
		assertFalse("numeros8 no funciona",Validaciones.isNumericDecimal(numeros8));
		System.out.println("---------FIN---DECIMALES----------");
	
	}
}
