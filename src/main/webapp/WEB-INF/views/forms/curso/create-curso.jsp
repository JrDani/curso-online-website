<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!Doctype HTML>
<html>
<head>
</head>
<body>
	
	<p><a href="${ s:mvcUrl('GCC#listar').build() }">Voltar para gerenciamento de cursos</a></p>

	<h1>Em desenvolvimento</h1>
	<form:form servletRelativeAction="/gerenciar-curso/insert" method="post" commandName="curso">
		<div>
			<label>Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Categoria</label>
			<form:select path="categoria.id" items="${ categorias }" />	
			<form:errors path="categoria.id" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" />
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Dificuldade</label>
			<form:select path="curso_dificuldade" items="${ dificuldade }" />			
			<form:errors path="curso_dificuldade" />
		</div>
		<div>
			<label>Valor</label>
			<form:input path="valor" />
			<form:errors path="valor" />
		</div>
		<c:if test="${ not empty curso.id }">
			<form:hidden path="id" />			
		</c:if>
		<input type="submit" value="Enviar" />
	</form:form>

</body>
</html>