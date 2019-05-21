  CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_DET_MED_PAG (ID_MEDIO_PAGO, ID_TIPO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, ORIGEN_DOCUMENTO, ID_TRANSACCION, ESTADO, IMPORTE, MONTO_ACUMULADO_SIMULACION, MIGRADO_DEIMOS, ESTADO_DEIMOS, FECHA_ACTUALIZACION_DEIMOS, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO, MONEDA, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, FECHA_VALOR, REFERENCIA, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO, SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, ID_CLIENTE_ACUERDO_CONTRACARGO)
AS
  (SELECT scmp.id_medio_pago,
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
    scmp.id_cliente_legado,
    scmpd.fecha_valor,
    scmpd.referencia,
    -- Gestion de intereses
    scmpd.que_hacer_con_intereses        AS que_hacer_con_intereses,
    scmpd.cobrador_intereses_generados   AS cobrador_intereses_generados,
    scmpd.check_trasladar_intereses      AS check_trasladar_intereses,
    scmpd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    scmpd.porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
    scmpd.importe_bonif_intereses        AS importe_bonif_intereses,
    scmpd.sistema_acuerdo                AS sistema_acuerdo,
    scmpd.acuerdo_traslado_cargo,
    scmpd.estado_acuerdo_traslado_cargo,
    scmpd.id_cliente_acuerdo_tras_cargo,
    scmpd.estado_cargo_generado          AS estado_cargo_generado,
    scmpd.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    scmpd.acuerdo_contracargo            AS acuerdo_contracargo,
    scmpd.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    scmpd.TIPO_DE_CAMBIO_FECHA_COBRO     AS TIPO_DE_CAMBIO_FECHA_COBRO,
    scmpd.TIPO_DE_CAMBIO_FECHA_EMISION   AS TIPO_DE_CAMBIO_FECHA_EMISION,
    scmpd.IMPORTE_APLIC_FEC_EMIS_MON_ORI AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    scmpd.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo
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
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM SHV_COB_MED_PAG_COMP_OTRAS
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_plan_pago
    UNION
    --
    -- Medio pago CTA
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      TIPO_DE_CAMBIO_FECHA_COBRO,
      TIPO_DE_CAMBIO_FECHA_EMISION,
      IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_cta
    UNION
    --
    -- Medio pago Nota de Credito Calipso
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      TIPO_DE_CAMBIO_FECHA_COBRO,
      TIPO_DE_CAMBIO_FECHA_EMISION,
      IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_nota_cred_clp
    UNION
    --
    -- Medio de pago Debito Proxima Factura Calipso
    --
    SELECT scmpdpc.id_medio_pago,
      NULL,
      scmpdpc.fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      scmpdpc.que_hacer_con_intereses                                              AS que_hacer_con_intereses,
      NVL(scmpdpc.cobrador_interes_real_tras,scmpdpc.cobrador_intereses_generados) AS cobrador_intereses_generados,
      scmpdpc.check_trasladar_intereses                                            AS check_trasladar_intereses,
      scmpdpc.cobrador_intereses_trasladados                                       AS cobrador_intereses_trasladados,
      scmpdpc.porcentaje_bonif_intereses                                           AS porcentaje_bonif_intereses,
      scmpdpc.importe_bonif_intereses                                              AS importe_bonif_intereses,
      scmp1.sistema_origen                                                         AS sistema_acuerdo,
      scmpdpc.acuerdo_traslado_cargo                                               AS acuerdo_traslado_cargo,
      scmpdpc.estado_acuerdo_traslado_cargo                                        AS estado_acuerdo_traslado_cargo,
      scmpdpc.id_cliente_acuerdo_tras_cargo                                        AS id_cliente_acuerdo_tras_cargo,
      scmpdpc.estado_cargo_generado                                                AS estado_cargo_generado,
      scmpdpc.sistema_acuerdo_contracargo                                          AS sistema_acuerdo_contracargo,
      scmpdpc.acuerdo_contracargo                                                  AS acuerdo_contracargo,
      scmpdpc.estado_acuerdo_contracargo                                           AS estado_acuerdo_contracargo,
      NULL                                                                         AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL                                                                         AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL                                                                         AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      scmpdpc.id_cliente_acuerdo_contracargo                                       AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_deb_prox_clp scmpdpc,
      shv_cob_med_pago scmp1
    WHERE scmpdpc.id_medio_pago = scmp1.id_medio_pago
    UNION
    --
    -- Medio pago Remanente
    --
    SELECT id_medio_pago,
      cuenta_remanente
      ||tipo_remanente,
      fecha_alta,
      tipo_remanente AS subtipo_medio_pago,
      NULL           AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_remanente
    UNION
    --
    -- Medio pago Nota de Credito Mic
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS sistema_acuerdo,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS estado_cargo_generado,
      NULL AS sistema_acuerdo_contracargo,
      NULL AS acuerdo_contracargo,
      NULL AS estado_acuerdo_contracargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_nota_cred_mic
    UNION
    --
    -- Medio de pago Debito Proxima Factura Mic
    --
    SELECT scmpdpm.id_medio_pago,
      NULL,
      scmpdpm.fecha,
      NULL AS subtipo_medio_pago,
      NULL AS origen_documento,
      -- Gestion de intereses
      scmpdpm.que_hacer_con_intereses AS que_hacer_con_intereses,
      CASE
        WHEN scmpdpm.cobrador_interes_real_rg_tras IS NOT NULL
        AND scmpdpm.cobrador_interes_real_rg_tras  <> 0
        THEN scmpdpm.cobrador_interes_real_rg_tras
        ELSE scmpdpm.cobrador_intereses_generados
      END cobrador_intereses_generados,
      scmpdpm.check_trasladar_intereses      AS check_trasladar_intereses,
      scmpdpm.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
      scmpdpm.porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
      scmpdpm.importe_bonif_intereses        AS importe_bonif_intereses,
      scmp2.sistema_origen                   AS sistema_acuerdo,
      scmpdpm.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
      scmpdpm.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
      scmpdpm.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
      scmpdpm.estado_cargo_generado          AS estado_cargo_generado,
      scmpdpm.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
      scmpdpm.acuerdo_contracargo            AS acuerdo_contracargo,
      scmpdpm.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
      NULL                                   AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL                                   AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL                                   AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      scmpdpm.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_deb_prox_mic scmpdpm,
      shv_cob_med_pago scmp2
    WHERE scmpdpm.id_medio_pago = scmp2.id_medio_pago
    ) scmpd
  WHERE scmpd.id_medio_pago    = scmp.id_medio_pago
  AND sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
  );

  CREATE OR REPLACE FORCE VIEW SHV_SOP_TAGETIK (CICLO, TIPO_FACTURA, TIPO_PAGO, FECHA_EMISION, FECHA_VENCIMIENTO, FECHA_VALOR, ID_TIPO_MEDIO_PAGO, MARKETING, CAJA, IMPORTE, ID_CLIENTE, RAZON_SOCIAL, TIPO_CLIENTE, TIPO_COMPROBANTE, CLASE_COMPROBANTE, SUC_COMPROBANTE, NUM_COMPROBANTE, ID_CUENTA_COB, ID_OPE, MON, FECHA_WORKFLOW, ORIGEN, DESCOBRO, TIPO_CAMBIO, IMPORTE_MED_PAGO_MONEDA_ORGEN, TIPO_CAMBIO_MEDIO_PAGO, MONEDA_MEDIO_PAGO, ID_CUENTA_COB_PADRE, SUBTIPO_DE_DOCUMENTO, MONEDA_OPERACION, NUMERO_TRANSACCION)
