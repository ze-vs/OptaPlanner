package com.vasya;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class ScoreCalculator implements EasyScoreCalculator<Schedule> {

    public static final Integer LINE_ONE_LOAD = 5356;
    public static final Integer LINE_TWO_LOAD = 526;
    public static final Integer LINE_THREE_LOAD = 3384;

    public Score calculateScore(Schedule schedule) {
        //счёт
        Integer hardScore = 0;
        Integer lineOneLoad = 0;
        Integer lineTwoLoad = 0;
        Integer lineThreeLoad = 0;

        for (Operator operator : schedule.getOperators()) {
            if (operator.getLine().equals(1)) {
                lineOneLoad += operator.getLoad();
            }
            if (operator.getLine().equals(2)) {
                lineTwoLoad += operator.getLoad();
            }
            if (operator.getLine().equals(3)) {
                lineThreeLoad += operator.getLoad();
            }
        }

        //лучшее решение
        if (lineOneLoad >= LINE_ONE_LOAD && lineThreeLoad >= LINE_THREE_LOAD && lineTwoLoad >= LINE_TWO_LOAD) {
            hardScore += 10000;
        }

        //выставляем счёт равный модулю разности необходимого значиния линии и текущего и умножаем на приоритет
        hardScore += Math.abs(LINE_ONE_LOAD - lineOneLoad) * -100;
        hardScore += Math.abs(LINE_TWO_LOAD - lineTwoLoad) * -10;
        hardScore += Math.abs(LINE_THREE_LOAD - lineThreeLoad) * -1;

        return HardSoftScore.valueOf(hardScore, 0);
    }
}
