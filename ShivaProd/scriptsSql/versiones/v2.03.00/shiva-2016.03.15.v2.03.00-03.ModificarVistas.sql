CREATE OR REPLACE VIEW SHV_SOP_DESCOBROS_GRILLA_TRANS (NUMERO_TRANSACCION_FORMATEADO, ESTADO_TRANSACCION, SISTEMA_ORIGEN_DOCUMENTO, ID_CLIENTE_LEGADO_DOCUMENTO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION_DEB, FECHA_ACUMULADO_SIMULACION_DEB, ORIGEN_DOCUMENTO, SISTEMA_ORIGEN_MEDIO_PAGO, ID_CLIENTE_LEGADO_MEDIO_PAGO, TIPO_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, REFERENCIA_MEDIO_PAGO, FECHA_MEDIO_PAGO, IMPORTE_MEDIO_PAGO, MONTO_ACUMULADO_SIMULACION_CRE, TIPO_PAGO, QUE_HACER_CON_INTERESES, INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_A_BONIFICAR, IMPORTE_A_BONIFICAR
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
    NULL AS importe_medio_pago,
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
    sscdmp.sistema_origen             AS sistema_origen_medio_pago,
    sscdmp.id_cliente_legado          AS id_cliente_legado_medio_pago,
    sscdmp.descripcion_medio_pago     AS tipo_medio_pago,
    sscdmp.subtipo_medio_pago         AS subtipo_medio_pago,
    sscdmp.referencia                 AS referencia_medio_pago,
    sscdmp.fecha_valor                AS fecha_medio_pago,
    sscdmp.importe                    AS importe_medio_pago,
    sscdmp.monto_acumulado_simulacion AS monto_acumulado_simulacion_cre,
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
  AND sscdmp.estado           != 'NUEVO'
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
    NULL AS importe_medio_pago,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sctd.que_hacer_con_intereses                                                                                                                                                       AS que_hacer_con_intereses,
    DECODE(sctd.tipo_tratamiento_diferencia, 'REINTEGRO_CRED_PROX_FAC_MIC', sctd.COBRADOR_INTERES_REAL_NRG_TRAS + sctd.COBRADOR_INTERES_REAL_RG_TRAS, sctd.COBRADOR_INTERES_REAL_TRAS) AS intereses,
    sctd.check_trasladar_intereses                                                                                                                                                     AS check_trasladar_intereses,
    sctd.cobrador_intereses_trasladados                                                                                                                                                AS cobrador_intereses_trasladados,
    NULL                                                                                                                                                                               AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses                                                                                                                                                    AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses                                                                                                                                                       AS importe_a_bonificar,
    sctd.sistema_origen                                                                                                                                                                AS sistema_acuerdo,
    sctd.acuerdo_traslado_cargo                                                                                                                                                        AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo                                                                                                                                                 AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo                                                                                                                                                 AS id_cliente_acuerdo_tras_cargo,
    sctd.estado_cargo_generado                                                                                                                                                         AS estado_cargo_generado,
    sctd.sistema_acuerdo_contracargo                                                                                                                                                   AS sistema_acuerdo_contracargo,
    sctd.acuerdo_contracargo                                                                                                                                                           AS acuerdo_contracargo,
    sctd.estado_acuerdo_contracargo                                                                                                                                                    AS estado_acuerdo_contracargo,
    sctd.id_cliente_acuerdo_contracargo                                                                                                                                                AS id_cliente_acuerdo_contracargo,
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
  
  
  
  CREATE OR REPLACE VIEW SHV_SOP_COBROS_GRILLA_TRANSAC (NUMERO_TRANSACCION_FORMATEADO, ESTADO_TRANSACCION, SISTEMA_ORIGEN_DOCUMENTO, ID_CLIENTE_LEGADO_DOCUMENTO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION_DEB, FECHA_ACUMULADO_SIMULACION_DEB, ORIGEN_DOCUMENTO, SISTEMA_ORIGEN_MEDIO_PAGO, ID_CLIENTE_LEGADO_MEDIO_PAGO, TIPO_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, REFERENCIA_MEDIO_PAGO, FECHA_MEDIO_PAGO, IMPORTE_MEDIO_PAGO, MONTO_ACUMULADO_SIMULACION_CRE, TIPO_PAGO, QUE_HACER_CON_INTERESES, INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_A_BONIFICAR, IMPORTE_A_BONIFICAR,ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, TIPO_MENSAJE_ESTADO_TRANSACCIO, MENSAJE_ESTADO_TRANSACCION, TIPO_MENSAJE_ESTADO_DEBITO, MENSAJE_ESTADO_DEBITO, ESTADO_DEBITO, TIPO_MENSAJE_ESTADO_CREDITO, MENSAJE_ESTADO_CREDITO, ESTADO_CREDITO, ID_COBRO, ID_OPERACION, ID_TRANSACCION, NUMERO_TRANSACCION, NUMERO_TRANSACCION_PADRE, NUMERO_TRANSACCION_PADRE_FORMA, ID_FACTURA, ID_MEDIO_PAGO, ID_TRATAMIENTO_DIFERENCIA, ID_TRANSACCION_PADRE)
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
    NULL AS importe_medio_pago,
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
    sscdf.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sscdf.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sscdf.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
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
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia,
    NULL AS id_transaccion_padre
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_sop_cobros_detalle_factura sscdf
  WHERE sscdf.id_transaccion = sct.id_transaccion
  AND scc.id_operacion       = sct.id_operacion
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
    sscdmp.sistema_origen             AS sistema_origen_medio_pago,
    sscdmp.id_cliente_legado          AS id_cliente_legado_medio_pago,
    sscdmp.descripcion_medio_pago     AS tipo_medio_pago,
    sscdmp.subtipo_medio_pago         AS subtipo_medio_pago,
    sscdmp.referencia                 AS referencia_medio_pago,
    sscdmp.fecha_valor                AS fecha_medio_pago,
    sscdmp.importe                    AS importe_medio_pago,
    SSCDMP.MONTO_ACUMULADO_SIMULACION AS MONTO_ACUMULADO_SIMULACION_CRE,
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
    sscdmp.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sscdmp.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sscdmp.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
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
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                         AS id_factura,
    sscdmp.id_medio_pago                         AS id_medio_pago,
    NULL                                         AS id_tratamiento_diferencia,
    NULL                                         AS id_transaccion_padre
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_sop_cobros_detalle_med_pag sscdmp
  WHERE sscdmp.id_transaccion = sct.id_transaccion
  AND scc.id_operacion        = sct.id_operacion
  AND sscdmp.estado           != 'NUEVO'
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
    NULL AS importe_medio_pago,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sctd.que_hacer_con_intereses        AS que_hacer_con_intereses,
    sctd.cobrador_intereses_generados   AS intereses,
    sctd.check_trasladar_intereses      AS check_trasladar_intereses,
    sctd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    NULL                                AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses     AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses        AS importe_a_bonificar,
    sctd.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
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
    scc.id_cobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                   AS id_factura,
    NULL                                   AS id_medio_pago,
    sctd.id_tratamiento_diferencia         AS id_tratamiento_diferencia,
    NULL                                   AS id_transaccion_padre
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion = sct.id_transaccion
  AND scc.id_operacion      = sct.id_operacion
  );
  
  
  Exit;