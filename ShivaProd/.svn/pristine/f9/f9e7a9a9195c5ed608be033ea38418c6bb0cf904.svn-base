CREATE OR REPLACE VIEW SHV_MAS_OPER_MASIV_BUSQUEDA (ID_OPERACION_MASIVA, ID_EMPRESA, ID_SEGMENTO, NOMBRE_ARCHIVO, ESTADO, TIPO_OPERACION, FECHA_MODIFICACION, ID_MOTIVO, DESCRIPCION_MOTIVO, ID_ANALISTA, ID_COPROPIETARIO, FECHA_CREACION, FECHA_DERIVACION, FECHA_AUTORIZACION, FECHA_PROCESO, REGISTROS_INGRESADOS, REGISTROS_PROCESADOS_OK, REGISTROS_PROCESADOS_ERROR)
                                  AS
  SELECT SMOM.ID_OPERACION_MASIVA AS idOperacionMasiva,
    SMOM.ID_EMPRESA               AS empresa,
    SMOM.ID_SEGMENTO              AS segmento,
    SMOMA.NOMBRE_ARCHIVO          AS nombreArchivo,
    SWWE.ESTADO                   AS estado,
    SMOM.TIPO_OPERACION           AS tipoOperacion,
    SWWE.FECHA_MODIFICACION       AS fechaModificacion,
    SMOM.ID_MOTIVO                AS idMotivo,
    SPMC.DESCRIPCION              AS descripcionMotivo,
    SMOM.ID_ANALISTA              AS idAnalista,
    SMOM.ID_COPROPIETARIO         AS idCopropietario,
    SMOM.FECHA_CREACION           AS fechaCreacion,
    SMOMA.FECHA_DERIVACION        AS fechaDerivacion,
    SMOMA.FECHA_AUTORIZACION      AS fechaAutorizacion,
    SMOMA.FECHA_PROCESO           AS fechaProceso,
    (SELECT COUNT(SMR.ID_REGISTRO)
    FROM SHV_MAS_REGISTRO SMR
    WHERE SMOM.ID_OPERACION_MASIVA = SMR.ID_OPERACION_MASIVA
    ) AS registrosIngresados,
    (SELECT COUNT(SMR.ID_REGISTRO)
    FROM SHV_MAS_REGISTRO SMR
    WHERE SMOM.ID_OPERACION_MASIVA = SMR.ID_OPERACION_MASIVA
    AND SMR.ESTADO                 = 'PROCESADO'
    ) AS registrosProcesados,
    (SELECT COUNT(SMR.ID_REGISTRO)
    FROM SHV_MAS_REGISTRO SMR
    WHERE SMOM.ID_OPERACION_MASIVA = SMR.ID_OPERACION_MASIVA
    AND SMR.ESTADO                IN ('ERROR_MIC','ERROR_SIEBEL')
    ) AS registrosConError
  FROM SHV_MAS_OPER_MASIVA SMOM
  INNER JOIN SHV_MAS_OPER_MASIVA_ARCH SMOMA
  ON SMOM.ID_OPERACION_MASIVA = SMOMA.ID_OPERACION_MASIVA
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SMOM.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SMOM.ID_MOTIVO = SPMC.ID_MOTIVO_COBRO;
  
  
  
 CREATE OR REPLACE VIEW SHV_SOP_OPER_MASIVA_HISTORIAL (ID_OPERACION_MASIVA, ESTADO, USUARIO_MODIFICACION, FECHA_MODIFICACION, OBSERVACIONES)
AS
  (SELECT oper_masiva.ID_OPERACION_MASIVA,
    wf_estado.ESTADO,
    wf_estado.USUARIO_MODIFICACION,
    TO_CHAR(wf_estado.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
    NULL                                                           AS observaciones
  FROM SHV_WF_WORKFLOW_ESTADO wf_estado
  INNER JOIN SHV_MAS_OPER_MASIVA oper_masiva
  ON (wf_estado.ID_WORKFLOW = oper_masiva.ID_WORKFLOW)
  WHERE wf_estado.estado   <> 'MAS_CONFIGURACION'
  UNION
  SELECT oper_masiva.ID_OPERACION_MASIVA,
    wf_estado_hist.ESTADO,
    wf_estado_hist.USUARIO_MODIFICACION,
    TO_CHAR(wf_estado_hist.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
    NULL                                                                AS observaciones
  FROM shv_wf_workflow_estado_hist wf_estado_hist
  INNER JOIN SHV_MAS_OPER_MASIVA oper_masiva
  ON (wf_estado_hist.ID_WORKFLOW = oper_masiva.ID_WORKFLOW)
  WHERE wf_estado_hist.estado   <> 'MAS_CONFIGURACION'
  );
  
  exit;