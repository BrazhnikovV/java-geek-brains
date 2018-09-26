package jprof.lesson_2.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private final Statement stmt;

    /**
     * constructor
     */
    public BaseAuthService() {

        DBConnection db_connect = new DBConnection();
        db_connect.init();
        stmt = db_connect.getConnection();                
    }

    @Override
    public String getLoginByLoginPass( String login, String pass ) {

        String query = "SELECT * FROM users WHERE name='"+login+"' AND pass='"+pass+"'";
        
        try ( ResultSet resultSet = stmt.executeQuery( query ) ) {

            while ( resultSet.next() ) {
                System.out.println( resultSet.getString(2) );
                return resultSet.getString(2);
            }
            stmt.close();
        } 
        catch ( SQLException e ) {
            System.out.println( "Not found records" );
        }        

        return null;
    }
    
    @Override
    public int renameLogin( String old_login, String new_login ) {
        
        String query = "UPDATE users SET name='" + new_login + "' WHERE name='" + old_login + "'";
        
        try {
            int result = stmt.executeUpdate( query );
            stmt.close();
            return result;            
        } 
        catch ( SQLException e ) {
            System.out.println( "Not found records" );
        }
        
        return 0;
    }
}
