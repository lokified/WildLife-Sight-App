import org.sql2o.Connection;
import java.util.Objects;

public abstract class WildlifeAnimal {

    public String name;
    public int id;

    public String type;

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildlifeAnimal that = (WildlifeAnimal) o;
        return
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type);
    }

    public void save() {
        try(Connection conn = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id = (int) conn.createQuery(sql,true)
                    .addParameter("name",this.name)
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