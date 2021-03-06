package pl.zut.zjava.entity;

import pl.zut.zjava.commons.enums.PositionType;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import static pl.zut.zjava.commons.enums.PositionType.Values.PRACOWNIK;

@Entity
@Table(name = "worker", schema = "javaz" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "position_name" , discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(PRACOWNIK)
@XmlRootElement( name = "pracownik " )
public class Worker implements Serializable {

	@Id
	@Column( name = "id", nullable = false)
	protected String pesel;

	@Column( name = "first_name", nullable = false )
	protected String firstName;
	
	@Column( name = "last_name", nullable = false )
	protected String lastName;

	@Column( name = "salary" )
	protected BigDecimal salary;
	
	@Column( name = "position_name", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	protected PositionType positionType;

	@Column( name = "phone" )
	protected String phone;

	public Worker() {}

	@Override
	public String toString() {
		return getFullData();
	}

	@XmlAttribute( name = "id" )
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@XmlElement( name = "imie" )
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement( name = "nazwisko" )
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement( name = "wynagrodzenie" )
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@XmlElement( name = "stanowisko" )
	public PositionType getPositionType() {
		return positionType;
	}

	public void setPositionType(PositionType positionType) {
		this.positionType = positionType;
	}

	@XmlElement( name = "numerTelefonu" )
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	@Transient
	public String getFullData() {

		StringBuilder fullData =
			new StringBuilder("-----------------------------------------------\n")
				.append(getData())
				.append("-----------------------------------------------\n");

		return fullData.toString();
	}



	@Transient
	protected StringBuilder getData() {

		StringBuilder data = new StringBuilder();
		data.append("\tPESEL          : ").append(pesel).append("\n");
		data.append("\tImie           : ").append(firstName).append("\n");
		data.append("\tNazwisko       : ").append(lastName).append("\n");
		data.append("\tWynagrodzenie  : ").append(salary).append(" zl\n");
		data.append("\tStanowisko     : ").append(positionType.name()).append("\n");
		data.append("\tTelefon        : ").append(phone).append("\n");

		return data;
	}
}

