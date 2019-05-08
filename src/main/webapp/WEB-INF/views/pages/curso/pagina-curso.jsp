<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="${ curso.titulo }">
	<main>
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-4">
					<img src="https://agoncal.files.wordpress.com/2014/05/java_ee_logo_vert_v2.png"
					class="img-thumbnail" />				
				</div>
				<div class="col-4">
					<h1>${ curso.titulo }</h1>
					<p>${ curso.descricao }</p>	
					<p class="mt-2">
						<security:authorize access="isAuthenticated()">
								
							<c:if test="${ matriculado }">
								<!-- matriculado -->
								<a href="${ s:mvcUrl('AAC#continuarCurso').arg(0, curso.id).build() }" 
								class="btn btn-outline-primary">
								Ir para as aulas</a>
							</c:if>
							
							<c:if test="${not matriculado }">
								<!-- não matriculado -->
								<a href="${ s:mvcUrl('MC#createMatricula').arg(0, curso.id).build() }" 
								class="btn btn-outline-primary">
								Se Matricular</a>
							</c:if>	
									
						</security:authorize>
						
						<security:authorize access="!isAuthenticated()">
						  <a href="${ s:mvcUrl('UC#loginForm').build() }" 
						  class="btn btn-outline-primary">Faça login para assistir</a>
						</security:authorize>
					</p>
				</div>
				
				<div class="col-8 mt-5 ">
				<h3>Tem dúvidas sobre o curso?</h3>
					<a href="#">Envie uma mensagem para o dono do curso</a>
				</div>
				<div class="col-8 mt-5">
					<h2>Comentários sobre este curso</h2>
					<ul class="list-unstyled mt-3">
						<c:forEach items="${ comentarios }" var="co">
								<li class="media breadcrumb mt-2 mb-4">
									<img src="http://www.icon100.com/up/4132/64/62-man.png" 
									class="mr-3" alt="...">
									<div class="media-body ">
								    	<h5 class="mt-0 mb-1 text-blue">${ co.usuario.username }</h5>
										${ co.comentario }
								    </div>									
								</li>				
						</c:forEach>
					</ul>
					
					<h4 class="mt-2">Comentar</h4>
					
					<c:if test="${not empty error }">
						<div class="alert alert-danger"><s:message code="${ error }" /></div>
					</c:if>
					
					<security:authorize access="isAuthenticated()">
					  <jsp:include page="${request.contextPath}/comentario/create" />
					</security:authorize>
					<security:authorize access="!isAuthenticated()">
					  <a href="${ s:mvcUrl('UC#loginForm').build() }">Você precisa estar logado para comentar</a>
					</security:authorize>
				</div>
			</div><!-- row -->
			
		</div><!-- container -->		
		
	</main>
</tags:pageTemplate>