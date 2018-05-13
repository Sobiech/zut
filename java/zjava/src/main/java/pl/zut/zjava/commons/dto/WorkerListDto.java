package pl.zut.zjava.commons.dto;

import pl.zut.zjava.entity.Worker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement( name = "pracownicy" )
public class WorkerListDto
        implements Serializable {

    private List<Worker> workerList;

    public WorkerListDto() {
        this.workerList = new ArrayList<>();
    }

    public WorkerListDto(List<Worker> workers) {
        this.workerList = workers;
    }

    @XmlElement( name = "pracownik" )
    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }
}
