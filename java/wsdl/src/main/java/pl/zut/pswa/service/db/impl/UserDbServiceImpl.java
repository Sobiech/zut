package pl.zut.pswa.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.pswa.entity.User;
import pl.zut.pswa.service.db.UserDbService;
import pl.zut.pswa.enums.PersistenceUnitFactory;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UserDbServiceImpl
        extends AbstractDbServiceImpl<User> implements UserDbService {

    private static final Logger logger = LoggerFactory.getLogger(UserDbServiceImpl.class);

    public UserDbServiceImpl() {
        super(User.class);
    }


    @Override
    public List<User> findByFirstName(String firstName) {

        long start = System.currentTimeMillis();
        try {
            logger.info("findByFirstName(): by firstName:{}", firstName);

            return getEntityManager()
                    .createQuery("FROM User u WHERE u.firstName = :firstName", User.class)
                    .setParameter("firstName", firstName)
                    .getResultList();
        } finally {
            logger.debug("findByFirstName(): done in {}[ms]", (System.currentTimeMillis() - start ));
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        long start = System.currentTimeMillis();
        try {
            logger.info("findByLastName(): by lastName:{}", lastName);

            return getEntityManager()
                    .createQuery("FROM User u WHERE u.lastName = :lastName", User.class)
                    .setParameter("lastName", lastName)
                    .getResultList();
        } finally {
            logger.debug("findByLastName(): done in {}[ms]", (System.currentTimeMillis() - start ));
        }
    }

    @Override
    public User findByPhone(String phone) {

        long start = System.currentTimeMillis();
        try {

            logger.info("findByPhone(): by phone:{}", phone);

            return getEntityManager()
                .createQuery("FROM User u WHERE u.phone = :phone", User.class)
                .setParameter("phone", phone)
                .getSingleResult();
        } catch(NoResultException e) {
            return null;
        } finally {
            logger.debug("findByPhone(): done in {}[ms]", (System.currentTimeMillis() - start ));
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return PersistenceUnitFactory.PSWA_UNIT.createEntityManager();
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }


}
