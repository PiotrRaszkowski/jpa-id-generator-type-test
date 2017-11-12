package pl.raszkowski.generatortypetest.control;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.raszkowski.generatortypetest.entity.TestEntity;

@Stateless
public class Inserter {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save(TestEntity testEntity) {
        entityManager.persist(testEntity);
    }
}
