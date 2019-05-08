<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form servletRelativeAction="/curso/pesquisar" method="post">
	<input name="pesquisar" placeholder="Pesquisar.." class="form-control buscar " />
	<input type="submit" 
       style="position: absolute; left: -9999px; width: 1px; height: 1px;"
       tabindex="-1" />
</form:form>