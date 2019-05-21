package ar.com.telecom.shiva.base.constantes;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;


public class Constantes {
	
	
	/** Aplicacion */
	public static final String SHIVA_APLICACION = "Shiva";
	
	/** Configuracion */
	public static final String PROPIEDAD_CONFIGURACION = "configuracion";
	public static final String DEFINICION_PROCESAMIENTO_ARCHIVO_AVC_ACUERDO = "definicionProcesamientoArchivoAvcAcuerdo";
	
	/** Version Aplicacion */
	public static final String VERSION = "version";
	public static final String VERSION_APLICACION = getVersionEntorno();
	public static final String getVersionEntorno() {
		String entorno = System.getProperty(Constantes.PROPIEDAD_ENTORNO);
		return Propiedades.SHIVA_VERSION.getString("shiva.version."+entorno);
	}
	
	/** Directorio del servidor JBOSS*/
	public static final String PROPIEDAD_SERVIDOR_JBOSS = "jboss.home.dir";
	public static final String SERVIDOR_JBOSS = System.getProperty(Constantes.PROPIEDAD_SERVIDOR_JBOSS);
	
	/** Directorio del servidor JBOSS-LOG*/
	public static final String PROPIEDAD_SERVIDOR_JBOSS_LOG = "jboss.server.log.dir";
	public static final String SERVIDOR_JBOSS_LOG = System.getProperty(Constantes.PROPIEDAD_SERVIDOR_JBOSS_LOG);
	
	/** Directorio del servidor aplicacion*/
	public static final String PROPIEDAD_SERVIDOR_SHIVA = "jboss.server.base.dir";
	public static final String SERVIDOR_SHIVA = System.getProperty(Constantes.PROPIEDAD_SERVIDOR_SHIVA);

	/**Directorio donde se dejan los archivos temporales **/
	public static final String PROPIEDAD_DIRECTORIO_TEMPORAL_SHIVA = "shiva.dir.temp";
	public static final String DIRECTORIO_TEMPORAL_SHIVA = Propiedades.SHIVA_PROPIEDADES.getString(Constantes.PROPIEDAD_DIRECTORIO_TEMPORAL_SHIVA);
	
	/** Entorno */
	public static final String PROPIEDAD_ENTORNO = "entorno";
	public static final String ENTORNO = getEntorno();
	public static final String ENTORNO_LOCAL = "desa";
	public static final String getEntorno() {
		String valor = System.getProperty(Constantes.PROPIEDAD_ENTORNO);
		boolean esDesarrollo="desa".equalsIgnoreCase(valor) 
				|| "desa2".equalsIgnoreCase(valor)
				|| "desa3".equalsIgnoreCase(valor)
			    || "desa4".equalsIgnoreCase(valor);
		if (esDesarrollo) {
			return ENTORNO_LOCAL;
		}
		boolean esInte="inte".equalsIgnoreCase(valor) 
				|| "inte2".equalsIgnoreCase(valor)
				|| "inte3".equalsIgnoreCase(valor)
				|| "inte4".equalsIgnoreCase(valor);
		if (esInte) {
			return "inte";
		}
		boolean esPau="pau".equalsIgnoreCase(valor) 
				|| "pau2".equalsIgnoreCase(valor)
				|| "pau3".equalsIgnoreCase(valor)
				|| "pau4".equalsIgnoreCase(valor);
		if (esPau) {
			return "pau";
		}
		return valor;
	}
	public static final boolean ES_ENTORNO_DESA = "desa".equalsIgnoreCase(ENTORNO);
	public static final boolean ES_ENTORNO_INTE = "inte".equalsIgnoreCase(ENTORNO);
	public static final boolean ES_ENTORNO_PAU  = "pau".equalsIgnoreCase(ENTORNO);
	public static final boolean ES_ENTORNO_PROD = "prod".equalsIgnoreCase(ENTORNO);
	
	/** Emular Perfiles LDAP de otro ambiente */
	public static final String PROPIEDAD_ENTORNO_EMULAR_PERFILES = "entornoEmularPerfiles";
	public static final String ENTORNO_EMULAR_PERFILES = System.getProperty(PROPIEDAD_ENTORNO_EMULAR_PERFILES);
	
	//Determino si el servidor que está corriendo es un windows o no
	public static final boolean isServerWindows() {  
		String server_os = System.getProperty("os.name").toLowerCase();
		if (server_os.indexOf("win") >= 0) {
			//if windows
			return true;
		}else{
			return false;
		}
	}
	
	/** Aplicacion + Entorno */
	//public static final String APLICACION_ENTORNO = SHIVA_APLICACION+"_"+ENTORNO.toUpperCase();
	public static final String APLICACION_ENTORNO = getAplicacionEntorno();
	/**
	 * Armamos este metodo para poder emular un ambiente de prepi, emulando los perfiles de otro ambiente
	 * @return
	 */
	public static final String getAplicacionEntorno() {
		String retorno = SHIVA_APLICACION + "_" + ENTORNO.toUpperCase();

		if (ENTORNO_EMULAR_PERFILES != null && !"".equals(ENTORNO_EMULAR_PERFILES)) {
			retorno = SHIVA_APLICACION + "_" + ENTORNO_EMULAR_PERFILES.toUpperCase();
		} 
		return retorno;
	}
	
