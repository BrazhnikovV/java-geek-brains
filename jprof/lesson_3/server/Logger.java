package jprof.lesson_3.server;

import java.io.*;

/**
 * Logger - класс реализующий логирование приложения
 *
 * @version 1.0.1
 * @package jpof.lesson_3.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Logger {

    /**
     *  @access private
     *  @var DataOutputStream out
     */
    private DataOutputStream out = null;

    /**
     *  @access private
     *  @var DataInputStream in
     */
    private DataInputStream in = null;

    /**
     * constructor
     */
    public Logger () {

    }

    public static void main(String[] args) {
        //createFile();
        //writeMessageToFile( "message" );
    }

    /**
     * writeMessageToFile -
     * @access private
     * @param msg -
     * @return void
     */
    private void writeMessageToFile ( String msg ) {
        try {
            out = new DataOutputStream( new FileOutputStream("log.txt" ) );
            out.writeUTF( msg + "\n" );
            out.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * readMessagesFormFile -
     * @access private
     * @return
     */
    private static void readMessagesFormFile() {

    }
}
