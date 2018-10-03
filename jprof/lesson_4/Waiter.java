package jprof.lesson_4;

public class Waiter implements Runnable{

    private Monitor monitor;

    private static String[] chars = new String[3];
    private static int countChars = 0;
    private static int count1 = 0;
    private boolean end = true;

    public Waiter( Monitor m, String threadName ){
        this.monitor = m;

        this.chars[countChars] = threadName;
        this.countChars++;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        int id = (int) Thread.currentThread().getId();

        synchronized ( monitor ) {

            while ( end ) {
                try {
                    Thread.sleep(1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {

                    if ( this.chars[this.count1] == name ) {
                        this.count1++;
                        if ( count1 == 3 ) {
                            this.count1 = 0;
                        }

                        monitor.notifyAll();
                        System.out.println( "Уснул поток -> " + name );
                        System.out.println( "=================" );
                        monitor.wait();
                    }
                    else {
                        monitor.wait();
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
