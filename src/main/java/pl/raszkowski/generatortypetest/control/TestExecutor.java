package pl.raszkowski.generatortypetest.control;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import pl.raszkowski.generatortypetest.entity.TestEntity;
import pl.raszkowski.generatortypetest.entity.TestExecutionResult;

public class TestExecutor {

    @Resource(name = "test-MES")
    private ManagedExecutorService managedExecutorService;

    @Inject
    private Instance<InserterJob> inserterJobInstance;

    public TestExecutionResult executeTests(Class<? extends TestEntity> testEntityClass, Long entriesToInsert) {
        Instant startTime = Instant.now();

        Set<Future<Void>> results = new HashSet<>();

        for (int i = 0; i < entriesToInsert; i++) {
            Future<Void> result = execute(testEntityClass);

            results.add(result);
        }

        waitForResults(results);

        return TestExecutionResult.builder()
                .executionTime(Duration.between(startTime, Instant.now()))
                .testSize(entriesToInsert)
                .build();
    }

    private Future<Void> execute(Class<? extends TestEntity> testEntityClass) {
        TestEntity testEntity = buildEntity(testEntityClass);

        InserterJob inserterJob = inserterJobInstance.get();
        inserterJob.setTestEntity(testEntity);

        return managedExecutorService.submit(inserterJob);
    }

    private TestEntity buildEntity(Class<? extends TestEntity> testEntityClass) {
        try {
            return testEntityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("Unable to create new instance of {0}.", testEntityClass.getSimpleName()), e);
        }
    }

    private void waitForResults(Set<Future<Void>> results) {
        for (Future<?> result : results) {
            try {
                result.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
