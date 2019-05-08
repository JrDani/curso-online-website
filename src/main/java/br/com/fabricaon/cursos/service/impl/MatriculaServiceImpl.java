package br.com.fabricaon.cursos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabricaon.cursos.dao.MatriculaDAO;
import br.com.fabricaon.cursos.model.Matricula;
import br.com.fabricaon.cursos.model.Usuario;
import br.com.fabricaon.cursos.service.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {
	@Autowired
	private MatriculaDAO matriculaDAO;	
	
	public boolean EhEstudanteDoCurso(Integer curso_id, Integer user_id) {
		List<Matricula> matricula = matriculaDAO.find(curso_id, user_id);
		if(matricula.isEmpty())
			return false;
		
		return true;
	}

	//return list of courses where user is subscribed
	@Override
	public List<Matricula> MatriculasDoUsuario(Usuario usuario) {
		return matriculaDAO.find(usuario.getId());	
	}
}
