
package dhbw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name="Author")
public class Author
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	String name;

	//@OneToMany(mappedBy="author")
	//public List<Publication> publications = new ArrayList<Publication>();

	public Author() {
	}
	
	@Override
	public String toString() {
		return String.format("Author(%s)", name);
	}
}

