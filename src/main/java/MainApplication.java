import services.mainService;
public class MainApplication {

    public static void main(String[] args) {
        try{
            mainService.RunApplication();
        }catch( Exception e) {
            e.printStackTrace();
        }
    }

}
