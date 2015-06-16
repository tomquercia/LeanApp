package com.nationwide.mobile.lean;

/**
 * Created by Tom on 5/31/2015.
 */
import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Category {

    public ArrayList<Category> children;
    public ArrayList<String> selection;
    public static Context context;


    public String name;

    public Category() {
        children = new ArrayList<Category>();
        selection = new ArrayList<String>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category (String name, ArrayList<String> selection){
        children = new ArrayList<Category>();
        this.selection = selection;
        this.name=name;
    }

    public String toString() {
        return this.name;
    }

    // generate some random amount of child objects (1..10)
    private void generateChildren(String option) {
        String[] children;
        switch (option){
            case "Build":
                children=context.getResources().getStringArray(R.array.build);
                break;
            case "Incident Management":
                children=context.getResources().getStringArray(R.array.incident_management);
                break;
            case "Defect Management":
                children=context.getResources().getStringArray(R.array.defect_management);
                break;
            case "Requests (enhancements, CQ issues, DR, etc)":
                children=context.getResources().getStringArray(R.array.requests);
                break;
            case "Requirements":
                children=context.getResources().getStringArray(R.array.requirements);
                break;
            case "Item Testing":
                children=context.getResources().getStringArray(R.array.item_testing);
                break;
            case "Release Testing":
                children=context.getResources().getStringArray(R.array.release_testing);
                break;
            case "Release / config management":
                children=context.getResources().getStringArray(R.array.release_config_management);
                break;
            case "Resource Management":
                children=context.getResources().getStringArray(R.array.resource_management);
                break;
            case "Miscellaneous":
                children=context.getResources().getStringArray(R.array.misc);
                break;
            default:
                children=context.getResources().getStringArray(R.array.misc);
                break;
        }
        for(int i=0; i < children.length; i++) {
            Category cat = new Category(children[i]);
            this.children.add(cat);
        }
    }

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String[] options = context.getResources().getStringArray(R.array.tasks);
        for(int i = 0; i < options.length; i++) {
            Category cat = new Category(options[i]);
            cat.generateChildren(options[i]);
            categories.add(cat);
        }
        return categories;
    }


    public static ArrayList<Category> getPopulatedCategories(QuarterHour quarterHour){
        ArrayList<Category> categories = new ArrayList<Category>();
        String[] options = context.getResources().getStringArray(R.array.tasks);
        for(int i = 0; i < options.length; i++) {
            Category cat = new Category(options[i],quarterHour.getChoices().get(i).selection);
            cat.generateSavedChildren(options[i], quarterHour);
/*            for (int j = 0; j<quarterHour.getChoices().get(i).selection.size();j++){
                Category child = new Category(quarterHour.getChoices().get(i).selection.get(j));
                this.children.add(child);
            }*/
            categories.add(cat);
        }
        return categories;
    }

    public void generateSavedChildren(String option, QuarterHour quarterHour){
        String[] children;
        int parent;
        switch (option){
            case "Build":
                parent=0;
                children=context.getResources().getStringArray(R.array.build);
                break;
            case "Incident Management":
                parent=1;
                children=context.getResources().getStringArray(R.array.incident_management);
                break;
            case "Defect Management":
                parent=2;
                children=context.getResources().getStringArray(R.array.defect_management);
                break;
            case "Requests (enhancements, CQ issues, DR, etc)":
                parent=3;
                children=context.getResources().getStringArray(R.array.requests);
                break;
            case "Requirements":
                parent=4;
                children=context.getResources().getStringArray(R.array.requirements);
                break;
            case "Item Testing":
                parent=5;
                children=context.getResources().getStringArray(R.array.item_testing);
                break;
            case "Release Testing":
                parent=6;
                children=context.getResources().getStringArray(R.array.release_testing);
                break;
            case "Release / config management":
                parent=7;
                children=context.getResources().getStringArray(R.array.release_config_management);
                break;
            case "Resource Management":
                parent=8;
                children=context.getResources().getStringArray(R.array.resource_management);
                break;
            case "Miscellaneous":
                parent=9;
                children=context.getResources().getStringArray(R.array.misc);
                break;
            default:
                parent=9;
                children=context.getResources().getStringArray(R.array.misc);
                break;
        }
        for(int i=0; i < children.length; i++) {
            String choice = children[i];
            ArrayList<String> selections= quarterHour.getChoices().get(parent).selection;
            Category cat = new Category(choice, selections);
            this.children.add(cat);
            //this.selection.add(selections.get(i));
        }
/*        for (int j = 0; j<quarterHour.getChoices().get(i).selection.size();j++){
            Category child = new Category(quarterHour.getChoices().get(i).selection.get(j));
            this.children.add(child);
        }*/
    }


    public static Category get(String name)
    {
        ArrayList<Category> collection = Category.getCategories();
        for (Iterator<Category> iterator = collection.iterator(); iterator.hasNext();) {
            Category cat = (Category) iterator.next();
            if(cat.name.equals(name)) {
                return cat;
            }

        }
        return null;
    }
}
