package API.database.utils;

import API.HourlyData;
import API.WeeklyData;
import API.database.models.Daily;
import API.database.models.Hourly;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CRUD_Operator {

    public void createData(WeeklyData week, HourlyData hourlyData){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Daily day;

            for (int i = 0; i < week.getDaily().getTime().size(); i++){
                day = new Daily();

                // setting each individual field
                day.setTime(week.getDaily().getTime().get(i));

                this.createHourly(hourlyData, day);

                day.setApparentTempMax(week.getDaily().getApparentTempMax().get(i));

                day.setApparentTempMin(week.getDaily().getApparentTempMin().get(i));

                day.setDomWindDirection(week.getDaily().getWindDirectionDegrees().get(i));

                day.setMaxWindSpeed(week.getDaily().getMaxWindSpeed().get(i));

                day.setPreciptationSum(week.getDaily().getPreciptation_sum().get(i));

                day.setRainSum(week.getDaily().getRain_sum().get(i));

                day.setUvIndex(week.getDaily().getUvIndex().get(i));

                day.setTemperatureMax(week.getDaily().getTemperatureMax().get(i));

                day.setTemperatureMin(week.getDaily().getTemperatureMin().get(i));

                session.persist(day);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createHourly(HourlyData hourlyData, Daily daily){

        try {
            Hourly hourly;
            String day;
            String hour;

            for (int i = 0; i < hourlyData.getHourly().getTime().size(); i++){

                day = hourlyData.getHourly().getTime().get(i).substring(0, 9);
                hour = hourlyData.getHourly().getTime().get(i).substring(11);

                if (daily.getTime().equals(day)) {
                    hourly = new Hourly();


                    // Creating hourly data and passing it to daily
                    hourly.setHour(hour);
                    hourly.setDayId(daily);
                    hourly.setUvIndex(hourlyData.getHourly().getUvIndex().get(i));
                    hourly.setRainAmount(hourlyData.getHourly().getRainAmount().get(i));
                    hourly.setRainChance(hourlyData.getHourly().getRainChance().get(i));
                    hourly.setTemperature(hourlyData.getHourly().getTemperature().get(i));
                    hourly.setWindDirection(hourlyData.getHourly().getWindDirection().get(i));
                    hourly.setWindSpeed(hourlyData.getHourly().getWindSpeed().get(i));

                    // Passing data to daily
                    daily.getHourlyID().add(hourly);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Daily readDay(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Daily daily = null;
        try {
            daily = session.get(Daily.class, id);
        } finally {
            session.close();
        }
        return daily;
    }

    public ArrayList<Hourly> readHour(long dayId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Daily daily = null;
        try {
            daily = session.get(Daily.class, dayId);
        } finally {
            session.close();
        }
        return daily.getHourlyID();
    }

}
