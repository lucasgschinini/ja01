ALTER TABLE SHV_COB_TRATAMIENTO_DIFERENCIA ADD CONSTRAINT SHV_COB_TRATAMIENTO_DIF_PK PRIMARY KEY (ID_TRATAMIENTO_DIFERENCIA);
ALTER TABLE SHV_WF_WORKFLOW_MARCA ADD CONSTRAINT SHV_WF_MARCA_PK PRIMARY KEY (ID_WORKFLOW_MARCA);
ALTER TABLE SHV_WF_WORKFLOW_MARCA_HIST ADD CONSTRAINT SHV_WF_MARCA_HIST_PK PRIMARY KEY (ID_WORKFLOW_MARCA_HIST);

--------------------------------------------------------------------------------------------------------------------
-- Actualizacion de tablas de documento de cobro, para generacion de documentos a SCARD 
-- Se agrega nueva columna y se actualizan datos de columnas existentes
--------------------------------------------------------------------------------------------------------------------

-- Actualizo el nombre de la columna para preservar los datos existentes
alter table shv_cob_documento rename column tipo_documento to tipo_operacion;

-- Creo una nueva columna
alter table shv_cob_documento add tipo_documento varchar2(15 byte);

/
Exit;