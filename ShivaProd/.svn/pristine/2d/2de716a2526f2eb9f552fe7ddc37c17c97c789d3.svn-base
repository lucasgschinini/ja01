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
      NULL AS TIPO_DE_CAMBIO_FECHA_COBRO,
      NULL AS TIPO_DE_CAMBIO_FECHA_EMISION,
      NULL AS IMPORTE_APLIC_FEC_EMIS_MON_ORI,
      scmpdpm.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_deb_prox_mic scmpdpm,
      shv_cob_med_pago scmp2
    WHERE scmpdpm.id_medio_pago = scmp2.id_medio_pago
    ) scmpd
  WHERE scmpd.id_medio_pago    = scmp.id_medio_pago
  AND sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
  );

  
  
CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_GRILLA_TRANS (NUMERO_TRANSACCION_FORMATEADO, ESTADO_TRANSACCION, SISTEMA_ORIGEN_DOCUMENTO, ID_CLIENTE_LEGADO_DOCUMENTO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION_DEB, FECHA_ACUMULADO_SIMULACION_DEB, ORIGEN_DOCUMENTO, SISTEMA_ORIGEN_MEDIO_PAGO, ID_CLIENTE_LEGADO_MEDIO_PAGO, TIPO_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, REFERENCIA_MEDIO_PAGO, FECHA_MEDIO_PAGO, MONEDA_MEDIO_PAGO, IMPORTE_MEDIO_PAGO, TIPO_CAMBIO_FECHA_COBRO_MP, TIPO_CAMBIO_FECHA_EMISION_MP, IMPORTE_APL_FEC_EMI_MON_ORI_MP, MONTO_ACUMULADO_SIMULACION_CRE, TIPO_PAGO, QUE_HACER_CON_INTERESES, INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_A_BONIFICAR, IMPORTE_A_BONIFICAR
  , SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO, SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO, TIPO_MENSAJE_ESTADO_TRANSACCIO, MENSAJE_ESTADO_TRANSACCION, TIPO_MENSAJE_ESTADO_DEBITO, MENSAJE_ESTADO_DEBITO, ESTADO_DEBITO, TIPO_MENSAJE_ESTADO_CREDITO, MENSAJE_ESTADO_CREDITO, ESTADO_CREDITO, ID_DESCOBRO, ID_OPERACION, ID_TRANSACCION, NUMERO_TRANSACCION, NUMERO_TRANSACCION_PADRE, NUMERO_TRANSACCION_PADRE_FORMA, ID_FACTURA, ID_MEDIO_PAGO, ID_TRATAMIENTO_DIFERENCIA, ID_TRANSACCION_PADRE)
