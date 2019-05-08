package br.com.fabricaon.cursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/certificado")
public class CertificadoController {

	@RequestMapping(method=RequestMethod.POST)
	public String lista() {
		//checa se usuario tem perfil, se não tiver avisa para ele que não é permitido gerar um certificado
		//matricula.status = concluido gera certificado se nao apenas mostra o progresso
		return null;
	}
	
	@RequestMapping("/gera/{matricula_id}")
	public String get(@PathVariable Integer matricula_id) {
		//select cursos where matricula_id and 
		return null;
	}
}
