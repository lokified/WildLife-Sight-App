import org.sql2o.Connection;

import java.util.List;

public class Animals extends WildlifeAnimal {

    public static final String DATABASE_TYPE = "Not-endangered";

    public Animals(String name, int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals";
        try (Connection conn = DB.sql2o.open()){
          return conn.createQuery(sql)
                  .throwOnMappingFailure(false)
          .executeAndFetch(Animals.class);
        }
    }

}