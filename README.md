<h2>no folder</h2>

```shell
export CLASSPATH=/usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar
java sth.app		
```
ou
```python
# FIX java -cp /usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar sth.app
```
 
 <h1>links</h1>
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos
 
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos
 
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos/Enunciado_do_Projecto_de_2018-2019

 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos/Avalia%C3%A7%C3%A3o_do_Projecto_%28%C3%89poca_Normal%29

 <h1>todo</h1>

Entrega Intermédia <br>

A Entrega Intermédia avalia o estado do projecto relativamente a um mínimo de funcionalidade. <br>

Esta entrega tem uma classificação máxima de 5 valores (2.5 valores para testes automáticos). <br>

Serão executados testes automáticos nesta entrega. <br>

A não realização da Entrega Intermédia conduz automaticamente a uma classificação de 0 (zero) na componente correspondente (mas não globalmente no projecto). <br>
Funcionalidade a implementar <br>

<h2>A funcionalidade necessária para a entrega intermédia, além da abertura de todos os submenus, é a seguinte: <br>
</h2>
- [ ] Leitura, interpretação e representação em memória do ficheiro textual indicado pela propriedade import (implica implementar algumas classes do "core") <br>
- [ ] Menu principal: Salvaguarda do estado actual da aplicação: "Abrir" e "Guardar" -- implementação completa <br>
- [ ] Menu principal: Gestão e consulta de dados da aplicação: abertura dos menus dos vários portais (comandos fornecidos já implementados). <br>
- [ ] Portal Pessoal -- implementação completa <br>
- [ ] Portal do Docente -- implementação completa, excepto as operações "Ver submissões de um projecto" e "Ver resultados de um inquérito". <br>

Todos os outros comandos em todos os menus têm de estar implementados ("execute" pode estar vazio). Não é má prática implementar os outros comandos, pois poupa esforço para a entrega final, mas não  <br>serão avaliados nesta entrega. <br>

Note-se que os comandos DoLogin, DoOpenPersonnelMenu, DoOpenRepresentativeMenu, DoOpenStudentMenu e DoOpenTeachingMenu (todos do menu principal) não podem ser alterados. Note-se ainda que a classe  <br>App e os menus também não podem ser alterados. <br>

A funcionalidade a implementar em sth-core tem de ser suficiente para suportar os comandos indicados acima. <br>
Critérios de avaliação (não automática) <br>

<h2>Serão aplicados os seguintes critérios na avaliação da entrega intermédia do projecto. <br></h2>
Factores aditivos positivos: <br>

0.30 - Atributos não públicos <br>
0.30 - Atributos e métodos não "static" (excepto onde autorizado) <br>
0.20 - Utilização correcta da classe SchoolManager <br>
0.20 - Serialização <br>
0.50 - Utilização das estruturas de dados correctas <br>
0.50 - Separação app/core (pode haver descontos na parte automática) <br>
0.30 - Qualidade do projecto <br>
0.20 - Comentários Javadoc (a colocar necessariamente na classe School) <br>

Factores aditivos negativos: <br>

0.15 - Violação de regras de codificação Java <br>

Será aplicado um desconto até 1.00 pela existência de lixo no repositório CVS. Este "lixo" é apenas considerado se aparecer na cópia local durante um "checkout".  <br>
