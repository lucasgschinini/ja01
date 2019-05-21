package ar.com.telecom.shiva.base.constantes;

import java.math.BigDecimal;



public class ConstantesCobro {
	
	/**Operaciones*/
	public static final Integer NUEVO_COBRO = 1;
	public static final Integer ACTUALIZAR_SOLAMENTE_COBRO = 2;
    
	
	/* Conf. Cobro Cantidad de registros por pagina */
	public static final int CANTIDAD_POR_PAGINA_DEB = 80;
	public static final int CANTIDAD_POR_PAGINA_CRED = 100;
	public static final String PAGINADO_BUSCAR = "buscar";
	public static final String PAGINADO_SIGUIENTE = "stge";
	public static final String PAGINADO_ANTERIOR = "ant";
	
	/**
	 * Estados para la validacion de debitos
	 */
	public static final String PENDIENTE = "Pendiente";
	public static final String EN_PROCESO = "En proceso";
	public static final String FINALIZADO = "Finalizado";
	
	public static final int cantMaxRegistrosImportarDebitos = 1500;
	public static final int logitudEsperadaLETRA = 1;
	public static final int cantidadCaracteresComprobante = 8;
	public static final String letraInicialSucursal = "U";
	public static final int cantidadCaracteresSucursal = 4;
	public static final int cantidadCaracteresTipoDocuConDUC = 13;
	
	/** Archivo X - Indice de campos del registro **/
	public static final int CLIENTE = 1;
	public static final int SISTEMA = 2; 
	public static final int TIPO_DOCUMENTO = 3;
	public static final int NRO_DOCUMENTO = 4;
	public static final int REFERENCIA_MIC = 5;
	public static final int MONEDA = 6;
	public static final int COBRAR_2_VENCIMIENTO = 7;
	public static final int DESTRANSFERIR_TERCEROS = 8;
	public static final int IMPORTE_A_COBRAR = 9;
	public static final int SIN_DIFERENCIA_DE_CAMBIO = 10;

	public static final String CLIENTE_NOMBRE = "Cliente";
	public static final String SISTEMA_NOMBRE = "Sistema"; 
	public static final String TIPO_DOCUMENTO_NOMBRE = "Tipo Documento";
	public static final String NRO_DOCUMENTO_NOMBRE = "Numero de Documento";
	public static final String LETRA_NOMBRE = "Letra";
	public static final String SUCURSAL_NOMBRE = "Sucursal";
	public static final String COMPROBANTE_NOMBRE = "Comprobante";
	public static final String REFERENCIA_MIC_NOMBRE = "Referencia MIC";
	public static final String MONEDA_NOMBRE = "Moneda";
	public static final String COBRAR_2_VENCIMIENTO_NOMBRE = "Cobrar al 2º Vencimiento";
	public static final String DESTRANSFERIR_TERCEROS_NOMBRE = "Destransferir terceros";
	public static final String IMPORTE_A_COBRAR_NOMBRE = "Importe a cobrar";
	public static final String SIN_DIFERENCIA_DE_CAMBIO_NOMBRE = "Sin diferencia de cambio";

	//	/** Archivo Importar Debitos - Indice de campos del registro **/
	public static final String ERROR_LENGTH_CAMPO = "Longitud erronea del campo";
	public static final String ERROR_TIPO_DE_DATO = "Tipo de dato erróneo del campo";
	public static final String ERROR_OBLIGATORIO = " es obligatorio";
	public static final String ERROR_EL_CAMPO = "El campo ";
	public static final String ERROR_REGISTRO_DUPLICADO = "Registro Duplicado";
	public static final String ERROR_REGISTRO_DUPLICADO_EN_COBRO = "Registro Duplicado en el cobro";
	public static final String ERROR_REGISTRO_DUC_TRATAMIENTO = "Existe un tratamiento de la diferencia seleccionado";
	public static final String ERROR_REGISTRO_DUC_FILE = "Existe un tratamiento de la diferencia seleccionado";
	
	
	
