<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form servletRelativeAction="/playlist/insert" method="post" commandName="playlist">
	<c:if test="${ not empty playlist.id }">
		<form:hidden path="id" />
	</c:if>
	<form:hidden path="curso.id" value="${ curso_id }" />
	<div class="form-group">
		<label>Título da playlist (opcional)</label>
		<form:input path="titulo" placeholder="Parte 1, Módulo 1, 2º Bimestre, etc.." class="form-control"/>
	</div>
	<input type="submit" value="Nova playlist" class="btn f-bg-primary mb-2" />
</form:form>

