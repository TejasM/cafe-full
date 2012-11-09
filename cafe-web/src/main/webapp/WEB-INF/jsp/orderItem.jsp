<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title><c:out value="Create Order Item" /></title>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.8.2.min.js">
	
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
<style>
.error {
	color: red;
}
</style>
<link rel="stylesheet" type="text/css"
	href="resources/template/form.css">
</head>
<body>
	<div id="stylized" class="myform">
		<h1>Create OrderItem:</h1>
		<p> Create a possible item for the order menu</p>
		<form:form commandName="orderItem" id="orderItem"
			onsubmit="return false;">
			<table>
				<tbody>
					<tr>
						<td><form:label path="type">Type:</form:label></td>
						<td><form:select path="type" items="${types}"></form:select>
					</tr>
					<tr>
						<td><form:label path="iced">Iced:</form:label></td>
						<td><form:checkbox path="iced"></form:checkbox>
					</tr>
					<tr>
						<td><form:label path="shots">Shots:</form:label></td>
						<td><form:input path="shots"></form:input>
					</tr>
				</tbody>
			</table>
			<table>
				<tr>
					<td><input type="submit" value="Add Order Item"
						onclick="submitOrderItem()" /></td>
				</tr>
			</table>
		</form:form>
		<br> <span id="success"></span> <br> 
			<a href="<c:url value="order"/>"><button>Create Order</button></a>
			<div class="spacer"></div>
			<a href="<c:url value="orderItem"/>"><button>Create Order Item</button></a>
	</div>

	<script type="text/javascript">
		function submitOrderItem() {
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("orderItem", jq('#orderItem').serialize(), function(
						data) {
					// data contains the result
					// Assign result to the sum id
					jq("#success").replaceWith(
							'<span id="success">' + data + '</span>');
				});
			});
		}
	</script>

</body>