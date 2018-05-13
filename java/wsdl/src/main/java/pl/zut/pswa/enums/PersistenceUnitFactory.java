package pl.zut.pswa.enums;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceUnitFactory {

    PSWA_UNIT("pswa");


    private final EntityManagerFactory emf;

    private final String persistenceUnitName;

    /**
     * Maps the persistence unit name to this instance of the enum.
     *
     * @param persistenceUnitName the name of the peristence unit in
     *                            persistence.xml
     *
     * @see #getPersistenceUnitName()
     */
    PersistenceUnitFactory(final String persistenceUnitName){
        this.persistenceUnitName = persistenceUnitName;
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    /**
     * Creates a new {@link EntityManager}.  NEVER share this across threads.
     * @return a new {@link EntityManager}
     */
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

}
