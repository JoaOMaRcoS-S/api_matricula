package com.joaomarcos.matricula.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;
	
	public Aluno salvar(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public List<Aluno> listar(){
		return repository.findAll();
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Aluno atualizar(Long id,Aluno aluno) {
		Aluno atual = repository.findById(id).orElseThrow();
		atual.setCpf(aluno.getCpf());
		atual.setIdade(aluno.getIdade());
		atual.setNome(aluno.getNome());
		repository.save(atual);
		return atual;
	}
	
	public Aluno buscarPorId(Long id) {
	    return repository.findById(id).orElseThrow();
	}
}
