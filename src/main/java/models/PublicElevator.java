package models;

public class PublicElevator extends Elevator {

    private Boolean accessKey;

    //Empty Constructor
    public PublicElevator(){}

    //AllArgs Constructor
    public PublicElevator(Long id, Float maxWeight, Float actualWeight, Integer position, Boolean accessKey) {
        super(id, maxWeight, actualWeight, position);
        this.accessKey = accessKey;
    }

    //Getter
    public Boolean getAccessKey() {
        return accessKey;
    }

    //Setter
    public void setAccessKey(Boolean hasAccessKey) {
        this.accessKey = hasAccessKey;
    }

}
