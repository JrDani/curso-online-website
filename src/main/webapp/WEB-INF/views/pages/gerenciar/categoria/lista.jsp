<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Gerenciando categorias">
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					
					<div class="shadow-sm  p-3 bg-white rounded ">
						<h1 class="border-bottom border-gray pb-2">Todas as categorias</h1>
						
						<div class="my-4">
							<c:forEach items="${categorias }" var="c">
								<div class="d-flex justify-content-between align-items-center 
								 border-bottom border-gray text-muted py-2">
									<strong class="text-gray-dark">${ c.nome }</strong>
									<a href="${ s:mvcUrl('CC#updateCategoriaForm').arg(0, c.id).build() }">Editar</a>
								</div>
							</c:forEach>
						</div>
						
						<a href="${ s:mvcUrl('CC#createCategoriaPage').build() }" class="btn f-bg-primary">
							Adicionar categoria</a>
					</div>
					
				</div>
			</div>
		</div>
	</main>
</tags:pageTemplate>