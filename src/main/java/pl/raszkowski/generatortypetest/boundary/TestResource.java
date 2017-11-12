package pl.raszkowski.generatortypetest.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.raszkowski.generatortypetest.control.MultipleTestsExecutor;
import pl.raszkowski.generatortypetest.control.TestResultsJsonFormatter;
import pl.raszkowski.generatortypetest.entity.AutoGeneratorTestEntity;
import pl.raszkowski.generatortypetest.entity.IdentityGeneratorTestEntity;
import pl.raszkowski.generatortypetest.entity.MultipleTestsExecutionResult;
import pl.raszkowski.generatortypetest.entity.SequenceGeneratorTestEntity;
import pl.raszkowski.generatortypetest.entity.TableGeneratorTestEntity;

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @Inject
    private MultipleTestsExecutor multipleTestsExecutor;

    @Inject
    private TestResultsJsonFormatter testResultsJsonFormatter;

    @GET
    @Path("identityGenerator/{entries}/{repeat}")
    public JsonObject insertIdentityGenerator(@PathParam("entries") Long entries, @PathParam("repeat") int repeat) {
        MultipleTestsExecutionResult multipleTestsExecutionResult = multipleTestsExecutor.execute(IdentityGeneratorTestEntity.class, entries, repeat);

        return testResultsJsonFormatter.buildResult(multipleTestsExecutionResult);
    }

    @GET
    @Path("tableGenerator/{entries}/{repeat}")
    public JsonObject insertTableGenerator(@PathParam("entries") Long entries, @PathParam("repeat") int repeat) {
        MultipleTestsExecutionResult multipleTestsExecutionResult = multipleTestsExecutor.execute(TableGeneratorTestEntity.class, entries, repeat);

        return testResultsJsonFormatter.buildResult(multipleTestsExecutionResult);
    }

    @GET
    @Path("sequenceGenerator/{entries}/{repeat}")
    public JsonObject insertSequenceGenerator(@PathParam("entries") Long entries, @PathParam("repeat") int repeat) {
        MultipleTestsExecutionResult multipleTestsExecutionResult = multipleTestsExecutor.execute(SequenceGeneratorTestEntity.class, entries, repeat);

        return testResultsJsonFormatter.buildResult(multipleTestsExecutionResult);
    }

    @GET
    @Path("autoGenerator/{entries}/{repeat}")
    public JsonObject insertAutoGenerator(@PathParam("entries") Long entries, @PathParam("repeat") int repeat) {
        MultipleTestsExecutionResult multipleTestsExecutionResult = multipleTestsExecutor.execute(AutoGeneratorTestEntity.class, entries, repeat);

        return testResultsJsonFormatter.buildResult(multipleTestsExecutionResult);
    }
}
