package br.com.fabricaon.cursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/access-denied")
	public ModelAndView accessDeniedPage() {
		return new ModelAndView("pages/error/accessDenied");
	}
}
