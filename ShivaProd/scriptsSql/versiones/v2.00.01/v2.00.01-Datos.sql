--
-- Actualizacion de los datos de moneda en la tabla de creditos, para poder implementar un enum
--
update shv_cob_ed_credito set moneda = 'PES' where moneda = '$';
update shv_cob_ed_credito set moneda = 'DOL' where moneda = 'U$D';


commit;
/
Exit;