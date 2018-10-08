package jprof.lesson_5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Race - класс трасса, содержит участки трассы
 *
 * @version 1.0.1
 * @package jprof.lesson_5
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Race {

    /**
     *  @access private
     *  @var ArrayList<Stage> stages
     */
    private ArrayList<Stage> stages;

    /**
     * constructor -
     * @param stages - участок трассы
     * @return void
     */
    public Race( Stage... stages ) {
        this.stages = new ArrayList<>( Arrays.asList( stages ) );
    }

    /**
     * getStages - получить участки трассы
     * @return ArrayList<Stage>
     */
    public ArrayList<Stage> getStages() { return stages; }
}
