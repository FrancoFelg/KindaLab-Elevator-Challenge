package services;

import models.*;

import java.util.Scanner;

//This service is created to diminish the quantity of code in the main class.
public class mainService {

    static Integer  actualFloor = 0;

    private final static String menuText = "Welcome to our Java elevator system. \n" +
            "What do you want to do? \n" +
            "1 . Call the Public Elevator \n" +
            "2 . Use and enter into the Public Elevator \n" +
            "3 . Left the Public Elevator\n" +
            "4 . Call the Freight Elevator\n" +
            "5 . Use and enter into the Freight Elevator\n" +
            "6 . Left the Freight Elevator\n" +
            "7 . Search the Access key \n" +
            "8 . Leave the access key \n" +
            "9 . Select how much weight you are carrying \n" +
            "10 . Change user weight \n" +
            "0 . Exit the building \n";

    private final static String GenericElevatorCase = "Please select the floor that you'll want to go (for the basement, please select -1) \n";

    private final static String publicElevatorCase = "You selected the Public Elevator. \n" + GenericElevatorCase;
    private final static String freightElevatorCase = "You selected the Freight Elevator. \n" + GenericElevatorCase;

    private final static String defaultCase = "Sorry, that's not an existing option, try something else.\n";

    private final static String accessKeyFound = "Take the access key: Now you have access to the basement and the last floor.";
    private final static String leaveAccessKey = "You haven't got the access key anymore. Basement and last floor blockeds.";
    private final static String exitCase = "See you later!";

    private final static String callingElevator = "The elevator has arrived to your actual floor.";

    //This procedure will run the entire application.
    public static void RunApplication() throws Exception {

        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, "Public", 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F, "Freight", 0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);

        menuSystem(building, publicElevator, freightElevator);
    }

    public static void menuSystem(Building building, PublicElevator publicElevator, FreightElevator freightElevator) throws Exception {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        boolean exit = false;
        Integer opc;
        User user = new User(0);

        //This while permits to get the user weight
        System.out.println("Welcome to our Java elevator system! \n");
        settingUserWeight(sc, user);
        settingUserCarryingWeight(sc, user);

        while(!exit){
            System.out.println(menuText);
            System.out.println("Actually in " + user.getActualFloor() + " floor \n\n");
            try{
                opc = sc.nextInt();
            switch(opc){
                case 0:
                    System.out.println(exitCase);
                    exit = true;
                    break;

                case 1://Call public elevator
                    callingElevator(publicElevator, user);
                    break;

                case 2://Use public elevator
                    elevatorGenericCase(sc, building, publicElevator, actualFloor, user);
                    leaveElevator(freightElevator, 2);
                    break;

                case 3://Empty public elevator
                    //System.out.println(emptyElevatorCase);
                    ValidationService.validateElevatorEmptiness(publicElevator);
                    leaveElevator(publicElevator, 1);
                    break;

                case 4://Call freight elevator
                    callingElevator(freightElevator, user);
                    break;

                case 5://Use freight elevator
                    elevatorGenericCase(sc, building, freightElevator, actualFloor, user);
                    leaveElevator(publicElevator, 2);
                    break;

                case 6://Empty freight elevator
                    ValidationService.validateElevatorEmptiness(freightElevator);
                    leaveElevator(freightElevator, 1);
                    break;

                case 7://Search access key
                    System.out.println(accessKeyFound);
                    searchAccessKey(publicElevator);
                    break;

                case 8://Leave access key
                    System.out.println(leaveAccessKey);
                    leaveAccessKey(publicElevator);
                    break;
                case 9://Select weight carrying
                    settingUserCarryingWeight(sc, user);
                    break;
                case 10://Select user weight
                    settingUserWeight(sc, user);
                    break;

                //secret code
                case 100:
                    System.out.println("\nUser \n " +
                            "ActualFloor=" + user.getActualFloor() + "\n"+
                            "Weight="+ user.getWeight() + "\n"+
                            "CarryingWeight=" + user.getCarryingWeight() + "\n");

                    System.out.println("Public \n " +
                            "Position=" + publicElevator.getPosition() + "\n"+
                            "Weight"+ publicElevator.getActualWeight() + "\n"+
                            "Access Key=" + publicElevator.getAccessKey() + "\n" );


                    System.out.println("Freight \n" +
                            "Position=" + freightElevator.getPosition() +"\n"+
                            "Weight="+ freightElevator.getActualWeight() +"\n");
                    break;
                //secret code

                default: System.out.println(defaultCase);
            }

            }catch(Exception e){
                System.out.println("Input error, select another one.");
            }
        }

    }

    /**
     * This method lets us get the user weight. It cannot stop until the user sets and valid weight.
     * @param sc: The scanner we will use.
     * @param user: The user who we will set his weight.
     */
    public static void settingUserWeight(Scanner sc, User user){
        String weightInText = "";
        Float weight = 0F;
        Boolean exit = false;
        while(!exit){
            try{
                System.out.println("Can you insert your weight? (You can use numbers and dots, not commas.)\n");

                weightInText = sc.nextLine();
                weight = Float.parseFloat(weightInText);

                if(weight > 0){//If the input
                    exit = true;
                }

            }catch(Exception e){
                System.out.println("There has been an error in your weight input, can you repeat it or change it?");
            }
        }

        user.setWeight(weight);
        exit = false;
    }

    /**
     * This method lets us get the users carrying weight. It cannot stop until the user sets and valid weight.
     * @param sc: The scanner we will use.
     * @param user: The user who we will set his carrying weight.
     */
    public static void settingUserCarryingWeight(Scanner sc, User user){
        String weightInText = "";
        Float weight = 0F;
        Boolean exit = false;
        while(!exit){
            try{
                System.out.println("Can you insert the weight you want to carry? (You can use numbers and dots, not commas.)\n");

                weightInText = sc.nextLine();
                weight = Float.parseFloat(weightInText);

                if(weight > 0){//If the input
                    exit = true;
                }

            }catch(Exception e){
                System.out.println("There has been an error in your carrying weight input, can you repeat it or change it?");
            }
        }

        user.setCarryingWeight(weight);
        exit = false;
    }

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
