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
    private static final int size = 100000/*00*/;

    /**
     *  @access private
     *  @var integer h
     */
    private final int h = size / 2;

    /**
     *  @access private
     *  @var array main_arr
     */
    private static float[] main_arr;

    /**
     *  @access private
     *  @var array first_part_arr
     */
    private static float[] first_part_arr;

    /**
     *  @access private
     *  @var array second_part_arr
     */
    private static float[] second_part_arr;

    public static void main(String[] args) {
        ArrayWorker arr_worker = new ArrayWorker();

        arr_worker.firstGeneralMethod();
        arr_worker.secondGeneralMethod();
    }

    /**
     * firstGeneralMethod - первый основной метод домашнего задания
     *
     */
    public void firstGeneralMethod () {

        // засекаем начало время выполнения
        notifyExecutionTime();

        newValueForArrayElements (
            fillArray( createArray() ),
            size,
            "main"
        );

        // засекаем окончание времени выполнения
        notifyExecutionTime();
    }

    /**
     * secondGeneralMethod - второй основной метод домашнего задания
     *
     */
    public void secondGeneralMethod () {

        // засекаем начало время выполнения
        notifyExecutionTime();

        // разбиваем массив на две части
        main_arr = fillArray( createArray() );
        first_part_arr  = splitArrayTwoParts( main_arr, 1 );
        second_part_arr = splitArrayTwoParts( main_arr, 2 );

        // присваиваем новые значения полученным массивам в два потока
        new Thread(() -> ArrayWorker.newValueForArrayElements(
                first_part_arr, h, "first" )
        ).start();

        new Thread(() -> ArrayWorker.newValueForArrayElements(
                second_part_arr, h, "second" )
        ).start();

        // склеиваем массив из двух частей
        joinArrayTwoParts();

        // засекаем окончание времени выполнения
        notifyExecutionTime();
    }

    /**
     * createArray - создать длинный массив
     *
     * @return array
     */
    private float[] createArray () {

        float[] arr = new float[size];

        return arr;
    }

    /**
     * fillArray - заполнить массив единицами
     *
     * @param arr - длинный массив
     * @return array
     */
    private float[] fillArray ( float[] arr ) {

        for ( int i = 0; i < size; i++ ) {
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
     * @param arr - длинный массив
     * @param size_arr - длинна массив
     */
    private static synchronized void newValueForArrayElements ( float[] arr, int size_arr, String type ) {

        for ( int i = 0; i < size_arr; i++ ) {
            arr[i] = ( float ) ( arr[i] * Math.sin( 0.2f + i / 5 ) * Math.cos( 0.2f + i / 5 ) * Math.cos( 0.4f + i / 2 ) );
        }

        if ( type == "main" ) { main_arr = arr; }
        if ( type == "first" ) { first_part_arr = arr; }
        if ( type == "second" ) { second_part_arr = arr; }
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

        float[] half_arr = new float[h];

        if ( number_part == 1 ) {
            System.arraycopy( arr, 0, half_arr, 0, h );
            return half_arr;
        }
        if ( number_part == 2 ) {
            System.arraycopy( arr, h, half_arr, 0, h );
            return half_arr;
        }

        return half_arr;
    }

    /**
     * joinArrayTwoParts - склеить массив из двух частей
     *
     * @return array
     *
     */
    private synchronized void  joinArrayTwoParts () {

        System.arraycopy( first_part_arr, 0, main_arr, 0, h );
        System.arraycopy( second_part_arr,0, main_arr, h, h );
    }
}
