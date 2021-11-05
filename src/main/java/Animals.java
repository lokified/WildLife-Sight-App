public class Animals extends WildlifeAnimal {

    public static final String DATABASE_TYPE = "Not-endangered";

    public Animals(String name, int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
    }
}