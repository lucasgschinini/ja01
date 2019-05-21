--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de facturas -
--------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_DETALLE_FACTURA AS (
  --
  -- Facturas Mic
  --
  SELECT
    -- Datos del documento
    scf.id_factura,
    scf.id_transaccion,
    scf.estado,
    scf.sistema_origen                             AS sistema_origen,
    scf.tipo_comprobante                           AS tipo_comprobante,
    NVL(TO_CHAR(scfm.tipo_factura), scfm.tipo_duc) AS subtipo_documento,
    NULL                                           AS origen_documento,
    DECODE (scf.clase_comprobante,'S','','D','', scf.clase_comprobante
    ||'-')
    ||lpad(scf.sucursal_comprobante, 4, '0')
    ||'-'
    ||lpad(scf.numero_comprobante, 8, '0') AS numero_documento,
    scfm.id_referencia_factura             AS numero_referencia_mic,
    scf.fecha_vencimiento                  AS fecha_vencimiento,
    scfm.fecha_segundo_vencimiento         AS fecha_segundo_vencimiento,
    'PES'                                  AS moneda, -- Los documentos de MIC son todos en Pesos, por lo que se presenta el valor 'PES' por defecto
    scf.fecha_valor                        AS fecha_cobro,
    scf.importe_cobrar                     AS importe_a_cobrar,
    scf.importe_original                   AS importe_original,
    scf.saldo_actual                       AS saldo_actual,
    NULL                                   AS tipo_de_cambio_fecha_cobro,
    NULL                                   AS tipo_de_cambio_fecha_emision,
    NULL                                   AS importe_aplic_fec_emis_mon_ori,
    scf.monto_acumulado_simulacion         AS monto_acumulado_simulacion,
    scf.fecha_acumulado_simulacion         AS fecha_acumulado_simulacion,
    -- Tipo de pago
    scf.tipo_pago AS tipo_pago,
    -- Gestion de intereses
    scf.que_hacer_con_intereses       AS que_hacer_con_intereses,
    scf.cobrador_intereses_generados  AS cobrador_intereses_generados,
    scf.check_trasladar_intereses     AS check_trasladar_intereses,
    scfm.cobrar_segundo_vencimiento   AS check_cobrar_segundo_vencimien,
    scf.porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
    scf.importe_bonif_intereses       AS importe_bonif_intereses,
    scf.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    scf.tipo_mensaje_estado AS tipo_mensaje_estado,
    scf.mensaje_estado      AS mensaje_estado
  FROM shv_cob_factura scf,
    shv_cob_factura_mic scfm
  WHERE scfm.id_factura = scf.id_factura
  --
  UNION
  --
  -- Facturas Calipso
  --
  SELECT
    -- Datos del documento
    scf.id_factura,
    scf.id_transaccion,
    scf.estado,
    scf.sistema_origen    AS sistema_origen,
    scf.tipo_comprobante  AS tipo_comprobante,
    NULL                  AS subtipo_documento,
    scfc.origen_documento AS origen_documento,
    DECODE (scf.clase_comprobante,'S','','D','', scf.clase_comprobante
    ||'-')
    ||lpad(scf.sucursal_comprobante, 4, '0')
    ||'-'
    ||lpad(scf.numero_comprobante, 8, '0') AS numero_documento,
    NULL                                   AS numero_referencia_mic,
    scf.fecha_vencimiento                  AS fecha_vencimiento,
    NULL                                   AS fecha_segundo_vencimiento,
    scfc.moneda                            AS moneda,
    scf.fecha_valor                        AS fecha_cobro,
    scf.importe_cobrar                     AS importe_a_cobrar,
    scf.importe_original                   AS importe_original,
    scf.saldo_actual                       AS saldo_actual,
    scfc.tipo_de_cambio_fecha_cobro        AS tipo_de_cambio_fecha_cobro,
    scfc.tipo_de_cambio_fecha_emision      AS tipo_de_cambio_fecha_emision,
    scfc.importe_aplic_fec_emis_mon_ori    AS importe_aplic_fec_emis_mon_ori,
    scf.monto_acumulado_simulacion         AS monto_acumulado_simulacion,
    scf.fecha_acumulado_simulacion         AS fecha_acumulado_simulacion,
    -- Tipo de pago
    scf.tipo_pago AS tipo_pago,
    -- Gestion de intereses
    scf.que_hacer_con_intereses       AS que_hacer_con_intereses,
    scf.cobrador_intereses_generados  AS cobrador_intereses_generados,
    scf.check_trasladar_intereses     AS check_trasladar_intereses,
    NULL                              AS check_cobrar_segundo_vencimien,
    scf.porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
    scf.importe_bonif_intereses       AS importe_bonif_intereses,
    scf.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    scf.tipo_mensaje_estado AS tipo_mensaje_estado,
    scf.mensaje_estado      AS mensaje_estado
  FROM shv_cob_factura scf,
    shv_cob_factura_calipso scfc
  WHERE scfc.id_factura = scf.id_factura
  );

