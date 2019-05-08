<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<form:form method="post" commandName="usuario">
	<div class="form-group">
		<div class="form-group">
		<label>Seu email</label>
		<form:input type="email" path="username" class="form-control" />
		<form:errors path="username" />
		</div>
	</div>
	<div class="form-group">
		<label>Senha</label>
		<form:input type="password" path="password" class="form-control" />
		<form:errors path="password" />
	</div>	
	<input type="submit" value="Login" class="btn f-bg-primary" />
</form:form>
