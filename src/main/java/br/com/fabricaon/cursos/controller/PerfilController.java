package br.com.fabricaon.cursos.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CategoriaDAO;
import br.com.fabricaon.cursos.dao.PerfilDAO;
import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Perfil;
import br.com.fabricaon.cursos.validation.PerfilValidation;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private PerfilDAO perfilDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new PerfilValidation());
	}
	
	@RequestMapping("")
	public ModelAndView perfilPage() {
		return new ModelAndView("pages/usuario/perfil");
	}
	
	@RequestMapping("/create")
	public ModelAndView perfilCreate(Perfil perfil) {
		List<Categoria> categorias = categoriaDAO.lista();
		Map<Integer, String> categoria_option = new LinkedHashMap<Integer, String>();
		categorias.forEach(x -> categoria_option.put(x.getId(), x.getNome()));
	
		return new ModelAndView("forms/perfil/create-perfil")
				.addObject("categorias", categoria_option);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ModelAndView insert(@Valid Perfil perfil, BindingResult result) {
		if(result.hasErrors()) {
			return perfilCreate(perfil);
		}
		
		perfilDAO.insert(perfil);
		
		return new ModelAndView("redirect:/");
	}
}
