<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Produkty</title>
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Zaloguj się</h3>
			 	</div>
			  	<div class="panel-body">
			  	<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br />
					</div>
				</c:if>
			    	<form action="<c:url value="/login"></c:url>" method="post" class="form-signin">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="Email" name='email' id="inputEmail" type="email">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Hasło" name='password'  type="password">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Zaloguj się">
			    	</fieldset>
			      	</form>
			    </div>
			    <div class="col-md-offset-1">
			    	 Nie masz jeszcze konta? <a href="<spring:url value="/register" />">Zarejestruj się teraz!</a>
			    </div>
			</div>
		</div>
	</div>
	</div>
</body>
