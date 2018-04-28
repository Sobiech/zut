package pl.zut.zjava.entity;

import java.math.BigDecimal;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

@Table( name = "handlowiec", schema = "javaz", description = "Handlowiec" )
public class Handlowiec extends Pracownik {

	private static final long serialVersionUID = -2056805239800720312L;

	@Column(name = "stawka_prowizji", description = "Prowizja (%)")
	private BigDecimal stawkaProwizji;

	@Column(name = "limit_prowizji", description = "Limit prowizji/miesi¹c (z³)")
	private Integer limitProwizji;



	public BigDecimal getStawkaProwizji() {
		return stawkaProwizji;
	}

	public void setStawkaProwizji(BigDecimal stawkaProwizji) {
		this.stawkaProwizji = stawkaProwizji;
	}

	public Integer getLimitProwizji() {
		return limitProwizji;
	}

	public void setLimitProwizji(Integer limitProwizji) {
		this.limitProwizji = limitProwizji;
	}


	/** strings **/

	public String getStawkaProwizjiStr() {
		return String.valueOf(stawkaProwizji);
	}

	public void setStawkaProwizjiStr(String stawkaProwizji) {
		this.stawkaProwizji = BigDecimal.valueOf(Float.valueOf(stawkaProwizji));
	}

	public String getLimitProwizjiStr() {
		return String.valueOf(limitProwizji);
	}

	public void setLimitProwizjiStr(String limitProwizji) {
		this.limitProwizji = Integer.parseInt(limitProwizji);
	}


	@Override
	protected StringBuilder getData() {

		StringBuilder data = super.getData();

		if(stawkaProwizji != null)
			data.append("Stawka prowizji: ").append(stawkaProwizji).append("\n");

		if(limitProwizji != null)
			data.append("Limit prowizji : ").append(limitProwizji).append("\n");

		return data;
	}

}
