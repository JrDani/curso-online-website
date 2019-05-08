<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Login em Cursos Online">
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					<h1 class="mb-4">Alterar informações do seu perfil</h1>
					<jsp:include page="${request.contextPath}/perfil/create" />
				</div>
			</div>
		</div>
	</main>
</tags:pageTemplate>