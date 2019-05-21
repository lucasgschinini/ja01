
-- SHV_PARAM_MOTIVO_COBRO
INSERT INTO shv_param_motivo_cobro (ID_MOTIVO_COBRO, DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA)
VALUES (1,'Financiación de deuda','FASE 2 SPRING 1','SI');
INSERT INTO shv_param_motivo_cobro (ID_MOTIVO_COBRO, DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA)
VALUES (2,'Reintegro','FASE 2 SPRING 1','SI');
INSERT INTO shv_param_motivo_cobro (ID_MOTIVO_COBRO, DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA)
VALUES (3,'Depuración de crédito','FASE 2 SPRING 1','SI');
Insert into shv_param_motivo_cobro (ID_MOTIVO_COBRO,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA) 
values (4,'Cobranza Regular','FASE 2 SPRINT 4','NO');
Insert into shv_param_motivo_cobro (ID_MOTIVO_COBRO,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA) 
values (5,'Depuración de Créditos','FASE 2 SPRINT 4','NO');
Insert into shv_param_motivo_cobro (ID_MOTIVO_COBRO,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA) 
values (6,'Financiación de Deuda','FASE 2 SPRINT 4','NO');
Insert into shv_param_motivo_cobro (ID_MOTIVO_COBRO,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA) 
values (7,'Postbaja / CyQ','FASE 2 SPRINT 4','NO');
Insert into shv_param_motivo_cobro (ID_MOTIVO_COBRO,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN,USO_OPERACION_MASIVA) 
values (8,'Reclamos y Reintegros','FASE 2 SPRINT 4','NO');

-- Sentencias de insert de actualizaciones de menu y seguridad
-- Creacion del perfil 'Analista de Cobranza' y opcion de menu 'Configuración de Cobro'
-------------------------------------------------------------------------------------------------------------------

-- Creacion de perfil 'Analista de Cobranza'
Insert into SHV_SEG_PERFIL (ID_PERFIL,NOMBRE,ID_PERFIL_APLICATIVO,ID_EMPRESA,ID_SEGMENTO) values ('26','Analista Cobranza - Telecom Argentina - Grandes Clientes','10','TA','OGC');
Insert into SHV_SEG_PERFIL (ID_PERFIL,NOMBRE,ID_PERFIL_APLICATIVO,ID_EMPRESA,ID_SEGMENTO) values ('27','Analista Cobranza - Telecom Argentina - Whole Sale','10','TA','OYP');
Insert into SHV_SEG_PERFIL (ID_PERFIL,NOMBRE,ID_PERFIL_APLICATIVO,ID_EMPRESA,ID_SEGMENTO) values ('28','Analista Cobranza - Telecom Argentina - PyME','10','TA','OGE');
Insert into SHV_SEG_PERFIL (ID_PERFIL,NOMBRE,ID_PERFIL_APLICATIVO,ID_EMPRESA,ID_SEGMENTO) values ('29','Analista Cobranza - Telecom Argentina - Telefonia Publica','10','TA','OTP');
Insert into SHV_SEG_PERFIL (ID_PERFIL,NOMBRE,ID_PERFIL_APLICATIVO,ID_EMPRESA,ID_SEGMENTO) values ('30','Analista Cobranza - Telecom Argentina - Residencial','10','TA','OCO');

-- Creacion de la opcion de menu 'Cobros / Alta de Cobros'
Insert into SHV_MNU_MENU (ID_MENU,DESCRIPCION,URL_ACCESO,ORDEN,MENU_ID_MENU) values ('22','Cobros',null,'8','0');
Insert into SHV_MNU_MENU (ID_MENU,DESCRIPCION,URL_ACCESO,ORDEN,MENU_ID_MENU) values ('23','Configuración de Cobro','cobros-configuracion','1','22');

-- Asocio el menu con el perfil de Analista de Cobranza (23) para los diferentes segmentos (26 a 30)
Insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values ('115','26','23');
Insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values ('116','27','23');
Insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values ('117','28','23');
Insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values ('118','29','23');
Insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values ('119','30','23');

