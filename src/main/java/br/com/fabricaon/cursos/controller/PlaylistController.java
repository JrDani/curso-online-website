package br.com.fabricaon.cursos.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.PlaylistDAO;
import br.com.fabricaon.cursos.model.Playlist;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	PlaylistDAO playlistDAO;
	
	@RequestMapping("/create")
	public ModelAndView playlistCreate(Integer curso_id, Playlist playlist) {
		return new ModelAndView("forms/playlist/criar-playlist")
				.addObject("curso_id", curso_id);
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editarPlaylist(@PathVariable Integer id, Playlist playlist) {
		
		Playlist playlistSelecionado = playlistDAO.find(id);
		playlist.setCurso(playlistSelecionado.getCurso());
		playlist.setId(playlistSelecionado.getId());
		playlist.setTitulo(playlistSelecionado.getTitulo());
		
		return this.playlistCreate(playlistSelecionado.getCurso().getId(), playlist);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ModelAndView insert(Playlist playlist) {
		
		Integer id = playlist.getId();
		if(id == null) {
			
			//insere
			playlist.setCalendar(Calendar.getInstance());
			id = playlistDAO.insertAndGetId(playlist); 
			
			return new ModelAndView("redirect:/sumario/playlist/"+id);
			
		}else {
			
			//update
			Playlist playlistSelecionado = playlistDAO.find(id);
			playlist.setCalendar(playlistSelecionado.getCalendar());
			playlistDAO.update(playlist);
			
			return new ModelAndView("redirect:/gerenciar-curso/curso/"+playlist.getCurso().getId());
		}
		
	}	
}