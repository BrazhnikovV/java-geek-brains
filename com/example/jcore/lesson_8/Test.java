package com.example.jcore.lesson_8;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        }).start();
        */

        /*
        HashMap <Integer,String> hashmap = new HashMap<Integer, String>();
        hashmap.put(100, "one");
        hashmap.put(200, "two");

        for (Map.Entry value : hashmap.entrySet()) {
            System.out.println("Value: " + value);
            System.out.println("Key: " + value.getKey());
        }
        */


        Object obj = new Integer(10);
        String str = (String)obj;
        System.out.println(str);

        //int[] a = new int[10];
        //a[20] = 10;
        //a[5] = a[2] / 0;


        /*
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });

        Thread t = new Thread(t1);

        t.start();
        */



        //Test.test();
        //Thread t = new Thread();
        //t.run();
    }

    public void test() {
        System.out.println( "asasas" );
    }
}
