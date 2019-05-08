package br.com.fabricaon.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.dao.PlaylistDAO;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Playlist;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.service.MatriculaService;

@Controller
@RequestMapping("/continuar-curso")
public class AcessarAulaController {
	
	@Autowired
	private MatriculaService matriculaService;
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private PlaylistDAO playlistDAO;
	
	@RequestMapping("/{curso_id}")
	public ModelAndView continuarCurso(@PathVariable Integer curso_id, @AuthenticationPrincipal 
			Usuario usuario) {
	
		if(matriculaService.EhEstudanteDoCurso(curso_id, usuario.getId())) {
			Curso c = cursoDAO.find(curso_id).get();
			List<Playlist> p = playlistDAO.listaPorCurso(c);
			
			return new ModelAndView("redirect:/"+c.getTitulo()+"/videos/"+p.get(0).getId()) ;
		}
	
		return new ModelAndView("redirect:/matricula");
	}	
	
}
