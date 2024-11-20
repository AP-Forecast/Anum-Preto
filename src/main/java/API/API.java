package API;

public class API {

    public static void main( String [] args){
        Localizer localizer = new Localizer("-6.904811", "-37.520145");

        try {
            System.out.println(localizer.getLocation());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
