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
        assertEquals(1,testAnimal.getSighting_Id());
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

    @Test
    public void find_returnsAnimalsWithSameId_secondAnimal() {
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();
        Animals secondAnimal = new Animals("Snake",2);
        secondAnimal.save();
        assertEquals(Animals.find(secondAnimal.getId()),secondAnimal);
    }

    @Test
    public void update_changesNameAndType() {
        String initialName = "Lion";
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();

        testAnimal.update(testAnimal.getId(),"Snake",1);
        Animals updatedAnimal = Animals.find(testAnimal.getId());
        assertNotEquals(initialName,updatedAnimal.getName());
    }

    @Test
    public void delete_deletesAnimal_true() {
        Animals testAnimal = new Animals("Lion",1);
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,Animals.find(testAnimal.getId()));
    }
}