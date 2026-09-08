package com.joaomarcos.matricula.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int vaga;
	private String nomeDisciplina;
	private int horas;
	
	@ManyToMany
	private List<Disciplina> preRequisitos;
	@Enumerated(EnumType.STRING)
	private DiaDisciplina dia;
	@Enumerated(EnumType.STRING)
	private TurnoDisciplina turno;
	
	public Disciplina() {}
	
	public Disciplina(int vaga,String nomeDisciplina,int horas,DiaDisciplina dia,TurnoDisciplina turno) {
		this.horas = horas;
		this.nomeDisciplina = nomeDisciplina;
		this.vaga = vaga;
		this.dia = dia;
		this.turno = turno;
	}
	
	public List<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(List<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public void setTurnoDisciplina(TurnoDisciplina turno) {
		this.turno = turno;
	}
	public TurnoDisciplina getTurnoDisciplina() {
		return turno;
	}
	public void setDiaDisciplina(DiaDisciplina dia) {
		this.dia = dia;
	}
	public DiaDisciplina getDiaDisciplina() {
		return dia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVaga() {
		return vaga;
	}
	public void setVaga(int vaga) {
		this.vaga = vaga;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", vaga=" + vaga + ", nomeDisciplina=" + nomeDisciplina + ", horas=" + horas
				+ ", dia=" + dia + ", turno=" + turno + "]";
	}
	
	
	
	
}
