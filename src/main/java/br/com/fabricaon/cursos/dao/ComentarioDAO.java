package br.com.fabricaon.cursos.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Comentario;

@Repository
@Transactional
public class ComentarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Optional<Comentario> find(Integer id){
		return Optional.ofNullable(entityManager.find(Comentario.class, id));
	}
	
	public void insert(Comentario comentario) {
		entityManager.persist(comentario);
	}
	
	public void update(Comentario comentario) {
		entityManager.merge(comentario);
	}
	
	public void delete(Comentario comentario) {
		entityManager.remove(comentario);
	}
		
	public List<Comentario> listarPorCurso(Integer curso_id) {
		String jpql = "from Comentario where curso.id = :id";		
		TypedQuery<Comentario> query = entityManager.createQuery(jpql, Comentario.class);
		query.setParameter("id", curso_id);	
		
		return query.getResultList();	
	}
	
}
