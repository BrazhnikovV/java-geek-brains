package com.example.jcore.lesson_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Team - класс команда участников
 *
 * @version 1.0.1
 * @package com.example.jcore
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Team {

    /**
     *  @access private
     *  @var string name
     */
    private String name;

    /**
     *  @access private
     *  @var array players
     */
    private IMember[] players = new IMember[4];

    /**
     *  @access private
     *  @var array history
     */
    private List<String> history = new ArrayList<String>();

    /**
     * constructor
     *
     * @param name - название команды
     * @param players - массив участников команды
     * @return undefined
     */
    public Team( String name, IMember ... players ) throws ArrayIndexOutOfBoundsException {

        if ( players.length > 4 ) {
            throw new ArrayIndexOutOfBoundsException( "Не больше четырех участников" );
        }
        this.name = name;
        this.players = players;
    }


    /**
     * getPlayers - получить массив игроков команды
     *
     * @return array
     */
    public IMember[] getPlayers() {

        return this.players;
    }

    /**
     * printPlayersMainInfo - метод вывода информации обо всех членах команды
     *
     * @return void
     */
    public void printPlayersMainInfo () {

        System.out.println( "Участники соревнований:" );
        System.out.println( "=======================" );

        for ( int i = 0; i < this.players.length; i++ ) {
            System.out.println( this.players[i].getName() );
        }
    }

    /**
     *
     * printPlayersCourseInfo - метод для вывода информации
     * о членах команды, прошедших дистанцию
     *
     * @return void
     */
    public void printPlayersCourseInfo () {

        Object[] history_array = this.history.toArray();

        System.out.println( "=============================" );
        System.out.println( "Результат прохождения полосы:" );
        System.out.println( "=============================" );

        for( Object action : history_array ){
            System.out.println( action );
        }
    }

    /**
     * passAnObstacle - пройти препятствие и записать действие и участника в историю
     *
     * @param name - имя участника
     * @param obstacle - название препятствия
     * @return void
     */
    public void passAnObstacle ( String name, String obstacle ) {

        this.history.add( name );
        this.history.add( obstacle );
    }
}