	/** LOCK LISTENER Unica instancia corriendo */
	public static final String LOCK_KEY_IMPUTACION_LISTENER = "LOCK_KEY_IMPUTACION_LISTENER";
	public static final String LOCK_KEY_IMPUTACION_BATCH = "LOCK_KEY_IMPUTACION_BATCH";
	public static final String LOCK_KEY_IMPUTACION_MANUAL_BATCH = "LOCK_KEY_IMPUTACION_MANUAL_BATCH";
	public static final String LOCK_KEY_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS = "LOCK_KEY_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS";
	public static final String LOCK_KEY_IMPUTACION_RECEPCION_BATCH = "LOCK_KEY_IMPUTACION_RECEPCION_BATCH";
	public static final String LOCK_KEY_IMPUTACION__VALORES_CLIENTES_PUROS_BATCH = "LOCK_KEY_IMPUTACION_VALORES_CLIENTES_PUROS_BATCH";
	public static final String LOCK_KEY_SIMULACION_COBRO_BATCH = "LOCK_KEY_SIMULACION_COBRO_BATCH";
	public static final String LOCK_KEY_SIMULACION_DESCOBRO_BATCH = "LOCK_KEY_SIMULACION_DESCOBRO_BATCH";
	public static final String LOCK_KEY_IMPUTACION_DESCOBRO_BATCH = "LOCK_KEY_IMPUTACION_DESCOBRO_BATCH";
	public static final String LOCK_KEY_MIGRACION_BATCH = "LOCK_KEY_MIGRACION_BATCH";
	public static final String LOCK_KEY_REVERSION_BATCH = "LOCK_KEY_REVERSION_BATCH";
	public static final String LOCK_KEY_MOTOR_CONCILIACION_BATCH = "LOCK_KEY_MOTOR_CONCILIACION_BATCH";
	public static final String LOCK_KEY_MOTOR_CONCILIACION_BATCH_ALTA = "LOCK_KEY_MOTOR_CONCILIACION_BATCH_ALTA";
	public static final String LOCK_KEY_PROCESAR_ARCHIVO_AVC_BATCH = "LOCK_KEY_PROCESAR_ARCHIVO_AVC_BATCH";
	public static final String LOCK_KEY_PROCESAR_ARCHIVO_AVC_DINAMICO_BATCH = "LOCK_KEY_PROCESAR_ARCHIVO_AVC_DINAMICO_BATCH";
	public static final String LOCK_KEY_CONTABILIDAD_BATCH = "LOCK_KEY_CONTABILIDAD_BATCH";
	public static final String LOCK_KEY_ANULAR_BOLETA_BATCH = "LOCK_KEY_ANULAR_BOLETA_BATCH";
	public static final String LOCK_KEY_OPERACION_TRUNCAS_BATCH = "LOCK_KEY_OPERACION_TRUNCAS_BATCH";
	public static final String LOCK_KEY_OPERACION_TAGETIK = "LOCK_KEY_OPERACION_TAGETIK";
	public static final String LOCK_KEY_NOVEDADES_BATCH = "LOCK_KEY_NOVEDADES_BATCH";
	public static final String LOCK_KEY_REPORTE_MOROSIDAD_BATCH = "LOCK_KEY_REPORTE_MOROSIDAD_BATCH";
	public static final String LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_BATCH = "LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_BATCH";
	public static final String LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_DISPONIBLES_BATCH = "LOCK_KEY_REPORTE_HISTORICO_VALORES_OTRAS_EMPRESAS_DISPONIBLES_BATCH";
	public static final String LOCK_KEY_REPORTE_APLICACION_MANUAL_PENDIENTE_BATCH = "LOCK_KEY_REPORTE_APLICACION_MANUAL_PENDIENTE_BATCH";
	public static final String LOCK_KEY_REPORTE_TAREA_PENDIENTE_APLICACION_MANUAL_BATCH = "LOCK_KEY_REPORTE_TAREA_PENDIENTE_APLICACION_MANUAL_BATCH";
	public static final String LOCK_KEY_APERTURA_CAJA_BATCH = "LOCK_KEY_APERTURA_CAJA_BATCH";
	public static final String LOCK_KEY_CIERRE_CAJA_BATCH = "LOCK_KEY_CIERRE_CAJA_BATCH";
	public static final String LOCK_KEY_RECIBO_SCARD_BATCH = "LOCK_KEY_RECIBO_SCARD_BATCH";
	public static final String LOCK_KEY_RECONFIRMACION_COBRO_BATCH = "LOCK_KEY_RECONFIRMACION_COBRO_BATCH";
	public static final String LOCK_KEY_REPORTE_RETENCIONES_IIBB_BATCH = "LOCK_KEY_REPORTE_RETENCIONES_IIBB_BATCH";
	public static final String LOCK_KEY_SUBDIARIO_COBRANZA_BATCH = "LOCK_KEY_SUBDIARIO_COBRANZA_BATCH";
	public static final String LOCK_KEY_ENCRIPTACION_ARCHIVOS_BATCH = "LOCK_KEY_ENCRIPTACION_ARCHIVOS_BATCH";
	public static final String LOCK_KEY_ACTUALIZACION_TEAM_COMERCIAL_BATCH = "LOCK_KEY_ACTUALIZACION_TEAM_COMERCIAL_BATCH";
	public static final String LOCK_KEY_CALIPSO_SECURITY_TEST = "LOCK_KEY_CALIPSO_SECURITY_TEST";
	public static final String LOCK_KEY_VALIDACION_DEBITOS_BATCH = "LOCK_KEY_VALIDACION_DEBITOS_BATCH";
	public static final String LOCK_KEY_OPERACIONES_MASIVAS_SIEBEL_BATCH = "LOCK_KEY_OPERACIONES_MASIVAS_SIEBEL_BATCH";
	public static final String LOCK_KEY_OPERACIONES_MASIVAS_BATCH = "LOCK_KEY_OPERACIONES_MASIVAS_BATCH";
	public static final String LOCK_KEY_OPERACIONES_MASIVAS_BATCH_LECTURA_MIC = "LOCK_KEY_OPERACIONES_MASIVAS_BATCH_LECTURA_MIC";
	public static final String LOCK_KEY_CARGAR_COTIZACIONES_SAP_BATCH = "LOCK_KEY_CARGAR_COTIZACIONES_SAP_BATCH";
	public static final String LOCK_KEY_EXCEPCION_MOROSIDAD_BATCH = "LOCK_KEY_EXCEPCION_MOROSIDAD_BATCH";
	public static final String LOCK_KEY_ENVIO_REVERSIONES_ICE_BATCH = "LOCK_KEY_ENVIO_REVERSIONES_ICE_BATCH";
	public static final String LOCK_KEY_ACTUALIZACION_SALDOS_REEMBOLSO_BATCH = "LOCK_KEY_ACTUALIZACION_SALDOS_REEMBOLSO_BATCH";
	public static final String LOCK_KEY_GENERAR_SALIDA_DETALLE_APLICACIONES_MANUALES_BATCH = "LOCK_KEY_GENERAR_SALIDA_DETALLE_APLICACIONES_MANUALES_BATCH";
	public static final String LOCK_KEY_PROCESAR_ENTRADA_DETALLE_APLICACIONES_MANUALES_BATCH = "LOCK_KEY_PROCESAR_ENTRADA_DETALLE_APLICACIONES_MANUALES_BATCH";
	public static final String LOCK_KEY_REPORTE_REGISTROS_AVC_PENDIENTE_CONCILIAR = "LOCK_KEY_REPORTE_REGISTROS_AVC_PENDIENTE_CONCILIAR";
	public static final String LOCK_KEY_REPORTE_PERFILES_AUDITORIA = "LOCK_KEY_REPORTE_PERFILES_AUDITORIA";
	public static final String LOCK_KEY_CONTROL_HILOS_COBROS_BATCH = "LOCK_KEY_CONTROL_HILOS_COBROS_BATCH";
	
