package services;

import models.Building;
import models.Elevator;
import models.PublicElevator;
import models.User;

public class ValidationService {

    /**
     * This method validates that the input is correct. It only permits a range from -1 to the max floor, in this case, 50
     * @param destiny: Is the floor to compare
     * @param max: Is the top floor of the building
     */
    public static void validateFloorRange(Integer destiny, Integer max) throws Exception {
        if(!(destiny <= max && destiny >= -1 )){
            System.out.println("That floor number is not valid, try another one.");
            throw new Exception() ;
        }
    }

    /**
     * @param actual: Is the actual weight that the user wants to carry on the elevator
     * @param max: Is the maximum weight that the elevator can lift
     */
    public static void validateWeightLimit(Float actual, Float max) throws Exception {
        if(actual > max){
            System.out.println("The weight limit is exceeded, please try diminishing it.");
            throw new Exception() ;
        }
    }

    public static void validateUserDontHaveAccessKey(PublicElevator publicElevator) throws Exception {
        if( publicElevator.getAccessKey() == false ){
            System.out.println("You already have an access key.");
            throw new Exception();
        }
    }

    public static void validateUserHaveAccessKey(PublicElevator publicElevator) throws Exception {
        if( publicElevator.getAccessKey() == true ){
            System.out.println("You don't have any access key.");
            throw new Exception();
        }
    }

    public static void validatePublicElevatorAccess(PublicElevator elevator, Integer floor, Building building) throws Exception {
        if(elevator.getAccessKey() == false && (floor == -1 || floor == building.getStoriesHigh() ) ){
            System.out.println("You don't have the permission to enter to this floor.");
            throw new Exception();
        }
    }

    public static void validateElevatorEmptiness(Elevator elevator) throws Exception {
        if(elevator.getActualWeight() == 0){
            System.out.println("You are not in this elevator");
            throw new Exception();
        }
    }
    /**
    This method validates that the user is in the same floor that the elevator.
     */
    public static void validateUserAndElevatorFloor(User user, Elevator elevator) throws Exception {
        if(user.getActualFloor() != elevator.getPosition()){
            System.out.println("The elevator is not in your actual floor.");
            throw new Exception();
        }
    }

}
