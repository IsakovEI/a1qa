package util;

public class DbRequestService {
    private static final String SELECT_TEST_BY_MIN_WORKING_TIME = "SELECT distinctrow\n" +
            "project.name AS 'PROJECT',\n" +
            "t.name AS 'TEST',\n" +
            "MIN_WORKING_TIME\n" +
            "FROM (SELECT\n" +
            "test.project_id,\n" +
            "test.name,\n" +
            "min(test.end_time - test.start_time) AS 'MIN_WORKING_TIME'\n" +
            "FROM test\n" +
            "GROUP BY test.name)t\n" +
            "LEFT JOIN project ON project.id = t.project_id\n" +
            "ORDER BY PROJECT, TEST";

    private static final String SELECT_UNIQUE_TESTS_COUNT_BY_PROJECT = "SELECT \n" +
            "project.name AS 'PROJECT',\n" +
            "COUNT(test.name) AS 'TEST_COUNT'\n" +
            "FROM test\n" +
            "LEFT JOIN project ON project.id = test.project_id\n" +
            "GROUP BY project_id\n" +
            "ORDER BY PROJECT";

    public static String getSelectTestByMinWorkingTime() {
        return SELECT_TEST_BY_MIN_WORKING_TIME;
    }

    public static String getSelectUniqueTestsCountByProject() {
        return SELECT_UNIQUE_TESTS_COUNT_BY_PROJECT;
    }

    public static String getSelectTestsExecutedAfter() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "SELECT \n" +
                "project.name AS 'PROJECT',\n" +
                "test.name AS 'TEST',\n" +
                "test.start_time AS 'DATE'\n" +
                "FROM test\n" +
                "LEFT JOIN project ON test.project_id = project.id\n" +
                "WHERE start_time > '");
        sb.append(TestData.getDate());
        sb.append("'\nORDER BY PROJECT, TEST, DATE ");
        return sb.toString();
    }

    public static String getSelectTestsExecutedWithFirefoxAndChrome() {
        String[] browsers = TestData.getBrowsers();

        StringBuilder sb = new StringBuilder();
        boolean isFirstBrowser = true;
        for (String browser : browsers) {
            if (!isFirstBrowser) {
                sb.append("\nunion\n");

            }
            isFirstBrowser = false;
            sb.append("\n");
            sb.append(
                    "SELECT COUNT(*) AS 'BROWSERS'\n" +
                    "FROM test\n" +
                    "WHERE browser = ");
            sb.append(browser);
        }
        return sb.toString();
    }
}