-- Asocio el controlador de Cobros al perfil aplicativo de Analista de Cobranza (10) 
-- Insert into shv_seg_perfil_aplicativo_ctrl (ID_CONTROLADOR,ID_PERFIL_APLICATIVO,CONTROLADOR) values ('28','10','CobroOnlineController');
-- Eso no va

--Parametros de medio de pago
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) values ('30','NOTA DE CREDITO PENDIENTE DE EMISION','22','NO','SPRINT4');
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) values ('31','COMPENSACION','23','NO','SPRINT4');
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) values ('32','INTERCOMPANY','24','NO','SPRINT4');
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) values ('33','LIQUIDO PRODUCTO','25','NO','SPRINT4');

-- Nuevos estados para el workflow
Insert into SHV_PARAM_WORKFLOW_ESTADO (ID_ESTADO,TIPO_WORKFLOW,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN) values ('COB_NO_DISPONIBLE','COBRO','Cobro no Disponible','SPRINT4');
Insert into SHV_PARAM_WORKFLOW_ESTADO (ID_ESTADO,TIPO_WORKFLOW,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN) values ('COB_EN_EDICION_VERIFCANDO_DEBITOS','COBRO','Cobro en Edición Verificando Débitos','SPRINT4');
Insert into SHV_PARAM_WORKFLOW_ESTADO (ID_ESTADO,TIPO_WORKFLOW,DESCRIPCION,AUD_REQUERIMIENTO_ORIGEN) values ('COB_EN_EDICION','COBRO','Cobro en Edición','SPRINT4');
--

-- MEDIOS DE PAGOS
update shv_cob_ed_otros_medio_pago set tipo_credito = 36 where tipo_credito = 30;
update shv_cob_ed_otros_medio_pago set tipo_credito = 35 where tipo_credito = 33;
update shv_cob_ed_otros_medio_pago set tipo_credito = 34 where tipo_credito = 32;
update shv_cob_ed_otros_medio_pago set tipo_credito = 33 where tipo_credito = 31;

delete from shv_param_tipo_medio_pago where id_tipo_medio_pago in (30, 31, 32, 33);

