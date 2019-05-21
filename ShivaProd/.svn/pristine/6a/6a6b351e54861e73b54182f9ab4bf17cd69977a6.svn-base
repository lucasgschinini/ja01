CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_BUSQUEDA (ID_COBRO, ID_COBRO_PADRE, ID_EMPRESA, ID_SEGMENTO, ID_OPERACION, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, ANALISTA, COPROPIETARIO, ID_REVERSION, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_APROB_SUPER_COB, FECHA_APROB_REFER_COB, FECHA_IMPUTACION, SUB_ESTADO, FECHA_ULTIMO_ESTADO)
   						 AS
                         
 	SELECT SCEC.ID_COBRO AS idCobro,
    SCEC.id_cobro_padre  AS idCobroPadre,
    SCEC.id_empresa      AS empresa,
    SCEC.id_segmento     AS segmento,
    SCEC.ID_OPERACION    AS idOperacion,
    SCEC.ID_MOTIVO_COBRO AS idMotivoCobro,
    SPMC.DESCRIPCION     AS descMotivoCobro,
    (
    SELECT LISTAGG(SUBSTR(CEC.ID_CLIENTE_LEGADO || ' ' || CEC.RAZON_SOCIAL,0,15), '-') 
      WITHIN GROUP(ORDER BY CEC.ID_CLIENTE_LEGADO)
        FROM SHV_COB_ED_CLIENTE CEC WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    )                          AS cliente,
    SWWE.estado                AS estado,
    SCEC.ID_ANALISTA           AS analista,
    SCEC.ID_COPROPIETARIO      AS copropietario,
    SCC.ID_DESCOBRO               AS idReversion,
    SCEC.IMPORTE_TOTAL         AS importeTotal,
    SCEC.FECHA_ALTA            AS fechaAlta,
    SCEC.FECHA_DERIVACION      AS fechaDerivacion,
    SCEC.FECHA_APROB_SUPER_COB AS fechaAprobSuperCob,
    SCEC.FECHA_APROB_REFER_COB AS fechaAprobReferCob,
    SCEC.FECHA_IMPUTACION      AS fechaImputacion,
    
      (SELECT SWWM1.ID_MARCA
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE swwm1.id_workflow_MARCA=(
SELECT max (SWWM.ID_WORKFLOW_MARCA)
    FROM SHV_WF_WORKFLOW_MARCA SWWM
    where SWWM.id_workflow_estado = SWWE.id_workflow_estado)) as subEstado,
    
    TO_CHAR((SELECT SWWM1.fecha_creacion
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE swwm1.id_workflow_MARCA=(
	SELECT max (SWWM.ID_WORKFLOW_MARCA)
    FROM SHV_WF_WORKFLOW_MARCA SWWM
    where SWWM.id_workflow_estado = SWWE.id_workflow_estado)), 'DD/MM/YYYY HH24:MI:SS')as fechaUltimoEstado
    
  FROM SHV_COB_ED_COBRO SCEC
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO
  LEFT JOIN SHV_COB_COBRO SCC
  ON SCEC.ID_COBRO = SCC.ID_COBRO;
  
--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de grilla de transacciones - Fecha de cobro para Reintegros
--------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_GRILLA_TRANSAC AS
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
    sscdf.tipo_comprobante               AS tipo_comprobante,
    sscdf.subtipo_documento              AS subtipo_documento,
    sscdf.numero_documento               AS numero_documento,
    sscdf.numero_referencia_mic          AS numero_referencia_mic,
    sscdf.fecha_vencimiento              AS fecha_vencimiento,
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
    sscdf.cobrador_intereses_generados  AS intereses,
    sscdf.check_trasladar_intereses     AS check_trasladar_intereses,
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
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia
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
    NULL AS tipo_comprobante,
    NULL AS subtipo_documento,
    NULL AS numero_documento,
    NULL AS numero_referencia_mic,
    NULL AS fecha_vencimiento,
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
    sscdmp.cobrador_intereses_generados  AS intereses,
    sscdmp.check_trasladar_intereses     AS check_trasladar_intereses,
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
    NULL                 AS id_factura,
    sscdmp.id_medio_pago AS id_medio_pago,
    NULL                 AS id_tratamiento_diferencia
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_sop_cobros_detalle_med_pag sscdmp
  WHERE sscdmp.id_transaccion = sct.id_transaccion
  AND scc.id_operacion        = sct.id_operacion
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
    sctd.cobrador_intereses_generados  AS intereses,
    sctd.check_trasladar_intereses     AS check_trasladar_intereses,
    sctd.porcentaje_bonif_intereses    AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses       AS importe_a_bonificar,
    sctd.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
    -- Mensajes de apropiacion
    sct.tipo_mensaje_estado AS tipo_mensaje_estado_transaccio,
    sct.mensaje_estado      AS mensaje_estado_transaccion,
    NULL                    AS tipo_mensaje_estado_debito,
    NULL                    AS mensaje_estado_debito,
    NULL                    AS estado_debito,
    NULL                    AS tipo_mensaje_estado_credito,
    NULL                    AS mensaje_estado_credito,
    NULL                    AS estado_credito,
    -- Datos ocultos del cobro / transaccion
    scc.id_cobro,
    sct.id_operacion,
    sct.id_transaccion,
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    NULL                           AS id_factura,
    NULL                           AS id_medio_pago,
    sctd.id_tratamiento_diferencia AS id_tratamiento_diferencia
  FROM shv_cob_cobro scc,
    shv_cob_transaccion sct,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion = sct.id_transaccion
  AND scc.id_operacion      = sct.id_operacion
  );  
  
