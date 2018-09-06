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
    private final int size = 10000000;

    /**
     *  @access private
     *  @var integer h
     */
    private final int h = size / 2;

    /**
     * firstGeneralMethod -
     *
     * @return String
     */
    public void firstGeneralMethod () {

    }

    /**
     * secondGeneralMethod -
     *
     * @return String
     */
    public void secondGeneralMethod () {

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
     * @return long
     */
    private long notifyExecutionTime () {
        return System.currentTimeMillis();
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
}
