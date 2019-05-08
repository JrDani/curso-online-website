<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Meus cursos">
	
	<main>
	<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					
					<div class="shadow-sm  p-3 bg-white rounded">
						<h1 class="border-bottom border-gray pb-2">Minhas matrículas</h1>
						<div class="text-muted my-4">
							<c:forEach items="${ matriculas }" var="m">
								<div class="border-bottom border-gray py-2 align-items-center">
								<a href="${ s:mvcUrl('AAC#continuarCurso').arg(0, m.curso.id).build() }"
								 title="Continuar curso">
								<strong>${ m.curso.titulo }</strong></a>
								</div>		
							</c:forEach>
							<c:if test="${empty matriculas }">
								<p>Você ainda não se matriculou em algum curso</p>
							</c:if>
						</div>
					</div>					
				</div>				
			</div>
		</div>
	</main>
	
</tags:pageTemplate>