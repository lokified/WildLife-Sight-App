import org.sql2o.Connection;
import java.util.Objects;

public abstract class WildlifeAnimal {

    public String name;
    public int sightingId;
    public int id;

    public String type;

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildlifeAnimal that = (WildlifeAnimal) o;
        return sightingId == that.sightingId &&
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type);
    }

    public void save() {
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,sightingId,type) VALUES (:name, :sightingId, :type)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("sightingId",this.sightingId)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
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