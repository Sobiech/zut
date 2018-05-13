package pl.zut.zjava.jaxb;

import pl.zut.zjava.entity.AbstractWorker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement( name = "pracownicy" )
public class WorkerList
        implements Serializable {

    private List<AbstractWorker> workerList;

    public WorkerList() {
        this.workerList = new ArrayList<>();
    }

    public WorkerList(List<AbstractWorker> workers) {
        this.workerList = workers;
    }

    @XmlElement( name = "pracownik" )
    public List<AbstractWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<AbstractWorker> workerList) {
        this.workerList = workerList;
    }
}
