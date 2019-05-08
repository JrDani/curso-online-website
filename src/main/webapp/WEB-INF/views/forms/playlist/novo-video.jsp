<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form servletRelativeAction="/gerenciar-video/insert" method="post" commandName="video">
		
	<form:hidden path="videosSumario.id" />
	
	<c:if test="${ not empty video.id }">
		<form:hidden path="id" />
	</c:if>		
	
	<input type="hidden" name="playlistID" value="${playlist_id}" />
	
	<div class="form-group">
		<label>Url do vídeo</label>
		<form:input path="url" class="form-control" />
		<form:errors path="url" />
	</div>
	<div class="form-group">
		<label>Titulo</label>
		<form:input path="titulo" class="form-control" />
	</div>		
	<div class="form-group">
		<label>Duração (minutos)</label>
		<form:input path="duracao_minuto" class="form-control" />
		<form:errors path="duracao_minuto" />
	</div>
	<input type="submit" value="Enviar" class="btn f-bg-primary" />
</form:form>
	
