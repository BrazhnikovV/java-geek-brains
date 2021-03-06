package jprof.lesson_5;

/**
 * Road - класс дорога ( участок трассы )
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Road extends Stage {

    /**
     * constructor
     */
    public Road( int length ) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go( Car c ) {
        try {
            System.out.println( c.getName() + " начал этап: " + description );
            Thread.sleep(length / c.getSpeed() * 1000 );
            System.out.println( c.getName() + " закончил этап: " + description );
            c.getCdl().countDown();
        }
        catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
