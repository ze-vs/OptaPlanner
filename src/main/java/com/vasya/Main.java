package com.vasya;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.List;

public class Main {

    static Schedule unsolvedCourseSchedule;

    public static void main(String[] args) {
        unsolvedCourseSchedule = new Schedule();

        Generator generator = new Generator();

        unsolvedCourseSchedule.getLinesList().addAll(generator.generateLine());
        /*unsolvedCourseSchedule.getOperators().addAll(generator.generateOperators(90));*/
        unsolvedCourseSchedule.getOperators().addAll(generator.innerGenerateOperators(50));
        //unsolvedCourseSchedule.getLoadsList().addAll(generator.generateLoad());

        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource("scheduleSolverConfiguration.xml");
        Solver<Schedule> solver = solverFactory.buildSolver();
        Schedule solvedCourseSchedule = solver.solve(unsolvedCourseSchedule);
        System.out.println(solvedCourseSchedule.toString());
        overLack(solvedCourseSchedule.getOperators());
        System.out.println("test");
        System.out.println("task1");

    }

    public static void overLack(List<Operator> operators) {
        int lineOneLoad = 0;
        int lineTwoLoad = 0;
        int lineThreeLoad = 0;
        for (Operator operator : operators) {
            if (operator.getLine().equals(1)) {
                lineOneLoad += operator.getLoad();
            } else if (operator.getLine().equals(2)) {
                lineTwoLoad += operator.getLoad();
            } else {
                lineThreeLoad += operator.getLoad();
            }
        }

        printOverLack(lineOneLoad, "1");
        printOverLack(lineTwoLoad, "2");
        printOverLack(lineThreeLoad, "3");


    }

    public static void printOverLack(int load, String lineNumber) {
        int loadNeeded;
        if (lineNumber.equals("1")) {
            loadNeeded = ScoreCalculator.LINE_ONE_LOAD;
        } else if (lineNumber.equals("2")) {
            loadNeeded = ScoreCalculator.LINE_TWO_LOAD;
        } else {
            loadNeeded = ScoreCalculator.LINE_THREE_LOAD;
        }

        if (load > loadNeeded) {
            float overload = (float) (load - loadNeeded) / 50;
            System.out.println("Переизбыток " + Math.ceil(overload) + " человек на линии " + lineNumber);
        } else {
            float lackofload = (float) (loadNeeded - load) / 50;
            System.out.println("Недостаток " + Math.ceil(lackofload) + " человек на линии " + lineNumber);
        }
    }
}
