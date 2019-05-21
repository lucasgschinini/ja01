-------------------------------------------------------------------------------------------
-- Se agregan campos nuevos para contener datos de respuesta de aplicacion de
-- medios de pago en dolares en las tablas nota de credito calipso y CTA.
-------------------------------------------------------------------------------------------

alter table shv_cob_med_pag_nota_cred_clp add tipo_de_cambio_fecha_emision number(15,7);
alter table shv_cob_med_pag_nota_cred_clp add tipo_de_cambio_fecha_cobro number(15,7);
alter table shv_cob_med_pag_nota_cred_clp add importe_aplic_fec_emis_mon_pes number(17,4);
alter table shv_cob_med_pag_nota_cred_clp add importe_aplic_fec_emis_mon_ori number(17,4);

alter table shv_cob_med_pag_cta add tipo_de_cambio_fecha_emision number(15,7);
alter table shv_cob_med_pag_cta add tipo_de_cambio_fecha_cobro number(15,7);
alter table shv_cob_med_pag_cta add importe_aplic_fec_emis_mon_pes number(17,4);
alter table shv_cob_med_pag_cta add importe_aplic_fec_emis_mon_ori number(17,4);

alter table SHV_COB_FACTURA_CALIPSO add ID_DOC_CTAS_COB_PADRE NUMBER;
alter table SHV_COB_MED_PAG_NOTA_CRED_CLP add ID_DOC_CTAS_COB_PADRE NUMBER;

Exit;