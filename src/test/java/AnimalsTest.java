import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {

    @Rule
    public DataBaseRule database = new DataBaseRule();


    @Test
    public void Animals_instantiatesCorrectly() {
        Animals testAnimal = new Animals("Lion");
        assertTrue(testAnimal instanceof Animals);
    }

    @Test
    public void Animals_getsNameCorrectly_lion() {
        Animals testAnimal = new Animals("Lion");
        assertEquals("Lion",testAnimal.getName());
    }


    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Animals testAnimal = new Animals("Lion");
        Animals anotherTestAnimal = new Animals("Lion");
        assertTrue(testAnimal.equals(anotherTestAnimal));
    }

    @Test
    public void save_savesAnimalCorrectlyToDatabase_List() {
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();
        assertTrue(Animals.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimals() {
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();
        Animals savedAnimal = Animals.all().get(0);
        assertEquals(savedAnimal.getId(),testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimals() {
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();
        Animals testAnotherAnimal = new Animals("Snake");
        testAnotherAnimal.save();
        assertEquals(true,Animals.all().get(0).equals(testAnimal));
        assertEquals(true,Animals.all().get(1).equals(testAnotherAnimal));
    }

    @Test
    public void find_returnsAnimalsWithSameId_secondAnimal() {
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();
        Animals secondAnimal = new Animals("Snake");
        secondAnimal.save();
        assertEquals(Animals.find(secondAnimal.getId()),secondAnimal);
    }

    @Test
    public void update_changesNameAndType() {
        String initialName = "Lion";
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();

        testAnimal.update(testAnimal.getId(),"Snake");
        Animals updatedAnimal = Animals.find(testAnimal.getId());
        assertNotEquals(initialName,updatedAnimal.getName());
    }

    @Test
    public void delete_deletesAnimal_true() {
        Animals testAnimal = new Animals("Lion");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,Animals.find(testAnimal.getId()));
    }
}