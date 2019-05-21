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
	
  execute immediate 'grant select on '||'&1'||'.SEQ_SHV_MAS_OPERACION_ARCH'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SEQ_SHV_MAS_OPERACION_ARCH');

  
  execute immediate 'grant select on '||'&1'||'.SEQ_SHV_MAS_OPERACION_MASIVA'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SEQ_SHV_MAS_OPERACION_MASIVA');


  execute immediate 'grant select on '||'&1'||'.SEQ_SHV_MAS_REGISTRO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SEQ_SHV_MAS_REGISTRO');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_OPER_MASIVA'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA');

  
  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_OPER_MASIVA_ADJUNTO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA_ADJUNTO');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_OPER_MASIVA_ARCH'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA_ARCH');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_APLICAR_DEUDA'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_APLICAR_DEUDA');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_CLI_SIEBEL'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_CLI_SIEBEL');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_CUOTA_CONV'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_CUOTA_CONV');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_DESESTIMIENTO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_DESESTIMIENTO');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_GANANCIAS'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_GANANCIAS');


  execute immediate 'grant select, insert, update, delete on '||'&1'||'.SHV_MAS_REGISTRO_REINTEGRO'||' to '||'&2';
  dbms_output.put_line('Se ha creado exitosamente el grant al usuario '||'&2'||' para el objeto '||'&1'||'SHV_MAS_REGISTRO_REINTEGRO');

Exception
	when others then
		dbms_output.put_line('Error al generar sinonimos: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/	