package jprof.lesson_3;

import jprof.lesson_3.client.ClientController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


        Integer[] list = {12,31,31,2,1,2,1,21,2,1,3,21};

        List<Integer> r =  new ArrayList( Arrays.asList( list ) );

        int sum = r.stream().map(x -> {
            return x * x;
        }).reduce((x, y) -> {
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            return x + y;
        }).get();
        System.out.println(sum);
    }
}
