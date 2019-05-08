<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ usuario_novo }">
	<a href="#">Deixar para depois</a>
</c:if>
<form:form servletRelativeAction="/perfil/insert" method="post" commandName="perfil">
	<div class="form-group">
		<label>Seu nome</label>
		<form:input path="nome" class="form-control" />
		<form:errors path="nome" />
	</div>
	<div class="form-group">
		<label>Selecione sua preferência</label>
		<form:select path="categoria.id" items="${categorias }" class="form-control"  />
		<form:errors path="categoria.id" />
	</div>
	<input type="submit" value="Enviar" class="btn f-bg-primary" />
</form:form>
