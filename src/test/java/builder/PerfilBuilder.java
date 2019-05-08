package builder;

import br.com.fabricaon.cursos.model.Categoria;
import br.com.fabricaon.cursos.model.Perfil;

public class PerfilBuilder {
	private Integer id;
	private String nome;
	private Categoria categoria;
	
	public PerfilBuilder() {		
		categoria = new Categoria();
		categoria.setId(1);
		categoria.setNome("Programação");
		nome = "Daniel Junior";		
	}
		
	public Perfil build() {
		Perfil perfil = new Perfil();
		perfil.setNome(nome);
		perfil.setCategoria(categoria);
		return perfil;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
