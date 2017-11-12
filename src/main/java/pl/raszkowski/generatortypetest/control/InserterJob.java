package pl.raszkowski.generatortypetest.control;

import java.util.concurrent.Callable;
import javax.ejb.EJB;

import lombok.Setter;
import pl.raszkowski.generatortypetest.entity.TestEntity;

public class InserterJob implements Callable<Void> {

    @EJB
    private Inserter inserter;

    @Setter
    private TestEntity testEntity;

    @Override
    public Void call() throws Exception {

        inserter.save(testEntity);

        return null;
    }
}
