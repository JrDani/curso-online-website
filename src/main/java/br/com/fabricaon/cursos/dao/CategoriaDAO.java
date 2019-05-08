package br.com.fabricaon.cursos.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Categoria;

@Repository
@Transactional
public class CategoriaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Optional<Categoria> find(Integer id) {
		return Optional.ofNullable(entityManager.find(Categoria.class, id));
	}
	
	public List<Categoria> lista(){
		return entityManager.createQuery("from Categoria", Categoria.class)
				.getResultList();
	}
	
	public void insert(Categoria categoria) {
		entityManager.persist(categoria);
	}
	
	public void update(Categoria categoria) {
		
		entityManager.merge(categoria);	
	}
	
	public void delete(Categoria categoria) {		
		
		entityManager.remove(categoria);
	}
}
