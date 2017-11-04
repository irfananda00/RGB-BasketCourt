package com.rubydev.basketcourt.model;

/**
 * Created by irfanandarafifsatrio on 11/1/17.
 */

public class Score {

    private int id;
    private String nim;
    private String team_a;
    private String team_b;
    private int score_a;
    private int score_b;
    private String date;

    public Score(String team_a, String team_b, int score_a, int score_b, String date) {
        this.team_a = team_a;
        this.team_b = team_b;
        this.score_a = score_a;
        this.score_b = score_b;
        this.date = date;
    }

    public Score(String team_a, String team_b, int score_a, int score_b, String date, String nim) {
        this.team_a = team_a;
        this.team_b = team_b;
        this.score_a = score_a;
        this.score_b = score_b;
        this.date = date;
        this.nim = nim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getTeam_a() {
        return team_a;
    }

    public void setTeam_a(String team_a) {
        this.team_a = team_a;
    }

    public String getTeam_b() {
        return team_b;
    }

    public void setTeam_b(String team_b) {
        this.team_b = team_b;
    }

    public int getScore_a() {
        return score_a;
    }

    public void setScore_a(int score_a) {
        this.score_a = score_a;
    }

    public int getScore_b() {
        return score_b;
    }

    public void setScore_b(int score_b) {
        this.score_b = score_b;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
