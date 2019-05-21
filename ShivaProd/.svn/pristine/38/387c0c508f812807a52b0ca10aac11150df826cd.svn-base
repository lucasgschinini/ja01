--Limpieza de codigos basuras
update shv_cob_ed_debito set marca_reclamo = 'SIN_MARCA' where marca_reclamo = 'NO' and SISTEMA_ORIGEN = 'MIC';
update shv_cob_ed_debito set marca_reclamo = 'PENDIENTE' where marca_reclamo = 'SI' and SISTEMA_ORIGEN = 'MIC';
update shv_cob_ed_debito set marca_reclamo = 'NO_RECLAMADA' where marca_reclamo = 'NO' and SISTEMA_ORIGEN = 'CALIPSO';
update shv_cob_ed_debito set marca_reclamo = 'RECLAMADA' where marca_reclamo = 'SI' and SISTEMA_ORIGEN = 'CALIPSO';
--
update shv_cob_ed_credito set marca_reclamo = 'SIN_MARCA' where marca_reclamo = 'NO' and SISTEMA_ORIGEN = 'MIC';
update shv_cob_ed_credito set marca_reclamo = 'PENDIENTE' where marca_reclamo = 'SI' and SISTEMA_ORIGEN = 'MIC';
update shv_cob_ed_credito set marca_reclamo = 'RECLAMADA' where marca_reclamo = 'NO' and SISTEMA_ORIGEN = 'CALIPSO';
update shv_cob_ed_credito set marca_reclamo = 'NO_RECLAMADA' where marca_reclamo = 'SI' and SISTEMA_ORIGEN = 'CALIPSO';
--
update shv_cob_ed_debito set marca_reclamo = 'SIN_MARCA' where marca_reclamo = 'NO_RECLAMADA' and SISTEMA_ORIGEN = 'MIC';
--
update shv_cob_ed_debito set numero_comprobante = null where numero_comprobante = '-';
--
update shv_cob_ed_cliente set descripcion_holding = null where descripcion_holding = '-';
--


commit;
/
Exit;