	public static final String ERROR_NRO_DOS_DIGITOS= " debe respetar el formato 999.999.999,99";
	public static final String ERROR_SUCURSAL_CON_U = "La sucursal debe iniciar con U";
	public static final String ERROR_FALTA_LETRA = "Falta ingresar la Letra, o bien quite el - del inicio del campo Nro Documento.";
	public static final String ERROR_FORMATO_NRO_DOCUMENTO_DUC = " debe respetar el formato Unnn-nnnnnnnn";
	public static final String ERROR_FORMATO_NRO_DOCUMENTO = " debe respetar el formato X-nnnn-nnnnnnnn o nnnn-nnnnnnnn";
	public static final String ERROR_VALORES_POSIBLES = "Valor/es posible/s para el campo ";
	public static final String ERROR_VALORES_POSIBLES_MIC_CLP = "Valores posibles: MIC o CLP";
	public static final String ERROR_VALORES_POSIBLES_S_N = "Valores posibles: S o N";
	public static final String ERROR_VALORES_POSIBLES_S_N_VACIO = "Valores posibles: S, N o Vacio";
	public static final String ERROR_VALORES_POSIBLES_S_VACIO = "Valores posibles: S o Vacio";
	public static final String ERROR_VALORES_POSIBLES_FAC_DEB_DUC = "Valores posibles: FAC, DEB o DUC";
	public static final String ERROR_NO_PUEDE_CONTENER_VALOR = "no puede contener valor si el Sistema es CLP";
	public static final String ERROR_EL_REGISTRO_NRO = "El Registro Nro ";
	public static final String ERROR_POSEE_CLIENTE_NO_VALIDO = " posee un Cliente no valido.";
	public static final String ERROR_VALORES_POSIBLE_LETRA_A_B_VACIO = " Valores posibles para la Letra: A, B, o Vacio";
	public static final String ERROR_VALORES_POSIBLE_LETRA_A_B_E = " Valores posibles para la Letra: A, B o E";
	public static final String ERROR_VALORES_POSIBLES_$_U$S = " Valores posibles: $ o U$S";
	public static final String ERROR_MONEDA_OPERACION = " La moneda (1) no coincide con la moneda del cobro";
	
	public static final String ERROR_CANT_MAX_REGISTROS = "ERROR: El archivo excede el maximo de 1500 debitos esperados.";
	public static final String ERROR_CANT_MAX_REGISTROS_MUCHOS_ARCH = "ERROR: La cantidad total de debitos que se intentan subir superan el maximo de 1500 debitos esperados.";
	public static final String ERROR_COBRO_CON_DOCUMENTO_DUC = "conf.cobro.mensaje.error.documento.duc";
	public static final String ERROR_COBRO_CON_DOCUMENTO_NO_DUC = "conf.cobro.mensaje.error.documento.no.duc";
	public static final String ERROR_COBRO_CON_DOCUMENTO_TRATAMIENTO_CON_DUC = "conf.cobro.mensaje.error.documento.tratamiento.duc";
	public static final String ERROR_COBRO_CON_DOCUMENTO_DUC_CON_TRATAMIENTO = "conf.cobro.mensaje.error.documenyo.duc.tratamiento";

	public static final String LONGITUD_CORRECTA_STRING = ". Debe ser de longitud ";
	public static final String LONGITUD_CORRECTA_SUCURSAL_STRING = ". Longitud correcta de la sucursal: ";
	public static final String LONGITUD_CORRECTA_LETRA_STRING = ". Longitud correcta de la letra: ";

	public static final String FORMATO_NUMERICO_STRING = "El campo debe ser numérico";
	public static final String FORMATO_COMPROBANTE_NUMERICO_STRING = "El comprobante debe ser numérico";
	public static final String FORMATO_ALPHANUMERICO_STRING = "El campo debe ser alphanumerico";
	public static final String FORMATO_SUCURSAL_ALPHANUMERICO_STRING = "La sucursal debe ser alphanumerica";
	public static final String FORMATO_SUCURSAL_NUMERICO_STRING = "La sucursal debe ser numerica";
	public static final String FORMATO_SUCURSAL2_ALPHANUMERICO_STRING = "La sucursal debe respetar el formato Unnn";
	public static final String FORMATO_LETRA_ALPHANUMERICO_STRING = "La letra debe ser alphanumerica";

	public static final String CON_TIPO_DOCUMENTO_DUC = "con Tipo de Documento DUC";
	public static final String CON_COBRAR_2_VENCIMIENTO_S = "con Cobrar al 2do Vencimiento en S";
	public static final String CON_COBRAR_2_VENCIMIENTO_N = "con Cobrar al 2do Vencimiento en N";
	public static final String CON_DESTRANSFERIR_TERCEROS_S = "con Destransferir Terceros en S";
	public static final String CON_DESTRANSFERIR_TERCEROS_N = "con Destransferir Terceros en N";
	public static final String CON_SIN_DIFERENCIA_DE_CAMBIO_S = "con Sin Diferencia de cambio en S";
	public static final String CON_SIN_DIFERENCIA_DE_CAMBIO_N = "con Sin Diferencia de cambio en N";
	public static final String CON_SIN_DIFERENCIA_DE_CAMBIO_VACIO = "con Sin Diferencia de cambio vacío";
	public static final String CON_SISTEMA_MIC = "con Sistema MIC";
	public static final String CON_SISTEMA_CLP = "con Sistema CLP";
	public static final String CON_REFERENCIA_MIC = "con Referencia MIC";
	public static final String CON_MONEDA_PESO = "con Moneda $";
	public static final String CON_MONEDA_DOLAR = "con Moneda U$S";

