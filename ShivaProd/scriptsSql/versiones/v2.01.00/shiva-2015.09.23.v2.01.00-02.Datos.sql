----------------------------------------------------------------------------------------------------------------
-- Actualizaci髇 de SHV_PARAM_PARAMETRO para SIMULACION en cobros
----------------------------------------------------------------------------------------------------------------
INSERT INTO SHV_PARAM_PARAMETRO (CLAVE, VALOR_TEXTO, VALOR_NUMERICO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES ('cobros.simulacion.cantConcurrenciasApropCalipso', null, 2, 'SPRINT 5');

INSERT INTO SHV_PARAM_PARAMETRO (CLAVE, VALOR_TEXTO, VALOR_NUMERICO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES ('cobros.simulacion.cantConcurrenciasApropMic', null, 2, 'SPRINT 5');

INSERT INTO SHV_PARAM_PARAMETRO (CLAVE, VALOR_TEXTO, VALOR_NUMERICO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES ('cobros.simulacion.cantConcurrenciasGenCargoCalipso', null, 2, 'SPRINT 5');

INSERT INTO SHV_PARAM_PARAMETRO (CLAVE, VALOR_TEXTO, VALOR_NUMERICO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES ('cobros.simulacion.cantConcurrenciasGenCargoMic', null, 2, 'SPRINT 5');

INSERT INTO SHV_PARAM_PARAMETRO (CLAVE, VALOR_TEXTO, VALOR_NUMERICO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES ('cobros.simulacion.cantConcurrenciasApropDeimos', null, 0, 'SPRINT 5');

---------------------------------------------------------------------------------------------------
-- Agrego el tipo de medio de pago simple, para poder dar soporte a las tablas del online
---------------------------------------------------------------------------------------------------

Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('37', 'CREDITO PROXIMA FACTURA', 99, 'SI', 'SPRINT5');

INSERT INTO SHV_PARAM_TIPO_MEDIO_PAGO_USO (ID_TIPO_MEDIO_PAGO_USO, ID_TIPO_MEDIO_PAGO, ID_MONEDA, PERMITE_USO_REINTEGRO, PERMITE_USO_DUC, PERMITE_USO_COBRO, ESTADO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES (SEQ_SHV_PARAM_TIP_MED_PAGO_USO.NEXTVAL, '37', 'PES', 'NO', 'NO', 'SI', 'ACTIVO', 'SPRINT 5');

Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('38', 'DEBITO PROXIMA FACTURA', 99, 'SI', 'SPRINT5');

INSERT INTO SHV_PARAM_TIPO_MEDIO_PAGO_USO (ID_TIPO_MEDIO_PAGO_USO, ID_TIPO_MEDIO_PAGO, ID_MONEDA, PERMITE_USO_REINTEGRO, PERMITE_USO_DUC, PERMITE_USO_COBRO, ESTADO, AUD_REQUERIMIENTO_ORIGEN) 
VALUES (SEQ_SHV_PARAM_TIP_MED_PAGO_USO.NEXTVAL, '38', 'PES', 'NO', 'NO', 'SI', 'ACTIVO', 'SPRINT 5');

---------------------------------------------------------------------------------------------------
-- Actualizo los id_tipo_medio_pago segun corresponda en as tablas de tratamiento de 
-- diferencia del online
---------------------------------------------------------------------------------------------------

update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '14' where tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '15' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_GIRO';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '26' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_CHEQUE';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '27' where tipo_tratamiento_diferencia = 'REINTEGRO_CREDITO_RED_INTEL';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '30' where tipo_tratamiento_diferencia = 'REINTEGRO_TRANSFERENCIA_BAN';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '37' where tipo_tratamiento_diferencia = 'REINTEGRO_CRED_PROX_FAC';
update shv_cob_ed_tratamiento_dif set id_tipo_medio_pago = '38' where tipo_tratamiento_diferencia = 'DEBITO_PROX_FAC';

---------------------------------------------------------------------------------------------------
-- Actualizo los id_tipo_medio_pago segun corresponda en as tablas de tratamiento de 
-- diferencia del batch
---------------------------------------------------------------------------------------------------

update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '14' where tipo_tratamiento_diferencia = 'ENVIO_A_GANANCIAS';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '15' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_GIRO';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '26' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_CHEQUE';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '27' where tipo_tratamiento_diferencia = 'REINTEGRO_CREDITO_RED_INTEL';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '28' where tipo_tratamiento_diferencia = 'DEBITO_PROX_FAC_CLP';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '29' where tipo_tratamiento_diferencia = 'DEBITO_PROX_FAC_MIC';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '30' where tipo_tratamiento_diferencia = 'REINTEGRO_TRANSFERENCIA_BAN';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '31' where tipo_tratamiento_diferencia = 'REINTEGRO_CRED_PROX_FAC_CLP';
update shv_cob_tratamiento_diferencia set id_tipo_medio_pago = '32' where tipo_tratamiento_diferencia = 'REINTEGRO_CRED_PROX_FAC_MIC';

---------------------------------------------------------------------------------------------------
-- Actualizo el "REINTEGRO_POR_GIRO" a "REINTEGRO_PAGO_CUENTA_TERCEROS" en las tablas de 
-- tratamiento de diferencia del online
---------------------------------------------------------------------------------------------------

update shv_cob_ed_tratamiento_dif set tipo_tratamiento_diferencia = 'REINTEGRO_PAGO_CUENTA_TERCEROS' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_GIRO';

---------------------------------------------------------------------------------------------------
-- Actualizo el "REINTEGRO_POR_GIRO" a "REINTEGRO_PAGO_CUENTA_TERCEROS" en las tablas de 
-- tratamiento de diferencia del batch
---------------------------------------------------------------------------------------------------

update shv_cob_tratamiento_diferencia set tipo_tratamiento_diferencia = 'REINTEGRO_PAGO_CUENTA_TERCEROS' where tipo_tratamiento_diferencia = 'REINTEGRO_POR_GIRO';


insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('014','Apropiaci贸n del valor','SPRINT 6');

update SHV_PARAM_TIPO_ORIGEN_CONTABLE set descripcion='Confirmaci贸n de aplicaci贸n MP sobre reintegro valor SHIVA' where ID_TIPO_ORIGEN_CONTABLE='017';
update SHV_PARAM_TIPO_ORIGEN_CONTABLE set descripcion='Desapropiaci贸n del valor' where ID_TIPO_ORIGEN_CONTABLE='018';
update SHV_PARAM_TIPO_ORIGEN_CONTABLE set descripcion='Reversi贸n de aplicaci贸n MP sobre reintegro valor' where ID_TIPO_ORIGEN_CONTABLE='019';
commit;

/
Exit;
