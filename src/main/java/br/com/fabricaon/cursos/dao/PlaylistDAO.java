package br.com.fabricaon.cursos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Playlist;

@Repository
@Transactional
public class PlaylistDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Playlist> listaPorCurso(Curso curso){
		return entityManager.createQuery("from Playlist where curso = :c", Playlist.class)
				.setParameter("c", curso)
				.getResultList();
	}
	/*
	public List<Video> VideosPorCurso(Curso curso) {
		String jpql = "v from Video v, VideosSumario s, Playlist p where v.videosSumario.id = s.id and "
				+ "s.playlist.id = p.id and p.curso = :c";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class)
				.setParameter("c", curso);
		
		return query.getResultList();
	}
	*/

	public Integer insertAndGetId(Playlist playlist) {
		 entityManager.persist(playlist);
		 entityManager.flush();
		 return playlist.getId();
	}
	
	public Playlist find(Integer playlistid) {
		return entityManager.find(Playlist.class, playlistid);
	}

	public void update(Playlist playlist) {
		
		entityManager.merge(playlist);
	}
}
