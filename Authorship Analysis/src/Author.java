import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * This class stores information for an author including
 * metric statistics, maps, and the authors name
 * @author Trever Mock
 * @version 2.0
 */
public class Author implements Serializable {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -3811976208381351352L;

    /** The authors name */
    private String name = null;
    
    /** A Map containing words the author uses to their relative frequencies */
    private Map<String, Double> wordMap = new HashMap<String, Double>();
    
    /** A Map containing letters the author uses to their relative frequencies */
    private Map<Character, Double> letterMap = new HashMap<Character, Double>();
    
    /** A Map containing letter pairs to their relative frequencies */
    private Map<String, Double> letterPairMap = new HashMap<String, Double>();
    
    /** Average Sentence Length recorded for this authors works. */
    private double avgSentenceLength = -1;
    
    /**
     * Constructor that sets the authors name.
     * @param name - the name of the author
     */
    public Author(String name) {
        this.name = name;
        this.avgSentenceLength = -1;
    }
    
    /**
     * Gets the authors name for this object.
     * @return the authors name
     */
    private String getName() {
        return name;
    }

    /**
     * Calculates and returns the average word length
     * for this author based on the map of words to
     * frequencies stored in the WordMap variable.
     * @return the average word length this author uses
     */
    public double getAvgWordLength() {
        String word;
        double lengthSum = 0;
        double numberOfWords = 0;
        Iterator<String> itr = this.getWordMap().keySet().iterator();
        while (itr.hasNext()) {
            word = (String) itr.next();
            lengthSum = (double) (lengthSum + (word.length() * this.getWordMap().get(word)));
            numberOfWords = (double) (numberOfWords + this.getWordMap().get(word));    
        }
        return (lengthSum/numberOfWords);
    }
    
    /**
     * Calculates and returns the ratio of average
     * word length to average sentence length.
     * @return average word length / average sentence length
     */
    public double getWordToSentenceRatio() {
        return (this.getAvgWordLength() / this.avgSentenceLength);
    }
    
    /** 
     * Get the Word Map 
     * @return the Word Map
     */
    public Map<String, Double> getWordMap() {
        return this.wordMap;
    }
    
    /** 
     * Get the Letter Map
     * @return the Letter Map
     */
    public Map<Character, Double> getLetterMap() {
        return this.letterMap;
    }
    
    /** 
     * Get the Letter Pair Map
     * @return the Letter Pair Map
     */
    public Map<String, Double> getLetterPairMap() {
        return this.letterPairMap;
    }

    /**
     * Gets the average sentence length for this author.
     * @return the average sentence length for this author
     */
    public double getAvgSentenceLength() {
        return avgSentenceLength;
    }

    /** 
     * Sets the average sentence length for this author.
     * @param avgSentenceLength - the new average sentence length for this author
     */
    public void setAvgSentenceLength(double avgSentenceLength) {
        this.avgSentenceLength = avgSentenceLength;
    }
    
    /** 
     * Sets the Word Map for this author.
     * @param map - the new Word Map for this author
     */
    public void setWordMap(Map<String, Double> map) {
        this.wordMap = map;
    }
    
    /** 
     * Sets the Letter Map for this author.
     * @param map - the new Letter Map for this author
     */
    public void setLetterMap(Map<Character, Double> map) {
        this.letterMap = map;
    }
    
    /** 
     * Sets the Letter Pair Map for this author.
     * @param map - the new Letter Pair Map for this author
     */
    public void setLetterPairMap(Map<String, Double> map) {
        this.letterPairMap = map;
    }
    
    /**
     * Calculates and returns the Vocabulary Richness
     * for this author. Vocabulary Richness is calculated
     * by dividing the number of words with 1 frequency
     * by the total number of words in the map.
     * @return - the Vocabulary Richness Metric
     */
    @SuppressWarnings({ "rawtypes" })
    public Double getVocabularyRichness() {  
        Iterator itr = this.getWordMap().keySet().iterator();
        double richWords = 0;
        while (itr.hasNext()) {
            Double next = this.getWordMap().get(itr.next());
            if (next.equals(new Double(1))) {
                richWords++;
            }
        }
        return ((double) richWords) / ((double) this.getWordMap().size());
    }
    
    /**
     * Calculate and return the most frequent letter used by this author.
     * @return the most frequent letter
     */
    @SuppressWarnings({ "rawtypes" })
    public Character getMostFrequentLetter() {  
        Iterator itr = this.getLetterMap().values().iterator();
        Double tempMost = new Double(0);
        Double currentValue;
        while (itr.hasNext()) {
            currentValue = (Double) itr.next();
            if ((double) currentValue > (double) tempMost) {
                tempMost = currentValue;
            }
        }
        itr = this.getLetterMap().keySet().iterator();
        Character currentChar;
        while (itr.hasNext()) {
            currentChar = (Character) itr.next();
            if (tempMost.equals(this.letterMap.get(currentChar))) {
                return currentChar;
            }
        }
        return null;
    }
    
    /**
     * Calculate and return the least frequent letter used by this author.
     * @return the least frequent letter
     */
    @SuppressWarnings("rawtypes")
    public Character getLeastFrequentLetter() {
        Iterator itr = this.getLetterMap().values().iterator();
        Double tempLeast = new Double(Integer.MAX_VALUE);
        Double currentValue;
        while (itr.hasNext()) {
            currentValue = (Double) itr.next();
            if ((double) currentValue < (double) tempLeast) {
                tempLeast = currentValue;
            }
        }
        itr = this.getLetterMap().keySet().iterator();
        Character currentChar;
        while (itr.hasNext()) {
            currentChar = (Character) itr.next();
            if (tempLeast.equals(this.letterMap.get(currentChar))) {
                return currentChar;
            }
        }
        return null;
    }
    
    /**
     * Calculate and return the most frequent letter pair used by this author.
     * @return the most frequent letter pair
     */
    @SuppressWarnings("rawtypes")
    public String getMostFrequentLetterPair() {
        Iterator itr = this.getLetterPairMap().values().iterator();
        Double tempMost = new Double(0);
        Double currentValue;
        while (itr.hasNext()) {
            currentValue = (Double) itr.next();
            if ((double) currentValue > (double) tempMost) {
                tempMost = currentValue;
            }
        }
        itr = this.getLetterPairMap().keySet().iterator();
        String currentPair;
        while (itr.hasNext()) {
            currentPair = (String) itr.next();
            if (tempMost.equals(this.letterPairMap.get(currentPair))) {
                return currentPair;
            }
        }
        return null;
    }
    
    /**
     * Calculate and return the least frequent letter pair used by this author.
     * @return the least frequent letter pair
     */
    @SuppressWarnings("rawtypes")
    public String getLeastFrequentLetterPair() {
        Iterator itr = this.getLetterPairMap().values().iterator();
        Double tempLeast = new Double(Integer.MAX_VALUE);
        Double currentValue;
        while (itr.hasNext()) {
            currentValue = (Double) itr.next();
            if ((double) currentValue < (double) tempLeast) {
                tempLeast = currentValue;
            }
        }
        itr = this.getLetterPairMap().keySet().iterator();
        String currentPair;
        while (itr.hasNext()) {
            currentPair = (String) itr.next();
            if (tempLeast.equals(this.letterPairMap.get(currentPair))) {
                return currentPair;
            }
        }
        return null;
    }
    
    /**
     * Renders Author as a string.
     * @return string rendering of this object
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
