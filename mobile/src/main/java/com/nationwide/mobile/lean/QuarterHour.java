package com.nationwide.mobile.lean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by querct1 on 6/8/2015.
 */
public class QuarterHour implements Serializable{
    private String quarterHour;
    private ArrayList<Category> choices;

    public QuarterHour(String quarterHour, ArrayList<Category> choices) {
        this.quarterHour = quarterHour;
        this.choices = choices;
    }

    public String getQuarterHour() {
        return quarterHour;
    }

    public void setQuarterHour(String quarterHour) {
        this.quarterHour = quarterHour;
    }

    public ArrayList<Category> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Category> choices) {
        this.choices = choices;
    }
}
