package br.com.fabricaon.cursos.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Playlist;
import br.com.fabricaon.cursos.model.VideosSumario;

@Repository
@Transactional
public class VideosSumarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<VideosSumario> listaPorPlaylist(Playlist playlist){
		return entityManager.createQuery("from VideosSumario where playlist = :p", VideosSumario.class)
				.setParameter("p", playlist)
				.getResultList();
	}
	
	
	public void insert(VideosSumario videosSumario) {
		entityManager.persist(videosSumario);
	}


	public Optional<VideosSumario> find(Integer capituloID) {
		return Optional.ofNullable(entityManager.find(VideosSumario.class, capituloID));
	}
}
