-----------------------------------------------------------------------------------------------------------------------------
-- Actualización de la vista de Subdiario
-----------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FORCE VIEW "SHV_SOP_SUBDIARIO" ("FECHAPROCESAMIENTO", "IDTRANSACCION", "NUMERORECIBO", "TIPOOPERACION", "TIPOCOMPROBANTE", "DOCUMENTOLEGAL", "CLIENTELEGADO", "RAZONSOCIAL", "MONEDA", "IMPORTE", "IMPORTE_APLICADO_EN_PESOS", "IMPORTE_MONEDA_ORIGEN", "FECHAVALOR", "IDCAJA")
AS
  (
  -----------------------------------------------------------------
  -- Facturas MIC (Cobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                  AS TIPOCOMPROBANTE,
    DECODE(FAC.TIPO_COMPROBANTE, 'DUC', FMIC.ID_REFERENCIA_FACTURA,
    FAC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(FAC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(FAC.NUMERO_COMPROBANTE,8,'0')
    ) AS DOCUMENTOLEGAL,
    FAC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    'PES'                                 AS MONEDA,
    FAC.IMPORTE_COBRAR                    AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    FAC.FECHA_VALOR                       AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_MIC FMIC,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW     = WE.ID_WORKFLOW
  AND COB.ID_OPERACION      = OP.ID_OPERACION
  AND OP.ID_OPERACION       = DOC.ID_OPERACION
  AND OP.ID_OPERACION       = TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   = FAC.ID_TRANSACCION
  AND FAC.ID_FACTURA        = FMIC.ID_FACTURA
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Cobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                  AS TIPOCOMPROBANTE,
    FAC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(FAC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(FAC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    FAC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    FCAL.MONEDA                           AS MONEDA,
    FAC.IMPORTE_COBRAR                    AS IMPORTE,
    FCAL.IMPORTE_APLIC_FEC_EMIS_MON_PES   AS IMPORTE_APLICADO_EN_PESOS,
    FCAL.IMPORTE_APLIC_FEC_EMIS_MON_ORI   AS IMPORTE_MONEDA_ORIGEN,
    FAC.FECHA_VALOR                       AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas MIC (Descobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                  AS TIPOCOMPROBANTE,
    FAC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(FAC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(FAC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    FAC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    'PES'                                 AS MONEDA,
    FAC.IMPORTE_COBRAR                    AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    FAC.FECHA_VALOR                       AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_MIC FMIC,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND FAC.ID_FACTURA        =FMIC.ID_FACTURA
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Descobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                  AS TIPOCOMPROBANTE,
    FAC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(FAC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(FAC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    FAC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    FCAL.MONEDA                           AS MONEDA,
    FAC.IMPORTE_COBRAR                    AS IMPORTE,
    FCAL.IMPORTE_APLIC_FEC_EMIS_MON_PES   AS IMPORTE_APLICADO_EN_PESOS,
    FCAL.IMPORTE_APLIC_FEC_EMIS_MON_ORI   AS IMPORTE_MONEDA_ORIGEN,
    FAC.FECHA_VALOR                       AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Cobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = MP.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Descobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = MP.ID_CLIENTE_LEGADO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Cobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = MP.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Descobro)
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = MP.ID_CLIENTE_LEGADO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = NCCLP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    MPCTA.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    MPCTA.CLASE_COMPROBANTE
    ||'-'
    || LPAD(MPCTA.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(MPCTA.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    MPCTA.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    TDIF.IMPORTE                            AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    MPCTA.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPCTA.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                 = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'CHQ'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (2,5)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferenia Shiva (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'TRF'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =8
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque diferido Shiva (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'CHD'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (3,6)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'INT'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =9
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'REM'                                 AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')   AS DOCUMENTOLEGAL,
    REM.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    REM.FECHA_ALTA                        AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_REMANENTE REM,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                = MP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Envio a Ganancias, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'GANANCIA'                            AS TIPOOPERACION,
    'BOL'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (4,7)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = NCCLP.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA Calipso (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    MPCTA.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    MPCTA.CLASE_COMPROBANTE
    ||'-'
    || LPAD(MPCTA.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(MPCTA.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    MPCTA.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    TDIF.IMPORTE                            AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    MPCTA.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPCTA.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                 = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'CHQ'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (2,5)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferencia Shiva (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'TRF'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =8
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Diferido Shiva (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'CHD'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (3,6)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'INT'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =9
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'REM'                                 AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')   AS DOCUMENTOLEGAL,
    REM.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    REM.FECHA_ALTA                        AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_REMANENTE REM,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                = MP.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Reintegro, Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'BOL'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                 =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (4,7)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = NCCLP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA Calipso (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    MPCTA.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    MPCTA.CLASE_COMPROBANTE
    ||'-'
    || LPAD(MPCTA.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(MPCTA.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    MPCTA.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    TDIF.IMPORTE                            AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    MPCTA.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPCTA.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                 = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'CHQ'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (2,5)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferencia Shiva (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'TRF'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =8
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Diferido Shiva (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'CHD'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (3,6)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'INT'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =9
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'REM'                                 AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')   AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    REM.FECHA_ALTA                        AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_REMANENTE REM,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                = MP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Ganancia, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_GANANCIA'                  AS TIPOOPERACION,
    'BOL'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (4,7)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA = 'ENVIO_A_GANANCIAS'
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCCLP.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCCLP.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = NCCLP.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCCLP.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA Calipso (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    MPCTA.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    MPCTA.CLASE_COMPROBANTE
    ||'-'
    || LPAD(MPCTA.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(MPCTA.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    MPCTA.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    TDIF.IMPORTE                            AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    MPCTA.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPCTA.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    NCMIC.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    NCMIC.FECHA_EMISION                     AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                 = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = NCMIC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'CHQ'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (2,5)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferencia Shiva (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'TRF'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =8
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Diferido Shiva (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'CHD'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (3,6)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'INT'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                =9
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'REM'                                 AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')   AS DOCUMENTOLEGAL,
    REM.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    REM.FECHA_ALTA                        AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_REMANENTE REM,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                = MP.ID_MEDIO_PAGO
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = REM.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Reintegro, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                 AS TIPOOPERACION,
    'BOL'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    VAL.ID_CLIENTE_LEGADO                 AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    MPSHIVA.FECHA_VALOR                   AS FECHAVALOR,
    OP.ID_CAJA                            AS IDCAJA
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_SHIVA MPSHIVA,
    SHV_VAL_VALOR VAL,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW             =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION              =OP.ID_OPERACION
  AND OP.ID_OPERACION                  =DOC.ID_OPERACION
  AND OP.ID_OPERACION                  =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION              =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION              =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                 = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                 = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR               IN (4,7)
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = VAL.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  );
  --
  
Exit;