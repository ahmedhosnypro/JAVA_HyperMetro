package metro;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonMetroParser {

    private JsonMetroParser() {
    }

    public static MetroSystem parseJsonMetroSystem(String fileName) {

        Gson gson = new Gson();
        MetroSystem metroSystem = new MetroSystem();
        File file = new File(fileName);
        if (file.exists()) {
            Path path = Paths.get(fileName);
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

                JsonElement jsonElement = JsonParser.parseReader(reader);

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                for (var line : jsonObject.entrySet()) {
                    MetroLine metroLine = new MetroLine(line.getKey());
                    for (var station : line.getValue().getAsJsonObject().keySet()) {
                        Station s = gson.fromJson(line.getValue().getAsJsonObject().get(station)
                                .getAsJsonObject().toString(), Station.class);
                        s.setPosition(Integer.parseInt(station));
                        metroLine.addStation(s);
                    }
                    metroSystem.getLines().add(metroLine);
                }
            } catch (Exception e) {
                System.err.println("Incorrect File");
            }
        } else {
            System.out.println("Error! Such a file doesn't exist!");
            return null;
        }
        return metroSystem;
    }
}
