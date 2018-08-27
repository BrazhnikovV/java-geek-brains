package com.example.jcore.lesson_1;

public class Main {
    public static void main(String[] args) {

        // созлаем участников команды
        Dog   dog = new Dog ( "Palkan" );
        Cat   cat = new Cat ( "Murzik" );
        Bird  bird  = new Bird ( "Woddy" );
        Mouse mouse = new Mouse ( "Pik" );
        Mouse mouse1 = new Mouse ( "Pik" );

        // создаем экземпляр класса команда участников
        Team team = new Team ( "champions", dog, cat, bird, mouse );

        // выводим информацию о всех участниках соревнований
        team.printPlayersMainInfo();

        // создаем экземпляр класса полоса препятствий
        Course course = new Course ();

        // проходим полосу препятствий
        course.doIt( team );

        // выводим информация об участниках проходивших препятствия
        team.printPlayersCourseInfo();
    }
}