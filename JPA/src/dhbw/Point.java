package dhbw;

import javax.persistence.*;

@Entity
@Table(name = "punkte")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public double x;
    public double y;
    public double z;

    public Point() {
    }

    @Override
    public String toString() {
        return String.format("P(%d: %.0f, %.0f, %.0f)", id, x,y,z);
    }

    public void move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }
}
