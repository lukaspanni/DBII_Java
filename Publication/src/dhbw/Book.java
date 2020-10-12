
package dhbw;


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

