-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de sinonimos para la vista de historial de operaciones masivas
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
	
  execute immediate 'create or replace synonym SHV_SOP_OPER_MASIVA_HISTORIAL for '||'&1'||'.SHV_SOP_OPER_MASIVA_HISTORIAL';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_SOP_OPER_MASIVA_HISTORIAL para el objeto '||'&1'||'SHV_SOP_OPER_MASIVA_HISTORIAL');

Exception
	when others then
		dbms_output.put_line('Error al generar sinonimos: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/