AS
  (
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
    sscdf.id_cliente_legado              AS id_cliente_legado_documento,
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
    NULL AS id_cliente_legado_medio_pago,
    NULL AS tipo_medio_pago,
    NULL AS subtipo_medio_pago,
    NULL AS referencia_medio_pago,
    NULL AS fecha_medio_pago,
    NULL AS moneda_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS TIPO_CAMBIO_FECHA_COBRO_MP,
    NULL AS TIPO_CAMBIO_FECHA_EMISION_MP,
    NULL AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    sscdf.tipo_pago AS tipo_pago,
    -- Gestion de intereses
    sscdf.que_hacer_con_intereses        AS que_hacer_con_intereses,
    sscdf.cobrador_intereses_generados   AS intereses_generados,
    sscdf.check_trasladar_intereses      AS check_trasladar_intereses,
    sscdf.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    sscdf.check_cobrar_segundo_vencimien AS check_cobrar_segundo_vencimien,
    sscdf.porcentaje_bonif_intereses     AS porcentaje_a_bonificar,
    sscdf.importe_bonif_intereses        AS importe_a_bonificar,
    sscdf.sistema_origen                 AS sistema_acuerdo,
    sscdf.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sscdf.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sscdf.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
    sscdf.estado_cargo_generado          AS estado_cargo_generado,
    sscdf.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    sscdf.acuerdo_contracargo            AS acuerdo_contracargo,
    sscdf.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    sscdf.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
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
    scd.id_descobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia,
    NULL AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_sop_descobros_det_factura sscdf
  WHERE sscdf.id_transaccion = sct.id_transaccion
  AND scd.id_operacion       = sct.id_operacion
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
    NULL AS id_cliente_legado_documento,
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
    sscdmp.sistema_origen                 AS sistema_origen_medio_pago,
    sscdmp.id_cliente_legado              AS id_cliente_legado_medio_pago,
    sscdmp.descripcion_medio_pago         AS tipo_medio_pago,
    sscdmp.subtipo_medio_pago             AS subtipo_medio_pago,
    sscdmp.referencia                     AS referencia_medio_pago,
    sscdmp.fecha_valor                    AS fecha_medio_pago,
    sscdmp.moneda                         AS moneda_medio_pago,
    sscdmp.importe                        AS importe_medio_pago,
    sscdmp.TIPO_DE_CAMBIO_FECHA_COBRO     AS TIPO_CAMBIO_FECHA_COBRO_MP,
    sscdmp.TIPO_DE_CAMBIO_FECHA_EMISION   AS TIPO_CAMBIO_FECHA_EMISION_MP,
    sscdmp.IMPORTE_APLIC_FEC_EMIS_MON_ORI AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
    sscdmp.monto_acumulado_simulacion     AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sscdmp.que_hacer_con_intereses        AS que_hacer_con_intereses,
    sscdmp.cobrador_intereses_generados   AS intereses_generados,
    sscdmp.check_trasladar_intereses      AS check_trasladar_intereses,
    sscdmp.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    NULL                                  AS check_cobrar_segundo_vencimien,
    sscdmp.porcentaje_bonif_intereses     AS porcentaje_a_bonificar,
    sscdmp.importe_bonif_intereses        AS importe_a_bonificar,
    sscdmp.sistema_acuerdo                AS sistema_acuerdo,
    sscdmp.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sscdmp.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sscdmp.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
    sscdmp.estado_cargo_generado          AS estado_cargo_generado,
    sscdmp.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    sscdmp.acuerdo_contracargo            AS acuerdo_contracargo,
    sscdmp.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    sscdmp.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
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
    scd.id_descobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                         AS id_factura,
    sscdmp.id_medio_pago                         AS id_medio_pago,
    NULL                                         AS id_tratamiento_diferencia,
    NULL                                         AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_sop_descobros_det_med_pag sscdmp
  WHERE sscdmp.id_transaccion = sct.id_transaccion
  AND scd.id_operacion        = sct.id_operacion
  AND sscdmp.estado          != 'NUEVO'
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
    sctd.sistema_origen                                                         AS sistema_origen_documento,
    NULL                                                                        AS id_cliente_legado_documento,
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
    NULL AS id_cliente_legado_medio_pago,
    NULL AS tipo_medio_pago,
    NULL AS subtipo_medio_pago,
    NULL AS referencia_medio_pago,
    NULL AS fecha_medio_pago,
    NULL AS moneda_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS TIPO_CAMBIO_FECHA_COBRO_MP,
    NULL AS TIPO_CAMBIO_FECHA_EMISION_MP,
    NULL AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sctd.que_hacer_con_intereses AS que_hacer_con_intereses,
    CASE
      WHEN sctd.cobrador_interes_real_nrg_tras IS NOT NULL
      AND sctd.cobrador_interes_real_nrg_tras  <> 0
      THEN sctd.cobrador_interes_real_nrg_tras
      ELSE sctd.cobrador_intereses_generados
    END cobrador_intereses_generados,
    sctd.check_trasladar_intereses      AS check_trasladar_intereses,
    sctd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    NULL                                AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses     AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses        AS importe_a_bonificar,
    sctd.sistema_origen                 AS sistema_acuerdo,
    sctd.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
    sctd.estado_cargo_generado          AS estado_cargo_generado,
    sctd.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    sctd.acuerdo_contracargo            AS acuerdo_contracargo,
    sctd.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    sctd.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
    -- Mensajes de apropiacion
    sct.tipo_mensaje_estado  AS tipo_mensaje_estado_transaccio,
    sct.mensaje_estado       AS mensaje_estado_transaccion,
    sctd.tipo_mensaje_estado AS tipo_mensaje_estado_debito,
    sctd.mensaje_estado      AS mensaje_estado_debito,
    sctd.estado              AS estado_debito,
    NULL                     AS tipo_mensaje_estado_credito,
    NULL                     AS mensaje_estado_credito,
    NULL                     AS estado_credito,
    -- Datos ocultos del cobro / transaccion
    scd.id_descobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                         AS id_factura,
    NULL                                         AS id_medio_pago,
    sctd.id_tratamiento_diferencia               AS id_tratamiento_diferencia,
    NULL                                         AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion = sct.id_transaccion
  AND scd.id_operacion      = sct.id_operacion
  );
  
  
Exit;