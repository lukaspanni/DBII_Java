package dhbw;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Publication_Type")
public abstract class Publication
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;

	public String title;
	public Date publishingDate;
	
	@ManyToOne
	Author author;
	
	public Publication() {
	}
	
	@Override
	public String toString() {
		return String.format("Publication(%s)", title);
	}
}

