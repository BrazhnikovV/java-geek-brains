package jprof.lesson_5;

import java.util.concurrent.CountDownLatch;

public class MainClass {

    public static final int CARS_COUNT = 4;

    /**
     *  @access private
     *  @var CountDownLatch cdlMain
     */
    private static CountDownLatch cdlMain;

    /**
     *  @access private
     *  @var CountDownLatch cdlObstacles
     */
    private static CountDownLatch cdlObstacles;

    public static void main(String[] args) {

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );

        Race race = new Race( new Road(60 ), new Tunnel(), new Road(40 ) );

        cdlMain = new CountDownLatch( CARS_COUNT * race.getStages().size() );
        cdlObstacles = new CountDownLatch( CARS_COUNT );

        Car[] cars = new Car[CARS_COUNT];
        int speed = 20 + (int) ( Math.random() * 10 );

        for ( int i = 0; i < cars.length; i++ ) {
            cars[i] = new Car(
                    race,
                    speed,
                    cdlMain,
                    cdlObstacles
            );
        }

        for ( int i = 0; i < cars.length; i++ ) {
            new Thread( cars[i] ).start();
        }

        try {
            cdlMain.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" );
    }
}
