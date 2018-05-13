package pl.zut.pswa.entity;

import com.google.common.base.Objects;
import pl.zut.pswa.enums.Role;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users", schema = "pswa")
@SequenceGenerator(name = "User_sequence", sequenceName = "pswa.users_id_seq", allocationSize = 1)
@XmlRootElement( name = "user" )
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

    private static final long serialVersionUID = -8791541530146491503L;


    /**
     * Id uzytkownika.
     */
    @Id
    @GeneratedValue(generator = "User_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * Haslo uzytkownika.
     */
    @Column( name = "password" )
    private String password;

    /**
     * Imie.
     */
    @Column( name = "first_name" )
    @XmlElement( name = "first_name" )
    private String firstName;

    /**
     * Nazwisko.
     */
    @Column( name = "last_name" )
    @XmlElement( name = "last_name" )
    private String lastName;

    /**
     * Adres email.
     */
    private String email;


    /**
     * Nr telefonu.
     */

    @Column(unique = true)
    private String phone;

    /**
     * Czy konto jest aktywne.
     */
    private boolean active;

    /**
     * Data ostatniego logowania do uslugi.
     */
    @Column( name = "last_login" )
    @XmlElement( name = "last_login" )
    private Date lastLogin;


    /**
     * Data utworzenia konta.
     */
    @Column( name = "register_date" )
    @XmlElement( name = "register_date" )
    private Date registerDate;


    /**
     * rola uzytkownika
     */
    @Column( name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", lastLogin=" + lastLogin +
                ", registerDate=" + registerDate +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                Objects.equal(password, user.password) &&
                Objects.equal(firstName, user.firstName) &&
                Objects.equal(lastName, user.lastName) &&
                Objects.equal(email, user.email) &&
                Objects.equal(phone, user.phone) &&
                Objects.equal(lastLogin, user.lastLogin) &&
                Objects.equal(registerDate, user.registerDate) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, password, firstName, lastName, email, phone, active, lastLogin, registerDate, role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}