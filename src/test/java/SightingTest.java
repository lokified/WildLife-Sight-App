import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DataBaseRule database = new DataBaseRule();

    @Test
    public void Sighting_instantiatesCorrectly() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        assertTrue(testSight instanceof Sighting);
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithAnimal_Id() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        assertEquals("Lion",testSight.getAnimal_Id());
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithLocation (){
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        assertEquals("Zone A",testSight.getLocation());
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithRangerName() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        assertEquals("Songyam",testSight.getRangerName());
    }

    @Test
    public void equals_returnTrueIfAnimalIdLocationAndRangerNameAreEqual_true() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        Sighting testAnotherSight = new Sighting("Lion","Zone A","Songyam");
        assertTrue(testSight.equals(testAnotherSight));
    }

    @Test
    public void save_savesSightingsToDB_List() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();
        assertTrue(Sighting.all().get(0).equals(testSight));
    }

    @Test
    public void all_returnsAllInstancesOfAnimals() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();
        Sighting testAnotherSight = new Sighting("Zebra","Zone B","Cho");
        testAnotherSight.save();

        assertEquals(true,Sighting.all().get(0).equals(testSight));
        assertEquals(true,Sighting.all().get(1).equals(testAnotherSight));
    }

    @Test
    public void save_assignsIdToSightings() {

        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();
        Sighting savedSight = Sighting.all().get(0);
        assertEquals(savedSight.getId(),testSight.getId());
    }

    @Test
    public void find_returnsSightingsWithSameId_testAnotherSight() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();
        Sighting testAnotherSight = new Sighting("Zebra","Zone B","Cho");
        testAnotherSight.save();
        assertEquals(Sighting.find(testAnotherSight.getId()),testAnotherSight);
    }

    @Test
    public void update_changesAnimalIdLocationAndRangerName() {
        String initialLocation = "Zone A";
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();

        testSight.update(testSight.getId(),"Zebra","Zone B","Cho");
        Sighting updatedSight = Sighting.find(testSight.getId());
        assertNotEquals(initialLocation,updatedSight.getLocation());
    }

    @Test
    public void delete_deletesSIGHTINGS_true() {
        Sighting testSight = new Sighting("Lion","Zone A","Songyam");
        testSight.save();
        testSight.delete();
        assertEquals(null,Sighting.find(testSight.getId()));
    }
}