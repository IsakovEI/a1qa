package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Objects;

public class DbUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtil.class);
    private static final String DB_URL = (String) Config.getParamValue("DB_URL");
    private static final String USER = (String) Config.getParamValue("USER");
    private static final String PASSWORD = (String) Config.getParamValue("PASSWORD");

    public static String[][] getResultArrayByQuery(String query) {
        String[][] responseResultsArray = null;
        try {
            ResultSet resultSet = Objects.requireNonNull(getStatement()).executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            resultSet.last();
            int rowCount = resultSet.getRow()+1;
            responseResultsArray = new String[rowCount][columnCount];
            resultSet.beforeFirst();

            for (int i = 0; i < columnCount; i++) {
                responseResultsArray[0][i] = metaData.getColumnLabel(i+1);
            }

            for (int i = 1; i < rowCount; i++) {
                resultSet.next();
                for (int j = 0; j < columnCount; j++) {
                    responseResultsArray[i][j] = String.valueOf(resultSet.getObject(j+1));
                }
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
        return responseResultsArray;
    }

    private static Statement getStatement() {
        if (DB_URL == null) {
            LOGGER.info("DB_URL = null");
            return null;
        }
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
}
