<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="Login em Cursos Online">
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					<c:if test="${param.error }">
						<div class="alert alert-danger">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
					</c:if>
					<c:if test="${param.logout }">
						<div class="alert alert-info">Logout realizado com sucesso</div>
					</c:if>
					<h1>Fazer Login</h1>
					<jsp:include page="${request.contextPath}/usuario/login/form" />
				</div>
			</div>
		</div>
	</main>
</tags:pageTemplate>