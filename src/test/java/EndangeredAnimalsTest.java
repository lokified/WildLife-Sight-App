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
        assertEquals(1,testEndangeredAnimals.getSightingId());
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

}