AS
  (
  ----------------------------------------------------------------------------
  -- MIC
  ----------------------------------------------------------------------------
  -- Factura Mic (Cobro)
  ----------------------------------------------------------------------------
  SELECT fmic.ciclo_facturacion                                         AS ciclo,
    fmic.tipo_factura                                                   AS tipo_factura,
    fac.tipo_pago                                                       AS tipo_pago,
    fmic.fecha_emision                                                  AS fecha_emision,
    fac.fecha_vencimiento                                               AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    fmic.marketing_customer_group                                       AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    NULL                                                                AS id_cliente,
    NULL                                                                AS razon_social,
    NULL                                                                AS tipo_cliente,
    NULL                                                                AS tipo_comprobante,
    NULL                                                                AS clase_comprobante,
    NULL                                                                AS suc_comprobante,
    NULL                                                                AS num_comprobante,
    NULL                                                                AS id_cuenta_cob,
    NULL                                                                AS id_ope,
    NULL                                                                AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'MIC'                                                               AS origen,
    NULL                                                                AS descobro ,
    NULL                                                                AS tipo_cambio,
    -- Dollares
    NULL AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    NULL AS TIPO_CAMBIO_MEDIO_PAGO,
    NULL AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    NULL AS ID_CUENTA_COB_PADRE,
    NULL AS subtipo_de_documento,
    -- Dollares Dolares
    NULL                    AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_factura_mic fmic,
    shv_cob_med_pago mp
  WHERE cob.id_workflow     =we.id_workflow
  AND cob.id_operacion      =op.id_operacion
  AND op.id_operacion       =tran.id_operacion
  AND tran.id_transaccion   =fac.id_transaccion
  AND fac.id_factura        = fmic.id_factura
  AND tran.id_transaccion   =mp.id_transaccion
  AND we.estado            IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado            <> 'NUEVO'
  AND fac.estado           <> 'NUEVO'
  AND fac.tipo_comprobante <> 'DUC'
  GROUP BY fmic.ciclo_facturacion,
    fmic.tipo_factura ,
    fac.tipo_pago ,
    fmic.fecha_emision ,
    fac.fecha_vencimiento ,
    fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    fmic.marketing_customer_group ,
    op.id_caja,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Medio de pago Mic (Cobro)
  ----------------------------------------------------------------------------
  SELECT ncm.ciclo_facturacion                                          AS ciclo,
    ncm.tipo_factura                                                    AS tipo_factura,
    ncm.tipo_pago                                                       AS tipo_pago,
    ncm.fecha_emision                                                   AS fecha_emision,
    ncm.fecha_vencimiento                                               AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    ncm.marketing_customer_group                                        AS marketing,
    op.id_caja                                                          AS caja,
    -SUM(mp.importe)                                                    AS importe,
    NULL                                                                AS id_cliente,
    NULL                                                                AS razon_social,
    NULL                                                                AS tipo_cliente,
    NULL                                                                AS tipo_comprobante,
    NULL                                                                AS clase_comprobante,
    NULL                                                                AS suc_comprobante,
    NULL                                                                AS num_comprobante,
    NULL                                                                AS id_cuenta_cob,
    NULL                                                                AS id_ope,
    NULL                                                                AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'MIC'                                                               AS origen,
    NULL                                                                AS descobro ,
    NULL                                                                AS tipo_cambio,
    -- Dollares
    NULL AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    NULL AS TIPO_CAMBIO_MEDIO_PAGO,
    NULL AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    NULL AS ID_CUENTA_COB_PADRE,
    NULL AS subtipo_de_documento,
    -- Dollares Dolares
    NULL                    AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_mic ncm
  WHERE cob.id_workflow     =we.id_workflow
  AND cob.id_operacion      =op.id_operacion
  AND op.id_operacion       =tran.id_operacion
  AND tran.id_transaccion   =fac.id_transaccion
  AND tran.id_transaccion   =mp.id_transaccion
  AND mp.id_medio_pago      = ncm.id_medio_pago
  AND we.estado            IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado            <> 'NUEVO'
  AND fac.estado           <> 'NUEVO'
  AND fac.tipo_comprobante <> 'DUC'
  GROUP BY ncm.ciclo_facturacion ,
    ncm.tipo_factura ,
    ncm.tipo_pago ,
    ncm.fecha_emision ,
    ncm.fecha_vencimiento ,
    fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    ncm.marketing_customer_group ,
    op.id_caja,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Mic (DesCobro)
  ----------------------------------------------------------------------------
  SELECT fmic.ciclo_facturacion                                         AS ciclo,
    fmic.tipo_factura                                                   AS tipo_factura,
    fac.tipo_pago                                                       AS tipo_pago,
    fmic.fecha_emision                                                  AS fecha_emision,
    fac.fecha_vencimiento                                               AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    fmic.marketing_customer_group                                       AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    NULL                                                                AS id_cliente,
    NULL                                                                AS razon_social,
    NULL                                                                AS tipo_cliente,
    NULL                                                                AS tipo_comprobante,
    NULL                                                                AS clase_comprobante,
    NULL                                                                AS suc_comprobante,
    NULL                                                                AS num_comprobante,
    NULL                                                                AS id_cuenta_cob,
    NULL                                                                AS id_ope,
    NULL                                                                AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'MIC'                                                               AS origen,
    'X'                                                                 AS descobro,
    NULL                                                                AS tipo_cambio,-- Dollares
    NULL                                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    NULL                                                                AS TIPO_CAMBIO_MEDIO_PAGO,
    NULL                                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    NULL                                                                AS ID_CUENTA_COB_PADRE,
    NULL                                                                AS subtipo_de_documento,
    -- Dollares Dolares
    NULL                    AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_factura_mic fmic,
    shv_cob_med_pago mp
  WHERE cob.id_workflow     =we.id_workflow
  AND cob.id_operacion      =op.id_operacion
  AND op.id_operacion       =tran.id_operacion
  AND tran.id_transaccion   =fac.id_transaccion
  AND fac.id_factura        = fmic.id_factura
  AND tran.id_transaccion   =mp.id_transaccion
  AND we.estado             = 'DES_DESCOBRADO'
  AND mp.estado            <> 'NUEVO'
  AND fac.estado           <> 'NUEVO'
  AND fac.tipo_comprobante <> 'DUC'
  GROUP BY fmic.ciclo_facturacion,
    fmic.tipo_factura ,
    fac.tipo_pago ,
    fmic.fecha_emision ,
    fac.fecha_vencimiento ,
    fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    fmic.marketing_customer_group ,
    op.id_caja,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Medio de pago Mic (DesCobro)
  ----------------------------------------------------------------------------
  SELECT ncm.ciclo_facturacion                                          AS ciclo,
    ncm.tipo_factura                                                    AS tipo_factura,
    ncm.tipo_pago                                                       AS tipo_pago,
    ncm.fecha_emision                                                   AS fecha_emision,
    ncm.fecha_vencimiento                                               AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    ncm.marketing_customer_group                                        AS marketing,
    op.id_caja                                                          AS caja,
    -SUM(mp.importe)                                                    AS importe,
    NULL                                                                AS id_cliente,
    NULL                                                                AS razon_social,
    NULL                                                                AS tipo_cliente,
    NULL                                                                AS tipo_comprobante,
    NULL                                                                AS clase_comprobante,
    NULL                                                                AS suc_comprobante,
    NULL                                                                AS num_comprobante,
    NULL                                                                AS id_cuenta_cob,
    NULL                                                                AS id_ope,
    NULL                                                                AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'MIC'                                                               AS origen,
    'X'                                                                 AS descobro,
    NULL                                                                AS tipo_cambio,-- Dollares
    NULL                                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    NULL                                                                AS TIPO_CAMBIO_MEDIO_PAGO,
    NULL                                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    NULL                                                                AS ID_CUENTA_COB_PADRE,
    NULL                                                                AS subtipo_de_documento,
    -- Dollares Dolares
    NULL                    AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_mic ncm
  WHERE cob.id_workflow     =we.id_workflow
  AND cob.id_operacion      =op.id_operacion
  AND op.id_operacion       =tran.id_operacion
  AND tran.id_transaccion   =fac.id_transaccion
  AND tran.id_transaccion   =mp.id_transaccion
  AND mp.id_medio_pago      = ncm.id_medio_pago
  AND we.estado             = 'DES_DESCOBRADO'
  AND mp.estado            <> 'NUEVO'
  AND fac.estado           <> 'NUEVO'
  AND fac.tipo_comprobante <> 'DUC'
  GROUP BY ncm.ciclo_facturacion ,
    ncm.tipo_factura ,
    ncm.tipo_pago ,
    ncm.fecha_emision ,
    ncm.fecha_vencimiento ,
    fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    ncm.marketing_customer_group ,
    op.id_caja,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- CALIPSO
  ----------------------------------------------------------------------------
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago No CTA ni NC ni usuario ni usuario
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,-- Dollares
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, 1.0000 ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_tipo_medio_pago NOT   IN (21,22, 16, 25, 33, 34, 35) -- CTA, NC, DESISTIMIENTO, PLAN_DE_PAGO, COMPENSACION, COMPENSACION_INTERCOMPANY,COMPENSACION_LIQUIDO_PROD
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago No CTA ni NC ni USUARIO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_tipo_medio_pago NOT     IN (21,22, 16, 25, 33, 34, 35) -- CTA, NC, DESISTIMIENTO, PLAN_DE_PAGO, COMPENSACION, COMPENSACION_INTERCOMPANY,COMPENSACION_LIQUIDO_PROD
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaPP.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_PLAN_PAGO mpShivaPP
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaPP.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaPP.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaPP.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_PLAN_PAGO mpShivaPP
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaPP.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaPP.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA HV_COB_MED_PAG_DESISTIMIENTO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_DESISTIMIENTO mpShivaD
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaD.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_DESISTIMIENTO mpShivaD
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaD.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaD
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaD.id_medio_pago
  AND mp.id_tipo_medio_pago NOT   IN (21,22) -- CTA y NC
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaC.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaC
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaC.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaC.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaC.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaC
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaC.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaC.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_INTERCOM
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaI.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_INTERCOM mpShivaI
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaI.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaI.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHV_COB_MED_PAG_COMP_INTERCOM
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaI.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_INTERCOM mpShivaI
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaI.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaI.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_LIQUIDO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaCL.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_LIQUIDO mpShivaCL
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaCL.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaCL.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHV_COB_MED_PAG_COMP_INTERCOM
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaCL.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_LIQUIDO mpShivaCL
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaCL.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaCL.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------------------
  --  Factura Calipso (Cobro) sin hijos Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,-- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_COBRO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = ncc.moneda
      THEN DECODE(ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                 AS MONEDA_MEDIO_PAGO,--Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = ncc.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'--and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion ,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    ncc.moneda,
    fac.importe_cobrar,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncc.tipo_cambio
  UNION
  -----------------------------------------------------------------------------
  --  Factura Calipso (Cobro) con hijos por diferencia de cambio Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_COBRO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = ncc.moneda
      THEN DECODE(ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = ncc.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    ncc.moneda,
    fac.importe_cobrar,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncc.tipo_cambio
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Credito Mic
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,-- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_NOTA_CRED_MIC mps
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = mps.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.tipo_pago,
    fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago MIC
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_NOTA_CRED_MIC mps
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mps.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Nota de credito (Cobro) Sin hijos
  -----------------------------------------------------------------
  SELECT NULL                                                           AS ciclo,
    NULL                                                                AS tipo_factura,
    NULL                                                                AS tipo_pago,
    NULL                                                                AS fecha_emision,
    NULL                                                                AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    NULL                                                                AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    ncc.id_cliente_legado                                               AS id_cliente,
    ncc.razon_social_cliente_legado                                     AS razon_social,
    ncc.tipo_cliente                                                    AS tipo_cliente,
    ncc.tipo_comprobante                                                AS tipo_comprobante,
    ncc.clase_comprobante                                               AS clase_comprobante,
    ncc.sucursal_comprobante                                            AS suc_comprobante,
    ncc.numero_comprobante                                              AS num_comprobante,
    ncc.id_documento_cuenta_cobranza                                    AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    ncc.moneda                                                          AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    DECODE( mp.moneda, 'PES', 1.00000, ncc.tipo_cambio )                AS TIPO_CAMBIO,
    mp.importe                                                          AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncc.tipo_de_cambio_fecha_ ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                       AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                AS MONEDA_MEDIO_PAGO,
    ncc.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    ncc.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             =ncc.id_medio_pago
  AND we.estado                   IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND fac.id_factura               = fcal.id_factura
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago ,
    op.id_caja ,
    ncc.id_cliente_legado ,
    ncc.razon_social_cliente_legado ,
    ncc.tipo_cliente ,
    ncc.tipo_comprobante ,
    ncc.clase_comprobante ,
    ncc.sucursal_comprobante ,
    ncc.numero_comprobante ,
    ncc.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    ncc.moneda ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    mp.moneda,
    ncc.tipo_cambio,
    mp.importe,
    op.moneda_operacion,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncc.moneda,
    ncc.id_doc_ctas_cob_padre,
    ncc.subtipo,
    -- Dollares Dolares
    fac.moneda_importe_cobrar,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Nota de credit (Cobro) Con hijos diferencia de combio
  -----------------------------------------------------------------
  SELECT NULL                                                           AS ciclo,
    NULL                                                                AS tipo_factura,
    NULL                                                                AS tipo_pago,
    NULL                                                                AS fecha_emision,
    NULL                                                                AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    NULL                                                                AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    ncc.id_cliente_legado                                               AS id_cliente,
    ncc.razon_social_cliente_legado                                     AS razon_social,
    ncc.tipo_cliente                                                    AS tipo_cliente,
    ncc.tipo_comprobante                                                AS tipo_comprobante,
    ncc.clase_comprobante                                               AS clase_comprobante,
    ncc.sucursal_comprobante                                            AS suc_comprobante,
    ncc.numero_comprobante                                              AS num_comprobante,
    ncc.id_documento_cuenta_cobranza                                    AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    ncc.moneda                                                          AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    DECODE( ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio )               AS TIPO_CAMBIO,
    mp.importe                                                          AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncc.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                       AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                AS MONEDA_MEDIO_PAGO,
    ncc.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    ncc.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               =ncc.id_medio_pago
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND fac.id_factura                 = fcal.id_factura
  AND tran.numero_transaccion_padre IS NOT NULL
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'
  GROUP BY fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    op.id_caja ,
    ncc.id_cliente_legado ,
    ncc.razon_social_cliente_legado ,
    ncc.tipo_cliente ,
    ncc.tipo_comprobante ,
    ncc.clase_comprobante ,
    ncc.sucursal_comprobante ,
    ncc.numero_comprobante ,
    ncc.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    ncc.moneda ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    ncc.tipo_cambio,
    mp.importe,
    op.moneda_operacion,
    mp.moneda,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncc.id_doc_ctas_cob_padre ,
    ncc.subtipo ,
    fac.moneda_importe_cobrar,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(Cobro) sin hijos
  -----------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    ncta.importe_aplic_fec_emis_mon_ori AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncta.TIPO_DE_CAMBIO_FECHA_cobro)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                  AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta ncta
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = ncta.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncta.importe_aplic_fec_emis_mon_ori,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    ncta.tipo_de_cambio_fecha_cobro,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncta.tipo_cambio
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(Cobro) sin hijos
  -----------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    NULL                                                                AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    ncta.importe_aplic_fec_emis_mon_ori AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncta.TIPO_DE_CAMBIO_FECHA_COBRO)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                  AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta ncta
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = ncta.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncta.importe_aplic_fec_emis_mon_ori,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    ncta.tipo_de_cambio_fecha_cobro,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncta.tipo_cambio
  UNION
  ----------------------------------------------------------------------------
  -- Calipso DescobroCLP
  ----------------------------------------------------------------------------
  ----------------------------------------------------------------------------
  -- Factura Calipso (DesCobro) sin hijos Medio de pago No CTA ni NC ni usuario
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,-- Dollares
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, 1.0000 ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_tipo_medio_pago NOT   IN (21,22, 16, 25, 33, 34, 35) -- CTA, NC, DESISTIMIENTO, PLAN_DE_PAGO, COMPENSACION, COMPENSACION_INTERCOMPANY,COMPENSACION_LIQUIDO_PROD
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago No CTA ni NC ni USUARIO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_tipo_medio_pago NOT     IN (21,22, 16, 25, 33, 34, 35) -- CTA, NC, DESISTIMIENTO, PLAN_DE_PAGO, COMPENSACION, COMPENSACION_INTERCOMPANY,COMPENSACION_LIQUIDO_PROD
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaPP.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_PLAN_PAGO mpShivaPP
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaPP.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaPP.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaPP.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_PLAN_PAGO mpShivaPP
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaPP.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaPP.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA HV_COB_MED_PAG_DESISTIMIENTO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_DESISTIMIENTO mpShivaD
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaD.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_PLAN_PAGO
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_DESISTIMIENTO mpShivaD
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaD.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaD.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaD
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaD.id_medio_pago
  AND mp.id_tipo_medio_pago NOT   IN (21,22) -- CTA y NC
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaD.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaC.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaC
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaC.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaC.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHIVA SHV_COB_MED_PAG_COMP_OTRAS
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaC.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_OTRAS mpShivaC
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaC.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaC.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_INTERCOM
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                               AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaI.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                               AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                             AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_INTERCOM mpShivaI
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaI.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaI.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHV_COB_MED_PAG_COMP_INTERCOM
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaI.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                     AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_INTERCOM mpShivaI
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaI.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaI.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA SHV_COB_MED_PAG_COMP_LIQUIDO
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    --    fac.importe_cobrar                                                       AS importe,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION) AS tipo_cambio,-- Dollares
    mp.importe                                                AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE(mp.moneda, 'PES', 1.00000, mpShivaCL.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                                                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                              AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_LIQUIDO mpShivaCL
  WHERE cob.id_workflow            = we.id_workflow
  AND cob.id_operacion             = op.id_operacion
  AND op.id_operacion              = tran.id_operacion
  AND tran.id_transaccion          = fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          = mp.id_transaccion
  AND mp.id_medio_pago             = mpShivaCL.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaCL.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago SHV_COB_MED_PAG_COMP_INTERCOM
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe                                                 AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, mpShivaCL.TIPO_CAMBIO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                                                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre                                 AS ID_CUENTA_COB_PADRE,
    fcal.subtipo                                               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_COMP_LIQUIDO mpShivaCL
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mpShivaCL.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    mp.id_tipo_medio_pago,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    mpShivaCL.TIPO_CAMBIO,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------------------
  --  Factura Calipso (Cobro) sin hijos Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,-- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_COBRO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                 AS MONEDA_MEDIO_PAGO,--Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = ncc.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'--and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    op.id_caja ,
    op.moneda_operacion ,
    fcal.moneda ,
    fac.importe_cobrar ,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.id_cliente_legado ,
    fcal.razon_social_cliente_legado ,
    fcal.tipo_cliente ,
    fac.tipo_comprobante ,
    fac.clase_comprobante ,
    fac.sucursal_comprobante ,
    fac.numero_comprobante ,
    fcal.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    fcal.tipo_cambio,
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe ,
    mp.moneda,
    ncc.moneda,
    ncc.tipo_cambio,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    fcal.id_doc_ctas_cob_padre ,
    fcal.subtipo ,
    -- Dollares Dolares
    fac.moneda_importe_cobrar,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------------------
  --  Factura Calipso (Cobro) con hijos por diferencia de cambio Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
  --Error
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( ncc.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_COBRO ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                 AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = ncc.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    op.id_caja ,
    op.moneda_operacion,
    fcal.moneda,
    fac.importe_cobrar,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.id_cliente_legado ,
    fcal.razon_social_cliente_legado ,
    fcal.tipo_cliente ,
    fac.tipo_comprobante ,
    fac.clase_comprobante ,
    fac.sucursal_comprobante ,
    fac.numero_comprobante ,
    fcal.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    fcal.tipo_cambio ,
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION ,
    mp.importe ,
    mp.moneda,
    ncc.tipo_cambio,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncc.moneda , --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre ,
    fcal.subtipo ,
    -- Dollares Dolares
    fac.moneda_importe_cobrar ,
    TRAN.NUMERO_TRANSACCION
  UNION
  ----------------------------------------------------------------------------
  -- Factura Calipso (Cobro) sin hijos Credito Mic
  ----------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,-- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_NOTA_CRED_MIC mps
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = mps.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente ,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  ------------------------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago MIC
  ------------------------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    mp.importe AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    DECODE( mp.moneda, 'PES', 1.00000, 1.00000 --mps.tipoDeCambioFechaCobro
    )                          AS TIPO_CAMBIO_MEDIO_PAGO,
    'PES'                      AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    SHV_COB_MED_PAG_NOTA_CRED_MIC mps
  WHERE cob.id_workflow              = we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = mps.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    mp.importe,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Nota de credito (Cobro) Sin hijos
  -----------------------------------------------------------------
  SELECT NULL                                                           AS ciclo,
    NULL                                                                AS tipo_factura,
    NULL                                                                AS tipo_pago,
    NULL                                                                AS fecha_emision,
    NULL                                                                AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    NULL                                                                AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    ncc.id_cliente_legado                                               AS id_cliente,
    ncc.razon_social_cliente_legado                                     AS razon_social,
    ncc.tipo_cliente                                                    AS tipo_cliente,
    ncc.tipo_comprobante                                                AS tipo_comprobante,
    ncc.clase_comprobante                                               AS clase_comprobante,
    ncc.sucursal_comprobante                                            AS suc_comprobante,
    ncc.numero_comprobante                                              AS num_comprobante,
    ncc.id_documento_cuenta_cobranza                                    AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    ncc.moneda                                                          AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    DECODE( mp.moneda, 'PES', 1.00000, ncc.tipo_cambio )                AS TIPO_CAMBIO,
    mp.importe                                                          AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncc.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                       AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                AS MONEDA_MEDIO_PAGO,
    ncc.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    ncc.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             =ncc.id_medio_pago
  AND we.estado                   IN ('DES_DESCOBRADO')
  AND fac.id_factura               = fcal.id_factura
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor ,
    mp.id_tipo_medio_pago ,
    op.id_caja ,
    ncc.id_cliente_legado ,
    ncc.razon_social_cliente_legado ,
    ncc.tipo_cliente ,
    ncc.tipo_comprobante ,
    ncc.clase_comprobante ,
    ncc.sucursal_comprobante ,
    ncc.numero_comprobante ,
    ncc.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    ncc.moneda ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    mp.moneda,
    ncc.tipo_cambio,
    mp.importe ,
    op.moneda_operacion,
    ncc.tipo_cambio,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncc.id_doc_ctas_cob_padre ,
    ncc.subtipo ,
    -- Dollares Dolares
    fac.moneda_importe_cobrar,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Nota de credit (Cobro) Con hijos diferencia de combio
  -----------------------------------------------------------------
  SELECT NULL                                                           AS ciclo,
    NULL                                                                AS tipo_factura,
    NULL                                                                AS tipo_pago,
    NULL                                                                AS fecha_emision,
    NULL                                                                AS fecha_vencimiento,
    fac.fecha_valor                                                     AS fecha_valor,
    mp.id_tipo_medio_pago                                               AS id_tipo_medio_pago,
    NULL                                                                AS marketing,
    op.id_caja                                                          AS caja,
    SUM(mp.importe)                                                     AS importe,
    ncc.id_cliente_legado                                               AS id_cliente,
    ncc.razon_social_cliente_legado                                     AS razon_social,
    ncc.tipo_cliente                                                    AS tipo_cliente,
    ncc.tipo_comprobante                                                AS tipo_comprobante,
    ncc.clase_comprobante                                               AS clase_comprobante,
    ncc.sucursal_comprobante                                            AS suc_comprobante,
    ncc.numero_comprobante                                              AS num_comprobante,
    ncc.id_documento_cuenta_cobranza                                    AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    ncc.moneda                                                          AS mon,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    DECODE( ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio )               AS TIPO_CAMBIO,
    mp.importe                                                          AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncc.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncc.TIPO_DE_CAMBIO_FECHA_EMISION)
    END                       AS TIPO_CAMBIO_MEDIO_PAGO,
    ncc.moneda                AS MONEDA_MEDIO_PAGO,
    ncc.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    ncc.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_wf_workflow_estado we,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc,
    shv_cob_factura_calipso fcal
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               =ncc.id_medio_pago
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND fac.id_factura                 = fcal.id_factura
  AND tran.numero_transaccion_padre IS NOT NULL
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    ncc.id_cliente_legado ,
    ncc.razon_social_cliente_legado ,
    ncc.tipo_cliente ,
    ncc.tipo_comprobante ,
    ncc.clase_comprobante ,
    ncc.sucursal_comprobante ,
    ncc.numero_comprobante ,
    ncc.id_documento_cuenta_cobranza ,
    op.id_operacion ,
    ncc.moneda ,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') ,
    DECODE( ncc.moneda, 'PES', 1.00000, ncc.tipo_cambio ) ,
    mp.importe ,
    op.moneda_operacion,
    mp.moneda,
    ncc.tipo_cambio,
    ncc.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncc.moneda ,
    ncc.id_doc_ctas_cob_padre ,
    ncc.subtipo ,
    -- Dollares Dolares
    fac.moneda_importe_cobrar,
    TRAN.NUMERO_TRANSACCION
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(Cobro) sin hijos
  -----------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    ncta.importe_aplic_fec_emis_mon_ori AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                  AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta ncta
  WHERE cob.id_workflow            =we.id_workflow
  AND cob.id_operacion             =op.id_operacion
  AND op.id_operacion              =tran.id_operacion
  AND tran.id_transaccion          =fac.id_transaccion
  AND fac.id_factura               = fcal.id_factura
  AND tran.id_transaccion          =mp.id_transaccion
  AND mp.id_medio_pago             = ncta.id_medio_pago
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  AND tran.numero_transaccion_padre IS NULL
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncta.importe_aplic_fec_emis_mon_ori,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    ncta.tipo_de_cambio_fecha_cobro,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncta.tipo_cambio
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(Cobro) sin hijos
  -----------------------------------------------------------------
  SELECT NULL             AS ciclo,
    NULL                  AS tipo_factura,
    NULL                  AS tipo_pago,
    NULL                  AS fecha_emision,
    NULL                  AS fecha_vencimiento,
    fac.fecha_valor       AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL                  AS marketing,
    op.id_caja            AS caja,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN fac.importe_cobrar
      ELSE fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
    END                                                                 AS importe,
    fac.id_cliente_legado                                               AS id_cliente,
    fcal.razon_social_cliente_legado                                    AS razon_social,
    fcal.tipo_cliente                                                   AS tipo_cliente,
    fac.tipo_comprobante                                                AS tipo_comprobante,
    fac.clase_comprobante                                               AS clase_comprobante,
    fac.sucursal_comprobante                                            AS suc_comprobante,
    fac.numero_comprobante                                              AS num_comprobante,
    fcal.id_documento_cuenta_cobranza                                   AS id_cuenta_cob,
    op.id_operacion                                                     AS id_ope,
    fcal.moneda                                                         AS mon, --Moneda de la factura
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') AS fecha_workflow,
    'CAL'                                                               AS origen,
    'X'                                                                 AS descobro,
    CASE
      WHEN op.moneda_operacion = fcal.moneda
      THEN DECODE(fcal.moneda, 'PES', 1.00000, fcal.tipo_cambio)
      ELSE DECODE(fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION)
    END AS tipo_cambio,
    --DECODE( fcal.moneda, 'PES', 1.00000, fcal.TIPO_DE_CAMBIO_FECHA_EMISION ) AS tipo_cambio,
    -- Dollares
    ncta.importe_aplic_fec_emis_mon_ori AS IMPORTE_MED_PAGO_MONEDA_ORGEN,
    --DECODE( mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro ) AS TIPO_CAMBIO_MEDIO_PAGO,
    CASE
      WHEN op.moneda_operacion = mp.moneda
      THEN DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_cambio)
      ELSE DECODE(mp.moneda, 'PES', 1.00000, ncta.tipo_de_cambio_fecha_cobro)
    END                        AS TIPO_CAMBIO_MEDIO_PAGO,
    mp.moneda                  AS MONEDA_MEDIO_PAGO, --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre AS ID_CUENTA_COB_PADRE,
    fcal.subtipo               AS subtipo_de_documento,
    -- Dollares Dolares
    fac.moneda_importe_cobrar AS MONEDA_OPERACION,
    TRAN.NUMERO_TRANSACCION   AS NUMERO_TRANSACCION
  FROM shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion TRAN,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_cta ncta
  WHERE cob.id_workflow              =we.id_workflow
  AND cob.id_operacion               =op.id_operacion
  AND op.id_operacion                =tran.id_operacion
  AND tran.id_transaccion            =fac.id_transaccion
  AND fac.id_factura                 = fcal.id_factura
  AND tran.id_transaccion            =mp.id_transaccion
  AND mp.id_medio_pago               = ncta.id_medio_pago
  AND tran.numero_transaccion_padre IS NOT NULL
  AND we.estado                     IN ('DES_DESCOBRADO')
  AND mp.estado                     <> 'NUEVO'
  AND fac.estado                    <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.fecha_valor,
    mp.id_tipo_medio_pago,
    op.id_caja,
    fac.id_cliente_legado,
    fcal.razon_social_cliente_legado,
    fcal.tipo_cliente,
    fac.tipo_comprobante,
    fac.clase_comprobante,
    fac.sucursal_comprobante,
    fac.numero_comprobante,
    fcal.id_documento_cuenta_cobranza,
    op.id_operacion,
    fcal.moneda,
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
    ncta.importe_aplic_fec_emis_mon_ori,
    fcal.id_doc_ctas_cob_padre,
    fcal.subtipo,
    mp.moneda,
    fac.importe_cobrar,
    ncta.tipo_de_cambio_fecha_cobro,
    op.moneda_operacion,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    fac.moneda_importe_cobrar,
    fcal.tipo_cambio,
    TRAN.NUMERO_TRANSACCION,
    ncta.tipo_cambio
  );
  
  CREATE OR REPLACE VIEW SHV_SOP_COBROS_DETALLE_MED_PAG (ID_MEDIO_PAGO, ID_TIPO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, ORIGEN_DOCUMENTO, ID_TRANSACCION, ESTADO, IMPORTE, MONTO_ACUMULADO_SIMULACION, MIGRADO_DEIMOS, ESTADO_DEIMOS, FECHA_ACTUALIZACION_DEIMOS, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO, MONEDA, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, FECHA_VALOR, REFERENCIA, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, ACUERDO_TRASLADO_CARGO_ORIGNAL)
