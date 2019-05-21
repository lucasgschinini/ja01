CREATE OR REPLACE VIEW SHV_SOP_TAGETIK (CICLO, TIPO_FACTURA, TIPO_PAGO, FECHA_EMISION, FECHA_VENCIMIENTO, FECHA_VALOR, ID_TIPO_MEDIO_PAGO, MARKETING, CAJA, IMPORTE, ID_CLIENTE, RAZON_SOCIAL, TIPO_CLIENTE, TIPO_COMPROBANTE, CLASE_COMPROBANTE, SUC_COMPROBANTE, NUM_COMPROBANTE, ID_CUENTA_COB, ID_OPE, MON, FECHA_WORKFLOW, ORIGEN, DESCOBRO, TIPO_CAMBIO, IMPORTE_MED_PAGO_MONEDA_ORGEN, TIPO_CAMBIO_MEDIO_PAGO, MONEDA_MEDIO_PAGO, ID_CUENTA_COB_PADRE, SUBTIPO_DE_DOCUMENTO)
AS
  (
  ----------------------------------------------------------------------------
  -- MIC 
  ----------------------------------------------------------------------------
  -- Factura Mic (Cobro) 
  ----------------------------------------------------------------------------
  SELECT fmic.ciclo_facturacion                                        AS ciclo,
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
	NULL as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	NULL as TIPO_CAMBIO_MEDIO_PAGO,
	NULL as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	NULL as ID_CUENTA_COB_PADRE,
	NULL as subtipo_de_documento
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
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy')
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
	NULL as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	NULL as TIPO_CAMBIO_MEDIO_PAGO,
	NULL as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	NULL as ID_CUENTA_COB_PADRE,
	NULL as subtipo_de_documento
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
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy')
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
	NULL as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	NULL as TIPO_CAMBIO_MEDIO_PAGO,
	NULL as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	NULL as ID_CUENTA_COB_PADRE,
	NULL as subtipo_de_documento
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
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy')
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
	NULL as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	NULL as TIPO_CAMBIO_MEDIO_PAGO,
	NULL as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	NULL as ID_CUENTA_COB_PADRE,
	NULL as subtipo_de_documento
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
    TO_DATE(TO_CHAR(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy')
	UNION
 ----------------------------------------------------------------------------
 -- CALIPSO 
 ----------------------------------------------------------------------------
 -- Factura Calipso (Cobro) sin hijos Medio de pago SHIVA
 ----------------------------------------------------------------------------
  SELECT
    NULL AS ciclo,
    NULL AS tipo_factura,
    fac.tipo_pago AS tipo_pago,
    NULL AS fecha_emision,
    fac.fecha_vencimiento AS fecha_vencimiento,
    fac.fecha_valor AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL AS marketing,
    op.id_caja as caja,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
    fac.id_cliente_legado as id_cliente,
    fcal.razon_social_cliente_legado as razon_social,
    fcal.tipo_cliente as tipo_cliente,
    fac.tipo_comprobante as tipo_comprobante,
    fac.clase_comprobante as clase_comprobante,
    fac.sucursal_comprobante as suc_comprobante,
    fac.numero_comprobante as num_comprobante,
    fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
    op.id_operacion as id_ope,
    fcal.moneda as mon, --Moneda de la factura
    TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
    'CAL' as origen, NULL as descobro, 
    DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,-- Dollares
    mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
    DECODE(
        mp.moneda,
        'PES',
        1.00000,
        1.00000 --mps.tipoDeCambioFechaCobro  
    ) as TIPO_CAMBIO_MEDIO_PAGO,
    'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
    fcal.subtipo as subtipo_de_documento
  FROM 
    shv_cob_cobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion tran,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mps
  WHERE cob.id_workflow        =we.id_workflow
    AND cob.id_operacion       =op.id_operacion
    AND op.id_operacion        =tran.id_operacion
    AND tran.id_transaccion    =fac.id_transaccion
    AND fac.id_factura         = fcal.id_factura
    AND tran.id_transaccion    =mp.id_transaccion
    AND  mp.id_medio_pago = mps.id_medio_pago
    AND TRAN.NUMERO_TRANSACCION NOT IN(
        SELECT NUMERO_TRANSACCION_PADRE
          FROM SHV_COB_TRANSACCION tran
          WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
            AND tran.id_operacion = op.id_operacion
    )
    AND we.estado IN ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
    AND  mp.estado            <> 'NUEVO' 
	AND fac.estado            <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.tipo_pago,
    fac.fecha_vencimiento,
	fac.fecha_valor,
	mp.id_tipo_medio_pago,
	op.id_caja,
	fac.id_cliente_legado,
	fcal.razon_social_cliente_legado,
	fcal.tipo_cliente ,fac.tipo_comprobante,
	fac.clase_comprobante,fac.sucursal_comprobante,
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
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
	UNION
	------------------------------------------------------------------------------
	-- Facturas Calipso (Cobro) con hijos por diferencia de cambio Medio pago Shiva
	------------------------------------------------------------------------------
  select 
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	NULL as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		1.00000 --mps.tipoDeCambioFechaCobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
	shv_cob_cobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_shiva mps
   where cob.id_workflow = we.id_workflow
      and cob.id_operacion=op.id_operacion
	  and op.id_operacion=tran.id_operacion
	  and tran.id_transaccion=fac.id_transaccion
	  and fac.id_factura= fcal.id_factura
	  and tran.id_transaccion=mp.id_transaccion 
	  and  mp.id_medio_pago = mps.id_medio_pago
      and tran.numero_transaccion_padre IS NOT NULL
      and we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
	  and  mp.estado <> 'NUEVO' and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
  group by fac.tipo_pago,
    fac.fecha_vencimiento,
	fac.fecha_valor,
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
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo, mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
	UNION
   -----------------------------------------------------------------------------
  --  Factura Calipso (Cobro) sin hijos Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
 select 
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente,
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen, NULL as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,--Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
  	shv_cob_cobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  where cob.id_workflow=we.id_workflow
    and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion
	and  mp.id_medio_pago = ncc.id_medio_pago
	AND TRAN.NUMERO_TRANSACCION NOT IN(
      SELECT NUMERO_TRANSACCION_PADRE
        FROM SHV_COB_TRANSACCION tran
        WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
        and tran.id_operacion = op.id_operacion
    )
    AND we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO'--and fcal.moneda ='PES'
  group by fac.tipo_pago,fac.fecha_vencimiento,
    fac.fecha_valor,
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
	op.id_operacion ,fcal.moneda,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo,
	mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	UNION
-----------------------------------------------------------------------------
--  Factura Calipso (Cobro) con hijos por diferencia de cambio Medio de Pago Nota de Credito
------------------------------------------------------------------------------
  select 
    NULL as ciclo,
    NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
    NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
    fcal.razon_social_cliente_legado as razon_social,
    fcal.tipo_cliente as tipo_cliente,
    fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
    fac.sucursal_comprobante as suc_comprobante,
    fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
    op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen, NULL as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
	   mp.moneda,
	   'PES',
	   1.00000,
	   ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
     shv_cob_cobro cob,
     shv_cob_operacion op,
     shv_wf_workflow_estado we,
  	 shv_cob_transaccion tran,
     shv_cob_factura fac,
	 shv_cob_factura_calipso fcal,
	 shv_cob_med_pago mp,
	 shv_cob_med_pag_nota_cred_clp ncc
   where cob.id_workflow=we.id_workflow
    and cob.id_operacion=op.id_operacion
    and op.id_operacion=tran.id_operacion
    and tran.id_transaccion=fac.id_transaccion
    and fac.id_factura= fcal.id_factura
    and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncc.id_medio_pago 
    and tran.numero_transaccion_padre IS NOT NULL
    AND we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
	and  mp.estado <> 'NUEVO' 
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
  group by fac.tipo_pago,
	fac.fecha_vencimiento,
	fac.fecha_valor,
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
	fcal.moneda, TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo,
	mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	UNION
  -----------------------------------------------------------------
  -- Nota de credito (Cobro) Sin hijos
  -----------------------------------------------------------------
select 
	NULL as ciclo,
	NULL as tipo_factura,
	NULL as tipo_pago,
	NULL as fecha_emision,
	NULL as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	sum(mp.importe) as importe,
	ncc.id_cliente_legado as id_cliente,
	ncc.razon_social_cliente_legado as razon_social,
	ncc.tipo_cliente as tipo_cliente,
	ncc.tipo_comprobante as tipo_comprobante,
	ncc.clase_comprobante as clase_comprobante,
	ncc.sucursal_comprobante as suc_comprobante,
	ncc.numero_comprobante as num_comprobante,
	ncc.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,ncc.moneda as mon,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,'CAL' as origen,
	NULL as descobro,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_cambio  
	) as TIPO_CAMBIO,
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	ncc.moneda as MONEDA_MEDIO_PAGO,
	ncc.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	ncc.subtipo as subtipo_de_documento
  from  shv_cob_cobro cob,
    shv_cob_operacion op,
	shv_cob_transaccion tran,
    shv_cob_factura fac,
	shv_wf_workflow_estado we,
	shv_cob_med_pago mp,
	shv_cob_med_pag_nota_cred_clp ncc,
	shv_cob_factura_calipso fcal
    where cob.id_workflow=we.id_workflow
	  and cob.id_operacion=op.id_operacion
	  and op.id_operacion=tran.id_operacion
	  and tran.id_transaccion=fac.id_transaccion
	  and tran.id_transaccion=mp.id_transaccion
	  and mp.id_medio_pago=ncc.id_medio_pago
	  and we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
      AND fac.id_factura= fcal.id_factura 
      AND TRAN.NUMERO_TRANSACCION NOT IN(
        SELECT NUMERO_TRANSACCION_PADRE
          FROM SHV_COB_TRANSACCION tran
          WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
          and tran.id_operacion = op.id_operacion
      )
      and  mp.estado <> 'NUEVO'
	  and fac.estado <> 'NUEVO'
    group by 
      ncc.fecha_emision,fac.fecha_valor,
      mp.id_tipo_medio_pago,op.id_caja,
	  ncc.id_cliente_legado,
	  ncc.razon_social_cliente_legado,
	  ncc.tipo_cliente,
	  ncc.tipo_comprobante,
	  ncc.clase_comprobante,
	  ncc.sucursal_comprobante,
	  ncc.numero_comprobante,
	  ncc.id_documento_cuenta_cobranza,
	  op.id_operacion,
	  ncc.moneda,
	  TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	  ncc.tipo_cambio,
	  mp.importe,
	  ncc.tipo_de_cambio_fecha_cobro,
	  ncc.moneda,
	  ncc.id_doc_ctas_cob_padre,
	  ncc.subtipo, mp.moneda
	  UNION
	  -----------------------------------------------------------------
  -- Nota de credit (Cobro) Con hijos diferencia de combio
  -----------------------------------------------------------------
select 
	NULL as ciclo,
	NULL as tipo_factura,
	NULL as tipo_pago,
	NULL as fecha_emision,
	NULL as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing, op.id_caja as caja,
	sum(mp.importe) as importe,
	ncc.id_cliente_legado as id_cliente,
	ncc.razon_social_cliente_legado as razon_social,
	ncc.tipo_cliente as tipo_cliente,
	ncc.tipo_comprobante as tipo_comprobante,
	ncc.clase_comprobante as clase_comprobante,
	ncc.sucursal_comprobante as suc_comprobante,
	ncc.numero_comprobante as num_comprobante,
	ncc.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	ncc.moneda as mon,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,'CAL' as origen,
	NULL as descobro, 
	DECODE(
		ncc.moneda,
		'PES',
		1.00000,
		ncc.tipo_cambio  
	) as TIPO_CAMBIO,
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	ncc.moneda as MONEDA_MEDIO_PAGO,
	ncc.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	ncc.subtipo as subtipo_de_documento
  from  shv_cob_cobro cob,
	shv_cob_operacion op,
	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_wf_workflow_estado we,
	shv_cob_med_pago mp,
	shv_cob_med_pag_nota_cred_clp ncc,
	shv_cob_factura_calipso fcal
   where cob.id_workflow=we.id_workflow 
     and cob.id_operacion=op.id_operacion
	 and op.id_operacion=tran.id_operacion
	 and tran.id_transaccion=fac.id_transaccion
	 and tran.id_transaccion=mp.id_transaccion
	 and mp.id_medio_pago=ncc.id_medio_pago
	 and we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR')
     AND fac.id_factura= fcal.id_factura
     and tran.numero_transaccion_padre IS NOT NULL
     and  mp.estado <> 'NUEVO'
	 and fac.estado <> 'NUEVO'
    group by 
      ncc.fecha_emision,
	  fac.fecha_valor,
      mp.id_tipo_medio_pago,
	  op.id_caja,
	  ncc.id_cliente_legado,
	  ncc.razon_social_cliente_legado,
	  ncc.tipo_cliente,
	  ncc.tipo_comprobante,
	  ncc.clase_comprobante,
	  ncc.sucursal_comprobante,
	  ncc.numero_comprobante,
	  ncc.id_documento_cuenta_cobranza,
	  op.id_operacion,
	  ncc.moneda,
	  TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	  ncc.tipo_cambio,
	  mp.importe,
	  ncc.tipo_de_cambio_fecha_cobro,
	  ncc.moneda,
	  ncc.id_doc_ctas_cob_padre,
	  ncc.subtipo, mp.moneda
	  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(Cobro) sin hijos
  -----------------------------------------------------------------
select
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente,
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen, NULL as descobro, 
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	ncta.importe_aplic_fec_emis_mon_ori as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncta.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from shv_cob_cobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_cta ncta
   where cob.id_workflow=we.id_workflow
	and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncta.id_medio_pago
	AND TRAN.NUMERO_TRANSACCION NOT IN(
		SELECT NUMERO_TRANSACCION_PADRE
			FROM SHV_COB_TRANSACCION tran
			WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
			and tran.id_operacion = op.id_operacion
    )
	and we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
   group by fac.tipo_pago,
	fac.fecha_vencimiento,
	fac.fecha_valor,
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
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
     fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	 ncta.importe_aplic_fec_emis_mon_ori,
	 fcal.id_doc_ctas_cob_padre,
	 fcal.subtipo,
	 mp.moneda,
	 fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	 ncta.tipo_de_cambio_fecha_cobro
	 UNION
-----------------------------------------------------------------
-- Facturas Calipso CTA(Cobro) sin hijos
-----------------------------------------------------------------
select
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen, NULL as descobro, 
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	ncta.importe_aplic_fec_emis_mon_ori as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncta.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from shv_cob_cobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_cta ncta
   where cob.id_workflow=we.id_workflow
	and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncta.id_medio_pago
	and tran.numero_transaccion_padre IS NOT NULL
	and we.estado in ('COB_COBRADO','COB_CONFIRMADO_CON_ERROR') 
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
	group by fac.tipo_pago,
		fac.fecha_vencimiento,
		fac.fecha_valor,
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
		TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
		fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
		ncta.importe_aplic_fec_emis_mon_ori,
		fcal.id_doc_ctas_cob_padre,
		fcal.subtipo,
		mp.moneda,
		fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
		ncta.tipo_de_cambio_fecha_cobro
		UNION
----------------------------------------------------------------------------
-- Calipso Descobro
----------------------------------------------------------------------------
-- Factura Calipso (DesCobro) sin hijos Medio de pago SHIVA
----------------------------------------------------------------------------
  SELECT
    NULL AS ciclo,
    NULL AS tipo_factura,
    fac.tipo_pago AS tipo_pago,
    NULL AS fecha_emision,
    fac.fecha_vencimiento AS fecha_vencimiento,
    fac.fecha_valor AS fecha_valor,
    mp.id_tipo_medio_pago AS id_tipo_medio_pago,
    NULL AS marketing,
    op.id_caja as caja,
    fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
    fac.id_cliente_legado as id_cliente,
    fcal.razon_social_cliente_legado as razon_social,
    fcal.tipo_cliente as tipo_cliente,
    fac.tipo_comprobante as tipo_comprobante,
    fac.clase_comprobante as clase_comprobante,
    fac.sucursal_comprobante as suc_comprobante,
    fac.numero_comprobante as num_comprobante,
    fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
    op.id_operacion as id_ope,
    fcal.moneda as mon, --Moneda de la factura
    TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
    'CAL' as origen, 
    'X' as descobro, 
    DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
    -- Dollares
    mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
    DECODE(
        mp.moneda,
        'PES',
        1.00000,
        1.00000 --mps.tipoDeCambioFechaCobro  
    ) as TIPO_CAMBIO_MEDIO_PAGO,
    'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
    fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
    fcal.subtipo as subtipo_de_documento
  FROM 
    shv_cob_descobro cob,
    shv_cob_operacion op,
    shv_wf_workflow_estado we,
    shv_cob_transaccion tran,
    shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_shiva mps
  WHERE cob.id_workflow        =we.id_workflow
    AND cob.id_operacion       =op.id_operacion
    AND op.id_operacion        =tran.id_operacion
    AND tran.id_transaccion    =fac.id_transaccion
    AND fac.id_factura         = fcal.id_factura
    AND tran.id_transaccion    =mp.id_transaccion
    AND  mp.id_medio_pago = mps.id_medio_pago
    AND TRAN.NUMERO_TRANSACCION NOT IN(
        SELECT NUMERO_TRANSACCION_PADRE
          FROM SHV_COB_TRANSACCION tran
          WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
            AND tran.id_operacion = op.id_operacion
    )
     AND we.estado             = 'DES_DESCOBRADO'
    AND  mp.estado            <> 'NUEVO' 
	AND fac.estado            <> 'NUEVO' --and fcal.moneda ='PES'
  GROUP BY fac.tipo_pago,
    fac.fecha_vencimiento,
	fac.fecha_valor,
	mp.id_tipo_medio_pago,
	op.id_caja,
	fac.id_cliente_legado,
	fcal.razon_social_cliente_legado,
	fcal.tipo_cliente ,fac.tipo_comprobante,
	fac.clase_comprobante,fac.sucursal_comprobante,
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
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
	UNION
------------------------------------------------------------------------------
-- Facturas Calipso (DesCobro) con hijos por diferencia de cambio Medio pago Shiva
------------------------------------------------------------------------------
  select 
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	'X' as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		1.00000 --mps.tipoDeCambioFechaCobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
	shv_cob_descobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_shiva mps
   where cob.id_workflow = we.id_workflow
      and cob.id_operacion=op.id_operacion
	  and op.id_operacion=tran.id_operacion
	  and tran.id_transaccion=fac.id_transaccion
	  and fac.id_factura= fcal.id_factura
	  and tran.id_transaccion=mp.id_transaccion 
	  and  mp.id_medio_pago = mps.id_medio_pago
      and tran.numero_transaccion_padre IS NOT NULL
       AND we.estado             = 'DES_DESCOBRADO'
	  and  mp.estado <> 'NUEVO' and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
  group by fac.tipo_pago,
    fac.fecha_vencimiento,
	fac.fecha_valor,
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
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo, mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI
	UNION
   -----------------------------------------------------------------------------
  --  Factura Calipso (DesCobro) sin hijos Medio de Pago Nota de Credito
  ------------------------------------------------------------------------------
 select 
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente,
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	'X' as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,--Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
  	shv_cob_descobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
    shv_cob_factura_calipso fcal,
    shv_cob_med_pago mp,
    shv_cob_med_pag_nota_cred_clp ncc
  where cob.id_workflow=we.id_workflow
    and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion
	and  mp.id_medio_pago = ncc.id_medio_pago
	AND TRAN.NUMERO_TRANSACCION NOT IN(
      SELECT NUMERO_TRANSACCION_PADRE
        FROM SHV_COB_TRANSACCION tran
        WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
        and tran.id_operacion = op.id_operacion
    )
     AND we.estado             = 'DES_DESCOBRADO'
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO'--and fcal.moneda ='PES'
  group by fac.tipo_pago,fac.fecha_vencimiento,
    fac.fecha_valor,
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
	op.id_operacion ,fcal.moneda,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo,
	mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	UNION
-----------------------------------------------------------------------------
--  Factura Calipso (DesCobro) con hijos por diferencia de cambio Medio de Pago Nota de Credito
------------------------------------------------------------------------------
  select 
    NULL as ciclo,
    NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
    NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
    fcal.razon_social_cliente_legado as razon_social,
    fcal.tipo_cliente as tipo_cliente,
    fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
    fac.sucursal_comprobante as suc_comprobante,
    fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
    op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	'X' as descobro,
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
	   mp.moneda,
	   'PES',
	   1.00000,
	   ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from 
     shv_cob_descobro cob,
     shv_cob_operacion op,
     shv_wf_workflow_estado we,
  	 shv_cob_transaccion tran,
     shv_cob_factura fac,
	 shv_cob_factura_calipso fcal,
	 shv_cob_med_pago mp,
	 shv_cob_med_pag_nota_cred_clp ncc
   where cob.id_workflow=we.id_workflow
    and cob.id_operacion=op.id_operacion
    and op.id_operacion=tran.id_operacion
    and tran.id_transaccion=fac.id_transaccion
    and fac.id_factura= fcal.id_factura
    and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncc.id_medio_pago 
    and tran.numero_transaccion_padre IS NOT NULL
    AND we.estado             = 'DES_DESCOBRADO'
	and  mp.estado <> 'NUEVO' 
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
  group by fac.tipo_pago,
	fac.fecha_vencimiento,
	fac.fecha_valor,
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
	fcal.moneda, TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
    fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	mp.importe,
	fcal.id_doc_ctas_cob_padre,
	fcal.subtipo,
	mp.moneda,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	ncc.TIPO_DE_CAMBIO_FECHA_COBRO
	UNION
  -----------------------------------------------------------------
  -- Nota de credito (DesCobro) Sin hijos
  -----------------------------------------------------------------
select 
	NULL as ciclo,
	NULL as tipo_factura,
	NULL as tipo_pago,
	NULL as fecha_emision,
	NULL as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	sum(mp.importe) as importe,
	ncc.id_cliente_legado as id_cliente,
	ncc.razon_social_cliente_legado as razon_social,
	ncc.tipo_cliente as tipo_cliente,
	ncc.tipo_comprobante as tipo_comprobante,
	ncc.clase_comprobante as clase_comprobante,
	ncc.sucursal_comprobante as suc_comprobante,
	ncc.numero_comprobante as num_comprobante,
	ncc.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,ncc.moneda as mon,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,'CAL' as origen,
	'X' as descobro,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_cambio  
	) as TIPO_CAMBIO,
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	ncc.moneda as MONEDA_MEDIO_PAGO,
	ncc.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	ncc.subtipo as subtipo_de_documento
  from  shv_cob_descobro cob,
    shv_cob_operacion op,
	shv_cob_transaccion tran,
    shv_cob_factura fac,
	shv_wf_workflow_estado we,
	shv_cob_med_pago mp,
	shv_cob_med_pag_nota_cred_clp ncc,
	shv_cob_factura_calipso fcal
    where cob.id_workflow=we.id_workflow
	  and cob.id_operacion=op.id_operacion
	  and op.id_operacion=tran.id_operacion
	  and tran.id_transaccion=fac.id_transaccion
	  and tran.id_transaccion=mp.id_transaccion
	  and mp.id_medio_pago=ncc.id_medio_pago
	   AND we.estado             = 'DES_DESCOBRADO'
      AND fac.id_factura= fcal.id_factura 
      AND TRAN.NUMERO_TRANSACCION NOT IN(
        SELECT NUMERO_TRANSACCION_PADRE
          FROM SHV_COB_TRANSACCION tran
          WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
          and tran.id_operacion = op.id_operacion
      )
      and  mp.estado <> 'NUEVO'
	  and fac.estado <> 'NUEVO'
    group by 
      ncc.fecha_emision,fac.fecha_valor,
      mp.id_tipo_medio_pago,op.id_caja,
	  ncc.id_cliente_legado,
	  ncc.razon_social_cliente_legado,
	  ncc.tipo_cliente,
	  ncc.tipo_comprobante,
	  ncc.clase_comprobante,
	  ncc.sucursal_comprobante,
	  ncc.numero_comprobante,
	  ncc.id_documento_cuenta_cobranza,
	  op.id_operacion,
	  ncc.moneda,
	  TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	  ncc.tipo_cambio,
	  mp.importe,
	  ncc.tipo_de_cambio_fecha_cobro,
	  ncc.moneda,
	  ncc.id_doc_ctas_cob_padre,
	  ncc.subtipo, mp.moneda
	  UNION
	  -----------------------------------------------------------------
  -- Nota de credit (DesCobro) Con hijos diferencia de combio
  -----------------------------------------------------------------
select 
	NULL as ciclo,
	NULL as tipo_factura,
	NULL as tipo_pago,
	NULL as fecha_emision,
	NULL as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing, op.id_caja as caja,
	sum(mp.importe) as importe,
	ncc.id_cliente_legado as id_cliente,
	ncc.razon_social_cliente_legado as razon_social,
	ncc.tipo_cliente as tipo_cliente,
	ncc.tipo_comprobante as tipo_comprobante,
	ncc.clase_comprobante as clase_comprobante,
	ncc.sucursal_comprobante as suc_comprobante,
	ncc.numero_comprobante as num_comprobante,
	ncc.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	ncc.moneda as mon,
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,'CAL' as origen,
	'X' as descobro,
	DECODE(
		ncc.moneda,
		'PES',
		1.00000,
		ncc.tipo_cambio  
	) as TIPO_CAMBIO,
	mp.importe as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncc.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	ncc.moneda as MONEDA_MEDIO_PAGO,
	ncc.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	ncc.subtipo as subtipo_de_documento
  from  shv_cob_descobro cob,
	shv_cob_operacion op,
	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_wf_workflow_estado we,
	shv_cob_med_pago mp,
	shv_cob_med_pag_nota_cred_clp ncc,
	shv_cob_factura_calipso fcal
   where cob.id_workflow=we.id_workflow 
     and cob.id_operacion=op.id_operacion
	 and op.id_operacion=tran.id_operacion
	 and tran.id_transaccion=fac.id_transaccion
	 and tran.id_transaccion=mp.id_transaccion
	 and mp.id_medio_pago=ncc.id_medio_pago
	 AND we.estado             = 'DES_DESCOBRADO'
     AND fac.id_factura= fcal.id_factura
     and tran.numero_transaccion_padre IS NOT NULL
     and  mp.estado <> 'NUEVO'
	 and fac.estado <> 'NUEVO'
    group by 
      ncc.fecha_emision,
	  fac.fecha_valor,
      mp.id_tipo_medio_pago,
	  op.id_caja,
	  ncc.id_cliente_legado,
	  ncc.razon_social_cliente_legado,
	  ncc.tipo_cliente,
	  ncc.tipo_comprobante,
	  ncc.clase_comprobante,
	  ncc.sucursal_comprobante,
	  ncc.numero_comprobante,
	  ncc.id_documento_cuenta_cobranza,
	  op.id_operacion,
	  ncc.moneda,
	  TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'),
	  ncc.tipo_cambio,
	  mp.importe,
	  ncc.tipo_de_cambio_fecha_cobro,
	  ncc.moneda,
	  ncc.id_doc_ctas_cob_padre,
	  ncc.subtipo, mp.moneda
	  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso CTA(DesCobro) sin hijos
  -----------------------------------------------------------------
select
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente,
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	'X' as descobro, 
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	ncta.importe_aplic_fec_emis_mon_ori as IMPORTE_MED_PAGO_MONEDA_ORGEN,
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncta.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from shv_cob_descobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_cta ncta
   where cob.id_workflow=we.id_workflow
	and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncta.id_medio_pago
	AND TRAN.NUMERO_TRANSACCION NOT IN(
		SELECT NUMERO_TRANSACCION_PADRE
			FROM SHV_COB_TRANSACCION tran
			WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
			and tran.id_operacion = op.id_operacion
    )
	AND we.estado             = 'DES_DESCOBRADO'
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
   group by fac.tipo_pago,
	fac.fecha_vencimiento,
	fac.fecha_valor,
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
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
     fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
	 ncta.importe_aplic_fec_emis_mon_ori,
	 fcal.id_doc_ctas_cob_padre,
	 fcal.subtipo,
	 mp.moneda,
	 fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
	 ncta.tipo_de_cambio_fecha_cobro
	 UNION
-----------------------------------------------------------------
-- Facturas Calipso CTA(DesCobro) sin hijos
-----------------------------------------------------------------
select
	NULL as ciclo,
	NULL as tipo_factura,
	fac.tipo_pago as tipo_pago,
	NULL as fecha_emision,
	fac.fecha_vencimiento as fecha_vencimiento,
	fac.fecha_valor as fecha_valor,
	mp.id_tipo_medio_pago as id_tipo_medio_pago,
	NULL as marketing,
	op.id_caja as caja,
	fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI as importe,
	fac.id_cliente_legado as id_cliente, 
	fcal.razon_social_cliente_legado as razon_social,
	fcal.tipo_cliente as tipo_cliente,
	fac.tipo_comprobante as tipo_comprobante,
	fac.clase_comprobante as clase_comprobante,
	fac.sucursal_comprobante as suc_comprobante,
	fac.numero_comprobante as num_comprobante,
	fcal.id_documento_cuenta_cobranza as id_cuenta_cob,
	op.id_operacion as id_ope,
	fcal.moneda as mon, --Moneda de la factura
	TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy') as fecha_workflow,
	'CAL' as origen,
	'X' as descobro, 
	DECODE(
        fcal.moneda,
        'PES',
        1.00000,
        fcal.TIPO_DE_CAMBIO_FECHA_EMISION  
    ) as tipo_cambio,
	-- Dollares
	ncta.importe_aplic_fec_emis_mon_ori as IMPORTE_MED_PAGO_MONEDA_ORGEN, 
	DECODE(
		mp.moneda,
		'PES',
		1.00000,
		ncta.tipo_de_cambio_fecha_cobro  
	) as TIPO_CAMBIO_MEDIO_PAGO,
	'PES' as MONEDA_MEDIO_PAGO,  --Moneda del medio pago
	fcal.id_doc_ctas_cob_padre as ID_CUENTA_COB_PADRE,
	fcal.subtipo as subtipo_de_documento
  from shv_cob_descobro cob,
	shv_cob_operacion op,
	shv_wf_workflow_estado we,
  	shv_cob_transaccion tran,
	shv_cob_factura fac,
	shv_cob_factura_calipso fcal,
	shv_cob_med_pago mp,
	shv_cob_med_pag_cta ncta
   where cob.id_workflow=we.id_workflow
	and cob.id_operacion=op.id_operacion
	and op.id_operacion=tran.id_operacion
	and tran.id_transaccion=fac.id_transaccion
	and fac.id_factura= fcal.id_factura
	and tran.id_transaccion=mp.id_transaccion 
	and  mp.id_medio_pago = ncta.id_medio_pago
	and tran.numero_transaccion_padre IS NOT NULL
	AND we.estado             = 'DES_DESCOBRADO'
	and  mp.estado <> 'NUEVO'
	and fac.estado <> 'NUEVO' --and fcal.moneda ='PES'
	group by fac.tipo_pago,
		fac.fecha_vencimiento,
		fac.fecha_valor,
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
		TO_DATE(to_char(we.fecha_modificacion, 'dd.mm.yyyy'), 'dd.mm.yyyy'), 
		fcal.TIPO_DE_CAMBIO_FECHA_EMISION,
		ncta.importe_aplic_fec_emis_mon_ori,
		fcal.id_doc_ctas_cob_padre,
		fcal.subtipo,
		mp.moneda,
		fcal.IMPORTE_APLIC_FEC_EMIS_MON_ORI,
		ncta.tipo_de_cambio_fecha_cobro
 );
Exit;