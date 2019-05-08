package br.com.fabricaon.cursos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.fabricaon.cursos.model.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Usuario usuario){
		
		entityManager.persist(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuario = entityManager.createQuery("select u from Usuario u where u.username = :user", Usuario.class)
				.setParameter("user", username)
				.getResultList();
		
		if(usuario.isEmpty()) {
			throw new RuntimeException("Este email não está cadastrado no sistema");
		}
		
		return usuario.get(0);
	}
}
