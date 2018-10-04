package jprof.lesson_4.WaitNotify;

public class WaitNotifyTest {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Waiter firstWaiter = new Waiter( monitor );
        new Thread( firstWaiter,"A" ).start();

        Waiter secondWaiter = new Waiter( monitor );
        new Thread( secondWaiter,"B" ).start();

        Waiter thirdWaiter = new Waiter( monitor );
        new Thread( thirdWaiter,"C" ).start();
    }
}
