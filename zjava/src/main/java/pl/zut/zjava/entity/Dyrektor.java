package pl.zut.zjava.entity;

import java.math.BigDecimal;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

@Table(name = "dyrektor", schema = "javaz", description = "Dyrektor" )
public class Dyrektor extends Pracownik {
	
	private static final long serialVersionUID = -5672867925492755145L;

	
	@Column( name = "dodatek_sluzbowy", description = "Dodatek służbowy (zł)" )
	private BigDecimal dodatekSluzbowy;
	
	
	@Column( name = "karta_sluzbowa", 	description = "Karta służbowa numer" )
	private Integer kartaSluzbowa;
	
	
	@Column( name = "limit_kosztow", 	description = "Limit kosztów/miesiąc (zł)" )
	private Integer limitKosztow;

	
	@Override
	public String toString() {
		return "Dyrektor [dodatekSluzbowy=" + dodatekSluzbowy + ", kartaSluzbowa=" + kartaSluzbowa + ", limitKosztow="
				+ limitKosztow + ", pesel=" + pesel + ", imie=" + imie + ", nazwisko=" + nazwisko + ", wynagrodzenie="
				+ wynagrodzenie + ", stanowisko=" + stanowisko + ", telefon=" + telefon + "]";
	}


	public BigDecimal getDodatekSluzbowy() {
		return dodatekSluzbowy;
	}


	public void setDodatekSluzbowy(BigDecimal dodatekSluzbowy) {
		this.dodatekSluzbowy = dodatekSluzbowy;
	}


	public Integer getKartaSluzbowa() {
		return kartaSluzbowa;
	}


	public void setKartaSluzbowa(Integer kartaSluzbowa) {
		this.kartaSluzbowa = kartaSluzbowa;
	}


	public Integer getLimitKosztow() {
		return limitKosztow;
	}


	public void setLimitKosztow(Integer limitKosztow) {
		this.limitKosztow = limitKosztow;
	}

}