--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de medios de pago -
--------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_DETALLE_MED_PAG AS
  (
  SELECT scmp.id_medio_pago,
    scmp.id_tipo_medio_pago,
    sptmp.descripcion descripcion_medio_pago,
    scmpd.subtipo_medio_pago,
    scmpd.origen_documento AS origen_documento,
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
    scmpd.que_hacer_con_intereses      AS que_hacer_con_intereses,
    scmpd.cobrador_intereses_generados AS cobrador_intereses_generados,
    scmpd.check_trasladar_intereses    AS check_trasladar_intereses,
    scmpd.porcentaje_bonif_intereses   AS porcentaje_bonif_intereses,
    scmpd.importe_bonif_intereses      AS importe_bonif_intereses,
    scmpd.acuerdo_traslado_cargo,
    scmpd.estado_acuerdo_traslado_cargo,
    scmpd.id_cliente_acuerdo_tras_cargo
  FROM shv_param_tipo_medio_pago sptmp,
    shv_cob_med_pago scmp,
    (
    --
    -- Medio pago Shiva
    --
    SELECT id_medio_pago              AS id_medio_pago,
      referencia_valor                AS referencia,
      fecha_valor                     AS fecha_valor,
      svvr.id_tipo_retencion_impuesto AS subtipo_medio_pago,
      NULL                            AS origen_documento,
      -- Gestion de intereses
      NULL as que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_shiva scmps,
      shv_val_valor_retencion svvr
    WHERE scmps.id_valor = svvr.id_valor (+)
    UNION
    --
    -- Medio pago Compensacion
    --
    SELECT id_medio_pago,
      numero_compensacion AS refmediopago,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_compensacion
    UNION
    --
    -- Medio pago Compensacion Intercompany
    --
    SELECT id_medio_pago,
      numero_compensacion,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_comp_intercom
    UNION
    --
    -- Medio pago Compensacion Liquido Producto
    --
    SELECT id_medio_pago,
      numero_compensacion,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_comp_liquido
    UNION
    --
    -- Medio pago Desistimiento
    --
    SELECT id_medio_pago,
      numero_acta,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_desistimiento
    UNION
    --
    -- Medio de pago Plan de Pago
    --
    SELECT id_medio_pago,
      NULL,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_plan_pago
    UNION
    --
    -- Medio pago CTA
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante)
      ||lpad(sucursal_comprobante, 4, '0')
      ||'-'
      ||lpad(numero_comprobante, 8, '0'),
      fecha_emision,
      NULL             AS subtipo_medio_pago,
      origen_documento AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_cta
    UNION
    --
    -- Medio pago Nota de Credito Calipso
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante)
      ||lpad(sucursal_comprobante, 4, '0')
      ||'-'
      ||lpad(numero_comprobante, 8, '0'),
      fecha_emision,
      NULL             AS subtipo_medio_pago,
      origen_documento AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_nota_cred_clp
    UNION
    --
    -- Medio de pago Debito Proxima Factura Calipso
    --
    SELECT id_medio_pago,
      NULL,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      que_hacer_con_intereses       AS que_hacer_con_intereses,
      cobrador_intereses_generados  AS cobrador_intereses_generados,
      check_trasladar_intereses     AS check_trasladar_intereses,
      porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
      importe_bonif_intereses       AS importe_bonif_intereses,
      acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_deb_prox_clp
    UNION
    --
    -- Medio pago Remanente
    --
    SELECT id_medio_pago,
      cuenta_remanente
      ||tipo_remanente,
      fecha_alta,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_remanente
    UNION
    --
    -- Medio pago Nota de Credito Mic
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','S','', clase_comprobante)
      ||lpad(sucursal_comprobante, 4, '0')
      ||'-'
      ||lpad(numero_comprobante, 8, '0'),
      fecha_emision,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_nota_cred_mic
    UNION
    --
    -- Medio de pago Debito Proxima Factura Mic
    --
    SELECT id_medio_pago,
      NULL,
      fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      que_hacer_con_intereses       AS que_hacer_con_intereses,
      cobrador_intereses_generados  AS cobrador_intereses_generados,
      check_trasladar_intereses     AS check_trasladar_intereses,
      porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
      importe_bonif_intereses       AS importe_bonif_intereses,
      acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo
    FROM shv_cob_med_pag_deb_prox_mic
    ) scmpd
  WHERE scmpd.id_medio_pago    = scmp.id_medio_pago
  AND sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
  );
