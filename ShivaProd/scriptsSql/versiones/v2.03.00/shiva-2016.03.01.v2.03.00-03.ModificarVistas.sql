CREATE OR REPLACE FORCE VIEW "SHV_SOP_OPER_MASIVA_HISTORIAL" ("ID_OPERACION_MASIVA", "ESTADO", "USUARIO_MODIFICACION", "FECHA_MODIFICACION", "OBSERVACIONES")
AS
  (SELECT oper_masiva.ID_OPERACION_MASIVA,
    wf_estado.ESTADO,
    wf_estado.USUARIO_MODIFICACION,
    TO_CHAR(wf_estado.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
    NULL                                                           AS observaciones
  FROM SHV_WF_WORKFLOW_ESTADO wf_estado
  INNER JOIN SHV_MAS_OPER_MASIVA oper_masiva
  ON (wf_estado.ID_WORKFLOW = oper_masiva.ID_WORKFLOW)
  where wf_estado.estado <> 'MAS_CONFIGURACION'
  UNION
  SELECT oper_masiva.ID_OPERACION_MASIVA,
    wf_estado_hist.ESTADO,
    wf_estado_hist.USUARIO_MODIFICACION,
    TO_CHAR(wf_estado_hist.fecha_modificacion, 'DD/MM/YYYY HH24:MI:SS') AS fecha_modificacion,
    NULL                                                           AS observaciones
  FROM shv_wf_workflow_estado_hist wf_estado_hist
  INNER JOIN SHV_MAS_OPER_MASIVA oper_masiva
  ON (wf_estado_hist.ID_WORKFLOW = oper_masiva.ID_WORKFLOW)
  where wf_estado_hist.estado <> 'MAS_CONFIGURACION'
);

Exit;