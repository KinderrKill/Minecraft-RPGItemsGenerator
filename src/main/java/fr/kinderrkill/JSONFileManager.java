package fr.kinderrkill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JSONFileManager {

    public void test(String selectedItem, String fileName, String displayName, int levelObject, Map<String, String> mainArgs, List<String> lores) {
        JSONObject object = new JSONObject();
        object.put("Type", selectedItem);
        object.put("DisplayName", displayName);
        object.put("LevelObject", "§eNiveau d'objet: " + levelObject);

        JSONArray listMainArgs = new JSONArray();
        listMainArgs.add("");
        mainArgs.forEach((key, value) -> listMainArgs.add(key + ": " + value));

        object.put("MainArgs", listMainArgs);

        JSONArray list = new JSONArray();
        list.clear();
        list.add("");
        list.addAll(lores);

        object.put("Lore", list);

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("assets/results/");
        try (FileWriter file = new FileWriter(resource.getFile() + fileName + ".json")) {
            file.write(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
