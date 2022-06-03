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
            "3 . Leave the Public Elevator\n" +
            "4 . Call the Freight Elevator\n" +
            "5 . Use and enter into the Freight Elevator\n" +
            "6 . Leave the Freight Elevator\n" +
            "7 . Search the Access key \n" +
            "8 . Leave the access key \n" +
            "9 . Select how much weight you are carrying \n" +
            "10 . Change user weight \n" +
            "0 . Exit the building \n";

    private final static String defaultCase = "Sorry, that's not an existing option, try something else.\n";
    private final static String accessKeyFound = "Take the access key: Now you have access to the basement and the last floor.";
    private final static String leaveAccessKey = "You haven't got the access key anymore. Basement and last floor blockeds.";
    private final static String exitCase = "See you later!";

    //This procedure will run the entire application.
    public static void RunApplication() throws Exception {

        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
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
        UserService.settingUserWeight(sc, user);
        UserService.settingUserCarryingWeight(sc, user);

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
                    ElevatorService.callingElevator(publicElevator, user);
                    break;

                case 2://Use public elevator
                    ElevatorService.elevatorGenericCase(sc, building, publicElevator, actualFloor, user);
                    ElevatorService.leaveElevator(freightElevator, 2);
                    break;

                case 3://Empty public elevator
                    //System.out.println(emptyElevatorCase);
                    ValidationService.validateElevatorEmptiness(publicElevator);
                    ElevatorService.leaveElevator(publicElevator, 1);
                    break;

                case 4://Call freight elevator
                    ElevatorService.callingElevator(freightElevator, user);
                    break;

                case 5://Use freight elevator
                    ElevatorService.elevatorGenericCase(sc, building, freightElevator, actualFloor, user);
                    ElevatorService.leaveElevator(publicElevator, 2);
                    break;

                case 6://Empty freight elevator
                    ValidationService.validateElevatorEmptiness(freightElevator);
                    ElevatorService.leaveElevator(freightElevator, 1);
                    break;

                case 7://Search access key
                    System.out.println(accessKeyFound);
                    ElevatorService.searchAccessKey(publicElevator);
                    break;
                case 8://Leave access key
                    System.out.println(leaveAccessKey);
                    ElevatorService.leaveAccessKey(publicElevator);
                    break;
                case 9://Select weight carrying
                    UserService.settingUserCarryingWeight(sc, user);
                    break;
                case 10://Select user weight
                    UserService.settingUserWeight(sc, user);
                    break;

                //secret code
                case 100:
                    System.out.println("\nUser \n" +
                            "ActualFloor=" + user.getActualFloor() + "\n"+
                            "Weight="+ user.getWeight() + "\n"+
                            "CarryingWeight=" + user.getCarryingWeight() + "\n");

                    System.out.println("Public \n" +
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

}
