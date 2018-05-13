package pl.zut.zjava.server.processor;

import pl.zut.zjava.entity.AbstractWorker;
import pl.zut.zjava.entity.service.impl.AbstractWorkerServiceImpl;

import java.util.List;

public class GetWorkerProcessor {

    public List<AbstractWorker> getWorkerList() {
        return new AbstractWorkerServiceImpl().getAll();
    }

}
