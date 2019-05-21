insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('destinatarios.mail.validacion.archivo.desde.mic','SHIVA-PROD@ta.telecom.com.ar;SHIVA-REF@ta.telecom.com.ar',null,'SPRINT8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('destinatarios.mail.proceso.archivo.desde.mic','SHIVA-PROD@ta.telecom.com.ar;SHIVA-REF@ta.telecom.com.ar',null,'SPRINT8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values ('destinatarios.mail.siebel','SHIVA-PROD@ta.telecom.com.ar;SHIVA-REF@ta.telecom.com.ar',null,'SPRINT8');

insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values('OpMas.Batch.ConsulSiebel.registros.AProcesar',null,500,'SPRINT 8');
insert into shv_param_parametro (CLAVE,VALOR_TEXTO,VALOR_NUMERICO,AUD_REQUERIMIENTO_ORIGEN) values('OpMas.Batch.ConsulSiebel.registros.PorVuelta',null,100,'SPRINT 8');

commit;

Exit;
