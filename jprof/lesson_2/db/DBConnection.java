package jprof.lesson_2.db;

import java.sql.*;

/**
 * DBConnection - класс для работы с базой данных
 *
 * @version 1.0.1
 * @package com.example.jprof.lesson_2
 * @author Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class DBConnection {

    /**
     *  @access private
     *  @var Connection connection
     */
    private Connection connection;
    /**
     *  @access private
     *  @var Statement statement
     */
    private Statement statement;
    /**
     *  @access private
     *  @var String db_name
     */
    private final String db_name = "jdbc:mysql://localhost/messenger";
    /**
     *  @access private
     *  @var String user_name
     */
    private final String user_name = "user";
    /**
     *  @access private
     *  @var String password
     */
    private final String password  = "password";

    /**
     * constructor
     *
     */
    public DBConnection() {

    }

    /**
     * init - инициализировать соединение с базой данных
     *
     * @access public
     */
    public void init() {

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
            connection = DriverManager.getConnection( db_name, user_name, password );
            statement  = connection.createStatement();
        } 
        catch ( ClassNotFoundException | SQLException e ) {
            System.out.println( "Failed to get connection" );
        }
    }

    /**
     * getConnection - получить соединение
     *
     * @access public
     * @return Connection
     */
    public Statement getConnection() {
        return statement;
        //return connection;
    }

    /**
     * close - закрыть соединение
     *
     * @access public
     * @param  rs
     */
    public void close( ResultSet rs ) {

        if ( rs != null ) {
            try {
                rs.close();
            } 
            catch ( SQLException e ) {}
        }
    }

    /**
     * close - закрыть
     *
     * @access public
     * @param  stmt
     */
    public void close( Statement stmt ) {

        if ( stmt != null ) {
            try {
                stmt.close();
            } 
            catch ( SQLException e ) {}
        }
    }

    /**
     * destroy - уничтожить соединение, освободить ресурсы
     *
     * @access public
     */
    public void destroy() {

        if ( connection != null ) {
            try {
                connection.close();
            } 
            catch ( SQLException e ) {}
        }
    }
}
