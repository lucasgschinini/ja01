update shv_cob_documento set tipo_documento ='RECIBO' WHERE tipo_operacion ='COBRO';

update shv_cob_documento set tipo_documento ='NOTA_REEMBOLSO' WHERE tipo_operacion ='DESCOBRO';

commit;

/
Exit;
