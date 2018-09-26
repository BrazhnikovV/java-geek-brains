package jprof.lesson_2.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jprof.lesson_2.db.DBConnection;

/**
 * BaseAuthService - базовый класс авторизации
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BaseAuthService implements IAuthService {

    /**
     *  @access private
     *  @var String login
     */
    private ArrayList<AuthEntry> entries = new ArrayList<>();

    /**
     * constructor
     */
    public BaseAuthService() {

        DBConnection db_connect = new DBConnection();
        db_connect.init();
        Statement stmt = db_connect.getConnection();
        
        try ( ResultSet resultSet = stmt.executeQuery( "SELECT * FROM users" ) ) {

            while ( resultSet.next() ) {
                entries.add( new AuthEntry( 
                        resultSet.getString(2),
                        resultSet.getString(3), 
                        resultSet.getString(4)
                ));
            }
            stmt.close();
        } 
        catch ( SQLException e ) {
            System.out.println( "Failed to get connection" );
        }
    }

    @Override
    public String getLoginByLoginPass( String login, String pass ) {

        for ( AuthEntry o : entries ) {
            if (o.getLogin().equals( login ) && o.getPass().equals( pass ) ) {
                return o.getLogin();
            }
        }

        return null;
    }
}
