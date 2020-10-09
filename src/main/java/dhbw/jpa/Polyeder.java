package dhbw.jpa;

import javax.persistence.*;


@Entity
@Table(name="polyeder")
public class Polyeder
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;

	public String name;
 
	public Polyeder() {
	}
    
	public double getPerimeter() {
		return 0.0;
	}
      
	@Override
	public String toString() {
		return String.format("POL(%d,%s)", id, name);
	}
}
