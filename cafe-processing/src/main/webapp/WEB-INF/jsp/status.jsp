<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
<title><c:out value="Order Status" /></title>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css"
	href="resources/template/form.css">
</head>
<body>
	
		<span id="table"><div id="stylized" class="myform"> <h1>Delivered:</h1> <br>
			<table>
				<c:forEach var="delivery" items="${delivered}">
					<tr>
						<td>${delivery}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</span>
	

	<script type="text/javascript">
		window.onload = reload;
		function reload() {
			jq("#table").load("status");
		}
		var refreshId = setInterval(reload, 10000);
	</script>
</body>