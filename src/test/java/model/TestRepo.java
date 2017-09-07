package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import empired.model.Animal;
import empired.repo.AnimalRepository;
import empired.repo.JsonHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TestRepo {
  private ObjectMapper mapper = null;
  private Animal elephant = new Animal("elephant", "trunk", "trumpets", "grey");
  private Animal lion = new Animal("lion", "mane", "roars", "yellow");

  @Before
  public void start() {
    mapper = new ObjectMapper();
    mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
  }

  @Test
  public void testReadingAnimalsFromJson() {
    InputStream is = TestRepo.class.getResourceAsStream("/test.json");
    try {
      AnimalRepository test = mapper.readValue(is, AnimalRepository.class);
      assertEquals(2, test.size());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testWritingAnimalRepoToJson() {
    String filename = "animals-write-test.json";
    AnimalRepository animalRepo = new AnimalRepository();
    animalRepo.addAnimal(elephant);
    animalRepo.addAnimal(lion);
    JsonHelper.writeAnimalRepoToJson(animalRepo,filename);

    File fileCheck = new File(filename);
    assertTrue(fileCheck.exists());
    fileCheck.delete();
    assertFalse(fileCheck.exists());
  }
}
