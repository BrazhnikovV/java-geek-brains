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
    private final int size = 1000000/*0*/;

    /**
     *  @access private
     *  @var integer h
     */
    private final int h = size / 2;

    /**
     * firstGeneralMethod - первый основной метод домашнего задания
     *
     */
    public void firstGeneralMethod () {

        // засекаем начало время выполнения
        notifyExecutionTime();

        float[] arr = newValueForArrayElements (
            fillArray( createArray() )
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
        float[] main_arr = fillArray( createArray() );
        float[] first_part_arr = splitArrayTwoParts( main_arr, 1 );
        float[] second_part_arr = splitArrayTwoParts( main_arr, 2 );

        System.out.println( first_part_arr.length );
        System.out.println( first_part_arr[first_part_arr.length - 1] );

        System.out.println( second_part_arr.length );
        System.out.println( second_part_arr[second_part_arr.length - 1] );

        // склеиваем маиисв из двух частей
        float[] new_main_arr = joinArrayTwoParts( first_part_arr, second_part_arr );

        System.out.println( new_main_arr.length );
        System.out.println( new_main_arr[new_main_arr.length - 1] );

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
     * @return array
     */
    private float[] newValueForArrayElements ( float[] arr ) {

        for ( int i = 0; i < size; i++ ) {
            arr[i] = ( float ) ( arr[i] * Math.sin( 0.2f + i / 5 ) * Math.cos( 0.2f + i / 5 ) * Math.cos( 0.4f + i / 2 ) );
        }

        return arr;
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
            System.arraycopy( arr, h / 2, half_arr, 0, h );
            return half_arr;
        }

        return half_arr;
    }

    /**
     * joinArrayTwoParts - склеить массив из двух частей
     *
     * @param first_part_array - первая половина массива
     * @param second_part_array - вторая половина массива
     * @return array
     *
     */
    private float[] joinArrayTwoParts ( float[] first_part_array, float[] second_part_array ) {

        float[] half_arr = new float[size];

        System.arraycopy( first_part_array, 0, half_arr, 0, h );
        System.arraycopy( second_part_array,0, half_arr, h, h );

        return half_arr;
    }
}
