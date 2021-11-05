import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimals extends WildlifeAnimal {

    public static final String DATABASE_TYPE = "endangered";
    private String health;
    private String age;

    public EndangeredAnimals(String name, String health, String age, int sightingId) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.sightingId = sightingId;
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


}