--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de grilla de transacciones - 
--------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_GRILLA_TRANSAC AS  (
  --
  -- Datos de Facturas
  --
  SELECT
    -- Datos de la transaccion
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion, 5, '0') AS numero_transaccion_formateado,
    sct.estado                             AS estado_transaccion,
    -- Datos del documento a cobrar
    sscdf.sistema_origen                 AS sistema_origen_documento,
    sscdf.tipo_comprobante               AS tipo_comprobante,
    sscdf.subtipo_documento              AS subtipo_documento,
    sscdf.numero_documento               AS numero_documento,
    sscdf.numero_referencia_mic          AS numero_referencia_mic,
    sscdf.fecha_vencimiento              AS fecha_vencimiento,
    sscdf.fecha_segundo_vencimiento      AS fecha_segundo_vencimiento,
    sscdf.moneda                         AS moneda,
    sscdf.fecha_cobro                    AS fecha_cobro,
    sscdf.importe_a_cobrar               AS importe_a_cobrar,
    sscdf.importe_original               AS importe_original,
    sscdf.saldo_actual                   AS saldo_actual,
    sscdf.tipo_de_cambio_fecha_cobro     AS tipo_de_cambio_fecha_cobro,
    sscdf.tipo_de_cambio_fecha_emision   AS tipo_de_cambio_fecha_emision,
    sscdf.importe_aplic_fec_emis_mon_ori AS importe_aplic_fec_emis_mon_ori,
    sscdf.monto_acumulado_simulacion     AS monto_acumulado_simulacion_deb,
    sscdf.fecha_acumulado_simulacion     AS fecha_acumulado_simulacion_deb,
    -- Datos comunes
    sscdf.origen_documento AS origen_documento,
    -- Datos de medios de pago
    NULL AS sistema_origen_medio_pago,
    NULL AS tipo_medio_pago,
    NULL AS subtipo_medio_pago,
    NULL AS referencia_medio_pago,
    NULL AS fecha_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    sscdf.tipo_pago AS tipo_pago,
    -- Gestion de intereses
    sscdf.que_hacer_con_intereses       AS que_hacer_con_intereses,
    sscdf.cobrador_intereses_generados  AS intereses_generados,
    sscdf.check_trasladar_intereses     AS check_trasladar_intereses,
    sscdf.check_cobrar_segundo_vencimien AS check_cobrar_segundo_vencimien,
    sscdf.porcentaje_bonif_intereses    AS porcentaje_a_bonificar,
    sscdf.importe_bonif_intereses       AS importe_a_bonificar,
    sscdf.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    sscdf.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    sscdf.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    sct.tipo_mensaje_estado   AS tipo_mensaje_estado_transaccio,
    sct.mensaje_estado        AS mensaje_estado_transaccion,
    sscdf.tipo_mensaje_estado AS tipo_mensaje_estado_debito,
    sscdf.mensaje_estado      AS mensaje_estado_debito,
    sscdf.estado              AS estado_debito,
    NULL                      AS tipo_mensaje_estado_credito,
    NULL                      AS mensaje_estado_credito,
    NULL                      AS estado_credito,
    -- Datos ocultos del cobro / transaccion
    scc.id_cobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    decode(sct.id_transaccion_padre, null, null, 
          lpad(sctp.id_operacion, 7, '0')
          ||'.'
          ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_sop_cobros_detalle_factura sscdf
  WHERE sscdf.id_transaccion = sct.id_transaccion
  AND scc.id_operacion       = sct.id_operacion
  AND sct.id_transaccion_padre = sctp.id_transaccion (+)
  --
  UNION
  --
  -- Datos de medios de pago utilizados
  --
  SELECT
    -- Datos de la transaccion
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion, 5, '0') AS numero_transaccion_formateado,
    sct.estado                             AS estado_transaccion,
    -- Datos del documento a cobrar
    NULL AS sistema_origen_documento,
    NULL AS tipo_comprobante,
    NULL AS subtipo_documento,
    NULL AS numero_documento,
    NULL AS numero_referencia_mic,
    NULL AS fecha_vencimiento,
    NULL AS fecha_segundo_vencimiento,
    NULL AS moneda,
    NULL AS fecha_cobro,
    NULL AS importe_a_cobrar,
    NULL AS importe_original,
    NULL AS saldo_actual,
    NULL AS tipo_de_cambio_fecha_cobro,
    NULL AS tipo_de_cambio_fecha_emision,
    NULL AS importe_aplic_fec_emis_mon_ori,
    NULL AS monto_acumulado_simulacion,
    NULL AS fecha_acumulado_simulacion,
    -- Datos comunes
    sscdmp.origen_documento AS origen_documento,
    -- Datos de medios de pago
    sscdmp.sistema_origen             AS sistema_origen_medio_pago,
    sscdmp.descripcion_medio_pago     AS tipo_medio_pago,
    sscdmp.subtipo_medio_pago         AS subtipo_medio_pago,
    sscdmp.referencia                 AS referencia_medio_pago,
    sscdmp.fecha_valor                AS fecha_medio_pago,
    sscdmp.importe                    AS importe_medio_pago,
    sscdmp.monto_acumulado_simulacion AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sscdmp.que_hacer_con_intereses       AS que_hacer_con_intereses,
    sscdmp.cobrador_intereses_generados  AS intereses_generados,
    sscdmp.check_trasladar_intereses     AS check_trasladar_intereses,
    NULL                                 AS check_cobrar_segundo_vencimien,
    sscdmp.porcentaje_bonif_intereses    AS porcentaje_a_bonificar,
    sscdmp.importe_bonif_intereses       AS importe_a_bonificar,
    sscdmp.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    sscdmp.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    sscdmp.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    sct.tipo_mensaje_estado    AS tipo_mensaje_estado_transaccio,
    sct.mensaje_estado         AS mensaje_estado_transaccion,
    NULL                       AS tipo_mensaje_estado_debito,
    NULL                       AS mensaje_estado_debito,
    NULL                       AS estado_debito,
    sscdmp.tipo_mensaje_estado AS tipo_mensaje_estado_credito,
    sscdmp.mensaje_estado      AS mensaje_estado_credito,
    sscdmp.estado              AS estado_credito,
    -- Datos ocultos del cobro / transaccion
    scc.id_cobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    decode(sct.id_transaccion_padre, null, null, 
          lpad(sctp.id_operacion, 7, '0')
          ||'.'
          ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    NULL                 AS id_factura,
    sscdmp.id_medio_pago AS id_medio_pago,
    NULL                 AS id_tratamiento_diferencia
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_sop_cobros_detalle_med_pag sscdmp
  WHERE sscdmp.id_transaccion = sct.id_transaccion
  AND scc.id_operacion        = sct.id_operacion
  AND sct.id_transaccion_padre = sctp.id_transaccion (+)
  --
  UNION
  --
  -- Datos de tratamiento de diferencia
  --
  SELECT
    -- Datos de la transaccion
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion, 5, '0') AS numero_transaccion_formateado,
    sct.estado                             AS estado_transaccion,
    -- Datos del documento a cobrar
    NULL                                                                        AS sistema_origen_documento,
    DECODE(sctd.tipo_tratamiento_diferencia, 'ENVIO_A_GANANCIAS', 'GAN', 'REI') AS tipo_comprobante,
    sctd.tipo_tratamiento_diferencia                                            AS subtipo_documento,
    NULL                                                                        AS numero_documento,
    NULL                                                                        AS numero_referencia_mic,
    NULL                                                                        AS fecha_vencimiento,
    NULL                                                                        AS fecha_segundo_vencimiento,
    NULL                                                                        AS moneda,
    sctd.fecha_valor                                                            AS fecha_cobro,
    sctd.importe                                                                AS importe_a_cobrar,
    NULL                                                                        AS importe_original,
    NULL                                                                        AS saldo_actual,
    NULL                                                                        AS tipo_de_cambio_fecha_cobro,
    NULL                                                                        AS tipo_de_cambio_fecha_emision,
    NULL                                                                        AS importe_aplic_fec_emis_mon_ori,
    NULL                                                                        AS monto_acumulado_simulacion,
    NULL                                                                        AS fecha_acumulado_simulacion,
    -- Datos comunes
    NULL AS origen_documento,
    -- Datos de medios de pago
    NULL AS sistema_origen_medio_pago,
    NULL AS tipo_medio_pago,
    NULL AS subtipo_medio_pago,
    NULL AS referencia_medio_pago,
    NULL AS fecha_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sctd.que_hacer_con_intereses       AS que_hacer_con_intereses,
    sctd.cobrador_intereses_generados  AS intereses,
    sctd.check_trasladar_intereses     AS check_trasladar_intereses,
    NULL                               AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses    AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses       AS importe_a_bonificar,
    sctd.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    sct.tipo_mensaje_estado  AS tipo_mensaje_estado_transaccio,
    sct.mensaje_estado       AS mensaje_estado_transaccion,
    sctd.tipo_mensaje_estado AS tipo_mensaje_estado_debito,
    sctd.mensaje_estado      AS mensaje_estado_debito,
    NULL                     AS estado_debito,
    NULL                     AS tipo_mensaje_estado_credito,
    NULL                     AS mensaje_estado_credito,
    NULL                     AS estado_credito,
    -- Datos ocultos del cobro / transaccion
    scc.id_cobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    decode(sct.id_transaccion_padre, null, null, 
          lpad(sctp.id_operacion, 7, '0')
          ||'.'
          ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    NULL                           AS id_factura,
    NULL                           AS id_medio_pago,
    sctd.id_tratamiento_diferencia AS id_tratamiento_diferencia
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion = sct.id_transaccion
  AND scc.id_operacion      = sct.id_operacion
  AND sct.id_transaccion_padre = sctp.id_transaccion (+)
  );
  
