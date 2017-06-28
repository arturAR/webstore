<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<li><a href="<spring:url value="/"/>">Strona główna</a></li>
<li><a href="<spring:url value="/products/"/>">Produkty</a></li>
<sec:authorize access="hasRole('ADMIN')">
	<li class="dropdown"><a href="<spring:url value="/admin/add"/>"
		data-toggle="dropdown" role="button">Dodaj produkt </a></li>
</sec:authorize>
<li><a href="<spring:url value="/cart/"/>">Koszyk</a></li>
<sec:authorize access="!hasAnyRole('ADMIN', 'USER')">
	<li class="dropdown"><a href="<spring:url value="/login"/>"
		data-toggle="dropdown" role="button">Zaloguj się </a></li>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
	<li class="dropdown"><a href="<spring:url value="/logout"/>"
		data-toggle="dropdown" role="button">Wyloguj się </a></li>
</sec:authorize>