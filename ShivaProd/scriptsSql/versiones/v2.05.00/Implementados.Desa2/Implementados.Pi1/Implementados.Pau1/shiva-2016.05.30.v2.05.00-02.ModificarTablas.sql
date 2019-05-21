-------------------------------------------------------------------------------------------
-- Se agregan campos nuevos para contener datos de respuesta de aplicacion de
-- medios de pago en dolares en las tablas nota de credito calipso y CTA.
-------------------------------------------------------------------------------------------
alter table SHV_COB_MED_PAG_CTA add ID_DOC_CTAS_COB_PADRE NUMBER;

Exit;