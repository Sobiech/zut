package pl.zut.zjava.server.strategy;

import pl.zut.zjava.entity.Worker;

import java.util.List;

public interface ConnectionStrategy {

    List<Worker> getWorkerList(String host, Integer port, String sid)
            throws Exception;

}
