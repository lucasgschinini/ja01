set serveroutput on size 50000;
Declare

  ---------------------------------------------------------------------------------------------------------------
  -- Procedimiento que reinicia a 0 una sequencia dada
  ---------------------------------------------------------------------------------------------------------------
  Procedure reiniciarSecuencia ( nombreSecuencia in varchar2 ) is
      res number;
    Begin
      dbms_output.put_line('    .Voy a reiniciar la secuencia '||nombreSecuencia);
      execute immediate 'select ' || nombreSecuencia || '.nextval from dual' INTO res;
      execute immediate 'alter sequence ' || nombreSecuencia || ' increment by -' || res || ' minvalue 0';
      execute immediate 'select ' || nombreSecuencia || '.nextval from dual' INTO res;
      execute immediate 'alter sequence ' || nombreSecuencia || ' increment by 1 minvalue 0';
      dbms_output.put_line('      .Secuencia reiniciada!');
    End;
  
  ---------------------------------------------------------------------------------------------------------------
  -- Procedimiento para limpiar secuencias
  ---------------------------------------------------------------------------------------------------------------
  Procedure reiniciarSecuencias is
      cursor misSecuencias is
        select sequence_name as nombre from all_sequences
        where sequence_name like '%SHV%'
        and sequence_name not like '%PARAM%';
    Begin
      for secuencia in misSecuencias loop
        reiniciarSecuencia(secuencia.nombre);
      end loop;
    End;

  ---------------------------------------------------------------------------------------------------------------
  -- Procedimiento que limpia una tabla
  ---------------------------------------------------------------------------------------------------------------
  Procedure limpiarTabla (nombreTabla in varchar2) is 
    cantidadRegistros number;
    Begin
      dbms_output.put_line('    .Voy a limpiar la tabla '||nombreTabla);
      execute immediate 'delete from ' || nombreTabla;
      dbms_output.put_line('      .Tabla blanqueda!');
      
      execute immediate 'select count(*) from '||nombreTabla into cantidadRegistros;
      dbms_output.put_line('      .Cantidad de registros: '||cantidadRegistros);
    End;

  ---------------------------------------------------------------------------------------------------------------
  -- Procedimiento que limpia todas las tablas de la base (a excepcion de param, menu y seguridad)
  ---------------------------------------------------------------------------------------------------------------
  Procedure limpiarTablas is 
    Begin
      -- Workflow Tareas
      limpiarTabla('shv_wf_tarea_registro_avc');
      limpiarTabla('shv_wf_tarea_talonario');
      limpiarTabla('shv_wf_tarea_valor');
      limpiarTabla('shv_wf_tarea_valor_reversion');
      -- Migracion
      limpiarTabla('sHV_MIG_BOL_DEP_CHEQUE_PD_ER');
      limpiarTabla('sHV_MIG_BOLETA_DEP_CHEQUE_ER');
      limpiarTabla('sHV_MIG_BOLETA_DEP_CHEQUE_PD');
      limpiarTabla('sHV_MIG_BOLETA_DEP_EFECTIVO');
      limpiarTabla('sHV_MIG_BOLETA_DEP_EFECTIVO_ER');
      limpiarTabla('sHV_MIG_BOLETA_DEPOSITO_CHEQUE');
      limpiarTabla('sHV_MIG_BOLETA_SIN_VALOR');
      limpiarTabla('sHV_MIG_BOLETA_SIN_VALOR_ER');
      limpiarTabla('sHV_MIG_CONSTANCIA');
      limpiarTabla('sHV_MIG_EQUIVALENCIA');
      limpiarTabla('sHV_MIG_REG_AVC_ARCHIVOS_ER');
      limpiarTabla('sHV_MIG_REG_AVC_CHEQUE_PD');
      limpiarTabla('sHV_MIG_REG_AVC_CHEQUE_PD_ER');
      limpiarTabla('sHV_MIG_REG_AVC_DEP_EFEC_ER');
      limpiarTabla('sHV_MIG_REG_AVC_DEP_EFECTIVO');
      limpiarTabla('sHV_MIG_REG_AVC_TRANSFE_ER');
      limpiarTabla('sHV_MIG_REG_AVC_TRANSFERENCIA');
      limpiarTabla('sHV_MIG_REGISTRO_AVC_ARCHIVOS');
      limpiarTabla('sHV_MIG_REGISTRO_AVC_CHEQUE');
      limpiarTabla('sHV_MIG_REGISTRO_AVC_CHEQUE_ER');
      limpiarTabla('sHV_MIG_VALOR_CHEQUE');
      limpiarTabla('sHV_MIG_VALOR_CHEQUE_DIFERIDO');
      limpiarTabla('sHV_MIG_VALOR_CHEQUE_ER');
      limpiarTabla('sHV_MIG_VALOR_CHEQUE_PD_ER');
      limpiarTabla('sHV_MIG_VALOR_EFECTIVO');
      limpiarTabla('sHV_MIG_VALOR_EFECTIVO_ER');
      limpiarTabla('sHV_MIG_VALOR_INTERDEPOSITO');
      limpiarTabla('sHV_MIG_VALOR_INTERDEPOSITO_ER');
      limpiarTabla('sHV_MIG_VALOR_TRANSFERENCIA');
      limpiarTabla('sHV_MIG_VALOR_TRANSFERENCIA_ER');
      -- Cobros
      limpiarTabla('sHV_COB_DOCUMENTO');
      limpiarTabla('sHV_COB_TRANSACCION_MSJ_FACT');
      limpiarTabla('sHV_COB_TRANSACCION_MSJ_MP');
      limpiarTabla('sHV_COB_TRANSACCION_MSJ_DET');
      limpiarTabla('sHV_COB_MED_PAG_COMPENSACION');
      limpiarTabla('sHV_COB_MED_PAG_CTA');
      limpiarTabla('sHV_COB_MED_PAG_DESISTIMIENTO');
      limpiarTabla('sHV_COB_MED_PAG_NOTA_CRED_CLP');
      limpiarTabla('sHV_COB_MED_PAG_NOTA_CRED_MIC');
      limpiarTabla('sHV_COB_MED_PAG_PLAN_PAGO');
      limpiarTabla('sHV_COB_MED_PAG_REMANENTE');
      limpiarTabla('sHV_COB_MED_PAG_TRAN_PROX_FACT');
      limpiarTabla('sHV_COB_MED_PAG_SHIVA');
      limpiarTabla('sHV_COB_MED_PAG_USUARIO');
      limpiarTabla('sHV_COB_MED_PAG_MIC');
      limpiarTabla('sHV_COB_MED_PAG_CALIPSO');
      limpiarTabla('sHV_COB_MED_PAGO');
      limpiarTabla('sHV_COB_FACTURA_CALIPSO');
      limpiarTabla('sHV_COB_FACTURA_MIC');
      limpiarTabla('sHV_COB_FACTURA');
      limpiarTabla('sHV_COB_PLANILLA_MOVIKA');
      limpiarTabla('sHV_COB_COBRO');
      limpiarTabla('sHV_COB_DESCOBRO');
      limpiarTabla('sHV_COB_TRANSACCION');
      limpiarTabla('sHV_COB_OPERACION');
      -- Relaciones Registros AVC / Valores / Boletas
      limpiarTabla('sHV_REL_REGISTRO_AVC_BOLETA');
      limpiarTabla('sHV_REL_REGISTRO_AVC_VALOR');
      -- Registros AVC
      limpiarTabla('sHV_AVC_REG_ADJUNTO');
      limpiarTabla('sHV_AVC_REG_AVC_BOLETA');
      limpiarTabla('sHV_AVC_REG_AVC_VALOR');
      limpiarTabla('sHV_AVC_REG_AVC_CHEQUE');
      limpiarTabla('sHV_AVC_REG_AVC_CHEQUE_PD');
      limpiarTabla('sHV_AVC_REG_AVC_EFECTIVO');
      limpiarTabla('sHV_AVC_REG_AVC_DEPOSITO');
      limpiarTabla('sHV_AVC_REG_AVC_INTERDEPOSITO');
      limpiarTabla('sHV_AVC_REG_AVC_TRANSFERENCIA');
      limpiarTabla('sHV_AVC_REGISTRO_AVC');
      limpiarTabla('sHV_AVC_ARCHIVO_AVC');
      -- Contabilidad
      limpiarTabla('sHV_CNT_CONTABILIDAD_DETALLE');
      limpiarTabla('sHV_CNT_CONTABILIDAD_CABECERA');
      -- Constancia de Recepcion
      limpiarTabla('sHV_VAL_CONSTANCIA_RECEP_VALOR');
      limpiarTabla('sHV_VAL_CONSTANCIA_RECEPCION');
      -- Boletas
      limpiarTabla('sHV_BOL_BOLETA_CON_VALOR');
      limpiarTabla('sHV_BOL_BOLETA_SIN_VALOR');
      limpiarTabla('sHV_VAL_BOLETA_DEP_CHEQUE');
      limpiarTabla('sHV_VAL_BOLETA_DEP_CHEQUE_PD');
      limpiarTabla('sHV_VAL_BOLETA_DEP_EFECTIVO');
      limpiarTabla('sHV_BOL_BOLETA');
      -- Valores
      limpiarTabla('sHV_VAL_VALOR_ADJUNTO');
      limpiarTabla('sHV_VAL_REVERSION_VALOR');
      limpiarTabla('sHV_VAL_VALOR_CHEQUE');
      limpiarTabla('sHV_VAL_VALOR_CHEQUE_PD');
      limpiarTabla('sHV_VAL_VALOR_EFECTIVO');
      limpiarTabla('sHV_VAL_VALOR_INTERDEPOSITO');
      limpiarTabla('sHV_VAL_VALOR_POR_REVERSION');
      limpiarTabla('sHV_VAL_VALOR_RETENCION');
      limpiarTabla('sHV_VAL_VALOR_TRANSFERENCIA');
      limpiarTabla('sHV_VAL_VALOR');
      -- Adjuntos
      limpiarTabla('sHV_DOC_DOCUMENTO_ADJUNTO');
      -- Talonarios
      limpiarTabla('shv_tal_compensacion');
      limpiarTabla('shv_tal_recibo_preimpreso');
      limpiarTabla('shv_tal_talonario');
      -- Workflows
      limpiarTabla('shv_wf_tarea');
      limpiarTabla('shv_wf_workflow_estado');
      limpiarTabla('shv_wf_workflow_estado_hist');
      limpiarTabla('shv_wf_workflow');
    End;

Begin
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('Comienza proceso reset...');
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('');
  dbms_output.put_line('  Reiniciando secuencias..');
  reiniciarSecuencias;
  dbms_output.put_line('  Secuencias reiniciadas con exito!');
  dbms_output.put_line('');
  dbms_output.put_line('  Limpiando tablas...');
  limpiarTablas;
  dbms_output.put_line('  Tablas blanquedas con exito!');
  dbms_output.put_line('');
  commit;
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('Fin del proceso de reset.');
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  
exception
  when others then
    dbms_output.put_line('Fin del proceso de reset con ERRORES!');
    dbms_output.put_line(SubStr('Error '||TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
  raise;  
End;
/
exit