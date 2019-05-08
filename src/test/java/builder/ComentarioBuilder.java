package builder;

import java.util.Calendar;

import br.com.fabricaon.cursos.model.Comentario;
import br.com.fabricaon.cursos.model.Curso;
import br.com.fabricaon.cursos.model.Usuario;

public class ComentarioBuilder {
	private Integer id;
	private Curso curso;
	private Usuario usuario;
	private String comentario;
	private Calendar data;
	
	public ComentarioBuilder() {
		curso = new CursoBuilder().build();
		curso.setId(1);
		
		usuario = new UsuarioBuilder().build();
		usuario.setId(1);
		
		comentario = "Este curso e muito bom";
		data = Calendar.getInstance();
	}
	
	public Comentario build() {
		Comentario comentarioObj = new Comentario();
		comentarioObj.setCurso(curso);
		comentarioObj.setData(data);
		comentarioObj.setUsuario(usuario);
		comentarioObj.setComentario(comentario);
		
		return comentarioObj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	

}
