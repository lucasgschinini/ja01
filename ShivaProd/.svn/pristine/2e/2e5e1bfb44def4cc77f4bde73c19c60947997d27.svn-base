 CREATE OR REPLACE TRIGGER TRG_SHV_PARAM_COTIZACION 
  Before insert or update or delete
  On SHV_PARAM_COTIZACION
  For each row
    Declare

      str_insert    shv_aud_auditoria_param.accion%type            := 'INSERT';
      str_update    shv_aud_auditoria_param.accion%type            := 'UPDATE';
      str_delete    shv_aud_auditoria_param.accion%type            := 'DELETE';

      str_valor     varchar2(20) := 'valor: ';
      str_original  varchar2(20) := 'original: ';
      str_actual    varchar2(20) := 'actual: ';
      str_separador varchar2(1) := '|';

      str_tabla     shv_aud_auditoria_param.tabla%type             := 'SHV_PARAM_COTIZACION';
      str_datos     shv_aud_auditoria_param.datos_modificados%type := null;

    Begin

      -- Acciones a realizar sobre acciones de INSERT
      if inserting then

        str_datos :=
          '[ID_COTIZACION]('||str_valor||:new.ID_COTIZACION|| ')'
        || str_separador
          ||'[FECHA]('||str_valor||:new.FECHA|| ')'
        || str_separador
          ||'[MONEDA]('||str_valor||:new.MONEDA|| ')'
        || str_separador
          ||'[TIPO_CAMBIO]('||str_valor||:new.TIPO_CAMBIO|| ')'
;

        insert into shv_aud_auditoria_param
        values(SEQ_SHV_AUD_AUDITORIA_PARAM.nextval, user, sysdate, str_insert, str_tabla, str_datos, :new.aud_requerimiento_origen);
      end if;

      -- Acciones a realizar sobre acciones de UPDATE
      if updating then

       if (NVL(TO_CHAR(:old.ID_COTIZACION), ' ') <> NVL(TO_CHAR(:new.ID_COTIZACION), ' ')) then
          str_datos := '[ID_COTIZACION]('||str_original||:old.ID_COTIZACION|| ') ('||str_actual||:new.ID_COTIZACION|| ')';
        end if;

       if (NVL(TO_CHAR(:old.FECHA), ' ') <> NVL(TO_CHAR(:new.FECHA), ' ')) then
          str_datos := str_datos||'[FECHA]('||str_original||:old.FECHA|| ') ('||str_actual||:new.FECHA|| ')';
        end if;

       if (NVL(TO_CHAR(:old.MONEDA), ' ') <> NVL(TO_CHAR(:new.MONEDA), ' ')) then
          str_datos := str_datos||'[MONEDA]('||str_original||:old.MONEDA|| ') ('||str_actual||:new.MONEDA|| ')';
        end if;

       if (NVL(TO_CHAR(:old.TIPO_CAMBIO), ' ') <> NVL(TO_CHAR(:new.TIPO_CAMBIO), ' ')) then
          str_datos := str_datos||'[TIPO_CAMBIO]('||str_original||:old.TIPO_CAMBIO|| ') ('||str_actual||:new.TIPO_CAMBIO|| ')';
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
          '[ID_COTIZACION]('||str_valor||:old.ID_COTIZACION|| ')'
        || str_separador
          ||'[FECHA]('||str_valor||:old.FECHA|| ')'
        || str_separador
          ||'[MONEDA]('||str_valor||:old.MONEDA|| ')'
        || str_separador
          ||'[TIPO_CAMBIO]('||str_valor||:old.TIPO_CAMBIO|| ')'
     
;

        insert into shv_aud_auditoria_param
        values(SEQ_SHV_AUD_AUDITORIA_PARAM.nextval, user, sysdate, str_delete, str_tabla, str_datos, ' ');

      end if;

  End TRG_SHV_PARAM_COTIZACION;
  /
  -- se coloca / y no exit; porque el sql plus asi lo requiere