package com.netanzi.measurable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class SkillModel {
    public static SkillModel skill;
    public static ArrayList<SkillModel> skills = new ArrayList<>();
    private final int skillID;
    private String title;
    private String description;
    private double score;
    private ArrayList<String> metrics;
    private ArrayList<ExerciseModel> exercises;


    /**
     * Represents a SkillModel with default values.
     */
    public SkillModel() {
        this.skillID = -1;
        this.title = "title";
        this.description = "description";
        this.score = 0;
        this.metrics = null;
        exercises = new ArrayList<>();
    }

    /**
     * Represents a SkillModel with specified values.
     *
     * @param skillID     The ID of the skill.
     * @param title       The title of the skill.
     * @param description The description of the skill.
     * @param score       The score associated with the skill.
     * @param metrics     The list of metrics associated with the skill.
     */
    public SkillModel(int skillID, String title, String description, double score, ArrayList<String> metrics) {
        this.skillID = skillID;
        this.title = title;
        this.description = description;
        this.score = score;
        this.metrics = metrics;
        exercises = new ArrayList<>();
    }

    /**
     * Represents a SkillModel with specified values.
     *
     * @param skillID     The ID of the skill.
     * @param title       The title of the skill.
     * @param description The description of the skill.
     * @param score       The score associated with the skill.
     * @param metrics     The list of metrics associated with the skill.
     * @param exercises     The list of exercises associated with the skill.
     */
    public SkillModel(int skillID, String title, String description, double score, ArrayList<String> metrics, ArrayList<ExerciseModel> exercises) {
        this.skillID = skillID;
        this.title = title;
        this.description = description;
        this.score = score;
        this.metrics = metrics;
        this.exercises = exercises;
    }

    /** Getters and Setters */
    public int getSkillID() {
        return skillID;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ArrayList<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(ArrayList<String> metrics) {
        this.metrics = metrics;
    }
    public int getMetricsSize(){
        return metrics.size();
    }
    public int getExercisesSize(){
        return exercises.size();
    }
    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }
    public ExerciseModel getExercise(int index){
        return exercises.get(index);
    }
    public LocalDateTime getRecentDate(){
        if (exercises.size() < 1)
            return null;
        return getExercise(exercises.size()-1).getDate();
    }
    public void setExercises(ArrayList<ExerciseModel> exercises) {
        this.exercises = exercises;
    }
    public void addExercise(ExerciseModel exercise){
        exercises.add(exercise);
    }
    /*public void addMetric(String name, double value){
        score = ((score * metrics.size()) + value) / (metrics.size() + 1);
        metrics.put(name, value);
    }*/
}
