
-- cambio de version
UPDATE SHV_PARAM_PARAMETRO
SET VALOR_TEXTO = '2.00.01', AUD_REQUERIMIENTO_ORIGEN = 'SPRINT 4'
WHERE CLAVE = 'version';
	
--COMMIT		
 commit;
/
Exit;