package com.example.jcore.lesson_5.home_work;

import java.util.Arrays;

/**
 * ArrayWorker -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_5.home_work
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ArrayWorker {

    /**
     *  @access private
     *  @var integer size
     */
    private static final int SIZE = 10000000/*00*/;

    /**
     *  @access private
     *  @var integer h
     */
    private static final int HALF = SIZE / 2;

    /**
     *  @access private
     *  @var array main_arr
     */
    private static float[] main_arr = new float[SIZE];;

    /**
     * main -
     * @param args -
     */
    public static void main(String[] args) {
        firstGeneralMethod();
        secondGeneralMethod();
    }

    /**
     * firstGeneralMethod - первый основной метод домашнего задания
     */
    private static void firstGeneralMethod () {

        // засекаем начало время выполнения
        long start = System.currentTimeMillis();

        main_arr = fillArray( createArray() );

        for ( int i = 0; i < main_arr.length; i++ ) {
            main_arr[i] = newValueForArrayElements( i, main_arr[i] );
        }

        // засекаем окончание времени выполнения
        long end = System.currentTimeMillis();
        System.out.println( end - start );
    }

    /**
     * secondGeneralMethod - второй основной метод домашнего задания
     *
     */
    private static void secondGeneralMethod () {

        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        // засекаем начало время выполнения
        long start = System.currentTimeMillis();

        // заполняем главный массив
        main_arr = fillArray( createArray() );

        // разбиваем массив на две части
        System.arraycopy( main_arr, 0, a1, 0, HALF);
        System.arraycopy( main_arr, HALF, a2, 0, HALF);

        // присваиваем новые значения полученным массивам в два потока
        Thread thread_first = new Thread(() -> {
            for ( int i = 0; i < a1.length; i++ ) {
                a1[i] = newValueForArrayElements( i, a1[i] );
            }
            System.arraycopy( a1, 0, main_arr, 0, HALF );
        });

        Thread thread_second = new Thread(() -> {
            for ( int i = 0; i < a2.length; i++ ) {
                a2[i] = newValueForArrayElements( i, a2[i] );
            }
            System.arraycopy( a2,0, main_arr, HALF, HALF );
        });

        // запускаем потоки на выполнение
        thread_first.start();
        thread_second.start();

        // отлавливаем ошибки выполнения потоков и сообщаем основному потоку
        // о том, что необходимо дождаться окончания выполнения созданных допоплнительно потоков
        try {
            thread_first.join();
            thread_second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // засекаем окончание времени выполнения
        long end = System.currentTimeMillis();
        System.out.println( end - start );
    }

    /**
     * createArray - создать длинный массив
     *
     * @return array
     */
    private static float[] createArray () {

        float[] arr = new float[SIZE];

        return arr;
    }

    /**
     * fillArray - заполнить массив единицами
     *
     * @param arr - длинный массив
     * @return array
     */
    private static float[] fillArray ( float[] arr ) {

        for ( int i = 0; i < SIZE; i++ ) {
            arr[i] = 1;
        }

        return arr;
    }

    /**
     * newValueForArrayElements - проходит по всему массиву и считает новое значение
     * по формуле :
     * ( ​ float​ )(​ arr​ [ ​ i ​ ] ​ * ​ Math​ . ​ sin​ ( ​ 0.2f ​ + i ​ / ​ 5 ​ ) ​ * ​
     * Math​ . cos​ ( ​ 0.2f ​ + i ​ / ​ 5 ​ ) ​ * Math​ . ​ cos​ ( ​ 0.4f​ ​ + ​ i ​ / ​ ​ 2 ​ ))
     *
     * @param i -
     * @param val -
     * @return array
     */
    private static float newValueForArrayElements ( int i, float val ) {
        return ( float ) ( val * Math.sin( 0.2f + i / 5 ) * Math.cos( 0.2f + i / 5 ) * Math.cos( 0.4f + i / 2 ) );
    }
}
