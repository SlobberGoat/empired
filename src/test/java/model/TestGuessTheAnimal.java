package model;

import empired.game.GuessTheAnimal;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGuessTheAnimal {

    @Test
    public void testTraitRandomness() {
        // the traits index that we desire are:
        //  1 = animal body part,
        //  2 = animal sound,
        //  3 = animal colour
        for(int i=0; i < 100; i++) {
            int result = GuessTheAnimal.randomTrait();
            assertTrue(result >  0);
            assertTrue(result <  4);
        }
    }

    @Test
    public void testAnimalSizeRandomness() {
        int testAnimalArraySize = 5;
        for(int i=0; i < 100; i++) {
            int result = GuessTheAnimal.randomAnimal(testAnimalArraySize);
            assertTrue(result >=  0);
            assertTrue(result <  testAnimalArraySize);
        }
    }

}