	public static final String LINEA_NRO = "Linea número: ";
	public static final String CAUSA = ". Causa: ";
	public static final String VACIO = ": Vacio";
	public static final String FACDEB = ": FAC / DEB";
	public static final String FACDEBDUC = ": FAC, DEB o DUC";
	public static final String MIC = ": MIC";
	public static final String CLP = ": CLP";
	public static final String PESO = ": $";
	public static final String DOLAR = ": U$S";
	public static final String CODIGO_ESTADO_CREDITO_DEIMOS = "55";
	
	
	public static final String MENSAJE_CONSULTA_ESTADO_DEUDA = "Consulta Estado Deuda Deimos";
	public static final String MENSAJE_APROPIACION = "Apropiacion Deimos";
	public static final String MENSAJE_DESAPROPIACION = "Desapropiacion Deimos";

	public static final int TRANSACCION_NO_PROXIMA_FAC = 0;
	public static final int TRANSACCION_PROXIMA_FAC_CON_INTERESES = 1;
	public static final int TRANSACCION_SOLO_LECTURA = 4;
	public static final int TRANSACCION_CON_INTERESES_NO_DIFERENCIA_CAMBIO = 2;
	public static final int TRANSACCION_DISABLED = 3;
	public static final int TRANSACCION_ASTERISCOS = 5;
	// Simulacion de Cobros - Parametros de validaciones
	public static final String COBROS_VALIDACION_PORCENTAJE_MAXIMO_MONTO_PROXIMA_FACTURA = "cobros.validacion.porcentajeMaximoMontoProximaFact";
	public static final String COBROS_VALIDACION_IMPORTE_MAXIMO_MONTO_PROXIMA_FACTURA = "cobros.validacion.importeMaximoMontoProximaFact";
	public static final String COBROS_VALIDACION_CANTIDAD_TRANSACCIONES_MAXIMO_ONLINE_PERMITIDO = "cobros.validacion.cantTransacMaximoOnlinePermitido";
	public static final String COBROS_VALIDACION_SEGMENTO_OBLIGATORIO_TRASLADO_INTERESES_PROXIMA_FACTURA = "cobros.validacion.segmentoObligatorioTrasladarInteresReintCredProxFact";
	
	// Simulacion de Cobros - Parametros para las concurrencias
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_CALIPSO = "cobros.simulacion.cantConcurrenciasApropCalipso";	
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_MIC = "cobros.simulacion.cantConcurrenciasApropMic";
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_GENERACION_CARGO_CALIPSO = "cobros.simulacion.cantConcurrenciasGenCargoCalipso";
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_GENERACION_CARGO_MIC = "cobros.simulacion.cantConcurrenciasGenCargoMic";
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_APROPIACION_DEIMOS = "cobros.simulacion.cantConcurrenciasApropDeimos";
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_VERIFICAR_PARTIDAS_SAP = "cobros.simulacion.cantConcurrenciasVerifPartidaSap";
	public static final String COBROS_SIMULACION_CANTIDAD_CONCURRENCIAS_CONSULTAR_PROVEEDOR_SAP = "cobros.simulacion.cantConcurrenciasVerifProveedorSap";
	
	public static final String GRUPO_TEAM_COMERCIAL = "Grupo de analistas";
	
	// Codigo de error proveniente de Calipso, para indicar que un elemento no ha sido apropiado
	// porque existe otro, en la misma llamada, con error.
	public static final String CODIGO_ERROR_9999 = "9999";
	
	public static final BigDecimal maxImportePorDefecto = new BigDecimal("99999999999999999.99");
	
		// ID motivo cobro
	public static final String ID_MOTIVO_COBRO_COMPENSACION = "9";
	
	
	public static final String FIRMANTE_DOCUMENTO_DETALLEFIRMA = "firmante.documento.detalleFirma";
	public static final String FIRMANTE_DOCUMENTO_SECTORSOLICITANTE = "firmante.documento.sectorSolicitante";
	public static final String FIRMANTE_DOCUMENTO_SEGMENTO = "firmante.documento.segmento";
	
	public static final String GRUPO_INTERNO_SHIVA = "0";
	
	public static final Integer GRUPO_INTERNO_SHIVA_INTEGER = 0;
	
	
}