	/** Ldap Role Prefijo */
	public static final String ROL_PREFIJO = "ROLE_";
	
	/**	unchecked */
    public static final String UNCHECKED = "unchecked";
    
    /**	unecked */
    public static final String UNDEFINED="undefined";
    
    /** por defecto */
    public static final String DEFAULT = "default";
    
    /** Usuario */
    public static final String SECURITY_USERNAME = "j_username";
    public static final String LOGIN = "loginUser";
    public static final String LOGIN_USERNAME = "loginUserName";
    
    public static final String SECURITY_PASSWORD = "j_password";
    /** Excepcion desde validacion */
    public static final String EXCEPCION = "errorGeneral";
    
    /** Vista Excepcion */
    public static final String EXCEPCION_PAGINA = "sesion/excepcion";
    public static final String EXCEPCION_TRANSACCION_PAGINA = "sesion/transaccion";
    
    /**DESTINOS*/
    public static final String DESTINO_BANDEJA_ENTRADA = "bandeja";
    public static final String DESTINO_BUSQUEDA_COBRO = "busquedaCobro";
    public static final String DESTINO_VUELTA_BUSQUEDA = "vuelta-busqueda";
    
    /** Diferentes operaciones */
    public static final Integer CREATE = 0;
    public static final Integer UPDATE = 1;
    public static final Integer UNSUBSCRIBE = 2;
    public static final Integer DELETE = 3;
    public static final Integer DETAIL = 4;
    public static final Integer RECHAZAR_TALONARIO = 5;
    public static final Integer RENDIR_TALONARIO = 6;
    public static final Integer AUTORIZAR_RENDICION = 7;
    public static final Integer RECHAZAR_RENDICION = 8;
    public static final Integer CONFIRMAR_AVISO_PAGO = 9;
    public static final Integer RECHAZAR_AVISO_PAGO = 10;
    public static final Integer CREAR_VALOR_REGISTRO_AVC = 11;
    public static final Integer CONFIRMAR_VALOR_REGISTRO_AVC = 12;
    public static final Integer CONFIRMAR_ANULAR_REGISTRO_AVC = 13;
    public static final Integer RECHAZAR_ANULAR_REGISTRO_AVC = 14;
    public static final Integer SUBIR_ARCHIVO_OPERACION_MASIVA = 15;
    public static final Integer SUBIR_COMPROBANTE = 16;
    public static final Integer EDITAR_TRANSFERENCIA = 17;
    public static final Integer ANULAR_REGISTRO_AVC = 18;
    public static final Integer VALIDAR_NOMBRE_ARCHIVO_OPERACION_MASIVA = 19;
    public static final Integer BUSCAR_REGISTRO_AVC = 20;
        
