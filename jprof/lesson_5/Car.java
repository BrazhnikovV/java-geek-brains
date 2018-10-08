package jprof.lesson_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

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
     *  @var int countCarsLimit
     */
    private static final int countCarsLimit = 4;

    /**
     *  @access private
     *  @var int countCarsIncr
     */
    private static int countCarsIncr = 0;

    /**
     *  @access private
     *  @var String name
     */
    private String name;
    
    /**
     *  @access private
     *  @var int countCarsLimit
     */
    private static CountDownLatch cdlMain;


    /**
     *  @access private
     *  @var int countCarsLimit
     */
    private static CountDownLatch cdlObstacles;

    /**
     * constructor
     * @param race
     * @param speed
     */
    public Car( Race race, int speed, CountDownLatch cdlMain, CountDownLatch cdlObstacles ) {
        this.race  = race;
        this.speed = speed;
        this.cdlMain = cdlMain;
        this.cdlObstacles = cdlObstacles;

        CARS_COUNT++;


        this.name = "Участник #" + CARS_COUNT;
    }

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
     * getCdl -
     * @return CountDownLatch
     */
    public CountDownLatch getCdl() {
        return cdlMain;
    }

    /**
     * getCountCarsIncr -
     * @return int
     */
    public int getCountCarsIncr () {
        return this.countCarsIncr;
    }

    @Override
    public void run() {
        try {
            System.out.println( this.name + " готовится" );
            Thread.sleep(500 + (int)( Math.random() * 800 ) );
            System.out.println( this.name + " готов" );

            this.countCarsIncr++;
            cdlObstacles.countDown();
            if ( countCarsLimit == countCarsIncr ) {
                System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!" );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            cdlObstacles.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for ( int i = 0; i < race.getStages().size(); i++ ) {
            race.getStages().get(i).go(this );
        }
    }
}

