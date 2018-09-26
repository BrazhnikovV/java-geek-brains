package com.example.jprof.lesson_1;

/**
 * Fruit - базовый класс фрукты
 *
 * @version 1.0.1
 * @package com.example.jprof.lesson_1
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Fruit {

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
    public Fruit ( int weight ) {
        this.weight = weight;
    }

    /**
     * getWeight - получить вес фрукта
     *
     * @return int
     */
    public int getWeight () {
        return this.weight;
    }
}
