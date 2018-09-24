package com.example.jprof.lesson_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Box -
 *
 * @version 1.0.1
 * @package com.example.jprof.lesson_1
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Box<T extends Fruit> {

    /**
     *  @access private
     *  @var T fruits
     */
    private List<T> fruits = new ArrayList<>();

    /**
     * constructor
     *
     * @return undefined
     */
    public Box () {}

    /**
     * add - добавить фрукт в коробку
     *
     * @param fruit - фрукт
     * @return undefined
     */
    public void add ( T fruit ) {
        this.fruits.add( fruit );
    }

    /**
     * getWeight - получить общий вес коробки
     *
     * @return int
     */
    public int getWeight () {

        int sum = 0;

        for ( int i = 0; i < this.fruits.size(); i++ ) {
            sum += this.fruits.get( i ).getWeight();
        }

        return sum;
    }
}
