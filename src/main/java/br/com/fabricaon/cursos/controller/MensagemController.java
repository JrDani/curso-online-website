package br.com.fabricaon.cursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fabricaon.cursos.model.Mensagem;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {

	@RequestMapping(method=RequestMethod.GET)
	public String lista(){
		return null;
	}
	
	@RequestMapping("/form")
	public String create() {
		return "forms/mensagem/create-message";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String insert(Mensagem mensagem) {
		return null;
	}
	
}
