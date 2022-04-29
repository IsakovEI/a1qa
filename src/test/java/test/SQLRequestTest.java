package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import util.DbRequestService;
import util.DbUtil;
import util.IWriter;
import util.TestData;

import java.util.Arrays;

public class SQLRequestTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLRequestTest.class);

    @Test
    public void dbRequestsTest() {

        LOGGER.info("Step 1. Get minimum working time for each test. Sort by projects and tests into projects.");
        IWriter.printResult(DbUtil.getResultArrayByQuery(DbRequestService.getSelectTestByMinWorkingTime()));

        LOGGER.info("Step 2. Get all projects with the number of unique tests.");
        IWriter.printResult(DbUtil.getResultArrayByQuery(DbRequestService.getSelectUniqueTestsCountByProject()));

        LOGGER.info("Step 3. Get tests for each project  whom has been carried out after " + TestData.getDate() + ". Sort by projects and tests into projects.");
        IWriter.printResult(DbUtil.getResultArrayByQuery(DbRequestService.getSelectTestsExecutedAfter()));

        LOGGER.info("Step 4. Get the number of tests that had carried out with: " + Arrays.toString(TestData.getBrowsers()));
        IWriter.printResult(DbUtil.getResultArrayByQuery(DbRequestService.getSelectTestsExecutedWithFirefoxAndChrome()));
    }
}
