CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_DETALLE_MED_PAG (ID_MEDIO_PAGO, ID_TIPO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, ORIGEN_DOCUMENTO, ID_TRANSACCION, ESTADO, IMPORTE, MONTO_ACUMULADO_SIMULACION, MIGRADO_DEIMOS, ESTADO_DEIMOS, FECHA_ACTUALIZACION_DEIMOS, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO, MONEDA, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, FECHA_VALOR, REFERENCIA, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, ACUERDO_TRASLADO_CARGO_ORIGNAL)
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

  Exit;