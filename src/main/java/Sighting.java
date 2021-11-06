import java.sql.Timestamp;
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

    @Override
    public int hashCode() {
        return Objects.hash(animal_Id, location, rangerName, id, timeSeen);
    }
}
