package com.joaomarcos.matricula.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {
	@Autowired
	private MatriculaRepository repository;
	
	public List<Matricula> listarMatricula(){
		return repository.findAll();
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Matricula atualizar(Long id, Matricula m) {
		Matricula atual = repository.findById(id).orElseThrow();
		atual.setStatus(m.getStatus());
		repository.save(atual);
		return atual;
	}
	
	public boolean temVaga(Disciplina d) {
		long ocupadas = repository.countByDisciplinaAndStatus(d, StatusMatricula.CONCLUIDO);
		return ocupadas < d.getVaga();
	}
	
	public boolean cumpriuPreRequisito(Aluno aluno,Disciplina d) {
		if(d.getPreRequisitos().isEmpty()|| d.getPreRequisitos()==null) {
			return true;
		}
		List<Disciplina> concluidas = repository
				.findByAlunoAndStatus(aluno, StatusMatricula.CONCLUIDO)
				.stream().map(Matricula::getDisciplina)
				.toList();
		return concluidas.containsAll(d.getPreRequisitos());		
	}
	
	public boolean temChoqueHorario(Aluno a, Disciplina d) {		
		return repository
				.findByAlunoAndStatus(a, StatusMatricula.ATIVO)
				.stream()
				.map(Matricula:: getDisciplina)
				.anyMatch(outra -> outra.getDiaDisciplina()== d.getDiaDisciplina() && outra.getTurnoDisciplina() == d.getTurnoDisciplina());
		
	}
	
	public Matricula matricular(Aluno a, Disciplina d) {
		if(!temVaga(d)) {
			throw new MatriculaInvalidaException("Não há vagas para esta disciplina"); 
		}
		if(!cumpriuPreRequisito(a, d)) {
			throw new MatriculaInvalidaException("Aluno possui pré-requisitos para realizar a matrícula para esta disciplina");
			
		}
		if(temChoqueHorario(a, d)) {
			throw new MatriculaInvalidaException("As disciplinas e/ou turno selecionados são iguais, por favor altere para prosseguir");
		}
		Matricula m = new Matricula(a,d,StatusMatricula.ATIVO, LocalDateTime.now());
		return repository.save(m);
	}
}
