import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {
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
}