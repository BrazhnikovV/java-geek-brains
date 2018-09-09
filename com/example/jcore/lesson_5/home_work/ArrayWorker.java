package com.example.jcore.lesson_5.home_work;

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
    private static final int SIZE = 10/*00*/;

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
        ArrayWorker arr_worker = new ArrayWorker();

        //arr_worker.firstGeneralMethod();
        arr_worker.secondGeneralMethod();
    }

    /**
     * firstGeneralMethod - первый основной метод домашнего задания
     */
    public void firstGeneralMethod () {

        // засекаем начало время выполнения
        notifyExecutionTime();

        main_arr = fillArray( createArray() );

        for ( int i = 0; i < main_arr.length; i++ ) {
            main_arr[i] = newValueForArrayElements( i, main_arr[i] );
        }

        // засекаем окончание времени выполнения
        notifyExecutionTime();
    }

    /**
     * secondGeneralMethod - второй основной метод домашнего задания
     *
     */
    public void secondGeneralMethod () {

        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        // засекаем начало время выполнения
        notifyExecutionTime();

        // заполняем главный массив
        main_arr = fillArray( createArray() );

        // разбиваем массив на две части
        a1 = splitArrayTwoParts( main_arr, 1 );
        a2 = splitArrayTwoParts( main_arr, 2 );

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
        // о том, что необходимо дождаться окончания выполнения созданных дпоплнительно потоков
        try {
            thread_first.join();
            thread_second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for ( int i = 0; i < SIZE; i++ ) {
            System.out.println( main_arr[i] );
        }

        // засекаем окончание времени выполнения
        notifyExecutionTime();
    }

    /**
     * createArray - создать длинный массив
     *
     * @return array
     */
    private float[] createArray () {

        float[] arr = new float[SIZE];

        return arr;
    }

    /**
     * fillArray - заполнить массив единицами
     *
     * @param arr - длинный массив
     * @return array
     */
    private float[] fillArray ( float[] arr ) {

        for ( int i = 0; i < SIZE; i++ ) {
            arr[i] = 1;
        }

        return arr;
    }

    /**
     * notifyExecutionTime - засечь время выполнения
     *
     */
    private void notifyExecutionTime () {
        // !!! Необходимо выводить разницу
        System.out.println( System.currentTimeMillis() );
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

    /**
     * splitArrayTwoParts - разделить массив на две части
     *
     * @param arr - длинный массив
     * @param number_part - первая или вторая половина массива
     * @return array
     *
     */
    private float[] splitArrayTwoParts ( float[] arr, int number_part ) {

        float[] half_arr = new float[HALF];

        if ( number_part == 1 ) {
            System.arraycopy( arr, 0, half_arr, 0, HALF );
            return half_arr;
        }
        if ( number_part == 2 ) {
            System.arraycopy( arr, HALF, half_arr, 0, HALF );
            return half_arr;
        }

        return half_arr;
    }
}
