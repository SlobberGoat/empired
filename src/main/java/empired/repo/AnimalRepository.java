package empired.repo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import empired.model.Animal;

import java.io.File;
import java.util.ArrayList;

@JsonAutoDetect
public class AnimalRepository {
  private static final String JSON_FILE = "game.json";

  @JsonProperty("animals")
  private ArrayList<Animal> animals = null;

  public AnimalRepository() {
    super();
  }

  public ArrayList<Animal> getAnimals() {
    //System.out.println("AnimalRepository.getAnimals");
    if (animals == null) {
      loadAnimals();
    }
    return animals;
  }

  public void setAnimals(ArrayList<Animal> animals) {
    this.animals = animals;
  }

  public void addAnimal(Animal animal) {
    if (animals == null) {
      loadAnimals();
    }
    animals.add(animal);
    showSize();
    writeAnimalsToJson();
  }


  private void loadAnimals() {
    File checkFile = new File(JSON_FILE);
    if(checkFile.exists()) {
      readAnimalsFromJson();
    } else {
      animals = new ArrayList<Animal>();
    }
  }

  private void showSize() {
    System.out.println("("+size()+ "animal(s) were loaded)");
  }

  public int size() {
    if (animals != null) {
      return animals.size();
    }
    return 0;
  }

  private void writeAnimalsToJson() {
    if(animals != null) {
      JsonHelper.writeAnimalRepoToJson(this, JSON_FILE);
    }
  }

  private void readAnimalsFromJson() {
     AnimalRepository loaded = JsonHelper.readAnimalRepoFromJson(JSON_FILE);
     this.setAnimals(loaded.getAnimals());
    System.out.println(size()+" animal(s) were loaded from file.\n");
  }
}
