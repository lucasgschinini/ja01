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
	
  execute immediate 'create or replace synonym SHV_MAS_OPER_MASIVA for '||'&1'||'.SHV_MAS_OPER_MASIVA';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_OPER_MASIVA para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA');

  	
  execute immediate 'create or replace synonym SHV_MAS_OPER_MASIVA_ADJUNTO for '||'&1'||'.SHV_MAS_OPER_MASIVA_ADJUNTO';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_OPER_MASIVA_ADJUNTO para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA_ADJUNTO');

	
  execute immediate 'create or replace synonym SHV_MAS_OPER_MASIVA_ARCH for '||'&1'||'.SHV_MAS_OPER_MASIVA_ARCH';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_OPER_MASIVA_ARCH para el objeto '||'&1'||'SHV_MAS_OPER_MASIVA_ARCH');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO for '||'&1'||'.SHV_MAS_REGISTRO';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO para el objeto '||'&1'||'SHV_MAS_REGISTRO');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_APLICAR_DEUDA for '||'&1'||'.SHV_MAS_REGISTRO_APLICAR_DEUDA';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_APLICAR_DEUDA para el objeto '||'&1'||'SHV_MAS_REGISTRO_APLICAR_DEUDA');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_CLI_SIEBEL for '||'&1'||'.SHV_MAS_REGISTRO_CLI_SIEBEL';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_CLI_SIEBEL para el objeto '||'&1'||'SHV_MAS_REGISTRO_CLI_SIEBEL');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_CUOTA_CONV for '||'&1'||'.SHV_MAS_REGISTRO_CUOTA_CONV';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_CUOTA_CONV para el objeto '||'&1'||'SHV_MAS_REGISTRO_CUOTA_CONV');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_DESESTIMIENTO for '||'&1'||'.SHV_MAS_REGISTRO_DESESTIMIENTO';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_DESESTIMIENTO para el objeto '||'&1'||'SHV_MAS_REGISTRO_DESESTIMIENTO');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_GANANCIAS for '||'&1'||'.SHV_MAS_REGISTRO_GANANCIAS';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_GANANCIAS para el objeto '||'&1'||'SHV_MAS_REGISTRO_GANANCIAS');

	
  execute immediate 'create or replace synonym SHV_MAS_REGISTRO_REINTEGRO for '||'&1'||'.SHV_MAS_REGISTRO_REINTEGRO';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SHV_MAS_REGISTRO_REINTEGRO para el objeto '||'&1'||'SHV_MAS_REGISTRO_REINTEGRO');

  	
  execute immediate 'create or replace synonym SEQ_SHV_MAS_OPERACION_ARCH for '||'&1'||'.SEQ_SHV_MAS_OPERACION_ARCH';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SEQ_SHV_MAS_OPERACION_ARCH para el objeto '||'&1'||'SEQ_SHV_MAS_OPERACION_ARCH');

  	
  execute immediate 'create or replace synonym SEQ_SHV_MAS_OPERACION_MASIVA for '||'&1'||'.SEQ_SHV_MAS_OPERACION_MASIVA';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SEQ_SHV_MAS_OPERACION_MASIVA para el objeto '||'&1'||'SEQ_SHV_MAS_OPERACION_MASIVA');

	
  execute immediate 'create or replace synonym SEQ_SHV_MAS_REGISTRO for '||'&1'||'.SEQ_SHV_MAS_REGISTRO';
  dbms_output.put_line('Se ha creado exitosamente el sinonimo SEQ_SHV_MAS_REGISTRO para el objeto '||'&1'||'SEQ_SHV_MAS_REGISTRO');


Exception
	when others then
		dbms_output.put_line('Error al generar sinonimos: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/