
package dhbw;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Publication
{
	public boolean isHardcover;
	public int numPages;

	public Book() {
	}
	
	@Override
	public String toString() {
		return String.format("Book(%s)", title);
	}
}

