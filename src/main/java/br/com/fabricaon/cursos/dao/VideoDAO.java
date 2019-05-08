package br.com.fabricaon.cursos.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Video;

@Repository
@Transactional
public class VideoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Video video) {
		
		entityManager.persist(video);
	}

	public Optional<Video> find(Integer id) {

		return Optional.ofNullable(entityManager.find(Video.class, id));		
	}

	public void update(Video video) {
		
		entityManager.merge(video);
	}
	
	public void delete(Video video) {
		
		entityManager.remove(video);
	}
}