-- 01       CHEQUES PROPIOS
update shv_param_tipo_medio_pago set orden_imputacion = 17 where id_tipo_medio_pago = '01';
-- 02       CHEQUES DIFERIDOS
update shv_param_tipo_medio_pago set orden_imputacion = 18 where id_tipo_medio_pago = '02';
-- 03       BOLETA DE DEPOSITO
update shv_param_tipo_medio_pago set orden_imputacion = 19 where id_tipo_medio_pago = '03';
-- 05       TRANSF. BANCARIA
update shv_param_tipo_medio_pago set orden_imputacion = 20 where id_tipo_medio_pago = '05';
-- 06       COMPENSACION / INTERCOMPANY / LP
--Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
--values ('06', 'COMPENSACION / INTERCOMPANY / LP', '99','NO', 'SPRINT4');
-- 07       IMP. MUN. SEG. HIG
update shv_param_tipo_medio_pago set orden_imputacion = 9 where id_tipo_medio_pago = '07';
-- 08       IMPUESTO AL SELLO
update shv_param_tipo_medio_pago set orden_imputacion = 8 where id_tipo_medio_pago = '08';
-- 09       RET SEG SEC
update shv_param_tipo_medio_pago set orden_imputacion = 7 where id_tipo_medio_pago = '09';
-- 10       RETENCION GANANCIAS
update shv_param_tipo_medio_pago set orden_imputacion = 6 where id_tipo_medio_pago = '10';
-- 11       RETENCION IVA
update shv_param_tipo_medio_pago set orden_imputacion = 5 where id_tipo_medio_pago = '11';
-- 12       RETENCION IIBB
update shv_param_tipo_medio_pago set orden_imputacion = 4 where id_tipo_medio_pago = '12';
-- 13 PROXIMA FACTURA (MISC)
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('13','PROXIMA FACTURA (MISC)','99','NO','SPRINT4');
-- 14 ENVIO A GANANCIAS
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('14','ENVIO A GANANCIAS','99','NO','SPRINT4');
-- 15 REINTEGRO POR GIRO
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('15','REINTEGRO POR GIRO','99','SI','SPRINT4');
-- 16       DESISTIMIENTO
update shv_param_tipo_medio_pago set orden_imputacion = 23 where id_tipo_medio_pago = '16';
-- 17       INTERDEPOSITOS
update shv_param_tipo_medio_pago set orden_imputacion = 21 where id_tipo_medio_pago = '17';
-- 19       REMA NO ACTUALIZABLE
update shv_param_tipo_medio_pago set orden_imputacion = 14 where id_tipo_medio_pago = '19';
-- 20       REMANEN. ACTUALIZADO
update shv_param_tipo_medio_pago set orden_imputacion = 15 where id_tipo_medio_pago = '20';
-- 21       PAGO CUENTA CALIPSO
update shv_param_tipo_medio_pago set orden_imputacion = 16 where id_tipo_medio_pago = '21';
-- 22       NOTA DE CREDITO
update shv_param_tipo_medio_pago set orden_imputacion = 12 where id_tipo_medio_pago = '22';
-- 23       IVA RG3349 (PNUD)
update shv_param_tipo_medio_pago set orden_imputacion = 10 where id_tipo_medio_pago = '23';
-- 24       IMPUESTO TASAS MUNICIPALES
update shv_param_tipo_medio_pago set orden_imputacion = 11 where id_tipo_medio_pago = '24';
-- 25       PLANPAGO
update shv_param_tipo_medio_pago set orden_imputacion = 22 where id_tipo_medio_pago = '25';
-- 26 REINTEGRO POR CHEQUE
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('26','REINTEGRO POR CHEQUE','99','SI','SPRINT4');
-- 27 REINTEGRO CREDITO RED INTELIGENTE
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('27','REINTEGRO CREDITO RED INTELIGENTE','99','SI','SPRINT4');
-- 28       DEBITO PROXIMA FACTURA (CALIPSO)
update shv_param_tipo_medio_pago set descripcion = 'DEBITO PROXIMA FACTURA (CALIPSO)', orden_imputacion = 24, genera_intereses = 'SI' 
where id_tipo_medio_pago = '28';
-- 29       DEBITO PROXIMA FACTURA (MIC)
update shv_param_tipo_medio_pago set descripcion = 'DEBITO PROXIMA FACTURA (MIC)', orden_imputacion = 25, genera_intereses = 'SI' 
where id_tipo_medio_pago = '29';
-- 30       REINTEGRO TRANSFERENCIA BANCARIA
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('30', 'REINTEGRO TRANSFERENCIA BANCARIA', 99, 'SI', 'SPRINT4');
-- 31       CREDITO PROXIMA FACTURA (CALIPSO)
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('31', 'CREDITO PROXIMA FACTURA (CALIPSO)', 99, 'SI', 'SPRINT4');
-- 32       CREDITO PROXIMA FACTURA (MIC)
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('32', 'CREDITO PROXIMA FACTURA (MIC)', 99, 'SI', 'SPRINT4');
-- 33       COMPENSACION
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('33', 'COMPENSACION', 1, 'NO', 'SPRINT4');
-- 34 COMPENSACION INTERCOMPANY
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('34', 'COMPENSACION INTERCOMPANY', 2, 'NO', 'SPRINT4');
-- 35 COMPENSACION LIQUIDO PRODUCTO
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('35', 'COMPENSACION LIQUIDO PRODUCTO', 3, 'NO', 'SPRINT4');
-- 36 NOTA DE CREDITO PENDIENTE DE EMISION
Insert into SHV_PARAM_TIPO_MEDIO_PAGO (ID_TIPO_MEDIO_PAGO,DESCRIPCION,ORDEN_IMPUTACION,GENERA_INTERESES,AUD_REQUERIMIENTO_ORIGEN) 
values ('36', 'NOTA DE CREDITO PENDIENTE DE EMISION', 13, 'SI', 'SPRINT4');

COMMIT;
/
Exit;