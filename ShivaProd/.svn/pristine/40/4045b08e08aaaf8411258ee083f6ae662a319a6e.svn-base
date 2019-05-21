package ar.com.telecom.shiva.test.modulos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class OperacionMasivaDummie {

	public OperacionMasivaDummie() {
		// TODO Auto-generated constructor stub
	}

	
	public static int camposGanancia[] = {1, 2, 3, 4, 5, 6, 7, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74,75, 76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95, 105,106, 107};
	
	public static int camposDesestimiento[] = {1, 2, 3, 4, 5, 6, 7, 16,  17,18,19, 20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,102,103, 104};
	
	public static int camposReintegro[] = {1, 2,3,4,5,6,7,12,13,15,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,105,106,107,108,109,110};
	
	public static int camposDeuda[] = {1, 2, 3, 4, 5, 6,7, 8,9,10,11, 16, 17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49, 50,51,52,53,54,55,56, 57,58,59,  60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107};
	
	private int getVectores(TipoArchivoOperacionMasivaEnum key)[] {
		int vector[] = null;
		switch (key) {
		case DEUDA:
			vector = camposDeuda;
			break;
		case GNCIA:
				vector = camposGanancia;
			break;
		case DSIST:
				vector = camposDesestimiento;
			break;
		case REINT:
				vector = camposReintegro;
			break;
		}
		return vector;
	}
	
	
	private boolean contiene(int lista[], int posicion) {
		for (int numero : lista ) {
			if (numero == posicion) {
				return true;
			}
		}
		return false;
	}
	public String crearRegistro(OperacionMasivaDummieVo vo) {
		
		MicOperacionMasivaCamposEntradaEnum campos[] = MicOperacionMasivaCamposEntradaEnum.values();
		int lista[] = this.getVectores(vo.tipo);
		//StringBuffer str = new StringBuffer();
		List<String> camposSalida = new ArrayList<String>();
		for (MicOperacionMasivaCamposEntradaEnum campo: campos) {
			if (
				MicOperacionMasivaCamposEntradaEnum.HEAD_TIPO_REGISTRO.equals(campo) ||
				MicOperacionMasivaCamposEntradaEnum.HEAD_CANT_REGISTRO.equals(campo) ||
				MicOperacionMasivaCamposEntradaEnum.HEAD_FECHA_PROCESAMIENRO.equals(campo)
			) {
				
			} else {
				if (this.contiene(lista, campo.posicion() + 1)) {
					vo.key = campo;
					camposSalida.add(this.datos(vo));
				} else {
					if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
						camposSalida.add(this.espacios(campo.longitud()));
					} else {
						camposSalida.add(this.ceros(campo.longitud()));;
					}
				}
			}
			
		}
		
		return StringUtils.join(
				camposSalida.toArray(new String[camposSalida.size()]),
				"|"
		);
	}
	
	
	private String ceros(int cant) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cant; i++) {
			str.append("0");
		}
		return str.toString();
	}
	private String espacios(int cant) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cant; i++) {
			str.append(" ");
		}
		return str.toString();
	}
	
	public String datos(OperacionMasivaDummieVo vo) {
		String value = Utilidad.EMPTY_STRING;
		switch (vo.key) {
			case HEAD_TIPO_REGISTRO:
			break;	
			case HEAD_CANT_REGISTRO:
			break;	
			case HEAD_FECHA_PROCESAMIENRO:
				break;	
			//CORE
		//Parametros generales del registro de salida
			case PARAMETROS_GENERALES_TIPO_REGISTRO://1
				value = "D";
				break;
			case PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA://2
				value = "id_op_mas";
				break;
			case PARAMETROS_GENERALES_TIPO_INVOCACION://3
				value = vo.tipo.getCodigo();
				break;	
			case PARAMETROS_GENERALES_ID_OPERACION_SHIVA://4
				value = "id_op";
				break;	
			case PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION://5
				value = "00001";
				break;

		// Datos de cobranza generales
			case COBRANZA_GENERALES_ID_COBRANZA://6
				value = Utilidad.addStartingZeros("71", 12);
				break;	
			case COBRANZA_GENERALES_FECHA_VALOR_COBRANZA://7
				value = "20160220";
				break;

		// Datos de cobranza apropiacion de deuda
			case COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS://8
				value = Utilidad.addStartingZeros("100", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS://9
				value = Utilidad.addStartingZeros("1000", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS://10
				value = Utilidad.addStartingZeros("10000", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS://11
				value = Utilidad.addStartingZeros("100000", 10);
				break;

		// Datos de cobranza generacion de cargo
			case COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS://12
				value = Utilidad.addStartingZeros("10", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS://13
				value = Utilidad.addStartingZeros("100", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS://14
				value = Utilidad.addStartingZeros("1000", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS://15
				value = Utilidad.addStartingZeros("10000", 10);
				break;
	
		// Datos del debito imputado: cliente
			case DEBITO_IMPUTADO_CODIGO_CLIENTE://16
				if (!Validaciones.isEmptyString(vo.clienteDebito)) {
					value = Utilidad.addStartingZeros(vo.clienteDebito, 10);
				} else {
					value = this.ceros(10);
				}
				break;
		
		// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
				case DEBITO_IMPUTADO_GRAL_CUENTA://17
					value = "001";
				break;
			case DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO://18
					value = vo.tipoComprobante.name();
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC://19
				if (TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = "02";
				} else {
					value = this.ceros(2);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC://20
				if (TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = Utilidad.rellenarEspaciosDerecha("SERV. TEMPORARIOS", 20);
				} else {
					value = this.espacios(20);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC://21
				if (TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = "G";
				} else {
					value = this.espacios(1);
				}
				break;
			
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC://22
				if (TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = Utilidad.rellenarEspaciosDerecha("GENERADO", 18);
				} else {
					value = this.espacios(18);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_ACUERDO://23
				value = Utilidad.addStartingZeros("1234567890", 10);
				break;
			case DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE://24
				if (!TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = "A";
				} else {
					value = this.espacios(1);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE://25
				if (!TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = "0111";
				} else {
					value = this.espacios(4);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE: //26
				if (!TipoComprobanteEnum.DUC.equals(vo.tipoComprobante)) {
					value = Utilidad.addStartingZeros("" + vo.comprobante, 8);
				} else {
					value = this.espacios(8);
				}
				break;
			
			case DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC://27
				if (!Validaciones.isEmptyString(vo.numRefMicFac)) {
					value = Utilidad.rellenarEspaciosDerecha(vo.numRefMicFac, 15);
				} else {
					value = "875057008010159";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO: // 28
				value = "20160221";
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO: // 29
				switch (vo.tipoComprobante) {
				case DUC:
					value = Utilidad.addStartingZeros("100", 10);	
					break;
				case FAC:
					value = Utilidad.addStartingZeros("2000", 10);
					break;
				default:
					value = this.ceros(10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO:// 30
				switch (vo.tipoComprobante) {
				case DUC:
					value = Utilidad.addStartingZeros("100", 10);	
					break;
				case FAC:
					value = Utilidad.addStartingZeros("2001", 10);
					break;
				default:
					value = this.ceros(10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO:// 31
				switch (vo.tipoComprobante) {
				case DUC:
					value = Utilidad.addStartingZeros("100", 10);	
					break;
				case FAC:
					value = Utilidad.addStartingZeros("2000", 10);
					break;
				default:
					value = this.ceros(10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION:// 32
				value ="00";
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION:// 33
				value = Utilidad.rellenarEspaciosDerecha("POTENCIAL", 10);
				break;
			case DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS:// 34
				if (SiNoEnum.SI.equals(vo.estadoConceptoTercerosDeuda)) {
					value = "S";
				} else {
					value ="N";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA:// 35
				if (SiNoEnum.SI.equals(vo.posibilidadDestraferencia)) {
					value = "S";
				} else {
					value ="N";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS:// 36
				if (SiNoEnum.SI.equals(vo.estadoConceptoTercerosDeuda)) {
					value = this.ceros(10);
				} else {
					value = Utilidad.addStartingZeros("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS:// 37
				if (SiNoEnum.SI.equals(vo.estadoConceptoTercerosDeuda)) {
					value = this.ceros(10);
				} else {
					value = Utilidad.addStartingZeros("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS:// 38
				if (SiNoEnum.SI.equals(vo.estadoConceptoTercerosDeuda)) {
					value = this.ceros(10);
				} else {
					value = Utilidad.addStartingZeros("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA:// 39
				value = "01";
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA:// 40
				value = Utilidad.rellenarEspaciosDerecha("ENV. AL CLIENTE", 20);
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO: //41
				if (vo.marcaReclamoVacia) {
					value = " ";
				} else {
					value = "D";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO://42
				if (vo.marcaReclamoVacia) {
					value = this.espacios(10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("ANULADO", 10);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ://43
				value = "Q";
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ://44
				value = Utilidad.rellenarEspaciosDerecha("QUIEBRA", 8);
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_EMISION://45
				value = "20160222";
				break;
			case DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO://46
				value = "101";
				break;
			case DEBITO_IMPUTADO_GRAL_CUOTA://47
				value = "111";
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL://48
				value = "20160223";
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO://49
				value = "20160224";
				
			// Datos del debito imputado: tagetik  (foto de los datos p:revios a la imputacion)
				break;
			case DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE://50
				value = Utilidad.rellenarEspaciosDerecha("Razón social cliente", 30);
				break;
			case DEBITO_TAGETIK_TIPO_CLIENTE://51
				value = "16";
				break;
			case DEBITO_TAGETIK_CUIT://52
				value = "30709711640";
				break;
			case DEBITO_TAGETIK_CICLO_FACTURACION://53
				value = "01";
				break;
			case DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP://54
				value = "A001";
				break;
			case DEBITO_TAGETIK_TIPO_FACTURA://55
				value = "51";
				break;
			case DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA://56
				value = "FACT. FLEXCAB";
			break;	
			// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
				
			case DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA://57
				value = "20160225";
				break;
			case DEBITO_DAKOTA_INDICADOR_PETICION_CORTE://58
				value = "1";
				break;
			case DEBITO_DAKOTA_CODIGO_TARIFA://59
				value = "01234";
				break;
				// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
				
			case SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE://60
				value = Utilidad.addStartingZeros("100", 10);
				break;
			case SALDO_TERCERO_FINACIABLE_TRANSFERIBLE://61
				value = Utilidad.addStartingZeros("1000", 10);
				break;
			case SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE://62
				value = Utilidad.addStartingZeros("10000", 10);
				break;
			// Datos del credito aplicado: cliente
			
			case CODIGO_CLIENTE_CREDITO_APLICADO: // 63
				if (!Validaciones.isEmptyString(vo.clienteCredito)) {
					value = Utilidad.addStartingZeros(vo.clienteCredito, 10);
				} else {
					value = this.ceros(10);
				}
				break;
		// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
				
			case MEDIO_DE_PAGO_CUENTA:	//64
				value = Utilidad.rellenarCerosIzquierda("12",3);
				break;
			// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
			case MEDIO_DE_PAGO_TIPO_CREDITO://65
				value = vo.tipoCreito;
				break;
			case NC_TIPO_COMPROBANTE://66
				value = "CRE";
				break;
			case NC_CLASE_COMPROBANTE://67
				if ("CRE".equals(vo.tipoCreito)) {
					value = "u";
				}
				break;
			case NC_SUCURSAL_COMPROBANTE://68
				if ("CRE".equals(vo.tipoCreito)) {
					value = "4402";
				}
				break;
			case NC_NUMERO_COMPROBANTE://69
				if ("CRE".equals(vo.tipoCreito)) {
					value = "00658518";
				}
				break;
			case NC_NUMERO_REFERENCIA_MIC://70
				if ("CRE".equals(vo.tipoCreito)) {
					value = vo.numRefMicCred;
				}
				break;
			// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
			
			case REMANENTE_CODIGO_TIPO://71
				if ("REM".equals(vo.tipoCreito)) {
					value = "1";
				} else {
					value = "0";
				}
				break;
			case REMANENTE_DESCRIPCION_TIPO://72
				if ("REM".equals(vo.tipoCreito)) {
					value = "TRANSFERIBLE NO ACTUALIZABLE ";
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 10);
				}
				break;
					
				
				
			// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
			case CREDITO_APLICADO_IMPORTE://73
				value = Utilidad.addStartingZeros("200", 10);
				break;
			case CREDITO_APLICADO_SALDO://74
				value = Utilidad.addStartingZeros("100", 10);
				break;
			case CREDITO_APLICADO_FECHA_ALTA://75
				value = "20160420";
				break;
			case CREDITO_APLICADO_FECHA_EMISION://76
				value = "20160421";
				break;
			case CREDITO_APLICADO_FECHA_VENCIMIENTO://77
				value = "20160422";
				break;
			case CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO://78
				value = "20160423";
				break;
			case CREDITO_APLICADO_CODIGO_MARCA_RECLAMO://79
				value = "A";
				break;
			case CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO://80
				value = Utilidad.rellenarEspaciosDerecha("ANULADO", 10);
				break;
			case CREDITO_APLICADO_CODIGO_MARCA_CYQ://81
				value = "Q";
				break;
			case CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ://82
				value = Utilidad.rellenarEspaciosDerecha("QUIEBRA", 8);
				break;
			case CREDITO_APLICADO_CODIGO_ESTADO_CREDITO://83
				value = "20";
				break;
			case CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO://84
				value = Utilidad.rellenarEspaciosDerecha("AVISO DE INCOMUNIC.", 20);
				break;
			// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
				case CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE://85
					value = Utilidad.rellenarEspaciosDerecha("CRIPEREZ PEREZ", 30);
				break;
			case CREDITO_TAGETIK_TIPO_CLIENTE://86
				value = Utilidad.rellenarEspaciosDerecha("1A", 2);
				break;
			case CREDITO_TAGETIK_CUIT://87
				value = Utilidad.rellenarEspaciosDerecha("30709711640", 13);
				break;
			case CREDITO_TAGETIK_CICLO_FACTURACION://88
				value = Utilidad.addStartingZeros("2", 2);
				break;
			case CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP://89
				value = Utilidad.rellenarEspaciosDerecha("1", 4);
				break;
			case CREDITO_TAGETIK_TIPO_FACTURA://90
				value = "52";
				break;
			case CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA://91
				value = Utilidad.rellenarEspaciosDerecha("FACT. BAJAS", 15);
				  
				break;
			case CREDITO_TAGETIK_IMPORTE_ORIGINAL://92
				value = Utilidad.addStartingZeros("200", 10);
				break;
				
				
			// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
			case CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA://93
				value = "20160425";
				break;
			case CREDITO_DAKOTA_INDICADOR_PETICION_CORTE://94
				value = "1";
				break;
			case CREDITO_DAKOTA_CODIGO_TARIFA://95
				value = "12345";
				break;
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
			case ACUERDO_GNERALES_CODIGO_CLIENTE_MORA://96
				if (!Validaciones.isEmptyString(vo.clienteAcuerdo)) {
					value = Utilidad.addStartingZeros(vo.clienteAcuerdo, 10);
				} else {
					value = this.ceros(10);
				}
				break;
			case ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO://97
				value = Utilidad.rellenarCerosIzquierda("10", 10);
				break;
			case ACUERDO_GNERALES_NUMERO_LINEA://98
				value = Utilidad.rellenarCerosIzquierda("10", 10);
				break;
			case ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO://99
				value = Utilidad.rellenarCerosIzquierda("01", 2);
				break;
			case ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO://100
				value = Utilidad.rellenarEspaciosDerecha("POTENCIAL", 10);
				break;
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
			case ACUERDO_CLIENTE_CODIGO_CLIENTE://101
				if (!Validaciones.isEmptyString(vo.clienteAcuerdo)) {
					value = Utilidad.addStartingZeros(vo.clienteAcuerdo, 10);
				} else {
					value = this.ceros(10);
				}
				break;
		// Datos de respuesta generales: resultado de la invocación a nivel debito
			case RESPUESTA_DEBITO_RESULTADO_CONSULTA://102
				if (Validaciones.isObjectNull(vo.errordebito)) {
					return Utilidad.rellenarEspaciosDerecha("", 3);
				}
				if (SiNoEnum.SI.equals(vo.errordebito)) {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.OK.name(), 3);
				} else {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.NOK.name(), 3);
				}
				break;
			case RESPUESTA_DEBITO_CODIGO_ERROR://103
				if (Validaciones.isObjectNull(vo.errordebito)) {
					return Utilidad.rellenarEspaciosDerecha("", 10);
				}
				if (SiNoEnum.SI.equals(vo.errordebito)) {
					value = Utilidad.rellenarEspaciosDerecha("", 10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("N_DEBITO", 10);
				}
				break;
			case RESPUESTA_DEBITO_DESCRIPCION_ERROR://104
				if (Validaciones.isObjectNull(vo.errordebito)) {
					return Utilidad.rellenarEspaciosDerecha("", 100);
				}
				if (SiNoEnum.SI.equals(vo.errordebito)) {
					value = Utilidad.rellenarEspaciosDerecha("", 100);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("Detallee_DEBIRO", 100);
				}
				break;
			// Datos de respuesta generales: resultado de la invocación a nivel credito
			case RESPUESTA_CREDITO_RESULTADO_CONSULTA://105
				if (Validaciones.isObjectNull(vo.errorCredito)) {
					return Utilidad.rellenarEspaciosDerecha("", 3);
				} 
				if (SiNoEnum.SI.equals(vo.errorCredito)) {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.OK.name(), 3);
				} else {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.NOK.name(), 3);
				}
				break;
			case RESPUESTA_CREDITO_CODIGO_ERROR://106
				if (Validaciones.isObjectNull(vo.errorCredito)) {
					return Utilidad.rellenarEspaciosDerecha("", 10);
				}
				if (SiNoEnum.SI.equals(vo.errorCredito)) {
					value = Utilidad.rellenarEspaciosDerecha("", 10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("N_CREDITO", 10);
				}
				break;
			case RESPUESTA_CREDITO_DESCRIPCION_ERROR://107
				if (Validaciones.isObjectNull(vo.errorCredito)) {
					return Utilidad.rellenarEspaciosDerecha("", 100);
				}
				if (SiNoEnum.SI.equals(vo.errorCredito)) {
					Utilidad.rellenarEspaciosDerecha("", 100);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("Detallee_CREDITO", 100);
				}
				break;
			// Datos de respuesta generales: resultado de la invocación a nivel reintegro (solo si genera cargo por PF)
			
			case RESPUESTA_REINTEGRO_RESULTADO_CONSULTA://108
				if (Validaciones.isObjectNull(vo.errorReintegro)) {
					return Utilidad.rellenarEspaciosDerecha("", 3);
				}
				if (SiNoEnum.SI.equals(vo.errorReintegro)) {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.OK.name(), 3);
				} else {
					value = Utilidad.rellenarEspaciosDerecha(TipoResultadoEnum.NOK.name(), 3);
				}
				break;
			case RESPUESTA_REINTEGRO_CODIGO_ERROR://109
				if (Validaciones.isObjectNull(vo.errorReintegro)) {
					return Utilidad.rellenarEspaciosDerecha("", 10);
				}
				if (SiNoEnum.SI.equals(vo.errorReintegro)) {
					value = Utilidad.rellenarEspaciosDerecha("", 10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("N_REINTEGRO", 10);
				}
				break;
			case RESPUESTA_REINTEGRO_DESCRIPCION_ERROR://110
				if (Validaciones.isObjectNull(vo.errorReintegro)) {
					return Utilidad.rellenarEspaciosDerecha("", 100);
				}
				if (SiNoEnum.SI.equals(vo.errorReintegro)) {
					value = Utilidad.rellenarEspaciosDerecha("", 100);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("Detallee_REINTEGRO", 100);
				}
				break;
		}
		
		return value;
	}
	public static int OM_DESESTIMIENTO = 0;
}
