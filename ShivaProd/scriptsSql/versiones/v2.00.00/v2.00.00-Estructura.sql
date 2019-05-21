------------------------------------------------------------------------------------
--Secuencias
------------------------------------------------------------------------------------
CREATE SEQUENCE SEQ_SHV_COB_ED_MED_PAG_CLIENTE INCREMENT BY 1 START WITH 1 MAXVALUE 99999999999999999999999 MINVALUE 1 CACHE 20;

------------------------------------------------------------------------------------
--Tablas
------------------------------------------------------------------------------------

-- SHV_COB_ED_COBRO
CREATE TABLE SHV_COB_ED_COBRO (
    ID_COBRO                                       NUMBER NOT NULL,
    ID_WORKFLOW                                    NUMBER NOT NULL,
    ID_OPERACION                                   NUMBER NOT NULL,
    ID_EMPRESA                                     VARCHAR2(2 BYTE) NOT NULL,
    ID_SEGMENTO                                    VARCHAR2(3 BYTE) NOT NULL,
    ID_ANALISTA                                    VARCHAR2(9 BYTE) NOT NULL,
    ID_COPROPIETARIO                               VARCHAR2(9 BYTE),
	ID_MOTIVO_COBRO                                NUMBER NOT NULL,
	OBSERVACION                                    VARCHAR2(250 BYTE),
	FECHA_ALTA                                     DATE NOT NULL,
	USUARIO_ALTA                                   VARCHAR2(9 BYTE) NOT NULL,
    FECHA_DERIVACION                               DATE,
	USUARIO_DERIVACION                             VARCHAR2(9 BYTE),
    FECHA_APROB_SUPER_COB                          DATE,
	USUARIO_APROB_SUPER_COB                        VARCHAR2(9 BYTE),
    FECHA_APROB_REFER_COB                          DATE,
	USUARIO_APROB_REFER_COB                        VARCHAR2(9 BYTE),
    FECHA_IMPUTACION                               DATE,
	USUARIO_IMPUTACION                             VARCHAR2(9 BYTE),
    FECHA_ULTIMA_MODIFICACION                      DATE,
	USUARIO_ULTIMA_MODIFICACION                    VARCHAR2(9 BYTE),
    CONSTRAINT COBRO_PK PRIMARY KEY (ID_COBRO) ENABLE
);

-- SHV_COB_ED_TRATAMIENTO_DIF
CREATE TABLE SHV_COB_ED_TRATAMIENTO_DIF (
	ID_COBRO                                       NUMBER NOT NULL,
	ENVIO_A                                        VARCHAR2(20 BYTE) NOT NULL,
	CON_CALCULO_INTERES                            VARCHAR2(2 BYTE),
	NUMERO_TRAMITE_REINTEGRO                       NUMBER(10,0),
	FECHA_ALTA_TRAMITE_REINTEGRO                   DATE,
	SISTEMA_DESTINO                                VARCHAR2(10 BYTE),
	LINEA                                          NUMBER(10,0),     
	ACUERDO_FACTURACION                            NUMBER(10,0),
	ID_CLIENTE_ACUERDO_FACTURACION                 NUMBER,
	CONSTRAINT TRATAMIENTO_DIFERENCIA_PK PRIMARY KEY (ID_COBRO) ENABLE
);

-- SHV_COB_ED_CLIENTE
CREATE TABLE SHV_COB_ED_CLIENTE	(                 
	ID_CLIENTE_COBRO                               NUMBER(20) NOT NULL,
	ID_COBRO                                       NUMBER NOT NULL,
	ID_CLIENTE_LEGADO                              NUMBER(20) NOT NULL,
  CONSTRAINT CLIENTE_PK PRIMARY KEY (ID_CLIENTE_COBRO)  ENABLE
) ;

