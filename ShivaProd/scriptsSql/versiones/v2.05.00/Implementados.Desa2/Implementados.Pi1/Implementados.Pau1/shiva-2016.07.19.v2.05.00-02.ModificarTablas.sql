--shv_cob_med_pag_plan_pago
alter table SHV_COB_MED_PAG_PLAN_PAGO add TIPO_CAMBIO NUMBER(29,12);
--shv_cob_med_pag_desistimiento
alter table SHV_COB_MED_PAG_DESISTIMIENTO add TIPO_CAMBIO NUMBER(29,12);
--shv_cob_med_pag_compensacion
alter table SHV_COB_MED_PAG_COMPENSACION add TIPO_CAMBIO NUMBER(29,12);
--shv_cob_med_pag_comp_intercom
alter table SHV_COB_MED_PAG_COMP_INTERCOM add TIPO_CAMBIO NUMBER(29,12);
--shv_cob_med_pag_comp_liquido
alter table SHV_COB_MED_PAG_COMP_LIQUIDO add TIPO_CAMBIO NUMBER(29,12);
Exit;