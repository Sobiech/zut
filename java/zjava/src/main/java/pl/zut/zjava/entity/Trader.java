package pl.zut.zjava.entity;

import pl.zut.zjava.commons.enums.PositionType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = PositionType.Values.HANDLOWIEC )
@XmlRootElement( name = "handlowiec" )
public class Trader extends AbstractWorker {


	private static final long serialVersionUID = -2056805239800720312L;


	@Column(name = "comission_rate")
	private BigDecimal comissionRate;


	@Column(name = "comission_limit")
	private Integer comissionLimit;


	@Override
	protected StringBuilder getData() {

		StringBuilder
			data = super.getData();
			data.append("\tStawka prowizji       : ").append(comissionRate).append("\n");
			data.append("\tLimit prowizji        : ").append(comissionLimit).append("\n");

		return data;
	}

	@XmlElement( name = "stawkaProwizji" )
	public BigDecimal getComissionRate() {
		return comissionRate;
	}

	public void setComissionRate(BigDecimal comissionRate) {
		this.comissionRate = comissionRate;
	}

	@XmlElement( name = "limitProwizji" )
	public Integer getComissionLimit() {
		return comissionLimit;
	}

	public void setComissionLimit(Integer comissionLimit) {
		this.comissionLimit = comissionLimit;
	}
}
