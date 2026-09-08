package com.joaomarcos.matricula.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long>{
	long countByDisciplinaAndStatus(Disciplina disciplina, StatusMatricula status);
	List<Matricula> findByAlunoAndStatus(Aluno aluno, StatusMatricula status);
}
