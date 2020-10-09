package dhbw;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "polyeder")
public class Polyeder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String name;

    public Polyeder() {
    }

    @OneToMany(mappedBy = "polyeder")
    public List<Face> faces = new ArrayList<>();

    public double getPerimeter() {
        Set<Edge> edges = new HashSet<>();
        for (Face f : faces) {
            edges.addAll(f.edges);
        }
        return edges.stream().mapToDouble(Edge::getLength).sum();
    }

    @Override
    public String toString() {
        return String.format("POL(%d,%s)", id, name);
    }
}
