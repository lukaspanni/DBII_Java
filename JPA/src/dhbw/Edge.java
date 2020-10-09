package dhbw;

import javax.persistence.*;

@Entity
@Table(name = "kanten")
public class Edge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @ManyToOne
    @JoinColumn(name = "f1")
    public Face f1;

    @ManyToOne
    @JoinColumn(name = "f2")
    public Face f2;

    @ManyToOne
    @JoinColumn(name = "p1")
    public Point p1;

    @ManyToOne
    @JoinColumn(name = "p2")
    public Point p2;

    public double getLength() {
        return Math.sqrt(
                Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2)
        );
    }

    public Edge() {

    }

    @Override
    public String toString() {
        return String.format("E(%d)", id);
    }
}
