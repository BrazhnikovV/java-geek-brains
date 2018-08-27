package com.example.jcore.lesson_1;

/**
 * Dog - абстрактный класс животные
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_2
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Dog extends Animals {

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
    public Dog(String name){
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
