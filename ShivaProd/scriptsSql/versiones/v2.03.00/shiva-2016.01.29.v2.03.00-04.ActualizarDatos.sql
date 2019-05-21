insert into shv_param_parametro values('valor.busqueda.exportacion.limite',null,500,'SHIVA');
insert into shv_param_parametro values('cobro.busqueda.exportacion.limite',null,500,'SPRINT 8');


--Menu
insert into SHV_MNU_MENU (ID_MENU,DESCRIPCION,URL_ACCESO,ORDEN,MENU_ID_MENU) values ('26','Operaciones masivas',null,'9','0');
insert into SHV_MNU_MENU (ID_MENU,DESCRIPCION,URL_ACCESO,ORDEN,MENU_ID_MENU) values ('27','Alta de operaciones masivas','operacion-masiva-alta','1','26');
insert into SHV_MNU_MENU (ID_MENU,DESCRIPCION,URL_ACCESO,ORDEN,MENU_ID_MENU) values ('28','Busqueda de operaciones masivas','operacion-masiva-busqueda','2','26');

--SupervisorOperacionMasiva
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (221, 46 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (222, 46 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (224, 45 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (225, 45 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (227, 44 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (228, 44 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (230, 43 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (231, 43 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (233, 42 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (234, 42 , 28);
--AnalistaOperacionMasiva
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (236, 41 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (237, 41 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (239, 40 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (240, 40 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (242, 39 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (243, 39 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (245, 38 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (246, 38 , 28);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (248, 37 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (249, 37 , 28);
--Consulta
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (251, 24 , 27);
insert into SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL,ID_PERFIL,ID_MENU) values (252, 24 , 28);

-- 
-- SHV_PROCESO_TRANSACCIONES
--
INSERT INTO SHV_PROCESO_TRANSACCIONES (PROCESO,MINUTOS,TRANSACCIONES,HABILITADO) values ('procesarOperacionMasivaAlta','60','20','1');
--

insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('operacion.masiva.cant.reg.DEUDA',null,'80000','SPRINT8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('operacion.masiva.cant.reg.DSIST',null,'20000','SPRINT8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('operacion.masiva.cant.reg.GNCIA',null,'20000','SPRINT8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('operacion.masiva.cant.reg.REINT',null,'20000','SPRINT8');

commit;

Exit;