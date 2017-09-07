package empired.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Animal {
  private String type;
  private String bodyPart;
  private String sound;
  private String colour;

  @JsonCreator
  public Animal(@JsonProperty("type") String type, @JsonProperty("bodyPart") String bodyPart, @JsonProperty("sound") String sound,  @JsonProperty("colour") String colour) {
    super();
    this.type = type;
    this.bodyPart = bodyPart;
    this.sound = sound;
    this.colour = colour;
  }

  public Animal(Animal animal) {
    this.type = animal.type;
    this.bodyPart = animal.bodyPart;
    this.sound = animal.sound;
    this.colour = animal.colour;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBodyPart() {
    return bodyPart;
  }

  public void setBodyPart(String bodyPart) {
    this.bodyPart = bodyPart;
  }

  public String getSound() {
    return sound;
  }

  public void setSound(String sound) {
    this.sound = sound;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

}
