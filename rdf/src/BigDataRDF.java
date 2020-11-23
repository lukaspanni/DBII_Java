import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class BigDataRDF {

    static final String FILE = "web-crawler/web-crawler-0001.rdf";
    private static final String TESTURL = "http://data.linkedmdb.org/resource/film/7129";

    public static void main(String[] args)  {
        try {
            InputStream inputStream = new FileInputStream(FILE);
            Model model = Rio.parse(inputStream, "", RDFFormat.NQUADS);

            ValueFactory valueFactory = SimpleValueFactory.getInstance();
            IRI page = valueFactory.createIRI(TESTURL);

            System.out.println("\nLinks From Page: ");
            dumpLinksFromPage(model, page);
            System.out.println("\nLinks To Page: ");
            dumpLinksToPage(model, page);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void dumpLinksToPage(Model model, IRI page) {
        Model filtered = model.filter(null, null, page);
        filtered.forEach(st -> Rio.write(st, System.out, RDFFormat.TURTLE));
    }

    private static void dumpLinksFromPage(Model model, IRI page) {
        Model filtered = model.filter(page, null, null);
        filtered.forEach(st -> Rio.write(st, System.out, RDFFormat.TURTLE));
    }
}
