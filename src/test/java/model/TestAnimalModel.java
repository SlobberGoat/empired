package model;

import empired.model.Animal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAnimalModel {
  private String animalType = "Animal Type";
  private String animalPart = "Animal Part";
  private String animalSound = "Animal Sound";
  private String animalColour = "Animal Colour";

  private Animal testAnimal = null;

  @Before
  public void initialise() {
    testAnimal = new Animal(animalType, animalPart, animalSound, animalColour);
  }

  @Test
  public void animalPropertiesShouldMatch() {
    assertEquals(animalType, testAnimal.getType());
    assertEquals(animalPart, testAnimal.getBodyPart());
    assertEquals(animalSound, testAnimal.getSound());
    assertEquals(animalColour, testAnimal.getColour());
  }

}
