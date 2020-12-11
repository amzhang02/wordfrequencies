import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordTable {
    Map<String, WordEntry> wordMap = new HashMap<>();
    List<WordEntry> wordEntryList = new ArrayList<>();
    Random randomGen = new Random();

    public WordEntry recordWord(String word){
        boolean wordIn = wordMap.containsKey(word);
        if (wordIn){
            wordMap.get(word).frequency += 1;
        }
        else{
            WordEntry newWordEntry = new WordEntry(word);
            wordMap.put(word, newWordEntry);

        }
        return wordMap.get(word);
    }

    public WordEntry findEntry(String word){
        if(wordMap.containsKey(word)){
            return wordMap.get(word);
        }
        else{
            return null;
        }
    }

    public void processEntries(){
        wordEntryList.addAll(wordMap.values());
        MergeSorter<WordEntry> wordEntryListSorter = new MergeSorter<>();
        wordEntryList = wordEntryListSorter.mergeSort(wordEntryList);
        double totalFrequency = 0.0;
        double pastCumulativeProbability = 0.0;
        for (WordEntry wordEntry : wordEntryList){
            totalFrequency += wordEntry.frequency;
        }
        for (WordEntry wordEntry : wordEntryList){
            wordEntry.probability = wordEntry.frequency/totalFrequency;
            wordEntry.cumulativeProbability =  pastCumulativeProbability + wordEntry.probability;
            pastCumulativeProbability = wordEntry.cumulativeProbability;
         }

    }

    public WordEntry randomEntry(){
        double randomWord = randomGen.nextDouble();
        double previousCumulative = 0.0;
        for (WordEntry wordEntry : wordEntryList){
            if(randomWord >= previousCumulative && randomWord <= wordEntry.cumulativeProbability){
                return wordEntry;
            }
            previousCumulative = wordEntry.cumulativeProbability;
        }
        return null;
    }

    public void displayCommonWords(int topWords){
        for(int numWords = 0; numWords < topWords; numWords ++){
            System.out.println(wordEntryList.get(numWords));
        }
    }

    public ArrayList<String> getCommonWords(int commonWords){
        ArrayList<String> commonWordsList = new ArrayList<>();
        for(int numWords = 0; numWords < commonWords; numWords ++){
            if(numWords < wordEntryList.size()){
                commonWordsList.add(wordEntryList.get(numWords).word);
            }
        }
        return commonWordsList;
    }
}

