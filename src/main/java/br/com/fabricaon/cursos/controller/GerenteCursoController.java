package br.com.fabricaon.cursos.controller;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CategoriaDAO;
import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.dao.PlaylistDAO;
import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.CursoDificuldade;
import br.com.fabricaon.cursos.model.Playlist;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.validation.CursoValidation;

@Controller
@RequestMapping("/gerenciar-curso")
public class GerenteCursoController {
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private PlaylistDAO playlistDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CursoValidation());
	}
	
	@RequestMapping(method=RequestMethod.GET)	
	public ModelAndView listar() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		List<Curso> cursos = cursoDAO.listaPorDono(usuario);
		return new ModelAndView("pages/gerenciar/curso/index")
				.addObject("cursos", cursos);
	}
	
	@RequestMapping("/curso/{id}")
	public ModelAndView curso(@PathVariable Integer id) {
		Optional<Curso> curso = cursoDAO.find(id);
		if(!curso.isPresent())
			return new ModelAndView("redirect:/gerenciar-curso");
		
		List<Playlist> playlists = playlistDAO.listaPorCurso(curso.get());
		
		return new ModelAndView("pages/gerenciar/curso/curso")
				.addObject("curso", curso.get())
				.addObject("playlists", playlists);
	}
	
	@RequestMapping("/create")
	public ModelAndView create(Curso curso) {
		ModelAndView modelAndView = new ModelAndView("forms/curso/create-curso");
		List<Categoria> categorias = categoriaDAO.lista();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		categorias.forEach(x -> map.put(x.getId(), x.getNome()));
		
		modelAndView.addObject("categorias", map);
		modelAndView.addObject("dificuldade", CursoDificuldade.values());
		
		return modelAndView;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView updateForm(@PathVariable Integer id, Curso curso) {
		
		Optional<Curso> cursoSelecionado = cursoDAO.find(id);
		
		if(!cursoSelecionado.isPresent())
			return this.curso(id);
		
		curso.clonar(cursoSelecionado.get());		
		return this.create(curso);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@CacheEvict(value="cursosHome", allEntries=true)
	public ModelAndView insert(@Valid Curso curso, BindingResult result) {		
		
		if(result.hasErrors()) {
			return create(curso);
		}
		
		Integer cursoid = curso.getId();
		
		if(cursoid == null) {
			
			//new
			curso.setData(Calendar.getInstance());	
			Usuario usuario = new Usuario();
			usuario.setId(1);
			curso.setUsuario(usuario);			
			cursoid = cursoDAO.insertAndGetId(curso);		
			
		}else {
			
			//update
			Optional<Curso> cursoSelecionado = cursoDAO.find(cursoid);
			if(cursoSelecionado.isPresent()) {
				
				curso.setData(cursoSelecionado.get().getData());
				curso.setUsuario(cursoSelecionado.get().getUsuario());
				cursoDAO.update(curso);
			}
			
		}		
	
		return new ModelAndView("redirect:/gerenciar-curso/curso/"+cursoid);
	}	
	
}
