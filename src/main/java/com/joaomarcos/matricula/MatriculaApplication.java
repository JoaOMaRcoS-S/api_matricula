package com.joaomarcos.matricula;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joaomarcos.matricula.model.Aluno;
import com.joaomarcos.matricula.model.AlunoRepository;
import com.joaomarcos.matricula.model.DiaDisciplina;
import com.joaomarcos.matricula.model.Disciplina;
import com.joaomarcos.matricula.model.DisciplinaRepository;
import com.joaomarcos.matricula.model.TurnoDisciplina;

@SpringBootApplication
public class MatriculaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatriculaApplication.class, args);
    }

    @Bean
    CommandLineRunner popular(AlunoRepository alunoRepo, DisciplinaRepository disciplinaRepo) {
        return args -> {
            Aluno joao = new Aluno("João Marcos", 22, "147765344");
            alunoRepo.save(joao);
            
            Aluno fernandaSantos = new Aluno("Fernanda dos Santos", 30, "3453453454");
            alunoRepo.save(fernandaSantos);
            
            Aluno claudioJose = new Aluno("Claudio Jose", 19, "567576567567");
            alunoRepo.save(claudioJose);
            
            Aluno mariaAntonia = new Aluno("Maria Antônia", 20, "564545747");
            alunoRepo.save(mariaAntonia);
            
            Aluno sandro = new Aluno("Sandro Leonardo", 26, "187867867845");
            alunoRepo.save(sandro);

            Disciplina calculo1 = new Disciplina(40, "Cálculo I", 60,
                    DiaDisciplina.SEGUNDA, TurnoDisciplina.NOTURNO);
            disciplinaRepo.save(calculo1);

            Disciplina algebra = new Disciplina(30, "Álgebra Linear", 60,
                    DiaDisciplina.TERÇA, TurnoDisciplina.NOTURNO);
            disciplinaRepo.save(algebra);

            Disciplina calculo2 = new Disciplina(25, "Cálculo II", 60,
                    DiaDisciplina.QUARTA, TurnoDisciplina.NOTURNO);
            calculo2.setPreRequisitos(List.of(calculo1));
            disciplinaRepo.save(calculo2);

            Disciplina choque = new Disciplina(20, "Estrutura de Dados", 60,
                    DiaDisciplina.SEGUNDA, TurnoDisciplina.NOTURNO);
            disciplinaRepo.save(choque);
            Disciplina sistemasDistribuidos = new Disciplina(20, "Sistemas Distribuidos", 60,
                    DiaDisciplina.TERÇA, TurnoDisciplina.MATUTINO);
            disciplinaRepo.save(sistemasDistribuidos);
            Disciplina compiladores = new Disciplina(30, "Compiladores", 90,
                    DiaDisciplina.SEXTA, TurnoDisciplina.VESPERTINO);
            disciplinaRepo.save(compiladores);
            Disciplina redes1 = new Disciplina(40, "Redes I", 60,
                    DiaDisciplina.SEGUNDA, TurnoDisciplina.NOTURNO);
            disciplinaRepo.save(redes1);
            Disciplina redes2 = new Disciplina(40, "Redes II", 60,
                    DiaDisciplina.SEGUNDA, TurnoDisciplina.NOTURNO);
            redes2.setPreRequisitos(List.of(redes1));
            disciplinaRepo.save(redes2);
        };
    }
}
