package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class IFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(IFileReader.class);

    public static String readFile(File file) {
            StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return sb.toString();
    }
}
