//Alyssa Zhang
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> readFile(String myFile){
        SimpleFile file = new SimpleFile(myFile);
        List<String> wordList = new ArrayList<>();

        for (String line : file) {
            // split line into words
            for (String word : line.split(" ")) {
                word = word.trim();
                if (word.length() > 0) {
                    wordList.add(word);
                }
            }
        }
        return wordList;
    }

    public static void unigram(String file){
        List<String> words = readFile(file);
        WordTable wordTable = new WordTable();
        for(int i = 0; i < words.size(); i ++){
            wordTable.recordWord(words.get(i));
        }
        wordTable.processEntries();
        wordTable.displayCommonWords(10);

        for(int lineNum = 0; lineNum < 10; lineNum ++){
            String randWord = "";
            for(int wordNum = 0; wordNum <10; wordNum ++){
                randWord += wordTable.randomEntry().word + " ";
            }
            System.out.println(randWord);
        }
    }

    public static void bigram(String file){
        List<String> words = readFile(file);
        WordTable wordTable = new WordTable();
        for(int i = 0; i < words.size()-1; i ++){
            wordTable.recordWord(words.get(i));
            wordTable.findEntry(words.get(i)).wordTable.recordWord(words.get(i + 1));
        }

        wordTable.processEntries();
        for(WordEntry wordEntry : wordTable.wordMap.values()){
            wordEntry.wordTable.processEntries();
        }

        for(String word : wordTable.getCommonWords(10)){
            System.out.println(wordTable.findEntry(word));
            for(String word2 : wordTable.findEntry(word).wordTable.getCommonWords(10)){
                System.out.println("       " + wordTable.findEntry(word).wordTable.findEntry(word2));
            }
        }

        WordTable newWordTable = wordTable;
        for(int lineNum = 0; lineNum < 10; lineNum ++){
            String randWord = "";
            String nextRandWord = "";
            for(int wordNum = 0; wordNum <10; wordNum ++){
                randWord = newWordTable.randomEntry().word;
                System.out.print(randWord + " ");
                newWordTable = wordTable.findEntry(randWord).wordTable;
            }
            System.out.println();
        }
    }

    public static void trigram(String file){
        List<String> words = readFile(file);
        WordTable wordTable = new WordTable();
        for(int i = 0; i < words.size()-2; i ++){
            wordTable.recordWord(words.get(i));
            wordTable.findEntry(words.get(i)).wordTable.recordWord(words.get(i + 1));
            wordTable.findEntry(words.get(i)).wordTable.findEntry(words.get(i + 1)).wordTable.recordWord(words.get(i + 2));
        }

        wordTable.processEntries();
        for(WordEntry wordEntry : wordTable.wordMap.values()){
            wordEntry.wordTable.processEntries();
            for(WordEntry wordEntry2 : wordEntry.wordTable.wordMap.values()){
                wordEntry2.wordTable.processEntries();
            }
        }

        for(String word : wordTable.getCommonWords(10)){
            System.out.println(wordTable.findEntry(word));
            for(String word2 : wordTable.findEntry(word).wordTable.getCommonWords(10)){
                System.out.println("       " + wordTable.findEntry(word).wordTable.findEntry(word2));
                for(String word3 : wordTable.findEntry(word).wordTable.findEntry(word2).wordTable.getCommonWords(10)){
                    System.out.println("           " + wordTable.findEntry(word).wordTable.findEntry(word2).wordTable.findEntry(word3));
                }
            }
        }

        WordTable wordTable1 = wordTable;
        String randWord1 = wordTable1.randomEntry().word;
        WordTable wordTable2 = wordTable1.findEntry(randWord1).wordTable;
        String randWord2 = wordTable2.randomEntry().word;
        WordTable wordTable3 = wordTable2.findEntry(randWord2).wordTable;

        for(int lineNum = 0; lineNum < 10; lineNum ++){
            for(int wordNum = 0; wordNum <10; wordNum ++){
                String randWord3 = wordTable3.randomEntry().word;
                System.out.print(randWord3 + " ");
                wordTable3 = wordTable.findEntry(randWord2).wordTable.findEntry(randWord3).wordTable;
                randWord2 = randWord3;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // write your code here
        unigram("textFiles/Harry-Potter.txt");
        bigram("textFiles/Harry-Potter.txt");
        trigram("textFiles/Harry-Potter.txt");
    }
}
