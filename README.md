# Project title and Description / About
O objectivo do projecto é criar uma aplicação que gere parte da actividade dos cursos de uma universidade.

 The Project was based on the University Management System. The major task was to model the given
problem using UML and solving that using Java. The most challenging part was selecting the proper
Design Pattern and data structure that could result in an efficient solution. The solution included the
appropriate relation among the various classes like Professors, Departments, Students,
Representative Students, Subjects, and others to store the objects in appropriate manner. 
 
```shell
export CLASSPATH=/usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar
java sth.app		
```
ou
```python
# FIX java -cp /usr/share/java/po-uuilib.jar:./sth-core/sth-core.jar:./sth-app/sth-app.jar sth.app
```
 
 # Getting Started
 ## Prerequisites
 - Java 11
 - po-uuilib package installed and classpath well defined
 
 ## How to Run
 $cd project
 $make
 $java -Dimport=sth-app/people.import -cp "/usr/share/java/po-uuilib.jar:sth-core/sth-core.jar:sth-app/sth-app.jar" sth.app.App

 # Main Feature Highlight
 Portal
The major functionality included separate portal for each person according to their status. In each of the portals
there were options to show the profile, change the details of the profile and find another person
using the search id. Depending on the person’s status, the options in the portal would be different.
For example, a professor would have option to create project, while student had option to submit
the project. The project was well structured in packages so enough knowledge of the concepts like
access modifier, data abstraction, encapsulation, inheritance, and polymorphism had to be
dominated to be able to solve the project.

 # Feature Description

 # App Look Screenshot
![image](https://user-images.githubusercontent.com/22733095/110450620-47fdff00-80bb-11eb-9778-d2c0a61180f1.png)
![image](https://user-images.githubusercontent.com/22733095/110450902-8bf10400-80bb-11eb-8d3f-cd92ba9e2d6d.png)
![image](https://user-images.githubusercontent.com/22733095/110451050-b642c180-80bb-11eb-97fc-87a8b19d5b96.png)
![image](https://user-images.githubusercontent.com/22733095/110451171-d6728080-80bb-11eb-841f-a02d908bf4c6.png)


 <h1>links</h1>
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos
 
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos
 
 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos/Enunciado_do_Projecto_de_2018-2019

 https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/Programa%C3%A7%C3%A3o_com_Objectos/Projecto_de_Programa%C3%A7%C3%A3o_com_Objectos/Avalia%C3%A7%C3%A3o_do_Projecto_%28%C3%89poca_Normal%29


Authors
Basanta Poudel
Ricardo Almeida
