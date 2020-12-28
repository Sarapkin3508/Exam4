package entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="groups")
@NamedQueries({@NamedQuery(name = "Group.byMountain", query = "SELECT g FROM Group g JOIN FETCH g.mountain m WHERE m.nameOfMountain = :nameOfMountain "),
        @NamedQuery(name = "Group.byProcess", query = "SELECT g FROM Group g WHERE g.inProcess = :inProcess")})
public class Group {

    @Id
    @Column
    private int id_group;
   // @Column
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(nullable = false)
    private Mountain mountain;

    @ManyToMany
    @JoinTable(name="groups_alpinists",
            joinColumns = @JoinColumn(name="id_group"),
            inverseJoinColumns = @JoinColumn(name="id_alpinist")
    )
    private List<Alpinist> alpinists = new ArrayList<Alpinist>();


    @Column
    private boolean inProcess;
    @Column
    private LocalDate date;
    @Column
    private int duration;



    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Alpinist> getAlpinists() {
        return alpinists;
    }

    public void setAlpinists(List<Alpinist> alpinists) {
        this.alpinists = alpinists;
    }

    public boolean addAlpinist(Alpinist alpinist) {
        alpinists.add(alpinist);
        return true;

    }
}
