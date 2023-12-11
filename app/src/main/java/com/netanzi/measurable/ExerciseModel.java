package com.netanzi.measurable;

import java.time.LocalDateTime;
import java.util.Map;

public class ExerciseModel {
    private int exerciseID;
    private Map<String, Double> results;
    private LocalDateTime date;


    /** Getters and Setters */
    public int getExerciseID() {
        return exerciseID;
    }

    public Map<String, Double> getResults() {
        return results;
    }
    public double getValue(String key){
        return results.get(key);
    }

    public void setResults(Map<String, Double> results) {
        this.results = results;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
