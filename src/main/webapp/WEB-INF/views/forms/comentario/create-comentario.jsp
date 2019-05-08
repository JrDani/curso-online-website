<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form servletRelativeAction="/comentario/comentar?para=${requestScope['javax.servlet.forward.request_uri']}"
 method="post" commandName="comentario" >
	<div class="form-group">
		<form:hidden path="curso.id" value="${ curso.id }" />
		<label>Seu comentario</label>
		<form:textarea path="comentario" id="comentario" class="form-control" />
		<form:errors path="comentario" />
	</div>
	<input type="submit" value="Enviar" class="btn f-bg-primary" />
</form:form>