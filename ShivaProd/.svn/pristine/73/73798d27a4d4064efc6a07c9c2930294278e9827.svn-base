--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de busqueda de cobros -
--------------------------------------------------------------------------------------------------------------
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
    
    TO_CHAR(
      NVL (
        (SELECT SWWM1.fecha_creacion FROM SHV_WF_WORKFLOW_MARCA SWWM1 WHERE swwm1.id_workflow_MARCA =
              (SELECT MAX (SWWM.ID_WORKFLOW_MARCA) FROM SHV_WF_WORKFLOW_MARCA SWWM WHERE SWWM.id_workflow_estado = SWWE.id_workflow_estado)),
         SWWE.fecha_modificacion
    ), 'DD/MM/YYYY HH24:MI:SS') AS fechaUltimoEstado
    
  FROM SHV_COB_ED_COBRO SCEC
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO
  LEFT JOIN SHV_COB_COBRO SCC
  ON SCEC.ID_COBRO = SCC.ID_COBRO;

--------------------------------------------------------------------------------------------------------------
-- Actualizacion de la vista de historial de cobros -
--------------------------------------------------------------------------------------------------------------

    -- Spring 5
  -- Vista de busqueda historial de cobros
  -- No muestro el estado COB_RECHAZADO sin marca
	CREATE OR REPLACE VIEW SHV_SOP_COBROS_HISTORIAL
AS
 (SELECT ed_cobro1.id_cobro,
    ed_cobro1.id_cobro_padre,
    ed_cobro1.id_operacion,
    wf_estado1.estado,
    wf_estado1.subestado,
    TO_CHAR(wf_estado1.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
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
      DBMS_LOB.substr(swwe.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado swwe
    WHERE swwe.estado NOT IN ('COB_NO_DISPONIBLE', 'COB_RECHAZADO')
    UNION ALL
    SELECT sweh.id_workflow,
      sweh.estado,
      NULL AS subestado,
      sweh.fecha_modificacion,
      sweh.usuario_modificacion,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado NOT IN ('COB_NO_DISPONIBLE', 'COB_RECHAZADO')
    UNION ALL
    SELECT swwe.id_workflow,
      swwe.estado,
      swwm.id_marca,
      swwm.fecha_creacion,
      swwm.usuario_creacion,
      NULL
    FROM shv_wf_workflow_estado swwe
    INNER JOIN shv_wf_workflow_marca swwm
    ON (swwe.id_workflow_estado = swwm.id_workflow_estado)
    WHERE (swwe.estado         != 'COB_NO_DISPONIBLE')
    AND swwm.id_marca          != 'SIMULACION_ONLINE_FINALIZADA_CON_EXITO'
    UNION ALL
    SELECT sweh.id_workflow,
      sweh.estado,
      swmh.id_marca,
      swmh.fecha_creacion,
      swmh.usuario_creacion,
      NULL
    FROM shv_wf_workflow_estado_hist sweh
    INNER JOIN shv_wf_workflow_marca_hist swmh
    ON(sweh.id_workflow_estado_hist = swmh.id_workflow_estado_hist)
    WHERE sweh.estado              != 'COB_NO_DISPONIBLE'
    AND swmh.id_marca              != 'SIMULACION_ONLINE_FINALIZADA_CON_EXITO'
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
    NULL                                                           AS subestado,
    TO_CHAR(wf_estado.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
    wf_estado.usuario_modificacion,
    wf_estado.observacion,
    sct.mensaje_estado,
    sct.numero_transaccion,
    scf.tipo_comprobante,
    scf.clase_comprobante,
    scf.sucursal_comprobante,
    scf.numero_comprobante,
    scf.importe_cobrar,
    TO_CHAR(scf.fecha_valor, 'DD/MM/YYYY HH24:MI:SS') AS fecha_valor,
    sscdmp.referencia,
    scatc.acuerdo_traslado_cargo
  FROM
    (SELECT swwe.id_workflow,
      swwe.estado,
      swwe.fecha_modificacion,
      swwe.usuario_modificacion,
      DBMS_LOB.substr(swwe.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado swwe
    WHERE (swwe.estado != 'COB_NO_DISPONIBLE')
    UNION
    SELECT sweh.id_workflow,
      sweh.estado,
      sweh.fecha_modificacion,
      sweh.usuario_modificacion,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) AS observacion
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
  OR wf_estado.estado     = 'COB_CONFIRMADO_CON_ERROR')
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

Exit;