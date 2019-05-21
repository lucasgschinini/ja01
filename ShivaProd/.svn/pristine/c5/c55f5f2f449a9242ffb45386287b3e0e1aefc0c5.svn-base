CREATE OR REPLACE VIEW SHV_SOP_BANDEJA_DE_ENTRADA (ID_TAREA, TIPO_TAREA, ESTADO_TAREA, FECHA_CREACION, USUARIO_CREACION, FECHA_EJECUCION, USUARIO_EJECUCION, INFORMACION_ADICIONAL, GESTIONA_SOBRE, PERFIL_ASIGNACION, USUARIO_ASIGNACION, ID_WORKFLOW_ASOCIADO, NUMERO_CLIENTE, RAZON_SOCIAL, IMPORTE, ESTADO_WORKFLOW_ASOCIADO, ID_REGISTRO_AVC, ID_VALOR_POR_REVERSION, ID_VALOR, ID_TALONARIO, ID_OPERACION_MASIVA, ID_COBRO, ID_OPERACION, ID_DESCOBRO, ID_EMPRESA, DESCRIPCION_EMPRESA, ID_SEGMENTO, DESCRIPCION_SEGMENTO, NUMERO_BOLETA, DESCRIPCION_BANCO, NUMERO_CHEQUE, FECHA_VENCIMIENTO, CODIGO_INTERDEPOSITO, REFERENCIA, TIPO, NRO_CONSTANCIA, CUIT, PROVINCIA, COD_ORGANISMO, RANGO)
AS
  (
  -- SPRINT 7, u573005, fabio.giaquinta.ruiz, SE AGREGA EL CAMPO ID_OPERACION PARA EL REENVIO DEL COBRO CUANDO ESTA EN ERROR EN DESAPROPIACION
  -- SPRINT 7, u573005, fabio.giaquinta.ruiz, SE AGREGA EL UNION DE TAREAS DE DESCOBROS
  -- INCLUYE CORRECCION DEFECTO 83 Y 84 SPRINT 3 U573005
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA_DEPOSITO_CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA)    AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                AS DESCRIPCION_BANCO,
    TO_CHAR(BOLDEPCHE.NUMERO_CHEQUE) AS NUMERO_CHEQUE,
    NULL                             AS FECHA_VENCIMIENTO,
    NULL                             AS CODIGO_INTERDEPOSITO,
    NULL                             AS REFERENCIA,
    NULL                             AS TIPO,
    NULL                             AS NRO_CONSTANCIA,
    NULL                             AS CUIT,
    NULL                             AS PROVINCIA,
    NULL                             AS COD_ORGANISMO,
    NULL                             AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_CHEQUE BOLDEPCHE,
    SHV_BOL_BOLETA BOLETA,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA       = SWTV.ID_TAREA
  AND SVV.ID_VALOR       = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW    = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA     = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO    = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR  = 2
  AND BOLDEPCHE.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA   = BOLDEPCHE.ID_BOLETA
  AND BANCO.ID_BANCO     = SUBSTR('0000'
    || BOLDEPCHE.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA_DEPOSITO_CHEQUE_DIFERIDO(3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA)                        AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                    AS DESCRIPCION_BANCO,
    NULL                                                 AS NUMERO_CHEQUE,
    TO_CHAR(BOLDEPCHEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                                 AS CODIGO_INTERDEPOSITO,
    TO_CHAR(BOLDEPCHEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                                 AS TIPO,
    NULL                                                 AS NRO_CONSTANCIA,
    NULL                                                 AS CUIT,
    NULL                                                 AS PROVINCIA,
    NULL                                                 AS COD_ORGANISMO,
    NULL                                                 AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_CHEQUE_PD BOLDEPCHEPD,
    SHV_BOL_BOLETA BOLETA,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA         = SWTV.ID_TAREA
  AND SVV.ID_VALOR         = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA       = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO      = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR    = 3
  AND BOLDEPCHEPD.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA     = BOLDEPCHEPD.ID_BOLETA
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || BOLDEPCHEPD.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA DEPOSITO EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                          AS DESCRIPCION_BANCO,
    NULL                          AS NUMERO_CHEQUE,
    NULL                          AS FECHA_VENCIMIENTO,
    NULL                          AS CODIGO_INTERDEPOSITO,
    TO_CHAR(BOLETA.NUMERO_BOLETA) AS REFERENCIA,
    NULL                          AS TIPO,
    NULL                          AS NRO_CONSTANCIA,
    NULL                          AS CUIT,
    NULL                          AS PROVINCIA,
    NULL                          AS COD_ORGANISMO,
    NULL                          AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_EFECTIVO BOLDEPEFECTIVO,
    SHV_BOL_BOLETA BOLETA
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
    -- WHERE DE VALORES
  AND SWT.ID_TAREA            = SWTV.ID_TAREA
  AND SVV.ID_VALOR            = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA          = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO         = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR       = 4
  AND BOLDEPEFECTIVO.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA        = BOLDEPEFECTIVO.ID_BOLETA
  AND SWT.ESTADO              = 'PENDIENTE'
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(VALORCHEQUE.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                  AS DESCRIPCION_BANCO,
    NULL                               AS NUMERO_CHEQUE,
    NULL                               AS FECHA_VENCIMIENTO,
    NULL                               AS CODIGO_INTERDEPOSITO,
    TO_CHAR(VALORCHEQUE.NUMERO_CHEQUE) AS REFERENCIA,
    NULL                               AS TIPO,
    NULL                               AS NRO_CONSTANCIA,
    NULL                               AS CUIT,
    NULL                               AS PROVINCIA,
    NULL                               AS COD_ORGANISMO,
    NULL                               AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_CHEQUE VALORCHEQUE,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA         = SWTV.ID_TAREA
  AND SVV.ID_VALOR         = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA       = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO      = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR    = 5
  AND VALORCHEQUE.ID_VALOR = SVV.ID_VALOR
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || VALORCHEQUE.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    NULL AS ID_OPERACION,
    -- COBROS
    NULL AS ID_COBRO,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(VALORCHEQUEPD.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                      AS DESCRIPCION_BANCO,
    NULL                                                   AS NUMERO_CHEQUE,
    TO_CHAR(VALORCHEQUEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                                   AS CODIGO_INTERDEPOSITO,
    TO_CHAR(VALORCHEQUEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                                   AS TIPO,
    NULL                                                   AS NRO_CONSTANCIA,
    NULL                                                   AS CUIT,
    NULL                                                   AS PROVINCIA,
    NULL                                                   AS COD_ORGANISMO,
    NULL                                                   AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_CHEQUE_PD VALORCHEQUEPD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA           = SWTV.ID_TAREA
  AND SVV.ID_VALOR           = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW        = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA         = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO        = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR      = 6
  AND VALORCHEQUEPD.ID_VALOR = SVV.ID_VALOR
  AND BANCO.ID_BANCO         = SUBSTR('0000'
    || VALORCHEQUEPD.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - EFECTIVO (7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(EFECT.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                         AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(EFECT.NUMERO_BOLETA) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_EFECTIVO EFECT
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 7
  AND EFECT.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                              AS NUMERO_BOLETA,
    NULL                              AS DESCRIPCION_BANCO,
    NULL                              AS NUMERO_CHEQUE,
    NULL                              AS FECHA_VENCIMIENTO,
    NULL                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(TRANSF.NUMERO_REFERENCIA) AS REFERENCIA,
    NULL                              AS TIPO,
    NULL                              AS NRO_CONSTANCIA,
    NULL                              AS CUIT,
    NULL                              AS PROVINCIA,
    NULL                              AS COD_ORGANISMO,
    NULL                              AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_TRANSFERENCIA TRANSF
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 8
  AND TRANSF.ID_VALOR   = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(INTER.NUMERO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    INTER.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_INTERDEPOSITO INTER
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 9
  AND INTER.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - RETENCION IMPUESTO(10)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                  AS NUMERO_BOLETA,
    NULL                                  AS DESCRIPCION_BANCO,
    NULL                                  AS NUMERO_CHEQUE,
    NULL                                  AS FECHA_VENCIMIENTO,
    NULL                                  AS CODIGO_INTERDEPOSITO,
    TO_CHAR(RET.NRO_CONSTANCIA_RETENCION) AS REFERENCIA,
    TIPORET.DESCRIPCION                   AS TIPO,
    NULL                                  AS NRO_CONSTANCIA,
    RET.CUIT                              AS CUIT,
    JURI.DESCRIPCION                      AS PROVINCIA,
    NULL                                  AS COD_ORGANISMO,
    NULL                                  AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_RETENCION RET,
    SHV_PARAM_TIPO_RET_IMPUESTO TIPORET,
    SHV_PARAM_JURISDICCION JURI
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA                       = SWTV.ID_TAREA
  AND SVV.ID_VALOR                       = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW                    = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA                     = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO                    = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR                  = 10
  AND RET.ID_VALOR                       = SVV.ID_VALOR
  AND TIPORET.ID_TIPO_RETENCION_IMPUESTO = RET.ID_TIPO_RETENCION_IMPUESTO
  AND RET.ID_JURISDICCION                = JURI.ID_JURISDICCION (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPOSITO.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION               AS DESCRIPCION_BANCO,
    NULL                            AS NUMERO_CHEQUE,
    NULL                            AS FECHA_VENCIMIENTO,
    NULL                            AS CODIGO_INTERDEPOSITO,
    TO_CHAR(CHEQUE.NUMERO_CHEQUE)   AS REFERENCIA,
    NULL                            AS TIPO,
    NULL                            AS NRO_CONSTANCIA,
    NULL                            AS CUIT,
    NULL                            AS PROVINCIA,
    NULL                            AS COD_ORGANISMO,
    NULL                            AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_CHEQUE CHEQUE,
    SHV_AVC_REG_AVC_DEPOSITO DEPOSITO,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC     = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR       = 2
  AND CHEQUE.ID_REGISTRO_AVC   = SARA.ID_REGISTRO_AVC
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || CHEQUE.CODIGO_BANCO, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - CHEQUE DIFERIDO (3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPOSITO.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                 AS DESCRIPCION_BANCO,
    NULL                                              AS NUMERO_CHEQUE,
    TO_CHAR(CHEQUEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(CHEQUEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                              AS TIPO,
    NULL                                              AS NRO_CONSTANCIA,
    NULL                                              AS CUIT,
    NULL                                              AS PROVINCIA,
    NULL                                              AS COD_ORGANISMO,
    NULL                                              AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_CHEQUE_PD CHEQUEPD,
    SHV_AVC_REG_AVC_DEPOSITO DEPOSITO,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC     = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR       = 3
  AND CHEQUEPD.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || CHEQUEPD.CODIGO_BANCO, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPO.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                        AS DESCRIPCION_BANCO,
    NULL                        AS NUMERO_CHEQUE,
    NULL                        AS FECHA_VENCIMIENTO,
    NULL                        AS CODIGO_INTERDEPOSITO,
    TO_CHAR(DEPO.NUMERO_BOLETA) AS REFERENCIA,
    NULL                        AS TIPO,
    NULL                        AS NRO_CONSTANCIA,
    NULL                        AS CUIT,
    NULL                        AS PROVINCIA,
    NULL                        AS COD_ORGANISMO,
    NULL                        AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_DEPOSITO DEPO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW     = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR   = 4
  AND DEPO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - TRANSFERENCIA (8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                      AS NUMERO_BOLETA,
    NULL                      AS DESCRIPCION_BANCO,
    NULL                      AS NUMERO_CHEQUE,
    NULL                      AS FECHA_VENCIMIENTO,
    NULL                      AS CODIGO_INTERDEPOSITO,
    TO_CHAR(TRANS.REFERENCIA) AS REFERENCIA,
    NULL                      AS TIPO,
    NULL                      AS NRO_CONSTANCIA,
    NULL                      AS CUIT,
    NULL                      AS PROVINCIA,
    NULL                      AS COD_ORGANISMO,
    NULL                      AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_TRANSFERENCIA TRANS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC  = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR    = 8
  AND TRANS.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - INTERDEPOSITO (9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(INTER.CODIGO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    INTER.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_INTERDEPOSITO INTER
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC  = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR    = 9
  AND INTER.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION            AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_CHEQUE) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 5
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || SVVPR.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                              AS DESCRIPCION_BANCO,
    NULL                                           AS NUMERO_CHEQUE,
    TO_CHAR(SVVPR.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                           AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                           AS TIPO,
    NULL                                           AS NRO_CONSTANCIA,
    NULL                                           AS CUIT,
    NULL                                           AS PROVINCIA,
    NULL                                           AS COD_ORGANISMO,
    NULL                                           AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 6
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || SVVPR.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - EFECTIVO(7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                         AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 7
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                             AS NUMERO_BOLETA,
    NULL                             AS DESCRIPCION_BANCO,
    NULL                             AS NUMERO_CHEQUE,
    NULL                             AS FECHA_VENCIMIENTO,
    NULL                             AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_REFERENCIA) AS REFERENCIA,
    NULL                             AS TIPO,
    NULL                             AS NRO_CONSTANCIA,
    NULL                             AS CUIT,
    NULL                             AS PROVINCIA,
    NULL                             AS COD_ORGANISMO,
    NULL                             AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 8
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.CODIGO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    SVVPR.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 9
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TALONARIOS
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL AS NUMERO_CLIENTE,
    NULL AS RAZON_SOCIAL,
    NULL AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    STT.ID_TALONARIO AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    NULL AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL AS NUMERO_BOLETA,
    NULL AS DESCRIPCION_BANCO,
    NULL AS NUMERO_CHEQUE,
    NULL AS FECHA_VENCIMIENTO,
    NULL AS CODIGO_INTERDEPOSITO,
    NULL AS REFERENCIA,
    NULL AS TIPO,
    NULL AS NRO_CONSTANCIA,
    NULL AS CUIT,
    NULL AS PROVINCIA,
    NULL AS COD_ORGANISMO,
    SUBSTR('0000'
    || STT.NUMERO_SERIE, -4)
    || '-'
    || SUBSTR('00000000'
    || STT.RANGO_DESDE, -8)
    || ' A '
    || SUBSTR('0000'
    || STT.NUMERO_SERIE, -4)
    || '-'
    || SUBSTR('00000000'
    || STT.RANGO_HASTA, -8) AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE TALONARIOS
    SHV_WF_TAREA_TALONARIO SWTT,
    SHV_TAL_TALONARIO STT,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE TALONARIOS
  AND SWT.ID_TAREA     = SWTT.ID_TAREA
  AND STT.ID_TALONARIO = SWTT.ID_TALONARIO
  AND STT.ID_WORKFLOW  = SWFEENTIDAD.ID_WORKFLOW
  AND STT.ID_EMPRESA   = SPE.ID_EMPRESA (+)
  AND STT.ID_SEGMENTO  = SPS.ID_SEGMENTO (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TAREA-COBRO
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) = 'ANALISTACOBRANZA'
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SWTC.ID_CLIENTE_LEGADO    AS NUMERO_CLIENTE,
    SWTC.RAZON_SOCIAL_CLIENTE AS RAZON_SOCIAL,
    SWTC.IMPORTE              AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    SCEC.ID_COBRO AS ID_COBRO,
    NULL          AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                       AS NUMERO_BOLETA,
    NULL                       AS DESCRIPCION_BANCO,
    NULL                       AS NUMERO_CHEQUE,
    NULL                       AS FECHA_VENCIMIENTO,
    NULL                       AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SCEC.ID_OPERACION) AS REFERENCIA,
    NULL                       AS TIPO,
    NULL                       AS NRO_CONSTANCIA,
    NULL                       AS CUIT,
    NULL                       AS PROVINCIA,
    NULL                       AS COD_ORGANISMO,
    NULL                       AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE COBROS
    SHV_WF_TAREA_COBRO SWTC,
    SHV_COB_ED_COBRO SCEC,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE COBROS
  AND SWT.ID_TAREA     = SWTC.ID_TAREA
  AND SCEC.ID_COBRO    = SWTC.ID_COBRO
  AND SCEC.ID_WORKFLOW = SWFEENTIDAD.ID_WORKFLOW
  AND SWT.TIPO_TAREA  <> 'COB_REV_COB_DES'
  AND SCEC.ID_EMPRESA  = SPE.ID_EMPRESA (+)
  AND SCEC.ID_SEGMENTO = SPS.ID_SEGMENTO (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TAREA-COBRO ERROR DESAPROPIACION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) = 'ANALISTACOBRANZA'
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SWTC.ID_CLIENTE_LEGADO    AS NUMERO_CLIENTE,
    SWTC.RAZON_SOCIAL_CLIENTE AS RAZON_SOCIAL,
    SWTC.IMPORTE              AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    SCEC.ID_COBRO     AS ID_COBRO,
    SCEC.ID_OPERACION AS ID_OPERACION,
    -- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                       AS NUMERO_BOLETA,
    NULL                       AS DESCRIPCION_BANCO,
    NULL                       AS NUMERO_CHEQUE,
    NULL                       AS FECHA_VENCIMIENTO,
    NULL                       AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SCEC.ID_OPERACION) AS REFERENCIA,
    NULL                       AS TIPO,
    NULL                       AS NRO_CONSTANCIA,
    NULL                       AS CUIT,
    NULL                       AS PROVINCIA,
    NULL                       AS COD_ORGANISMO,
    NULL                       AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE COBROS
    SHV_WF_TAREA_COBRO SWTC,
    SHV_COB_ED_COBRO SCEC,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE COBROS
  AND SWT.ID_TAREA     = SWTC.ID_TAREA
  AND SCEC.ID_COBRO    = SWTC.ID_COBRO
  AND SCEC.ID_WORKFLOW = SWFEENTIDAD.ID_WORKFLOW
  AND SWT.TIPO_TAREA   = 'COB_REV_COB_DES'
  AND SCEC.ID_EMPRESA  = SPE.ID_EMPRESA (+)
  AND SCEC.ID_SEGMENTO = SPS.ID_SEGMENTO (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TAREA-DESCOBRO
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) = 'ANALISTACOBRANZA'
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SWTD.ID_CLIENTE_LEGADO    AS NUMERO_CLIENTE,
    SWTD.RAZON_SOCIAL_CLIENTE AS RAZON_SOCIAL,
    SWTD.IMPORTE              AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
    -- DESCOBROS
    SCD.ID_OPERACION AS ID_OPERACION,
    SCD.ID_DESCOBRO  AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                      AS NUMERO_BOLETA,
    NULL                      AS DESCRIPCION_BANCO,
    NULL                      AS NUMERO_CHEQUE,
    NULL                      AS FECHA_VENCIMIENTO,
    NULL                      AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SCD.ID_OPERACION) AS REFERENCIA,
    NULL                      AS TIPO,
    NULL                      AS NRO_CONSTANCIA,
    NULL                      AS CUIT,
    NULL                      AS PROVINCIA,
    NULL                      AS COD_ORGANISMO,
    NULL                      AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE DESCOBROS
    SHV_WF_TAREA_DESCOBRO SWTD,
    SHV_COB_DESCOBRO SCD,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE DESCOBROS
  AND SWT.ID_TAREA    = SWTD.ID_TAREA
  AND SCD.ID_DESCOBRO = SWTD.ID_DESCOBRO
  AND SCD.ID_WORKFLOW = SWFEENTIDAD.ID_WORKFLOW
  AND SCD.ID_EMPRESA  = SPE.ID_EMPRESA (+)
  and SCD.ID_SEGMENTO = SPS.ID_SEGMENTO (+)
  );


Exit;