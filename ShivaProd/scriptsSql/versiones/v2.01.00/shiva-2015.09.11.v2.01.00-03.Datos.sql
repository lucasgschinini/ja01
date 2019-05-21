----------------------------------------------------------------------------------------------------------------
-- Actualización de tratamiendo de diferencia "Reintegro x Giro" por "Reintegro Pago Cuenta Terceros"
----------------------------------------------------------------------------------------------------------------

update shv_param_tipo_medio_pago
set descripcion = 'REINTEGRO PAGO CUENTA TERCEROS'
where id_tipo_medio_pago = 15;

commit;

/
Exit;
