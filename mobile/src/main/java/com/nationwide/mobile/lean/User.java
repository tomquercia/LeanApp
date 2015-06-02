package com.nationwide.mobile.lean;

/**
 * Created by querct1 on 6/2/2015.
 */
public class User {
    private String firstName;
    private String lastName;
    private String role;
    private String managerFirstName;
    private String managerLastName;
    private String city;
    private String building;
    private String floorNumber;

    public User(String firstName, String lastName, String role, String managerFirstName, String managerLastName, String city, String building, String floorNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.role=role;
        this.managerFirstName=managerFirstName;
        this.managerLastName=managerLastName;
        this.city=city;
        this.building=building;
        this.floorNumber=floorNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }
}
