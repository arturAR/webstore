<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<section class="container">
		<form:form  modelAttribute="newUser" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>Zarejestruj się</legend>

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="email"><spring:message code="addUser.form.email.label"/></label>
					<div class="col-lg-10">
						<form:input id="email" path="email" type="text" class="form:input-large"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="name"><spring:message code="addUser.form.firstName.label"/></label>
					<div class="col-lg-10">
						<form:input id="firstName" path="firstName" type="text" class="form:input-large"/>
						<form:errors path="firstName" cssClass="text-danger"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName"><spring:message code="addUser.form.lastName.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="lastName" path="lastName" type="text" class="form:input-large"/>
							<form:errors path="lastName" cssClass="text-danger"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="password"><spring:message code="addUser.form.password.label"/></label>
					<div class="col-lg-10">
						<form:input id="password" path="password" type="text" class="form:input-large"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="phoneNumber"><spring:message code="addUser.form.phoneNumber.label"/></label>
					<div class="col-lg-10">
						<div class="form:input-prepend">
							<form:input id="phoneNumber" path="phoneNumber" type="text" class="form:input-large"/>
							<form:errors path="phoneNumber" cssClass="text-danger"/>
						</div>
					</div>
				</div>



				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Dodaj"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>

