import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DataBaseRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "dammey5");
        //DB.sql2o = new Sql2o("jdbc:postgresql://ec2-18-206-108-36.compute-1.amazonaws.com:5432/d62hif7i4bl3tk", "vlsgrdcvjyvonu", "b8b259355af4e04b20aac9fd9d4a445a5121ff15882e17ec943bbec8b13748b0");

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
