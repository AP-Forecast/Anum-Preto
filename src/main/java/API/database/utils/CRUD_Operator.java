package API.database.utils;

import API.HourlyData;
import API.WeeklyData;
import API.database.models.Daily;
import API.database.models.Hourly;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
            day = hourlyData.getHourly().getTime().get(0).substring(0, 10);

            if (day.equals(daily.getTime())){
                for (int j = 0; j < hourlyData.getHourly().getTime().size(); j++){

                    hourly = new Hourly();

                    day = hourlyData.getHourly().getTime().get(j).substring(0, 10);
                    System.out.println(day);
                    System.out.println("compara legal dog");
                    System.out.println(daily.getTime());
                    hour = hourlyData.getHourly().getTime().get(j).substring(11);

                    // Creating hourly data and passing it to daily
                    hourly.setHour(hour);
                    hourly.setDayId(daily);
                    hourly.setUvIndex(hourlyData.getHourly().getUvIndex().get(j));
                    hourly.setRainAmount(hourlyData.getHourly().getRainAmount().get(j));
                    hourly.setRainChance(hourlyData.getHourly().getRainChance().get(j));
                    hourly.setTemperature(hourlyData.getHourly().getTemperature().get(j));
                    hourly.setWindDirection(hourlyData.getHourly().getWindDirection().get(j));
                    hourly.setWindSpeed(hourlyData.getHourly().getWindSpeed().get(j));
                    // Passing data to daily
                    daily.setHourlyID(hourly);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewHourly(HourlyData hourlyData){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();
            List<Daily> dailyList = session.createQuery("FROM Daily", Daily.class).getResultList();
            Hourly hourly;
            String date = hourlyData.getHourly().getTime().get(0).substring(0,10);
            String hour;

            for (Daily day: dailyList){
                if (day.getTime().equals(date)){
                    for (int j = 0; j < hourlyData.getHourly().getTime().size(); j++) {
                        hourly = new Hourly();

                        hour = hourlyData.getHourly().getTime().get(j).substring(11);
                        hourly.setHour(hour);
                        hourly.setDayId(day);
                        hourly.setUvIndex(hourlyData.getHourly().getUvIndex().get(j));
                        hourly.setRainAmount(hourlyData.getHourly().getRainAmount().get(j));
                        hourly.setRainChance(hourlyData.getHourly().getRainChance().get(j));
                        hourly.setTemperature(hourlyData.getHourly().getTemperature().get(j));
                        hourly.setWindDirection(hourlyData.getHourly().getWindDirection().get(j));
                        hourly.setWindSpeed(hourlyData.getHourly().getWindSpeed().get(j));
                        // Passing data to daily
                        day.setHourlyID(hourly);


                    }

                    session.persist(day);
                    transaction.commit();
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
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

    public List<Hourly> readHour(long dayId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Hourly> hourData = null;
        try {
            tx = session.beginTransaction();
            Daily daily = session.get(Daily.class, dayId);
            if (daily != null) {
                hourData = new ArrayList<>(daily.getHourlyID()); // Força inicialização
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hourData;
    }


    public List<Daily> readAllDays() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Daily> dailyList = null; // Initialize the list to hold Daily records
        try {
            session.beginTransaction(); // Start a transaction (optional for read operations)

            // Fetch all Daily records
            dailyList = session.createQuery("FROM Daily", Daily.class).getResultList();

            session.getTransaction().commit(); // Commit the transaction if needed
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace();
        } finally {
            session.close(); // Ensure the session is closed
        }
        return dailyList; // Return the list of Daily records
    }

    public static void truncateDb(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create the SQL for truncating the table
            String sql = "TRUNCATE TABLE daily CASCADE";
            session.createNativeQuery(sql).executeUpdate();

            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on error
            }
            e.printStackTrace();
        } finally {
            session.close(); // Ensure the session is closed
        }
    }

}
