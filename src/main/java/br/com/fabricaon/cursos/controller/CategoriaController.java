package br.com.fabricaon.cursos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CategoriaDAO;
import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.validation.CategoriaValidation;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CategoriaValidation());	
	}
	
	@RequestMapping("/lista")
	@Cacheable(value="categoriaList")
	public ModelAndView listaCategoria() {
		
		List<Categoria> categorias = categoriaDAO.lista();
		
		ModelAndView modelAndView = new ModelAndView("pages/gerenciar/categoria/lista");
		modelAndView.addObject("categorias", categorias);
		return modelAndView;
	}
	
	@RequestMapping("/create")
	public ModelAndView createCategoriaPage(Categoria categoria) {
		return new ModelAndView("pages/gerenciar/categoria/create");
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView updateCategoriaForm(@PathVariable Integer id, Categoria categoria) {
		
		Optional<Categoria> find = categoriaDAO.find(id);
		if(!find.isPresent())
			return this.listaCategoria();		
		
		categoria.setId(find.get().getId());
		categoria.setNome(find.get().getNome());
	
		return this.createCategoriaPage(categoria);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@CacheEvict(value="categoriaList", allEntries=true)
	public ModelAndView insert(@Valid Categoria categoria, BindingResult result) {
		if(result.hasErrors()) {
			return createCategoriaPage(categoria);
		}
		
		Integer categoriaId = categoria.getId();
		if(categoriaId == null) {
			
			//new
			categoriaDAO.insert(categoria);
		}else {
			
			//update
			categoriaDAO.update(categoria);
		}
		
		return new ModelAndView("redirect:/categoria/lista");
	}
}
