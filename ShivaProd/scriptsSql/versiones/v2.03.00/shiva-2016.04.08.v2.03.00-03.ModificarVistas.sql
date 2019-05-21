----------------------
-- Vista de operaciones relacionadas 
----------------------

CREATE OR REPLACE VIEW SHV_SOP_DESCOBROS_OPER_RELAC (SISTEMA, ID_OPERACION, ID_OPERACION_PADRE, NRO_TRANSACCION_PADRE, TIPO_COMPROBANTE, NRO_DOCUMENTO_RELACIONADO, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, SUB_ESTADO, FECHA_ULTIMO_ESTADO, ANALISTA, COPROPIETARIO, ANALISTA_TEAM_COMERCIAL, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_AUTORIZACION_REFERENTE, FECHA_IMPUTACION, ID_DOCUMENTO_CUENTAS_COBRANZA, ID_COBRANZA, ID_OPERACION_RELACIONADA, ID_DESCOBRO)
   AS
SELECT SCDOR.SISTEMA                         AS SISTEMA,
    NVL(SCDOR.ID_OPERACION, SCDOR.ID_COBRANZA) AS ID_OPERACION,
    SCDOR.ID_OPERACION_PADRE                   AS ID_OPERACION_PADRE,
    SCDOR.NRO_TRANSACCION_PADRE                AS NRO_TRANSACCION_PADRE,
    SCDOR.TIPO_COMPROBANTE                     as TIPO_COMPROBANTE,
    NVL( TO_CHAR(SCDOR.NUMERO_REFERENCIA_MIC),  (SCDOR.CLASE_COMPROBANTE
    || '-'
    || SCDOR.SUCURSAL_COMPROBANTE
    || '-'
    || SCDOR.NUMERO_COMPROBANTE) ) AS NRO_DOCUMENTO_RELACIONADO,
    SCEC.ID_MOTIVO_COBRO             AS ID_MOTIVO_COBRO,
    SPMC.DESCRIPCION                 AS DESC_MOTIVO_COBRO,
    NVL(
    (SELECT LISTAGG(SUBSTR(CEC.ID_CLIENTE_LEGADO
      || ' '
      || CEC.RAZON_SOCIAL,0,30), '-') WITHIN GROUP (
    ORDER BY CEC.ID_CLIENTE_LEGADO )
    FROM SHV_COB_ED_CLIENTE CEC
    WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    ), TO_CHAR(SCDOR.ID_CLIENTE_LEGADO) ) AS CLIENTE,
    SWWE.ESTADO                           AS ESTADO,
    (SELECT SWWM1.ID_MARCA
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE SWWM1.ID_WORKFLOW_MARCA=
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO
      )
    ) AS SUB_ESTADO,
    TO_CHAR( SCEC.FECHA_ULTIMA_MODIFICACION , 'DD/MM/YYYY HH24:MI:SS' ) AS FECHA_ULTIMO_ESTADO,
    SCEC.ID_ANALISTA                                           AS ANALISTA,
    SCEC.ID_COPROPIETARIO                                      AS COPROPIETARIO,
    (SELECT LISTAGG(TEAM.USER_ANALISTA_COBRANZA_DATOS, '-') WITHIN GROUP(
    ORDER BY TEAM.USER_ANALISTA_COBRANZA_DATOS)
    FROM SHV_COB_ED_CLIENTE CEC2
    JOIN SHV_PARAM_TEAM_COMERCIAL TEAM
    ON (TEAM.NRO_DE_CLIENTE=CEC2.ID_CLIENTE_LEGADO)
    WHERE CEC2.ID_COBRO    = SCEC.ID_COBRO
    )                                                              AS ANALISTA_TEAM_COMERCIAL,
    NVL( SCEC.IMPORTE_TOTAL, SCDOR.IMPORTE_COBRADO )               AS IMPORTE_TOTAL,
    TO_CHAR(SCEC.FECHA_ALTA, 'DD/MM/YYYY  HH24:MI:SS' )            AS FECHA_ALTA,
    TO_CHAR(SCEC.FECHA_DERIVACION, 'DD/MM/YYYY HH24:MI:SS' )       AS FECHA_DERIVACION,
    TO_CHAR(SCEC.FECHA_APROB_REFER_COB , 'DD/MM/YYYY HH24:MI:SS' ) AS FECHA_AUTORIZACION_REFERENTE,
    TO_CHAR(SCDOR.FECHA_COBRANZA , 'DD/MM/YYYY HH24:MI:SS' )       AS FECHA_IMPUTACION,
    SCDOR.ID_DOCUMENTO_CUENTAS_COBRANZA                            AS ID_DOCUMENTO_CUENTAS_COBRANZA,
    SCDOR.ID_COBRANZA                                              AS ID_COBRANZA,
    SCDOR.ID_OPERACION_RELACIONADA                                 AS ID_OPERACION_RELACIONADA,
    SCDOR.ID_DESCOBRO                                              AS ID_DESCOBRO
  FROM SHV_COB_DESCOBRO_OPERAC_RELAC SCDOR
  LEFT JOIN SHV_COB_COBRO SCC
  ON SCDOR.ID_OPERACION = SCC.ID_OPERACION
  LEFT JOIN SHV_COB_ED_COBRO SCEC
  ON SCC.ID_COBRO = SCEC.ID_COBRO
  LEFT JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  left join SHV_PARAM_MOTIVO_COBRO SPMC
  on SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO;
  
  --------
  -- se corrige el campo numero de referencia de medio de pago agregando un guion entre la clase y la sucursal
  -----
  CREATE OR REPLACE VIEW SHV_SOP_DESCOBROS_DET_MED_PAG (ID_MEDIO_PAGO, ID_TIPO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, ORIGEN_DOCUMENTO, ID_TRANSACCION, ESTADO, IMPORTE, MONTO_ACUMULADO_SIMULACION, MIGRADO_DEIMOS, ESTADO_DEIMOS, FECHA_ACTUALIZACION_DEIMOS, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO, MONEDA, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, FECHA_VALOR, REFERENCIA, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO, SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO)
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
      scmpdpc.que_hacer_con_intereses        AS que_hacer_con_intereses,
      scmpdpc.cobrador_intereses_generados   AS cobrador_intereses_generados,
      scmpdpc.check_trasladar_intereses      AS check_trasladar_intereses,
      scmpdpc.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
      scmpdpc.porcentaje_bonif_intereses     AS porcentaje_bonif_intereses,
      scmpdpc.importe_bonif_intereses        AS importe_bonif_intereses,
      scmp1.sistema_origen                   AS sistema_acuerdo,
      scmpdpc.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
      scmpdpc.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
      scmpdpc.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
      scmpdpc.estado_cargo_generado          AS estado_cargo_generado,
      scmpdpc.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
      scmpdpc.acuerdo_contracargo            AS acuerdo_contracargo,
      scmpdpc.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
      scmpdpc.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo
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
      scmpdpm.que_hacer_con_intereses        AS que_hacer_con_intereses,
      scmpdpm.cobrador_intereses_generados   AS cobrador_intereses_generados,
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
      scmpdpm.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo
    FROM shv_cob_med_pag_deb_prox_mic scmpdpm,
      shv_cob_med_pago scmp2
    WHERE scmpdpm.id_medio_pago = scmp2.id_medio_pago
    ) scmpd
  WHERE scmpd.id_medio_pago    = scmp.id_medio_pago
  AND sptmp.id_tipo_medio_pago = scmp.id_tipo_medio_pago
  );
  
  
  
  Exit;