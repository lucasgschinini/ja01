ALTER TABLE SHV_COB_ED_CREDITO ADD ID_TIPO_MEDIO_PAGO VARCHAR2(2);

ALTER TABLE SHV_COB_ED_OTROS_MEDIO_PAGO RENAME COLUMN TIPO_CREDITO TO ID_TIPO_MEDIO_PAGO;

--u573005, fabio.giaquinta.ruiz, sprint 5, Se modifica el porcentaje de 2 a 3 digitos 
alter table SHV_COB_FACTURA modify PORCENTAJE_BONIF_INTERESES NUMBER(3,0);

alter table SHV_COB_MED_PAG_DEB_PROX_CLP modify PORCENTAJE_BONIF_INTERESES NUMBER(3,0);

alter table SHV_COB_MED_PAG_DEB_PROX_MIC modify PORCENTAJE_BONIF_INTERESES NUMBER(3,0);

alter table SHV_COB_TRATAMIENTO_DIFERENCIA modify PORCENTAJE_BONIF_INTERESES NUMBER(3,0);

  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
  -- Se agrega la columna ID_OPERACION a la tabla SHV_WF_TAREA_COBRO
  --------------------------------------------------------------------------------
  --------------------------------------------------------------------------------
ALTER TABLE SHV_WF_TAREA_COBRO ADD ID_OPERACION NUMBER;

----------------------------------------------------------------------------------
-- Nuevos campos en tabla shv_cob_factura_mic
----------------------------------------------------------------------------------
alter table shv_cob_factura_mic add estado_concepto_terceros varchar2(2 byte);
alter table shv_cob_factura_mic add posibilidad_destransferencia varchar2(2 byte);
alter table shv_cob_factura_mic add importe_terceros_transferidos number(17,4);
alter table shv_cob_factura_mic add saldo_ter_financia_transf number(17,4);
alter table shv_cob_factura_mic add saldo_ter_financia_no_transf number(17,4);
alter table shv_cob_factura_mic add saldo_ter_no_financia_transf number(17,4);