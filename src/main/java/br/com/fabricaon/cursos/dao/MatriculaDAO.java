package br.com.fabricaon.cursos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Matricula;

@Repository
@Transactional
public class MatriculaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Matricula matricula) {
		entityManager.persist(matricula);
	}

	public List<Matricula> find(Integer curso_id, Integer user_id) {
		return entityManager.createQuery("from Matricula where curso_id = :c and usuario_id = :u", 
				Matricula.class)
				.setParameter("c", curso_id)
				.setParameter("u", user_id)
				.getResultList();	
	}
	
	public List<Matricula> find(Integer user_id){
		return entityManager.createQuery("from Matricula where usuario_id = :u", Matricula.class)
				.setParameter("u", user_id)
				.getResultList();
	}
}
