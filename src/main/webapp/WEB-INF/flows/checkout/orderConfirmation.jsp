<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Potwierdzenie zamówienia</title>
</head>

<body>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Zamówienie</h1>
				<p>Potwierdzenie zamówienia</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />

				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<h1>Paragon</h1>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>Adres do wysyłki</strong> <br>
								${order.shippingDetail.shippingAddress.doorNo},${order.shippingDetail.shippingAddress.streetName}
								<br>
								${order.shippingDetail.shippingAddress.city}
								<br>
								${order.shippingDetail.shippingAddress.country},${order.shippingDetail.shippingAddress.zipCode}
								<br>
							</address>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 text-right">
							<p>
								<em>Data zamówienia: <fmt:formatDate type="date"
										value="${order.shippingDetail.shippingDate}" /></em>
							</p>
							<br>Telefon kontaktowy: ${order.user.phoneNumber}
						</div>
					</div>
					<div class="row">

						<table class="table table-hover">
							<thead>
								<tr>
									<th>Produkt</th>
									<th>#</th>
									<th class="text-center">Cena</th>
									<th class="text-center">Łącznie</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartItem" items="${order.cart.cartItems}">
									<tr>
										<td class="col-md-9"><em>${cartItem.value.product.name}</em></td>
										<td class="col-md-1" style="text-align: center">
											${cartItem.value.quantity}</td>
										<td class="col-md-1 text-center">${cartItem.value.product.unitPrice}PLN</td>
										<td class="col-md-1 text-center">${cartItem.value.totalPrice}PLN</td>
									</tr>
								</c:forEach>

								<tr>
									<td> </td>
									<td> </td>
									<td class="text-right"><h4>
											<strong>Razem: </strong>
										</h4></td>
									<td class="text-center text-danger"><h4>
											<strong>${order.cart.grandTotal}PLN</strong>
										</h4></td>
								</tr>
							</tbody>
						</table>
						<button id="back" class="btn btn-default"
							name="_eventId_backToCollectShippingDetail">Powrót</button>

						<button type="submit" class="btn btn-success"
							name="_eventId_orderConfirmed">
							Potwierdź   <span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Anuluj</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
