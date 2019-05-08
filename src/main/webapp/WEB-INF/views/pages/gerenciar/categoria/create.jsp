<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<tags:pageTemplate titulo="Nova categoria">
	<main>	
		<div class="container mt-5 mb-5">			
			<div class="row d-flex justify-content-center">
				<div class="col-8">					
					<p><a href="${ s:mvcUrl('CC#listaCategoria').build() }">
					 Voltar para a lista de categorias</a></p>
					<h1>Nova categoria</h1>					
				 	
					<c:import url="/WEB-INF/views/forms/categoria/create-categoria.jsp"/>			
				</div>
			</div>
		</div>
	</main>
</tags:pageTemplate>