AS
  (SELECT scmp.id_medio_pago,
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
    scmp.id_cliente_legado,
    scmpd.fecha_valor,
    scmpd.referencia,
    -- Gestion de intereses
    scmpd.que_hacer_con_intereses        AS que_hacer_con_intereses,
    scmpd.cobrador_intereses_generados   AS cobrador_intereses_generados,
    scmpd.check_trasladar_intereses      AS check_trasladar_intereses,
    scmpd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    scmpd.porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
    scmpd.importe_bonif_intereses        AS importe_bonif_intereses,
    scmpd.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    scmpd.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    scmpd.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
    scmpd.TIPO_DE_CAMBIO_FECHA_COBRO     AS TIPO_DE_CAMBIO_FECHA_COBRO,
    scmpd.TIPO_DE_CAMBIO_FECHA_EMISION   AS TIPO_DE_CAMBIO_FECHA_EMISION,
    scmpd.IMPORTE_APLIC_FEC_EMIS_MON_ORI AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
    scmpd.acuerdo_traslado_cargo_orignal AS acuerdo_traslado_cargo_orignal
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
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM SHV_COB_MED_PAG_COMP_OTRAS
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_comp_intercom
    UNION
    --
    -- Medio pago Compensacion IIBB
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_comp_iibb
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_comp_liquido
    UNION
    --
    -- Medio pago Compensacion Cesion Creditos
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM SHV_COB_MED_PAG_COMP_CES_CRED
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_plan_pago
    UNION
    --
    -- Medio pago CTA
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      TIPO_DE_CAMBIO_FECHA_COBRO,
      TIPO_DE_CAMBIO_FECHA_EMISION,
      IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_cta
    UNION
    --
    -- Medio pago Nota de Credito Calipso
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      TIPO_DE_CAMBIO_FECHA_COBRO,
      TIPO_DE_CAMBIO_FECHA_EMISION,
      IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
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
      que_hacer_con_intereses        AS que_hacer_con_intereses,
      cobrador_intereses_generados   AS cobrador_intereses_generados,
      check_trasladar_intereses      AS check_trasladar_intereses,
      cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
      porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
      importe_bonif_intereses        AS importe_bonif_intereses,
      acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
      NULL                           AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL                           AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL                           AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      acuerdo_traslado_cargo_orignal AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_deb_prox_clp
    UNION
    --
    -- Medio pago Remanente
    --
    SELECT id_medio_pago,
      cuenta_remanente
      ||tipo_remanente,
      fecha_alta,
      tipo_remanente AS subtipo_medio_pago,
      NULL           AS origen_documento,
      -- Gestion de intereses
      NULL AS que_hacer_con_intereses,
      NULL AS cobrador_intereses_generados,
      NULL AS check_trasladar_intereses,
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_remanente
    UNION
    --
    -- Medio pago Nota de Credito Mic
    --
    SELECT id_medio_pago,
      DECODE (clase_comprobante,'S','','D','', clase_comprobante
      ||'-')
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
      NULL AS cobrador_intereses_trasladados,
      NULL AS porcentaje_bonif_intereses,
      NULL AS importe_bonif_intereses,
      NULL AS acuerdo_traslado_cargo,
      NULL AS estado_acuerdo_traslado_cargo,
      NULL AS id_cliente_acuerdo_tras_cargo,
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      NULL AS acuerdo_traslado_cargo_orignal
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
      que_hacer_con_intereses        AS que_hacer_con_intereses,
      cobrador_intereses_generados   AS cobrador_intereses_generados,
      check_trasladar_intereses      AS check_trasladar_intereses,
      cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
      porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
      importe_bonif_intereses        AS importe_bonif_intereses,
      acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
      estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
      id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
      NULL                           AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL                           AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL                           AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      acuerdo_traslado_cargo_orignal AS acuerdo_traslado_cargo_orignal
    FROM shv_cob_med_pag_deb_prox_mic
    ) scmpd
  WHERE scmpd.id_medio_pago    = scmp.id_medio_pago
  AND sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
  );
  
  
alter view SHV_SOP_COBROS_GRILLA_TRANSAC compile;

Exit;