echo "Login com funcionário" > A-01-01-M-ok.desc
echo "Login com professor" > A-01-02-M-ok.desc
echo "Login com aluno" > A-01-03-M-ok.desc
echo "Login com delegado" > A-01-04-M-ok.desc

echo "Login funcionário e vê funcionário" > A-02-01-M-ok.desc
echo "Login professor sem disciplinas e vê professor" > A-02-02-M-ok.desc
echo "Login professor só com uma disciplina e vê professor" > A-02-03-M-ok.desc
echo "Login professor com várias disciplina e vê professor disciplinas inseridas ordenadamente com mesmo curso" > A-02-04-M-ok.desc
echo "Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com mesmo curso" > A-02-12-M-ok.desc
echo "Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com curso  diferente" > A-02-13-M-ok.desc
echo "Login professor com várias disciplina (e há várias disciplinas com o mesmo nome) e vê professor" > A-02-05-M-ok.desc
echo "Login aluno com uma disciplina e vê aluno" > A-02-06-M-ok.desc
echo "Login aluno com várias disciplinas ordenadas e vê aluno" > A-02-07-M-ok.desc
echo "Login aluno com várias disciplinas desordenadas e vê aluno" > A-02-08-M-ok.desc
echo "Login delegado com uma disciplina e vê delegado" > A-02-09-M-ok.desc
echo "Login delegado com várias disciplinas e vê delegado" > A-02-10-M-ok.desc
echo "Login de pessoa que não existe" > A-02-11-M-ok.desc

echo "Procura pessoa com string que não existe" > A-03-01-M-ok.desc
echo "Procura pessoa com string que faz match com uma pessoa existente" > A-03-02-M-ok.desc
echo "Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id tb ordenada por nome)" > A-03-03-M-ok.desc
echo "Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id não ordenada por nome)" > A-03-04-M-ok.desc

echo "Altera o número de telefone de um funcionário" > A-04-01-M-ok.desc
echo "Altera o número de telefone de um professor" > A-04-02-M-ok.desc

echo "Mostra apenas uma pessoa" > A-05-01-M-ok.desc
echo "Mostar vários funcionários inseridos pela ordem do id" > A-05-02-M-ok.desc
echo "Mostar vários funcionários inseridos com uma ordem diferente do id" > A-05-03-M-ok.desc
echo "Mostra vários tipos de utilizadores" > A-05-04-M-ok.desc

echo "Persistência de Pessoas - guardar" > A-06-01-M-ok.desc
echo "Persistência de Pessoas - abrir" > A-06-02-M-ok.desc
echo "Persistência de Pessoas - guardar 2x" > A-06-03-M-ok.desc
echo "Persistência - abrir com uma pessoa que não existe no estado persistente" > A-06-04-M-ok.desc
echo "Persistência de Pessoas, cursos e disciplinas - guardar" > A-06-05-M-ok.desc
echo "Persistência de Pessoas, cursos e disciplinas - abrir e criar projecto e guardar" > A-06-06-M-ok.desc

echo "Criar projecto de disciplina não leccionada mas existente" > A-07-01-M-ok.desc
echo "Criar projecto de disciplina não leccionada e não existente" > A-07-02-M-ok.desc
echo "Criar projecto de disciplina lecionada e guardar (proj3)" > A-07-03-M-ok.desc
echo "Criar projecto com mesmo nome de disciplina lecionada" > A-07-04-M-ok.desc
echo "Criar projecto com mesmo nome em diferentes disciplinas lecionada" > A-07-05-M-ok.desc
echo "Criar projecto 2 projectos em duas disciplinas distintas com o mesmo nome" > A-07-06-M-ok.desc
echo "Abrir proj3 e Criar o mesmo projecto na disciplina mas por um docente diferente" > A-07-07-M-ok.desc
echo "?" > A-07-08-M-ok.desc

echo "Fechar projecto existente e aberto" > A-08-01-M-ok.desc
echo "Fechar projecto não existente" > A-08-02-M-ok.desc
echo "Fechar projecto existente e já fechado" > A-08-03-M-ok.desc

echo "Ver alunos de disciplina não existente e existente mas não leccionada" > A-09-01-M-ok.desc
echo "Ver alunos de disciplina sem alunos" > A-09-02-M-ok.desc
echo "Ver alunos de disciplina com um aluno" > A-09-03-M-ok.desc
echo "Ver alunos de disciplina com vários alunos e inseridos por ordem" > A-09-04-M-ok.desc
echo "Ver alunos de disciplina com vários alunos e inseridos fora de ordem" > A-09-05-M-ok.desc
echo "?" > A-09-06-M-ok.desc
