A-01-01 - Login com funcionário
A-01-02 - Login com professor
A-01-03 - Login com aluno
A-01-04 - Login com delegado

A-02-01 - Login funcionário e vê funcionário
A-02-02 - Login professor sem disciplinas e vê professor
A-02-03 - Login professor só com uma disciplina e vê professor
A-02-04 - Login professor com várias disciplina e vê professor disciplinas inseridas ordenadamente com mesmo curso
A-02-12 - Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com mesmo curso 
A-02-13 - Login professor com várias disciplina e vê professor disciplinas inseridas desordenadamente com curso  diferente
A-02-05 - Login professor com várias disciplina (e há várias disciplinas com o mesmo nome) e vê professor
A-02-06 - Login aluno com uma disciplina e vê aluno 
A-02-07 - Login aluno com várias disciplinas ordenadas e vê aluno  
A-02-08 - Login aluno com várias disciplinas desordenadas e vê aluno
A-02-09 - Login delegado com uma disciplina e vê delegado
A-02-10 - Login delegado com várias disciplinas e vê delegado
A-02-11 - Login de pessoa que não existe

A-03-01 - Procura pessoa com string que não existe 
A-03-02 - Procura pessoa com string que faz match com uma pessoa existente 
A-03-03 - Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id tb ordenada por nome)
A-03-04 - Procura pessoa com string que faz match com várias pessoas (e lista de pessoas por id não ordenada por nome)

A-04-01 - Altera o número de telefone de um funcionário 
A-04-02 - Altera o número de telefone de um professor 

A-05-01 - Mostra apenas uma pessoa
A-05-02 - Mostar vários funcionários inseridos pela ordem do id
A-05-03 - Mostar vários funcionários inseridos com uma ordem diferente do id
A-05-04 - Mostra vários tipos de utilizadores

A-06-01 - Persistência de Pessoas - guardar  
A-06-02 - Persistência de Pessoas - abrir 
A-06-03 - Persistência de Pessoas - guardar 2x
A-06-04 - Persistência - abrir com uma pessoa que não existe no estado persistente
A-06-05 - Persistência de Pessoas, cursos e disciplinas - guardar  
A-06-06 - Persistência de Pessoas, cursos e disciplinas - abrir e criar projecto e guardar

A-07-01 - Criar projecto de disciplina não leccionada mas existente
A-07-02 - Criar projecto de disciplina não leccionada e não existente  
A-07-03 - Criar projecto de disciplina lecionada e guardar (proj3)
A-07-04 - Criar projecto com mesmo nome de disciplina lecionada  
A-07-05 - Criar projecto com mesmo nome em diferentes disciplinas lecionada  
A-07-06 - Criar projecto 2 projectos em duas disciplinas distintas com o mesmo nome
A-07-07 - Abrir proj3 e Criar o mesmo projecto na disciplina mas por um docente diferente
A-07-08 - 

A-08-01 - Fechar projecto existente e aberto
A-08-02 - Fechar projecto não existente
A-08-03 - Fechar projecto existente e já fechado

A-09-01 - Ver alunos de disciplina não existente e existente mas não leccionada
A-09-02 - Ver alunos de disciplina sem alunos
A-09-03 - Ver alunos de disciplina com um aluno
A-09-04 - Ver alunos de disciplina com vários alunos e inseridos por ordem
A-09-05 - Ver alunos de disciplina com vários alunos e inseridos fora de ordem
A-09-06 - 

A-10-01 - cria 2 projectos na disciplina D1 e 1 na D2; grava em e1.obj
        - Docente vê projecto sem submissões D1->P1 e P2, D2 -> P3 
A-10-02 - Vê submissões de projecto não-existente
A-10-03 - Aluno submete em projecto de disciplina em que não está inscrito 
A-10-04 - Aluno submete em projecto não existente de disciplina em que está inscrito 
A-10-05 - Aluno ai1 submete em projecto P1 de disciplina D1 em que está inscrito (grava em e1.obj)
A-10-06 - Docente vê projecto com 1 submissão
A-10-07 - Aluno submete no mesmo projecto duas vezes (P2); gravar
A-10-08 - Docente vê projecto P2 com 1 submissão  
A-10-09 - Delegado submete em projecto a que não está inscrito
A-10-10 - Aluno ai2 submete no projecto P1; gravar 
A-10-11 - Docente vê projecto P1 com 2 submissões
A-10-12 - Aluno di1 fora de ordem submete no projecto P1; gravar
A-10-13 - Delegado cria inquérito para projecto existente e em disciplina inscrita (P1); gravar
A-10-14 - Aluno vê inquérito criado 
A-10-15 - Professor vê inquérito criado
A-10-16 - Professor vê projecto P1 com 3 submissões, fecha P1; gravar
A-10-17 - Delegado inscrito faz login e vê uma notificação; gravar
A-10-18 - Delegado volta a fazer login e já não vê notificação
A-10-19 - Delegado não inscrito faz login e vê uma notificação
A-10-20 - Delegado de outro curso faz login e não vê notificação
A-10-21 - Professor faz login e vê uma notificação; gravar 
A-10-22 - Professor volta a fazer login e já não vê notificação
A-10-23 - Aluno ai1 volta a fazer login e vê notificação; gravar
A-10-24 - Aluno di1 submete em projecto fechado (P1); gravar
A-10-25 - Professor vê submissões de projecto fechado
A-10-26 - Aluno vê inquérito aberto
A-10-27 - Professor vê inquérito aberto
A-10-29 - Delegado vê inquérito não existente de projecto existente e não existente
A-10-30 - Aluno (di2) que não fez submissão vê inquérito criado
A-10-31 - Criar inquérito em disciplina não existente e em disciplina sem projecto
A-10-32 - Criar inquérito em disciplina com um projecto mas com projecto não existente
A-10-33 - Criar inquérito em disciplina com um projecto e existente e delegado não inscrito na disciplina (guardar A2)
A-10-34 - Criar um segundo inquérito para o mesmo projecto
A-10-35 - Criar vários projectos e fechar P4 (D2); guardar
          D1 -> P1 (3 submissões, fechado e com inquérito), P2, P4  (P1 está aberto, os outros estão criados)
          D2 -> P3, P4, P5 (P4 fechado)
A-10-36 - Criar inquérito em disciplina com um projecto fechado (D2-> P3)
A-10-37 - Cancelar inquérito criado
A-10-38 - Delegado cancelar inquérito aberto e sem respostas
A-10-39 - Cancelar inquérito aberto e com uma resposta (grava com uma resposta)
A-10-40 - Cancelar inquérito fechado 
A-10-41 - Cancelar inquérito finalizado
A-10-42 - Cancelar inquérito fechado por delegado não inscrito 
A-10-43 - Cancelar inquérito criado por delegado não inscrito 
A-10-44 - Delegado não vê inquérito de disciplina não inscrita como aluno
A-10-45 - Professor vê inquérito de disciplina e projecto sem inquérito. Criar projecto P7 em D1 sem inquérito; guardar
A-10-46 - Aluno vê inquérito de disciplina e projecto sem inquérito
A-10-47 - Delegado vê inquéritos de disciplina só com um inquérito criado e cria inquérito em P2 e P4 de D1; guardar
A-10-48 - Professor fecha projectos P2 e P4; guarda; Vê inquérito criado e fechado
A-10-49 - Delegado entra e vê duas notificações; guardar
A-10-50 - Delegado vê inquéritos de disciplina sem inquéritos
