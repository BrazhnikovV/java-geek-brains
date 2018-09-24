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

        if ( this.fruits.size() > 0 ) {
            if ( this.fruits.get(0).getClass().getName() != fruit.getClass().getName() ) {
                throw new Error();
            }
        }
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

    /**
     * getFruits - получить фрукты в коробке
     *
     * @return List<T>
     */
    public List<T> getFruits () {
        return this.fruits;
    }

    /**
     * compare - сравнить текущую коробку с
     * переданной в качестве аргумента
     *
     * @return boolean
     */
    public boolean compare ( Box box ) {
        if ( this.getWeight() == box.getWeight() ) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * pepper - пересыпать фрукты в текущую
     * коробку из переданной
     *
     * @param box - коробка которую необходимо пересыпать в текущую
     * @return boolean
     */
    public boolean pepper ( Box box ) {
        List<T> input_box = box.getFruits();

        for ( int i = 0; i < input_box.size(); i++ ) {
            this.add( input_box.get( i ) );
            input_box.remove( i );
        }

        return true;
    }
}
