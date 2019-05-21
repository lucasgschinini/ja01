------------------------------------------------------------------------------------
--Correcciones
------------------------------------------------------------------------------------
alter table SHV_COB_ED_CREDITO modify TIPO_CREDITO VARCHAR2(50);
alter table SHV_COB_ED_CREDITO modify SUBTIPO VARCHAR2(3);
ALTER TABLE SHV_COB_ED_CREDITO RENAME COLUMN DESCRIPCION_TIPO_RETENCION TO TIPO_RETENCION;

/
Exit;