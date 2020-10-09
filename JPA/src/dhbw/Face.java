package dhbw;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flaechen")
public class Face {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public Face() {

    }

    @ManyToOne
    @JoinColumn(name = "poly")
    public Polyeder polyeder;

    @ManyToMany
    @JoinTable(name = "flaechen_kanten",
            joinColumns = {@JoinColumn(name = "flaeche", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "kante", referencedColumnName = "id")})
    public List<Edge> edges = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("F(%d)", id);
    }
}
