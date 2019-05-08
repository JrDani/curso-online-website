package builder;

import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Perfil;
import br.com.fabricaon.cursos.model.Usuario;

public class UsuarioBuilder {
	private Integer id;
	private String username;
	private String password;
	private String passwordConfirm;
	private Categoria categoria;
	private Perfil perfil;
	
	public UsuarioBuilder() {
		categoria = new Categoria();
		categoria.setId(1);
		categoria.setNome("Programação");
		
		perfil = new PerfilBuilder().build();
		perfil.setId(1);

		password = "123";
		username = "test";
		passwordConfirm = "123";
	}
	
	public Usuario build() {		
		Usuario usuario = new Usuario();
		usuario.setPassword(password);
		usuario.setUsername(username);
		usuario.setPerfil(perfil);
		return usuario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}	
	
}
