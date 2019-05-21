<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="span9">
		<table id="debitos" class="tablaResultado">
			<thead>
				<tr>
					<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
					<th></th>
					<th>Cliente</th>
					<th>Cuenta</th>
					<th>Sistema</th>
					<th>Tipo Documento</th>
					<th>Subtipo Documento</th>
					<th>Nro. Documento</th>
					<th>Nro. Referencia MIC</th>
					<th class="date">F. Vencimiento</th>
					<th class="importe">Saldo 1er Vto. Moneda Origen</th>
					<th>Moneda</th>
					<th>Importe al 1er Vto.</th>
					<th>Importe al 2do Vto.</th>
					<th>Saldo pesificado F. Emisión</th>
					<th>Estado Cptos. de 3ros</th>
					<th class="importe">Importe 3ros Transferidos</th>
					<th>Estado origen</th>
					<th>Reclamo en origen</th>
					<th>Migrado en origen</th>
					<th>Estado en Deimos</th>
					<th>Pago/compensación en proceso</th>
					<th>Reversión en proceso</th>
					<th>Operación/Analista</th>
					<th>Tipo de Cambio</th>
					<th class="date">F. Emisión</th>
					<th>Nro. Convenio</th>
					<th>Cuota Convenio</th>
					<th class="date">F. ult. pago parcial</th>
				</tr>
			</thead>
		</table>
		<div class="customPag_info" id="customPagDebitos_info">
			<span>Mostrando registros del 0 al 0 de 0 registros
				en total</span>
		</div>
		<div class="customPag_simple" id="customPagDebitos_paginate">
			<a
				class="customPaginate_button customPaginatePrevious disabled"
				id="customPagDebitos_previous">Anterior</a><a
				class="customPaginate_button customPaginateNext disabled"
				id="customPagDebitos_next">Siguiente</a>
		</div>
	</div>
</div>