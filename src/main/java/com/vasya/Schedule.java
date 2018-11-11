package com.vasya;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
//рещение , потом создаётся список решений
@PlanningSolution
public class Schedule {

    Logger logger = LoggerFactory.getLogger(getClass());

    private HardSoftScore score;

    private List<Operator> operators;
    private List<Integer> lines;
    private List<Integer> loads;

    Schedule(){
        operators = new ArrayList<Operator>();
        lines = new ArrayList<Integer>();
        loads = new ArrayList<Integer>();
    }

    @PlanningEntityCollectionProperty
    public List<Operator> getOperators() {
        return operators;
    }


    @ValueRangeProvider(id = "availableLine")
    @ProblemFactCollectionProperty
    public List<Integer> getLinesList() {
        return lines;
    }

    /*@ValueRangeProvider(id = "availableLoad")
    @ProblemFactCollectionProperty*/
    public List<Integer> getLoadsList() {
        return loads;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public String toString() {
        String result = "";
        for (Operator operator : getOperators()){
            result+="\n Operator id:"+ operator.getId()+ " load:"+ operator.getLoad()+" line:" + operator.getLine();
        }
        return result;
    }
}
