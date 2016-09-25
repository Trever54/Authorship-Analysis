import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Test Class for Author.java
 * @author Trever Mock
 * @version 2.0
 */
public class AuthorTest {

    /**
     * Checks that the constructor for the Author class works as intended
     */
    @Test
    public void authorConstructorTest() {
        Author test = new Author("Test");
        assertTrue(test.getAvgSentenceLength() == -1);
        assertTrue(test.toString().equals("Test"));
    }
    
    /**
     * Validates the average word length is being calculated
     * accurately based on the word map.
     */
    @Test
    public void averageWordLengthTest() {
        Author author = new Author("author");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("the", new Double(7));
        author.setWordMap(map);
        assertTrue(author.getAvgWordLength() == 3);
        map.put("so", new Double(7));
        map.put("hello", new Double(1));
        author.setWordMap(map);
        double answer = (41 / 15);
        assertTrue((int) author.getAvgWordLength() == (int) answer);  
    }
    
    /**
     * Validates that the word to sentence ratio method is calculating
     * the correct value.
     */
    @Test
    public void wordToSentenceRatioTest() {
        Author author = new Author("author");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("test", new Double(10));
        author.setWordMap(map);
        author.setAvgSentenceLength(2);
        assertTrue(author.getAvgSentenceLength() == 2); 
    }
    
    /**
     * Validates that vocabulary richness is being calculated
     * correctly.
     */
    @Test
    public void vocabularyRichnessTest() {
        Author author = new Author("author");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("RichWord", new Double(1));
        author.setWordMap(map);
        assertTrue(author.getVocabularyRichness().equals(new Double(1)));
        map.put("Other", new Double(6));
        map.put("hello", new Double(12));
        map.put("test", new Double(2));
        author.setWordMap(map);
        assertTrue(author.getVocabularyRichness().equals(new Double(0.25)));
    }
    
    /**
     * Validates that the letter Frequencies are being
     * calculated correctly.
     */
    @Test
    public void letterFrequencyTest() {
        Author author = new Author("author");
        Map<Character, Double> map = new HashMap<Character, Double>();
        map.put('A', new Double(3));
        map.put('B', new Double(22));
        map.put('C', new Double(13));
        map.put('D', new Double(2));
        map.put('E', new Double(55));
        map.put('F', new Double(54));
        map.put('G', new Double(35));
        author.setLetterMap(map);
        assertTrue(author.getMostFrequentLetter().equals('E'));
        assertTrue(author.getLeastFrequentLetter().equals('D'));
    }
    
    /**
     * Validates that the letter Pair Frequencies are being
     * calculated correctly.
     */
    @Test
    public void letterPairFrequencyTest() {
        Author author = new Author("author");
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("ht", new Double(1));
        map.put("fg", new Double(18));
        map.put("zx", new Double(12));
        map.put("qr", new Double(4));
        map.put("eo", new Double(9));
        author.setLetterPairMap(map);
        assertTrue(author.getMostFrequentLetterPair().equals("fg"));
        assertTrue(author.getLeastFrequentLetterPair().equals("ht"));
    }
}
