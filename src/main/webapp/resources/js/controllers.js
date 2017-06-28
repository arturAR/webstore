var cartApp = angular.module('cartApp', []);
 
cartApp.controller('cartCtrl',  function ($scope, $http) {
	
	$scope.refreshCart = function(cartId) {
		 $http.get('/webstore/rest/cart/'+$scope.cartId).success(function(data) {
		  	 $scope.cart = data;
		 });
	};
		  					
	$scope.clearCart = function() {
		 $http.delete('/webstore/rest/cart/'+$scope.cartId).success($scope.refreshCart($scope.cartId));
	};
	  					  
	$scope.initCartId = function(cartId) {
		 $scope.cartId=cartId;
		 $scope.refreshCart($scope.cartId);
	};
	  						
	$scope.addToCart = function(productCode) {
		 $http.put('/webstore/rest/cart/add/'+productCode).success(function(data) {
			$scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
		 	alert("Produkt pomyślnie dodany do koszyka!");
		 });
	};
	
	$scope.removeFromCart = function(productCode) {
		 $http.put('/webstore/rest/cart/remove/'+productCode).success(function(data) {
			$scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
		 });
	};
});
