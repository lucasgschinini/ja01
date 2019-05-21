<div id="sidebar">
	<div class="account">Menu</div>
	<div id="tablero" class="accordion-group">
		<div class="accordion-heading mi-menu">
			<a id="tablero" class="accordion-toggle less" onclick="javascript:contraer('tablero')"
				style="padding-top: 4px;  padding-bottom: 4px;"><strong>Tablero de Control</strong></a>
			<ul id="collapseHome" class="collapse in">
				<li class="current"><a id="aplicacion" href="${pageContext.request.contextPath}/mantenimientoAplicacion">Aplicacion</a></li>
				<li class="current"><a id="trazas" href="${pageContext.request.contextPath}/mantenimientoTrazas">Trazas</a></li>
				<li class="current"><a id="database" href="${pageContext.request.contextPath}/mantenimientoDatabase">Base de Datos</a></li>
				<li class="current"><a id="mail" href="${pageContext.request.contextPath}/mantenimientoMail">Mail</a></li>
				<li class="current"><a id="jms" href="${pageContext.request.contextPath}/mantenimientoJms">Servicio de Mensajeria</a></li>
				<li class="current"><a id="ws" href="${pageContext.request.contextPath}/mantenimientoWS">Web Services</a></li>
			</ul>
		</div>
	</div>
</div><!-- end #sidebar -->

<script>
$(document).ready(function(){
	$("#tablero").click(function(){
	  $("#collapseHome").slideToggle("slow");
	});
	
	$('#aplicacion').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	$('#trazas').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	$('#database').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	$('#mail').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	$('#jms').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	$('#ws').click(function() {
		$('#bloqueEspera').trigger('click');
	});
});
</script>
		