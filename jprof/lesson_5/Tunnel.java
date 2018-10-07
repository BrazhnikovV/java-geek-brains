package jprof.lesson_5;

/**
 * Tunnel - класс
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Tunnel extends Stage {

    /**
     * constructor
     */
    public Tunnel() {

        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go( Car c ) {
        try {
            try {
                System.out.println( c.getName() + " готовится к этапу(ждет): " + description );
                System.out.println( c.getName() + " начал этап: " + description );
                Thread.sleep(length / c.getSpeed() * 1000 );

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println( c.getName() + " закончил этап: " + description );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

