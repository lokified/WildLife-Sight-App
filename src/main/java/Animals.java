import org.sql2o.Connection;

import java.util.List;

public class Animals extends WildlifeAnimal  implements DatabaseManagement{

    public static final String DATABASE_TYPE = "Not-endangered";

    public Animals(String name, int sighting_Id) {
        this.name = name;
        this.sighting_Id = sighting_Id;
        type = DATABASE_TYPE;
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE type = 'Not-endangered'";
        try (Connection conn = DB.sql2o.open()){
          return conn.createQuery(sql)
                  .throwOnMappingFailure(false)
          .executeAndFetch(Animals.class);
        }
    }
    public void save() {
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,sighting_Id,type) VALUES (:name, :sighting_Id, :type)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("sighting_Id",this.sighting_Id)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(int id, String newName, int newSighting_Id) {
        String sql = "UPDATE animals SET (name,sighting_Id) = (:name, :sighting_Id) WHERE id = :id";
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("name",newName)
                    .addParameter("sighting_Id",newSighting_Id)
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