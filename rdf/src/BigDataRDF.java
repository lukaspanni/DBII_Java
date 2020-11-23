import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class BigDataRDF {

    static final String FILE = "web-crawler/web-crawler-0001.rdf";
    private static final String TESTURL = "http://data.linkedmdb.org/resource/film/7129";

    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream(FILE);
            Model model = Rio.parse(inputStream, "", RDFFormat.NQUADS);

            ValueFactory valueFactory = SimpleValueFactory.getInstance();
            IRI page = valueFactory.createIRI(TESTURL);

            System.out.println("\nLinks From Page: ");
            dumpLinksFromPage(model, page);
            System.out.println("\nLinks To Page: ");
            dumpLinksToPage(model, page);
            System.out.println("\nMost Incoming Links to: ");
            findMostIncomingLinks(model);
            System.out.println("\nExternal Link Count: ");
            countExternalLinks(model);

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

    private static void findMostIncomingLinks(Model model) {

        Map<IRI, Integer> map = new HashMap<>();
        model.stream()
                .map(Statement::getObject)
                .filter(v -> v instanceof IRI)
                .map(v -> (IRI) v)
                .forEach(i -> map.merge(i, 1, Integer::sum));
        IRI max = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        System.out.println(max);
    }

    private static void countExternalLinks(Model model) {
        int externalLinks = 0;
        for (Statement st : model) {
            Resource s = st.getSubject();
            Value o = st.getObject();
            if (!(s instanceof IRI && o instanceof IRI)) continue;
            IRI subj = (IRI) s;
            IRI obj = (IRI) o;

            String subjDomain = getDomain(subj);
            String objDomain = getDomain(obj);
            if(!subjDomain.equals(objDomain)){
                externalLinks++;
            }
        }
        System.out.println(externalLinks);
    }

    private static String getDomain(IRI iri) {
        try {
            return (new URL(iri.toString())).getHost();
        } catch (MalformedURLException e) {
            return "??";
        }
    }
}
