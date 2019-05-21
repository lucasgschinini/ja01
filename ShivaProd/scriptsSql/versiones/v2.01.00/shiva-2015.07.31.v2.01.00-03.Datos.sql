-----------------------------------------------------------------------------------------------------------------------------
-- Actualización de la nueva columna id_tipo_medio_pago
-----------------------------------------------------------------------------------------------------------------------------
update shv_cob_ed_credito set id_tipo_medio_pago = '01' where tipo_credito = 'BOLETA_DEPOSITO_CHEQUE' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '01' where tipo_credito = 'CHEQUE' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '02' where tipo_credito = 'BOLETA_DEPOSITO_CHEQUEDIF' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '02' where tipo_credito = 'CHEQUEDIF' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '03' where tipo_credito = 'BOLETA_DEPOSITO_EFECTIVO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '03' where tipo_credito = 'EFECTIVO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '05' where tipo_credito = 'TRANSFERENCIA' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '06' where tipo_credito = 'BOLETA_DEPOSITO_EFECTIVO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '07' where tipo_credito = 'IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '08' where tipo_credito = 'IMPUESTO_AL_SELLO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '09' where tipo_credito = 'RETENCION_SEGURIDAD_SOCIAL' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '10' where tipo_credito = 'RETENCION_GANANCIA' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '11' where tipo_credito = 'RETENCION_IVA' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '12' where tipo_credito = 'RETENCION_IIBB' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '16' where tipo_credito = 'DESISTIMIENTO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '17' where tipo_credito = 'INTERDEPOSITO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '19' where tipo_credito = 'REMANENTE_NO_ACTUALIZABLE' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '20' where tipo_credito = 'REMANENTE_ACTUALIZABLE' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '21' where tipo_credito = 'PAGOACUENTA' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '22' where tipo_credito = 'NOTACREDITO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '23' where tipo_credito = 'RETENCION_IVA_RG3349' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '24' where tipo_credito = 'IMPUESTO_TASAS_MUNICIPALES' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '25' where tipo_credito = 'PLANPAGO' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '33' where tipo_credito = 'COMPENSACION' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '34' where tipo_credito = 'INTERCOMPANY' and id_tipo_medio_pago is null;
update shv_cob_ed_credito set id_tipo_medio_pago = '35' where tipo_credito = 'LIQUIDOPRODUCTO' and id_tipo_medio_pago is null;
--
update shv_cob_ed_credito set id_tipo_medio_pago = '19' where tipo_credito = 'REMANENTE' and id_tipo_medio_pago is null and subtipo in(1, 2);
update shv_cob_ed_credito set id_tipo_medio_pago = '20' where tipo_credito = 'REMANENTE' and id_tipo_medio_pago is null and subtipo in(3, 4, 5, 6);
--
update shv_cob_ed_credito set id_tipo_medio_pago = '12' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 1;
update shv_cob_ed_credito set id_tipo_medio_pago = '11' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 2;
update shv_cob_ed_credito set id_tipo_medio_pago = '09' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 3;
update shv_cob_ed_credito set id_tipo_medio_pago = '10' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 4;
update shv_cob_ed_credito set id_tipo_medio_pago = '08' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 5;
update shv_cob_ed_credito set id_tipo_medio_pago = '07' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 6;
update shv_cob_ed_credito set id_tipo_medio_pago = '23' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 7;
update shv_cob_ed_credito set id_tipo_medio_pago = '24' where tipo_credito = 'RETENCION' and id_tipo_medio_pago is null and subtipo = 8;
--
commit;