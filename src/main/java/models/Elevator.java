package models;

/**
 * This class permits the creation of the other two elevators using inheritance
 * It has getters of all his attributes, but only setter for the position.
 */
public abstract class Elevator {

    //Attributes
    private Long id;
    private Float maxWeight; //it is calculated in kilograms.
    private Float actualWeight;
    private String accessLevel;
    private Integer position;

    //Empty Constructor
    public Elevator(){}


    //AllArgs Constructor
    public Elevator(Long id, Float maxWeight, Float actualWeight, String accessLevel, Integer position) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.actualWeight = actualWeight;
        this.accessLevel = accessLevel;
        this.position = position;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public Float getMaxWeight() {
        return maxWeight;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public Integer getPosition() {
        return position;
    }

    public Float getActualWeight() {
        return actualWeight;
    }

    //Setters
    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setActualWeight(Float actualWeight) {
        this.actualWeight = actualWeight;
    }
}
