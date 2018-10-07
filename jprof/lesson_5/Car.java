package jprof.lesson_5;

/**
 * Car - класс
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Car implements Runnable {

    /**
     *  @access private
     *  @var int CARS_COUNT
     */
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    /**
     *  @access private
     *  @var Race race
     */
    private Race race;

    /**
     *  @access private
     *  @var int speed
     */
    private int speed;

    /**
     *  @access private
     *  @var String name
     */
    private String name;

    /**
     * getName -
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * getSpeed -
     * @return int
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * constructor
     */
    public Car( Race race, int speed ) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println( this.name + " готовится" );
            Thread.sleep(500 + (int)( Math.random() * 800 ) );
            System.out.println( this.name + " готов" );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        for ( int i = 0; i < race.getStages().size(); i++ ) {
            race.getStages().get(i).go(this );
        }
    }
}

