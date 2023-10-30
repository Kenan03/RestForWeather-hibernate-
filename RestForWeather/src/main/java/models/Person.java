package models;
import jakarta.persistence.*;
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "email")
    public String email;
    @Column(name = "password")
    public String password;
    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Person(){}
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
