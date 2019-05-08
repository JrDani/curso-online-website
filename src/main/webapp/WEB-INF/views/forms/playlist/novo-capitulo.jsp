<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<form:form servletRelativeAction="/sumario/novo-capitulo" method="post" 
							commandName="videosSumario">
	<form:hidden path="playlist.id" value="${ playlist_id }" />
	<div class="form-group">
		<label>Número do capítulo: </label>
		<form:input path="capitulo" placeholder="Ex: 1" class="form-control" />
		<form:errors path="capitulo" />
	</div>
	<input type="submit" value="Enviar" class="btn f-bg-primary" />
</form:form>
