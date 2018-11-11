package com.vasya;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Operator {
    private String id;
    private Integer load;
    private Integer line;

    public void setLine(Integer line) {
        this.line = line;
    }

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

    public Integer getLoad() {
        return load;
    }
}
