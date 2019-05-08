<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Gerenciado curso ${ curso.titulo }">

<jsp:attribute name="extraStyle">
	<link rel="stylesheet" href='<c:url value="/resources/css/custom.card.css" />' >
</jsp:attribute>

<jsp:attribute name="extraScript">
<script>
	$(function () {
		$('[data-toggle="popover"]').popover()
	})
</script>
</jsp:attribute>

<jsp:body>
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					<p>
						<a href="${ s:mvcUrl('GCC#listar').build() }">Ver todos os meus cursos</a>
					</p>
					
					<div class="shadow p-3 mb-5 bg-white rounded">
					<h1>${ curso.titulo }</h1>
						<a href="${ s:mvcUrl('GCC#updateForm').arg(0, curso.id).build() }">
						Editar dados do curso</a>						
					</div>
				
					<div class="shadow p-3 bg-white rounded">
						<h2>Playlists</h2>		

						<div class="my-4 pb-3 border-bottom border-gray">
							
							<c:if test="${ empty playlists }">
								<p class="text-muted">Este curso não tem nenhuma playlist</p>
							</c:if>
							
							<c:if test="${ not empty playlists }">
							<div class="card-list">
								<c:forEach items="${ playlists }" var="p">								
									<div class="card">
										<div class="card-body">											
											<div class="card-text">
												<h6 class="card-title">
													<c:out value="${ empty p.titulo ? 'Sem título' : p.titulo}"
													 />
												</h6>
												<a href="${ s:mvcUrl('SC#sumarioCreate').arg(1, p.id)
												.build() }"	class="card-link">
												Upload de vídeos</a> 
									
												<a href="${ s:mvcUrl('PC#editarPlaylist').arg(0, p.id)
												.build() }" class="card-link">
												Alterar título
												</a>		
											</div>	
											<div class="card-meta text-muted">
												<div class="d-flex align-items-center">
													<span>${p.sumario.size()} capítulo(s)</span>
												</div>
											</div>									
										</div><!-- card body -->			
									</div>
								</c:forEach>
							</div><!-- card list -->
							</c:if>
							
						</div>
								
						<div>		
							<h3 class="text-muted">Nova playlist</h3>
							<jsp:include page="${request.contextPath}/playlist/create?curso_id=${ curso.id }"/>
							
							<a tabindex="0" data-toggle="popover" data-trigger="focus"
							title="Dica sobre playlists" data-content="Opcionalmente você pode dividir o teu 
							curso para usuário de diferentes sistemas operacionais, versões de ferramentas ou 
							simplesmente dividir as aulas em bimestes, módulos, semestres, etc." 
							style="cursor:pointer">
							Por que mais de uma playlist?</a>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</main>
</jsp:body>

</tags:pageTemplate>