    /** Perfiles */
    public static final String ANALISTA = TipoPerfilEnum.ANALISTA.descripcion();
    public static final String CAJERO_PAGADOR = TipoPerfilEnum.CAJERO_PAGADOR.descripcion();
    public static final String SUPERVISOR = TipoPerfilEnum.SUPERVISOR.descripcion();
    public static final String ADMIN_VALOR = TipoPerfilEnum.ADMIN_VALOR.descripcion();
    public static final String SUPERVISOR_ADMIN_VALOR = TipoPerfilEnum.SUPERVISOR_ADMIN_VALOR.descripcion();
    public static final String SUPERVISOR_TALONARIO = TipoPerfilEnum.SUPERVISOR_TALONARIO.descripcion();
    public static final String ADMIN_TALONARIO = TipoPerfilEnum.ADMIN_TALONARIO.descripcion();
    public static final String CONSULTA = TipoPerfilEnum.CONSULTA.descripcion();
    public static final String MANTENIMIENTO = TipoPerfilEnum.MANTENIMIENTO.descripcion();
    public static final String ANALISTA_COBRANZA = TipoPerfilEnum.ANALISTA_COBRANZA.descripcion();
    public static final String SUPERVISOR_COBRANZA = TipoPerfilEnum.SUPERVISOR_COBRANZA.descripcion();
    public static final String SUPERVISOR_COBRANZA_NAME = TipoPerfilEnum.SUPERVISOR_COBRANZA.name();
    public static final String REFERENTE_COBRANZA = TipoPerfilEnum.REFERENTE_COBRANZA.descripcion();
    public static final String ANALISTA_OPERACION_MASIVA = TipoPerfilEnum.ANALISTA_OPERACION_MASIVA.descripcion();
    public static final String SUPERVISOR_OPERACION_MASIVA = TipoPerfilEnum.SUPERVISOR_OPERACION_MASIVA.descripcion();
    public static final String SUPERVISOR_OPERACIONES_ESPECIALES = TipoPerfilEnum.SUPERVISOR_OPERACIONES_ESPECIALES.descripcion();
    public static final String ANALISTA_LEGAJOS_CHEQUES_RECHAZADOS = TipoPerfilEnum.ANALISTA_LEGAJOS_CHEQUES_RECHAZADOS.descripcion();
    public static final String REFERENTE_CAJA = TipoPerfilEnum.REFERENTE_CAJA.descripcion();
    public static final String REFERENTE_OPERACIONES_EXTERNAS = TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.descripcion();
    
    /** Datos Modificados */
    public static final String DATOS_MODIFICADOS_VALOR = "Valor";
    public static final String DATOS_MODIFICADOS_VALOR_ORIGINAL = "Valor Original";
    public static final String DATOS_MODIFICADOS_VALOR_MODIFICADO = "Valor Modificado";
    public static final String DATOS_MODIFICADOS_ESTADO = "Estado";
    public static final String DATOS_MODIFICADOS_OBSERVACIONES = "Observaciones";
    public static final String DATOS_MODIFICADOS_REFERENCIA = "Referencia";
    public static final String DATOS_MODIFICADOS_IMPORTE= "Importe";
    public static final String DATOS_MODIFICADOS_COMPENSACIONES= "Compensaciones";
    public static final String DATOS_MODIFICADOS_AGREGADOS= "Agregados";
    public static final String DATOS_MODIFICADOS_ELIMINADOS= "Eliminados";
    public static final String DATOS_MODIFICADOS_USUARIO_ANULACION= "Usuario Anulación";
    public static final String DATOS_MODIFICADOS_FECHA_ANULACION= "Fecha Anulación";
    public static final String DATOS_MODIFICADOS_NRO_RECIBO= "Número Recibo";
    public static final String DATOS_MODIFICADOS_ID_TALONARIO= "Id Talonario";
    public static final String DATOS_MODIFICADOS_FECHA_INGRESO= "Fecha Ingreso";
    public static final String DATOS_MODIFICADOS_OPERACION= "Operacion";
    public static final String DATOS_MODIFICADOS_PLANILLA_MOVIKA= "Planilla Movika";
    public static final String DATOS_MODIFICADOS_FACTURA= "Factura";
    public static final String DATOS_MODIFICADOS_ORIGEN= "Origen";
    
    /** Tipo Medio Pago */
    public static final String TIPO_MEDIO_PAGO_COMPENSACION= "06";
    public static final String TIPO_MEDIO_PAGO_DESISTIMIENTO= "16";
    public static final String TIPO_MEDIO_PAGO_TRASLADO_PROXIMA_FACTURA= "28";
    public static final String TIPO_MEDIO_PAGO_PLAN_DE_PAGO= "25";
    public static final String TIPO_MEDIO_PAGO_VALOR_SHIVA= "01";
    public static final String TIPO_MEDIO_PAGO_PAGO_A_CUENTA= "21";
    public static final String TIPO_MEDIO_PAGO_NOTA_CREDITO_MIC= "23";
    public static final String TIPO_MEDIO_PAGO_NOTA_CREDITO_CALIPSO= "22";
    
    
    public static final String PARAM_COMPENSACION_RAV = "cobros.compensacion.rav.";
    
    /** Timestamp */
    public static final String TIME_STAMP= "Timestamp";
    
    /** Posicion Perfil Ldap */
    public static final int POSICION_PERFIL_LDAP = 0;
    
    /** Posicion Empresa Ldap */
    public static final int POSICION_EMPRESA_LDAP = 1;
    
    /** Posicion Segmento Ldap */
    public static final int POSICION_SEGMENTO_LDAP = 2;
    
    /** Cantidad de tipo de valores */
    public static final int TIPO_VALOR = 10;
    
    /** Tiempos en milisegundos */
    public static final long $1_MIN_MS 		= 60000L;
    public static final long $Quince_MIN_MS = 900000L;
    public static final long $Media_HORA_MS = 1800000L;
    public static final long $1_HORA_MS 	= 3600000L;
    public static final long $1_SEMANA_MS 	= 6048000000000L;
    public static final long $2_SEMANAS_MS 	= 1209600000L;
    public static final long $6_MESES_MS 	= 15778500000L;
    
