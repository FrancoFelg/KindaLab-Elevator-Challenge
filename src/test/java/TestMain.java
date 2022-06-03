import models.*;
import org.junit.jupiter.api.*;
import services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestMain {

    @Test
    public void IfUserUsesElevatorThenActualFloorChanges() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,65F,20F);

        ElevatorService.usingElevator(building, freightElevator, 2,1, user );
        assertEquals(2, (int) user.getActualFloor());
    }

    @Test
    public void IfUserHaveAccessKeyThenCanGoToTheBasementInPublicElevator() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, true);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,65F,20F);

        ElevatorService.usingElevator(building, publicElevator, -1,0, user );
        assertEquals(-1, (int) user.getActualFloor());
    }

    @Test
    public void IfUserDontHaveAccessKeyThenCantGoToTheBasementInPublicElevator() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,65F,20F);

        try{
            ElevatorService.usingElevator(building, publicElevator, -1,0, user );
        }catch(Exception e){
            assertNotEquals(-1, user.getActualFloor());
        }
    }

    @Test
    public void IfUserDontHaveAccessKeyThenCanGoToTheBasementInFreightElevator() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,65F,20F);

        ElevatorService.usingElevator(building, freightElevator, -1,0, user );
        assertEquals(-1, (int) user.getActualFloor());
    }

    @Test
    public void IfWeightIs100ThenPublicElevatorMoves() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,60F,40F);

        ElevatorService.usingElevator(building, freightElevator, 5,0, user );
        assertEquals(5, (int) user.getActualFloor());
    }

    @Test
    public void IfWeightIs1000ThenPublicElevatorDontMoves() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,0F,1000F);

        try{
            ElevatorService.usingElevator(building, freightElevator, 5,0, user );
        }catch(Exception e){
            assertNotEquals(5, user.getActualFloor());
        }
    }


    @Test
    public void IfWeightIs1000ThenFreightElevatorMoves() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,0F,1000F);

        ElevatorService.usingElevator(building, freightElevator, 5,0, user );
        assertEquals(5, (int) user.getActualFloor());
    }

    @Test
    public void IfWeightIs5000ThenFreightElevatorDontMoves() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,0F,5000F);

        try{
            ElevatorService.usingElevator(building, freightElevator, 5,0, user );
        }catch(Exception e){
            assertNotEquals(5, user.getActualFloor());
        }
    }

    @Test
    public void IfTheFloorIsOutOfRangeThenThrowsError() throws Exception {
        PublicElevator publicElevator = new PublicElevator(1L, 1000F, 0F, 0, false);
        FreightElevator freightElevator = new FreightElevator(1L, 30000F, 0F,  0);
        Building building = new Building(1L, 50, publicElevator, freightElevator);
        User user = new User(0,0F,5000F);

        try{
            ElevatorService.usingElevator(building, freightElevator, -50,0, user );
        }catch(Exception e){
            assertNotEquals(-50, user.getActualFloor());
        }
    }


}
