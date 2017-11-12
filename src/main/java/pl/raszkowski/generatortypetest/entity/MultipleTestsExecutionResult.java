package pl.raszkowski.generatortypetest.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleTestsExecutionResult {

    private int repeat;

    private BigDecimal averageExecutionTimeInMilis = BigDecimal.ZERO;

    private BigDecimal totalExecutionTimeInMilis = BigDecimal.ZERO;

    private Long totalEntries = 0L;

    private List<TestExecutionResult> results = new ArrayList<>();

    public MultipleTestsExecutionResult(int repeat) {
        this.repeat = repeat;
    }

    public void addResult(TestExecutionResult testExecutionResult) {
        Long executionTimeInMilis = testExecutionResult.getExecutionTimeInMilis();

        averageExecutionTimeInMilis = averageExecutionTimeInMilis.add(new BigDecimal(executionTimeInMilis).divide(new BigDecimal(repeat), 2, BigDecimal.ROUND_HALF_UP));
        totalExecutionTimeInMilis = totalExecutionTimeInMilis.add(new BigDecimal(executionTimeInMilis));

        totalEntries += testExecutionResult.getTestSize();

        results.add(testExecutionResult);
    }

    public Long getAverageExecutionTimeInMilis() {
        return averageExecutionTimeInMilis.longValue();
    }

    public Long getAverageExecutionTimeInSeconds() {
        return Duration.ofMillis(averageExecutionTimeInMilis.longValue()).getSeconds();
    }

    public Long getTotalExecutionTimeInMilis() {
        return totalExecutionTimeInMilis.longValue();
    }

    public Long getTotalExecutionTimeInSeconds() {
        return Duration.ofMillis(totalExecutionTimeInMilis.longValue()).getSeconds();
    }
}
