echo "Login com funcionário"	> A-01-01-M-ok.descr
echo "Login com professor"	> A-01-02-M-ok.descr
echo "Login com aluno"	> A-01-03-M-ok.descr
echo "Login com delegado"	> A-01-04-M-ok.descr

echo "Login de funcionário e vê funcionário"	> A-02-01-M-ok.descr
echo "Login de professor sem disciplinas e vê professor"	> A-02-02-M-ok.descr
echo "Login de professor só com uma disciplina e vê professor"	> A-02-03-M-ok.descr
echo "Login de aluno com uma disciplina e vê aluno"	> A-02-06-M-ok.descr
echo "Login de aluno com várias disciplinas ordenadas e vê aluno"	> A-02-07-M-ok.descr
echo "Login de pessoa que não existe"	> A-02-11-M-ok.descr

echo "Procura pessoa com string que não existe"	> A-03-01-M-ok.descr
echo "Procura pessoa com string que faz match com uma pessoa existente"	> A-03-02-M-ok.descr

echo "Altera o número de telefone de um funcionário"	> A-04-01-M-ok.descr
echo "Altera o número de telefone de um professor"	> A-04-02-M-ok.descr

echo "Mostra apenas uma pessoa"	> A-05-01-M-ok.descr

echo "Persistência de Pessoas - guardar"	> A-06-01-M-ok.descr
echo "Persistência de Pessoas - abrir"	> A-06-02-M-ok.descr
echo "Persistência de Pessoas - guardar 2x"	> A-06-03-M-ok.descr

echo "Criar projecto de disciplina não leccionada mas existente"	> A-07-01-M-ok.descr
echo "Criar projecto de disciplina lecionada e guardar (proj3)"	> A-07-03-M-ok.descr

echo "Fechar projecto existente e aberto"	> A-08-01-M-ok.descr
echo "Fechar projecto não existente"	> A-08-02-M-ok.descr
echo "Fechar projecto existente e já fechado"	> A-08-03-M-ok.descr

echo "Ver alunos de disciplina não existente e existente mas não leccionada"	> A-09-01-M-ok.descr
echo "Ver alunos de disciplina com um aluno"	> A-09-03-M-ok.descr
