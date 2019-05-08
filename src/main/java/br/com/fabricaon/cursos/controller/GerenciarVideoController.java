package br.com.fabricaon.cursos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.fabricaon.cursos.dao.VideoDAO;
import br.com.fabricaon.cursos.dao.VideosSumarioDAO;
import br.com.fabricaon.cursos.model.Video;
import br.com.fabricaon.cursos.model.VideosSumario;
import br.com.fabricaon.cursos.validation.VideoValidation;

@Controller
@RequestMapping("/gerenciar-video")
public class GerenciarVideoController {
	
	@Autowired
	private VideosSumarioDAO videosSumarioDAO;
	@Autowired
	private VideoDAO videoDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new VideoValidation());
	}

	@RequestMapping("/video-formulario/{playlistID}/{capituloID}")
	public ModelAndView createVideo(@PathVariable Integer capituloID, @PathVariable Integer playlistID,
			@ModelAttribute("video") Video video) {
		Optional<VideosSumario> capitulo =  videosSumarioDAO.find(capituloID);
		
		if(!capitulo.isPresent())
			return new ModelAndView("redirect:/gerenciar-curso");
	
		video.setVideosSumario(capitulo.get());
		return new ModelAndView("pages/gerenciar/playlist/video")
				.addObject("playlist_id", playlistID);			
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("video") @Valid Video video,
			BindingResult result, Integer playlistID) {
		
		if(result.hasErrors()) {
			int capituloID = video.getVideosSumario().getId();
			return createVideo(capituloID, playlistID, video);
		}
		
		Integer videoId = video.getId();
		if(videoId == null) {
			
			//new
			videoDAO.insert(video);	
		}else {
			
			//update
			videoDAO.update(video);
		}
		
		return new ModelAndView("redirect:/sumario/playlist/"+playlistID);
	}
	
	@RequestMapping("/editar/{p_id}/{id}")
	public ModelAndView updateForm(@PathVariable Integer p_id, @PathVariable Integer id, Video video) {
		
		Optional<Video> videoSelecionado = videoDAO.find(id);
		if(!videoSelecionado.isPresent())
			return new ModelAndView("redirect:/sumario/playlist/"+p_id);		
		
		video.setId(videoSelecionado.get().getId());
		video.setDuracao_minuto(videoSelecionado.get().getDuracao_minuto());
		video.setTitulo(videoSelecionado.get().getTitulo());
		video.setUrl(videoSelecionado.get().getUrl());
		video.setVideosSumario(videoSelecionado.get().getVideosSumario());
		
		return this.createVideo(videoSelecionado.get().getVideosSumario().getId(), p_id, video);
	}
	
	@RequestMapping("/deletar/{id}/redirect/{playlist_id}")
	public ModelAndView deletar(@PathVariable Integer id, @PathVariable Integer playlist_id) {
		
		Optional<Video> find = videoDAO.find(id);
		if(find.isPresent())
			videoDAO.delete(find.get());
		
		return new ModelAndView("redirect:/sumario/playlist/"+playlist_id);
	}
}
