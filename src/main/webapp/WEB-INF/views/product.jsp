<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	

	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script src="/webstore/resource/js/controllers.js"></script>
	<section class="container" ng-app="cartApp">
		<div class="row">
		<div class="col-md-5">
	<img src="<c:url value="/resource/images/${product.productCode}.png"></c:url>" alt="image"  style = "width:100%"/>
		</div>
		
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>Identyfikator produktu: </strong><span class="label label-warning">${product.productCode}</span>
				</p>
				<p>
					<strong>Producent</strong>: ${product.manufacturer}
				</p>
				<p>
					<strong>Kategoria</strong>: ${product.category}
				</p>
				<p>
					<strong>Stan</strong>: ${product.condition}
				</p>
				<p>
					<strong>Liczba sztuk w magazynie</strong>: ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice} PLN</h4>
				<p ng-controller="cartCtrl">
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productCode}')"> 
						<span class="glyphicon-shopping-cart glyphicon"></span> Zamów teraz </a>
					<a href="<spring:url value="/cart" />" class="btn btn-default">
						<span class="glyphicon-hand-right glyphicon"></span> Koszyk
					</a>

 					<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> wstecz
					</a>

				</p>

			</div>
		</div>
	</section>
