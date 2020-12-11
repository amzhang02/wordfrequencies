import java.util.HashMap;
import java.util.Map;

public class WordEntry implements Comparable<WordEntry>{
    String word;
    int frequency;
    double probability;
    double cumulativeProbability;
    WordTable wordTable = new WordTable();

    public WordEntry(String word){
        this.word = word;
        this.frequency = 1;
        this.probability = 0.0;
        this.cumulativeProbability = 0.0;
    }

    public int compareTo(WordEntry otherWordEntry) {
        return otherWordEntry.frequency - this.frequency;
    }

    public String toString(){
        return "word: "+word+", frequency: "+frequency+", probability: "+probability+", cumulative prob.: "+cumulativeProbability;
    }
}