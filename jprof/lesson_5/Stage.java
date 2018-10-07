package jprof.lesson_5;

/**
 * Stage - класс
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public abstract class Stage {

    /**
     *  @access protected
     *  @var integer length
     */
    protected int length;

    /**
     *  @access protected
     *  @var String description
     */
    protected String description;

    /**
     * getDescription - точка запуска сервера
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * go -
     * @param c -
     * @return void
     */
    public abstract void go( Car c );
}