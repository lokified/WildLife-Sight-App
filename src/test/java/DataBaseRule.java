import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DataBaseRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "dammey5");
    }

    @Override
    protected void after() {
        try( Connection conn = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *";
            String deleteSightingsQuery = "DELETE FROM sightings *";
            conn.createQuery(deleteAnimalsQuery)
                    .executeUpdate();
            conn.createQuery(deleteSightingsQuery)
                    .executeUpdate();
        }
    }
}
