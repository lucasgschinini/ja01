------------------------------------------------------------------------------------
--Correcciones
------------------------------------------------------------------------------------

alter table SHV_COB_ED_CREDITO modify PROVINCIA VARCHAR2(50);

----------------------------------------------------------------------------------------
-- Actualizaciones para tratamiento diferencia
----------------------------------------------------------------------------------------
--
-- Actualizaciones de estructura
--
-- Limpieza de la tabla, para poder luego setear campos no nulos
-- Aqui borramos directamente los datos existentes debido a que los pocos registros que existen 
-- poseen datos en el campo TIPO_TRATAMIENTO_DIFERENCIA que no se corresponden con el Enum correcto.
DELETE FROM SHV_COB_ED_TRATAMIENTO_DIF;
-- Amplio el campo existente ENVIO_A a 50 caracteres para poder soportar el nuevo Enum
ALTER TABLE SHV_COB_ED_TRATAMIENTO_DIF MODIFY ENVIO_A VARCHAR2(50 BYTE) NOT NULL ENABLE;
-- Renombo el campo a su nombre correcto
ALTER TABLE SHV_COB_ED_TRATAMIENTO_DIF RENAME COLUMN ENVIO_A TO TIPO_TRATAMIENTO_DIFERENCIA;
-- Agrego el campo importe para guardar el importe diferencia a tratar, obligatorio siempre
ALTER TABLE SHV_COB_ED_TRATAMIENTO_DIF ADD IMPORTE NUMBER (15,2) NOT NULL ENABLE;

/
Exit;