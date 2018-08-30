package com.example.jcore.lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Phonebook - класс телефонная книга
 *
 * @version 1.0.1
 * @package com.example.jcore
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Phonebook {

    /**
     *  @access private
     *  @var map Карта хранящая список абонентов
     */
    Map<String, ArrayList> subscribers = new HashMap<>();

    /**
     * constructor
     *
     * @return undefined
     */
    public Phonebook()  {

    }

    /**
     * get - добавить нового абонента
     *
     * @param  surname - имя абонента
     * @param  phone - телефонный номер абонента
     * @return array
     */
    public void add( String surname, String phone ) {

        ArrayList phones = this.subscribers.get( surname );

        if ( phones == null ) {

            ArrayList<String> list_phones = new ArrayList<String>();
            list_phones.add( phone );

            this.subscribers.put( surname, list_phones );
        }
        else {
            phones.add( phone );
            this.subscribers.put( surname, phones );
        }
    }

    /**
     * get - получить номер телефона по фамилии абонента
     *
     * @param  surname
     * @return array
     */
    public ArrayList get( String surname ) {

        return this.subscribers.get( surname );
    }
}