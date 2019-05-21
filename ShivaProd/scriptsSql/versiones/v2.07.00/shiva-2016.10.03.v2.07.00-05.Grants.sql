-----------------------------------------------------------------------------------------------------------------------------
-- Creacion de grants para la parametrica de cotizacion
-----------------------------------------------------------------------------------------------------------------------------
GRANT SELECT, update, insert ,delete ON 	&1 .SHV_PARAM_COTIZACION	 TO 	&2	;
GRANT SELECT, update, insert ,delete ON 	&1 .SHV_COB_MED_PAG_COMP_CES_CRED	 TO 	&2	;
GRANT SELECT, update, insert ,delete ON 	&1 .SHV_COB_MED_PAG_COMP_IIBB	 TO 	&2	;


Exit;


