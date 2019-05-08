<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="Upload de video">
	<main>
	
		<div class="container mt-5 mb-5">
			<div class="row d-flex justify-content-center">
				<div class="col-8">
					<p><a href="${ s:mvcUrl('SC#sumarioCreate').arg(1, playlist_id).build() }">
					Voltar para a playlist</a></p>
					<h1>Adicionando vídeo no capítulo ${ video.videosSumario.capitulo }</h1>
					
					<c:import url="/WEB-INF/views/forms/playlist/novo-video.jsp"/>
				</div>
			</div>
		</div>
	</main>

</tags:pageTemplate>