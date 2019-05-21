
-- cambio de version
UPDATE SHV_PARAM_PARAMETRO
SET VALOR_TEXTO = '2.04.01', AUD_REQUERIMIENTO_ORIGEN = 'SPRINT 8'
WHERE CLAVE = 'version';

--COMMIT
 commit;

Exit;