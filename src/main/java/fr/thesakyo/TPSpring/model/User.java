package fr.thesakyo.TPSpring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;

    private String lastname;

    private String mail;

    private String password;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstname;  }
    public void setFirstName(String firstname) {  this.firstname = firstname; }

    public String getLastName() { return lastname; }
    public void setLastName(String lastname) { this.lastname = lastname; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail;  }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;  }

    /********************************************************************************/
    /********************************************************************************/

    @Override
    public final boolean equals(Object o) {

        if(this == o) return true;
        if(o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();

        if(thisEffectiveClass != oEffectiveClass) return false;

        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() { return getClass().hashCode(); }
}