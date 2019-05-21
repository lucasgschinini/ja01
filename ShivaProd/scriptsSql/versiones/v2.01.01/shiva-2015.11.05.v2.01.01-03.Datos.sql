-- Actualizaciones para SHV_PARAM_PARAMETRO - Version
--
UPDATE SHV_PARAM_PARAMETRO
SET VALOR_TEXTO = '2.01.01'
WHERE CLAVE = 'version';
--

-- Actualizaciones para que el perfil consulta pueda ver la busqueda de cobros
INSERT INTO SHV_MNU_MENU_PERFIL (ID_MENU_PERFIL, ID_PERFIL, ID_MENU) VALUES (213, 24, 24);

/
Exit;