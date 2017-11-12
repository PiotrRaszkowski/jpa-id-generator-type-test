package pl.raszkowski.generatortypetest.control;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import pl.raszkowski.generatortypetest.entity.MultipleTestsExecutionResult;
import pl.raszkowski.generatortypetest.entity.TestExecutionResult;

public class TestResultsJsonFormatter {

    public JsonObject buildResult(MultipleTestsExecutionResult multipleTestsExecutionResult) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Total Entries", multipleTestsExecutionResult.getTotalEntries());
        objectBuilder.add("Repeted", multipleTestsExecutionResult.getRepeat());
        objectBuilder.add("Entries per test", multipleTestsExecutionResult.getTotalEntries() / multipleTestsExecutionResult.getRepeat());
        objectBuilder.add("Average execution time [milis]", multipleTestsExecutionResult.getAverageExecutionTimeInMilis());
        objectBuilder.add("Average execution time [s]", multipleTestsExecutionResult.getAverageExecutionTimeInSeconds());
        objectBuilder.add("Total execution time [milis]", multipleTestsExecutionResult.getTotalExecutionTimeInMilis());
        objectBuilder.add("Total execution time [s]", multipleTestsExecutionResult.getTotalExecutionTimeInSeconds());

        JsonArray testsResults = buildTestsResults(multipleTestsExecutionResult);

        objectBuilder.add("Tests", testsResults);

        JsonArray allExecutionTimesInSeconds = buildAllExecutionTimesInSeconds(multipleTestsExecutionResult);

        objectBuilder.add("All Execution times [s]", allExecutionTimesInSeconds);

        return objectBuilder.build();
    }

    private JsonArray buildTestsResults(MultipleTestsExecutionResult multipleTestsExecutionResult) {
        JsonArrayBuilder testResultsArrayBuilder = Json.createArrayBuilder();
        int testNumber = 1;
        for (TestExecutionResult testExecutionResult : multipleTestsExecutionResult.getResults()) {
            JsonObjectBuilder singleTestObjectBuilder = Json.createObjectBuilder();
            singleTestObjectBuilder.add("Test number", testNumber++);
            singleTestObjectBuilder.add("Entries", testExecutionResult.getTestSize());
            singleTestObjectBuilder.add("Execution time [milis]", testExecutionResult.getExecutionTimeInMilis());
            singleTestObjectBuilder.add("Execution time [s]", testExecutionResult.getExecutionTimeInSeconds());

            testResultsArrayBuilder.add(singleTestObjectBuilder.build());
        }
        return testResultsArrayBuilder.build();
    }

    private JsonArray buildAllExecutionTimesInSeconds(MultipleTestsExecutionResult multipleTestsExecutionResult) {
        JsonArrayBuilder testsResultsArrayBuilder = Json.createArrayBuilder();
        for (TestExecutionResult testExecutionResult : multipleTestsExecutionResult.getResults()) {
            testsResultsArrayBuilder.add(testExecutionResult.getExecutionTimeInSeconds());
        }
        return testsResultsArrayBuilder.build();
    }
}
