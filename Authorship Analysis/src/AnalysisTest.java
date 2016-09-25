import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Class for Analysis.java
 * <p>
 * This test class requires certain text files
 * which should be provided with this test class.
 * <p>
 * Adjust the paths as necessary to allow this
 * test class to function.
 * @author Trever Mock
 * @version 2.0
 */
public class AnalysisTest {

    /**
     * The path to the Test1.txt file that will be read in
     * for testing use in this class. The file should be 
     * provided with this test class.
     */
    public String path1 = "C:\\Users\\Trever\\Workspace\\Authorship Analysis\\src\\Test1.txt";
    /**
     * The path to the Test2.txt file that will be read in
     * for testing use in this class. The file should be 
     * provided with this test class.
     */
    public String path2 = "C:\\Users\\Trever\\Workspace\\Authorship Analysis\\src\\Test2.txt";
    /**
     * The path to the Test3.txt file that will be read in
     * for testing use in this class. The file should be 
     * provided with this test class.
     */
    public String path3 = "C:\\Users\\Trever\\Workspace\\Authorship Analysis\\src\\Test3.txt";
    /**
     * The path to the Test4.txt file that will be read in
     * for testing use in this class. The file should be 
     * provided with this test class.
     */
    public String path4 = "C:\\Users\\Trever\\Workspace\\Authorship Analysis\\src\\Test4.txt";
    /**
     * The path to the Test5.txt file that will be read in
     * for testing use in this class. The file should be 
     * provided with this test class.
     */
    public String path5 = "C:\\Users\\Trever\\Workspace\\Authorship Analysis\\src\\Test5.txt";
    
    /**
     * Validates the program is correctly determining
     * the average sentence length when analyzing text files.
     */
    @Test
    public void avgSentenceLengthTest() {
        Author author = new Author("author");
        Analysis.analyze(path1, author);
        assertTrue(author.getAvgSentenceLength() == 4);
    }
    
    /**
     * Validates the program is correctly determining
     * the average word length when analyzing text files.
     */
    @Test
    public void avgWordLengthTest() {
        Author author = new Author("author");
        Analysis.analyze(path2, author);
        assertTrue(author.getAvgWordLength() == 5);
    }
    
    /**
     * Validates the program is correctly determining
     * the word frequencies when analyzing text files.
     */
    @Test
    public void wordMapTest() {
        Author author = new Author("author");
        Analysis.analyze(path3, author);
        assertTrue(author.getWordMap().get("the") == 5);
        assertTrue(author.getWordMap().get("so") == 8);
        assertTrue(author.getWordMap().get("hello") == 2);
        assertTrue(author.getWordMap().get("test") == 1);
    }
    /**
     * Validates the program is correctly determining
     * the letter frequencies when analyzing text files.
     */
    @Test
    public void letterFrequencyTest() {
        Author author = new Author("author");
        Analysis.analyze(path4, author);
        assertTrue(author.getLetterMap().get('s') == 2);
        assertTrue(author.getLetterMap().get('a') == 4);
        assertTrue(author.getLetterMap().get('l') == 6);
        assertTrue(author.getLetterMap().get('y') == 1);
        assertTrue(author.getMostFrequentLetter() == 'l');
        assertTrue(author.getLeastFrequentLetter() == 'y');
    }
    
    /**
     * Validates the program is correctly determining
     * the letter pair frequencies when analyzing text files.
     */
    @Test
    public void letterPairFrequencyTest() {
        Author author = new Author("author");
        Analysis.analyze(path5, author);
        assertTrue(author.getLetterPairMap().get("te") == 3);
        assertTrue(author.getLetterPairMap().get("es") == 2);
        assertTrue(author.getLetterPairMap().get("st") == 2);
        assertTrue(author.getLetterPairMap().get("ti") == 2);
        assertTrue(author.getLetterPairMap().get("in") == 2);
        assertTrue(author.getLetterPairMap().get("ng") == 1);
        assertTrue(author.getMostFrequentLetterPair().equals("te"));
        assertTrue(author.getLeastFrequentLetterPair().equals("ng"));
    }
}
