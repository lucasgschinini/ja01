--------------------------------------------------------------------------------------------------------------
-- Actualización de la vista de bandeja de tareas, para soportar tareas de cobros
--------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "SHV_SOP_BANDEJA_DE_ENTRADA" ("ID_TAREA", "TIPO_TAREA", "ESTADO_TAREA", "FECHA_CREACION", "USUARIO_CREACION", "FECHA_EJECUCION", "USUARIO_EJECUCION", "INFORMACION_ADICIONAL", "GESTIONA_SOBRE", "PERFIL_ASIGNACION", "USUARIO_ASIGNACION", "ID_WORKFLOW_ASOCIADO", "NUMERO_CLIENTE", "RAZON_SOCIAL", "IMPORTE", "ESTADO_WORKFLOW_ASOCIADO", "ID_REGISTRO_AVC", "ID_VALOR_POR_REVERSION", "ID_VALOR", "ID_TALONARIO", "ID_OPERACION_MASIVA", "ID_COBRO", "ID_EMPRESA", "DESCRIPCION_EMPRESA", "ID_SEGMENTO", "DESCRIPCION_SEGMENTO", "NUMERO_BOLETA", "DESCRIPCION_BANCO", "NUMERO_CHEQUE", "FECHA_VENCIMIENTO", "CODIGO_INTERDEPOSITO", "REFERENCIA", "TIPO", "NRO_CONSTANCIA", "CUIT", "PROVINCIA", "COD_ORGANISMO", "RANGO")
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
    swtc.id_cliente_legado    AS numero_cliente,
    swtc.razon_social_cliente AS razon_social,
    swtc.importe              AS importe,
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
    NULL                       AS numero_boleta,
    NULL                       AS descripcion_banco,
    NULL                       AS numero_cheque,
    NULL                       AS fecha_vencimiento,
    NULL                       AS codigo_interdeposito,
    TO_CHAR(scec.id_operacion) AS referencia,
    NULL                       AS tipo,
    NULL                       AS nro_constancia,
    NULL                       AS cuit,
    NULL                       AS provincia,
    NULL                       AS cod_organismo,
    NULL                       AS rango
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

--------------------------------------------------------------------------------------------------------------
-- Actualización de la vista de busqueda de valores
--------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW "SHV_SOP_BUSQUEDA_VALORES" ("ID_VALOR", "EMPRESA", "SEGMENTO", "ID_CLIENTE_LEGADO", "RAZON_SOCIAL_CLIENTE_AGRUPADOR", "TIPO_VALOR", "ESTADO_VALOR", "ORIGEN", "ACUERDO", "NRO_VALOR", "IMPORTE", "BD_IMPRESA", "BD_ENVIADA_MAIL", "FECHA_ALTA", "ID_ANALISTA", "NRO_RECIBO", "ID_CONSTANCIA_RECEPCION", "OPERACION_ASOCIADA", "FACTURA_RELACIONADA", "DOCUMENTACION_ORIG_RECIBIDA", "MOTIVO", "BANCO_DEPOSITO", "ID_COPROPIETARIO", "USUARIO_AUTORIZACION", "NUMERO_DOCUMENTO_CONTABLE", "MOTIVO_SUSPENSION", "EJECUTIVO", "ASISTENTE", "SALDO_DISPONIBLE", "FECHA_INGRESO_RECIBO", "FECHA_EMISION", "FECHA_TRANSFERENCIA", "VALOR_PADRE", "ARCHIVO_DE_VALORES_A_CONCILIAR", "FECHA_DEPOSITO", "FECHA_ULTIMO_ESTADO", "FECHA_DISPONIBLE", "NRO_BOLETA", "DESCRIPCION_BANCO_ORIGEN", "FECHA_VENCIMIENTO", "ID_TIPO_RETENCION", "DESCRIPCION_TIPO_RETENCION", "NRO_CUIT_RETENCION", "PROVINCIA_RETENCION", "REFERENCIA_VALOR", "ID_ANALISTA_TEAM_COMERCIAL", "ID_SUPERVISOR_TEAM_COMERCIAL",
  "ID_GERENTE_REG_TEAM_COMERCIAL", "CODIGO_ORGANISMO", "OBSERVACIONES", "ID_ESTADO_VALOR", "ID_TIPO_VALOR", "ID_ORIGEN", "ID_EMPRESA", "ID_SEGMENTO", "FECHA_VALOR", "COBRO_FORMATEADO", "ID_BOLETA", "FECHA_BUSQUEDA")
