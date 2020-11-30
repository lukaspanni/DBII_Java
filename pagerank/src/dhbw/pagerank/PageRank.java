
package dhbw.pagerank;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;


public class PageRank
{
	public static final String DIRNAME = "./pagerank_data/";

	public static final String INPUTFILE = "example1.rdf";
	public static final double ALPHA = 0;

	//public static final String INPUTFILE = "example2.rdf";
	//public static final double ALPHA = 0.15;


	public static void main(String args[]) {
		try {
			InputStream input = new FileInputStream(DIRNAME + INPUTFILE);
			Model model = Rio.parse(input, "", RDFFormat.NQUADS);

			System.out.println("Model loaded");

			// Collect subjects, objects and links from our pages into a hashmap
			Map<String,WebPage> pageMap = new HashMap<String,WebPage>();

			System.out.println("Found " + pageMap.size() + " web pages");

			// Put our pages into an array, so we can index them with an integer
			int numPages = pageMap.size();
			WebPage[] pageArray = new WebPage[numPages];
			int i = 0;
			for (WebPage wp : pageMap.values()) {
				pageArray[i++] = wp;
			}
			System.out.println();

			// Sort array to make matrix dumps look as in example
			Arrays.sort(pageArray, (WebPage a, WebPage b) -> a.getIrl().compareTo(b.getIrl()));

			// Create the matrix for iteration
			Matrix m = new Matrix(numPages);

			// Apply ALPHA and dump
			m.dump();
			System.out.println();

			// Create the vector for iteration
			Vector p = new Vector(numPages, 0.0);
			p.dump();
			System.out.println();

			// Run some iterations
			for (i=0; i<10; i++) {
				p.dump();
			}
			System.out.println();

			// Print results
			for (i=0; i<numPages; i++) {
				System.out.println(String.format("%4.1f %s", 100*p.get(i), pageArray[i].getIrl()));
			}

			System.out.println("done.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