--------------------------------------------------------------------------------------------------------------
-- Creación de vista para subdiario de cobranzas
--------------------------------------------------------------------------------------------------------------
  CREATE OR REPLACE FORCE VIEW SHV_SOP_SUBDIARIO AS (
  
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    fac.tipo_comprobante                  AS tipoComprobante,
    fac.clase_comprobante
    ||'-'
    || lpad(fac.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(fac.numero_comprobante,8,'0') AS documentoLegal,
    fac.id_cliente_legado                 AS clienteLegado,
    fmic.razon_social_cliente_legado      AS razonSocial,
    'PES'                                 AS moneda,
    fac.importe_cobrar                    AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    fac.fecha_valor                       AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_mic fmic
  WHERE cob.id_workflow  =we.id_workflow
  AND cob.id_operacion   =op.id_operacion
  AND op.id_operacion    =doc.id_operacion
  AND op.id_operacion    =tran.id_operacion
  AND tran.id_transaccion=fac.id_transaccion
  AND fac.id_factura     =fmic.id_factura
  AND we.estado         IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    fac.tipo_comprobante                  AS tipoComprobante,
    fac.clase_comprobante
    ||'-'
    || lpad(fac.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(fac.numero_comprobante,8,'0') AS documentoLegal,
    fac.id_cliente_legado                 AS clienteLegado,
    fcal.razon_social_cliente_legado      AS razonSocial,
    fcal.moneda                           AS moneda,
    fac.importe_cobrar                    AS importe,
    fcal.importe_aplic_fec_emis_mon_pes   AS importe_aplicado_en_pesos,
    fcal.importe_aplic_fec_emis_mon_ori   AS importe_moneda_origen,
    fac.fecha_valor                       AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow  =we.id_workflow
  AND cob.id_operacion   =op.id_operacion
  AND op.id_operacion    =doc.id_operacion
  AND op.id_operacion    =tran.id_operacion
  AND tran.id_transaccion=fac.id_transaccion
  AND fac.id_factura     =fcal.id_factura
  AND we.estado         IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    fac.tipo_comprobante                  AS tipoComprobante,
    fac.clase_comprobante
    ||'-'
    || lpad(fac.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(fac.numero_comprobante,8,'0') AS documentoLegal,
    fac.id_cliente_legado                 AS clienteLegado,
    fmic.razon_social_cliente_legado      AS razonSocial,
    'PES'                                 AS moneda,
    fac.importe_cobrar                    AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    fac.fecha_valor                       AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_mic fmic
  WHERE cob.id_workflow  =we.id_workflow
  AND cob.id_operacion   =op.id_operacion
  AND op.id_operacion    =doc.id_operacion
  AND op.id_operacion    =tran.id_operacion
  AND tran.id_transaccion=fac.id_transaccion
  AND fac.id_factura     =fmic.id_factura
  AND we.estado          ='DES_DESCOBRADO'
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    fac.tipo_comprobante                  AS tipoComprobante,
    fac.clase_comprobante
    ||'-'
    || lpad(fac.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(fac.numero_comprobante,8,'0') AS documentoLegal,
    fac.id_cliente_legado                 AS clienteLegado,
    fcal.razon_social_cliente_legado      AS razonSocial,
    fcal.moneda                           AS moneda,
    fac.importe_cobrar                    AS importe,
    fcal.importe_aplic_fec_emis_mon_pes   AS importe_aplicado_en_pesos,
    fcal.importe_aplic_fec_emis_mon_ori   AS importe_moneda_origen,
    fac.fecha_valor                       AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow  =we.id_workflow
  AND cob.id_operacion   =op.id_operacion
  AND op.id_operacion    =doc.id_operacion
  AND op.id_operacion    =tran.id_operacion
  AND tran.id_transaccion=fac.id_transaccion
  AND fac.id_factura     =fcal.id_factura
  AND we.estado          ='DES_DESCOBRADO'
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    ncClp.moneda                            AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp
  WHERE cob.id_workflow   =we.id_workflow
  AND cob.id_operacion    =op.id_operacion
  AND op.id_operacion     =doc.id_operacion
  AND op.id_operacion     =tran.id_operacion
  AND tran.id_transaccion =mp.id_transaccion
  AND ncClp.id_medio_pago = mp.id_medio_pago
  AND we.estado          IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    ncClp.moneda                            AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp
  WHERE cob.id_workflow   =we.id_workflow
  AND cob.id_operacion    =op.id_operacion
  AND op.id_operacion     =doc.id_operacion
  AND op.id_operacion     =tran.id_operacion
  AND tran.id_transaccion =mp.id_transaccion
  AND ncClp.id_medio_pago = mp.id_medio_pago
  AND we.estado           ='DES_DESCOBRADO'
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    'PES'                                   AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_mic ncMic
  WHERE cob.id_workflow   =we.id_workflow
  AND cob.id_operacion    =op.id_operacion
  AND op.id_operacion     =doc.id_operacion
  AND op.id_operacion     =tran.id_operacion
  AND tran.id_transaccion =mp.id_transaccion
  AND ncMic.id_medio_pago = mp.id_medio_pago
  AND we.estado          IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    op.tipo_operacion                     AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    'PES'                                   AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_mic ncMic
  WHERE cob.id_workflow   =we.id_workflow
  AND cob.id_operacion    =op.id_operacion
  AND op.id_operacion     =doc.id_operacion
  AND op.id_operacion     =tran.id_operacion
  AND tran.id_transaccion =mp.id_transaccion
  AND ncMic.id_medio_pago = mp.id_medio_pago
  AND we.estado           ='DES_DESCOBRADO'
  UNION
  
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*---------------------------------------------G A N A N C I A (COBROS)-----------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*GANANCIA- NOTA DE CREDITO CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = ncClp.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- CTA CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    mpCta.tipo_comprobante                AS tipoComprobante,
    mpCta.clase_comprobante
    ||'-'
    || lpad(mpCta.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(mpCta.numero_comprobante,8,'0') AS documentoLegal,
    mpCta.id_cliente_legado                 AS clienteLegado,
    NULL                                    AS razonSocial,
    mp.moneda                               AS moneda,
    tdif.importe                            AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    mpCta.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta mpCta,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpCta.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- NOTA DE CREDITO MIC*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_Mic ncMic,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND mp.id_medio_pago                 = ncMic.id_medio_pago
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-CHEQUE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'CHQ'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (2,5)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-TRANSFERENCIA*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'TRF'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =8
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-CHEQUE DIFERIDO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'CHD'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (3,6)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-INTERDEPOSITO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'INT'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =9
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-REMANENTE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'REM'                                 AS tipoComprobante,
    NULL                                  AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    rem.fecha_alta                        AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_remanente rem,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND rem.id_medio_pago                = mp.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*GANANCIA- SHIVA-EFECTIVO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'GANANCIA'                            AS tipoOperacion,
    'BOL'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (4,7)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*---------------------------------------------R E I N T E G R O (COBROS)---------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*REINTEGRO- NOTA DE CREDITO CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = ncClp.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- CTA CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    mpCta.tipo_comprobante                AS tipoComprobante,
    mpCta.clase_comprobante
    ||'-'
    || lpad(mpCta.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(mpCta.numero_comprobante,8,'0') AS documentoLegal,
    mpCta.id_cliente_legado                 AS clienteLegado,
    NULL                                    AS razonSocial,
    mp.moneda                               AS moneda,
    tdif.importe                            AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    mpCta.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta mpCta,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpCta.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- NOTA DE CREDITO MIC*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_Mic ncMic,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND mp.id_medio_pago                 = ncMic.id_medio_pago
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-CHEQUE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'CHQ'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (2,5)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-TRANSFERENCIA*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'TRF'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =8
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-CHEQUE DIFERIDO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'CHD'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (3,6)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-INTERDEPOSITO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'INT'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =9
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-REMANENTE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'REM'                                 AS tipoComprobante,
    NULL                                  AS documentoLegal,
    rem.id_cliente_legado                 AS clienteLegado,
    NULL                                  AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    rem.fecha_alta                        AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_remanente rem,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND rem.id_medio_pago                = mp.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*REINTEGRO- SHIVA-EFECTIVO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REINTEGRO'                           AS tipoOperacion,
    'BOL'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_cobro cob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE cob.id_workflow                =we.id_workflow
  AND cob.id_operacion                 =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (4,7)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  UNION
  
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*---------------------------------------------G A N A N C I A (DESCOBROS)-----------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*GANANCIA- NOTA DE CREDITO CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = ncClp.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- CTA CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    mpCta.tipo_comprobante                AS tipoComprobante,
    mpCta.clase_comprobante
    ||'-'
    || lpad(mpCta.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(mpCta.numero_comprobante,8,'0') AS documentoLegal,
    mpCta.id_cliente_legado                 AS clienteLegado,
    NULL                                    AS razonSocial,
    mp.moneda                               AS moneda,
    tdif.importe                            AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    mpCta.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta mpCta,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpCta.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- NOTA DE CREDITO MIC*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_Mic ncMic,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND mp.id_medio_pago                 = ncMic.id_medio_pago
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-CHEQUE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'CHQ'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (2,5)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-TRANSFERENCIA*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'TRF'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =8
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-CHEQUE DIFERIDO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'CHD'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (3,6)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-INTERDEPOSITO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'INT'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =9
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-REMANENTE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'REM'                                 AS tipoComprobante,
    NULL                                  AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    rem.fecha_alta                        AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_remanente rem,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND rem.id_medio_pago                = mp.id_medio_pago
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*GANANCIA- SHIVA-EFECTIVO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_GANANCIA'                  AS tipoOperacion,
    'BOL'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (4,7)
  AND tdif.tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS'
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*---------------------------------------------R E I N T E G R O (DESCOBROS)---------------------------------------------------------*/
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*REINTEGRO- NOTA DE CREDITO CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    ncClp.tipo_comprobante                AS tipoComprobante,
    ncClp.clase_comprobante
    ||'-'
    || lpad(ncClp.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncClp.numero_comprobante,8,'0') AS documentoLegal,
    ncClp.id_cliente_legado                 AS clienteLegado,
    ncClp.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncClp.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncClp,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = ncClp.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- CTA CALIPSO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    mpCta.tipo_comprobante                AS tipoComprobante,
    mpCta.clase_comprobante
    ||'-'
    || lpad(mpCta.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(mpCta.numero_comprobante,8,'0') AS documentoLegal,
    mpCta.id_cliente_legado                 AS clienteLegado,
    NULL                                    AS razonSocial,
    mp.moneda                               AS moneda,
    tdif.importe                            AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    mpCta.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta mpCta,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpCta.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- NOTA DE CREDITO MIC*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    ncMic.tipo_comprobante                AS tipoComprobante,
    ncMic.clase_comprobante
    ||'-'
    || lpad(ncMic.sucursal_comprobante,4,'0')
    ||'-'
    || lpad(ncMic.numero_comprobante,8,'0') AS documentoLegal,
    ncMic.id_cliente_legado                 AS clienteLegado,
    ncMic.razon_social_cliente_legado       AS razonSocial,
    mp.moneda                               AS moneda,
    mp.importe                              AS importe,
    NULL                                    AS importe_aplicado_en_pesos,
    NULL                                    AS importe_moneda_origen,
    ncMic.fecha_emision                     AS fechaValor,
    op.id_caja                              AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_Mic ncMic,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND mp.id_medio_pago                 = ncMic.id_medio_pago
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-CHEQUE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'CHQ'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (2,5)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-TRANSFERENCIA*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'TRF'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =8
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-CHEQUE DIFERIDO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'CHD'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (3,6)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-INTERDEPOSITO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'INT'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor                =9
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-REMANENTE*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'REM'                                 AS tipoComprobante,
    NULL                                  AS documentoLegal,
    rem.id_cliente_legado                 AS clienteLegado,
    NULL                                  AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    rem.fecha_alta                        AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_remanente rem,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND rem.id_medio_pago                = mp.id_medio_pago
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  UNION
  
  /*REINTEGRO- SHIVA-EFECTIVO*/
  SELECT we.fecha_modificacion AS fechaProcesamiento,
    lpad(op.id_operacion, 7,'0')
    ||'.'
    ||lpad(tran.numero_transaccion,5,'0') AS idTransaccion,
    doc.numero_recibo                     AS numeroRecibo,
    'REVERSION_REINTEGRO'                 AS tipoOperacion,
    'BOL'                                 AS tipoComprobante,
    mpShiva.referencia_valor              AS documentoLegal,
    val.id_cliente_legado                 AS clienteLegado,
    val.razon_social_cliente_agrupador    AS razonSocial,
    mp.moneda                             AS moneda,
    mp.importe                            AS importe,
    NULL                                  AS importe_aplicado_en_pesos,
    NULL                                  AS importe_moneda_origen,
    mpShiva.fecha_valor                   AS fechaValor,
    op.id_caja                            AS idCaja
  FROM shv_cob_descobro descob,
    shv_wf_workflow_estado we,
    shv_cob_operacion op,
    shv_cob_documento doc,
    shv_cob_transaccion TRAN,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mpShiva,
    shv_val_valor val,
    shv_cob_tratamiento_diferencia tdif
  WHERE descob.id_workflow             =we.id_workflow
  AND descob.id_operacion              =op.id_operacion
  AND op.id_operacion                  =doc.id_operacion
  AND op.id_operacion                  =tran.id_operacion
  AND tran.id_transaccion              =mp.id_transaccion
  AND tran.id_transaccion              =tdif.id_transaccion
  AND mp.id_medio_pago                 = mpShiva.id_medio_pago
  AND mpShiva.id_valor                 = val.id_valor
  AND val.id_tipo_valor               IN (4,7)
  AND tipo_tratamiento_diferencia NOT IN ('ENVIO_A_GANANCIAS')
  AND we.estado                        ='DES_DESCOBRADO'
  );
  
/
Exit;