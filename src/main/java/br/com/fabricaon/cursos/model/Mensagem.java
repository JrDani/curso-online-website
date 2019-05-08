package br.com.fabricaon.cursos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="remetente_id", nullable = false)
	private Usuario remetente;	

	@ManyToOne
	@JoinColumn(name="destinatario_id", nullable = false)
	private Usuario destinatario;	
	
	private Boolean foi_lido = false;
	private Boolean esta_ativo = true;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public Boolean getEsta_ativo() {
		return esta_ativo;
	}
	public void setEsta_ativo(Boolean esta_ativo) {
		this.esta_ativo = esta_ativo;
	}
	public Usuario getRemetente() {
		return remetente;
	}
	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	public Boolean getFoi_lido() {
		return foi_lido;
	}
	public void setFoi_lido(Boolean foi_lido) {
		this.foi_lido = foi_lido;
	}
		
}
