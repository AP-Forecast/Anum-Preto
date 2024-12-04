package API.database;

import API.database.models.Hourly; // Import your entity classes
import API.database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Migration {

    private static final Logger logger = LoggerFactory.getLogger(Migration.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Perform the migration
        migrateData(sessionFactory);
    }

    private static void migrateData(SessionFactory sessionFactory) {
        // Example data to be migrated
        List<Hourly> hourlyData = createSampleData(); // Create or fetch your data here

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                // Save each Hourly instance to the database
                for (Hourly hourly : hourlyData) {
                    session.persist(hourly);
                }

                transaction.commit();
                logger.info("Migration completed successfully.");
            } catch (Exception e) {
                logger.error("Error during migration, rolling back transaction: {}", e.getMessage(), e);
                transaction.rollback(); // Rollback in case of an error
            }
        } catch (Exception e) {
            logger.error("Failed to open session or execute migration: {}", e.getMessage(), e);
        }
    }

    private static List<Hourly> createSampleData() {
        // Create sample Hourly data for migration
        // This is just an example; replace it with your actual data creation logic
        Hourly hourly1 = new Hourly();
        hourly1.setDay("Monday");
        hourly1.setHour("08:00");
        hourly1.setTemperature(20.5);
        hourly1.setRainChance(10);
        hourly1.setRainAmount(0.0);
        hourly1.setWindSpeed(5.0);
        hourly1.setWindDirection(270);
        hourly1.setUvIndex(3.0);

        Hourly hourly2 = new Hourly();
        hourly2.setDay("Monday");
        hourly2.setHour("09:00");
        hourly2.setTemperature(22.0);
        hourly2.setRainChance(5);
        hourly2.setRainAmount(0.0);
        hourly2.setWindSpeed(6.0);
        hourly2.setWindDirection(280);
        hourly2.setUvIndex(4.0);

        return List.of(hourly1, hourly2); // Return a list of sample data
    }
}