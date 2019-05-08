package br.com.fabricaon.cursos.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.dao.MatriculaDAO;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Matricula;
import br.com.fabricaon.cursos.model.StatusMatricula;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.service.MatriculaService;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {
	
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private MatriculaDAO matriculaDAO;
	@Autowired
	private MatriculaService matriculaService;
	
	@RequestMapping("/")
	public ModelAndView listarMatriculas(@AuthenticationPrincipal Usuario usuario) {	
		List<Matricula> matriculasDoUsuario = matriculaService.MatriculasDoUsuario(usuario);
		return new ModelAndView("pages/usuario/cursos/lista")
				.addObject("matriculas", matriculasDoUsuario);
	
	}
	
	@RequestMapping("/form/{curso_id}")
	public ModelAndView createMatricula(@PathVariable Integer curso_id, 
			@AuthenticationPrincipal Usuario customUser) {
		
		//getCurso
		Curso curso = getCurso(curso_id);
		if(curso == null) {
			return new ModelAndView("redirect:/curso/"+curso_id); 
		}
		
		//Se ja tiver matriculado envie para aulas
		Boolean estaMatriculado = matriculaService.EhEstudanteDoCurso(curso_id, customUser.getId());
		if(estaMatriculado) {
			return new ModelAndView("redirect:/curso/"+curso.getId()+"/aulas"); 
		}
		
		//curso gratuito: matricule direto e redirecione pras aulas
		if(curso.getValor().compareTo(new BigDecimal(0)) == 0) {
			
			Matricula matricula = novaMatricula(curso, customUser.getId());
			matriculaDAO.insert(matricula);
			
			return new ModelAndView("redirect:/curso/"+curso.getId()+"/aulas"); 
		}
		
		//curso pago: manda para o formulário de pagamento
		ModelAndView modelAndView = new ModelAndView("forms/matricula/create-matricula")
				.addObject("curso_id", curso.getId())
				.addObject("valor", curso.getValor());
	
		return modelAndView;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ModelAndView insert(Integer curso_id, @AuthenticationPrincipal Usuario customUser) {	
		
		//getCurso
		Curso curso = getCurso(curso_id);
		if(curso == null) {
			return new ModelAndView("redirect:/curso/"+curso_id); 
		}
		
		Matricula matricula = novaMatricula(curso, customUser.getId());
		matriculaDAO.insert(matricula);
		
		return new ModelAndView("redirect:/matricula/curso/"+curso_id);
	}	
	
	public Matricula novaMatricula(Curso curso, Integer user_id) {
		Usuario usuario = new Usuario();
		usuario.setId(user_id);
		Matricula matricula = new Matricula();
		matricula.setCurso(curso);
		matricula.setStatus(StatusMatricula.ATIVO);
		matricula.setUsuario(usuario);
		
		return matricula;
	}
	
	public Curso getCurso(Integer cursoId) {
		Optional<Curso> find = cursoDAO.find(cursoId);	
		
		if(!find.isPresent())
			return null;
		
		Curso curso = find.get();
		return curso;
	}
	
}
