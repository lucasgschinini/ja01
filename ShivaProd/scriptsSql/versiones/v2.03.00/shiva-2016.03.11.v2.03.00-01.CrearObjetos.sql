-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de indices para mejora de performance
-----------------------------------------------------------------------------------------------------------------------------
  -- tabla de cobros x id_operacion
  create index IDX_COBRO_OPERACION ON SHV_COB_COBRO (ID_OPERACION) SHIVAINDEXTS;
  -- tabla de documentos x id_operacion
  create index IDX_COB_DOCUMENTO_OPERACION ON SHV_COB_DOCUMENTO (ID_OPERACION) SHIVAINDEXTS;
  -- tabla de transacciones x id_operacion
  create index IDX_COB_TRANSACCION_OPERACION ON SHV_COB_TRANSACCION (ID_OPERACION) SHIVAINDEXTS;
  -- tabla de factura x id_transaccion
  create index IDX_COB_FACTURA_TRANSACCION ON SHV_COB_FACTURA (ID_TRANSACCION) SHIVAINDEXTS;
  -- tabla de medio de pago x id_transaccion
  create index IDX_COB_MED_PAGO_TRANSACCION ON SHV_COB_MED_PAGO (ID_TRANSACCION) SHIVAINDEXTS;
  -- tabla de clientes de cobro x cobro y cliente legado
  create index IDX_COB_ED_CLIEN_COBRO_CLIENTE ON SHV_COB_ED_CLIENTE (ID_COBRO, ID_CLIENTE_LEGADO) SHIVAINDEXTS;
  -- tabla de workflow estado x id_workflow y estado
  create index IDX_WF_ESTADO_WORKFLOW_ESTADO ON SHV_WF_WORKFLOW_ESTADO(ID_WORKFLOW, ESTADO) SHIVAINDEXTS;
--
Exit;