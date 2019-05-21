
-- cambio de version
UPDATE SHV_PARAM_PARAMETRO
SET VALOR_TEXTO = '2.03.00', AUD_REQUERIMIENTO_ORIGEN = 'SPRINT 8'
WHERE CLAVE = 'version';
	
--COMMIT		
 commit;

Exit;