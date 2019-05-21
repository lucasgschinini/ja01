<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="menuGenerico" prefix="menu" %>

<div id="sidebar">

	<menu:generadorMenu titulo="Menu" />
	<script>
	function viewUrl(id) {
		location.href="${pageContext.request.contextPath}/"+id;
	}
	</script>

</div>



		