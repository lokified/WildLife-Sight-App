import org.sql2o.Connection;

import java.util.List;

public class Animals extends WildlifeAnimal  implements DatabaseManagement{

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

    public void update(int id, String newName, int newSightingId) {
        String sql = "UPDATE animals SET (name,sightingId) = (:name, :sightingId) WHERE id = :id";
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("name",newName)
                    .addParameter("sightingId",newSightingId)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public static Animals find(int id) {
        try (Connection conn = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            Animals animal = conn.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }
}