package br.com.fabricaon.cursos.service;

import java.util.List;

import br.com.fabricaon.cursos.model.Matricula;
import br.com.fabricaon.cursos.model.Usuario;

public interface MatriculaService {
	public boolean EhEstudanteDoCurso(Integer curso_id, Integer user_id);
	public List<Matricula> MatriculasDoUsuario(Usuario usuario);
	
}
