package br.com.fabricaon.cursos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.PlaylistDAO;
import br.com.fabricaon.cursos.dao.VideosSumarioDAO;
import br.com.fabricaon.cursos.model.Playlist;
import br.com.fabricaon.cursos.model.VideosSumario;
import br.com.fabricaon.cursos.validation.VideosSumarioValidation;

@Controller
@RequestMapping("/sumario")
public class SumarioController {
	
	@Autowired
	private VideosSumarioDAO videosSumarioDAO;
	@Autowired
	private PlaylistDAO playlistDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new VideosSumarioValidation());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView sumarioIndex() {
		return new ModelAndView("redirect:/gerenciar-curso");
	}
		
	@RequestMapping("/playlist/{playlist_id}")
	public ModelAndView sumarioCreate(VideosSumario videosSumario, @PathVariable Integer playlist_id) {
		
		Playlist playlist = playlistDAO.find(playlist_id);
		List<VideosSumario> capitulos_cadastrados = videosSumarioDAO.listaPorPlaylist(playlist);
				
		return new ModelAndView("pages/gerenciar/playlist/playlist")
				.addObject("capitulos", capitulos_cadastrados)
				.addObject("curso_id", playlist.getCurso().getId())
				.addObject("titulo", playlist.getTitulo());
	}
	
	@RequestMapping(value="/novo-capitulo", method=RequestMethod.POST)
	public ModelAndView novoCapitulo(@Valid VideosSumario videosSumario, BindingResult result){
		if(result.hasErrors()) {
			return sumarioCreate(videosSumario, videosSumario.getPlaylist().getId());
		}
		videosSumarioDAO.insert(videosSumario);
		return new ModelAndView("redirect:/sumario/playlist/"+videosSumario.getPlaylist().getId());
	}	
	
}
