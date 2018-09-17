package com.example.jcore.lesson_8;

import com.example.jcore.lesson_8.client.ClientController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * MainClass - запускает контроллер клиента
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.home_work
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class MainClass {

    /**
     * main -
     * @param args -
     */
    public static void main( String[] args ) {

        // Запускаем контроллер клиента,
        // он же инициализирует окно пользовательского интерфейса
        //ClientController controller = new ClientController();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                System.out.print( "Привет" );
            }
        };
        timer.schedule(task, 0L ,1000L);
    }
}
