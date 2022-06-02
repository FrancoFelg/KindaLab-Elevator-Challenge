package services;

import models.User;

import java.util.Scanner;

public class UserService {


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


}
