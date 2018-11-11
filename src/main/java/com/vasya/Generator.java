package com.vasya;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * генерируем значения
 */

public class Generator {
    /**
     * список линий
     * @return
     */
    public List<Integer> generateLine() {
        List<Integer> lines = new ArrayList<Integer>();
        lines.add(1);
        lines.add(2);
        lines.add(3);
        return lines;
    }

    /**
     * генерирует операторов с заданным сдвигом
     * @param shift - сдвиг (нагрузка относительно 50)
     * @return
     */
    public List<Operator> innerGenerateOperators(int shift) {
        List<Operator> operators = new ArrayList<Operator>();
        for (int i = 0; i < 100; i++) {
            String id = UUID.randomUUID().toString();
            Operator operator = new Operator();
            operator.setId(id);
            operator.setLine(1);
            Random random = new Random();
            operator.setLoad(random.nextInt(50) + shift);
            operators.add(operator);
        }
        return operators;
    }

    /**
     * генерирует операторов с заданным сдвигом и с нагрузкой равной или выше минимально необходимой
     * @param shift - сдвиг
     * @return
     */

    public List<Operator> generateOperators(int shift) {
        List<Operator> operators;
        int maxLoad;
        do {
            maxLoad = 0;
            operators = innerGenerateOperators(shift);
            for (Operator operator : operators) {
                maxLoad += operator.getLoad();
            }
        }
        while (maxLoad < (ScoreCalculator.LINE_ONE_LOAD + ScoreCalculator.LINE_TWO_LOAD + ScoreCalculator.LINE_THREE_LOAD));
        return operators;
    }

    /**
     *
     * @return
     */
/*    public List<Integer> generateLoad() {
        List<Integer> loadList = new ArrayList<Integer>();
        for (int i = 50; i < 101; i++) {
            loadList.add(i);
        }
        return loadList;
    }*/
}
