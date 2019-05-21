set serveroutput on size 50000;

Declare
  
  -- Cursor para recorrer las constancias de recepcion, que tengan su equivalencia en la tabla de migraciones
  cursor cursor_constancias_recepcion is
  select 
    cons.rowid, 
    cons.id_constancia_recepcion,
    mig.id_constancia_migracion
  from 
    shv_val_constancia_recepcion cons,
    shv_mig_constancia mig
  where cons.id_constancia_recepcion = mig.id_constancia_recepcion;
    
  -- Cursor para recorrer las constancias de recepcion por el campo padre, que tengan su equivalencia en la tabla de migraciones
  cursor cursor_constancias_recep_padre is
  select 
    cons.rowid, 
    cons.id_constancia_recepcion_padre,
    mig.id_constancia_migracion
  from 
    shv_val_constancia_recepcion cons,
    shv_mig_constancia mig
  where cons.id_constancia_recepcion_padre = mig.id_constancia_recepcion;

  -- Cursor para recorrer las constancias de recepcion, que tengan su equivalencia en la tabla de migraciones
  cursor cursor_constancias_recep_valor is
  select 
    consValor.rowid, 
    consValor.id_constancia_recepcion,
    mig.id_constancia_migracion
  from 
    shv_val_constancia_recep_valor consValor,
    shv_mig_constancia mig
  where consValor.id_constancia_recepcion = mig.id_constancia_recepcion;
  
  -- Variable temporal para secuencia
  id_constancia_valor_actual_seq  shv_val_constancia_recep_valor.id_constancia_recepcion%type;
  id_constancia_valor_actual_tab  shv_val_constancia_recep_valor.id_constancia_recepcion%type;
  id_constancia_valor_variable    shv_val_constancia_recep_valor.id_constancia_recepcion%type;
  id_constancia_valor_aux         shv_val_constancia_recep_valor.id_constancia_recepcion%type;

  contador  number := 0;

