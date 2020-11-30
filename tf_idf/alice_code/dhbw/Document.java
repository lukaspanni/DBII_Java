package dhbw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

    private final String fileName;
    private List<String> terms;

    private Map<String, Integer> termCount;
    private int vocabularyCount = 0;

    public Document(String fileName) {
        this.fileName = fileName;
        this.terms = new ArrayList<>();
        this.termCount = new HashMap<>();
    }

    public void addTerm(String term){
        term = term.replaceAll("['.:,;!?]", "").toLowerCase();
        terms.add(term);
        termCount.merge(term, 1, Integer::sum);
        vocabularyCount++;
    }

    public double getTermFrequency(String term){
        if(vocabularyCount == 0 || !termCount.containsKey(term)) {
            return 0;
        }
        return (double)termCount.get(term) / vocabularyCount;
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean contains(String term) {
        return termCount.containsKey(term);
    }
}
