package org.jazzteam;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryLoader {
    private static final String FILENAME = "src/main/resources/dictionary.json";
    private static final String KEY = "key";
    private static final String VALUE = "value";
    
    public static Map<Integer, String> loadDictionary(NumbersEnum numbersEnum) {
        Map<Integer, String> treeMap = new TreeMap<>();
        JsonArray jsonArray = null;
        try(InputStream fis = new FileInputStream(FILENAME)) {
            jsonArray = Json.createReader(fis).readObject().getJsonArray(String.valueOf(numbersEnum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) {
            for (JsonObject object : jsonArray.getValuesAs(JsonObject.class)) {
                treeMap.put(object.getInt(KEY), object.getString(VALUE));
            }
        }
        return treeMap;
    }
}
