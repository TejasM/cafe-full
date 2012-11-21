<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript"
	src="resources/js/jquery/jquery-1.8.2.min.js">
	
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
<title><c:out value="Create Order" /></title>
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
		<h1>Create Order:</h1>
		<p> Select Items to Order</p>
		<br>
		<form:form commandName="order" id="order" onsubmit="return false;">
			<table>
				<tbody>
					<tr>
						<td><form:label path="orderItems">Order Items:</form:label></td>
						<td><form:select path="orderItems" items="${orderItems}"></form:select>
					</tr>
				</tbody>
			</table>
			<table>
				<tr>
					<td><input type="submit" value="Submit Order" onclick="submitOrder()" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit for Batch Processing" onclick="submitOrderBatch()" /></td>
				</tr>
			</table>
		</form:form>
		<br> <span id="success"></span> <br>
		<a href="<c:url value="order"/>"><button>Create Order</button></a>
		<div class="spacer"></div>
		<a href="<c:url value="orderItem"/>"><button>Create Order Item</button></a>
	</div>



	<script type="text/javascript">
		function submitOrder() {					
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("order", jq('#order').serialize(), function(data) {
					// data contains the result
					// Assign result to the sum id
					jq("#success").replaceWith(
							'<span id="success">' + data + '</span>');
					jq("#table").load("/cafe/status");
				});
			});
		}
	</script>
	<script type="text/javascript">
		function submitOrderBatch() {					
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("order/batch", jq('#order').serialize(), function(data) {
					// data contains the result
					// Assign result to the sum id
					jq("#success").replaceWith(
							'<span id="success">' + data + '</span>');
					jq("#table").load("/cafe/status");
				});
			});
		}
	</script>

</body>