AS
  (
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO EFECTIVO Sprint3, incluye defecto 133
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL AS FACTURA_RELACIONADA ,
    NVL (SVVE.DOCUMENTACION_ORIG_RECIBIDA, 'NO') DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVVE.FECHA_DEPOSITO                            AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SVVE.NUMERO_BOLETA                  AS NRO_BOLETA ,
    NULL                                AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVVE.NUMERO_BOLETA)         AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                               AS ID_BOLETA,
    TO_CHAR(SVVE.FECHA_DEPOSITO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_EFECTIVO SVVE
  ON SVV.ID_VALOR = SVVE.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO CHEQUE
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL AS FACTURA_RELACIONADA ,
    NVL (SVVC.DOCUMENTACION_ORIG_RECIBIDA, 'NO') DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVVC.FECHA_DEPOSITO                            AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SVVC.NUMERO_BOLETA                  AS NRO_BOLETA ,
    SPBORIGEN.DESCRIPCION               AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVVC.NUMERO_CHEQUE)         AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                               AS ID_BOLETA,
    TO_CHAR(SVVC.FECHA_DEPOSITO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_CHEQUE SVVC
  ON SVV.ID_VALOR = SVVC.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPBORIGEN
  ON SVVC.ID_BANCO_ORIGEN = SPBORIGEN.ID_BANCO
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO CHEQUE DIFERIDO
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL AS FACTURA_RELACIONADA ,
    NVL (SVVCPD.DOCUMENTACION_ORIG_RECIBIDA, 'NO') DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVVCPD.FECHA_DEPOSITO                          AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SVVCPD.NUMERO_BOLETA                AS NRO_BOLETA ,
    SPBORIGEN.DESCRIPCION               AS DESCRIPCION_BANCO_ORIGEN ,
    SVVCPD.FECHA_VENCIMIENTO            AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVVCPD.NUMERO_CHEQUE)       AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                                    AS ID_BOLETA,
    TO_CHAR(SVVCPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM
    --FABIO.GIAQUINTA.RUIZ 13/08/2014
    --JOINS ORDENADOS POR TABLA CON MAYOR CANTIDAD DE REGISTROS
    SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_CHEQUE_PD SVVCPD
  ON SVV.ID_VALOR = SVVCPD.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO SPBORIGEN
  ON SVVCPD.ID_BANCO_ORIGEN = SPBORIGEN.ID_BANCO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO TRANSFERENCIAS
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL AS FACTURA_RELACIONADA ,
    NVL (SVVT.DOCUMENTACION_ORIG_RECIBIDA, 'NO') DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    SVVT.FECHA_TRANSFERENCIA                       AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    NULL                                           AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    NULL                                AS NRO_BOLETA ,
    NULL                                AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVVT.NUMERO_REFERENCIA)     AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                                    AS ID_BOLETA,
    TO_CHAR(SVVT.FECHA_TRANSFERENCIA, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM
    --FABIO.GIAQUINTA.RUIZ 13/08/2014
    --JOINS ORDENADOS POR TABLA CON MAYOR CANTIDAD DE REGISTROS
    SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_TRANSFERENCIA SVVT
  ON SVV.ID_VALOR = SVVT.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO SPBORIGEN
  ON SVVT.ID_BANCO_ORIGEN = SPBORIGEN.ID_BANCO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO INTERDEPOSITO
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL                 AS FACTURA_RELACIONADA ,
    NULL                 AS DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVVI.FECHA_INTERDEPOSITO                       AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    NULL                                AS NRO_BOLETA ,
    NULL                                AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVVI.NUMERO_INTERDEPOSITO)  AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    SVVI.CODIGO_ORGANISMO               AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                                    AS ID_BOLETA,
    TO_CHAR(SVVI.FECHA_INTERDEPOSITO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM
    --FABIO.GIAQUINTA.RUIZ 13/08/2014
    --JOINS ORDENADOS POR TABLA CON MAYOR CANTIDAD DE REGISTROS
    SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_INTERDEPOSITO SVVI
  ON SVV.ID_VALOR = SVVI.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- AVISO DE PAGO RETENCIONES
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    NULL             AS ORIGEN ,
    NULL             AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    NULL                                          AS BD_IMPRESA ,
    NULL                                          AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    NULL                                          AS NRO_RECIBO ,
    NULL                                          AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    DECODE (SVVR.ID_TIPO_COMPROBANTE, NULL, NULL, (SPTC.DESCRIPCION
    ||' '
    ||SPTLC.DESCRIPCION
    ||' '
    ||SVVR.SUCURSAL_COMPROBANTE
    ||'-'
    ||SVVR.NUMERO_COMPROBANTE)) AS FACTURA_RELACIONADA -- >> PARA RETENCIONES
    ,
    NULL                 AS DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    NULL                 AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    NULL                                           AS FECHA_INGRESO_RECIBO ,
    TO_CHAR(SVVR.FECHA_EMISION, 'DD/MM/YYYY')      AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    NULL                                           AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    NULL                                   AS NRO_BOLETA ,
    NULL                                   AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                   AS FECHA_VENCIMIENTO ,
    SPTRI.ID_TIPO_RETENCION_IMPUESTO       AS ID_TIPO_RETENCION,
    SPTRI.DESCRIPCION                      AS DESCRIPCION_TIPO_RETENCION,
    SVVR.CUIT                              AS NRO_CUIT_RETENCION,
    SPJ.DESCRIPCION                        AS PROVINCIA_RETENCION,
    TO_CHAR(SVVR.NRO_CONSTANCIA_RETENCION) AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS      AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS    AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL             AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                   AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                      AS OBSERVACIONES,
    --DATOS DE USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    NULL                                              AS ID_BOLETA,
    TO_CHAR(SVVR.FECHA_EMISION, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM
    --FABIO.GIAQUINTA.RUIZ 13/08/2014
    --JOINS ORDENADOS POR TABLA CON MAYOR CANTIDAD DE REGISTROS
    SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_VALOR_RETENCION SVVR
  ON SVV.ID_VALOR = SVVR.ID_VALOR
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_TIPO_COMPROBANTE SPTC
  ON SVVR.ID_TIPO_COMPROBANTE = SPTC.ID_TIPO_COMPROBANTE
  LEFT JOIN SHV_PARAM_TIPO_LETRA_COMP SPTLC
  ON SVVR.ID_TIPO_LETRA_COMPROBANTE = SPTLC.ID_TIPO_LETRA_COMPROBANTE
  LEFT JOIN SHV_PARAM_JURISDICCION SPJ
  ON SVVR.ID_JURISDICCION = SPJ.ID_JURISDICCION
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  LEFT JOIN SHV_PARAM_TIPO_RET_IMPUESTO SPTRI
  ON SVVR.ID_TIPO_RETENCION_IMPUESTO = SPTRI.ID_TIPO_RETENCION_IMPUESTO
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- BOLETA DEPOSITO EFECTIVO
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    SBB.IMPRESION_ESTADO                          AS BD_IMPRESA ,
    SBB.EMAIL_ENVIO_ESTADO                        AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    STRP.NUMERO_RECIBO                            AS NRO_RECIBO ,
    SVCRV.ID_CONSTANCIA_RECEPCION                 AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL                 AS FACTURA_RELACIONADA ,
    NULL                 AS DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    SVBDE.FECHA_RECIBO                             AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVBDE.FECHA_DEPOSITO                           AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SBB.NUMERO_BOLETA                   AS NRO_BOLETA ,
    NULL                                AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SBB.NUMERO_BOLETA)          AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    SBB.ID_BOLETA                                       AS ID_BOLETA,
    TO_CHAR(SVBDE.FECHA_DEPOSITO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM
    --FABIO.GIAQUINTA.RUIZ 13/08/2014
    --JOINS ORDENADOS POR TABLA CON MAYOR CANTIDAD DE REGISTROS
    SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_BOLETA_DEP_EFECTIVO SVBDE
  ON SVV.ID_VALOR = SVBDE.ID_VALOR
  INNER JOIN SHV_BOL_BOLETA SBB
  ON SVBDE.ID_BOLETA = SBB.ID_BOLETA
  LEFT JOIN SHV_TAL_RECIBO_PREIMPRESO STRP
  ON STRP.ID_RECIBO_PREIMPRESO = SVBDE.ID_RECIBO_PREIMPRESO
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_VAL_CONSTANCIA_RECEP_VALOR SVCRV
  ON SVV.ID_VALOR = SVCRV.ID_VALOR
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- BOLETA DEPOSITO CHEQUE
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    SBB.IMPRESION_ESTADO                          AS BD_IMPRESA ,
    SBB.EMAIL_ENVIO_ESTADO                        AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    STRP.NUMERO_RECIBO                            AS NRO_RECIBO ,
    SVCRV.ID_CONSTANCIA_RECEPCION                 AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL                 AS FACTURA_RELACIONADA ,
    NULL                 AS DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    SVBDC.FECHA_RECIBO                             AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVBDC.FECHA_DEPOSITO                           AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SBB.NUMERO_BOLETA                   AS NRO_BOLETA ,
    SPBORIGEN.DESCRIPCION               AS DESCRIPCION_BANCO_ORIGEN ,
    NULL                                AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVBDC.NUMERO_CHEQUE)        AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    SBB.ID_BOLETA                                       AS ID_BOLETA,
    TO_CHAR(SVBDC.FECHA_DEPOSITO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  LEFT JOIN SHV_VAL_BOLETA_DEP_CHEQUE SVBDC
  ON SVV.ID_VALOR = SVBDC.ID_VALOR
  INNER JOIN SHV_BOL_BOLETA SBB
  ON SVBDC.ID_BOLETA = SBB.ID_BOLETA
  LEFT JOIN SHV_TAL_RECIBO_PREIMPRESO STRP
  ON STRP.ID_RECIBO_PREIMPRESO = SVBDC.ID_RECIBO_PREIMPRESO
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_VAL_CONSTANCIA_RECEP_VALOR SVCRV
  ON SVV.ID_VALOR = SVCRV.ID_VALOR
  LEFT JOIN SHV_PARAM_BANCO SPBORIGEN
  ON SVBDC.ID_BANCO_ORIGEN = SPBORIGEN.ID_BANCO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  ---------------------------------------------------------------------------------------
  UNION
  ---------------------------------------------------------------------------------------
  -- BOLETA DEPOSITO CHEQUE DIFERIDO
  ---------------------------------------------------------------------------------------
  SELECT
    -- DATOS GENERALES DE LA VISTA, MODELO ORIGINAL
    SVV.ID_VALOR ,
    SPE.DESCRIPCION       AS EMPRESA ,
    SPS.DESCRIPCION       AS SEGMENTO ,
    SVV.ID_CLIENTE_LEGADO AS ID_CLIENTE_LEGADO ,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR ,
    SPTV.DESCRIPCION AS TIPO_VALOR ,
    SPWE.DESCRIPCION AS ESTADO_VALOR ,
    SPO.DESCRIPCION  AS ORIGEN ,
    SVV.ID_ACUERDO   AS ACUERDO ,
    SVV.NUMERO_VALOR AS NRO_VALOR ,
    SVV.IMPORTE ,
    SBB.IMPRESION_ESTADO                          AS BD_IMPRESA ,
    SBB.EMAIL_ENVIO_ESTADO                        AS BD_ENVIADA_MAIL ,
    TO_CHAR(SVV.FECHA_ALTA, 'DD/MM/YYYY HH24:MI') AS FECHA_ALTA ,
    SVV.ID_ANALISTA                               AS ID_ANALISTA ,
    STRP.NUMERO_RECIBO                            AS NRO_RECIBO ,
    SVCRV.ID_CONSTANCIA_RECEPCION                 AS ID_CONSTANCIA_RECEPCION ,
    SVV.OPERACION_ASOCIADA ,
    NULL                 AS FACTURA_RELACIONADA ,
    NULL                 AS DOCUMENTACION_ORIG_RECIBIDA ,
    SPM.DESCRIPCION      AS MOTIVO ,
    SPB.DESCRIPCION      AS BANCO_DEPOSITO ,
    SVV.ID_COPROPIETARIO AS ID_COPROPIETARIO ,
    SVV.USUARIO_AUTORIZACION ,
    SVV.NUMERO_DOCUMENTO_CONTABLE ,
    SPMS.DESCRIPCION                AS MOTIVO_SUSPENSION ,
    SPTC.USER_EJECUTIVO_CUENTA      AS EJECUTIVO ,
    SPTC.USER_ANALISTA_CONTRATO_VOZ AS ASISTENTE ,
    SVV.SALDO_DISPONIBLE ,
    SVBDCPD.FECHA_RECIBO                           AS FECHA_INGRESO_RECIBO ,
    NULL                                           AS FECHA_EMISION ,
    NULL                                           AS FECHA_TRANSFERENCIA ,
    UDF_SHV_OBTENER_NRO_CHEQUE(SVV.ID_VALOR_PADRE) AS VALOR_PADRE ,
    SAAA.NOMBRE_ARCHIVO                            AS ARCHIVO_DE_VALORES_A_CONCILIAR ,
    SVBDCPD.FECHA_DEPOSITO                         AS FECHA_DEPOSITO ,
    SVV.FECHA_ULTIMO_ESTADO ,
    SVV.FECHA_DISPONIBLE ,
    -- DATOS SEPARACION VIEJO CAMPO NRO. VALOR REQ 11 SPRINT 3
    SBB.NUMERO_BOLETA                   AS NRO_BOLETA ,
    SPBORIGEN.DESCRIPCION               AS DESCRIPCION_BANCO_ORIGEN ,
    SVBDCPD.FECHA_VENCIMIENTO           AS FECHA_VENCIMIENTO ,
    NULL                                AS ID_TIPO_RETENCION,
    NULL                                AS DESCRIPCION_TIPO_RETENCION,
    NULL                                AS NRO_CUIT_RETENCION,
    NULL                                AS PROVINCIA_RETENCION,
    TO_CHAR(SVBDCPD.NUMERO_CHEQUE)      AS REFERENCIA_VALOR,
    SPTC.USER_ANALISTA_COBRANZA_DATOS   AS ID_ANALISTA_TEAM_COMERCIAL,
    SPTC.USER_SUPERVISOR_COBRANZA_DATOS AS ID_SUPERVISOR_TEAM_COMERCIAL,
    SPTC.USER_GERENTE_REGIONAL          AS ID_GERENTE_REG_TEAM_COMERCIAL,
    NULL                                AS CODIGO_ORGANISMO,
    SVV.OBSERVACIONES                   AS OBSERVACIONES,
    -- DATOS ADICIONALES PARA USO INTERNO
    SWWE.ESTADO AS ID_ESTADO_VALOR ,
    SVV.ID_TIPO_VALOR ,
    SVV.ID_ORIGEN ,
    SVV.ID_EMPRESA ,
    SVV.ID_SEGMENTO ,
    SVV.FECHA_VALOR,
          (SELECT RTRIM (
                     REGEXP_REPLACE (
                        (LISTAGG (LPAD (TRAN.ID_OPERACION, 7, '0'), ';')
                            WITHIN GROUP (ORDER BY TRAN.ID_OPERACION)),
                        '([^;]*)(;\1)+($|;)',
                        '\1\3'),
                     ';')
                     AS ID_SHIVA
             FROM SHV_COB_COBRO COBRO,
                  SHV_COB_TRANSACCION TRAN,
                  SHV_COB_MED_PAGO MED,
                  SHV_COB_MED_PAG_SHIVA MEDSHIV
            WHERE     TRAN.ID_OPERACION = COBRO.ID_OPERACION
                  AND MED.ID_TRANSACCION = TRAN.ID_TRANSACCION
                  AND MED.ID_MEDIO_PAGO = MEDSHIV.ID_MEDIO_PAGO
                  AND MEDSHIV.ID_VALOR = SVV.ID_VALOR
                  AND TRAN.ESTADO IN
                         ('PENDIENTE',
                          'APROPIADA',
                          'ERROR_CONFIRMACION',
                          'ERROR_DESAPROPIACION',
                          'EN_PROCESO_APROPIACION',
                          'EN_PROCESO_CONFIRMACION',
                          'EN_PROCESO_DESAPROPIACION',
                          'CONFIRMADA')
                  AND ROWNUM < 100)
             AS COBRO_FORMATEADO,
    SBB.ID_BOLETA                                            AS ID_BOLETA,
    TO_CHAR(SVBDCPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY HH24:MI') AS FECHA_BUSQUEDA
  FROM SHV_VAL_VALOR SVV
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SVV.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO SPWE
  ON SWWE.ESTADO = SPWE.ID_ESTADO
  INNER JOIN SHV_VAL_BOLETA_DEP_CHEQUE_PD SVBDCPD
  ON SVV.ID_VALOR = SVBDCPD.ID_VALOR
  INNER JOIN SHV_BOL_BOLETA SBB
  ON SVBDCPD.ID_BOLETA = SBB.ID_BOLETA
  LEFT JOIN SHV_AVC_REG_AVC_VALOR SARAV
  ON SVV.ID_VALOR = SARAV.ID_VALOR
  LEFT JOIN SHV_AVC_REGISTRO_AVC SARA
  ON SARAV.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  LEFT JOIN SHV_AVC_ARCHIVO_AVC SAAA
  ON SARA.ID_ARCHIVO_AVC = SAAA.ID_ARCHIVO_AVC
  LEFT JOIN SHV_TAL_RECIBO_PREIMPRESO STRP
  ON STRP.ID_RECIBO_PREIMPRESO = SVBDCPD.ID_RECIBO_PREIMPRESO
  LEFT JOIN SHV_VAL_CONSTANCIA_RECEP_VALOR SVCRV
  ON SVV.ID_VALOR = SVCRV.ID_VALOR
  LEFT JOIN SHV_PARAM_ACUERDO SPA
  ON SVV.ID_ACUERDO = SPA.ID_ACUERDO
  LEFT JOIN SHV_PARAM_BANCO SPBORIGEN
  ON SVBDCPD.ID_BANCO_ORIGEN = SPBORIGEN.ID_BANCO
  LEFT JOIN SHV_PARAM_BANCO_CUENTA SPBC
  ON SPA.ID_BANCO_CUENTA = SPBC.ID_BANCO_CUENTA
  LEFT JOIN SHV_PARAM_BANCO SPB
  ON SPBC.ID_BANCO = SPB.ID_BANCO
  INNER JOIN SHV_PARAM_TIPO_VALOR SPTV
  ON SVV.ID_TIPO_VALOR = SPTV.ID_TIPO_VALOR
  LEFT JOIN SHV_PARAM_MOTIVO SPM
  ON SVV.ID_MOTIVO = SPM.ID_MOTIVO
  INNER JOIN SHV_PARAM_SEGMENTO SPS
  ON SVV.ID_SEGMENTO = SPS.ID_SEGMENTO
  LEFT JOIN SHV_PARAM_ORIGEN SPO
  ON SVV.ID_ORIGEN = SPO.ID_ORIGEN
  LEFT JOIN SHV_PARAM_MOTIVO_SUSPENSION SPMS
  ON SVV.ID_MOTIVO_SUSPENSION = SPMS.ID_MOTIVO_SUSPENSION
  INNER JOIN SHV_PARAM_EMPRESA SPE
  ON SVV.ID_EMPRESA = SPE.ID_EMPRESA
  LEFT JOIN SHV_PARAM_TEAM_COMERCIAL SPTC
  ON SVV.ID_CLIENTE_LEGADO = SPTC.NRO_DE_CLIENTE
  );  
  
  Exit;