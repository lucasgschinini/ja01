---------------------------------------------------------------------------------------------------
-- Elimino las FK de la tabla de transacciones para poder grabar mensajes en la simulacion
-- cuando la transaccion aún no existe
---------------------------------------------------------------------------------------------------

Alter table SHV_COB_TRANSACCION_MSJ_DET drop constraint SHV_COB_TRANSACCION_MSJ_D_FK1;
Alter table SHV_COB_TRANSACCION_MSJ_DET drop constraint SHV_COB_TRANSACCION_MSJ_D_FK2;

---------------------------------------------------------------------------------------------------
-- Agrego el id_tipo_medio_pago a las tablas de tratamiento de diferencia del online
---------------------------------------------------------------------------------------------------

alter table shv_cob_ed_tratamiento_dif add id_tipo_medio_pago varchar2(2);

---------------------------------------------------------------------------------------------------
-- Agrego el id_tipo_medio_pago a las tablas de tratamiento de diferencia del batch
---------------------------------------------------------------------------------------------------

alter table shv_cob_tratamiento_diferencia add id_tipo_medio_pago varchar2(2);

/
Exit;
