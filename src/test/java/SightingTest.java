import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Test
    public void Sighting_instantiatesCorrectly() {
        Sighting testSight = new Sighting(001,"Zone A","Songyam");
        assertTrue(testSight instanceof Sighting);
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithAnimal_Id() {
        Sighting testSight = new Sighting(001,"Zone A","Songyam");
        assertEquals(001,testSight.getAnimal_Id());
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithLocation (){
        Sighting testSight = new Sighting(001,"Zone A","Songyam");
        assertEquals("Zone A",testSight.getLocation());
    }

    @Test
    public void Sighting_instantiatesCorrectlyWithRangerName() {
        Sighting testSight = new Sighting(001,"Zone A","Songyam");
        assertEquals("Songyam",testSight.getRangerName());
    }

    @Test
    public void equals_returnTrueIfAnimalIdLocationAndRangerNameAreEqual_true() {
        Sighting testSight = new Sighting(001,"Zone A","Songyam");
        Sighting testAnotherSight = new Sighting(001,"Zone A","Songyam");
        assertTrue(testSight.equals(testAnotherSight));
    }
}