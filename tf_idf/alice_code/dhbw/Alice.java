
package dhbw;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Alice {
    public static final String DIRNAME = "./alice_data/";

    private Vocabulary vocabulary = new Vocabulary();

    private List<Document> documents = new ArrayList<>();

    public static void main(String args[]) {

        Alice m = new Alice();
        m.loadDocuments();

        m.search("frowning");
        m.search("wonder");
        m.search("pale house");
        m.search("Off with her head");

        System.out.println("\n\ndone.");
    }

    private void loadDocuments() {
        vocabulary.loadIgnoreWords();

        loadDocument("ch01.txt");
        loadDocument("ch02.txt");
        loadDocument("ch03.txt");
        loadDocument("ch04.txt");
        loadDocument("ch05.txt");
        loadDocument("ch06.txt");
        loadDocument("ch07.txt");
        loadDocument("ch08.txt");
        loadDocument("ch09.txt");
        loadDocument("ch10.txt");
        loadDocument("ch11.txt");
        loadDocument("ch12.txt");
    }

    private void loadDocument(String fileName) {
        try {
            System.out.println("Loading " + fileName);

            Document doc = new Document(fileName);
            InputStream inputStream = new FileInputStream(DIRNAME + fileName);
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNext()) {
                    String term = scanner.next();
                    if (vocabulary.isIgnored(term)) {
                        continue;
                    }
                    doc.addTerm(term);
                }
            }
            documents.add(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<Double, Document> search(String query) {
        List<String> terms = new ArrayList<>();
        //Split query
        try (Scanner scanner = new Scanner(query)) {
            while (scanner.hasNext())
                terms.add(scanner.next().toLowerCase());
        }
        SortedMap<Double, Document> relevantDocuments = new TreeMap<>(Collections.reverseOrder());
        for (Document document : documents) {
            double relevance = 0;
            //compute relevance
            for(String term: terms) {
                double tf = document.getTermFrequency(term);
                double idf = getInverseDocumentFrequency(term);
                relevance += tf * idf;
            }
            //threshold relevance > 1e-7
            if (relevance > 1e-7) {
                relevantDocuments.put(relevance, document);
            }
        }
        relevantDocuments.forEach((rel, doc) -> System.out.format("Relevance for %s in %s: %.6f\n", query, doc.getFileName(), rel));
        return relevantDocuments;
    }

    private double getInverseDocumentFrequency(String term) {
        double documentCount = documents.size();
        long documentContainingCount = documents.stream().filter(d -> d.contains(term)).count();
        return Math.log(documentCount / documentContainingCount);
    }
}