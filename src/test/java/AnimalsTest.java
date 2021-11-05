import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {

    @Rule
    public DataBaseRule database = new DataBaseRule();


    @Test
    public void Animals_instantiatesCorrectly() {
        Animals testAnimal = new Animals("Lion",1);
        assertTrue(testAnimal instanceof Animals);
    }

    @Test
    public void Animals_getsNameCorrectly_lion() {
        Animals testAnimal = new Animals("Lion",1);
        assertEquals("Lion",testAnimal.getName());
    }

    @Test
    public void Animals_getsSightingIdCorrectly_1() {
        Animals testAnimal = new Animals("Lion",1);
        assertEquals(1,testAnimal.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animals testAnimal = new Animals("Lion",1);
        Animals anotherTestAnimal = new Animals("Lion",1);
        assertTrue(testAnimal.equals(anotherTestAnimal));
    }

    @Test
    public void save_savesAnimalCorrectlyToDatabase_List() {
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();
        assertTrue(Animals.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimals() {
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();
        Animals savedAnimal = Animals.all().get(0);
        assertEquals(savedAnimal.getId(),testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimals() {
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();
        Animals testAnotherAnimal = new Animals("Snake",2);
        testAnotherAnimal.save();
        assertEquals(true,Animals.all().get(0).equals(testAnimal));
        assertEquals(true,Animals.all().get(1).equals(testAnotherAnimal));
    }
}