-- SHV_COB_ED_DEBITO
CREATE TABLE SHV_COB_ED_DEBITO	(
	ID_DEBITO                                      NUMBER NOT NULL,
	ID_COBRO                                       NUMBER NOT NULL,
	ID_CLIENTE_LEGADO                              NUMBER(20) NOT NULL,
	SISTEMA_ORIGEN                                 VARCHAR2(7 BYTE) NOT NULL,
    TIPO_COMPROBANTE                               VARCHAR2(3 BYTE),
    CLASE_COMPROBANTE                              CHAR(1 BYTE),
    SUCURSAL_COMPROBANTE                           VARCHAR2(4 BYTE),
	NUMERO_COMPROBANTE                             VARCHAR2(8 BYTE),
	CUENTA                                         NUMBER(3),
	TIPO_DUC                                       VARCHAR2(50 BYTE),
	ESTADO_DUC                                     VARCHAR2(50 BYTE),
	MONEDA                                         VARCHAR2(3 BYTE) NOT NULL,
	IMPORTE_TERCEROS_TRANSFERIDOS                  NUMBER(17,4),
	IMPORTE_PRI_VENC_MONEDA_ORIGEN                 NUMBER(17,4),
	IMPORTE_PRI_VENC_PESIFICADO                    NUMBER(17,4),	
	IMPORTE_A_COBRAR                               NUMBER(17,4),
	IMPORTE_SEG_VENC                               NUMBER(17,4),
	IMPORTE_PRI_VENC_TERCEROS                      NUMBER(17,4),
	IMPORTE_SEG_VENC_TERCEROS                      NUMBER(17,4),
	SALDO_PRI_VENC_MONEDA_ORIGEN                   NUMBER(17,4),
	SALDO_PESIFICADO                               NUMBER(17,4),
	SALDO_TER_FINANCIA_TRANSF                      NUMBER(17,4),
	SALDO_TER_FINANCIA_NO_TRANSF                   NUMBER(17,4),
	SALDO_TER_NO_FINANCIA_TRANSF                   NUMBER(17,4),
	CHECK_COBRAR_SEG_VENCIMIENTO                   VARCHAR2(2 BYTE),
	CHECK_DESTRANFERIR_TERCEROS                    VARCHAR2(2 BYTE),
	ACUERDO_FACTURACION                     	   VARCHAR2(50 BYTE),
	ESTADO_ACUERDO_FACTURACION                     VARCHAR2(20 BYTE),
	ESTADO_CONCEPTO_TERCEROS                       VARCHAR2(2 BYTE),
	ESTADO_FACTURA                                 VARCHAR2(50 BYTE),
	TIPO_DE_CAMBIO                                 NUMBER(8,7),
	FECHA_EMISION                                  DATE,
	CHECK_SIN_DIFERENCIA_CAMBIO                    VARCHAR2(2 BYTE),
	NUMERO_CONVENIO                                NUMBER(3),
	CUOTA                                          NUMBER(3),
	NUMERO_REFERENCIA_MIC                          NUMBER(15),
	ID_DOC_CUENTA_COBRANZA                         NUMBER(10),
	FECHA_VENCIMIENTO                              DATE,
	FECHA_ULTIMO_PAGO_PARCIAL                      DATE,
	POSIBILIDAD_DESTRANSFERENCIA                   VARCHAR2(2 BYTE),
	ESTADO_MOROSIDAD                               VARCHAR2(20 BYTE),
	MARCA_MIGRADO_DEIMOS                           VARCHAR2(2 BYTE),
	MARCA_RECLAMO                                  VARCHAR2(50 BYTE),
	MARCA_CYQ                                      VARCHAR2(50 BYTE),
	FECHA_PUESTA_COBRO                             DATE,
	ORIGEN                                         VARCHAR2(50 BYTE),
	MARCA_APROPIADO_EN_DEIMOS                      VARCHAR2(2 BYTE),
	-- Tagetik
	RAZON_SOCIAL_CLIENTE                           VARCHAR2(200 BYTE),
	TIPO_CLIENTE                                   VARCHAR2(2 BYTE),
	CUIT                                           VARCHAR2(13 BYTE),
	CICLO_FACTURACION                              NUMBER(2),
	MARKETING_CUSTOMER_GROUP                       VARCHAR2(4 BYTE),
	TIPO_FACTURA                                   VARCHAR2(50 BYTE),
	-- Dakota
	UNIDAD_OPERATIVA                               VARCHAR2(20 BYTE),
	SUBTIPO                                        VARCHAR2(3 BYTE),
	HOLDING                                        VARCHAR2(20 BYTE),
	FECHA_VENCIMIENTO_MORA                         DATE,
	INDICADOR_PETICION_CORTE                       VARCHAR2(1 BYTE),
	CODIGO_TARIFA                                  VARCHAR2(5 BYTE),
	-- Deimos
	DMOS_APROPIADO_EN_DEIMOS                       VARCHAR2(2 BYTE),
	DMOS_ESTADO_TRAMITE                            VARCHAR2(50 BYTE),
	DMOS_CANTIDAD_DE_CUOTAS                        NUMBER(4),
	DMOS_CANTIDAD_DE_CUOTAS_PAGAS                  NUMBER(4),
	DMOS_NUMERO_CONVENIO                           NUMBER(3),
	-- Validaciones en general del debito
	RESULTADO_VALIDACION_ESTADO                    VARCHAR2(50 BYTE) NOT NULL,
	RESULTADO_VALIDACION_COD_ERR                   VARCHAR2(10 BYTE),
	RESULTADO_VALIDACION_DES_ERR                   VARCHAR2(100 BYTE),
  CONSTRAINT DEBITO_PK PRIMARY KEY (ID_DEBITO)     ENABLE
) ;

