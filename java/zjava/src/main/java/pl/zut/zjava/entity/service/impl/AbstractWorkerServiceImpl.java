package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.entity.service.AbstractWorkerService;
import pl.zut.zjava.jaxb.WorkerList;

import java.util.List;

public class AbstractWorkerServiceImpl
        extends AbstractDbServiceImpl<AbstractWorker> implements AbstractWorkerService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractWorkerServiceImpl.class);

    public AbstractWorkerServiceImpl(){
        super(AbstractWorker.class);
    }


    @Override
    protected Logger getLogger() {
        return logger;
    }


    @Override
    public List<AbstractWorker> getList(Integer limit) {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM AbstractWorker d");
            return getEntityManager().createQuery(hql.toString(), AbstractWorker.class).setMaxResults(limit).getResultList();

        } finally {
            logger.debug("getList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    public List<AbstractWorker> getAll() {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM AbstractWorker d");
            return getEntityManager().createQuery(hql.toString(), AbstractWorker.class).getResultList();

        } finally {
            logger.debug("getList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    public WorkerList getWorkerList() {
        return new WorkerList(getAll());
    }
}
