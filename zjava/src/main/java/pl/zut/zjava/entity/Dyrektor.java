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




	/** strings **/

	public String getDodatekSluzbowyStr() {
		return String.valueOf(dodatekSluzbowy);
	}

	public void setDodatekSluzbowyStr(String dodatekSluzbowy) {
		this.dodatekSluzbowy = BigDecimal.valueOf(Float.valueOf(dodatekSluzbowy));
	}


	public String getKartaSluzbowaStr() {
		return String.valueOf(kartaSluzbowa);
	}

	public void setKartaSluzbowaStr(String kartaSluzbowa) {
		this.kartaSluzbowa = Integer.parseInt(kartaSluzbowa);
	}


	public String getLimitKosztowStr() {
		return String.valueOf(limitKosztow);
	}

	public void setLimitKosztowStr(String limitKosztow) {
		this.limitKosztow = Integer.parseInt(limitKosztow);
	}


	@Override
	protected StringBuilder getData() {

		StringBuilder data = super.getData();

		if(dodatekSluzbowy != null)
			data.append("Dodatek sluzbowy  : ").append(dodatekSluzbowy).append("\n");

		if(kartaSluzbowa != null)
			data.append("Karta sluzbowa : ").append(kartaSluzbowa).append("\n");

		if(limitKosztow != null)
			data.append("Limit kosztow  : ").append(limitKosztow).append("\n");

		return data;
	}

}

