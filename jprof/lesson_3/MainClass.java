package jprof.lesson_3;

import jprof.lesson_3.client.ClientController;

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
        ClientController controller = new ClientController();
    }
}
