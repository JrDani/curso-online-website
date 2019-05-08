package br.com.fabricaon.cursos.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fabricaon.cursos.dao.ComentarioDAO;
import br.com.fabricaon.cursos.model.Comentario;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.validation.ComentarioValidation;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ComentarioValidation());
	}
	
	@RequestMapping("/create")
	public ModelAndView form(Comentario comentario) {
		return new ModelAndView("forms/comentario/create-comentario");
	}
	
	@RequestMapping(value="/comentar", method=RequestMethod.POST)
	public ModelAndView comentar(@Valid Comentario comentario, BindingResult result, @RequestParam String para, 
			RedirectAttributes redirectAttributes) {		
		
		//setup the redirect page
		para = para.split("/", 3)[2];
		ModelAndView modelAndView = new ModelAndView("redirect:/"+para+"#comentario");
				
		//validation error
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", result.getFieldError().getCode());
			return modelAndView;
		}
		
		//ok! insert
		Usuario usuario = new Usuario();
		usuario.setId(1);
		comentario.setData(Calendar.getInstance());
		comentario.setUsuario(usuario);
		comentarioDAO.insert(comentario);
		
		return modelAndView;
	}
}
