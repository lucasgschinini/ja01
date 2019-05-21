CREATE TABLE SHV_PARAM_REGLA_COBRO
  (
    ID_REGLA      NUMBER NOT NULL ENABLE,
    TIPO_REGLA    VARCHAR2(15 BYTE) NOT NULL ENABLE,
    ID_EMPRESA    VARCHAR2(2 BYTE) NOT NULL ENABLE,
    ID_SEGMENTO   VARCHAR2(3 BYTE) NOT NULL ENABLE,
    PORC_MINIMO   NUMBER,
    PORC_MAXIMO   NUMBER, 
    MONEDA        VARCHAR2(3 BYTE),
    MONTO_MINIMO  NUMBER(17,4),
    MONTO_MAXIMO  NUMBER(17,4),
    ACCION        VARCHAR2(11 BYTE),
    AUD_REQUERIMIENTO_ORIGEN VARCHAR2(15 BYTE) NOT NULL ENABLE,
    CONSTRAINT REGLA_COBRO_PK PRIMARY KEY (ID_REGLA) ENABLE,
    CONSTRAINT REGLA_COBRO_EMPRESA_FK FOREIGN KEY (ID_EMPRESA) REFERENCES SHV_PARAM_EMPRESA (ID_EMPRESA) ENABLE,
    CONSTRAINT REGLA_COBRO_SEGMENTO_FK FOREIGN KEY (ID_SEGMENTO) REFERENCES SHV_PARAM_SEGMENTO (ID_SEGMENTO) ENABLE
    )
  TABLESPACE SHIVATS;

--------------------------------------------------------
--  DDL for Trigger TRG_SHV_PARAM_ACUERDO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER TRG_SHV_PARAM_REGLA_COBRO 
  Before insert or update or delete
  On SHV_PARAM_REGLA_COBRO
  For each row
    Declare

      str_insert    shv_aud_auditoria_param.accion%type            := 'INSERT';
      str_update    shv_aud_auditoria_param.accion%type            := 'UPDATE';
      str_delete    shv_aud_auditoria_param.accion%type            := 'DELETE';

      str_valor     varchar2(20) := 'valor: ';
      str_original  varchar2(20) := 'original: ';
      str_actual    varchar2(20) := 'actual: ';
      str_separador varchar2(1) := '|';

      str_tabla     shv_aud_auditoria_param.tabla%type             := 'SHV_PARAM_REGLA_COBRO';
      str_datos     shv_aud_auditoria_param.datos_modificados%type := null;

    Begin

      -- Acciones a realizar sobre acciones de INSERT
      if inserting then

        str_datos :=
          '[ID_REGLA]('||str_valor||:new.ID_REGLA|| ')'
        || str_separador
          ||'[TIPO_REGLA]('||str_valor||:new.TIPO_REGLA|| ')'
        || str_separador
          ||'[ID_EMPRESA]('||str_valor||:new.ID_EMPRESA|| ')'
        || str_separador
          ||'[ID_SEGMENTO]('||str_valor||:new.ID_SEGMENTO|| ')'
        || str_separador
          ||'[PORC_MINIMO]('||str_valor||:new.PORC_MINIMO|| ')'
        || str_separador
          ||'[PORC_MAXIMO]('||str_valor||:new.PORC_MAXIMO|| ')'
        || str_separador
          ||'[MONEDA]('||str_valor||:new.MONEDA|| ')'
        || str_separador
          ||'[MONTO_MINIMO]('||str_valor||:new.MONTO_MINIMO|| ')'
        || str_separador
          ||'[MONTO_MAXIMO]('||str_valor||:new.MONTO_MAXIMO|| ')'
        || str_separador
          ||'[ACCION]('||str_valor||:new.ACCION|| ')'
