package ar.com.telecom.shiva.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;

public class ValidacionesOM extends SoporteContextoSpringTest {
	private String lineaSinErroresDIST  = "D|0000000034|03|5004473|00001|500447300001|20150202|0000000000|0000000000|0000000000|0000000000|0000000000|0000000000|0000000000|0000000000|0000000000|000|        |00|                    | |                  |9220122940|S|    |        |922012294010081|00000000|0000000000|0000000000|0000000000|06|BAJA DEFIN| | |0000000000|0000000000|0000000000|  |                    | |          | |        |00000000|000|000|00000000|00000000|                              |  |             |00|    |00|               |00000000| |     |0000000000|0000000000|0000000000|0000000000|000|   |   | |    |        |000000000000000|0|                                        |0000000000|0000000000|00000000|00000000|00000000|00000000| |          | |        |  |                    |                              |  |             |00|    |00|               |0000000000|00000000| |     |0000000000|0000000000|0000000000|00|          |0000000000|NOK|S033      |LOS DOCUMENTOS EN ESTADO 'COBRADO' NO PUEDEN SER GESTIONADOS                                        |   |          |                                                                                                    |   |          | ";
//	@Test
//	public void validar() {
//		boolean salida = false;
//		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
//		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
//		salida = ValidacionesOM.validarParametrosGenerales(false, campos.split("\\|"));
//
//		System.out.println("..." + salida);
//		System.out.println(ValidarAuxiliarSingleton.getInstance().toString());
//	}
	@Test
	public void testParametrosGenerales() {
		boolean salida = true;
		String campos[] = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
		
		System.out.println("**************************[Linea 49] Sin errores");
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">--->" + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------");

		System.out.println("####");
		MicOperacionMasivaCamposEntradaEnum field = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO;
		System.out.println("**************************[Linea 56] Error campo " + field.getNombreColumna());
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] = "CABEZA";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 62] Error campo " + field.getNombreColumna());
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] = "";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 68] Error campo " + field.getNombreColumna());
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] = "0";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 74] Error campo " + field.getNombreColumna());
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] = " ";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");

		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA;
		System.out.println("**************************[Linea 83] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA.posicion()] = "0000000000";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 90] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA.posicion()] = "000000000A";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 97] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA.posicion()] = "0000000001";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 104] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA.posicion()] = "00000000011";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");
		System.out.println("**************************[Linea 111] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA.posicion()] = "000000001";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()] +"*");

		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION;
		System.out.println("**************************[Linea 121] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "05";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 128] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "051";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 135] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 142] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = " 5";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 149] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 156] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "A5";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 163] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 170] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "DESC";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_SHIVA;
		System.out.println("**************************[Linea 180] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 187] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000010";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 194] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000A0";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 201] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000010";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 208] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "      10";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 215] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION;
		System.out.println("**************************[Linea 225] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 232] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000A";
		salida = ValidacionesOM.validarParametrosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println(salida);
	}

	@Test // Datos de cobranza generales
	public void testCobrazaGenerales() {
		boolean salida = true;
		String campos[] = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().reIniciar();
		
		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
		
		System.out.println("**************************[Linea 251] Sin errores");
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">--->" + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------");

		System.out.println("####");
		MicOperacionMasivaCamposEntradaEnum field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA;
		System.out.println("**************************[Linea 258] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000000";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA;
		System.out.println("**************************[Linea 266] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000000000A";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 273] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000001";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 280] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "1";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_FECHA_VALOR_COBRANZA;
		System.out.println("**************************[Linea 290] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000000";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 297] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "20150278";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 304] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "27022015";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 311] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "270220150";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 318] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "2702201A";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 325] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "2702201";
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	}
	
	// Datos de cobranza apropiacion de deuda
	@Test 
	public void testCobrazaApropiacionDeDeuda() {
		boolean salida = true;
		String campos[] = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().reIniciar();
		
		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
		
		System.out.println("**************************[Linea 353] Sin errores");
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">--->" + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------");

		System.out.println("####");
		MicOperacionMasivaCamposEntradaEnum field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS;
		System.out.println("**************************[Linea 360] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 367] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 374] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 381] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 388] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS;
		System.out.println("**************************[Linea 397] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 404] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 411] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 418] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 425] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS;
		System.out.println("**************************[Linea 433] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 441] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 448] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 455] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 462] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS;
		System.out.println("**************************[Linea 471] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 478] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 485] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 492] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 499] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	}
	
	// Datos de cobranza generacion de cargo
	@Test 
	public void testCobrazaGeneracionCargo() {
		boolean salida = true;
		String campos[] = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().reIniciar();
		
		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
		
		System.out.println("**************************[Linea 518] Sin errores");
		salida = ValidacionesOM.validarCobranzaGenerales(aplica, campos);
		System.out.println(">--->" + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------");

		System.out.println("####");
		MicOperacionMasivaCamposEntradaEnum field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS;
		System.out.println("**************************[Linea 524] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 532] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 539] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 546] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 553] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS;
		System.out.println("**************************[Linea 561] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 569] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 576] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 583] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 590] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS;
		System.out.println("**************************[Linea 599] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 606] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 613] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 620] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 627] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS;
		System.out.println("**************************[Linea 636] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 643] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "000000000A";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 650] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "12358";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 657] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000.12";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 664] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarCobranzaApropiacionDeDeuda(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	}
	
	
	
	
	
	
	
	
	// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	@Test
	public void testDebitoImputadoDatosGeneralesNoDuc() {
		boolean salida = true;
		
		String campos[] = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().reIniciar();
		
		ValidarAuxiliarSingleton.getInstance().setCurrentfileName("SHIVA.OperacionesMasivas.MIC.Salida.20160329.txt");
		ValidarAuxiliarSingleton.getInstance().setNrolinea(1);
		
		System.out.println("**************************[Linea 682] Sin errores");
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">--->" + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------");

		System.out.println("####");
		MicOperacionMasivaCamposEntradaEnum field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUENTA;
		System.out.println("**************************[Linea 689] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00A";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 696] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 703] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "1_1";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 710] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "   ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO;
		System.out.println("**************************[Linea 719] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "DUC";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 726] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "XXX     ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 733] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "        ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
		System.out.println("**************************[Linea 742] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "02";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
		System.out.println("**************************[Linea 751] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "VENTA LINEAS        ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
		System.out.println("**************************[Linea 759] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "G";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 767] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "X ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
		System.out.println("**************************[Linea 776] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "GENERADO          ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ACUERDO;
		System.out.println("**************************[Linea 776] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 792] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "1         ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
		System.out.println("**************************[Linea 801] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 801] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "X";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 814] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "D ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 822] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "DX";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
		System.out.println("**************************[Linea 830] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
		System.out.println("**************************[Linea 840] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "X1025636695668s";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO;
		System.out.println("**************************[Linea 851] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "20150602";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO;
		System.out.println("**************************[Linea 861] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000,2214";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO;
		System.out.println("**************************[Linea 861] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000,2214";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO;
		System.out.println("**************************[Linea 861] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000,2214";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		System.out.println("####");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO;
		System.out.println("**************************[Linea 861] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "00000,2214";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION;
		System.out.println("**************************[Linea 900] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "05";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 908] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "1";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION;
		System.out.println("**************************[Linea 918] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "BAJA DEFIN ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS;
		System.out.println("**************************[Linea 918] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "YES";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");

		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA;
		System.out.println("**************************[Linea 918] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "S1";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA;
		System.out.println("**************************[Linea 918] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "S";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS;
		System.out.println("**************************[Linea 958] Error campo " + field.getNombreColumna());
		//campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0001235698";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS;
		System.out.println("**************************[Linea 968] Error campo " + field.getNombreColumna());
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000000000";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS;
		System.out.println("**************************[Linea 978] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000102030";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS;
		System.out.println("**************************[Linea 988] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "0000102030";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA;
		System.out.println("**************************[Linea 1000] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "01";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 1007] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "34";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 1015] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "34 ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 1022] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "X4";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA;
		System.out.println("**************************[Linea 1032] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "ENV. AL CLIENTE     ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA;
		System.out.println("**************************[Linea 1040] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "ENV. AL CLIENTE";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO;
		System.out.println("**************************[Linea 1050] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = " ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 1058] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "D";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		System.out.println("**************************[Linea 1064] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "D ";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO;
		System.out.println("**************************[Linea 1050] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "RESUELTO A";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ;
		System.out.println("**************************[Linea 1050] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "X";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
		
		System.out.println("###");
		field = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ;
		System.out.println("**************************[Linea 1050] Error campo " + field.getNombreColumna());
		campos = lineaSinErrores.split("\\|");
		ValidarAuxiliarSingleton.getInstance().clear();
		campos[field.posicion()] = "QUIEBRA";
		salida = ValidacionesOM.validarDebitoImputadoDatosGenerales(aplica, campos);
		System.out.println(">---> " + ValidarAuxiliarSingleton.getInstance().toString());
		System.out.println("-------------------------------------------------------" + campos[field.posicion()] + "*");
	}
	
	//Parametros generales del registro de salida
	public static boolean validarParametrosGenerales(boolean aplica, String[] campos) {
		boolean validado = true;

		
		
		// Tipo de Registro
		MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO;
		String campoValue = campos[campo.posicion()];
		
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (!TipoRegistroEnum.D.name().equals(campoValue.trim())) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorImagen"),
				String.valueOf(campoValue.trim()),
				String.valueOf(TipoRegistroEnum.D.name())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		// Id de Operación Masiva Shiva"
		campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA;
		campoValue = campos[campo.posicion()];
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (!Validaciones.isNumeric(campoValue)) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
				String.valueOf(campoValue)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
			validado  = false;
		}
		// Tipo de invocación
		campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION;
		campoValue = campos[campo.posicion()];
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
			validado  = false;
		}
		TipoArchivoOperacionMasivaEnum  tipoArchivoOperacionMasivaEnum = TipoArchivoOperacionMasivaEnum.getEnumByCodigo(campoValue);
		if (Validaciones.isObjectNull(tipoArchivoOperacionMasivaEnum)) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorImagen"),
				campoValue.trim(),
				TipoArchivoOperacionMasivaEnum.DEUDA.getCodigo() + "|" + 
					TipoArchivoOperacionMasivaEnum.DSIST.getCodigo() + "|" + 
					TipoArchivoOperacionMasivaEnum.REINT.getCodigo() + "|" +
					TipoArchivoOperacionMasivaEnum.GNCIA.getCodigo()
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		// Id de Operación de Shiva
		campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_SHIVA;
		campoValue = campos[campo.posicion()];
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (!Validaciones.isNumeric(campoValue)) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
				String.valueOf(campoValue)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
			ValidarAuxiliarSingleton.getInstance().setLinea(Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
			validado  = false;
		}
		//Id de secuencia de Transacción
		campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION;
		campoValue = campos[campo.posicion()];
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (!Validaciones.isNumeric(campoValue)) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
				String.valueOf(campoValue)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
			validado  = false;
		}
		return validado;
	}
		// Datos de cobranza generales
		public static boolean validarCobranzaGenerales(boolean aplica, String[] campos) {
			boolean validado = true;

			// Id de Cobranza
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA;
			String campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
					String.valueOf(campoValue)
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			// Fecha valor de la cobranza
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_FECHA_VALOR_COBRANZA;
			campoValue = campos[campo.posicion()];

			if (!validarCampoFecha(campo, campoValue, campos)) {
				validado = false;
			}
		
			return validado;
		}
		
		public static boolean verificarLongitud(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				return false;
			}
			return true;
		}
		public static boolean validarCampoFecha(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			
			try{
				if (Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campoValue))) {
					throw new ParseException("",0);
				}
			} catch (ParseException e){
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorFecha"), 
						campoValue
					);
				ValidarAuxiliarSingleton.getInstance().setLinea(mensajeError);
				validado  = false;
			}
			return validado;
		}
		public static boolean verificarCampoNumerico(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;

			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
					String.valueOf(campoValue)
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
//			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
//				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
//				validado  = false;
//			}
			return validado;
		}
		public static boolean validarCampoSiNo(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;

			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (
				!SiNoEnum.SI.getDescripcionCorta().equals(campoValue.trim()) &&
				!SiNoEnum.NO.getDescripcionCorta().equals(campoValue.trim())
			) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						SiNoEnum.SI.getDescripcionCorta() + " | " + SiNoEnum.NO.getDescripcionCorta()
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			return validado;
		}
		// Datos de cobranza apropiacion de deuda
		public static boolean validarCobranzaApropiacionDeDeuda(boolean aplica, String[] campos) {
			boolean validado = true;

			if (aplica) {
				// Intereses trasladados regulados
				MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS;
				String campoValue = campos[campo.posicion()];
	
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
	
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS;
				campoValue = campos[campo.posicion()];
				
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS;
				campoValue = campos[campo.posicion()];
				
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS;
				campoValue = campos[campo.posicion()];
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				
			}
			return validado;
		}
		
		//Datos de cobranza generacion de cargo
		public static boolean validarCobranzaGeneracionCargo(boolean aplica, String[] campos) {
			boolean validado = true;

			if (aplica) {
				// Intereses trasladados regulados
				MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS;
				String campoValue = campos[campo.posicion()];
		
				validado = verificarCampoNumerico(campo, campoValue, campos);
		
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS;
				campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
		
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS;
				campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
				
				campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS;
				campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
			} else {
				
			}
			return validado;
		}
		// Datos del debito imputado: cliente
		public static boolean validarDebitoImputadoCliente(boolean aplica, String[] campos) {
			boolean validado = true;

			if (aplica) {
				MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_CODIGO_CLIENTE;
				String campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
			} else {
				
			}
			return validado;
		}
		// Datos del debito imputado: cliente
				public static boolean validarCrediotAplicadoCliente(boolean aplica, String[] campos) {
					boolean validado = true;

					if (aplica) {
						MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.CODIGO_CLIENTE_CREDITO_APLICADO;
						String campoValue = campos[campo.posicion()];
						validado = verificarCampoNumerico(campo, campoValue, campos);
					} else {
						
					}
					return validado;
				}
		// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
		public static boolean validarDebitoImputadoDatosGenerales(boolean aplica, String[] campos) {
			boolean validado = true;
			TipoComprobanteEnum tipoDocumento_18 = null;

			if (aplica) {
				//Cuenta
				MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUENTA;
				String campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
		
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO;
				campoValue = campos[campo.posicion()];
		
				// Tipo de Documento
				if (campo.longitud() != campoValue.length()) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
				campoValue = campoValue.trim();
				if (
					!TipoComprobanteEnum.CON.getValor().equals(campoValue) &&
					!TipoComprobanteEnum.DEB.getValor().equals(campoValue) &&
					!TipoComprobanteEnum.DUC.getValor().equals(campoValue) &&
					!TipoComprobanteEnum.FAC.getValor().equals(campoValue)
				) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							TipoComprobanteEnum.CON.getValor() + "|" + 
								TipoComprobanteEnum.DEB.getValor() + "|" + 
								TipoComprobanteEnum.DUC.getValor() + "|" +
								TipoComprobanteEnum.FAC.getValor()
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				} else {
					tipoDocumento_18 = TipoComprobanteEnum.getEnumByValor(campoValue);
				}
				
				// Codigo Tipo de DUC
				if (!Validaciones.isObjectNull(tipoDocumento_18) && TipoComprobanteEnum.DUC.equals(tipoDocumento_18)) {
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
					campoValue = campos[campo.posicion()];

					if (campo.longitud() != campoValue.length()) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
							String.valueOf(campoValue.length()),
							String.valueOf(campo.longitud())
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
					}
					
					TipoDUCEnum tipoDucEnum = null;
					tipoDucEnum = TipoDUCEnum.getEnumByCodigo(campoValue.trim());
					if (Validaciones.isObjectNull(tipoDucEnum)) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								TipoDUCEnum.getStringValues()
							);
					}
					// Descripcion Tipo de DUC... 20
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
					campoValue = campos[campo.posicion()];
					tipoDucEnum = null;
					if (campo.longitud() != campoValue.length()) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
							String.valueOf(campoValue.length()),
							String.valueOf(campo.longitud())
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
					}
					tipoDucEnum = TipoDUCEnum.getEnumByDescripcion(campoValue.trim());
					if (Validaciones.isObjectNull(tipoDucEnum)) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								TipoDUCEnum.getStringValues()
							);
					}
					// Codigo Estado del DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					campoValue = campoValue.trim();
					if (
						!EstadoDUCEnum.GENERADO.codigo().equals(campoValue.trim()) &&
						!EstadoDUCEnum.ENVIADO.codigo().equals(campoValue.trim()) &&
						!EstadoDUCEnum.VENCIDO.codigo().equals(campoValue.trim()) &&
						!EstadoDUCEnum.CONV_BAJA_INEX.codigo().equals(campoValue.trim())
					) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString(
									"error.operacionMasiva.validarCampos.mic.errorImagen"),
									campoValue.trim(),
									EstadoDUCEnum.GENERADO.codigo() + "|" + 
											EstadoDUCEnum.ENVIADO.codigo() + "|" + 
											EstadoDUCEnum.VENCIDO.codigo() + "|" +
											EstadoDUCEnum.CONV_BAJA_INEX.codigo() 
							);
							ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
							validado  = false;
					}
					// Descripción Estado del DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					campoValue = campoValue.trim();
					if (
						!EstadoDUCEnum.GENERADO.descripcion().equals(campoValue.trim()) &&
						!EstadoDUCEnum.ENVIADO.descripcion().equals(campoValue.trim()) &&
						!EstadoDUCEnum.VENCIDO.descripcion().equals(campoValue.trim()) &&
						!EstadoDUCEnum.CONV_BAJA_INEX.descripcion().equals(campoValue.trim())
					) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString(
									"error.operacionMasiva.validarCampos.mic.errorImagen"),
									campoValue.trim(),
									EstadoDUCEnum.GENERADO.descripcion() + "|" + 
											EstadoDUCEnum.ENVIADO.descripcion() + "|" + 
											EstadoDUCEnum.VENCIDO.descripcion() + "|" +
											EstadoDUCEnum.CONV_BAJA_INEX.descripcion() 
							);
							ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
							validado  = false;
					}
					
				} else {
					// Codigo Tipo de DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					
					// Descripcion Tipo de DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Codigo Estado del DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Descripción Estado del DUC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
				}
				// Acuerdo
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ACUERDO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!Validaciones.isObjectNull(tipoDocumento_18) && !TipoComprobanteEnum.DUC.equals(tipoDocumento_18)) {
					//Clase de Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					campoValue = campoValue.trim();
					if (
						!"A".equals(campoValue.trim()) &&
						!"B".equals(campoValue.trim()) &&
						!"D".equals(campoValue.trim()) &&
						!"S".equals(campoValue.trim())
					) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								"A / B / D (sin letra factura mixta) / S (cuando es sin letra)"
							);
							ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
							validado  = false;
					}
					// Sucursal del Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					if (!verificarCampoNumerico(campo, campoValue, campos)) {
						validado = false;
					}
					// Numero del Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					if (!verificarCampoNumerico(campo, campoValue, campos)) {
						validado = false;
					}
					// Numero de Referencia MIC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNumerico(campo, campoValue, campos)) {
						validado = false;
					}
				} else {
					//Clase de Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Sucursal del Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Numero del Comprobante
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Numero de Referencia MIC
					campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
					campoValue = campos[campo.posicion()];
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
				}	
				// Fecha Vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				
				if (!validarCampoFecha(campo, campoValue, campos)) {
					validado = false;
				}
				// Importe al 1er vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Importe al 2do vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Saldo al 1er vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Estado acuerdo Facturación
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campoValue = campoValue.trim();
				if (
					!EstadoAcuerdoFacturacionEnum.POTENCIAL.codigo().equals(campoValue.trim()) &&
					!EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(campoValue.trim()) &&
					!EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(campoValue.trim()) &&
					!EstadoAcuerdoFacturacionEnum.BAJA_DEFINITIVA.codigo().equals(campoValue.trim())
				) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							EstadoAcuerdoFacturacionEnum.POTENCIAL.codigo() + " | " +
							EstadoAcuerdoFacturacionEnum.ACTIVO.codigo() + " | " +
							EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo() + " | "+
							EstadoAcuerdoFacturacionEnum.BAJA_DEFINITIVA.codigo()
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
		
				// Descripcion Estado acuerdo Factuación
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				campoValue = campoValue.trim();
				if (
					!"POTENCIAL".equals(campoValue.trim()) &&
					!"ACTIVO".equals(campoValue.trim()) &&
					!"INCOMUNICADO".equals(campoValue.trim()) &&
					!"BAJA DEFIN".equals(campoValue.trim())
				) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							"POTENCIAL" + " - " +
							"ACTIVO" + " - " +
							"INCOMUNICADO" + " - "+
							"BAJA DEFIN"
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(mensajeError);
						validado  = false;
				}
				//validarCampoSiNo
				// Estado Conceptos de Terceros
				SiNoEnum conseptoTerceros = null;
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS;
				campoValue = campos[campo.posicion()];
				if (!validarCampoSiNo(campo, campoValue, campos)) {
					validado = false;
				} else {
					conseptoTerceros = SiNoEnum.getEnumByDescripcionCorta(campoValue.trim());
				}
				
				// Posibilidad Destransferencia
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA;
				campoValue = campos[campo.posicion()];
				if (!validarCampoSiNo(campo, campoValue, campos)) {
					validado = false;
				}
				
				// Importe 3eros Transferidos
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				
				// Importe 1er vencimiento con 3eros
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS;
				campoValue = campos[campo.posicion()];
				if (SiNoEnum.SI.equals(conseptoTerceros)) {
					if (!verificarCampoNumerico(campo, campoValue, campos)) {
						validado = false;
					}
				} else {
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
				}
				
				// Importe 2do vencimiento con 3eros
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Estado Factura
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicCodigo(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Estado Factura
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicDescripcion(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				//Codigo Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				List<MarcaReclamoEnum> lstMarca = new ArrayList<MarcaReclamoEnum>();
				lstMarca.add(MarcaReclamoEnum.SIN_MARCA);
				lstMarca.add(MarcaReclamoEnum.ANULADO);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_A);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_P);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_T);
				lstMarca.add(MarcaReclamoEnum.PENDIENTE);
				List<String> listaCodigo = new ArrayList<String>();
				List<String> listaDesc = new ArrayList<String>();
				StringBuffer leyenda = new StringBuffer();
				for (MarcaReclamoEnum m : lstMarca) {
					listaCodigo.add(m.codigo());
					if (MarcaReclamoEnum.SIN_MARCA.equals(m)) {
						listaDesc.add(Utilidad.rellenarEspaciosDerecha("", 10));
					} else {
						listaDesc.add(m.descripcion());
					}
					leyenda.append(m.descripcion() + "(" + m.codigo() + ")");
				}
				if (!verificarCampoEnGrupo(true, listaCodigo, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
				
				//Descripcion Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, listaDesc, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getNames(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				//DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getDesc(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				//DEBITO_IMPUTADO_GRAL_FECHA_EMISION
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_EMISION;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoFecha(true, campo, campoValue, campos)) {
					validado = false;
				}
				// Número de Convenio
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Cuota
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUOTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha de ultimo pago parcial
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoFecha(false, campo, campoValue, campos)) {
					validado = false;
				}
				//DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoFecha(true, campo, campoValue, campos)) {
					validado = false;
				}
			} else {
			
			}
			return validado;
		}
		// Datos de respuesta generales: resultado de la invocación a nivel debito
		public static boolean validarRespuestasResultadoDebito(boolean aplica, String[] campos) {
			boolean validado = true;
			TipoResultadoEnum resultado = null;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = "";
			
			if (aplica) {
				//Resultado de la consulta
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];

				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				List<String> lista = new ArrayList<String>();
				lista.add(TipoResultadoEnum.NOK.name());
				lista.add(TipoResultadoEnum.OK.name());
				lista.add(TipoResultadoEnum.WRN.name());

				String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name();
				if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				} else {
					resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				}

				// Código de error 
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
				
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
		// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
				public static boolean validarDebitoDakota(boolean aplica, String[] campos) {
					
					boolean validado = true;
					MicOperacionMasivaCamposEntradaEnum campo = null;
					String campoValue = null;
					
					if (aplica) {
						// Tipo Cuenta
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
						campoValue = campos[campo.posicion()];
						if (!verificarCampoFecha(false, campo, campoValue, campos)) {
							validado = false;
						}
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_INDICADOR_PETICION_CORTE;
						campoValue = campos[campo.posicion()];
						if (!verificarLongitud(campo, campoValue, campos)) {
							validado = false;
						}
						
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_CODIGO_TARIFA;
						campoValue = campos[campo.posicion()];
						if (!verificarLongitud(campo, campoValue, campos)) {
							validado = false;
						}
					} else {
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
						campoValue = campos[campo.posicion()];
						if (!verificarCampoNulo(campo, campoValue, campos)) {
							validado = false;
						}
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_INDICADOR_PETICION_CORTE;
						campoValue = campos[campo.posicion()];
						if (!verificarCampoNulo(campo, campoValue, campos)) {
							validado = false;
						}
						campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_CODIGO_TARIFA;
						campoValue = campos[campo.posicion()];
						if (!verificarCampoNulo(campo, campoValue, campos)) {
							validado = false;
						}
					}
					return validado;
				}
		// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
		public static boolean validarSaldoTerceros(boolean aplica, String[] campos) {
			
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				// Tipo Cuenta
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINACIABLE_TRANSFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINACIABLE_TRANSFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
		// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
		public static boolean validarMedioPago(boolean aplica, String[] campos) {
			
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				// Tipo Cuenta
				campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_CUENTA;
				campoValue = campos[campo.posicion()];
		
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if ("CRE".equals(campoValue) || "REM".equals(campoValue)) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								"CRE | REM"
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
				}
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_CUENTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}			
			
		// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
		public static boolean validarNotaCredito(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				// Tipo de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_TIPO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if ("CRE".equals(campoValue)) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								"CRE"
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
				}
				// Clase de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_CLASE_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				
				List<String>lista = new ArrayList<String>();
				lista.add("A");
				lista.add("B");
				lista.add("S");
				String leyenda = "A | B | S (Vacio)";
 				// Sucursal del Comprobante
				if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				}
				
				campo = MicOperacionMasivaCamposEntradaEnum.NC_SUCURSAL_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Nro de Referencia MIC
				campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_REFERENCIA_MIC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// Tipo de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_TIPO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Clase de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_CLASE_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Sucursal del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_SUCURSAL_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Nro de Referencia MIC
				campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_REFERENCIA_MIC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
		
		// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)

		public static boolean validarCreditoRemanete(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				// Codigo de Tipo de Remanente
				campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_CODIGO_TIPO;
				campoValue = campos[campo.posicion()];
				List<String> lista = new ArrayList<String>();
				lista.add("1");
				lista.add("2");
				String leyenda = "1 o 2";
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(false, lista, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion del Tipo de Remanente
				campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_DESCRIPCION_TIPO;
				campoValue = campos[campo.posicion()];
			
				lista.clear();
				lista.add("TRANSFERIBLE NO ACTUALIZABLE");
				lista.add("NO TRANSFERIBLE NO ACTUALIZABLE");
				
				leyenda = "TRANSFERIBLE NO ACTUALIZABLE o NO TRANSFERIBLE NO ACTUALIZABLE";
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(false, lista, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// Codigo de Tipo de Remanente
				campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_CODIGO_TIPO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion del Tipo de Remanente
				campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_DESCRIPCION_TIPO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
			
		// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
		
		public static boolean validarCreditoDatosGenerales(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				// importe
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_IMPORTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Saldo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_SALDO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				String tipo = campos[MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO.posicion()];
				
				
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ALTA;
				campoValue = campos[campo.posicion()];
				
				// Fecha alta
				if ("REM".equals(tipo)) {
					if (!verificarCampoFecha(true, campo, campoValue, campos)) {
						validado = false;
					}
				} else {
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
				}
				if (!"REM".equals(tipo)) { 
					// Fecha de emisión
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoFecha(true, campo, campoValue, campos)) {
						validado = false;
					}
					// Fecha vencimiento
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoFecha(true, campo, campoValue, campos)) {
						validado = false;
					}
					// Fecha último movimiento cobro (PP)
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoFecha(false, campo, campoValue, campos)) {
						validado = false;
					}
					// Codigo Marca Reclamo
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
					campoValue = campos[campo.posicion()];
					
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					
					List<MarcaReclamoEnum> lstMarca = new ArrayList<MarcaReclamoEnum>();
					lstMarca.add(MarcaReclamoEnum.SIN_MARCA);
					lstMarca.add(MarcaReclamoEnum.ANULADO);
					lstMarca.add(MarcaReclamoEnum.RESUELTO_A);
					lstMarca.add(MarcaReclamoEnum.RESUELTO_P);
					lstMarca.add(MarcaReclamoEnum.RESUELTO_T);
					lstMarca.add(MarcaReclamoEnum.PENDIENTE);
					List<String> listaCodigo = new ArrayList<String>();
					List<String> listaDesc = new ArrayList<String>();
					
					StringBuffer leyenda = new StringBuffer();
					for (MarcaReclamoEnum m : lstMarca) {
						listaCodigo.add(m.codigo());
						if (MarcaReclamoEnum.SIN_MARCA.equals(m)) {
							listaDesc.add(Utilidad.rellenarEspaciosDerecha("", 10));
						} else {
							listaDesc.add(m.descripcion());
						}
						leyenda.append(m.descripcion() + "(" + m.codigo() + ")");
					}
					if (!verificarCampoEnGrupo(true, listaCodigo, leyenda.toString(), campo, campoValue, campos)) {
						validado = false;
					}
					
					// Descripcion Marca Reclamo
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
					campoValue = campos[campo.posicion()];
					
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					if (!verificarCampoEnGrupo(true, listaDesc, leyenda.toString(), campo, campoValue, campos)) {
						validado = false;
					}
				
					// Codigo Marca C&Q
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
					campoValue = campos[campo.posicion()];
					
					
					
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getNames(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
						validado = false;
					}
					
					
					
					
					
					
					// Descripcion Marca C&Q
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
					campoValue = campos[campo.posicion()];
					
					if (!verificarLongitud(campo, campoValue, campos)) {
						validado = false;
					}
					if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getDesc(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
						validado = false;
					}
				} else {
					// Fecha de emisión
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					
					// Fecha vencimiento
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Fecha último movimiento cobro (PP)
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Codigo Marca Reclamo
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Descripcion Marca Reclamo
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
					campoValue = campos[campo.posicion()];
					
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Codigo Marca C&Q
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					// Descripcion Marca C&Q
					campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
					campoValue = campos[campo.posicion()];
					if (!verificarCampoNulo(campo, campoValue, campos)) {
						validado = false;
					}
					
				}
				//Codigo Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicCodigo(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicDescripcion(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// importe
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_IMPORTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Saldo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_SALDO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha alta
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ALTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha de emisión
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha último movimiento cobro (PP)
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				//Codigo Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			
			return validado;
		}
		// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
		public static boolean validarCreditoAplicadoTagetik(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			List<String> lista = new ArrayList<String>();
			String leyenda = "";

			if (aplica) {
				// Razón social cliente
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Tipo de Cliente
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_CLIENTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// CUIT
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CUIT;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Ciclo de Facturación
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CICLO_FACTURACION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Marketing Customer Group.
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Tipo de Factura
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_FACTURA;
				campoValue = campos[campo.posicion()];
				
				lista = new ArrayList<String>();
				StringBuffer str = new StringBuffer();
				
				lista.add(TipoFacturaEnum.FACT_FLEXCAB.descripcion());
				str.append(TipoFacturaEnum.FACT_FLEXCAB.codigo() + " " + TipoFacturaEnum.FACT_FLEXCAB.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.FACT_BAJAS.descripcion());
				str.append(TipoFacturaEnum.FACT_BAJAS.codigo() + " " + TipoFacturaEnum.FACT_BAJAS.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.FACT_POST_BAJ.descripcion());
				str.append(TipoFacturaEnum.FACT_POST_BAJ.codigo() + " " + TipoFacturaEnum.FACT_POST_BAJ.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.FACT_INIC_ESP.descripcion());
				str.append(TipoFacturaEnum.FACT_INIC_ESP.codigo() + " " + TipoFacturaEnum.FACT_INIC_ESP.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
				str.append(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo() + " " + TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.INTERES_RECARGO.descripcion());
				str.append(TipoFacturaEnum.INTERES_RECARGO.codigo() + " " + TipoFacturaEnum.INTERES_RECARGO.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.DESCUENTOS.descripcion());
				str.append(TipoFacturaEnum.DESCUENTOS.codigo() + " " + TipoFacturaEnum.DESCUENTOS.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.DERECHO_REHABIL.descripcion());
				str.append(TipoFacturaEnum.DERECHO_REHABIL.codigo() + " " + TipoFacturaEnum.DERECHO_REHABIL.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
				str.append(TipoFacturaEnum.ND_INICIAL_ESP.codigo() + " " + TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
				str.append(TipoFacturaEnum.DESC_F_INIC_ESP.codigo() + " " + TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.FACTURA_ONLINE.descripcion());
				str.append(TipoFacturaEnum.FACTURA_ONLINE.codigo() + " " + TipoFacturaEnum.FACTURA_ONLINE.descripcion());
				str.append("|");
				lista.add(TipoFacturaEnum.NC_ONLINE.descripcion());
				str.append(TipoFacturaEnum.NC_ONLINE.codigo() + " " + TipoFacturaEnum.NC_ONLINE.descripcion());
				
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				}
				
				// Descripción Tipo de Factura
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
				campoValue = campos[campo.posicion()];
				lista = new ArrayList<String>();
				lista.add(String.valueOf(TipoFacturaEnum.FACT_FLEXCAB.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.FACT_BAJAS.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.FACT_POST_BAJ.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.FACT_INIC_ESP.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.INTERES_RECARGO.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.DESCUENTOS.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.DERECHO_REHABIL.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.ND_INICIAL_ESP.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.DESC_F_INIC_ESP.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.FACTURA_ONLINE.codigo()));
				lista.add(String.valueOf(TipoFacturaEnum.NC_ONLINE.codigo()));
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				
				if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				}
				
				// Importe Original
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_IMPORTE_ORIGINAL;
				campoValue = campos[campo.posicion()];
				
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// Razón social cliente
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Tipo de Cliente
				
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_CLIENTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// CUIT
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CUIT;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Ciclo de Facturación
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CICLO_FACTURACION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Marketing Customer Group.
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Tipo de Factura
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_FACTURA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripción Tipo de Factura
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				
				// Importe Original
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_IMPORTE_ORIGINAL;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
		// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
		public static boolean validarCreditoAplicadoDakota(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			List<String> lista = new ArrayList<String>();
			String leyenda = "";

			if (aplica) {
				// Fecha vencimiento mora
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
				campoValue = campos[campo.posicion()];

				if (!verificarCampoFecha(false, campo, campoValue, campos)) {
					validado = false;
				}
				// Indicador de Petición de Corte
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_INDICADOR_PETICION_CORTE;
				campoValue = campos[campo.posicion()];

				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Tarifa
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_CODIGO_TARIFA;
				campoValue = campos[campo.posicion()];

				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				
			} else {
				// Fecha vencimiento mora
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
				campoValue = campos[campo.posicion()];

				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Indicador de Petición de Corte
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_INDICADOR_PETICION_CORTE;
				campoValue = campos[campo.posicion()];

				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Tarifa
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_CODIGO_TARIFA;
				campoValue = campos[campo.posicion()];

				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
	
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
		public static boolean validarAcuerdoInteresPorMoraDatosGenerales(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			List<String> lista = new ArrayList<String>();
			String leyenda = "";
			
			if (aplica) {
				// Código de cliente
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_CLIENTE_MORA;
				campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
				// Acuerdo de Facturación de intereses / reintegro
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO;
				campoValue = campos[campo.posicion()];
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero de Linea
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_NUMERO_LINEA;
				campoValue = campos[campo.posicion()];
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Estado acuerdo
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO;
				campoValue = campos[campo.posicion()];
				if (verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				lista.add("00");
				lista.add("01");
				lista.add("02");
				lista.add("06");
				leyenda = "00 | 01 | 02 | 06";
				
				if (!verificarCampoEnGrupo(true, lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				}
				// escripcion Estado acuerdo
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				lista.clear();
				lista.add("POTENCIAL");
				lista.add("ACTIVO");
				lista.add("INCOMUNICA");
				lista.add("BAJA DEFIN");
				leyenda = " POTENCIAL | ACTIVO | INCOMUNICA (INCOMUNICADO) | BAJA DEFIN (BAJA DEFINITIVA)";
				if (!verificarCampoEnGrupo(true, lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// Código de cliente
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_CLIENTE_MORA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Acuerdo de Facturación de intereses / reintegro
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero de Linea
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_NUMERO_LINEA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Estado acuerdo
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// escripcion Estado acuerdo
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
		public static boolean validarAcuerdoInteresPorMoraCliente(boolean aplica, String[] campos) {
			boolean validado = true;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = null;
			
			if (aplica) {
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_CLIENTE_CODIGO_CLIENTE;
				campoValue = campos[campo.posicion()];
				validado = verificarCampoNumerico(campo, campoValue, campos);
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_CLIENTE_CODIGO_CLIENTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return validado;
		}

		
		public static boolean validarRespuestasResultadoCredito(boolean aplica, String[] campos) {
			boolean validado = true;
			TipoResultadoEnum resultado = null;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = "";
			
			if (aplica) {
				//Resultado de la consulta
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];

				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				List<String> lista = new ArrayList<String>();
				lista.add(TipoResultadoEnum.NOK.name());
				lista.add(TipoResultadoEnum.OK.name());
				lista.add(TipoResultadoEnum.WRN.name());

				String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name();
				if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				} else {
					resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				}

				// Código de error 
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
				
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			return  validado;
		}
		
		public static boolean validarRespuestasResultadoReintegro(boolean aplica, String[] campos) {
			boolean validado = true;
			TipoResultadoEnum resultado = null;
			MicOperacionMasivaCamposEntradaEnum campo = null;
			String campoValue = "";
			
			if (aplica) {
				//Resultado de la consulta
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];

				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				List<String> lista = new ArrayList<String>();
				lista.add(TipoResultadoEnum.NOK.name());
				lista.add(TipoResultadoEnum.OK.name());
				lista.add(TipoResultadoEnum.WRN.name());

				String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name();
				if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
					validado = false;
				} else {
					resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				}

				// Código de error 
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
				
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
		
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
			} else {
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_RESULTADO_CONSULTA;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_CODIGO_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_DESCRIPCION_ERROR;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			
			return validado;
		}
		public static boolean verificarCampoEnGrupo(List<String> grupo, String leyenda, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;
			if (!grupo.contains(campoValue.trim())) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						leyenda
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			return validado;
		}
		public static boolean verificarCampoEnGrupo(boolean conNulos, List<String> grupo, String leyenda, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;
			if (conNulos) {
				if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						return true;
					}
				} else {
					if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
						return true;
					}
				}
			}
			if (!grupo.contains(campoValue.trim())) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						leyenda
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			return validado;
		}
		
		public static boolean verificarCampoFecha(boolean obligatorio, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;

			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			if (!obligatorio) {
				if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
					return true;
				}
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
								campoValue);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado = false;
				}
			}
			try{
				if(Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campoValue))) {
					throw new ParseException("", 0);
				}
			} catch(ParseException e){
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
							"Fecha de Acta Desistimiento");
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			return validado;
		}
		public static boolean verificarCampoNulo(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
			boolean validado = true;

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
				if (!Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.empty"),
							campoValue,
							"ALFANUMERICO",
							Utilidad.rellenarEspaciosIzquierda(
								"",
								campo.longitud()
					));
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado = false;
				}
			} else {
				if (!Validaciones.isEmptyNumericoSerializado(campoValue)) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.empty"),
							campoValue,
							"ALFANUMERICO",
							Utilidad.rellenarEspaciosIzquierda(
							"",
							campo.longitud()
					));
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado = false;
				}
			}
			return validado;
		}
		
		
		private String lineaSinErroresDEUDA = "D|0000000935|01|0200347|00001|020034700001|20030805|0000001991|0000000000|0000002489|0000000000|0000000000|0000000000|0000000000|0000000000|0000009032|046|FAC     |00|                    | |                  |8750574680|S|7105|01664354|875057468010157|20020311|0000087677|0000087677|0000038547|06|BAJA DEFIN|N|N|0000000000|0000000000|0000000000|01|ENV. AL CLIENTE     | |          | |        |20020123|000|000|00000000|20020204|OTAPEREZ                      |21|30-57191007-8|00|GCGO|72|DESCUENTOS     |99991231|N|     |0000000000|0000000000|0000000000|0000009032|038|CRE|CRE|S|C001|00000097|078737381120014|0|                                        |0000087677|0000038547|00000000|20030805|20030805|20160212| |          | |        |01|ENV. AL CLIENTE     |OTAPEREZ                      |21|30-57191007-8|99|GCGO|72|DESCUENTOS     |0000087677|99991231|N|     |0000009032|8130601882|0000000000|01|ACTIVO    |0000009032|OK |K072      |FACTURA APROPIADA                                                                                   |OK |K074      |CREDITOS APROPIADOS                                                                                 |   |          |                                                                                                    ";

		public static int camposGanancia[] = {1, 2, 3, 4, 5, 6, 7, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74,75, 76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95, 105,106, 107};
		
		public static int camposDesestimiento[] = {1, 2, 3, 4, 5, 6, 7, 16,  17,18,19, 20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,102,103, 104};
		
		public static int camposReintegro[] = {1, 2,3,4,5,6,7,12,13,15,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,105,106,107,108,109,110};
		
		public static int camposDeuda[] = {1, 2, 3, 4, 5, 6,7, 8,9,10,11, 16, 17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49, 50,51,52,53,54,55,56, 57,58,59,  60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107};

		private String lineaSinErrores = lineaSinErroresDEUDA;
		private boolean aplica = true;
		
		
}
