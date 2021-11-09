import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimals extends WildlifeAnimal implements DatabaseManagement {

    public static final String DATABASE_TYPE = "endangered";
    private String health;
    private String age;

    public EndangeredAnimals(String name, String health, String age) {
        if (name.equals(" ")) {
            throw new IllegalArgumentException("Please enter a name.");

        }
        this.name = name;
        this.health = health;
        this.age = age;
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


    @Override
    public void save() {
        try(Connection conn = DB.sql2o.open()) {
           String sql = "INSERT INTO animals (name, type, health, age) VALUES (:name, :type, :health, :age)";
           this.id = (int) conn.createQuery(sql,true)
                   .addParameter("name",this.name)
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

    public void update(int id, String newName, String newHealth, String newAge) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name, health = :health, age = :age WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("health", newHealth)
                    .addParameter("age", newAge)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


}
