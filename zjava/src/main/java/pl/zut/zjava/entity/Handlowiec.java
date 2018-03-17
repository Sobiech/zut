package pl.zut.zjava.entity;

import java.math.BigDecimal;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

@Table( name = "handlowiec", schema = "javaz", description = "Handlowiec" )
public class Handlowiec extends Pracownik {

	private static final long serialVersionUID = -2056805239800720312L;

	@Column( name = "stawka_prowizji", 	description = "Prowizja (%)" )
	private BigDecimal stawkaProwizji;

	@Column( name = "limit_prowizji", 	description = "Limit prowizji/miesi¹c (z³)" )
	private Integer limitProwizji;
	
	@Override
	public String toString() {
		return "Handlowiec [stawkaProwizji=" + stawkaProwizji + ", limitProwizji=" + limitProwizji + ", pesel=" + pesel
				+ ", imie=" + imie + ", nazwisko=" + nazwisko + ", wynagrodzenie=" + wynagrodzenie + ", stanowisko="
				+ stanowisko + ", telefon=" + telefon + "]";
	}

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
	
}
