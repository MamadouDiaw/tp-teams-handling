package fr.sorbonne.paris.nord.university.api.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "team")
@Access(AccessType.FIELD)
public class TeamEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name",length = 255)
    private String name;

    @Column(name="slogan",length = 255)
    private String slogan;

    public TeamEntity() {

    }
    public TeamEntity(long id ,String name, String slogan) {
        this.id = id ;
        this.name = name;
        this.slogan = slogan;
    }

    public TeamEntity(String name, String slogan) {

        this.name = name;
        this.slogan = slogan;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}