-- SHV_COB_ED_CREDITO
CREATE TABLE SHV_COB_ED_CREDITO	( 
	ID_CREDITO                                     NUMBER NOT NULL,
	ID_COBRO                                       NUMBER NOT NULL,
	ID_VALOR                                       NUMBER NOT NULL, -- Solo se hace la FK no en el modelo, porque se va a usar la vista
	ID_CLIENTE_LEGADO                              NUMBER(20) NOT NULL,
	SISTEMA_ORIGEN                                 VARCHAR2(7 BYTE) NOT NULL,
    TIPO_COMPROBANTE                               VARCHAR2(3 BYTE),
    CLASE_COMPROBANTE                              CHAR(1 BYTE),
    SUCURSAL_COMPROBANTE                           VARCHAR2(4 BYTE),
	NUMERO_COMPROBANTE                             VARCHAR2(8 BYTE),
	NRO_REFERENCIA_MIC                             NUMBER(15),
	CUENTA                                         NUMBER(3),
	TIPO_CREDITO                                   VARCHAR2(3 BYTE),
	MONEDA                                         VARCHAR2(3 BYTE) NOT NULL,
	IMPORTE_MONEDA_ORIGEN                          NUMBER(17,4),
	IMPORTE_PESIFICADO                             NUMBER(17,4),
	IMPORTE_A_UTILIZAR                             NUMBER(17,4),
	SALDO_MONEDA_ORIGEN                            NUMBER(17,4),
	SALDO_PESIFICADO                               NUMBER(17,4),
	FECHA_VALOR                                    DATE,
	FECHA_ALTA                                     DATE,
	FECHA_EMISION                                  DATE,
	FECHA_VENCIMIENTO                              DATE,
	FECHA_ULTIMO_MOVIMIENTO                        DATE,
	FECHA_INGRESO_RECIBO                           DATE,
	FECHA_DEPOSITO                                 DATE,
	FECHA_TRANSFERENCIA                            DATE,
	FECHA_VENCIMIENTO_MORA                         DATE,
	TIPO_DE_CAMBIO                                 NUMBER(8,7),
	PROVINCIA                                      VARCHAR2(1 BYTE),
	RAZON_SOCIAL_CLIENTE                           VARCHAR2(200 BYTE),
	TIPO_CLIENTE                                   VARCHAR2(2 BYTE),
	CUIT                                           VARCHAR2(13 BYTE),
	ESTADO_VALOR                                   NUMBER,
	ID_DOCUMENTO_CUENTA_COBRANZA                   NUMBER(10),
	ESTADO_MOROCIDAD                               VARCHAR2(20 BYTE),
	MARCA_RECLAMO                                  VARCHAR2(50 BYTE),
	MARCA_CYQ                                      VARCHAR2(50 BYTE),
	ESTADO_CREDITO                                 VARCHAR2(50 BYTE),
	MARCA_MIGRADO_DEIMOS                           VARCHAR2(2 BYTE),
	UNIDAD_OPERATIVA                               VARCHAR2(20 BYTE),
	SUBTIPO                                        VARCHAR2(3 BYTE),
	HOLDING                                        VARCHAR2(20 BYTE),
	CICLO_FACTURACION                              NUMBER(2),
	MARKETING_CUSTOMER_GROUP                       VARCHAR2(4 BYTE),
	INDICADOR_PETICION_CORTE                       VARCHAR2(1 BYTE),
	CODIGO_TARIFA                                  VARCHAR2(5 BYTE),
	NUMERO_ACUERDO                                 NUMBER(20),
	CODIGO_TIPO_REMANENTE                          NUMBER(1),
	REFERENCIA_VALOR                               VARCHAR2(50 BYTE),
	DESCRIPCION_TIPO_RETENCION                     VARCHAR2(50 BYTE),
	-- Validaciones en general del crédito
	RESULTADO_VALIDACION_ESTADO                    VARCHAR2(50 BYTE) NOT NULL,
	RESULTADO_VALIDACION_COD_ERR                   VARCHAR2(10 BYTE),
	RESULTADO_VALIDACION_DES_ERR                   VARCHAR2(100 BYTE),
	CONSTRAINT CREDITO_PK PRIMARY KEY (ID_CREDITO) ENABLE
) ;

