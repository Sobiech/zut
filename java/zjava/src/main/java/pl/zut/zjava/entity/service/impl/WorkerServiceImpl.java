package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.WorkerService;

import java.util.List;

public class WorkerServiceImpl
	extends AbstractDbServiceImpl<Worker> implements WorkerService {

    private static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    public WorkerServiceImpl(){
        super(Worker.class);
    }

    @Override
    public List<Worker> getWorkerList(Integer limit) {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM Worker w");
            return getEntityManager().createQuery(hql.toString(), Worker.class).setMaxResults(limit).getResultList();
        } finally {
            logger.debug("getWorkerList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
