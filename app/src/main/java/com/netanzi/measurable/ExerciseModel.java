package com.netanzi.measurable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExerciseModel {
    private int exerciseID;
    private Map<String, Double> results;
    private LocalDateTime date;

    public ExerciseModel(int exerciseID, LocalDateTime date) {
        this.exerciseID = exerciseID;
        this.date = date;
        results = new HashMap<>();
    }

    public ExerciseModel(int exerciseID, Map<String, Double> results, LocalDateTime date) {
        this.exerciseID = exerciseID;
        this.results = results;
        this.date = date;
    }

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
    public void addResult(String key, double value){
        results.put(key, value);
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

    public double getScore() {
        if (results == null || results.isEmpty()) {
            return 0.0; // Return a default value if there are no results
        }

        double sum = 0.0;
        int count = 0;

        for (Double value : results.values()) {
            sum += value;
            count++;
        }

        return sum / count;
    }
}