Begin
  
  dbms_output.put_line('---------------------------------------------------------------------------');
  dbms_output.put_line('Comienza proceso actualización de ID de Constacias de Recepción');
  dbms_output.put_line('---------------------------------------------------------------------------');

  -- Esta constraint debiera ser eliminada de la base, no tiene sentido ya que luego de este procesamiento, pierde valor.
  -- execute immediate 'alter table SHV_MIG_CONSTANCIA disable constraint ID_CONSTANCIA_RECEPCION_FK';
  
  -- Deshabilito las constraints
  execute immediate 'alter table SHV_VAL_CONSTANCIA_RECEPCION disable constraint VALOR_ID_CONST_RECEP_PADRE_FK';
  execute immediate 'alter table SHV_VAL_CONSTANCIA_RECEP_VALOR disable constraint VALOR_ID_CONSTANCIA_RECEP_FK';
  
  --
  -- Actualizacion de ID de constancia de recepcion por valor
  --
  for reg_constancia_recep_valor in cursor_constancias_recep_valor loop
    Begin
      update shv_val_constancia_recep_valor 
      set id_constancia_recepcion = reg_constancia_recep_valor.id_constancia_migracion
      where 
      rowid = reg_constancia_recep_valor.rowid
      and id_constancia_recepcion = reg_constancia_recep_valor.id_constancia_recepcion;
      
      dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEP_VALOR" - Se ha actualizado el ID de Constancia: '||reg_constancia_recep_valor.id_constancia_recepcion||' con el valor: '||reg_constancia_recep_valor.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recep_valor.rowid);
    Exception
      when others then
        dbms_output.put_line('Error al tratar de realizar la siguiente actualizacion:');  
        dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEP_VALOR" - ID de Constancia: '||reg_constancia_recep_valor.id_constancia_recepcion||' con el valor: '||reg_constancia_recep_valor.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recep_valor.rowid);  
        dbms_output.put_line('');
    End;
  End loop;  

  --
  -- Actualizacion de ID de constancia de recepcion padre. En teoria esto no debiera darse, pero lo ponemos por si acaso
  --
  for reg_constancia_recep_padre in cursor_constancias_recep_padre loop
    Begin
      update shv_val_constancia_recepcion 
      set id_constancia_recepcion_padre = reg_constancia_recep_padre.id_constancia_migracion
      where 
      rowid = reg_constancia_recep_padre.rowid
      and id_constancia_recepcion_padre = reg_constancia_recep_padre.id_constancia_recepcion_padre;
      
      dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEPCION" - Se ha actualizado el ID de Constancia Padre: '||reg_constancia_recep_padre.id_constancia_recepcion_padre||' con el valor: '||reg_constancia_recep_padre.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recep_padre.rowid);
    Exception
      when others then
        dbms_output.put_line('Error al tratar de realizar la siguiente actualizacion:');  
        dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEPCION" - ID de Constancia Padre: '||reg_constancia_recep_padre.id_constancia_recepcion_padre||' con el valor: '||reg_constancia_recep_padre.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recep_padre.rowid);
        dbms_output.put_line('');
    End;
  End loop;
  
  --
  -- Actualizacion de ID de constancia de recepcion
  --
  for reg_constancia_recepcion in cursor_constancias_recepcion loop
    Begin
      update shv_val_constancia_recepcion 
      set id_constancia_recepcion = reg_constancia_recepcion.id_constancia_migracion
      where 
      rowid = reg_constancia_recepcion.rowid
      and id_constancia_recepcion = reg_constancia_recepcion.id_constancia_recepcion;
      
      dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEPCION" - Se ha actualizado el ID de Constancia: '||reg_constancia_recepcion.id_constancia_recepcion||' con el valor: '||reg_constancia_recepcion.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recepcion.rowid);
    Exception
      when DUP_VAL_ON_INDEX then
        dbms_output.put_line('Error al tratar de realizar la siguiente actualizacion:');  
        dbms_output.put_line('ID de Constancia de Recepcion ya existente');  
        dbms_output.put_line('Tabla "SHV_VAL_CONSTANCIA_RECEPCION" - ID de Constancia: '||reg_constancia_recepcion.id_constancia_recepcion||' con el valor: '||reg_constancia_recepcion.id_constancia_migracion||' para el registro con rowID: '||reg_constancia_recepcion.rowid);
        
        delete from shv_val_constancia_recepcion 
        where 
        rowid = reg_constancia_recepcion.rowid
        and id_constancia_recepcion = reg_constancia_recepcion.id_constancia_recepcion;
        
        dbms_output.put_line('Registro eliminado por ID duplicado!');
        dbms_output.put_line('');
        
    End;
  End loop;
  
  --
  -- Habilito las contraints
  --
  execute immediate 'alter table SHV_VAL_CONSTANCIA_RECEPCION enable constraint VALOR_ID_CONST_RECEP_PADRE_FK';
  execute immediate 'alter table SHV_VAL_CONSTANCIA_RECEP_VALOR enable constraint VALOR_ID_CONSTANCIA_RECEP_FK';
  
  --
  -- Hacer el calculo de mayor valor de ID de Constancia, y avanzar la secuencia hasta un valor mas alto
  --

  -- Busco el valor actual de la secuencia de constancia de recepcion
  select SEQ_SHV_VAL_CONSTANCIA_RECEP.NEXTVAL into id_constancia_valor_actual_seq from dual;
  
  -- Busco el valor mas alto de constancia de recepcion en las tablas
  select max(id_constancia_recepcion) into id_constancia_valor_actual_tab from shv_val_constancia_recepcion;

  -- Me quedo con el valor mayor
  if id_constancia_valor_actual_seq >= id_constancia_valor_actual_tab then
    id_constancia_valor_variable := id_constancia_valor_actual_seq;
  else
    id_constancia_valor_variable := id_constancia_valor_actual_tab;
  end if;

  --
  -- Calculo del ID al cuál debemos llevar la secuencia
  --
  
  -- Para el numero actual, lo llevo a decimales para usar la funcion ROUND
  -- Uso un auxiliar para este tratamiento
  id_constancia_valor_aux := id_constancia_valor_variable;
  
  while id_constancia_valor_aux > 1 loop
    id_constancia_valor_aux := id_constancia_valor_aux / 10;
    contador := contador + 1;
  end loop;
 
  -- Ya tengo el decimal, ahora le aplico el ROUND
  select round(id_constancia_valor_aux, 0) into id_constancia_valor_aux from dual;
  
  -- Ahora llevo el numero calculado al nivel de digitos que tenia el numero original
  for i in 1..contador loop
    id_constancia_valor_aux := id_constancia_valor_aux * 10;
  end loop;

  -- El numero de constancia para la secuencia es este
  dbms_output.put_line('ID de Constancia a usar para las futuras altas: '||id_constancia_valor_aux);

  -- Avanzo la sequencia hasta su valor. Para ello, elimino la secuencia y la creo comenzando con el numero que me interesa
  -- if id_constancia_valor_aux > 0 then
  --  execute immediate 'drop sequence SEQ_SHV_VAL_CONSTANCIA_RECEP';
  --  execute immediate 'create sequence SEQ_SHV_VAL_CONSTANCIA_RECEP minvalue 1 maxvalue 99999999999999999999999 increment by 1 start with '||id_constancia_valor_aux||' cache 20 noorder nocycle';
  --end if;
  
  commit;
  
  dbms_output.put_line('---------------------------------------------------------------------------');
  dbms_output.put_line('Fin proceso actualización de ID de Constancias de Recepcion');
  dbms_output.put_line('---------------------------------------------------------------------------');

  exception
  when others then
	  dbms_output.put_line('Fin proceso actualización de ID de Constancias de Recepcion con ERRORES!');
      dbms_output.put_line(SubStr('Error '||TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
  raise; 
  
End;
/
exit

