package empired.game;

import java.util.ArrayList;
import java.util.Iterator;

import empired.model.Animal;

public class Deduce {

	public static void animalsWithParts(ArrayList<Animal> animalList, String bodyPart, boolean isaPartMatch) {
		Iterator<Animal> iterator = animalList.iterator();
		while (iterator.hasNext()) {
			Animal animal = iterator.next();
			if ((isaPartMatch && !animal.getBodyPart().equals(bodyPart))
					|| (!isaPartMatch && animal.getBodyPart().equals(bodyPart))) {
				iterator.remove();
			}
		}
	}

	public static void animalsWithSounds(ArrayList<Animal> animalList, String sound, boolean isSoundMatch) {
		Iterator<Animal> iterator = animalList.iterator();
		while (iterator.hasNext()) {
			Animal animal = iterator.next();
			if ((isSoundMatch && !animal.getSound().equals(sound))
					|| (!isSoundMatch && animal.getSound().equals(sound))) {
				iterator.remove();
			}
		}
	}

	public static void animalsWithColours(ArrayList<Animal> animalList, String colour, boolean isColourMatch) {
		Iterator<Animal> iterator = animalList.iterator();
		while (iterator.hasNext()) {
			Animal animal = iterator.next();
			if ((isColourMatch && !animal.getColour().equals(colour))
					|| (!isColourMatch && animal.getColour().equals(colour))) {
				iterator.remove();
			}
		}
	}

}
