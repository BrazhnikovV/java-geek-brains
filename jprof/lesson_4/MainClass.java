package jprof.lesson_4;

import jprof.lesson_3.client.ClientController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MainClass - запускает контроллер клиента
 *
 * @version 1.0.1
 * @package jprof.lesson_4
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
