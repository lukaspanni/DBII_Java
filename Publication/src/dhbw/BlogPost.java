
package dhbw;


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

