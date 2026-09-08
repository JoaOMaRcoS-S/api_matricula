package com.joaomarcos.matricula.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repository;
	
	public List<Disciplina> listarDisciplina(){
		return repository.findAll();
	}
	
	public Disciplina salvar(Disciplina d) {
		return repository.save(d);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Disciplina atualizar(Long id, Disciplina d) {
		Disciplina atual = repository.findById(id).orElseThrow();
		atual.setDiaDisciplina(d.getDiaDisciplina());
		atual.setHoras(d.getHoras());
		atual.setNomeDisciplina(d.getNomeDisciplina());
		atual.setTurnoDisciplina(d.getTurnoDisciplina());
		repository.save(atual);
		return atual;
	}
	
	public Disciplina buscarPorId(Long id) {
	    return repository.findById(id).orElseThrow();
	}
}
