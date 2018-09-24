package com.example.jprof.lesson_1;

/**
 * Apple -
 *
 * @version 1.0.1
 * @package com.example.jprof.lesson_1
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Apple extends Fruit {

    /**
     *  @access private
     *  @var string name
     */
    private int weight;

    /**
     * constructor
     *
     * @param weight - вес фрукта
     * @return undefined
     */
    public Apple ( int weight ) {
        this.weight = weight;
    }
}
