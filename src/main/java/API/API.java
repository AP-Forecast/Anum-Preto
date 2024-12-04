package API;

import API.database.utils.CRUD_Operator;

public class API {

    public static void main( String [] args){

        Localizer.setLocation("-6.904819", "-37.528106");
        CRUD_Operator doc = new CRUD_Operator();

        try {

            for (String texto : Localizer.getWeeklyData()){
                System.out.println(texto);
            }

            for (String texto : Localizer.getHourlyData()){
                System.out.println(texto);
            }


            doc.createData(Localizer.getData(), Localizer.getHourly_data());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
