package org.jazzteam.util;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryLoader {
    private static final String PATH = "src/main/resources/";
    private static final String FILE_NAME = "dictionary.json";
    private static final String KEY = "Key";
    private static final String VALUE = "Value";
    
    public static Map<Integer, String> load(DictionaryType type) {
        Map<Integer, String> dictionary = new TreeMap<>();
        try (InputStream stream = new FileInputStream(PATH + FILE_NAME);
             JsonReader reader = Json.createReader(stream)) {
            JsonArray array = reader.readObject().getJsonArray((String.valueOf(type)));
            for (JsonObject object : array.getValuesAs(JsonObject.class)) {
                dictionary.put(object.getInt(KEY), object.getString(VALUE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
