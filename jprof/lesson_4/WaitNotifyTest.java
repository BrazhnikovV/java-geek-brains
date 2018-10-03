package jprof.lesson_4;

public class WaitNotifyTest {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Waiter firstWaiter = new Waiter( monitor, "firstWaiter" );
        new Thread( firstWaiter,"firstWaiter" ).start();

        Waiter secondWaiter = new Waiter( monitor, "secondWaiter" );
        new Thread( secondWaiter,"secondWaiter" ).start();

        Waiter thirdWaiter = new Waiter( monitor, "thirdWaiter" );
        new Thread( thirdWaiter,"thirdWaiter" ).start();
    }

}
