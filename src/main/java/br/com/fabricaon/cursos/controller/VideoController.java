package br.com.fabricaon.cursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("{tituloCurso}/videos")
public class VideoController {
	
	/*
	 auto wired
	 Historico de videos
	 */
	
	
	
	@RequestMapping("/{playlist_id}/{video_id}")
	public ModelAndView openVideo(@PathVariable Integer playlist_id, @PathVariable Integer video_id) {
		//Playlist playlist = dao.find(playlist_id);
		//Video video = dao.find(viode_id);
		ModelAndView modelAndView = new ModelAndView("pages/curso/videos/videos");
		System.out.println("Mostrando video "+playlist_id+" da playlist "+video_id);
		
		/* acrescenta no historico, e chama metodo retorno booleano para dizer se é a hora de gerar certificado
			se sim define que link proximo deva abrir uma requisicao atendida por certificado controller
			e tambem na matricula é definido o status como concluido, na view dos video tem uma opcao que mostra 
			"gerar certificado" caso o curso foi concluido
		 */
			
		return modelAndView;
	}
	
	@RequestMapping("/{playlist_id}")
	public ModelAndView primeiroVideoDaPlaylist(@PathVariable Integer playlist_id) {
		return new ModelAndView("pages/curso/videos/videos");
	}
	
	
}
