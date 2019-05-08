package dao;

import java.math.BigDecimal;
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
import br.com.fabricaon.cursos.dao.CursoDAO;
import br.com.fabricaon.cursos.dao.PerfilDAO;
import br.com.fabricaon.cursos.dao.UsuarioDAO;
import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Perfil;
import br.com.fabricaon.cursos.model.Usuario;
import builder.CursoBuilder;
import builder.PerfilBuilder;
import builder.UsuarioBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JpaConfiguration.class, CursoDAO.class, CategoriaDAO.class, 
		UsuarioDAO.class, PerfilDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class CursoDAOTest {
	
	@Autowired
	private CursoDAO cursoDAO;	
	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private PerfilDAO perfilDAO;
	
	@Before
	public void dependenciasObjetos() {
		insertCategoria();
		insertPerfil();
		insertUsuario();
	}
	
	@Test
	@DirtiesContext
	@Transactional
	public void testeCRUD() {
		
		Curso curso = new CursoBuilder().build();

		//Create
		try{			
			cursoDAO.insert(curso);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("erro ao cadastrar");
		}	
		
		//Read
		Optional<Curso> resultado = cursoDAO.find(1);
		if(!resultado.isPresent()) 
			Assert.fail("erro ao selecionar");
		
		//update
		resultado.get().setTitulo("testando");
		cursoDAO.update(resultado.get());
		Optional<Curso> novoResultado = cursoDAO.find(1);
		Assert.assertEquals("testando", novoResultado.get().getTitulo());
		
		//delete		
		cursoDAO.delete(novoResultado.get());
		Optional<Curso> check = cursoDAO.find(1);
		if(check.isPresent())
			Assert.fail("Não deletou");
	}
	
	@Test		
	@Transactional	
	public void lista(){	
		
		Curso curso = new CursoBuilder().build(); 
		
		Curso curso2 = new CursoBuilder().build();
		curso2.setTitulo("PHP 7");		
		curso2.setValor(new BigDecimal(50.50));
		
		Curso curso3 = new CursoBuilder().build();
		curso3.setTitulo("Typescript");
		curso3.setValor(new BigDecimal(200));
		
		cursoDAO.insert(curso);
		cursoDAO.insert(curso2);
		cursoDAO.insert(curso3);
		
		List<Curso> cursos = cursoDAO.lista();
		if(cursos.size() != 3) {
			Assert.fail("Listar falhou");
		}
		BigDecimal valor = new BigDecimal(0);
	
		for (Curso c : cursos) {
			valor = valor.add(c.getValor());
		}
		
		Assert.assertEquals(new BigDecimal(250.5).setScale(1), valor);
	}
	
	public void insertCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNome("Programação");
		try{
			categoriaDAO.insert(categoria);
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("erro ao cadastrar a categoria");
		}	
	}
	
	public void insertPerfil() {
		Perfil perfil = new PerfilBuilder().build();
		
		try{
			perfilDAO.insert(perfil);
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("erro ao cadastrar o perfil");
		}	
	}
	
	public void insertUsuario() {
		Usuario usuario = new UsuarioBuilder().build();
		
		try{
			usuarioDAO.insert(usuario);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("erro ao cadastrar o usuário");
		}	
	}

	
}
