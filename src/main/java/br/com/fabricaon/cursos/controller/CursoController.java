package br.com.fabricaon.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.ComentarioDAO;
import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.model.Comentario;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.service.MatriculaService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@RequestMapping("/{curso_id}")
	public ModelAndView detalhe(@PathVariable Integer curso_id, @AuthenticationPrincipal Usuario customUser) {
		ModelAndView modelAndView = new ModelAndView("pages/curso/pagina-curso");
		
		Optional<Curso> curso = cursoDAO.find(curso_id);
		boolean estaMatriculado = false;
		
		if(customUser != null) {
			estaMatriculado = matriculaService.EhEstudanteDoCurso(curso_id, customUser.getId());
		}
		
		if(curso.isPresent()) {
			modelAndView.addObject("curso", curso.get());
			List<Comentario> comentarios = comentarioDAO.listarPorCurso(curso_id);
			modelAndView.addObject("comentarios", comentarios);
			modelAndView.addObject("matriculado", estaMatriculado);
			
			return modelAndView;
		}else {
			throw new RuntimeException("Curso não encontrado");
		}
		
	}
	
	@RequestMapping(value="/pesquisar", method=RequestMethod.POST)
	public ModelAndView pesquisar(String pesquisar) {
		
		List<Curso> resultado = cursoDAO.pesquisar(pesquisar);
		
		ModelAndView modelAndView = new ModelAndView("/pages/home/busca_resultados");
		modelAndView.addObject("resultado", resultado);
		modelAndView.addObject("query", pesquisar);
	
		return modelAndView;
				
	}
}
