package pl.raszkowski.generatortypetest.control;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import pl.raszkowski.generatortypetest.entity.MultipleTestsExecutionResult;
import pl.raszkowski.generatortypetest.entity.TestEntity;
import pl.raszkowski.generatortypetest.entity.TestExecutionResult;

@Slf4j
public class MultipleTestsExecutor {

    @Inject
    private TestExecutor testExecutor;

    public MultipleTestsExecutionResult execute(Class<? extends TestEntity> testEntityClass, Long entriesPerTest, int repeat) {
        log.info("Executing tests for entity = {}, entries = {}, repeat = {}.", testEntityClass.getSimpleName(), entriesPerTest, repeat);

        MultipleTestsExecutionResult multipleTestsExecutionResult = new MultipleTestsExecutionResult(repeat);
        for (int i = 0; i< repeat; i++) {
            log.info("Running test no = {}.", i+1);
            TestExecutionResult testExecutionResult = testExecutor.executeTests(testEntityClass, entriesPerTest);
            multipleTestsExecutionResult.addResult(testExecutionResult);
            log.info("Test no = {} finished. Execution time [milis] = {}.", i+1, testExecutionResult.getExecutionTimeInMilis());
        }
        return multipleTestsExecutionResult;
    }
}
