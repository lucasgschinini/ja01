-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de grants para las nuevas secuencias de SCARD
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
	
  execute immediate 'grant select on '||'&1'||'.SEQ_SHV_SCARD_RECIBO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SEQ_SHV_SCARD_RECIBO');

  execute immediate 'grant select on '||'&1'||'.SEQ_SHV_SCARD_NOTA_REEMBOLSO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SEQ_SHV_SCARD_NOTA_REEMBOLSO');

Exception
	when others then
		dbms_output.put_line('Error al generar sinonimos: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/	