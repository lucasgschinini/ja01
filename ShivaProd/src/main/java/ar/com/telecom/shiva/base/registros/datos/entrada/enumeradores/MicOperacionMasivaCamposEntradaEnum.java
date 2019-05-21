package ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.constantes.Constantes;


//TODO u578934 maxi
//Procesar la respuesta del archivo de entrada generado por MIC como respuesta al procesamiento solicitado
public enum MicOperacionMasivaCamposEntradaEnum {
	// HEAD
	HEAD_TIPO_REGISTRO(0, String.class, 1, Constantes.TIPO_ALFANUMERICO, "Tipo de Registro"),
	HEAD_CANT_REGISTRO(1, Long.class, 10, Constantes.TIPO_NUMERICO, "Cantidad de registros"),
	HEAD_FECHA_PROCESAMIENRO(2, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha de procesamiento del archivo"),
	
	// CORE
	// Parametros generales del registro de salida
	PARAMETROS_GENERALES_TIPO_REGISTRO(0, Enum.class, 1, Constantes.TIPO_ALFANUMERICO, "Tipo de Registro"),
	PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA(1, Long.class, 10, Constantes.TIPO_NUMERICO, "Id de Operación Masiva Shiva"),
	PARAMETROS_GENERALES_TIPO_INVOCACION(2, Enum.class, 2, Constantes.TIPO_ALFANUMERICO, "Tipo de invocación"),
	PARAMETROS_GENERALES_ID_OPERACION_SHIVA(3, Long.class, 7, Constantes.TIPO_NUMERICO, "Id de Operación de Shiva"),
	PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION(4, Long.class, 5, Constantes.TIPO_NUMERICO, "Id de secuencia de Transacción"),
	// Datos de cobranza generales
	COBRANZA_GENERALES_ID_COBRANZA(5, Long.class, 12, Constantes.TIPO_NUMERICO, "Id de Cobranza"),
	COBRANZA_GENERALES_FECHA_VALOR_COBRANZA(6, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha valor de la cobranza"),
	// Datos de cobranza apropiacion de deuda
	COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS(7, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses trasladados regulados"),
	COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS(8, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses trasladados no regulados"),
	COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS(9, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses bonificados regulados"),
	COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS(10, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses bonificados no regulados"),
	// Datos de cobranza generacion de cargo
	COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS(11, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses trasladados regulados"),
	COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS(12, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses trasladados no regulados"),
	COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS(13, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses bonificados regulados"),
	COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS(14, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Intereses bonificados no regulados"),
	// Datos del debito imputado: cliente
	DEBITO_IMPUTADO_CODIGO_CLIENTE(15, Long.class, 10, Constantes.TIPO_NUMERICO, "Código de cliente"),
	// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	DEBITO_IMPUTADO_GRAL_CUENTA(16, Long.class, 3, Constantes.TIPO_NUMERICO, "Cuenta"),
	DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO(17, Enum.class, 8, Constantes.TIPO_ALFANUMERICO, "Tipo de Documento"),
	DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC(18, Integer.class, 2, Constantes.TIPO_NUMERICO, "Codigo Tipo de DUC"),
	DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC(19, Enum.class, 20, Constantes.TIPO_ALFANUMERICO, "Descripcion Tipo de DUC"),
	DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC(20, Integer.class, 1, Constantes.TIPO_ALFANUMERICO, "Codigo Estado del DUC"),

	DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC(21, Enum.class, 18, Constantes.TIPO_ALFANUMERICO, "Descripción Estado del DUC"),
	DEBITO_IMPUTADO_GRAL_ACUERDO(22, String.class, 10, Constantes.TIPO_ALFANUMERICO, "Acuerdo"),
	DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE(23, String.class, 1, Constantes.TIPO_ALFANUMERICO, "Clase de Comprobante"),
	DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE(24, String.class, 4, Constantes.TIPO_ALFANUMERICO, "Sucursal del Comprobante"),
	DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE(25, String.class, 8, Constantes.TIPO_ALFANUMERICO, "Numero del Comprobante"),

	DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC(26, Long.class, 15, Constantes.TIPO_ALFANUMERICO, "Numero de Referencia MIC"),
	DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO(27, Date.class,8, Constantes.TIPO_NUMERICO, "Fecha Vencimiento"),
	DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO(28, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Importe al 1er vencimiento"),
	DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO(29, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Importe al 2do vencimiento"),
	DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO(30, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Saldo al 1er vencimiento"),

	DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION(31, Enum.class,2, Constantes.TIPO_NUMERICO, "Codigo Estado acuerdo Facturación"),
	DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION(32, String.class,10, Constantes.TIPO_ALFANUMERICO, "Descripcion Estado acuerdo Factuación"),
	DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS(33, String.class,1, Constantes.TIPO_ALFANUMERICO, "Estado Conceptos de Terceros"),
	DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA(34, String.class,1, Constantes.TIPO_ALFANUMERICO, "Posibilidad Destransferencia"),
	DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS(35, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Importe 3eros Transferidos"),

	DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS(36, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Importe 1er vencimiento con 3eros"),
	DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS(37, BigDecimal.class,10, Constantes.TIPO_NUMERICO, "Importe 2do vencimiento con 3eros"),
	
	DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA(38, String.class, 2, Constantes.TIPO_ALFANUMERICO, "Codigo Estado Factura"),
	DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA(39, String.class, 20, Constantes.TIPO_ALFANUMERICO, "Descripcion Estado Factura"),
	DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO(40, String.class, 1, Constantes.TIPO_ALFANUMERICO, "Codigo Marca Reclamo"),
	DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO(41, String.class, 10, Constantes.TIPO_ALFANUMERICO, "Descripcion Marca Reclamo"),
	
	
	DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ(42, Enum.class, 1, Constantes.TIPO_ALFANUMERICO, "Codigo Marca C&Q"),
	DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ(43, Enum.class, 8, Constantes.TIPO_ALFANUMERICO, "Descripcion Marca C&Q"),
	DEBITO_IMPUTADO_GRAL_FECHA_EMISION(44, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha emisión"),
	
	
	DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO(45, Long.class, 3, Constantes.TIPO_NUMERICO, "Número de Convenio"),
	DEBITO_IMPUTADO_GRAL_CUOTA(46, Long.class, 3, Constantes.TIPO_NUMERICO, "Cuota"),
	
	
	DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL(47, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha de ultimo pago parcial"),
	DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO(48, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha de puesta al cobro"),
	// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
	DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE(49, String.class, 30, Constantes.TIPO_ALFANUMERICO, "Razón social cliente"),
	DEBITO_TAGETIK_TIPO_CLIENTE(50, Enum.class, 2, Constantes.TIPO_ALFANUMERICO, "Tipo de Cliente"),
	DEBITO_TAGETIK_CUIT(51, String.class, 13, Constantes.TIPO_ALFANUMERICO, "CUIT"),
	DEBITO_TAGETIK_CICLO_FACTURACION(52, Integer.class, 2, Constantes.TIPO_NUMERICO, "Ciclo de Facturación"),
	DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP(53, String.class, 4, Constantes.TIPO_ALFANUMERICO, "Marketing Customer Group."),
	DEBITO_TAGETIK_TIPO_FACTURA(54, Integer.class, 2, Constantes.TIPO_NUMERICO, "Tipo de Factura"),
	DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA(55, Enum.class, 15, Constantes.TIPO_ALFANUMERICO, "Descripción Tipo de Factura"),
	// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
	DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA(56, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha vencimiento mora"),
	DEBITO_DAKOTA_INDICADOR_PETICION_CORTE(57, String.class, 1, Constantes.TIPO_ALFANUMERICO, "Indicador de Petición de Corte"),
	DEBITO_DAKOTA_CODIGO_TARIFA(58, String.class, 5, Constantes.TIPO_ALFANUMERICO, "Codigo Tarifa"),
	// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
	SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE(59, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Saldo tercero financiable no transferible"),
	SALDO_TERCERO_FINACIABLE_TRANSFERIBLE(60, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Saldo tercero financiable transferible"),
	SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE(61, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Saldo tercero no financiable transferible"),
	// Datos del credito aplicado: cliente
	CODIGO_CLIENTE_CREDITO_APLICADO(62, Long.class, 10, Constantes.TIPO_NUMERICO, "Código de cliente"),
//	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	MEDIO_DE_PAGO_CUENTA(63, Long.class, 3, Constantes.TIPO_NUMERICO, "Cuenta"),
	MEDIO_DE_PAGO_TIPO_CREDITO(64, Enum.class,3, Constantes.TIPO_ALFANUMERICO, "Tipo de Crédito"),
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	NC_TIPO_COMPROBANTE(65, Enum.class, 3, Constantes.TIPO_ALFANUMERICO, "Tipo de Comprobante"),
	NC_CLASE_COMPROBANTE(66, Enum.class, 1, Constantes.TIPO_ALFANUMERICO, "Clase de Comprobante"),
	NC_SUCURSAL_COMPROBANTE(67, String.class, 4, Constantes.TIPO_ALFANUMERICO, "Sucursal del Comprobante"),
	NC_NUMERO_COMPROBANTE(68, String.class, 8, Constantes.TIPO_ALFANUMERICO, "Numero del Comprobante"),
	NC_NUMERO_REFERENCIA_MIC(69, Long.class, 15, Constantes.TIPO_NUMERICO, "Nro de Referencia MIC"),
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	REMANENTE_CODIGO_TIPO(70, Long.class, 1, Constantes.TIPO_NUMERICO, "Codigo de Tipo de Remanente"),
	REMANENTE_DESCRIPCION_TIPO(71, Enum.class, 40, Constantes.TIPO_ALFANUMERICO, "Descripcion del Tipo de Remanente"),
	// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
	CREDITO_APLICADO_IMPORTE(72, BigDecimal.class, 10,Constantes.TIPO_NUMERICO, "Importe"),
	CREDITO_APLICADO_SALDO(73 , BigDecimal.class, 10,Constantes.TIPO_NUMERICO, "Saldo"),
	CREDITO_APLICADO_FECHA_ALTA(74 , Date.class, 8,Constantes.TIPO_NUMERICO, "Fecha alta"),
	CREDITO_APLICADO_FECHA_EMISION(75 , Date.class, 8,Constantes.TIPO_NUMERICO, "Fecha de emisión"),
	CREDITO_APLICADO_FECHA_VENCIMIENTO(76 ,Date.class, 8,Constantes.TIPO_NUMERICO, "Fecha vencimiento"),
	CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO(77, Date.class, 8,Constantes.TIPO_NUMERICO, "Fecha último movimiento cobro (PP)"),
	CREDITO_APLICADO_CODIGO_MARCA_RECLAMO(78, Enum.class, 1,Constantes.TIPO_ALFANUMERICO, "Codigo Marca Reclamo"),
	CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO(79, Enum.class, 10,Constantes.TIPO_ALFANUMERICO, "Descripcion Marca Reclamo"),
	CREDITO_APLICADO_CODIGO_MARCA_CYQ(80, Enum.class, 1,Constantes.TIPO_ALFANUMERICO, "Codigo Marca C&Q"),
	CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ(81, Enum.class, 8,Constantes.TIPO_ALFANUMERICO, "Descripcion Marca C&Q"),
	CREDITO_APLICADO_CODIGO_ESTADO_CREDITO(82 , Enum.class, 2, Constantes.TIPO_ALFANUMERICO, "Codigo Estado Crédito"),
	CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO(83 , Enum.class, 20,Constantes.TIPO_ALFANUMERICO, "Descripcion Estado Crédito"),
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE(84, String.class, 30, Constantes.TIPO_ALFANUMERICO, "Razón social cliente"),
	CREDITO_TAGETIK_TIPO_CLIENTE(85, String.class, 2, Constantes.TIPO_ALFANUMERICO, "Tipo de Cliente"),
	CREDITO_TAGETIK_CUIT(86, String.class, 13, Constantes.TIPO_ALFANUMERICO, "CUIT"),
	CREDITO_TAGETIK_CICLO_FACTURACION(87, Integer.class, 2, Constantes.TIPO_NUMERICO, "Ciclo de Facturación"),
	CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP(88, String.class, 4, Constantes.TIPO_ALFANUMERICO, "Marketing Customer Group."),
	CREDITO_TAGETIK_TIPO_FACTURA(89, Integer.class, 2, Constantes.TIPO_NUMERICO, "Tipo de Factura"),
	CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA(90, Enum.class, 15, Constantes.TIPO_ALFANUMERICO, "Descripción Tipo de Factura"),
	CREDITO_TAGETIK_IMPORTE_ORIGINAL(91, BigDecimal.class, 10, Constantes.TIPO_NUMERICO, "Importe Original"),
	
	// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
	CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA(92, Date.class, 8, Constantes.TIPO_NUMERICO, "Fecha vencimiento mora"),
	CREDITO_DAKOTA_INDICADOR_PETICION_CORTE(93, String.class, 1, Constantes.TIPO_ALFANUMERICO, "Indicador de Petición de Corte"),
	CREDITO_DAKOTA_CODIGO_TARIFA(94, String.class, 5, Constantes.TIPO_ALFANUMERICO, "Codigo Tarifa"),
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
	ACUERDO_GNERALES_CODIGO_CLIENTE_MORA(95, Long.class, 10, Constantes.TIPO_NUMERICO, "Código de cliente"),
	ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO(96, Long.class, 10, Constantes.TIPO_NUMERICO, "Acuerdo de Facturación de intereses / reintegro"),
	ACUERDO_GNERALES_NUMERO_LINEA(97, Long.class, 10, Constantes.TIPO_NUMERICO, "Numero de Linea"),
	ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO(98, Integer.class, 2, Constantes.TIPO_NUMERICO, "Codigo Estado acuerdo"),
	ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO(99, Enum.class, 10, Constantes.TIPO_ALFANUMERICO, "Descripcion Estado acuerdo"),
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	ACUERDO_CLIENTE_CODIGO_CLIENTE(100, Long.class, 10, Constantes.TIPO_NUMERICO, "Código de cliente"),
	// Datos de respuesta generales: resultado de la invocación a nivel debito
	RESPUESTA_DEBITO_RESULTADO_CONSULTA(101, Enum.class, 3, Constantes.TIPO_ALFANUMERICO, "Resultado de la consulta"),
	RESPUESTA_DEBITO_CODIGO_ERROR(102, String.class, 10, Constantes.TIPO_ALFANUMERICO, "Código de error"),
	RESPUESTA_DEBITO_DESCRIPCION_ERROR(103, String.class, 100, Constantes.TIPO_ALFANUMERICO, "Descripción del error"),
	// Datos de respuesta generales: resultado de la invocación a nivel credito
	RESPUESTA_CREDITO_RESULTADO_CONSULTA(104, Enum.class, 3, Constantes.TIPO_ALFANUMERICO, "Resultado de la consulta"),
	RESPUESTA_CREDITO_CODIGO_ERROR(105, String.class, 10, Constantes.TIPO_ALFANUMERICO, "Código de error"),
	RESPUESTA_CREDITO_DESCRIPCION_ERROR(106, String.class, 100, Constantes.TIPO_ALFANUMERICO, "Descripción del error"),
	// Datos de respuesta generales: resultado de la invocación a nivel reintegro (solo si genera cargo por PF)
	RESPUESTA_REINTEGRO_RESULTADO_CONSULTA(107, Enum.class, 3, Constantes.TIPO_ALFANUMERICO, "Resultado de la consulta"),
	RESPUESTA_REINTEGRO_CODIGO_ERROR(108, String.class, 10, Constantes.TIPO_ALFANUMERICO, "Código de error"),
	RESPUESTA_REINTEGRO_DESCRIPCION_ERROR(109, String.class, 100, Constantes.TIPO_ALFANUMERICO, "Descripción del error");

	int posicion;
	@SuppressWarnings("rawtypes")
	Class clase;
	int longitud;
	int tipo;
	String nombre;

	@SuppressWarnings("rawtypes")
	private MicOperacionMasivaCamposEntradaEnum(int posicion, Class clase, int longitud, int tipo, String nombre) {
		this.posicion = posicion;
		this.clase = clase;
		this.longitud = longitud;
		this.tipo = tipo;
		this.nombre = nombre;
	}
	/**
	 * @return the posicion
	 */
	public int posicion() {
		return posicion;
	}
	/**
	 * @return the tipo
	 */
	@SuppressWarnings("rawtypes")
	public Class clase() {
		return clase;
	}
	/**
	 * @return the longitud
	 */
	public int longitud() {
		return longitud;
	}
	public int tipo() {
		return tipo;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static MicOperacionMasivaCamposEntradaEnum getEnumByName(String name) {
		for (MicOperacionMasivaCamposEntradaEnum tipoOrigen : MicOperacionMasivaCamposEntradaEnum.values()) {
			if (tipoOrigen.name().equalsIgnoreCase(name)) {
				return tipoOrigen;
			}
		}
		return null;
	}
	public static MicOperacionMasivaCamposEntradaEnum getEnumByPosicion(int posicion) {
		for (MicOperacionMasivaCamposEntradaEnum tipoOrigen : MicOperacionMasivaCamposEntradaEnum.values()) {
			if (tipoOrigen.posicion() == posicion) {
				return tipoOrigen;
			}
		}
		return null;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombreColumna() {
		return "#" + (this.posicion() + 1) + " " + this.nombre;
	}
}
 