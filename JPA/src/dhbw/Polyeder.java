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
        faces.forEach(f -> edges.addAll(f.edges));
        return edges.stream().mapToDouble(Edge::getLength).sum();
    }

    public void move(double dx, double dy, double dz) {
        Set<Point> points = new HashSet<>();
        faces.forEach(f -> f.edges.forEach(e -> {
            points.add(e.p1);
            points.add(e.p1);
        }));
        points.forEach(p -> p.move(dx, dy, dz));
    }

    @Override
    public String toString() {
        return String.format("POL(%d,%s)", id, name);
    }
}
