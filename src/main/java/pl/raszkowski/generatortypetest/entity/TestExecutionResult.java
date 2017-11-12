package pl.raszkowski.generatortypetest.entity;

import java.time.Duration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestExecutionResult {

    private Duration executionTime;

    private Long testSize;

    public Long getExecutionTimeInMilis() {
        return executionTime.toMillis();
    }

    public Long getExecutionTimeInSeconds() {
        return executionTime.getSeconds();
    }
}
