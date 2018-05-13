package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.Director;
import pl.zut.zjava.entity.Trader;
import pl.zut.zjava.entity.service.DirectorService;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class DirectorServiceImpl
	extends AbstractDbServiceImpl<Director> implements DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    public DirectorServiceImpl() {
        super(Director.class);
    }

    @Override
    public List<Director> getDirectorList(Integer limit) {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM Director d");
            return getEntityManager().createQuery(hql.toString(), Director.class).setMaxResults(limit).getResultList();

        } finally {
            logger.debug("getDirectorList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    protected Logger getLogger() {
        return logger;
    }
}
