package util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class TestData {
    private static String testDataString;

    public static String getDate() {
        getTestDataString();
        return (String) getString("date");
    }

    public static String[] getBrowsers() {
        getTestDataString();
        JSONArray jsonArray = (JSONArray) getString("browsers");
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                result[i] = jsonArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
                result[i] = "can't parse jsonObject";
            }
        }
        return result;
    }

    private static void getTestDataString() {
        if (testDataString == null) {
            String fileSource = (String) Config.getParamValue("testDataFileSource");
            File testDataFile = new File(fileSource);
            testDataString = IFileReader.readFile(testDataFile);
        }
    }

    private static Object getString(String paramName) {
        try {
            return new JSONObject(testDataString).get(paramName);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
