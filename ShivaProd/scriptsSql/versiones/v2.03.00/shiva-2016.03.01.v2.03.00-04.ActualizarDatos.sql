
update SHV_MNU_MENU set descripcion = 'Búsqueda de Operaciones Masivas' where id_menu = '28';
update SHV_PARAM_WORKFLOW_ESTADO set DESCRIPCION='Operación Masiva Imputada',AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_estado = 'MAS_IMPUTADA';
update SHV_PARAM_WORKFLOW_ESTADO set DESCRIPCION='Operación Masiva Anulada', AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_estado = 'MAS_ANULADA';
update SHV_PARAM_WORKFLOW_ESTADO set DESCRIPCION='Operación Masiva Parcialmente Imputada', AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_estado = 'MAS_PARCIALMENTE_IMPUTADA';
update SHV_PARAM_WORKFLOW_ESTADO set DESCRIPCION='Operación Masiva Rechazada', AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_estado = 'MAS_RECHAZADA';

update SHV_PARAM_MOTIVO_COBRO set DESCRIPCION='Financiación de deuda' , AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_motivo_cobro=1; 
update SHV_PARAM_MOTIVO_COBRO set DESCRIPCION='Depuración de crédito' , AUD_REQUERIMIENTO_ORIGEN='SPRINT8' where id_motivo_cobro=3;

insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('destinatarios.mail.creacion.archivo.hacia.mic','SHIVA-PROD@ta.telecom.com.ar;SHIVA-REF@ta.telecom.com.ar',null,'SPRINT8');

commit;

Exit;