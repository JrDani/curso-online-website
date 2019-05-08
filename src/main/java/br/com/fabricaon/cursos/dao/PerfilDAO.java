package br.com.fabricaon.cursos.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Perfil;

@Repository
@Transactional
public class PerfilDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Perfil perfil) {
		entityManager.persist(perfil);
	}
	
	public Perfil find(Integer id) {
		Perfil perfil = new Perfil();
		perfil.setId(id);
		return perfil;
	}

}
