package br.com.fabricaon.cursos.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne	
	@JoinColumn(nullable=false)
	private Categoria categoria; 
	
	@Column(nullable = false)	
	private String titulo;
	
	@Column(nullable = false, columnDefinition="TEXT")
	private String descricao;	
	
	@Enumerated(EnumType.STRING)
    @Column(name="curso_dificuldade", nullable=false)
	private CursoDificuldade curso_dificuldade;
	
	@JoinColumn(name="usuario_id", nullable=false)
	@ManyToOne	
	private Usuario usuario;
	
	@Column(nullable = false)
	private BigDecimal valor = new BigDecimal(0);	
	
	@Column(nullable = false)
	@DateTimeFormat
	private Calendar data;	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public CursoDificuldade getCurso_dificuldade() {
		return curso_dificuldade;
	}
	public void setCurso_dificuldade(CursoDificuldade curso_dificuldade) {
		this.curso_dificuldade = curso_dificuldade;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void clonar(Curso alvo) {
		
		this.categoria = alvo.getCategoria();
		this.curso_dificuldade = alvo.getCurso_dificuldade();
		this.data = alvo.getData();
		this.descricao = alvo.getDescricao();
		this.id = alvo.getId();
		this.titulo = alvo.getTitulo();
		this.usuario = alvo.getUsuario();
		this.valor = alvo.getValor();
	}
	
}