-- SHV_COB_ED_OTROS_MEDIO_PAGO
CREATE TABLE SHV_COB_ED_OTROS_MEDIO_PAGO
  (
    ID_MEDIO_PAGO      NUMBER NOT NULL ENABLE,
    ID_COBRO           NUMBER NOT NULL ENABLE,
    ID_TIPO_MEDIO_PAGO VARCHAR2(2 BYTE) NOT NULL ENABLE,
    IMPORTE            NUMBER(17,4) NOT NULL ENABLE,
    NRO_ACTA           VARCHAR2(25 BYTE),
    FECHA              DATE,
    CHECK_MEDIO_PAGO_EN_PROCESO VARCHAR2(2 BYTE),
    CONSTRAINT OTROS_MEDIO_PAGO_PK PRIMARY KEY (ID_MEDIO_PAGO) ENABLE
) ;

-- SHV_COB_ED_COBRO_ADJUNTO
CREATE TABLE SHV_COB_ED_ADJUNTO	(
	ID_COBRO                                       NUMBER NOT NULL,
	ID_ADJUNTO                                     NUMBER NOT NULL 
) ;

-- SHV_COB_ED_MEDIO_PAGO_CLIENTE
CREATE TABLE SHV_COB_ED_MEDIO_PAGO_CLIENTE	(
	ID_MEDIO_PAGO_CLIENTE                          NUMBER NOT NULL,
	ID_MEDIO_PAGO                                  NUMBER NOT NULL,
	ID_CLIENTE_LEGADO                              NUMBER(20) NOT NULL,
	CONSTRAINT MEDIO_PAGO_CLIENTE_PK PRIMARY KEY (ID_MEDIO_PAGO_CLIENTE)  ENABLE
) ;


-- SHV_WF_TAREA_COBRO
CREATE TABLE SHV_WF_TAREA_COBRO	(
	ID_TAREA                                     NUMBER NOT NULL,
	ID_COBRO                                     NUMBER NOT NULL,
	CONSTRAINT TAREA_COBRO_PK PRIMARY KEY (ID_TAREA)  ENABLE
) ;

