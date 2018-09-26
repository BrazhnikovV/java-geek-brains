package com.example.jcore.lesson_1;

/**
 * Cat -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_2
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Cat extends Animals {

    /**
     *  @access private
     *  @var string name
     */
    private String name;

    /**
     * constructor
     *
     * @param name -
     * @return undefined
     */
    public Cat(String name){
        super();
        this.name = name;
    }

    /**
     * getName -
     *
     * @return String
     */
    public String getName () {
        return this.name;
    }
}
