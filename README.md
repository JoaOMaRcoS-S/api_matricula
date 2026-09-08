Sistema de Matrícula em Disciplina

Aplicação web construída como exercício de modelagem MVC para a disciplina de Arquitetura de Software (Engenharia de Software — UCSAL).

Um aluno seleciona uma disciplina e se matricula. Antes de confirmar, o sistema valida três regras de negócio: disponibilidade de vaga, cumprimento dos pré-requisitos e ausência de choque de horário. Quando alguma regra falha, a interface exibe o motivo específico da recusa.

Stack
Java 21
Spring Boot 4.1.1 (Spring MVC, Spring Data JPA)
Thymeleaf
H2 (banco em memória)
Maven
Como executar
bash
./mvnw spring-boot:run

No Windows:

mvnw.cmd spring-boot:run

A aplicação sobe em http://localhost:8080/matriculas.

O banco é em memória e é recriado a cada inicialização. Um CommandLineRunner popula alunos e disciplinas de exemplo automaticamente — incluindo casos que exercitam as três regras de validação.

Arquitetura

A estrutura de pacotes espelha as três camadas do padrão MVC:

com.joaomarcos.matricula
├── model         → entidades, enums, services, repositories, exception
├── controller    → MatriculaController
└── (view)        → templates Thymeleaf em src/main/resources/templates
Decisões de projeto

As regras de negócio estão no Model, não no Controller. As três validações vivem no MatriculaService. O critério aplicado foi: se a interface fosse substituída por um aplicativo mobile, nenhuma delas precisaria ser reescrita. Regra de negócio é conhecimento sobre o domínio, não sobre a forma de acesso.

Service e Repository estão sob model. Ambos compõem a camada Model do MVC — dados e regras. A separação interna entre eles segue o princípio de responsabilidade única, mas não constitui camadas adicionais do padrão.

Cada validação é um método separado. temVaga, cumpriuPreRequisitos e temChoqueHorario são independentes para que a alteração de uma regra não obrigue a modificar o código das outras.

O método salvar público foi removido do MatriculaService. A criação de matrícula acontece exclusivamente pelo método matricular, que executa as validações antes de persistir. Um salvar acessível permitiria contornar as regras, tornando-as opcionais.

Matricula é uma entidade própria, não uma lista dentro de Aluno. O vínculo entre aluno e disciplina carrega informação própria — status e data de matrícula. É esse status que permite distinguir disciplina cursada de disciplina concluída, distinção necessária para a verificação de pré-requisitos.

Disciplina tem auto-relacionamento. Os pré-requisitos são uma lista de outras disciplinas, mapeada com @ManyToMany.

Vagas disponíveis são calculadas, não armazenadas. O campo vaga guarda o total da turma; a ocupação vem da contagem de matrículas ativas. Armazenar o saldo criaria um dado derivado sujeito a divergir do estado real.

O POST usa Post-Redirect-Get. Após processar a matrícula, o controller redireciona em vez de renderizar, evitando reenvio do formulário em caso de atualização da página.

Regras implementadas
Validação	Critério
Vaga	Matrículas ativas na disciplina devem ser menores que o total de vagas
Pré-requisito	Todas as disciplinas exigidas devem constar como concluídas pelo aluno
Choque de horário	Nenhuma disciplina ativa do aluno pode ter o mesmo dia e turno
Autor

João Marcos — Engenharia de Software, UCSAL
