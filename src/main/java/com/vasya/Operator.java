package com.vasya;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;


@PlanningEntity
public class Operator {
    public static final Integer MIN_COLLS = 50;
    public static final Integer MAX_COLLS = 100;

    private String id;
    private Integer load;
    private Integer line;



    public void setLine(Integer line) {
        this.line = line;
    }
    /*    private List<Integer> calls;*/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    @PlanningVariable(valueRangeProviderRefs = {"availableLine"})
    public Integer getLine() {
        return line;
    }

    /*@PlanningVariable(valueRangeProviderRefs = {"availableLoad"})*/
    public Integer getLoad() {
        return load;
    }



/*    @PlanningVariable(valueRangeProviderRefs = {"availableCollsCapacity"})
    public List<Integer> getCalls() {
        return calls;
    }

    public void setCalls(List<Integer> calls) {
        this.calls = calls;
    }*/
}
