<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="Gerenciando playlist">
	<jsp:attribute name="extraStyle">
		<link rel="stylesheet" href='<c:url value="/resources/css/custom.card.css" />' >
	</jsp:attribute>
	
<jsp:body>
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					<p><a href="${ s:mvcUrl('GCC#curso').arg(0, curso_id).build() }">Voltar para página do
					 curso</a></p>
					
					<h1>Playlist 
						<small><c:out value="${ not empty titulo ? titulo : 'Sem título' }" /></small>
					</h1>
					
					<div class="mt-4 shadow p-3 bg-white rounded">
						
						<div>
							<h4>Criar novo capítulo</h4>
							<c:import url="/WEB-INF/views/forms/playlist/novo-capitulo.jsp"/>							
						</div>
					
						<div class="mt-5">
							<h4>Capítulos</h4>
							<c:if test="${ empty capitulos }">
								<p>Não existem capítulos cadastrados nesta playlist</p>
							</c:if>
							<c:forEach items="${ capitulos }" var="c">										
								<div class="my-4 pb-3 even-border-bottom border-gray">
									<h5>Capítulo: ${c.capitulo }</h5>	
									<p>
										<a href="${s:mvcUrl('GVC#createVideo').arg(0, c.id).arg(1, playlist_id)
										.build()}">
										Adicionar video</a>				
									</p>
								
									<c:forEach items="${ c.videos }" var="v">
										<div class="card">
											<div class="card-body">											
												<div class="card-text">
													<div class="card-title">${v.titulo }</div>
													<a href="${ s:mvcUrl('GVC#updateForm').arg(0, playlist_id).arg(1, v.id).build() }" class="card-link">
													Editar dados do video</a>
													
													<a href="${ s:mvcUrl('GVC#deletar').arg(0, v.id).arg(1, playlist_id).build() }" class="card-link">
													Deletar video
													</a>																
												</div>
												<div class="card-meta text-muted"> 
													<div class="d-flex align-items-center">
														${ v.getDuracao_minuto() } min
													</div>
												</div>
											</div>	
										</div><!-- card -->	
									</c:forEach>
								</div>
							</c:forEach>							
						</div>
						
					</div><!-- bg-white rounded -->
					
				</div>
			</div>
		</div>
	</main>
</jsp:body>
</tags:pageTemplate>