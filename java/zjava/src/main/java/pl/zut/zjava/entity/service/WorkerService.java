package pl.zut.zjava.entity.service;

import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.commons.dto.WorkerListDto;

import java.util.List;

public interface WorkerService
        extends AbstractDbService<Worker> {

    List<Worker> getList(Integer limit);

    List<Worker> getAll();

    WorkerListDto getWorkerList();

}
