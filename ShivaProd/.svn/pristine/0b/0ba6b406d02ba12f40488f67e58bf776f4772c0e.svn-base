-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de sinonimos para las nuevas secuencias de SCARD
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
	
  execute immediate 'create or replace synonym SHV_MAS_OPER_MASIV_BUSQUEDA for '||'&1'||'.SHV_MAS_OPER_MASIV_BUSQUEDA';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SEQ_SHV_SCARD_RECIBO para el objeto '||'&1'||'SEQ_SHV_SCARD_RECIBO');

Exception
	when others then
		dbms_output.put_line('Error al generar sinonimos: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/