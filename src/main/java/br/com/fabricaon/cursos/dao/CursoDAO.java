package br.com.fabricaon.cursos.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Usuario;

@Repository
@Transactional
public class CursoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Curso curso) {
		
		entityManager.persist(curso);			
	}
	
	public Integer insertAndGetId(Curso curso) {
		
		entityManager.persist(curso);
		entityManager.flush();
		return curso.getId();		
	}
	
	public Optional<Curso> find(Integer id) {		
		
		return Optional.ofNullable(entityManager.find(Curso.class, id));
	}
	
	public void update(Curso curso) {
		
		entityManager.merge(curso);	
	}
	
	public void delete(Curso curso) {		
		
		entityManager.remove(curso);
	}
	
	public List<Curso> lista(){
		
		return entityManager.createQuery("from Curso", Curso.class)
				.getResultList();
	}
	
	public List<Curso> pesquisar(String consulta) {
		
		String jpql = "from Curso c where c.titulo like :consulta or c.descricao like :consulta";
		TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
				query.setParameter("consulta", "%"+consulta+"%");
				
		return query.getResultList();
	}

	public List<Curso> listaPorDono(Usuario usuario) {
		
		TypedQuery<Curso> query = entityManager.createQuery("from Curso c where c.usuario = :u",
				Curso.class).setParameter("u", usuario);
		
		return query.getResultList();
	}
}
