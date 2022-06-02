package models;

public class FreightElevator extends Elevator {

    public FreightElevator() {
    }

    public FreightElevator(Long id, Float maxWeight, Float actualWeight, String accessLevel, Integer position) {
        super(id, maxWeight, actualWeight, accessLevel, position);
    }
}
