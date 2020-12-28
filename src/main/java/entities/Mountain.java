package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mountains")
@NamedQueries({@NamedQuery(name = "Mountain.byCountry", query = "SELECT m FROM Mountain m WHERE m.country = :country")})
public class Mountain {


    @Id
    @Column
    private int id_mountain;
    @Column (nullable = false, length = 70)
    private String nameOfMountain;
    @Column (nullable = false, length = 100)
    private String country;
    @Column
    private int height;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_group")
    private List<Group> groupList = new ArrayList<>();


    public int getId_mountain() {
        return id_mountain;
    }

    public void setId_mountain(int id_mountain) {
        this.id_mountain = id_mountain;
    }

    public String getNameOfMountain() {

        return nameOfMountain;
    }

    public void setNameOfMountain(String nameOfMountain) {
        if (nameOfMountain.length() > 4) {
            this.nameOfMountain = nameOfMountain;
        }else {
            System.out.println("Слишком короткое название");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.length() > 3) {
            this.country = country;
        }else {
            System.out.println("Слишком короткое название");
        }

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 100) {
            this.height = height;
        }else {
            System.out.println("Слишком короткое название");
        }

    }
}
