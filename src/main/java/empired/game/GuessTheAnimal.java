package empired.game;

import empired.model.Animal;
import empired.repo.AnimalRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GuessTheAnimal {
  private String playerName = null;
  private BufferedReader reader = null;
  private AnimalRepository animalRepository = null;
  private boolean userStillWantsToPlay = true;

  public GuessTheAnimal() {
    init();
    while (userStillWantsToPlay) {
      showMainMenu();
    }
  }


  private void showMainMenu() {
    tell(playerName + ", here are your options:");
    tell("<a> Add another animal?\n<p> Play Guess The Animal?\n<q> Quit the game.");
    String answer = ask("What would you like to do? a/p/q ?");
    switch (answer.charAt(0)) {
      case 'a':
        notEnoughAnimals();
        break;
      case 'p':
        playGame();
        break;
      case 'q':
        userStillWantsToPlay = false;
        break;
      default:
        tell("Sorry, but I don't understand that option. Please select again.");
        break;
    }
  }

  private void playGame() {
    pickTheAnimal(animalRepository.getAnimals());
    guessTheAnimal(animalRepository.getAnimals());
  }

  private void pickTheAnimal(ArrayList<Animal> animals) {
    do {
      tell("Look at the following list of animals");
      for (Animal currentAnimal : animals) {
        tellAnimal(currentAnimal);
      }
      tell("Pick one animal (and don't tell anyone!) and I'll try to guess which one it is!");
    } while (!confirm("Have you chosen an animal yet?"));
  }


  private void tellAnimal(Animal animal) {
    StringBuffer sb = new StringBuffer("\t* ").append(animal.getType()).append("; ").append("has a ");
    sb.append(animal.getBodyPart()).append(", ").append(animal.getSound()).append(" and is ").append(animal.getColour()).append(".");
    tell(sb.toString());
  }

  private void guessTheAnimal(ArrayList<Animal> animals) {
    ArrayList<Animal> crossOut = cloneList(animals);
    boolean guessed;

    do {
      Animal randomAnimal = crossOut.get(randomAnimal(crossOut.size()));
      guessed = queryRandomTrait(crossOut, randomAnimal);
    } while (!guessed && crossOut.size() > 0);
  }


  private boolean queryRandomTrait(ArrayList<Animal> animalList, Animal randomAnimal) {
    int randomTrait = randomTrait();
    switch (randomTrait) {
      case 1:
        String bodyPart = randomAnimal.getBodyPart();
        boolean isaBodyMatch = quizBodyPart(bodyPart);
        Deduce.animalsWithParts(animalList, bodyPart, isaBodyMatch);
        break;
      case 2:
        String sound = randomAnimal.getSound();
        boolean isaSoundMatch = quizSound(sound);
        Deduce.animalsWithSounds(animalList, sound, isaSoundMatch);
        break;
      case 3:
        String colour = randomAnimal.getColour();
        boolean isaColourMatch = quizColour(colour);
        Deduce.animalsWithColours(animalList, colour, isaColourMatch);
        break;
      default:
        break;
    }
    if (animalList.size() == 1) {
      tell("I know what your animal is!\n It's a " + animalList.get(0).getType() + "!!\n");
      return true;
    }
    return false;
  }


  private boolean quizColour(String colour) {
    StringBuffer sb = new StringBuffer("Is your animal ").append(colour).append(" in colour?");
    return confirm(sb.toString());
  }

  private boolean quizSound(String sound) {
    StringBuffer sb = new StringBuffer("Does your animal ").append(sound).append("?");
    return confirm(sb.toString());
  }

  private boolean quizBodyPart(String bodyPart) {
    StringBuffer sb = new StringBuffer("Does your animal have a ").append(bodyPart).append("?");
    return confirm(sb.toString());
  }


  public static int randomTrait() {
    // return a random animal trait index
    return random(1, 3);
  }

  public static int randomAnimal(int animalCount) {
    // return a random animal entry index
    return random(0, animalCount - 1);
  }

  private static int random(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }


  private void init() {
    animalRepository = new AnimalRepository();
    reader = new BufferedReader(new InputStreamReader(System.in));

    tell("Welcome to Guess The Animal.");
    animalRepository.getAnimals();

    if (playerName == null) {
      playerName = getPlayerName();
    }

    while (animalRepository.size() < 2) {
      notEnoughAnimals();
    }
  }

  public String getPlayerName() {
    String playerName = ask("Before we begin, can I have your name?");
    return playerName.trim();
  }

  public void notEnoughAnimals() {
    if (animalRepository.size() == 0) {
      tell("Hi " + playerName + ", we don't have enough animals to play the game yet, so lets add an animal now!");
    } else if (animalRepository.size() < 2) {
      tell("Sorry " + playerName + ", but we still need you to enter another animal before we can start playing.");
    }
    queryUserForNewAnimal();
  }

  private void queryUserForNewAnimal() {
    String type, body, sound, colour;

    do {
      type = ask("Please enter a the type of animal (examples: cat, dog, horse)");
      body = ask("Does this animal have a special body part? (examaples: beak, wings, claws)");
      sound = ask("Does this animal make a certain sound? (examples: roar, quack, bark)");
      colour = ask("What is the main colour of this animal? (examples: brown, white, grey)");
    } while (!confirm("Are you happy with the details you entered?"));

    // lets add the new animal
    animalRepository.addAnimal(new Animal(type, body, sound, colour));
  }

  private boolean confirm(String message) {
    System.out.println(message + " y/n?");
    try {
      String answer = reader.readLine();
      if ("y".equalsIgnoreCase(answer)) {
        return true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }


  private String ask(String message) {
    System.out.println(message);
    try {
      return reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void tell(String message) {
    System.out.println(message);
  }


  private ArrayList<Animal> cloneList(List<Animal> list) {
    ArrayList<Animal> clone = new ArrayList<Animal>(list.size());
    for (Animal item : list) {
      clone.add(new Animal(item));
    }
    return clone;
  }

  public static void main(String[] args) {
    new GuessTheAnimal();
  }
}
