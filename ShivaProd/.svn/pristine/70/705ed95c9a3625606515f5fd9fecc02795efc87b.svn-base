--
-- Actualizacion de los datos de moneda en la tabla de creditos, para poder implementar un enum
--
update shv_cob_ed_credito set moneda = 'PES' where moneda = '$';
update shv_cob_ed_credito set moneda = 'DOL' where moneda = 'U$D';
--
--Limpieza de codigos basuras
update shv_cob_ed_credito set tipo_credito = null where tipo_credito = 't';
update shv_cob_ed_credito set SUBTIPO = null where SUBTIPO = 's';
update shv_cob_ed_credito set SUBTIPO = null where SUBTIPO = '72';
--



commit;
/
Exit;