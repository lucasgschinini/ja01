LOAD DATA
CHARACTERSET WE8ISO8859P1
INSERT
INTO TABLE SHV_MIG_REGISTRO_AVC_ARCHIVOS
 FIELDS TERMINATED BY '|'
 TRAILING NULLCOLS
(
  ID_ARCHIVO INTEGER EXTERNAL,
  NOMBRE_ARCHIVO CHAR TERMINATED BY '|',
  USUARIO_PROCESAMIENTO CHAR TERMINATED BY '|',
  FECHA_PROCESAMIENTO DATE "DD/MM/YYYY HH24:MI:SS", 
  NUMERO_ACUERDO INTEGER EXTERNAL,
  ID "SEQ_SHV_MIG_ID.NEXTVAL"
)