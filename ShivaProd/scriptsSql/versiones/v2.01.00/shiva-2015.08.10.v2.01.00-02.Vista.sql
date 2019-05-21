 CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_BUSQUEDA (ID_COBRO, ID_COBRO_PADRE, ID_EMPRESA, ID_SEGMENTO, ID_OPERACION, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, ANALISTA, COPROPIETARIO, ID_REVERSION, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_APROB_SUPER_COB, FECHA_APROB_REFER_COB, FECHA_IMPUTACION, SUB_ESTADO, FECHA_ULTIMO_ESTADO)
   						 AS
                         
 	SELECT SCEC.ID_COBRO AS idCobro,
    SCEC.id_cobro_padre  AS idCobroPadre,
    SCEC.id_empresa      AS empresa,
    SCEC.id_segmento     AS segmento,
    SCEC.ID_OPERACION    AS idOperacion,
    SCEC.ID_MOTIVO_COBRO AS idMotivoCobro,
    SPMC.DESCRIPCION     AS descMotivoCobro,
    (SELECT SUBSTR(CEC.ID_CLIENTE_LEGADO
      || ' '
      || CEC.RAZON_SOCIAL,0,15)
    FROM SHV_COB_ED_CLIENTE CEC
    WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    AND rownum         < 2
    )                          AS cliente,
    SWWE.estado                AS estado,
    SCEC.ID_ANALISTA           AS analista,
    SCEC.ID_COPROPIETARIO      AS copropietario,
    NULL                       AS idReversion,
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
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO;
  
/
Exit;