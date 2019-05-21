CREATE OR REPLACE VIEW SHV_SOP_BANDEJA_DE_ENTRADA(ID_TAREA, TIPO_TAREA, ESTADO_TAREA, FECHA_CREACION, USUARIO_CREACION, FECHA_EJECUCION, USUARIO_EJECUCION, INFORMACION_ADICIONAL, GESTIONA_SOBRE, PERFIL_ASIGNACION, USUARIO_ASIGNACION, ID_WORKFLOW_ASOCIADO, NUMERO_CLIENTE, RAZON_SOCIAL, IMPORTE, ESTADO_WORKFLOW_ASOCIADO, ID_REGISTRO_AVC, ID_VALOR_POR_REVERSION, ID_VALOR, ID_TALONARIO, ID_OPERACION_MASIVA, ID_COBRO, ID_EMPRESA, DESCRIPCION_EMPRESA, ID_SEGMENTO, DESCRIPCION_SEGMENTO, NUMERO_BOLETA, DESCRIPCION_BANCO, NUMERO_CHEQUE, FECHA_VENCIMIENTO, CODIGO_INTERDEPOSITO, REFERENCIA, TIPO, NRO_CONSTANCIA, CUIT, PROVINCIA, COD_ORGANISMO, RANGO)
AS
  (
  --- Sprint 4
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - BOLETA DEPOSITO EFECTIVO (4)
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
    depo.numero_boleta AS numero_boleta,
    NULL               AS descripcion_banco,
    NULL               AS numero_cheque,
    NULL               AS fecha_vencimiento,
    NULL               AS codigo_interdeposito,
    NULL               AS referencia,
    NULL               AS tipo,
    NULL               AS nro_constancia,
    NULL               AS cuit,
    NULL               AS provincia,
    NULL               AS cod_organismo,
    NULL               AS rango
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
  AND sara.id_workflow     = swfeEntidad.id_workflow
  AND sara.id_tipo_valor   = 4
  AND depo.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - BOLETA DEPOSITO CHEQUE (2)
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
    depo.numero_boleta,
    banco.descripcion    AS descripcion_banco,
    cheque.numero_cheque AS numero_cheque,
    NULL                 AS fecha_vencimiento,
    NULL                 AS codigo_interdeposito,
    NULL                 AS referencia,
    NULL                 AS tipo,
    NULL                 AS nro_constancia,
    NULL                 AS cuit,
    NULL                 AS provincia,
    NULL                 AS cod_organismo,
    NULL                 AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    shv_avc_reg_avc_cheque cheque,
    shv_avc_reg_avc_deposito depo,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc   = swtra.id_registro_avc
  AND sara.id_workflow       = swfeEntidad.id_workflow
  AND sara.id_tipo_valor     = 2
  AND cheque.id_registro_avc = sara.id_registro_avc
  AND banco.id_banco         = SUBSTR('0000'
    || cheque.codigo_banco, -4)
  AND depo.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - BOLETA DEPOSITO CHEQUE DIFERIDO (3)
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
    depo.numero_boleta                                 AS numero_boleta,
    banco.descripcion                                  AS descripcion_banco,
    NULL                                               AS numero_cheque,
    TO_CHAR(chequedif.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                               AS codigo_interdeposito,
    NULL                                               AS referencia,
    NULL                                               AS tipo,
    NULL                                               AS nro_constancia,
    NULL                                               AS cuit,
    NULL                                               AS provincia,
    NULL                                               AS cod_organismo,
    NULL                                               AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    shv_avc_reg_avc_cheque_pd chequedif,
    shv_avc_reg_avc_deposito depo,
    shv_param_banco banco
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc      = swtra.id_registro_avc
  AND sara.id_workflow          = swfeEntidad.id_workflow
  AND sara.id_tipo_valor        = 3
  AND chequedif.id_registro_avc = sara.id_registro_avc
  AND banco.id_banco            = SUBSTR('0000'
    || chequedif.codigo_banco, -4)
  AND depo.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Registros AVC - INTERDEP�ITO (9)
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
    NULL                       AS numero_boleta,
    NULL                       AS descripcion_banco,
    NULL                       AS numero_cheque,
    NULL                       AS fecha_vencimiento,
    inter.codigo_interdeposito AS codigo_interdeposito,
    NULL                       AS referencia,
    NULL                       AS tipo,
    NULL                       AS nro_constancia,
    NULL                       AS cuit,
    NULL                       AS provincia,
    NULL                       AS cod_organismo,
    NULL                       AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    shv_avc_reg_avc_interdeposito inter
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc  = swtra.id_registro_avc
  AND sara.id_workflow      = swfeEntidad.id_workflow
  AND sara.id_tipo_valor    = 9
  AND inter.id_registro_avc = sara.id_registro_avc
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
    NULL              AS numero_boleta,
    NULL              AS descripcion_banco,
    NULL              AS numero_cheque,
    NULL              AS fecha_vencimiento,
    NULL              AS codigo_interdeposito,
    transf.referencia AS referencia,
    NULL              AS tipo,
    NULL              AS nro_constancia,
    NULL              AS cuit,
    NULL              AS provincia,
    NULL              AS cod_organismo,
    NULL              AS rango
    ---------------------------------------
  FROM
    -- from comunes a todas las tareas
    shv_wf_tarea swt,
    shv_wf_workflow_estado swfe,
    -- from de Registros AVC
    shv_wf_tarea_registro_avc swtra,
    shv_avc_registro_avc sara,
    shv_wf_workflow_estado swfeEntidad,
    shv_avc_reg_avc_transferencia transf
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.id_tarea  = swtra.id_tarea
  AND swt.estado    = 'PENDIENTE'
    -- where de Registros AVC
  AND sara.id_registro_avc   = swtra.id_registro_avc
  AND sara.id_workflow       = swfeEntidad.id_workflow
  AND sara.id_tipo_valor     = 8
  AND transf.id_registro_avc = sara.id_registro_avc
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - EFECTIVO (7)
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
    svvpr.numero_boleta AS numero_boleta,
    NULL                AS descripcion_banco,
    NULL                AS numero_cheque,
    NULL                AS fecha_vencimiento,
    NULL                AS codigo_interdeposito,
    NULL                AS referencia,
    NULL                AS tipo,
    NULL                AS nro_constancia,
    NULL                AS cuit,
    NULL                AS provincia,
    NULL                AS cod_organismo,
    NULL                AS rango
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
  -- Valores por Reversion - CHEQUE DIFERIDO (6)
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
    NULL                                           AS numero_boleta,
    NULL                                           AS descripcion_banco,
    NULL                                           AS numero_cheque,
    TO_CHAR(svvpr.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                           AS codigo_interdeposito,
    NULL                                           AS referencia,
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
    shv_wf_workflow_estado swfeEntidad
  WHERE
    -- where comunes a todas las tareas
    swt.id_workflow = swfe.id_workflow
  AND swt.estado    = 'PENDIENTE'
    -- where de Valores por Reversion
  AND swt.id_tarea                 = swtvr.id_tarea
  AND svvpr.id_valor_por_reversion = swtvr.id_valor_por_reversion
  AND svvpr.id_workflow            = swfeEntidad.id_workflow
  AND svvpr.id_tipo_valor          = 6
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - CHEQUE(5)
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
    NULL                AS numero_boleta,
    banco.descripcion   AS descripcion_banco,
    svvpr.numero_cheque AS numero_cheque,
    NULL                AS fecha_vencimiento,
    NULL                AS codigo_interdeposito,
    NULL                AS referencia,
    NULL                AS tipo,
    NULL                AS nro_constancia,
    NULL                AS cuit,
    NULL                AS provincia,
    NULL                AS cod_organismo,
    NULL                AS rango
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
  AND banco.id_banco               = SUBSTR('0000'
    || svvpr.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores por Reversion - INTERDEP�ITO(9)
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
    NULL AS numero_boleta,
    NULL AS descripcion_banco,
    NULL AS numero_cheque,
    NULL AS fecha_vencimiento,
    svvpr.codigo_interdeposito codigo_interdeposito,
    NULL AS referencia,
    NULL AS tipo,
    NULL AS nro_constancia,
    NULL AS cuit,
    NULL AS provincia,
    NULL AS cod_organismo,
    NULL AS rango
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
  -- Valores por Reversion - TRANSFERENCIA(8)
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
    NULL                    AS numero_boleta,
    NULL                    AS descripcion_banco,
    NULL                    AS numero_cheque,
    NULL                    AS fecha_vencimiento,
    NULL                    AS codigo_interdeposito,
    svvpr.numero_referencia AS referencia,
    NULL                    AS tipo,
    NULL                    AS nro_constancia,
    NULL                    AS cuit,
    NULL                    AS provincia,
    NULL                    AS cod_organismo,
    NULL                    AS rango
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
    efect.numero_boleta AS numero_boleta,
    NULL                AS descripcion_banco,
    NULL                AS numero_cheque,
    NULL                AS fecha_vencimiento,
    NULL                AS codigo_interdeposito,
    NULL                AS referencia,
    NULL                AS tipo,
    NULL                AS nro_constancia,
    NULL                AS cuit,
    NULL                AS provincia,
    NULL                AS cod_organismo,
    NULL                AS rango
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
  AND svv.id_tipo_valor = 7
  AND efect.id_valor    = svv.id_valor
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - CHEQUE(5)
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
    NULL                 AS numero_boleta,
    banco.descripcion    AS descripcion_banco,
    cheque.numero_cheque AS numero_cheque,
    NULL                 AS fecha_vencimiento,
    NULL                 AS codigo_interdeposito,
    NULL                 AS referencia,
    NULL                 AS tipo,
    NULL                 AS nro_constancia,
    NULL                 AS cuit,
    NULL                 AS provincia,
    NULL                 AS cod_organismo,
    NULL                 AS rango
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
    shv_val_valor_cheque cheque,
    shv_param_banco banco
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
  AND svv.id_tipo_valor = 5
  AND cheque.id_valor   = svv.id_valor
  AND banco.id_banco    = SUBSTR('0000'
    || cheque.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - CHEQUE_DIFERIDO(6)
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
    NULL                                              AS numero_boleta,
    banco.descripcion                                 AS descripcion_banco,
    chequepd.numero_cheque                            AS numero_cheque,
    TO_CHAR(chequepd.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                              AS codigo_interdeposito,
    NULL                                              AS referencia,
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
    -- from de Valores
    shv_wf_tarea_valor swtv,
    shv_val_valor svv,
    shv_wf_workflow_estado swfeEntidad,
    shv_param_empresa spe,
    shv_param_segmento sps,
    shv_val_valor_cheque_pd chequepd,
    shv_param_banco banco
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
  AND svv.id_tipo_valor = 6
  AND chequepd.id_valor = svv.id_valor
  AND banco.id_banco    = SUBSTR('0000'
    || chequepd.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - RETENCI�_IMPUESTO(10)
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
    NULL                         AS numero_boleta,
    NULL                         AS descripcion_banco,
    NULL                         AS numero_cheque,
    NULL                         AS fecha_vencimiento,
    NULL                         AS codigo_interdeposito,
    NULL                         AS referencia,
    tiporet.descripcion          AS tipo,
    ret.nro_constancia_retencion AS nro_constancia,
    ret.cuit                     AS cuit,
    juri.id_provincia            AS provincia,
    NULL                         AS cod_organismo,
    NULL                         AS rango
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
  AND tiporet.id_tipo_retencion_impuesto = ret.id_tipo_retencion_impuesto
  AND (ret.id_jurisdiccion              IS NULL
  OR ret.id_jurisdiccion                 = juri.id_jurisdiccion)
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
    NULL                     AS numero_boleta,
    NULL                     AS descripcion_banco,
    NULL                     AS numero_cheque,
    NULL                     AS fecha_vencimiento,
    NULL                     AS codigo_interdeposito,
    transf.numero_referencia AS referencia,
    NULL                     AS tipo,
    NULL                     AS nro_constancia,
    NULL                     AS cuit,
    NULL                     AS provincia,
    NULL                     AS cod_organismo,
    NULL                     AS rango
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
  AND svv.id_tipo_valor = 8
  AND transf.id_valor   = svv.id_valor
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - BOLETA_DEPOSITO_EFECTIVO(4)
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
    boleta.numero_boleta AS numero_boleta,
    NULL                 AS descripcion_banco,
    NULL                 AS numero_cheque,
    NULL                 AS fecha_vencimiento,
    NULL                 AS codigo_interdeposito,
    NULL                 AS referencia,
    NULL                 AS tipo,
    NULL                 AS nro_constancia,
    NULL                 AS cuit,
    NULL                 AS provincia,
    NULL                 AS cod_organismo,
    NULL                 AS rango
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
    shv_val_boleta_dep_efectivo depefect,
    shv_bol_boleta boleta
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
  AND svv.id_tipo_valor = 4
  AND depefect.id_valor = svv.id_valor
  AND boleta.id_boleta  = depefect.id_boleta
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - BOLETA_DEPOSITO_CHEQUE(2)
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
    boleta.numero_boleta    AS numero_boleta,
    banco.descripcion       AS descripcion_banco,
    boldepche.numero_cheque AS numero_cheque,
    NULL                    AS fecha_vencimiento,
    NULL                    AS codigo_interdeposito,
    NULL                    AS referencia,
    NULL                    AS tipo,
    NULL                    AS nro_constancia,
    NULL                    AS cuit,
    NULL                    AS provincia,
    NULL                    AS cod_organismo,
    NULL                    AS rango
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
  AND banco.id_banco     = SUBSTR('0000'
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
    boleta.numero_boleta                                 AS numero_boleta,
    banco.descripcion                                    AS descripcion_banco,
    boldepchepd.numero_cheque                            AS numero_cheque,
    TO_CHAR(boldepchepd.fecha_vencimiento, 'DD/MM/YYYY') AS fecha_vencimiento,
    NULL                                                 AS codigo_interdeposito,
    NULL                                                 AS referencia,
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
  AND banco.id_banco       = SUBSTR('0000'
    || boldepchepd.id_banco_origen, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Valores - INTERDEP�ITO(9)
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
    NULL                       AS numero_boleta,
    NULL                       AS descripcion_banco,
    NULL                       AS numero_cheque,
    NULL                       AS fecha_vencimiento,
    inter.numero_interdeposito AS codigo_interdeposito,
    NULL                       AS referencia,
    NULL                       AS tipo,
    NULL                       AS nro_constancia,
    NULL                       AS cuit,
    NULL                       AS provincia,
    inter.codigo_organismo     AS cod_organismo,
    NULL                       AS rango
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
  AND svv.id_tipo_valor = 9
  AND inter.id_valor    = svv.id_valor
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
  AND stt.id_segmento  = sps.id_segmento (+)

--------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- EDICION DE COBRO
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
    swtc.id_cliente_legado  AS numero_cliente,  
	swtc.razon_social_cliente AS razon_social,
    swtc.importe_val_deb AS importe,
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
    NULL AS numero_boleta,
    NULL AS descripcion_banco,
    NULL AS numero_cheque,
    NULL AS fecha_vencimiento,
    NULL AS codigo_interdeposito,
    scec.id_cobro AS referencia,
    NULL AS tipo,
    NULL AS nro_constancia,
    NULL AS cuit,
    NULL AS provincia,
    NULL AS cod_organismo,
    NULL AS rango
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
  AND scec.id_cobro = swtc.id_cobro
  AND scec.id_workflow  = swfeEntidad.id_workflow
  AND scec.id_empresa   = spe.id_empresa (+)
  AND scec.id_segmento  = sps.id_segmento (+)
  );
  
  
  -- Spring 5
  -- Vista de busqueda de cobros 
  CREATE OR REPLACE FORCE VIEW "SHV_SOP_COBROS_BUSQUEDA" ("ID_COBRO", "ID_EMPRESA", "ID_SEGMENTO", "ID_OPERACION", "ID_MOTIVO_COBRO", "DESC_MOTIVO_COBRO", "CLIENTE", "ESTADO", "ANALISTA", "COPROPIETARIO", "ID_REVERSION", "IMPORTE_TOTAL", "FECHA_ALTA", "FECHA_DERIVACION", "FECHA_APROB_SUPER_COB", "FECHA_APROB_REFER_COB", "FECHA_IMPUTACION", "SUB_ESTADO", "FECHA_ULTIMO_ESTADO")
                         AS
 SELECT SCEC.ID_COBRO   AS idCobro,
    SCEC.id_empresa      AS empresa,
    SCEC.id_segmento     AS segmento,
    SCEC.ID_OPERACION    AS idOperacion,
    SCEC.ID_MOTIVO_COBRO AS idMotivoCobro,
    SPMC.DESCRIPCION     AS descMotivoCobro,
    (SELECT SUBSTR(CEC.ID_CLIENTE_LEGADO
      || ' '
      || CEC.RAZON_SOCIAL,0,15)
    FROM SHV_COB_ED_CLIENTE CEC
    WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    AND rownum         < 2
    )                          AS cliente,
    SWWE.estado                AS estado,
    SCEC.ID_ANALISTA           AS analista,
    SCEC.ID_COPROPIETARIO      AS copropietario,
    NULL                       AS idReversion,
    SCEC.IMPORTE_TOTAL         AS importeTotal,
    SCEC.FECHA_ALTA            AS fechaAlta,
    SCEC.FECHA_DERIVACION      AS fechaDerivacion,
    SCEC.FECHA_APROB_SUPER_COB AS fechaAprobSuperCob,
    SCEC.FECHA_APROB_REFER_COB AS fechaAprobReferCob,
    SCEC.FECHA_IMPUTACION      AS fechaImputacion,
	
    (SELECT LISTAGG(SWWM.ID_MARCA, '|') WITHIN GROUP(
    ORDER BY SWWM.ID_MARCA)
    FROM SHV_WF_WORKFLOW_MARCA SWWM
    WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO
    ) AS subEstado,
    
    -- obteniendo la fecha mas reciente entre el workflow estado y las marcas
    case
      when (SELECT MAX(SWWM.fecha_creacion)  FROM shv_wf_workflow_marca SWWM WHERE SWWE.ID_WORKFLOW_ESTADO = SWWM.ID_WORKFLOW_ESTADO) 
    > SWWE.fecha_modificacion
      then TO_CHAR ((SELECT MAX(SWWM.fecha_creacion)  FROM shv_wf_workflow_marca SWWM WHERE SWWE.ID_WORKFLOW_ESTADO = SWWM.ID_WORKFLOW_ESTADO),'DD/MM/YYYY - HH24:MI:SS')
    when (SELECT MAX(SWWM.fecha_creacion)  FROM shv_wf_workflow_marca SWWM WHERE SWWE.ID_WORKFLOW_ESTADO = SWWM.ID_WORKFLOW_ESTADO) 
    <= SWWE.fecha_modificacion
      then TO_CHAR (SWWE.fecha_modificacion,'DD/MM/YYYY - HH24:MI:SS')
    else TO_CHAR (SWWE.fecha_modificacion,'DD/MM/YYYY - HH24:MI:SS')
	end  AS fechaUltimoEstado
 FROM SHV_COB_ED_COBRO SCEC
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO;
  

  
----------------------------------------------------------------------------------------
-- Vista de detalles de factura, alimenta la vista de la grilla de transacciones
----------------------------------------------------------------------------------------
create or replace view shv_sop_cobros_detalle_factura as (
  --
  -- Facturas Mic
  --
  select 
    -- Datos del documento
    scf.id_factura,
    scf.id_transaccion,
    scf.estado,
    scf.sistema_origen as sistema_origen,
    scf.tipo_comprobante as tipo_comprobante,
    nvl(to_char(scfm.tipo_factura), scfm.tipo_duc) as subtipo_documento,
    DECODE (scf.clase_comprobante,'S','','D','', scf.clase_comprobante||'-')
    ||lpad(scf.sucursal_comprobante, 4, '0')
    ||'-'
    ||lpad(scf.numero_comprobante, 8, '0') as numero_documento,
    scfm.id_referencia_factura as numero_referencia_mic,
    scf.fecha_vencimiento as fecha_vencimiento,
    'PES' as moneda,  -- Los documentos de MIC son todos en Pesos, por lo que se presenta el valor 'PES' por defecto
    scf.fecha_valor as fecha_cobro,
    scf.importe_cobrar as importe_a_cobrar,
    scf.importe_original as importe_original,
    scf.saldo_actual as saldo_actual,
    null as tipo_de_cambio_fecha_cobro,
    null as tipo_de_cambio_fecha_emision,
    null as importe_aplic_fec_emis_mon_ori,
    scf.monto_acumulado_simulacion as monto_acumulado_simulacion,
    scf.fecha_acumulado_simulacion as fecha_acumulado_simulacion,
    -- Tipo de pago
    scf.tipo_pago as tipo_pago,
    -- Gestion de intereses
    scf.cobrador_intereses_generados as cobrador_intereses_generados,
    scf.check_trasladar_intereses as check_trasladar_intereses,
    scf.porcentaje_bonif_intereses as porcentaje_bonif_intereses,
    scf.importe_bonif_intereses as importe_bonif_intereses,
    scf.acuerdo_traslado_cargo as acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    scf.tipo_mensaje_estado as tipo_mensaje_estado,
    scf.mensaje_estado as mensaje_estado
  from
    shv_cob_factura scf,
    shv_cob_factura_mic scfm
  where 
    scfm.id_factura = scf.id_factura
  --
  union
  --
  -- Facturas Calipso
  --
  select 
    -- Datos del documento
    scf.id_factura,
    scf.id_transaccion,
    scf.estado,
    scf.sistema_origen as sistema_origen,
    scf.tipo_comprobante as tipo_comprobante,
    null as subtipo_documento,
    DECODE (scf.clase_comprobante,'S','','D','', scf.clase_comprobante||'-')
    ||lpad(scf.sucursal_comprobante, 4, '0')
    ||'-'
    ||lpad(scf.numero_comprobante, 8, '0') as numero_documento,
    null as numero_referencia_mic,
    scf.fecha_vencimiento as fecha_vencimiento,
    scfc.moneda as moneda,
    scf.fecha_valor as fecha_cobro,
    scf.importe_cobrar as importe_a_cobrar,
    scf.importe_original as importe_original,
    scf.saldo_actual as saldo_actual,
    scfc.tipo_de_cambio_fecha_cobro as tipo_de_cambio_fecha_cobro,
    scfc.tipo_de_cambio_fecha_emision as tipo_de_cambio_fecha_emision,
    scfc.importe_aplic_fec_emis_mon_ori as importe_aplic_fec_emis_mon_ori,
    scf.monto_acumulado_simulacion as monto_acumulado_simulacion,
    scf.fecha_acumulado_simulacion as fecha_acumulado_simulacion,
    -- Tipo de pago
    scf.tipo_pago as tipo_pago,
    -- Gestion de intereses
    scf.cobrador_intereses_generados as cobrador_intereses_generados,
    scf.check_trasladar_intereses as check_trasladar_intereses,
    scf.porcentaje_bonif_intereses as porcentaje_bonif_intereses,
    scf.importe_bonif_intereses as importe_bonif_intereses,
    scf.acuerdo_traslado_cargo as acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    scf.tipo_mensaje_estado as tipo_mensaje_estado,
    scf.mensaje_estado as mensaje_estado
  from
    shv_cob_factura scf,
    shv_cob_factura_calipso scfc
  where 
    scfc.id_factura = scf.id_factura
);

----------------------------------------------------------------------------------------
-- Vista de detalles de medios de pago, alimenta la vista de la grilla de transacciones
----------------------------------------------------------------------------------------

create or replace view shv_sop_cobros_detalle_med_pag as (
  select 
    scmp.id_medio_pago, 
    scmp.id_tipo_medio_pago,
    sptmp.descripcion descripcion_medio_pago,
    scmp.id_transaccion,
    scmp.estado,
    scmp.importe,
    scmp.monto_acumulado_simulacion,
    scmp.migrado_deimos,
    scmp.estado_deimos,
    scmp.fecha_actualizacion_deimos,
    scmp.tipo_mensaje_estado,
    scmp.mensaje_estado,
    scmp.moneda,
    scmp.sistema_origen,
    scmpd.fecha_valor,
    scmpd.referencia,
    -- Gestion de intereses
    scmpd.cobrador_intereses_generados as cobrador_intereses_generados,
    scmpd.check_trasladar_intereses as check_trasladar_intereses,
    scmpd.porcentaje_bonif_intereses as porcentaje_bonif_intereses,
    scmpd.importe_bonif_intereses as importe_bonif_intereses,
    scmpd.acuerdo_traslado_cargo,
    scmpd.estado_acuerdo_traslado_cargo,
    scmpd.id_cliente_acuerdo_tras_cargo
  from 
    shv_param_tipo_medio_pago sptmp,
    shv_cob_med_pago scmp,
    (
    --
    -- Medio pago Shiva
    --
    select 
      id_medio_pago, referencia_valor as referencia, fecha_valor as fecha_valor, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_shiva
    union
    --
    -- Medio pago Compensacion
    --
    select 
      id_medio_pago, numero_compensacion as refmediopago, fecha, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_compensacion
    union
    --
    -- Medio pago Compensacion Intercompany
    --
    select 
      id_medio_pago, numero_compensacion, fecha, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_comp_intercom
    union
    --
    -- Medio pago Compensacion Liquido Producto
    --
    select 
      id_medio_pago, numero_compensacion, fecha, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_comp_liquido
    union
    --
    -- Medio pago Desistimiento
    --
    select 
      id_medio_pago, numero_acta, fecha, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_desistimiento
    union
    --
    -- Medio de pago Plan de Pago
    --
    select 
      id_medio_pago, null, fecha,
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_plan_pago
    union
    --
    -- Medio pago CTA
    --
    select 
      id_medio_pago, 
      decode (clase_comprobante,'S','','D','', clase_comprobante)||lpad(sucursal_comprobante, 4, '0')||'-'||lpad(numero_comprobante, 8, '0'), 
      fecha_emision, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_cta
    union
    --
    -- Medio pago Nota de Credito Calipso
    --
    select 
      id_medio_pago, 
      decode (clase_comprobante,'S','','D','', clase_comprobante)||lpad(sucursal_comprobante, 4, '0')||'-'||lpad(numero_comprobante, 8, '0'), 
      fecha_emision, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_nota_cred_clp
    union
    --
    -- Medio de pago Debito Proxima Factura Calipso
    --
    select id_medio_pago, null, fecha, 
      -- Gestion de intereses
      cobrador_intereses_generados as cobrador_intereses_generados,
      check_trasladar_intereses as check_trasladar_intereses,
      porcentaje_bonif_intereses as porcentaje_bonif_intereses,
      importe_bonif_intereses as importe_bonif_intereses,
      acuerdo_traslado_cargo as acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_deb_prox_clp
    union
    --
    -- Medio pago Remanente
    --
    select 
      id_medio_pago, cuenta_remanente||tipo_remanente, fecha_alta, 
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_remanente
    union
    --
    -- Medio pago Nota de Credito Mic
    --
    select 
      id_medio_pago, 
      decode (clase_comprobante,'S','','S','', clase_comprobante)||lpad(sucursal_comprobante, 4, '0')||'-'||lpad(numero_comprobante, 8, '0'), 
      fecha_emision,
      -- Gestion de intereses
      null as cobrador_intereses_generados,
      null as check_trasladar_intereses,
      null as porcentaje_bonif_intereses,
      null as importe_bonif_intereses,
      null as acuerdo_traslado_cargo,
      null as estado_acuerdo_traslado_cargo,
      null as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_nota_cred_mic
    union
    --
    -- Medio de pago Debito Proxima Factura Mic
    --
    select 
      id_medio_pago, null, fecha, 
      -- Gestion de intereses
      cobrador_intereses_generados as cobrador_intereses_generados,
      check_trasladar_intereses as check_trasladar_intereses,
      porcentaje_bonif_intereses as porcentaje_bonif_intereses,
      importe_bonif_intereses as importe_bonif_intereses,
      acuerdo_traslado_cargo as acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo
    from shv_cob_med_pag_deb_prox_mic
  ) scmpd
  where 
    scmpd.id_medio_pago = scmp.id_medio_pago
    and sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
);

----------------------------------------------------------------------------------------
-- Vista de la grilla de transacciones
----------------------------------------------------------------------------------------
create or replace view shv_sop_cobros_grilla_transac as (
--
-- Datos de Facturas
--
select 
  -- Datos de la transaccion
  lpad(sct.id_operacion, 7, '0')||'.'||lpad(sct.numero_transaccion, 5, '0') as numero_transaccion_formateado,
  sct.estado as estado_transaccion,
  -- Datos del documento a cobrar
  sscdf.sistema_origen as sistema_origen_documento,
  sscdf.tipo_comprobante as tipo_comprobante,
  sscdf.subtipo_documento as subtipo_documento,
  sscdf.numero_documento as numero_documento,
  sscdf.numero_referencia_mic as numero_referencia_mic,
  sscdf.fecha_vencimiento as fecha_vencimiento,
  sscdf.moneda as moneda,
  sscdf.fecha_cobro as fecha_cobro,
  sscdf.importe_a_cobrar as importe_a_cobrar,
  sscdf.importe_original as importe_original,
  sscdf.saldo_actual as saldo_actual,
  sscdf.tipo_de_cambio_fecha_cobro as tipo_de_cambio_fecha_cobro,
  sscdf.tipo_de_cambio_fecha_emision as tipo_de_cambio_fecha_emision,
  sscdf.importe_aplic_fec_emis_mon_ori as importe_aplic_fec_emis_mon_ori,
  sscdf.monto_acumulado_simulacion as monto_acumulado_simulacion_deb,
  sscdf.fecha_acumulado_simulacion as fecha_acumulado_simulacion_deb,
  -- Datos de medios de pago
  null as sistema_origen_medio_pago,
  null as tipo_medio_pago,
  null as subtipo_medio_pago,
  null as referencia_medio_pago,
  null as fecha_medio_pago,
  null as importe_medio_pago,
  null as monto_acumulado_simulacion_cre,
  -- Tipo de pago
  sscdf.tipo_pago as tipo_pago,
  -- Gestion de intereses
  sscdf.cobrador_intereses_generados as intereses,
  sscdf.check_trasladar_intereses as check_trasladar_intereses,
  sscdf.porcentaje_bonif_intereses as porcentaje_a_bonificar,
  sscdf.importe_bonif_intereses as importe_a_bonificar,
  sscdf.acuerdo_traslado_cargo as acuerdo_traslado_cargo,
  sscdf.estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
  sscdf.id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo,
  -- Mensajes de apropiacion
  null as tipo_mensaje_estado_transaccio,
  null as mensaje_estado_transaccion,
  sscdf.tipo_mensaje_estado as tipo_mensaje_estado_debito,
  sscdf.mensaje_estado as mensaje_estado_debito,
  sscdf.estado as estado_debito,
  null as tipo_mensaje_estado_credito,
  null as mensaje_estado_credito,
  null as estado_credito,
  -- Datos ocultos del cobro / transaccion
  scc.id_cobro,
  sct.id_operacion,
  sct.id_transaccion,
  sct.id_transaccion_padre,
  sct.numero_transaccion,
  sscdf.id_factura,
  0 as id_medio_pago,
  null as id_tratamiento_diferencia
from
  shv_cob_cobro scc,
  shv_cob_transaccion sct,
  shv_sop_cobros_detalle_factura sscdf
where 
  sscdf.id_transaccion = sct.id_transaccion
  and scc.id_operacion = sct.id_operacion
--
union
-- 
-- Datos de medios de pago utilizados
--
select 
  -- Datos de la transaccion
  lpad(sct.id_operacion, 7, '0')||'.'||lpad(sct.numero_transaccion, 5, '0') as numero_transaccion_formateado,
  sct.estado as estado_transaccion,
  -- Datos del documento a cobrar
  null as sistema_origen_documento,
  null as tipo_comprobante,
  null as subtipo_documento,
  null as numero_documento,
  null as numero_referencia_mic,
  null as fecha_vencimiento,
  null as moneda,
  null as fecha_cobro,
  null as importe_a_cobrar,
  null as importe_original,
  null as saldo_actual,
  null as tipo_de_cambio_fecha_cobro,
  null as tipo_de_cambio_fecha_emision,
  null as importe_aplic_fec_emis_mon_ori,
  null as monto_acumulado_simulacion,
  null as fecha_acumulado_simulacion,
  -- Datos de medios de pago
  sscdmp.sistema_origen as sistema_origen_medio_pago,
  sscdmp.descripcion_medio_pago as tipo_medio_pago,
  null as subtipo_medio_pago,
  sscdmp.referencia as referencia_medio_pago,
  sscdmp.fecha_valor as fecha_medio_pago,
  sscdmp.importe as importe_medio_pago,
  sscdmp.monto_acumulado_simulacion as monto_acumulado_simulacion_cre,
  -- Tipo de pago
  null as tipo_pago,
  -- Gestion de intereses
  sscdmp.cobrador_intereses_generados as intereses,
  sscdmp.check_trasladar_intereses as check_trasladar_intereses,
  sscdmp.porcentaje_bonif_intereses as porcentaje_a_bonificar,
  sscdmp.importe_bonif_intereses as importe_a_bonificar,
  sscdmp.acuerdo_traslado_cargo as acuerdo_traslado_cargo,
  sscdmp.estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
  sscdmp.id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo,
  -- Mensajes de apropiacion
  null as tipo_mensaje_estado_transaccio,
  null as mensaje_estado_transaccion,
  null as tipo_mensaje_estado_debito,
  null as mensaje_estado_debito,
  null as estado_debito,
  sscdmp.tipo_mensaje_estado as tipo_mensaje_estado_credito,
  sscdmp.mensaje_estado as mensaje_estado_credito,
  sscdmp.estado as estado_credito,
  -- Datos ocultos del cobro / transaccion
  scc.id_cobro,
  sct.id_operacion,
  sct.id_transaccion,
  sct.id_transaccion_padre,
  sct.numero_transaccion,
  null as id_factura,
  sscdmp.id_medio_pago as id_medio_pago,
  null as id_tratamiento_diferencia
from
  shv_cob_cobro scc,
  shv_cob_transaccion sct,
  shv_sop_cobros_detalle_med_pag sscdmp
where 
  sscdmp.id_transaccion = sct.id_transaccion
  and scc.id_operacion = sct.id_operacion
--
union
-- 
-- Datos de tratamiento de diferencia
--
select 
  -- Datos de la transaccion
  lpad(sct.id_operacion, 7, '0')||'.'||lpad(sct.numero_transaccion, 5, '0') as numero_transaccion_formateado,
  sct.estado as estado_transaccion,
  -- Datos del documento a cobrar
  null as sistema_origen_documento,
  decode(sctd.tipo_tratamiento_diferencia, 'ENVIO_A_GANANCIAS', 'GAN', 'REI') as tipo_comprobante,
  sctd.tipo_tratamiento_diferencia as subtipo_documento,
  null as numero_documento,
  null as numero_referencia_mic,
  null as fecha_vencimiento,
  null as moneda,
  sctd.fecha_tramite_reintegro as fecha_cobro,
  sctd.importe as importe_a_cobrar,
  null as importe_original,
  null as saldo_actual,
  null as tipo_de_cambio_fecha_cobro,
  null as tipo_de_cambio_fecha_emision,
  null as importe_aplic_fec_emis_mon_ori,
  null as monto_acumulado_simulacion,
  null as fecha_acumulado_simulacion,
  -- Datos de medios de pago
  null as sistema_origen_medio_pago,
  null as tipo_medio_pago,
  null as subtipo_medio_pago,
  null as referencia_medio_pago,
  null as fecha_medio_pago,
  null as importe_medio_pago,
  null as monto_acumulado_simulacion_cre,
  -- Tipo de pago
  null as tipo_pago,
  -- Gestion de intereses
  sctd.cobrador_intereses_generados as intereses,
  sctd.check_trasladar_intereses as check_trasladar_intereses,
  sctd.porcentaje_bonif_intereses as porcentaje_a_bonificar,
  sctd.importe_bonif_intereses as importe_a_bonificar,
  sctd.acuerdo_traslado_cargo as acuerdo_traslado_cargo,
  sctd.estado_acuerdo_traslado_cargo as estado_acuerdo_traslado_cargo,
  sctd.id_cliente_acuerdo_tras_cargo as id_cliente_acuerdo_tras_cargo,
  -- Mensajes de apropiacion
  sct.tipo_mensaje_estado as tipo_mensaje_estado_transaccio,
  sct.mensaje_estado as mensaje_estado_transaccion,
  null as tipo_mensaje_estado_debito,
  null as mensaje_estado_debito,
  null as estado_debito,
  null as tipo_mensaje_estado_credito,
  null as mensaje_estado_credito,
  null as estado_credito,
  -- Datos ocultos del cobro / transaccion
  scc.id_cobro,
  sct.id_operacion,
  sct.id_transaccion,
  sct.id_transaccion_padre,
  sct.numero_transaccion,
  null as id_factura,
  null as id_medio_pago,
  sctd.id_tratamiento_diferencia as id_tratamiento_diferencia
from
  shv_cob_cobro scc,
  shv_cob_transaccion sct,
  shv_cob_tratamiento_diferencia sctd
where 
  sctd.id_transaccion = sct.id_transaccion
  and scc.id_operacion = sct.id_operacion  
);

    -- Spring 5
  -- Vista de busqueda historial de cobros 
  
  CREATE OR REPLACE VIEW SHV_SOP_COBROS_HISTORIAL
AS  
  (SELECT ed_cobro1.id_cobro,
    ed_cobro1.id_cobro_padre,
    ed_cobro1.id_operacion,
    wf_estado1.estado,
    wf_estado1.subestado,
    TO_CHAR(wf_estado1.fecha_modificacion, 'DD/MM/YYYY - HH24:MI:SS') AS fecha_modificacion,
    wf_estado1.usuario_modificacion,
    wf_estado1.observacion,
    NULL AS mensaje_estado,
    NULL AS numero_transaccion,
    NULL AS tipo_comprobante,
    NULL AS clase_comprobante,
    NULL AS sucursal_comprobante,
    NULL AS numero_comprobante,
    NULL AS importe_cobrar,
    NULL AS fecha_valor,
    NULL AS referencia,
    NULL AS acuerdo_traslado_cargo
  FROM
    (SELECT swwe.id_workflow,
            swwe.estado,
            NULL AS subestado,
            swwe.fecha_modificacion,
            swwe.usuario_modificacion,
            DBMS_LOB.substr(swwe.datos_modificados,1,4000) as observacion
    FROM shv_wf_workflow_estado swwe
    WHERE swwe.estado != 'COB_NO_DISPONIBLE'
    UNION ALL
    SELECT  sweh.id_workflow,
            sweh.estado,
            NULL AS subestado,
            sweh.fecha_modificacion,
            sweh.usuario_modificacion,
            DBMS_LOB.substr(sweh.datos_modificados,1,4000) as observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado != 'COB_NO_DISPONIBLE'
    UNION ALL
    SELECT  swwe.id_workflow,
            swwe.estado,
            swwm.id_marca,
            swwm.fecha_creacion,
            swwm.usuario_creacion,
            NULL
    FROM shv_wf_workflow_estado swwe
    INNER JOIN shv_wf_workflow_marca swwm
    ON (swwe.id_workflow_estado = swwm.id_workflow_estado)
    WHERE (swwe.estado != 'COB_NO_DISPONIBLE')
    UNION ALL
    SELECT  sweh.id_workflow,
            sweh.estado,
            swmh.id_marca,
            swmh.fecha_creacion,
            swmh.usuario_creacion,
            NULL
    FROM shv_wf_workflow_estado_hist sweh
    INNER JOIN shv_wf_workflow_marca_hist swmh
    ON(sweh.id_workflow_estado_hist = swmh.id_workflow_estado_hist)
    WHERE sweh.estado != 'COB_NO_DISPONIBLE'
    ) wf_estado1
  INNER JOIN SHV_COB_ED_COBRO ed_cobro1
  ON (wf_estado1.id_workflow = ed_cobro1.id_workflow)
  WHERE wf_estado1.estado   != 'COB_NO_DISPONIBLE'
  AND (wf_estado1.estado     = 'COB_EN_EDICION'
  OR wf_estado1.estado       = 'COB_EN_EDICION_VERIFCANDO_DEBITOS'
  OR wf_estado1.estado       = 'COB_PEND_REFERENTE_COBRANZA'
  OR wf_estado1.estado       = 'COB_RECHAZADO'
  OR wf_estado1.estado       = 'COB_PENDIENTE'
  OR wf_estado1.estado       = 'COB_PROCESO'
  OR wf_estado1.estado       = 'COB_COBRADO'
  OR wf_estado1.estado       = 'COB_REEDITADO'
  OR wf_estado1.estado       = 'COB_ANULADO')
  
  UNION
  
  SELECT ed_cobro.id_cobro,
    ed_cobro.id_cobro_padre,
    ed_cobro.id_operacion,
    wf_estado.estado,
    NULL AS subestado,
    TO_CHAR(wf_estado.fecha_modificacion, 'DD/MM/YYYY - HH24:MI:SS') AS fecha_modificacion,
    wf_estado.usuario_modificacion,
    wf_estado.observacion,
    sct.mensaje_estado,
    sct.numero_transaccion,
    scf.tipo_comprobante,
    scf.clase_comprobante,
    scf.sucursal_comprobante,
    scf.numero_comprobante,
    scf.importe_cobrar,
    TO_CHAR(scf.fecha_valor, 'DD/MM/YYYY - HH24:MI:SS') AS fecha_valor,
    sscdmp.referencia,
    scatc.acuerdo_traslado_cargo
  FROM
    (SELECT swwe.id_workflow,
      swwe.estado,
      swwe.fecha_modificacion,
      swwe.usuario_modificacion,
      DBMS_LOB.substr(swwe.datos_modificados,1,4000) as observacion
    FROM shv_wf_workflow_estado swwe
    WHERE (swwe.estado != 'COB_NO_DISPONIBLE')
    UNION
    SELECT sweh.id_workflow,
      sweh.estado,
      sweh.fecha_modificacion,
      sweh.usuario_modificacion,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) as observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado != 'COB_NO_DISPONIBLE'
    ) wf_estado
  RIGHT JOIN SHV_COB_ED_COBRO ed_cobro
  ON (wf_estado.id_workflow = ed_cobro.id_workflow)
  FULL JOIN shv_cob_transaccion sct
  ON (ed_cobro.id_operacion = sct.id_operacion)
  FULL JOIN shv_cob_factura scf
  ON (sct.id_transaccion = scf.id_transaccion)
  FULL JOIN shv_sop_cobros_detalle_med_pag sscdmp
  ON (sct.id_transaccion = sscdmp.id_transaccion)
  FULL JOIN
    (SELECT scf.id_transaccion,
      scf.acuerdo_traslado_cargo
    FROM shv_cob_factura scf
    WHERE scf.estado='ERROR'
    UNION
    SELECT sscdmp.id_transaccion,
      sscdmp.acuerdo_traslado_cargo
    FROM shv_sop_cobros_detalle_med_pag sscdmp
    WHERE sscdmp.estado='ERROR'
    ) scatc
  ON (sct.id_transaccion  = scatc.id_transaccion)
  WHERE wf_estado.estado != 'COB_NO_DISPONIBLE'
  AND (wf_estado.estado   = 'COB_ERROR_COBRO'
  OR wf_estado.estado     = 'COB_ERROR_APROPIACION'
  OR wf_estado.estado     = 'COB_ERROR_DESAPROPIACION'
  OR wf_estado.estado     = 'COB_ERROR_CONFIRMACION'
  OR wf_estado.estado     = 'COB_CONFIRMADO_CON_ERROR'));
  
CREATE OR REPLACE FORCE VIEW SHV_VAL_BONIFICACION_INTERES_V 
AS
  ( SELECT DISTINCT TO_CHAR(swwe.FECHA_MODIFICACION, 'DD/MM/YYYY') AS FECHA_DE_OPERACION ,
    sweh.USUARIO_MODIFICACION                                      AS USUARIO ,
    LPAD(sco.ID_OPERACION, 7, '0')                                 AS ID_OPERACION_SHIVA ,
    LPAD(sco.ID_OPERACION, 7, '0')
    || '.'
    || LPAD(sct.NUMERO_TRANSACCION, 5, '0') AS ID_TRANSACCION_SHIVA ,
    scf.ID_CLIENTE_LEGADO                   AS CLIENTE ,
    CASE
      WHEN (SELECT COUNT(scfc2.ID_FACTURA)
        FROM shv_cob_factura_calipso scfc2
        WHERE scfc2.ID_FACTURA = scf.ID_FACTURA) > 0
      THEN 'Calipso'
      WHEN (SELECT COUNT(scfm2.ID_FACTURA)
        FROM shv_cob_factura_mic scfm2
        WHERE scfm2.ID_FACTURA = scf.ID_FACTURA) > 0
      THEN 'MIC'
      ELSE 'N/A'
    END AS SISTEMA_COBRADOR ,
    tipo_comprobante
    ||' '
    ||DECODE (clase_comprobante,'S','','D','', clase_comprobante)
    ||lpad(sucursal_comprobante, 4, '0')
    ||'-'
    ||lpad(numero_comprobante, 8, '0') AS NUMERO_DE_FACTURA ,
    scfm.ID_REFERENCIA_FACTURA         AS REFERENCIA_DE_FACTURA ,
    scf.FECHA_VENCIMIENTO              AS FECHA_PRIMER_VTO ,
    scf.IMPORTE_ORIGINAL               AS IMPORTE_ORIGINAL_DE_LA_FACTURA ,
    scf.FECHA_VALOR                    AS FECHA_VALOR_DEL_COBRO ,
    scfc.TIPO_CAMBIO                   AS TIPO_CAMBIO_A_FECHA_DE_COBRO ,
    scf.TIPO_PAGO                      AS TIPO_DE_COBRO ,
    CASE
      WHEN (scf.QUE_HACER_CON_INTERESES = 'BV')
      THEN 100
      WHEN (scf.QUE_HACER_CON_INTERESES = 'BM')
      THEN 100
      ELSE scf.PORCENTAJE_BONIF_INTERESES
    END                                 AS PORCENTAJE_DE_BONIFICACION ,
    scf.IMPORTE_COBRAR                  AS IMPORTE_COBRADO ,
    scf.COBRADOR_INTERESES_GENERADOS    AS INTERESES_GANADOS ,
    scfm.INTERES_BONIFICADO_REGULADO    AS INTERES_BONIF_REGULADO_MIC ,
    scfm.INTERES_BONIFICADO_NO_REGULADO AS INTERES_BONIF_NO_REGULADO_MIC ,
    scfc.COBRADOR_INTERESES_BONIFICADOS AS INTERESES_BONIFICADOS_CALIPSO
  FROM shv_cob_factura scf
  LEFT JOIN shv_cob_factura_mic scfm
  ON scf.ID_FACTURA = scfm.ID_FACTURA
  LEFT JOIN shv_cob_factura_calipso scfc
  ON scf.ID_FACTURA = scfc.ID_FACTURA
  INNER JOIN shv_cob_transaccion sct
  ON scf.ID_TRANSACCION = sct.ID_TRANSACCION
  INNER JOIN shv_cob_operacion sco
  ON sct.ID_OPERACION = sco.ID_OPERACION
  INNER JOIN shv_cob_cobro scc
  ON sco.ID_OPERACION = scc.ID_OPERACION
  INNER JOIN shv_wf_workflow_estado_hist sweh
  ON (scc.ID_WORKFLOW = sweh.ID_WORKFLOW
  AND sweh.ESTADO     ='COB_PENDIENTE')
  INNER JOIN shv_wf_workflow_estado swwe
  ON scc.ID_WORKFLOW = swwe.ID_WORKFLOW
  INNER JOIN SHV_PARAM_WORKFLOW_ESTADO spwe
  ON spwe.ID_ESTADO                        = swwe.ESTADO
  WHERE swwe.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND ((scfm.INTERES_BONIFICADO_REGULADO  IS NOT NULL
  AND scfm.INTERES_BONIFICADO_REGULADO     > 0)
  OR (scfm.INTERES_BONIFICADO_NO_REGULADO IS NOT NULL
  AND scfm.INTERES_BONIFICADO_NO_REGULADO  > 0)
  OR (scfc.COBRADOR_INTERESES_BONIFICADOS  IS NOT NULL
  AND scfc.COBRADOR_INTERESES_BONIFICADOS  > 0))
  );