-- id_motivo por id_motivo_cobro
-- SHV_PARAM_MOTIVO_COBRO
drop table SHV_PARAM_MOTIVO_COBRO;

CREATE TABLE SHV_PARAM_MOTIVO_COBRO(	
	ID_MOTIVO_COBRO NUMBER NOT NULL, 
	DESCRIPCION VARCHAR2(50 BYTE), 
	AUD_REQUERIMIENTO_ORIGEN VARCHAR2(15 BYTE),
	USO_OPERACION_MASIVA VARCHAR2(2 BYTE),
	CONSTRAINT SHV_PARAM_MOTIVO_OPER_MAS_PK PRIMARY KEY (ID_MOTIVO_COBRO)  ENABLE
) ;

------------------------------------------------------------------------------------
--Foreign key
------------------------------------------------------------------------------------


-- cliente con cobro ed
ALTER TABLE SHV_COB_ED_CLIENTE
ADD CONSTRAINT CLIENTE_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- cobro adjunto con cobro ed
ALTER TABLE SHV_COB_ED_ADJUNTO
ADD CONSTRAINT COBRO_ADJUNTO_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- cobro adjunto con adjunto
ALTER TABLE SHV_COB_ED_ADJUNTO
ADD CONSTRAINT COBRO_ADJUNTO_ADJUNTO_FK
FOREIGN KEY (ID_ADJUNTO) REFERENCES SHV_DOC_DOCUMENTO_ADJUNTO(ID_ADJUNTO) ENABLE;

-- credito con cobro ed
ALTER TABLE SHV_COB_ED_CREDITO
ADD CONSTRAINT CREDITO_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- debito con cobro ed
ALTER TABLE SHV_COB_ED_DEBITO
ADD CONSTRAINT DEBITO_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- medios de pago con cobro ed
ALTER TABLE SHV_COB_ED_OTROS_MEDIO_PAGO
ADD CONSTRAINT OTROS_MEDIO_PAGO_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- tratamiento diferencia
ALTER TABLE SHV_COB_ED_TRATAMIENTO_DIF
ADD CONSTRAINT TRATAMIENTO_DIF_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO(ID_COBRO) ENABLE;

-- cobro ed con empresa
ALTER TABLE SHV_COB_ED_COBRO 
ADD CONSTRAINT COBRO_ED_EMPRESA_FK 
FOREIGN KEY (ID_EMPRESA) REFERENCES SHV_PARAM_EMPRESA (ID_EMPRESA) ENABLE;

-- cobro ed con motivo 
ALTER TABLE SHV_COB_ED_COBRO 
ADD CONSTRAINT COBRO_ED_MOTIVO_FK 
FOREIGN KEY (ID_MOTIVO_COBRO) REFERENCES SHV_PARAM_MOTIVO_COBRO (ID_MOTIVO_COBRO) ENABLE;

-- cobro ed con segmento
ALTER TABLE SHV_COB_ED_COBRO
ADD CONSTRAINT COBRO_ED_SEGMENTO_FK
FOREIGN KEY (ID_SEGMENTO) REFERENCES SHV_PARAM_SEGMENTO(ID_SEGMENTO) ENABLE;

-- cobro ed con workflow
ALTER TABLE SHV_COB_ED_COBRO 
ADD CONSTRAINT COBRO_ED_WORKFLOW_FK 
FOREIGN KEY (ID_WORKFLOW) REFERENCES SHV_WF_WORKFLOW (ID_WORKFLOW) ENABLE;

-- credito con valor
ALTER TABLE SHV_COB_ED_CREDITO
ADD CONSTRAINT CREDITO_VALOR_FK
FOREIGN KEY (ID_VALOR) REFERENCES SHV_VAL_VALOR (ID_VALOR) ENABLE;

