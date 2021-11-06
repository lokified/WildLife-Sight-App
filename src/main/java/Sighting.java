import org.sql2o.Connection;import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighting {

    private int animal_Id;
    private String location;
    private String rangerName;
    private int id;
    private Timestamp timeSeen;

    public Sighting(int animal_Id, String location, String rangerName) {
        this.animal_Id = animal_Id;
        this.location = location;
        this.rangerName = rangerName;

    }

    public int getAnimal_Id() {
        return animal_Id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimeSeen() {
        return timeSeen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return animal_Id == sighting.animal_Id &&
                id == sighting.id &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(rangerName, sighting.rangerName) &&
                Objects.equals(timeSeen, sighting.timeSeen);
    }



    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings;";
        try (Connection conn = DB.sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    public void save() {
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_Id, location, rangerName) VALUES (:animal_Id, :location, :rangerName);  ";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("animal_Id",this.animal_Id)
                    .addParameter("location",this.location)
                    .addParameter("rangerName",this.rangerName)
                    .executeUpdate()
                    .getKey();

        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id =:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    public void update(int id, int newAnimal_id, String newLocation, String newRangerName) {
        String sql = "UPDATE sightings SET (animal_Id,location,rangerName) = (:animal_Id, :location, :rangerName) WHERE id = :id";
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("animal_Id",newAnimal_id)
                    .addParameter("location",newLocation)
                    .addParameter("rangerName",newRangerName)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public void delete(){
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM sightings WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }
}