    public static final long $1_MIN_EN_SEGUNDOS	= 60;
    
    public static final long $1_HORA_EN_MINUTOS	= 60;
    
    public static final long $1_DIA_EN_HORAS	= 24;
    
    /** Tipo de Archivo */
    public static final String ARCHIVO_CSV = ".csv";
    public static final String ARCHIVO_TXT = ".txt";
    public static final String ARCHIVO_XML = ".xml";
    public static final String EXTENSION_ARCHIVO_ADJUNTO = "pdf";
    public static final String NOMBRE_ARCHIVO_DEFAULT_PDF  = "archivoPDF.pdf";
    public static final String PDF_CONTENT_TYPE = "application/pdf";
    
    /** Parametros */
    public static final String USUARIO_BATCH = "usuario.batch.id";
    public static final String USUARIO_NOMBRE_BATCH = "usuario.batch.nombre";
    
    public static final String USUARIO_BATCH_COBROS_IMPUTACION = "usuario.batch.cobros.imputacion.id";
    public static final String USUARIO_BATCH_COBROS_IMPUTACION_NOMBRE = "usuario.batch.cobros.imputacion.nombre";

    public static final String USUARIO_BATCH_COBROS_RECEPCION = "usuario.batch.cobros.recepcion.id";
    public static final String USUARIO_BATCH_COBROS_RECEPCION_NOMBRE = "usuario.batch.cobros.recepcion.nombre";
    
    public static final String USUARIO_MIGRACION = "usuario.migracion.id";
    public static final String USUARIO_MIGRACION_BATCH = "usuario.migracion.nombre";
    
    public static final String USUARIO_SAP = "usuario.sap.id";
    public static final String USUARIO_SAP_NOMBRE = "usuario.sap.nombre";
    
    public static final String DESTINATARIOS_MAIL_CAP = "destinatarios.mail.cap";
    
    /** Servicio de mensajeria Parte 1*/
    public static final String JMS_CANTIDAD_CONCURRENCIAS = "cantidad.concurrencias.jms";
    public static final String WS_CANTIDAD_TIMEOUT_SEGUNDOS = "cantidad.timeout.segundos.ws";
    public static final String WS_CANTIDAD_REINTENTOS = "cantidad.reintentos.ws";
    
    /** Servicio de mensajeria  Parte 2*/
    public static final String MSG_CANTIDAD_REINTENTOS = "cantidad.reintentos.msg";
    public static final String MSG_TIEMPO_DE_REINTENTO_MINUTOS = "tiempo.para.reintento.minutos.msg";
    
    /** Servicio de mensajeria  Parte 3*/
    public static final String CANTIDAD_CONCURRENCIAS_IMPUTACION_DESCOBROS = "cant.concurrencias.imputacion.descobros";
    public static final String CANTIDAD_CONCURRENCIAS_IMPUTACION_RESPUESTA_COBROS = "cant.concurrencias.imputacion.respuesta.cobros";
    public static final String CANTIDAD_CONCURRENCIAS_IMPUTACION_RESPUESTA_DESCOBROS = "cant.concurrencias.imputacion.respuesta.descobros";
    public static final String CANTIDAD_CONCURRENCIAS_IMPUTACION_RECEPCION_COBROS = "cant.concurrencias.imputacion.recepcion.cobros";
    public static final String CANTIDAD_CONCURRENCIAS_IMPUTACION_RECEPCION_DESCOBROS = "cant.concurrencias.imputacion.recepcion.descobros";
    
    public static final String	IMPUTACION_COBROS_CANT_HILOS_COBROS_CHICOS = "imputacion.cobros.cant.hilos.cobros.chicos";
    public static final String	IMPUTACION_COBROS_CANT_HILOS_COBROS_GRANDES = "imputacion.cobros.cant.hilos.cobros.grandes";
    public static final String	IMPUTACION_COBROS_CANT_TRANSACCIONES_COBROS_CHICOS = "imputacion.cobros.cant.transacciones.cobros.chicos";
    public static final String	IMPUTACION_COBROS_CANT_TRANSACCIONES_PROCESAR_HILO = "imputacion.cobros.cant.transacciones.procesar.hilo";
    public static final String	IMPUTACION_COBROS_PROCESAR_COBROS_CHICOS_EN_HILOS_COBROS_GRANDES = "imputacion.cobros.procesar.chicos.en.hilos.grandes";
    public static final String	IMPUTACION_COBROS_LIMITE_FINALIZAR_PROCESO = "imputacion.cobros.limite.finalizar.proceso";
    
    public static final String IMPUTACION_MANUAL_COBROS_CANT_HILOS = "imputacion.manual.cobros.cant.hilos";
    
    /** Parametros MQ */
    public static final String MIC_COD_RESP_OK = "7800";
    public static final String MIC_COD_PROCESO_OK = "7820";
    public static final String MIC_COD_TRANSACCION_YA_PROCESADA = "S031";
    public static final String CALIPSO_COD_TRANSACCION_YA_PROCESADA = "S031";
    public static final String DEIMOS_COD_TRANSACCION_YA_PROCESADA = "S031";
    
    public static final String CALIPSO_COD_DOCUMENTO_SIN_SALDO ="7017";
    
