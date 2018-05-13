package pl.zut.pswa.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.pswa.entity.User;
import pl.zut.pswa.enums.PersistenceUnitFactory;
import pl.zut.pswa.enums.Role;
import pl.zut.pswa.service.db.AuthDbService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class AuthDbServiceImpl
        extends AbstractDbServiceImpl<User> implements AuthDbService {

    private static final Logger logger = LoggerFactory.getLogger(UserDbServiceImpl.class);

    public AuthDbServiceImpl() {
        super(User.class);
    }

    @Override
    public User findByEmailPasswordAndRole(String email, String password, Role role) {

        long start = System.currentTimeMillis();
        try {
            logger.info("findByEmailPasswordAndRole(): email:{}, role:{}", email, role);
            StringBuilder hql =
                new StringBuilder("FROM User u WHERE ")
                    .append(" u.email = :email AND ")
                    .append(" u.password = :password AND ")
                    .append(" u.role = :role ");

            return getEntityManager()
                    .createQuery(hql.toString(), User.class)

                    .setParameter("email", email)
                    .setParameter("password", password)
                    .setParameter("role", role)

                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            logger.debug("findByEmailPasswordAndRole(): done in {}[ms]", (System.currentTimeMillis() - start ));
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
