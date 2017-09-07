package empired.repo;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;

public class JsonHelper {

  public static void writeAnimalRepoToJson(AnimalRepository repo, String filename) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    File fileOut = new File(filename);
    if(fileOut.exists()) {
      fileOut.delete();
    }
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileOut, true)));
      writer.writeValue(out, repo);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static AnimalRepository  readAnimalRepoFromJson(String filename) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      FileInputStream fos = new FileInputStream(new File(filename));
      return  mapper.readValue(fos, AnimalRepository.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
