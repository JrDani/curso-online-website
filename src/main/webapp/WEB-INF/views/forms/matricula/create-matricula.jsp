<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<body>
<form:form servletRelativeAction="/matricula/insert" method="post">
	<input type="hidden" name="curso_id" value="${curso_id }"/>
	<h2>Selecione a forma de pagamento:</h2>
	<p>Preço R$ ${ valor }</p>
	<label>Relaxa tô só bricando :)</label><input type="radio" name="pagamento" />
	
	<input type="submit" value="Enviar" />
</form:form>
</body>
</html>