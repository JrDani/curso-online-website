package builder;

import java.util.Calendar;

import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.CursoDificuldade;
import br.com.fabricaon.cursos.model.Usuario;

public class CursoBuilder {
	private Categoria categoria;
	private Usuario usuario;
	private CursoDificuldade cursoDificuldade;
	private String descricao;
	private Integer id;
	private String titulo;
	
	public CursoBuilder() {
		categoria = new Categoria();	
		categoria.setId(1);
		categoria.setNome("Programação");
		
		usuario = new UsuarioBuilder().build();
		usuario.setId(1);
		
		cursoDificuldade = CursoDificuldade.BASICO;
		descricao = "Aprenda a orientação a objetos ao mesmo tempo que aprende uma linguagem de programação";
		titulo = "Java para iniciante";
	}
	
	public Curso build() {
		Curso curso = new Curso();
		curso.setData(Calendar.getInstance());				
		curso.setCategoria(categoria);
		curso.setTitulo(titulo);
		curso.setDescricao(descricao);	
		curso.setCurso_dificuldade(cursoDificuldade);
		curso.setUsuario(usuario);
		
		return curso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CursoDificuldade getCursoDificuldade() {
		return cursoDificuldade;
	}

	public void setCursoDificuldade(CursoDificuldade cursoDificuldade) {
		this.cursoDificuldade = cursoDificuldade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
