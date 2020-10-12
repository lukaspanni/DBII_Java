
package dhbw;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BlogPost")
public class BlogPost extends Publication
{
	public String url;

	public BlogPost() {
	}
	
	@Override
	public String toString() {
		return String.format("BlogPost(%s)", title);
	}

}

