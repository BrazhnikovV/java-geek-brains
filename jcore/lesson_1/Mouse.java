package com.example.jcore.lesson_1;

/**
 * Mouse -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_2
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Mouse extends Animals {

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
    public Mouse(String name){
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
