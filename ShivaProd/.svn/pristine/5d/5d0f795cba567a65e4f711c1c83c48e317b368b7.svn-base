set serveroutput on size 50000;

Declare
	cursor cur_id_recibo_preimp_cheque_pd is
	select 
		id, ID_RECIBO_PREIMPRESO 
	from 
		shv_mig_boleta_dep_cheque_pd;
	
	cursor cur_id_recibo_preimp_cheque is
	select 
		id, ID_RECIBO_PREIMPRESO 
	from 
		shv_mig_boleta_deposito_cheque;
	
	cursor cur_id_recibo_preimp_efectivo is
	select 
		id, ID_RECIBO_PREIMPRESO 
	from 
		shv_mig_boleta_dep_efectivo;
  
  idReciboPreimpreso  shv_tal_recibo_preimpreso.id_recibo_preimpreso%type;
  numeroRecibo        shv_tal_recibo_preimpreso.numero_recibo%type;
  numeroSerie         shv_tal_talonario.numero_serie%type := 0001;
  
Begin

  -- Actualización de la tabla Cheque Pago Diferido
	for reg_id_rec_preimp in cur_id_recibo_preimp_cheque_pd loop
		begin
      dbms_output.put_line(' ');
      dbms_output.put_line('Comienza procesamiento para el registro ID: '|| reg_id_rec_preimp.id);
      
      numeroRecibo := lpad(to_char(numeroSerie), 4, '0')||'-'||lpad(to_char(reg_id_rec_preimp.ID_RECIBO_PREIMPRESO), 8, '0');
      dbms_output.put_line('Voy a buscar numeroRecibo: '||numeroRecibo||' en la tabla de recibos preimpresos.');
      
      Begin
        select id_recibo_preimpreso 
        into idReciboPreimpreso
        from shv_tal_recibo_preimpreso
        where numero_recibo = numeroRecibo;
        
        dbms_output.put_line('Encontré el recibo, su idReciboPreimpreso es: '||idReciboPreimpreso);
  
        update shv_mig_boleta_dep_cheque_pd tabla
        set tabla.id_recibo_preimpreso = idReciboPreimpreso
        where tabla.id = reg_id_rec_preimp.id;
  
        dbms_output.put_line('Se actualizó el registro ID: '|| reg_id_rec_preimp.id ||' con el idReciboPreimpreso: '||idReciboPreimpreso);
        
      Exception 
        When no_data_found then 
          dbms_output.put_line('No existe recibo preimpreso para el numero de recibo: '||numeroRecibo);
      End;
      
		end;
	End loop;
  
  -- Actualización de la tabla Cheque
  for reg_id_rec_preimp in cur_id_recibo_preimp_cheque loop
		begin
      dbms_output.put_line(' ');
      dbms_output.put_line('Comienza procesamiento para el registro ID: '|| reg_id_rec_preimp.id);
      
      numeroRecibo := lpad(to_char(numeroSerie), 4, '0')||'-'||lpad(to_char(reg_id_rec_preimp.ID_RECIBO_PREIMPRESO), 8, '0');
      dbms_output.put_line('Voy a buscar numeroRecibo: '||numeroRecibo||' en la tabla de recibos preimpresos.');
      
      Begin
        select id_recibo_preimpreso 
        into idReciboPreimpreso
        from shv_tal_recibo_preimpreso
        where numero_recibo = numeroRecibo;
        
        dbms_output.put_line('Encontré el recibo, su idReciboPreimpreso es: '||idReciboPreimpreso);
  
        update shv_mig_boleta_deposito_cheque tabla_ch
        set tabla_ch.id_recibo_preimpreso = idReciboPreimpreso
        where tabla_ch.id = reg_id_rec_preimp.id;
  
        dbms_output.put_line('Se actualizó el registro ID: '|| reg_id_rec_preimp.id ||' con el idReciboPreimpreso: '||idReciboPreimpreso);
        
      Exception 
        When no_data_found then 
          dbms_output.put_line('No existe recibo preimpreso para el numero de recibo: '||numeroRecibo);
      End;
      
		end;
	End loop;
  
  for reg_id_rec_preimp in cur_id_recibo_preimp_efectivo loop
		begin
      dbms_output.put_line(' ');
      dbms_output.put_line('Comienza procesamiento para el registro ID: '|| reg_id_rec_preimp.id);
      
      numeroRecibo := lpad(to_char(numeroSerie), 4, '0')||'-'||lpad(to_char(reg_id_rec_preimp.ID_RECIBO_PREIMPRESO), 8, '0');
      dbms_output.put_line('Voy a buscar numeroRecibo: '||numeroRecibo||' en la tabla de recibos preimpresos.');
      
      Begin
        select id_recibo_preimpreso 
        into idReciboPreimpreso
        from shv_tal_recibo_preimpreso
        where numero_recibo = numeroRecibo;
        
        dbms_output.put_line('Encontré el recibo, su idReciboPreimpreso es: '||idReciboPreimpreso);
  
        update shv_mig_boleta_dep_efectivo tabla
        set tabla.id_recibo_preimpreso = idReciboPreimpreso
        where tabla.id = reg_id_rec_preimp.id;
  
        dbms_output.put_line('Se actualizó el registro ID: '|| reg_id_rec_preimp.id ||' con el idReciboPreimpreso: '||idReciboPreimpreso);
        
      Exception 
        When no_data_found then 
          dbms_output.put_line('No existe recibo preimpreso para el numero de recibo: '||numeroRecibo);
      End;
      
		end;
	End loop;
  
End;
/
Exit