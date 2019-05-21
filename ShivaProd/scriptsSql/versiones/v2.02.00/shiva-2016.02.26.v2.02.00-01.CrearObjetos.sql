-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de nuevas secuencias para recibos y notas de reembolso para SCARD
-----------------------------------------------------------------------------------------------------------------------------
set serveroutput on size unlimited
set linesize 9999

Declare
 
  ultima_secuencia all_sequences.last_number%type;
  
Begin

  select last_number into ultima_secuencia from all_sequences
  where sequence_name = 'SEQ_SHV_SCARD_COMPROBANTE';
  
  execute immediate 'CREATE SEQUENCE SEQ_SHV_SCARD_RECIBO INCREMENT BY 1 START WITH '||to_char(ultima_secuencia + 1)||' MAXVALUE 99999999999999999999999 MINVALUE 1 CACHE 20';
  dbms_output.put_line('Se ha creado exitosamente la secuencia SEQ_SHV_SCARD_RECIBO');
  execute immediate 'CREATE SEQUENCE SEQ_SHV_SCARD_NOTA_REEMBOLSO INCREMENT BY 1 START WITH '||to_char(ultima_secuencia + 1)||' MAXVALUE 99999999999999999999999 MINVALUE 1 CACHE 20';
  dbms_output.put_line('Se ha creado exitosamente la secuencia SEQ_SHV_SCARD_NOTA_REEMBOLSO');

Exception
	when others then
			dbms_output.put_line('Error al generar las secuencias para SCARD: '||SubStr(TO_CHAR(SQLCODE)||': '||SQLERRM, 1, 255));
      
End;
/
Exit;