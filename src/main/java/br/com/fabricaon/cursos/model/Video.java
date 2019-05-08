package br.com.fabricaon.cursos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String url;
	
	private String titulo;
	@Column(nullable=false)
	private Integer duracao_minuto;
	
	@ManyToOne	
	@JoinColumn(name="videos_sumario_id", nullable=false)
	private VideosSumario videosSumario;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getDuracao_minuto() {
		return duracao_minuto;
	}
	public void setDuracao_minuto(Integer duracao_minuto) {
		this.duracao_minuto = duracao_minuto;
	}
	public VideosSumario getVideosSumario() {
		return videosSumario;
	}
	public void setVideosSumario(VideosSumario videosSumario) {
		this.videosSumario = videosSumario;
	}
	
}