    public static final String MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION = "8101";
    public static final String MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS = "8050";
    public static final String MSG_COD_RESP_NO_SE_PUEDE_GENERAR_DOC_X_DIFERENCUIA_CAMBIO = "8043";
    public static final String MSJ_COD_RESP_SALDO_RESTANTE_SIN_SALDAR = "8077";
    
    /**longitud de la cadena */
    public static final int LONGITUD_NRO_RECIBO = 8;
    public static final int LONGITUD_NRO_SERIE = 4;
    public static final int LONGITUD_NRO_CLIENTE = 10;
    
    /** Aplicaciones Externas */
    public static final String CALIPSO_CLP = "CLP";
    public static final String CALIPSO_CAL = "CAL";
    public static final String MIC = "MIC";
    public static final String SAP = "SAP";

    /** Aplicacion local */
    public static final String SHIVA = "SHV";
    
    /** Codigo de error Generico*/
    public static final String ERROR_GENERICO_APROPIACION = "5001";
    /** Codigo de error de edicion de grilla de debitos y creditos*/
    public static final String ERROR_VALIDACION_EDICION_GRILLAS = "5002";
    public static final String ERROR_SALDO_MAXIMO_DEBITO = "5103";

    /** Codigo error descobro **/
    public static final String COD_DESCOBRO_ERROR_8010 = "8010";
    public static final String COD_DESCOBRO_ERROR_8012 = "8012";
    public static final String COD_DESCOBRO_ERROR_8200_OP_RELAC = "8200";
    
    /** EMPRESAS **/
    public static final String EMPRESA_TELECOM_ARGENTINA = "TA";
    public static final String EMPRESA_TELECOM_PERSONAL = "TP";
    public static final String EMPRESA_FIBERTEL = "FT";
    public static final String EMPRESA_CABLEVISION = "CV";
    public static final String EMPRESA_NEXTEL = "NX";
    
    /** Segmentos **/
    public static final String SEGMENTO_RESIDENCIAL = "OCO";
    public static final String SEGMENTO_TELEFONIA_PUBLICA = "OTP";
    public static final String SEGMENTO_PYME = "OGE";
    public static final String SEGMENTO_WHOLE_SALE = "OYP";
    public static final String SEGMENTO_GRANDES_CLIENTES = "OGC";
    
    /** Moneda **/
    public static final String MONEDA_$ = "$";
    public static final String MONEDA_U$D = "U$S";
    public static final String MONEDA_PES = "PES";
    public static final String MONEDA_DOL = "DOL";
    
    /** Tipo de cambio **/
    public static final String TIPO_DE_CAMBIO_POR_DEFECTO = "1";
    
    /** Origen **/
    public static final String ORIGEN_CONCILIACION_ID = "5";
    public static final String ORIGEN_REVERSION_ID = "7";
    
    public static final String ORIGEN_CONCILIACION_DESCRIPCION = "Conciliacion";
    
    /** Anular Boletas **/
	public static final String DIAS_ANTERIORIDAD_DEPURACION_BOLETA = "dias.anterioridad.depuracion.boleta";
		
	/** Team Comercial - Dias vigencia de un histórico **/
	public static final String TEAM_COMERCIAL_BATCH_DIAS_VIGENCIA_HIST = "team.comercial.dias.vigencia.historico";
		
	/** Errores de Batch */
	/** Error base de la aplicacion */
	public static final int SH_ERROR = 1; 
	/** Error de instancia */
	public static final int SH_ERROR_INSTANCIA = 1;
	/** Error de procesamiento total */
	public static final int SH_PROCESAMIENTO_PARCIAL = 2;
	
	/** Codigos de banco de Reversion de cobros */
	public static final String BANCO_GALICIA ="0007"; 
	public static final String BANCO_CIUDAD = "0029";
	public static final String BANCO_PROVINCIA_0148 ="0148"; 
	public static final String BANCO_PROVINCIA_0014 ="0014"; 
	
	/** Cantidad máxima permitida (en bytes) por archivo adjunto */
	public static final int TAMANIO_MAX_POR_ARCHIVO = (Integer.valueOf(Propiedades.VALIDACIONES_PROPIEDADES.getString("validacion.pesoMaximoPermitido")).intValue() * 1024 * 1024);
	
	public static final int CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO = 50;
	
	public static final String SEPARADOR_PIPE = "|";
	
	//Constantes fijas de caracteres
	public static final String SEPARADOR_CODIGO_CLIENTE_LEGADO = "|";
	public static final String UNDERSCORE = "_";
	public static final String WHITESPACE_AND_OPEN_PARENTESIS = " (";
	public static final String OPEN_PARENTESIS = "(";
	public static final String CLOSE_PARENTESIS = ")";
	public static final String BARRA = "/";
	public static final String BARRA_INVERTIDA = "\\" ;
	public static final String ASTERISK = "*";
	public static final String AMPERSAND = "&";
	public static final String PERCENT = "%";
	public static final String OPEN_LLAVE = "{";
	public static final String CLOSE_LLAVE = "}";
	
	public static final String OPEN_CORCHETES = "[";
	public static final String CLOSE_CORCHETES = "]";
	public static final String SEMICOLON = ";";
	public static final String COMMA = ",";
	public static final String PUNTO = ".";
	public static final String DOS_PUNTOS = ":";
	public static final String WHITESPACE = " ";
	public static final String EXCLAMATION_AND_WHITESPACE = "! ";
	public static final String EMPTY_STRING = "";
	public static final String CARRIAGE_RETURN = "\n";
	public static final String RETORNO_WIN = "\r\n";
	public static final String RETORNO_UNIX = "\n";
	public static final String CARRIAGE_RETURN_R = "\r";
	public static final String SIGNO_MENOS = "-";
	public static final String TAB = "\t";
	public static final String SIGNO_INTERROGACION = "?";
	
