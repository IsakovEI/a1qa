package util;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    private static final File CONFIG_FILE = new File("src/test/resources/config.json");
    private static String configStringInJson;

    public static Object getParamValue(String paramName) {
        if (configStringInJson == null) {
            configStringInJson = IFileReader.readFile(CONFIG_FILE);
        }
        try {
            return new JSONObject(configStringInJson).get(paramName);
        } catch (JSONException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
}
