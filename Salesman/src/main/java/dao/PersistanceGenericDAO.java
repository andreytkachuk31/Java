package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Andrii_Tkachuk
 * @since 1/8/2015
 */
public abstract class PersistanceGenericDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("transactions-optional");

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    protected void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
