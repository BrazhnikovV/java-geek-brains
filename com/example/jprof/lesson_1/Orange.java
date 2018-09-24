package com.example.jprof.lesson_1;

/**
 * Orange -
 *
 * @version 1.0.1
 * @package com.example.jprof.lesson_1
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Orange extends Fruit {

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
    public Orange ( int weight ) {
        this.weight = weight;
    }
}
