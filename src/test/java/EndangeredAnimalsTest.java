import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EndangeredAnimalsTest {

    @Rule
    public DataBaseRule database = new DataBaseRule();


    @Test
    public void EndangeredAnimals_instantiatesCorrectly() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertTrue(testEndangeredAnimals instanceof EndangeredAnimals);
    }

    @Test
    public void EndangeredAnimals_getsNameCorrectly_lion() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertEquals("White Rhino",testEndangeredAnimals.getName());
    }

    @Test
    public void EndangeredAnimals_getsSightingIdCorrectly_1() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertEquals(1,testEndangeredAnimals.getSighting_Id());
    }

    @Test
    public void EndangeredAnimals_getHealthyCorrectly_healthy() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertEquals("healthy",testEndangeredAnimals.getHealth());
    }

    @Test
    public void EndangeredAnimals_getsAgeCorrectly_old() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertEquals("adult",testEndangeredAnimals.getAge());
    }


    @Test
    public void equals_returnsTrueIfNameHealthAgeAndSightingIdAreSame_true() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        EndangeredAnimals anotherTestEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        assertTrue(testEndangeredAnimals.equals(anotherTestEndangeredAnimals));
    }

    @Test
    public void save_savesEndangeredAnimalsInDB_List() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",2);
        testEndangeredAnimals.save();
        assertTrue(EndangeredAnimals.all().get(0).equals(testEndangeredAnimals));
    }

    @Test
    public void all_returnsAllEndangeredAnimals() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        testEndangeredAnimals.save();
        EndangeredAnimals testAnotherEndangeredAnimals = new EndangeredAnimals("Polar Bear","ill","young",3);
        testAnotherEndangeredAnimals.save();

        assertTrue(EndangeredAnimals.all().get(0).equals(testEndangeredAnimals));
        assertTrue(EndangeredAnimals.all().get(1).equals(testAnotherEndangeredAnimals));

    }

    @Test
    public void find_returnsEndangeredAnimalsWithSameId_secondAnimal() {
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        testEndangeredAnimals.save();
        EndangeredAnimals testAnotherEndangeredAnimals = new EndangeredAnimals("Polar Bear","ill","young",3);
        testAnotherEndangeredAnimals.save();
        assertEquals(EndangeredAnimals.find(testAnotherEndangeredAnimals.getId()),testAnotherEndangeredAnimals);
    }

    @Test
    public void update_changesNameHealthAndAge() {
        String initialName = "White Rhino";
        EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("White Rhino","healthy","adult",1);
        testEndangeredAnimals.save();

        testEndangeredAnimals.update(testEndangeredAnimals.getId(),"Polar Bear",2,"ill","young");
        Animals updatedAnimal = Animals.find(testEndangeredAnimals.getId());
        assertNotEquals(initialName,updatedAnimal.getName());
    }
}