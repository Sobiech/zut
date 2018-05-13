package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.jaxb.WorkerList;

import java.util.List;

public interface AbstractWorkerService
        extends AbstractDbService<AbstractWorker> {

    List<AbstractWorker> getList(Integer limit);

    List<AbstractWorker> getAll();

    WorkerList getWorkerList();

}
