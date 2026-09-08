package com.joaomarcos.matricula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joaomarcos.matricula.model.Aluno;
import com.joaomarcos.matricula.model.AlunoService;
import com.joaomarcos.matricula.model.Disciplina;
import com.joaomarcos.matricula.model.DisciplinaService;
import com.joaomarcos.matricula.model.Matricula;
import com.joaomarcos.matricula.model.MatriculaInvalidaException;
import com.joaomarcos.matricula.model.MatriculaService;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listar());
        model.addAttribute("disciplinas", disciplinaService.listarDisciplina());
        model.addAttribute("matriculas", matriculaService.listarMatricula());
        return "matricula";
    }

    @PostMapping
    public String matricular(@RequestParam Long alunoId,
                             @RequestParam Long disciplinaId,
                             RedirectAttributes redirect) {
        try {
            Aluno aluno = alunoService.buscarPorId(alunoId);
            Disciplina disciplina = disciplinaService.buscarPorId(disciplinaId);

            Matricula m = matriculaService.matricular(aluno, disciplina);
            redirect.addFlashAttribute("sucesso",
                    "Matrícula realizada em " + m.getDisciplina().getNomeDisciplina());

        } catch (MatriculaInvalidaException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }

        return "redirect:/matriculas";
    }
}