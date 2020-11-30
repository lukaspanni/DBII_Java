
package dhbw.pagerank;


import java.util.HashSet;
import java.util.Set;

public class WebPage
{
	private final String irl;
	private final Set<WebPage> linkedPages;

	public WebPage(String irl) {
		this.irl = irl;
		this.linkedPages = new HashSet<>();
	}

	public String getIrl() {
		return irl;
	}

	public void addLinkTo(WebPage objectPage) {
		linkedPages.add(objectPage);
	}

	public Set<WebPage> getLinkedPages() {
		return linkedPages;
	}

	public boolean hasLinkTo(WebPage webPage) {
		return linkedPages.contains(webPage);
	}

	public int linkCount() {
		return linkedPages.size();
	}
}
