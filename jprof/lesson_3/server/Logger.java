package jprof.lesson_3.server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
     *  @access private
     *  @var List<String> massagesList
     */
    private List<String> massagesList = new ArrayList<>();

    /**
     *  @access private
     *  @var String logName
     */
    private final String logName = "log.txt";

    /**
     * constructor
     */
    public Logger () {

    }

    /**
     * writeLog - клиенткая функция для записи в лог
     *
     * @access public
     * @param msg -
     * @return boolean
     */
    public boolean writeLog ( String msg ) {
        this.writeMessageToFile( msg );
        return true;
    }

    /**
     * readLog - клиентмкая функция чтения лога
     *
     * @access public
     * @return List<String>
     */
    public List<String> readLog () {
        this.readMessagesFormFile();
        return this.massagesList;
    }

    /**
     * writeMessageToFile - записать сообщение в лог файл
     *
     * @access private
     * @param msg - текстовое сообщение
     * @return void
     */
    private void writeMessageToFile ( String msg ) {
        try {
            out = new DataOutputStream( new FileOutputStream( this.logName ) );
            out.writeUTF( msg + "\n" );
            out.close();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * readMessagesFormFile - получить все сообщения из лог файла и сохранить их
     *
     * @access private
     * @return
     */
    private void readMessagesFormFile() {
        try {
            in = new DataInputStream( new FileInputStream( this.logName ) );

            while ( in.available() > 0 ) {
                massagesList.add( in.readUTF() );
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
