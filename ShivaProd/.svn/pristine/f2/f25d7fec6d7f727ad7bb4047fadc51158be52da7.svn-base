set serveroutput on size 50000;
Declare

Begin
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('Comienza proceso actualizacion secuencia constancia recepcion x migracion...');
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('');
  dbms_output.put_line('  Eliminando la secuencia...');

  begin
	execute immediate 'drop sequence SEQ_SHV_VAL_CONSTANCIA_RECEP';
  exception 
	when others then
	dbms_output.put_line('La secuencia SEQ_SHV_VAL_CONSTANCIA_RECEP no existe.');
  end;
  
  dbms_output.put_line('  Creando la secuencia...');
  execute immediate 'create sequence SEQ_SHV_VAL_CONSTANCIA_RECEP minvalue 5000 maxvalue 99999999999999999999999 increment by 1 start with 5000 cache 20 noorder nocycle';
  
  dbms_output.put_line('  Secuencia reiniciada con exito!');
  dbms_output.put_line('');

  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  dbms_output.put_line('Fin proceso actualizacion secuencia constancia recepcion x migracion...');
  dbms_output.put_line('-----------------------------------------------------------------------------------------');
  
exception
  when others then
    dbms_output.put_line('Fin del proceso de reset con ERRORES!');
    dbms_output.put_line(SubStr('Error '||TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
  raise;  
End;
/
exit