;

        insert into shv_aud_auditoria_param
        values(SEQ_SHV_AUD_AUDITORIA_PARAM.nextval, user, sysdate, str_insert, str_tabla, str_datos, :new.aud_requerimiento_origen);
      end if;

      -- Acciones a realizar sobre acciones de UPDATE
      if updating then

       if (NVL(TO_CHAR(:old.ID_REGLA), ' ') <> NVL(TO_CHAR(:new.ID_REGLA), ' ')) then
          str_datos := '[ID_REGLA]('||str_original||:old.ID_REGLA|| ') ('||str_actual||:new.ID_REGLA|| ')';
        end if;

       if (NVL(TO_CHAR(:old.TIPO_REGLA), ' ') <> NVL(TO_CHAR(:new.TIPO_REGLA), ' ')) then
          str_datos := str_datos||'[TIPO_REGLA]('||str_original||:old.TIPO_REGLA|| ') ('||str_actual||:new.TIPO_REGLA|| ')';
        end if;

       if (NVL(TO_CHAR(:old.ID_EMPRESA), ' ') <> NVL(TO_CHAR(:new.ID_EMPRESA), ' ')) then
          str_datos := str_datos||'[ID_EMPRESA]('||str_original||:old.ID_EMPRESA|| ') ('||str_actual||:new.ID_EMPRESA|| ')';
        end if;

       if (NVL(TO_CHAR(:old.ID_SEGMENTO), ' ') <> NVL(TO_CHAR(:new.ID_SEGMENTO), ' ')) then
          str_datos := str_datos||'[ID_SEGMENTO]('||str_original||:old.ID_SEGMENTO|| ') ('||str_actual||:new.ID_SEGMENTO|| ')';
        end if;

       if (NVL(TO_CHAR(:old.PORC_MINIMO), ' ') <> NVL(TO_CHAR(:new.PORC_MINIMO), ' ')) then
          str_datos := str_datos||'[PORC_MINIMO]('||str_original||:old.PORC_MINIMO|| ') ('||str_actual||:new.PORC_MINIMO|| ')';
        end if;
        
         if (NVL(TO_CHAR(:old.PORC_MAXIMO), ' ') <> NVL(TO_CHAR(:new.PORC_MAXIMO), ' ')) then
          str_datos := str_datos||'[PORC_MAXIMO]('||str_original||:old.PORC_MAXIMO|| ') ('||str_actual||:new.PORC_MAXIMO|| ')';
        end if;
        
         if (NVL(TO_CHAR(:old.MONEDA), ' ') <> NVL(TO_CHAR(:new.MONEDA), ' ')) then
          str_datos := str_datos||'[MONEDA]('||str_original||:old.MONEDA|| ') ('||str_actual||:new.MONEDA|| ')';
        end if;
        
         if (NVL(TO_CHAR(:old.MONTO_MINIMO), ' ') <> NVL(TO_CHAR(:new.MONTO_MINIMO), ' ')) then
          str_datos := str_datos||'[MONTO_MINIMO]('||str_original||:old.MONTO_MINIMO|| ') ('||str_actual||:new.MONTO_MINIMO|| ')';
        end if;
        
         if (NVL(TO_CHAR(:old.MONTO_MAXIMO), ' ') <> NVL(TO_CHAR(:new.MONTO_MAXIMO), ' ')) then
          str_datos := str_datos||'[MONTO_MAXIMO]('||str_original||:old.MONTO_MAXIMO|| ') ('||str_actual||:new.MONTO_MAXIMO|| ')';
        end if;
        
         if (NVL(TO_CHAR(:old.ACCION), ' ') <> NVL(TO_CHAR(:new.ACCION), ' ')) then
          str_datos := str_datos||'[ACCION]('||str_original||:old.ACCION|| ') ('||str_actual||:new.ACCION|| ')';
        end if;

       if (str_datos is null) then
          str_datos := 'Se ha actualizado con los mismos datos';
        end if;

        insert into shv_aud_auditoria_param
        values(SEQ_SHV_AUD_AUDITORIA_PARAM.nextval, user, sysdate, str_update, str_tabla, str_datos, :new.aud_requerimiento_origen);
      end if;

      -- Acciones a realizar sobre acciones de DELETE
      if deleting then

       str_datos :=
          '[ID_REGLA]('||str_valor||:old.ID_REGLA|| ')'
        || str_separador
          ||'[TIPO_REGLA]('||str_valor||:old.TIPO_REGLA|| ')'
        || str_separador
          ||'[ID_EMPRESA]('||str_valor||:old.ID_EMPRESA|| ')'
        || str_separador
          ||'[ID_SEGMENTO]('||str_valor||:old.ID_SEGMENTO|| ')'
        || str_separador
          ||'[PORC_MINIMO]('||str_valor||:old.PORC_MINIMO|| ')'
        || str_separador
          ||'[PORC_MAXIMO]('||str_valor||:old.PORC_MAXIMO|| ')'
        || str_separador
          ||'[MONEDA]('||str_valor||:old.MONEDA|| ')'
        || str_separador
          ||'[MONTO_MINIMO]('||str_valor||:old.MONTO_MINIMO|| ')'
        || str_separador
          ||'[MONTO_MAXIMO]('||str_valor||:old.MONTO_MAXIMO|| ')'
        || str_separador
          ||'[ACCION]('||str_valor||:old.ACCION|| ')'
;

        insert into shv_aud_auditoria_param
        values(SEQ_SHV_AUD_AUDITORIA_PARAM.nextval, user, sysdate, str_delete, str_tabla, str_datos, ' ');

      end if;

  End TRG_SHV_PARAM_REGLA_COBRO;
Exit;