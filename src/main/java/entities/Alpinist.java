package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="alpinist")
@NamedQueries({@NamedQuery(name = "Alpinist.byAge", query = "SELECT a FROM Alpinist a WHERE a.age >= :from AND a.age < :to")})
public class Alpinist {

    @Id
   // @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
  //  @JoinColumn(name="id_group", nullable = false)
    @Column
    private int id_alpinist;
    @Column (nullable = false, length = 20)
    private String name;
    @Column (nullable = false, length = 100)
    private String address;
    @Column
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "alpinists")
    private List<Group> groupList= new ArrayList<>();



  //  List<Group> groupList = new ArrayList<>();


    public int getId_alpinist() {
        return id_alpinist;
    }

    public void setId_alpinist(int id_alpinist) {
        this.id_alpinist = id_alpinist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 3) {
            this.name = name;
        }else {
            System.out.println("Слишком короткое название");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 5) {
            this.address = address;
        }else {
            System.out.println("Слишком короткое название");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        }else {
            System.out.println("Слишком короткое название");
        }
    }
}
