<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!Doctype HTML>
<html>
<head>
</head>
<body>
	<form:form action="/cursosOnline/usuario/insert" method="post" commandName="novoUsuario">
		<div class="form-group">
			<label>Seu email</label>
			<form:input type="email" path="username" />
			<form:errors path="username" />
		</div>
		<div class="form-group">
			<label>Senha</label>
			<form:input type="password" path="password" />
			<form:errors path="password" />
		</div>
		<div class="form-group">
			<label>Repita sua senha</label>
			<input type="password" name="passwordConfirm" />
			<form:errors path="passwordConfirm" />
		</div>
		<input type="submit" value="Cadastrar" class="btn btn-primary" />
	</form:form>
</body>
</html>