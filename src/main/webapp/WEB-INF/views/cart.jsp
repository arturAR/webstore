<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="/webstore/resource/js/controllers.js"></script>

	<section class="container" ng-app="cartApp">
		<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">

			<div>
				<a class="btn btn-danger pull-left" ng-click="clearCart()"> 
					<span class="glyphicon glyphicon-remove-sign"></span> Wyczyść koszyk
				</a> 
				<a href="<spring:url value="/checkout?cartId=${cartId}"/>" class="btn btn-success pull-right"> <span
					class="glyphicon-shopping-cart glyphicon"></span> Kupuję
				</a>
			</div>
			<table class="table table-hover">
				<tr>
					<th>Produkt</th>
          <th>Cena za sztukę</th>
          <th>Liczba sztuk</th>
          <th>Cena</th>
          <th>Akcja</th>

				</tr>
				<tr ng-repeat="item in cart.cartItems">
					<td>{{item.product.productCode}}-{{item.product.name}}</td>
					<td>{{item.product.unitPrice}} PLN</td>
					<td>{{item.quantity}}</td>
					<td>{{item.totalPrice}} PLN</td>
					<td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productCode)"> <span
							class="glyphicon glyphicon-remove" /></span> Usuń
					</a></td>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th>Łączna cena</th>
					<th>{{cart.grandTotal}} PLN</th>
					<th></th>
				</tr>
			</table>
			
			<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Wróć do zakupów
			</a>
		</div>
	</section>
