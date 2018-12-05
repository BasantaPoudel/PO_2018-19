Portal do Docente

Este menu apresenta as operações disponíveis para docentes, estando apenas disponível para estes utilizadores.
A lista completa é a seguinte: Criar projecto, Fechar projecto, Ver submissões de um projecto, Ver alunos de disciplina leccionada, Ver resultados de um inquérito.

As etiquetas das opções deste menu estão definidas na classe sth.app.teaching.Label.
Todos os métodos correspondentes às mensagens de diálogo para este menu estão definidos na classe sth.app.teaching.Message.

Sempre que é pedido o nome da disciplina (requestDisciplineName()), é lançada a excepção NoSuchDisciplineException, se a disciplina indicada não existir ou se o docente não leccionar a disciplina em causa.

Estes comandos já estão implementados nas classes da package sth.app.teaching (disponível no CVS), respectivamente:
DoCreateProject, DoCloseProject, DoShowProjectSubmissions, DoShowDisciplineStudents, DoShowSurveyResults.

_____________________1_________________________________________________
Criar projecto

É pedido o nome da disciplina e o nome do projecto
no contexto dessa disciplina (requestProjectName()).
____________________________
É lançada a excepção DuplicateProjectException, se o projecto já existir.

-Em caso de identificação bem sucedida, o projecto
 é criado.
___________________2____________________________________________________
Fechar projecto

É pedido o nome da disciplina e o nome do projecto
no contexto dessa disciplina (requestProjectName()).
É lançada a excepção NoSuchProjectException, se o projecto não existir.
____________________________
-Em caso de identificação bem sucedida,
o projecto é fechado.
-Se o projecto já estiver fechado, o comando
não executa nenhuma acção.

____________________3___________________________________________________

Ver submissões de um projecto

É pedido o nome da disciplina e o nome do projecto
 no contexto dessa disciplina (requestProjectName()).
É lançada a excepção NoSuchProjectException, se o projecto não existir.
____________________________
-Em caso de identificação bem sucedida,
as submissões do projecto são apresentadas,
ordenadas pelo número de aluno que realizou a submissão,
e com o seguinte formato.

Formato de apresentação

Nome da Disciplina - Nome do Projecto
* Identificador do 1º aluno - submissão do 1º aluno
...
* Identificador do Nº aluno - submissão do Nº aluno

Exemplo de apresentação

Programação com Objectos - Gatos Simples
* 0234 - Gato.java
* 6789 - Cat.java
* 7912 - Tigre.java

Este comando pode operar,
tanto sobre projectos abertos, como sobre projectos fechados.

*___________4____________________________________________________________

Ver alunos de disciplina leccionada

É pedido o nome da disciplina.
-Em caso de identificação bem sucedida,
é apresentada a lista de alunos,
 ordenada pelo identificador de aluno,
 no formato definido em Mostrar pessoa.

*_______________________5________________________________________________
Ver resultados de um inquérito

É pedido o nome da disciplina e o nome do projecto
 no contexto dessa disciplina (requestProjectName()).
É lançada
 a excepção NoSuchProjectException, se o projecto não existir,
 e NoSurveyException, se o inquérito não tiver sido criado para o projecto em causa.

Em caso de identificação bem sucedida, os resultados do inquérito são apresentados de acordo com o formato seguinte.
_______________________
Formato de apresentação para inquéritos criados (por abrir)

Nome da disciplina - Nome do projecto (por abrir)

Formato de apresentação para inquéritos abertos

Nome da disciplina - Nome do projecto (aberto)

Formato de apresentação para inquéritos fechados

Nome da disciplina - Nome do projecto (fechado)
_______________________
Formato de apresentação para inquéritos finalizados

Nome da disciplina - Nome do projecto
 * Número de submissões: Número de submissões
 * Número de respostas: Número de respostas
 * Tempos de resolução (horas) (mínimo, médio, máximo): Tempo mínimo de resolução do projecto, Tempo médio de resolução do projecto, Tempo máximo de resolução do projecto

 _______________________
Exemplo de apresentação

Note-se que apenas deve ser apresentado um inquérito (o do projecto especificado).
A lista apenas ilustra várias hipóteses.

Programação com Objectos - Coisas Estranhas (aberto)
Programação com Objectos - Gatos Simples
 * Número de submissões: 30
 * Número de respostas: 20
 * Tempos de resolução (horas) (mínimo, médio, máximo): 10, 16, 20
Programação com Objectos - Gatos Sofisticados (fechado)
