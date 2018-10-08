package jprof.lesson_5;

import java.util.concurrent.CyclicBarrier;

/**
 * Tunnel - класс тунель ( участок трассы )
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Tunnel extends Stage {

    /**
     *  @access protected
     *  @var CyclicBarrier cb
     */
    private CyclicBarrier cb;

    /**
     * constructor
     */
    public Tunnel() {

        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.cb = new CyclicBarrier(2 );
    }

    @Override
    public void go( Car c ) {
        try {
            try {
                //
                this.cb.await();
                System.out.println( c.getName() + " готовится к этапу(ждет): " + description );
                System.out.println( c.getName() + " начал этап: " + description );
                Thread.sleep(length / c.getSpeed() * 1000 );
            }
            catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            finally {
                System.out.println( c.getName() + " закончил этап: " + description );
                c.getCdl().countDown();
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

