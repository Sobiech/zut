package pl.zut.zjava.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

import static pl.zut.zjava.commons.enums.PositionType.Values.DYREKTOR;

@Entity
@DiscriminatorValue(DYREKTOR )
@XmlRootElement( name = "dyrektor" )
public class Director extends Worker {


	private static final long serialVersionUID = -5672867925492755145L;


	@Column( name = "business_allowance" )
	private BigDecimal businessAllowance;


	@Column( name = "business_card" )
	private String businessCard;


	@Column( name = "cost_limit" )
	private Integer costLimit;


	@XmlElement( name = "dodatekSluzbowy" )
	public BigDecimal getBusinessAllowance() {
		return businessAllowance;
	}

	public void setBusinessAllowance(BigDecimal businessAllowance) {
		this.businessAllowance = businessAllowance;
	}

	@XmlElement( name = "kartaSluzbowa" )
	public String getBusinessCard() {
		return businessCard;
	}

	public void setBusinessCard(String businessCard) {
		this.businessCard = businessCard;
	}

	@XmlElement( name = "limitKosztow" )
	public Integer getCostLimit() {
		return costLimit;
	}

	public void setCostLimit(Integer costLimit) {
		this.costLimit = costLimit;
	}



	@Override
	protected StringBuilder getData() {

		StringBuilder
			data = super.getData();
			data.append("\tDodatek sluzbowy      : ").append(businessAllowance).append("\n");
			data.append("\tKarta sluzbowa        : ").append(businessCard).append("\n");
			data.append("\tLimit kosztow         : ").append(costLimit).append("\n");

		return data;
	}

}

