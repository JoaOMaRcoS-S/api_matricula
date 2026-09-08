package com.joaomarcos.matricula.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Matricula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	@Enumerated(EnumType.STRING)
	private StatusMatricula status;
	private LocalDateTime matriculado;
	
	public Matricula () {}
	
	public Matricula (Aluno aluno,Disciplina disciplina,StatusMatricula status,LocalDateTime matriculado) {
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.matriculado = LocalDateTime.now();
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public StatusMatricula getStatus() {
		return status;
	}

	public void setStatus(StatusMatricula status) {
		this.status = status;
	}

	public LocalDateTime getMatriculado() {
		return matriculado;
	}

	public void setMatriculado(LocalDateTime matriculado) {
		this.matriculado = matriculado;
	}

	@Override
	public String toString() {
		return "Matricula [id=" + id + ", aluno=" + aluno + ", disciplina=" + disciplina + ", status=" + status
				+ ", matriculado=" + matriculado + "]";
	}
}
