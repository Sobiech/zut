package pl.zut.zjava.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

@Table( name = "pracownik", schema = "javaz", description = "Pracownik" )
public class Pracownik implements Serializable {
	
	private static final long serialVersionUID = -3890684154176837336L;

	@Column( name = "id", 			 description = "Identyfikator PESEL",  primaryKey = Column.PRIMARY_KEY )
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
		return Objects.isNull(imie ) ? "" : imie + " " + ( Objects.isNull(nazwisko) ? "" : nazwisko) ;
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

	public String getSalary() {
		return String.valueOf(wynagrodzenie);
	}

	public void setSalary(String wynagrodzenie) {
		this.wynagrodzenie = BigDecimal.valueOf(Float.parseFloat(wynagrodzenie));
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


	public String getFullData() {

		StringBuilder fullData =
			new StringBuilder("----------------------------------\n")
				.append(getData())
				.append("----------------------------------\n");

		return fullData.toString();
	}


	protected StringBuilder getData() {

		StringBuilder data = new StringBuilder();
		if(getPesel() != null)
			data.append("Identyfikator  : ").append(getPesel()).append("\n");

		if(getImie() != null)
			data.append("Imie           : ").append(getImie()).append("\n");

		if(getNazwisko() != null)
			data.append("Nazwisko       : ").append(getNazwisko()).append("\n");

		if(getWynagrodzenie() != null)
			data.append("Wynagrodzenie  : ").append(getWynagrodzenie()).append(" zl\n");

		if(getStanowisko() != null)
			data.append("Stanowisko     : ").append(getStanowisko()).append("\n");

		if(getTelefon() != null)
			data.append("Telefon        : ").append(getTelefon()).append("\n");

		return data;
	}
}

