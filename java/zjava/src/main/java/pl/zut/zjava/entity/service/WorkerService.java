package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Worker;

import java.util.List;

public interface WorkerService extends AbstractDbService<Worker> {

    List<Worker> getWorkerList(Integer limit);
}
