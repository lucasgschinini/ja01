---------------------------------------------------------------------------------------------------
-- Actualizo los id_tipo_medio_pago segun corresponda en as tablas de tratamiento de 
-- diferencia del batch
---------------------------------------------------------------------------------------------------

update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '31' where tipo_tratamiento_diferencia = 'REINTEGRO_CRED_PROX_FAC_CLP';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '32' where tipo_tratamiento_diferencia = 'REINTEGRO_CRED_PROX_FAC_MIC';


Exit;