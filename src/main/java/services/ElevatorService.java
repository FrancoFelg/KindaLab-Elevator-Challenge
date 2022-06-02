package services;

import models.Building;
import models.Elevator;
import models.PublicElevator;
import models.User;

import java.util.Scanner;

public class ElevatorService {

    private final static String callingElevator = "The elevator has arrived to your actual floor.";
    private final static String GenericElevatorCase = "Please select the floor that you'll want to go (for the basement, please select -1) \n";
    private final static String publicElevatorCase = "You selected the Public Elevator. \n" + GenericElevatorCase;
    private final static String freightElevatorCase = "You selected the Freight Elevator. \n" + GenericElevatorCase;

    /**
     * This method changes the elevator position to the user position.
     * @param elevator: Is the elevator that changes its position
     * @param user: Is the actual user who called the elevator
     */
    public static void callingElevator(Elevator elevator, User user) {
        System.out.println(callingElevator);
        elevator.setPosition(user.getActualFloor());
    }

    /**
     * This method permits the user use the Elevator and change its position and the weight. It calls usingElevator
     * @param sc: Scanner
     * @param building: Actual building
     * @param elevator: Actual elevator
     */
    public static void elevatorGenericCase(Scanner sc, Building building, Elevator elevator, Integer actualFloor, User user) throws Exception{
        try{
            ValidationService.validateUserAndElevatorFloor(user, elevator);
            ValidationService.validateWeightLimit(user.getWeight()+user.getCarryingWeight(), elevator.getMaxWeight());
            if(elevator.getClass() == PublicElevator.class){
                System.out.println(publicElevatorCase);
            }else{
                System.out.println(freightElevatorCase);
            }

            Integer floor = sc.nextInt();
            usingElevator(building, elevator, floor, actualFloor, user);
        }catch(Exception e){
            System.out.println("Try using other inputs.");
        }
    }

    /**
     * This method validates that the inputs are correct and then, it changes the position and the weight in the elevator
     * @param building: Is the actual building. Used to get the quantity of stories.
     * @param elevator: The actual elevator. Generic because it lets use the Public and Freight elevators in only one method.
     * @param floorNumber: Is the floor that we need to go.
     */
    public static void usingElevator(Building building, Elevator elevator, Integer floorNumber, Integer actualFloor, User user) throws Exception {
        //Instantiating objects for more clarity using the functions
        Integer maxFloor = building.getStoriesHigh();
        Float elevMaxWeight = elevator.getMaxWeight();
        Float elevActWeight = elevator.getActualWeight();

        //Validating the floor range and the weight limit
        ValidationService.validateFloorRange(floorNumber, maxFloor);
        ValidationService.validateWeightLimit(elevActWeight, elevMaxWeight);

        //If the elevator is public I verify that the user has an access key to enter in the basement and the 50th floor.
        if(elevator.getClass() == PublicElevator.class){
            ValidationService.validatePublicElevatorAccess( (PublicElevator) elevator, floorNumber, building);
        }

        //changing the floor and the weight of the elevator
        elevator.setPosition(floorNumber);
        elevator.setActualWeight(user.getWeight()+user.getCarryingWeight());

        // <---
        if(elevator.getClass() == PublicElevator.class){
            System.out.println(publicElevatorCase);
        }else{
            System.out.println(freightElevatorCase);
        }

        user.setActualFloor(floorNumber);

        if(floorNumber == -1){
            System.out.println("Welcome to the basement");
        }else{
            System.out.println("Welcome to the floor " + floorNumber);
        }

    }

    /**
     * This method turns AccessKey to true
     * @param pe: Is the Public Elevator.
     */
    public static void searchAccessKey(PublicElevator pe) throws Exception {

        try{
            ValidationService.validateUserHaveAccessKey(pe);
        }catch(Exception e){
            System.out.println("You already have an access key.");
        }
        pe.setAccessKey(true);
    }

    /**
     * This method turns AccessKey to false
     * @param pe: Is the Public Elevator.
     */
    public static void leaveAccessKey(PublicElevator pe) throws Exception {

        try{
            ValidationService.validateUserDontHaveAccessKey(pe);
        }catch(Exception e){
            System.out.println("You don't have any access key.");
        }

        pe.setAccessKey(false);
    }

    /**
     * This method lets the user leave the elevator, so it's weight is 0
     * @param elevator: Is the actual elevator
     * @throws Exception
     */
    public static void leaveElevator(Elevator elevator, Integer opt) throws Exception{
        try{
            if(opt == 1){//case opt == 1 -> The user wants to leave the elevator
                if(elevator.getClass() == PublicElevator.class){
                    System.out.println("You left the Public Elevator.");
                }else{
                    System.out.println("You left the Freight Elevator.");
                }
            }//case opt == 2 -> Internally the app sets the elevator weight to 0
            elevator.setActualWeight(0F);


        }catch(Exception e){
            throw new Exception("There is no more things to get out from the elevator.");
        }
    }


}
