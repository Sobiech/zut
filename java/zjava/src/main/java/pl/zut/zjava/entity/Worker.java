package pl.zut.zjava.entity;

import pl.zut.zjava.commons.enums.PositionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue(value = PositionType.Values.PRACOWNIK )
@XmlRootElement( name = "pracownik" )
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker extends AbstractWorker {


}
