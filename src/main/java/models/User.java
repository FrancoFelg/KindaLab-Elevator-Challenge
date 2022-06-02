package models;

public class User {

    //Attributes
    private Integer actualFloor;
    private Float weight;
    private Float carryingWeight;

    //Empty Constructor
    public User(){}

    //Only actualFloor Constructor
    public User(Integer actualFloor) {
        this.actualFloor = actualFloor;
    }

    //AllArgs Constructor
    public User(Integer actualFloor, Float weight, Float carryingWeight) {
        this.actualFloor = actualFloor;
        this.weight = weight;
        this.carryingWeight = carryingWeight;
    }

    //Getters
    public Integer getActualFloor() {
        return actualFloor;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getCarryingWeight() {
        return carryingWeight;
    }

    //Setters
    public void setActualFloor(Integer actualFloor) {
        this.actualFloor = actualFloor;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setCarryingWeight(Float carryingWeight) {
        this.carryingWeight = carryingWeight;
    }
}