	/** Codigos de proceasmiento de archivos AVC */
	public static final String NO_GRABAR_REGISTROS_AVC = "1";
	public static final String GRABAR_REGISTROS_AVC = "2";
	public static final Integer CANTIDAD_INTENTOS_LOGIN_MISMA_PASS = 20;
	public static final Integer MINUTOS_INTENTOS_LOGIN_MISMA_PASS = 20;
	public static final Integer CANTIDAD_INTENTOS_LOGIN_MISMO_USUARIO = 3;
	public static final Integer TAMANIO_CACHE = 500;
	
	public static final String RETENCION_IIBB = "1";
		
	public static final String BANDEJA_ENTRADA_ID_FILTRO_TODOS = "1";
	public static final String BANDEJA_ENTRADA_ID_FILTRO_MIS_TAREAS = "2";
	public static final String BANDEJA_ENTRADA_ID_FILTRO_DERIVADAS = "3";
		
	/** Lista de campos a validar, esto sirve para cuando se quiere validar un campo en particular
	 * en un validador que tiene muchos campos que no necesitan ser validados
	 */
	public static final String VALIDAR_OBSERVACIONES_A_ENVIAR = "1";
	
	public static final String TODOS_LOS_SEGMENTOS = "TODOS_LOS_SEGMENTOS";
	public static final String TODOS_LOS_SEGMENTOS_ID = "todos";
	
	/**
	 * @author u573005, sprint3, constantes para mails
	 */
	public static final String AVISO_DE_PAGO = "Aviso de Pago";
	
	
	/**
	 * @author u562163, sprint5, exportacion de la busqueda de valores
	 */
	public static final String LIMITE_EXPORTACION_BUSQUEDA_VALORES = "valor.busqueda.exportacion.limite";
	public static final String LIMITE_EXPORTACION_BUSQUEDA_COBROS = "cobro.busqueda.exportacion.limite";
	
	/**
	 * @author u579607, sprint8, batch consulta siebel, registros
	 */
	public static final String CANT_REGISTROS_A_PROCESAR = "OpMas.Batch.ConsulSiebel.registros.AProcesar";
	public static final String CANT_REGISTROS_POR_VUELTA = "OpMas.Batch.ConsulSiebel.registros.PorVuelta";
	
	/** Constantes HTML casos que se usan en codigo Java
	 * 
	 */
	public static final String RETORNO_HTML = "<br>";
	 /** @author u573005, sprint 7, Codigo de error Generico de Descobros*/
    public static final String ERROR_GENERICO_DESCOBRO = "6001";
	
	public static final String JULIANO_ULTIMO_DIA = "9999365";
	public static final String JULIANO_PRIMER_DIA = "0001001";
	
	public static final String ACUERDO_CONCILIABLE = "S";
	public static final String ACUERDO_NO_CONCILIABLE = "N";

	/**
	 * @autho u578936, deberia hacer una archivo de propiedades
	 */
	public static final String OPERACION_MASIVA_MIC_ENTRADA_NOMBRE_ARCHIVO = "OperacionesMasivas.MIC.Entrada";
	public static final String OPERACION_MASIVA_MIC_SALIDA_NOMBRE_ARCHIVO = "OperacionesMasivas.MIC.Salida";
	public static final int OPERACION_MASIVA_MIC_CANT_CAMPOS_NOMBRE_ARCHIVO = 5;
	public static final String OPERACION_MASIVA_MIC_SALIDA_APP_NOMBRE_ARCHIVO = "SHIVA";
	public static final String OPERACION_MASIVA_MIC_ENTRADA_APP_NOMBRE_ARCHIVO = "SHIVA";
	public static final int TIPO_NUMERICO = 0;
	public static final int TIPO_ALFANUMERICO = 1;
	
	public static final int CERO = 0;
	public static final long CERO_PORCIENTO = 0l;
	public static final long CIEN_PORCIENTO = 100l;
	public static final String IS_MENU_PARAN_VALUE = "1";
	public static final String IS_MENU_PARAN_NAME = "p";
	
	public static final int OPERACION_MASIVA_CANTIDAD_REGISTROS = 110;
	
	// Se debe utilizar en los casos donde la respuesta del servicio venga incompleta, en tal caso 
	// asumimos directamente que existe un error.
	public static final String ERROR_INVOCACION_WS_RESPUESTA_INCONSISTENTE = "0100";
	
	// Carpeta "historico" para mover archivos una vez procesados.
	// Para su uso, se debe contatenar con la ruta en donde se encuentran procesando los archivos
	// por ejemplo, "cotizaciones/historico"
	public static final String CARPETA_HISTORICO = "historico";
	
	// Retornos de procesamiento batch
	public static final int PROCESO_BATCH_RETORNO_EXIT_OK = 0;
	public static final int PROCESO_BATCH_RETORNO_EXIT_ERROR = 1;
	public static final int PROCESO_BATCH_RETORNO_EXIT_WARNING = 2;
	
	public static final int CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO = 10;
	
	// Encoding Character Set
	public static final String ENCODING_CP1252 = "Cp1252";
	public static final String ENCODING_UTF8 = "UTF-8";
	
