----------------------------------------------------------------------------------------------------------------
-- Actualizaci�n de origenes contables
----------------------------------------------------------------------------------------------------------------
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('017','Confirmaci�n de aplicaci�n MP sobre reintegro valor SHIVA','SPRINT 6');
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('018','Desapropiaci�n del valor','SPRINT 6');
insert into SHV_PARAM_TIPO_ORIGEN_CONTABLE values ('019','Reversi�n de aplicaci�n MP sobre reintegro valor SHIVA','SPRINT 6');

----------------------------------------------------------------------------------------------------------------
-- Actualizaci�n de montos y porcentajes maximos para proxima factura
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
