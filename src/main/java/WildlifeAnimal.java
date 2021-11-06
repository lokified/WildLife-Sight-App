import org.sql2o.Connection;
import java.util.Objects;

public abstract class WildlifeAnimal {

    public String name;
    public int sighting_Id;
    public int id;

    public String type;

    public String getName() {
        return name;
    }

    public int getSighting_Id() {
        return sighting_Id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildlifeAnimal that = (WildlifeAnimal) o;
        return sighting_Id == that.sighting_Id &&
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type);
    }



    public void delete(){
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }
}