  CREATE OR REPLACE FORCE VIEW SHV_SOP_COBROS_BUSQUEDA (ID_COBRO, ID_COBRO_PADRE, ID_EMPRESA, ID_SEGMENTO, ID_OPERACION, ID_MOTIVO_COBRO, DESC_MOTIVO_COBRO, CLIENTE, ESTADO, ANALISTA, COPROPIETARIO, ID_ANALISTA_TEAM_COMERCIAL, IMPORTE_TOTAL, FECHA_ALTA, FECHA_DERIVACION, FECHA_APROB_SUPER_COB, FECHA_APROB_REFER_COB, FECHA_IMPUTACION, SUB_ESTADO, FECHA_ULTIMO_ESTADO, TIENE_DUC)
                         AS
   SELECT SCEC.ID_COBRO   AS IDCOBRO,
    SCEC.ID_COBRO_PADRE  AS IDCOBROPADRE,
    SCEC.ID_EMPRESA      AS EMPRESA,
    SCEC.ID_SEGMENTO     AS SEGMENTO,
    SCEC.ID_OPERACION    AS IDOPERACION,
    SCEC.ID_MOTIVO_COBRO AS IDMOTIVOCOBRO,
    SPMC.DESCRIPCION     AS DESCMOTIVOCOBRO,
    (SELECT LISTAGG(SUBSTR(CEC.ID_CLIENTE_LEGADO
      || ' '
      || CEC.RAZON_SOCIAL,0,30), '-') WITHIN GROUP(
    ORDER BY CEC.ID_CLIENTE_LEGADO)
    FROM SHV_COB_ED_CLIENTE CEC
    WHERE CEC.ID_COBRO = SCEC.ID_COBRO
    )                     AS CLIENTE,
    SWWE.ESTADO           AS ESTADO,
    SCEC.ID_ANALISTA      AS ANALISTA,
    SCEC.ID_COPROPIETARIO AS COPROPIETARIO,
    (SELECT LISTAGG(TEAM.USER_ANALISTA_COBRANZA_DATOS, '-') WITHIN GROUP(
    ORDER BY TEAM.USER_ANALISTA_COBRANZA_DATOS)
    FROM SHV_COB_ED_CLIENTE CEC2
    JOIN SHV_PARAM_TEAM_COMERCIAL TEAM
    ON (TEAM.NRO_DE_CLIENTE=CEC2.ID_CLIENTE_LEGADO)
    WHERE CEC2.ID_COBRO    = SCEC.ID_COBRO
    )                          AS ANALISTATEAMCOMERCIAL,
    SCEC.IMPORTE_TOTAL         AS IMPORTETOTAL,
    SCEC.FECHA_ALTA            AS FECHAALTA,
    SCEC.FECHA_DERIVACION      AS FECHADERIVACION,
    SCEC.FECHA_APROB_SUPER_COB AS FECHAAPROBSUPERCOB,
    SCEC.FECHA_APROB_REFER_COB AS FECHAAPROBREFERCOB,
    SCEC.FECHA_IMPUTACION      AS FECHAIMPUTACION,
    (SELECT SWWM1.ID_MARCA
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE SWWM1.ID_WORKFLOW_MARCA=
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO
      )
    ) AS SUBESTADO,
    TO_CHAR( NVL (
    (SELECT SWWM1.FECHA_CREACION
    FROM SHV_WF_WORKFLOW_MARCA SWWM1
    WHERE SWWM1.ID_WORKFLOW_MARCA =
      (SELECT MAX (SWWM.ID_WORKFLOW_MARCA)
      FROM SHV_WF_WORKFLOW_MARCA SWWM
      WHERE SWWM.ID_WORKFLOW_ESTADO = SWWE.ID_WORKFLOW_ESTADO
      )
    ), SWWE.FECHA_MODIFICACION ), 'DD/MM/YYYY HH24:MI:SS') AS FECHAULTIMOESTADO ,
    CASE
      WHEN ( SELECT DISTINCT TIPO_COMPROBANTE
        FROM SHV_COB_ED_DEBITO DEB
        WHERE DEB.ID_COBRO   = SCEC.ID_COBRO
        AND TIPO_COMPROBANTE = 'DUC' ) IS NULL
      THEN 'NO'
      ELSE 'SI'
    END AS TIENE_DUC
  FROM SHV_COB_ED_COBRO SCEC
  INNER JOIN SHV_WF_WORKFLOW_ESTADO SWWE
  ON SCEC.ID_WORKFLOW = SWWE.ID_WORKFLOW
  INNER JOIN SHV_PARAM_MOTIVO_COBRO SPMC
  ON SCEC.ID_MOTIVO_COBRO = SPMC.ID_MOTIVO_COBRO;
  
  exit;