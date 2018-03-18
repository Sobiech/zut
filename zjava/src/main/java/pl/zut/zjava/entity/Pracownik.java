package pl.zut.zjava.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

@Table( name = "pracownik", schema = "javaz", description = "Pracownik" )
public class Pracownik implements Serializable {
	
	private static final long serialVersionUID = -3890684154176837336L;

	@Column( name = "id", 			 description = "Identyfikator PESEL",  primaryKey = Column.IS_PRIMARY_KEY )
	protected String pesel;
	
	@Column( name = "imie", 		 description = "Imię" )
	protected String imie;
	
	@Column( name = "nazwisko", 	 description = "Nazwisko" )
	protected String nazwisko;

	@Column( name = "wynagrodzenie", description = "Wynagrodzenie (zł)" )
	protected BigDecimal wynagrodzenie;
	
	@Column( name = "stanowisko", 	 description = "Stanowisko" )
	protected String stanowisko;

	@Column( name = "telefon", 		 description = "Numer telefonu" )
	protected String telefon;
	
	@Override
	public String toString() {
		return "Pracownik [pesel=" + pesel + ", imie=" + imie + ", nazwisko=" + nazwisko + ", wynagrodzenie="
				+ wynagrodzenie + ", stanowisko=" + stanowisko + ", telefon=" + telefon + "]";
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public BigDecimal getWynagrodzenie() {
		return wynagrodzenie;
	}

	public void setWynagrodzenie(BigDecimal wynagrodzenie) {
		this.wynagrodzenie = wynagrodzenie;
	}

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}

