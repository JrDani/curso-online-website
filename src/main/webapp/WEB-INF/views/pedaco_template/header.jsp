<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<header class="container-fluid">
    <div class="f-bg-primary header-box ">
        <div class="row align-items-center">
            <div class="col-md-2">
                <a href="${ s:mvcUrl('HC#welcome').build() }" id="logo">CURSOS ONLINE</a>
            </div>
            <div class="col-md-7">              
                   <div class="row justify-content-md-center align-items-center" >
                       <div class="col-md-8">
                      		<form:form servletRelativeAction="/curso/pesquisar" method="post">
								<input name="pesquisar" placeholder="Pesquisar.." 
								class="form-control buscar " />
								<input type="submit" 
							       style="position: absolute; left: -9999px; width: 1px; height: 1px;"
							       tabindex="-1" />
							</form:form>                         
                       </div>
                   </div>  
            </div>
            
            <nav class="navbar navbar-expand-lg col-md-3 "> 
                <button class="navbar-toggler" type="button" data-toggle="collapse" 
                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse d-flex" id="navbarSupportedContent">     
                    <ul class="navbar-nav f-menu ml-auto">
                        <li class="nav-item"><a href='${ s:mvcUrl("HC#welcome").build() }' 
                        class="nav-link text-white">Home</a></li>
                        <li class="nav-item"><a href="${ s:mvcUrl('UC#loginForm').build() }" 
                        class="nav-link text-white">Login</a></li>
                        <!--<li><a href="${ s:mvcUrl('UC#create').build() }">Cadastro</a></li>-->                      
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" 
                            role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Minha conta
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                <!--Restrito para Usuários logados-->
                                <a href="${ s:mvcUrl('MC#listarMatriculas').build() }"
                                 class="dropdown-item">Meus cursos</a>
                                <a href="${ s:mvcUrl('PC#perfilPage').build() }" class="dropdown-item">
                                Meu perfil</a>                              
                                <a href='<c:url value="/logout" />' class="dropdown-item"> Logout </a> 
                               
                                <!--Restrito para Professores-->
                                <div class="dropdown-divider"></div>   
                               	<a href="${ s:mvcUrl('GCC#listar').build() }" 
                                	class="dropdown-item">Enviar vídeo</a>
                                
                                <!--Restrito para administradores-->
                                <div class="dropdown-divider"></div>
                            	<a href="${ s:mvcUrl('CC#listaCategoria').build() }"
                              	   class="dropdown-item">Gerenciar Categorias</a>                      
                            	
                            </div>
                        </li>   
                    </ul>    
                </div>               
            </nav>
        </div>
    </div>
</header>
