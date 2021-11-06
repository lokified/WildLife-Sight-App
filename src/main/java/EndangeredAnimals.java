import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimals extends WildlifeAnimal implements DatabaseManagement {

    public static final String DATABASE_TYPE = "endangered";
    private String health;
    private String age;

    public EndangeredAnimals(String name, String health, String age, int sightingId) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.sighting_Id = sighting_Id;
        type = DATABASE_TYPE;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimals that = (EndangeredAnimals) o;
        return Objects.equals(health, that.health) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, age);
    }



    public void save() {
        try(Connection conn = DB.sql2o.open()) {
           String sql = "INSERT INTO animals (name,sighting_Id, type, health, age) VALUES (:name, :sighting_Id, :type, :health, :age)";
           this.id = (int) conn.createQuery(sql,true)
                   .addParameter("name",this.name)
                   .addParameter("sighting_Id",this.sighting_Id)
                   .addParameter("type",this.type)
                   .addParameter("health",this.health)
                   .addParameter("age",this.age)
                   .executeUpdate()
                   .getKey();
        }
    }

    public static List<EndangeredAnimals> all() {
        String sql = "SELECT * FROM animals WHERE type = 'endangered'";
        try ( Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }

    public static EndangeredAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id;";
            EndangeredAnimals EndangeredAnimals = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimals.class);
            return EndangeredAnimals;
        }
    }

    public void update(int id, String newName, int newSightingId, String newHealth, String newAge) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name, sightingId = :sightingId, health = :health, age = :age WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("sightingId",newSightingId)
                    .addParameter("health", newHealth)
                    .addParameter("age", newAge)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


}
