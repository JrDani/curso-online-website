package br.com.fabricaon.cursos.model;

import java.util.Calendar;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Playlist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@OneToMany(mappedBy="playlist")	
	@MapKey(name="capitulo")	
	private Map<Integer, VideosSumario> sumario;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="curso_id", nullable=false)
	private Curso curso;
	
	@DateTimeFormat
	@Column(nullable=false)
	private Calendar calendar;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, VideosSumario> getSumario() {
		return sumario;
	}

	public void setSumario(Map<Integer, VideosSumario> videos) {
		this.sumario = videos;
	}
	
}
