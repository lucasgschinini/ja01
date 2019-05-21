<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!-- Compatibilidad con Internet Explorer 8 y versiones anteriores -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">




<!-- css prueba -->

<link href="${pageContext.request.contextPath}/css/version-1/dataTables.bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/dataTables.foundation.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/dataTables.jqueryui.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/css/version-1/buttons.dataTables.css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath}/css/buttons.dataTables-1.2.2.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/buttons.bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/buttons.foundation.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/buttons.jqueryui.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/css/version-1/jquery.dataTables.css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath}/css/jquery.dataTables-1.10.12.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/version-1/jquery.dataTables_themeroller.css" rel="stylesheet">


<!-- css prueba -->



<!-- js prueba -->
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/version-1/jquery-1.12.0.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery-2.2.3.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/jszip.js"></script>
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/version-1/jquery.dataTables.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables-1.10.12.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/dataTables.foundation.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/dataTables.jqueryui.js"></script>
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/version-1/dataTables.buttons.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/dataTables.buttons.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/buttons.bootstrap.js"></script>
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/version-1/buttons.colVis.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/buttons.colVis-2016.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/buttons.flash.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/buttons.foundation.js"></script>

<script src="${pageContext.request.contextPath}/js/version-1/buttons.jqueryui.js"></script>
<script src="${pageContext.request.contextPath}/js/version-1/buttons.print.js"></script>

<%-- <script src="${pageContext.request.contextPath}/js/version-1/pdfmake.min.js"></script> --%>

<script src="${pageContext.request.contextPath}/js/version-1/vfs_fonts.js"></script>
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/version-1/buttons.html5.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/buttons.html5-1.1.20160328.js"></script>


<!-- js prueba -->
	


<!-- CSS  -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/css/TableTools.css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath}/css/bootstrap-fileupload.css"  rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css"  rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/fixedColumns.bootstrap-3.2.2.css"  rel="stylesheet">

 <!-- CSS DATA TABLE	1.9 No se borrar para poder hacer una vuelta atras -->
<%--  <link href="${pageContext.request.contextPath}/css/jquery.dataTables.css" rel="stylesheet">  --%>
<!-- CSS DATA TABLE	1.10 -->
<%--  <link href="${pageContext.request.contextPath}/css/jquery.dataTables-1.10.css" rel="stylesheet"> 	 --%>
<!-- JS  -->
<%-- <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>       --%>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.datepicker-es.js"></script>
<script src="${pageContext.request.contextPath}/js/spin.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.functions.js"></script>
<!-- Cambio a version actual -->
<%-- <script src="${pageContext.request.contextPath}/js/dataTables.fixedColumns.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/dataTables.fixedColumns-3.2.2.js"></script>





<!-- DATA TABLE	1.9 No se borrar para poder hacer una vuelta atras -->
<%--  <script src="${pageContext.request.contextPath}/js/jquery.dataTables.nightly.js"></script>  --%>
<!-- DATA TABLE	1.10 -->
<%--  <script src="${pageContext.request.contextPath}/js/jquery.dataTables-1.10.js"></script> --%>
<!-- PLUGINS DE DATA TABLE -->
<%--  <script src="${pageContext.request.contextPath}/js/TableTools.js"></script>		  --%>
<script src="${pageContext.request.contextPath}/js/ZeroClipboard.js"></script>		
<script src="${pageContext.request.contextPath}/js/date-latam.js"></script>


<!-- Funciones Comunes Custom -->
<script src="${pageContext.request.contextPath}/js/Util.Tabla.js"></script>		
<script src="${pageContext.request.contextPath}/js/Util.Menu.js"></script>	

<!-- PLUGINS PARA SUBIR UN ARCHIVO -->
<script src="${pageContext.request.contextPath}/js/bootstrap-fileupload.min.js"></script>
  
<!-- Pagina de bloques -->
<script src="${pageContext.request.contextPath}/js/bootbox.js"></script>

<script src="${pageContext.request.contextPath}/js/excepcion.js"></script>

<jsp:include page="../template/bloques.jsp"></jsp:include>

<script type="text/javascript">
var urlAsynchronousHTTP='${pageContext.request.contextPath}';
var versionShiva='<%=Constantes.VERSION_APLICACION%>';
var limiteParaExportarBusquedaValores = '${limiteParaExportarBusquedaValores}';
var limiteParaExportarBusquedaCobros = '${limiteParaExportarBusquedaCobros}';
</script>

