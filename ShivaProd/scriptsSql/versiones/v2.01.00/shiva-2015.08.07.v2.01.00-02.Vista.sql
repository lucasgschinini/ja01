CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_HISTORIAL (ID_COBRO, ID_COBRO_PADRE, ID_OPERACION, ESTADO, SUBESTADO, FECHA_MODIFICACION, USUARIO_MODIFICACION, OBSERVACION, MENSAJE_ESTADO, NUMERO_TRANSACCION, TIPO_COMPROBANTE, CLASE_COMPROBANTE, SUCURSAL_COMPROBANTE, NUMERO_COMPROBANTE, IMPORTE_COBRAR, FECHA_VALOR, REFERENCIA, ACUERDO_TRASLADO_CARGO)
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
    WHERE swwe.estado != 'COB_NO_DISPONIBLE'
    UNION ALL
    SELECT sweh.id_workflow,
      sweh.estado,
      NULL AS subestado,
      sweh.fecha_modificacion,
      sweh.usuario_modificacion,
      DBMS_LOB.substr(sweh.datos_modificados,1,4000) AS observacion
    FROM shv_wf_workflow_estado_hist sweh
    WHERE sweh.estado != 'COB_NO_DISPONIBLE'
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
    AND swwm.id_marca != 'SIMULACION_ONLINE_FINALIZADA_CON_EXITO'
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
    AND swmh.id_marca != 'SIMULACION_ONLINE_FINALIZADA_CON_EXITO'
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
  
/
Exit;