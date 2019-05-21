set serveroutput on size unlimited;
set linesize 9999;

declare
    cursor cursorEdCobro is
	  select ID_COBRO idCobro
	  from shv_cob_ed_cobro order by id_cobro;                                                                    
                                                                     
begin
	dbms_output.put_line('Se inicia la actualizacion ');
	
	for edCobro in cursorEdCobro loop
	
		update SHV_COB_ED_COBRO set ID_ANALISTA_TEAM_COMERCIAL =
			(select 
			case when (select COUNT(TEAM.USER_ANALISTA_COBRANZA_DATOS)
				FROM SHV_COB_ED_CLIENTE CEC2,
				SHV_PARAM_TEAM_COMERCIAL TEAM    
				where CEC2.ID_COBRO    = SCEC.ID_COBRO
				and TEAM.NRO_DE_CLIENTE=TO_CHAR(CEC2.ID_CLIENTE_LEGADO)
				) = 1
				then 
				(SELECT TEAM.USER_ANALISTA_COBRANZA_DATOS
			   FROM SHV_COB_ED_CLIENTE CEC2,
				SHV_PARAM_TEAM_COMERCIAL TEAM    
				where CEC2.ID_COBRO = SCEC.ID_COBRO
				and TEAM.NRO_DE_CLIENTE = TO_CHAR(CEC2.ID_CLIENTE_LEGADO)
				and rownum=1
				)
				else null
				end
				from SHV_COB_ED_COBRO SCEC
				where id_cobro = edCobro.idCobro
			), ANALISTA_TEAM_COMERCIAL = 
			(select
				case when (select COUNT(TEAM.ANALISTA_COBRANZA_DATOS)
				FROM SHV_COB_ED_CLIENTE CEC2,
				SHV_PARAM_TEAM_COMERCIAL TEAM    
				where CEC2.ID_COBRO    = SCEC.ID_COBRO
				and TEAM.NRO_DE_CLIENTE=TO_CHAR(CEC2.ID_CLIENTE_LEGADO)
				) = 1
				then 
				(SELECT TEAM.ANALISTA_COBRANZA_DATOS
			   FROM SHV_COB_ED_CLIENTE CEC2,
				SHV_PARAM_TEAM_COMERCIAL TEAM    
				where CEC2.ID_COBRO = SCEC.ID_COBRO
				and TEAM.NRO_DE_CLIENTE = TO_CHAR(CEC2.ID_CLIENTE_LEGADO)
				and rownum=1
				)
				else 'Grupo de analistas'
				end
			from SHV_COB_ED_COBRO SCEC
			where id_cobro = edCobro.idCobro
			) where id_cobro = edCobro.idCobro;
	
	end loop;
	
	commit;
	
	dbms_output.put_line('Se finaliza la actualizacion ');
end;
/
Exit;