ALTER TABLE SHV_WF_TAREA_COBRO 
ADD CONSTRAINT TAREA_COBRO_TAREA_FK 
FOREIGN KEY (ID_TAREA) REFERENCES SHV_WF_TAREA (ID_TAREA) ENABLE;

ALTER TABLE SHV_WF_TAREA_COBRO
ADD CONSTRAINT TAREA_COBRO_COBRO_ED_FK
FOREIGN KEY (ID_COBRO) REFERENCES SHV_COB_ED_COBRO (ID_COBRO) ENABLE;


----------------------------------------------------------------------------------------------
--CORRECCIONES
----------------------------------------------------------------------------------------------
-- Primary Key
ALTER TABLE SHV_COB_ED_CLIENTE
DROP CONSTRAINT CLIENTE_PK;

ALTER TABLE SHV_COB_ED_CLIENTE
ADD CONSTRAINT CLIENTE_PK PRIMARY KEY (ID_CLIENTE_COBRO, ID_COBRO);

ALTER TABLE SHV_COB_ED_CREDITO
DROP CONSTRAINT CREDITO_PK;

ALTER TABLE SHV_COB_ED_CREDITO
ADD CONSTRAINT CREDITO_PK PRIMARY KEY (ID_CREDITO, ID_COBRO);

ALTER TABLE SHV_COB_ED_DEBITO
DROP CONSTRAINT DEBITO_PK;

ALTER TABLE SHV_COB_ED_DEBITO
ADD CONSTRAINT DEBITO_PK PRIMARY KEY (ID_DEBITO, ID_COBRO);

ALTER TABLE SHV_COB_ED_OTROS_MEDIO_PAGO
DROP CONSTRAINT OTROS_MEDIO_PAGO_PK;

ALTER TABLE SHV_COB_ED_OTROS_MEDIO_PAGO
ADD CONSTRAINT OTROS_MEDIO_PAGO_PK PRIMARY KEY (ID_MEDIO_PAGO, ID_COBRO);

------------

DROP TABLE SHV_COB_ED_MEDIO_PAGO_CLIENTE;
CREATE TABLE SHV_COB_ED_MEDIO_PAGO_CLIENTE	(
	ID_MEDIO_PAGO_CLIENTE                          NUMBER NOT NULL,
	ID_MEDIO_PAGO                                  NUMBER NOT NULL,
  	ID_COBRO                                       NUMBER NOT NULL,
	ID_CLIENTE_LEGADO                              NUMBER(20) NOT NULL,
	CONSTRAINT MEDIO_PAGO_CLIENTE_PK PRIMARY KEY (ID_MEDIO_PAGO_CLIENTE, ID_MEDIO_PAGO, ID_COBRO)  ENABLE
) ;

--
alter table SHV_COB_ED_CREDITO modify ID_VALOR null;
--
alter table SHV_COB_ED_TRATAMIENTO_DIF modify ENVIO_A null;
--
alter table SHV_COB_ED_CREDITO drop column ESTADO_VALOR;
alter table SHV_COB_ED_CREDITO drop column ESTADO_CREDITO;
alter table SHV_COB_ED_CREDITO drop column ESTADO_MOROCIDAD;
alter table SHV_COB_ED_CREDITO add ESTADO_ORIGEN VARCHAR2(50);
--
alter table SHV_COB_ED_DEBITO drop column ESTADO_MOROSIDAD;
alter table SHV_COB_ED_DEBITO drop column ESTADO_FACTURA;
alter table SHV_COB_ED_DEBITO add ESTADO_ORIGEN VARCHAR2(50);
--
ALTER TABLE SHV_COB_ED_OTROS_MEDIO_PAGO RENAME COLUMN ID_TIPO_MEDIO_PAGO TO TIPO_CREDITO; 
--
alter table SHV_WF_TAREA_COBRO add IMPORTE NUMBER(15,2);
--
alter table SHV_COB_ED_CREDITO modify PROVINCIA VARCHAR2(50);

/
Exit;