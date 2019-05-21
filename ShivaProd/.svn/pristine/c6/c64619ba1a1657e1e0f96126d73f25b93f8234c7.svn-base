--Actualizo la precision de 3 a 20 segun la interface de deimos
alter table shv_cob_ed_debito modify dmos_numero_convenio number(20);
alter table shv_cob_ed_credito modify dmos_numero_convenio number(20);

--------------------------------------------------------------------------------------------------------------------
-- Actualizacion de tablas de documento de cobro, para generacion de documentos a SCARD 
-- Se agrega nueva columna id_cliente_legado
--------------------------------------------------------------------------------------------------------------------
alter table shv_cob_documento add id_cliente_legado number(10);

/
Exit;