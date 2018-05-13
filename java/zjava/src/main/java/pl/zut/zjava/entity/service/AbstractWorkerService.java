package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.AbstractWorker;

import java.util.List;

public interface AbstractWorkerService
        extends AbstractDbService<AbstractWorker> {

    List<AbstractWorker> getList(Integer limit);

}
