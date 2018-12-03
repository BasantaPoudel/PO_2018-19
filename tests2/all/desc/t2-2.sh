echo " - Login com funcionário" > A-01-01-M-ok.desc
echo " - Login com professor" > A-01-02-M-ok.desc
echo " - Login com aluno" > A-01-03-M-ok.desc
echo " - Login com delegado" > A-01-04-M-ok.desc

echo " - Login funcionário e vê funcionário" > A-02-01-M-ok.desc
echo " - Login professor sem disciplinas e vê professor" > A-02-02-M-ok.desc
echo " - Login professor só com uma disciplina e vê professor" > A-02-03-M-ok.desc
echo " - Login professor com várias disciplina e vê professor disciplinas inseridas ordenadamente com mesmo curso" > A-02-04-M-ok.desc
echo " - Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com mesmo curso " > A-02-12-M-ok.desc
echo " - Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com curso  diferente" > A-02-13-M-ok.desc
echo " - Login professor com várias disciplina (e há várias disciplinas com o mesmo nome) e vê professor" > A-02-05-M-ok.desc
echo " - Login aluno com uma disciplina e vê aluno " > A-02-06-M-ok.desc
echo " - Login aluno com várias disciplinas ordenadas e vê aluno  " > A-02-07-M-ok.desc
echo " - Login aluno com várias disciplinas desordenadas e vê aluno" > A-02-08-M-ok.desc
echo " - Login delegado com uma disciplina e vê delegado" > A-02-09-M-ok.desc
echo " - Login delegado com várias disciplinas e vê delegado" > A-02-10-M-ok.desc
echo " - Login de pessoa que não existe" > A-02-11-M-ok.desc

echo " - Procura pessoa com string que não existe " > A-03-01-M-ok.desc
echo " - Procura pessoa com string que faz match com uma pessoa existente " > A-03-02-M-ok.desc
echo " - Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id tb ordenada por nome)" > A-03-03-M-ok.desc
echo " - Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id não ordenada por nome)" > A-03-04-M-ok.desc

echo " - Altera o número de telefone de um funcionário " > A-04-01-M-ok.desc
echo " - Altera o número de telefone de um professor " > A-04-02-M-ok.desc

echo " - Mostra apenas uma pessoa" > A-05-01-M-ok.desc
echo " - Mostar vários funcionários inseridos pela ordem do id" > A-05-02-M-ok.desc
echo " - Mostar vários funcionários inseridos com uma ordem diferente do id" > A-05-03-M-ok.desc
echo " - Mostra vários tipos de utilizadores" > A-05-04-M-ok.desc

echo " - Persistência de Pessoas - guardar  " > A-06-01-M-ok.desc
echo " - Persistência de Pessoas - abrir " > A-06-02-M-ok.desc
echo " - Persistência de Pessoas - guardar 2x" > A-06-03-M-ok.desc
echo " - Persistência - abrir com uma pessoa que não existe no estado persistente" > A-06-04-M-ok.desc
echo " - Persistência de Pessoas, cursos e disciplinas - guardar  " > A-06-05-M-ok.desc
echo " - Persistência de Pessoas, cursos e disciplinas - abrir e criar projecto e guardar" > A-06-06-M-ok.desc

echo " - Criar projecto de disciplina não leccionada mas existente" > A-07-01-M-ok.desc
echo " - Criar projecto de disciplina não leccionada e não existente  " > A-07-02-M-ok.desc
echo " - Criar projecto de disciplina lecionada e guardar (proj3)" > A-07-03-M-ok.desc
echo " - Criar projecto com mesmo nome de disciplina lecionada  " > A-07-04-M-ok.desc
echo " - Criar projecto com mesmo nome em diferentes disciplinas lecionada  " > A-07-05-M-ok.desc
echo " - Criar projecto 2 projectos em duas disciplinas distintas com o mesmo nome" > A-07-06-M-ok.desc
echo " - Abrir proj3 e Criar o mesmo projecto na disciplina mas por um docente diferente" > A-07-07-M-ok.desc
echo " - " > A-07-08-M-ok.desc

echo " - Fechar projecto existente e aberto" > A-08-01-M-ok.desc
echo " - Fechar projecto não existente" > A-08-02-M-ok.desc
echo " - Fechar projecto existente e já fechado" > A-08-03-M-ok.desc

echo " - Ver alunos de disciplina não existente e existente mas não leccionada" > A-09-01-M-ok.desc
echo " - Ver alunos de disciplina sem alunos" > A-09-02-M-ok.desc
echo " - Ver alunos de disciplina com um aluno" > A-09-03-M-ok.desc
echo " - Ver alunos de disciplina com vários alunos e inseridos por ordem" > A-09-04-M-ok.desc
echo " - Ver alunos de disciplina com vários alunos e inseridos fora de ordem" > A-09-05-M-ok.desc
echo " - " > A-09-06-M-ok.desc

