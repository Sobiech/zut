package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.WorkerService;
import pl.zut.zjava.commons.dto.WorkerListDto;

import java.util.List;

public class WorkerServiceImpl
        extends AbstractDbServiceImpl<Worker> implements WorkerService {

    private static final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    public WorkerServiceImpl(){
        super(Worker.class);
    }


    @Override
    protected Logger getLogger() {
        return logger;
    }


    @Override
    public List<Worker> getList(Integer limit) {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM Worker d");
            return getEntityManager().createQuery(hql.toString(), Worker.class).setMaxResults(limit).getResultList();

        } finally {
            logger.debug("getList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    public List<Worker> getAll() {

        long start = System.currentTimeMillis();
        try {

            StringBuilder hql = new StringBuilder("FROM Worker d");
            return getEntityManager().createQuery(hql.toString(), Worker.class).getResultList();

        } finally {
            logger.debug("getList(): done in :{}[ms]", (System.currentTimeMillis() - start));
        }
    }


    @Override
    public WorkerListDto getWorkerList() {
        return new WorkerListDto(getAll());
    }
}
