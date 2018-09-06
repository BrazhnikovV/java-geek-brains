package com.example.jcore.lesson_5.home_work;

import com.example.jcore.lesson_5.paint_elements_threads.BounceFrame;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                frame.setVisible( true );
            }
        });
    }
}
