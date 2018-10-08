package jprof.lesson_5;

/**
 * Stage - абстрактный класс участок трассы
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
     * getDescription - получить описание прохождения участка
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * go - пройти участок
     * @param c - объект участника ( машины )
     * @return void
     */
    public abstract void go( Car c );
}