package pl.zut.zjava.server.processor;

import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.entity.service.impl.WorkerServiceImpl;

import java.util.List;

public class GetWorkerFrameProcessor {

    public List<Worker> getWorkerList() {
        return new WorkerServiceImpl().getAll();
    }

}
