package models;

public class FreightElevator extends Elevator {

    public FreightElevator() {
    }

    public FreightElevator(Long id, Float maxWeight, Float actualWeight, Integer position) {
        super(id, maxWeight, actualWeight, position);
    }
}
