LOAD DATA
CHARACTERSET WE8ISO8859P1
INSERT
INTO TABLE SHV_MIG_REG_AVC_CHEQUE_PD
 FIELDS TERMINATED BY '|'
 TRAILING NULLCOLS
(
 ID_ARCHIVO INTEGER EXTERNAL,
 IMPORTE DECIMAL EXTERNAL,
 NUMERO_ACUERDO INTEGER EXTERNAL,
 CODIGO_CLIENTE	INTEGER EXTERNAL,
 ID_REC_INSTRUMENTO INTEGER EXTERNAL,
 REND INTEGER EXTERNAL,
 FECHA_DE_PAGO DATE "DD/MM/YYYY HH24:MI:SS",
 FORMA_PAGO CHAR TERMINATED BY '|',
 ESTADO_ACREDITACION CHAR TERMINATED BY '|',
 FECHA_ACREDITACION DATE "DD/MM/YYYY HH24:MI:SS",
 NUMERO_BOLETA INTEGER EXTERNAL,
 SUCURSAL_DEPOSITO INTEGER EXTERNAL,
 GRUPO_ACREEDOR	CHAR TERMINATED BY '|',
 NOMBRE_CLIENTE	CHAR TERMINATED BY '|',
 CODIGO_RECHAZO	CHAR TERMINATED BY '|',
 DATOS_BANCO_CUENTA_CHEQUE CHAR TERMINATED BY '|',
 FECHA_VENCIMIENTO DATE "DD/MM/YYYY HH24:MI:SS",
 ID "SEQ_SHV_MIG_ID.NEXTVAL"
)

