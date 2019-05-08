<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Gerenciando cursos">
	
	<main>
	<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					
					<div class="shadow-sm  p-3 bg-white rounded">
						<h1 class="border-bottom border-gray pb-2">Todos os meus cursos</h1>
						<div class="text-muted my-4">
							<c:forEach items="${ cursos }" var="c">
								<div class="border-bottom border-gray py-2 align-items-center">
								<a href="${s:mvcUrl('GCC#curso').arg(0, c.id).build() }">
								<strong>${ c.titulo }</strong></a>
								</div>		
							</c:forEach>
							<c:if test="${empty cursos }">
								<p>Você ainda não tem cursos cadastrados</p>
							</c:if>
						</div>
						<a href="${ s:mvcUrl('GCC#create').build() }" class="btn f-bg-primary">
						Criar novo curso</a>
					</div>					
				</div>				
			</div>
		</div>
	</main>
	
</tags:pageTemplate>