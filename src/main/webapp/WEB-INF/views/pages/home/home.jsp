<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tags:pageTemplate titulo="Bem vindo ao Cursos Online">

	<main>
		<section id="courses" class="container">
		    <div class="row">  
			    <c:forEach items="${ cursos }" var="curso">
			    	<div class="col-md-4" style="width: 18rem;">
			            <div class="card">
			                <img src="https://agoncal.files.wordpress.com/2014/05/java_ee_logo_vert_v2.png" class="card-img-top" alt="https://agoncal.files.wordpress.com/2014/05/java_ee_logo_vert_v2.png">
			                <div class="card-body">
			                <h5 class="card-title">${ curso.titulo }</h5>
			                <p class="card-text">${fn:substring(curso.descricao, 0, 45)}</p>
			                <a href="${ s:mvcUrl('CC#detalhe').arg(0, curso.id).build() }" 
			                class="btn f-bg-primary">Saiba mais</a>
			                </div>
			            </div>
			        </div>
					
				</c:forEach>      
	     	</div>
	     </section> 		
	</main>
	
</tags:pageTemplate>