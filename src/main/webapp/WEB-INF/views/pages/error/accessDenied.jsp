<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<tags:pageTemplate titulo="Bem vindo ao Cursos Online">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-6">
					<h1 >Acesso restrito</h1>
					<p>Sua conta não tem permissão para acessar esta página</p>
					<p>	<a href="${ s:mvcUrl('HC#welcome').build() }">Ir para Home Page</a></p>
				</div>
			</div>
		</div>
	</main>

</tags:pageTemplate>