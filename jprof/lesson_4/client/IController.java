package jprof.lesson_4.client;

/**
 * IController - интерфейс для класса ClientController
 *
 * @version 1.0.1
 * @package jprof.lesson_4.client
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public interface IController {

    /**
     * sendMessage
     * @param msg - текст сообщения
     */
    void sendMessage(String msg);

    /**
     * closeConnection
     */
    void closeConnection();
}
