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
  -- y tampoco hijos que no serían padres de nadie
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
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION tran
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
	and tran.id_operacion = op.id_operacion
    )
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  AND DOC.ID_CLIENTE_LEGADO = CLI.ID_CLIENTE_LEGADO
  and tran.numero_transaccion_padre is null
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Cobro) con hijos por diferencia de cambio
  -----------------------------------------------------------------
  SELECT WE.FECHA_MODIFICACION AS FECHAPROCESAMIENTO,
    LPAD(OP.ID_OPERACION, 7,'0')
    ||'.'
    ||LPAD(TRAN.NUMERO_TRANSACCION_PADRE,5,'0') AS IDTRANSACCION,
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
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  AND DOC.ID_CLIENTE_LEGADO = CLI.ID_CLIENTE_LEGADO
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
  -- y tampoco hijos que no serían padres de nadie
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
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION tran
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
	and tran.id_operacion = op.id_operacion
    )
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND CLI.ID_CLIENTE_LEGADO = FAC.ID_CLIENTE_LEGADO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  AND doc.id_cliente_legado = cli.id_cliente_legado
  and tran.numero_transaccion_padre is null
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Facturas Calipso (Descobro) con hijos por diferencia de cambio
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
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =FAC.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND FAC.ID_FACTURA        =FCAL.ID_FACTURA
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
  -- Medio de Pago Nota de Credito Calipso (Cobro) sin hijos
  -- y tampoco hijos que no serían padres de nadie
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
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION tran
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
	and tran.id_operacion = op.id_operacion
    )
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = COB.ID_COBRO
  AND WE.ESTADO            IN ('COB_COBRADO', 'COB_CONFIRMADO_CON_ERROR')
  AND DOC.TIPO_OPERACION    ='COBRO'
  AND DOC.TIPO_DOCUMENTO    ='RECIBO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  and tran.numero_transaccion_padre is null
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Cobro) con hijos 
  -- por diferencia de cambio
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
  WHERE COB.ID_WORKFLOW     =WE.ID_WORKFLOW
  AND COB.ID_OPERACION      =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
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
  -- Medio de Pago Nota de Credito Calipso (Descobro) sin hijos
  -- y tampoco hijos que no serían padres de nadie
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
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION NOT IN
    (SELECT NUMERO_TRANSACCION_PADRE
    FROM SHV_COB_TRANSACCION tran
    WHERE NUMERO_TRANSACCION_PADRE IS NOT NULL
	and tran.id_operacion = op.id_operacion
    )
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
  and tran.numero_transaccion_padre is null
  -----------------------------------------------------------------
  UNION
  -----------------------------------------------------------------
  -- Medio de Pago Nota de Credito Calipso (Descobro) con hijos 
  -- por diferencia de cambio
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
  WHERE DESCOB.ID_WORKFLOW  =WE.ID_WORKFLOW
  AND DESCOB.ID_OPERACION   =OP.ID_OPERACION
  AND OP.ID_OPERACION       =DOC.ID_OPERACION
  AND OP.ID_OPERACION       =TRAN.ID_OPERACION
  AND TRAN.ID_TRANSACCION   =MP.ID_TRANSACCION
  AND TRAN.NUMERO_TRANSACCION_PADRE IS NOT NULL
  AND NCCLP.ID_MEDIO_PAGO   = MP.ID_MEDIO_PAGO
  AND CLI.ID_COBRO          = DESCOB.ID_COBRO
  AND WE.ESTADO             ='DES_DESCOBRADO'
  AND DOC.TIPO_OPERACION    ='DESCOBRO'
  AND DOC.TIPO_DOCUMENTO    ='NOTA_REEMBOLSO'
  AND DOC.ESTADO            ='PROCESADO'
  AND scf.id_transaccion    = tran.id_transaccion
  AND doc.id_cliente_legado = scf.id_cliente_legado
  AND cli.id_cliente_legado = scf.id_cliente_legado
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

Exit;