echo " - cria 2 projectos na disciplina D1 e 1 na D2; grava em e1.obj / - Docente vê projecto sem submissões D1->P1 e P2, D2 -> P3 " > A-10-01-M-ok.desc
echo " - Vê submissões de projecto não-existente" > A-10-02-M-ok.desc
echo " - Aluno submete em projecto de disciplina em que não está inscrito " > A-10-03-M-ok.desc
echo " - Aluno submete em projecto não existente de disciplina em que está inscrito " > A-10-04-M-ok.desc
echo " - Aluno ai1 submete em projecto P1 de disciplina D1 em que está inscrito (grava em e1.obj)" > A-10-05-M-ok.desc
echo " - Docente vê projecto com 1 submissão" > A-10-06-M-ok.desc
echo " - Aluno submete no mesmo projecto duas vezes (P2); gravar" > A-10-07-M-ok.desc
echo " - Docente vê projecto P2 com 1 submissão  " > A-10-08-M-ok.desc
echo " - Delegado submete em projecto a que não está inscrito" > A-10-09-M-ok.desc
echo " - Aluno ai2 submete no projecto P1; gravar " > A-10-10-M-ok.desc
echo " - Docente vê projecto P1 com 2 submissões" > A-10-11-M-ok.desc
echo " - Aluno di1 fora de ordem submete no projecto P1; gravar" > A-10-12-M-ok.desc
echo " - Delegado cria inquérito para projecto existente e em disciplina inscrita (P1); gravar" > A-10-13-M-ok.desc
echo " - Aluno vê inquérito criado " > A-10-14-M-ok.desc
echo " - Professor vê inquérito criado" > A-10-15-M-ok.desc
echo " - Professor vê projecto P1 com 3 submissões, fecha P1; gravar" > A-10-16-M-ok.desc
echo " - Delegado inscrito faz login e vê uma notificação; gravar" > A-10-17-M-ok.desc
echo " - Delegado volta a fazer login e já não vê notificação" > A-10-18-M-ok.desc
echo " - Delegado não inscrito faz login e vê uma notificação" > A-10-19-M-ok.desc
echo " - Delegado de outro curso faz login e não vê notificação" > A-10-20-M-ok.desc
echo " - Professor faz login e vê uma notificação; gravar " > A-10-21-M-ok.desc
echo " - Professor volta a fazer login e já não vê notificação" > A-10-22-M-ok.desc
echo " - Aluno ai1 volta a fazer login e vê notificação; gravar" > A-10-23-M-ok.desc
echo " - Aluno di1 submete em projecto fechado (P1); gravar" > A-10-24-M-ok.desc
echo " - Professor vê submissões de projecto fechado" > A-10-25-M-ok.desc
echo " - Aluno vê inquérito aberto" > A-10-26-M-ok.desc
echo " - Professor vê inquérito aberto" > A-10-27-M-ok.desc
echo " - Delegado vê inquérito não existente de projecto existente e não existente" > A-10-29-M-ok.desc
echo " - Aluno (di2) que não fez submissão vê inquérito criado" > A-10-30-M-ok.desc
echo " - Criar inquérito em disciplina não existente e em disciplina sem projecto" > A-10-31-M-ok.desc
echo " - Criar inquérito em disciplina com um projecto mas com projecto não existente" > A-10-32-M-ok.desc
echo " - Criar inquérito em disciplina com um projecto e existente e delegado não inscrito na disciplina (guardar A2)" > A-10-33-M-ok.desc
echo " - Criar um segundo inquérito para o mesmo projecto" > A-10-34-M-ok.desc
echo " - Criar vários projectos e fechar P4 (D2); guardar / D1 -> P1 (3 submissões, fechado e com inquérito), P2, P4  (P1 está aberto, os outros estão criados) / D2 -> P3, P4, P5 (P4 fechado)" > A-10-35-M-ok.desc
echo " - Criar inquérito em disciplina com um projecto fechado (D2-> P3)" > A-10-36-M-ok.desc
echo " - Cancelar inquérito criado" > A-10-37-M-ok.desc
echo " - Delegado cancelar inquérito aberto e sem respostas" > A-10-38-M-ok.desc
echo " - Cancelar inquérito aberto e com uma resposta (grava com uma resposta)" > A-10-39-M-ok.desc
echo " - Cancelar inquérito fechado " > A-10-40-M-ok.desc
echo " - Cancelar inquérito finalizado" > A-10-41-M-ok.desc
echo " - Cancelar inquérito fechado por delegado não inscrito " > A-10-42-M-ok.desc
echo " - Cancelar inquérito criado por delegado não inscrito " > A-10-43-M-ok.desc
echo " - Delegado não vê inquérito de disciplina não inscrita como aluno" > A-10-44-M-ok.desc
echo " - Professor vê inquérito de disciplina e projecto sem inquérito. Criar projecto P7 em D1 sem inquérito; guardar" > A-10-45-M-ok.desc
echo " - Aluno vê inquérito de disciplina e projecto sem inquérito" > A-10-46-M-ok.desc
echo " - Delegado vê inquéritos de disciplina só com um inquérito criado e cria inquérito em P2 e P4 de D1; guardar" > A-10-47-M-ok.desc
echo " - Professor fecha projectos P2 e P4; guarda; Vê inquérito criado e fechado" > A-10-48-M-ok.desc
echo " - Delegado entra e vê duas notificações; guardar" > A-10-49-M-ok.desc
echo " - Delegado vê inquéritos de disciplina sem inquéritos" > A-10-50-M-ok.desc
