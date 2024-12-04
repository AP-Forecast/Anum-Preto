package API;

public class API {

    public static void main( String [] args){

        Localizer.setLocation("-6.904819", "-37.528106");

        try {
            for (String texto : Localizer.getHourlyData()){
                System.out.println(texto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
