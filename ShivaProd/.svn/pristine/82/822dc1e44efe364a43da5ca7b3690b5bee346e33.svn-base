CREATE OR REPLACE FORCE VIEW SHV_SOP_SUBDIARIO (FECHAPROCESAMIENTO, IDTRANSACCION, NUMERORECIBO, TIPOOPERACION, TIPOCOMPROBANTE, DOCUMENTOLEGAL, CLIENTELEGADO, RAZONSOCIAL, MONEDA, IMPORTE, IMPORTE_APLICADO_EN_PESOS, IMPORTE_MONEDA_ORIGEN, FECHAVALOR, IDCAJA, ID_FACTURA, ESTADO_FACTURA, ID_MEDIO_PAGO, ESTADO_MEDIO_PAGO, FECHA_MEDIO_PAGO, ID_TRATAMIENTO_DIFERENCIA, ESTADO_TRATAMIENTO_DIFERENCIA)
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
    DECODE(FAC.TIPO_COMPROBANTE, 'DUC', FMIC.ID_REFERENCIA_FACTURA, FAC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(FAC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(FAC.NUMERO_COMPROBANTE,8,'0') ) AS DOCUMENTOLEGAL,
    FAC.ID_CLIENTE_LEGADO                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    FAC.IMPORTE_COBRAR                      AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    FAC.FECHA_VALOR                         AS FECHAVALOR,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
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
  AND doc.id_cliente_legado = cli.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Cobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW            =WE.ID_WORKFLOW
  AND COB.ID_OPERACION             =OP.ID_OPERACION
  AND OP.ID_OPERACION              =DOC.ID_OPERACION
  AND OP.ID_OPERACION              =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION          =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND FAC.ID_FACTURA                 =FCAL.ID_FACTURA
  AND CLI.ID_COBRO                   = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO          = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                     IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION             ='COBRO'
  AND DOC.TIPO_DOCUMENTO             ='RECIBO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND DOC.ID_CLIENTE_LEGADO          = CLI.ID_CLIENTE_LEGADO
  AND tran.numero_transaccion_padre IS NULL
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                           AS NUMERORECIBO,
    OP.TIPO_OPERACION                           AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                        AS TIPOCOMPROBANTE,
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
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND COB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                =DOC.ID_OPERACION
  AND OP.ID_OPERACION                =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION            =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND FAC.ID_FACTURA                 =FCAL.ID_FACTURA
  AND CLI.ID_COBRO                   = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO          = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                     IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION             ='COBRO'
  AND DOC.TIPO_DOCUMENTO             ='RECIBO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND DOC.ID_CLIENTE_LEGADO          = CLI.ID_CLIENTE_LEGADO
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
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
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
  AND doc.id_cliente_legado = cli.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Descobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW         =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION          =OP.ID_OPERACION
  AND OP.ID_OPERACION              =DOC.ID_OPERACION
  AND OP.ID_OPERACION              =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION          =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND FAC.ID_FACTURA                 =FCAL.ID_FACTURA
  AND CLI.ID_COBRO                   = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO          = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                      ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION             ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO             ='NOTA_REEMBOLSO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND doc.id_cliente_legado          = cli.id_cliente_legado
  AND tran.numero_transaccion_padre IS NULL
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Descobro) con hijos por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                           AS NUMERORECIBO,
    OP.TIPO_OPERACION                           AS TIPOOPERACION,
    FAC.TIPO_COMPROBANTE                        AS TIPOCOMPROBANTE,
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
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    fac.id_factura AS id_factura,
    fac.estado     AS estado_factura,
    NULL           AS id_medio_pago,
    NULL           AS estado_medio_pago,
    NULL           AS fecha_medio_pago,
    NULL           AS id_tratamiento_diferencia,
    NULL           AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_FACTURA FAC,
    SHV_COB_FACTURA_CALIPSO FCAL,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW           =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION            =OP.ID_OPERACION
  AND OP.ID_OPERACION                =DOC.ID_OPERACION
  AND OP.ID_OPERACION                =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION            =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND FAC.ID_FACTURA                 =FCAL.ID_FACTURA
  AND CLI.ID_COBRO                   = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO          = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO                      ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION             ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO             ='NOTA_REEMBOLSO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND doc.id_cliente_legado          = cli.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Cobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_PES    AS IMPORTE_APLICADO_EN_PESOS,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_ORI    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE COB.ID_WORKFLOW            =WE.ID_WORKFLOW
  AND COB.ID_OPERACION             =OP.ID_OPERACION
  AND OP.ID_OPERACION              =DOC.ID_OPERACION
  AND OP.ID_OPERACION              =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION          =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND NCCLP.ID_MEDIO_PAGO            = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO                   = COB.ID_COBRO
  AND WE.ESTADO                     IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION             ='COBRO'
  AND DOC.TIPO_DOCUMENTO             ='RECIBO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND scf.id_transaccion             = tran.id_transaccion
  AND doc.id_cliente_legado          = scf.id_cliente_legado
  AND cli.id_cliente_legado          = scf.id_cliente_legado
  AND tran.numero_transaccion_padre IS NULL
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Cobro) con hijos
  -- por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                           AS NUMERORECIBO,
    OP.TIPO_OPERACION                           AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                      AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_PES    AS IMPORTE_APLICADO_EN_PESOS,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_ORI    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE COB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND COB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                =DOC.ID_OPERACION
  AND OP.ID_OPERACION                =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION            =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCCLP.ID_MEDIO_PAGO            = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO                   = COB.ID_COBRO
  AND WE.ESTADO                     IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION             ='COBRO'
  AND DOC.TIPO_DOCUMENTO             ='RECIBO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND scf.id_transaccion             = tran.id_transaccion
  AND doc.id_cliente_legado          = scf.id_cliente_legado
  AND cli.id_cliente_legado          = scf.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Descobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_PES    AS IMPORTE_APLICADO_EN_PESOS,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_ORI    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE DESCOB.ID_WORKFLOW         =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION          =OP.ID_OPERACION
  AND OP.ID_OPERACION              =DOC.ID_OPERACION
  AND OP.ID_OPERACION              =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION          =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND NCCLP.ID_MEDIO_PAGO            = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO                   = DESCOB.ID_COBRO
  AND WE.ESTADO                      ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION             ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO             ='NOTA_REEMBOLSO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND scf.id_transaccion             = tran.id_transaccion
  AND doc.id_cliente_legado          = scf.id_cliente_legado
  AND cli.id_cliente_legado          = scf.id_cliente_legado
  AND tran.numero_transaccion_padre IS NULL
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Descobro) con hijos
  -- por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                           AS NUMERORECIBO,
    OP.TIPO_OPERACION                           AS TIPOOPERACION,
    NCCLP.TIPO_COMPROBANTE                      AS TIPOCOMPROBANTE,
    NCCLP.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCCLP.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    NCCLP.MONEDA                            AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_PES    AS IMPORTE_APLICADO_EN_PESOS,
    NCCLP.IMPORTE_APLIC_FEC_EMIS_MON_ORI    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE DESCOB.ID_WORKFLOW           =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION            =OP.ID_OPERACION
  AND OP.ID_OPERACION                =DOC.ID_OPERACION
  AND OP.ID_OPERACION                =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION            =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCCLP.ID_MEDIO_PAGO            = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO                   = DESCOB.ID_COBRO
  AND WE.ESTADO                      ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION             ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO             ='NOTA_REEMBOLSO'
  AND DOC.ESTADO                     ='PROCESADO'
  AND scf.id_transaccion             = tran.id_transaccion
  AND doc.id_cliente_legado          = scf.id_cliente_legado
  AND cli.id_cliente_legado          = scf.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Cobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  AND tran.numero_transaccion_padre IS NULL
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Cobro) con hijos
  -- por diferencia de cambio
  ----------------------------------------------------------------- 
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Descobro) sin hijos
  -- y tampoco hijos que no sera padres de nadie
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
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION TRAN
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
    AND tran.id_operacion           = op.id_operacion
    )
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  AND tran.numero_transaccion_padre IS NULL
  ----------------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Descobro) con hijos
  -- por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    OP.TIPO_OPERACION                     AS TIPOOPERACION,
    NCMIC.TIPO_COMPROBANTE                AS TIPOCOMPROBANTE,
    NCMIC.CLASE_COMPROBANTE
    ||'-'
    || LPAD(NCMIC.SUCURSAL_COMPROBANTE,4,'0')
    ||'-'
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0') AS DOCUMENTOLEGAL,
    scf.id_cliente_legado                   AS CLIENTELEGADO,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    'PES'                                   AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    scf.fecha_valor                         AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_ED_CLIENTE CLI,
    shv_cob_factura scf
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCMIC.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago CTA (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND mp.estado                       != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Cheque Shiva (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Transferenia Shiva (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Cheque diferido Shiva (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Interdeposito Shiva (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Remanente MIC (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
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
  -- Medio de Pago Efectivo Shiva (Envio a Ganancias en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Nota de Credito Calipso (Reintegro en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago CTA Calipso (Reintegro en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION               ='COBRO'
  AND DOC.TIPO_DOCUMENTO               ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                     = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                       IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                       ='PROCESADO'
  AND mp.estado                       != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reintegro en Cobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Cheque Shiva (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Transferencia Shiva (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Cheque Diferido Shiva (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Interdeposito Shiva (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Remanente MIC (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Efectivo Shiva (Reintegro en Cobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Nota de Credito Calipso (Ganancia en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago CTA Calipso (Ganancia en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND mp.estado                       != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Ganancia en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Cheque Shiva (Ganancia en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Transferencia Shiva (Ganancia en, Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Cheque Diferido Shiva (Ganancia en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Interdeposito Shiva (Ganancia en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Remanente MIC (Ganancia en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
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
  -- Medio de Pago Efectivo Shiva (Ganancia en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  -- Medio de Pago Nota de Credito Calipso (Reintegro en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago CTA Calipso (Reintegro en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION               ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO               ='RECIBO'
  AND CLI.ID_COBRO                     = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND DOC.ID_CLIENTE_LEGADO            = MPCTA.ID_CLIENTE_LEGADO
  AND WE.ESTADO                        ='DES_DESCOBRADO'
  AND DOC.ESTADO                       ='PROCESADO'
  AND mp.estado                       != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reintegro en Descobro)
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
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Cheque Shiva (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Transferencia Shiva (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Cheque Diferido Shiva (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Interdeposito Shiva (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Remanente MIC (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Efectivo Shiva (Reintegro en Descobro)
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
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  AND TIPO_TRATAMIENTO_DIFERENCIA NOT IN ('ENVIO_A_GANANCIAS', 'REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
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
  -- Medio de Pago Nota de Credito Calipso (Reint. Credito Prox. Fact en Cobro)
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
    tdif.id_cliente_acuerdo_tras_cargo      AS clientelegado,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = NCCLP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA (Reint. Credito Prox. Fact en Cobro)
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
    tdif.id_cliente_acuerdo_tras_cargo      AS clientelegado,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    TDIF.IMPORTE                            AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPCTA.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  AND mp.estado                        != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reint. Credito Prox. Fact en Cobro)
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
    tdif.id_cliente_acuerdo_tras_cargo      AS clientelegado,
    CLI.RAZON_SOCIAL                        AS RAZONSOCIAL,
    MP.MONEDA                               AS MONEDA,
    MP.IMPORTE                              AS IMPORTE,
    NULL                                    AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                    AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                        AS fechavalor,
    OP.ID_CAJA                              AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                  = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'CHQ'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (2,5)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferenia Shiva (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'TRF'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                 =8
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque diferido Shiva (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'CHD'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (3,6)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'INT'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                 =9
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'REM'                                 AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')   AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
  FROM SHV_COB_COBRO COB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_REMANENTE REM,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                 = MP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Reint. Credito Prox. Fact en Cobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0') AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                     AS NUMERORECIBO,
    'REINTEGRO'                           AS TIPOOPERACION,
    'BOL'                                 AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR              AS DOCUMENTOLEGAL,
    tdif.id_cliente_acuerdo_tras_cargo    AS clientelegado,
    CLI.RAZON_SOCIAL                      AS RAZONSOCIAL,
    MP.MONEDA                             AS MONEDA,
    MP.IMPORTE                            AS IMPORTE,
    NULL                                  AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                  AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                      AS fechavalor,
    OP.ID_CAJA                            AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE COB.ID_WORKFLOW                 =WE.ID_WORKFLOW
  AND COB.ID_OPERACION                  =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (4,7)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='COBRO'
  AND DOC.TIPO_DOCUMENTO                ='NOTA_REEMBOLSO'
  AND CLI.ID_COBRO                      = COB.ID_COBRO
  AND cli.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND doc.id_cliente_legado             = tdif.id_cliente_acuerdo_tras_cargo
  AND WE.ESTADO                        IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.ESTADO                        ='PROCESADO'
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Reint. Credito Prox. Fact en Descobro)
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
    || LPAD(NCCLP.NUMERO_COMPROBANTE,8,'0')                                      AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCCLP.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_CLP NCCLP,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = NCCLP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago CTA Calipso (Reint. Credito Prox. Fact en Descobro)
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
    || LPAD(MPCTA.NUMERO_COMPROBANTE,8,'0')                                      AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    TDIF.IMPORTE                                                                 AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPCTA.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_CTA MPCTA,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPCTA.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  AND mp.estado                        != 'NUEVO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Nota de Credito MIC (Reint. Credito Prox. Fact en Descobro)
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
    || LPAD(NCMIC.NUMERO_COMPROBANTE,8,'0')                                      AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    NCMIC.FECHA_EMISION AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
  FROM SHV_COB_DESCOBRO DESCOB,
    SHV_WF_WORKFLOW_ESTADO WE,
    SHV_COB_OPERACION OP,
    SHV_COB_DOCUMENTO DOC,
    SHV_COB_TRANSACCION TRAN,
    SHV_COB_MED_PAGO MP,
    SHV_COB_MED_PAG_NOTA_CRED_MIC NCMIC,
    SHV_COB_TRATAMIENTO_DIFERENCIA TDIF,
    SHV_COB_ED_CLIENTE CLI
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND MP.ID_MEDIO_PAGO                  = NCMIC.ID_MEDIO_PAGO
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Shiva (Reint. Credito Prox. Fact en Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'CHQ'                                                                        AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR                                                     AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (2,5)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Transferencia Shiva (Reint. Credito Prox. Fact en, Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'TRF'                                                                        AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR                                                     AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                 =8
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Cheque Diferido Shiva (Reint. Credito Prox. Fact en Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'CHD'                                                                        AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR                                                     AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (3,6)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Interdeposito Shiva (Reint. Credito Prox. Fact en Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'INT'                                                                        AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR                                                     AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                 =9
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Remanente MIC (Reint. Credito Prox. Fact en Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'REM'                                                                        AS TIPOCOMPROBANTE,
    LPAD(REM.CUENTA_REMANENTE, 13, '0')                                          AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL             AS id_factura,
    NULL             AS estado_factura,
    mp.id_medio_pago AS id_medio_pago,
    mp.estado        AS estado_medio_pago,
    REM.FECHA_ALTA   AS fecha_medio_pago,
    NULL             AS id_tratamiento_diferencia,
    NULL             AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND REM.ID_MEDIO_PAGO                 = MP.ID_MEDIO_PAGO
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
  ----------------------------------------------------------------------
  UNION
  ----------------------------------------------------------------------
  -- Medio de Pago Efectivo Shiva (Reint. Credito Prox. Fact en Descobro)
  ----------------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION,5,'0')                                        AS IDTRANSACCION,
    DOC.NUMERO_RECIBO                                                            AS NUMERORECIBO,
    'REVERSION_REINTEGRO'                                                        AS TIPOOPERACION,
    'BOL'                                                                        AS TIPOCOMPROBANTE,
    MPSHIVA.REFERENCIA_VALOR                                                     AS DOCUMENTOLEGAL,
    NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo) AS clientelegado,
    CLI.RAZON_SOCIAL                                                             AS RAZONSOCIAL,
    MP.MONEDA                                                                    AS MONEDA,
    MP.IMPORTE                                                                   AS IMPORTE,
    NULL                                                                         AS IMPORTE_APLICADO_EN_PESOS,
    NULL                                                                         AS IMPORTE_MONEDA_ORIGEN,
    tdif.fecha_valor                                                             AS fechavalor,
    OP.ID_CAJA                                                                   AS IDCAJA,
    --
    -- Datos para uso interno
    --
    NULL                AS id_factura,
    NULL                AS estado_factura,
    mp.id_medio_pago    AS id_medio_pago,
    mp.estado           AS estado_medio_pago,
    MPSHIVA.FECHA_VALOR AS fecha_medio_pago,
    NULL                AS id_tratamiento_diferencia,
    NULL                AS estado_tratamiento_diferencia
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
  WHERE DESCOB.ID_WORKFLOW              =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION               =OP.ID_OPERACION
  AND OP.ID_OPERACION                   =DOC.ID_OPERACION
  AND OP.ID_OPERACION                   =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION               =MP.ID_TRANSACCION
  AND TRAN.ID_TRANSACCION               =TDIF.ID_TRANSACCION
  AND MP.ID_MEDIO_PAGO                  = MPSHIVA.ID_MEDIO_PAGO
  AND MPSHIVA.ID_VALOR                  = VAL.ID_VALOR
  AND VAL.ID_TIPO_VALOR                IN (4,7)
  AND TDIF.TIPO_TRATAMIENTO_DIFERENCIA IN ('REINTEGRO_CRED_PROX_FAC_MIC', 'REINTEGRO_CRED_PROX_FAC_CLP')
  AND DOC.TIPO_OPERACION                ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO                ='RECIBO'
  AND CLI.ID_COBRO                      = DESCOB.ID_COBRO
  AND cli.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND doc.id_cliente_legado             = NVL(tdif.id_cliente_acuerdo_contracargo, tdif.id_cliente_acuerdo_tras_cargo)
  AND WE.ESTADO                         ='DES_DESCOBRADO'
  AND DOC.ESTADO                        ='PROCESADO'
    ----------------------------------------------------------------------
  );
  
  
  
  CREATE OR REPLACE FORCE VIEW SHV_SOP_DESCOBROS_GRILLA_TRANS (NUMERO_TRANSACCION_FORMATEADO, ESTADO_TRANSACCION, SISTEMA_ORIGEN_DOCUMENTO, ID_CLIENTE_LEGADO_DOCUMENTO, TIPO_COMPROBANTE, SUBTIPO_DOCUMENTO, NUMERO_DOCUMENTO, NUMERO_REFERENCIA_MIC, FECHA_VENCIMIENTO, FECHA_SEGUNDO_VENCIMIENTO, MONEDA, FECHA_COBRO, IMPORTE_A_COBRAR, IMPORTE_ORIGINAL, SALDO_ACTUAL, TIPO_DE_CAMBIO_FECHA_COBRO, TIPO_DE_CAMBIO_FECHA_EMISION, IMPORTE_APLIC_FEC_EMIS_MON_ORI, MONTO_ACUMULADO_SIMULACION_DEB, FECHA_ACUMULADO_SIMULACION_DEB, ORIGEN_DOCUMENTO, SISTEMA_ORIGEN_MEDIO_PAGO, ID_CLIENTE_LEGADO_MEDIO_PAGO, TIPO_MEDIO_PAGO, SUBTIPO_MEDIO_PAGO, REFERENCIA_MEDIO_PAGO, FECHA_MEDIO_PAGO, MONEDA_MEDIO_PAGO, IMPORTE_MEDIO_PAGO, TIPO_CAMBIO_FECHA_COBRO_MP, TIPO_CAMBIO_FECHA_EMISION_MP, IMPORTE_APL_FEC_EMI_MON_ORI_MP, MONTO_ACUMULADO_SIMULACION_CRE, TIPO_PAGO, QUE_HACER_CON_INTERESES, INTERESES_GENERADOS, CHECK_TRASLADAR_INTERESES
  , COBRADOR_INTERESES_TRASLADADOS, CHECK_COBRAR_SEGUNDO_VENCIMIEN, PORCENTAJE_A_BONIFICAR, IMPORTE_A_BONIFICAR, SISTEMA_ACUERDO, ACUERDO_TRASLADO_CARGO, ESTADO_ACUERDO_TRASLADO_CARGO, ID_CLIENTE_ACUERDO_TRAS_CARGO, ESTADO_CARGO_GENERADO, SISTEMA_ACUERDO_CONTRACARGO, ACUERDO_CONTRACARGO, ESTADO_ACUERDO_CONTRACARGO, ID_CLIENTE_ACUERDO_CONTRACARGO, TIPO_MENSAJE_ESTADO_TRANSACCIO, MENSAJE_ESTADO_TRANSACCION, TIPO_MENSAJE_ESTADO_DEBITO, MENSAJE_ESTADO_DEBITO, ESTADO_DEBITO, TIPO_MENSAJE_ESTADO_CREDITO, MENSAJE_ESTADO_CREDITO, ESTADO_CREDITO, ID_DESCOBRO, ID_OPERACION, ID_TRANSACCION, NUMERO_TRANSACCION, NUMERO_TRANSACCION_PADRE, NUMERO_TRANSACCION_PADRE_FORMA, ID_FACTURA, ID_MEDIO_PAGO, ID_TRATAMIENTO_DIFERENCIA, ID_TRANSACCION_PADRE)
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
    NULL AS moneda_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS TIPO_CAMBIO_FECHA_COBRO_MP,
    NULL AS TIPO_CAMBIO_FECHA_EMISION_MP,
    NULL AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
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
    sscdf.sistema_origen                 AS sistema_acuerdo,
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
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    sscdf.id_factura,
    0    AS id_medio_pago,
    NULL AS id_tratamiento_diferencia,
    NULL AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_sop_descobros_det_factura sscdf
  WHERE sscdf.id_transaccion = sct.id_transaccion
  AND scd.id_operacion       = sct.id_operacion
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
    sscdmp.sistema_origen                 AS sistema_origen_medio_pago,
    sscdmp.id_cliente_legado              AS id_cliente_legado_medio_pago,
    sscdmp.descripcion_medio_pago         AS tipo_medio_pago,
    sscdmp.subtipo_medio_pago             AS subtipo_medio_pago,
    sscdmp.referencia                     AS referencia_medio_pago,
    sscdmp.fecha_valor                    AS fecha_medio_pago,
    sscdmp.moneda                         AS moneda_medio_pago,
    sscdmp.importe                        AS importe_medio_pago,
    sscdmp.TIPO_DE_CAMBIO_FECHA_COBRO     AS TIPO_CAMBIO_FECHA_COBRO_MP,
    sscdmp.TIPO_DE_CAMBIO_FECHA_EMISION   AS TIPO_CAMBIO_FECHA_EMISION_MP,
    sscdmp.IMPORTE_APLIC_FEC_EMIS_MON_ORI AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
    sscdmp.monto_acumulado_simulacion     AS monto_acumulado_simulacion_cre,
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
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                         AS id_factura,
    sscdmp.id_medio_pago                         AS id_medio_pago,
    NULL                                         AS id_tratamiento_diferencia,
    NULL                                         AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_sop_descobros_det_med_pag sscdmp
  WHERE sscdmp.id_transaccion = sct.id_transaccion
  AND scd.id_operacion        = sct.id_operacion
  AND sscdmp.estado          != 'NUEVO'
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
    'PES'                                                                       AS moneda,
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
    NULL AS moneda_medio_pago,
    NULL AS importe_medio_pago,
    NULL AS TIPO_CAMBIO_FECHA_COBRO_MP,
    NULL AS TIPO_CAMBIO_FECHA_EMISION_MP,
    NULL AS IMPORTE_APL_FEC_EMI_MON_ORI_MP,
    NULL AS monto_acumulado_simulacion_cre,
    -- Tipo de pago
    NULL AS tipo_pago,
    -- Gestion de intereses
    sctd.que_hacer_con_intereses AS que_hacer_con_intereses,
    CASE
      WHEN sctd.cobrador_interes_real_nrg_tras IS NOT NULL
      AND sctd.cobrador_interes_real_nrg_tras  <> 0
      THEN sctd.cobrador_interes_real_nrg_tras
      ELSE sctd.cobrador_intereses_generados
    END cobrador_intereses_generados,
    sctd.check_trasladar_intereses      AS check_trasladar_intereses,
    sctd.cobrador_intereses_trasladados AS cobrador_intereses_trasladados,
    NULL                                AS check_cobrar_segundo_vencimien,
    sctd.porcentaje_bonif_intereses     AS porcentaje_a_bonificar,
    sctd.importe_bonif_intereses        AS importe_a_bonificar,
    sctd.sistema_origen                 AS sistema_acuerdo,
    sctd.acuerdo_traslado_cargo         AS acuerdo_traslado_cargo,
    sctd.estado_acuerdo_traslado_cargo  AS estado_acuerdo_traslado_cargo,
    sctd.id_cliente_acuerdo_tras_cargo  AS id_cliente_acuerdo_tras_cargo,
    sctd.estado_cargo_generado          AS estado_cargo_generado,
    sctd.sistema_acuerdo_contracargo    AS sistema_acuerdo_contracargo,
    sctd.acuerdo_contracargo            AS acuerdo_contracargo,
    sctd.estado_acuerdo_contracargo     AS estado_acuerdo_contracargo,
    sctd.id_cliente_acuerdo_contracargo AS id_cliente_acuerdo_contracargo,
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
    sct.numero_transaccion,
    sct.numero_transaccion_padre,
    lpad(sct.id_operacion, 7, '0')
    ||'.'
    ||lpad(sct.numero_transaccion_padre, 5, '0') AS numero_transaccion_padre_forma,
    NULL                                         AS id_factura,
    NULL                                         AS id_medio_pago,
    sctd.id_tratamiento_diferencia               AS id_tratamiento_diferencia,
    NULL                                         AS id_transaccion_padre
  FROM shv_cob_descobro scd,
    shv_cob_transaccion sct,
    shv_cob_tratamiento_diferencia sctd
  WHERE sctd.id_transaccion = sct.id_transaccion
  AND scd.id_operacion      = sct.id_operacion
  );

Exit;