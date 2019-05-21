----------------------------------------------------------------------------------------------------------------
-- Actualización de origenes contables
----------------------------------------------------------------------------------------------------------------
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('017','Confirmación de aplicación MP sobre reintegro valor SHIVA','SPRINT 6');
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('018','Desapropiación del valor','SPRINT 6');
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('019','Reversión de aplicación MP sobre reintegro valor SHIVA','SPRINT 6');

----------------------------------------------------------------------------------------------------------------
-- Actualización de montos y porcentajes maximos para proxima factura
----------------------------------------------------------------------------------------------------------------
update shv_param_parametro
set valor_numerico = 100
where clave = 'cobros.validacion.porcentajeMaximoMontoProximaFact';

update shv_param_parametro
set valor_numerico = 1500
where clave = 'cobros.validacion.importeMaximoMontoProximaFact';

commit;

/
Exit;
