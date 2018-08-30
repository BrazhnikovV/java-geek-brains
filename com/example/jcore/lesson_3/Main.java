package com.example.jcore.lesson_3;

import java.util.*;
import java.lang.String;

public class Main {

    public static void main(String[] args) {
        // выполнение первого задания домашней работы
        taskFirst();

        // выполнение второго задания домашней работы
        Phonebook phonebook = new Phonebook();

        phonebook.add(  "Бражников", "+7 952 951 34 05" );
        phonebook.add(  "Бражников", "+7 952 951 34 06" );

        ArrayList abonent = phonebook.get( "Бражников" );

        System.out.println( abonent );
    }

    private static void taskFirst ()  {

        // массив не уникальных слов
        String[] words = {
            "политика", "независимость", "балкон", "витрина", "причина", "великолепие", "политика", "змея",
            "колыбель", "простор", "витрина", "колокол", "рычаг", "политика", "балкон", "перестройка",
            "транспорт", "море", "витрина", "великолепие",
        };

        // список уникальных слов (хеш-множество)
        Set<String> unic_words = new HashSet<String>();

        // Карта хранящая информацию о количестве повторений
        // каждого слова из массива слов words
        Map<String, Integer> matches_words = new HashMap<>();

        // определяем переменную для хранения длинны массива слов
        int count_arr_elems = words.length;

        // проходим по массиву слов, в хеш-множество уникальных слов
        // добавляются только уникальные слова
        for ( int i = 0; i < count_arr_elems; i++ ) {

            Integer count_repeat_word = 0;

            for ( int j = 0; j < count_arr_elems; j++ ) {
                if ( words[i] == words[j] ) {
                    count_repeat_word++;
                }
            }

            // просто вставляем слова в хеш-множество,
            // повторения будут отброшены
            unic_words.add( words[i] );

            // определяем количество повторений
            matches_words.put( words[i], count_repeat_word );
        }

        // выводим список уникальных слов
        System.out.println( "Список уникальных слов" );
        System.out.println( "======================" );

        for( String word : unic_words ){
            System.out.println( word );
        }

        System.out.println( "======================" );

        // выводим список слов с указанием на количество повторений
        System.out.println( "Количество повторений слов" );
        System.out.println( "======================" );

        for( Map.Entry<String, Integer> element : matches_words.entrySet() ){
            System.out.println( element.getKey() );
            System.out.println( element.getValue() );
        }
    }
}