package jprof.lesson_4;

import java.util.concurrent.*;

public class ExecutorTest {
    private static int countTest = 0;

    public static void main( String[] args ) {

        Callable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1 );
                return 123;
            }
            catch ( InterruptedException e ) {
                throw new IllegalStateException( "task interrupted", e );
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1 );
        Future<Integer> future   = executor.submit( task );

        System.out.println( "future done? " + future.isDone() );

        Integer result = null;

        try {
            result = future.get();
        }
        catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        catch ( ExecutionException e ) {
            e.printStackTrace();
        }

        System.out.println( "future done? " + future.isDone() );
        System.out.print( "result: " + result );

        /*
        ExecutorService executorService = Executors.newFixedThreadPool( 2 );
        for ( int i = 0; i < 50; i++ ) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println( name );
                    System.out.println( "Асинхронный поток " + countTest );
                    countTest++;
                }
            });
        }
        executorService.shutdown();
        System.out.println( "Основной поток" );
        */
    }
}
