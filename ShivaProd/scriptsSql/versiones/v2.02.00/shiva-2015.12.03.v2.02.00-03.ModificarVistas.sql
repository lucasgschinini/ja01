CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_HISTORIAL (ID_DESCOBRO, ID_OPERACION, ESTADO, ID_MARCA, USUARIO_ULTIMA_MODIFICACION, FECHA_ULTIMA_MODIFICACION, MENSAJE_ESTADO, NUMERO_TRANSACCION, TIPO_COMPROBANTE, CLASE_COMPROBANTE, SUCURSAL_COMPROBANTE, NUMERO_COMPROBANTE, IMPORTE_COBRAR, FECHA_VALOR, REFERENCIA, ACUERDO_TRASLADO_CARGO)
AS
  (SELECT scd.ID_DESCOBRO,
    scd.ID_OPERACION,
    wf_estado1.ESTADO,
    wf_estado1.subestado,
    wf_estado1.USUARIO_ULTIMA_MODIFICACION,
    TO_CHAR(wf_estado1.FECHA_ULTIMA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_ULTIMA_MODIFICACION,
    NULL                                                            AS MENSAJE_ESTADO,
    NULL                                                            AS NUMERO_TRANSACCION,
    NULL                                                            AS TIPO_COMPROBANTE,
    NULL                                                            AS CLASE_COMPROBANTE,
    NULL                                                            AS SUCURSAL_COMPROBANTE,
    NULL                                                            AS NUMERO_COMPROBANTE,
    NULL                                                            AS IMPORTE_COBRAR,
    NULL                                                            AS FECHA_VALOR,
    NULL                                                            AS REFERENCIA,
    NULL                                                            AS ACUERDO_TRASLADO_CARGO
  FROM
    (SELECT swwe.id_workflow,
      swwe.estado,
      NULL AS subestado,
      swwe.fecha_modificacion AS FECHA_ULTIMA_MODIFICACION,
      swwe.usuario_modificacion AS USUARIO_ULTIMA_MODIFICACION,
      DBMS_LOB.substr(swwe.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado swwe
    WHERE swwe.estado != 'DES_NO_DISPONIBLE'
    UNION ALL
    SELECT sweh.id_workflow,
      sweh.estado,
      NULL AS subestado,
      sweh.fecha_modificacion AS FECHA_ULTIMA_MODIFICACION,
      sweh.usuario_modificacion AS USUARIO_ULTIMA_MODIFICACION,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado != 'DES_NO_DISPONIBLE'
    UNION ALL
    SELECT swwe.id_workflow,
      swwe.estado,
      swwm.id_marca,
      swwm.fecha_creacion AS FECHA_ULTIMA_MODIFICACION,
      swwm.usuario_creacion AS USUARIO_ULTIMA_MODIFICACION,
      NULL
    FROM shv_wf_workflow_estado swwe
    INNER JOIN shv_wf_workflow_marca swwm
    ON (swwe.id_workflow_estado = swwm.id_workflow_estado)
    WHERE (swwe.estado         != 'DES_NO_DISPONIBLE')
    UNION ALL
    SELECT sweh.id_workflow,
      sweh.estado,
      swmh.id_marca,
      swmh.fecha_creacion AS FECHA_ULTIMA_MODIFICACION,
      swmh.usuario_creacion AS USUARIO_ULTIMA_MODIFICACION,
      NULL
    FROM shv_wf_workflow_estado_hist sweh
    INNER JOIN shv_wf_workflow_marca_hist swmh
    ON(sweh.id_workflow_estado_hist = swmh.id_workflow_estado_hist)
    WHERE sweh.estado              != 'DES_NO_DISPONIBLE'
    ) wf_estado1
  INNER JOIN SHV_COB_DESCOBRO scd
  ON (scd.id_workflow      = wf_estado1.id_workflow)
  WHERE wf_estado1.ESTADO != 'DES_NO_DISPONIBLE'
  UNION
  SELECT scd.ID_DESCOBRO,
    scd.ID_OPERACION,
    wf_estado.ESTADO,
    NULL AS subestado,
    wf_estado.USUARIO_ULTIMA_MODIFICACION,
    TO_CHAR(wf_estado.FECHA_ULTIMA_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_ULTIMA_MODIFICACION,
    sct.MENSAJE_ESTADO,
    sct.NUMERO_TRANSACCION,
    scf.TIPO_COMPROBANTE,
    scf.CLASE_COMPROBANTE,
    scf.SUCURSAL_COMPROBANTE,
    scf.NUMERO_COMPROBANTE,
    scf.IMPORTE_COBRAR,
    TO_CHAR(scf.FECHA_VALOR, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_VALOR,
    sscdmp.REFERENCIA,
    scatc.ACUERDO_TRASLADO_CARGO
  FROM
    (SELECT swwe.id_workflow,
      swwe.estado,
      swwe.fecha_modificacion AS FECHA_ULTIMA_MODIFICACION,
      swwe.usuario_modificacion AS USUARIO_ULTIMA_MODIFICACION,
      DBMS_LOB.substr(swwe.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado swwe
    WHERE (swwe.estado != 'DES_NO_DISPONIBLE')
    UNION
    SELECT sweh.id_workflow,
      sweh.estado,
      sweh.fecha_modificacion AS FECHA_ULTIMA_MODIFICACION,
      sweh.usuario_modificacion AS USUARIO_ULTIMA_MODIFICACION,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado != 'DES_NO_DISPONIBLE'
    ) wf_estado
  INNER JOIN SHV_COB_DESCOBRO scd
  ON (scd.id_workflow = wf_estado.id_workflow)
  LEFT JOIN SHV_COB_TRANSACCION sct
  ON (scd.id_operacion = sct.id_operacion)
  LEFT JOIN SHV_COB_FACTURA scf
  ON (sct.id_transaccion = scf.id_transaccion)
  LEFT JOIN shv_sop_cobros_detalle_med_pag sscdmp
  ON (sct.id_transaccion = sscdmp.id_transaccion)
  LEFT JOIN
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
  ON (sct.id_transaccion = scatc.id_transaccion)
  WHERE wf_estado.ESTADO = 'DES_DESCOBRO_EN_ERROR'
  OR wf_estado.ESTADO    = 'DES_ERROR_EN_PRIMER_DESCOBRO'
);    
      
      
CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_BUSQUEDA (ID_COBRO, ID_COBRO_PADRE, ID_EMPRESA, ID_SEGMENTO, ID_OPERACION, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, ANALISTA, COPROPIETARIO, ID_ANALISTA_TEAM_COMERCIAL, ID_REVERSION_PADRE , ID_OPERACION_REVERSION, ID_REVERSION, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_APROB_SUPER_COB, FECHA_APROB_REFER_COB, FECHA_IMPUTACION, SUB_ESTADO, FECHA_ULTIMO_ESTADO, ESTADO_REVERSION)
                         AS
  SELECT SCEC.ID_COBRO   AS idCobro,
    SCEC.id_cobro_padre  AS idCobroPadre,
    SCEC.id_empresa      AS empresa,
    SCEC.id_segmento     AS segmento,
    SCEC.ID_OPERACION    AS idOperacion,
    SCEC.ID_MOTIVO_COBRO AS idMotivoCobro,
    SPMC.DESCRIPCION     AS descMotivoCobro,
    (SELECT LISTAGG(SUBSTR(CEC.ID_CLIENTE_LEGADO
      || ' '
      || CEC.RAZON_SOCIAL,0,30), '-') WITHIN GROUP(
    ORDER BY CEC.ID_CLIENTE_LEGADO)
    FROM SHV_COB_ED_CLIENTE CEC
    WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    )                     AS cliente,
    SWWE.estado           AS estado,
    SCEC.ID_ANALISTA      AS analista,
    SCEC.ID_COPROPIETARIO AS copropietario,
    (SELECT LISTAGG(team.USER_ANALISTA_COBRANZA_DATOS, '-') WITHIN GROUP(
    ORDER BY team.USER_ANALISTA_COBRANZA_DATOS)
    FROM SHV_COB_ED_CLIENTE CEC2
    JOIN SHV_PARAM_TEAM_COMERCIAL TEAM
    ON (TEAM.NRO_DE_CLIENTE=CEC2.ID_CLIENTE_LEGADO)
    WHERE CEC2.ID_COBRO    = SCEC.ID_COBRO
    )                          AS analistaTeamComercial,
    SCD.ID_DESCOBRO_PADRE      AS idReversionPadre,
    SCD.ID_OPERACION           AS idOperacionReversion,
    SCD.ID_DESCOBRO            AS idReversion,
    SCEC.IMPORTE_TOTAL         AS importeTotal,
    SCEC.FECHA_ALTA            AS fechaAlta,
    SCEC.FECHA_DERIVACION      AS fechaDerivacion,
    SCEC.FECHA_APROB_SUPER_COB AS fechaAprobSuperCob,
    SCEC.FECHA_APROB_REFER_COB AS fechaAprobReferCob,
    SCEC.FECHA_IMPUTACION      AS fechaImputacion,
    (SELECT SWWM1.ID_MARCA
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE swwm1.id_workflow_MARCA=
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.id_workflow_estado = SWWE.id_workflow_estado
      )
    ) AS subEstado,
    TO_CHAR( NVL (
    (SELECT SWWM1.fecha_creacion
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE swwm1.id_workflow_MARCA =
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.id_workflow_estado = SWWE.id_workflow_estado
      )
    ), SWWE.fecha_modificacion ), 'DD/MM/YYYY HH24:MI:SS') AS fechaUltimoEstado,
    SWWE1.ESTADO                                           AS estadoReversion
  FROM SHV_COB_ED_COBRO SCEC
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO
  LEFT JOIN SHV_COB_DESCOBRO SCD
  ON SCEC.ID_COBRO = SCD.ID_COBRO
  LEFT JOIN SHV_WF_WORKFLOW_ESTADO SWWE1
  ON SCD.ID_WORKFLOW = SWWE1.ID_WORKFLOW
  ;
  
  
  
  
   CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_DET_FACTURA (ID_FACTURA, ID_TRANSACCION, ESTADO, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, ORIGEN_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION, FECHA_ACUMULADO_SIMULACION, TIPO_PAGO, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, COBRADOR_INTERESES_TRASLADADOS, CHECK_TRASLADAR_INTERESES, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO , SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO)
    AS (
  --
  -- Facturas Mic
  --
  SELECT
    -- Datos del documento
    scf.id_factura,
    scf.id_transaccion,
    scf.estado,
    scf.sistema_origen                             AS sistema_origen,
    scf.id_cliente_legado                          AS id_cliente_legado,
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
    scf.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    scf.check_trasladar_intereses     AS check_trasladar_intereses,
    scfm.cobrar_segundo_vencimiento   AS check_cobrar_segundo_vencimien,
    scf.porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
    scf.importe_bonif_intereses       AS importe_bonif_intereses,
    scf.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
	scf.estado_cargo_generado          AS estado_cargo_generado,
    scf.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    scf.acuerdo_contracargo            AS acuerdo_contracargo,
    scf.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    scf.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
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
    scf.sistema_origen                      AS sistema_origen,
    scf.id_cliente_legado                   AS id_cliente_legado,
    scf.tipo_comprobante                    AS tipo_comprobante,
    NULL                                    AS subtipo_documento,
    scfc.origen_documento                   AS origen_documento,
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
    scf.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    scf.check_trasladar_intereses     AS check_trasladar_intereses,
    NULL                              AS check_cobrar_segundo_vencimien,
    scf.porcentaje_bonif_intereses    AS porcentaje_bonif_intereses,
    scf.importe_bonif_intereses       AS importe_bonif_intereses,
    scf.acuerdo_traslado_cargo        AS acuerdo_traslado_cargo,
    scf.estado_acuerdo_traslado_cargo AS estado_acuerdo_traslado_cargo,
    scf.id_cliente_acuerdo_tras_cargo AS id_cliente_acuerdo_tras_cargo,
	scf.estado_cargo_generado          AS estado_cargo_generado,
    scf.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    scf.acuerdo_contracargo            AS acuerdo_contracargo,
    scf.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    scf.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
    -- Mensajes de apropiacion
    scf.tipo_mensaje_estado AS tipo_mensaje_estado,
    scf.mensaje_estado      AS mensaje_estado
  FROM shv_cob_factura scf,
    shv_cob_factura_calipso scfc
  WHERE scfc.id_factura = scf.id_factura
  );

  
  
  CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_DET_MED_PAG (ID_MEDIO_PAGO, ID_TIPO_MEDIO_PAGO, DESCRIPCION_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, ORIGEN_DOCUMENTO, ID_TRANSACCION, ESTADO, IMPORTE, MONTO_ACUMULADO_SIMULACION, MIGRADO_DEIMOS, ESTADO_DEIMOS, FECHA_ACTUALIZACION_DEIMOS, TIPO_MENSAJE_ESTADO, MENSAJE_ESTADO, MONEDA, SISTEMA_ORIGEN, ID_CLIENTE_LEGADO, FECHA_VALOR, REFERENCIA, QUE_HACER_CON_INTERESES, COBRADOR_INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, PORCENTAJE_BONIF_INTERESES, IMPORTE_BONIF_INTERESES, SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO , SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO)
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
	    scmpd.que_hacer_con_intereses      AS que_hacer_con_intereses,
	    scmpd.cobrador_intereses_generados AS cobrador_intereses_generados,
	    scmpd.check_trasladar_intereses    AS check_trasladar_intereses,
	    scmpd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
	    scmpd.porcentaje_bonif_intereses   AS porcentaje_bonif_intereses,
	    scmpd.importe_bonif_intereses      AS importe_bonif_intereses,
	    scmpd.sistema_acuerdo              AS sistema_acuerdo,
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
  
CREATE OR REPLACE VIEW SHV_SOP_DESCOBROS_GRILLA_TRANS (NUMERO_TRANSACCION_FORMATEADO, ESTADO_TRANSACCION, SISTEMA_ORIGEN_DOCUMENTO, ID_CLIENTE_LEGADO_DOCUMENTO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION_DEB, FECHA_ACUMULADO_SIMULACION_DEB, ORIGEN_DOCUMENTO, SISTEMA_ORIGEN_MEDIO_PAGO, ID_CLIENTE_LEGADO_MEDIO_PAGO, TIPO_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, REFERENCIA_MEDIO_PAGO, FECHA_MEDIO_PAGO, IMPORTE_MEDIO_PAGO, MONTO_ACUMULADO_SIMULACION_CRE, TIPO_PAGO, QUE_HACER_CON_INTERESES, INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES, COBRADOR_INTERESES_TRASLADADOS, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_A_BONIFICAR, IMPORTE_A_BONIFICAR,
  SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO, SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO, TIPO_MENSAJE_ESTADO_TRANSACCIO, MENSAJE_ESTADO_TRANSACCION, TIPO_MENSAJE_ESTADO_DEBITO, MENSAJE_ESTADO_DEBITO, ESTADO_DEBITO, TIPO_MENSAJE_ESTADO_CREDITO, MENSAJE_ESTADO_CREDITO, ESTADO_CREDITO, ID_DESCOBRO, ID_OPERACION, ID_TRANSACCION, ID_TRANSACCION_PADRE, NUMERO_TRANSACCION, NUMERO_TRANSACCION_PADRE, NUMERO_TRANSACCION_PADRE_FORMA, ID_FACTURA, ID_MEDIO_PAGO, ID_TRATAMIENTO_DIFERENCIA)
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
    NULL                                 AS sistema_acuerdo,
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
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    DECODE(sct.id_transaccion_padre, NULL, NULL, lpad(sctp.id_operacion, 7, '0')
    ||'.'
    ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_sop_descobros_det_factura sscdf
  WHERE sscdf.id_transaccion   = sct.id_transaccion
  AND scd.id_operacion         = sct.id_operacion
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
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    DECODE(sct.id_transaccion_padre, NULL, NULL, lpad(sctp.id_operacion, 7, '0')
    ||'.'
    ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    NULL                                     AS id_factura,
    sscdmp.id_medio_pago                     AS id_medio_pago,
    NULL                                     AS id_tratamiento_diferencia
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_sop_descobros_det_med_pag sscdmp
  WHERE sscdmp.id_transaccion  = sct.id_transaccion
  AND scd.id_operacion         = sct.id_operacion
  AND sct.id_transaccion_padre = sctp.id_transaccion (+)
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
    sctd.que_hacer_con_intereses   																	                                                                                                               AS que_hacer_con_intereses,
    DECODE(sctd.tipo_tratamiento_diferencia, 'REINTEGRO_CRED_PROX_FAC_MIC', sctd.COBRADOR_INTERES_REAL_NRG_TRAS + sctd.COBRADOR_INTERES_REAL_RG_TRAS, sctd.COBRADOR_INTERES_REAL_TRAS) AS intereses,
    sctd.check_trasladar_intereses                                                                                                                                                 AS check_trasladar_intereses,
    sctd.cobrador_intereses_trasladados                                                                                                                                            AS cobrador_intereses_trasladados,
    NULL                                                                                                                                                                           AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses                                                                                                                                                AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses                                                                                                                                                   AS importe_a_bonificar,
    sctd.sistema_origen                                                                                                                                                            AS sistema_acuerdo,
    sctd.acuerdo_traslado_cargo                                                                                                                                                    AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo                                                                                                                                             AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo                                                                                                                                             AS id_cliente_acuerdo_tras_cargo,
    sctd.estado_cargo_generado                                                                                                                                                     AS estado_cargo_generado,
    sctd.sistema_acuerdo_contracargo                                                                                                                                               AS sistema_acuerdo_contracargo,
    sctd.acuerdo_contracargo                                                                                                                                                       AS acuerdo_contracargo,
    sctd.estado_acuerdo_contracargo                                                                                                                                                AS estado_acuerdo_contracargo,
    sctd.id_cliente_acuerdo_contracargo                                                                                                                                            AS id_cliente_acuerdo_contracargo,
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
    sct.id_transaccion_padre,
    sct.numero_transaccion,
    sctp.numero_transaccion numero_transaccion_padre,
    DECODE(sct.id_transaccion_padre, NULL, NULL, lpad(sctp.id_operacion, 7, '0')
    ||'.'
    ||lpad(sctp.numero_transaccion, 5, '0')) AS numero_transaccion_padre_forma,
    NULL                                     AS id_factura,
    NULL                                     AS id_medio_pago,
    sctd.id_tratamiento_diferencia           AS id_tratamiento_diferencia
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_cob_transaccion sctp,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion    = sct.id_transaccion
  AND scd.id_operacion         = sct.id_operacion
  AND sct.id_transaccion_padre = sctp.id_transaccion (+)
  );
  
  
   --u573005, fabio.giaquinta.ruiz, sprint 7
  -- muestra los datos de las operaciones relacionadas de un descobro
  CREATE OR REPLACE VIEW SHV_SOP_DESCOBROS_OPER_RELAC (SISTEMA, ID_OPERACION, ID_OPERACION_PADRE, NRO_TRANSACCION_PADRE, TIPO_COMPROBANTE, NRO_DOCUMENTO_RELACIONADO, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, SUB_ESTADO, FECHA_ULTIMO_ESTADO, ANALISTA, COPROPIETARIO, ANALISTA_TEAM_COMERCIAL, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_AUTORIZACION_REFERENTE, FECHA_IMPUTACION, ID_DOCUMENTO_CUENTAS_COBRANZA, ID_COBRANZA, ID_OPERACION_RELACIONADA, ID_DESCOBRO)
                               AS
  select SCDOR.SISTEMA          as SISTEMA,
    NVL(SCDOR.ID_OPERACION, SCDOR.ID_COBRANZA) AS ID_OPERACION,
    SCDOR.ID_OPERACION_PADRE    AS ID_OPERACION_PADRE,
    SCDOR.NRO_TRANSACCION_PADRE AS NRO_TRANSACCION_PADRE,
    SCDOR.TIPO_COMPROBANTE      AS TIPO_COMPROBANTE,
    NVL( TO_CHAR(SCDOR.NUMERO_REFERENCIA_MIC),  NVL( TO_CHAR(SCDOR.ID_DOCUMENTO_CUENTAS_COBRANZA), (SCDOR.CLASE_COMPROBANTE
    || '-'
    || SCDOR.SUCURSAL_COMPROBANTE
    || '-'
    || SCDOR.NUMERO_COMPROBANTE) ) ) AS NRO_DOCUMENTO_RELACIONADO,
    SCEC.ID_MOTIVO_COBRO           AS ID_MOTIVO_COBRO,
    SPMC.DESCRIPCION               AS DESC_MOTIVO_COBRO,
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
    TO_CHAR(
    (SELECT SWWM1.FECHA_CREACION
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE SWWM1.ID_WORKFLOW_MARCA=
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO
      )
    ), 'DD/MM/YYYY HH24:MI:SS' ) AS FECHA_ULTIMO_ESTADO,
    SCEC.ID_ANALISTA             AS ANALISTA,
    SCEC.ID_COPROPIETARIO        AS COPROPIETARIO,
    (SELECT LISTAGG(TEAM.USER_ANALISTA_COBRANZA_DATOS, '-') WITHIN GROUP(
    ORDER BY TEAM.USER_ANALISTA_COBRANZA_DATOS)
    FROM SHV_COB_ED_CLIENTE CEC2
    JOIN SHV_PARAM_TEAM_COMERCIAL TEAM
    ON (TEAM.NRO_DE_CLIENTE=CEC2.ID_CLIENTE_LEGADO)
    WHERE CEC2.ID_COBRO    = SCEC.ID_COBRO
    )                                                                                     AS ANALISTA_TEAM_COMERCIAL,
    NVL( SCEC.IMPORTE_TOTAL, SCDOR.IMPORTE_COBRADO )                                      AS IMPORTE_TOTAL,
    TO_CHAR(SCEC.FECHA_ALTA, 'DD/MM/YYYY' )                                               AS FECHA_ALTA,
    TO_CHAR(SCEC.FECHA_DERIVACION, 'DD/MM/YYYY' )                                         AS FECHA_DERIVACION,
    TO_CHAR(SCEC.FECHA_APROB_REFER_COB , 'DD/MM/YYYY' )                                   as FECHA_AUTORIZACION_REFERENTE,
    TO_CHAR(SCDOR.FECHA_COBRANZA , 'DD/MM/YYYY HH24:MI:SS.FF' ) AS FECHA_IMPUTACION,
    SCDOR.ID_DOCUMENTO_CUENTAS_COBRANZA                                                   AS ID_DOCUMENTO_CUENTAS_COBRANZA,
    SCDOR.ID_COBRANZA                                                                     AS ID_COBRANZA,
    SCDOR.ID_OPERACION_RELACIONADA                                                        AS ID_OPERACION_RELACIONADA,
    SCDOR.ID_DESCOBRO                                                                     AS ID_DESCOBRO
  FROM SHV_COB_DESCOBRO_OPERAC_RELAC SCDOR
  LEFT JOIN SHV_COB_COBRO SCC
  ON SCDOR.ID_OPERACION = SCC.ID_OPERACION
  LEFT JOIN SHV_COB_ED_COBRO SCEC
  ON SCC.ID_COBRO = SCEC.ID_COBRO
  LEFT JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  left join SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO;
  
  
  
  
  CREATE OR REPLACE VIEW SHV_SOP_BANDEJA_DE_ENTRADA (ID_TAREA, TIPO_TAREA, ESTADO_TAREA, FECHA_CREACION, USUARIO_CREACION, FECHA_EJECUCION, USUARIO_EJECUCION, INFORMACION_ADICIONAL, GESTIONA_SOBRE, PERFIL_ASIGNACION, USUARIO_ASIGNACION, ID_WORKFLOW_ASOCIADO, NUMERO_CLIENTE, RAZON_SOCIAL, IMPORTE, ESTADO_WORKFLOW_ASOCIADO, ID_REGISTRO_AVC, ID_VALOR_POR_REVERSION, ID_VALOR, ID_TALONARIO, ID_OPERACION_MASIVA, ID_COBRO, ID_DESCOBRO, ID_EMPRESA, DESCRIPCION_EMPRESA, ID_SEGMENTO, DESCRIPCION_SEGMENTO, NUMERO_BOLETA, DESCRIPCION_BANCO, NUMERO_CHEQUE, FECHA_VENCIMIENTO, CODIGO_INTERDEPOSITO, REFERENCIA, TIPO, NRO_CONSTANCIA, CUIT, PROVINCIA, COD_ORGANISMO, RANGO)
AS
  (
  -- INCLUYE CORRECCION DEFECTO 83 Y 84 SPRINT 3 U573005
  -- SPRINT 7, SE AGREGA EL UNION DE TAREAS DE DESCOBROS, U573005, FABIO.GIAQUINTA.RUIZ
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA_DEPOSITO_CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA)    AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                AS DESCRIPCION_BANCO,
    TO_CHAR(BOLDEPCHE.NUMERO_CHEQUE) AS NUMERO_CHEQUE,
    NULL                             AS FECHA_VENCIMIENTO,
    NULL                             AS CODIGO_INTERDEPOSITO,
    NULL                             AS REFERENCIA,
    NULL                             AS TIPO,
    NULL                             AS NRO_CONSTANCIA,
    NULL                             AS CUIT,
    NULL                             AS PROVINCIA,
    NULL                             AS COD_ORGANISMO,
    NULL                             AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_CHEQUE BOLDEPCHE,
    SHV_BOL_BOLETA BOLETA,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA       = SWTV.ID_TAREA
  AND SVV.ID_VALOR       = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW    = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA     = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO    = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR  = 2
  AND BOLDEPCHE.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA   = BOLDEPCHE.ID_BOLETA
  AND BANCO.ID_BANCO     = SUBSTR('0000'
    || BOLDEPCHE.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA_DEPOSITO_CHEQUE_DIFERIDO(3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA)                        AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                    AS DESCRIPCION_BANCO,
    NULL                                                 AS NUMERO_CHEQUE,
    TO_CHAR(BOLDEPCHEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                                 AS CODIGO_INTERDEPOSITO,
    TO_CHAR(BOLDEPCHEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                                 AS TIPO,
    NULL                                                 AS NRO_CONSTANCIA,
    NULL                                                 AS CUIT,
    NULL                                                 AS PROVINCIA,
    NULL                                                 AS COD_ORGANISMO,
    NULL                                                 AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_CHEQUE_PD BOLDEPCHEPD,
    SHV_BOL_BOLETA BOLETA,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA         = SWTV.ID_TAREA
  AND SVV.ID_VALOR         = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA       = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO      = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR    = 3
  AND BOLDEPCHEPD.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA     = BOLDEPCHEPD.ID_BOLETA
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || BOLDEPCHEPD.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - BOLETA DEPOSITO EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(BOLETA.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                          AS DESCRIPCION_BANCO,
    NULL                          AS NUMERO_CHEQUE,
    NULL                          AS FECHA_VENCIMIENTO,
    NULL                          AS CODIGO_INTERDEPOSITO,
    TO_CHAR(BOLETA.NUMERO_BOLETA) AS REFERENCIA,
    NULL                          AS TIPO,
    NULL                          AS NRO_CONSTANCIA,
    NULL                          AS CUIT,
    NULL                          AS PROVINCIA,
    NULL                          AS COD_ORGANISMO,
    NULL                          AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_BOLETA_DEP_EFECTIVO BOLDEPEFECTIVO,
    SHV_BOL_BOLETA BOLETA
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
    -- WHERE DE VALORES
  AND SWT.ID_TAREA            = SWTV.ID_TAREA
  AND SVV.ID_VALOR            = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA          = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO         = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR       = 4
  AND BOLDEPEFECTIVO.ID_VALOR = SVV.ID_VALOR
  AND BOLETA.ID_BOLETA        = BOLDEPEFECTIVO.ID_BOLETA
  AND SWT.ESTADO              = 'PENDIENTE'
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(VALORCHEQUE.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                  AS DESCRIPCION_BANCO,
    NULL                               AS NUMERO_CHEQUE,
    NULL                               AS FECHA_VENCIMIENTO,
    NULL                               AS CODIGO_INTERDEPOSITO,
    TO_CHAR(VALORCHEQUE.NUMERO_CHEQUE) AS REFERENCIA,
    NULL                               AS TIPO,
    NULL                               AS NRO_CONSTANCIA,
    NULL                               AS CUIT,
    NULL                               AS PROVINCIA,
    NULL                               AS COD_ORGANISMO,
    NULL                               AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_CHEQUE VALORCHEQUE,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA         = SWTV.ID_TAREA
  AND SVV.ID_VALOR         = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA       = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO      = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR    = 5
  AND VALORCHEQUE.ID_VALOR = SVV.ID_VALOR
  AND BANCO.ID_BANCO       = SUBSTR('0000'
    || VALORCHEQUE.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(VALORCHEQUEPD.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                      AS DESCRIPCION_BANCO,
    NULL                                                   AS NUMERO_CHEQUE,
    TO_CHAR(VALORCHEQUEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                                   AS CODIGO_INTERDEPOSITO,
    TO_CHAR(VALORCHEQUEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                                   AS TIPO,
    NULL                                                   AS NRO_CONSTANCIA,
    NULL                                                   AS CUIT,
    NULL                                                   AS PROVINCIA,
    NULL                                                   AS COD_ORGANISMO,
    NULL                                                   AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_CHEQUE_PD VALORCHEQUEPD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA           = SWTV.ID_TAREA
  AND SVV.ID_VALOR           = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW        = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA         = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO        = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR      = 6
  AND VALORCHEQUEPD.ID_VALOR = SVV.ID_VALOR
  AND BANCO.ID_BANCO         = SUBSTR('0000'
    || VALORCHEQUEPD.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - EFECTIVO (7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(EFECT.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                         AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(EFECT.NUMERO_BOLETA) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_EFECTIVO EFECT
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 7
  AND EFECT.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                              AS NUMERO_BOLETA,
    NULL                              AS DESCRIPCION_BANCO,
    NULL                              AS NUMERO_CHEQUE,
    NULL                              AS FECHA_VENCIMIENTO,
    NULL                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(TRANSF.NUMERO_REFERENCIA) AS REFERENCIA,
    NULL                              AS TIPO,
    NULL                              AS NRO_CONSTANCIA,
    NULL                              AS CUIT,
    NULL                              AS PROVINCIA,
    NULL                              AS COD_ORGANISMO,
    NULL                              AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_TRANSFERENCIA TRANSF
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 8
  AND TRANSF.ID_VALOR   = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(INTER.NUMERO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    INTER.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_INTERDEPOSITO INTER
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA      = SWTV.ID_TAREA
  AND SVV.ID_VALOR      = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW   = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA    = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO   = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR = 9
  AND INTER.ID_VALOR    = SVV.ID_VALOR
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES - RETENCION IMPUESTO(10)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SVV.ID_CLIENTE_LEGADO              AS NUMERO_CLIENTE,
    SVV.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SVV.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    SVV.ID_VALOR AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                  AS NUMERO_BOLETA,
    NULL                                  AS DESCRIPCION_BANCO,
    NULL                                  AS NUMERO_CHEQUE,
    NULL                                  AS FECHA_VENCIMIENTO,
    NULL                                  AS CODIGO_INTERDEPOSITO,
    TO_CHAR(RET.NRO_CONSTANCIA_RETENCION) AS REFERENCIA,
    TIPORET.DESCRIPCION                   AS TIPO,
    NULL                                  AS NRO_CONSTANCIA,
    RET.CUIT                              AS CUIT,
    JURI.DESCRIPCION                      AS PROVINCIA,
    NULL                                  AS COD_ORGANISMO,
    NULL                                  AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES
    SHV_WF_TAREA_VALOR SWTV,
    SHV_VAL_VALOR SVV,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS,
    SHV_VAL_VALOR_RETENCION RET,
    SHV_PARAM_TIPO_RET_IMPUESTO TIPORET,
    SHV_PARAM_JURISDICCION JURI
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES
  AND SWT.ID_TAREA                       = SWTV.ID_TAREA
  AND SVV.ID_VALOR                       = SWTV.ID_VALOR
  AND SVV.ID_WORKFLOW                    = SWFEENTIDAD.ID_WORKFLOW
  AND SVV.ID_EMPRESA                     = SPE.ID_EMPRESA (+)
  AND SVV.ID_SEGMENTO                    = SPS.ID_SEGMENTO (+)
  AND SVV.ID_TIPO_VALOR                  = 10
  AND RET.ID_VALOR                       = SVV.ID_VALOR
  AND TIPORET.ID_TIPO_RETENCION_IMPUESTO = RET.ID_TIPO_RETENCION_IMPUESTO
  AND RET.ID_JURISDICCION                = JURI.ID_JURISDICCION (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - CHEQUE (2)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPOSITO.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION               AS DESCRIPCION_BANCO,
    NULL                            AS NUMERO_CHEQUE,
    NULL                            AS FECHA_VENCIMIENTO,
    NULL                            AS CODIGO_INTERDEPOSITO,
    TO_CHAR(CHEQUE.NUMERO_CHEQUE)   AS REFERENCIA,
    NULL                            AS TIPO,
    NULL                            AS NRO_CONSTANCIA,
    NULL                            AS CUIT,
    NULL                            AS PROVINCIA,
    NULL                            AS COD_ORGANISMO,
    NULL                            AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_CHEQUE CHEQUE,
    SHV_AVC_REG_AVC_DEPOSITO DEPOSITO,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC     = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR       = 2
  AND CHEQUE.ID_REGISTRO_AVC   = SARA.ID_REGISTRO_AVC
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || CHEQUE.CODIGO_BANCO, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - CHEQUE DIFERIDO (3)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPOSITO.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                                 AS DESCRIPCION_BANCO,
    NULL                                              AS NUMERO_CHEQUE,
    TO_CHAR(CHEQUEPD.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                              AS CODIGO_INTERDEPOSITO,
    TO_CHAR(CHEQUEPD.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                              AS TIPO,
    NULL                                              AS NRO_CONSTANCIA,
    NULL                                              AS CUIT,
    NULL                                              AS PROVINCIA,
    NULL                                              AS COD_ORGANISMO,
    NULL                                              AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_CHEQUE_PD CHEQUEPD,
    SHV_AVC_REG_AVC_DEPOSITO DEPOSITO,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC     = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW         = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR       = 3
  AND CHEQUEPD.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND DEPOSITO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  AND BANCO.ID_BANCO           = SUBSTR('0000'
    || CHEQUEPD.CODIGO_BANCO, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - EFECTIVO (4)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(DEPO.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                        AS DESCRIPCION_BANCO,
    NULL                        AS NUMERO_CHEQUE,
    NULL                        AS FECHA_VENCIMIENTO,
    NULL                        AS CODIGO_INTERDEPOSITO,
    TO_CHAR(DEPO.NUMERO_BOLETA) AS REFERENCIA,
    NULL                        AS TIPO,
    NULL                        AS NRO_CONSTANCIA,
    NULL                        AS CUIT,
    NULL                        AS PROVINCIA,
    NULL                        AS COD_ORGANISMO,
    NULL                        AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_DEPOSITO DEPO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW     = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR   = 4
  AND DEPO.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - TRANSFERENCIA (8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                      AS NUMERO_BOLETA,
    NULL                      AS DESCRIPCION_BANCO,
    NULL                      AS NUMERO_CHEQUE,
    NULL                      AS FECHA_VENCIMIENTO,
    NULL                      AS CODIGO_INTERDEPOSITO,
    TO_CHAR(TRANS.REFERENCIA) AS REFERENCIA,
    NULL                      AS TIPO,
    NULL                      AS NRO_CONSTANCIA,
    NULL                      AS CUIT,
    NULL                      AS PROVINCIA,
    NULL                      AS COD_ORGANISMO,
    NULL                      AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_TRANSFERENCIA TRANS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC  = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR    = 8
  AND TRANS.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- REGISTROS AVC - INTERDEPOSITO (9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SARA.CODIGO_CLIENTE                 AS NUMERO_CLIENTE,
    SARA.RAZON_SOCIAL_CLIENTE_AGRUPADOR AS RAZON_SOCIAL,
    SARA.IMPORTE                        AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    SWTRA.ID_REGISTRO_AVC AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(INTER.CODIGO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    INTER.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE REGISTROS AVC
    SHV_WF_TAREA_REGISTRO_AVC SWTRA,
    SHV_AVC_REGISTRO_AVC SARA,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_AVC_REG_AVC_INTERDEPOSITO INTER
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ID_TAREA  = SWTRA.ID_TAREA
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE REGISTROS AVC
  AND SARA.ID_REGISTRO_AVC  = SWTRA.ID_REGISTRO_AVC
  AND SARA.ID_WORKFLOW      = SWFEENTIDAD.ID_WORKFLOW
  AND SARA.ID_TIPO_VALOR    = 9
  AND INTER.ID_REGISTRO_AVC = SARA.ID_REGISTRO_AVC
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - CHEQUE (5)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS NUMERO_BOLETA,
    BANCO.DESCRIPCION            AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_CHEQUE) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 5
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || SVVPR.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - CHEQUE DIFERIDO(6)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA)                   AS NUMERO_BOLETA,
    BANCO.DESCRIPCION                              AS DESCRIPCION_BANCO,
    NULL                                           AS NUMERO_CHEQUE,
    TO_CHAR(SVVPR.FECHA_VENCIMIENTO, 'DD/MM/YYYY') AS FECHA_VENCIMIENTO,
    NULL                                           AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_CHEQUE)                   AS REFERENCIA,
    NULL                                           AS TIPO,
    NULL                                           AS NRO_CONSTANCIA,
    NULL                                           AS CUIT,
    NULL                                           AS PROVINCIA,
    NULL                                           AS COD_ORGANISMO,
    NULL                                           AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_BANCO BANCO
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 6
  AND BANCO.ID_BANCO               = SUBSTR('0000'
    || SVVPR.ID_BANCO_ORIGEN, -4)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - EFECTIVO(7)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS NUMERO_BOLETA,
    NULL                         AS DESCRIPCION_BANCO,
    NULL                         AS NUMERO_CHEQUE,
    NULL                         AS FECHA_VENCIMIENTO,
    NULL                         AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_BOLETA) AS REFERENCIA,
    NULL                         AS TIPO,
    NULL                         AS NRO_CONSTANCIA,
    NULL                         AS CUIT,
    NULL                         AS PROVINCIA,
    NULL                         AS COD_ORGANISMO,
    NULL                         AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 7
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - TRANSFERENCIA(8)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                             AS NUMERO_BOLETA,
    NULL                             AS DESCRIPCION_BANCO,
    NULL                             AS NUMERO_CHEQUE,
    NULL                             AS FECHA_VENCIMIENTO,
    NULL                             AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.NUMERO_REFERENCIA) AS REFERENCIA,
    NULL                             AS TIPO,
    NULL                             AS NRO_CONSTANCIA,
    NULL                             AS CUIT,
    NULL                             AS PROVINCIA,
    NULL                             AS COD_ORGANISMO,
    NULL                             AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 8
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- VALORES POR REVERSION - INTERDEPOSITO(9)
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    SWT.PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL          AS NUMERO_CLIENTE,
    NULL          AS RAZON_SOCIAL,
    SVVPR.IMPORTE AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    SWTVR.ID_VALOR_POR_REVERSION AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    NULL AS ID_EMPRESA,
    NULL AS DESCRIPCION_EMPRESA,
    NULL AS ID_SEGMENTO,
    NULL AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                                AS NUMERO_BOLETA,
    NULL                                AS DESCRIPCION_BANCO,
    NULL                                AS NUMERO_CHEQUE,
    NULL                                AS FECHA_VENCIMIENTO,
    NULL                                AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SVVPR.CODIGO_INTERDEPOSITO) AS REFERENCIA,
    NULL                                AS TIPO,
    NULL                                AS NRO_CONSTANCIA,
    NULL                                AS CUIT,
    NULL                                AS PROVINCIA,
    SVVPR.CODIGO_ORGANISMO              AS COD_ORGANISMO,
    NULL                                AS RANGO
    ---------------------------------------
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE VALORES POR REVERSION
    SHV_WF_TAREA_VALOR_REVERSION SWTVR,
    SHV_VAL_VALOR_POR_REVERSION SVVPR,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE VALORES POR REVERSION
  AND SWT.ID_TAREA                 = SWTVR.ID_TAREA
  AND SVVPR.ID_VALOR_POR_REVERSION = SWTVR.ID_VALOR_POR_REVERSION
  AND SVVPR.ID_WORKFLOW            = SWFEENTIDAD.ID_WORKFLOW
  AND SVVPR.ID_TIPO_VALOR          = 9
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TALONARIOS
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) IN ('SUPERVISOR', 'ANALISTA', 'SUPERVISORTALONARIO')
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    NULL AS NUMERO_CLIENTE,
    NULL AS RAZON_SOCIAL,
    NULL AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    STT.ID_TALONARIO AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL AS NUMERO_BOLETA,
    NULL AS DESCRIPCION_BANCO,
    NULL AS NUMERO_CHEQUE,
    NULL AS FECHA_VENCIMIENTO,
    NULL AS CODIGO_INTERDEPOSITO,
    NULL AS REFERENCIA,
    NULL AS TIPO,
    NULL AS NRO_CONSTANCIA,
    NULL AS CUIT,
    NULL AS PROVINCIA,
    NULL AS COD_ORGANISMO,
    SUBSTR('0000'
    || STT.NUMERO_SERIE, -4)
    || '-'
    || SUBSTR('00000000'
    || STT.RANGO_DESDE, -8)
    || ' A '
    || SUBSTR('0000'
    || STT.NUMERO_SERIE, -4)
    || '-'
    || SUBSTR('00000000'
    || STT.RANGO_HASTA, -8) AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE TALONARIOS
    SHV_WF_TAREA_TALONARIO SWTT,
    SHV_TAL_TALONARIO STT,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE TALONARIOS
  AND SWT.ID_TAREA     = SWTT.ID_TAREA
  AND STT.ID_TALONARIO = SWTT.ID_TALONARIO
  AND STT.ID_WORKFLOW  = SWFEENTIDAD.ID_WORKFLOW
  AND STT.ID_EMPRESA   = SPE.ID_EMPRESA (+)
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
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) = 'ANALISTACOBRANZA'
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SWTC.ID_CLIENTE_LEGADO    AS NUMERO_CLIENTE,
    SWTC.RAZON_SOCIAL_CLIENTE AS RAZON_SOCIAL,
    SWTC.IMPORTE              AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    SCEC.ID_COBRO AS ID_COBRO,
	-- DESCOBROS
    NULL AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                       AS NUMERO_BOLETA,
    NULL                       AS DESCRIPCION_BANCO,
    NULL                       AS NUMERO_CHEQUE,
    NULL                       AS FECHA_VENCIMIENTO,
    NULL                       AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SCEC.ID_OPERACION) AS REFERENCIA,
    NULL                       AS TIPO,
    NULL                       AS NRO_CONSTANCIA,
    NULL                       AS CUIT,
    NULL                       AS PROVINCIA,
    NULL                       AS COD_ORGANISMO,
    NULL                       AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE COBROS
    SHV_WF_TAREA_COBRO SWTC,
    SHV_COB_ED_COBRO SCEC,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE COBROS
  AND SWT.ID_TAREA     = SWTC.ID_TAREA
  AND SCEC.ID_COBRO    = SWTC.ID_COBRO
  AND SCEC.ID_WORKFLOW = SWFEENTIDAD.ID_WORKFLOW
  AND SCEC.ID_EMPRESA  = SPE.ID_EMPRESA (+)
  AND SCEC.ID_SEGMENTO = SPS.ID_SEGMENTO (+)
  --------------------------------------------------------------------------------
  UNION
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- TAREA-DESCOBRO
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  SELECT
    -- DATOS COMUNES A TODAS LAS TAREAS
    SWT.ID_TAREA,
    SWT.TIPO_TAREA,
    SWT.ESTADO                                           AS ESTADO_TAREA,
    TO_CHAR(SWT.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION,
    SWT.USUARIO_CREACION,
    TO_CHAR(SWT.FECHA_EJECUCION, 'DD/MM/YYYY HH24:MI:SS') AS FECHA_EJECUCION,
    SWT.USUARIO_EJECUCION,
    SWT.INFORMACION_ADICIONAL,
    SWT.GESTIONA_SOBRE,
    CASE
      WHEN UPPER(SWT.PERFIL_ASIGNACION) = 'ANALISTACOBRANZA'
      THEN (SWT.PERFIL_ASIGNACION
        ||'_'
        ||SPE.ID_EMPRESA
        ||'_'
        ||SPS.ID_SEGMENTO)
      ELSE SWT.PERFIL_ASIGNACION
    END AS PERFIL_ASIGNACION,
    SWT.USUARIO_ASIGNACION,
    SWT.ID_WORKFLOW AS ID_WORKFLOW_ASOCIADO,
    -- NRO. CLIENTE, RAZON SOCIAL, IMPORTE
    SWTD.ID_CLIENTE_LEGADO    AS NUMERO_CLIENTE,
    SWTD.RAZON_SOCIAL_CLIENTE AS RAZON_SOCIAL,
    SWTD.IMPORTE              AS IMPORTE,
    -- REGISTRO AVC / VALOR, TALONARIO
    SWFEENTIDAD.ESTADO AS ESTADO_WORKFLOW_ASOCIADO,
    -- REGISTROS AVC
    NULL AS ID_REGISTRO_AVC,
    -- VALOR POR REVERSION
    NULL AS ID_VALOR_POR_REVERSION,
    -- VALOR
    NULL AS ID_VALOR,
    -- TALONARIO
    NULL AS ID_TALONARIO,
    -- OPERACION MASIVA
    NULL AS ID_OPERACION_MASIVA,
    -- COBROS
    NULL AS ID_COBRO,
	-- DESCOBROS
    SCD.ID_DESCOBRO AS ID_DESCOBRO,
    -- VALOR / TALONARIO / OPERACION MASIVA / COBRO
    SPE.ID_EMPRESA  AS ID_EMPRESA,
    SPE.DESCRIPCION AS DESCRIPCION_EMPRESA,
    SPS.ID_SEGMENTO AS ID_SEGMENTO,
    SPS.DESCRIPCION AS DESCRIPCION_SEGMENTO,
    ---------------------------------------
    NULL                       AS NUMERO_BOLETA,
    NULL                       AS DESCRIPCION_BANCO,
    NULL                       AS NUMERO_CHEQUE,
    NULL                       AS FECHA_VENCIMIENTO,
    NULL                       AS CODIGO_INTERDEPOSITO,
    TO_CHAR(SCD.ID_OPERACION) AS REFERENCIA,
    NULL                       AS TIPO,
    NULL                       AS NRO_CONSTANCIA,
    NULL                       AS CUIT,
    NULL                       AS PROVINCIA,
    NULL                       AS COD_ORGANISMO,
    NULL                       AS RANGO
  FROM
    -- FROM COMUNES A TODAS LAS TAREAS
    SHV_WF_TAREA SWT,
    SHV_WF_WORKFLOW_ESTADO SWFE,
    -- FROM DE DESCOBROS
    SHV_WF_TAREA_DESCOBRO SWTD,
    SHV_COB_DESCOBRO SCD,
    SHV_WF_WORKFLOW_ESTADO SWFEENTIDAD,
    SHV_PARAM_EMPRESA SPE,
    SHV_PARAM_SEGMENTO SPS
  WHERE
    -- WHERE COMUNES A TODAS LAS TAREAS
    SWT.ID_WORKFLOW = SWFE.ID_WORKFLOW
  AND SWT.ESTADO    = 'PENDIENTE'
    -- WHERE DE DESCOBROS
  AND SWT.ID_TAREA     = SWTD.ID_TAREA
  AND SCD.ID_DESCOBRO    = SWTD.ID_DESCOBRO
  AND SCD.ID_WORKFLOW = SWFEENTIDAD.ID_WORKFLOW
  AND SCD.ID_EMPRESA  = SPE.ID_EMPRESA (+)
  AND SCD.ID_SEGMENTO = SPS.ID_SEGMENTO (+)
  );
  
  Exit;