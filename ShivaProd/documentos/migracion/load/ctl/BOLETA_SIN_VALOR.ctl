LOAD DATA
CHARACTERSET WE8ISO8859P1
INSERT
INTO TABLE SHV_MIG_BOLETA_SIN_VALOR
 FIELDS TERMINATED BY '|'
 TRAILING NULLCOLS
(
 NUMERO_BOLETA INTEGER EXTERNAL,
 EMAIL CHAR TERMINATED BY '|',
 ID_SEGMENTO CHAR TERMINATED BY '|',
 ID_CLIENTE_LEGADO INTEGER EXTERNAL, 
 ID_ANALISTA CHAR TERMINATED BY '|',
 ID_COPROPIETARIO CHAR TERMINATED BY '|',
 IMPORTE DECIMAL EXTERNAL,
 ID_ORIGEN INTEGER EXTERNAL,
 OBSERVACIONES CHAR TERMINATED BY '|',
 NUMERO_ACUERDO INTEGER EXTERNAL,
 OPERACION_ASOCIADA INTEGER EXTERNAL,
 ID_MOTIVO INTEGER EXTERNAL,
 ESTADO CHAR TERMINATED BY '|',
 ID "SEQ_SHV_MIG_ID.NEXTVAL"
)