	//Batch Automaticacion de tarea de confirmacion aplicacion manual
	public static final String TIPO_REGISTRO_SALIDA_DETALLE_APLICACIONES_MANUALES_CUERPO = "01";
	public static final String TIPO_REGISTRO_SALIDA_DETALLE_APLICACIONES_MANUALES_PIE = "02";
	public static final String LISTA_EMPRESAS_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL = "automatizacionConfirmacionAplicacionManual.listEmpresasBatch";
	public static final String LISTA_GRUPO_MAIL_COMUN_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL = "automatizacionConfirmacionAplicacionManual.listGrupoMailEnComun";
	public static final String LISTA_GRUPO_MAIL_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL = "automatizacionConfirmacionAplicacionManual.listGrupoMailBatch";
	public static final String TIPO_OPERACION_DESCOBRO = "RCO";
	public static final String MEDIO_PAGO_SHIVA = "SHIVA";
	public static final String MEDIO_PAGO_NOTA_CREDITO = "NOTA DE CREDITO";
	public static final String MEDIO_PAGO_PAGO_CUENTA = "PAGO CUENTA CALIPSO";
	public static final String ESTADO_PENDIENTE = "PENDIENTE";
	
	//Mar
	public static final String LISTA_GRUPO_MAIL_BATCH_TAREAS_PENDIENTES_APLICACION_MANUAL = "reporteTareasAplicacionManualPendiente.listGrupoMailBatch";
	
	//Batch tareas Pendientes
	public static final  String LISTA_SOCIEDAD_SISTEMA_TAREAS_PENDIENTES_APLICACION_MANUAL = "reporteTareasAplicacionManualPendiente.listSocySis";

	//Batch Imputacion Automatica de Valores pertenecientes a Clientes Puros
	public static final String LISTA_EMPRESAS_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS = "imputacionAutomaticaValoresClientesPuros.listEmpresasBatch";
	public static final String LISTA_TIPO_VALOR_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS = "imputacionAutomaticaValoresClientesPuros.listTipoValorBatch";
	public static final String LISTA_ANALISTA_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS = "imputacionAutomaticaValoresClientesPuros.listAnalistaBatch";
	public static final String LISTA_GRUPO_MAIL_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS = "imputacionAutomaticaValoresClientesPuros.listGrupoMailBatch";
	public static final String MOTIVO_COBRO_SALIDA_AUTOMATICA_VALORES = "10";
	
	//Batch Reporte Aplicacion Manual Pendientes
	public static final String REPORTE_APLICACION_MANUAL_PENDIENTE_DIAS_A_RESTAR = "reporteAplicacionManualPendientes.diasARestar";
	public static final String PROCESO_BATCH_GENERAR_SALIDA_DETALLE_APLICACIONES_MANUALES ="shivaGenerarSalidaDetalleAplicacionesManuales";
	
	public static final String PROCESO_BATCH_PROCESAR_ENTRADA_DETALLE_APLICACIONES_MANUALES ="shivaProcesarEntradaDetalleAplicacionesManuales";
	
	public static final String PROCESO_BATCH_REPORTE_PERFILES_AUDITORIA ="shivaReportePerfilesAuditoria";
	public static final int CANTIDAD_MAXIMA_CARACTERES_SISTEMA = 20;
	public static final int CANTIDAD_MAXIMA_CARACTERES_REFERENCIA = 20;
	public static final int CANTIDAD_MAXIMA_CARACTERES_IMPORTE = 20;
	public static final int CANTIDAD_MAXIMA_CARACTERES_OPERACION_EXTERNA = 20;
	
	public static final String SI_REEDICION_PARCIAL = "SI";
	
	public static final String ID_MOTIVO_COMPENSACION = "9";
	public static final String sinDiferenciaDeCambio = "sinDiferenciaDeCambio";
	public static final String cliente = "cliente";
	
	public static final String CUENTA_CORRIENTE = "Cta Cte";
	public static final String CONJUNTO_DEBITOS = "Conj Deb";
	
	//Batch Reporte Perfiles Auditoria 
	public static final String SHIVA_INFO_USERS = "SHIVA_INFO_USERS.txt";
	public static final String DESTINATARIO_MAIL_REPORTE_PERFILES_AUDITORIA = "reportePerfilesAuditoria.listaMail";
	public static final String DESTINATARIO_COPIA_MAIL_REPORTE_PERFILES_AUDITORIA = "reportePerfilesAuditoria.listaMailCopia";
	public static final String ESPANOL = "ES";
	
	public static final String TRUE = "true";
	
	public static final String LISTA_SOCIEDAD_SISTEMA_AGRUPAMIENTO_NRO_TRANSACCION_FICTICIO= "simulacionCobros.agrupamientoPorNumeroTransaccionFicticio";
	
	public static final String CONTROL_HILOS_COBROS_PARAMETRO_HORA_LIMITE="control.hilos.cobros.parametro.hora.limite";
	
	public static final String CONTROL_HILOS_COBROS_PARAMETRO_MAIL_DESTINATARIO="control.hilos.cobros.parametro.mail.destinatario";
	
	public static final String CONTROL_HILOS_COBROS_PARAMETRO_MAIL_DESTINATARIO_CC="control.hilos.cobros.parametro.mail.destinatario.cc";
	
	public static final String CONTROL_HILOS_COBROS_PARAMETRO_ESTADOS_COBRO="control.hilos.cobros.parametro.estados.cobro";
	
	public static final String CONTROL_HILOS_COBROS_PARAMETRO_HORA_ENVIO_MAIL_ESTADOS_COBRO="control.hilos.cobros.parametro.hora.envio.mail.estados.cobro";
}
