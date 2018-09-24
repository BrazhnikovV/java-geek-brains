package com.example.jprof.lesson_1;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Main {
    public static void main(String[] args) {
        Box box_apple  = new Box();
        Box box_orange = new Box();

        Apple apple1 = new Apple( 12 );
        Apple apple2 = new Apple( 4 );

        Orange orange1 = new Orange( 12 );
        Orange orange2 = new Orange( 4 );

        box_apple.add( apple1 );
        box_apple.add( apple2 );

        box_orange.add( orange1 );
        box_orange.add( orange2 );

        int weight = box_apple.getWeight();

        boolean comp = box_apple.compare( box_orange );

        System.out.println( weight );
        System.out.println( comp );
    }
}
