package br.com.fabricaon.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.model.Curso;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="cursosHome")
	public ModelAndView welcome() {
		List<Curso> cursos = cursoDAO.lista();
		ModelAndView modelAndView = new ModelAndView("pages/home/home");
		modelAndView.addObject("cursos", cursos);
		return modelAndView; 
	}
	
	//for testing 
	@RequestMapping("/sucesso")
	public String sucesso() {
		return "/sucesso";
	}
}
