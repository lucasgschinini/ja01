CREATE OR REPLACE VIEW SHV_SOP_BANDEJA_DE_ENTRADA (ID_TAREA, TIPO_TAREA, ESTADO_TAREA, FECHA_CREACION, USUARIO_CREACION, FECHA_EJECUCION, USUARIO_EJECUCION, INFORMACION_ADICIONAL, GESTIONA_SOBRE, PERFIL_ASIGNACION, USUARIO_ASIGNACION, ID_WORKFLOW_ASOCIADO, NUMERO_CLIENTE, RAZON_SOCIAL, IMPORTE, ESTADO_WORKFLOW_ASOCIADO, ID_REGISTRO_AVC, ID_VALOR_POR_REVERSION, ID_VALOR, ID_TALONARIO, ID_OPERACION_MASIVA, ID_COBRO, ID_EMPRESA, DESCRIPCION_EMPRESA, ID_SEGMENTO, DESCRIPCION_SEGMENTO, NUMERO_BOLETA, DESCRIPCION_BANCO, NUMERO_CHEQUE, FECHA_VENCIMIENTO, CODIGO_INTERDEPOSITO, REFERENCIA, TIPO, NRO_CONSTANCIA, CUIT, PROVINCIA, COD_ORGANISMO, RANGO)
AS
  (
  -- Incluye correccion defecto 83 y 84 sprint 3 u573005
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - BOLETA_DEPOSITO_CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(boleta.numero_boleta)    AS numero_boleta,
    banco.descripcion                AS descripcion_banco,
    TO_CHAR(boldepche.numero_cheque) AS numero_cheque,
    NULL                             AS fecha_vencimiento,
    NULL                             AS codigo_interdeposito,
    NULL                             AS referencia,
    NULL                             AS tipo,
    NULL                             AS nro_constancia,
    NULL                             AS cuit,
    NULL                             AS provincia,
    NULL                             AS cod_organismo,
    NULL                             AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_boleta_dep_cheque boldepche,
    shv_bol_boleta boleta,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea       = swtv.id_tarea
  AND svv.id_valor       = swtv.id_valor
  AND svv.id_workflow    = swfeEntidad.id_workflow
  AND svv.id_empresa     = spe.id_empresa (+)
  AND svv.id_segmento    = sps.id_segmento (+)
  AND svv.id_tipo_valor  = 2
  AND boldepche.id_valor = svv.id_valor
  AND boleta.id_boleta   = boldepche.id_boleta
  AND BANCO.ID_BANCO     = SUBSTR('0000'
    || boldepche.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - BOLETA_DEPOSITO_CHEQUE_DIFERIDO(3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(boleta.numero_boleta)                        AS numero_boleta,
    BANCO.DESCRIPCION                                    AS DESCRIPCION_BANCO,
    NULL                                                 AS numero_cheque,
    TO_CHAR(boldepchepd.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                                 AS CODIGO_INTERDEPOSITO,
    TO_CHAR(boldepchepd.numero_cheque)                   AS referencia,
    NULL                                                 AS tipo,
    NULL                                                 AS nro_constancia,
    NULL                                                 AS cuit,
    NULL                                                 AS provincia,
    NULL                                                 AS cod_organismo,
    NULL                                                 AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_boleta_dep_cheque_pd boldepchepd,
    shv_bol_boleta boleta,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea         = swtv.id_tarea
  AND svv.id_valor         = swtv.id_valor
  AND svv.id_workflow      = swfeEntidad.id_workflow
  AND svv.id_empresa       = spe.id_empresa (+)
  AND svv.id_segmento      = sps.id_segmento (+)
  AND svv.id_tipo_valor    = 3
  AND boldepchepd.id_valor = svv.id_valor
  AND boleta.id_boleta     = boldepchepd.id_boleta
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || boldepchepd.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - BOLETA DEPOSITO EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(boleta.numero_boleta) AS numero_boleta,
    NULL                          AS descripcion_banco,
    NULL                          AS numero_cheque,
    NULL                          AS fecha_vencimiento,
    NULL                          AS codigo_interdeposito,
    TO_CHAR(boleta.numero_boleta) AS referencia,
    NULL                          AS tipo,
    NULL                          AS nro_constancia,
    NULL                          AS cuit,
    NULL                          AS provincia,
    NULL                          AS cod_organismo,
    NULL                          AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    SHV_VAL_VALOR SVV,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_boleta_dep_efectivo boldepefectivo,
    shv_bol_boleta boleta
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
    -- where de Valores
  AND swt.id_tarea            = swtv.id_tarea
  AND SVV.ID_VALOR            = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND svv.id_empresa          = spe.id_empresa (+)
  AND svv.id_segmento         = sps.id_segmento (+)
  AND svv.id_tipo_valor       = 4
  AND boldepefectivo.id_valor = svv.id_valor
  AND BOLETA.ID_BOLETA        = BOLDEPEFECTIVO.ID_BOLETA
  AND SWT.ESTADO              = 'PENDIENTE'
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(valorcheque.numero_boleta) AS numero_boleta,
    BANCO.DESCRIPCION                  AS DESCRIPCION_BANCO,
    NULL                               AS numero_cheque,
    NULL                               AS fecha_vencimiento,
    NULL                               AS CODIGO_INTERDEPOSITO,
    TO_CHAR(valorcheque.numero_cheque) AS referencia,
    NULL                               AS tipo,
    NULL                               AS nro_constancia,
    NULL                               AS cuit,
    NULL                               AS provincia,
    NULL                               AS cod_organismo,
    NULL                               AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_cheque valorcheque,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea         = swtv.id_tarea
  AND svv.id_valor         = swtv.id_valor
  AND svv.id_workflow      = swfeEntidad.id_workflow
  AND svv.id_empresa       = spe.id_empresa (+)
  AND SVV.ID_SEGMENTO      = SPS.ID_SEGMENTO (+)
  AND svv.id_tipo_valor    = 5
  AND VALORCHEQUE.ID_VALOR = SVV.ID_VALOR
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || VALORCHEQUE.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(valorchequepd.numero_boleta)                   AS numero_boleta,
    BANCO.DESCRIPCION                                      AS DESCRIPCION_BANCO,
    NULL                                                   AS numero_cheque,
    TO_CHAR(valorchequepd.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                                   AS CODIGO_INTERDEPOSITO,
    TO_CHAR(valorchequepd.numero_cheque)                   AS referencia,
    NULL                                                   AS tipo,
    NULL                                                   AS nro_constancia,
    NULL                                                   AS cuit,
    NULL                                                   AS provincia,
    NULL                                                   AS cod_organismo,
    NULL                                                   AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_cheque_pd valorchequepd,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea           = swtv.id_tarea
  AND svv.id_valor           = swtv.id_valor
  AND svv.id_workflow        = swfeEntidad.id_workflow
  AND svv.id_empresa         = spe.id_empresa (+)
  AND SVV.ID_SEGMENTO        = SPS.ID_SEGMENTO (+)
  AND svv.id_tipo_valor      = 6
  AND valorchequepd.id_valor = svv.id_valor
  AND BANCO.ID_BANCO         = SUBSTR('0000'
    || valorchequepd.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - EFECTIVO (7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(efect.numero_boleta) AS numero_boleta,
    NULL                         AS descripcion_banco,
    NULL                         AS numero_cheque,
    NULL                         AS fecha_vencimiento,
    NULL                         AS codigo_interdeposito,
    TO_CHAR(efect.numero_boleta) AS referencia,
    NULL                         AS tipo,
    NULL                         AS nro_constancia,
    NULL                         AS cuit,
    NULL                         AS provincia,
    NULL                         AS cod_organismo,
    NULL                         AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_efectivo efect
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea      = swtv.id_tarea
  AND svv.id_valor      = swtv.id_valor
  AND svv.id_workflow   = swfeEntidad.id_workflow
  AND svv.id_empresa    = spe.id_empresa (+)
  AND svv.id_segmento   = sps.id_segmento (+)
  AND SVV.ID_TIPO_VALOR = 7
  AND EFECT.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    NULL                              AS numero_boleta,
    NULL                              AS descripcion_banco,
    NULL                              AS numero_cheque,
    NULL                              AS fecha_vencimiento,
    NULL                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(transf.numero_referencia) AS referencia,
    NULL                              AS tipo,
    NULL                              AS nro_constancia,
    NULL                              AS cuit,
    NULL                              AS provincia,
    NULL                              AS cod_organismo,
    NULL                              AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_transferencia transf
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea      = swtv.id_tarea
  AND svv.id_valor      = swtv.id_valor
  AND svv.id_workflow   = swfeEntidad.id_workflow
  AND svv.id_empresa    = spe.id_empresa (+)
  AND svv.id_segmento   = sps.id_segmento (+)
  AND SVV.ID_TIPO_VALOR = 8
  AND TRANSF.ID_VALOR   = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    NULL                                AS numero_boleta,
    NULL                                AS descripcion_banco,
    NULL                                AS numero_cheque,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(inter.numero_interdeposito) AS referencia,
    NULL                                AS tipo,
    NULL                                AS nro_constancia,
    NULL                                AS cuit,
    NULL                                AS provincia,
    inter.codigo_organismo              AS cod_organismo,
    NULL                                AS rango
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_interdeposito inter
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea      = swtv.id_tarea
  AND svv.id_valor      = swtv.id_valor
  AND svv.id_workflow   = swfeEntidad.id_workflow
  AND svv.id_empresa    = spe.id_empresa (+)
  AND svv.id_segmento   = sps.id_segmento (+)
  AND SVV.ID_TIPO_VALOR = 9
  AND INTER.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - RETENCION IMPUESTO(10)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    svv.id_cliente_legado              AS numero_cliente,
    svv.razon_social_cliente_agrupador AS razon_social,
    svv.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    svv.id_valor AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    NULL                                  AS numero_boleta,
    NULL                                  AS descripcion_banco,
    NULL                                  AS numero_cheque,
    NULL                                  AS fecha_vencimiento,
    NULL                                  AS CODIGO_INTERDEPOSITO,
    TO_CHAR(ret.nro_constancia_retencion) AS referencia,
    TIPORET.DESCRIPCION                   AS TIPO,
    NULL                                  AS nro_constancia,
    RET.CUIT                              AS CUIT,
    juri.descripcion                      AS provincia,
    NULL                                  AS cod_organismo,
    NULL                                  AS rango
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_retencion ret,
    shv_param_tipo_ret_impuesto tiporet,
    shv_param_jurisdiccion juri
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores
  AND swt.id_tarea                       = swtv.id_tarea
  AND svv.id_valor                       = swtv.id_valor
  AND svv.id_workflow                    = swfeEntidad.id_workflow
  AND svv.id_empresa                     = spe.id_empresa (+)
  AND svv.id_segmento                    = sps.id_segmento (+)
  AND svv.id_tipo_valor                  = 10
  AND ret.id_valor                       = svv.id_valor
  AND TIPORET.ID_TIPO_RETENCION_IMPUESTO = RET.ID_TIPO_RETENCION_IMPUESTO
  AND RET.ID_JURISDICCION                = JURI.ID_JURISDICCION (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    sara.codigo_cliente                 AS numero_cliente,
    sara.razon_social_cliente_agrupador AS razon_social,
    sara.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    swtra.id_registro_avc AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(deposito.numero_boleta) AS numero_boleta,
    BANCO.DESCRIPCION               AS DESCRIPCION_BANCO,
    NULL                            AS numero_cheque,
    NULL                            AS fecha_vencimiento,
    NULL                            AS CODIGO_INTERDEPOSITO,
    TO_CHAR(cheque.numero_cheque)   AS referencia,
    NULL                            AS tipo,
    NULL                            AS nro_constancia,
    NULL                            AS cuit,
    NULL                            AS provincia,
    NULL                            AS cod_organismo,
    NULL                            AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    SHV_AVC_REG_AVC_CHEQUE cheque,
    shv_avc_reg_avc_deposito deposito,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc     = swtra.id_registro_avc
  AND sara.id_workflow         = swfeEntidad.id_workflow
  AND SARA.ID_TIPO_VALOR       = 2
  AND cheque.id_registro_avc   = sara.id_registro_avc
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || cheque.codigo_banco, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - CHEQUE DIFERIDO (3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    sara.codigo_cliente                 AS numero_cliente,
    sara.razon_social_cliente_agrupador AS razon_social,
    sara.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    swtra.id_registro_avc AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(deposito.numero_boleta)                   AS numero_boleta,
    BANCO.DESCRIPCION                                 AS DESCRIPCION_BANCO,
    NULL                                              AS NUMERO_CHEQUE,
    TO_CHAR(chequepd.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(chequepd.numero_cheque)                   AS referencia,
    NULL                                              AS tipo,
    NULL                                              AS nro_constancia,
    NULL                                              AS cuit,
    NULL                                              AS provincia,
    NULL                                              AS cod_organismo,
    NULL                                              AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    SHV_AVC_REG_AVC_CHEQUE_PD chequepd,
    shv_avc_reg_avc_deposito deposito,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc     = swtra.id_registro_avc
  AND sara.id_workflow         = swfeEntidad.id_workflow
  AND SARA.ID_TIPO_VALOR       = 3
  AND chequepd.id_registro_avc = sara.id_registro_avc
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || chequepd.codigo_banco, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    sara.codigo_cliente                 AS numero_cliente,
    sara.razon_social_cliente_agrupador AS razon_social,
    sara.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    swtra.id_registro_avc AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(depo.numero_boleta) AS numero_boleta,
    NULL                        AS descripcion_banco,
    NULL                        AS numero_cheque,
    NULL                        AS fecha_vencimiento,
    NULL                        AS codigo_interdeposito,
    TO_CHAR(depo.numero_boleta) AS referencia,
    NULL                        AS tipo,
    NULL                        AS nro_constancia,
    NULL                        AS cuit,
    NULL                        AS provincia,
    NULL                        AS cod_organismo,
    NULL                        AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    shv_avc_reg_avc_deposito depo
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc = swtra.id_registro_avc
  AND SARA.ID_WORKFLOW     = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR   = 4
  AND depo.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - TRANSFERENCIA (8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    sara.codigo_cliente                 AS numero_cliente,
    sara.razon_social_cliente_agrupador AS razon_social,
    sara.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    swtra.id_registro_avc AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    NULL                      AS numero_boleta,
    NULL                      AS descripcion_banco,
    NULL                      AS numero_cheque,
    NULL                      AS fecha_vencimiento,
    NULL                      AS CODIGO_INTERDEPOSITO,
    TO_CHAR(trans.REFERENCIA) AS referencia,
    NULL                      AS tipo,
    NULL                      AS nro_constancia,
    NULL                      AS cuit,
    NULL                      AS provincia,
    NULL                      AS cod_organismo,
    NULL                      AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    SHV_AVC_REG_AVC_TRANSFERENCIA trans
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc  = swtra.id_registro_avc
  AND sara.id_workflow      = swfeEntidad.id_workflow
  AND SARA.ID_TIPO_VALOR    = 8
  AND trans.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - INTERDEPOSITO (9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    sara.codigo_cliente                 AS numero_cliente,
    sara.razon_social_cliente_agrupador AS razon_social,
    sara.importe                        AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    swtra.id_registro_avc AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    NULL                                AS numero_boleta,
    NULL                                AS descripcion_banco,
    NULL                                AS numero_cheque,
    NULL                                AS fecha_vencimiento,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(inter.CODIGO_INTERDEPOSITO) AS referencia,
    NULL                                AS tipo,
    NULL                                AS nro_constancia,
    NULL                                AS cuit,
    NULL                                AS provincia,
    inter.CODIGO_ORGANISMO              AS cod_organismo,
    NULL                                AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    SHV_AVC_REG_AVC_INTERDEPOSITO inter
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc  = swtra.id_registro_avc
  AND sara.id_workflow      = swfeEntidad.id_workflow
  AND SARA.ID_TIPO_VALOR    = 9
  AND inter.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL          AS numero_cliente,
    NULL          AS razon_social,
    svvpr.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    swtvr.id_valor_por_reversion AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS numero_boleta,
    banco.descripcion            AS descripcion_banco,
    NULL                         AS numero_cheque,
    NULL                         AS fecha_vencimiento,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(svvpr.numero_cheque) AS referencia,
    NULL                         AS tipo,
    NULL                         AS nro_constancia,
    NULL                         AS cuit,
    NULL                         AS provincia,
    NULL                         AS cod_organismo,
    NULL                         AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores por Reversion
    shv_wf_tarea_valor_reversion swtvr,
    shv_val_valor_por_reversion svvpr,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 5
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || svvpr.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL          AS numero_cliente,
    NULL          AS razon_social,
    svvpr.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    swtvr.id_valor_por_reversion AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA)                   AS numero_boleta,
    banco.descripcion                              AS descripcion_banco,
    NULL                                           AS numero_cheque,
    TO_CHAR(svvpr.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                           AS CODIGO_INTERDEPOSITO,
    TO_CHAR(svvpr.numero_cheque)                   AS referencia,
    NULL                                           AS tipo,
    NULL                                           AS nro_constancia,
    NULL                                           AS cuit,
    NULL                                           AS provincia,
    NULL                                           AS cod_organismo,
    NULL                                           AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores por Reversion
    shv_wf_tarea_valor_reversion swtvr,
    shv_val_valor_por_reversion svvpr,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 6
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || svvpr.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - EFECTIVO(7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL          AS numero_cliente,
    NULL          AS razon_social,
    svvpr.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    swtvr.id_valor_por_reversion AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                         AS descripcion_banco,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS fecha_vencimiento,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS referencia,
    NULL                         AS tipo,
    NULL                         AS nro_constancia,
    NULL                         AS cuit,
    NULL                         AS provincia,
    NULL                         AS cod_organismo,
    NULL                         AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores por Reversion
    shv_wf_tarea_valor_reversion swtvr,
    shv_val_valor_por_reversion svvpr,
    shv_wf_workflow_estado swfeEntidad
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 7
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL          AS numero_cliente,
    NULL          AS razon_social,
    svvpr.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    swtvr.id_valor_por_reversion AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    NULL                             AS NUMERO_BOLETA,
    NULL                             AS descripcion_banco,
    NULL                             AS NUMERO_CHEQUE,
    NULL                             AS fecha_vencimiento,
    NULL                             AS CODIGO_INTERDEPOSITO,
    TO_CHAR(svvpr.NUMERO_REFERENCIA) AS referencia,
    NULL                             AS tipo,
    NULL                             AS nro_constancia,
    NULL                             AS cuit,
    NULL                             AS provincia,
    NULL                             AS cod_organismo,
    NULL                             AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores por Reversion
    shv_wf_tarea_valor_reversion swtvr,
    shv_val_valor_por_reversion svvpr,
    shv_wf_workflow_estado swfeEntidad
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 8
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    swt.perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL          AS numero_cliente,
    NULL          AS razon_social,
    svvpr.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    swtvr.id_valor_por_reversion AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    NULL AS id_empresa,
    NULL AS descripcion_empresa,
    NULL AS id_segmento,
    NULL AS descripcion_segmento,
    ---------------------------------------
    NULL                                AS numero_boleta,
    NULL                                AS descripcion_banco,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS fecha_vencimiento,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(svvpr.CODIGO_INTERDEPOSITO) AS referencia,
    NULL                                AS tipo,
    NULL                                AS nro_constancia,
    NULL                                AS cuit,
    NULL                                AS provincia,
    svvpr.CODIGO_ORGANISMO              AS cod_organismo,
    NULL                                AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Valores por Reversion
    shv_wf_tarea_valor_reversion swtvr,
    shv_val_valor_por_reversion svvpr,
    shv_wf_workflow_estado swfeEntidad
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 9
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Talonarios
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL AS numero_cliente,
    NULL AS razon_social,
    NULL AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    stt.id_talonario AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    NULL AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    NULL AS numero_boleta,
    NULL AS descripcion_banco,
    NULL AS numero_cheque,
    NULL AS fecha_vencimiento,
    NULL AS codigo_interdeposito,
    NULL AS referencia,
    NULL AS tipo,
    NULL AS nro_constancia,
    NULL AS cuit,
    NULL AS provincia,
    NULL AS cod_organismo,
    SUBSTR('0000'
    || stt.numero_serie, -4)
    || '-'
    || SUBSTR('00000000'
    || stt.rango_desde, -8)
    || ' a '
    || SUBSTR('0000'
    || stt.numero_serie, -4)
    || '-'
    || SUBSTR('00000000'
    || stt.rango_hasta, -8) AS rango
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Talonarios
    shv_wf_tarea_talonario swtt,
    shv_tal_talonario stt,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Talonarios
  AND swt.id_tarea     = swtt.id_tarea
  AND stt.id_talonario = swtt.id_talonario
  AND stt.id_workflow  = swfeEntidad.id_workflow
  AND stt.id_empresa   = spe.id_empresa (+)
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
    -- Datos comunes a todas las tareas
    swt.id_tarea,
    swt.tipo_tarea,
    swt.estado                                           AS estado_tarea,
    TO_CHAR(swt.fecha_creacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_creacion,
    swt.usuario_creacion,
    TO_CHAR(swt.fecha_ejecucion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_ejecucion,
    swt.usuario_ejecucion,
    swt.informacion_adicional,
    swt.gestiona_sobre,
    CASE
      WHEN upper(swt.perfil_asignacion) = 'ANALISTACOBRANZA'
      THEN (swt.perfil_asignacion
        ||'_'
        ||spe.id_empresa
        ||'_'
        ||sps.id_segmento)
      ELSE swt.perfil_asignacion
    END AS perfil_asignacion,
    swt.usuario_asignacion,
    swt.id_workflow AS id_workflow_asociado,
    -- Nro. Cliente, Razon Social, Importe
    NULL         AS numero_cliente,
    NULL         AS razon_social,
    swtc.importe AS importe,
    -- Registro AVC / Valor, Talonario
    swfeEntidad.estado AS estado_workflow_asociado,
    -- Registros AVC
    NULL AS id_registro_avc,
    -- Valor por Reversion
    NULL AS id_valor_por_reversion,
    -- Valor
    NULL AS id_valor,
    -- Talonario
    NULL AS id_talonario,
    -- Operacion Masiva
    NULL AS id_operacion_masiva,
    -- Cobros
    scec.id_cobro AS id_cobro,
    -- Valor / Talonario / Operacion Masiva / Cobro
    spe.id_empresa  AS id_empresa,
    spe.descripcion AS descripcion_empresa,
    sps.id_segmento AS id_segmento,
    sps.descripcion AS descripcion_segmento,
    ---------------------------------------
    NULL                   AS numero_boleta,
    NULL                   AS descripcion_banco,
    NULL                   AS numero_cheque,
    NULL                   AS fecha_vencimiento,
    NULL                   AS codigo_interdeposito,
    TO_CHAR(scec.id_cobro) AS referencia,
    NULL                   AS tipo,
    NULL                   AS nro_constancia,
    NULL                   AS cuit,
    NULL                   AS provincia,
    NULL                   AS cod_organismo,
    NULL                   AS rango
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Cobros
    shv_wf_tarea_cobro swtc,
    shv_cob_ed_cobro scec,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Cobros
  AND swt.id_tarea     = swtc.id_tarea
  AND scec.id_cobro    = swtc.id_cobro
  AND scec.id_workflow = swfeEntidad.id_workflow
  AND scec.id_empresa  = spe.id_empresa (+)
  AND scec.id_segmento = sps.id_segmento (+)
  );
  
  /
  Exit;