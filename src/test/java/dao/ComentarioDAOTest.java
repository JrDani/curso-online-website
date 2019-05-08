package dao;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricaon.cursos.config.DataSourceConfigurationTest;
import br.com.fabricaon.cursos.config.JpaConfiguration;
import br.com.fabricaon.cursos.dao.CategoriaDAO;
import br.com.fabricaon.cursos.dao.ComentarioDAO;
import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.dao.PerfilDAO;
import br.com.fabricaon.cursos.dao.UsuarioDAO;
import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Comentario;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Perfil;
import br.com.fabricaon.cursos.model.Usuario;
import builder.ComentarioBuilder;
import builder.CursoBuilder;
import builder.PerfilBuilder;
import builder.UsuarioBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {JpaConfiguration.class, ComentarioDAO.class,
		CursoDAO.class, UsuarioDAO.class, CategoriaDAO.class, PerfilDAO.class,
		DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ComentarioDAOTest {
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private PerfilDAO perfilDAO;
	
	@Before	
	public void dependenciaObjetos() {
		insertCategoria();
		insertPerfil();
		insertUsuario();
		insertCurso();		
	}
	
	@Test
	@DirtiesContext
	@Transactional
	public void crudTest() {
		Comentario comentario = new ComentarioBuilder().build();
		
		//create
		insert(comentario);
				
		//find + update
		Optional<Comentario> novoComentario = select();	
		if(!novoComentario.isPresent())
			Assert.fail("Erro ao selecionar");
		novoComentario.get().setComentario("Ola");
		update(novoComentario.get());		
		Optional<Comentario> checkingUpdate = select();
		Assert.assertEquals("Ola", checkingUpdate.get().getComentario());
		
		//find + delete
		Optional<Comentario> paraDeletar = select();
		if(!paraDeletar.isPresent())
			Assert.fail("Erro ao selecionar para deletar");
		delete(paraDeletar.get());
		
		Optional<Comentario> checkingDelete = select();
		if(checkingDelete.isPresent())
			Assert.fail("Erro ao deletar");
	
	}
	
	@Test
	@Transactional
	public void listarPorCursoTest() {
		Comentario comentario = new ComentarioBuilder().build();
		Comentario comentario2 = new ComentarioBuilder().build();		
		Comentario comentario3 = new ComentarioBuilder().build();
		Comentario comentario4 = new ComentarioBuilder().build();
		
		insert(comentario);
		insert(comentario2);
		insert(comentario3);
		insert(comentario4);
		
		List<Comentario> comentariosProCurso1 = comentarioDAO.listarPorCurso(1);
		Assert.assertEquals(4, comentariosProCurso1.size());
		
		List<Comentario> comentariosProCurso2 = comentarioDAO.listarPorCurso(2);
		Assert.assertEquals(0, comentariosProCurso2.size());
		
	}
	
	
	public void insert(Comentario comentario) {
		try {
			comentarioDAO.insert(comentario);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Inserção falhou");
		}
	}
	
	public Optional<Comentario> select() {
		Optional<Comentario> comentario = comentarioDAO.find(1);		
		return comentario;		
	}
	
	public void update(Comentario comentario) {
		comentarioDAO.update(comentario);
	}
	
	public void delete(Comentario comentario) {
		comentarioDAO.delete(comentario);
	}
	

	public void insertCurso() {
		Curso curso = new CursoBuilder().build();
		try {
			cursoDAO.insert(curso);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Inserção do curso falhou");
		}
	}
	
	public void insertCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNome("Programação");
		
		try {
			categoriaDAO.insert(categoria);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Inserção da categoria falhou");
		}
	}
	
	public void insertPerfil() {
		Perfil perfil = new PerfilBuilder().build();		
		
		try {
			perfilDAO.insert(perfil);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Inserção do perfil falhou");
		}
	}
	
	public void insertUsuario() {
		Usuario usuario = new UsuarioBuilder().build();
		try {
			usuarioDAO.insert(usuario);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Inserção do usuário falhou");
		}
	}

	
}
