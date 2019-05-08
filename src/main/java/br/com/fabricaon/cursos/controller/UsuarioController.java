package br.com.fabricaon.cursos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fabricaon.cursos.dao.UsuarioDAO;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.validation.NewUserValidation;
import br.com.fabricaon.cursos.validation.UserValidation;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
		
	@InitBinder("novoUsuario")
	public void initBinder2(WebDataBinder binder) {
		binder.addValidators(new NewUserValidation());
	}
	
	@InitBinder("usuario")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidation());
	}
	
	@RequestMapping("/create")
	public ModelAndView create(@ModelAttribute("novoUsuario") Usuario novoUsuario) {
		return new ModelAndView("forms/usuario/create-usuario");
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("novoUsuario") @Valid Usuario novoUsuario, BindingResult result2,
			RedirectAttributes redirectAttributes) {
		
		if (result2.hasErrors()) {
			return create(novoUsuario);
		}
		
		usuarioDAO.insert(novoUsuario);
		
		redirectAttributes.addFlashAttribute("usuario_novo", true);
		return new ModelAndView("redirect:/perfil/");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginForm() {
		return new ModelAndView("pages/usuario/login");
	}	
	
	@RequestMapping("/login/form")
	public ModelAndView loginGenerateForm(Usuario usuario) {
		return new ModelAndView("forms/usuario/login");
	}	
	
}
