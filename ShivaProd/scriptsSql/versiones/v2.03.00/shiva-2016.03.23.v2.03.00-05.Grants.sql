-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de grants para la vista de operaciones masivas
-----------------------------------------------------------------------------------------------------------------------------
set serveroutput on size unlimited
set linesize 9999
set echo off

Declare
 
Begin
  -- Parametro &1: usuario A
  -- Parametro &2: usuario Z
	
  dbms_output.put_line('Parametro 1 (usuario A): ' ||'&1');
  dbms_output.put_line('Parametro 2 (usuario Z): ' ||'&2');
	
  execute immediate 'grant select on '||'&1'||'.SHV_SOP_OPER_MASIVA_HISTORIAL'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_SOP_OPER_MASIVA_HISTORIAL');

Exception
	when others then
		dbms_output.put_line('Error al generar grants: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/	


