package dhbw.objectdb;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Point implements Serializable
{  
	private static final long serialVersionUID = 1;

	private double x;
    private double y;
    private double z;
 
    public Point() {
    }
 
    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
 
    public double getX() {
    	return x;
    }
    public double getY() {
    	return y;
    }
    public double getZ() {
    	return z;
    }
     
    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", this.x, this.y, this.z);
    }
}
